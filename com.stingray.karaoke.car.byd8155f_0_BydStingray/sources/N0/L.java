package N0;

import S0.AbstractC0703o;
import h2.AbstractC1347d;
import n0.AbstractC1864N;
import n0.AbstractC1887p;
import n0.C1865O;
import n0.C1868S;
import n0.C1891t;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class L {
    public static final L d = new L(0, 0, null, null, null, 0, 0, 0, null, 16777215);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final D f4358a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final u f4359b;
    public final y c;

    public L(D d10, u uVar, y yVar) {
        this.f4358a = d10;
        this.f4359b = uVar;
        this.c = yVar;
    }

    public static L a(L l, AbstractC1887p abstractC1887p, p0.h hVar, int i7) {
        Y0.a aVar;
        Y0.m bVar;
        Y0.m mVar;
        Y0.n nVar;
        float fC = l.f4358a.f4324a.c();
        D d10 = l.f4358a;
        long j3 = d10.f4325b;
        S0.A a10 = d10.c;
        S0.w wVar = d10.d;
        S0.x xVar = d10.f4326e;
        AbstractC0703o abstractC0703o = d10.f4327f;
        String str = d10.f4328g;
        long j10 = d10.f4329h;
        Y0.a aVar2 = d10.f4330i;
        Y0.n nVar2 = d10.f4331j;
        U0.b bVar2 = d10.f4332k;
        long j11 = d10.l;
        Y0.j jVar = d10.f4333m;
        C1865O c1865o = (i7 & 16384) != 0 ? d10.f4334n : null;
        p0.e eVar = (i7 & 32768) != 0 ? d10.f4335p : hVar;
        u uVar = l.f4359b;
        int i10 = uVar.f4409a;
        int i11 = uVar.f4410b;
        long j12 = uVar.c;
        Y0.o oVar = uVar.d;
        y yVar = l.c;
        Y0.g gVar = uVar.f4412f;
        int i12 = uVar.f4413g;
        int i13 = uVar.f4414h;
        Y0.p pVar = uVar.f4415i;
        l.getClass();
        x xVar2 = yVar != null ? yVar.f4421a : null;
        Y0.l lVar = Y0.l.f10622a;
        if (abstractC1887p == null) {
            aVar = aVar2;
            nVar = nVar2;
            mVar = lVar;
        } else {
            if (abstractC1887p instanceof C1868S) {
                aVar = aVar2;
                long jQ = AbstractC2673a.Q(fC, ((C1868S) abstractC1887p).f19420a);
                bVar = jQ != 16 ? new Y0.c(jQ) : lVar;
            } else {
                aVar = aVar2;
                if (!(abstractC1887p instanceof AbstractC1864N)) {
                    throw new A9.k();
                }
                bVar = new Y0.b((AbstractC1864N) abstractC1887p, fC);
            }
            mVar = bVar;
            nVar = nVar2;
        }
        return new L(new D(mVar, j3, a10, wVar, xVar, abstractC0703o, str, j10, aVar, nVar, bVar2, j11, jVar, c1865o, xVar2, eVar), new u(i10, i11, j12, oVar, yVar != null ? yVar.f4422b : null, gVar, i12, i13, pVar), yVar);
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public static N0.L b(N0.L r31, long r32, long r34, S0.A r36, S0.AbstractC0703o r37, long r38, n0.C1865O r40, long r41, N0.y r43, Y0.g r44, Y0.p r45, int r46) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: N0.L.b(N0.L, long, long, S0.A, S0.o, long, n0.O, long, N0.y, Y0.g, Y0.p, int):N0.L");
    }

    public static L f(L l, long j3, long j10, S0.A a10, S0.w wVar, AbstractC0703o abstractC0703o, long j11, Y0.j jVar, int i7, long j12, int i10) {
        long j13 = (i10 & 1) != 0 ? C1891t.f19452k : j3;
        long j14 = (i10 & 2) != 0 ? a1.m.c : j10;
        S0.A a11 = (i10 & 4) != 0 ? null : a10;
        S0.w wVar2 = (i10 & 8) != 0 ? null : wVar;
        AbstractC0703o abstractC0703o2 = (i10 & 32) != 0 ? null : abstractC0703o;
        long j15 = (i10 & 128) != 0 ? a1.m.c : j11;
        long j16 = C1891t.f19452k;
        Y0.j jVar2 = (i10 & 4096) != 0 ? null : jVar;
        int i11 = (32768 & i10) != 0 ? Integer.MIN_VALUE : i7;
        long j17 = (i10 & 131072) != 0 ? a1.m.c : j12;
        D dA = E.a(l.f4358a, j13, null, Float.NaN, j14, a11, wVar2, null, abstractC0703o2, null, j15, null, null, null, j16, jVar2, null, null, null);
        u uVarA = v.a(l.f4359b, i11, Integer.MIN_VALUE, j17, null, null, null, 0, Integer.MIN_VALUE, null);
        return (l.f4358a == dA && l.f4359b == uVarA) ? l : new L(dA, uVarA);
    }

    public final long c() {
        return this.f4358a.f4324a.a();
    }

    public final boolean d(L l) {
        if (this != l) {
            if (!A7.m.a(this.f4359b, l.f4359b) || !this.f4358a.a(l.f4358a)) {
                return false;
            }
        }
        return true;
    }

    public final L e(L l) {
        return (l == null || l.equals(d)) ? this : new L(this.f4358a.c(l.f4358a), this.f4359b.a(l.f4359b));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof L)) {
            return false;
        }
        L l = (L) obj;
        return A7.m.a(this.f4358a, l.f4358a) && A7.m.a(this.f4359b, l.f4359b) && A7.m.a(this.c, l.c);
    }

    public final int hashCode() {
        int iHashCode = (this.f4359b.hashCode() + (this.f4358a.hashCode() * 31)) * 31;
        y yVar = this.c;
        return iHashCode + (yVar != null ? yVar.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TextStyle(color=");
        sb.append((Object) C1891t.i(c()));
        sb.append(", brush=");
        D d10 = this.f4358a;
        sb.append(d10.f4324a.b());
        sb.append(", alpha=");
        sb.append(d10.f4324a.c());
        sb.append(", fontSize=");
        sb.append((Object) a1.m.d(d10.f4325b));
        sb.append(", fontWeight=");
        sb.append(d10.c);
        sb.append(", fontStyle=");
        sb.append(d10.d);
        sb.append(", fontSynthesis=");
        sb.append(d10.f4326e);
        sb.append(", fontFamily=");
        sb.append(d10.f4327f);
        sb.append(", fontFeatureSettings=");
        sb.append(d10.f4328g);
        sb.append(", letterSpacing=");
        sb.append((Object) a1.m.d(d10.f4329h));
        sb.append(", baselineShift=");
        sb.append(d10.f4330i);
        sb.append(", textGeometricTransform=");
        sb.append(d10.f4331j);
        sb.append(", localeList=");
        sb.append(d10.f4332k);
        sb.append(", background=");
        AbstractC1347d.n(d10.l, ", textDecoration=", sb);
        sb.append(d10.f4333m);
        sb.append(", shadow=");
        sb.append(d10.f4334n);
        sb.append(", drawStyle=");
        sb.append(d10.f4335p);
        sb.append(", textAlign=");
        u uVar = this.f4359b;
        sb.append((Object) Y0.i.b(uVar.f4409a));
        sb.append(", textDirection=");
        sb.append((Object) Y0.k.b(uVar.f4410b));
        sb.append(", lineHeight=");
        sb.append((Object) a1.m.d(uVar.c));
        sb.append(", textIndent=");
        sb.append(uVar.d);
        sb.append(", platformStyle=");
        sb.append(this.c);
        sb.append(", lineHeightStyle=");
        sb.append(uVar.f4412f);
        sb.append(", lineBreak=");
        sb.append((Object) Y0.e.a(uVar.f4413g));
        sb.append(", hyphens=");
        sb.append((Object) Y0.d.b(uVar.f4414h));
        sb.append(", textMotion=");
        sb.append(uVar.f4415i);
        sb.append(')');
        return sb.toString();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public L(D d10, u uVar) {
        x xVar = d10.o;
        w wVar = uVar.f4411e;
        this(d10, uVar, (xVar == null && wVar == null) ? null : new y(xVar, wVar));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public L(long j3, long j10, S0.A a10, S0.w wVar, S0.s sVar, long j11, int i7, long j12, y yVar, int i10) {
        long j13 = (i10 & 1) != 0 ? C1891t.f19452k : j3;
        long j14 = (i10 & 2) != 0 ? a1.m.c : j10;
        S0.A a11 = (i10 & 4) != 0 ? null : a10;
        S0.w wVar2 = (i10 & 8) != 0 ? null : wVar;
        S0.s sVar2 = (i10 & 32) != 0 ? null : sVar;
        long j15 = (i10 & 128) != 0 ? a1.m.c : j11;
        long j16 = C1891t.f19452k;
        int i11 = (32768 & i10) != 0 ? Integer.MIN_VALUE : i7;
        long j17 = (131072 & i10) != 0 ? a1.m.c : j12;
        y yVar2 = (i10 & 524288) != 0 ? null : yVar;
        this(new D(j13, j14, a11, wVar2, (S0.x) null, sVar2, (String) null, j15, (Y0.a) null, (Y0.n) null, (U0.b) null, j16, (Y0.j) null, (C1865O) null, yVar2 != null ? yVar2.f4421a : null, (p0.e) null), new u(i11, Integer.MIN_VALUE, j17, null, yVar2 != null ? yVar2.f4422b : null, null, 0, Integer.MIN_VALUE, null), yVar2);
    }
}
