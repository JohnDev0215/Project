package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class p implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ConcurrentHashMap f16759m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Integer f16760n;
    public Long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f16761p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ConcurrentHashMap f16762q;

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("cookies");
            c1489n1.L(this.l);
        }
        if (this.f16759m != null) {
            c1489n1.w("headers");
            c1489n1.I(k10, this.f16759m);
        }
        if (this.f16760n != null) {
            c1489n1.w("status_code");
            c1489n1.I(k10, this.f16760n);
        }
        if (this.o != null) {
            c1489n1.w("body_size");
            c1489n1.I(k10, this.o);
        }
        if (this.f16761p != null) {
            c1489n1.w("data");
            c1489n1.I(k10, this.f16761p);
        }
        ConcurrentHashMap concurrentHashMap = this.f16762q;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16762q, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
