package com.android.launcher3.home;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.bydauto.setting.AbsBYDAutoSettingListener;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.LauncherManager;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.launcher3.R;
import com.android.launcher3.home.banner.Banner;
import com.android.launcher3.home.banner.IndicatorView;
import com.android.launcher3.home.listener.OnApplicationCallBack;
import com.android.launcher3.home.listener.OnBroadcastReceiverCallBack;
import com.android.launcher3.home.listener.OnViewPageChangeListener;
import com.android.launcher3.home.pageutil.BannerUtils;
import com.android.launcher3.home.pageutil.BitmapUtils;
import com.android.launcher3.home.pageutil.CommotUtils;
import com.android.launcher3.home.pageutil.CountTimer;
import com.android.launcher3.home.pageutil.PreferencesUtils;
import com.android.launcher3.home.receiver.AppStateBroadcastReceiver;
import com.android.launcher3.home.receiver.ThemeBroadcastReceiver;
import com.android.launcher3.home.viewpage.ImageHolderCreator;
import com.android.launcher3.home.viewpage.SysWallPage;
import com.android.launcher3.home.viewpage.ViewpagerTransformAnim;
import com.android.launcher3.home.viewpage.WallPage;
import com.android.launcher3.home.viewpage.WallPaperHandler;
import com.android.launcher3.util.Logger;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class MainActivity extends BaseDispatchTouchActivity {
    private AppStateBroadcastReceiver appStateBroadcastReceiver;
    private Banner banner;
    private BYDAutoSettingDevice device;
    private ImageView imgMap;
    private ImageView imgSetting;
    private IndicatorView indicatorView;
    private Thread mGetBitmapList;
    private Thread mGetFirstBitmap;
    private String mLanguage;
    private int mOrientation;
    private AbsBYDAutoSettingListener mSettingListener;
    private int mSteeringWheelPosition;
    private View mView;
    private ThemeBroadcastReceiver themeBroadcastReceiver;
    private LinkedHashMap<String, ParcelFileDescriptor> wallpaperFiles;
    private WallpaperManager wallpaperManager;
    private ArrayList<WallPage> image = new ArrayList<>();
    private ArrayList<WallPage> imageList = new ArrayList<>();
    private int currentPage = 0;
    private boolean isOnCreate = true;
    OnViewPageChangeListener onViewPageChangeListener = new OnViewPageChangeListener() { // from class: com.android.launcher3.home.MainActivity.7
        @Override // com.android.launcher3.home.listener.OnViewPageChangeListener
        public void OnViewPageChange() {
            Intent intent = new Intent(Constants.ACTION_WALLPAPER_UPDATE);
            intent.putExtra(Constants.SEND_MESSAGE_TO_SYSTEMUI, MainActivity.this.banner.getCurrentPager());
            MainActivity.this.getApplicationContext().sendBroadcast(intent);
            MainActivity.this.homeHandler.sendEmptyMessage(18);
        }
    };
    OnBroadcastReceiverCallBack onBroadcastReceiverCallBack = new OnBroadcastReceiverCallBack() { // from class: com.android.launcher3.home.MainActivity.8
        @Override // com.android.launcher3.home.listener.OnBroadcastReceiverCallBack
        public void OnBroadcastReceiverCallBack(String packageName) {
            if (packageName.equals(Constants.PKG_MAP_LAUNCHER) || packageName.equals(Constants.PKG_MAP_LAUNCHER_HW)) {
                MainActivity.this.setMapAppIconVisibility();
            }
        }

        @Override // com.android.launcher3.home.listener.OnBroadcastReceiverCallBack
        public void OnBroadcastReceiverCallBack(int type) {
            if (type == 24) {
                MainActivity.this.thirdAppSetWallpaperReturn();
            }
        }
    };
    public final WallPaperHandler homeHandler = new WallPaperHandler(new Handler.Callback() { // from class: com.android.launcher3.home.MainActivity.9
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            int i = msg.what;
            Object obj = msg.obj;
            try {
                if (i == 15) {
                    Logger.e("pengbin", "ADD_LOCAL_FIRST_WALLPAPER.....0 == " + MainActivity.this.image.size());
                    MainActivity.this.banner.setPages(MainActivity.this.image);
                    if (MainActivity.this.mGetBitmapList != null) {
                        MainActivity.this.mGetBitmapList.interrupt();
                    }
                    MainActivity.this.mGetBitmapList = new Thread(new Runnable() { // from class: com.android.launcher3.home.MainActivity.9.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity.this.addLocalWallPaperDelay();
                        }
                    });
                    MainActivity.this.mGetBitmapList.setName(Boolean.toString(BannerUtils.isLand(MainActivity.this.getApplicationContext())));
                    MainActivity.this.mGetBitmapList.start();
                    Logger.e("pengbin", "ADD_LOCAL_FIRST_WALLPAPER before cancelStartAnimate");
                    MainActivity.this.cancelStartAnimate();
                } else if (i == 16) {
                    Logger.e("pengbin", "ADD_REMOTE_FIRST_WALLPAPER.....0 == " + MainActivity.this.image.size());
                    MainActivity.this.banner.setPages(MainActivity.this.image);
                    if (MainActivity.this.mGetBitmapList != null) {
                        MainActivity.this.mGetBitmapList.interrupt();
                    }
                    MainActivity.this.mGetBitmapList = new Thread(new Runnable() { // from class: com.android.launcher3.home.MainActivity.9.2
                        @Override // java.lang.Runnable
                        public void run() {
                            MainActivity.this.addRemoteWallPaperDelay();
                        }
                    });
                    MainActivity.this.mGetBitmapList.setName(Boolean.toString(BannerUtils.isLand(MainActivity.this.getApplicationContext())));
                    MainActivity.this.mGetBitmapList.start();
                    Logger.e("pengbin", "ADD_REMOTE_FIRST_WALLPAPER before cancelStartAnimate");
                    MainActivity.this.cancelStartAnimate();
                    Logger.e("pengbin", "ADD_REMOTE_FIRST_WALLPAPER.....222 == ");
                } else if (i == 5) {
                    Logger.e("pengbin", "ADD_LOCAL_WALLPAPER_DELAY......0 == " + MainActivity.this.imageList.size());
                    MainActivity.this.banner.setPages(MainActivity.this.imageList, MainActivity.this.currentPage);
                    if (MainActivity.this.mCountTimerView != null) {
                        MainActivity.this.mCountTimerView.start();
                    }
                    if (PreferencesUtils.getInt(MainActivity.this.getApplicationContext(), Constants.WALLPAPER_TYPE, 10) == 10) {
                        MainActivity.this.banner.setVisibility(0);
                    }
                    if (MainActivity.this.banner != null) {
                        MainActivity.this.banner.setAutoPlay(true);
                    }
                } else if (i == 13) {
                    Logger.e("pengbin", "ADD_REMOTE_WALLPAPER_DELAY......0 == " + MainActivity.this.imageList.size());
                    MainActivity.this.banner.setPages(MainActivity.this.imageList, MainActivity.this.currentPage);
                    if (MainActivity.this.mCountTimerView != null) {
                        MainActivity.this.mCountTimerView.start();
                    }
                    if (PreferencesUtils.getInt(MainActivity.this.getApplicationContext(), Constants.WALLPAPER_TYPE, 10) == 10) {
                        MainActivity.this.banner.setVisibility(0);
                    }
                    if (MainActivity.this.banner != null) {
                        MainActivity.this.banner.setAutoPlay(true);
                    }
                } else if (i == 17) {
                    Logger.e("pengbin", "ADD_CONFIG_CHANGED_WALLPAPER....");
                    MainActivity.this.banner.setPages(MainActivity.this.imageList, MainActivity.this.currentPage);
                    MainActivity.this.homeHandler.sendEmptyMessage(18);
                } else if (i == 3) {
                    Logger.e("pengbin", "REPLACE_DESKTOP....");
                    Settings.System.putInt(MainActivity.this.getContentResolver(), Constants.LAUNCHER3_REPLACE_DESKTOP, 2);
                } else if (i == 14) {
                    MainActivity.this.indicatorView.setVisibility(8);
                } else if (i == 18) {
                    MainActivity.this.indicatorView.setVisibility(0);
                    if (MainActivity.this.mCountTimerView != null) {
                        MainActivity.this.mCountTimerView.start();
                    }
                } else if (i == 25) {
                    Logger.e("pengbin", "SWITCH_TO_MAP_LAUNCHER.....1");
                }
            } catch (Exception e) {
                Logger.e("pengbin", "DELAY_SET_WALLPAPER......" + e);
            }
            return true;
        }
    });
    OnApplicationCallBack onApplicationCallBack = new OnApplicationCallBack() { // from class: com.android.launcher3.home.MainActivity.10
        @Override // com.android.launcher3.home.listener.OnApplicationCallBack
        public void onConfigurationChanged(Configuration newConfig) {
            Logger.e("pengbin", "HomeApplication......onConfigurationChanged == " + MainActivity.this.mLanguage + " == " + MainActivity.this.mSteeringWheelPosition);
            if (newConfig.orientation == MainActivity.this.mOrientation) {
                if (MainActivity.this.mLanguage.equals(Locale.getDefault().getLanguage()) || MainActivity.this.mSteeringWheelPosition == MainActivity.this.getSteeringWheelPosition()) {
                    return;
                }
                MainActivity mainActivity = MainActivity.this;
                mainActivity.mSteeringWheelPosition = mainActivity.getSteeringWheelPosition();
                MainActivity.this.mLanguage = Locale.getDefault().getLanguage();
                MainActivity.this.layoutInflaterLayoutView(true);
                return;
            }
            MainActivity mainActivity2 = MainActivity.this;
            mainActivity2.currentPage = mainActivity2.banner.getCurrentPager();
            MainActivity.this.layoutInflaterLayoutView(true);
        }

        @Override // com.android.launcher3.home.listener.OnApplicationCallBack
        public void onBroadcastReceiverCallBack(int type) {
            if (type == 2) {
                Logger.e("pengbin", "百变主题设置轮播壁纸...成功 == " + (MainActivity.this.wallpaperFiles == null));
                MainActivity.this.wallpaperFiles = HomeApplication.getInstance().getWallpaperFiles();
                MainActivity.this.currentPage = 0;
                if (MainActivity.this.wallpaperFiles == null) {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.setFirstBitmap(BannerUtils.getLocalFirstBitmap(mainActivity.getApplicationContext()), 11);
                    MainActivity.this.homeHandler.sendEmptyMessage(15);
                } else {
                    MainActivity mainActivity2 = MainActivity.this;
                    MainActivity.this.setFirstBitmap(mainActivity2.getFirstWallpaperFilesToBitMaps(mainActivity2.wallpaperFiles), 12);
                    MainActivity.this.homeHandler.sendEmptyMessage(16);
                }
                HomeApplication.getInstance().clearWallPaper();
                return;
            }
            if (type == 4) {
                if (MainActivity.this.banner != null) {
                    MainActivity.this.banner.setAutoTurningTime(PreferencesUtils.getLong(MainActivity.this.getApplicationContext(), Constants.CHANGE_TIME, 86400000L));
                    MainActivity.this.banner.setAutoPlay(true);
                    Logger.e("pengbin", "百变主题更改了轮播速度...成功");
                    return;
                }
                return;
            }
            if (type == 22) {
                MainActivity.this.recycleBitMap(1);
                MainActivity.this.recycleBitMapList(3);
                if (MainActivity.this.banner != null) {
                    MainActivity.this.banner.setAutoPlay(false);
                }
            }
        }

        @Override // com.android.launcher3.home.listener.OnApplicationCallBack
        public void onCancelStartAnimate() {
            Logger.e("pengbin", "onApplicationCallBack onCancelStartAnimate");
            MainActivity.this.cancelStartAnimate();
        }
    };

    @Override // android.app.Activity
    public void onBackPressed() {
    }

    @Override // com.android.launcher3.home.BaseDispatchTouchActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.e("pengbin", "MainActivity.....onCreate...." + savedInstanceState);
        HomeApplication.getInstance().setApplicationCallBack(this.onApplicationCallBack);
        if (savedInstanceState != null) {
            this.currentPage = savedInstanceState.getInt(Constants.SAVE_PAGE_INDEX, 0);
        }
        this.mLanguage = Locale.getDefault().getLanguage();
        this.mSteeringWheelPosition = getSteeringWheelPosition();
        Window window = getWindow();
        window.clearFlags(67108864);
        window.addFlags(Integer.MIN_VALUE);
        window.getDecorView().setSystemUiVisibility(5888);
        window.setNavigationBarColor(0);
        window.setStatusBarColor(0);
        this.wallpaperManager = WallpaperManager.getInstance(this);
        layoutInflaterLayoutView(false);
        this.device = BYDAutoSettingDevice.getInstance(this);
        registerThemeReceiver();
        registerAppStateReceiver();
        registerSettingListener();
    }

    private void registerSettingListener() {
        AbsBYDAutoSettingListener absBYDAutoSettingListener = new AbsBYDAutoSettingListener() { // from class: com.android.launcher3.home.MainActivity.1
            @Override // android.hardware.IBYDAutoListener
            public void isConnected(boolean isBinding) {
                Log.e("pengbin", "registerSettingListener.....isConnected = " + isBinding);
            }
        };
        this.mSettingListener = absBYDAutoSettingListener;
        this.device.registerListener(absBYDAutoSettingListener);
    }

    private void registerThemeReceiver() {
        this.themeBroadcastReceiver = new ThemeBroadcastReceiver(this.onBroadcastReceiverCallBack);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.THEME_GET_WALLPAPER_STATE);
        registerReceiver(this.themeBroadcastReceiver, intentFilter);
    }

    private void registerAppStateReceiver() {
        this.appStateBroadcastReceiver = new AppStateBroadcastReceiver(this.onBroadcastReceiverCallBack);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addDataScheme("package");
        registerReceiver(this.appStateBroadcastReceiver, intentFilter);
    }

    public synchronized void layoutInflaterLayoutView(boolean isConfigurationChanged) {
        Logger.e("pengbin", "MainActivity.....layoutInflaterLayoutView == " + isConfigurationChanged);
        if (BannerUtils.isLand(getApplicationContext())) {
            this.mOrientation = 2;
        } else {
            this.mOrientation = 1;
        }
        Logger.e("pengbin", "MainActivity...mSteeringWheelPosition == " + this.mSteeringWheelPosition + " mOrientation == " + this.mOrientation);
        if (this.mSteeringWheelPosition == 0) {
            if (this.mOrientation == 2) {
                this.mView = LayoutInflater.from(this).inflate(R.layout.activity_main_land, (ViewGroup) null);
            } else {
                this.mView = LayoutInflater.from(this).inflate(R.layout.activity_main, (ViewGroup) null);
            }
        } else if (this.mOrientation == 2) {
            this.mView = LayoutInflater.from(this).inflate(R.layout.activity_main_land_right, (ViewGroup) null);
        } else {
            this.mView = LayoutInflater.from(this).inflate(R.layout.activity_main_right, (ViewGroup) null);
        }
        this.mView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        setContentView(this.mView);
        initView();
        initListener();
        initBanner(isConfigurationChanged);
    }

    private void initView() {
        Logger.e("pengbin", "initView");
        this.banner = (Banner) findViewById(R.id.banner);
        this.imgMap = (ImageView) findViewById(R.id.img_map);
        this.imgSetting = (ImageView) findViewById(R.id.img_setting);
        this.banner.setOnViewPageChangeListener(this.onViewPageChangeListener);
        setMapAppIconVisibility();
        this.indicatorView = new IndicatorView(this).setIndicatorColor(getColor(R.color.indicator_default)).setIndicatorSelectorColor(getColor(R.color.indicator_selected));
        this.homeHandler.sendEmptyMessage(14);
        Logger.e("pengbin", "uiConfig == " + HomeApplication.getInstance().uiConfig);
        setUiModeBg(HomeApplication.getInstance().uiConfig, isLightMode());
    }

    public void setMapAppIconVisibility() {
        Logger.e("pengbin", "setMapAppIconVisibility");
        if (this.imgMap == null) {
            return;
        }
        Logger.e("pengbin", "setMapAppIconVisibility,test isAppInstalled");
        if (isAppInstalled(Constants.PKG_MAP_LAUNCHER) || isAppInstalled(Constants.PKG_MAP_LAUNCHER_HW)) {
            Logger.e("pengbin", "isAppInstalled.....true");
            this.imgMap.setVisibility(0);
        } else {
            Logger.e("pengbin", "isAppInstalled.....false");
            this.imgMap.setVisibility(8);
        }
    }

    private void initListener() {
        if (this.mCountTimerView != null) {
            this.mCountTimerView.addCurrentTimeListener(new CountTimer.CurrentTimeListener() { // from class: com.android.launcher3.home.MainActivity.2
                @Override // com.android.launcher3.home.pageutil.CountTimer.CurrentTimeListener
                public void currentTime(long currentTime) {
                }

                @Override // com.android.launcher3.home.pageutil.CountTimer.CurrentTimeListener
                public void onFinish() {
                    if (MainActivity.this.indicatorView != null) {
                        MainActivity.this.indicatorView.setVisibility(8);
                    }
                }

                @Override // com.android.launcher3.home.pageutil.CountTimer.CurrentTimeListener
                public void onStartTime() {
                    if (MainActivity.this.indicatorView == null || MainActivity.this.image.size() <= 1) {
                        return;
                    }
                    MainActivity.this.indicatorView.setVisibility(0);
                }
            });
        }
        findViewById(R.id.img_setting).setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.home.MainActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent appIntent;
                if (Settings.System.getInt(MainActivity.this.getContentResolver(), Constants.SYSTEMUI_SPLIT_ACTION, 0) == 1 || (appIntent = CommotUtils.getAppIntent(MainActivity.this.getApplicationContext(), Constants.AUTOMULTIPLETHEME_ACTION)) == null) {
                    return;
                }
                MainActivity.this.startActivity(appIntent);
            }
        });
        findViewById(R.id.img_map).setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.home.MainActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Settings.System.getInt(MainActivity.this.getContentResolver(), Constants.SYSTEMUI_SPLIT_ACTION, 0) == 1) {
                    return;
                }
                Settings.System.putInt(MainActivity.this.getContentResolver(), Constants.LAUNCHER3_REPLACE_DESKTOP, 1);
                MainActivity.this.getApplicationContext().sendBroadcast(new Intent(Constants.ACTION_TO_SCREEN_SPLIT));
                MainActivity.this.setMapAsLauncher();
            }
        });
    }

    @Override // com.android.launcher3.home.BaseDispatchTouchActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        int i = PreferencesUtils.getInt(getApplicationContext(), Constants.WALLPAPER_TYPE, 10);
        Logger.e("pengbin", "MainActivity.....onResume == " + i);
        if (i == 10) {
            this.banner.setVisibility(0);
            this.banner.setAutoPlay(true);
        } else {
            this.banner.setVisibility(8);
            this.banner.setAutoPlay(false);
        }
        this.homeHandler.removeMessages(18);
        if (i == 10) {
            this.homeHandler.postDelayed(new Runnable() { // from class: com.android.launcher3.home.-$$Lambda$MainActivity$Vzxq4ywjZDf9zKSGXUH2JKCmJms
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onResume$0$MainActivity();
                }
            }, 1000L);
        } else {
            this.homeHandler.postDelayed(new Runnable() { // from class: com.android.launcher3.home.-$$Lambda$MainActivity$HbFfU1J7rWSmSf9GFvXNpmOxjSs
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onResume$1$MainActivity();
                }
            }, TelecomManager.VERY_SHORT_CALL_TIME_MS);
        }
    }

    public /* synthetic */ void lambda$onResume$0$MainActivity() {
        Logger.e("pengbin", "onResume postDelayed cancelStartAnimate wallPaperType == 10");
        cancelStartAnimate();
    }

    public /* synthetic */ void lambda$onResume$1$MainActivity() {
        Logger.e("pengbin", "onResume WallpaperService postDelayed cancelStartAnimate");
        cancelStartAnimate();
    }

    @Override // com.android.launcher3.home.BaseDispatchTouchActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        Logger.e("pengbin", "MainActivity.....onPause");
        this.banner.setAutoPlay(false);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Logger.e("pengbin", "MainActivity.....onDestroy");
        if (this.mCountTimerView != null) {
            this.mCountTimerView.cancel();
            this.mCountTimerView = null;
        }
        ThemeBroadcastReceiver themeBroadcastReceiver = this.themeBroadcastReceiver;
        if (themeBroadcastReceiver != null) {
            unregisterReceiver(themeBroadcastReceiver);
            this.onBroadcastReceiverCallBack = null;
        }
        AppStateBroadcastReceiver appStateBroadcastReceiver = this.appStateBroadcastReceiver;
        if (appStateBroadcastReceiver != null) {
            unregisterReceiver(appStateBroadcastReceiver);
            this.onBroadcastReceiverCallBack = null;
        }
        AbsBYDAutoSettingListener absBYDAutoSettingListener = this.mSettingListener;
        if (absBYDAutoSettingListener != null) {
            this.device.unregisterListener(absBYDAutoSettingListener);
            this.mSettingListener = null;
        }
        Banner banner = this.banner;
        if (banner != null) {
            banner.setOnViewPageChangeListener(null);
        }
        this.homeHandler.removeCallbacksAndMessages(null);
        HomeApplication.getInstance().clearApplicationCallBack();
        recycleBitMapList(4);
        recycleBitMap(2);
    }

    private void initBanner(boolean isConfigurationChanged) {
        long j = PreferencesUtils.getLong(getApplicationContext(), Constants.CHANGE_TIME, 86400000L);
        this.banner.setHolderCreator(new ImageHolderCreator()).setIndicator(this.indicatorView);
        this.banner.setAutoTurningTime(j);
        this.banner.setAutoPlay(true);
        this.banner.setPageTransformer(true, new ViewpagerTransformAnim());
        int i = PreferencesUtils.getInt(getApplicationContext(), Constants.WALLPAPER_TYPE, 10);
        Logger.e("pengbin", "wallPaperType == " + i + " === " + this.currentPage);
        if (i == 10) {
            this.banner.setVisibility(0);
            setViewPageBitmapList(isConfigurationChanged);
        } else {
            this.banner.setVisibility(8);
        }
    }

    private void setViewPageBitmapList(boolean isConfigurationChanged) {
        Bitmap firstWallpaperFilesToBitMaps;
        try {
            LinkedHashMap<String, ParcelFileDescriptor> linkedHashMap = (LinkedHashMap) this.wallpaperManager.getWallpaperFileList(BannerUtils.isLand(getApplicationContext()) ? WallpaperManager.ORIENTATION.LAND : WallpaperManager.ORIENTATION.PORT, WallpaperManager.MODE.CAROUSEL, false);
            this.wallpaperFiles = linkedHashMap;
            if (linkedHashMap != null) {
                if (isConfigurationChanged) {
                    int i = this.currentPage;
                    if (i == 0) {
                        Logger.e("pengbin", "setViewPageBitmapList.......1");
                        firstWallpaperFilesToBitMaps = getFirstWallpaperFilesToBitMaps(this.wallpaperFiles);
                    } else {
                        firstWallpaperFilesToBitMaps = changeWallpaperFilesToBitMap(linkedHashMap, i);
                    }
                } else {
                    firstWallpaperFilesToBitMaps = getFirstWallpaperFilesToBitMaps(linkedHashMap);
                }
                Logger.e("pengbin", "setViewPageBitmapList.......2");
                if (firstWallpaperFilesToBitMaps != null) {
                    setFirstBitmap(firstWallpaperFilesToBitMaps, 7);
                }
                if (this.image.size() == 0) {
                    setFirstBitmap(BannerUtils.getLocalFirstBitmap(getApplicationContext()), 6);
                    this.homeHandler.sendEmptyMessage(15);
                    return;
                } else {
                    this.homeHandler.sendEmptyMessage(16);
                    return;
                }
            }
            if (isConfigurationChanged) {
                if (this.currentPage == 0) {
                    setFirstBitmap(BannerUtils.getLocalFirstBitmap(getApplicationContext()), 5);
                } else {
                    setFirstBitmap(BannerUtils.getLocalFirstBitmap(getApplicationContext(), this.currentPage), 4);
                }
            } else {
                setFirstBitmap(BannerUtils.getLocalFirstBitmap(getApplicationContext()), 3);
            }
            this.homeHandler.sendEmptyMessage(15);
        } catch (Exception e) {
            Logger.e("pengbin", "setViewPageBitmapList == Exception == " + e.toString());
            if (isConfigurationChanged) {
                if (this.currentPage == 0) {
                    setFirstBitmap(BannerUtils.getLocalFirstBitmap(getApplicationContext()), 2);
                    this.homeHandler.sendEmptyMessage(15);
                    return;
                } else {
                    this.imageList = BannerUtils.getImage(getApplicationContext(), 7);
                    this.homeHandler.sendEmptyMessage(17);
                    return;
                }
            }
            setFirstBitmap(BannerUtils.getLocalFirstBitmap(getApplicationContext()), 1);
            this.homeHandler.sendEmptyMessage(15);
        }
    }

    public void setFirstBitmap(Bitmap bitmap, int i) {
        recycleBitMap(3);
        this.image.add(new SysWallPage(bitmap));
        Logger.e("pengbin", "setFirstBitmap......0 == " + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recycleBitMap(int i) {
        Logger.e("pengbin", "recycleBitMap.............1 == " + i);
        if (this.image.size() > 0) {
            for (final WallPage wallPage : this.image) {
                if (wallPage.getViewPageBitmap() != null) {
                    runOnUiThread(new Runnable() { // from class: com.android.launcher3.home.MainActivity.5
                        @Override // java.lang.Runnable
                        public void run() {
                            wallPage.getViewPageBitmap().recycle();
                        }
                    });
                }
            }
        }
        this.image.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void recycleBitMapList(int i) {
        Logger.e("pengbin", "recycleBitMapList.............1 == " + i);
        Logger.e("pengbin", "current thread name = " + Thread.currentThread().getName());
        if (this.imageList.size() > 0) {
            for (int i2 = 0; i2 < this.imageList.size(); i2++) {
                final WallPage wallPage = this.imageList.get(i2);
                if (wallPage.getViewPageBitmap() != null) {
                    runOnUiThread(new Runnable() { // from class: com.android.launcher3.home.MainActivity.6
                        @Override // java.lang.Runnable
                        public void run() {
                            wallPage.getViewPageBitmap().recycle();
                            Logger.e("pengbin", "wallPage.getViewPageBitmap().recycle()");
                        }
                    });
                }
            }
        }
        this.imageList.clear();
        Logger.e("pengbin", "imageList.clear()");
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.banner != null) {
            Logger.e("pengbin", "onSaveInstanceState.....");
            outState.putInt(Constants.SAVE_PAGE_INDEX, this.banner.getCurrentPager());
        }
    }

    public void cancelStartAnimate() {
        BYDAutoSettingDevice bYDAutoSettingDevice;
        if (!this.isOnCreate || (bYDAutoSettingDevice = this.device) == null) {
            return;
        }
        bYDAutoSettingDevice.notifyDilinkStarted();
        Logger.e("pengbin", "isOnCreate...0");
        this.isOnCreate = false;
    }

    public void addRemoteWallPaperDelay() {
        LinkedHashMap<String, ParcelFileDescriptor> linkedHashMap = (LinkedHashMap) this.wallpaperManager.getWallpaperFileList(BannerUtils.isLand(getApplicationContext()) ? WallpaperManager.ORIENTATION.LAND : WallpaperManager.ORIENTATION.PORT, WallpaperManager.MODE.CAROUSEL, false);
        this.wallpaperFiles = linkedHashMap;
        ArrayList<Bitmap> arrayListChangeWallpaperFilesToBitMaps = changeWallpaperFilesToBitMaps(linkedHashMap);
        if (!Thread.currentThread().getName().equals(Boolean.toString(BannerUtils.isLand(getApplicationContext())))) {
            Logger.e("pengbin", "addRemoteWallPaperDelay not currentThread");
            return;
        }
        recycleBitMapList(1);
        Logger.e("pengbin", "bitmaps.size = " + arrayListChangeWallpaperFilesToBitMaps.size());
        Iterator<Bitmap> it = arrayListChangeWallpaperFilesToBitMaps.iterator();
        while (it.hasNext()) {
            this.imageList.add(new SysWallPage(it.next()));
            Logger.e("pengbin", "addRemoteWallPaperDelay imageList add");
        }
        this.homeHandler.sendEmptyMessage(13);
    }

    public void addLocalWallPaperDelay() {
        recycleBitMapList(2);
        this.imageList = BannerUtils.getImage(getApplicationContext(), 7);
        if (Thread.currentThread().getName().equals(Boolean.toString(BannerUtils.isLand(getApplicationContext())))) {
            this.homeHandler.sendEmptyMessage(5);
        }
    }

    public void setMapAsLauncher() {
        String str = Constants.PKG_MAP_LAUNCHER;
        boolean zIsAppInstalled = isAppInstalled(Constants.PKG_MAP_LAUNCHER);
        String str2 = Constants.PKG_MAP_LAUNCHER_HW;
        if (zIsAppInstalled || isAppInstalled(Constants.PKG_MAP_LAUNCHER_HW)) {
            if (isAppInstalled(Constants.PKG_MAP_LAUNCHER)) {
                Logger.e("pengbin", "setMapAsLauncher, pkgName=" + Constants.PKG_MAP_LAUNCHER);
            } else {
                str = "";
            }
            if (isAppInstalled(Constants.PKG_MAP_LAUNCHER_HW)) {
                Logger.e("pengbin", "setMapAsLauncher, pkgName=" + Constants.PKG_MAP_LAUNCHER_HW);
            } else {
                str2 = str;
            }
            try {
                ((LauncherManager) getSystemService(Context.LAUNCHER_SERVICE)).setDefaultLauncher(str2);
                long jUptimeMillis = SystemClock.uptimeMillis();
                sendEvent(3, 0, 0, jUptimeMillis, jUptimeMillis);
                sendEvent(3, 1, 0, jUptimeMillis, jUptimeMillis);
                HomeApplication.getInstance().cancelToastMessage();
                HomeApplication.getInstance().showToastMessage(getString(R.string.set_map_launcher));
            } catch (Exception unused) {
            }
        }
    }

    public static void sendEvent(int keyCode, int action, int flags, long down, long when) {
        InputManager.getInstance().injectInputEvent(new KeyEvent(down, when, action, keyCode, (flags & 128) != 0 ? 1 : 0, 0, -1, 0, flags | 8 | 64, 257), 0);
    }

    public boolean isAppInstalled(String packageName) {
        try {
            return getPackageManager().getPackageInfo(packageName, 0) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Logger.e("pengbin", "MainActivity......onConfigurationChanged");
        setUiModeBg(HomeApplication.getInstance().uiConfig, (newConfig.uiMode & 48) == 16);
    }

    public void setUiModeBg(int uiConfig, boolean isLightMode) {
        if (uiConfig == 0) {
            if (isLightMode) {
                this.imgMap.setImageResource(R.drawable.icon_light_mode_map);
                this.imgSetting.setImageResource(R.drawable.icon_light_mode_theme);
                return;
            } else {
                this.imgMap.setImageResource(R.drawable.icon_dark_mode_map);
                this.imgSetting.setImageResource(R.drawable.icon_dark_mode_theme);
                return;
            }
        }
        if (uiConfig == 1) {
            this.imgMap.setImageResource(R.drawable.icon_dark_mode_map_denza);
            this.imgSetting.setImageResource(R.drawable.icon_dark_mode_theme_denza);
            return;
        }
        if (uiConfig == 2) {
            if (isLightMode) {
                this.imgMap.setImageResource(R.drawable.icon_light_mode_map);
                this.imgSetting.setImageResource(R.drawable.icon_light_mode_theme);
                return;
            } else {
                this.imgMap.setImageResource(R.drawable.icon_dark_mode_map);
                this.imgSetting.setImageResource(R.drawable.icon_dark_mode_theme);
                return;
            }
        }
        if (isLightMode) {
            this.imgMap.setImageResource(R.drawable.icon_light_mode_map);
            this.imgSetting.setImageResource(R.drawable.icon_light_mode_theme);
        } else {
            this.imgMap.setImageResource(R.drawable.icon_dark_mode_map);
            this.imgSetting.setImageResource(R.drawable.icon_dark_mode_theme);
        }
    }

    public int getSteeringWheelPosition() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("getInt", String.class, Integer.TYPE);
            method.setAccessible(true);
            Object objInvoke = method.invoke(cls, "persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
            if (objInvoke == null || !(objInvoke instanceof Integer)) {
                return 0;
            }
            return ((Integer) objInvoke).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private ArrayList<Bitmap> changeWallpaperFilesToBitMaps(LinkedHashMap<String, ParcelFileDescriptor> wallpaperFiles) {
        BufferedInputStream bufferedInputStream;
        ArrayList<Bitmap> arrayList = new ArrayList<>();
        Iterator<Map.Entry<String, ParcelFileDescriptor>> it = wallpaperFiles.entrySet().iterator();
        while (it.hasNext()) {
            try {
                bufferedInputStream = new BufferedInputStream(new ParcelFileDescriptor.AutoCloseInputStream(it.next().getValue()));
            } catch (IOException | OutOfMemoryError e) {
                Logger.e("pengbin", "Can't decode file", e);
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int i = bufferedInputStream.read();
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(i);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
                options.inSampleSize = BitmapUtils.calculateInSampleSize(options);
                options.inJustDecodeBounds = false;
                Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
                arrayList.add(bitmapDecodeByteArray);
                Logger.e("pengbin", "压缩后的bitmap大小 == " + ((bitmapDecodeByteArray.getByteCount() / 1024) / 1024) + "M");
                bufferedInputStream.close();
            } catch (Throwable th) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        return arrayList;
    }

    private Bitmap changeWallpaperFilesToBitMap(LinkedHashMap<String, ParcelFileDescriptor> wallpaperFiles, int index) {
        Bitmap bitmapDecodeByteArray = null;
        int i = 0;
        for (Map.Entry<String, ParcelFileDescriptor> entry : wallpaperFiles.entrySet()) {
            if (i == index) {
                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new ParcelFileDescriptor.AutoCloseInputStream(entry.getValue()));
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        while (true) {
                            int i2 = bufferedInputStream.read();
                            if (i2 == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(i2);
                        }
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        bitmapDecodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                        bufferedInputStream.close();
                    } catch (Throwable th) {
                        try {
                            bufferedInputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                } catch (IOException | OutOfMemoryError e) {
                    Logger.e("pengbin", "Can't decode file", e);
                }
            }
            i++;
        }
        return bitmapDecodeByteArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap getFirstWallpaperFilesToBitMaps(LinkedHashMap<String, ParcelFileDescriptor> wallpaperFiles) {
        Map.Entry<String, ParcelFileDescriptor> next = wallpaperFiles.entrySet().iterator().next();
        Bitmap bitmapDecodeByteArray = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new ParcelFileDescriptor.AutoCloseInputStream(next.getValue()));
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int i = bufferedInputStream.read();
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(i);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bitmapDecodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                bufferedInputStream.close();
            } catch (Throwable th) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException | OutOfMemoryError e) {
            Logger.e("pengbin", "Can't decode file", e);
        }
        return bitmapDecodeByteArray;
    }

    public void thirdAppSetWallpaperReturn() {
        int i = PreferencesUtils.getInt(getApplicationContext(), Constants.WALLPAPER_TYPE, 10);
        Logger.e("pengbin", "wallPaperType......111 == " + i);
        Intent intent = new Intent(Constants.THIRD_APP_CHANGE_WALLPAPER);
        intent.putExtra(Constants.WALLPAPER_SETTING_RETURN, i == 0 ? 1 : -1);
        getApplicationContext().sendBroadcast(intent);
    }

    public boolean isLightMode() {
        int i = getResources().getConfiguration().uiMode & 48;
        if (i == 32) {
            return false;
        }
        if (i == 16) {
        }
        return true;
    }
}
