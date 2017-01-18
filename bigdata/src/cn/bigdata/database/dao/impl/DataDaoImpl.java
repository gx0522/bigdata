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
	// 查询数据库数据信息(按销量前五输出)
	public List<Data> getDataList() {
		List<Data> dataList = new ArrayList<Data>();
		// TODO Auto-generated method stub
		String sql = "select * from tmall a where not exists"
				+ "(select 1 from tmall b  where b.tmpname=a.tmpname and b.adddate>a.adddate) "
				+ "order by tmsaleNum desc limit 5 ";
		// String sql1="order by 商品销量 fetch first 5 rows only";
		Object[] params = {};

		ResultSet resultset = this.excuteQuery(sql, params);
		// resultset =this.excuteQuery(sql1, params);
		try {
			while (resultset.next()) {
				String id = resultset.getString("pid"); // 商品的ID
				String name = resultset.getString("tmpname"); // 商品名称
				double price = resultset.getInt("tmprice"); // 商品价格
				int numOfSale = resultset.getInt("tmsaleNum"); // 商品销量
				int numOfComment = resultset.getInt("comNum"); // 评论数量
				Date dt = new Date();
				dt = resultset.getDate("adddate");
				String date = String.format("%tF", dt);// 采集日期
				Data data = new Data();
				data.setId(id);
				data.setName(name);
				data.setNumOfComment(numOfComment);
				data.setNumOfSale(numOfSale);
				data.setPrice(price);
				data.setDate(date);
				dataList.add(data);

				// System.out.println("商品名称"+name+"\t"+"商品价格"+price+"\t"+"商品销量"+numOfSale+"\t"+"评论数量"+numOfComment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.close();
		}
		return dataList;
	}

	// 获取每日销量信息
	public List<Sale> getSaleList() {
		int tempSale = 0; // 当天累计销售量
		int temp = 0; // 历史累计销售量
		Date tempDate = null; // 当天时间

		List<Sale> saleList = new ArrayList<Sale>();// 销售量集合
		List<Sale> saleNumList = new ArrayList<Sale>();// 当天销售量集合
		String sql = "select * from tmall order by adddate asc ";
		Object[] params = {};
		ResultSet resultset = this.excuteQuery(sql, params);
		try {
			// 销售量集合添加数据
			while (resultset.next()) {
				// double price = resultset.getInt("tmprice"); // 商品价格
				int numOfSale = resultset.getInt("tmsaleNum"); // 商品销量

				Date dt = new Date();
				dt = resultset.getDate("adddate");
				// String date=String.format("%tF",dt );//采集日期
				Sale sale = new Sale();
				sale.setSaleNum(numOfSale);
				sale.setDate(dt);
				saleList.add(sale);

				// System.out.println("商品名称"+name+"\t"+"商品价格"+price+"\t"+"商品销量"+numOfSale+"\t"+"评论数量"+numOfComment);
			}
			// 计算当天销售量(日期相同就销售量累加，日期不同就存入集合)
			for (Sale sale : saleList) {
				if (sale.getDate().equals(tempDate)) {
					if (sale.getSaleNum() > 0) {
						tempSale = tempSale + sale.getSaleNum();
					}
				} else {
					Sale saleInformation = new Sale();// 当天销售量
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
			Sale saleInformationEnd = new Sale();// 当天销售量
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
