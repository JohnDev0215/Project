package com.micphone;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.micphone.service.MicphoneService;
import com.micphone.utils.ReflectUtil;
import com.micphone.utils.SysUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class TsBydEventBase {
    private static final String KEY_BASE_ANDROID_ID = "androidid";
    private static final String KEY_BASE_API = "_api";
    private static final String KEY_BASE_CAR_ID = "_car_id";
    private static final String KEY_BASE_CAR_MODEL = "_car_model";
    private static final String KEY_BASE_CAR_PLATFORM = "_car_platform";
    private static final String KEY_BASE_DID = "_did";
    private static final String KEY_BASE_FIRM = "_firm";
    private static final String KEY_BASE_FLAG_1 = "&";
    private static final String KEY_BASE_FLAG_2 = "=";
    private static final String KEY_BASE_H = "_h";
    private static final String KEY_BASE_IM_EI = "imei";
    private static final String KEY_BASE_IM_SI = "imsi";
    private static final String KEY_BASE_KTV_ID = "ktv_id";
    private static final String KEY_BASE_LAT = "_lat";
    private static final String KEY_BASE_LNG = "_lng";
    private static final String KEY_BASE_M = "_m";
    private static final String KEY_BASE_MAC = "_mac";
    private static final String KEY_BASE_MIKE_BIND = "_mike_bind";
    private static final String KEY_BASE_MIKE_PID = "_mike_pid";
    private static final String KEY_BASE_MIKE_SN = "_mike_sn";
    private static final String KEY_BASE_MIKE_VER = "_mike_ver";
    private static final String KEY_BASE_MIKE_VID = "_mike_vid";
    private static final String KEY_BASE_NET_TYPE = "_net_type";
    private static final String KEY_BASE_OS_VER = "_os_ver";
    private static final String KEY_BASE_R1 = "_r1";
    private static final String KEY_BASE_R2 = "_r2";
    private static final String KEY_BASE_SDK_INFO = "_sdk_info";
    private static final String KEY_BASE_SRC = "_src";
    private static final String KEY_BASE_TM = "_tm";
    private static final String KEY_BASE_TS = "_ts";
    private static final String KEY_BASE_TYPE_SRC = "_type_src";
    private static final String KEY_BASE_VN = "_vn";
    private static final String TAG = "TsBydEventBase";
    private static final String TS_EVENT_URL = "https://log5.ktvsky.com/mic/event?";
    private static final String TS_EVENT_URL_TEST = "https://log3pre.ktvsky.com/mic/event?";

    private static String addArgument(String str, Object obj) {
        return KEY_BASE_FLAG_1 + str + KEY_BASE_FLAG_2 + obj;
    }

    private static String getBaseAndroidId(Context context) {
        return SysUtils.getInstance().getAndroidId(context);
    }

    private static int getBaseApi() {
        return Build.VERSION.SDK_INT;
    }

    private static String getBaseCarId() {
        return null;
    }

    private static String getBaseCarModel(TsBydMicManager tsBydMicManager) {
        return String.valueOf(tsBydMicManager.getCarType());
    }

    private static String getBaseCarPlatform() {
        return Build.PRODUCT;
    }

    private static String getBaseDid() {
        return null;
    }

    public static String getBaseEvent(TsBydMicManager tsBydMicManager, int i, int i2) {
        Context context = tsBydMicManager.getContext();
        StringBuilder sb = new StringBuilder();
        String property = ReflectUtil.getProperty(ReflectUtil.BYD_EVENT_URL_TEST, "1");
        Log.d(TAG, "event_url_test=" + property);
        if ("1".equals(property)) {
            sb.append(TS_EVENT_URL);
        } else {
            sb.append(TS_EVENT_URL_TEST);
        }
        sb.append(addArgument(KEY_BASE_SRC, getBaseSrc()));
        sb.append(addArgument(KEY_BASE_TYPE_SRC, getBaseTypeSrc()));
        sb.append(addArgument(KEY_BASE_CAR_PLATFORM, getBaseCarPlatform()));
        sb.append(addArgument(KEY_BASE_CAR_MODEL, getBaseCarModel(tsBydMicManager)));
        sb.append(addArgument(KEY_BASE_M, getBaseM()));
        sb.append(addArgument(KEY_BASE_H, getBaseH(context)));
        sb.append(addArgument(KEY_BASE_R1, getBaseR1(context)));
        sb.append(addArgument(KEY_BASE_R2, getBaseR2(context)));
        sb.append(addArgument(KEY_BASE_IM_SI, getBaseImsi()));
        sb.append(addArgument(KEY_BASE_IM_EI, getBaseImei()));
        sb.append(addArgument(KEY_BASE_ANDROID_ID, getBaseAndroidId(context)));
        sb.append(addArgument(KEY_BASE_KTV_ID, getBaseKtvId()));
        sb.append(addArgument(KEY_BASE_MAC, getBaseMac()));
        sb.append(addArgument(KEY_BASE_DID, getBaseDid()));
        sb.append(addArgument(KEY_BASE_CAR_ID, getBaseCarId()));
        sb.append(addArgument(KEY_BASE_NET_TYPE, getBaseNetType(context)));
        sb.append(addArgument(KEY_BASE_FIRM, getBaseFirm()));
        sb.append(addArgument(KEY_BASE_SDK_INFO, getBaseSdkInfo()));
        sb.append(addArgument(KEY_BASE_OS_VER, getBaseOsVer()));
        sb.append(addArgument(KEY_BASE_VN, getBaseVn(context)));
        sb.append(addArgument(KEY_BASE_MIKE_BIND, Integer.valueOf(getBaseMikeBind(tsBydMicManager))));
        sb.append(addArgument(KEY_BASE_MIKE_VER, getBaseMikeVer()));
        sb.append(addArgument(KEY_BASE_MIKE_SN, getBaseMikeSn()));
        sb.append(addArgument(KEY_BASE_MIKE_PID, Integer.valueOf(getBaseMikePid())));
        sb.append(addArgument(KEY_BASE_MIKE_VID, Integer.valueOf(getBaseMikeVid())));
        sb.append(addArgument(KEY_BASE_API, Integer.valueOf(getBaseApi())));
        sb.append(addArgument(KEY_BASE_LAT, Float.valueOf(getBaseLat())));
        sb.append(addArgument(KEY_BASE_LNG, Float.valueOf(getBaseLng())));
        sb.append(addArgument(KEY_BASE_TM, getBaseTm()));
        sb.append(addArgument(KEY_BASE_TS, Long.valueOf(getBaseTs())));
        sb.append(TsBydEventCustom.getCustomEvent(tsBydMicManager, i, i2));
        return sb.toString();
    }

    private static String getBaseFirm() {
        return null;
    }

    private static String getBaseH(Context context) {
        return context.getResources().getConfiguration().orientation != 2 ? "2" : "1";
    }

    private static String getBaseImei() {
        return null;
    }

    private static String getBaseImsi() {
        return null;
    }

    private static String getBaseKtvId() {
        return null;
    }

    private static float getBaseLat() {
        return 0.0f;
    }

    private static float getBaseLng() {
        return 0.0f;
    }

    private static String getBaseM() {
        return Build.MODEL;
    }

    private static String getBaseMac() {
        return SysUtils.getInstance().getMacAddress();
    }

    private static int getBaseMikeBind(TsBydMicManager tsBydMicManager) {
        return tsBydMicManager.isBindMic() ? 1 : 2;
    }

    private static int getBaseMikePid() {
        return MicphoneService.getUsbDevicePid();
    }

    private static String getBaseMikeSn() {
        return MicphoneService.getUsbDeviceSnLong();
    }

    private static String getBaseMikeVer() {
        return MicphoneService.getUsbDeviceName();
    }

    private static int getBaseMikeVid() {
        return MicphoneService.getUsbDeviceVid();
    }

    private static String getBaseNetType(Context context) {
        return SysUtils.getInstance().netType(SysUtils.getInstance().getAPNType(context));
    }

    private static String getBaseOsVer() {
        return ReflectUtil.getProperty("ro.product.version.soc.name", null);
    }

    private static String getBaseR1(Context context) {
        return SysUtils.getInstance().getScreenWidthHeightDensity(context);
    }

    private static String getBaseR2(Context context) {
        return SysUtils.getInstance().getScreenWidthHeightDensity(context);
    }

    private static String getBaseSdkInfo() {
        return Build.VERSION.RELEASE;
    }

    private static String getBaseSrc() {
        return TsBydMicManager.TS_CHANNEL;
    }

    private static String getBaseTm() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    private static long getBaseTs() {
        return System.currentTimeMillis();
    }

    private static String getBaseTypeSrc() {
        return "0";
    }

    private static String getBaseVn(Context context) {
        return SysUtils.getInstance().getAppVersion(context);
    }
}
