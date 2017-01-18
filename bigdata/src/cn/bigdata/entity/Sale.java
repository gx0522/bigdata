package cn.bigdata.entity;

import java.util.Date;
//每日销量对象
public class Sale {
	private int saleNum;	//销量
	private Date date;		//日期
	public int getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(int saleNum) {
		this.saleNum = saleNum;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
