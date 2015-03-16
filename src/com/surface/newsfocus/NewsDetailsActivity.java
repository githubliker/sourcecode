package com.surface.newsfocus;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.surface.newsfocus.R;
import com.surface.newsfocus.tool.FileUtil;


public class NewsDetailsActivity extends Activity implements OnTouchListener{

	private WebView webView;
	private float startX;
	private float startY;
	private RelativeLayout relativeLayout;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what==1){
				relativeLayout.setVisibility(View.GONE);
				webView.getSettings().setBlockNetworkImage(false);
				webView.getSettings().setJavaScriptEnabled(false);
				webView.setVisibility(View.VISIBLE);
				FileUtil.addFile(msg.obj.toString());
				webView.loadDataWithBaseURL(null, msg.obj.toString().replace("data-url", "src")
						.replace("class=\"lazy-load\"","").replace("本日点击排行榜", "")
						.replace("查看原图", ""), "text/html", "utf-8",null);
			}
		}
	};

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_details);
		String url = getIntent().getStringExtra("url");
		
		relativeLayout = (RelativeLayout)findViewById(R.id.activity_news_details_relative_is_loading);
		
		webView = (WebView)findViewById(R.id.news_details_webview);
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.addJavascriptInterface(new InJavaScriptLocalObj(), "local_obj");
		webView.setWebViewClient(new MyWebViewClient());
		webView.getSettings().setBlockNetworkImage(true);
		webView.loadUrl("http://m.baidu.com/news?tn=bdbody&src="+url+"&pu=sz@1320_2001,usm@4,ta@iphone_1_4.3_3_533&bd_page_type=1");
		webView.setOnTouchListener(this);
		relativeLayout.setOnTouchListener(this);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==android.R.id.home){
			finish();
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * javascript��ͦ���� 
	 * @author linpeng123l
	 *
	 */
	final class InJavaScriptLocalObj{
		private boolean isLoaded = false;
		@JavascriptInterface//加入这个注解
		public void showSource(String html) {
			if(!isLoaded){
				Document document = Jsoup.parse(html);
				Elements elements = document.getElementsByClass("page-view-article");
				elements.remove(elements.select(".img-eye"));
				elements.select(".img-eye").remove();
				Message message = new Message();
				message.obj = document.head()+elements.toString();
				message.what = 1;
				handler.sendMessage(message);
				isLoaded = true;
			}
		}
	}

	/*
	 * ����ذ�ť
	 */
	public void back(View view){
		finish();
	}


	/**
	 * webview������
	 * @author linpeng123l
	 *
	 */
	final class MyWebViewClient extends WebViewClient{ 
		public int count;
		
		public boolean shouldOverrideUrlLoading(WebView view, String url) {  
			view.loadUrl(url);  
			return true;  
		} 
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
		}   
		public void onPageFinished(WebView view, String url) {
			view.loadUrl("javascript:window.local_obj.showSource('<head>'+" +
					"document.getElementsByTagName('html')[0].innerHTML+'</head>');");
			super.onPageFinished(view, url);
		}
	}
	
	/**
	 * �󻬶����ؼ���
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			startY = event.getY();
			break;
		case MotionEvent.ACTION_UP:
			float endX = event.getX();
			float endY = event.getY();
			if(Math.abs(endX-startX)>150&&((endY-startY)==0||Math.abs((endX-startX)/(endY-startY))>2)){
				finish();
			}
			break;
		default:
			break;
		}
		return false;
	}
}
