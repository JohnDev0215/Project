package com.byd.automultipletheme.common;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.BuildConfig;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class WallpaperManagerUtils {
    public static final int CAROUSEL = 4;
    public static final int LAND = 0;
    public static final int NORMAL = 2;
    public static final int PORT = 1;
    public static final int TIME = 3;
    public static final String WALLPAPER_SAVE_SUCCESS = "com.byd.automultipletheme.SAVE_SUCCESS";
    Handler handler;
    WallpaperManager.OnWallpaperChangedListener mListener;
    WallpaperManager wallpaperManager;

    private WallpaperManagerUtils() {
        this.wallpaperManager = WallpaperManager.getInstance(Utils.getApp());
        this.mListener = new WallpaperManager.OnWallpaperChangedListener() { // from class: com.byd.automultipletheme.common.WallpaperManagerUtils.1
            public void onWallpaperChanged() {
                com.blankj.utilcode.util.LogUtils.i("onWallpaperChanged");
            }

            public void onWallpaperListChanged() {
                com.blankj.utilcode.util.LogUtils.i("onWallpaperListChanged");
                Intent intent = new Intent(WallpaperManagerUtils.WALLPAPER_SAVE_SUCCESS);
                intent.setPackage(BuildConfig.APPLICATION_ID);
                Utils.getApp().sendBroadcast(intent);
            }
        };
        this.handler = new Handler();
    }

    private static class Holder {
        private static WallpaperManagerUtils instance = new WallpaperManagerUtils();

        private Holder() {
        }
    }

    public static WallpaperManagerUtils getInstance() {
        return Holder.instance;
    }

    public void addOnWallpaperChangedListener() {
        this.wallpaperManager.addOnWallpaperChangedListener(this.mListener, this.handler);
    }

    public void removeOnWallpaperChangedListener() {
        this.wallpaperManager.removeOnWallpaperChangedListener(this.mListener);
    }

    public int addBitmaps(Map<String, Bitmap> map, int i, int i2, boolean z) {
        int iAddBitmaps = 0;
        if (i == 0 && i2 == 2) {
            try {
                iAddBitmaps = this.wallpaperManager.addBitmaps(map, WallpaperManager.ORIENTATION.LAND, WallpaperManager.MODE.NORMAL, z);
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        if (i == 0 && i2 == 3) {
            iAddBitmaps = this.wallpaperManager.addBitmaps(map, WallpaperManager.ORIENTATION.LAND, WallpaperManager.MODE.TIME, z);
        }
        if (i == 0 && i2 == 4) {
            iAddBitmaps = this.wallpaperManager.addBitmaps(map, WallpaperManager.ORIENTATION.LAND, WallpaperManager.MODE.CAROUSEL, z);
        }
        if (i == 1 && i2 == 2) {
            iAddBitmaps = this.wallpaperManager.addBitmaps(map, WallpaperManager.ORIENTATION.PORT, WallpaperManager.MODE.NORMAL, z);
        }
        if (i == 1 && i2 == 3) {
            iAddBitmaps = this.wallpaperManager.addBitmaps(map, WallpaperManager.ORIENTATION.PORT, WallpaperManager.MODE.TIME, z);
        }
        return (i == 1 && i2 == 4) ? this.wallpaperManager.addBitmaps(map, WallpaperManager.ORIENTATION.PORT, WallpaperManager.MODE.CAROUSEL, z) : iAddBitmaps;
    }

    public int setBitmapList(Map<String, Bitmap> map, int i, int i2, boolean z) {
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(Utils.getApp());
        int bitmapList = 0;
        if (i == 0 && i2 == 2) {
            try {
                bitmapList = wallpaperManager.setBitmapList(map, WallpaperManager.ORIENTATION.LAND, WallpaperManager.MODE.NORMAL, z);
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        if (i == 0 && i2 == 3) {
            bitmapList = wallpaperManager.setBitmapList(map, WallpaperManager.ORIENTATION.LAND, WallpaperManager.MODE.TIME, z);
        }
        if (i == 0 && i2 == 4) {
            bitmapList = wallpaperManager.setBitmapList(map, WallpaperManager.ORIENTATION.LAND, WallpaperManager.MODE.CAROUSEL, z);
        }
        if (i == 1 && i2 == 2) {
            bitmapList = wallpaperManager.setBitmapList(map, WallpaperManager.ORIENTATION.PORT, WallpaperManager.MODE.NORMAL, z);
        }
        if (i == 1 && i2 == 3) {
            bitmapList = wallpaperManager.setBitmapList(map, WallpaperManager.ORIENTATION.PORT, WallpaperManager.MODE.TIME, z);
        }
        return (i == 1 && i2 == 4) ? wallpaperManager.setBitmapList(map, WallpaperManager.ORIENTATION.PORT, WallpaperManager.MODE.CAROUSEL, z) : bitmapList;
    }

    public int deleteBitmaps(String[] strArr, int i, int i2, boolean z) {
        int iDeleteBitmaps = 0;
        if (i == 0 && i2 == 2) {
            try {
                iDeleteBitmaps = this.wallpaperManager.deleteBitmaps(strArr, WallpaperManager.ORIENTATION.LAND, WallpaperManager.MODE.NORMAL, z);
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        if (i == 0 && i2 == 3) {
            iDeleteBitmaps = this.wallpaperManager.deleteBitmaps(strArr, WallpaperManager.ORIENTATION.LAND, WallpaperManager.MODE.TIME, z);
        }
        if (i == 0 && i2 == 4) {
            iDeleteBitmaps = this.wallpaperManager.deleteBitmaps(strArr, WallpaperManager.ORIENTATION.LAND, WallpaperManager.MODE.CAROUSEL, z);
        }
        if (i == 1 && i2 == 2) {
            iDeleteBitmaps = this.wallpaperManager.deleteBitmaps(strArr, WallpaperManager.ORIENTATION.PORT, WallpaperManager.MODE.NORMAL, z);
        }
        if (i == 1 && i2 == 3) {
            iDeleteBitmaps = this.wallpaperManager.deleteBitmaps(strArr, WallpaperManager.ORIENTATION.PORT, WallpaperManager.MODE.TIME, z);
        }
        return (i == 1 && i2 == 4) ? this.wallpaperManager.deleteBitmaps(strArr, WallpaperManager.ORIENTATION.PORT, WallpaperManager.MODE.CAROUSEL, z) : iDeleteBitmaps;
    }
}
