package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.CaipinEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 菜品
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("caipin")
public class CaipinView extends CaipinEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 菜品类型的值
	*/
	@ColumnInfo(comment="菜品类型的字典表值",type="varchar(200)")
	private String caipinValue;
	/**
	* 二级类型的值
	*/
	@ColumnInfo(comment="二级类型的字典表值",type="varchar(200)")
	private String caipinErjiValue;
	/**
	* 是否上架的值
	*/
	@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
	private String shangxiaValue;




	public CaipinView() {

	}

	public CaipinView(CaipinEntity caipinEntity) {
		try {
			BeanUtils.copyProperties(this, caipinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 菜品类型的值
	*/
	public String getCaipinValue() {
		return caipinValue;
	}
	/**
	* 设置： 菜品类型的值
	*/
	public void setCaipinValue(String caipinValue) {
		this.caipinValue = caipinValue;
	}
	//当前表的
	/**
	* 获取： 二级类型的值
	*/
	public String getCaipinErjiValue() {
		return caipinErjiValue;
	}
	/**
	* 设置： 二级类型的值
	*/
	public void setCaipinErjiValue(String caipinErjiValue) {
		this.caipinErjiValue = caipinErjiValue;
	}
	//当前表的
	/**
	* 获取： 是否上架的值
	*/
	public String getShangxiaValue() {
		return shangxiaValue;
	}
	/**
	* 设置： 是否上架的值
	*/
	public void setShangxiaValue(String shangxiaValue) {
		this.shangxiaValue = shangxiaValue;
	}




	@Override
	public String toString() {
		return "CaipinView{" +
			", caipinValue=" + caipinValue +
			", caipinErjiValue=" + caipinErjiValue +
			", shangxiaValue=" + shangxiaValue +
			"} " + super.toString();
	}
}
