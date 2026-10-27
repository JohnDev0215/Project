package io.sentry.rrweb;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class l extends b implements InterfaceC1485m0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public HashMap f16862A;
    public ConcurrentHashMap B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public ConcurrentHashMap f16863C;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16864n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f16865p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f16866q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f16867r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public String f16868s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f16869t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f16870u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f16871v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public String f16872w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f16873x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f16874y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f16875z;

    public l() {
        super(c.Custom);
        this.f16867r = "h264";
        this.f16868s = "mp4";
        this.f16872w = "constant";
        this.f16864n = "video";
    }

    @Override // io.sentry.rrweb.b
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || l.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        l lVar = (l) obj;
        return this.o == lVar.o && this.f16865p == lVar.f16865p && this.f16866q == lVar.f16866q && this.f16869t == lVar.f16869t && this.f16870u == lVar.f16870u && this.f16871v == lVar.f16871v && this.f16873x == lVar.f16873x && this.f16874y == lVar.f16874y && this.f16875z == lVar.f16875z && h6.e.i(this.f16864n, lVar.f16864n) && h6.e.i(this.f16867r, lVar.f16867r) && h6.e.i(this.f16868s, lVar.f16868s) && h6.e.i(this.f16872w, lVar.f16872w);
    }

    @Override // io.sentry.rrweb.b
    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(super.hashCode()), this.f16864n, Integer.valueOf(this.o), Long.valueOf(this.f16865p), Long.valueOf(this.f16866q), this.f16867r, this.f16868s, Integer.valueOf(this.f16869t), Integer.valueOf(this.f16870u), Integer.valueOf(this.f16871v), this.f16872w, Integer.valueOf(this.f16873x), Integer.valueOf(this.f16874y), Integer.valueOf(this.f16875z)});
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
        c1489n1.L(this.f16864n);
        c1489n1.w("payload");
        c1489n1.i();
        c1489n1.w("segmentId");
        c1489n1.H(this.o);
        c1489n1.w("size");
        c1489n1.H(this.f16865p);
        c1489n1.w("duration");
        c1489n1.H(this.f16866q);
        c1489n1.w("encoding");
        c1489n1.L(this.f16867r);
        c1489n1.w("container");
        c1489n1.L(this.f16868s);
        c1489n1.w("height");
        c1489n1.H(this.f16869t);
        c1489n1.w("width");
        c1489n1.H(this.f16870u);
        c1489n1.w("frameCount");
        c1489n1.H(this.f16871v);
        c1489n1.w("frameRate");
        c1489n1.H(this.f16873x);
        c1489n1.w("frameRateType");
        c1489n1.L(this.f16872w);
        c1489n1.w("left");
        c1489n1.H(this.f16874y);
        c1489n1.w("top");
        c1489n1.H(this.f16875z);
        ConcurrentHashMap concurrentHashMap = this.B;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.B, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
        ConcurrentHashMap concurrentHashMap2 = this.f16863C;
        if (concurrentHashMap2 != null) {
            for (String str2 : concurrentHashMap2.keySet()) {
                AbstractC1347d.t(this.f16863C, str2, c1489n1, str2, k10);
            }
        }
        c1489n1.n();
        HashMap map = this.f16862A;
        if (map != null) {
            for (String str3 : map.keySet()) {
                AbstractC1347d.s(this.f16862A, str3, c1489n1, str3, k10);
            }
        }
        c1489n1.n();
    }
}
