package com.byd.vrassistant.bridge;

/* JADX INFO: loaded from: classes.dex */
public class BridgeHelper {
    static NaviStatus naviStatus;
    static SystemSetting systemSetting;
    static VrAreaStatus vrAreaStatus;
    static VrOperation vrOperation;
    static VrStatus vrStatus;
    static VrUiStatus vrUiStatus;

    public static SystemSetting getSystemSetting() {
        return systemSetting;
    }

    public static void setSystemSetting(SystemSetting systemSetting2) {
        systemSetting = systemSetting2;
    }

    public static VrAreaStatus getVrAreaStatus() {
        return vrAreaStatus;
    }

    public static void setVrAreaStatus(VrAreaStatus vrAreaStatus2) {
        vrAreaStatus = vrAreaStatus2;
    }

    public static VrStatus getVrStatus() {
        return vrStatus;
    }

    public static void setVrStatus(VrStatus vrStatus2) {
        vrStatus = vrStatus2;
    }

    public static VrOperation getVrOperation() {
        return vrOperation;
    }

    public static void setVrOperation(VrOperation vrOperation2) {
        vrOperation = vrOperation2;
    }

    public static VrUiStatus getVrUiStatus() {
        return vrUiStatus;
    }

    public static void setVrUiStatus(VrUiStatus vrUiStatus2) {
        vrUiStatus = vrUiStatus2;
    }

    public static NaviStatus getNaviStatus() {
        return naviStatus;
    }

    public static void setNaviStatus(NaviStatus naviStatus2) {
        naviStatus = naviStatus2;
    }
}
