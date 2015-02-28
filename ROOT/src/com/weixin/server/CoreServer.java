package com.weixin.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ivy.course.util.SignUtil;

import com.weixin.process.WechatProcess;

/** 
 * Servlet implementation class CoreServlet 
 */  
@WebServlet("/Core")
public class CoreServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Log LOG = LogFactory.getLog(CoreServer.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 微信加密签名  
        String signature = request.getParameter("signature");  
        // 时间戮  
        String timestamp = request.getParameter("timestamp");  
        // 随机数  
        String nonce = request.getParameter("nonce");  
        // 随机字符串   
        String result = "";       
        String echostr = request.getParameter("echostr");  
        
        LOG.info(echostr);
               
        /** 读取接收到的xml消息 */  
        StringBuffer sb = new StringBuffer();  
        InputStream is = request.getInputStream();  
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");  
        BufferedReader br = new BufferedReader(isr);  
        String s = "";  
        while ((s = br.readLine()) != null) {  
            sb.append(s);  
        }  
        
        
        String xml = sb.toString(); //次即为接收到微信端发送过来的xml数据  
               
        LOG.info("xml:"+xml);
        
        if (echostr != null && echostr.length() > 1) {  
        	// 通过检验 signature 对请求进行校验，若校验成功则原样返回 echostr，表示接入成功，否则接入失败  
            if(SignUtil.checkSignature(signature, timestamp, nonce)){
            	LOG.debug("check right:"+echostr);
            	result = echostr; 
            }
             
        } else {  
            //正常的微信处理流程  
        	
            result = new WechatProcess().processWechatMag(xml);  
        } 
          
        PrintWriter out = response.getWriter();  
        LOG.debug("return result:"+result);
       out.print(result); 
       out.close();  
       out = null;     
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
