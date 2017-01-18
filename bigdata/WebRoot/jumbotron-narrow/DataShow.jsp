<%@page import="cn.bigdata.entity.Data"%>
<%@page import="cn.bigdata.entity.Sale"%>
<%@page import="java.util.List"%>
<%@page import="cn.bigdata.database.dao.impl.DataDaoImpl"%>
<%@page import="cn.bigdata.database.dao.DataDao"%>
<%@page import="cn.bigdata.service.impl.BigdataServiceImpl"%>
<%@page import="cn.bigdata.service.BigdataService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<<jsp:useBean id="bigdataService" class="cn.bigdata.service.impl.BigdataServiceImpl" scope="page"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
		//BigdataService bigdataService=new BigdataServiceImpl();
		DataDao dataDao=new DataDaoImpl();
		bigdataService.setList(dataDao);
		List<Data> dataList=bigdataService.getDataList();
		List<Sale> saleList=bigdataService.getSaleList();
		for(Sale sale:saleList){
	%>
	
	<tr><%=sale.getDate() %>
	<tr><%=sale.getSaleNum() %>
	<br>		
		<% }
		for(Data data:dataList){
		%>
	<tr><%=data.getId() %>
	<tr><%=data.getName() %>
	
	<tr><%=data.getPrice() %>
	<tr><%=data.getNumOfSale() %>
	<tr><%=data.getNumOfComment() %>
	<tr><%=data.getDate() %>
	<br>
	<% 	}
		
 %>
</body>
</html>