package com.byd.vrassistant.utils;

/* JADX INFO: loaded from: classes2.dex */
public class SystemSettingCodeUtil {
    private static final String TAG = "NEU_SystemSettingUtil";
    private static final String formatted3Num = "%03d";
    private static final String formatted4Num = "%04d";

    private static String getPageCode(int i, int i2, int i3, int i4) {
        return get3BitNum(i) + get3BitNum(i2) + get4BitNum(i3) + get4BitNum(i4);
    }

    private static String get3BitNum(int i) {
        return String.format(formatted3Num, Integer.valueOf(i));
    }

    private static String get4BitNum(int i) {
        return String.format(formatted4Num, Integer.valueOf(i));
    }

    private static String getLevel1PageCode(int i) {
        return getPageCode(i, 0, 0, 0);
    }

    private static String getLevel2PageCode(int i, int i2) {
        return getPageCode(i, i2, 0, 0);
    }

    private static String getLevel3PageCode(int i, int i2, int i3) {
        return getPageCode(i, i2, i3, 0);
    }

    public static String getLightPageCode() {
        return getLevel1PageCode(3);
    }

    public static String getAmbientLightPageCode() {
        return getLevel2PageCode(6, 2);
    }

    public static String getVoiceSettingPageCode() {
        return getLevel1PageCode(14);
    }

    public static String getAudioSettingPageCode() {
        return getLevel2PageCode(10, 1);
    }

    public static String getSOCPageCode() {
        return getLevel3PageCode(5, 1, 3);
    }

    public static String getSystemSettingPageCode() {
        return getLevel1PageCode(15);
    }

    public static String getBTPageCode() {
        return getLevel3PageCode(3, 1, 1);
    }

    public static String getWifiPageCode() {
        return getLevel3PageCode(3, 1, 2);
    }

    public static String getHotPotPageCode() {
        return getLevel3PageCode(3, 1, 3);
    }

    public static String get4GPageCode() {
        return getLevel3PageCode(3, 1, 4);
    }

    public static String getSystemLanguageCode() {
        return getLevel3PageCode(3, 6, 3);
    }

    public static String getIntelligentNavigationPageCode() {
        return getLevel2PageCode(9, 1);
    }

    public static String getLaneSupportPageCode() {
        return getLevel3PageCode(4, 1, 13);
    }

    public static String getLaneAssistantPageCode() {
        return getLevel2PageCode(9, 2);
    }

    public static String getAirSettingCode() {
        return getLevel2PageCode(6, 9);
    }

    public static String getVehicleSettingCode() {
        return getLevel1PageCode(8);
    }
}
