package b.d.a.a.e0;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Deprecated
    public float f1864a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Deprecated
    public float f1865b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Deprecated
    public float f1866c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Deprecated
    public float f1867d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Deprecated
    public float f1868e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Deprecated
    public float f1869f;
    public final List<e> g = new ArrayList();
    public final List<f> h = new ArrayList();
    public boolean i;

    public static class a extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final c f1870b;

        public a(c cVar) {
            this.f1870b = cVar;
        }

        @Override // b.d.a.a.e0.m.f
        public void a(Matrix matrix, b.d.a.a.d0.a aVar, int i, Canvas canvas) {
            c cVar = this.f1870b;
            float f2 = cVar.f1878f;
            float f3 = cVar.g;
            RectF rectF = new RectF(cVar.f1874b, cVar.f1875c, cVar.f1876d, cVar.f1877e);
            boolean z = f3 < 0.0f;
            Path path = aVar.g;
            if (z) {
                int[] iArr = b.d.a.a.d0.a.k;
                iArr[0] = 0;
                iArr[1] = aVar.f1823f;
                iArr[2] = aVar.f1822e;
                iArr[3] = aVar.f1821d;
            } else {
                path.rewind();
                path.moveTo(rectF.centerX(), rectF.centerY());
                path.arcTo(rectF, f2, f3);
                path.close();
                float f4 = -i;
                rectF.inset(f4, f4);
                int[] iArr2 = b.d.a.a.d0.a.k;
                iArr2[0] = 0;
                iArr2[1] = aVar.f1821d;
                iArr2[2] = aVar.f1822e;
                iArr2[3] = aVar.f1823f;
            }
            float fWidth = rectF.width() / 2.0f;
            if (fWidth <= 0.0f) {
                return;
            }
            float f5 = 1.0f - (i / fWidth);
            float[] fArr = b.d.a.a.d0.a.l;
            fArr[1] = f5;
            fArr[2] = ((1.0f - f5) / 2.0f) + f5;
            aVar.f1819b.setShader(new RadialGradient(rectF.centerX(), rectF.centerY(), fWidth, b.d.a.a.d0.a.k, b.d.a.a.d0.a.l, Shader.TileMode.CLAMP));
            canvas.save();
            canvas.concat(matrix);
            canvas.scale(1.0f, rectF.height() / rectF.width());
            if (!z) {
                canvas.clipPath(path, Region.Op.DIFFERENCE);
                canvas.drawPath(path, aVar.h);
            }
            canvas.drawArc(rectF, f2, f3, true, aVar.f1819b);
            canvas.restore();
        }
    }

    public static class b extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f1871b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f1872c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final float f1873d;

        public b(d dVar, float f2, float f3) {
            this.f1871b = dVar;
            this.f1872c = f2;
            this.f1873d = f3;
        }

        @Override // b.d.a.a.e0.m.f
        public void a(Matrix matrix, b.d.a.a.d0.a aVar, int i, Canvas canvas) {
            d dVar = this.f1871b;
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(dVar.f1880c - this.f1873d, dVar.f1879b - this.f1872c), 0.0f);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.preTranslate(this.f1872c, this.f1873d);
            matrix2.preRotate(b());
            if (aVar == null) {
                throw null;
            }
            rectF.bottom += i;
            rectF.offset(0.0f, -i);
            int[] iArr = b.d.a.a.d0.a.i;
            iArr[0] = aVar.f1823f;
            iArr[1] = aVar.f1822e;
            iArr[2] = aVar.f1821d;
            Paint paint = aVar.f1820c;
            float f2 = rectF.left;
            paint.setShader(new LinearGradient(f2, rectF.top, f2, rectF.bottom, iArr, b.d.a.a.d0.a.j, Shader.TileMode.CLAMP));
            canvas.save();
            canvas.concat(matrix2);
            canvas.drawRect(rectF, aVar.f1820c);
            canvas.restore();
        }

        public float b() {
            d dVar = this.f1871b;
            return (float) Math.toDegrees(Math.atan((dVar.f1880c - this.f1873d) / (dVar.f1879b - this.f1872c)));
        }
    }

    public static class c extends e {
        public static final RectF h = new RectF();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Deprecated
        public float f1874b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Deprecated
        public float f1875c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Deprecated
        public float f1876d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @Deprecated
        public float f1877e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @Deprecated
        public float f1878f;

        @Deprecated
        public float g;

        public c(float f2, float f3, float f4, float f5) {
            this.f1874b = f2;
            this.f1875c = f3;
            this.f1876d = f4;
            this.f1877e = f5;
        }

        @Override // b.d.a.a.e0.m.e
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f1881a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            h.set(this.f1874b, this.f1875c, this.f1876d, this.f1877e);
            path.arcTo(h, this.f1878f, this.g, false);
            path.transform(matrix);
        }
    }

    public static class d extends e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1879b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1880c;

        @Override // b.d.a.a.e0.m.e
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f1881a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f1879b, this.f1880c);
            path.transform(matrix);
        }
    }

    public static abstract class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f1881a = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    public static abstract class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Matrix f1882a = new Matrix();

        public abstract void a(Matrix matrix, b.d.a.a.d0.a aVar, int i, Canvas canvas);
    }

    public m() {
        e(0.0f, 0.0f, 270.0f, 0.0f);
    }

    public void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        c cVar = new c(f2, f3, f4, f5);
        cVar.f1878f = f6;
        cVar.g = f7;
        this.g.add(cVar);
        a aVar = new a(cVar);
        float f8 = f6 + f7;
        boolean z = f7 < 0.0f;
        if (z) {
            f6 = (f6 + 180.0f) % 360.0f;
        }
        float f9 = z ? (180.0f + f8) % 360.0f : f8;
        b(f6);
        this.h.add(aVar);
        this.f1868e = f9;
        double d2 = f8;
        this.f1866c = (((f4 - f2) / 2.0f) * ((float) Math.cos(Math.toRadians(d2)))) + ((f2 + f4) * 0.5f);
        this.f1867d = (((f5 - f3) / 2.0f) * ((float) Math.sin(Math.toRadians(d2)))) + ((f3 + f5) * 0.5f);
    }

    public final void b(float f2) {
        float f3 = this.f1868e;
        if (f3 == f2) {
            return;
        }
        float f4 = ((f2 - f3) + 360.0f) % 360.0f;
        if (f4 > 180.0f) {
            return;
        }
        float f5 = this.f1866c;
        float f6 = this.f1867d;
        c cVar = new c(f5, f6, f5, f6);
        cVar.f1878f = this.f1868e;
        cVar.g = f4;
        this.h.add(new a(cVar));
        this.f1868e = f2;
    }

    public void c(Matrix matrix, Path path) {
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            this.g.get(i).a(matrix, path);
        }
    }

    public void d(float f2, float f3) {
        d dVar = new d();
        dVar.f1879b = f2;
        dVar.f1880c = f3;
        this.g.add(dVar);
        b bVar = new b(dVar, this.f1866c, this.f1867d);
        float fB = bVar.b() + 270.0f;
        float fB2 = bVar.b() + 270.0f;
        b(fB);
        this.h.add(bVar);
        this.f1868e = fB2;
        this.f1866c = f2;
        this.f1867d = f3;
    }

    public void e(float f2, float f3, float f4, float f5) {
        this.f1864a = f2;
        this.f1865b = f3;
        this.f1866c = f2;
        this.f1867d = f3;
        this.f1868e = f4;
        this.f1869f = (f4 + f5) % 360.0f;
        this.g.clear();
        this.h.clear();
        this.i = false;
    }
}
