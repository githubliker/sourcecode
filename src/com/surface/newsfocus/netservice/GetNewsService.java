package com.surface.newsfocus.netservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;

import android.util.Log;

import com.surface.newsfocus.bean.News;

public class GetNewsService extends NetService{

	private static String TAG = "GetNewsService";
	/**
	 * 通过page返回新闻列表集合
	 * @param newsName 搜索新闻名称
	 * @param page 搜索新闻页码
	 * @return
	 */
	public static List<News> getNewsByPage(String newsName,int page){
		
		getJsonObjectsByUrl("http://m.baidu.com/video?tn=bdapisearch&word=电视剧"+"&pn="+20*page+"&rn=20&t=1386838893136");
		JSONObject[] jsonObjects = getJsonObjectsByUrl("http://m.baidu.com/news?tn=bdapisearch&word="+newsName+"&pn="+20*page+"&rn=20&t=1386838893136");
		List<News> newss = new ArrayList<News>();
		try{
			if(jsonObjects!=null&&jsonObjects.length>0){
				for(JSONObject jsonObject : jsonObjects){
					News news = new News();
					news.setTitle(jsonObject.getString("title"));
					news.setSource(jsonObject.getString("author"));
					news.setUrl(jsonObject.getString("url"));
					news.setPhotoUrl(jsonObject.getString("imgUrl"));
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm",Locale.CHINA);
					news.setDate(dateFormat.format(new Date(jsonObject.getLong("sortTime")*1000)));
				    newss.add(news);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return newss;
	}
}
