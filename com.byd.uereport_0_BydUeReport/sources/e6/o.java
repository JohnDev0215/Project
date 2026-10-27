package e6;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ShapePath.java */
/* JADX INFO: loaded from: classes2.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Deprecated
    public float f4745a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Deprecated
    public float f4746b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Deprecated
    public float f4747c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Deprecated
    public float f4748d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Deprecated
    public float f4749e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Deprecated
    public float f4750f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List<f> f4751g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List<g> f4752h = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4753i;

    /* JADX INFO: compiled from: ShapePath.java */
    public class a extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ List f4754b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Matrix f4755c;

        public a(List list, Matrix matrix) {
            this.f4754b = list;
            this.f4755c = matrix;
        }

        @Override // e6.o.g
        public void a(Matrix matrix, d6.a aVar, int i8, Canvas canvas) {
            Iterator it = this.f4754b.iterator();
            while (it.hasNext()) {
                ((g) it.next()).a(this.f4755c, aVar, i8, canvas);
            }
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class b extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f4757b;

        public b(d dVar) {
            this.f4757b = dVar;
        }

        @Override // e6.o.g
        public void a(Matrix matrix, d6.a aVar, int i8, Canvas canvas) {
            aVar.a(canvas, matrix, new RectF(this.f4757b.k(), this.f4757b.o(), this.f4757b.l(), this.f4757b.j()), i8, this.f4757b.m(), this.f4757b.n());
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class c extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final e f4758b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f4759c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final float f4760d;

        public c(e eVar, float f8, float f9) {
            this.f4758b = eVar;
            this.f4759c = f8;
            this.f4760d = f9;
        }

        @Override // e6.o.g
        public void a(Matrix matrix, d6.a aVar, int i8, Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.f4758b.f4769c - this.f4760d, this.f4758b.f4768b - this.f4759c), 0.0f);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.preTranslate(this.f4759c, this.f4760d);
            matrix2.preRotate(c());
            aVar.b(canvas, matrix2, rectF, i8);
        }

        public float c() {
            return (float) Math.toDegrees(Math.atan((this.f4758b.f4769c - this.f4760d) / (this.f4758b.f4768b - this.f4759c)));
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class d extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final RectF f4761b = new RectF();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Deprecated
        public float f4762c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Deprecated
        public float f4763d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @Deprecated
        public float f4764e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @Deprecated
        public float f4765f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        @Deprecated
        public float f4766g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        @Deprecated
        public float f4767h;

        public d(float f8, float f9, float f10, float f11) {
            q(f8);
            u(f9);
            r(f10);
            p(f11);
        }

        @Override // e6.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f4770a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF = f4761b;
            rectF.set(k(), o(), l(), j());
            path.arcTo(rectF, m(), n(), false);
            path.transform(matrix);
        }

        public final float j() {
            return this.f4765f;
        }

        public final float k() {
            return this.f4762c;
        }

        public final float l() {
            return this.f4764e;
        }

        public final float m() {
            return this.f4766g;
        }

        public final float n() {
            return this.f4767h;
        }

        public final float o() {
            return this.f4763d;
        }

        public final void p(float f8) {
            this.f4765f = f8;
        }

        public final void q(float f8) {
            this.f4762c = f8;
        }

        public final void r(float f8) {
            this.f4764e = f8;
        }

        public final void s(float f8) {
            this.f4766g = f8;
        }

        public final void t(float f8) {
            this.f4767h = f8;
        }

        public final void u(float f8) {
            this.f4763d = f8;
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class e extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f4768b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f4769c;

        @Override // e6.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f4770a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f4768b, this.f4769c);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static abstract class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f4770a = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static abstract class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Matrix f4771a = new Matrix();

        public abstract void a(Matrix matrix, d6.a aVar, int i8, Canvas canvas);

        public final void b(d6.a aVar, int i8, Canvas canvas) {
            a(f4771a, aVar, i8, canvas);
        }
    }

    public o() {
        n(0.0f, 0.0f);
    }

    public void a(float f8, float f9, float f10, float f11, float f12, float f13) {
        d dVar = new d(f8, f9, f10, f11);
        dVar.s(f12);
        dVar.t(f13);
        this.f4751g.add(dVar);
        b bVar = new b(dVar);
        float f14 = f12 + f13;
        boolean z7 = f13 < 0.0f;
        if (z7) {
            f12 = (f12 + 180.0f) % 360.0f;
        }
        c(bVar, f12, z7 ? (180.0f + f14) % 360.0f : f14);
        double d8 = f14;
        r(((f8 + f10) * 0.5f) + (((f10 - f8) / 2.0f) * ((float) Math.cos(Math.toRadians(d8)))));
        s(((f9 + f11) * 0.5f) + (((f11 - f9) / 2.0f) * ((float) Math.sin(Math.toRadians(d8)))));
    }

    public final void b(float f8) {
        if (g() == f8) {
            return;
        }
        float fG = ((f8 - g()) + 360.0f) % 360.0f;
        if (fG > 180.0f) {
            return;
        }
        d dVar = new d(i(), j(), i(), j());
        dVar.s(g());
        dVar.t(fG);
        this.f4752h.add(new b(dVar));
        p(f8);
    }

    public final void c(g gVar, float f8, float f9) {
        b(f8);
        this.f4752h.add(gVar);
        p(f9);
    }

    public void d(Matrix matrix, Path path) {
        int size = this.f4751g.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.f4751g.get(i8).a(matrix, path);
        }
    }

    public boolean e() {
        return this.f4753i;
    }

    public g f(Matrix matrix) {
        b(h());
        return new a(new ArrayList(this.f4752h), matrix);
    }

    public final float g() {
        return this.f4749e;
    }

    public final float h() {
        return this.f4750f;
    }

    public float i() {
        return this.f4747c;
    }

    public float j() {
        return this.f4748d;
    }

    public float k() {
        return this.f4745a;
    }

    public float l() {
        return this.f4746b;
    }

    public void m(float f8, float f9) {
        e eVar = new e();
        eVar.f4768b = f8;
        eVar.f4769c = f9;
        this.f4751g.add(eVar);
        c cVar = new c(eVar, i(), j());
        c(cVar, cVar.c() + 270.0f, cVar.c() + 270.0f);
        r(f8);
        s(f9);
    }

    public void n(float f8, float f9) {
        o(f8, f9, 270.0f, 0.0f);
    }

    public void o(float f8, float f9, float f10, float f11) {
        t(f8);
        u(f9);
        r(f8);
        s(f9);
        p(f10);
        q((f10 + f11) % 360.0f);
        this.f4751g.clear();
        this.f4752h.clear();
        this.f4753i = false;
    }

    public final void p(float f8) {
        this.f4749e = f8;
    }

    public final void q(float f8) {
        this.f4750f = f8;
    }

    public final void r(float f8) {
        this.f4747c = f8;
    }

    public final void s(float f8) {
        this.f4748d = f8;
    }

    public final void t(float f8) {
        this.f4745a = f8;
    }

    public final void u(float f8) {
        this.f4746b = f8;
    }
}
