package io.sentry.android.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import io.sentry.C1457d;
import io.sentry.C1523x;
import io.sentry.EnumC1495p1;
import java.nio.charset.Charset;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class c0 extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.sentry.E f16154a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SentryAndroidOptions f16155b;
    public final io.sentry.android.core.internal.util.d c;

    public c0(SentryAndroidOptions sentryAndroidOptions) {
        io.sentry.E e10 = io.sentry.E.f15834a;
        this.c = new io.sentry.android.core.internal.util.d(60000L, 0);
        this.f16154a = e10;
        this.f16155b = sentryAndroidOptions;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, final Intent intent) {
        SentryAndroidOptions sentryAndroidOptions = this.f16155b;
        final String action = intent.getAction();
        final boolean zEquals = "android.intent.action.BATTERY_CHANGED".equals(action);
        if (zEquals && this.c.a()) {
            return;
        }
        final long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            sentryAndroidOptions.getExecutorService().submit(new Runnable() { // from class: io.sentry.android.core.b0
                @Override // java.lang.Runnable
                public final void run() {
                    String strSubstring;
                    int i7;
                    c0 c0Var = this.l;
                    c0Var.getClass();
                    C1457d c1457d = new C1457d(jCurrentTimeMillis);
                    c1457d.o = "system";
                    c1457d.f16515q = "device.event";
                    Charset charset = io.sentry.util.i.f16925a;
                    String str = action;
                    if (str != null) {
                        int iLastIndexOf = str.lastIndexOf(".");
                        strSubstring = (iLastIndexOf < 0 || str.length() <= (i7 = iLastIndexOf + 1)) ? str : str.substring(i7);
                    } else {
                        strSubstring = null;
                    }
                    if (strSubstring != null) {
                        c1457d.c(strSubstring, "action");
                    }
                    Intent intent2 = intent;
                    boolean z6 = zEquals;
                    SentryAndroidOptions sentryAndroidOptions2 = c0Var.f16155b;
                    if (z6) {
                        Float fB = F.b(intent2, sentryAndroidOptions2);
                        if (fB != null) {
                            c1457d.c(fB, "level");
                        }
                        Boolean boolC = F.c(intent2, sentryAndroidOptions2);
                        if (boolC != null) {
                            c1457d.c(boolC, "charging");
                        }
                    } else {
                        Bundle extras = intent2.getExtras();
                        HashMap map = new HashMap();
                        if (extras != null && !extras.isEmpty()) {
                            for (String str2 : extras.keySet()) {
                                try {
                                    Object obj = extras.get(str2);
                                    if (obj != null) {
                                        map.put(str2, obj.toString());
                                    }
                                } catch (Throwable th) {
                                    sentryAndroidOptions2.getLogger().p(EnumC1495p1.ERROR, th, "%s key of the %s action threw an error.", str2, str);
                                }
                            }
                            c1457d.c(map, "extras");
                        }
                    }
                    c1457d.f16517s = EnumC1495p1.INFO;
                    C1523x c1523x = new C1523x();
                    c1523x.c(intent2, "android:intent");
                    c0Var.f16154a.k(c1457d, c1523x);
                }
            });
        } catch (Throwable th) {
            sentryAndroidOptions.getLogger().p(EnumC1495p1.ERROR, th, "Failed to submit system event breadcrumb action.", new Object[0]);
        }
    }
}
