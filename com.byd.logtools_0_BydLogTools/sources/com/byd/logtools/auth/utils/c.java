package com.byd.logtools.auth.utils;

import android.content.Context;

/* JADX INFO: compiled from: CountryCodeManger.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile c f3146a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3147b = 47;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3148c;

    private c(Context context) {
        this.f3148c = context;
    }

    public static c b(Context context) {
        if (f3146a == null) {
            synchronized (c.class) {
                if (f3146a == null) {
                    f3146a = new c(context);
                }
            }
        }
        return f3146a;
    }

    public int a() {
        return this.f3147b;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0040 A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String c() {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.logtools.auth.utils.c.c():java.lang.String");
    }

    public void d() {
        String strA = com.byd.bydcommonlib.a.a(this.f3148c);
        if (strA == null || strA.isEmpty()) {
            return;
        }
        this.f3147b = Integer.parseInt(strA);
    }
}
