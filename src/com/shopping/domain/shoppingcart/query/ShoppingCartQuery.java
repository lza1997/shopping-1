package com.shopping.domain.shoppingcart.query;

import java.util.Date;

import com.shopping.common.util.page.BaseQuery;

/**
 * Shopping Cart Information Query Class
 * @author WangYue
 *
 */
public class ShoppingCartQuery extends BaseQuery {
	
	/**
	 * shopping cart id
	 */
	private Long id;
	
	/**
	 * user code
	 */
	private String userCode;
	
	/**
	 * commodity id
	 */
	private Long commodityId;

	/**
	 *  commodity code
	 */
	private String commodityCode;
	
	
	/**
     * start time
     */
    private Date startTime;

    /**
     * end time
     */
    private Date endTime;

	
	/**
	 * yes or no Delete
	 */
	private int yn;
	public String getCommodityCode() {
		return commodityCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Long getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getYn() {
		return yn;
	}
	public void setYn(int yn) {
		this.yn = yn;
	}
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}
	
	
}
