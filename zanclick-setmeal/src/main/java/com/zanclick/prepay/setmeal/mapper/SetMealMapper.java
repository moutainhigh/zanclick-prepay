package com.zanclick.prepay.setmeal.mapper;

import com.zanclick.prepay.common.base.dao.mybatis.BaseMapper;
import com.zanclick.prepay.setmeal.entity.SetMeal;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Administrator
 * @date 2019-12-03 15:59:44
 **/
@Mapper
public interface SetMealMapper extends BaseMapper<SetMeal,Long> {
    /**
     * 根据套餐编号查询
     *
     * @param packageNo
     * @return
     */
    SetMeal selectByPackageNo(String packageNo);

    void unshelveSetMealByAppId(String appId);

}
