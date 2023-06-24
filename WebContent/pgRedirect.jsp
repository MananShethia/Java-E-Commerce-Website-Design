<%@page import="com.controller.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,com.paytm.pg.merchant.CheckSumServiceHelper"%>    
<%@ include file="header.jsp" %>
<%
Random r1=new Random();
int o1=r1.nextInt(100000);

String merchantMid = "bPdnXU83720846076945";
//Key in your staging and production MID available in your dashboard
String merchantKey = "0S@ec0PFIzOixt%L";
//Key in your staging and production merchant key available in your dashboard
String orderId = "order"+o1;
String channelId = "WEB";
String custId = "cust123";

String email = "javawebapp.404@gmail.com";
String txnAmount = request.getParameter("TXN_AMOUNT").toString();
String website = "WEBSTAGING";
//This is the staging value. Production value is available in your dashboard
String industryTypeId = "Retail";
//This is the staging value. Production value is available in your dashboard
String callbackUrl = "http://localhost:9080/MyProject/pgResponse.jsp";
TreeMap<String, String> paytmParams = new TreeMap<String, String>();
paytmParams.put("MID",merchantMid);
paytmParams.put("ORDER_ID",orderId);
paytmParams.put("CHANNEL_ID",channelId);
paytmParams.put("CUST_ID",custId);
paytmParams.put("MOBILE_NO","9377614772");
paytmParams.put("EMAIL",email);
paytmParams.put("TXN_AMOUNT",txnAmount);
paytmParams.put("WEBSITE",website);
paytmParams.put("INDUSTRY_TYPE_ID",industryTypeId);
paytmParams.put("CALLBACK_URL", callbackUrl);
String paytmChecksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(merchantKey, paytmParams);
StringBuilder outputHtml = new StringBuilder();
outputHtml.append("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>");
outputHtml.append("<html>");
outputHtml.append("<head>");
outputHtml.append("<title>Merchant Checkout Page</title>");
outputHtml.append("</head>");
outputHtml.append("<body>");
outputHtml.append("<center><h1>Please do not refresh this page...</h1></center>");

outputHtml.append("<form method='post' action='"+PaytmConstants.PAYTM_URL+"' name='f1'>");
for(Map.Entry<String,String> entry : paytmParams.entrySet()) {
outputHtml.append("<input type='hidden' name='"+entry.getKey()+"' value='"+entry.getValue()+"'>");
}
outputHtml.append("<input type='hidden' name='CHECKSUMHASH' value='"+paytmChecksum+"'>");
outputHtml.append("</form>");
outputHtml.append("<script type='text/javascript'>");
outputHtml.append("document.f1.submit();");
outputHtml.append("</script>");
outputHtml.append("</body>");
outputHtml.append("</html>");
out.println(outputHtml);
%>
