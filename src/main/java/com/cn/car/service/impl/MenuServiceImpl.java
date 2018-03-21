package com.cn.car.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.car.dao.MenuDao;
import com.cn.car.entity.Menu;
import com.cn.car.service.MenuService;
@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuDao menuDao;
	
	/**
	 * menu列表
	 */
	@Override
	public List<Menu> menulist() {
		// TODO Auto-generated method stub
		return menuDao.menulist();
	}
	
}
