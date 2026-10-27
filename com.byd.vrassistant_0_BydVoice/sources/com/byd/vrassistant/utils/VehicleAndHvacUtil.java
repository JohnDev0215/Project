package com.byd.vrassistant.utils;

import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.manager.SystemSettingManager;

/* JADX INFO: loaded from: classes2.dex */
public class VehicleAndHvacUtil {
    private static final String TAG = "NEU_VehicleAndHvacUtil";

    public static String getArea(String str) {
        try {
            RLog.i(TAG, "getArea:  before = " + str);
            if (str == null || "".equals(str) || VehicleAreaEnum.SEAT_UNKNOWN.equals(str)) {
                str = SharedPreferencesUtil.getCurrentArea(BydVoiceApplication.getContext());
            }
        } catch (Exception e) {
            RLog.i(TAG, "getArea: Exception = " + e.getMessage());
        }
        RLog.i(TAG, "getArea:  after = " + str);
        return str;
    }

    public static boolean isLeftRudder() {
        boolean z = !SystemSettingManager.get().isRightDriver();
        RLog.i(TAG, "isLeftRudder(): isLeft = " + z);
        return z;
    }
}
