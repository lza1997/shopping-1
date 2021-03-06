package com.haos.service.recentlyviewed.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.haos.common.util.CommonConstant;
import com.haos.common.util.page.PageModel;
import com.haos.common.util.page.ResultCollection;
import com.haos.domain.commodity.Commodity;
import com.haos.domain.recentlyviewed.RecentlyViewed;
import com.haos.domain.recentlyviewed.query.RecentlyViewedQuery;
import com.haos.manager.commodity.CommodityManager;
import com.haos.manager.recentlyviewed.RecentlyViewedManager;
import com.haos.service.recentlyviewed.RecentlyViewedService;

/**
 * RecentlyViewed information DAO implementation
 * @author WangYue
 *
 */
public class RecentlyViewedServiceImpl  implements RecentlyViewedService {
	 private static final Logger log = LogManager.getLogger(RecentlyViewedServiceImpl.class);
	
	/**
	 * Recently Viewed Manager interface
	 */
	private RecentlyViewedManager recentlyViewedManager;
	
	/**
	 * Commodity information Manager interface
	 */
	private CommodityManager  commodityManager;

	@Override
	public ResultCollection addRecentlyViewed(RecentlyViewed recentlyViewed) {
		if(null!=recentlyViewed){
			if(null!=recentlyViewed.getCommodityId()){
				Commodity commodity=commodityManager.getCommodityById(recentlyViewed.getCommodityId());
				if(null!=commodity&&null!=commodity.getId()){
					ResultCollection result = new ResultCollection();
					 recentlyViewed.setCommodityCode(commodity.getCommodityCode()); //set commodity code
					 recentlyViewed.setCommodityName(commodity.getCommodityName()); //set commodity name
					//new price  < old price
          			if(null!=commodity.getNewPrice()&&commodity.getNewPrice()>0){
          				Double price=commodity.getNewPrice()<commodity.getPrice()?commodity.getNewPrice():commodity.getPrice();
          				recentlyViewed.setPrice(price); //set price
          				
          			}else{
          				recentlyViewed.setPrice(commodity.getPrice()); //set price
          			}
					 recentlyViewed.setCommodityPhoto(commodity.getCommodityPhoto()); //set commodity photo
					 recentlyViewed.setViewedCount(commodity.getViewedCount()); //set commodity viewed count
					 recentlyViewed.setCommentCount(commodity.getCommentCount()); //set commodity comment count
					 recentlyViewed.setIsRead(CommonConstant.YN_NO_DELETE);  //set is read =0
					 recentlyViewed.setStatus(commodity.getStatus()); //set commodity status
					 recentlyViewed.setCreateTime(new Date()); //set create time
					 recentlyViewed.setUpdateTime(new Date()); //set update time
					 recentlyViewed.setYn(CommonConstant.YN_NO_DELETE);  //set yn=0
					 try {
						boolean resultFlag = recentlyViewedManager.addRecentlyViewed(recentlyViewed);
						if(resultFlag){
							Long viewedCount=commodity.getViewedCount(); //get viewed count from commodity
							if(null==viewedCount){
								viewedCount=0L;
							}
							viewedCount++; //set viewed count +1
							commodity.setViewedCount(viewedCount); //set viewed count to commodity
							commodityManager.updateCommodityViewedCount(commodity); //call updateCommodityViewedCount()method
							
						}
						result.addDefaultModel("resultFlag", resultFlag);
						result.setSuccess(true);
					} catch (Exception e) {
						log.error("RecentlyViewedServiceImpl -> addRecentlyViewed() error!!", e);
						throw new RuntimeException("RecentlyViewedServiceImpl -> RecentlyViewedServiceImpl -> addRecentlyViewed() error!!");
					}
					return result;
				}else{
					log.error("RecentlyViewedServiceImpl -> commodity  empty!!");
					throw new RuntimeException("RecentlyViewedServiceImpl -> RecentlyViewedServiceImpl -> commodity  empty!!");
				}
				
			}else{
				log.error("RecentlyViewedServiceImpl -> commodity id empty!!");
				throw new RuntimeException("RecentlyViewedServiceImpl -> RecentlyViewedServiceImpl -> commodity id empty!!");
			}
			
			
			
		}else{
			log.error("RecentlyViewedServiceImpl -> recentlyViewed empty!!");
			throw new RuntimeException("RecentlyViewedServiceImpl -> recentlyViewed empty!!");
		}
	}


	@Override
	public ResultCollection queryRecentlyViewedByUserCode(String userCode) {
		ResultCollection result = new ResultCollection();
        try {
            List<RecentlyViewed> recentlyViewedList = recentlyViewedManager.queryRecentlyViewedByUserCode(userCode);
            result.addDefaultModel("recentlyViewedList", recentlyViewedList);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("RecentlyViewedServiceImpl -> queryRecentlyViewedByUserCode() error!!", e);
        }
        return result;
	}


	@Override
	public ResultCollection queryRecentlyViewedListByQueryBean(RecentlyViewedQuery recentlyViewedQuery) {
		 ResultCollection result = new ResultCollection();
	        try {
	            List<RecentlyViewed> recentlyViewedList = recentlyViewedManager.queryRecentlyViewedListByQueryBean(recentlyViewedQuery);
	            result.addDefaultModel("recentlyViewedList", recentlyViewedList);
	            result.setSuccess(true);
	        } catch (Exception e) {
	            log.error("RecentlyViewedServiceImpl -> queryRecentlyViewedListByQueryBean() error!!", e);
	        }
	        return result;
	}
	
	@Override
	public ResultCollection queryRecentlyViewedListPageByQueryBean(RecentlyViewedQuery recentlyViewedQuery,
			int pageIndex, int pageSize) {
		ResultCollection result = new ResultCollection();
		if(null==recentlyViewedQuery){
			recentlyViewedQuery = new RecentlyViewedQuery();
		}
        try {
        	PageModel<RecentlyViewed> recentlyViewedList = recentlyViewedManager.queryRecentlyViewedListPageByQueryBean(recentlyViewedQuery,pageIndex,pageSize);
            result.addDefaultModel("recentlyViewedList", recentlyViewedList);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error("RecentlyViewedServiceImpl -> queryRecentlyViewedListPageByQueryBean() error!!", e);
        }
        return result;
	}

	@Override
	public ResultCollection deleteRecentlyViewedById(Long id) {
		 ResultCollection result = new ResultCollection();
	        try {
	            boolean resultFlag = recentlyViewedManager.deleteRecentlyViewedById(id);
	            result.addDefaultModel("resultFlag", resultFlag);
	            result.setSuccess(true);
	        } catch (Exception e) {
	            log.error("RecentlyViewedServiceImpl -> deleteRecentlyViewedById() error!!", e);
	        }
	        return result;
	}


	public void setRecentlyViewedManager(RecentlyViewedManager recentlyViewedManager) {
		this.recentlyViewedManager = recentlyViewedManager;
	}


	public void setCommodityManager(CommodityManager commodityManager) {
		this.commodityManager = commodityManager;
	}

	
}
