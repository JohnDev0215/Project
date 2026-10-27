package defpackage;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;

/* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class nk {
    public final ok[] a = new ok[4];
    public final Matrix[] b = new Matrix[4];
    public final Matrix[] c = new Matrix[4];
    public final PointF d = new PointF();
    public final Path e = new Path();
    public final Path f = new Path();
    public final ok g = new ok();
    public final float[] h = new float[2];
    public final float[] i = new float[2];
    public boolean j = true;

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public interface a {
        void a(ok okVar, Matrix matrix, int i);

        void b(ok okVar, Matrix matrix, int i);
    }

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public static final class b {
        public final mk a;
        public final Path b;
        public final RectF c;
        public final a d;
        public final float e;

        public b(mk mkVar, float f, RectF rectF, a aVar, Path path) {
            this.d = aVar;
            this.a = mkVar;
            this.e = f;
            this.c = rectF;
            this.b = path;
        }
    }

    public nk() {
        for (int i = 0; i < 4; i++) {
            this.a[i] = new ok();
            this.b[i] = new Matrix();
            this.c[i] = new Matrix();
        }
    }

    public final float a(int i) {
        return (i + 1) * 90;
    }

    public final void b(b bVar, int i) {
        this.h[0] = this.a[i].k();
        this.h[1] = this.a[i].l();
        this.b[i].mapPoints(this.h);
        if (i == 0) {
            Path path = bVar.b;
            float[] fArr = this.h;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = bVar.b;
            float[] fArr2 = this.h;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.a[i].d(this.b[i], bVar.b);
        a aVar = bVar.d;
        if (aVar != null) {
            aVar.b(this.a[i], this.b[i], i);
        }
    }

    public final void c(b bVar, int i) {
        int i2 = (i + 1) % 4;
        this.h[0] = this.a[i].i();
        this.h[1] = this.a[i].j();
        this.b[i].mapPoints(this.h);
        this.i[0] = this.a[i2].k();
        this.i[1] = this.a[i2].l();
        this.b[i2].mapPoints(this.i);
        float f = this.h[0];
        float[] fArr = this.i;
        float fMax = Math.max(((float) Math.hypot(f - fArr[0], r1[1] - fArr[1])) - 0.001f, 0.0f);
        float fI = i(bVar.c, i);
        this.g.n(0.0f, 0.0f);
        fk fkVarJ = j(i, bVar.a);
        fkVarJ.b(fMax, fI, bVar.e, this.g);
        Path path = new Path();
        this.g.d(this.c[i], path);
        if (this.j && Build.VERSION.SDK_INT >= 19 && (fkVarJ.a() || k(path, i) || k(path, i2))) {
            path.op(path, this.f, Path.Op.DIFFERENCE);
            this.h[0] = this.g.k();
            this.h[1] = this.g.l();
            this.c[i].mapPoints(this.h);
            Path path2 = this.e;
            float[] fArr2 = this.h;
            path2.moveTo(fArr2[0], fArr2[1]);
            this.g.d(this.c[i], this.e);
        } else {
            this.g.d(this.c[i], bVar.b);
        }
        a aVar = bVar.d;
        if (aVar != null) {
            aVar.a(this.g, this.c[i], i);
        }
    }

    public void d(mk mkVar, float f, RectF rectF, Path path) {
        e(mkVar, f, rectF, null, path);
    }

    public void e(mk mkVar, float f, RectF rectF, a aVar, Path path) {
        path.rewind();
        this.e.rewind();
        this.f.rewind();
        this.f.addRect(rectF, Path.Direction.CW);
        b bVar = new b(mkVar, f, rectF, aVar, path);
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

    public final ck g(int i, mk mkVar) {
        return i != 1 ? i != 2 ? i != 3 ? mkVar.t() : mkVar.r() : mkVar.j() : mkVar.l();
    }

    public final dk h(int i, mk mkVar) {
        return i != 1 ? i != 2 ? i != 3 ? mkVar.s() : mkVar.q() : mkVar.i() : mkVar.k();
    }

    public final float i(RectF rectF, int i) {
        float[] fArr = this.h;
        ok[] okVarArr = this.a;
        fArr[0] = okVarArr[i].c;
        fArr[1] = okVarArr[i].d;
        this.b[i].mapPoints(fArr);
        return (i == 1 || i == 3) ? Math.abs(rectF.centerX() - this.h[0]) : Math.abs(rectF.centerY() - this.h[1]);
    }

    public final fk j(int i, mk mkVar) {
        return i != 1 ? i != 2 ? i != 3 ? mkVar.o() : mkVar.p() : mkVar.n() : mkVar.h();
    }

    public final boolean k(Path path, int i) {
        Path path2 = new Path();
        this.a[i].d(this.b[i], path2);
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
        h(i, bVar.a).b(this.a[i], 90.0f, bVar.e, bVar.c, g(i, bVar.a));
        float fA = a(i);
        this.b[i].reset();
        f(i, bVar.c, this.d);
        Matrix matrix = this.b[i];
        PointF pointF = this.d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.b[i].preRotate(fA);
    }

    public final void m(int i) {
        this.h[0] = this.a[i].i();
        this.h[1] = this.a[i].j();
        this.b[i].mapPoints(this.h);
        float fA = a(i);
        this.c[i].reset();
        Matrix matrix = this.c[i];
        float[] fArr = this.h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.c[i].preRotate(fA);
    }
}
