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
	//查询数据库信息
	public void getList(){ 
		
		String driver=ConfigManager.getInstance().getString("jdbc.driver.class");
		String url=ConfigManager.getInstance().getString("jdbc.connection.url");
		String username=ConfigManager.getInstance().getString("jdbc.connection.username");
		String password=ConfigManager.getInstance().getString("jdbc.connection.password");
		try {
			//加载驱动
			Class.forName(driver);
			//获取数据库连接
			connection=DriverManager.getConnection(url, username,password);
			//获取Statement对象，执行sql语句
			String sql="select * from `2016-12-29_tmall_csv`";
			statement=connection.createStatement();
			resultset=statement.executeQuery(sql);
			//依次遍历数据库信息（分别获取变量）
			while(resultset.next()){
				//double id=resultset.getInt("商品id");			//商品的ID
				String name=resultset.getString("商品名称");		//商品名称
				double price=resultset.getInt("商品价格");		//商品价格
				int numOfSale=resultset.getInt("商品销量");		//商品销量
				int numOfComment=resultset.getInt("评论数量");	//评论数量
				System.out.println("商品名称"+name+"\t"+"商品价格"+price+"\t"+"商品销量"+numOfSale+"\t"+"评论数量"+numOfComment);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		}finally{
			//关闭数据库调用，释放资源
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
	//通过数据源获得链接
	public Connection getConnectionDS(){
		try {
			//初始化上下文
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
