package com.dao;

import com.entity.RuzhuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.RuzhuView;

/**
 * 入住记录 Dao 接口
 *
 * @author 
 */
public interface RuzhuDao extends BaseMapper<RuzhuEntity> {

   List<RuzhuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
