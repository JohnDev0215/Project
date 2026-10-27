package g1;

import h2.AbstractC1347d;
import java.util.ArrayList;
import k1.AbstractC1598c;
import n7.AbstractC1998m;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: g1.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1283j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k1.g f14725a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f14726b;
    public final int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C1275b f14727e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f14728f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f14729g;

    public C1283j() {
        new ArrayList();
        this.f14725a = new k1.g(new char[0]);
        this.c = 1000;
        this.d = 1000;
        this.f14728f = 0;
        this.f14729g = new ArrayList();
    }

    public static g0.o b(g0.o oVar, C1278e c1278e, InterfaceC2782k interfaceC2782k) {
        return oVar.k(new C1282i(c1278e, interfaceC2782k));
    }

    public final k1.g a(o oVar) throws k1.h {
        String string = oVar.a().toString();
        k1.g gVar = this.f14725a;
        AbstractC1598c abstractC1598cA = gVar.A(string);
        if ((abstractC1598cA instanceof k1.g ? (k1.g) abstractC1598cA : null) == null) {
            gVar.G(string, new k1.g(new char[0]));
        }
        AbstractC1598c abstractC1598cV = gVar.v(string);
        if (abstractC1598cV instanceof k1.g) {
            return (k1.g) abstractC1598cV;
        }
        StringBuilder sbJ = AbstractC1347d.j("no object found for key <", string, ">, found [");
        sbJ.append(abstractC1598cV.q());
        sbJ.append("] : ");
        sbJ.append(abstractC1598cV);
        throw new k1.h(sbJ.toString(), gVar);
    }

    public final C1278e c() {
        ArrayList arrayList = this.f14729g;
        int i7 = this.f14728f;
        this.f14728f = i7 + 1;
        C1278e c1278e = (C1278e) AbstractC1998m.b0(i7, arrayList);
        if (c1278e != null) {
            return c1278e;
        }
        C1278e c1278e2 = new C1278e(Integer.valueOf(this.f14728f));
        arrayList.add(c1278e2);
        return c1278e2;
    }

    public final C1275b d() {
        C1275b c1275b = this.f14727e;
        if (c1275b != null) {
            return c1275b;
        }
        C1275b c1275b2 = new C1275b(this);
        this.f14727e = c1275b2;
        return c1275b2;
    }

    public final void e() {
        this.f14725a.f17619p.clear();
        this.d = this.c;
        this.f14726b = 0;
        this.f14728f = 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1283j)) {
            return false;
        }
        return A7.m.a(this.f14725a, ((C1283j) obj).f14725a);
    }

    public final void f(int i7) {
        this.f14726b = ((this.f14726b * 1009) + i7) % 1000000007;
    }

    public final int hashCode() {
        return this.f14725a.hashCode();
    }
}
