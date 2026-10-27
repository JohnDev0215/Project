package com.byd.music.utils;

import android.app.UiModeManager;
import android.content.Context;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.provider.Settings;
import com.blankj.utilcode.util.Utils;
import com.byd.bluetoothaidl.manage.HfpManage;
import com.byd.music.R;
import com.byd.music.ThirdSDK.Carplay.CarplayManager;

/* JADX INFO: loaded from: classes5.dex */
public class DeviceInfoUtils {
    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static String getPlatformCode() {
        try {
            return getPlatform(Utils.getApp(), "ro.vehicle.type");
        } catch (Throwable th) {
            LogUtil.i(" DeviceInfoUtils --> getPlatformCode:error=" + th.getMessage());
            return "unknown";
        }
    }

    private static String getPlatform(Context context, String str) throws IllegalArgumentException {
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) clsLoadClass.getMethod("get", String.class).invoke(clsLoadClass, str);
        } catch (Throwable th) {
            LogUtil.e(" DeviceInfoUtils --> getPlatform:error=" + th);
            return "";
        }
    }

    public static String getSystemVersion() {
        String string = "";
        try {
            string = SystemPropertiesInvoke.getString("apps.setting.product.outswver", null);
            if (getPlatformCode().startsWith("Di1")) {
                BYDAutoVersionDevice.getInstance(Utils.getApp());
            }
        } catch (Throwable th) {
            LogUtil.e(" DeviceInfoUtils --> getSystemVersion:error=" + th);
        }
        return string;
    }

    public static boolean isInCall() {
        return isInCall(true);
    }

    public static boolean isInCall(boolean z) {
        boolean z2 = true;
        boolean z3 = HfpManage.getInstance().getCallState() > 0;
        boolean zIsCarplayCalling = CarplayManager.isCarplayCalling();
        boolean z4 = Settings.System.getInt(Utils.getApp().getContentResolver(), "byd_ecall_window_event", 0) == 1;
        if (!z3 && !zIsCarplayCalling && !z4) {
            z2 = false;
        }
        if (z2 && z) {
            LogUtil.i(" DeviceInfoUtils --> isInCall, btCall = " + z3 + ", carplayCalling = " + zIsCarplayCalling + ", ECallCalling = " + z4);
            if (z) {
                ToastUtil.showCenter(Utils.getApp().getResources().getString(R.string.incall_forbid_operation));
            }
        }
        return z2;
    }

    public static Boolean isNight() {
        return Boolean.valueOf(((UiModeManager) Utils.getApp().getSystemService(Context.UI_MODE_SERVICE)).getNightMode() != 1);
    }
}
