package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.CartEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 购物车
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("cart")
public class CartView extends CartEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 菜品
		/**
		* 菜品名称
		*/

		@ColumnInfo(comment="菜品名称",type="varchar(200)")
		private String caipinName;
		/**
		* 菜品编号
		*/

		@ColumnInfo(comment="菜品编号",type="varchar(200)")
		private String caipinUuidNumber;
		/**
		* 菜品照片
		*/

		@ColumnInfo(comment="菜品照片",type="varchar(200)")
		private String caipinPhoto;
		/**
		* 菜品类型
		*/
		@ColumnInfo(comment="菜品类型",type="int(11)")
		private Integer caipinTypes;
			/**
			* 菜品类型的值
			*/
			@ColumnInfo(comment="菜品类型的字典表值",type="varchar(200)")
			private String caipinValue;
		/**
		* 二级类型
		*/
		@ColumnInfo(comment="二级类型",type="int(11)")
		private Integer caipinErjiTypes;
			/**
			* 二级类型的值
			*/
			@ColumnInfo(comment="二级类型的字典表值",type="varchar(200)")
			private String caipinErjiValue;
		/**
		* 菜品数量
		*/

		@ColumnInfo(comment="菜品数量",type="int(11)")
		private Integer caipinKucunNumber;
		/**
		* 菜品原价
		*/
		@ColumnInfo(comment="菜品原价",type="decimal(10,2)")
		private Double caipinOldMoney;
		/**
		* 现价/份
		*/
		@ColumnInfo(comment="现价/份",type="decimal(10,2)")
		private Double caipinNewMoney;
		/**
		* 菜品热度
		*/

		@ColumnInfo(comment="菜品热度",type="int(11)")
		private Integer caipinClicknum;
		/**
		* 菜品介绍
		*/

		@ColumnInfo(comment="菜品介绍",type="longtext")
		private String caipinContent;
		/**
		* 是否上架
		*/
		@ColumnInfo(comment="是否上架",type="int(11)")
		private Integer shangxiaTypes;
			/**
			* 是否上架的值
			*/
			@ColumnInfo(comment="是否上架的字典表值",type="varchar(200)")
			private String shangxiaValue;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer caipinDelete;
	//级联表 用户
		/**
		* 用户编号
		*/

		@ColumnInfo(comment="用户编号",type="varchar(200)")
		private String yonghuUuidNumber;
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;
		/**
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 账户状态
		*/
		@ColumnInfo(comment="账户状态",type="int(11)")
		private Integer jinyongTypes;
			/**
			* 账户状态的值
			*/
			@ColumnInfo(comment="账户状态的字典表值",type="varchar(200)")
			private String jinyongValue;



	public CartView() {

	}

	public CartView(CartEntity cartEntity) {
		try {
			BeanUtils.copyProperties(this, cartEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 菜品

		/**
		* 获取： 菜品名称
		*/
		public String getCaipinName() {
			return caipinName;
		}
		/**
		* 设置： 菜品名称
		*/
		public void setCaipinName(String caipinName) {
			this.caipinName = caipinName;
		}

		/**
		* 获取： 菜品编号
		*/
		public String getCaipinUuidNumber() {
			return caipinUuidNumber;
		}
		/**
		* 设置： 菜品编号
		*/
		public void setCaipinUuidNumber(String caipinUuidNumber) {
			this.caipinUuidNumber = caipinUuidNumber;
		}

		/**
		* 获取： 菜品照片
		*/
		public String getCaipinPhoto() {
			return caipinPhoto;
		}
		/**
		* 设置： 菜品照片
		*/
		public void setCaipinPhoto(String caipinPhoto) {
			this.caipinPhoto = caipinPhoto;
		}
		/**
		* 获取： 菜品类型
		*/
		public Integer getCaipinTypes() {
			return caipinTypes;
		}
		/**
		* 设置： 菜品类型
		*/
		public void setCaipinTypes(Integer caipinTypes) {
			this.caipinTypes = caipinTypes;
		}


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
		/**
		* 获取： 二级类型
		*/
		public Integer getCaipinErjiTypes() {
			return caipinErjiTypes;
		}
		/**
		* 设置： 二级类型
		*/
		public void setCaipinErjiTypes(Integer caipinErjiTypes) {
			this.caipinErjiTypes = caipinErjiTypes;
		}


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

		/**
		* 获取： 菜品数量
		*/
		public Integer getCaipinKucunNumber() {
			return caipinKucunNumber;
		}
		/**
		* 设置： 菜品数量
		*/
		public void setCaipinKucunNumber(Integer caipinKucunNumber) {
			this.caipinKucunNumber = caipinKucunNumber;
		}

		/**
		* 获取： 菜品原价
		*/
		public Double getCaipinOldMoney() {
			return caipinOldMoney;
		}
		/**
		* 设置： 菜品原价
		*/
		public void setCaipinOldMoney(Double caipinOldMoney) {
			this.caipinOldMoney = caipinOldMoney;
		}

		/**
		* 获取： 现价/份
		*/
		public Double getCaipinNewMoney() {
			return caipinNewMoney;
		}
		/**
		* 设置： 现价/份
		*/
		public void setCaipinNewMoney(Double caipinNewMoney) {
			this.caipinNewMoney = caipinNewMoney;
		}

		/**
		* 获取： 菜品热度
		*/
		public Integer getCaipinClicknum() {
			return caipinClicknum;
		}
		/**
		* 设置： 菜品热度
		*/
		public void setCaipinClicknum(Integer caipinClicknum) {
			this.caipinClicknum = caipinClicknum;
		}

		/**
		* 获取： 菜品介绍
		*/
		public String getCaipinContent() {
			return caipinContent;
		}
		/**
		* 设置： 菜品介绍
		*/
		public void setCaipinContent(String caipinContent) {
			this.caipinContent = caipinContent;
		}
		/**
		* 获取： 是否上架
		*/
		public Integer getShangxiaTypes() {
			return shangxiaTypes;
		}
		/**
		* 设置： 是否上架
		*/
		public void setShangxiaTypes(Integer shangxiaTypes) {
			this.shangxiaTypes = shangxiaTypes;
		}


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

		/**
		* 获取： 逻辑删除
		*/
		public Integer getCaipinDelete() {
			return caipinDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setCaipinDelete(Integer caipinDelete) {
			this.caipinDelete = caipinDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户编号
		*/
		public String getYonghuUuidNumber() {
			return yonghuUuidNumber;
		}
		/**
		* 设置： 用户编号
		*/
		public void setYonghuUuidNumber(String yonghuUuidNumber) {
			this.yonghuUuidNumber = yonghuUuidNumber;
		}

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}

		/**
		* 获取： 余额
		*/
		public Double getNewMoney() {
			return newMoney;
		}
		/**
		* 设置： 余额
		*/
		public void setNewMoney(Double newMoney) {
			this.newMoney = newMoney;
		}
		/**
		* 获取： 账户状态
		*/
		public Integer getJinyongTypes() {
			return jinyongTypes;
		}
		/**
		* 设置： 账户状态
		*/
		public void setJinyongTypes(Integer jinyongTypes) {
			this.jinyongTypes = jinyongTypes;
		}


			/**
			* 获取： 账户状态的值
			*/
			public String getJinyongValue() {
				return jinyongValue;
			}
			/**
			* 设置： 账户状态的值
			*/
			public void setJinyongValue(String jinyongValue) {
				this.jinyongValue = jinyongValue;
			}


	@Override
	public String toString() {
		return "CartView{" +
			", yonghuUuidNumber=" + yonghuUuidNumber +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", newMoney=" + newMoney +
			", caipinName=" + caipinName +
			", caipinUuidNumber=" + caipinUuidNumber +
			", caipinPhoto=" + caipinPhoto +
			", caipinKucunNumber=" + caipinKucunNumber +
			", caipinOldMoney=" + caipinOldMoney +
			", caipinNewMoney=" + caipinNewMoney +
			", caipinClicknum=" + caipinClicknum +
			", caipinContent=" + caipinContent +
			", caipinDelete=" + caipinDelete +
			"} " + super.toString();
	}
}
