package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.FangjianCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 客房收藏
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("fangjian_collection")
public class FangjianCollectionView extends FangjianCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 类型的值
	*/
	@ColumnInfo(comment="类型的字典表值",type="varchar(200)")
	private String fangjianCollectionValue;

	//级联表 客房
		/**
		* 客房名称
		*/

		@ColumnInfo(comment="客房名称",type="varchar(200)")
		private String fangjianName;
		/**
		* 客房编号
		*/

		@ColumnInfo(comment="客房编号",type="varchar(200)")
		private String fangjianUuidNumber;
		/**
		* 客房照片
		*/

		@ColumnInfo(comment="客房照片",type="varchar(200)")
		private String fangjianPhoto;
		/**
		* 房型
		*/
		@ColumnInfo(comment="房型",type="int(11)")
		private Integer fangjianTypes;
			/**
			* 房型的值
			*/
			@ColumnInfo(comment="房型的字典表值",type="varchar(200)")
			private String fangjianValue;
		/**
		* 剩余房数
		*/

		@ColumnInfo(comment="剩余房数",type="int(11)")
		private Integer fangjianKucunNumber;
		/**
		* 客房原价
		*/
		@ColumnInfo(comment="客房原价",type="decimal(10,2)")
		private Double fangjianOldMoney;
		/**
		* 现价/天
		*/
		@ColumnInfo(comment="现价/天",type="decimal(10,2)")
		private Double fangjianNewMoney;
		/**
		* 客房热度
		*/

		@ColumnInfo(comment="客房热度",type="int(11)")
		private Integer fangjianClicknum;
		/**
		* 客房介绍
		*/

		@ColumnInfo(comment="客房介绍",type="longtext")
		private String fangjianContent;
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
		private Integer fangjianDelete;
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



	public FangjianCollectionView() {

	}

	public FangjianCollectionView(FangjianCollectionEntity fangjianCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, fangjianCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 类型的值
	*/
	public String getFangjianCollectionValue() {
		return fangjianCollectionValue;
	}
	/**
	* 设置： 类型的值
	*/
	public void setFangjianCollectionValue(String fangjianCollectionValue) {
		this.fangjianCollectionValue = fangjianCollectionValue;
	}


	//级联表的get和set 客房

		/**
		* 获取： 客房名称
		*/
		public String getFangjianName() {
			return fangjianName;
		}
		/**
		* 设置： 客房名称
		*/
		public void setFangjianName(String fangjianName) {
			this.fangjianName = fangjianName;
		}

		/**
		* 获取： 客房编号
		*/
		public String getFangjianUuidNumber() {
			return fangjianUuidNumber;
		}
		/**
		* 设置： 客房编号
		*/
		public void setFangjianUuidNumber(String fangjianUuidNumber) {
			this.fangjianUuidNumber = fangjianUuidNumber;
		}

		/**
		* 获取： 客房照片
		*/
		public String getFangjianPhoto() {
			return fangjianPhoto;
		}
		/**
		* 设置： 客房照片
		*/
		public void setFangjianPhoto(String fangjianPhoto) {
			this.fangjianPhoto = fangjianPhoto;
		}
		/**
		* 获取： 房型
		*/
		public Integer getFangjianTypes() {
			return fangjianTypes;
		}
		/**
		* 设置： 房型
		*/
		public void setFangjianTypes(Integer fangjianTypes) {
			this.fangjianTypes = fangjianTypes;
		}


			/**
			* 获取： 房型的值
			*/
			public String getFangjianValue() {
				return fangjianValue;
			}
			/**
			* 设置： 房型的值
			*/
			public void setFangjianValue(String fangjianValue) {
				this.fangjianValue = fangjianValue;
			}

		/**
		* 获取： 剩余房数
		*/
		public Integer getFangjianKucunNumber() {
			return fangjianKucunNumber;
		}
		/**
		* 设置： 剩余房数
		*/
		public void setFangjianKucunNumber(Integer fangjianKucunNumber) {
			this.fangjianKucunNumber = fangjianKucunNumber;
		}

		/**
		* 获取： 客房原价
		*/
		public Double getFangjianOldMoney() {
			return fangjianOldMoney;
		}
		/**
		* 设置： 客房原价
		*/
		public void setFangjianOldMoney(Double fangjianOldMoney) {
			this.fangjianOldMoney = fangjianOldMoney;
		}

		/**
		* 获取： 现价/天
		*/
		public Double getFangjianNewMoney() {
			return fangjianNewMoney;
		}
		/**
		* 设置： 现价/天
		*/
		public void setFangjianNewMoney(Double fangjianNewMoney) {
			this.fangjianNewMoney = fangjianNewMoney;
		}

		/**
		* 获取： 客房热度
		*/
		public Integer getFangjianClicknum() {
			return fangjianClicknum;
		}
		/**
		* 设置： 客房热度
		*/
		public void setFangjianClicknum(Integer fangjianClicknum) {
			this.fangjianClicknum = fangjianClicknum;
		}

		/**
		* 获取： 客房介绍
		*/
		public String getFangjianContent() {
			return fangjianContent;
		}
		/**
		* 设置： 客房介绍
		*/
		public void setFangjianContent(String fangjianContent) {
			this.fangjianContent = fangjianContent;
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
		public Integer getFangjianDelete() {
			return fangjianDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setFangjianDelete(Integer fangjianDelete) {
			this.fangjianDelete = fangjianDelete;
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
		return "FangjianCollectionView{" +
			", fangjianCollectionValue=" + fangjianCollectionValue +
			", fangjianName=" + fangjianName +
			", fangjianUuidNumber=" + fangjianUuidNumber +
			", fangjianPhoto=" + fangjianPhoto +
			", fangjianKucunNumber=" + fangjianKucunNumber +
			", fangjianOldMoney=" + fangjianOldMoney +
			", fangjianNewMoney=" + fangjianNewMoney +
			", fangjianClicknum=" + fangjianClicknum +
			", fangjianContent=" + fangjianContent +
			", fangjianDelete=" + fangjianDelete +
			", yonghuUuidNumber=" + yonghuUuidNumber +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", newMoney=" + newMoney +
			"} " + super.toString();
	}
}
