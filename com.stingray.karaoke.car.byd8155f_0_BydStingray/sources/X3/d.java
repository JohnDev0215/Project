package X3;

import N0.q;
import O3.m;
import a4.AbstractC0927h;
import a4.C0920a;
import a4.C0926g;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class d extends b {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final P3.a f10029D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final Rect f10030E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final Rect f10031F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final RectF f10032G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final m f10033H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final R3.g f10034I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public C0926g f10035J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public C1.j f10036K;

    public d(O3.j jVar, e eVar) {
        m mVar;
        super(jVar, eVar);
        this.f10029D = new P3.a(3, 0);
        this.f10030E = new Rect();
        this.f10031F = new Rect();
        this.f10032G = new RectF();
        O3.a aVar = jVar.l;
        if (aVar == null) {
            mVar = null;
        } else {
            mVar = (m) ((HashMap) aVar.c()).get(eVar.f10041g);
        }
        this.f10033H = mVar;
        q qVar = this.f10008p.f10056x;
        if (qVar != null) {
            this.f10034I = new R3.g(this, this, qVar);
        }
    }

    @Override // X3.b, Q3.e
    public final void a(RectF rectF, Matrix matrix, boolean z6) {
        super.a(rectF, matrix, z6);
        if (this.f10033H != null) {
            float fC = AbstractC0927h.c();
            if (this.o.f5125u) {
                rectF.set(0.0f, 0.0f, r4.f5133a * fC, r4.f5134b * fC);
            } else {
                rectF.set(0.0f, 0.0f, n().getWidth() * fC, n().getHeight() * fC);
            }
            this.f10007n.mapRect(rectF);
        }
    }

    @Override // X3.b
    public final void h(Canvas canvas, Matrix matrix, int i7, C0920a c0920a) {
        m mVar;
        Bitmap bitmapN = n();
        if (bitmapN == null || bitmapN.isRecycled() || (mVar = this.f10033H) == null) {
            return;
        }
        float fC = AbstractC0927h.c();
        P3.a aVar = this.f10029D;
        aVar.setAlpha(i7);
        R3.g gVar = this.f10034I;
        if (gVar != null) {
            c0920a = gVar.a(matrix, i7);
        }
        int width = bitmapN.getWidth();
        int height = bitmapN.getHeight();
        Rect rect = this.f10030E;
        rect.set(0, 0, width, height);
        boolean z6 = this.o.f5125u;
        Rect rect2 = this.f10031F;
        if (z6) {
            rect2.set(0, 0, (int) (mVar.f5133a * fC), (int) (mVar.f5134b * fC));
        } else {
            rect2.set(0, 0, (int) (bitmapN.getWidth() * fC), (int) (bitmapN.getHeight() * fC));
        }
        boolean z10 = c0920a != null;
        if (z10) {
            if (this.f10035J == null) {
                this.f10035J = new C0926g();
            }
            if (this.f10036K == null) {
                this.f10036K = new C1.j(7, false);
            }
            C1.j jVar = this.f10036K;
            jVar.l = 255;
            jVar.f944m = null;
            c0920a.getClass();
            C0920a c0920a2 = new C0920a(c0920a);
            jVar.f944m = c0920a2;
            c0920a2.b(i7);
            RectF rectF = this.f10032G;
            rectF.set(rect2.left, rect2.top, rect2.right, rect2.bottom);
            matrix.mapRect(rectF);
            canvas = this.f10035J.e(canvas, rectF, this.f10036K);
        }
        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmapN, rect, rect2, aVar);
        if (z10) {
            this.f10035J.c();
        }
        canvas.restore();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x014d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.Bitmap n() {
        /*
            Method dump skipped, instruction units count: 345
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: X3.d.n():android.graphics.Bitmap");
    }
}
