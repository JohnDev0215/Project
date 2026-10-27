package com.byd.auto_emanual.util;

import android.content.Context;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.text.TextUtils;
import com.byd.auto_emanual.Config;
import com.byd.auto_emanual.Parameters;
import com.byd.auto_emanual.fragment.ZipInfo;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class AutoInfoUtil {
    private static final String DEBUG_COUNTRY_PREFIX = "debug_country_";
    private static final String DEBUG_PARENT = "/storage/emulated/0/Pictures/";
    private static final String DEBUG_SERVER_FLAG = "/storage/emulated/0/Pictures/debug_server_flag";
    private static final String DEBUG_TYPE_PREFIX = "debug_type_";
    private static final short OFFLINE_DID = 8448;
    private static final String TAG = "AutoInfoUtil";
    private static String platformType = null;
    private static int powerType = -1;
    private static String type;
    private static String vin;

    public static boolean isDebugServer() {
        return new File(DEBUG_SERVER_FLAG).exists();
    }

    private static String getDebugType() {
        String debug = isDebugServer() ? getDebug("", DEBUG_TYPE_PREFIX) : "";
        if (!TextUtils.isEmpty(debug)) {
            MLog.d(TAG, "getDebugType = " + debug);
        }
        return debug;
    }

    private static String getDebugCountry() {
        String debug = isDebugServer() ? getDebug("", DEBUG_COUNTRY_PREFIX) : "";
        MLog.d(TAG, "getDebugCountry = " + debug);
        return debug;
    }

    private static String getDebug(String debugResult, String debugPrefix) {
        File[] fileArrListFiles;
        File file = new File(DEBUG_PARENT);
        if (!file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) {
            return debugResult;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.getName().startsWith(debugPrefix)) {
                return file2.getName().substring(debugPrefix.length());
            }
        }
        return debugResult;
    }

    public static void setOldVIN(String oldVin) {
        MLog.d(TAG, "setOldVIN: " + oldVin);
        vin = oldVin;
    }

    public static String getAutoVIN(Context context) {
        return !TextUtils.isEmpty(vin) ? vin : "byd304DE0CADF795900";
    }

    public static String getAutoType(Context context) {
        String debugType = getDebugType();
        type = debugType;
        if (!TextUtils.isEmpty(debugType)) {
            return type;
        }
        if (!Config.CAN_GET_AUTO_INFO) {
            return "92";
        }
        try {
            type = Utils.getProperty("persist.sys.AutoType", ZipInfo.NO_UPLOAD_TIME);
            MLog.d(TAG, "getAutoType: " + type);
            return type;
        } catch (Exception e) {
            MLog.e(TAG, "getAutoType", e);
            return "92";
        }
    }

    public static String getAutoTypeSub(Context context, String autoType) {
        return (autoType.equals("136") && isFourWheelDrive(context)) ? Parameters.CONTENT_ITEM_FOOTER : "";
    }

    public static String getPlatformType() {
        if (!TextUtils.isEmpty(platformType)) {
            return platformType;
        }
        String str = Utils.is8155F() ? "Di5.0_DXF_W" : "Di3.0_3.5UI";
        try {
            platformType = Utils.getProperty("ro.vehicle.type", str);
            MLog.d(TAG, "getPlatformType = " + platformType);
        } catch (Exception e) {
            platformType = str;
            MLog.e(TAG, "getPlatformType", e);
        }
        return platformType;
    }

    public static String getCountryCode() {
        String debugCountry = getDebugCountry();
        if (TextUtils.isEmpty(debugCountry)) {
            if (Utils.getProperty(BYDAutoSysPropertiesDevice.BUILD_REGION, " ").equals("ROM")) {
                debugCountry = Utils.getProperty(BYDAutoSysPropertiesDevice.COUNTRY_CODE, debugCountry);
            }
        } else if ("86".equals(debugCountry)) {
            debugCountry = "";
        }
        MLog.d(TAG, "getCountryCode = " + debugCountry);
        return debugCountry;
    }

    public static int getAutoModelName(Context context) {
        if (!Config.CAN_GET_AUTO_INFO) {
            return 0;
        }
        try {
            BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(context);
            if (bYDAutoBodyworkDevice != null) {
                return bYDAutoBodyworkDevice.getAutoModelName();
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            MLog.d(TAG, "getAutoModelName exception : " + e.getMessage());
            return 0;
        }
    }

    public static boolean is5GSimCard() {
        String property = Utils.getProperty("ro.byd.telephony.cap.5G", null);
        MLog.d(TAG, "is5GSimCard = " + property);
        return !TextUtils.isEmpty(property) && property.equalsIgnoreCase("true");
    }

    public static String getNetType() {
        return is5GSimCard() ? Parameters.CONTENT_ITEM_PATH : Parameters.CONTENT_ITEM_FOOTER;
    }

    private static boolean isFourWheelDrive(Context context) {
        if (Config.CAN_GET_AUTO_INFO) {
            try {
                BYDAutoEventValue bYDAutoEventValue = BYDAutoSettingDevice.getInstance(context).get(new int[]{546}, Integer.TYPE);
                if (bYDAutoEventValue != null) {
                    int i = bYDAutoEventValue.intValue;
                    MLog.i(TAG, "isFourWheelDrive : driveConfigType: " + i);
                    return i == 2 || i == 4;
                }
            } catch (Exception | NoSuchFieldError e) {
                MLog.i(TAG, "isFourWheelDrive error : " + e.toString());
            }
        }
        return false;
    }

    public static int getAutoPowerType(Context context) {
        if (powerType == -1) {
            BYDAutoTestDevice bYDAutoTestDevice = BYDAutoTestDevice.getInstance(context);
            if (bYDAutoTestDevice != null) {
                bYDAutoTestDevice.readConfigurationWordInfo(OFFLINE_DID);
                powerType = bYDAutoTestDevice.getAutoPowerType();
                MLog.d(TAG, "getAutoPowerType = " + powerType);
            } else {
                MLog.d(TAG, "getAutoPowerType mBYDAutoTestDevice = null " + powerType);
            }
        }
        return powerType;
    }
}
