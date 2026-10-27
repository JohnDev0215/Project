package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class z implements InterfaceC1485m0 {
    public Long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Integer f16808m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16809n;
    public String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Boolean f16810p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Boolean f16811q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Boolean f16812r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Boolean f16813s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public y f16814t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Map f16815u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public ConcurrentHashMap f16816v;

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("id");
            c1489n1.K(this.l);
        }
        if (this.f16808m != null) {
            c1489n1.w("priority");
            c1489n1.K(this.f16808m);
        }
        if (this.f16809n != null) {
            c1489n1.w("name");
            c1489n1.L(this.f16809n);
        }
        if (this.o != null) {
            c1489n1.w("state");
            c1489n1.L(this.o);
        }
        if (this.f16810p != null) {
            c1489n1.w("crashed");
            c1489n1.J(this.f16810p);
        }
        if (this.f16811q != null) {
            c1489n1.w("current");
            c1489n1.J(this.f16811q);
        }
        if (this.f16812r != null) {
            c1489n1.w("daemon");
            c1489n1.J(this.f16812r);
        }
        if (this.f16813s != null) {
            c1489n1.w("main");
            c1489n1.J(this.f16813s);
        }
        if (this.f16814t != null) {
            c1489n1.w("stacktrace");
            c1489n1.I(k10, this.f16814t);
        }
        if (this.f16815u != null) {
            c1489n1.w("held_locks");
            c1489n1.I(k10, this.f16815u);
        }
        ConcurrentHashMap concurrentHashMap = this.f16816v;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16816v, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
