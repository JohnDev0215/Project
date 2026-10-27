package io.sentry;

import h2.AbstractC1347d;
import java.util.Date;
import java.util.HashMap;
import n7.AbstractC1985B;

/* JADX INFO: renamed from: io.sentry.d1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1459d1 implements InterfaceC1485m0 {
    public final io.sentry.protocol.t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final io.sentry.protocol.r f16521m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final a2 f16522n;
    public Date o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public HashMap f16523p;

    public C1459d1(io.sentry.protocol.t tVar, io.sentry.protocol.r rVar, a2 a2Var) {
        this.l = tVar;
        this.f16521m = rVar;
        this.f16522n = a2Var;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        io.sentry.protocol.t tVar = this.l;
        if (tVar != null) {
            c1489n1.w("event_id");
            c1489n1.I(k10, tVar);
        }
        io.sentry.protocol.r rVar = this.f16521m;
        if (rVar != null) {
            c1489n1.w("sdk");
            c1489n1.I(k10, rVar);
        }
        a2 a2Var = this.f16522n;
        if (a2Var != null) {
            c1489n1.w("trace");
            c1489n1.I(k10, a2Var);
        }
        if (this.o != null) {
            c1489n1.w("sent_at");
            c1489n1.I(k10, AbstractC1985B.m(this.o));
        }
        HashMap map = this.f16523p;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f16523p, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
