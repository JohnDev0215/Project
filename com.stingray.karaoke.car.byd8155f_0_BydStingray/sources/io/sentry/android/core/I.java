package io.sentry.android.core;

import android.os.FileObserver;
import h8.AbstractC1387d;
import io.sentry.C1523x;
import io.sentry.D0;
import io.sentry.EnumC1495p1;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class I extends FileObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f16059a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final D0 f16060b;
    public final io.sentry.K c;
    public final long d;

    public I(String str, D0 d02, io.sentry.K k10, long j3) {
        super(str);
        this.f16059a = str;
        this.f16060b = d02;
        h6.e.q(k10, "Logger is required.");
        this.c = k10;
        this.d = j3;
    }

    @Override // android.os.FileObserver
    public final void onEvent(int i7, String str) {
        if (str == null || i7 != 8) {
            return;
        }
        EnumC1495p1 enumC1495p1 = EnumC1495p1.DEBUG;
        Integer numValueOf = Integer.valueOf(i7);
        String str2 = this.f16059a;
        io.sentry.K k10 = this.c;
        k10.k(enumC1495p1, "onEvent fired for EnvelopeFileObserver with event type %d on path: %s for file %s.", numValueOf, str2, str);
        C1523x c1523xC = AbstractC1387d.c(new H(this.d, k10));
        String strL = android.hardware.bydauto.ac.tsimpl.a.l(M5.b.i(str2), File.separator, str);
        D0 d02 = this.f16060b;
        d02.getClass();
        h6.e.q(strL, "Path is required.");
        d02.b(new File(strL), c1523xC);
    }
}
