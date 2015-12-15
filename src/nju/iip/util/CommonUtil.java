package nju.iip.util;

import java.util.Iterator;
import nju.iip.model.WechatPost;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	@SuppressWarnings("unchecked")
	public WechatPost parseXml(String xml) {
		// 将解析结果存储在HashMap中
		WechatPost wechatPost = new WechatPost();
		try{
			org.dom4j.Document doc = DocumentHelper.parseText(xml);
			org.dom4j.Element rootElt = doc.getRootElement(); // 获取根节点
            System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
            Iterator<org.dom4j.Element> iter = rootElt.elementIterator("item"); // 获取根节点下的子节点head
            while (iter.hasNext()) {
            	org.dom4j.Element itemEle = (org.dom4j.Element) iter.next();
            	Iterator<org.dom4j.Element> iters = itemEle.elementIterator("display");
            	while (iters.hasNext()) {
            		org.dom4j.Element displayEle = (org.dom4j.Element) iters.next();
            		String title = displayEle.elementTextTrim("title"); 
            		System.out.println("title="+title);
            		String url = displayEle.elementTextTrim("url"); 
            		System.out.println("url="+url);
            		String sourcename = displayEle.elementTextTrim("sourcename"); 
            		System.out.println("sourcename="+sourcename);
            		String date = displayEle.elementTextTrim("date"); 
            		System.out.println("date="+date);
            	}
            }
		}catch(Exception e) {
			logger.info("parseXml error", e);
		}
		return wechatPost;
	}
	
	

}
