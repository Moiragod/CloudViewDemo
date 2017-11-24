package com.moiragod.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.moira.lib.kernal.utils.CommonUtils;
import com.moira.lib.kernal.utils.PerfectClickListener;
import com.moira.lib.kernal.utils.SPUtils;
import com.moiragod.app.R;
import com.moiragod.app.databinding.ActivityMainBinding;
import com.moiragod.app.databinding.NavHeaderMainBinding;
import com.moiragod.constant.ConstantsImageUrl;
import com.moiragod.ui.book.BookFragment;
import com.moiragod.ui.gank.GankFragment;
import com.moiragod.ui.menu.NavAboutActivity;
import com.moiragod.ui.menu.NavDownloadActivity;
import com.moiragod.ui.menu.NavFeedbackActivity;
import com.moiragod.ui.menu.NavHomePageActivity;
import com.moiragod.ui.one.OneFragment;
import com.moiragod.utils.ImageLoadUtils;
import com.moiragod.view.HomeFragmentPagerAdapter;
import com.moiragod.view.statusbar.StatusBarUtil;
import com.moiragod.view.webview.WebViewActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private FrameLayout mTitleMenu;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatButton;
    private NavigationView mNavView;
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;

    // 一定需要对应的bean
    private ActivityMainBinding mBinding;
    //    private Activity
    private ImageView mTitleGank;
    private ImageView mTitleOne;
    private ImageView mTitleDou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initStatusView();
        initLayoutView();
        initContentFragment();
        initDrawerLayout();
        initListener();
    }

    private void initStatusView() {
        ViewGroup.LayoutParams layoutParams = mBinding.include.viewStatus.getLayoutParams();
        layoutParams.height = StatusBarUtil.getStatusBarHeight(this);
        mBinding.include.viewStatus.setLayoutParams(layoutParams);
    }

    private void initLayoutView() {
        mDrawerLayout = mBinding.drawerLayout;
        mNavView = mBinding.navView;
        mFloatButton = mBinding.include.fab;
        mToolbar = mBinding.include.toolbar;
        mTitleMenu = mBinding.include.llTitleMenu;
        mViewPager = mBinding.include.vpViewpager;
        mFloatButton.setVisibility(View.GONE);

        mTitleGank = mBinding.include.ivTitleGank;
        mTitleOne = mBinding.include.ivTitleOne;
        mTitleDou = mBinding.include.ivTitleDou;

        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this, mDrawerLayout,
                CommonUtils.getColor(R.color.colorTheme));
    }

    private void initContentFragment() {
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(GankFragment.newInstance());
        mFragmentList.add(BookFragment.newInstance());
        mFragmentList.add(OneFragment.newInstance());
        // 注意使用的是：getSupportFragmentManager
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数(cpu消耗少)
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.addOnPageChangeListener(this);
        mBinding.include.ivTitleGank.setSelected(true);
        mViewPager.setCurrentItem(0);

        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private NavHeaderMainBinding mHeaderBinding;

    /**
     * inflateHeaderView 进来的布局要宽一些
     */
    private void initDrawerLayout() {
        mNavView.inflateHeaderView(R.layout.nav_header_main);
        View headerView = mNavView.getHeaderView(0);
        mHeaderBinding = DataBindingUtil.bind(headerView);
        mHeaderBinding.setListener(this);
        mHeaderBinding.dayNightSwitch.setChecked(SPUtils.getNightMode());

        ImageLoadUtils.displayCircle(mHeaderBinding.ivAvatar, ConstantsImageUrl.IC_AVATAR);
        mHeaderBinding.llNavExit.setOnClickListener(this);
        mHeaderBinding.ivAvatar.setOnClickListener(this);

        mHeaderBinding.llNavHomepage.setOnClickListener(listener);
        mHeaderBinding.llNavScanDownload.setOnClickListener(listener);
        mHeaderBinding.llNavDeedback.setOnClickListener(listener);
        mHeaderBinding.llNavAbout.setOnClickListener(listener);
        mHeaderBinding.llNavLogin.setOnClickListener(listener);
    }

    private PerfectClickListener listener = new PerfectClickListener() {
        @Override
        protected void onNextClickEvent(final View v) {
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
            mBinding.drawerLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    switch (v.getId()) {
                        case R.id.ll_nav_homepage:// 主页
                            NavHomePageActivity.start(MainActivity.this);
                            break;
                        case R.id.ll_nav_scan_download://扫码下载
                            NavDownloadActivity.start(MainActivity.this);
                            break;
                        case R.id.ll_nav_deedback:// 问题反馈
                            NavFeedbackActivity.start(MainActivity.this);
                            break;
                        case R.id.ll_nav_about:// 关于云阅
                            NavAboutActivity.start(MainActivity.this);
                            break;
                        case R.id.ll_nav_login:// 登录GitHub账号
                            WebViewActivity.loadUrl(v.getContext(), "https://github.com/login", "Github login");
                            break;
                    }
                }
            }, 260);
        }
    };

    private void initListener() {
        mTitleMenu.setOnClickListener(this);
        mBinding.include.ivTitleGank.setOnClickListener(this);
        mBinding.include.ivTitleDou.setOnClickListener(this);
        mBinding.include.ivTitleOne.setOnClickListener(this);
        mFloatButton.setOnClickListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mTitleGank.setSelected(true);
                mTitleOne.setSelected(false);
                mTitleDou.setSelected(false);
                break;
            case 1:
                mTitleOne.setSelected(true);
                mTitleGank.setSelected(false);
                mTitleDou.setSelected(false);
                break;
            case 2:
                mTitleDou.setSelected(true);
                mTitleOne.setSelected(false);
                mTitleGank.setSelected(false);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                mBinding.drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                // 不退出程序，进入后台
                moveTaskToBack(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_title_menu:// 开启菜单
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_title_gank:// 干货栏
                if (mViewPager.getCurrentItem() != 0) {//不然cpu会有损耗
                    mTitleGank.setSelected(true);
                    mTitleOne.setSelected(false);
                    mTitleDou.setSelected(false);
                    mViewPager.setCurrentItem(0);
                }
                break;
            case R.id.iv_title_one:// 电影栏
                if (mViewPager.getCurrentItem() != 1) {
                    mTitleOne.setSelected(true);
                    mTitleGank.setSelected(false);
                    mTitleDou.setSelected(false);
                    mViewPager.setCurrentItem(1);
                }
                break;
            case R.id.iv_title_dou:// 书籍栏
                if (mViewPager.getCurrentItem() != 2) {
                    mTitleDou.setSelected(true);
                    mTitleOne.setSelected(false);
                    mTitleGank.setSelected(false);
                    mViewPager.setCurrentItem(2);
                }
                break;
//            case R.id.iv_avatar: // 头像进入GitHub
//                WebViewActivity.loadUrl(v.getContext(),CommonUtils.getString(R.string.string_url_cloudreader),"CloudReader");
//                break;
            case R.id.ll_nav_exit:// 退出应用
                finish();
                break;
            default:
                break;
        }
    }

    public boolean getNightMode() {
        return false;
    }

    public void onNightModeClick(View view) {
    }
}