package com.byd.vrsettings.utils;

import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.byd.vrdownload.model.LanguageName;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.chad.library.BuildConfig;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
public class PropertiesFreeWakeupUtil {
    private static final String TAG = "NEU_PropertiesFreeWakeupUtil";
    private static Properties properties;

    public static boolean loadLanguage() {
        String string = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "SP_KEY_CUR_LANGUAGE");
        RLog.i(TAG, "loadLanguage: " + string);
        get().clear();
        if (TextUtils.isEmpty(string)) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/en_free_wakeup.properties"));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (string.contains(LanguageName.ENG)) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/en_free_wakeup.properties"));
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        if (string.contains("por-PRT")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/pt_pt_free_wakeup.properties"));
                return true;
            } catch (IOException e3) {
                e3.printStackTrace();
                return false;
            }
        }
        if (string.contains("spa-ESP")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/es_es_free_wakeup.properties"));
                return true;
            } catch (IOException e4) {
                e4.printStackTrace();
                return false;
            }
        }
        if (string.contains(LanguageName.SPA)) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/es_free_wakeup.properties"));
                return true;
            } catch (IOException e5) {
                e5.printStackTrace();
                return false;
            }
        }
        if (string.contains("eng-SGP")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/en_free_wakeup.properties"));
                return true;
            } catch (IOException e6) {
                e6.printStackTrace();
                return false;
            }
        }
        if (string.contains("eng-GBR")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/en_free_wakeup.properties"));
                return true;
            } catch (IOException e7) {
                e7.printStackTrace();
                return false;
            }
        }
        if (string.contains(LanguageName.NOR)) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/no_free_wakeup.properties"));
                return true;
            } catch (IOException e8) {
                e8.printStackTrace();
                return false;
            }
        }
        if (string.contains(LanguageName.FRA)) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/fr_free_wakeup.properties"));
                return true;
            } catch (IOException e9) {
                e9.printStackTrace();
                return false;
            }
        }
        if (string.contains(LanguageName.THA)) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/th_free_wakeup.properties"));
                return true;
            } catch (IOException e10) {
                e10.printStackTrace();
                return false;
            }
        }
        if (string.contains(LanguageName.JPN)) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/jp_free_wakeup.properties"));
                return true;
            } catch (IOException e11) {
                e11.printStackTrace();
                return false;
            }
        }
        if (string.contains("eng-XWW")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/en_free_wakeup.properties"));
                return true;
            } catch (IOException e12) {
                e12.printStackTrace();
                return false;
            }
        }
        if (string.contains("eng-AUS")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/en_free_wakeup.properties"));
                return true;
            } catch (IOException e13) {
                e13.printStackTrace();
                return false;
            }
        }
        if (string.contains(LanguageName.POR)) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/pt_free_wakeup.properties"));
                return true;
            } catch (IOException e14) {
                e14.printStackTrace();
                return false;
            }
        }
        if (string.contains("deu-DEU")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/de_free_wakeup.properties"));
                return true;
            } catch (IOException e15) {
                e15.printStackTrace();
                return false;
            }
        }
        if (string.contains("cmn-CHN")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/zh-cn_free_wakeup.properties"));
                return true;
            } catch (IOException e16) {
                e16.printStackTrace();
                return false;
            }
        }
        if (string.contains("yue-HKG")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/zh-hk_free_wakeup.properties"));
                return true;
            } catch (IOException e17) {
                e17.printStackTrace();
                return false;
            }
        }
        if (string.contains("ita-ITA")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/it_free_wakeup.properties"));
                return true;
            } catch (IOException e18) {
                e18.printStackTrace();
                return false;
            }
        }
        if (string.contains("swe-SWE")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/sv_free_wakeup.properties"));
                return true;
            } catch (IOException e19) {
                e19.printStackTrace();
                return false;
            }
        }
        if (string.contains("tur-TUR")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/tr_free_wakeup.properties"));
                return true;
            } catch (IOException e20) {
                e20.printStackTrace();
                return false;
            }
        }
        if (string.contains("pol-POL")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/pl_free_wakeup.properties"));
                return true;
            } catch (IOException e21) {
                e21.printStackTrace();
                return false;
            }
        }
        if (string.contains("nld-NLD")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/nl_free_wakeup.properties"));
                return true;
            } catch (IOException e22) {
                e22.printStackTrace();
                return false;
            }
        }
        if (string.contains("fin-FIN")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/fi_free_wakeup.properties"));
                return true;
            } catch (IOException e23) {
                e23.printStackTrace();
                return false;
            }
        }
        if (string.contains("heb-ISR")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/iw_free_wakeup.properties"));
                return true;
            } catch (IOException e24) {
                e24.printStackTrace();
                return false;
            }
        }
        if (string.contains("kor-KOR")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/ko_free_wakeup.properties"));
                return true;
            } catch (IOException e25) {
                e25.printStackTrace();
                return false;
            }
        }
        if (string.contains("dan-DNK")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/da_free_wakeup.properties"));
                return true;
            } catch (IOException e26) {
                e26.printStackTrace();
                return false;
            }
        }
        if (string.contains("hun-HUN")) {
            try {
                get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/hu_free_wakeup.properties"));
                return true;
            } catch (IOException e27) {
                e27.printStackTrace();
                return false;
            }
        }
        try {
            get().load(BydVoiceSettingApplication.getContext().getAssets().open("wakeupinfo/en_free_wakeup.properties"));
            return true;
        } catch (IOException e28) {
            e28.printStackTrace();
            return false;
        }
    }

    public static Properties get() {
        if (properties == null) {
            properties = new Properties();
        }
        return properties;
    }

    public static Map<String, String> getPropertyGroup(String str) {
        try {
            RLog.e(TAG, "getPropertyGroup: " + str);
            return GSON.toMap(new String(get().getProperty(str).getBytes("ISO8859-1"), "UTF-8"));
        } catch (Exception e) {
            Log.e(TAG, "getPropertyGroup: " + str + " is not found , exception is " + e.getMessage());
            return new ArrayMap();
        }
    }

    public static String getProperty(String str) {
        try {
            return new String(get().getProperty(str).getBytes("ISO8859-1"), "UTF-8");
        } catch (Exception e) {
            Log.e(TAG, "getProperty: " + str + " is not found , exception is " + e.getMessage());
            return BuildConfig.FLAVOR;
        }
    }
}
