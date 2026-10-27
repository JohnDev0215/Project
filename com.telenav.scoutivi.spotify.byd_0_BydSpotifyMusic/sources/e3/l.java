package e3;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

/* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final m[] f6252a = new m[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Matrix[] f6253b = new Matrix[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix[] f6254c = new Matrix[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final PointF f6255d = new PointF();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Path f6256e = new Path();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Path f6257f = new Path();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final m f6258g = new m();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final float[] f6259h = new float[2];

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final float[] f6260i = new float[2];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final Path f6261j = new Path();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final Path f6262k = new Path();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f6263l = true;

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final l f6264a = new l();
    }

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public interface b {
        void a(m mVar, Matrix matrix, int i7);

        void b(m mVar, Matrix matrix, int i7);
    }

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final k f6265a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Path f6266b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final RectF f6267c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b f6268d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final float f6269e;

        c(k kVar, float f7, RectF rectF, b bVar, Path path) {
            this.f6268d = bVar;
            this.f6265a = kVar;
            this.f6269e = f7;
            this.f6267c = rectF;
            this.f6266b = path;
        }
    }

    public l() {
        for (int i7 = 0; i7 < 4; i7++) {
            this.f6252a[i7] = new m();
            this.f6253b[i7] = new Matrix();
            this.f6254c[i7] = new Matrix();
        }
    }

    private float a(int i7) {
        return (i7 + 1) * 90;
    }

    private void b(c cVar, int i7) {
        this.f6259h[0] = this.f6252a[i7].k();
        this.f6259h[1] = this.f6252a[i7].l();
        this.f6253b[i7].mapPoints(this.f6259h);
        if (i7 == 0) {
            Path path = cVar.f6266b;
            float[] fArr = this.f6259h;
            path.moveTo(fArr[0], fArr[1]);
        } else {
            Path path2 = cVar.f6266b;
            float[] fArr2 = this.f6259h;
            path2.lineTo(fArr2[0], fArr2[1]);
        }
        this.f6252a[i7].d(this.f6253b[i7], cVar.f6266b);
        b bVar = cVar.f6268d;
        if (bVar != null) {
            bVar.b(this.f6252a[i7], this.f6253b[i7], i7);
        }
    }

    private void c(c cVar, int i7) {
        int i8 = (i7 + 1) % 4;
        this.f6259h[0] = this.f6252a[i7].i();
        this.f6259h[1] = this.f6252a[i7].j();
        this.f6253b[i7].mapPoints(this.f6259h);
        this.f6260i[0] = this.f6252a[i8].k();
        this.f6260i[1] = this.f6252a[i8].l();
        this.f6253b[i8].mapPoints(this.f6260i);
        float f7 = this.f6259h[0];
        float[] fArr = this.f6260i;
        float fMax = Math.max(((float) Math.hypot(f7 - fArr[0], r1[1] - fArr[1])) - 0.001f, 0.0f);
        float fI = i(cVar.f6267c, i7);
        this.f6258g.n(0.0f, 0.0f);
        f fVarJ = j(i7, cVar.f6265a);
        fVarJ.b(fMax, fI, cVar.f6269e, this.f6258g);
        this.f6261j.reset();
        this.f6258g.d(this.f6254c[i7], this.f6261j);
        if (this.f6263l && (fVarJ.a() || l(this.f6261j, i7) || l(this.f6261j, i8))) {
            Path path = this.f6261j;
            path.op(path, this.f6257f, Path.Op.DIFFERENCE);
            this.f6259h[0] = this.f6258g.k();
            this.f6259h[1] = this.f6258g.l();
            this.f6254c[i7].mapPoints(this.f6259h);
            Path path2 = this.f6256e;
            float[] fArr2 = this.f6259h;
            path2.moveTo(fArr2[0], fArr2[1]);
            this.f6258g.d(this.f6254c[i7], this.f6256e);
        } else {
            this.f6258g.d(this.f6254c[i7], cVar.f6266b);
        }
        b bVar = cVar.f6268d;
        if (bVar != null) {
            bVar.a(this.f6258g, this.f6254c[i7], i7);
        }
    }

    private void f(int i7, RectF rectF, PointF pointF) {
        if (i7 == 1) {
            pointF.set(rectF.right, rectF.bottom);
            return;
        }
        if (i7 == 2) {
            pointF.set(rectF.left, rectF.bottom);
        } else if (i7 != 3) {
            pointF.set(rectF.right, rectF.top);
        } else {
            pointF.set(rectF.left, rectF.top);
        }
    }

    private e3.c g(int i7, k kVar) {
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? kVar.t() : kVar.r() : kVar.j() : kVar.l();
    }

    private d h(int i7, k kVar) {
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? kVar.s() : kVar.q() : kVar.i() : kVar.k();
    }

    private float i(RectF rectF, int i7) {
        float[] fArr = this.f6259h;
        m[] mVarArr = this.f6252a;
        fArr[0] = mVarArr[i7].f6272c;
        fArr[1] = mVarArr[i7].f6273d;
        this.f6253b[i7].mapPoints(fArr);
        return (i7 == 1 || i7 == 3) ? Math.abs(rectF.centerX() - this.f6259h[0]) : Math.abs(rectF.centerY() - this.f6259h[1]);
    }

    private f j(int i7, k kVar) {
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? kVar.o() : kVar.p() : kVar.n() : kVar.h();
    }

    public static l k() {
        return a.f6264a;
    }

    private boolean l(Path path, int i7) {
        this.f6262k.reset();
        this.f6252a[i7].d(this.f6253b[i7], this.f6262k);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        this.f6262k.computeBounds(rectF, true);
        path.op(this.f6262k, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }

    private void m(c cVar, int i7) {
        h(i7, cVar.f6265a).b(this.f6252a[i7], 90.0f, cVar.f6269e, cVar.f6267c, g(i7, cVar.f6265a));
        float fA = a(i7);
        this.f6253b[i7].reset();
        f(i7, cVar.f6267c, this.f6255d);
        Matrix matrix = this.f6253b[i7];
        PointF pointF = this.f6255d;
        matrix.setTranslate(pointF.x, pointF.y);
        this.f6253b[i7].preRotate(fA);
    }

    private void n(int i7) {
        this.f6259h[0] = this.f6252a[i7].i();
        this.f6259h[1] = this.f6252a[i7].j();
        this.f6253b[i7].mapPoints(this.f6259h);
        float fA = a(i7);
        this.f6254c[i7].reset();
        Matrix matrix = this.f6254c[i7];
        float[] fArr = this.f6259h;
        matrix.setTranslate(fArr[0], fArr[1]);
        this.f6254c[i7].preRotate(fA);
    }

    public void d(k kVar, float f7, RectF rectF, Path path) {
        e(kVar, f7, rectF, null, path);
    }

    public void e(k kVar, float f7, RectF rectF, b bVar, Path path) {
        path.rewind();
        this.f6256e.rewind();
        this.f6257f.rewind();
        this.f6257f.addRect(rectF, Path.Direction.CW);
        c cVar = new c(kVar, f7, rectF, bVar, path);
        for (int i7 = 0; i7 < 4; i7++) {
            m(cVar, i7);
            n(i7);
        }
        for (int i8 = 0; i8 < 4; i8++) {
            b(cVar, i8);
            c(cVar, i8);
        }
        path.close();
        this.f6256e.close();
        if (this.f6256e.isEmpty()) {
            return;
        }
        path.op(this.f6256e, Path.Op.UNION);
    }
}
