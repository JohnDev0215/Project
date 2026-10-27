package Y2;

import B2.K;
import B2.L;
import e2.C1207o;
import e2.C1208p;
import e2.InterfaceC1201i;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.t;
import java.io.EOFException;

/* JADX INFO: loaded from: classes.dex */
public final class n implements L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final L f10652a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j f10653b;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public l f10656g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public C1208p f10657h;
    public int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f10654e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f10655f = AbstractC1343A.f15198f;
    public final t c = new t();

    public n(L l, j jVar) {
        this.f10652a = l;
        this.f10653b = jVar;
    }

    @Override // B2.L
    public final void a(t tVar, int i7, int i10) {
        if (this.f10656g == null) {
            this.f10652a.a(tVar, i7, i10);
            return;
        }
        e(i7);
        tVar.f(this.f10654e, i7, this.f10655f);
        this.f10654e += i7;
    }

    @Override // B2.L
    public final void b(long j3, int i7, int i10, int i11, K k10) {
        if (this.f10656g == null) {
            this.f10652a.b(j3, i7, i10, i11, k10);
            return;
        }
        AbstractC1346c.b("DRM on subtitles is not supported", k10 == null);
        int i12 = (this.f10654e - i11) - i10;
        this.f10656g.h(this.f10655f, i12, i10, k.c, new m(this, j3, i7));
        int i13 = i12 + i10;
        this.d = i13;
        if (i13 == this.f10654e) {
            this.d = 0;
            this.f10654e = 0;
        }
    }

    @Override // B2.L
    public final int c(InterfaceC1201i interfaceC1201i, int i7, boolean z6) throws EOFException {
        if (this.f10656g == null) {
            return this.f10652a.c(interfaceC1201i, i7, z6);
        }
        e(i7);
        int iN = interfaceC1201i.n(this.f10655f, this.f10654e, i7);
        if (iN != -1) {
            this.f10654e += iN;
            return iN;
        }
        if (z6) {
            return -1;
        }
        throw new EOFException();
    }

    @Override // B2.L
    public final void d(C1208p c1208p) {
        c1208p.f14316n.getClass();
        String str = c1208p.f14316n;
        AbstractC1346c.c(e2.L.g(str) == 3);
        boolean zEquals = c1208p.equals(this.f10657h);
        j jVar = this.f10653b;
        if (!zEquals) {
            this.f10657h = c1208p;
            this.f10656g = jVar.j(c1208p) ? jVar.c(c1208p) : null;
        }
        l lVar = this.f10656g;
        L l = this.f10652a;
        if (lVar == null) {
            l.d(c1208p);
            return;
        }
        C1207o c1207oA = c1208p.a();
        c1207oA.f14246m = e2.L.l("application/x-media3-cues");
        c1207oA.f14244j = str;
        c1207oA.f14250r = Long.MAX_VALUE;
        c1207oA.f14233H = jVar.a(c1208p);
        l.d(new C1208p(c1207oA));
    }

    public final void e(int i7) {
        int length = this.f10655f.length;
        int i10 = this.f10654e;
        if (length - i10 >= i7) {
            return;
        }
        int i11 = i10 - this.d;
        int iMax = Math.max(i11 * 2, i7 + i11);
        byte[] bArr = this.f10655f;
        byte[] bArr2 = iMax <= bArr.length ? bArr : new byte[iMax];
        System.arraycopy(bArr, this.d, bArr2, 0, i11);
        this.d = 0;
        this.f10654e = i11;
        this.f10655f = bArr2;
    }
}
