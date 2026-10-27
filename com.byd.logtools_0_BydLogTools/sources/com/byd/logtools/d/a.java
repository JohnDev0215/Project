package com.byd.logtools.d;

import android.content.SharedPreferences;
import com.byd.logtools.application.BydLogApplication;

/* JADX INFO: compiled from: BydSharePreferences.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final a f3158a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static SharedPreferences f3159b;

    private a() {
        if (f3159b == null) {
            f3159b = BydLogApplication.f3103b.getSharedPreferences("BydSharePreferences", 0);
        }
    }

    public static a b() {
        return f3158a;
    }

    public synchronized boolean a(String str, boolean z) {
        return f3159b.getBoolean(str, z);
    }

    public synchronized long c(String str, long j) {
        return f3159b.getLong(str, j);
    }

    public synchronized void d(String str, boolean z) {
        SharedPreferences.Editor editorEdit = f3159b.edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }

    public synchronized void e(String str, long j) {
        SharedPreferences.Editor editorEdit = f3159b.edit();
        editorEdit.putLong(str, j);
        editorEdit.apply();
    }
}
