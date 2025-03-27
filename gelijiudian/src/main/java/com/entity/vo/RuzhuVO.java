package com.entity.vo;

import com.entity.RuzhuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 入住记录
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("ruzhu")
public class RuzhuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 入住编号
     */

    @TableField(value = "ruzhu_uuid_number")
    private String ruzhuUuidNumber;


    /**
     * 房间号
     */

    @TableField(value = "ruzhu_name")
    private String ruzhuName;


    /**
     * 体温
     */

    @TableField(value = "ruzhu_tiwen")
    private Double ruzhuTiwen;


    /**
     * 健康码
     */

    @TableField(value = "ruzhu_photo")
    private String ruzhuPhoto;


    /**
     * 附件
     */

    @TableField(value = "ruzhu_file")
    private String ruzhuFile;


    /**
     * 入住时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "ruzhu_time")
    private Date ruzhuTime;


    /**
     * 退房时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "tuifang_time")
    private Date tuifangTime;


    /**
     * 入住备注
     */

    @TableField(value = "ruzhu_content")
    private String ruzhuContent;


    /**
     * 上传时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：入住编号
	 */
    public String getRuzhuUuidNumber() {
        return ruzhuUuidNumber;
    }


    /**
	 * 获取：入住编号
	 */

    public void setRuzhuUuidNumber(String ruzhuUuidNumber) {
        this.ruzhuUuidNumber = ruzhuUuidNumber;
    }
    /**
	 * 设置：房间号
	 */
    public String getRuzhuName() {
        return ruzhuName;
    }


    /**
	 * 获取：房间号
	 */

    public void setRuzhuName(String ruzhuName) {
        this.ruzhuName = ruzhuName;
    }
    /**
	 * 设置：体温
	 */
    public Double getRuzhuTiwen() {
        return ruzhuTiwen;
    }


    /**
	 * 获取：体温
	 */

    public void setRuzhuTiwen(Double ruzhuTiwen) {
        this.ruzhuTiwen = ruzhuTiwen;
    }
    /**
	 * 设置：健康码
	 */
    public String getRuzhuPhoto() {
        return ruzhuPhoto;
    }


    /**
	 * 获取：健康码
	 */

    public void setRuzhuPhoto(String ruzhuPhoto) {
        this.ruzhuPhoto = ruzhuPhoto;
    }
    /**
	 * 设置：附件
	 */
    public String getRuzhuFile() {
        return ruzhuFile;
    }


    /**
	 * 获取：附件
	 */

    public void setRuzhuFile(String ruzhuFile) {
        this.ruzhuFile = ruzhuFile;
    }
    /**
	 * 设置：入住时间
	 */
    public Date getRuzhuTime() {
        return ruzhuTime;
    }


    /**
	 * 获取：入住时间
	 */

    public void setRuzhuTime(Date ruzhuTime) {
        this.ruzhuTime = ruzhuTime;
    }
    /**
	 * 设置：退房时间
	 */
    public Date getTuifangTime() {
        return tuifangTime;
    }


    /**
	 * 获取：退房时间
	 */

    public void setTuifangTime(Date tuifangTime) {
        this.tuifangTime = tuifangTime;
    }
    /**
	 * 设置：入住备注
	 */
    public String getRuzhuContent() {
        return ruzhuContent;
    }


    /**
	 * 获取：入住备注
	 */

    public void setRuzhuContent(String ruzhuContent) {
        this.ruzhuContent = ruzhuContent;
    }
    /**
	 * 设置：上传时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：上传时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
