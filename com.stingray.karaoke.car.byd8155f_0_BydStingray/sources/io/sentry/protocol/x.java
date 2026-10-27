package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.C1505q1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class x implements InterfaceC1485m0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public ConcurrentHashMap f16791A;
    public String B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public C1505q1 f16792C;
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16793m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16794n;
    public Integer o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Integer f16795p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f16796q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f16797r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Boolean f16798s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f16799t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Boolean f16800u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f16801v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public String f16802w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public String f16803x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public String f16804y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public String f16805z;

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("filename");
            c1489n1.L(this.l);
        }
        if (this.f16793m != null) {
            c1489n1.w("function");
            c1489n1.L(this.f16793m);
        }
        if (this.f16794n != null) {
            c1489n1.w("module");
            c1489n1.L(this.f16794n);
        }
        if (this.o != null) {
            c1489n1.w("lineno");
            c1489n1.K(this.o);
        }
        if (this.f16795p != null) {
            c1489n1.w("colno");
            c1489n1.K(this.f16795p);
        }
        if (this.f16796q != null) {
            c1489n1.w("abs_path");
            c1489n1.L(this.f16796q);
        }
        if (this.f16797r != null) {
            c1489n1.w("context_line");
            c1489n1.L(this.f16797r);
        }
        if (this.f16798s != null) {
            c1489n1.w("in_app");
            c1489n1.J(this.f16798s);
        }
        if (this.f16799t != null) {
            c1489n1.w("package");
            c1489n1.L(this.f16799t);
        }
        if (this.f16800u != null) {
            c1489n1.w("native");
            c1489n1.J(this.f16800u);
        }
        if (this.f16801v != null) {
            c1489n1.w("platform");
            c1489n1.L(this.f16801v);
        }
        if (this.f16802w != null) {
            c1489n1.w("image_addr");
            c1489n1.L(this.f16802w);
        }
        if (this.f16803x != null) {
            c1489n1.w("symbol_addr");
            c1489n1.L(this.f16803x);
        }
        if (this.f16804y != null) {
            c1489n1.w("instruction_addr");
            c1489n1.L(this.f16804y);
        }
        if (this.B != null) {
            c1489n1.w("raw_function");
            c1489n1.L(this.B);
        }
        if (this.f16805z != null) {
            c1489n1.w("symbol");
            c1489n1.L(this.f16805z);
        }
        if (this.f16792C != null) {
            c1489n1.w("lock");
            c1489n1.I(k10, this.f16792C);
        }
        ConcurrentHashMap concurrentHashMap = this.f16791A;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16791A, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
