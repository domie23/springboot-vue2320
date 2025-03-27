package com.dao;

import com.entity.JiankangshangbaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiankangshangbaoView;

/**
 * 健康上报 Dao 接口
 *
 * @author 
 */
public interface JiankangshangbaoDao extends BaseMapper<JiankangshangbaoEntity> {

   List<JiankangshangbaoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
