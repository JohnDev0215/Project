package c.b.a.a.i0;

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
    public float f3913a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Deprecated
    public float f3914b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Deprecated
    public float f3915c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Deprecated
    public float f3916d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Deprecated
    public float f3917e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Deprecated
    public float f3918f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List<f> f3919g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List<g> f3920h = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3921i;

    /* JADX INFO: compiled from: ShapePath.java */
    public class a extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ List f3922b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Matrix f3923c;

        public a(List list, Matrix matrix) {
            this.f3922b = list;
            this.f3923c = matrix;
        }

        @Override // c.b.a.a.i0.o.g
        public void a(Matrix matrix, c.b.a.a.h0.a aVar, int i2, Canvas canvas) {
            Iterator it = this.f3922b.iterator();
            while (it.hasNext()) {
                ((g) it.next()).a(this.f3923c, aVar, i2, canvas);
            }
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class b extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f3925b;

        public b(d dVar) {
            this.f3925b = dVar;
        }

        @Override // c.b.a.a.i0.o.g
        public void a(Matrix matrix, c.b.a.a.h0.a aVar, int i2, Canvas canvas) {
            aVar.a(canvas, matrix, new RectF(this.f3925b.k(), this.f3925b.o(), this.f3925b.l(), this.f3925b.j()), i2, this.f3925b.m(), this.f3925b.n());
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class c extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final e f3926b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f3927c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final float f3928d;

        public c(e eVar, float f2, float f3) {
            this.f3926b = eVar;
            this.f3927c = f2;
            this.f3928d = f3;
        }

        @Override // c.b.a.a.i0.o.g
        public void a(Matrix matrix, c.b.a.a.h0.a aVar, int i2, Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.f3926b.f3937c - this.f3928d, this.f3926b.f3936b - this.f3927c), 0.0f);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.preTranslate(this.f3927c, this.f3928d);
            matrix2.preRotate(c());
            aVar.b(canvas, matrix2, rectF, i2);
        }

        public float c() {
            return (float) Math.toDegrees(Math.atan((this.f3926b.f3937c - this.f3928d) / (this.f3926b.f3936b - this.f3927c)));
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class d extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final RectF f3929b = new RectF();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Deprecated
        public float f3930c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Deprecated
        public float f3931d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @Deprecated
        public float f3932e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @Deprecated
        public float f3933f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        @Deprecated
        public float f3934g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        @Deprecated
        public float f3935h;

        public d(float f2, float f3, float f4, float f5) {
            q(f2);
            u(f3);
            r(f4);
            p(f5);
        }

        @Override // c.b.a.a.i0.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f3938a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF = f3929b;
            rectF.set(k(), o(), l(), j());
            path.arcTo(rectF, m(), n(), false);
            path.transform(matrix);
        }

        public final float j() {
            return this.f3933f;
        }

        public final float k() {
            return this.f3930c;
        }

        public final float l() {
            return this.f3932e;
        }

        public final float m() {
            return this.f3934g;
        }

        public final float n() {
            return this.f3935h;
        }

        public final float o() {
            return this.f3931d;
        }

        public final void p(float f2) {
            this.f3933f = f2;
        }

        public final void q(float f2) {
            this.f3930c = f2;
        }

        public final void r(float f2) {
            this.f3932e = f2;
        }

        public final void s(float f2) {
            this.f3934g = f2;
        }

        public final void t(float f2) {
            this.f3935h = f2;
        }

        public final void u(float f2) {
            this.f3931d = f2;
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class e extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f3936b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f3937c;

        @Override // c.b.a.a.i0.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f3938a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f3936b, this.f3937c);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static abstract class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f3938a = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static abstract class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Matrix f3939a = new Matrix();

        public abstract void a(Matrix matrix, c.b.a.a.h0.a aVar, int i2, Canvas canvas);

        public final void b(c.b.a.a.h0.a aVar, int i2, Canvas canvas) {
            a(f3939a, aVar, i2, canvas);
        }
    }

    public o() {
        n(0.0f, 0.0f);
    }

    public void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        d dVar = new d(f2, f3, f4, f5);
        dVar.s(f6);
        dVar.t(f7);
        this.f3919g.add(dVar);
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
        this.f3920h.add(new b(dVar));
        p(f2);
    }

    public final void c(g gVar, float f2, float f3) {
        b(f2);
        this.f3920h.add(gVar);
        p(f3);
    }

    public void d(Matrix matrix, Path path) {
        int size = this.f3919g.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f3919g.get(i2).a(matrix, path);
        }
    }

    public boolean e() {
        return this.f3921i;
    }

    public g f(Matrix matrix) {
        b(h());
        return new a(new ArrayList(this.f3920h), matrix);
    }

    public final float g() {
        return this.f3917e;
    }

    public final float h() {
        return this.f3918f;
    }

    public float i() {
        return this.f3915c;
    }

    public float j() {
        return this.f3916d;
    }

    public float k() {
        return this.f3913a;
    }

    public float l() {
        return this.f3914b;
    }

    public void m(float f2, float f3) {
        e eVar = new e();
        eVar.f3936b = f2;
        eVar.f3937c = f3;
        this.f3919g.add(eVar);
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
        this.f3919g.clear();
        this.f3920h.clear();
        this.f3921i = false;
    }

    public final void p(float f2) {
        this.f3917e = f2;
    }

    public final void q(float f2) {
        this.f3918f = f2;
    }

    public final void r(float f2) {
        this.f3915c = f2;
    }

    public final void s(float f2) {
        this.f3916d = f2;
    }

    public final void t(float f2) {
        this.f3913a = f2;
    }

    public final void u(float f2) {
        this.f3914b = f2;
    }
}
