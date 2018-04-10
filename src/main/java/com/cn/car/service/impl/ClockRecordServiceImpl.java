package com.cn.car.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.car.dao.ClockRecordDao;
import com.cn.car.entity.ClockRecord;
import com.cn.car.service.ClockRecordService;

@Service("clockRecordService")
public class ClockRecordServiceImpl implements ClockRecordService {

	@Resource
	private ClockRecordDao clockRecordDao;
	
	/**
	 * 记录打卡
	 * @param record
	 * @return
	 */
	@Override
	public int insert(ClockRecord record) {
		return clockRecordDao.insert(record);
	}

	/**
	 * 查询是否重复打卡
	 * @param record
	 * @return
	 */
	public ClockRecord getCr(ClockRecord record){
		// TODO Auto-generated method stub
		return clockRecordDao.getCr(record);
	}
	/**
	 * 重复打卡则覆盖
	 * @param record
	 * @return
	 */
	public int updateCr(ClockRecord record){
		return clockRecordDao.updateCr(record);
	}

}
