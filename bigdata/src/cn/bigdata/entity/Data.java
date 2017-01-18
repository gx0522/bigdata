package cn.bigdata.entity;

//数据对象
public class Data {
	private String id;				//商品id
	private String name;		//商品名字	
	private double price;		//商品价格
	private int numOfSale;		//商品销售量
	private int numOfComment;//商品评论量	
	private String date;			//采集时间
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNumOfSale() {
		return numOfSale;
	}
	public void setNumOfSale(int numOfSale) {
		this.numOfSale = numOfSale;
	}
	public int getNumOfComment() {
		return numOfComment;
	}
	public void setNumOfComment(int numOfComment) {
		this.numOfComment = numOfComment;
	}
	
	
}
