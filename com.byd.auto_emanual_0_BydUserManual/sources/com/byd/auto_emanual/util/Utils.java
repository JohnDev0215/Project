package com.byd.auto_emanual.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.byd.auto_emanual.MApp;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.fragment.ManualFragment;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.shiro.config.Ini;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    private static final String DEBUG_NOT_SHOW_VIDEO = "/storage/emulated/0/Pictures/debug_not_show_video";
    public static String NORWAY_CODE = "47";
    private static final String NOT_SHOW_CONNECTOR = "_";
    private static final String TAG = "Utils";

    public static boolean is6125F() {
        return false;
    }

    public static boolean is8155F() {
        return true;
    }

    private static boolean notShow(Context context, int resId) {
        return true;
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            MLog.e(TAG, "getVersionName NameNotFoundException", e);
            return context.getString(R.string.default_version_name);
        }
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static int getApplicationUid(String packageName) {
        try {
            return MApp.context.getPackageManager().getApplicationInfo(packageName, 0).uid;
        } catch (PackageManager.NameNotFoundException e) {
            MLog.e(TAG, "getApplicationUid NameNotFoundException: " + e);
            return -1;
        }
    }

    public static boolean isNumeric(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static String filterKeyword(CharSequence s) {
        String strValueOf = String.valueOf(s);
        if ("%".equals(strValueOf)) {
            strValueOf = "/%";
        } else if (NOT_SHOW_CONNECTOR.equals(strValueOf)) {
            strValueOf = "/_";
        }
        return strValueOf.trim();
    }

    public static boolean notShowVideo(Context context) {
        MLog.d(TAG, "notShowVideo = true");
        return true;
    }

    public static boolean notShowFeedback(Context context) {
        return notShow(context, R.array.not_show_feedback);
    }

    public static boolean notShowUpdate(Context context) {
        return notShow(context, R.array.not_show_update);
    }

    private static boolean show(Context context, int resId) {
        String autoType;
        String[] stringArray = context.getResources().getStringArray(resId);
        if (stringArray != null) {
            autoType = AutoInfoUtil.getAutoType(context);
            for (String str : stringArray) {
                if (str.contains(NOT_SHOW_CONNECTOR)) {
                    if (str.split(NOT_SHOW_CONNECTOR)[0].equals(autoType)) {
                        break;
                    }
                } else {
                    if (str.equals(autoType)) {
                        break;
                    }
                }
            }
            autoType = null;
        } else {
            autoType = null;
        }
        if (TextUtils.isEmpty(autoType)) {
            return false;
        }
        if ("114".equals(autoType)) {
            return !NORWAY_CODE.equals(AutoInfoUtil.getCountryCode());
        }
        return true;
    }

    public static String getStandardLanguageCode() {
        String currentLanguage = getCurrentLanguage();
        int identifier = MApp.context.getResources().getIdentifier(currentLanguage, "string", MApp.context.getPackageName());
        if (identifier != 0) {
            String string = MApp.context.getString(identifier);
            MLog.i(TAG, "getStandardLanguageCode " + currentLanguage + " -> " + string);
            return string;
        }
        MLog.i(TAG, "getStandardLanguageCode no need to transform : " + currentLanguage);
        return currentLanguage;
    }

    public static String getCurrentLanguage() {
        return MApp.context.getResources().getConfiguration().getLocales().get(0).toString().replace(Ini.COMMENT_POUND, "-");
    }

    public static String getCurrentLanguageDisplay() {
        return MApp.context.getResources().getConfiguration().getLocales().get(0).getDisplayLanguage();
    }

    public static String getResourceLanguage() {
        String strLoad = SharedPrefUtil.load(MApp.context, ManualFragment.zipRequireDownloadResourceLanguage());
        String strLoad2 = SharedPrefUtil.load(MApp.context, ManualFragment.zipRequireDownloadMultiResourceLanguage());
        if (!TextUtils.isEmpty(strLoad2)) {
            strLoad = strLoad2;
        }
        MLog.i(TAG, "getResourceLanguage " + strLoad);
        return strLoad;
    }

    public static String getResourceLanguageDisplay() {
        if (TextUtils.isEmpty(SharedPrefUtil.load(MApp.context, ManualFragment.zipRequireDownloadSizeKey()))) {
            MLog.i(TAG, "sizeString is empty.");
            return "";
        }
        String strLoad = SharedPrefUtil.load(MApp.context, ManualFragment.zipRequireDownloadResourceLanguage());
        String strLoad2 = SharedPrefUtil.load(MApp.context, ManualFragment.zipRequireDownloadMultiResourceLanguage());
        String str = TAG;
        MLog.i(str, "getResourceLanguageDisplay " + strLoad + ", " + strLoad2);
        if (TextUtils.isEmpty(strLoad) && TextUtils.isEmpty(strLoad2)) {
            MLog.i(str, "resourceLanguage is empty.");
            return "";
        }
        String resourceLanguageDisplayImpl = TextUtils.isEmpty(strLoad) ? "" : getResourceLanguageDisplayImpl(strLoad);
        if (TextUtils.isEmpty(strLoad2)) {
            return resourceLanguageDisplayImpl;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : strLoad2.split(",")) {
            sb.append(getResourceLanguageDisplayImpl(str2)).append(",").append(" ");
        }
        return sb.length() > 1 ? sb.subSequence(0, sb.length() - 2).toString() : resourceLanguageDisplayImpl;
    }

    private static String getResourceLanguageDisplayImpl(String resourceLanguage) {
        if ("en".equals(resourceLanguage)) {
            resourceLanguage = resourceLanguage + "_US";
        }
        if (resourceLanguage.length() < 5) {
            MLog.i(TAG, "resourceLanguage.length() < 5.");
            return "";
        }
        String strSubstring = resourceLanguage.substring(0, 5);
        int iIndexOf = strSubstring.indexOf(NOT_SHOW_CONNECTOR);
        if (iIndexOf < 0) {
            MLog.i(TAG, "do not have _.");
            return "";
        }
        String str = "language_" + resourceLanguage;
        if ("zh_CN_-Hans".equals(resourceLanguage)) {
            str = "language_zh_CN";
        }
        if ("zh_TW".equals(resourceLanguage)) {
            str = "traditional";
        }
        int identifier = MApp.context.getResources().getIdentifier(str, "string", MApp.context.getPackageName());
        if (identifier != 0) {
            String string = MApp.context.getString(identifier);
            MLog.i(TAG, "1 getResourceLanguageDisplayImpl " + resourceLanguage + ", " + string);
            return string;
        }
        String str2 = TAG;
        MLog.i(str2, "resId = 0!!! stringId = " + str);
        String strSubstring2 = strSubstring.substring(0, iIndexOf);
        String strSubstring3 = strSubstring.substring(iIndexOf + 1);
        MLog.i(str2, strSubstring2 + strSubstring3);
        String displayName = new Locale(strSubstring2, "", "").getDisplayName();
        if (Locale.TRADITIONAL_CHINESE.getCountry().equals(strSubstring3)) {
            displayName = displayName + "(" + MApp.context.getString(R.string.traditional) + ")";
        }
        MLog.i(str2, "2 getResourceLanguageDisplayImpl " + resourceLanguage + ", " + displayName);
        return displayName;
    }

    public static String getProperty(String key, String defaultValue) {
        try {
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                String str = (String) cls.getMethod("get", String.class, String.class).invoke(cls, key, defaultValue);
                MLog.d(TAG, "getProperty " + key + " : " + str);
                return str;
            } catch (Exception e) {
                String str2 = TAG;
                MLog.d(str2, "getProperty " + key + " exception. ", e);
                MLog.d(str2, "getProperty " + key + " : " + defaultValue);
                return defaultValue;
            }
        } catch (Throwable th) {
            MLog.d(TAG, "getProperty " + key + " : " + defaultValue);
            throw th;
        }
    }
}
