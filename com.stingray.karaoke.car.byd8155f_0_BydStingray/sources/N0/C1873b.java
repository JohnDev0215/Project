package n0;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;

/* JADX INFO: renamed from: n0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1873b implements InterfaceC1889r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Canvas f19426a = AbstractC1874c.f19428a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Rect f19427b;
    public Rect c;

    @Override // n0.InterfaceC1889r
    public final void a(C1877f c1877f, long j3, long j10, long j11, long j12, C1878g c1878g) {
        if (this.f19427b == null) {
            this.f19427b = new Rect();
            this.c = new Rect();
        }
        Canvas canvas = this.f19426a;
        Bitmap bitmapM = AbstractC1861K.m(c1877f);
        Rect rect = this.f19427b;
        A7.m.c(rect);
        int i7 = (int) (j3 >> 32);
        rect.left = i7;
        int i10 = (int) (j3 & 4294967295L);
        rect.top = i10;
        rect.right = i7 + ((int) (j10 >> 32));
        rect.bottom = i10 + ((int) (j10 & 4294967295L));
        Rect rect2 = this.c;
        A7.m.c(rect2);
        int i11 = (int) (j11 >> 32);
        rect2.left = i11;
        int i12 = (int) (j11 & 4294967295L);
        rect2.top = i12;
        rect2.right = i11 + ((int) (j12 >> 32));
        rect2.bottom = i12 + ((int) (j12 & 4294967295L));
        canvas.drawBitmap(bitmapM, rect, rect2, c1878g.f19432a);
    }

    @Override // n0.InterfaceC1889r
    public final void b(float f10, float f11) {
        this.f19426a.scale(f10, f11);
    }

    @Override // n0.InterfaceC1889r
    public final void c(float f10) {
        this.f19426a.rotate(f10);
    }

    @Override // n0.InterfaceC1889r
    public final void d(float f10, long j3, C1878g c1878g) {
        this.f19426a.drawCircle(m0.c.d(j3), m0.c.e(j3), f10, c1878g.f19432a);
    }

    @Override // n0.InterfaceC1889r
    public final void e(float f10, float f11, float f12, float f13, float f14, float f15, C1878g c1878g) {
        this.f19426a.drawRoundRect(f10, f11, f12, f13, f14, f15, c1878g.f19432a);
    }

    @Override // n0.InterfaceC1889r
    public final void f(InterfaceC1859I interfaceC1859I, C1878g c1878g) {
        Canvas canvas = this.f19426a;
        if (!(interfaceC1859I instanceof C1880i)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        canvas.drawPath(((C1880i) interfaceC1859I).f19436a, c1878g.f19432a);
    }

    @Override // n0.InterfaceC1889r
    public final void g(m0.d dVar, C1878g c1878g) {
        Canvas canvas = this.f19426a;
        Paint paint = c1878g.f19432a;
        canvas.saveLayer(dVar.f18882a, dVar.f18883b, dVar.c, dVar.d, paint, 31);
    }

    @Override // n0.InterfaceC1889r
    public final void h(float f10, float f11, float f12, float f13, float f14, float f15, C1878g c1878g) {
        this.f19426a.drawArc(f10, f11, f12, f13, f14, f15, false, c1878g.f19432a);
    }

    @Override // n0.InterfaceC1889r
    public final void i(float f10, float f11, float f12, float f13, int i7) {
        this.f19426a.clipRect(f10, f11, f12, f13, i7 == 0 ? Region.Op.DIFFERENCE : Region.Op.INTERSECT);
    }

    @Override // n0.InterfaceC1889r
    public final void j(float f10, float f11) {
        this.f19426a.translate(f10, f11);
    }

    @Override // n0.InterfaceC1889r
    public final void k() {
        this.f19426a.restore();
    }

    @Override // n0.InterfaceC1889r
    public final void l(float f10, float f11, float f12, float f13, C1878g c1878g) {
        this.f19426a.drawRect(f10, f11, f12, f13, c1878g.f19432a);
    }

    @Override // n0.InterfaceC1889r
    public final void m() {
        this.f19426a.save();
    }

    @Override // n0.InterfaceC1889r
    public final void n(long j3, long j10, C1878g c1878g) {
        this.f19426a.drawLine(m0.c.d(j3), m0.c.e(j3), m0.c.d(j10), m0.c.e(j10), c1878g.f19432a);
    }

    @Override // n0.InterfaceC1889r
    public final void o() {
        C1890s.f19444a.a(this.f19426a, false);
    }

    @Override // n0.InterfaceC1889r
    public final void p(InterfaceC1859I interfaceC1859I, int i7) {
        Canvas canvas = this.f19426a;
        if (!(interfaceC1859I instanceof C1880i)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        canvas.clipPath(((C1880i) interfaceC1859I).f19436a, i7 == 0 ? Region.Op.DIFFERENCE : Region.Op.INTERSECT);
    }

    @Override // n0.InterfaceC1889r
    public final void r(float[] fArr) {
        int i7 = 0;
        while (i7 < 4) {
            int i10 = 0;
            while (i10 < 4) {
                if (fArr[(i7 * 4) + i10] != (i7 == i10 ? 1.0f : 0.0f)) {
                    Matrix matrix = new Matrix();
                    AbstractC1861K.x(matrix, fArr);
                    this.f19426a.concat(matrix);
                    return;
                }
                i10++;
            }
            i7++;
        }
    }

    @Override // n0.InterfaceC1889r
    public final void s() {
        C1890s.f19444a.a(this.f19426a, true);
    }

    @Override // n0.InterfaceC1889r
    public final void t(C1877f c1877f, long j3, C1878g c1878g) {
        this.f19426a.drawBitmap(AbstractC1861K.m(c1877f), m0.c.d(j3), m0.c.e(j3), c1878g.f19432a);
    }
}
