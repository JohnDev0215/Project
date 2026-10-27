package c.b.a.a.v;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ShapePath.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Deprecated
    public float f1881a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Deprecated
    public float f1882b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Deprecated
    public float f1883c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Deprecated
    public float f1884d;

    @Deprecated
    public float e;

    @Deprecated
    public float f;
    public final List<f> g = new ArrayList();
    public final List<g> h = new ArrayList();
    public boolean i;

    /* JADX INFO: compiled from: ShapePath.java */
    public class a extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ List f1885b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Matrix f1886c;

        public a(List list, Matrix matrix) {
            this.f1885b = list;
            this.f1886c = matrix;
        }

        @Override // c.b.a.a.v.o.g
        public void a(Matrix matrix, c.b.a.a.u.a aVar, int i, Canvas canvas) {
            Iterator it = this.f1885b.iterator();
            while (it.hasNext()) {
                ((g) it.next()).a(this.f1886c, aVar, i, canvas);
            }
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class b extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f1888b;

        public b(d dVar) {
            this.f1888b = dVar;
        }

        @Override // c.b.a.a.v.o.g
        public void a(Matrix matrix, c.b.a.a.u.a aVar, int i, Canvas canvas) {
            aVar.a(canvas, matrix, new RectF(this.f1888b.k(), this.f1888b.o(), this.f1888b.l(), this.f1888b.j()), i, this.f1888b.m(), this.f1888b.n());
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class c extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final e f1889b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f1890c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final float f1891d;

        public c(e eVar, float f, float f2) {
            this.f1889b = eVar;
            this.f1890c = f;
            this.f1891d = f2;
        }

        @Override // c.b.a.a.v.o.g
        public void a(Matrix matrix, c.b.a.a.u.a aVar, int i, Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.f1889b.f1896c - this.f1891d, this.f1889b.f1895b - this.f1890c), 0.0f);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.preTranslate(this.f1890c, this.f1891d);
            matrix2.preRotate(c());
            aVar.b(canvas, matrix2, rectF, i);
        }

        public float c() {
            return (float) Math.toDegrees(Math.atan((this.f1889b.f1896c - this.f1891d) / (this.f1889b.f1895b - this.f1890c)));
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class d extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final RectF f1892b = new RectF();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Deprecated
        public float f1893c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Deprecated
        public float f1894d;

        @Deprecated
        public float e;

        @Deprecated
        public float f;

        @Deprecated
        public float g;

        @Deprecated
        public float h;

        public d(float f, float f2, float f3, float f4) {
            q(f);
            u(f2);
            r(f3);
            p(f4);
        }

        @Override // c.b.a.a.v.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f1897a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF = f1892b;
            rectF.set(k(), o(), l(), j());
            path.arcTo(rectF, m(), n(), false);
            path.transform(matrix);
        }

        public final float j() {
            return this.f;
        }

        public final float k() {
            return this.f1893c;
        }

        public final float l() {
            return this.e;
        }

        public final float m() {
            return this.g;
        }

        public final float n() {
            return this.h;
        }

        public final float o() {
            return this.f1894d;
        }

        public final void p(float f) {
            this.f = f;
        }

        public final void q(float f) {
            this.f1893c = f;
        }

        public final void r(float f) {
            this.e = f;
        }

        public final void s(float f) {
            this.g = f;
        }

        public final void t(float f) {
            this.h = f;
        }

        public final void u(float f) {
            this.f1894d = f;
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class e extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1895b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1896c;

        @Override // c.b.a.a.v.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f1897a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f1895b, this.f1896c);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static abstract class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f1897a = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static abstract class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Matrix f1898a = new Matrix();

        public abstract void a(Matrix matrix, c.b.a.a.u.a aVar, int i, Canvas canvas);

        public final void b(c.b.a.a.u.a aVar, int i, Canvas canvas) {
            a(f1898a, aVar, i, canvas);
        }
    }

    public o() {
        n(0.0f, 0.0f);
    }

    public void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        d dVar = new d(f2, f3, f4, f5);
        dVar.s(f6);
        dVar.t(f7);
        this.g.add(dVar);
        b bVar = new b(dVar);
        float f8 = f6 + f7;
        boolean z = f7 < 0.0f;
        if (z) {
            f6 = (f6 + 180.0f) % 360.0f;
        }
        c(bVar, f6, z ? (180.0f + f8) % 360.0f : f8);
        double d2 = f8;
        r(((f2 + f4) * 0.5f) + (((f4 - f2) / 2.0f) * ((float) Math.cos(Math.toRadians(d2)))));
        s(((f3 + f5) * 0.5f) + (((f5 - f3) / 2.0f) * ((float) Math.sin(Math.toRadians(d2)))));
    }

    public final void b(float f2) {
        if (g() == f2) {
            return;
        }
        float fG = ((f2 - g()) + 360.0f) % 360.0f;
        if (fG > 180.0f) {
            return;
        }
        d dVar = new d(i(), j(), i(), j());
        dVar.s(g());
        dVar.t(fG);
        this.h.add(new b(dVar));
        p(f2);
    }

    public final void c(g gVar, float f2, float f3) {
        b(f2);
        this.h.add(gVar);
        p(f3);
    }

    public void d(Matrix matrix, Path path) {
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            this.g.get(i).a(matrix, path);
        }
    }

    public boolean e() {
        return this.i;
    }

    public g f(Matrix matrix) {
        b(h());
        return new a(new ArrayList(this.h), matrix);
    }

    public final float g() {
        return this.e;
    }

    public final float h() {
        return this.f;
    }

    public float i() {
        return this.f1883c;
    }

    public float j() {
        return this.f1884d;
    }

    public float k() {
        return this.f1881a;
    }

    public float l() {
        return this.f1882b;
    }

    public void m(float f2, float f3) {
        e eVar = new e();
        eVar.f1895b = f2;
        eVar.f1896c = f3;
        this.g.add(eVar);
        c cVar = new c(eVar, i(), j());
        c(cVar, cVar.c() + 270.0f, cVar.c() + 270.0f);
        r(f2);
        s(f3);
    }

    public void n(float f2, float f3) {
        o(f2, f3, 270.0f, 0.0f);
    }

    public void o(float f2, float f3, float f4, float f5) {
        t(f2);
        u(f3);
        r(f2);
        s(f3);
        p(f4);
        q((f4 + f5) % 360.0f);
        this.g.clear();
        this.h.clear();
        this.i = false;
    }

    public final void p(float f2) {
        this.e = f2;
    }

    public final void q(float f2) {
        this.f = f2;
    }

    public final void r(float f2) {
        this.f1883c = f2;
    }

    public final void s(float f2) {
        this.f1884d = f2;
    }

    public final void t(float f2) {
        this.f1881a = f2;
    }

    public final void u(float f2) {
        this.f1882b = f2;
    }
}
