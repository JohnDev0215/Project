package d.b.a.a.x;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;

/* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final o[] f4794a = new o[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Matrix[] f4795b = new Matrix[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix[] f4796c = new Matrix[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final PointF f4797d = new PointF();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Path f4798e = new Path();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Path f4799f = new Path();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final o f4800g = new o();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final float[] f4801h = new float[2];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final float[] f4802i = new float[2];
    private boolean j = true;

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public interface a {
        void a(o oVar, Matrix matrix, int i2);

        void b(o oVar, Matrix matrix, int i2);
    }

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final m f4803a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f4804b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final RectF f4805c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f4806d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f4807e;

        b(m mVar, float f2, RectF rectF, a aVar, Path path) {
            this.f4806d = aVar;
            this.f4803a = mVar;
            this.f4807e = f2;
            this.f4805c = rectF;
            this.f4804b = path;
        }
    }

    public n() {
        for (int i2 = 0; i2 < 4; i2++) {
            this.f4794a[i2] = new o();
            this.f4795b[i2] = new Matrix();
            this.f4796c[i2] = new Matrix();
        }
    }

    private float a(int i2) {
        return (i2 + 1) * 90;
    }

    private void b(b bVar, int i2) {
        this.f4801h[0] = this.f4794a[i2].k();
        this.f4801h[1] = this.f4794a[i2].l();
        this.f4795b[i2].mapPoints(this.f4801h);
        if (i2 == 0) {
            Path path = bVar.f4804b;
            float[] fArr = this.f4801h;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = bVar.f4804b;
            float[] fArr2 = this.f4801h;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.f4794a[i2].d(this.f4795b[i2], bVar.f4804b);
        a aVar = bVar.f4806d;
        if (aVar != null) {
            aVar.b(this.f4794a[i2], this.f4795b[i2], i2);
        }
    }

    private void c(b bVar, int i2) {
        int i3 = (i2 + 1) % 4;
        this.f4801h[0] = this.f4794a[i2].i();
        this.f4801h[1] = this.f4794a[i2].j();
        this.f4795b[i2].mapPoints(this.f4801h);
        this.f4802i[0] = this.f4794a[i3].k();
        this.f4802i[1] = this.f4794a[i3].l();
        this.f4795b[i3].mapPoints(this.f4802i);
        float f2 = this.f4801h[0];
        float[] fArr = this.f4802i;
        float fMax = Math.max(((float) Math.hypot(f2 - fArr[0], r1[1] - fArr[1])) - 0.001f, 0.0f);
        float fI = i(bVar.f4805c, i2);
        this.f4800g.n(0.0f, 0.0f);
        f fVarJ = j(i2, bVar.f4803a);
        fVarJ.c(fMax, fI, bVar.f4807e, this.f4800g);
        Path path = new Path();
        this.f4800g.d(this.f4796c[i2], path);
        if (this.j && Build.VERSION.SDK_INT >= 19 && (fVarJ.b() || k(path, i2) || k(path, i3))) {
            path.op(path, this.f4799f, Path.Op.DIFFERENCE);
            this.f4801h[0] = this.f4800g.k();
            this.f4801h[1] = this.f4800g.l();
            this.f4796c[i2].mapPoints(this.f4801h);
            Path path2 = this.f4798e;
            float[] fArr2 = this.f4801h;
            path2.moveTo(fArr2[0], fArr2[1]);
            this.f4800g.d(this.f4796c[i2], this.f4798e);
        } else {
            this.f4800g.d(this.f4796c[i2], bVar.f4804b);
        }
        a aVar = bVar.f4806d;
        if (aVar != null) {
            aVar.a(this.f4800g, this.f4796c[i2], i2);
        }
    }

    private void f(int i2, RectF rectF, PointF pointF) {
        if (i2 == 1) {
            pointF.set(rectF.right, rectF.bottom);
            return;
        }
        if (i2 == 2) {
            pointF.set(rectF.left, rectF.bottom);
        } else if (i2 != 3) {
            pointF.set(rectF.right, rectF.top);
        } else {
            pointF.set(rectF.left, rectF.top);
        }
    }

    private c g(int i2, m mVar) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? mVar.t() : mVar.r() : mVar.j() : mVar.l();
    }

    private d h(int i2, m mVar) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? mVar.s() : mVar.q() : mVar.i() : mVar.k();
    }

    private float i(RectF rectF, int i2) {
        float[] fArr = this.f4801h;
        o[] oVarArr = this.f4794a;
        fArr[0] = oVarArr[i2].f4810c;
        fArr[1] = oVarArr[i2].f4811d;
        this.f4795b[i2].mapPoints(fArr);
        return (i2 == 1 || i2 == 3) ? Math.abs(rectF.centerX() - this.f4801h[0]) : Math.abs(rectF.centerY() - this.f4801h[1]);
    }

    private f j(int i2, m mVar) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? mVar.o() : mVar.p() : mVar.n() : mVar.h();
    }

    private boolean k(Path path, int i2) {
        Path path2 = new Path();
        this.f4794a[i2].d(this.f4795b[i2], path2);
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

    private void l(b bVar, int i2) {
        h(i2, bVar.f4803a).b(this.f4794a[i2], 90.0f, bVar.f4807e, bVar.f4805c, g(i2, bVar.f4803a));
        float fA = a(i2);
        this.f4795b[i2].reset();
        f(i2, bVar.f4805c, this.f4797d);
        Matrix matrix = this.f4795b[i2];
        PointF pointF = this.f4797d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.f4795b[i2].preRotate(fA);
    }

    private void m(int i2) {
        this.f4801h[0] = this.f4794a[i2].i();
        this.f4801h[1] = this.f4794a[i2].j();
        this.f4795b[i2].mapPoints(this.f4801h);
        float fA = a(i2);
        this.f4796c[i2].reset();
        Matrix matrix = this.f4796c[i2];
        float[] fArr = this.f4801h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.f4796c[i2].preRotate(fA);
    }

    public void d(m mVar, float f2, RectF rectF, Path path) {
        e(mVar, f2, rectF, null, path);
    }

    public void e(m mVar, float f2, RectF rectF, a aVar, Path path) {
        path.rewind();
        this.f4798e.rewind();
        this.f4799f.rewind();
        this.f4799f.addRect(rectF, Path.Direction.CW);
        b bVar = new b(mVar, f2, rectF, aVar, path);
        for (int i2 = 0; i2 < 4; i2++) {
            l(bVar, i2);
            m(i2);
        }
        for (int i3 = 0; i3 < 4; i3++) {
            b(bVar, i3);
            c(bVar, i3);
        }
        path.close();
        this.f4798e.close();
        if (Build.VERSION.SDK_INT < 19 || this.f4798e.isEmpty()) {
            return;
        }
        path.op(this.f4798e, Path.Op.UNION);
    }
}
