package Q3;

import A7.D;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
public final class n implements R3.a, c, l {
    public final boolean c;
    public final O3.j d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final R3.e f6197e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final R3.e f6198f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final R3.f f6199g;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f6202j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f6195a = new Path();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final RectF f6196b = new RectF();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final D f6200h = new D(1, false);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public R3.e f6201i = null;

    public n(O3.j jVar, X3.b bVar, W3.i iVar) {
        iVar.getClass();
        this.c = iVar.c;
        this.d = jVar;
        R3.e eVarT0 = iVar.d.t0();
        this.f6197e = eVarT0;
        R3.e eVarT02 = ((V3.a) iVar.f9790e).t0();
        this.f6198f = eVarT02;
        R3.f fVarT0 = iVar.f9789b.t0();
        this.f6199g = fVarT0;
        bVar.d(eVarT0);
        bVar.d(eVarT02);
        bVar.d(fVarT0);
        eVarT0.a(this);
        eVarT02.a(this);
        fVarT0.a(this);
    }

    @Override // R3.a
    public final void b() {
        this.f6202j = false;
        this.d.invalidateSelf();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    @Override // Q3.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(java.util.List r5, java.util.List r6) {
        /*
            r4 = this;
            r6 = 0
        L1:
            r0 = r5
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r1 = r0.size()
            if (r6 >= r1) goto L34
            java.lang.Object r0 = r0.get(r6)
            Q3.c r0 = (Q3.c) r0
            boolean r1 = r0 instanceof Q3.s
            if (r1 == 0) goto L27
            r1 = r0
            Q3.s r1 = (Q3.s) r1
            int r2 = r1.c
            r3 = 1
            if (r2 != r3) goto L27
            A7.D r0 = r4.f6200h
            java.util.ArrayList r0 = r0.l
            r0.add(r1)
            r1.d(r4)
            goto L31
        L27:
            boolean r1 = r0 instanceof Q3.p
            if (r1 == 0) goto L31
            Q3.p r0 = (Q3.p) r0
            R3.e r0 = r0.f6211b
            r4.f6201i = r0
        L31:
            int r6 = r6 + 1
            goto L1
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Q3.n.c(java.util.List, java.util.List):void");
    }

    @Override // Q3.l
    public final Path e() {
        R3.e eVar;
        boolean z6 = this.f6202j;
        Path path = this.f6195a;
        if (z6) {
            return path;
        }
        path.reset();
        if (this.c) {
            this.f6202j = true;
            return path;
        }
        PointF pointF = (PointF) this.f6198f.d();
        float f10 = pointF.x / 2.0f;
        float f11 = pointF.y / 2.0f;
        R3.f fVar = this.f6199g;
        float fI = fVar == null ? 0.0f : fVar.i();
        if (fI == 0.0f && (eVar = this.f6201i) != null) {
            fI = Math.min(((Float) eVar.d()).floatValue(), Math.min(f10, f11));
        }
        float fMin = Math.min(f10, f11);
        if (fI > fMin) {
            fI = fMin;
        }
        PointF pointF2 = (PointF) this.f6197e.d();
        path.moveTo(pointF2.x + f10, (pointF2.y - f11) + fI);
        path.lineTo(pointF2.x + f10, (pointF2.y + f11) - fI);
        RectF rectF = this.f6196b;
        if (fI > 0.0f) {
            float f12 = pointF2.x + f10;
            float f13 = fI * 2.0f;
            float f14 = pointF2.y + f11;
            rectF.set(f12 - f13, f14 - f13, f12, f14);
            path.arcTo(rectF, 0.0f, 90.0f, false);
        }
        path.lineTo((pointF2.x - f10) + fI, pointF2.y + f11);
        if (fI > 0.0f) {
            float f15 = pointF2.x - f10;
            float f16 = pointF2.y + f11;
            float f17 = fI * 2.0f;
            rectF.set(f15, f16 - f17, f17 + f15, f16);
            path.arcTo(rectF, 90.0f, 90.0f, false);
        }
        path.lineTo(pointF2.x - f10, (pointF2.y - f11) + fI);
        if (fI > 0.0f) {
            float f18 = pointF2.x - f10;
            float f19 = pointF2.y - f11;
            float f20 = fI * 2.0f;
            rectF.set(f18, f19, f18 + f20, f20 + f19);
            path.arcTo(rectF, 180.0f, 90.0f, false);
        }
        path.lineTo((pointF2.x + f10) - fI, pointF2.y - f11);
        if (fI > 0.0f) {
            float f21 = pointF2.x + f10;
            float f22 = fI * 2.0f;
            float f23 = pointF2.y - f11;
            rectF.set(f21 - f22, f23, f21, f22 + f23);
            path.arcTo(rectF, 270.0f, 90.0f, false);
        }
        path.close();
        this.f6200h.l(path);
        this.f6202j = true;
        return path;
    }
}
