package com.byd.logtools.d;

import android.content.Context;

/* JADX INFO: compiled from: ConfigrationManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static b f3160a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f3161b;

    private b(Context context) {
        this.f3161b = context;
    }

    public static void a(Context context) {
        f3160a = new b(context);
    }
}
