package N0;

import android.graphics.Canvas;
import android.text.TextUtils;
import n0.AbstractC1874c;
import n0.AbstractC1887p;
import n0.C1865O;
import n0.InterfaceC1889r;

/* JADX INFO: renamed from: N0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0406a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final V0.c f4362a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4363b;
    public final long c;
    public final O0.x d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final CharSequence f4364e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f4365f;

    /* JADX WARN: Removed duplicated region for block: B:104:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0100  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public C0406a(V0.c r25, int r26, boolean r27, long r28) {
        /*
            Method dump skipped, instruction units count: 772
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: N0.C0406a.<init>(V0.c, int, boolean, long):void");
    }

    public final O0.x a(int i7, int i10, TextUtils.TruncateAt truncateAt, int i11, int i12, int i13, int i14, int i15) {
        w wVar;
        float fD = d();
        V0.c cVar = this.f4362a;
        V0.a aVar = V0.b.f9289a;
        y yVar = cVar.f9290m.c;
        return new O0.x(this.f4364e, fD, cVar.f9294r, i7, truncateAt, cVar.f9299w, (yVar == null || (wVar = yVar.f4422b) == null) ? false : wVar.f4419a, i11, i13, i14, i15, i12, i10, cVar.f9296t);
    }

    public final float b() {
        return this.d.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long c(m0.d r22, int r23, B2.C0074n r24) {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: N0.C0406a.c(m0.d, int, B2.n):long");
    }

    public final float d() {
        return a1.a.h(this.c);
    }

    public final void e(InterfaceC1889r interfaceC1889r) {
        Canvas canvasA = AbstractC1874c.a(interfaceC1889r);
        O0.x xVar = this.d;
        if (xVar.d) {
            canvasA.save();
            canvasA.clipRect(0.0f, 0.0f, d(), b());
        }
        if (canvasA.getClipBounds(xVar.f5058p)) {
            int i7 = xVar.f5052h;
            if (i7 != 0) {
                canvasA.translate(0.0f, i7);
            }
            O0.w wVar = O0.y.f5060a;
            wVar.f5046a = canvasA;
            xVar.f5050f.draw(wVar);
            if (i7 != 0) {
                canvasA.translate(0.0f, (-1) * i7);
            }
        }
        if (xVar.d) {
            canvasA.restore();
        }
    }

    public final void f(InterfaceC1889r interfaceC1889r, long j3, C1865O c1865o, Y0.j jVar, p0.e eVar, int i7) {
        V0.c cVar = this.f4362a;
        V0.d dVar = cVar.f9294r;
        int i10 = dVar.c;
        dVar.d(j3);
        dVar.f(c1865o);
        dVar.g(jVar);
        dVar.e(eVar);
        dVar.b(i7);
        e(interfaceC1889r);
        cVar.f9294r.b(i10);
    }

    public final void g(InterfaceC1889r interfaceC1889r, AbstractC1887p abstractC1887p, float f10, C1865O c1865o, Y0.j jVar, p0.e eVar, int i7) {
        V0.d dVar = this.f4362a.f9294r;
        int i10 = dVar.c;
        dVar.c(abstractC1887p, h6.j.f(d(), b()), f10);
        dVar.f(c1865o);
        dVar.g(jVar);
        dVar.e(eVar);
        dVar.b(i7);
        e(interfaceC1889r);
        dVar.b(i10);
    }
}
