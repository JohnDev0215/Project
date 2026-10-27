package com.byd.vrassistant.hint;

import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import cerence.ark.assistant.framework.data.Language;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
public class PropertiesUtil {
    private static final Map<String, String> LANGUAGE_PATH;
    private static final String TAG = "NEU_PropertiesUtil";
    private static final Properties configProperties;
    private static final Properties valueProperties = new Properties();

    static {
        Properties properties = new Properties();
        configProperties = properties;
        LANGUAGE_PATH = new HashMap<String, String>() { // from class: com.byd.vrassistant.hint.PropertiesUtil.1
            {
                put("eng", "language/en.properties");
                put(Language.POR_PRT, "language/pt-pt.properties");
                put(Language.SPA_ESP, "language/es-es.properties");
                put("spa-XLA", "language/es.properties");
                put("nor-NOR", "language/no.properties");
                put("fra-FRA", "language/fr.properties");
                put("tha-THA", "language/th.properties");
                put("jpn-JPN", "language/jp.properties");
                put("por-BRA", "language/pt.properties");
                put(Language.DEU_DEU, "language/de.properties");
                put(Language.CMN_CHN, "language/zh-cn.properties");
                put(Language.YUE_HKG, "language/zh-hk.properties");
                put(Language.ITA_ITA, "language/it.properties");
                put(Language.SWE_SWE, "language/sv.properties");
                put("tur-TUR", "language/tr.properties");
                put(Language.POL_POL, "language/pl.properties");
                put(Language.NLD_NLD, "language/nl.properties");
                put(Language.FIN_FIN, "language/fi.properties");
                put(Language.HEB_ISR, "language/iw.properties");
                put(Language.KOR_KOR, "language/ko.properties");
                put(Language.DAN_DNK, "language/da.properties");
                put(Language.HUN_HUN, "language/hu.properties");
            }
        };
        try {
            properties.load(AppUtils.getApp().getAssets().open("language/config.properties"));
        } catch (Exception e) {
            Log.e(TAG, "loadConfig: error= " + e.getMessage());
        }
    }

    public static Properties getValueProperties() {
        return valueProperties;
    }

    public static boolean loadLanguage() {
        String string = Settings.System.getString(AppUtils.getApp().getContentResolver(), "SP_KEY_CUR_LANGUAGE");
        String fileToLoad = getFileToLoad(string);
        Log.i(TAG, "loadLanguage: " + string + ",file = " + fileToLoad);
        try {
            Properties properties = valueProperties;
            properties.clear();
            properties.load(AppUtils.getApp().getAssets().open(fileToLoad));
            return true;
        } catch (IOException e) {
            Log.e(TAG, "loadLanguage: error= " + e.getMessage());
            return false;
        }
    }

    public static boolean loadSpecificLanguage(String str) {
        String fileToLoad = getFileToLoad(str);
        Log.i(TAG, "loadLanguage: " + str + ",file = " + fileToLoad);
        try {
            valueProperties.load(AppUtils.getApp().getAssets().open(fileToLoad));
            return true;
        } catch (IOException e) {
            Log.e(TAG, "loadLanguage: error= " + e.getMessage());
            return false;
        }
    }

    public static String getFileToLoad(String str) {
        for (Map.Entry<String, String> entry : LANGUAGE_PATH.entrySet()) {
            if (str != null && str.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "language/en.properties";
    }

    public static Map<String, String> getConfigPropertyGroup(String str) {
        try {
            String property = configProperties.getProperty(str);
            if (property != null && !property.isEmpty()) {
                return GSON.toMap(new String(property.getBytes("ISO8859-1"), StandardCharsets.UTF_8));
            }
            return new ArrayMap();
        } catch (Exception e) {
            Log.e(TAG, "getConfigPropertyGroup: " + str + " is not found ,exception is " + e.getMessage());
            return new ArrayMap();
        }
    }

    public static String getConfigProperty(String str) {
        try {
            String property = configProperties.getProperty(str);
            if (property != null && !property.isEmpty()) {
                return new String(property.getBytes("ISO8859-1"), StandardCharsets.UTF_8);
            }
            return "";
        } catch (Exception e) {
            Log.e(TAG, "getConfigProperty: " + str + " is not found ,exception is " + e.getMessage());
            return "";
        }
    }

    public static Map<String, String> getPropertyGroup(String str) {
        try {
            return GSON.toMap(new String(valueProperties.getProperty(str).getBytes("ISO8859-1"), StandardCharsets.UTF_8));
        } catch (Exception e) {
            Log.e(TAG, "getPropertyGroup: " + str + " is not found ,exception is " + e.getMessage());
            return new ArrayMap();
        }
    }

    public static String getProperty(String str) {
        try {
            return new String(valueProperties.getProperty(str).getBytes("ISO8859-1"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            Log.e(TAG, "getProperty: " + str + " is not found , exception is " + e.getMessage());
            return "";
        }
    }

    public static boolean IfCurrentLanguageIsEng() {
        String string = Settings.System.getString(AppUtils.getApp().getContentResolver(), "SP_KEY_CUR_LANGUAGE");
        Log.i(TAG, "IfCurrentLanguageIsEng: " + string);
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return string.contains("eng-USA") || string.contains(Language.ENG_SGP) || string.contains(Language.ENG_GBR) || string.contains(Language.ENG_XWW) || string.contains(Language.ENG_AUS) || string.contains("por-BRA") || string.contains(Language.CMN_CHN);
    }

    public static String getDomainName(String str) {
        try {
            return new String(valueProperties.getProperty(str).getBytes("ISO8859-1"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            Log.e(TAG, "getProperty: " + str + " is not found , exception is " + e.getMessage());
            return "";
        }
    }

    public static String getGroupName(String str) {
        try {
            return new String(valueProperties.getProperty(str).getBytes("ISO8859-1"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            Log.e(TAG, "getProperty: " + str + " is not found , exception is " + e.getMessage());
            return "";
        }
    }
}
