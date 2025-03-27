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
 * 菜品
 *
 * @author 
 * @email
 */
@TableName("caipin")
public class CaipinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public CaipinEntity() {

	}

	public CaipinEntity(T t) {
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
     * 菜品名称
     */
    @ColumnInfo(comment="菜品名称",type="varchar(200)")
    @TableField(value = "caipin_name")

    private String caipinName;


    /**
     * 菜品编号
     */
    @ColumnInfo(comment="菜品编号",type="varchar(200)")
    @TableField(value = "caipin_uuid_number")

    private String caipinUuidNumber;


    /**
     * 菜品照片
     */
    @ColumnInfo(comment="菜品照片",type="varchar(200)")
    @TableField(value = "caipin_photo")

    private String caipinPhoto;


    /**
     * 菜品类型
     */
    @ColumnInfo(comment="菜品类型",type="int(11)")
    @TableField(value = "caipin_types")

    private Integer caipinTypes;


    /**
     * 二级类型
     */
    @ColumnInfo(comment="二级类型",type="int(11)")
    @TableField(value = "caipin_erji_types")

    private Integer caipinErjiTypes;


    /**
     * 菜品数量
     */
    @ColumnInfo(comment="菜品数量",type="int(11)")
    @TableField(value = "caipin_kucun_number")

    private Integer caipinKucunNumber;


    /**
     * 菜品原价
     */
    @ColumnInfo(comment="菜品原价",type="decimal(10,2)")
    @TableField(value = "caipin_old_money")

    private Double caipinOldMoney;


    /**
     * 现价/份
     */
    @ColumnInfo(comment="现价/份",type="decimal(10,2)")
    @TableField(value = "caipin_new_money")

    private Double caipinNewMoney;


    /**
     * 菜品热度
     */
    @ColumnInfo(comment="菜品热度",type="int(11)")
    @TableField(value = "caipin_clicknum")

    private Integer caipinClicknum;


    /**
     * 菜品介绍
     */
    @ColumnInfo(comment="菜品介绍",type="longtext")
    @TableField(value = "caipin_content")

    private String caipinContent;


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
    @TableField(value = "caipin_delete")

    private Integer caipinDelete;


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
	 * 获取：菜品名称
	 */
    public String getCaipinName() {
        return caipinName;
    }
    /**
	 * 设置：菜品名称
	 */

    public void setCaipinName(String caipinName) {
        this.caipinName = caipinName;
    }
    /**
	 * 获取：菜品编号
	 */
    public String getCaipinUuidNumber() {
        return caipinUuidNumber;
    }
    /**
	 * 设置：菜品编号
	 */

    public void setCaipinUuidNumber(String caipinUuidNumber) {
        this.caipinUuidNumber = caipinUuidNumber;
    }
    /**
	 * 获取：菜品照片
	 */
    public String getCaipinPhoto() {
        return caipinPhoto;
    }
    /**
	 * 设置：菜品照片
	 */

    public void setCaipinPhoto(String caipinPhoto) {
        this.caipinPhoto = caipinPhoto;
    }
    /**
	 * 获取：菜品类型
	 */
    public Integer getCaipinTypes() {
        return caipinTypes;
    }
    /**
	 * 设置：菜品类型
	 */

    public void setCaipinTypes(Integer caipinTypes) {
        this.caipinTypes = caipinTypes;
    }
    /**
	 * 获取：二级类型
	 */
    public Integer getCaipinErjiTypes() {
        return caipinErjiTypes;
    }
    /**
	 * 设置：二级类型
	 */

    public void setCaipinErjiTypes(Integer caipinErjiTypes) {
        this.caipinErjiTypes = caipinErjiTypes;
    }
    /**
	 * 获取：菜品数量
	 */
    public Integer getCaipinKucunNumber() {
        return caipinKucunNumber;
    }
    /**
	 * 设置：菜品数量
	 */

    public void setCaipinKucunNumber(Integer caipinKucunNumber) {
        this.caipinKucunNumber = caipinKucunNumber;
    }
    /**
	 * 获取：菜品原价
	 */
    public Double getCaipinOldMoney() {
        return caipinOldMoney;
    }
    /**
	 * 设置：菜品原价
	 */

    public void setCaipinOldMoney(Double caipinOldMoney) {
        this.caipinOldMoney = caipinOldMoney;
    }
    /**
	 * 获取：现价/份
	 */
    public Double getCaipinNewMoney() {
        return caipinNewMoney;
    }
    /**
	 * 设置：现价/份
	 */

    public void setCaipinNewMoney(Double caipinNewMoney) {
        this.caipinNewMoney = caipinNewMoney;
    }
    /**
	 * 获取：菜品热度
	 */
    public Integer getCaipinClicknum() {
        return caipinClicknum;
    }
    /**
	 * 设置：菜品热度
	 */

    public void setCaipinClicknum(Integer caipinClicknum) {
        this.caipinClicknum = caipinClicknum;
    }
    /**
	 * 获取：菜品介绍
	 */
    public String getCaipinContent() {
        return caipinContent;
    }
    /**
	 * 设置：菜品介绍
	 */

    public void setCaipinContent(String caipinContent) {
        this.caipinContent = caipinContent;
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
    public Integer getCaipinDelete() {
        return caipinDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setCaipinDelete(Integer caipinDelete) {
        this.caipinDelete = caipinDelete;
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
        return "Caipin{" +
            ", id=" + id +
            ", caipinName=" + caipinName +
            ", caipinUuidNumber=" + caipinUuidNumber +
            ", caipinPhoto=" + caipinPhoto +
            ", caipinTypes=" + caipinTypes +
            ", caipinErjiTypes=" + caipinErjiTypes +
            ", caipinKucunNumber=" + caipinKucunNumber +
            ", caipinOldMoney=" + caipinOldMoney +
            ", caipinNewMoney=" + caipinNewMoney +
            ", caipinClicknum=" + caipinClicknum +
            ", caipinContent=" + caipinContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", caipinDelete=" + caipinDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
