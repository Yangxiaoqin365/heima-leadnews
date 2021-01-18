package com.heima.article.apis;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;

public interface ArticleHomeControllerApi {

    /**
     * 加载首页文章
     *
     * @param dto
     * @return
     */
    public ResponseResult load(ArticleHomeDto dto);

    /**
     * 加载更多文章
     *
     * @param dto
     * @return
     */
    public ResponseResult loadMore(ArticleHomeDto dto);

    /**
     * 加载最新文
     * @param dto
     * @return
     */
    public ResponseResult loadNew(ArticleHomeDto dto);
}
