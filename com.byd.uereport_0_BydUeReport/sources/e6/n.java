package e6;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;

/* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o[] f4730a = new o[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix[] f4731b = new Matrix[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix[] f4732c = new Matrix[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final PointF f4733d = new PointF();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Path f4734e = new Path();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Path f4735f = new Path();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final o f4736g = new o();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float[] f4737h = new float[2];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float[] f4738i = new float[2];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4739j = true;

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public interface a {
        void a(o oVar, Matrix matrix, int i8);

        void b(o oVar, Matrix matrix, int i8);
    }

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final m f4740a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f4741b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final RectF f4742c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f4743d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f4744e;

        public b(m mVar, float f8, RectF rectF, a aVar, Path path) {
            this.f4743d = aVar;
            this.f4740a = mVar;
            this.f4744e = f8;
            this.f4742c = rectF;
            this.f4741b = path;
        }
    }

    public n() {
        for (int i8 = 0; i8 < 4; i8++) {
            this.f4730a[i8] = new o();
            this.f4731b[i8] = new Matrix();
            this.f4732c[i8] = new Matrix();
        }
    }

    public final float a(int i8) {
        return (i8 + 1) * 90;
    }

    public final void b(b bVar, int i8) {
        this.f4737h[0] = this.f4730a[i8].k();
        this.f4737h[1] = this.f4730a[i8].l();
        this.f4731b[i8].mapPoints(this.f4737h);
        if (i8 == 0) {
            Path path = bVar.f4741b;
            float[] fArr = this.f4737h;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = bVar.f4741b;
            float[] fArr2 = this.f4737h;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.f4730a[i8].d(this.f4731b[i8], bVar.f4741b);
        a aVar = bVar.f4743d;
        if (aVar != null) {
            aVar.b(this.f4730a[i8], this.f4731b[i8], i8);
        }
    }

    public final void c(b bVar, int i8) {
        int i9 = (i8 + 1) % 4;
        this.f4737h[0] = this.f4730a[i8].i();
        this.f4737h[1] = this.f4730a[i8].j();
        this.f4731b[i8].mapPoints(this.f4737h);
        this.f4738i[0] = this.f4730a[i9].k();
        this.f4738i[1] = this.f4730a[i9].l();
        this.f4731b[i9].mapPoints(this.f4738i);
        float f8 = this.f4737h[0];
        float[] fArr = this.f4738i;
        float fMax = Math.max(((float) Math.hypot(f8 - fArr[0], r1[1] - fArr[1])) - 0.001f, 0.0f);
        float fI = i(bVar.f4742c, i8);
        this.f4736g.n(0.0f, 0.0f);
        f fVarJ = j(i8, bVar.f4740a);
        fVarJ.b(fMax, fI, bVar.f4744e, this.f4736g);
        Path path = new Path();
        this.f4736g.d(this.f4732c[i8], path);
        if (this.f4739j && Build.VERSION.SDK_INT >= 19 && (fVarJ.a() || k(path, i8) || k(path, i9))) {
            path.op(path, this.f4735f, Path.Op.DIFFERENCE);
            this.f4737h[0] = this.f4736g.k();
            this.f4737h[1] = this.f4736g.l();
            this.f4732c[i8].mapPoints(this.f4737h);
            Path path2 = this.f4734e;
            float[] fArr2 = this.f4737h;
            path2.moveTo(fArr2[0], fArr2[1]);
            this.f4736g.d(this.f4732c[i8], this.f4734e);
        } else {
            this.f4736g.d(this.f4732c[i8], bVar.f4741b);
        }
        a aVar = bVar.f4743d;
        if (aVar != null) {
            aVar.a(this.f4736g, this.f4732c[i8], i8);
        }
    }

    public void d(m mVar, float f8, RectF rectF, Path path) {
        e(mVar, f8, rectF, null, path);
    }

    public void e(m mVar, float f8, RectF rectF, a aVar, Path path) {
        path.rewind();
        this.f4734e.rewind();
        this.f4735f.rewind();
        this.f4735f.addRect(rectF, Path.Direction.CW);
        b bVar = new b(mVar, f8, rectF, aVar, path);
        for (int i8 = 0; i8 < 4; i8++) {
            l(bVar, i8);
            m(i8);
        }
        for (int i9 = 0; i9 < 4; i9++) {
            b(bVar, i9);
            c(bVar, i9);
        }
        path.close();
        this.f4734e.close();
        if (Build.VERSION.SDK_INT < 19 || this.f4734e.isEmpty()) {
            return;
        }
        path.op(this.f4734e, Path.Op.UNION);
    }

    public final void f(int i8, RectF rectF, PointF pointF) {
        if (i8 == 1) {
            pointF.set(rectF.right, rectF.bottom);
            return;
        }
        if (i8 == 2) {
            pointF.set(rectF.left, rectF.bottom);
        } else if (i8 != 3) {
            pointF.set(rectF.right, rectF.top);
        } else {
            pointF.set(rectF.left, rectF.top);
        }
    }

    public final c g(int i8, m mVar) {
        return i8 != 1 ? i8 != 2 ? i8 != 3 ? mVar.t() : mVar.r() : mVar.j() : mVar.l();
    }

    public final d h(int i8, m mVar) {
        return i8 != 1 ? i8 != 2 ? i8 != 3 ? mVar.s() : mVar.q() : mVar.i() : mVar.k();
    }

    public final float i(RectF rectF, int i8) {
        float[] fArr = this.f4737h;
        o[] oVarArr = this.f4730a;
        fArr[0] = oVarArr[i8].f4747c;
        fArr[1] = oVarArr[i8].f4748d;
        this.f4731b[i8].mapPoints(fArr);
        return (i8 == 1 || i8 == 3) ? Math.abs(rectF.centerX() - this.f4737h[0]) : Math.abs(rectF.centerY() - this.f4737h[1]);
    }

    public final f j(int i8, m mVar) {
        return i8 != 1 ? i8 != 2 ? i8 != 3 ? mVar.o() : mVar.p() : mVar.n() : mVar.h();
    }

    public final boolean k(Path path, int i8) {
        Path path2 = new Path();
        this.f4730a[i8].d(this.f4731b[i8], path2);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        path2.computeBounds(rectF, true);
        path.op(path2, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }

    public final void l(b bVar, int i8) {
        h(i8, bVar.f4740a).b(this.f4730a[i8], 90.0f, bVar.f4744e, bVar.f4742c, g(i8, bVar.f4740a));
        float fA = a(i8);
        this.f4731b[i8].reset();
        f(i8, bVar.f4742c, this.f4733d);
        Matrix matrix = this.f4731b[i8];
        PointF pointF = this.f4733d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.f4731b[i8].preRotate(fA);
    }

    public final void m(int i8) {
        this.f4737h[0] = this.f4730a[i8].i();
        this.f4737h[1] = this.f4730a[i8].j();
        this.f4731b[i8].mapPoints(this.f4737h);
        float fA = a(i8);
        this.f4732c[i8].reset();
        Matrix matrix = this.f4732c[i8];
        float[] fArr = this.f4737h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.f4732c[i8].preRotate(fA);
    }
}
