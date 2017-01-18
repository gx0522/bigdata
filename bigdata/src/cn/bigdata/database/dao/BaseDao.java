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

//操作数据库基类
public class BaseDao {
	protected Connection connection;
	protected PreparedStatement pstm;
	protected ResultSet rs;
	//获取数据库连接
	public boolean getConnection(){
		String driver=ConfigManager.getInstance().getString("jdbc.driver.class");
		String url=ConfigManager.getInstance().getString("jdbc.connection.url");
		String username=ConfigManager.getInstance().getString("jdbc.connection.username");
		String password=ConfigManager.getInstance().getString("jdbc.connection.password");
		try {
			//加载驱动
			Class.forName(driver);
			//获取数据库连接
			connection=DriverManager.getConnection(url, username,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		return true;
		
	}
	//通过数据源获取连接
	public Connection getConnectionDS(){
		try {
			//初始化上下文
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
	//查询select * from news_detail where author=?
	public ResultSet excuteQuery(String sql,Object[] params){
		if(this.getConnection()){
			try {
				pstm=connection.prepareStatement(sql);
				//填充占位符
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
	
	
	
	//释放资源
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
