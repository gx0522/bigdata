package cn.bigdata.entity;

import java.util.Date;
//ÿ����������
public class Sale {
	private int saleNum;	//����
	private Date date;		//����
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
