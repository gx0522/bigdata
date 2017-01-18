package cn.bigdata.service;

import java.util.List;

import cn.bigdata.database.dao.DataDao;
import cn.bigdata.entity.Data;
import cn.bigdata.entity.Sale;
//Service接口
public interface BigdataService {
		//查询数据库信息(返回集合)
		public List<Data> getDataList();
		//获取Dao
		public void setList(DataDao dataDao);
		//按日期查询信息
		public List<Sale> getSaleList();
		
}
