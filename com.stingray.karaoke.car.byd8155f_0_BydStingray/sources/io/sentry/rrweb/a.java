package io.sentry.rrweb;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.EnumC1495p1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a extends b implements InterfaceC1485m0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16827n;
    public double o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f16828p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f16829q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f16830r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public EnumC1495p1 f16831s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ConcurrentHashMap f16832t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public HashMap f16833u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ConcurrentHashMap f16834v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public ConcurrentHashMap f16835w;

    public a() {
        super(c.Custom);
        this.f16827n = "breadcrumb";
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("type");
        c1489n1.I(k10, this.l);
        c1489n1.w("timestamp");
        c1489n1.H(this.f16836m);
        c1489n1.w("data");
        c1489n1.i();
        c1489n1.w("tag");
        c1489n1.L(this.f16827n);
        c1489n1.w("payload");
        c1489n1.i();
        if (this.f16828p != null) {
            c1489n1.w("type");
            c1489n1.L(this.f16828p);
        }
        c1489n1.w("timestamp");
        c1489n1.I(k10, BigDecimal.valueOf(this.o));
        if (this.f16829q != null) {
            c1489n1.w("category");
            c1489n1.L(this.f16829q);
        }
        if (this.f16830r != null) {
            c1489n1.w("message");
            c1489n1.L(this.f16830r);
        }
        if (this.f16831s != null) {
            c1489n1.w("level");
            c1489n1.I(k10, this.f16831s);
        }
        if (this.f16832t != null) {
            c1489n1.w("data");
            c1489n1.I(k10, this.f16832t);
        }
        ConcurrentHashMap concurrentHashMap = this.f16834v;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16834v, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
        ConcurrentHashMap concurrentHashMap2 = this.f16835w;
        if (concurrentHashMap2 != null) {
            for (String str2 : concurrentHashMap2.keySet()) {
                AbstractC1347d.t(this.f16835w, str2, c1489n1, str2, k10);
            }
        }
        c1489n1.n();
        HashMap map = this.f16833u;
        if (map != null) {
            for (String str3 : map.keySet()) {
                AbstractC1347d.s(this.f16833u, str3, c1489n1, str3, k10);
            }
        }
        c1489n1.n();
    }
}
