package b.d.a.a.e0;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import b.d.a.a.e0.g;
import b.d.a.a.e0.m;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m[] f1855a = new m[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Matrix[] f1856b = new Matrix[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix[] f1857c = new Matrix[4];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final PointF f1858d = new PointF();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Path f1859e = new Path();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Path f1860f = new Path();
    public final m g = new m();
    public final float[] h = new float[2];
    public final float[] i = new float[2];
    public final Path j = new Path();
    public final Path k = new Path();
    public boolean l = true;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final k f1861a = new k();
    }

    public interface b {
    }

    public k() {
        for (int i = 0; i < 4; i++) {
            this.f1855a[i] = new m();
            this.f1856b[i] = new Matrix();
            this.f1857c[i] = new Matrix();
        }
    }

    public void a(j jVar, float f2, RectF rectF, b bVar, Path path) {
        float fCenterX;
        float f3;
        char c2;
        f fVar;
        m mVar;
        Matrix matrix;
        Path path2;
        float f4;
        float f5;
        path.rewind();
        this.f1859e.rewind();
        this.f1860f.rewind();
        this.f1860f.addRect(rectF, Path.Direction.CW);
        char c3 = 0;
        int i = 0;
        while (i < 4) {
            c cVar = i != 1 ? i != 2 ? i != 3 ? jVar.f1848f : jVar.f1847e : jVar.h : jVar.g;
            d dVar = i != 1 ? i != 2 ? i != 3 ? jVar.f1844b : jVar.f1843a : jVar.f1846d : jVar.f1845c;
            m mVar2 = this.f1855a[i];
            if (dVar == null) {
                throw null;
            }
            dVar.a(mVar2, 90.0f, f2, cVar.a(rectF));
            int i2 = i + 1;
            float f6 = i2 * 90;
            this.f1856b[i].reset();
            PointF pointF = this.f1858d;
            if (i == 1) {
                f4 = rectF.right;
            } else if (i != 2) {
                f4 = i != 3 ? rectF.right : rectF.left;
                f5 = rectF.top;
                pointF.set(f4, f5);
                Matrix matrix2 = this.f1856b[i];
                PointF pointF2 = this.f1858d;
                matrix2.setTranslate(pointF2.x, pointF2.y);
                this.f1856b[i].preRotate(f6);
                float[] fArr = this.h;
                m[] mVarArr = this.f1855a;
                fArr[0] = mVarArr[i].f1866c;
                fArr[1] = mVarArr[i].f1867d;
                this.f1856b[i].mapPoints(fArr);
                this.f1857c[i].reset();
                Matrix matrix3 = this.f1857c[i];
                float[] fArr2 = this.h;
                matrix3.setTranslate(fArr2[0], fArr2[1]);
                this.f1857c[i].preRotate(f6);
                i = i2;
            } else {
                f4 = rectF.left;
            }
            f5 = rectF.bottom;
            pointF.set(f4, f5);
            Matrix matrix22 = this.f1856b[i];
            PointF pointF22 = this.f1858d;
            matrix22.setTranslate(pointF22.x, pointF22.y);
            this.f1856b[i].preRotate(f6);
            float[] fArr3 = this.h;
            m[] mVarArr2 = this.f1855a;
            fArr3[0] = mVarArr2[i].f1866c;
            fArr3[1] = mVarArr2[i].f1867d;
            this.f1856b[i].mapPoints(fArr3);
            this.f1857c[i].reset();
            Matrix matrix32 = this.f1857c[i];
            float[] fArr22 = this.h;
            matrix32.setTranslate(fArr22[0], fArr22[1]);
            this.f1857c[i].preRotate(f6);
            i = i2;
        }
        int i3 = 0;
        while (i3 < 4) {
            float[] fArr4 = this.h;
            m[] mVarArr3 = this.f1855a;
            fArr4[c3] = mVarArr3[i3].f1864a;
            fArr4[1] = mVarArr3[i3].f1865b;
            this.f1856b[i3].mapPoints(fArr4);
            float[] fArr5 = this.h;
            if (i3 == 0) {
                path.moveTo(fArr5[c3], fArr5[1]);
            } else {
                path.lineTo(fArr5[c3], fArr5[1]);
            }
            this.f1855a[i3].c(this.f1856b[i3], path);
            if (bVar != null) {
                m mVar3 = this.f1855a[i3];
                Matrix matrix4 = this.f1856b[i3];
                g.a aVar = (g.a) bVar;
                g.this.f1831d.set(i3, mVar3.i);
                m.f[] fVarArr = g.this.f1829b;
                mVar3.b(mVar3.f1869f);
                fVarArr[i3] = new l(mVar3, new ArrayList(mVar3.h), new Matrix(matrix4));
            }
            int i4 = i3 + 1;
            int i5 = i4 % 4;
            float[] fArr6 = this.h;
            m[] mVarArr4 = this.f1855a;
            fArr6[c3] = mVarArr4[i3].f1866c;
            fArr6[1] = mVarArr4[i3].f1867d;
            this.f1856b[i3].mapPoints(fArr6);
            float[] fArr7 = this.i;
            m[] mVarArr5 = this.f1855a;
            fArr7[c3] = mVarArr5[i5].f1864a;
            fArr7[1] = mVarArr5[i5].f1865b;
            this.f1856b[i5].mapPoints(fArr7);
            float f7 = this.h[c3];
            float[] fArr8 = this.i;
            float fMax = Math.max(((float) Math.hypot(f7 - fArr8[c3], r13[1] - fArr8[1])) - 0.001f, 0.0f);
            float[] fArr9 = this.h;
            m[] mVarArr6 = this.f1855a;
            fArr9[c3] = mVarArr6[i3].f1866c;
            fArr9[1] = mVarArr6[i3].f1867d;
            this.f1856b[i3].mapPoints(fArr9);
            if (i3 == 1 || i3 == 3) {
                fCenterX = rectF.centerX();
                f3 = this.h[c3];
            } else {
                fCenterX = rectF.centerY();
                f3 = this.h[1];
            }
            float fAbs = Math.abs(fCenterX - f3);
            this.g.e(0.0f, 0.0f, 270.0f, 0.0f);
            if (i3 == 1) {
                c2 = 3;
                fVar = jVar.k;
            } else if (i3 != 2) {
                c2 = 3;
                fVar = i3 != 3 ? jVar.j : jVar.i;
            } else {
                c2 = 3;
                fVar = jVar.l;
            }
            fVar.a(fMax, fAbs, f2, this.g);
            this.j.reset();
            this.g.c(this.f1857c[i3], this.j);
            if (this.l && (b(this.j, i3) || b(this.j, i5))) {
                Path path3 = this.j;
                path3.op(path3, this.f1860f, Path.Op.DIFFERENCE);
                float[] fArr10 = this.h;
                m mVar4 = this.g;
                fArr10[c3] = mVar4.f1864a;
                fArr10[1] = mVar4.f1865b;
                this.f1857c[i3].mapPoints(fArr10);
                Path path4 = this.f1859e;
                float[] fArr11 = this.h;
                path4.moveTo(fArr11[c3], fArr11[1]);
                mVar = this.g;
                matrix = this.f1857c[i3];
                path2 = this.f1859e;
            } else {
                mVar = this.g;
                matrix = this.f1857c[i3];
                path2 = path;
            }
            mVar.c(matrix, path2);
            if (bVar != null) {
                m mVar5 = this.g;
                Matrix matrix5 = this.f1857c[i3];
                g.a aVar2 = (g.a) bVar;
                g.this.f1831d.set(i3 + 4, mVar5.i);
                m.f[] fVarArr2 = g.this.f1830c;
                mVar5.b(mVar5.f1869f);
                fVarArr2[i3] = new l(mVar5, new ArrayList(mVar5.h), new Matrix(matrix5));
            }
            i3 = i4;
            c3 = 0;
        }
        path.close();
        this.f1859e.close();
        if (this.f1859e.isEmpty()) {
            return;
        }
        path.op(this.f1859e, Path.Op.UNION);
    }

    public final boolean b(Path path, int i) {
        this.k.reset();
        this.f1855a[i].c(this.f1856b[i], this.k);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        this.k.computeBounds(rectF, true);
        path.op(this.k, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }
}
