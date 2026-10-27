package com.android.launcher3.home;

import android.app.Application;
import android.app.UiStyleConfig;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.widget.Toast;
import com.android.launcher3.home.listener.OnApplicationCallBack;
import com.android.launcher3.home.pageutil.BannerUtils;
import com.android.launcher3.home.pageutil.PreferencesUtils;
import com.android.launcher3.home.receiver.WallPaperContentObserver;
import com.android.launcher3.home.service.LiveWallpaperService;
import com.android.launcher3.home.service.TimeWallpaperService;
import com.android.launcher3.home.viewpage.WallPaperHandler;
import com.android.launcher3.util.Logger;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes4.dex */
public class HomeApplication extends Application {
    private static HomeApplication sInstance;
    private OnApplicationCallBack mOnApplicationCallBack;
    private WallpaperManager.OnColorsChangedListener onColorsChangedListener;
    public int uiConfig;
    private WallPaperContentObserver wallPaperContentObserver;
    private LinkedHashMap<String, ParcelFileDescriptor> wallpaperFiles;
    private WallpaperManager wallpaperManager;
    private Long tag = 0L;
    private boolean isThemeSetWallPaper = false;
    Toast currentToast = null;
    public final WallPaperHandler wallPaperHandler = new WallPaperHandler(new Handler.Callback() { // from class: com.android.launcher3.home.HomeApplication.2
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message msg) {
            int i = msg.what;
            try {
                if (i == 10) {
                    HomeApplication.this.setWallPaperClearListener();
                } else if (i == 11) {
                    Logger.e("pengbin", "百变主题设置了静态图壁纸...成功");
                    if (HomeApplication.this.mOnApplicationCallBack != null) {
                        HomeApplication.this.mOnApplicationCallBack.onBroadcastReceiverCallBack(22);
                    }
                } else if (i == 12) {
                    Logger.e("pengbin", "百变主题设置了时间壁纸...成功");
                    if (HomeApplication.this.mOnApplicationCallBack != null) {
                        HomeApplication.this.mOnApplicationCallBack.onBroadcastReceiverCallBack(22);
                    }
                } else {
                    if (i != 4) {
                        return true;
                    }
                    Logger.e("pengbin", "百变主题更改了轮播速度...成功");
                    if (HomeApplication.this.mOnApplicationCallBack != null) {
                        HomeApplication.this.mOnApplicationCallBack.onBroadcastReceiverCallBack(4);
                    }
                }
                return true;
            } catch (Throwable th) {
                th.printStackTrace();
                return true;
            }
        }
    });

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Logger.e("pengbin", "HomeApplication........onCreate == 1.18.2-202503121646");
        sInstance = this;
        this.uiConfig = UiStyleConfig.getInstance(getApplicationContext()).getUiStyle();
        this.wallPaperContentObserver = new WallPaperContentObserver(getApplicationContext(), this.wallPaperHandler);
        this.wallpaperManager = WallpaperManager.getInstance(this);
        registerWallpaperObserver(this.wallPaperContentObserver);
        registerColorsChangedListener();
        setCurrentWallPaper();
    }

    public static HomeApplication getInstance() {
        return sInstance;
    }

    public LinkedHashMap<String, ParcelFileDescriptor> getWallpaperFiles() {
        return this.wallpaperFiles;
    }

    public void showToastMessage(String message) {
        if (this.currentToast == null) {
            this.currentToast = Toast.makeText(getApplicationContext(), message, 0);
        }
        this.currentToast.show();
    }

    public void cancelToastMessage() {
        Toast toast = this.currentToast;
        if (toast != null) {
            toast.cancel();
        }
    }

    private void setCurrentToastNull() {
        this.currentToast = null;
    }

    public void setApplicationCallBack(OnApplicationCallBack onApplicationCallBack) {
        this.mOnApplicationCallBack = onApplicationCallBack;
    }

    public OnApplicationCallBack getOnApplicationCallBack() {
        return this.mOnApplicationCallBack;
    }

    public void clearApplicationCallBack() {
        this.mOnApplicationCallBack = null;
    }

    public void clearWallPaper() {
        try {
            this.wallpaperManager.clear();
        } catch (Exception e) {
            Logger.e("pengbin", "clearWallPaper......" + e.toString());
        }
    }

    private void setCurrentWallPaper() {
        int i = PreferencesUtils.getInt(getApplicationContext(), Constants.WALLPAPER_TYPE, 10);
        Logger.e("pengbin", "setCurrentWallPaper ==== " + i);
        if (i == 11) {
            setLiveWallpaper(getApplicationContext(), LiveWallpaperService.class.getCanonicalName());
        } else if (i == 12) {
            setLiveWallpaper(getApplicationContext(), TimeWallpaperService.class.getCanonicalName());
        }
    }

    private void registerColorsChangedListener() {
        this.wallpaperManager.addOnWallpaperChangedListener(new WallpaperManager.OnWallpaperChangedListener() { // from class: com.android.launcher3.home.HomeApplication.1
            @Override // android.app.WallpaperManager.OnWallpaperChangedListener
            public void onWallpaperListChanged() {
            }

            @Override // android.app.WallpaperManager.OnWallpaperChangedListener
            public void onWallpaperChanged() {
                Logger.e("pengbin", "onWallpaperChanged..." + HomeApplication.this.isThemeSetWallPaper);
                if (HomeApplication.this.isThemeSetWallPaper) {
                    PreferencesUtils.putInt(HomeApplication.this.getApplicationContext(), Constants.WALLPAPER_TYPE, 10, 1);
                    HomeApplication.this.isThemeSetWallPaper = false;
                } else {
                    PreferencesUtils.putInt(HomeApplication.this.getApplicationContext(), Constants.WALLPAPER_TYPE, 0, 2);
                    Intent intent = new Intent(Constants.THIRD_APP_CHANGE_WALLPAPER);
                    intent.putExtra(Constants.WALLPAPER_SETTING_RETURN, 1);
                    HomeApplication.this.getApplicationContext().sendBroadcast(intent);
                }
            }
        }, null);
    }

    public void registerWallpaperObserver(ContentObserver contentObserver) {
        try {
            getContentResolver().registerContentObserver(Constants.WALLPAPER_RESULT_URI, true, contentObserver);
            getContentResolver().registerContentObserver(Constants.CAROUSEL_TIME_URI, true, contentObserver);
        } catch (Exception e) {
            Logger.e("pengbin", "registerWallpaperInfo fail " + e.getClass());
        }
    }

    public void setWallpaperReturn(boolean z) {
        Logger.e("pengbin", "changerWallPapers......通知主题壁纸设置结果 == 1 == " + z);
        Intent intent = new Intent(Constants.SET_WALLPAPER_RESULT);
        intent.putExtra(Constants.WALLPAPER_SETTING_RETURN, z ? 1 : 0);
        sendBroadcast(intent);
    }

    public void setWallPaperClearListener() {
        try {
            LinkedHashMap<String, ParcelFileDescriptor> linkedHashMap = (LinkedHashMap) this.wallpaperManager.getWallpaperFileList(BannerUtils.isLand(getApplicationContext()) ? WallpaperManager.ORIENTATION.LAND : WallpaperManager.ORIENTATION.PORT, WallpaperManager.MODE.CAROUSEL, false);
            this.wallpaperFiles = linkedHashMap;
            if (linkedHashMap == null) {
                Logger.e("pengbin", "wallpaperFiles === null");
                setWallpaperReturn(false);
                return;
            }
            if (linkedHashMap.size() == 0) {
                Logger.e("pengbin", "wallpaperFiles === 0");
                setWallpaperReturn(false);
                return;
            }
            Logger.e("pengbin", "wallpaperFiles ===000== " + this.wallpaperFiles.size());
            this.isThemeSetWallPaper = true;
            OnApplicationCallBack onApplicationCallBack = this.mOnApplicationCallBack;
            if (onApplicationCallBack != null) {
                onApplicationCallBack.onBroadcastReceiverCallBack(2);
            } else {
                clearWallPaper();
            }
            setWallpaperReturn(true);
        } catch (Exception e) {
            Logger.e("pengbin", "clear wallPaper fail..." + e.toString());
            setWallpaperReturn(false);
        }
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setCurrentToastNull();
        OnApplicationCallBack onApplicationCallBack = this.mOnApplicationCallBack;
        if (onApplicationCallBack != null) {
            onApplicationCallBack.onConfigurationChanged(newConfig);
        }
    }

    private void setLiveWallpaper(Context context, String name) {
        try {
            Class.forName(WallpaperManager.class.getName()).getMethod("setWallpaperComponent", ComponentName.class).invoke(WallpaperManager.getInstance(context), new ComponentName(context.getPackageName(), name));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }
}
