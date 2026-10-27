package com.qtxa.qtxadefend;

import android.app.ActivityThread;
import android.content.Context;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class Utils {
    static Map<String, Object> tags = new HashMap();
    static int MAX_LINES = 30;

    public static String getAppName(String str) {
        return getAppName(ActivityThread.currentApplication(), str);
    }

    public static long getLastEntryTimestamp(Context context) {
        long j = PreferenceManager.getDefaultSharedPreferences(context).getLong(preferenceKey(), 0L);
        return j >= System.currentTimeMillis() ? System.currentTimeMillis() : j == 0 ? System.currentTimeMillis() : j;
    }

    public static long getMaxDelayTimes() {
        return 5000L;
    }

    public static long getSelinuxTimestamp(Context context) {
        long j = PreferenceManager.getDefaultSharedPreferences(context).getLong(selinuxPreferenceKey(), 0L);
        return j > System.currentTimeMillis() ? System.currentTimeMillis() : j;
    }

    public static int getVersionCode(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return 0;
            }
            return context.getPackageManager().getPackageInfo(str.split(":")[0], 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getVersionName(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return "null";
        }
        try {
            return context.getPackageManager().getPackageInfo(str.split(":")[0], 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }

    public static String gettype(String str, String str2) {
        int iIndexOf;
        BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
        String line = "";
        do {
            try {
                line = bufferedReader.readLine();
                if (line == null) {
                    return "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            iIndexOf = line.indexOf(str2);
        } while (iIndexOf <= -1);
        return line.substring(iIndexOf + str2.length());
    }

    public static String[] mySplit(String str, char c) {
        String[] strArr = new String[30];
        int i = 0;
        int i2 = 0;
        while (true) {
            try {
                int iIndexOf = str.indexOf(c, i);
                if (iIndexOf == -1) {
                    strArr[i2] = str.substring(i);
                    return strArr;
                }
                String strSubstring = str.substring(i, iIndexOf);
                if (strSubstring.length() > 0) {
                    strArr[i2] = strSubstring;
                    i2++;
                }
                i = iIndexOf + 1;
            } catch (Exception e) {
                w.d("mySplit_char:" + e.toString());
                return strArr;
            }
        }
    }

    public static String[] mySplit1(String str, String str2) {
        String[] strArr = new String[30];
        int i = 0;
        int i2 = 0;
        while (true) {
            try {
                int iIndexOf = str.indexOf(str2, i);
                if (iIndexOf == -1) {
                    strArr[i2] = str.substring(i);
                    return strArr;
                }
                String strSubstring = str.substring(i, iIndexOf);
                if (strSubstring.length() > 0) {
                    strArr[i2] = strSubstring;
                    i2++;
                }
                i = iIndexOf + 1;
            } catch (Exception e) {
                w.d("mySplit_String:" + e.toString());
                return strArr;
            }
        }
    }

    private static String preferenceKey() {
        return "last_entry_timestamp";
    }

    public static String readText(String str) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new FileReader(str));
        } catch (FileNotFoundException | IOException unused) {
        }
        try {
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
            }
            bufferedReader.close();
            String string = sb.toString();
            if (string.isEmpty()) {
                return null;
            }
            return string.trim();
        } catch (Throwable th) {
            bufferedReader.close();
            throw th;
        }
    }

    private static String selinuxPreferenceKey() {
        return "last_selinux_timestamp";
    }

    public static void setLastEntryTimestamp(Context context, long j) {
        if (j == 0) {
            return;
        }
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(preferenceKey(), j).commit();
        } catch (Throwable th) {
            w.d("setLastEntryTimestamp:" + th.toString());
        }
    }

    public static void setSelinuxTimestamp(Context context, long j) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(selinuxPreferenceKey(), j).apply();
    }

    public static String getAppName(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "null";
            }
            String str2 = str.split(":")[0];
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(str2, 0).applicationInfo.loadLabel(packageManager).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }

    public static String getVersionCode(String str) {
        return getVersionCode(ActivityThread.currentApplication(), str) + "";
    }

    public static String getVersionName(String str) {
        return getVersionName(ActivityThread.currentApplication(), str) + "";
    }
}
