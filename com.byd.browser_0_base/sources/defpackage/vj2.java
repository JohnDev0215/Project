package defpackage;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import defpackage.sc1;
import java.util.ArrayList;
import java.util.BitSet;

/* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class vj2 {
    public final xj2[] a = new xj2[4];
    public final Matrix[] b = new Matrix[4];
    public final Matrix[] c = new Matrix[4];
    public final PointF d = new PointF();
    public final Path e = new Path();
    public final Path f = new Path();
    public final xj2 g = new xj2();
    public final float[] h = new float[2];
    public final float[] i = new float[2];
    public final Path j = new Path();
    public final Path k = new Path();
    public final boolean l = true;

    /* JADX INFO: compiled from: ShapeAppearancePathProvider.java */
    public static class a {
        public static final vj2 a = new vj2();
    }

    public vj2() {
        for (int i = 0; i < 4; i++) {
            this.a[i] = new xj2();
            this.b[i] = new Matrix();
            this.c[i] = new Matrix();
        }
    }

    public final void a(uj2 uj2Var, float f, RectF rectF, sc1.a aVar, Path path) {
        int i;
        Matrix[] matrixArr;
        float[] fArr;
        Matrix[] matrixArr2;
        xj2[] xj2VarArr;
        Path path2;
        sc1.a aVar2;
        char c;
        Path path3;
        vj2 vj2Var = this;
        uj2 uj2Var2 = uj2Var;
        sc1.a aVar3 = aVar;
        Path path4 = path;
        path.rewind();
        Path path5 = vj2Var.e;
        path5.rewind();
        Path path6 = vj2Var.f;
        path6.rewind();
        path6.addRect(rectF, Path.Direction.CW);
        int i2 = 0;
        while (true) {
            i = 4;
            matrixArr = vj2Var.c;
            fArr = vj2Var.h;
            matrixArr2 = vj2Var.b;
            xj2VarArr = vj2Var.a;
            if (i2 >= 4) {
                break;
            }
            l00 l00Var = i2 != 1 ? i2 != 2 ? i2 != 3 ? uj2Var2.f : uj2Var2.e : uj2Var2.h : uj2Var2.g;
            d70 d70Var = i2 != 1 ? i2 != 2 ? i2 != 3 ? uj2Var2.b : uj2Var2.a : uj2Var2.d : uj2Var2.c;
            xj2 xj2Var = xj2VarArr[i2];
            d70Var.getClass();
            d70Var.c(f, l00Var.a(rectF), xj2Var);
            int i3 = i2 + 1;
            float f2 = i3 * 90;
            matrixArr2[i2].reset();
            PointF pointF = vj2Var.d;
            if (i2 == 1) {
                path3 = path5;
                pointF.set(rectF.right, rectF.bottom);
            } else if (i2 == 2) {
                path3 = path5;
                pointF.set(rectF.left, rectF.bottom);
            } else if (i2 != 3) {
                path3 = path5;
                pointF.set(rectF.right, rectF.top);
            } else {
                path3 = path5;
                pointF.set(rectF.left, rectF.top);
            }
            matrixArr2[i2].setTranslate(pointF.x, pointF.y);
            matrixArr2[i2].preRotate(f2);
            xj2 xj2Var2 = xj2VarArr[i2];
            fArr[0] = xj2Var2.c;
            fArr[1] = xj2Var2.d;
            matrixArr2[i2].mapPoints(fArr);
            matrixArr[i2].reset();
            matrixArr[i2].setTranslate(fArr[0], fArr[1]);
            matrixArr[i2].preRotate(f2);
            i2 = i3;
            path5 = path3;
        }
        Path path7 = path5;
        char c2 = 1;
        char c3 = 0;
        int i4 = 0;
        while (i4 < i) {
            xj2 xj2Var3 = xj2VarArr[i4];
            fArr[c3] = xj2Var3.a;
            fArr[c2] = xj2Var3.b;
            matrixArr2[i4].mapPoints(fArr);
            if (i4 == 0) {
                path4.moveTo(fArr[c3], fArr[c2]);
            } else {
                path4.lineTo(fArr[c3], fArr[c2]);
            }
            xj2VarArr[i4].c(matrixArr2[i4], path4);
            if (aVar3 != null) {
                xj2 xj2Var4 = xj2VarArr[i4];
                Matrix matrix = matrixArr2[i4];
                sc1 sc1Var = sc1.this;
                BitSet bitSet = sc1Var.h;
                xj2Var4.getClass();
                bitSet.set(i4, false);
                xj2Var4.b(xj2Var4.f);
                sc1Var.f[i4] = new wj2(new ArrayList(xj2Var4.h), new Matrix(matrix));
            }
            int i5 = i4 + 1;
            int i6 = i5 % 4;
            xj2 xj2Var5 = xj2VarArr[i4];
            fArr[0] = xj2Var5.c;
            fArr[1] = xj2Var5.d;
            matrixArr2[i4].mapPoints(fArr);
            xj2 xj2Var6 = xj2VarArr[i6];
            float f3 = xj2Var6.a;
            float[] fArr2 = vj2Var.i;
            fArr2[0] = f3;
            fArr2[1] = xj2Var6.b;
            matrixArr2[i6].mapPoints(fArr2);
            float fMax = Math.max(((float) Math.hypot(fArr[0] - fArr2[0], fArr[1] - fArr2[1])) - 0.001f, 0.0f);
            xj2 xj2Var7 = xj2VarArr[i4];
            fArr[0] = xj2Var7.c;
            fArr[1] = xj2Var7.d;
            matrixArr2[i4].mapPoints(fArr);
            float fAbs = (i4 == 1 || i4 == 3) ? Math.abs(rectF.centerX() - fArr[0]) : Math.abs(rectF.centerY() - fArr[1]);
            xj2 xj2Var8 = vj2Var.g;
            xj2Var8.e(0.0f, 0.0f, 270.0f, 0.0f);
            if0 if0Var = i4 != 1 ? i4 != 2 ? i4 != 3 ? uj2Var2.j : uj2Var2.i : uj2Var2.l : uj2Var2.k;
            if0Var.e(fMax, fAbs, f, xj2Var8);
            Path path8 = vj2Var.j;
            path8.reset();
            xj2Var8.c(matrixArr[i4], path8);
            if (vj2Var.l && (if0Var.b() || vj2Var.b(path8, i4) || vj2Var.b(path8, i6))) {
                path8.op(path8, path6, Path.Op.DIFFERENCE);
                fArr[0] = xj2Var8.a;
                fArr[1] = xj2Var8.b;
                matrixArr[i4].mapPoints(fArr);
                path2 = path7;
                path2.moveTo(fArr[0], fArr[1]);
                xj2Var8.c(matrixArr[i4], path2);
                aVar2 = aVar;
                path4 = path;
            } else {
                path2 = path7;
                path4 = path;
                xj2Var8.c(matrixArr[i4], path4);
                aVar2 = aVar;
            }
            if (aVar2 != null) {
                Matrix matrix2 = matrixArr[i4];
                sc1 sc1Var2 = sc1.this;
                c = 0;
                sc1Var2.h.set(i4 + 4, false);
                xj2Var8.b(xj2Var8.f);
                sc1Var2.g[i4] = new wj2(new ArrayList(xj2Var8.h), new Matrix(matrix2));
            } else {
                c = 0;
            }
            vj2Var = this;
            c3 = c;
            aVar3 = aVar2;
            path7 = path2;
            i4 = i5;
            c2 = 1;
            i = 4;
            uj2Var2 = uj2Var;
        }
        Path path9 = path7;
        path.close();
        path9.close();
        if (path9.isEmpty()) {
            return;
        }
        path4.op(path9, Path.Op.UNION);
    }

    public final boolean b(Path path, int i) {
        Path path2 = this.k;
        path2.reset();
        this.a[i].c(this.b[i], path2);
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
}
