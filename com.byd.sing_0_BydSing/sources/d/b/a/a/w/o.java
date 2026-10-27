package d.b.a.a.w;

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
    public float f4031a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Deprecated
    public float f4032b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Deprecated
    public float f4033c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Deprecated
    public float f4034d;

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
        final /* synthetic */ List f4035b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Matrix f4036c;

        a(List list, Matrix matrix) {
            this.f4035b = list;
            this.f4036c = matrix;
        }

        @Override // d.b.a.a.w.o.g
        public void a(Matrix matrix, d.b.a.a.v.a aVar, int i, Canvas canvas) {
            Iterator it = this.f4035b.iterator();
            while (it.hasNext()) {
                ((g) it.next()).a(this.f4036c, aVar, i, canvas);
            }
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    static class b extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final d f4038b;

        public b(d dVar) {
            this.f4038b = dVar;
        }

        @Override // d.b.a.a.w.o.g
        public void a(Matrix matrix, d.b.a.a.v.a aVar, int i, Canvas canvas) {
            aVar.a(canvas, matrix, new RectF(this.f4038b.k(), this.f4038b.o(), this.f4038b.l(), this.f4038b.j()), i, this.f4038b.m(), this.f4038b.n());
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    static class c extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f4039b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final float f4040c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final float f4041d;

        public c(e eVar, float f, float f2) {
            this.f4039b = eVar;
            this.f4040c = f;
            this.f4041d = f2;
        }

        @Override // d.b.a.a.w.o.g
        public void a(Matrix matrix, d.b.a.a.v.a aVar, int i, Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.f4039b.f4046c - this.f4041d, this.f4039b.f4045b - this.f4040c), 0.0f);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.preTranslate(this.f4040c, this.f4041d);
            matrix2.preRotate(c());
            aVar.b(canvas, matrix2, rectF, i);
        }

        float c() {
            return (float) Math.toDegrees(Math.atan((this.f4039b.f4046c - this.f4041d) / (this.f4039b.f4045b - this.f4040c)));
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class d extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final RectF f4042b = new RectF();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Deprecated
        public float f4043c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Deprecated
        public float f4044d;

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
            return this.f4043c;
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
            return this.f4044d;
        }

        private void p(float f) {
            this.f = f;
        }

        private void q(float f) {
            this.f4043c = f;
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
            this.f4044d = f;
        }

        @Override // d.b.a.a.w.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f4047a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF = f4042b;
            rectF.set(k(), o(), l(), j());
            path.arcTo(rectF, m(), n(), false);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class e extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private float f4045b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f4046c;

        @Override // d.b.a.a.w.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f4047a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f4045b, this.f4046c);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static abstract class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final Matrix f4047a = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    /* JADX INFO: compiled from: ShapePath.java */
    static abstract class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final Matrix f4048a = new Matrix();

        g() {
        }

        public abstract void a(Matrix matrix, d.b.a.a.v.a aVar, int i, Canvas canvas);

        public final void b(d.b.a.a.v.a aVar, int i, Canvas canvas) {
            a(f4048a, aVar, i, canvas);
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
        this.f4033c = f2;
    }

    private void s(float f2) {
        this.f4034d = f2;
    }

    private void t(float f2) {
        this.f4031a = f2;
    }

    private void u(float f2) {
        this.f4032b = f2;
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
        return this.f4033c;
    }

    float j() {
        return this.f4034d;
    }

    float k() {
        return this.f4031a;
    }

    float l() {
        return this.f4032b;
    }

    public void m(float f2, float f3) {
        e eVar = new e();
        eVar.f4045b = f2;
        eVar.f4046c = f3;
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
