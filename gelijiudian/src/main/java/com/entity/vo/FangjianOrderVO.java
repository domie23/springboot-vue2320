package com.entity.vo;

import com.entity.FangjianOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 客房预定
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("fangjian_order")
public class FangjianOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单编号
     */

    @TableField(value = "fangjian_order_uuid_number")
    private String fangjianOrderUuidNumber;


    /**
     * 客房
     */

    @TableField(value = "fangjian_id")
    private Integer fangjianId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 预定天数
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 入住时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "fangjian_order_time")
    private Date fangjianOrderTime;


    /**
     * 实付价格
     */

    @TableField(value = "fangjian_order_true_price")
    private Double fangjianOrderTruePrice;


    /**
     * 订单类型
     */

    @TableField(value = "fangjian_order_types")
    private Integer fangjianOrderTypes;


    /**
     * 支付类型
     */

    @TableField(value = "fangjian_order_payment_types")
    private Integer fangjianOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
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
	 * 设置：订单编号
	 */
    public String getFangjianOrderUuidNumber() {
        return fangjianOrderUuidNumber;
    }


    /**
	 * 获取：订单编号
	 */

    public void setFangjianOrderUuidNumber(String fangjianOrderUuidNumber) {
        this.fangjianOrderUuidNumber = fangjianOrderUuidNumber;
    }
    /**
	 * 设置：客房
	 */
    public Integer getFangjianId() {
        return fangjianId;
    }


    /**
	 * 获取：客房
	 */

    public void setFangjianId(Integer fangjianId) {
        this.fangjianId = fangjianId;
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
	 * 设置：预定天数
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：预定天数
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：入住时间
	 */
    public Date getFangjianOrderTime() {
        return fangjianOrderTime;
    }


    /**
	 * 获取：入住时间
	 */

    public void setFangjianOrderTime(Date fangjianOrderTime) {
        this.fangjianOrderTime = fangjianOrderTime;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getFangjianOrderTruePrice() {
        return fangjianOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setFangjianOrderTruePrice(Double fangjianOrderTruePrice) {
        this.fangjianOrderTruePrice = fangjianOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getFangjianOrderTypes() {
        return fangjianOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setFangjianOrderTypes(Integer fangjianOrderTypes) {
        this.fangjianOrderTypes = fangjianOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getFangjianOrderPaymentTypes() {
        return fangjianOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setFangjianOrderPaymentTypes(Integer fangjianOrderPaymentTypes) {
        this.fangjianOrderPaymentTypes = fangjianOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
