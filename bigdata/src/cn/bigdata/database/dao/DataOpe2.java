package cn.bigdata.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cn.bigdata.database.util.ConfigManager;

public class DataOpe2 {
	Connection connection=null;
	Statement statement=null;
	ResultSet resultset=null;
	//��ѯ���ݿ���Ϣ
	public void getList(){ 
		
		String driver=ConfigManager.getInstance().getString("jdbc.driver.class");
		String url=ConfigManager.getInstance().getString("jdbc.connection.url");
		String username=ConfigManager.getInstance().getString("jdbc.connection.username");
		String password=ConfigManager.getInstance().getString("jdbc.connection.password");
		try {
			//��������
			Class.forName(driver);
			//��ȡ���ݿ�����
			connection=DriverManager.getConnection(url, username,password);
			//��ȡStatement����ִ��sql���
			String sql="select * from `2016-12-29_tmall_csv`";
			statement=connection.createStatement();
			resultset=statement.executeQuery(sql);
			//���α������ݿ���Ϣ���ֱ��ȡ������
			while(resultset.next()){
				//double id=resultset.getInt("��Ʒid");			//��Ʒ��ID
				String name=resultset.getString("��Ʒ����");		//��Ʒ����
				double price=resultset.getInt("��Ʒ�۸�");		//��Ʒ�۸�
				int numOfSale=resultset.getInt("��Ʒ����");		//��Ʒ����
				int numOfComment=resultset.getInt("��������");	//��������
				System.out.println("��Ʒ����"+name+"\t"+"��Ʒ�۸�"+price+"\t"+"��Ʒ����"+numOfSale+"\t"+"��������"+numOfComment);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		}finally{
			//�ر����ݿ���ã��ͷ���Դ
			try {
				resultset.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	//ͨ������Դ�������
	public Connection getConnectionDS(){
		try {
			//��ʼ��������
			Context context=new InitialContext();
			DataSource dataSource=(DataSource)context.lookup("java:comp/env/jdbc/bigdata");
			connection=dataSource.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	public static void main(String[] args){
		DataOpe2 operation=new DataOpe2();
		operation.getList();
	}
}
