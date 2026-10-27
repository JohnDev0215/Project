package c.b.a.a.v;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;

/* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o[] f1873a = new o[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix[] f1874b = new Matrix[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix[] f1875c = new Matrix[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final PointF f1876d = new PointF();
    public final Path e = new Path();
    public final Path f = new Path();
    public final o g = new o();
    public final float[] h = new float[2];
    public final float[] i = new float[2];
    public boolean j = true;

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public interface a {
        void a(o oVar, Matrix matrix, int i);

        void b(o oVar, Matrix matrix, int i);
    }

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final m f1877a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f1878b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final RectF f1879c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f1880d;
        public final float e;

        public b(m mVar, float f, RectF rectF, a aVar, Path path) {
            this.f1880d = aVar;
            this.f1877a = mVar;
            this.e = f;
            this.f1879c = rectF;
            this.f1878b = path;
        }
    }

    public n() {
        for (int i = 0; i < 4; i++) {
            this.f1873a[i] = new o();
            this.f1874b[i] = new Matrix();
            this.f1875c[i] = new Matrix();
        }
    }

    public final float a(int i) {
        return (i + 1) * 90;
    }

    public final void b(b bVar, int i) {
        this.h[0] = this.f1873a[i].k();
        this.h[1] = this.f1873a[i].l();
        this.f1874b[i].mapPoints(this.h);
        if (i == 0) {
            Path path = bVar.f1878b;
            float[] fArr = this.h;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = bVar.f1878b;
            float[] fArr2 = this.h;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.f1873a[i].d(this.f1874b[i], bVar.f1878b);
        a aVar = bVar.f1880d;
        if (aVar != null) {
            aVar.b(this.f1873a[i], this.f1874b[i], i);
        }
    }

    public final void c(b bVar, int i) {
        int i2 = (i + 1) % 4;
        this.h[0] = this.f1873a[i].i();
        this.h[1] = this.f1873a[i].j();
        this.f1874b[i].mapPoints(this.h);
        this.i[0] = this.f1873a[i2].k();
        this.i[1] = this.f1873a[i2].l();
        this.f1874b[i2].mapPoints(this.i);
        float f = this.h[0];
        float[] fArr = this.i;
        float fMax = Math.max(((float) Math.hypot(f - fArr[0], r1[1] - fArr[1])) - 0.001f, 0.0f);
        float fI = i(bVar.f1879c, i);
        this.g.n(0.0f, 0.0f);
        f fVarJ = j(i, bVar.f1877a);
        fVarJ.b(fMax, fI, bVar.e, this.g);
        Path path = new Path();
        this.g.d(this.f1875c[i], path);
        if (this.j && Build.VERSION.SDK_INT >= 19 && (fVarJ.a() || k(path, i) || k(path, i2))) {
            path.op(path, this.f, Path.Op.DIFFERENCE);
            this.h[0] = this.g.k();
            this.h[1] = this.g.l();
            this.f1875c[i].mapPoints(this.h);
            Path path2 = this.e;
            float[] fArr2 = this.h;
            path2.moveTo(fArr2[0], fArr2[1]);
            this.g.d(this.f1875c[i], this.e);
        } else {
            this.g.d(this.f1875c[i], bVar.f1878b);
        }
        a aVar = bVar.f1880d;
        if (aVar != null) {
            aVar.a(this.g, this.f1875c[i], i);
        }
    }

    public void d(m mVar, float f, RectF rectF, Path path) {
        e(mVar, f, rectF, null, path);
    }

    public void e(m mVar, float f, RectF rectF, a aVar, Path path) {
        path.rewind();
        this.e.rewind();
        this.f.rewind();
        this.f.addRect(rectF, Path.Direction.CW);
        b bVar = new b(mVar, f, rectF, aVar, path);
        for (int i = 0; i < 4; i++) {
            l(bVar, i);
            m(i);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            b(bVar, i2);
            c(bVar, i2);
        }
        path.close();
        this.e.close();
        if (Build.VERSION.SDK_INT < 19 || this.e.isEmpty()) {
            return;
        }
        path.op(this.e, Path.Op.UNION);
    }

    public final void f(int i, RectF rectF, PointF pointF) {
        if (i == 1) {
            pointF.set(rectF.right, rectF.bottom);
            return;
        }
        if (i == 2) {
            pointF.set(rectF.left, rectF.bottom);
        } else if (i != 3) {
            pointF.set(rectF.right, rectF.top);
        } else {
            pointF.set(rectF.left, rectF.top);
        }
    }

    public final c g(int i, m mVar) {
        return i != 1 ? i != 2 ? i != 3 ? mVar.t() : mVar.r() : mVar.j() : mVar.l();
    }

    public final d h(int i, m mVar) {
        return i != 1 ? i != 2 ? i != 3 ? mVar.s() : mVar.q() : mVar.i() : mVar.k();
    }

    public final float i(RectF rectF, int i) {
        float[] fArr = this.h;
        o[] oVarArr = this.f1873a;
        fArr[0] = oVarArr[i].f1883c;
        fArr[1] = oVarArr[i].f1884d;
        this.f1874b[i].mapPoints(fArr);
        return (i == 1 || i == 3) ? Math.abs(rectF.centerX() - this.h[0]) : Math.abs(rectF.centerY() - this.h[1]);
    }

    public final f j(int i, m mVar) {
        return i != 1 ? i != 2 ? i != 3 ? mVar.o() : mVar.p() : mVar.n() : mVar.h();
    }

    public final boolean k(Path path, int i) {
        Path path2 = new Path();
        this.f1873a[i].d(this.f1874b[i], path2);
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

    public final void l(b bVar, int i) {
        h(i, bVar.f1877a).b(this.f1873a[i], 90.0f, bVar.e, bVar.f1879c, g(i, bVar.f1877a));
        float fA = a(i);
        this.f1874b[i].reset();
        f(i, bVar.f1879c, this.f1876d);
        Matrix matrix = this.f1874b[i];
        PointF pointF = this.f1876d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.f1874b[i].preRotate(fA);
    }

    public final void m(int i) {
        this.h[0] = this.f1873a[i].i();
        this.h[1] = this.f1873a[i].j();
        this.f1874b[i].mapPoints(this.h);
        float fA = a(i);
        this.f1875c[i].reset();
        Matrix matrix = this.f1875c[i];
        float[] fArr = this.h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.f1875c[i].preRotate(fA);
    }
}
