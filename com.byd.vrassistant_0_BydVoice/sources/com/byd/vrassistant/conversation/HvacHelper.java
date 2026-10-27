package com.byd.vrassistant.conversation;

import android.app.UiStyleConfig;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.util.Log;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.VehicleAndHvacUtil;

/* JADX INFO: loaded from: classes.dex */
public class HvacHelper {
    private static final String TAG = "NEU_HVACHELPER";
    private BYDAutoBodyworkDevice mBodyworkDevice;
    private BYDAutoSettingDevice settingDevice;
    private UiStyleConfig uiStyleConfig;

    private static final class SingletonHolder {
        private static final HvacHelper instance = new HvacHelper();

        private SingletonHolder() {
        }
    }

    public static HvacHelper getInstance() {
        return SingletonHolder.instance;
    }

    private HvacHelper() {
        try {
            this.mBodyworkDevice = BYDAutoBodyworkDevice.getInstance(BydVoiceApplication.getContext());
            this.settingDevice = BYDAutoSettingDevice.getInstance(BydVoiceApplication.getContext());
            this.uiStyleConfig = UiStyleConfig.getInstance(BydVoiceApplication.getContext());
        } catch (Exception e) {
            RLog.i(TAG, "HvacHelper Exception ", e);
        }
    }

    public boolean checkCommandOpenAC() {
        try {
            RLog.i(TAG, "HvacCheckHelper checkAcOn");
            int powerLevel = this.mBodyworkDevice.getPowerLevel();
            RLog.i(TAG, "getPowerLevel = " + powerLevel);
            return powerLevel == 1 || powerLevel == 2;
        } catch (Exception e) {
            RLog.i(TAG, "HvacCheckHelper Exception ", e);
            return false;
        }
    }

    public boolean checkCommandCloseAC() {
        return checkCommandOpenAC();
    }

    public void reduceOrRestoreWindSpeed(boolean z) {
        try {
            RLog.i(TAG, "reduceWindSpeed isReduce = " + z);
            int i = z ? 1 : 2;
            RLog.i(TAG, "reduceWindSpeed value = " + i);
            RLog.i(TAG, "reduceWindSpeed intValue = " + this.settingDevice.setCallState(i));
        } catch (Exception e) {
            RLog.i(TAG, "reduceWindSpeed  " + e);
        }
    }

    public String changeRearAcArea(String str) {
        if (str == null) {
            str = "";
        }
        RLog.i(TAG, "changeRearAcArea:  before = " + str);
        if (str.equals(VehicleAreaEnum.SEAT_ROW_2_LEFT) || str.equals(VehicleAreaEnum.SEAT_ROW_2_RIGHT) || str.equals(VehicleAreaEnum.SEAT_ROW_REAR)) {
            int supportRearAc = this.uiStyleConfig.getSupportRearAc();
            RLog.i(TAG, "changeRearAcArea:  acRearOnLine = " + supportRearAc);
            if (supportRearAc != 2) {
                RLog.i(TAG, "changeRearAcArea:  ==>  SEAT_ROW_FRONT");
                str = VehicleAreaEnum.SEAT_ROW_FRONT;
            }
        }
        RLog.i(TAG, "changeRearAcArea:  after = " + str);
        return str;
    }

    public String changeTemperRearAcArea(String str) {
        if (str == null) {
            str = "";
        }
        RLog.i(TAG, "changeRearAcArea:  before = " + str);
        if (str.equals(VehicleAreaEnum.SEAT_ROW_2_LEFT) || str.equals(VehicleAreaEnum.SEAT_ROW_2_RIGHT) || str.equals(VehicleAreaEnum.SEAT_ROW_REAR)) {
            int supportRearAc = this.uiStyleConfig.getSupportRearAc();
            RLog.i(TAG, "changeRearAcArea:  acRearOnLine = " + supportRearAc);
            if (supportRearAc != 2) {
                if (VehicleAndHvacUtil.isLeftRudder()) {
                    RLog.i(TAG, "changeRearAcArea:  ==>  SEAT_ROW_1_LEFT");
                    str = VehicleAreaEnum.SEAT_ROW_1_LEFT;
                } else {
                    RLog.i(TAG, "changeRearAcArea:  ==>  SEAT_ROW_1_RIGHT");
                    str = VehicleAreaEnum.SEAT_ROW_1_RIGHT;
                }
            }
        }
        RLog.i(TAG, "changeRearAcArea:  before = " + str);
        return str;
    }

    public static String isChangeTemperRearAcArea(String str) {
        Log.d(TAG, "isChangeTemperRearAcArea() called with: vehicleArea = [" + str + "]");
        if (str == null || "".equals(str) || VehicleAreaEnum.SEAT_UNKNOWN.equals(str)) {
            return getInstance().changeTemperRearAcArea(VehicleAndHvacUtil.getArea(str));
        }
        return VehicleAndHvacUtil.getArea(str);
    }
}
