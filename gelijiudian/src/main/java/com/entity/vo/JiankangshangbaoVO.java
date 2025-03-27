package com.entity.vo;

import com.entity.JiankangshangbaoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 健康上报
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jiankangshangbao")
public class JiankangshangbaoVO implements Serializable {
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
     * 健康上报编号
     */

    @TableField(value = "jiankangshangbao_uuid_number")
    private String jiankangshangbaoUuidNumber;


    /**
     * 现在状态
     */

    @TableField(value = "jiankangshangbao_types")
    private Integer jiankangshangbaoTypes;


    /**
     * 体温照片
     */

    @TableField(value = "jiankangshangbao_tiwen_photo")
    private String jiankangshangbaoTiwenPhoto;


    /**
     * 体温
     */

    @TableField(value = "jiankangshangbao_tiwen")
    private Double jiankangshangbaoTiwen;


    /**
     * 状态详情
     */

    @TableField(value = "jiankangshangbao_content")
    private String jiankangshangbaoContent;


    /**
     * 上报时间
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
	 * 设置：健康上报编号
	 */
    public String getJiankangshangbaoUuidNumber() {
        return jiankangshangbaoUuidNumber;
    }


    /**
	 * 获取：健康上报编号
	 */

    public void setJiankangshangbaoUuidNumber(String jiankangshangbaoUuidNumber) {
        this.jiankangshangbaoUuidNumber = jiankangshangbaoUuidNumber;
    }
    /**
	 * 设置：现在状态
	 */
    public Integer getJiankangshangbaoTypes() {
        return jiankangshangbaoTypes;
    }


    /**
	 * 获取：现在状态
	 */

    public void setJiankangshangbaoTypes(Integer jiankangshangbaoTypes) {
        this.jiankangshangbaoTypes = jiankangshangbaoTypes;
    }
    /**
	 * 设置：体温照片
	 */
    public String getJiankangshangbaoTiwenPhoto() {
        return jiankangshangbaoTiwenPhoto;
    }


    /**
	 * 获取：体温照片
	 */

    public void setJiankangshangbaoTiwenPhoto(String jiankangshangbaoTiwenPhoto) {
        this.jiankangshangbaoTiwenPhoto = jiankangshangbaoTiwenPhoto;
    }
    /**
	 * 设置：体温
	 */
    public Double getJiankangshangbaoTiwen() {
        return jiankangshangbaoTiwen;
    }


    /**
	 * 获取：体温
	 */

    public void setJiankangshangbaoTiwen(Double jiankangshangbaoTiwen) {
        this.jiankangshangbaoTiwen = jiankangshangbaoTiwen;
    }
    /**
	 * 设置：状态详情
	 */
    public String getJiankangshangbaoContent() {
        return jiankangshangbaoContent;
    }


    /**
	 * 获取：状态详情
	 */

    public void setJiankangshangbaoContent(String jiankangshangbaoContent) {
        this.jiankangshangbaoContent = jiankangshangbaoContent;
    }
    /**
	 * 设置：上报时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：上报时间
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
