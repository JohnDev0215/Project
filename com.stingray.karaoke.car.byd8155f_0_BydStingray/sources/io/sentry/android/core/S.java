package io.sentry.android.core;

import android.telephony.PhoneStateListener;
import io.sentry.C1457d;
import io.sentry.EnumC1495p1;

/* JADX INFO: loaded from: classes.dex */
public final class S extends PhoneStateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.sentry.E f16093a = io.sentry.E.f15834a;

    @Override // android.telephony.PhoneStateListener
    public final void onCallStateChanged(int i7, String str) {
        if (i7 == 1) {
            C1457d c1457d = new C1457d();
            c1457d.o = "system";
            c1457d.f16515q = "device.event";
            c1457d.c("CALL_STATE_RINGING", "action");
            c1457d.f16513n = "Device ringing";
            c1457d.f16517s = EnumC1495p1.INFO;
            this.f16093a.e(c1457d);
        }
    }
}
