package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import com.android.volley.BuildConfig;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: SPUtils.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ApplySharedPref"})
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Map<String, r> f2616a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SharedPreferences f2617b;

    private r(String str, int i) {
        this.f2617b = w.a().getSharedPreferences(str, i);
    }

    public static r b(String str) {
        return c(str, 0);
    }

    public static r c(String str, int i) {
        if (g(str)) {
            str = "spUtils";
        }
        Map<String, r> map = f2616a;
        r rVar = map.get(str);
        if (rVar == null) {
            synchronized (r.class) {
                rVar = map.get(str);
                if (rVar == null) {
                    rVar = new r(str, i);
                    map.put(str, rVar);
                }
            }
        }
        return rVar;
    }

    private static boolean g(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean a(String str) {
        return this.f2617b.contains(str);
    }

    public int d(String str, int i) {
        return this.f2617b.getInt(str, i);
    }

    public String e(String str) {
        return f(str, BuildConfig.FLAVOR);
    }

    public String f(String str, String str2) {
        return this.f2617b.getString(str, str2);
    }

    public void h(String str, String str2) {
        i(str, str2, false);
    }

    public void i(String str, String str2, boolean z) {
        if (z) {
            this.f2617b.edit().putString(str, str2).commit();
        } else {
            this.f2617b.edit().putString(str, str2).apply();
        }
    }

    public void j(String str) {
        k(str, false);
    }

    public void k(String str, boolean z) {
        if (z) {
            this.f2617b.edit().remove(str).commit();
        } else {
            this.f2617b.edit().remove(str).apply();
        }
    }
}
