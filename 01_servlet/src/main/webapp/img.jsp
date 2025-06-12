<%@page import="java.net.URL"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="image/jpeg"
    pageEncoding="UTF-8"%>

<%
	//String src = "‪C:/Users/tj/Downloads/down.jpg";
	//File file = new File(src);
	URL url = new URL("https://naverpa-phinf.pstatic.net/MjAyNTAxMTRfMjU4/MDAxNzM2ODE3Nzg3MDY3.-82fBmmPDgFldIzOTqebFyZo8vkk6rTVORS7W_PiJXEg._6B7ZcTjylCNmqUFcLhbc076rDGRttqKtau3KLN4R9Ag.JPEG/KakaoTalk_20250114_102203890_17368177870117409023038334047768.jpg");
	
	//FileInputStream fis = new FileInputStream(file);
	InputStream fis = url.openStream();
	byte[] bytes = fis.readAllBytes();
	
	OutputStream os = response.getOutputStream();
	os.write(bytes);
	
	fis.close();
%>