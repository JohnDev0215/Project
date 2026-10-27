package com.byd.dilink;

import android.content.Context;
import androidx.annotation.Keep;
import com.byd.dilink.log.DiLinkSDKLog;
import com.byd.foundation.utils.StringUtils;
import com.byd.foundation.utils.SystemPropertyUtils;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class DiLink {
    private static final String DECOUPLE_INDICATOR = "com.byd.annotation.BydAutoRegistry";
    public static final String SDK_VERSION = "0.0.1-rc.2";
    private static final String SELF_CHECK_INNER_TEST = "com.byd.dilinksdk.demo.SelfCheckInnerTest";
    private static final String TAG = "DiLink";
    public static final String VERSION_NAME_3_0 = "Di3.0_3.5UI";
    public static final String VERSION_NAME_3_0_3_0 = "Di3.0_3.0UI";
    public static final String VERSION_NAME_4_0 = "Di4.0_3.5UI";
    public static final String VERSION_NAME_5_0 = "Di5.0_5.0UI";
    public static final String VERSION_NAME_5_1 = "Di5.1_5.0UI";
    public static final String VERSION_NAME_6_0 = "RUI";
    public static final String VERSION_NAME_DiR_RUI = "DiR_RUI";
    public static final String VERSION_NAME_FSE_5_0 = "FSE_5.0UI";
    public static final String VERSION_NAME_FSE_5_1 = "FSE5.1_5.0UI";
    public static final String VERSION_NAME_FSE_RUI = "FSE_RUI";
    public static final String VERSION_NAME_RCS = "RCS_RUI";
    public static final String VERSION_NAME_RIES = "RIES_RUI";
    public static final String VERSION_NAME_RIES_5_0 = "RIES_5.0UI";
    public static final String VERSION_NAME_RSE_5_0 = "RSE_5.0UI";
    public static final String VERSION_NAME_RSE_RUI = "RSE_RUI";
    private static volatile boolean sIsForceUseDecoupleApi = false;
    private static volatile boolean sIsInnerTestPropChecked = false;
    private static boolean sIsSelfCheckOpen;
    private static String sVersionName;

    static {
        try {
            Class.forName(SELF_CHECK_INNER_TEST);
            sIsSelfCheckOpen = true;
            DiLinkSDKLog.d("self check is open!");
        } catch (ClassNotFoundException unused) {
            sIsSelfCheckOpen = false;
            DiLinkSDKLog.d("self check is close!");
        }
    }

    public static String getVersionName(Context context) {
        if (!StringUtils.isEmpty(sVersionName)) {
            return sVersionName;
        }
        sVersionName = SystemPropertyUtils.get(context, "ro.vehicle.type", "unknown");
        DiLinkSDKLog.d("DiLinkServiceManager->vehicleType: " + sVersionName);
        verifyPlatformAvailable(sVersionName);
        return sVersionName;
    }

    private static boolean hasDecoupleIndicatorClass() {
        boolean z = false;
        try {
            Class.forName(DECOUPLE_INDICATOR);
            z = true;
            DiLinkSDKLog.d("Current platform has DecoupleIndicatorClass!");
            return true;
        } catch (ClassNotFoundException unused) {
            DiLinkSDKLog.d("Current platform has no DecoupleIndicatorClass!");
            return z;
        }
    }

    private static boolean isCurrentPlatformDecoupled() {
        return isForceUseDecoupledApi();
    }

    private static boolean isForceUseDecoupledApi() {
        if (sIsInnerTestPropChecked) {
            return sIsForceUseDecoupleApi;
        }
        boolean z = false;
        int iIntValue = SystemPropertyUtils.getInt(DiLinkServiceManager.getApplicationContext(), "dilink.sdk.inner_test", 0).intValue();
        DiLinkSDKLog.d("prop [dilink.sdk.inner_test] = " + iIntValue);
        if (iIntValue != 110 && hasDecoupleIndicatorClass()) {
            z = true;
        }
        sIsForceUseDecoupleApi = z;
        DiLinkSDKLog.d("sIsForceUseDecoupleApi = " + sIsForceUseDecoupleApi);
        sIsInnerTestPropChecked = true;
        return sIsForceUseDecoupleApi;
    }

    public static boolean isNotSelfCheckInnerTest() {
        return !sIsSelfCheckOpen;
    }

    public static boolean isPlatform(String str) {
        return sVersionName.equals(str);
    }

    public static boolean isPlatform(String str, boolean z) {
        return sVersionName.equals(str) && isCurrentPlatformDecoupled() == z;
    }

    private static void verifyPlatformAvailable(String str) {
        str.hashCode();
        switch (str) {
            case "Di3.0_3.0UI":
            case "Di3.0_3.5UI":
            case "RSE_RUI":
            case "Di5.0_5.0UI":
            case "DiR_RUI":
            case "RIES_RUI":
            case "Di5.1_5.0UI":
            case "RUI":
            case "FSE_RUI":
            case "RSE_5.0UI":
            case "Di4.0_3.5UI":
            case "FSE_5.0UI":
            case "RIES_5.0UI":
            case "FSE5.1_5.0UI":
            case "RCS_RUI":
                return;
            default:
                throw new RuntimeException("Unsupported Target Platform: " + str);
        }
    }
}
