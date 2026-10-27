package com.byd.vrassistant.utils;

import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.vrassistant.BydVoiceApplication;

/* JADX INFO: loaded from: classes2.dex */
public class CarModeUtils {
    private static final String DEFAULT_ZONE = "0";
    private static String[] ForceDoubleAudioZones_CountryCode = {"673", "60", "91", "84", "62", "7", "375", "380", "373", "374", "372", "370", "994", "997", "996", "998", "992", "966", "971", "965", "968", "974", "973"};
    public static String[] SCD_WhiteList = {"car_47_0_0_6", "car_8D_0_0_4", "car_5A_0_0_4", "car_8F_0_0_4", "car_DC_0_0_4", "car_B6_4_1_8", "car_D0_4_1_8", "car_B6_0_1_8", "car_D0_0_1_8", "car_B4_0_1_8", "car_66_0_0_4", "car_72_0_0_4", "car_80_0_0_4", "car_87_4_0_4", "car_87_4_1_6", "car_87_4_0_6", "car_87_4_0_4", "car_89_4_1_6", "car_89_4_0_6", "car_89_4_0_4", "car_8A_0_0_4", "car_8A_4_0_4", "car_BA_0_0_4", "car_7F_0_0_4", "car_A2_0_0_4", "car_AD_0_0_4", "car_A2_6_0_4", "car_AD_6_0_4", "car_A2_0_0_6", "car_A2_6_0_6", "car_47_0_0_4", "car_C4_4_1_8", "car_89_4_1_8", "car_FF_0_0_4", "car_FF_0_1_6", "car_FF_0_1_8", "car_94_0_0_4", "car_D7_0_0_4", "car_8F_4_1_8", "car_FF_0_1", "car_8155_0_1", "car_C4_4_1", "car_89_4_1", "car_80_0_0", "car_89_4_0", "car_87_4_0", "car_66_0_0", "car_47_0_0", "car_A2_6_0", "car_A2_0_0", "car_AD_6_0", "car_AD_0_0", "car_8A_0_0", "car_8A_4_0", "car_7F_0_0", "car_BA_0_0", "car_FF_0_0"};
    private static final String TAG = "NEU_CarMode";
    private static String currentCarMode;

    public static String getZoneType() {
        String str = currentCarMode;
        if (str == null) {
            return DEFAULT_ZONE;
        }
        try {
            return str.split("_")[3];
        } catch (Exception e) {
            RLog.e(TAG, "getZoneType error", e);
            return DEFAULT_ZONE;
        }
    }

    public static String getCarMode(int i, String str, String str2) {
        CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext());
        String str3 = "car_FF_0_1_8";
        if (i != 255) {
            if (i == 137 && (str.equals("4") || str.equals("3"))) {
                str3 = "car_89_4_1_8";
            } else if (i == 196 && (str.equals("4") || str.equals("3"))) {
                str3 = "car_C4_4_1_8";
            } else if (i == 182 && (str.equals("4") || str.equals("3"))) {
                str3 = "car_B6_4_1_8";
            } else if (i == 182 && str.equals(DEFAULT_ZONE)) {
                str3 = "car_B6_0_1_8";
            } else if (i == 208 && (str.equals("4") || str.equals("3"))) {
                str3 = "car_D0_4_1_8";
            } else if (i == 208 && str.equals(DEFAULT_ZONE)) {
                str3 = "car_D0_0_1_8";
            } else if (i == 143 && (str.equals("4") || str.equals("3"))) {
                str3 = "car_8F_4_1_8";
            } else if (i == 180 && str.equals(DEFAULT_ZONE)) {
                str3 = "car_B4_0_1_8";
            }
        }
        currentCarMode = str3;
        return str3;
    }

    public static String getCurrentCarMode() {
        return currentCarMode;
    }

    public static void setCurrentCarMode(String str) {
        currentCarMode = str;
    }
}
