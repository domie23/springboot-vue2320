package com.entity.vo;

import com.entity.FangjianEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 客房
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("fangjian")
public class FangjianVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 客房名称
     */

    @TableField(value = "fangjian_name")
    private String fangjianName;


    /**
     * 客房编号
     */

    @TableField(value = "fangjian_uuid_number")
    private String fangjianUuidNumber;


    /**
     * 客房照片
     */

    @TableField(value = "fangjian_photo")
    private String fangjianPhoto;


    /**
     * 房型
     */

    @TableField(value = "fangjian_types")
    private Integer fangjianTypes;


    /**
     * 剩余房数
     */

    @TableField(value = "fangjian_kucun_number")
    private Integer fangjianKucunNumber;


    /**
     * 客房原价
     */

    @TableField(value = "fangjian_old_money")
    private Double fangjianOldMoney;


    /**
     * 现价/天
     */

    @TableField(value = "fangjian_new_money")
    private Double fangjianNewMoney;


    /**
     * 客房热度
     */

    @TableField(value = "fangjian_clicknum")
    private Integer fangjianClicknum;


    /**
     * 客房介绍
     */

    @TableField(value = "fangjian_content")
    private String fangjianContent;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "fangjian_delete")
    private Integer fangjianDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：客房名称
	 */
    public String getFangjianName() {
        return fangjianName;
    }


    /**
	 * 获取：客房名称
	 */

    public void setFangjianName(String fangjianName) {
        this.fangjianName = fangjianName;
    }
    /**
	 * 设置：客房编号
	 */
    public String getFangjianUuidNumber() {
        return fangjianUuidNumber;
    }


    /**
	 * 获取：客房编号
	 */

    public void setFangjianUuidNumber(String fangjianUuidNumber) {
        this.fangjianUuidNumber = fangjianUuidNumber;
    }
    /**
	 * 设置：客房照片
	 */
    public String getFangjianPhoto() {
        return fangjianPhoto;
    }


    /**
	 * 获取：客房照片
	 */

    public void setFangjianPhoto(String fangjianPhoto) {
        this.fangjianPhoto = fangjianPhoto;
    }
    /**
	 * 设置：房型
	 */
    public Integer getFangjianTypes() {
        return fangjianTypes;
    }


    /**
	 * 获取：房型
	 */

    public void setFangjianTypes(Integer fangjianTypes) {
        this.fangjianTypes = fangjianTypes;
    }
    /**
	 * 设置：剩余房数
	 */
    public Integer getFangjianKucunNumber() {
        return fangjianKucunNumber;
    }


    /**
	 * 获取：剩余房数
	 */

    public void setFangjianKucunNumber(Integer fangjianKucunNumber) {
        this.fangjianKucunNumber = fangjianKucunNumber;
    }
    /**
	 * 设置：客房原价
	 */
    public Double getFangjianOldMoney() {
        return fangjianOldMoney;
    }


    /**
	 * 获取：客房原价
	 */

    public void setFangjianOldMoney(Double fangjianOldMoney) {
        this.fangjianOldMoney = fangjianOldMoney;
    }
    /**
	 * 设置：现价/天
	 */
    public Double getFangjianNewMoney() {
        return fangjianNewMoney;
    }


    /**
	 * 获取：现价/天
	 */

    public void setFangjianNewMoney(Double fangjianNewMoney) {
        this.fangjianNewMoney = fangjianNewMoney;
    }
    /**
	 * 设置：客房热度
	 */
    public Integer getFangjianClicknum() {
        return fangjianClicknum;
    }


    /**
	 * 获取：客房热度
	 */

    public void setFangjianClicknum(Integer fangjianClicknum) {
        this.fangjianClicknum = fangjianClicknum;
    }
    /**
	 * 设置：客房介绍
	 */
    public String getFangjianContent() {
        return fangjianContent;
    }


    /**
	 * 获取：客房介绍
	 */

    public void setFangjianContent(String fangjianContent) {
        this.fangjianContent = fangjianContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getFangjianDelete() {
        return fangjianDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setFangjianDelete(Integer fangjianDelete) {
        this.fangjianDelete = fangjianDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
