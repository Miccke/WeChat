package com.cn.car.dao;

import com.cn.car.entity.ClockRecord;


public interface ClockRecordDao {
	/**
	 * 记录打卡
	 * @param record
	 * @return
	 */
	public int insert(ClockRecord record);
	/**
	 * 查询是否重复打卡
	 * @param record
	 * @return
	 */
	public ClockRecord getCr(ClockRecord record);
	/**
	 * 重复打卡则覆盖
	 * @param record
	 * @return
	 */
	public int updateCr(ClockRecord record);
}