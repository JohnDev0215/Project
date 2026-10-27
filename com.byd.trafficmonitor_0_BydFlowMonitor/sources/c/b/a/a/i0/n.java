package c.b.a.a.i0;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;

/* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o[] f3899a = new o[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix[] f3900b = new Matrix[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix[] f3901c = new Matrix[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final PointF f3902d = new PointF();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Path f3903e = new Path();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Path f3904f = new Path();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final o f3905g = new o();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float[] f3906h = new float[2];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float[] f3907i = new float[2];
    public boolean j = true;

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public interface a {
        void a(o oVar, Matrix matrix, int i2);

        void b(o oVar, Matrix matrix, int i2);
    }

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final m f3908a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f3909b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final RectF f3910c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f3911d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f3912e;

        public b(m mVar, float f2, RectF rectF, a aVar, Path path) {
            this.f3911d = aVar;
            this.f3908a = mVar;
            this.f3912e = f2;
            this.f3910c = rectF;
            this.f3909b = path;
        }
    }

    public n() {
        for (int i2 = 0; i2 < 4; i2++) {
            this.f3899a[i2] = new o();
            this.f3900b[i2] = new Matrix();
            this.f3901c[i2] = new Matrix();
        }
    }

    public final float a(int i2) {
        return (i2 + 1) * 90;
    }

    public final void b(b bVar, int i2) {
        this.f3906h[0] = this.f3899a[i2].k();
        this.f3906h[1] = this.f3899a[i2].l();
        this.f3900b[i2].mapPoints(this.f3906h);
        if (i2 == 0) {
            Path path = bVar.f3909b;
            float[] fArr = this.f3906h;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = bVar.f3909b;
            float[] fArr2 = this.f3906h;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.f3899a[i2].d(this.f3900b[i2], bVar.f3909b);
        a aVar = bVar.f3911d;
        if (aVar != null) {
            aVar.b(this.f3899a[i2], this.f3900b[i2], i2);
        }
    }

    public final void c(b bVar, int i2) {
        int i3 = (i2 + 1) % 4;
        this.f3906h[0] = this.f3899a[i2].i();
        this.f3906h[1] = this.f3899a[i2].j();
        this.f3900b[i2].mapPoints(this.f3906h);
        this.f3907i[0] = this.f3899a[i3].k();
        this.f3907i[1] = this.f3899a[i3].l();
        this.f3900b[i3].mapPoints(this.f3907i);
        float f2 = this.f3906h[0];
        float[] fArr = this.f3907i;
        float fMax = Math.max(((float) Math.hypot(f2 - fArr[0], r1[1] - fArr[1])) - 0.001f, 0.0f);
        float fI = i(bVar.f3910c, i2);
        this.f3905g.n(0.0f, 0.0f);
        f fVarJ = j(i2, bVar.f3908a);
        fVarJ.b(fMax, fI, bVar.f3912e, this.f3905g);
        Path path = new Path();
        this.f3905g.d(this.f3901c[i2], path);
        if (this.j && Build.VERSION.SDK_INT >= 19 && (fVarJ.a() || k(path, i2) || k(path, i3))) {
            path.op(path, this.f3904f, Path.Op.DIFFERENCE);
            this.f3906h[0] = this.f3905g.k();
            this.f3906h[1] = this.f3905g.l();
            this.f3901c[i2].mapPoints(this.f3906h);
            Path path2 = this.f3903e;
            float[] fArr2 = this.f3906h;
            path2.moveTo(fArr2[0], fArr2[1]);
            this.f3905g.d(this.f3901c[i2], this.f3903e);
        } else {
            this.f3905g.d(this.f3901c[i2], bVar.f3909b);
        }
        a aVar = bVar.f3911d;
        if (aVar != null) {
            aVar.a(this.f3905g, this.f3901c[i2], i2);
        }
    }

    public void d(m mVar, float f2, RectF rectF, Path path) {
        e(mVar, f2, rectF, null, path);
    }

    public void e(m mVar, float f2, RectF rectF, a aVar, Path path) {
        path.rewind();
        this.f3903e.rewind();
        this.f3904f.rewind();
        this.f3904f.addRect(rectF, Path.Direction.CW);
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
        this.f3903e.close();
        if (Build.VERSION.SDK_INT < 19 || this.f3903e.isEmpty()) {
            return;
        }
        path.op(this.f3903e, Path.Op.UNION);
    }

    public final void f(int i2, RectF rectF, PointF pointF) {
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

    public final c g(int i2, m mVar) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? mVar.t() : mVar.r() : mVar.j() : mVar.l();
    }

    public final d h(int i2, m mVar) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? mVar.s() : mVar.q() : mVar.i() : mVar.k();
    }

    public final float i(RectF rectF, int i2) {
        float[] fArr = this.f3906h;
        o[] oVarArr = this.f3899a;
        fArr[0] = oVarArr[i2].f3915c;
        fArr[1] = oVarArr[i2].f3916d;
        this.f3900b[i2].mapPoints(fArr);
        return (i2 == 1 || i2 == 3) ? Math.abs(rectF.centerX() - this.f3906h[0]) : Math.abs(rectF.centerY() - this.f3906h[1]);
    }

    public final f j(int i2, m mVar) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? mVar.o() : mVar.p() : mVar.n() : mVar.h();
    }

    public final boolean k(Path path, int i2) {
        Path path2 = new Path();
        this.f3899a[i2].d(this.f3900b[i2], path2);
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

    public final void l(b bVar, int i2) {
        h(i2, bVar.f3908a).b(this.f3899a[i2], 90.0f, bVar.f3912e, bVar.f3910c, g(i2, bVar.f3908a));
        float fA = a(i2);
        this.f3900b[i2].reset();
        f(i2, bVar.f3910c, this.f3902d);
        Matrix matrix = this.f3900b[i2];
        PointF pointF = this.f3902d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.f3900b[i2].preRotate(fA);
    }

    public final void m(int i2) {
        this.f3906h[0] = this.f3899a[i2].i();
        this.f3906h[1] = this.f3899a[i2].j();
        this.f3900b[i2].mapPoints(this.f3906h);
        float fA = a(i2);
        this.f3901c[i2].reset();
        Matrix matrix = this.f3901c[i2];
        float[] fArr = this.f3906h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.f3901c[i2].preRotate(fA);
    }
}
