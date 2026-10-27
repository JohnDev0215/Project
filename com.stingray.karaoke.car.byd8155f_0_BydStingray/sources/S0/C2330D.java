package s0;

import h2.AbstractC1347d;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: s0.D, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2330D extends AbstractC2332F implements Iterable, B7.a {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f21966m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final float f21967n;
    public final float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final float f21968p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final float f21969q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final float f21970r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final float f21971s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final List f21972t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final List f21973u;

    public C2330D(String str, float f10, float f11, float f12, float f13, float f14, float f15, float f16, List list, List list2) {
        this.l = str;
        this.f21966m = f10;
        this.f21967n = f11;
        this.o = f12;
        this.f21968p = f13;
        this.f21969q = f14;
        this.f21970r = f15;
        this.f21971s = f16;
        this.f21972t = list;
        this.f21973u = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof C2330D)) {
            C2330D c2330d = (C2330D) obj;
            return A7.m.a(this.l, c2330d.l) && this.f21966m == c2330d.f21966m && this.f21967n == c2330d.f21967n && this.o == c2330d.o && this.f21968p == c2330d.f21968p && this.f21969q == c2330d.f21969q && this.f21970r == c2330d.f21970r && this.f21971s == c2330d.f21971s && A7.m.a(this.f21972t, c2330d.f21972t) && A7.m.a(this.f21973u, c2330d.f21973u);
        }
        return false;
    }

    public final int hashCode() {
        return this.f21973u.hashCode() + ((this.f21972t.hashCode() + AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(this.l.hashCode() * 31, 31, this.f21966m), 31, this.f21967n), 31, this.o), 31, this.f21968p), 31, this.f21969q), 31, this.f21970r), 31, this.f21971s)) * 31);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new O8.h(this);
    }
}
