package s0;

import h2.AbstractC1347d;
import java.util.List;
import n0.AbstractC1861K;
import n0.AbstractC1887p;
import x.AbstractC2641j;

/* JADX INFO: renamed from: s0.G, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2333G extends AbstractC2332F {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f21975m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f21976n;
    public final AbstractC1887p o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final float f21977p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final AbstractC1887p f21978q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final float f21979r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final float f21980s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f21981t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f21982u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final float f21983v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final float f21984w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final float f21985x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final float f21986y;

    public C2333G(String str, List list, int i7, AbstractC1887p abstractC1887p, float f10, AbstractC1887p abstractC1887p2, float f11, float f12, int i10, int i11, float f13, float f14, float f15, float f16) {
        this.l = str;
        this.f21975m = list;
        this.f21976n = i7;
        this.o = abstractC1887p;
        this.f21977p = f10;
        this.f21978q = abstractC1887p2;
        this.f21979r = f11;
        this.f21980s = f12;
        this.f21981t = i10;
        this.f21982u = i11;
        this.f21983v = f13;
        this.f21984w = f14;
        this.f21985x = f15;
        this.f21986y = f16;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && C2333G.class == obj.getClass()) {
            C2333G c2333g = (C2333G) obj;
            return this.l.equals(c2333g.l) && A7.m.a(this.o, c2333g.o) && this.f21977p == c2333g.f21977p && A7.m.a(this.f21978q, c2333g.f21978q) && this.f21979r == c2333g.f21979r && this.f21980s == c2333g.f21980s && AbstractC1861K.s(this.f21981t, c2333g.f21981t) && AbstractC1861K.t(this.f21982u, c2333g.f21982u) && this.f21983v == c2333g.f21983v && this.f21984w == c2333g.f21984w && this.f21985x == c2333g.f21985x && this.f21986y == c2333g.f21986y && this.f21976n == c2333g.f21976n && A7.m.a(this.f21975m, c2333g.f21975m);
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.f21975m.hashCode() + (this.l.hashCode() * 31)) * 31;
        AbstractC1887p abstractC1887p = this.o;
        int iB = AbstractC1347d.b((iHashCode + (abstractC1887p != null ? abstractC1887p.hashCode() : 0)) * 31, 31, this.f21977p);
        AbstractC1887p abstractC1887p2 = this.f21978q;
        return Integer.hashCode(this.f21976n) + AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC2641j.b(this.f21982u, AbstractC2641j.b(this.f21981t, AbstractC1347d.b(AbstractC1347d.b((iB + (abstractC1887p2 != null ? abstractC1887p2.hashCode() : 0)) * 31, 31, this.f21979r), 31, this.f21980s), 31), 31), 31, this.f21983v), 31, this.f21984w), 31, this.f21985x), 31, this.f21986y);
    }
}
