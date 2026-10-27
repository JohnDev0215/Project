package r4;

import android.content.Context;
import android.hardware.bydauto.properties.CarSettings;

/* JADX INFO: compiled from: SettingsUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {
    public static boolean a(Context context, String str) {
        return context.getSharedPreferences(CarSettings.UserTableData.TABLE_NAME_SETTINGS, 0).contains(str);
    }

    public static boolean b(Context context, String str) {
        return context.getSharedPreferences(CarSettings.UserTableData.TABLE_NAME_SETTINGS, 0).getBoolean(str, false);
    }

    public static boolean c(Context context, String str, boolean z7) {
        return context.getSharedPreferences(CarSettings.UserTableData.TABLE_NAME_SETTINGS, 0).getBoolean(str, z7);
    }

    public static long d(Context context, String str) {
        return e(context, str, 0L);
    }

    public static long e(Context context, String str, long j8) {
        return context.getSharedPreferences(CarSettings.UserTableData.TABLE_NAME_SETTINGS, 0).getLong(str, j8);
    }

    public static String f(Context context, String str) {
        return context.getSharedPreferences(CarSettings.UserTableData.TABLE_NAME_SETTINGS, 0).getString(str, null);
    }

    public static void g(Context context, String str, boolean z7) {
        context.getSharedPreferences(CarSettings.UserTableData.TABLE_NAME_SETTINGS, 0).edit().putBoolean(str, z7).apply();
    }

    public static void h(Context context, String str, long j8) {
        context.getSharedPreferences(CarSettings.UserTableData.TABLE_NAME_SETTINGS, 0).edit().putLong(str, j8).apply();
    }

    public static void i(Context context, String str, String str2) {
        context.getSharedPreferences(CarSettings.UserTableData.TABLE_NAME_SETTINGS, 0).edit().putString(str, str2).apply();
    }
}
