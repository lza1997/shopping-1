package com.haos.manager.consumer.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.haos.common.util.CommonConstant;
import com.haos.common.util.manager.BaseManager;
import com.haos.common.util.page.PageModel;
import com.haos.common.util.page.PageModelImpl;
import com.haos.dao.consumer.ConsumerRecordsDao;
import com.haos.domain.consumer.ConsumerRecords;
import com.haos.domain.consumer.query.ConsumerRecordsQuery;
import com.haos.manager.consumer.ConsumerRecordsManager;

public class ConsumerRecordsManagerImpl extends BaseManager implements ConsumerRecordsManager {
	

    private static final Logger log = LogManager.getLogger(ConsumerRecordsManagerImpl.class);
	
	private ConsumerRecordsDao consumerRecordsDao;

	@Override
	public boolean addConsumerRecords(final ConsumerRecords consumerRecords) {
        boolean resultFlag = true;
        try {
            TransactionTemplate template = this.getDataSourceTransactionManager();
            template.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                    try {
                        boolean insertFlag = false;
                        if (consumerRecords != null) {
                           
                            insertFlag = consumerRecordsDao.addConsumerRecords(consumerRecords);
                            
                            if (!insertFlag) {
                                throw new RuntimeException("ConsumerRecordsManagerImpl.addConsumerRecords.error.");
                            } 

                        } else {
                            log.debug("ConsumerRecordsManagerImpl.addConsumerRecords.parameter.empty.");
                            throw new RuntimeException("ConsumerRecordsManagerImpl.addConsumerRecords.parameter.empty.");
                        }
                    } catch (Exception e) {
                        log.error(e);
                        transactionStatus.setRollbackOnly();
                        throw new RuntimeException("ConsumerRecordsManagerImpl.addConsumerRecords.exception:", e);
                    }
                }


            });

        } catch (Exception e) {
            resultFlag = false;
            log.error(e);
        }
        return resultFlag;
	}

	@Override
	public boolean updateConsumerRecords(ConsumerRecords consumerRecords) {
        boolean resultFlag = true;
        try {
            if (null != consumerRecords) {
                
                boolean updateRouteFlag = consumerRecordsDao.updateConsumerRecords(consumerRecords);
                
                if (!updateRouteFlag) {
                    throw new RuntimeException("ConsumerRecordsManagerImpl.updateConsumerRecords.fail.");
                }
            } else {
                log.debug("ConsumerRecordsManagerImpl.updateConsumerRecords.parameter.empty.");
                throw new RuntimeException("ConsumerRecordsManagerImpl.updateConsumerRecords.parameter.empty.");
            }
        } catch (Exception e) {
            resultFlag = false;
            log.error(e);
            //System.out.println(e);
        }
        return resultFlag;
	}

	@Override
	public ConsumerRecords getConsumerRecordsById(Long id) {
		return consumerRecordsDao.getConsumerRecordsById(id);
	}

	@Override
	public ConsumerRecords getConsumerRecordsByConsumerRecordsCode(String consumerRecordsCode) {
		return consumerRecordsDao.getConsumerRecordsByConsumerRecordsCode(consumerRecordsCode);
	}

	@Override
	public List<ConsumerRecords> queryConsumerRecordsListByQueryBean(ConsumerRecordsQuery consumerRecordsQuery) {
		return consumerRecordsDao.queryConsumerRecordsListByQueryBean(consumerRecordsQuery);
	}

	@Override
	public PageModel<ConsumerRecords> queryConsumerRecordsListPageByQueryBean(ConsumerRecordsQuery consumerRecordsQuery,int pageIndex, int pageSize) {
		
        if (null == consumerRecordsQuery) {
            consumerRecordsQuery = new ConsumerRecordsQuery();
        }
        //��ѯ����
        int totalItem = consumerRecordsDao.queryConsumerRecordsCountByQueryBean(consumerRecordsQuery);
        
        //������ҳ����,��ݵڼ�ҳ��ÿҳ��С
        PageModel<ConsumerRecords> consumerRecordss = new PageModelImpl<ConsumerRecords>(pageIndex, pageSize);
        
        //��������,ͬʱ��������ʼ�кͽ�����
        consumerRecordss.setTotalItem(totalItem);
        
        //���ÿ�ʼ��
        consumerRecordsQuery.setStartRow(consumerRecordss.getStartRow());
        
        //���������
        consumerRecordsQuery.setEndRow(consumerRecordss.getEndRow());
        
        //����Dao��ҳ����
        List<ConsumerRecords> consumerRecordsList = consumerRecordsDao.queryConsumerRecordsListPageByQueryBean(consumerRecordsQuery);
        
        //��ӵ���ҳ������
        consumerRecordss.addAll(consumerRecordsList);

        return consumerRecordss;
	}

	@Override
	public boolean deleteConsumerRecordsById(Long id) {
		
		// ������
        boolean resultFlag = true;
        try {
            if (null != id && !("").equals(id)) {
                // ɾ��
                boolean deleteFlag = consumerRecordsDao.deleteConsumerRecordsById(id);
                if (!deleteFlag) {
                    //ɾ��ʧ�ܣ��׳��쳣
                    throw new RuntimeException("ConsumerRecordsManagerImpl.deleteConsumerRecords.fail.");
                }
            } else {
                //��¼id Ϊnull���׳��쳣
                log.debug("ConsumerRecordsManagerImpl.deleteConsumerRecords.empty.");
                throw new RuntimeException("");
            }
        } catch (Exception e) {
            resultFlag = false;
            log.error(e);
        }
        return resultFlag;
	}
	
	
	@Override
	public boolean deleteConsumerRecordsByIds(final Long[]ids){
		
		// result flag
        boolean resultFlag = true;
        try {
        	//deal with many data of transaction, you should execute this transaction.
        	TransactionTemplate template =this.getDataSourceTransactionManager();
        	template.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
					try{
						boolean deleteFlag=false; //delete result flag
						//judge on ids whether null or not
						if(ids!=null&&ids.length>0){
							//Traverse array ids
							for(Long id:ids){
								deleteFlag=consumerRecordsDao.deleteConsumerRecordsById(id);
							}
							if(!deleteFlag){
								throw new RuntimeException("ConsumerRecordsManagerImpl.deleteConsumerRecordsByIds(),Fail.");
							}
						}else{
							throw new RuntimeException("ConsumerRecordsManagerImpl.deleteConsumerRecordsByIds(), ids param null.");
						}
						
					}catch(Exception e){
						//record log
                        log.error(e);
                        // roll back
                        transactionStatus.setRollbackOnly();
                        throw new RuntimeException("ConsumerRecordsManagerImpl.deleteConsumerRecordsByIds.exception:", e);
					}
				}
			});
        }catch(Exception e){
        	resultFlag = false;
            log.error(e);
        }
        return resultFlag;
	}

	@Override
	public int queryConsumerRecordsCountByQueryBean(ConsumerRecordsQuery consumerRecordsQuery) {
		return consumerRecordsDao.queryConsumerRecordsCountByQueryBean(consumerRecordsQuery);
	}

	public void setConsumerRecordsDao(ConsumerRecordsDao consumerRecordsDao) {
		this.consumerRecordsDao = consumerRecordsDao;
	}
	
	

}
