package b.a.a.a.b0;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final m[] f1670a = new m[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Matrix[] f1671b = new Matrix[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix[] f1672c = new Matrix[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final PointF f1673d = new PointF();
    private final Path e = new Path();
    private final Path f = new Path();
    private final m g = new m();
    private final float[] h = new float[2];
    private final float[] i = new float[2];
    private boolean j = true;

    public interface a {
        void a(m mVar, Matrix matrix, int i);

        void b(m mVar, Matrix matrix, int i);
    }

    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final k f1674a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f1675b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final RectF f1676c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a f1677d;
        public final float e;

        b(k kVar, float f, RectF rectF, a aVar, Path path) {
            this.f1677d = aVar;
            this.f1674a = kVar;
            this.e = f;
            this.f1676c = rectF;
            this.f1675b = path;
        }
    }

    public l() {
        for (int i = 0; i < 4; i++) {
            this.f1670a[i] = new m();
            this.f1671b[i] = new Matrix();
            this.f1672c[i] = new Matrix();
        }
    }

    private float a(int i) {
        return (i + 1) * 90;
    }

    private void b(b bVar, int i) {
        this.h[0] = this.f1670a[i].k();
        this.h[1] = this.f1670a[i].l();
        this.f1671b[i].mapPoints(this.h);
        Path path = bVar.f1675b;
        float[] fArr = this.h;
        if (i == 0) {
            path.moveTo(fArr[0], fArr[1]);
        } else {
            path.lineTo(fArr[0], fArr[1]);
        }
        this.f1670a[i].d(this.f1671b[i], bVar.f1675b);
        a aVar = bVar.f1677d;
        if (aVar != null) {
            aVar.b(this.f1670a[i], this.f1671b[i], i);
        }
    }

    private void c(b bVar, int i) {
        m mVar;
        Matrix matrix;
        Path path;
        int i2 = (i + 1) % 4;
        this.h[0] = this.f1670a[i].i();
        this.h[1] = this.f1670a[i].j();
        this.f1671b[i].mapPoints(this.h);
        this.i[0] = this.f1670a[i2].k();
        this.i[1] = this.f1670a[i2].l();
        this.f1671b[i2].mapPoints(this.i);
        float f = this.h[0];
        float[] fArr = this.i;
        float fMax = Math.max(((float) Math.hypot(f - fArr[0], r1[1] - fArr[1])) - 0.001f, 0.0f);
        float fI = i(bVar.f1676c, i);
        this.g.n(0.0f, 0.0f);
        f fVarJ = j(i, bVar.f1674a);
        fVarJ.b(fMax, fI, bVar.e, this.g);
        Path path2 = new Path();
        this.g.d(this.f1672c[i], path2);
        if (this.j && Build.VERSION.SDK_INT >= 19 && (fVarJ.a() || k(path2, i) || k(path2, i2))) {
            path2.op(path2, this.f, Path.Op.DIFFERENCE);
            this.h[0] = this.g.k();
            this.h[1] = this.g.l();
            this.f1672c[i].mapPoints(this.h);
            Path path3 = this.e;
            float[] fArr2 = this.h;
            path3.moveTo(fArr2[0], fArr2[1]);
            mVar = this.g;
            matrix = this.f1672c[i];
            path = this.e;
        } else {
            mVar = this.g;
            matrix = this.f1672c[i];
            path = bVar.f1675b;
        }
        mVar.d(matrix, path);
        a aVar = bVar.f1677d;
        if (aVar != null) {
            aVar.a(this.g, this.f1672c[i], i);
        }
    }

    private void f(int i, RectF rectF, PointF pointF) {
        float f;
        float f2;
        if (i == 1) {
            f = rectF.right;
        } else {
            if (i != 2) {
                f = i != 3 ? rectF.right : rectF.left;
                f2 = rectF.top;
                pointF.set(f, f2);
            }
            f = rectF.left;
        }
        f2 = rectF.bottom;
        pointF.set(f, f2);
    }

    private c g(int i, k kVar) {
        return i != 1 ? i != 2 ? i != 3 ? kVar.t() : kVar.r() : kVar.j() : kVar.l();
    }

    private d h(int i, k kVar) {
        return i != 1 ? i != 2 ? i != 3 ? kVar.s() : kVar.q() : kVar.i() : kVar.k();
    }

    private float i(RectF rectF, int i) {
        float fCenterX;
        float f;
        float[] fArr = this.h;
        m[] mVarArr = this.f1670a;
        fArr[0] = mVarArr[i].f1680c;
        fArr[1] = mVarArr[i].f1681d;
        this.f1671b[i].mapPoints(fArr);
        if (i == 1 || i == 3) {
            fCenterX = rectF.centerX();
            f = this.h[0];
        } else {
            fCenterX = rectF.centerY();
            f = this.h[1];
        }
        return Math.abs(fCenterX - f);
    }

    private f j(int i, k kVar) {
        return i != 1 ? i != 2 ? i != 3 ? kVar.o() : kVar.p() : kVar.n() : kVar.h();
    }

    private boolean k(Path path, int i) {
        Path path2 = new Path();
        this.f1670a[i].d(this.f1671b[i], path2);
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
        h(i, bVar.f1674a).b(this.f1670a[i], 90.0f, bVar.e, bVar.f1676c, g(i, bVar.f1674a));
        float fA = a(i);
        this.f1671b[i].reset();
        f(i, bVar.f1676c, this.f1673d);
        Matrix matrix = this.f1671b[i];
        PointF pointF = this.f1673d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.f1671b[i].preRotate(fA);
    }

    private void m(int i) {
        this.h[0] = this.f1670a[i].i();
        this.h[1] = this.f1670a[i].j();
        this.f1671b[i].mapPoints(this.h);
        float fA = a(i);
        this.f1672c[i].reset();
        Matrix matrix = this.f1672c[i];
        float[] fArr = this.h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.f1672c[i].preRotate(fA);
    }

    public void d(k kVar, float f, RectF rectF, Path path) {
        e(kVar, f, rectF, null, path);
    }

    public void e(k kVar, float f, RectF rectF, a aVar, Path path) {
        path.rewind();
        this.e.rewind();
        this.f.rewind();
        this.f.addRect(rectF, Path.Direction.CW);
        b bVar = new b(kVar, f, rectF, aVar, path);
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
