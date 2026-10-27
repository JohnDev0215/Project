package p;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public final class qc2 {
    public final yc2[] a = new yc2[4];
    public final Matrix[] b = new Matrix[4];
    public final Matrix[] c = new Matrix[4];
    public final PointF d = new PointF();
    public final Path e = new Path();
    public final Path f = new Path();
    public final yc2 g = new yc2();
    public final float[] h = new float[2];
    public final float[] i = new float[2];
    public final Path j = new Path();
    public final Path k = new Path();
    public final boolean l = true;

    public qc2() {
        for (int i = 0; i < 4; i++) {
            this.a[i] = new yc2();
            this.b[i] = new Matrix();
            this.c[i] = new Matrix();
        }
    }

    public final void a(oc2 oc2Var, float f, RectF rectF, oh1 oh1Var, Path path) {
        int i;
        Matrix[] matrixArr;
        float[] fArr;
        Matrix[] matrixArr2;
        yc2[] yc2VarArr;
        Path path2;
        oh1 oh1Var2;
        int i2;
        qc2 qc2Var = this;
        oh1 oh1Var3 = oh1Var;
        Path path3 = path;
        path.rewind();
        Path path4 = qc2Var.e;
        path4.rewind();
        Path path5 = qc2Var.f;
        path5.rewind();
        path5.addRect(rectF, Path.Direction.CW);
        int i3 = 0;
        while (true) {
            i = 4;
            matrixArr = qc2Var.c;
            fArr = qc2Var.h;
            matrixArr2 = qc2Var.b;
            yc2VarArr = qc2Var.a;
            if (i3 >= 4) {
                break;
            }
            v10 v10Var = i3 != 1 ? i3 != 2 ? i3 != 3 ? oc2Var.f : oc2Var.e : oc2Var.h : oc2Var.g;
            l13 l13Var = i3 != 1 ? i3 != 2 ? i3 != 3 ? oc2Var.b : oc2Var.a : oc2Var.d : oc2Var.c;
            yc2 yc2Var = yc2VarArr[i3];
            l13Var.getClass();
            l13Var.e(f, v10Var.a(rectF), yc2Var);
            int i4 = i3 + 1;
            float f2 = i4 * 90;
            matrixArr2[i3].reset();
            PointF pointF = qc2Var.d;
            if (i3 == 1) {
                i2 = i4;
                pointF.set(rectF.right, rectF.bottom);
            } else if (i3 == 2) {
                i2 = i4;
                pointF.set(rectF.left, rectF.bottom);
            } else if (i3 != 3) {
                i2 = i4;
                pointF.set(rectF.right, rectF.top);
            } else {
                i2 = i4;
                pointF.set(rectF.left, rectF.top);
            }
            matrixArr2[i3].setTranslate(pointF.x, pointF.y);
            matrixArr2[i3].preRotate(f2);
            yc2 yc2Var2 = yc2VarArr[i3];
            fArr[0] = yc2Var2.c;
            fArr[1] = yc2Var2.d;
            matrixArr2[i3].mapPoints(fArr);
            matrixArr[i3].reset();
            matrixArr[i3].setTranslate(fArr[0], fArr[1]);
            matrixArr[i3].preRotate(f2);
            i3 = i2;
        }
        int i5 = 0;
        while (i5 < i) {
            yc2 yc2Var3 = yc2VarArr[i5];
            fArr[0] = yc2Var3.a;
            fArr[1] = yc2Var3.b;
            matrixArr2[i5].mapPoints(fArr);
            if (i5 == 0) {
                path3.moveTo(fArr[0], fArr[1]);
            } else {
                path3.lineTo(fArr[0], fArr[1]);
            }
            yc2VarArr[i5].b(matrixArr2[i5], path3);
            if (oh1Var3 != null) {
                yc2 yc2Var4 = yc2VarArr[i5];
                Matrix matrix = matrixArr2[i5];
                BitSet bitSet = ((qf1) oh1Var3.e).l;
                yc2Var4.getClass();
                bitSet.set(i5, false);
                xc2[] xc2VarArr = ((qf1) oh1Var3.e).e;
                yc2Var4.a(yc2Var4.f);
                xc2VarArr[i5] = new rc2(new ArrayList(yc2Var4.h), new Matrix(matrix));
            }
            int i6 = i5 + 1;
            int i7 = i6 % 4;
            yc2 yc2Var5 = yc2VarArr[i5];
            fArr[0] = yc2Var5.c;
            fArr[1] = yc2Var5.d;
            matrixArr2[i5].mapPoints(fArr);
            yc2 yc2Var6 = yc2VarArr[i7];
            float f3 = yc2Var6.a;
            float[] fArr2 = qc2Var.i;
            fArr2[0] = f3;
            fArr2[1] = yc2Var6.b;
            matrixArr2[i7].mapPoints(fArr2);
            float fMax = Math.max(((float) Math.hypot(fArr[0] - fArr2[0], fArr[1] - fArr2[1])) - 0.001f, 0.0f);
            yc2 yc2Var7 = yc2VarArr[i5];
            fArr[0] = yc2Var7.c;
            fArr[1] = yc2Var7.d;
            matrixArr2[i5].mapPoints(fArr);
            if (i5 == 1 || i5 == 3) {
                Math.abs(rectF.centerX() - fArr[0]);
            } else {
                Math.abs(rectF.centerY() - fArr[1]);
            }
            yc2 yc2Var8 = qc2Var.g;
            yc2Var8.d(0.0f, 270.0f, 0.0f);
            (i5 != 1 ? i5 != 2 ? i5 != 3 ? oc2Var.j : oc2Var.i : oc2Var.l : oc2Var.k).getClass();
            yc2Var8.c(fMax, 0.0f);
            Path path6 = qc2Var.j;
            path6.reset();
            yc2Var8.b(matrixArr[i5], path6);
            if (qc2Var.l && (qc2Var.b(path6, i5) || qc2Var.b(path6, i7))) {
                path6.op(path6, path5, Path.Op.DIFFERENCE);
                fArr[0] = yc2Var8.a;
                fArr[1] = yc2Var8.b;
                matrixArr[i5].mapPoints(fArr);
                path4.moveTo(fArr[0], fArr[1]);
                yc2Var8.b(matrixArr[i5], path4);
                oh1Var2 = oh1Var;
                path2 = path;
            } else {
                path2 = path;
                yc2Var8.b(matrixArr[i5], path2);
                oh1Var2 = oh1Var;
            }
            if (oh1Var2 != null) {
                Matrix matrix2 = matrixArr[i5];
                ((qf1) oh1Var2.e).l.set(i5 + 4, false);
                xc2[] xc2VarArr2 = ((qf1) oh1Var2.e).k;
                yc2Var8.a(yc2Var8.f);
                xc2VarArr2[i5] = new rc2(new ArrayList(yc2Var8.h), new Matrix(matrix2));
            }
            qc2Var = this;
            path3 = path2;
            i5 = i6;
            i = 4;
            oh1Var3 = oh1Var2;
        }
        Path path7 = path3;
        path.close();
        path4.close();
        if (path4.isEmpty()) {
            return;
        }
        path7.op(path4, Path.Op.UNION);
    }

    public final boolean b(Path path, int i) {
        Path path2 = this.k;
        path2.reset();
        this.a[i].b(this.b[i], path2);
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
