package N0;

import S0.AbstractC0703o;
import h2.AbstractC1347d;
import n0.AbstractC1887p;
import n0.C1865O;
import n0.C1891t;

/* JADX INFO: loaded from: classes.dex */
public final class D {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Y0.m f4324a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f4325b;
    public final S0.A c;
    public final S0.w d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final S0.x f4326e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final AbstractC0703o f4327f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f4328g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f4329h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Y0.a f4330i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Y0.n f4331j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final U0.b f4332k;
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Y0.j f4333m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C1865O f4334n;
    public final x o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final p0.e f4335p;

    public D(long j3, long j10, S0.A a10, S0.w wVar, S0.x xVar, AbstractC0703o abstractC0703o, String str, long j11, Y0.a aVar, Y0.n nVar, U0.b bVar, long j12, Y0.j jVar, C1865O c1865o, x xVar2, p0.e eVar) {
        this(j3 != 16 ? new Y0.c(j3) : Y0.l.f10622a, j10, a10, wVar, xVar, abstractC0703o, str, j11, aVar, nVar, bVar, j12, jVar, c1865o, xVar2, eVar);
    }

    public final boolean a(D d) {
        if (this == d) {
            return true;
        }
        return a1.m.a(this.f4325b, d.f4325b) && A7.m.a(this.c, d.c) && A7.m.a(this.d, d.d) && A7.m.a(this.f4326e, d.f4326e) && A7.m.a(this.f4327f, d.f4327f) && A7.m.a(this.f4328g, d.f4328g) && a1.m.a(this.f4329h, d.f4329h) && A7.m.a(this.f4330i, d.f4330i) && A7.m.a(this.f4331j, d.f4331j) && A7.m.a(this.f4332k, d.f4332k) && C1891t.c(this.l, d.l) && A7.m.a(this.o, d.o);
    }

    public final boolean b(D d) {
        return A7.m.a(this.f4324a, d.f4324a) && A7.m.a(this.f4333m, d.f4333m) && A7.m.a(this.f4334n, d.f4334n) && A7.m.a(this.f4335p, d.f4335p);
    }

    public final D c(D d) {
        if (d == null) {
            return this;
        }
        Y0.m mVar = d.f4324a;
        return E.a(this, mVar.a(), mVar.b(), mVar.c(), d.f4325b, d.c, d.d, d.f4326e, d.f4327f, d.f4328g, d.f4329h, d.f4330i, d.f4331j, d.f4332k, d.l, d.f4333m, d.f4334n, d.o, d.f4335p);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof D)) {
            return false;
        }
        D d = (D) obj;
        return a(d) && b(d);
    }

    public final int hashCode() {
        Y0.m mVar = this.f4324a;
        long jA = mVar.a();
        int i7 = C1891t.l;
        int iHashCode = Long.hashCode(jA) * 31;
        AbstractC1887p abstractC1887pB = mVar.b();
        int iHashCode2 = (Float.hashCode(mVar.c()) + ((iHashCode + (abstractC1887pB != null ? abstractC1887pB.hashCode() : 0)) * 31)) * 31;
        a1.n[] nVarArr = a1.m.f11041b;
        int iC = AbstractC1347d.c(iHashCode2, 31, this.f4325b);
        S0.A a10 = this.c;
        int i10 = (iC + (a10 != null ? a10.l : 0)) * 31;
        S0.w wVar = this.d;
        int iHashCode3 = (i10 + (wVar != null ? Integer.hashCode(wVar.f7860a) : 0)) * 31;
        S0.x xVar = this.f4326e;
        int iHashCode4 = (iHashCode3 + (xVar != null ? Integer.hashCode(xVar.f7861a) : 0)) * 31;
        AbstractC0703o abstractC0703o = this.f4327f;
        int iHashCode5 = (iHashCode4 + (abstractC0703o != null ? abstractC0703o.hashCode() : 0)) * 31;
        String str = this.f4328g;
        int iC2 = AbstractC1347d.c((iHashCode5 + (str != null ? str.hashCode() : 0)) * 31, 31, this.f4329h);
        Y0.a aVar = this.f4330i;
        int iHashCode6 = (iC2 + (aVar != null ? Float.hashCode(aVar.f10605a) : 0)) * 31;
        Y0.n nVar = this.f4331j;
        int iHashCode7 = (iHashCode6 + (nVar != null ? nVar.hashCode() : 0)) * 31;
        U0.b bVar = this.f4332k;
        int iC3 = AbstractC1347d.c((iHashCode7 + (bVar != null ? bVar.l.hashCode() : 0)) * 31, 31, this.l);
        Y0.j jVar = this.f4333m;
        int i11 = (iC3 + (jVar != null ? jVar.f10620a : 0)) * 31;
        C1865O c1865o = this.f4334n;
        int iHashCode8 = (i11 + (c1865o != null ? c1865o.hashCode() : 0)) * 31;
        x xVar2 = this.o;
        int iHashCode9 = (iHashCode8 + (xVar2 != null ? xVar2.hashCode() : 0)) * 31;
        p0.e eVar = this.f4335p;
        return iHashCode9 + (eVar != null ? eVar.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SpanStyle(color=");
        Y0.m mVar = this.f4324a;
        sb.append((Object) C1891t.i(mVar.a()));
        sb.append(", brush=");
        sb.append(mVar.b());
        sb.append(", alpha=");
        sb.append(mVar.c());
        sb.append(", fontSize=");
        sb.append((Object) a1.m.d(this.f4325b));
        sb.append(", fontWeight=");
        sb.append(this.c);
        sb.append(", fontStyle=");
        sb.append(this.d);
        sb.append(", fontSynthesis=");
        sb.append(this.f4326e);
        sb.append(", fontFamily=");
        sb.append(this.f4327f);
        sb.append(", fontFeatureSettings=");
        sb.append(this.f4328g);
        sb.append(", letterSpacing=");
        sb.append((Object) a1.m.d(this.f4329h));
        sb.append(", baselineShift=");
        sb.append(this.f4330i);
        sb.append(", textGeometricTransform=");
        sb.append(this.f4331j);
        sb.append(", localeList=");
        sb.append(this.f4332k);
        sb.append(", background=");
        AbstractC1347d.n(this.l, ", textDecoration=", sb);
        sb.append(this.f4333m);
        sb.append(", shadow=");
        sb.append(this.f4334n);
        sb.append(", platformStyle=");
        sb.append(this.o);
        sb.append(", drawStyle=");
        sb.append(this.f4335p);
        sb.append(')');
        return sb.toString();
    }

    public D(Y0.m mVar, long j3, S0.A a10, S0.w wVar, S0.x xVar, AbstractC0703o abstractC0703o, String str, long j10, Y0.a aVar, Y0.n nVar, U0.b bVar, long j11, Y0.j jVar, C1865O c1865o, x xVar2, p0.e eVar) {
        this.f4324a = mVar;
        this.f4325b = j3;
        this.c = a10;
        this.d = wVar;
        this.f4326e = xVar;
        this.f4327f = abstractC0703o;
        this.f4328g = str;
        this.f4329h = j10;
        this.f4330i = aVar;
        this.f4331j = nVar;
        this.f4332k = bVar;
        this.l = j11;
        this.f4333m = jVar;
        this.f4334n = c1865o;
        this.o = xVar2;
        this.f4335p = eVar;
    }

    public D(long j3, long j10, S0.A a10, S0.w wVar, S0.x xVar, AbstractC0703o abstractC0703o, String str, long j11, Y0.a aVar, Y0.n nVar, U0.b bVar, long j12, Y0.j jVar, C1865O c1865o, int i7) {
        this((i7 & 1) != 0 ? C1891t.f19452k : j3, (i7 & 2) != 0 ? a1.m.c : j10, (i7 & 4) != 0 ? null : a10, (i7 & 8) != 0 ? null : wVar, (i7 & 16) != 0 ? null : xVar, (i7 & 32) != 0 ? null : abstractC0703o, (i7 & 64) != 0 ? null : str, (i7 & 128) != 0 ? a1.m.c : j11, (i7 & 256) != 0 ? null : aVar, (i7 & 512) != 0 ? null : nVar, (i7 & 1024) != 0 ? null : bVar, (i7 & 2048) != 0 ? C1891t.f19452k : j12, (i7 & 4096) != 0 ? null : jVar, (i7 & 8192) != 0 ? null : c1865o, (x) null, (p0.e) null);
    }
}
