package c.b.a.a.w;

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
    public float f2218a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Deprecated
    public float f2219b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Deprecated
    public float f2220c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Deprecated
    public float f2221d;

    @Deprecated
    public float e;

    @Deprecated
    public float f;
    private final List<f> g = new ArrayList();
    private final List<g> h = new ArrayList();
    private boolean i;

    /* JADX INFO: compiled from: ShapePath.java */
    class a extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f2222b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Matrix f2223c;

        a(List list, Matrix matrix) {
            this.f2222b = list;
            this.f2223c = matrix;
        }

        @Override // c.b.a.a.w.o.g
        public void a(Matrix matrix, c.b.a.a.v.a aVar, int i, Canvas canvas) {
            Iterator it = this.f2222b.iterator();
            while (it.hasNext()) {
                ((g) it.next()).a(this.f2223c, aVar, i, canvas);
            }
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    static class b extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final d f2225b;

        public b(d dVar) {
            this.f2225b = dVar;
        }

        @Override // c.b.a.a.w.o.g
        public void a(Matrix matrix, c.b.a.a.v.a aVar, int i, Canvas canvas) {
            aVar.a(canvas, matrix, new RectF(this.f2225b.k(), this.f2225b.o(), this.f2225b.l(), this.f2225b.j()), i, this.f2225b.m(), this.f2225b.n());
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    static class c extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f2226b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final float f2227c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final float f2228d;

        public c(e eVar, float f, float f2) {
            this.f2226b = eVar;
            this.f2227c = f;
            this.f2228d = f2;
        }

        @Override // c.b.a.a.w.o.g
        public void a(Matrix matrix, c.b.a.a.v.a aVar, int i, Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.f2226b.f2233c - this.f2228d, this.f2226b.f2232b - this.f2227c), 0.0f);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.preTranslate(this.f2227c, this.f2228d);
            matrix2.preRotate(c());
            aVar.b(canvas, matrix2, rectF, i);
        }

        float c() {
            return (float) Math.toDegrees(Math.atan((this.f2226b.f2233c - this.f2228d) / (this.f2226b.f2232b - this.f2227c)));
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class d extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final RectF f2229b = new RectF();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Deprecated
        public float f2230c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Deprecated
        public float f2231d;

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

        /* JADX INFO: Access modifiers changed from: private */
        public float j() {
            return this.f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float k() {
            return this.f2230c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float l() {
            return this.e;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float m() {
            return this.g;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float n() {
            return this.h;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float o() {
            return this.f2231d;
        }

        private void p(float f) {
            this.f = f;
        }

        private void q(float f) {
            this.f2230c = f;
        }

        private void r(float f) {
            this.e = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void s(float f) {
            this.g = f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void t(float f) {
            this.h = f;
        }

        private void u(float f) {
            this.f2231d = f;
        }

        @Override // c.b.a.a.w.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f2234a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF = f2229b;
            rectF.set(k(), o(), l(), j());
            path.arcTo(rectF, m(), n(), false);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class e extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private float f2232b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f2233c;

        @Override // c.b.a.a.w.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f2234a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f2232b, this.f2233c);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static abstract class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final Matrix f2234a = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    /* JADX INFO: compiled from: ShapePath.java */
    static abstract class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final Matrix f2235a = new Matrix();

        g() {
        }

        public abstract void a(Matrix matrix, c.b.a.a.v.a aVar, int i, Canvas canvas);

        public final void b(c.b.a.a.v.a aVar, int i, Canvas canvas) {
            a(f2235a, aVar, i, canvas);
        }
    }

    public o() {
        n(0.0f, 0.0f);
    }

    private void b(float f2) {
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

    private void c(g gVar, float f2, float f3) {
        b(f2);
        this.h.add(gVar);
        p(f3);
    }

    private float g() {
        return this.e;
    }

    private float h() {
        return this.f;
    }

    private void p(float f2) {
        this.e = f2;
    }

    private void q(float f2) {
        this.f = f2;
    }

    private void r(float f2) {
        this.f2220c = f2;
    }

    private void s(float f2) {
        this.f2221d = f2;
    }

    private void t(float f2) {
        this.f2218a = f2;
    }

    private void u(float f2) {
        this.f2219b = f2;
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

    public void d(Matrix matrix, Path path) {
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            this.g.get(i).a(matrix, path);
        }
    }

    boolean e() {
        return this.i;
    }

    g f(Matrix matrix) {
        b(h());
        return new a(new ArrayList(this.h), matrix);
    }

    float i() {
        return this.f2220c;
    }

    float j() {
        return this.f2221d;
    }

    float k() {
        return this.f2218a;
    }

    float l() {
        return this.f2219b;
    }

    public void m(float f2, float f3) {
        e eVar = new e();
        eVar.f2232b = f2;
        eVar.f2233c = f3;
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
}
