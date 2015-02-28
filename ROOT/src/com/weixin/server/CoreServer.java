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
		 // ΢�ż���ǩ��  
        String signature = request.getParameter("signature");  
        // ʱ��¾  
        String timestamp = request.getParameter("timestamp");  
        // �����  
        String nonce = request.getParameter("nonce");  
        // ����ַ���   
        String result = "";       
        String echostr = request.getParameter("echostr");  
        
        LOG.info(echostr);
               
        /** ��ȡ���յ���xml��Ϣ */  
        StringBuffer sb = new StringBuffer();  
        InputStream is = request.getInputStream();  
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");  
        BufferedReader br = new BufferedReader(isr);  
        String s = "";  
        while ((s = br.readLine()) != null) {  
            sb.append(s);  
        }  
        
        
        String xml = sb.toString(); //�μ�Ϊ���յ�΢�Ŷ˷��͹�����xml����  
               
        LOG.info("xml:"+xml);
        
        if (echostr != null && echostr.length() > 1) {  
        	// ͨ������ signature ���������У�飬��У��ɹ���ԭ������ echostr����ʾ����ɹ����������ʧ��  
            if(SignUtil.checkSignature(signature, timestamp, nonce)){
            	LOG.debug("check right:"+echostr);
            	result = echostr; 
            }
             
        } else {  
            //������΢�Ŵ�������  
        	
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
