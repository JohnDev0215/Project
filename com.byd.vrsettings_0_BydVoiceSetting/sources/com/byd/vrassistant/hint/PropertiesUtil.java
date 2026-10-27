package com.byd.vrassistant.hint;

import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.byd.vrdownload.model.LanguageName;
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
                put("por-PRT", "language/pt-pt.properties");
                put("spa-ESP", "language/es-es.properties");
                put(LanguageName.SPA, "language/es.properties");
                put(LanguageName.NOR, "language/no.properties");
                put(LanguageName.FRA, "language/fr.properties");
                put(LanguageName.THA, "language/th.properties");
                put(LanguageName.JPN, "language/jp.properties");
                put(LanguageName.POR, "language/pt.properties");
                put("deu-DEU", "language/de.properties");
                put("cmn-CHN", "language/zh-cn.properties");
                put("yue-HKG", "language/zh-hk.properties");
                put("ita-ITA", "language/it.properties");
                put("swe-SWE", "language/sv.properties");
                put("tur-TUR", "language/tr.properties");
                put("pol-POL", "language/pl.properties");
                put("nld-NLD", "language/nl.properties");
                put("fin-FIN", "language/fi.properties");
                put("heb-ISR", "language/iw.properties");
                put("kor-KOR", "language/ko.properties");
                put("dan-DNK", "language/da.properties");
                put("hun-HUN", "language/hu.properties");
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
            return com.chad.library.BuildConfig.FLAVOR;
        } catch (Exception e) {
            Log.e(TAG, "getConfigProperty: " + str + " is not found ,exception is " + e.getMessage());
            return com.chad.library.BuildConfig.FLAVOR;
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
            return com.chad.library.BuildConfig.FLAVOR;
        }
    }

    public static boolean IfCurrentLanguageIsEng() {
        String string = Settings.System.getString(AppUtils.getApp().getContentResolver(), "SP_KEY_CUR_LANGUAGE");
        Log.i(TAG, "IfCurrentLanguageIsEng: " + string);
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return string.contains(LanguageName.ENG) || string.contains("eng-SGP") || string.contains("eng-GBR") || string.contains("eng-XWW") || string.contains("eng-AUS") || string.contains(LanguageName.POR) || string.contains("cmn-CHN");
    }

    public static String getDomainName(String str) {
        try {
            return new String(valueProperties.getProperty(str).getBytes("ISO8859-1"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            Log.e(TAG, "getProperty: " + str + " is not found , exception is " + e.getMessage());
            return com.chad.library.BuildConfig.FLAVOR;
        }
    }

    public static String getGroupName(String str) {
        try {
            return new String(valueProperties.getProperty(str).getBytes("ISO8859-1"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            Log.e(TAG, "getProperty: " + str + " is not found , exception is " + e.getMessage());
            return com.chad.library.BuildConfig.FLAVOR;
        }
    }
}
