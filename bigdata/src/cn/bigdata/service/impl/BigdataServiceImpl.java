package cn.bigdata.service.impl;

import java.util.List;

import cn.bigdata.database.dao.DataDao;
import cn.bigdata.database.dao.impl.DataDaoImpl;
import cn.bigdata.entity.Data;
import cn.bigdata.entity.Sale;
import cn.bigdata.service.BigdataService;

//Service µœ÷¿‡
public class BigdataServiceImpl implements BigdataService {

	DataDao dataDao = new DataDaoImpl();

	public List<Data> getDataList() {
		return dataDao.getDataList();
		// TODO Auto-generated method stub

	}

	@Override
	public void setList(DataDao dataDao) {
		// TODO Auto-generated method stub
		this.dataDao = dataDao;
	}

	@Override
	public List<Sale> getSaleList() {
		// TODO Auto-generated method stub
		return dataDao.getSaleList();
	}

}
