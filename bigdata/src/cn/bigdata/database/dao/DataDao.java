package cn.bigdata.database.dao;

import java.util.List;

import cn.bigdata.entity.Data;
import cn.bigdata.entity.Sale;

public interface DataDao {	
	//查询数据库信息(返回集合)
	public List<Data> getDataList();
	//按日期查询销量
	public List<Sale> getSaleList();
}
