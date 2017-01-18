package cn.bigdata.database.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bigdata.database.dao.BaseDao;
import cn.bigdata.database.dao.DataDao;
import cn.bigdata.entity.Data;
import cn.bigdata.entity.Sale;

public class DataDaoImpl extends BaseDao implements DataDao {

	@Override
	// ��ѯ���ݿ�������Ϣ(������ǰ�����)
	public List<Data> getDataList() {
		List<Data> dataList = new ArrayList<Data>();
		// TODO Auto-generated method stub
		String sql = "select * from tmall a where not exists"
				+ "(select 1 from tmall b  where b.tmpname=a.tmpname and b.adddate>a.adddate) "
				+ "order by tmsaleNum desc limit 5 ";
		// String sql1="order by ��Ʒ���� fetch first 5 rows only";
		Object[] params = {};

		ResultSet resultset = this.excuteQuery(sql, params);
		// resultset =this.excuteQuery(sql1, params);
		try {
			while (resultset.next()) {
				String id = resultset.getString("pid"); // ��Ʒ��ID
				String name = resultset.getString("tmpname"); // ��Ʒ����
				double price = resultset.getInt("tmprice"); // ��Ʒ�۸�
				int numOfSale = resultset.getInt("tmsaleNum"); // ��Ʒ����
				int numOfComment = resultset.getInt("comNum"); // ��������
				Date dt = new Date();
				dt = resultset.getDate("adddate");
				String date = String.format("%tF", dt);// �ɼ�����
				Data data = new Data();
				data.setId(id);
				data.setName(name);
				data.setNumOfComment(numOfComment);
				data.setNumOfSale(numOfSale);
				data.setPrice(price);
				data.setDate(date);
				dataList.add(data);

				// System.out.println("��Ʒ����"+name+"\t"+"��Ʒ�۸�"+price+"\t"+"��Ʒ����"+numOfSale+"\t"+"��������"+numOfComment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.close();
		}
		return dataList;
	}

	// ��ȡÿ��������Ϣ
	public List<Sale> getSaleList() {
		int tempSale = 0; // �����ۼ�������
		int temp = 0; // ��ʷ�ۼ�������
		Date tempDate = null; // ����ʱ��

		List<Sale> saleList = new ArrayList<Sale>();// ����������
		List<Sale> saleNumList = new ArrayList<Sale>();// ��������������
		String sql = "select * from tmall order by adddate asc ";
		Object[] params = {};
		ResultSet resultset = this.excuteQuery(sql, params);
		try {
			// �����������������
			while (resultset.next()) {
				// double price = resultset.getInt("tmprice"); // ��Ʒ�۸�
				int numOfSale = resultset.getInt("tmsaleNum"); // ��Ʒ����

				Date dt = new Date();
				dt = resultset.getDate("adddate");
				// String date=String.format("%tF",dt );//�ɼ�����
				Sale sale = new Sale();
				sale.setSaleNum(numOfSale);
				sale.setDate(dt);
				saleList.add(sale);

				// System.out.println("��Ʒ����"+name+"\t"+"��Ʒ�۸�"+price+"\t"+"��Ʒ����"+numOfSale+"\t"+"��������"+numOfComment);
			}
			// ���㵱��������(������ͬ���������ۼӣ����ڲ�ͬ�ʹ��뼯��)
			for (Sale sale : saleList) {
				if (sale.getDate().equals(tempDate)) {
					if (sale.getSaleNum() > 0) {
						tempSale = tempSale + sale.getSaleNum();
					}
				} else {
					Sale saleInformation = new Sale();// ����������
					saleInformation.setSaleNum(tempSale - temp);
					saleInformation.setDate(tempDate);
					saleNumList.add(saleInformation);
					temp = temp + tempSale;
					if (sale.getSaleNum() > 0) {
						tempDate = sale.getDate();
						tempSale = sale.getSaleNum();
					}
				}
			}
			Sale saleInformationEnd = new Sale();// ����������
			saleInformationEnd.setSaleNum(tempSale);
			saleInformationEnd.setDate(tempDate);
			saleNumList.add(saleInformationEnd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.close();
		}
		return saleNumList;
	}
}
