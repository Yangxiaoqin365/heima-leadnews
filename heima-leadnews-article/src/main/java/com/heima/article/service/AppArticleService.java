package com.heima.article.service;

import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.common.dtos.ResponseResult;

public interface AppArticleService {

    /**
     * 加载更多数据
     * @param dto
     * @param type
     * @return
     */
    ResponseResult load(ArticleHomeDto dto, Short type);
}
