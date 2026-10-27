package n0;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;

/* JADX INFO: renamed from: n0.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1880i implements InterfaceC1859I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f19436a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RectF f19437b;
    public float[] c;
    public Matrix d;

    public C1880i(Path path) {
        this.f19436a = path;
    }

    public final m0.d c() {
        if (this.f19437b == null) {
            this.f19437b = new RectF();
        }
        RectF rectF = this.f19437b;
        A7.m.c(rectF);
        this.f19436a.computeBounds(rectF, true);
        return new m0.d(rectF.left, rectF.top, rectF.right, rectF.bottom);
    }

    public final boolean d(InterfaceC1859I interfaceC1859I, InterfaceC1859I interfaceC1859I2, int i7) {
        Path.Op op = i7 == 0 ? Path.Op.DIFFERENCE : i7 == 1 ? Path.Op.INTERSECT : i7 == 4 ? Path.Op.REVERSE_DIFFERENCE : i7 == 2 ? Path.Op.UNION : Path.Op.XOR;
        if (!(interfaceC1859I instanceof C1880i)) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        Path path = ((C1880i) interfaceC1859I).f19436a;
        if (interfaceC1859I2 instanceof C1880i) {
            return this.f19436a.op(path, ((C1880i) interfaceC1859I2).f19436a, op);
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
    }

    public final void e() {
        this.f19436a.reset();
    }

    public final void f(int i7) {
        this.f19436a.setFillType(i7 == 1 ? Path.FillType.EVEN_ODD : Path.FillType.WINDING);
    }

    public final void g(long j3) {
        Matrix matrix = this.d;
        if (matrix == null) {
            this.d = new Matrix();
        } else {
            matrix.reset();
        }
        Matrix matrix2 = this.d;
        A7.m.c(matrix2);
        matrix2.setTranslate(m0.c.d(j3), m0.c.e(j3));
        Matrix matrix3 = this.d;
        A7.m.c(matrix3);
        this.f19436a.transform(matrix3);
    }
}
