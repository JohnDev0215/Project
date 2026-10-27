package com.android.systemui.stackdivider.view.help;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.util.BlurUtil;
import com.android.systemui.util.FastClickUtil;
import com.android.systemui.util.ScreenShotUtil;
import com.android.systemui.widget.BydImageView;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SplitScreenHelpManager {
    private static final int DARK_LAND_INDEX = 0;
    private static final int DARK_PORT_INDEX = 1;
    private static final int LIGHT_LAND_INDEX = 2;
    private static final int LIGHT_PORT_INDEX = 3;
    private static final int PAGE_NUM = 4;
    private static final int PAGE_TYPE_EDIT_SPLIT_SCREEN = 2;
    private static final int PAGE_TYPE_EXIT_SPLIT_SCREEN = 3;
    private static final int PAGE_TYPE_GESTURES = 1;
    private static final int PAGE_TYPE_START_SPLIT_SCREEN = 0;
    private static volatile SplitScreenHelpManager mSplitScreenHelpManager;
    private BydImageView ivPage_1;
    private BydImageView ivPage_2;
    private BydImageView ivPage_3;
    private BydImageView ivPage_4;
    private FrameLayout mContainer;
    private final Context mContext;
    private final Divider mDivider;
    private int[] mFirstBitmapResIds;
    private final List<int[]> mFirstFrameResIds;
    private final View.OnClickListener mOnClickListener;
    private RelativeLayout mRlRoot;
    private SplitPagerAdapter mSplitPagerAdapter;
    private View mSplitRootView;
    private NoScrollViewPager mViewPager;
    private final List<SplitNavPageView> mViews;
    private final WindowManager mWindowManager;
    private TextView tvClose;
    private TextView tvExperience;
    private TextView tvNext;
    private boolean isShowHelpView = false;
    private int mCurrentPosition = 0;
    private final int[] mTitles = {R.string.split_screen_start_split_screen, R.string.split_screen_gestures, R.string.split_screen_edit_split_screen, R.string.split_screen_exit_split_screen};
    private final int[] mSubTitles = {R.string.split_screen_sub_start_split_screen, R.string.split_screen_sub_gestures, R.string.split_screen_sub_edit_split_screen, R.string.split_screen_sub_exit_split_screen};

    private SplitScreenHelpManager(Context context) {
        ArrayList arrayList = new ArrayList(4);
        this.mFirstFrameResIds = arrayList;
        this.mFirstBitmapResIds = new int[4];
        this.mOnClickListener = new View.OnClickListener() { // from class: com.android.systemui.stackdivider.view.help.SplitScreenHelpManager.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.tvExperience) {
                    BydLogHelper.logD("experience click");
                    SplitScreenHelpManager.this.hideSplitNavView();
                    if (SplitScreenHelpManager.this.mDivider.isSplitActive()) {
                        return;
                    }
                    ((StatusBar) SysUiServiceProvider.getComponent(SplitScreenHelpManager.this.mContext, StatusBar.class)).toggleSplitScreenMode();
                    return;
                }
                if (id == R.id.tvNext) {
                    if (FastClickUtil.isFastDoubleClick(500L)) {
                        BydLogHelper.logD("onClick next is fast click");
                        return;
                    }
                    BydLogHelper.logD("next step click");
                    SplitScreenHelpManager.this.mViewPager.setCurrentItem(SplitScreenHelpManager.this.mViewPager.getCurrentItem() + 1);
                    SplitScreenHelpManager.this.mViewPager.setScanScroll(false);
                    return;
                }
                if (id == R.id.tvClose) {
                    BydLogHelper.logD("close click");
                    if (!SplitScreenHelpManager.this.mDivider.isSplitActive()) {
                        ((StatusBar) SysUiServiceProvider.getComponent(SplitScreenHelpManager.this.mContext, StatusBar.class)).toggleSplitScreenMode();
                    }
                    SplitScreenHelpManager.this.hideSplitNavView();
                }
            }
        };
        this.mContext = context;
        this.mViews = new ArrayList();
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mDivider = (Divider) SysUiServiceProvider.getComponent(context, Divider.class);
        int[] iArr = {R.mipmap.dark_land_0, R.mipmap.dark_land_1, R.mipmap.dark_land_2, R.mipmap.dark_land_3};
        int[] iArr2 = {R.mipmap.dark_port_0, R.mipmap.dark_port_1, R.mipmap.dark_port_2, R.mipmap.dark_port_3};
        int[] iArr3 = {R.mipmap.light_land_0, R.mipmap.light_land_1, R.mipmap.light_land_2, R.mipmap.light_land_3};
        int[] iArr4 = {R.mipmap.light_port_0, R.mipmap.light_port_1, R.mipmap.light_port_2, R.mipmap.light_port_3};
        arrayList.add(0, iArr);
        arrayList.add(1, iArr2);
        arrayList.add(2, iArr3);
        arrayList.add(3, iArr4);
    }

    public static SplitScreenHelpManager getInstance(Context context) {
        if (mSplitScreenHelpManager == null) {
            synchronized (SplitScreenHelpManager.class) {
                if (mSplitScreenHelpManager == null) {
                    mSplitScreenHelpManager = new SplitScreenHelpManager(context);
                }
            }
        }
        return mSplitScreenHelpManager;
    }

    private void inflateContainer() {
        FrameLayout frameLayout = this.mContainer;
        if (frameLayout == null) {
            BydLogHelper.logD("inflateContainer mContainer is null!");
            return;
        }
        frameLayout.removeAllViews();
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.activity_split_screen_help, (ViewGroup) null);
        this.mSplitRootView = viewInflate;
        this.mContainer.addView(viewInflate);
        this.mSplitRootView.setFocusable(true);
        this.mSplitRootView.setFocusableInTouchMode(true);
        this.mSplitRootView.requestFocus();
        this.mSplitRootView.setOnKeyListener(new View.OnKeyListener() { // from class: com.android.systemui.stackdivider.view.help.-$$Lambda$SplitScreenHelpManager$9EvIPkw8z2VXUXOzF-12P1Ps83Q
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return this.f$0.lambda$inflateContainer$0$SplitScreenHelpManager(view, i, keyEvent);
            }
        });
        boolean z = (this.mContext.getResources().getConfiguration().uiMode & 48) == 32;
        boolean z2 = this.mWindowManager.getDefaultDisplay().getRotation() == 0;
        BydLogHelper.logD("inflateContainer isLandscape = " + z2 + " , isDarkMode : " + z);
        if (this.mFirstFrameResIds.size() == 4) {
            if (z && z2) {
                this.mFirstBitmapResIds = this.mFirstFrameResIds.get(0);
            } else if (!z && z2) {
                this.mFirstBitmapResIds = this.mFirstFrameResIds.get(2);
            } else if (z) {
                this.mFirstBitmapResIds = this.mFirstFrameResIds.get(1);
            } else {
                this.mFirstBitmapResIds = this.mFirstFrameResIds.get(3);
            }
        }
        initView(z, z2);
        initListener();
        if (this.mDivider.isSplitActive()) {
            this.mDivider.updateStatusBarBg(false);
        }
    }

    public /* synthetic */ boolean lambda$inflateContainer$0$SplitScreenHelpManager(View view, int i, KeyEvent keyEvent) {
        BydLogHelper.logD("showHelpView onKey keyCode = " + i + " , event : " + keyEvent);
        if (i != 4 || keyEvent.getAction() != 1) {
            return false;
        }
        hideSplitNavView();
        return true;
    }

    public void showHelpView() {
        BydLogHelper.logD("showHelpView start ----");
        BydLogHelper.logD("showHelpView isShowHelpActivity = " + this.isShowHelpView);
        if (this.isShowHelpView) {
            return;
        }
        BydLogHelper.logD("showHelpView mSplitNavRootView = " + this.mSplitRootView);
        if (this.mContainer == null) {
            this.mContainer = new FrameLayout(this.mContext);
        }
        inflateContainer();
        if (this.mDivider.isSplitActive()) {
            this.mDivider.setIndexWindowManagerVisibility(false);
        }
        this.mWindowManager.addView(this.mContainer, getLayoutParams());
        resetStatusBarIcon();
        this.isShowHelpView = true;
        BydLogHelper.logD("showHelpView end ----");
    }

    private void resetStatusBarIcon() {
        ((StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class)).updateStatusBarIconSplitHelpView();
    }

    private void resetStatusBarIconOnExit() {
        ((StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class)).updateStatusBarIconSplitHelpViewExit();
    }

    private void initView(boolean isDarkMode, boolean isLandscape) {
        this.tvClose = (TextView) this.mSplitRootView.findViewById(R.id.tvClose);
        this.tvNext = (TextView) this.mSplitRootView.findViewById(R.id.tvNext);
        this.tvExperience = (TextView) this.mSplitRootView.findViewById(R.id.tvExperience);
        this.mRlRoot = (RelativeLayout) this.mSplitRootView.findViewById(R.id.rl_root);
        this.mViewPager = (NoScrollViewPager) this.mSplitRootView.findViewById(R.id.view_pager);
        if (AppUtils.isRtl(this.mContext)) {
            this.mViewPager.setRotationY(180.0f);
        }
        this.ivPage_1 = (BydImageView) this.mSplitRootView.findViewById(R.id.ivPage_1);
        this.ivPage_2 = (BydImageView) this.mSplitRootView.findViewById(R.id.ivPage_2);
        this.ivPage_3 = (BydImageView) this.mSplitRootView.findViewById(R.id.ivPage_3);
        this.ivPage_4 = (BydImageView) this.mSplitRootView.findViewById(R.id.ivPage_4);
        ((BydImageView) this.mSplitRootView.findViewById(R.id.ivBg)).setBackgroundResource(R.color.color_split_screen_help_bg);
        try {
            setBlurBackground();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BydLogHelper.logD("initView new SplitNavPageView 1");
        this.mViews.clear();
        for (int i = 0; i < this.mFirstBitmapResIds.length; i++) {
            this.mViews.add(new SplitNavPageView(this.mContext, this.mTitles[i], this.mSubTitles[i], getVideoUri(isDarkMode, isLandscape, i), this.mFirstBitmapResIds[i]));
        }
        BydLogHelper.logD("initView new SplitNavPageView 2");
        this.mSplitPagerAdapter = new SplitPagerAdapter(this.mViews);
        this.mViewPager.setOffscreenPageLimit(0);
        this.mViewPager.setAdapter(this.mSplitPagerAdapter);
        this.mSplitPagerAdapter.notifyDataSetChanged();
        BydLogHelper.logD("initView mCurrentPosition = " + this.mCurrentPosition);
        startVideo(this.mCurrentPosition);
        this.mViewPager.setCurrentItem(this.mCurrentPosition);
        if (this.mCurrentPosition == 3) {
            this.tvClose.setVisibility(8);
            this.tvNext.setVisibility(8);
            this.tvExperience.setVisibility(0);
        } else {
            this.tvClose.setVisibility(0);
            this.tvNext.setVisibility(0);
            this.tvExperience.setVisibility(8);
        }
    }

    public void setBlurBackground() {
        Bitmap bitmapTakeScreenShot = ScreenShotUtil.takeScreenShot(this.mContext);
        if (bitmapTakeScreenShot == null) {
            bitmapTakeScreenShot = BitmapFactory.decodeResource(this.mContext.getResources(), R.mipmap.img_app_bg);
        }
        Bitmap bitmapBlur = BlurUtil.blur(this.mContext, bitmapTakeScreenShot, 10);
        RelativeLayout relativeLayout = this.mRlRoot;
        if (relativeLayout == null || bitmapBlur == null) {
            return;
        }
        relativeLayout.setBackground(new BitmapDrawable(this.mContext.getResources(), bitmapBlur));
    }

    private void initListener() {
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.android.systemui.stackdivider.view.help.SplitScreenHelpManager.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                BydLogHelper.logD("onPageSelected position : " + position);
                SplitScreenHelpManager.this.mCurrentPosition = position;
                SplitScreenHelpManager splitScreenHelpManager = SplitScreenHelpManager.this;
                splitScreenHelpManager.startVideo(splitScreenHelpManager.mCurrentPosition);
                if (position == 3) {
                    SplitScreenHelpManager.this.tvClose.setVisibility(8);
                    SplitScreenHelpManager.this.tvNext.setVisibility(8);
                    SplitScreenHelpManager.this.tvExperience.setVisibility(0);
                } else {
                    SplitScreenHelpManager.this.tvClose.setVisibility(0);
                    SplitScreenHelpManager.this.tvNext.setVisibility(0);
                    SplitScreenHelpManager.this.tvExperience.setVisibility(8);
                }
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
                BydLogHelper.logD("onPageScrollStateChanged state : " + state);
                if (state == 0) {
                    SplitScreenHelpManager.this.mViewPager.setScanScroll(true);
                }
            }
        });
        this.tvClose.setOnClickListener(this.mOnClickListener);
        this.tvNext.setOnClickListener(this.mOnClickListener);
        this.tvExperience.setOnClickListener(this.mOnClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startVideo(int page) {
        if (this.mViews == null) {
            return;
        }
        BydLogHelper.logD("startVideo page = " + page + " , mViews.size() : " + this.mViews.size());
        for (int i = 0; i < this.mViews.size(); i++) {
            SplitNavPageView splitNavPageView = this.mViews.get(i);
            if (i == page) {
                splitNavPageView.startVideo();
                updatePage(page);
            } else {
                splitNavPageView.stopVideo();
            }
        }
    }

    private String getVideoUri(boolean darkMode, boolean landscape, int position) {
        String str = ("android.resource://" + this.mContext.getPackageName() + "/") + this.mContext.getResources().getIdentifier(("nav_split_video_" + (darkMode ? "dark_" : "light_") + (landscape ? "land_" : "port_")) + position, "raw", this.mContext.getPackageName());
        BydLogHelper.logD("videoUri : " + str);
        return str;
    }

    private WindowManager.LayoutParams getLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = 2001;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.format = -2;
        layoutParams.flags = 142608160;
        layoutParams.gravity = GravityCompat.START;
        layoutParams.setTitle("SplitScreenHelpManager");
        return layoutParams;
    }

    private void updatePage(int pageType) {
        this.ivPage_1.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.drawable.split_screen_help_page_common_bg, null));
        this.ivPage_2.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.drawable.split_screen_help_page_common_bg, null));
        this.ivPage_3.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.drawable.split_screen_help_page_common_bg, null));
        this.ivPage_4.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.drawable.split_screen_help_page_common_bg, null));
        if (pageType == 0) {
            this.ivPage_1.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.drawable.split_screen_help_page_high_light_bg, null));
            return;
        }
        if (pageType == 1) {
            this.ivPage_2.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.drawable.split_screen_help_page_high_light_bg, null));
        } else if (pageType == 2) {
            this.ivPage_3.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.drawable.split_screen_help_page_high_light_bg, null));
        } else if (pageType == 3) {
            this.ivPage_4.setBackground(ResourcesCompat.getDrawable(this.mContext.getResources(), R.drawable.split_screen_help_page_high_light_bg, null));
        }
    }

    public void hideSplitNavView() {
        BydLogHelper.logD("hideSplitNavView isShowHelpActivity : " + this.isShowHelpView);
        if (this.isShowHelpView) {
            BydLogHelper.logD("hideSplitNavView isSplitActive : " + this.mDivider.isSplitActive());
            this.mCurrentPosition = 0;
            if (this.mDivider.isSplitActive()) {
                this.mDivider.updateStatusBarBg(true);
                this.mDivider.setIndexWindowManagerVisibility(true);
                new Handler().postDelayed(new Runnable() { // from class: com.android.systemui.stackdivider.view.help.-$$Lambda$SplitScreenHelpManager$BFOM-WGDRiqzdb05ZM42Rmmzr8A
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.reset();
                    }
                }, 200L);
                return;
            }
            reset();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        List<SplitNavPageView> list = this.mViews;
        if (list != null) {
            Iterator<SplitNavPageView> it = list.iterator();
            while (it.hasNext()) {
                it.next().release();
            }
            this.mViews.clear();
        }
        FrameLayout frameLayout = this.mContainer;
        if (frameLayout != null) {
            this.mWindowManager.removeViewImmediate(frameLayout);
            resetStatusBarIconOnExit();
        }
        this.mContainer = null;
        this.mSplitRootView = null;
        this.mSplitPagerAdapter = null;
        this.isShowHelpView = false;
        this.mRlRoot = null;
        this.tvClose = null;
        this.tvNext = null;
        this.tvExperience = null;
        this.ivPage_1 = null;
        this.ivPage_2 = null;
        this.ivPage_3 = null;
        this.ivPage_4 = null;
        this.mViewPager = null;
    }

    public boolean isShowHelpActivity() {
        return this.isShowHelpView;
    }

    public void onConfigurationChanged() {
        BydLogHelper.logD("onConfigurationChanged isShowHelpActivity : " + isShowHelpActivity());
        if (isShowHelpActivity()) {
            inflateContainer();
        }
    }
}
