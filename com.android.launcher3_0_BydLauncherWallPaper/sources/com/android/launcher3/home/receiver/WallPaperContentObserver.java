package com.android.launcher3.home.receiver;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.CarTwilightManager;
import android.os.DropBoxManager;
import android.security.KeyStore;
import com.android.launcher3.home.Constants;
import com.android.launcher3.home.pageutil.PreferencesUtils;
import com.android.launcher3.home.service.LiveWallpaperService;
import com.android.launcher3.home.service.TimeWallpaperService;
import com.android.launcher3.home.viewpage.WallPaperHandler;
import com.android.launcher3.util.Logger;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes4.dex */
public class WallPaperContentObserver extends ContentObserver {
    private Context mContext;
    private WallPaperHandler mHandler;
    private CarTwilightManager.TwilightChangeListener twilightChangeListener;

    public WallPaperContentObserver(Context context, WallPaperHandler handler) {
        super(handler);
        this.mContext = context;
        this.mHandler = handler;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        Logger.e("pengbin", "WallPaperContentObserver....selfChange ==== " + selfChange + " == " + uri);
        if (uri.equals(Constants.WALLPAPER_RESULT_URI)) {
            Logger.e("pengbin", "changerWallPapers........receive ");
            getWallpaperInfo();
        } else if (uri.equals(Constants.CAROUSEL_TIME_URI)) {
            getWallpaperChangeTime();
        }
    }

    public void getWallpaperInfo() {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.mContext.getContentResolver().query(Constants.WALLPAPER_RESULT_URI, null, "_id = ?", new String[]{"1"}, null);
                if (cursorQuery == null) {
                    Logger.e("pengbin", "name == 失败");
                    setWallpaperReturn(false);
                } else if (cursorQuery.getCount() < 1) {
                    Logger.e("pengbin", "name == null");
                    setWallpaperReturn(false);
                } else {
                    while (cursorQuery.moveToNext()) {
                        updateWallPaper(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("wallpaper_path")), cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("wallpaper_type")), cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("res_id")));
                    }
                    cursorQuery.close();
                }
                if (cursorQuery == null) {
                    return;
                }
            } catch (Exception e) {
                Logger.d("pengbin", "WallPaperContentObserver....getWallpaperInfo fail " + e.getClass());
                setWallpaperReturn(false);
                if (0 == 0) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (0 != 0) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public void getWallpaperChangeTime() {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.mContext.getContentResolver().query(Constants.CAROUSEL_TIME_URI, null, "_id = ?", new String[]{"1"}, null);
                if (cursorQuery == null) {
                    Logger.e("pengbin", "getWallpaperChangeTime...name == 失败");
                    setWallpaperReturn(false);
                } else if (cursorQuery.getCount() < 1) {
                    Logger.e("pengbin", "getWallpaperChangeTime...name == null");
                    setWallpaperReturn(false);
                } else {
                    while (cursorQuery.moveToNext()) {
                        PreferencesUtils.putLong(KeyStore.getApplicationContext(), Constants.CHANGE_TIME, cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow(DropBoxManager.EXTRA_TIME)));
                        this.mHandler.sendEmptyMessage(4);
                    }
                    cursorQuery.close();
                }
                if (cursorQuery == null) {
                    return;
                }
            } catch (Exception e) {
                Logger.d("pengbin", "getWallpaperChangeTime...time fail " + e.getClass());
                setWallpaperReturn(false);
                if (0 == 0) {
                    return;
                }
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (0 != 0) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    private void updateWallPaper(String path, int type, int resID) {
        Logger.e("pengbin", "drawSurfaceView........ type = " + type);
        if (type == 1) {
            this.mHandler.sendEmptyMessage(10);
            return;
        }
        if (type == 2) {
            PreferencesUtils.putInt(this.mContext, Constants.WALLPAPER_ID, resID, 4);
            setLiveWallpaper(this.mContext, LiveWallpaperService.class.getCanonicalName(), 11);
        } else if (type == 15) {
            setLiveWallpaper(this.mContext, TimeWallpaperService.class.getCanonicalName(), 12);
        }
    }

    private void setLiveWallpaper(Context context, String name, int type) {
        try {
            Class.forName(WallpaperManager.class.getName()).getMethod("setWallpaperComponent", ComponentName.class).invoke(WallpaperManager.getInstance(context), new ComponentName(context.getPackageName(), name));
            PreferencesUtils.putInt(this.mContext, Constants.WALLPAPER_TYPE, type, 5);
            this.mHandler.sendEmptyMessage(type);
            setWallpaperReturn(true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            setWallpaperReturn(false);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            setWallpaperReturn(false);
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            setWallpaperReturn(false);
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            setWallpaperReturn(false);
        }
    }

    public void setWallpaperReturn(boolean z) {
        Logger.e("pengbin", "changerWallPapers......通知主题壁纸设置结果 == " + z);
        Intent intent = new Intent(Constants.SET_WALLPAPER_RESULT);
        intent.putExtra(Constants.WALLPAPER_SETTING_RETURN, z ? 1 : 0);
        this.mContext.sendBroadcast(intent);
    }
}
