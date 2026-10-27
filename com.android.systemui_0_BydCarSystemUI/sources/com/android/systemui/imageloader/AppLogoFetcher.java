package com.android.systemui.imageloader;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.properties.CarSettings;
import android.text.TextUtils;
import com.android.systemui.R;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.recents.LegacyRecentsImpl;
import com.android.systemui.recents.RecentTaskBean;
import com.android.systemui.util.StyleUtil;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import com.byd.card.bean.Constant;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes.dex */
public class AppLogoFetcher implements DataFetcher<Bitmap> {
    public static final int DISPLAY_4K = 8847360;
    public static final int DISPLAY_MAX = 24000;
    public static final int THUMBNAIL_HEIGHT = 120;
    public static final int THUMBNAIL_WIDTH = 200;
    private final RecentTaskBean mRecentTaskBean;

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cancel() {
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cleanup() {
    }

    public AppLogoFetcher(RecentTaskBean recentTaskBean) {
        this.mRecentTaskBean = recentTaskBean;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void loadData(Priority priority, DataFetcher.DataCallback<? super Bitmap> callback) {
        Drawable themeLogoByPkg = getThemeLogoByPkg(this.mRecentTaskBean.getComponentName().getPackageName());
        if (themeLogoByPkg == null) {
            themeLogoByPkg = LegacyRecentsImpl.getTaskLoader().getAndUpdateActivityIcon(this.mRecentTaskBean.getTaskKey(), this.mRecentTaskBean.getTaskDescription(), true);
        }
        if (themeLogoByPkg != null) {
            callback.onDataReady(drawableToBitmap(themeLogoByPkg));
        } else {
            BydLogHelper.logD("without imageData!");
            callback.onLoadFailed(new Exception("without imageData!"));
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    private Drawable getThemeLogoByPkg(String pkg) {
        BydLogHelper.logD("getThemeLogoByPkg: uuid:" + CarSettings.UserTableData.getInt(SystemUIApplication.getInstance().getContentResolver(), CarSettings.UserTableData.BYD_APP_THEME));
        if (TextUtils.equals("com.byd.carsettings", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_carsettings);
        }
        if (TextUtils.equals("com.byd.hvac", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_airconditioning);
        }
        if (TextUtils.equals("com.byd.vrassistant.xf", pkg) || TextUtils.equals("com.byd.vrsettings", pkg)) {
            if (StyleUtil.isDenza()) {
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_autovoice_deza);
            }
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_autovoice);
        }
        if (TextUtils.equals("com.byd.localmusic", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_mediacenter);
        }
        if (TextUtils.equals("com.byd.bluetoothcall", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_bluetoothcall);
        }
        if (TextUtils.equals("com.byd.mycar", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_mycar);
        }
        if (TextUtils.equals("com.byd.appstartmanagement", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_appstartmanagement);
        }
        if (TextUtils.equals("com.byd.auto_camera", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_auto_camera);
        }
        if (TextUtils.equals("com.byd.auto_photo", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_auto_photo);
        }
        if (TextUtils.equals("com.byd.filemanager", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_filemanager);
        }
        if (TextUtils.equals("com.byd.trafficmonitor", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_trafficmonitor);
        }
        if (TextUtils.equals("com.byd.automultipletheme", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_multipletheme);
        }
        if (TextUtils.equals("com.byd.smarttravel", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_smarttravel);
        }
        if (TextUtils.equals("com.byd.avm", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_avc);
        }
        if (TextUtils.equals("com.byd.auto_emanual", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_auto_emanual);
        }
        if (TextUtils.equals("com.byd.dvr", pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_dvr);
        }
        if (TextUtils.equals(Constant.PackageName.OVER_SEA_APP_STORE, pkg)) {
            return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_appstore);
        }
        return null;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public Class<Bitmap> getDataClass() {
        return Bitmap.class;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
}
