package cn.bigdata.service;

import java.util.List;

import cn.bigdata.database.dao.DataDao;
import cn.bigdata.entity.Data;
import cn.bigdata.entity.Sale;
//Service�ӿ�
public interface BigdataService {
		//��ѯ���ݿ���Ϣ(���ؼ���)
		public List<Data> getDataList();
		//��ȡDao
		public void setList(DataDao dataDao);
		//�����ڲ�ѯ��Ϣ
		public List<Sale> getSaleList();
		
}
