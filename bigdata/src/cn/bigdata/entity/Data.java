package cn.bigdata.entity;

//���ݶ���
public class Data {
	private String id;				//��Ʒid
	private String name;		//��Ʒ����	
	private double price;		//��Ʒ�۸�
	private int numOfSale;		//��Ʒ������
	private int numOfComment;//��Ʒ������	
	private String date;			//�ɼ�ʱ��
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
