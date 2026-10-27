package com.amazon.identity.auth.device;

import android.content.Context;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class b7 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f240a;
    public static ce b;

    public static void a(String str) {
        a();
        String[] strArr = {e.d(str)};
        ce ceVar = b;
        if (ceVar == null) {
            return;
        }
        ceVar.a("GenericMetrics_WebserviceCall", "NetworkFailure", strArr);
    }

    public static synchronized void a() {
        Context context = f240a;
        if (context != null && b == null) {
            b = yd.a(context);
        }
    }
}
