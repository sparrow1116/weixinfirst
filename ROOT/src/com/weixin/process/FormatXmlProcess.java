package com.weixin.process;

import java.util.Date;

public class FormatXmlProcess {
	/** 
     * ��װ������ķ�����Ϣ 
     * @param to 
     * @param from 
     * @param content 
     * @return 
     */  
    public String formatXmlAnswer(String to, String from, String content) {  
        StringBuffer sb = new StringBuffer();  
        System.out.println("come in formatXmlAnswer");
        Date date = new Date();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(to);  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(from);  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(date.getTime());  
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");  
        sb.append(content);  
        sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");  
        System.out.println("come in formatXmlAnswer and the return String is:" + sb.toString());
        return sb.toString();  
    }
}
