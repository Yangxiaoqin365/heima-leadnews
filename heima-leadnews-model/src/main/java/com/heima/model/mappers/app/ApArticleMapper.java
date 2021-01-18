package com.heima.model.mappers.app;


import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleSDto;
import com.heima.model.user.pojos.ApUserArticleList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApArticleMapper {

    /**
     * 返回默认文章
     *
     * @param dto
     * @param type
     * @return
     */
    List<ApArticle> loadArticleListByLocation(@Param("dto") ArticleHomeDto dto, @Param("type") Short type);

    /**
     * 返回未登录用户默认文章
     *
     * @param list
     * @return
     */
    List<ApArticle> loadArticleListByIdList(List<ApUserArticleList> list);
}