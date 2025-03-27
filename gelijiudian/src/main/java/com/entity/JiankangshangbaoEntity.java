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
 * 健康上报
 *
 * @author 
 * @email
 */
@TableName("jiankangshangbao")
public class JiankangshangbaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiankangshangbaoEntity() {

	}

	public JiankangshangbaoEntity(T t) {
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
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 健康上报编号
     */
    @ColumnInfo(comment="健康上报编号",type="varchar(200)")
    @TableField(value = "jiankangshangbao_uuid_number")

    private String jiankangshangbaoUuidNumber;


    /**
     * 现在状态
     */
    @ColumnInfo(comment="现在状态",type="int(11)")
    @TableField(value = "jiankangshangbao_types")

    private Integer jiankangshangbaoTypes;


    /**
     * 体温照片
     */
    @ColumnInfo(comment="体温照片",type="varchar(200)")
    @TableField(value = "jiankangshangbao_tiwen_photo")

    private String jiankangshangbaoTiwenPhoto;


    /**
     * 体温
     */
    @ColumnInfo(comment="体温",type="decimal(10,2)")
    @TableField(value = "jiankangshangbao_tiwen")

    private Double jiankangshangbaoTiwen;


    /**
     * 状态详情
     */
    @ColumnInfo(comment="状态详情",type="longtext")
    @TableField(value = "jiankangshangbao_content")

    private String jiankangshangbaoContent;


    /**
     * 上报时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="上报时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间   listShow
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
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：健康上报编号
	 */
    public String getJiankangshangbaoUuidNumber() {
        return jiankangshangbaoUuidNumber;
    }
    /**
	 * 设置：健康上报编号
	 */

    public void setJiankangshangbaoUuidNumber(String jiankangshangbaoUuidNumber) {
        this.jiankangshangbaoUuidNumber = jiankangshangbaoUuidNumber;
    }
    /**
	 * 获取：现在状态
	 */
    public Integer getJiankangshangbaoTypes() {
        return jiankangshangbaoTypes;
    }
    /**
	 * 设置：现在状态
	 */

    public void setJiankangshangbaoTypes(Integer jiankangshangbaoTypes) {
        this.jiankangshangbaoTypes = jiankangshangbaoTypes;
    }
    /**
	 * 获取：体温照片
	 */
    public String getJiankangshangbaoTiwenPhoto() {
        return jiankangshangbaoTiwenPhoto;
    }
    /**
	 * 设置：体温照片
	 */

    public void setJiankangshangbaoTiwenPhoto(String jiankangshangbaoTiwenPhoto) {
        this.jiankangshangbaoTiwenPhoto = jiankangshangbaoTiwenPhoto;
    }
    /**
	 * 获取：体温
	 */
    public Double getJiankangshangbaoTiwen() {
        return jiankangshangbaoTiwen;
    }
    /**
	 * 设置：体温
	 */

    public void setJiankangshangbaoTiwen(Double jiankangshangbaoTiwen) {
        this.jiankangshangbaoTiwen = jiankangshangbaoTiwen;
    }
    /**
	 * 获取：状态详情
	 */
    public String getJiankangshangbaoContent() {
        return jiankangshangbaoContent;
    }
    /**
	 * 设置：状态详情
	 */

    public void setJiankangshangbaoContent(String jiankangshangbaoContent) {
        this.jiankangshangbaoContent = jiankangshangbaoContent;
    }
    /**
	 * 获取：上报时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：上报时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间   listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间   listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Jiankangshangbao{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", jiankangshangbaoUuidNumber=" + jiankangshangbaoUuidNumber +
            ", jiankangshangbaoTypes=" + jiankangshangbaoTypes +
            ", jiankangshangbaoTiwenPhoto=" + jiankangshangbaoTiwenPhoto +
            ", jiankangshangbaoTiwen=" + jiankangshangbaoTiwen +
            ", jiankangshangbaoContent=" + jiankangshangbaoContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
