package com.weixin.process;

public class WechatProcess {
	/** 
     * ��������xml����ȡ���ܻظ������ͨ��ͼ�������api�ӿڣ� 
     * @param xml ���յ���΢������ 
     * @return  ���յĽ��������xml��ʽ���ݣ� 
     */  
    public String processWechatMag(String xml){  
    	System.out.println("come in processWechatMag 0001");
        /** ����xml���� */  
        ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);  
        
        System.out.println("come in processWechatMag 0002");
          
        /** ���ı���ϢΪ��������ͼ�������api�ӿڣ���ȡ�ظ����� */  
        String result = "";  
//        if("text".endsWith(xmlEntity.getMsgType())){  
//            result = new TulingApiProcess().getTulingResult(xmlEntity.getContent());  
//        }  
        System.out.println("come in processWechatMag 0003");
        /** ��ʱ������û�������ǡ���á����ھ�������Ĺ���֮��resultΪ����Ҳ�á����Ƶ�����  
         *  ��Ϊ���ջظ���΢�ŵ�Ҳ��xml��ʽ�����ݣ�������Ҫ�����װΪ�ı����ͷ�����Ϣ 
         * */  
        result = "hello";
        System.out.println("come in WeChatProcess and the result is :" + result);
        result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);  
          
        System.out.println("come in WeChatProcess and return result String is :" + result);
        return result;  
    }
}
