package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApShowBehavior;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApShowBehaviorMapper {
    /**
     * 根据行为实体id和文章列表id查询是否有数据存在
     * @param id
     * @param articleIds
     * @return
     */
    List<ApShowBehavior> selectListByEntryIdAndArticleIds(@Param("entryId") Integer id,@Param("articleIds") Integer[] articleIds);

    /**
     * 保存用户或者设备的行为
     * @param articleIds
     * @param entryId
     */
    void saveShowBehavior(@Param("articleIds") Integer[] articleIds,@Param("entryId") Integer entryId);
}
