package com.byd.vrassistant.conversation;

import android.app.UiStyleConfig;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class VehicleHelper {
    private static final String TAG = TagUtil.getTagName(VehicleHelper.class);
    private BYDAutoAcDevice acDevice;
    private BYDAutoStudy autoStudy;
    private BYDAutoBodyworkDevice bodyworkDevice;
    private BYDAutoInstrumentDevice instrumentDevice;
    private BYDAutoSettingDevice settingDevice;
    private UiStyleConfig uiStyleConfig;

    private VehicleHelper() {
        try {
            Log.i(TAG, "VehicleHelper()");
            this.settingDevice = BYDAutoSettingDevice.getInstance(BydVoiceApplication.getContext());
            this.bodyworkDevice = BYDAutoBodyworkDevice.getInstance(BydVoiceApplication.getContext());
            this.autoStudy = BYDAutoStudy.getInstance(BydVoiceApplication.getContext());
            this.uiStyleConfig = UiStyleConfig.getInstance(BydVoiceApplication.getContext());
            this.acDevice = BYDAutoAcDevice.getInstance(BydVoiceApplication.getContext());
            this.instrumentDevice = BYDAutoInstrumentDevice.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            Log.i(TAG, "VehicleHelper() Exception ", e);
        }
    }

    private static final class SingletonHolder {
        private static final VehicleHelper INSTANCE = new VehicleHelper();

        private SingletonHolder() {
        }
    }

    public static VehicleHelper getInstance() {
        Log.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    public void saveSettingValue() {
        int seatVentilatingNotL1PState;
        ApplicationInfo applicationInfo;
        int seatHeatingState = this.settingDevice.getSeatHeatingState(1);
        if (seatHeatingState >= 1 && seatHeatingState <= 3) {
            saveSettingsValue("EXIST_DRIVER_SEAT_HEATING", 1);
        } else {
            saveSettingsValue("EXIST_DRIVER_SEAT_HEATING", 0);
        }
        int seatVentilatingState = this.settingDevice.getSeatVentilatingState(1);
        int leftPlatformConfigs = this.autoStudy.getLeftPlatformConfigs();
        String str = TAG;
        RLog.i(str, "checkHvacFunctionAvailability: configs = " + leftPlatformConfigs);
        if (leftPlatformConfigs == 1) {
            seatVentilatingNotL1PState = this.settingDevice.getSeatVentilatingState(2);
        } else {
            seatVentilatingNotL1PState = this.settingDevice.getSeatVentilatingNotL1PState();
        }
        int seatVentilatingState2 = this.settingDevice.getSeatVentilatingState(3);
        RLog.i(str, "checkHvacFunctionAvailability: seatRearLeft = " + seatVentilatingState2);
        if (seatVentilatingState2 < 1 || seatVentilatingState2 > 3) {
            seatVentilatingState2 = this.settingDevice.getSeatVentilatingState2(3);
        }
        int seatVentilatingState3 = this.settingDevice.getSeatVentilatingState(4);
        RLog.i(str, "checkHvacFunctionAvailability: seatRearRight = " + seatVentilatingState3);
        if (seatVentilatingState3 < 1 || seatVentilatingState3 > 3) {
            seatVentilatingState3 = this.settingDevice.getSeatVentilatingState2(4);
        }
        if ((seatVentilatingState >= 1 && seatVentilatingState <= 3) || ((seatVentilatingNotL1PState >= 1 && seatVentilatingNotL1PState <= 3) || ((seatVentilatingState2 >= 1 && seatVentilatingState2 <= 3) || (seatVentilatingState3 >= 1 && seatVentilatingState3 <= 3)))) {
            saveSettingsValue("EXIST_SEAT_VENTILATION", 1);
        } else {
            saveSettingsValue("EXIST_SEAT_VENTILATION", 0);
        }
        if (seatVentilatingNotL1PState >= 1 && seatVentilatingNotL1PState <= 3) {
            saveSettingsValue("EXIST_PASSENGER_SEAT_VENTILATION", 1);
        } else {
            saveSettingsValue("EXIST_PASSENGER_SEAT_VENTILATION", 0);
        }
        int ambientLightColorSupport = this.uiStyleConfig.getAmbientLightColorSupport();
        Log.i(str, "saveSettingValue: colorConfig = " + ambientLightColorSupport);
        if (ambientLightColorSupport > 2 && ambientLightColorSupport < 7) {
            saveSettingsValue("EXIST_MULTICOLOR_LIGHT", 1);
        } else {
            saveSettingsValue("EXIST_MULTICOLOR_LIGHT", 0);
        }
        int iALBrightness = this.settingDevice.getIALBrightness(1);
        RLog.i(str, "saveSettingValue: lightBrightness = " + iALBrightness);
        if (iALBrightness > 0 && iALBrightness < 7) {
            saveSettingsValue("EXIST_ATMOSPHERE_LIGHT", 1);
        } else {
            saveSettingsValue("EXIST_ATMOSPHERE_LIGHT", 0);
        }
        int moonRoofConfig = this.bodyworkDevice.getMoonRoofConfig();
        Log.i(str, "saveSettingValue: sunroofOnLine = " + moonRoofConfig);
        if (moonRoofConfig == 1 || moonRoofConfig == 3 || moonRoofConfig == 4 || moonRoofConfig == 6 || moonRoofConfig == 7) {
            saveSettingsValue("EXIST_CAR_DORMANT_WINDOW", 1);
        } else {
            saveSettingsValue("EXIST_CAR_DORMANT_WINDOW", 0);
        }
        if (moonRoofConfig == 1 || moonRoofConfig == 2 || moonRoofConfig == 6 || moonRoofConfig == 7) {
            saveSettingsValue("EXIST_SUNSHADE", 1);
        } else {
            saveSettingsValue("EXIST_SUNSHADE", 0);
        }
        saveSettingsValue("EXIST_ROTATION", VehicleBasicHelper.getInstance().rotateTheScreenConfig() ? 1 : 0);
        int acControlMode = this.acDevice.getAcControlMode();
        Log.i(str, "saveSettingValue: acType = " + acControlMode);
        if (acControlMode == 1) {
            saveSettingsValue("EXIST_FAN", 1);
        } else {
            saveSettingsValue("EXIST_FAN", 0);
        }
        int windowAntiPinchConfig = this.bodyworkDevice.getWindowAntiPinchConfig();
        Log.i(str, "saveSettingValue: windowConfig = " + windowAntiPinchConfig);
        if (windowAntiPinchConfig == 4) {
            saveSettingsValue("EXIST_CAR_WINDOW", 1);
        } else {
            saveSettingsValue("EXIST_CAR_WINDOW", 0);
        }
        int iHasMessage = this.bodyworkDevice.hasMessage(59);
        Log.i(str, "saveSettingValue: acOnLine = " + iHasMessage);
        if (iHasMessage == 1) {
            saveSettingsValue("EXIST_AC", 1);
            saveSettingsValue("EXIST_SPEED_LEVEL", 1);
            saveSettingsValue("EXIST_LOOP", 1);
            saveSettingsValue("EXIST_AC_DEFROST", 1);
        } else {
            saveSettingsValue("EXIST_AC", 0);
            saveSettingsValue("EXIST_SPEED_LEVEL", 0);
            saveSettingsValue("EXIST_LOOP", 0);
            saveSettingsValue("EXIST_AC_DEFROST", 0);
        }
        Intent intent = new Intent();
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setAction("android.intent.action.MAIN");
        intent.setPackage("com.byd.carsettings");
        saveSettingsValue("EXIST_CAR_SETTINGS", 0);
        Iterator<ResolveInfo> it = BydVoiceApplication.getContext().getPackageManager().queryIntentActivities(intent, 0).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && (applicationInfo = activityInfo.applicationInfo) != null) {
                String str2 = applicationInfo.packageName;
                RLog.i(TAG, "saveSettingValue: pkgName = " + str2);
                if (!TextUtils.isEmpty(str2) && str2.equals("com.byd.carsettings")) {
                    saveSettingsValue("EXIST_CAR_SETTINGS", 1);
                }
            }
        }
        if (BydSdkHelper.getInstance().readingLightConfig()) {
            saveSettingsValue("EXIST_READING_LIGHT", 1);
        } else {
            saveSettingsValue("EXIST_READING_LIGHT", 0);
        }
        if (BydSdkHelper.getInstance().getPm25Config()) {
            saveSettingsValue("EXIST_RAPID_PURIFICATION", 1);
        } else {
            saveSettingsValue("EXIST_RAPID_PURIFICATION", 0);
        }
    }

    private static void saveSettingsValue(String str, int i) {
        Settings.System.putInt(BydVoiceApplication.getContext().getContentResolver(), str, i);
    }

    public int getLengthUnit() {
        String str = TAG;
        Log.i(str, "getLengthUnit() called");
        int mileageUnit = this.instrumentDevice.getMileageUnit();
        Log.i(str, "getLengthUnit: mileageUnit = " + mileageUnit);
        return mileageUnit;
    }
}
