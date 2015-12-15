package nju.iip.spider;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nju.iip.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WechatSpider implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(WechatSpider.class);
	
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 获取openid和ext
	 * 
	 * @param OfficialAccount
	 * @return
	 */
	public String getOpenidAndExt(String OfficialAccount) {
		String url = "http://weixin.sogou.com/weixin?type=1&query="+ OfficialAccount;
		String html = HttpUtil.getHTML(url);
		Document doc = Jsoup.parse(html);
		Element elment = doc.select("div.wx-rb_v1").first();
		String href = elment.attr("href");
		try {
			return href.substring(href.indexOf("openid"));
		} catch (Exception e) {
			logger.info("getOpenidAndExt error", e);
		}
		return null;
	}

	public JSONObject getJson(String openid_ext) {
		String url = "http://weixin.sogou.com/gzhjs?" + openid_ext;
		String Jsonstr = HttpUtil.getHTML(url);
		JSONObject Json = JSONObject.fromObject(Jsonstr);
		return Json;
	}

	

	public static void main(String[] args) {
		WechatSpider spider = new WechatSpider();
		String xml = spider.getJson(spider.getOpenidAndExt("infoQ")).getString("items");
		JSONArray array = JSONArray.fromObject(xml);
		logger.info("xml=" + array.get(0).toString());
	}


}
