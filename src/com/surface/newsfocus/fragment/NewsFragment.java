package com.surface.newsfocus.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.surface.newsfocus.NewsDetailsActivity;
import com.surface.newsfocus.R;
import com.surface.newsfocus.adapter.NewsListViewAdapter;
import com.surface.newsfocus.bean.News;
import com.surface.newsfocus.netservice.GetNewsService;
import com.surface.newsfocus.tool.BitmapCache;
import com.surface.newsfocus.view.PullToRefreshView;
import com.surface.newsfocus.view.PullToRefreshView.OnFooterRefreshListener;
import com.surface.newsfocus.view.PullToRefreshView.OnHeaderRefreshListener;

public class NewsFragment extends Fragment implements OnHeaderRefreshListener,
		OnFooterRefreshListener, OnItemClickListener {
	Activity activity;
	List<News> newsList = new ArrayList<News>();
	ListView mListView;
	PullToRefreshView mRefreshView;
	private NewsListViewAdapter adapter;
	private boolean isLoading = true;;
	String text;
	ImageView detail_loading;
	public final static int SET_NEWSLIST = 0;

	private int page = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Bundle args = getArguments();
		text = args != null ? args.getString("text") : "";
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		this.activity = activity;

		super.onAttach(activity);
	}

	/** �˷�����˼Ϊfragment�Ƿ�ɼ� ,�ɼ�ʱ�������� */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		if (isVisibleToUser) {
			// fragment�ɼ�ʱ�������
			if (newsList != null && newsList.size() != 0) {
				handler.obtainMessage(SET_NEWSLIST).sendToTarget();
			} else {
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(2);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						handler.obtainMessage(SET_NEWSLIST).sendToTarget();
					}
				}).start();
			}
		} else {
			// fragment���ɼ�ʱ��ִ�в���
		}
		super.setUserVisibleHint(isVisibleToUser);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.news_fragment, null);
		mListView = (ListView) view.findViewById(R.id.mListView);
		mListView.setOnItemClickListener(this);
		mRefreshView = (PullToRefreshView) view
				.findViewById(R.id.main_pull_refresh_view);
		mRefreshView.setOnHeaderRefreshListener(this);
		mRefreshView.setOnFooterRefreshListener(this);
		adapter = new NewsListViewAdapter(activity, null);
		TextView item_textview = (TextView) view
				.findViewById(R.id.item_textview);
		detail_loading = (ImageView) view.findViewById(R.id.detail_loading);
		item_textview.setText(text);
		isLoading = true;
		new GetDataTask().execute(page);
		return view;
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case SET_NEWSLIST:
				detail_loading.setVisibility(View.GONE);
				adapter = new NewsListViewAdapter(activity, null);
				adapter.addNews(newsList);
				mListView.setAdapter(adapter);
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	private class GetDataTask extends AsyncTask<Integer, String, List<News>> {

		@Override
		protected List<News> doInBackground(Integer... pages) {
			// Simulates a background job.
			newsList = GetNewsService.getNewsByPage(text, pages[0]);
			return newsList;
		}

		@Override
		protected void onPostExecute(List<News> newss) {
			if (newss.size() > 0) {
				adapter.addNews(newss);
				adapter.notifyDataSetChanged();
				page++;
			}
			isLoading = false;
		}
	}

	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		// TODO Auto-generated method stub
		mRefreshView.postDelayed(new Runnable() {
			@Override
			public void run() {
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				mRefreshView.onHeaderRefreshComplete(format
						.format(new Date()));
				page = 0;
				new GetDataTask().execute(page);
				mRefreshView.onHeaderRefreshComplete();
			}
		}, 1000);
	}

	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		// TODO Auto-generated method stub
		mRefreshView.postDelayed(new Runnable() {
			@Override
			public void run() {
				new GetDataTask().execute(page);
				mRefreshView.onFooterRefreshComplete();
			}
		}, 1000);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(activity, NewsDetailsActivity.class);
		intent.putExtra("url", adapter.getNewss().get(arg2).getUrl());
		startActivity(intent);
	}
}
