package d.b.a.a.w;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;

/* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final o[] f4869a = new o[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Matrix[] f4870b = new Matrix[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix[] f4871c = new Matrix[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final PointF f4872d = new PointF();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Path f4873e = new Path();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Path f4874f = new Path();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final o f4875g = new o();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final float[] f4876h = new float[2];
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
        public final m f4877a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f4878b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final RectF f4879c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f4880d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f4881e;

        b(m mVar, float f2, RectF rectF, a aVar, Path path) {
            this.f4880d = aVar;
            this.f4877a = mVar;
            this.f4881e = f2;
            this.f4879c = rectF;
            this.f4878b = path;
        }
    }

    public n() {
        for (int i = 0; i < 4; i++) {
            this.f4869a[i] = new o();
            this.f4870b[i] = new Matrix();
            this.f4871c[i] = new Matrix();
        }
    }

    private float a(int i) {
        return (i + 1) * 90;
    }

    private void b(b bVar, int i) {
        this.f4876h[0] = this.f4869a[i].k();
        this.f4876h[1] = this.f4869a[i].l();
        this.f4870b[i].mapPoints(this.f4876h);
        if (i == 0) {
            Path path = bVar.f4878b;
            float[] fArr = this.f4876h;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = bVar.f4878b;
            float[] fArr2 = this.f4876h;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.f4869a[i].d(this.f4870b[i], bVar.f4878b);
        a aVar = bVar.f4880d;
        if (aVar != null) {
            aVar.b(this.f4869a[i], this.f4870b[i], i);
        }
    }

    private void c(b bVar, int i) {
        int i2 = (i + 1) % 4;
        this.f4876h[0] = this.f4869a[i].i();
        this.f4876h[1] = this.f4869a[i].j();
        this.f4870b[i].mapPoints(this.f4876h);
        this.i[0] = this.f4869a[i2].k();
        this.i[1] = this.f4869a[i2].l();
        this.f4870b[i2].mapPoints(this.i);
        float f2 = this.f4876h[0];
        float[] fArr = this.i;
        float fMax = Math.max(((float) Math.hypot(f2 - fArr[0], r1[1] - fArr[1])) - 0.001f, 0.0f);
        float fI = i(bVar.f4879c, i);
        this.f4875g.n(0.0f, 0.0f);
        f fVarJ = j(i, bVar.f4877a);
        fVarJ.b(fMax, fI, bVar.f4881e, this.f4875g);
        Path path = new Path();
        this.f4875g.d(this.f4871c[i], path);
        if (this.j && Build.VERSION.SDK_INT >= 19 && (fVarJ.a() || k(path, i) || k(path, i2))) {
            path.op(path, this.f4874f, Path.Op.DIFFERENCE);
            this.f4876h[0] = this.f4875g.k();
            this.f4876h[1] = this.f4875g.l();
            this.f4871c[i].mapPoints(this.f4876h);
            Path path2 = this.f4873e;
            float[] fArr2 = this.f4876h;
            path2.moveTo(fArr2[0], fArr2[1]);
            this.f4875g.d(this.f4871c[i], this.f4873e);
        } else {
            this.f4875g.d(this.f4871c[i], bVar.f4878b);
        }
        a aVar = bVar.f4880d;
        if (aVar != null) {
            aVar.a(this.f4875g, this.f4871c[i], i);
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
        float[] fArr = this.f4876h;
        o[] oVarArr = this.f4869a;
        fArr[0] = oVarArr[i].f4884c;
        fArr[1] = oVarArr[i].f4885d;
        this.f4870b[i].mapPoints(fArr);
        return (i == 1 || i == 3) ? Math.abs(rectF.centerX() - this.f4876h[0]) : Math.abs(rectF.centerY() - this.f4876h[1]);
    }

    private f j(int i, m mVar) {
        return i != 1 ? i != 2 ? i != 3 ? mVar.o() : mVar.p() : mVar.n() : mVar.h();
    }

    private boolean k(Path path, int i) {
        Path path2 = new Path();
        this.f4869a[i].d(this.f4870b[i], path2);
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
        h(i, bVar.f4877a).b(this.f4869a[i], 90.0f, bVar.f4881e, bVar.f4879c, g(i, bVar.f4877a));
        float fA = a(i);
        this.f4870b[i].reset();
        f(i, bVar.f4879c, this.f4872d);
        Matrix matrix = this.f4870b[i];
        PointF pointF = this.f4872d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.f4870b[i].preRotate(fA);
    }

    private void m(int i) {
        this.f4876h[0] = this.f4869a[i].i();
        this.f4876h[1] = this.f4869a[i].j();
        this.f4870b[i].mapPoints(this.f4876h);
        float fA = a(i);
        this.f4871c[i].reset();
        Matrix matrix = this.f4871c[i];
        float[] fArr = this.f4876h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.f4871c[i].preRotate(fA);
    }

    public void d(m mVar, float f2, RectF rectF, Path path) {
        e(mVar, f2, rectF, null, path);
    }

    public void e(m mVar, float f2, RectF rectF, a aVar, Path path) {
        path.rewind();
        this.f4873e.rewind();
        this.f4874f.rewind();
        this.f4874f.addRect(rectF, Path.Direction.CW);
        b bVar = new b(mVar, f2, rectF, aVar, path);
        for (int i = 0; i < 4; i++) {
            l(bVar, i);
            m(i);
        }
        for (int i2 = 0; i2 < 4; i2++) {
            b(bVar, i2);
            c(bVar, i2);
        }
        path.close();
        this.f4873e.close();
        if (Build.VERSION.SDK_INT < 19 || this.f4873e.isEmpty()) {
            return;
        }
        path.op(this.f4873e, Path.Op.UNION);
    }
}
