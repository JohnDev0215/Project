package F0;

import android.graphics.Outline;
import android.graphics.Path;
import m0.AbstractC1792a;
import n0.AbstractC1858H;
import n0.AbstractC1861K;
import n0.C1855E;
import n0.C1856F;
import n0.C1857G;
import n0.C1880i;
import n0.InterfaceC1859I;

/* JADX INFO: loaded from: classes.dex */
public final class H0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Outline f2028a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public AbstractC1858H f2029b;
    public C1880i c;
    public InterfaceC1859I d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2030e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2031f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public InterfaceC1859I f2032g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public m0.e f2033h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f2034i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public long f2035j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f2036k;
    public boolean l;

    public H0() {
        Outline outline = new Outline();
        outline.setAlpha(1.0f);
        this.f2028a = outline;
        this.f2035j = 0L;
        this.f2036k = 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(n0.InterfaceC1889r r21) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.H0.a(n0.r):void");
    }

    public final Outline b() {
        d();
        if (this.l) {
            return this.f2028a;
        }
        return null;
    }

    public final boolean c(AbstractC1858H abstractC1858H, float f10, boolean z6, float f11, long j3) {
        this.f2028a.setAlpha(f10);
        boolean zA = A7.m.a(this.f2029b, abstractC1858H);
        boolean z10 = !zA;
        if (!zA) {
            this.f2029b = abstractC1858H;
            this.f2030e = true;
        }
        this.f2036k = j3;
        boolean z11 = abstractC1858H != null && (z6 || f11 > 0.0f);
        if (this.l != z11) {
            this.l = z11;
            this.f2030e = true;
        }
        return z10;
    }

    public final void d() {
        if (this.f2030e) {
            this.f2035j = 0L;
            this.f2034i = 0.0f;
            this.d = null;
            this.f2030e = false;
            this.f2031f = false;
            AbstractC1858H abstractC1858H = this.f2029b;
            Outline outline = this.f2028a;
            if (abstractC1858H == null || !this.l || m0.f.d(this.f2036k) <= 0.0f || m0.f.b(this.f2036k) <= 0.0f) {
                outline.setEmpty();
                return;
            }
            if (abstractC1858H instanceof C1856F) {
                m0.d dVar = ((C1856F) abstractC1858H).f19376a;
                float f10 = dVar.f18882a;
                float f11 = dVar.f18883b;
                this.f2035j = h6.e.a(f10, f11);
                this.f2036k = h6.j.f(dVar.c(), dVar.b());
                outline.setRect(Math.round(f10), Math.round(f11), Math.round(dVar.c), Math.round(dVar.d));
                return;
            }
            if (!(abstractC1858H instanceof C1857G)) {
                if (abstractC1858H instanceof C1855E) {
                    e(((C1855E) abstractC1858H).f19375a);
                    return;
                }
                return;
            }
            m0.e eVar = ((C1857G) abstractC1858H).f19377a;
            float fB = AbstractC1792a.b(eVar.f18886e);
            float f12 = eVar.f18884a;
            float f13 = eVar.f18885b;
            this.f2035j = h6.e.a(f12, f13);
            this.f2036k = h6.j.f(eVar.b(), eVar.a());
            if (h6.i.u(eVar)) {
                this.f2028a.setRoundRect(Math.round(f12), Math.round(f13), Math.round(eVar.c), Math.round(eVar.d), fB);
                this.f2034i = fB;
                return;
            }
            C1880i c1880iI = this.c;
            if (c1880iI == null) {
                c1880iI = AbstractC1861K.i();
                this.c = c1880iI;
            }
            c1880iI.e();
            InterfaceC1859I.a(c1880iI, eVar);
            e(c1880iI);
        }
    }

    public final void e(InterfaceC1859I interfaceC1859I) {
        if (!(interfaceC1859I instanceof C1880i)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        Path path = ((C1880i) interfaceC1859I).f19436a;
        this.f2028a.setConvexPath(path);
        this.f2031f = !r1.canClip();
        this.d = interfaceC1859I;
    }
}
