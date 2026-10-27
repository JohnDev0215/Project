package com.byd.engineermode.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.byd.engineermode.DiagnoseApplication;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: compiled from: BydSPHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static b f2500a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SharedPreferences f2501b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SharedPreferences.Editor f2502c;

    private b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("BydSPHelper", 0);
        this.f2501b = sharedPreferences;
        this.f2502c = sharedPreferences.edit();
    }

    public static b a() {
        if (f2500a == null) {
            f2500a = new b(DiagnoseApplication.e());
        }
        return f2500a;
    }

    private synchronized String c(String str, String str2) {
        Thread.currentThread().getStackTrace();
        String string = this.f2501b.getString(str, "");
        String str3 = new SimpleDateFormat("yyyy-MM-dd:HHmmss").format(new Date(System.currentTimeMillis()));
        if (TextUtils.isEmpty(str2)) {
            return str3 + "#@$" + string;
        }
        return str3 + "->" + str2 + "#@$" + string;
    }

    public String b(String str) {
        Log.d("BydSPHelper", "Key = " + str);
        return this.f2501b.getString(str, "null");
    }

    public synchronized void d(String str) {
        Log.d("BydSPHelper", "saveEmptyMessage Key = " + str);
        this.f2502c.putString(str, "");
        this.f2502c.apply();
    }

    public synchronized void e(String str) {
        f(str, null);
    }

    public synchronized void f(String str, String str2) {
        Log.d("BydSPHelper", "Key = " + str + ", getValue(message) = " + c(str, str2));
        this.f2502c.putString(str, c(str, str2));
        this.f2502c.apply();
    }
}
