package cn.bigdata.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import cn.bigdata.database.util.ConfigManager;

//�������ݿ����
public class BaseDao {
	protected Connection connection;
	protected PreparedStatement pstm;
	protected ResultSet rs;
	//��ȡ���ݿ�����
	public boolean getConnection(){
		String driver=ConfigManager.getInstance().getString("jdbc.driver.class");
		String url=ConfigManager.getInstance().getString("jdbc.connection.url");
		String username=ConfigManager.getInstance().getString("jdbc.connection.username");
		String password=ConfigManager.getInstance().getString("jdbc.connection.password");
		try {
			//��������
			Class.forName(driver);
			//��ȡ���ݿ�����
			connection=DriverManager.getConnection(url, username,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		return true;
		
	}
	//ͨ������Դ��ȡ����
	public Connection getConnectionDS(){
		try {
			//��ʼ��������
			Context ctx=new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/bigdata");
			connection=ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	//��ѯselect * from news_detail where author=?
	public ResultSet excuteQuery(String sql,Object[] params){
		if(this.getConnection()){
			try {
				pstm=connection.prepareStatement(sql);
				//���ռλ��
				for(int i=0;i<params.length;i++){
					pstm.setObject(i+1, params[i]);
				}
				rs=pstm.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
		
	}
	
	
	
	//�ͷ���Դ
	public boolean close(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		if(pstm!=null){
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false; 
			}
		}
		return true;
		
	}
}
