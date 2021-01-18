package com.heima.article.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.heima.article.service.AppArticleService;
import com.heima.common.article.constans.ArticleConstans;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.mappers.app.ApArticleMapper;
import com.heima.model.mappers.app.ApUserArticleListMapper;
import com.heima.model.user.pojos.ApUser;
import com.heima.model.user.pojos.ApUserArticleList;
import com.heima.utils.threadlocal.AppThreadLocalUtils;
import org.apache.commons.net.nntp.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.Date;
import java.util.List;

@Service
public class ApparticleServiceImpl implements AppArticleService {

    public static final short MAX_PAGE_SIZE =50;
    /**
     * 加载更多数据
     * @param dto
     * @param type
     * @return
     */
    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        //参数校验
        if (dto == null){
            dto=new ArticleHomeDto();
        }
        //时间校验
        if (dto.getMaxBehotTime() ==null){
            dto.setMaxBehotTime(new Date());
        }
        if (dto.getMinBehotTime() ==null){
            dto.setMinBehotTime(new Date());

        }
        //分页参数校验
        Integer size = dto.getSize();
        if (size == null || size<=0){
            size=20;
        }
        size=Math.min(size,MAX_PAGE_SIZE);
        dto.setSize(size);

        //文章频道参数校验
        if (StringUtils.isEmpty(dto.getTag())){
            dto.setTag(ArticleConstans.DEFAULT_TAG);
        }

        //类型参数校验
        if (!type.equals(ArticleConstans.LOADTYPE_LOAD_MORE)&&!type.equals(ArticleConstans.LOADTYPE_LOAD_NEW)){
            type = ArticleConstans.LOADTYPE_LOAD_MORE;
        }

        //获取用户信息
        ApUser user = AppThreadLocalUtils.getUser();
        //判断用户是否存在
        if (user !=null){
            //存在 已登录 ，加载推荐的文章
            List<ApArticle> apArticleList =getUserArticle(user,dto,type);
            return ResponseResult.okResult(apArticleList);
        }else {
            //不存在 加载默认文章
            List<ApArticle> apArticles =getDefultArticle(dto,type);
            return ResponseResult.okResult(apArticles);
        }

    }
    @Autowired
    private ApArticleMapper apArticleMapper;

    /**
     * 返回默认文章
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getDefultArticle(ArticleHomeDto dto, Short type) {
        return apArticleMapper.loadArticleListByLocation(dto,type);
    }

    @Autowired
    private ApUserArticleListMapper apUserArticleListMapper;
    /**
     * 返回用户推荐的文章，如果没有，就返回默认
     * @param user
     * @param dto
     * @param type
     * @return
     */
    private List<ApArticle> getUserArticle(ApUser user, ArticleHomeDto dto, Short type) {
        List<ApUserArticleList> list =apUserArticleListMapper.loadArticleIdListByUser(user,dto,type);
        if (!list.isEmpty()){
           return apArticleMapper.loadArticleListByIdList(list);
        }else {
            return getDefultArticle(dto, type);
        }

    }
}
