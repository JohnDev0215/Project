package io.sentry;

import h2.AbstractC1347d;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: io.sentry.j1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1477j1 implements InterfaceC1485m0 {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f16584m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final EnumC1492o1 f16585n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Callable f16586p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final String f16587q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public HashMap f16588r;

    public C1477j1(EnumC1492o1 enumC1492o1, int i7, String str, String str2, String str3) {
        this.f16585n = enumC1492o1;
        this.l = str;
        this.o = i7;
        this.f16584m = str2;
        this.f16586p = null;
        this.f16587q = str3;
    }

    public final int a() {
        Callable callable = this.f16586p;
        if (callable == null) {
            return this.o;
        }
        try {
            return ((Integer) callable.call()).intValue();
        } catch (Throwable unused) {
            return -1;
        }
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        String str = this.l;
        if (str != null) {
            c1489n1.w("content_type");
            c1489n1.L(str);
        }
        String str2 = this.f16584m;
        if (str2 != null) {
            c1489n1.w("filename");
            c1489n1.L(str2);
        }
        c1489n1.w("type");
        c1489n1.I(k10, this.f16585n);
        String str3 = this.f16587q;
        if (str3 != null) {
            c1489n1.w("attachment_type");
            c1489n1.L(str3);
        }
        c1489n1.w("length");
        c1489n1.H(a());
        HashMap map = this.f16588r;
        if (map != null) {
            for (String str4 : map.keySet()) {
                AbstractC1347d.s(this.f16588r, str4, c1489n1, str4, k10);
            }
        }
        c1489n1.n();
    }

    public C1477j1(EnumC1492o1 enumC1492o1, Callable callable, String str, String str2, String str3) {
        h6.e.q(enumC1492o1, "type is required");
        this.f16585n = enumC1492o1;
        this.l = str;
        this.o = -1;
        this.f16584m = str2;
        this.f16586p = callable;
        this.f16587q = str3;
    }
}
