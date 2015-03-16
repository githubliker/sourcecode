package com.surface.newsfocus;

import java.util.ArrayList;

import net.youmi.android.AdManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.surface.newsfocus.R;
import com.surface.newsfocus.adapter.NewsFragmentPagerAdapter;
import com.surface.newsfocus.bean.NewsClassify;
import com.surface.newsfocus.fragment.NewsFragment;
import com.surface.newsfocus.tool.BaseTools;
import com.surface.newsfocus.tool.BitmapCache;
import com.surface.newsfocus.tool.Constants;
import com.surface.newsfocus.view.ColumnHorizontalScrollView;
import com.surface.newsfocus.view.DrawerView;
import com.umeng.update.UmengUpdateAgent;
/**
 * ��android�߷�ϵ�У�����ͷ�� --�����Ķ���  
 * author:RA
 * blog : http://blog.csdn.net/vipzjyno1/
 */
public class MainActivity extends FragmentActivity {
	/** �Զ���HorizontalScrollView */
	private ColumnHorizontalScrollView mColumnHorizontalScrollView;
	LinearLayout mRadioGroup_content;
	LinearLayout ll_more_columns;
	RelativeLayout rl_column;
	private ViewPager mViewPager;
	private ImageView button_more_columns;
	/** ���ŷ����б�*/
	private ArrayList<NewsClassify> newsClassify=new ArrayList<NewsClassify>();
	/** ��ǰѡ�е�8Ŀ*/
	private int columnSelectIndex = 0;
	/** ����Ӱ����*/
	public ImageView shade_left;
	/** ����Ӱ���� */
	public ImageView shade_right;
	private ImageView headmore;
	/** ��Ļ��� */
	private int mScreenWidth = 0;
	/** Item��� */
	private int mItemWidth = 0;
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	
	protected SlidingMenu side_drawer;
	public ImageLoader imageLoader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		UmengUpdateAgent.update(this);
		mScreenWidth = BaseTools.getWindowsWidth(this);
		mItemWidth = mScreenWidth / 7;// һ��Item���Ϊ��Ļ��1/7
		initView();
		initSlidingMenu();
		initAdView();
		RequestQueue mQueue = Volley.newRequestQueue(this);
		imageLoader = new ImageLoader(mQueue, new BitmapCache());
	}
	/** ��ʼ��layout�ؼ�*/
	private void initView() {
		mColumnHorizontalScrollView =  (ColumnHorizontalScrollView)findViewById(R.id.mColumnHorizontalScrollView);
		mRadioGroup_content = (LinearLayout) findViewById(R.id.mRadioGroup_content);
		ll_more_columns = (LinearLayout) findViewById(R.id.ll_more_columns);
		rl_column = (RelativeLayout) findViewById(R.id.rl_column);
		button_more_columns = (ImageView) findViewById(R.id.button_more_columns);
		mViewPager = (ViewPager) findViewById(R.id.mViewPager);
		shade_left = (ImageView) findViewById(R.id.shade_left);
		shade_right = (ImageView) findViewById(R.id.shade_right);
		headmore = (ImageView) findViewById(R.id.btn_category);
		button_more_columns.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		headmore.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(side_drawer == null) return;
				if(side_drawer.isMenuShowing()){
					side_drawer.showContent();
				}else{
					side_drawer.showMenu();
				}
			}
		});
		setChangelView();
	}
	
	private void initAdView(){
		// 初始化接口，应用启动的时候调用
		// 参数：appId, appSecret, 调试模式
		AdManager.getInstance(this).init("52473b1a145d0239", "60e36d639dbd0a04", true);
		// 检查配置，SDK运行失败时可以用来检查配置是否齐全
		// SpotManager.getInstance(this).checkPermission(this);
	}
	/** 
	 *  ��8Ŀ���仯ʱ�����
	 * */
	private void setChangelView() {
		initColumnData();
		initTabColumn();
		initFragment();
	}
	/** ��ȡColumn8Ŀ ���*/
	private void initColumnData() {
		newsClassify = Constants.getData();
	}

	/** 
	 *  ��ʼ��Column8Ŀ��
	 * */
	private void initTabColumn() {
		mRadioGroup_content.removeAllViews();
		int count =  newsClassify.size();
		mColumnHorizontalScrollView.setParam(this, mScreenWidth, mRadioGroup_content, shade_left, shade_right, ll_more_columns, rl_column);
		for(int i = 0; i< count; i++){
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth , LayoutParams.WRAP_CONTENT);
			params.leftMargin = 5;
			params.rightMargin = 5;
//			TextView localTextView = (TextView) mInflater.inflate(R.layout.column_radio_item, null);
			TextView columnTextView = new TextView(this);
			columnTextView.setTextAppearance(this, R.style.top_category_scroll_view_item_text);
//			localTextView.setBackground(getResources().getDrawable(R.drawable.top_category_scroll_text_view_bg));
			columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
			columnTextView.setGravity(Gravity.CENTER);
			columnTextView.setPadding(5, 5, 5, 5);
			columnTextView.setId(i);
			columnTextView.setText(newsClassify.get(i).getTitle());
			columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
			if(columnSelectIndex == i){
				columnTextView.setSelected(true);
			}
			columnTextView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
			          for(int i = 0;i < mRadioGroup_content.getChildCount();i++){
				          View localView = mRadioGroup_content.getChildAt(i);
				          if (localView != v)
				        	  localView.setSelected(false);
				          else{
				        	  localView.setSelected(true);
				        	  mViewPager.setCurrentItem(i);
				          }
			          }
			          Toast.makeText(getApplicationContext(), newsClassify.get(v.getId()).getTitle(), Toast.LENGTH_SHORT).show();
				}
			});
			mRadioGroup_content.addView(columnTextView, i ,params);
		}
	}
	/** 
	 *  ѡ���Column�����Tab
	 * */
	private void selectTab(int tab_postion) {
		columnSelectIndex = tab_postion;
		for (int i = 0; i < mRadioGroup_content.getChildCount(); i++) {
			View checkView = mRadioGroup_content.getChildAt(tab_postion);
			int k = checkView.getMeasuredWidth();
			int l = checkView.getLeft();
			int i2 = l + k / 2 - mScreenWidth / 2;
			// rg_nav_content.getParent()).smoothScrollTo(i2, 0);
			mColumnHorizontalScrollView.smoothScrollTo(i2, 0);
			// mColumnHorizontalScrollView.smoothScrollTo((position - 2) *
			// mItemWidth , 0);
		}
		//�ж��Ƿ�ѡ��
		for (int j = 0; j <  mRadioGroup_content.getChildCount(); j++) {
			View checkView = mRadioGroup_content.getChildAt(j);
			boolean ischeck;
			if (j == tab_postion) {
				ischeck = true;
			} else {
				ischeck = false;
			}
			checkView.setSelected(ischeck);
		}
	}
	/** 
	 *  ��ʼ��Fragment
	 * */
	private void initFragment() {
		int count =  newsClassify.size();
		for(int i = 0; i< count;i++){
			Bundle data = new Bundle();
    		data.putString("text", newsClassify.get(i).getTitle());
			NewsFragment newfragment = new NewsFragment();
			newfragment.setArguments(data);
			fragments.add(newfragment);
		}
		NewsFragmentPagerAdapter mAdapetr = new NewsFragmentPagerAdapter(getSupportFragmentManager(), fragments);
//		mViewPager.setOffscreenPageLimit(0);
		mViewPager.setAdapter(mAdapetr);
		mViewPager.setOnPageChangeListener(pageListener);
	}
	/** 
	 *  ViewPager�л�����
	 * */
	public OnPageChangeListener pageListener= new OnPageChangeListener(){

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			mViewPager.setCurrentItem(position);
			selectTab(position);
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	protected void initSlidingMenu() {
		side_drawer = new DrawerView(this).initSlidingMenu();
	}
	
	private long mExitTime;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(side_drawer!= null && (side_drawer.isMenuShowing() ||side_drawer.isSecondaryMenuShowing())){
				side_drawer.showContent();
			}else {
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "双击退出",
							Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
				} else {
					finish();
				}
			}
			return true;
		}
		//9��MENU��ť����¼����������κβ���
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
