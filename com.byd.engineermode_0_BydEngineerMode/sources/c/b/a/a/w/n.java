package c.b.a.a.w;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;

/* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final o[] f2210a = new o[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Matrix[] f2211b = new Matrix[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix[] f2212c = new Matrix[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final PointF f2213d = new PointF();
    private final Path e = new Path();
    private final Path f = new Path();
    private final o g = new o();
    private final float[] h = new float[2];
    private final float[] i = new float[2];
    private boolean j = true;

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public interface a {
        void a(o oVar, Matrix matrix, int i);

        void b(o oVar, Matrix matrix, int i);
    }

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final m f2214a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f2215b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final RectF f2216c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f2217d;
        public final float e;

        b(m mVar, float f, RectF rectF, a aVar, Path path) {
            this.f2217d = aVar;
            this.f2214a = mVar;
            this.e = f;
            this.f2216c = rectF;
            this.f2215b = path;
        }
    }

    public n() {
        for (int i = 0; i < 4; i++) {
            this.f2210a[i] = new o();
            this.f2211b[i] = new Matrix();
            this.f2212c[i] = new Matrix();
        }
    }

    private float a(int i) {
        return (i + 1) * 90;
    }

    private void b(b bVar, int i) {
        this.h[0] = this.f2210a[i].k();
        this.h[1] = this.f2210a[i].l();
        this.f2211b[i].mapPoints(this.h);
        if (i == 0) {
            Path path = bVar.f2215b;
            float[] fArr = this.h;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = bVar.f2215b;
            float[] fArr2 = this.h;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.f2210a[i].d(this.f2211b[i], bVar.f2215b);
        a aVar = bVar.f2217d;
        if (aVar != null) {
            aVar.b(this.f2210a[i], this.f2211b[i], i);
        }
    }

    private void c(b bVar, int i) {
        int i2 = (i + 1) % 4;
        this.h[0] = this.f2210a[i].i();
        this.h[1] = this.f2210a[i].j();
        this.f2211b[i].mapPoints(this.h);
        this.i[0] = this.f2210a[i2].k();
        this.i[1] = this.f2210a[i2].l();
        this.f2211b[i2].mapPoints(this.i);
        float f = this.h[0];
        float[] fArr = this.i;
        float fMax = Math.max(((float) Math.hypot(f - fArr[0], r1[1] - fArr[1])) - 0.001f, 0.0f);
        float fI = i(bVar.f2216c, i);
        this.g.n(0.0f, 0.0f);
        f fVarJ = j(i, bVar.f2214a);
        fVarJ.b(fMax, fI, bVar.e, this.g);
        Path path = new Path();
        this.g.d(this.f2212c[i], path);
        if (this.j && Build.VERSION.SDK_INT >= 19 && (fVarJ.a() || k(path, i) || k(path, i2))) {
            path.op(path, this.f, Path.Op.DIFFERENCE);
            this.h[0] = this.g.k();
            this.h[1] = this.g.l();
            this.f2212c[i].mapPoints(this.h);
            Path path2 = this.e;
            float[] fArr2 = this.h;
            path2.moveTo(fArr2[0], fArr2[1]);
            this.g.d(this.f2212c[i], this.e);
        } else {
            this.g.d(this.f2212c[i], bVar.f2215b);
        }
        a aVar = bVar.f2217d;
        if (aVar != null) {
            aVar.a(this.g, this.f2212c[i], i);
        }
    }

    private void f(int i, RectF rectF, PointF pointF) {
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

    private c g(int i, m mVar) {
        return i != 1 ? i != 2 ? i != 3 ? mVar.t() : mVar.r() : mVar.j() : mVar.l();
    }

    private d h(int i, m mVar) {
        return i != 1 ? i != 2 ? i != 3 ? mVar.s() : mVar.q() : mVar.i() : mVar.k();
    }

    private float i(RectF rectF, int i) {
        float[] fArr = this.h;
        o[] oVarArr = this.f2210a;
        fArr[0] = oVarArr[i].f2220c;
        fArr[1] = oVarArr[i].f2221d;
        this.f2211b[i].mapPoints(fArr);
        return (i == 1 || i == 3) ? Math.abs(rectF.centerX() - this.h[0]) : Math.abs(rectF.centerY() - this.h[1]);
    }

    private f j(int i, m mVar) {
        return i != 1 ? i != 2 ? i != 3 ? mVar.o() : mVar.p() : mVar.n() : mVar.h();
    }

    private boolean k(Path path, int i) {
        Path path2 = new Path();
        this.f2210a[i].d(this.f2211b[i], path2);
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

    private void l(b bVar, int i) {
        h(i, bVar.f2214a).b(this.f2210a[i], 90.0f, bVar.e, bVar.f2216c, g(i, bVar.f2214a));
        float fA = a(i);
        this.f2211b[i].reset();
        f(i, bVar.f2216c, this.f2213d);
        Matrix matrix = this.f2211b[i];
        PointF pointF = this.f2213d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.f2211b[i].preRotate(fA);
    }

    private void m(int i) {
        this.h[0] = this.f2210a[i].i();
        this.h[1] = this.f2210a[i].j();
        this.f2211b[i].mapPoints(this.h);
        float fA = a(i);
        this.f2212c[i].reset();
        Matrix matrix = this.f2212c[i];
        float[] fArr = this.h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.f2212c[i].preRotate(fA);
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
}
