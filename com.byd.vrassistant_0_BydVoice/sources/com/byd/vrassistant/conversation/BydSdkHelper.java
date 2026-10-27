package com.byd.vrassistant.conversation;

import android.app.UiStyleConfig;
import android.content.Context;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.doorlock.BYDAutoDoorLockDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.hardware.bydauto.speed.BYDAutoSpeedDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.util.Log;
import cerence.ark.assistant.api.context.grpc.UiContextGrpc;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.TagUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class BydSdkHelper {
    public static final int ERROR_DATA = -1;
    public static final int GEAR_DIFFERENCE = 16;
    public static final int MAX_CENTIGRADE = 33;
    public static final int MAX_CENTIGRADE_AC_HEATING = 27;
    public static final int MAX_FAHRENHEIT = 91;
    public static final int MAX_FAHRENHEIT_AC_HEATING = 81;
    public static final int MIN_CENTIGRADE = 17;
    public static final int MIN_FAHRENHEIT = 64;
    private static final String TAG = TagUtil.getTagName(BydSdkHelper.class);
    private BYDAutoAcDevice acDevice;
    private BYDAutoStudy autoStudy;
    private BYDAutoBodyworkDevice bodyworkDevice;
    private BYDAutoDoorLockDevice doorLockDevice;
    private BYDAutoGearboxDevice gearboxDevice;
    private BYDAutoInstrumentDevice instrumentDevice;
    private BYDAutoSettingDevice settingDevice;
    private BYDAutoSpeedDevice speedDevice;
    private UiStyleConfig uiStyleConfig;

    private int percentChangeToValue(int i) {
        if (i > 90) {
            return 1;
        }
        if (i >= 40 && i <= 60) {
            return 4;
        }
        if (i < 5 || i > 20) {
            return i < 5 ? 2 : 0;
        }
        return 5;
    }

    private static final class SingletonHolder {
        private static final BydSdkHelper INSTANCE = new BydSdkHelper();

        private SingletonHolder() {
        }
    }

    public static BydSdkHelper getInstance() {
        RLog.i(TAG, "getInstance()");
        return SingletonHolder.INSTANCE;
    }

    private BydSdkHelper() {
        try {
            RLog.i(TAG, "BydSdkHelper()");
            this.bodyworkDevice = BYDAutoBodyworkDevice.getInstance(BydVoiceApplication.getContext());
            this.settingDevice = BYDAutoSettingDevice.getInstance(BydVoiceApplication.getContext());
            this.speedDevice = BYDAutoSpeedDevice.getInstance(BydVoiceApplication.getContext());
            this.instrumentDevice = BYDAutoInstrumentDevice.getInstance(BydVoiceApplication.getContext());
            this.uiStyleConfig = UiStyleConfig.getInstance(BydVoiceApplication.getContext());
            this.acDevice = BYDAutoAcDevice.getInstance(BydVoiceApplication.getContext());
            this.autoStudy = BYDAutoStudy.getInstance(BydVoiceApplication.getContext());
            this.gearboxDevice = BYDAutoGearboxDevice.getInstance(BydVoiceApplication.getContext());
            this.doorLockDevice = BYDAutoDoorLockDevice.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            RLog.i(TAG, "BydSdkHelper() Exception ", e);
        }
    }

    public boolean childLockConfig() {
        String str = TAG;
        RLog.i(str, "childLockConfig() called");
        int windowOrChildLockConfig = getWindowOrChildLockConfig();
        RLog.i(str, "childLockConfig childLockConfig = " + windowOrChildLockConfig);
        return windowOrChildLockConfig == 2;
    }

    public boolean windowLockConfig() {
        String str = TAG;
        RLog.i(str, "windowLockConfig() called");
        int windowOrChildLockConfig = getWindowOrChildLockConfig();
        RLog.i(str, "windowLockConfig windowLockConfig = " + windowOrChildLockConfig);
        return windowOrChildLockConfig == 1;
    }

    private int getWindowOrChildLockConfig() {
        String str = TAG;
        RLog.i(str, "getWindowOrChildLockConfig() called");
        int electroniChildLockConfig = this.settingDevice.getElectroniChildLockConfig();
        RLog.i(str, "getWindowOrChildLockConfig lockConfig = " + electroniChildLockConfig);
        return electroniChildLockConfig;
    }

    public boolean getWindowLockState() {
        String str = TAG;
        RLog.i(str, "getWindowLockState() called");
        boolean childLockState = getChildLockState(1);
        RLog.i(str, "getWindowLockState windowState = " + childLockState);
        return childLockState;
    }

    public boolean getChildLockState(int i) {
        String str = TAG;
        RLog.i(str, "getChildLockState() called with: position = [" + i + "]");
        int doorLockStatus = this.doorLockDevice.getDoorLockStatus(i == 1 ? 6 : 7);
        RLog.i(str, "getChildLockState childState = " + doorLockStatus);
        return doorLockStatus == 1;
    }

    public int getChildLockAllState() {
        String str = TAG;
        RLog.i(str, "getChildLockAllState() called with:");
        boolean childLockState = getChildLockState(1);
        boolean childLockState2 = getChildLockState(2);
        RLog.i(str, "getChildLockAllState lockLeft = " + childLockState + " lockRight = " + childLockState2);
        if (childLockState && childLockState2) {
            return 1;
        }
        return (childLockState || childLockState2) ? -1 : 2;
    }

    public boolean setWindowLockState(boolean z) {
        String str = TAG;
        RLog.i(str, "setWindowLockState() called with: value = [" + z + "]");
        int rearWindowLock = this.doorLockDevice.setRearWindowLock(z ? 2 : 1);
        RLog.i(str, "setWindowLockState setOk = " + rearWindowLock);
        return rearWindowLock == 0;
    }

    public boolean setChildLockAllState(boolean z) {
        boolean childLockState;
        String str = TAG;
        RLog.i(str, "setChildLockAllState() called with: value = [" + z + "]");
        if (getChildLockState(1) != z) {
            childLockState = setChildLockState(1, z);
            RLog.i(str, "setChildLockAllState lockLeft setOk = " + childLockState);
        } else {
            childLockState = false;
        }
        sleep300();
        if (getChildLockState(2) != z) {
            childLockState = setChildLockState(2, z);
            RLog.i(str, "setChildLockAllState lockRight setOk = " + childLockState);
        }
        RLog.i(str, "setChildLockAllState setOk = " + childLockState);
        return true;
    }

    private boolean setChildLockState(int i, boolean z) {
        String str = TAG;
        RLog.i(str, "setChildLockState() called with: position = [" + i + "], value = [" + z + "]");
        int doorLockStatus = this.doorLockDevice.setDoorLockStatus(i == 1 ? 6 : 7, z ? 2 : 1);
        RLog.i(str, "setChildLockStateByPosition setOk = " + doorLockStatus);
        return doorLockStatus == 0;
    }

    public boolean setWindowState(int[] iArr) {
        String str = TAG;
        RLog.i(str, "setWindowState() called with: setValues = [" + Arrays.toString(iArr) + "]");
        int windowControlState = this.bodyworkDevice.setWindowControlState(iArr);
        RLog.i(str, "setWindowState setOk = " + windowControlState);
        return windowControlState == 0;
    }

    public int getWindowState(int i) {
        int windowOpenPercent;
        String str = TAG;
        RLog.i(str, "getWindowState() called with: seat = [" + i + "]");
        if (i == 2) {
            windowOpenPercent = this.bodyworkDevice.getWindowOpenPercent(2);
        } else if (i == 3) {
            windowOpenPercent = this.bodyworkDevice.getWindowOpenPercent(3);
        } else if (i == 4) {
            windowOpenPercent = this.bodyworkDevice.getWindowOpenPercent(4);
        } else {
            windowOpenPercent = this.bodyworkDevice.getWindowOpenPercent(1);
        }
        RLog.i(str, "getWindowState value percent = " + windowOpenPercent);
        int iPercentChangeToValue = percentChangeToValue(windowOpenPercent);
        RLog.i(str, "getWindowState value after = " + iPercentChangeToValue);
        return iPercentChangeToValue;
    }

    public boolean readingLightConfig() {
        String str = TAG;
        RLog.i(str, "readingLightConfig() called with: ");
        int indoorLightSupport = this.autoStudy.getIndoorLightSupport();
        RLog.i(str, "readingLightConfig config = " + indoorLightSupport);
        return indoorLightSupport == 1;
    }

    public boolean setReadingLight(boolean z) {
        String str = TAG;
        RLog.i(str, "setReadingLight() called with: isOk = [" + z + "]");
        int insideLightDoorState = this.settingDevice.setInsideLightDoorState(z ? 2 : 1);
        RLog.i(str, "setReadingLight setOk = " + insideLightDoorState);
        return insideLightDoorState == 0;
    }

    public boolean getPm25Config() {
        String str = TAG;
        RLog.i(str, "getPm25Config() called");
        int supportPM25Mode = this.autoStudy.getSupportPM25Mode();
        Log.i(str, "getPm25Config: airPurifier = " + supportPM25Mode);
        return supportPM25Mode == 1;
    }

    public void sleep300() {
        try {
            RLog.i(TAG, "sleep 300ms");
            Thread.sleep(300L);
        } catch (Exception e) {
            RLog.i(TAG, "sleep Exception ", e);
        }
    }

    public boolean acHeatingKeyConfig(Context context) {
        String str = TAG;
        Log.i(str, "acHeatingKeyConfig: ");
        int acHeatingButtonConfig = BYDAutoAcDevice.getInstance(context).getAcHeatingButtonConfig();
        Log.i(str, "acHeatingKeyConfig: state = " + acHeatingButtonConfig);
        return acHeatingButtonConfig == 0;
    }

    public boolean isTemp05(Context context) {
        String str = TAG;
        Log.i(str, "isTemp05: ");
        int temperatureGranularity = getTemperatureGranularity(context);
        Log.i(str, "isTemp05: state = " + temperatureGranularity);
        return temperatureGranularity == 2;
    }

    public int getTemperatureGranularity(Context context) {
        String str = TAG;
        Log.i(str, "getTemperatureGranularity: ");
        int tempRegulatingAccuracy = BYDAutoAcDevice.getInstance(context).getTempRegulatingAccuracy();
        Log.i(str, "getTemperatureGranularity: state = " + tempRegulatingAccuracy);
        return tempRegulatingAccuracy;
    }

    public boolean getAcHeatingKey(Context context) {
        String str = TAG;
        Log.i(str, "getAcHeatingKey: ");
        int acHeatingState = BYDAutoAcDevice.getInstance(context).getAcHeatingState();
        Log.i(str, "getAcHeatingKey: state = " + acHeatingState);
        return acHeatingState == 1;
    }

    public boolean setAcHeatingKey(Context context, boolean z) {
        String str = TAG;
        Log.i(str, "setAcHeatingKey: ");
        int acHeatingState = BYDAutoAcDevice.getInstance(context).setAcHeatingState(z ? 1 : 2);
        Log.i(str, "setAcHeatingKey: set = " + acHeatingState);
        return acHeatingState == 1;
    }

    public boolean getAcVentilationState() {
        String str = TAG;
        Log.i(str, "getAcVentilationState() called");
        int i = this.acDevice.get(new int[]{3554}, Integer.TYPE).intValue;
        Log.i(str, "getAcVentilationState: state = " + i);
        return i == 1;
    }

    public double getTemperature(Context context, int i) {
        int temprature;
        String str = TAG;
        Log.i(str, "getTemperature() called with: area = [" + i + "]");
        if (i == 1) {
            temprature = BYDAutoAcDevice.getInstance(context).getTemprature(1);
        } else if (i == 2) {
            if (HvacTemperatureHelper.getInstance().isSingleTemperatureControl()) {
                temprature = BYDAutoAcDevice.getInstance(context).getTemprature(1);
            } else {
                temprature = BYDAutoAcDevice.getInstance(context).getTemprature(2);
            }
        } else {
            temprature = BYDAutoAcDevice.getInstance(context).getTemprature(3);
        }
        double d = temprature;
        Log.i(str, "getTemperature: temp old = " + d);
        if (isTemp05(context)) {
            d /= 2.0d;
            Log.i(str, "getTemperature: temp 0.5 = " + d);
        }
        UiContextGrpc.TemperatureExpression temperUnit = HvacTemperatureHelper.getInstance().getTemperUnit();
        Log.i(str, "getTemperature: temperatureUnit = " + temperUnit);
        if (temperUnit != UiContextGrpc.TemperatureExpression.CELSIUS ? d < 64.0d || d > 91.0d : d < 17.0d || d > 33.0d) {
            d = -1.0d;
        }
        Log.i(str, "getTemperature: temp = " + d);
        return d;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean setTemperature(android.content.Context r4, int r5, double r6, int r8) {
        /*
            r3 = this;
            java.lang.String r0 = com.byd.vrassistant.conversation.BydSdkHelper.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "setTemperature() called with: area = ["
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.String r2 = "], value = ["
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r6)
            java.lang.String r2 = "]"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r0, r1)
            r1 = 1
            if (r5 != r1) goto L2a
        L28:
            r2 = r1
            goto L39
        L2a:
            r2 = 2
            if (r5 != r2) goto L38
            com.byd.vrassistant.conversation.HvacTemperatureHelper r5 = com.byd.vrassistant.conversation.HvacTemperatureHelper.getInstance()
            boolean r5 = r5.isSingleTemperatureControl()
            if (r5 == 0) goto L39
            goto L28
        L38:
            r2 = 3
        L39:
            boolean r4 = r3.isTemp05(r4)
            if (r4 == 0) goto L55
            r4 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r6 = r6 * r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "setTemperature: temp 0.5 = "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r6)
            java.lang.String r4 = r4.toString()
            android.util.Log.i(r0, r4)
        L55:
            android.hardware.bydauto.ac.BYDAutoAcDevice r4 = r3.acDevice
            int r5 = (int) r6
            int r4 = r4.setAcTemperature(r2, r5, r1, r8)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "setTemperature: setOk = "
            r5.<init>(r6)
            java.lang.StringBuilder r5 = r5.append(r4)
            java.lang.String r5 = r5.toString()
            android.util.Log.i(r0, r5)
            if (r4 != 0) goto L72
            goto L73
        L72:
            r1 = 0
        L73:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.BydSdkHelper.setTemperature(android.content.Context, int, double, int):boolean");
    }
}
