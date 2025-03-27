package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 客房
 *
 * @author 
 * @email
 */
@TableName("fangjian")
public class FangjianEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FangjianEntity() {

	}

	public FangjianEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 客房名称
     */
    @ColumnInfo(comment="客房名称",type="varchar(200)")
    @TableField(value = "fangjian_name")

    private String fangjianName;


    /**
     * 客房编号
     */
    @ColumnInfo(comment="客房编号",type="varchar(200)")
    @TableField(value = "fangjian_uuid_number")

    private String fangjianUuidNumber;


    /**
     * 客房照片
     */
    @ColumnInfo(comment="客房照片",type="varchar(200)")
    @TableField(value = "fangjian_photo")

    private String fangjianPhoto;


    /**
     * 房型
     */
    @ColumnInfo(comment="房型",type="int(11)")
    @TableField(value = "fangjian_types")

    private Integer fangjianTypes;


    /**
     * 剩余房数
     */
    @ColumnInfo(comment="剩余房数",type="int(11)")
    @TableField(value = "fangjian_kucun_number")

    private Integer fangjianKucunNumber;


    /**
     * 客房原价
     */
    @ColumnInfo(comment="客房原价",type="decimal(10,2)")
    @TableField(value = "fangjian_old_money")

    private Double fangjianOldMoney;


    /**
     * 现价/天
     */
    @ColumnInfo(comment="现价/天",type="decimal(10,2)")
    @TableField(value = "fangjian_new_money")

    private Double fangjianNewMoney;


    /**
     * 客房热度
     */
    @ColumnInfo(comment="客房热度",type="int(11)")
    @TableField(value = "fangjian_clicknum")

    private Integer fangjianClicknum;


    /**
     * 客房介绍
     */
    @ColumnInfo(comment="客房介绍",type="longtext")
    @TableField(value = "fangjian_content")

    private String fangjianContent;


    /**
     * 是否上架
     */
    @ColumnInfo(comment="是否上架",type="int(11)")
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "fangjian_delete")

    private Integer fangjianDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：客房名称
	 */
    public String getFangjianName() {
        return fangjianName;
    }
    /**
	 * 设置：客房名称
	 */

    public void setFangjianName(String fangjianName) {
        this.fangjianName = fangjianName;
    }
    /**
	 * 获取：客房编号
	 */
    public String getFangjianUuidNumber() {
        return fangjianUuidNumber;
    }
    /**
	 * 设置：客房编号
	 */

    public void setFangjianUuidNumber(String fangjianUuidNumber) {
        this.fangjianUuidNumber = fangjianUuidNumber;
    }
    /**
	 * 获取：客房照片
	 */
    public String getFangjianPhoto() {
        return fangjianPhoto;
    }
    /**
	 * 设置：客房照片
	 */

    public void setFangjianPhoto(String fangjianPhoto) {
        this.fangjianPhoto = fangjianPhoto;
    }
    /**
	 * 获取：房型
	 */
    public Integer getFangjianTypes() {
        return fangjianTypes;
    }
    /**
	 * 设置：房型
	 */

    public void setFangjianTypes(Integer fangjianTypes) {
        this.fangjianTypes = fangjianTypes;
    }
    /**
	 * 获取：剩余房数
	 */
    public Integer getFangjianKucunNumber() {
        return fangjianKucunNumber;
    }
    /**
	 * 设置：剩余房数
	 */

    public void setFangjianKucunNumber(Integer fangjianKucunNumber) {
        this.fangjianKucunNumber = fangjianKucunNumber;
    }
    /**
	 * 获取：客房原价
	 */
    public Double getFangjianOldMoney() {
        return fangjianOldMoney;
    }
    /**
	 * 设置：客房原价
	 */

    public void setFangjianOldMoney(Double fangjianOldMoney) {
        this.fangjianOldMoney = fangjianOldMoney;
    }
    /**
	 * 获取：现价/天
	 */
    public Double getFangjianNewMoney() {
        return fangjianNewMoney;
    }
    /**
	 * 设置：现价/天
	 */

    public void setFangjianNewMoney(Double fangjianNewMoney) {
        this.fangjianNewMoney = fangjianNewMoney;
    }
    /**
	 * 获取：客房热度
	 */
    public Integer getFangjianClicknum() {
        return fangjianClicknum;
    }
    /**
	 * 设置：客房热度
	 */

    public void setFangjianClicknum(Integer fangjianClicknum) {
        this.fangjianClicknum = fangjianClicknum;
    }
    /**
	 * 获取：客房介绍
	 */
    public String getFangjianContent() {
        return fangjianContent;
    }
    /**
	 * 设置：客房介绍
	 */

    public void setFangjianContent(String fangjianContent) {
        this.fangjianContent = fangjianContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    /**
	 * 设置：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getFangjianDelete() {
        return fangjianDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setFangjianDelete(Integer fangjianDelete) {
        this.fangjianDelete = fangjianDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Fangjian{" +
            ", id=" + id +
            ", fangjianName=" + fangjianName +
            ", fangjianUuidNumber=" + fangjianUuidNumber +
            ", fangjianPhoto=" + fangjianPhoto +
            ", fangjianTypes=" + fangjianTypes +
            ", fangjianKucunNumber=" + fangjianKucunNumber +
            ", fangjianOldMoney=" + fangjianOldMoney +
            ", fangjianNewMoney=" + fangjianNewMoney +
            ", fangjianClicknum=" + fangjianClicknum +
            ", fangjianContent=" + fangjianContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", fangjianDelete=" + fangjianDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
