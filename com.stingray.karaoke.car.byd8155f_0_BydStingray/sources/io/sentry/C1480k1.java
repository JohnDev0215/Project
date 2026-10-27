package io.sentry;

import h2.AbstractC1347d;
import java.util.AbstractMap;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import n7.AbstractC1985B;

/* JADX INFO: renamed from: io.sentry.k1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1480k1 extends X0 implements InterfaceC1485m0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public Date f16597A;
    public io.sentry.protocol.k B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public String f16598C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public A7.D f16599D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public A7.D f16600E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public EnumC1495p1 f16601F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public String f16602G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public List f16603H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public ConcurrentHashMap f16604I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public AbstractMap f16605J;

    public C1480k1(io.sentry.exception.a aVar) {
        this();
        this.f15985u = aVar;
    }

    public final io.sentry.protocol.s b() {
        Boolean bool;
        A7.D d = this.f16600E;
        if (d == null) {
            return null;
        }
        for (io.sentry.protocol.s sVar : d.l) {
            io.sentry.protocol.j jVar = sVar.f16772q;
            if (jVar != null && (bool = jVar.o) != null && !bool.booleanValue()) {
                return sVar;
            }
        }
        return null;
    }

    public final boolean c() {
        A7.D d = this.f16600E;
        return (d == null || d.l.isEmpty()) ? false : true;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("timestamp");
        c1489n1.I(k10, this.f16597A);
        if (this.B != null) {
            c1489n1.w("message");
            c1489n1.I(k10, this.B);
        }
        if (this.f16598C != null) {
            c1489n1.w("logger");
            c1489n1.L(this.f16598C);
        }
        A7.D d = this.f16599D;
        if (d != null && !d.l.isEmpty()) {
            c1489n1.w("threads");
            c1489n1.i();
            c1489n1.w("values");
            c1489n1.I(k10, this.f16599D.l);
            c1489n1.n();
        }
        A7.D d10 = this.f16600E;
        if (d10 != null && !d10.l.isEmpty()) {
            c1489n1.w("exception");
            c1489n1.i();
            c1489n1.w("values");
            c1489n1.I(k10, this.f16600E.l);
            c1489n1.n();
        }
        if (this.f16601F != null) {
            c1489n1.w("level");
            c1489n1.I(k10, this.f16601F);
        }
        if (this.f16602G != null) {
            c1489n1.w("transaction");
            c1489n1.L(this.f16602G);
        }
        if (this.f16603H != null) {
            c1489n1.w("fingerprint");
            c1489n1.I(k10, this.f16603H);
        }
        if (this.f16605J != null) {
            c1489n1.w("modules");
            c1489n1.I(k10, this.f16605J);
        }
        h6.f.t(this, c1489n1, k10);
        ConcurrentHashMap concurrentHashMap = this.f16604I;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16604I, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }

    public C1480k1() {
        io.sentry.protocol.t tVar = new io.sentry.protocol.t((UUID) null);
        Date dateF = AbstractC1985B.f();
        super(tVar);
        this.f16597A = dateF;
    }
}
