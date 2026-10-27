package com.byd.automultipletheme.common;

import android.graphics.Bitmap;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.properties.CarSettings;
import android.os.SystemProperties;
import androidx.core.os.LocaleListCompat;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import java.lang.reflect.Method;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AutoTools.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002J\u0006\u0010\r\u001a\u00020\nJ\u0006\u0010\u000e\u001a\u00020\nJ\u0006\u0010\u000f\u001a\u00020\nJ\u0006\u0010\u0010\u001a\u00020\nJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012¨\u0006\u0014"}, d2 = {"Lcom/byd/automultipletheme/common/AutoTools;", "", "()V", "getLocalThemeId", "", "getThemeName", "", IntentString.DATA_ID, "getWallpaperName", "isCarEV", "", "carType", "isCarHEV", "isDark", "isFrLocal", "isPauseGear", "isRightRudder", "zoomBitmap", "Landroid/graphics/Bitmap;", "bitmap", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AutoTools {
    private final boolean isCarHEV(int carType) {
        switch (carType) {
            case 15:
            case 31:
            case 35:
            case 43:
            case 46:
            case 47:
            case 48:
            case 50:
            case 52:
            case 56:
            case 57:
            case 61:
            case 63:
            case 65:
            case 67:
            case 71:
            case 74:
            case 83:
            case 85:
            case 88:
            case 89:
            case 90:
            case 102:
            case 111:
            case 113:
            case 115:
            case 121:
            case 129:
            case 136:
            case 140:
            case 141:
            case 143:
                return true;
            default:
                return false;
        }
    }

    public final boolean isCarEV(int carType) {
        switch (carType) {
            case 39:
            case 40:
            case 41:
            case 44:
            case 45:
            case 49:
            case 51:
            case 58:
            case 59:
            case 60:
            case 62:
            case 69:
            case 70:
            case 72:
            case 84:
            case 92:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 101:
            case 114:
            case 123:
            case 128:
            case 135:
            case 137:
            case 138:
            case 147:
            case 182:
            case 196:
            case 208:
                return true;
            default:
                return false;
        }
    }

    public final boolean isFrLocal() {
        Locale locale = LocaleListCompat.getDefault().get(0);
        if (locale == null) {
            return false;
        }
        String string = locale.toString();
        Intrinsics.checkNotNullExpressionValue(string, "it.toString()");
        if (!Intrinsics.areEqual("fr", StringsKt.trim((CharSequence) string).toString())) {
            String string2 = locale.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "it.toString()");
            if (!Intrinsics.areEqual("fr_FR", StringsKt.trim((CharSequence) string2).toString())) {
                return false;
            }
        }
        return true;
    }

    public final boolean isRightRudder() {
        Object objInvoke;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("getInt", String.class, Integer.TYPE);
            method.setAccessible(true);
            objInvoke = method.invoke(cls, "persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int iIntValue = (objInvoke == null || !(objInvoke instanceof Integer)) ? 0 : ((Number) objInvoke).intValue();
        return iIntValue == 1;
    }

    public final boolean isPauseGear() {
        int gearboxAutoModeLevel;
        try {
            String str = SystemProperties.get("persist.sys.energytype");
            if (str.equals(Const.CarEv)) {
                LogUtils.INSTANCE.i("isCarEV");
                gearboxAutoModeLevel = BYDAutoGearboxDevice.getInstance(Utils.getApp()).getGear();
            } else {
                gearboxAutoModeLevel = 0;
            }
            try {
                if (str.equals("2")) {
                    LogUtils.INSTANCE.i("isCarHEV");
                    gearboxAutoModeLevel = BYDAutoGearboxDevice.getInstance(Utils.getApp()).getGearboxAutoModeLevel();
                }
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e = e2;
            gearboxAutoModeLevel = 0;
        }
        LogUtils.INSTANCE.i("currentGear:", Integer.valueOf(gearboxAutoModeLevel));
        return gearboxAutoModeLevel == 1;
    }

    public final boolean isDark() {
        return (Utils.getApp().getResources().getConfiguration().uiMode & 48) == 32;
    }

    public final Bitmap zoomBitmap(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        if (bitmap.getWidth() >= ScreenUtils.getScreenWidth()) {
            return bitmap;
        }
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight(), true);
        bitmap.recycle();
        Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap, "{\n            val scaleB…    scaleBitmap\n        }");
        return bitmapCreateScaledBitmap;
    }

    public final int getLocalThemeId() {
        int i = CarSettings.UserTableData.getInt(Utils.getApp().getContentResolver(), CarSettings.UserTableData.BYD_APP_THEME);
        return (i == -2147482648 || i == 0) ? Const.APP_THEME_DEFAULT_ID : i;
    }

    public final String getThemeName(int id) {
        int i = Integer.parseInt(String.valueOf(StringsKt.last(String.valueOf(id))));
        if (i == 0) {
            String string = Utils.getApp().getResources().getString(R.string.str_default_theme);
            Intrinsics.checkNotNullExpressionValue(string, "getApp().resources.getSt…string.str_default_theme)");
            return string;
        }
        if (i == 1) {
            String string2 = Utils.getApp().getResources().getString(R.string.str_theme_NO1);
            Intrinsics.checkNotNullExpressionValue(string2, "getApp().resources.getSt…g(R.string.str_theme_NO1)");
            return string2;
        }
        if (i == 2) {
            String string3 = Utils.getApp().getResources().getString(R.string.str_theme_NO2);
            Intrinsics.checkNotNullExpressionValue(string3, "getApp().resources.getSt…g(R.string.str_theme_NO2)");
            return string3;
        }
        if (i == 3) {
            String string4 = Utils.getApp().getResources().getString(R.string.str_theme_NO3);
            Intrinsics.checkNotNullExpressionValue(string4, "getApp().resources.getSt…g(R.string.str_theme_NO3)");
            return string4;
        }
        if (i == 4) {
            String string5 = Utils.getApp().getResources().getString(R.string.str_theme_NO4);
            Intrinsics.checkNotNullExpressionValue(string5, "getApp().resources.getSt…g(R.string.str_theme_NO4)");
            return string5;
        }
        if (i == 5) {
            String string6 = Utils.getApp().getResources().getString(R.string.str_theme_NO5);
            Intrinsics.checkNotNullExpressionValue(string6, "getApp().resources.getSt…g(R.string.str_theme_NO5)");
            return string6;
        }
        String string7 = Utils.getApp().getResources().getString(R.string.str_theme_name, String.valueOf(StringsKt.last(String.valueOf(id))));
        Intrinsics.checkNotNullExpressionValue(string7, "getApp().resources.getSt…ring().last().toString())");
        return string7;
    }

    public final String getWallpaperName(int id) {
        int i = Integer.parseInt(String.valueOf(StringsKt.last(String.valueOf(id))));
        if (i == 0) {
            String string = Utils.getApp().getResources().getString(R.string.str_default_theme);
            Intrinsics.checkNotNullExpressionValue(string, "getApp().resources.getSt…string.str_default_theme)");
            return string;
        }
        if (i == 1) {
            String string2 = Utils.getApp().getResources().getString(R.string.str_time_wallpaper);
            Intrinsics.checkNotNullExpressionValue(string2, "getApp().resources.getSt…tring.str_time_wallpaper)");
            return string2;
        }
        if (i == 2) {
            String string3 = Utils.getApp().getResources().getString(R.string.str_wallpaper_NO2);
            Intrinsics.checkNotNullExpressionValue(string3, "getApp().resources.getSt…string.str_wallpaper_NO2)");
            return string3;
        }
        if (i == 3) {
            String string4 = Utils.getApp().getResources().getString(R.string.str_wallpaper_NO3);
            Intrinsics.checkNotNullExpressionValue(string4, "getApp().resources.getSt…string.str_wallpaper_NO3)");
            return string4;
        }
        if (i == 4) {
            String string5 = Utils.getApp().getResources().getString(R.string.str_wallpaper_NO4);
            Intrinsics.checkNotNullExpressionValue(string5, "getApp().resources.getSt…string.str_wallpaper_NO4)");
            return string5;
        }
        if (i == 5) {
            String string6 = Utils.getApp().getResources().getString(R.string.str_wallpaper_NO5);
            Intrinsics.checkNotNullExpressionValue(string6, "getApp().resources.getSt…string.str_wallpaper_NO5)");
            return string6;
        }
        String string7 = Utils.getApp().getResources().getString(R.string.str_wallpaper_name, String.valueOf(StringsKt.last(String.valueOf(id))));
        Intrinsics.checkNotNullExpressionValue(string7, "getApp().resources.getSt….toString()\n            )");
        return string7;
    }
}
