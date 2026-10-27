package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class j implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16733m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16734n;
    public Boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ConcurrentHashMap f16735p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public ConcurrentHashMap f16736q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Boolean f16737r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public HashMap f16738s;

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("type");
            c1489n1.L(this.l);
        }
        if (this.f16733m != null) {
            c1489n1.w("description");
            c1489n1.L(this.f16733m);
        }
        if (this.f16734n != null) {
            c1489n1.w("help_link");
            c1489n1.L(this.f16734n);
        }
        if (this.o != null) {
            c1489n1.w("handled");
            c1489n1.J(this.o);
        }
        if (this.f16735p != null) {
            c1489n1.w("meta");
            c1489n1.I(k10, this.f16735p);
        }
        if (this.f16736q != null) {
            c1489n1.w("data");
            c1489n1.I(k10, this.f16736q);
        }
        if (this.f16737r != null) {
            c1489n1.w("synthetic");
            c1489n1.J(this.f16737r);
        }
        HashMap map = this.f16738s;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f16738s, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
