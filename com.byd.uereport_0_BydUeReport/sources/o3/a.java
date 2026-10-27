package o3;

import android.content.Context;
import android.content.SharedPreferences;

/* JADX INFO: compiled from: SharedPreferencesUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static SharedPreferences f6871a;

    public static int a(Context context, String str, int i8) {
        if (f6871a == null) {
            f6871a = context.getSharedPreferences("digital_key", 0);
        }
        return f6871a.getInt(str, i8);
    }

    public static String b(Context context, String str, String str2) {
        if (f6871a == null) {
            f6871a = context.getSharedPreferences("digital_key", 0);
        }
        return f6871a.getString(str, str2);
    }

    public static void c(Context context, String str, int i8) {
        if (f6871a == null) {
            f6871a = context.getSharedPreferences("digital_key", 0);
        }
        f6871a.edit().putInt(str, i8).commit();
    }

    public static void d(Context context, String str, String str2) {
        if (f6871a == null) {
            f6871a = context.getSharedPreferences("digital_key", 0);
        }
        f6871a.edit().putString(str, str2).commit();
    }
}
