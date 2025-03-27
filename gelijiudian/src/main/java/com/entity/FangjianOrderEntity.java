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
 * 客房预定
 *
 * @author 
 * @email
 */
@TableName("fangjian_order")
public class FangjianOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FangjianOrderEntity() {

	}

	public FangjianOrderEntity(T t) {
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
     * 订单编号
     */
    @ColumnInfo(comment="订单编号",type="varchar(200)")
    @TableField(value = "fangjian_order_uuid_number")

    private String fangjianOrderUuidNumber;


    /**
     * 客房
     */
    @ColumnInfo(comment="客房",type="int(11)")
    @TableField(value = "fangjian_id")

    private Integer fangjianId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 预定天数
     */
    @ColumnInfo(comment="预定天数",type="int(11)")
    @TableField(value = "buy_number")

    private Integer buyNumber;


    /**
     * 入住时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="入住时间",type="timestamp")
    @TableField(value = "fangjian_order_time")

    private Date fangjianOrderTime;


    /**
     * 实付价格
     */
    @ColumnInfo(comment="实付价格",type="decimal(10,2)")
    @TableField(value = "fangjian_order_true_price")

    private Double fangjianOrderTruePrice;


    /**
     * 订单类型
     */
    @ColumnInfo(comment="订单类型",type="int(11)")
    @TableField(value = "fangjian_order_types")

    private Integer fangjianOrderTypes;


    /**
     * 支付类型
     */
    @ColumnInfo(comment="支付类型",type="int(11)")
    @TableField(value = "fangjian_order_payment_types")

    private Integer fangjianOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="订单创建时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
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
	 * 获取：订单编号
	 */
    public String getFangjianOrderUuidNumber() {
        return fangjianOrderUuidNumber;
    }
    /**
	 * 设置：订单编号
	 */

    public void setFangjianOrderUuidNumber(String fangjianOrderUuidNumber) {
        this.fangjianOrderUuidNumber = fangjianOrderUuidNumber;
    }
    /**
	 * 获取：客房
	 */
    public Integer getFangjianId() {
        return fangjianId;
    }
    /**
	 * 设置：客房
	 */

    public void setFangjianId(Integer fangjianId) {
        this.fangjianId = fangjianId;
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
	 * 获取：预定天数
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }
    /**
	 * 设置：预定天数
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：入住时间
	 */
    public Date getFangjianOrderTime() {
        return fangjianOrderTime;
    }
    /**
	 * 设置：入住时间
	 */

    public void setFangjianOrderTime(Date fangjianOrderTime) {
        this.fangjianOrderTime = fangjianOrderTime;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getFangjianOrderTruePrice() {
        return fangjianOrderTruePrice;
    }
    /**
	 * 设置：实付价格
	 */

    public void setFangjianOrderTruePrice(Double fangjianOrderTruePrice) {
        this.fangjianOrderTruePrice = fangjianOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getFangjianOrderTypes() {
        return fangjianOrderTypes;
    }
    /**
	 * 设置：订单类型
	 */

    public void setFangjianOrderTypes(Integer fangjianOrderTypes) {
        this.fangjianOrderTypes = fangjianOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getFangjianOrderPaymentTypes() {
        return fangjianOrderPaymentTypes;
    }
    /**
	 * 设置：支付类型
	 */

    public void setFangjianOrderPaymentTypes(Integer fangjianOrderPaymentTypes) {
        this.fangjianOrderPaymentTypes = fangjianOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "FangjianOrder{" +
            ", id=" + id +
            ", fangjianOrderUuidNumber=" + fangjianOrderUuidNumber +
            ", fangjianId=" + fangjianId +
            ", yonghuId=" + yonghuId +
            ", buyNumber=" + buyNumber +
            ", fangjianOrderTime=" + DateUtil.convertString(fangjianOrderTime,"yyyy-MM-dd") +
            ", fangjianOrderTruePrice=" + fangjianOrderTruePrice +
            ", fangjianOrderTypes=" + fangjianOrderTypes +
            ", fangjianOrderPaymentTypes=" + fangjianOrderPaymentTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
