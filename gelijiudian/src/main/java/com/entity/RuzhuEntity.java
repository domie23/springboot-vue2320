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
 * 入住记录
 *
 * @author 
 * @email
 */
@TableName("ruzhu")
public class RuzhuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public RuzhuEntity() {

	}

	public RuzhuEntity(T t) {
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
     * 入住编号
     */
    @ColumnInfo(comment="入住编号",type="varchar(200)")
    @TableField(value = "ruzhu_uuid_number")

    private String ruzhuUuidNumber;


    /**
     * 房间号
     */
    @ColumnInfo(comment="房间号",type="varchar(200)")
    @TableField(value = "ruzhu_name")

    private String ruzhuName;


    /**
     * 体温
     */
    @ColumnInfo(comment="体温",type="decimal(10,2)")
    @TableField(value = "ruzhu_tiwen")

    private Double ruzhuTiwen;


    /**
     * 健康码
     */
    @ColumnInfo(comment="健康码",type="varchar(200)")
    @TableField(value = "ruzhu_photo")

    private String ruzhuPhoto;


    /**
     * 附件
     */
    @ColumnInfo(comment="附件",type="varchar(200)")
    @TableField(value = "ruzhu_file")

    private String ruzhuFile;


    /**
     * 入住时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="入住时间",type="timestamp")
    @TableField(value = "ruzhu_time")

    private Date ruzhuTime;


    /**
     * 退房时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="退房时间",type="timestamp")
    @TableField(value = "tuifang_time")

    private Date tuifangTime;


    /**
     * 入住备注
     */
    @ColumnInfo(comment="入住备注",type="longtext")
    @TableField(value = "ruzhu_content")

    private String ruzhuContent;


    /**
     * 上传时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="上传时间",type="timestamp")
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
	 * 获取：入住编号
	 */
    public String getRuzhuUuidNumber() {
        return ruzhuUuidNumber;
    }
    /**
	 * 设置：入住编号
	 */

    public void setRuzhuUuidNumber(String ruzhuUuidNumber) {
        this.ruzhuUuidNumber = ruzhuUuidNumber;
    }
    /**
	 * 获取：房间号
	 */
    public String getRuzhuName() {
        return ruzhuName;
    }
    /**
	 * 设置：房间号
	 */

    public void setRuzhuName(String ruzhuName) {
        this.ruzhuName = ruzhuName;
    }
    /**
	 * 获取：体温
	 */
    public Double getRuzhuTiwen() {
        return ruzhuTiwen;
    }
    /**
	 * 设置：体温
	 */

    public void setRuzhuTiwen(Double ruzhuTiwen) {
        this.ruzhuTiwen = ruzhuTiwen;
    }
    /**
	 * 获取：健康码
	 */
    public String getRuzhuPhoto() {
        return ruzhuPhoto;
    }
    /**
	 * 设置：健康码
	 */

    public void setRuzhuPhoto(String ruzhuPhoto) {
        this.ruzhuPhoto = ruzhuPhoto;
    }
    /**
	 * 获取：附件
	 */
    public String getRuzhuFile() {
        return ruzhuFile;
    }
    /**
	 * 设置：附件
	 */

    public void setRuzhuFile(String ruzhuFile) {
        this.ruzhuFile = ruzhuFile;
    }
    /**
	 * 获取：入住时间
	 */
    public Date getRuzhuTime() {
        return ruzhuTime;
    }
    /**
	 * 设置：入住时间
	 */

    public void setRuzhuTime(Date ruzhuTime) {
        this.ruzhuTime = ruzhuTime;
    }
    /**
	 * 获取：退房时间
	 */
    public Date getTuifangTime() {
        return tuifangTime;
    }
    /**
	 * 设置：退房时间
	 */

    public void setTuifangTime(Date tuifangTime) {
        this.tuifangTime = tuifangTime;
    }
    /**
	 * 获取：入住备注
	 */
    public String getRuzhuContent() {
        return ruzhuContent;
    }
    /**
	 * 设置：入住备注
	 */

    public void setRuzhuContent(String ruzhuContent) {
        this.ruzhuContent = ruzhuContent;
    }
    /**
	 * 获取：上传时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：上传时间
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
        return "Ruzhu{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", ruzhuUuidNumber=" + ruzhuUuidNumber +
            ", ruzhuName=" + ruzhuName +
            ", ruzhuTiwen=" + ruzhuTiwen +
            ", ruzhuPhoto=" + ruzhuPhoto +
            ", ruzhuFile=" + ruzhuFile +
            ", ruzhuTime=" + DateUtil.convertString(ruzhuTime,"yyyy-MM-dd") +
            ", tuifangTime=" + DateUtil.convertString(tuifangTime,"yyyy-MM-dd") +
            ", ruzhuContent=" + ruzhuContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
