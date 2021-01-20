package com.heima.model.mappers.app;

import com.heima.model.behavior.pojos.ApBehaviorEntry;
import org.apache.ibatis.annotations.Param;

public interface ApBehaviorEntryMapper {
    /**
     * 根据用户信息查询行为id
     * @param userId
     * @param equipmentId
     * @return
     */
    ApBehaviorEntry selectByUserIdOrEquipment(@Param("userId") Long userId,@Param("equipmentId") Integer equipmentId);
}
