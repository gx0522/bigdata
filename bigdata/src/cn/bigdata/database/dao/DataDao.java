package cn.bigdata.database.dao;

import java.util.List;

import cn.bigdata.entity.Data;
import cn.bigdata.entity.Sale;

public interface DataDao {	
	//��ѯ���ݿ���Ϣ(���ؼ���)
	public List<Data> getDataList();
	//�����ڲ�ѯ����
	public List<Sale> getSaleList();
}
