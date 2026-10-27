package com.byd.vrsettings.utils;

import android.content.Context;
import android.os.Build;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.utils.Constant;

/* JADX INFO: loaded from: classes.dex */
public class ModeUtils {
    private static final String TAG = "NEU_ModeUtils";

    public static boolean isNightMode(Context context) {
        RLog.d(TAG, "isNightMode == Constant.isNightMode==" + Constant.isNightMode);
        if (Constant.isNightMode != Constant.UIMode.UNKNOWN) {
            return AnonymousClass1.$SwitchMap$com$byd$vrsettings$utils$Constant$UIMode[Constant.isNightMode.ordinal()] == 1;
        }
        if (getNightMode(context)) {
            Constant.isNightMode = Constant.UIMode.NIGHT;
            return true;
        }
        Constant.isNightMode = Constant.UIMode.WHITE;
        return false;
    }

    /* JADX INFO: renamed from: com.byd.vrsettings.utils.ModeUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$vrsettings$utils$Constant$UIMode;

        static {
            int[] iArr = new int[Constant.UIMode.values().length];
            $SwitchMap$com$byd$vrsettings$utils$Constant$UIMode = iArr;
            try {
                iArr[Constant.UIMode.NIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static boolean getNightMode(Context context) {
        BydVoiceSettingApplication context2 = BydVoiceSettingApplication.getContext();
        String vehicleType = Common.getVehicleType();
        RLog.d(TAG, "Vehicle Type: " + vehicleType);
        if (vehicleType.contains("Di3.0_3.0UI")) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            boolean zIsNightModeActive = context2.getResources().getConfiguration().isNightModeActive();
            RLog.d(TAG, "System is Night Mode R: " + zIsNightModeActive);
            return zIsNightModeActive;
        }
        int i = context2.getResources().getConfiguration().uiMode & 48;
        RLog.d(TAG, "System is Night Mode nightModeFlags: " + i);
        return i == 32;
    }
}
