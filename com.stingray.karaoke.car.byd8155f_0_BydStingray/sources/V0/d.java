package V0;

import A7.m;
import T.D;
import android.graphics.Paint;
import android.text.TextPaint;
import n0.AbstractC1861K;
import n0.AbstractC1887p;
import n0.C1865O;
import n0.C1878g;

/* JADX INFO: loaded from: classes.dex */
public final class d extends TextPaint {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C1878g f9300a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Y0.j f9301b;
    public int c;
    public C1865O d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AbstractC1887p f9302e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public D f9303f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public m0.f f9304g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public p0.e f9305h;

    public final C1878g a() {
        C1878g c1878g = this.f9300a;
        if (c1878g != null) {
            return c1878g;
        }
        C1878g c1878g2 = new C1878g(this);
        this.f9300a = c1878g2;
        return c1878g2;
    }

    public final void b(int i7) {
        if (AbstractC1861K.p(i7, this.c)) {
            return;
        }
        a().d(i7);
        this.c = i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(n0.AbstractC1887p r6, long r7, float r9) {
        /*
            r5 = this;
            r0 = 0
            if (r6 != 0) goto Ld
            r5.f9303f = r0
            r5.f9302e = r0
            r5.f9304g = r0
            r5.setShader(r0)
            goto L6e
        Ld:
            boolean r1 = r6 instanceof n0.C1868S
            if (r1 == 0) goto L1d
            n0.S r6 = (n0.C1868S) r6
            long r6 = r6.f19420a
            long r6 = x3.AbstractC2673a.Q(r9, r6)
            r5.d(r6)
            goto L6e
        L1d:
            boolean r1 = r6 instanceof n0.AbstractC1864N
            if (r1 == 0) goto L6e
            n0.p r1 = r5.f9302e
            boolean r1 = A7.m.a(r1, r6)
            r2 = 0
            if (r1 == 0) goto L38
            m0.f r1 = r5.f9304g
            if (r1 != 0) goto L30
            r1 = r2
            goto L36
        L30:
            long r3 = r1.f18890a
            boolean r1 = m0.f.a(r3, r7)
        L36:
            if (r1 != 0) goto L59
        L38:
            r3 = 9205357640488583168(0x7fc000007fc00000, double:2.247117487993712E307)
            int r1 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r1 == 0) goto L42
            r2 = 1
        L42:
            if (r2 == 0) goto L59
            r5.f9302e = r6
            m0.f r1 = new m0.f
            r1.<init>(r7)
            r5.f9304g = r1
            E0.M r1 = new E0.M
            r2 = 1
            r1.<init>(r2, r7, r6)
            T.D r6 = T.C0735d.G(r1)
            r5.f9303f = r6
        L59:
            n0.g r6 = r5.a()
            T.D r7 = r5.f9303f
            if (r7 == 0) goto L68
            java.lang.Object r7 = r7.getValue()
            r0 = r7
            android.graphics.Shader r0 = (android.graphics.Shader) r0
        L68:
            r6.h(r0)
            V0.i.b(r5, r9)
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: V0.d.c(n0.p, long, float):void");
    }

    public final void d(long j3) {
        if (j3 != 16) {
            setColor(AbstractC1861K.E(j3));
            this.f9303f = null;
            this.f9302e = null;
            this.f9304g = null;
            setShader(null);
        }
    }

    public final void e(p0.e eVar) {
        if (eVar == null || m.a(this.f9305h, eVar)) {
            return;
        }
        this.f9305h = eVar;
        if (eVar.equals(p0.g.f20641a)) {
            setStyle(Paint.Style.FILL);
            return;
        }
        if (eVar instanceof p0.h) {
            a().l(1);
            p0.h hVar = (p0.h) eVar;
            a().k(hVar.f20642a);
            a().f19432a.setStrokeMiter(hVar.f20643b);
            a().j(hVar.d);
            a().i(hVar.c);
            a().f19432a.setPathEffect(null);
        }
    }

    public final void f(C1865O c1865o) {
        if (c1865o == null || m.a(this.d, c1865o)) {
            return;
        }
        this.d = c1865o;
        if (c1865o.equals(C1865O.d)) {
            clearShadowLayer();
            return;
        }
        C1865O c1865o2 = this.d;
        float f10 = c1865o2.c;
        if (f10 == 0.0f) {
            f10 = Float.MIN_VALUE;
        }
        setShadowLayer(f10, m0.c.d(c1865o2.f19403b), m0.c.e(this.d.f19403b), AbstractC1861K.E(this.d.f19402a));
    }

    public final void g(Y0.j jVar) {
        if (jVar == null || m.a(this.f9301b, jVar)) {
            return;
        }
        this.f9301b = jVar;
        int i7 = jVar.f10620a;
        setUnderlineText((i7 | 1) == i7);
        Y0.j jVar2 = this.f9301b;
        jVar2.getClass();
        int i10 = jVar2.f10620a;
        setStrikeThruText((i10 | 2) == i10);
    }
}
