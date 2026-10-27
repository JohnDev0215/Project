package n2;

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
    public float f4192a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Deprecated
    public float f4193b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Deprecated
    public float f4194c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Deprecated
    public float f4195d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Deprecated
    public float f4196e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Deprecated
    public float f4197f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List<f> f4198g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List<g> f4199h = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4200i;

    /* JADX INFO: compiled from: ShapePath.java */
    public class a extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ List f4201b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Matrix f4202c;

        public a(List list, Matrix matrix) {
            this.f4201b = list;
            this.f4202c = matrix;
        }

        @Override // n2.o.g
        public void a(Matrix matrix, m2.a aVar, int i3, Canvas canvas) {
            Iterator it = this.f4201b.iterator();
            while (it.hasNext()) {
                ((g) it.next()).a(this.f4202c, aVar, i3, canvas);
            }
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class b extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d f4204b;

        public b(d dVar) {
            this.f4204b = dVar;
        }

        @Override // n2.o.g
        public void a(Matrix matrix, m2.a aVar, int i3, Canvas canvas) {
            aVar.a(canvas, matrix, new RectF(this.f4204b.k(), this.f4204b.o(), this.f4204b.l(), this.f4204b.j()), i3, this.f4204b.m(), this.f4204b.n());
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class c extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final e f4205b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f4206c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final float f4207d;

        public c(e eVar, float f3, float f4) {
            this.f4205b = eVar;
            this.f4206c = f3;
            this.f4207d = f4;
        }

        @Override // n2.o.g
        public void a(Matrix matrix, m2.a aVar, int i3, Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.f4205b.f4216c - this.f4207d, this.f4205b.f4215b - this.f4206c), 0.0f);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.preTranslate(this.f4206c, this.f4207d);
            matrix2.preRotate(c());
            aVar.b(canvas, matrix2, rectF, i3);
        }

        public float c() {
            return (float) Math.toDegrees(Math.atan((this.f4205b.f4216c - this.f4207d) / (this.f4205b.f4215b - this.f4206c)));
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class d extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final RectF f4208b = new RectF();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Deprecated
        public float f4209c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Deprecated
        public float f4210d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @Deprecated
        public float f4211e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @Deprecated
        public float f4212f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        @Deprecated
        public float f4213g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        @Deprecated
        public float f4214h;

        public d(float f3, float f4, float f5, float f6) {
            q(f3);
            u(f4);
            r(f5);
            p(f6);
        }

        @Override // n2.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f4217a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF = f4208b;
            rectF.set(k(), o(), l(), j());
            path.arcTo(rectF, m(), n(), false);
            path.transform(matrix);
        }

        public final float j() {
            return this.f4212f;
        }

        public final float k() {
            return this.f4209c;
        }

        public final float l() {
            return this.f4211e;
        }

        public final float m() {
            return this.f4213g;
        }

        public final float n() {
            return this.f4214h;
        }

        public final float o() {
            return this.f4210d;
        }

        public final void p(float f3) {
            this.f4212f = f3;
        }

        public final void q(float f3) {
            this.f4209c = f3;
        }

        public final void r(float f3) {
            this.f4211e = f3;
        }

        public final void s(float f3) {
            this.f4213g = f3;
        }

        public final void t(float f3) {
            this.f4214h = f3;
        }

        public final void u(float f3) {
            this.f4210d = f3;
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class e extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f4215b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f4216c;

        @Override // n2.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f4217a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f4215b, this.f4216c);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static abstract class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Matrix f4217a = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static abstract class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Matrix f4218a = new Matrix();

        public abstract void a(Matrix matrix, m2.a aVar, int i3, Canvas canvas);

        public final void b(m2.a aVar, int i3, Canvas canvas) {
            a(f4218a, aVar, i3, canvas);
        }
    }

    public o() {
        n(0.0f, 0.0f);
    }

    public void a(float f3, float f4, float f5, float f6, float f7, float f8) {
        d dVar = new d(f3, f4, f5, f6);
        dVar.s(f7);
        dVar.t(f8);
        this.f4198g.add(dVar);
        b bVar = new b(dVar);
        float f9 = f7 + f8;
        boolean z3 = f8 < 0.0f;
        if (z3) {
            f7 = (f7 + 180.0f) % 360.0f;
        }
        c(bVar, f7, z3 ? (180.0f + f9) % 360.0f : f9);
        double d3 = f9;
        r(((f3 + f5) * 0.5f) + (((f5 - f3) / 2.0f) * ((float) Math.cos(Math.toRadians(d3)))));
        s(((f4 + f6) * 0.5f) + (((f6 - f4) / 2.0f) * ((float) Math.sin(Math.toRadians(d3)))));
    }

    public final void b(float f3) {
        if (g() == f3) {
            return;
        }
        float fG = ((f3 - g()) + 360.0f) % 360.0f;
        if (fG > 180.0f) {
            return;
        }
        d dVar = new d(i(), j(), i(), j());
        dVar.s(g());
        dVar.t(fG);
        this.f4199h.add(new b(dVar));
        p(f3);
    }

    public final void c(g gVar, float f3, float f4) {
        b(f3);
        this.f4199h.add(gVar);
        p(f4);
    }

    public void d(Matrix matrix, Path path) {
        int size = this.f4198g.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.f4198g.get(i3).a(matrix, path);
        }
    }

    public boolean e() {
        return this.f4200i;
    }

    public g f(Matrix matrix) {
        b(h());
        return new a(new ArrayList(this.f4199h), matrix);
    }

    public final float g() {
        return this.f4196e;
    }

    public final float h() {
        return this.f4197f;
    }

    public float i() {
        return this.f4194c;
    }

    public float j() {
        return this.f4195d;
    }

    public float k() {
        return this.f4192a;
    }

    public float l() {
        return this.f4193b;
    }

    public void m(float f3, float f4) {
        e eVar = new e();
        eVar.f4215b = f3;
        eVar.f4216c = f4;
        this.f4198g.add(eVar);
        c cVar = new c(eVar, i(), j());
        c(cVar, cVar.c() + 270.0f, cVar.c() + 270.0f);
        r(f3);
        s(f4);
    }

    public void n(float f3, float f4) {
        o(f3, f4, 270.0f, 0.0f);
    }

    public void o(float f3, float f4, float f5, float f6) {
        t(f3);
        u(f4);
        r(f3);
        s(f4);
        p(f5);
        q((f5 + f6) % 360.0f);
        this.f4198g.clear();
        this.f4199h.clear();
        this.f4200i = false;
    }

    public final void p(float f3) {
        this.f4196e = f3;
    }

    public final void q(float f3) {
        this.f4197f = f3;
    }

    public final void r(float f3) {
        this.f4194c = f3;
    }

    public final void s(float f3) {
        this.f4195d = f3;
    }

    public final void t(float f3) {
        this.f4192a = f3;
    }

    public final void u(float f3) {
        this.f4193b = f3;
    }
}
