package e3;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: ShapePath.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Deprecated
    public float f6270a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Deprecated
    public float f6271b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Deprecated
    public float f6272c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Deprecated
    public float f6273d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Deprecated
    public float f6274e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Deprecated
    public float f6275f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final List<f> f6276g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final List<g> f6277h = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f6278i;

    /* JADX INFO: compiled from: ShapePath.java */
    class a extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f6279b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Matrix f6280c;

        a(List list, Matrix matrix) {
            this.f6279b = list;
            this.f6280c = matrix;
        }

        @Override // e3.m.g
        public void a(Matrix matrix, d3.a aVar, int i7, Canvas canvas) {
            Iterator it = this.f6279b.iterator();
            while (it.hasNext()) {
                ((g) it.next()).a(this.f6280c, aVar, i7, canvas);
            }
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    static class b extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final d f6282b;

        public b(d dVar) {
            this.f6282b = dVar;
        }

        @Override // e3.m.g
        public void a(Matrix matrix, d3.a aVar, int i7, Canvas canvas) {
            aVar.a(canvas, matrix, new RectF(this.f6282b.k(), this.f6282b.o(), this.f6282b.l(), this.f6282b.j()), i7, this.f6282b.m(), this.f6282b.n());
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    static class c extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f6283b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final float f6284c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final float f6285d;

        public c(e eVar, float f7, float f8) {
            this.f6283b = eVar;
            this.f6284c = f7;
            this.f6285d = f8;
        }

        @Override // e3.m.g
        public void a(Matrix matrix, d3.a aVar, int i7, Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.f6283b.f6294c - this.f6285d, this.f6283b.f6293b - this.f6284c), 0.0f);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.preTranslate(this.f6284c, this.f6285d);
            matrix2.preRotate(c());
            aVar.b(canvas, matrix2, rectF, i7);
        }

        float c() {
            return (float) Math.toDegrees(Math.atan((this.f6283b.f6294c - this.f6285d) / (this.f6283b.f6293b - this.f6284c)));
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class d extends f {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private static final RectF f6286h = new RectF();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Deprecated
        public float f6287b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Deprecated
        public float f6288c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Deprecated
        public float f6289d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @Deprecated
        public float f6290e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @Deprecated
        public float f6291f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        @Deprecated
        public float f6292g;

        public d(float f7, float f8, float f9, float f10) {
            q(f7);
            u(f8);
            r(f9);
            p(f10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float j() {
            return this.f6290e;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float k() {
            return this.f6287b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float l() {
            return this.f6289d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float m() {
            return this.f6291f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float n() {
            return this.f6292g;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float o() {
            return this.f6288c;
        }

        private void p(float f7) {
            this.f6290e = f7;
        }

        private void q(float f7) {
            this.f6287b = f7;
        }

        private void r(float f7) {
            this.f6289d = f7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void s(float f7) {
            this.f6291f = f7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void t(float f7) {
            this.f6292g = f7;
        }

        private void u(float f7) {
            this.f6288c = f7;
        }

        @Override // e3.m.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f6295a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF = f6286h;
            rectF.set(k(), o(), l(), j());
            path.arcTo(rectF, m(), n(), false);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class e extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private float f6293b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f6294c;

        @Override // e3.m.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f6295a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f6293b, this.f6294c);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static abstract class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final Matrix f6295a = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    /* JADX INFO: compiled from: ShapePath.java */
    static abstract class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final Matrix f6296a = new Matrix();

        g() {
        }

        public abstract void a(Matrix matrix, d3.a aVar, int i7, Canvas canvas);

        public final void b(d3.a aVar, int i7, Canvas canvas) {
            a(f6296a, aVar, i7, canvas);
        }
    }

    public m() {
        n(0.0f, 0.0f);
    }

    private void b(float f7) {
        if (g() == f7) {
            return;
        }
        float fG = ((f7 - g()) + 360.0f) % 360.0f;
        if (fG > 180.0f) {
            return;
        }
        d dVar = new d(i(), j(), i(), j());
        dVar.s(g());
        dVar.t(fG);
        this.f6277h.add(new b(dVar));
        p(f7);
    }

    private void c(g gVar, float f7, float f8) {
        b(f7);
        this.f6277h.add(gVar);
        p(f8);
    }

    private float g() {
        return this.f6274e;
    }

    private float h() {
        return this.f6275f;
    }

    private void p(float f7) {
        this.f6274e = f7;
    }

    private void q(float f7) {
        this.f6275f = f7;
    }

    private void r(float f7) {
        this.f6272c = f7;
    }

    private void s(float f7) {
        this.f6273d = f7;
    }

    private void t(float f7) {
        this.f6270a = f7;
    }

    private void u(float f7) {
        this.f6271b = f7;
    }

    public void a(float f7, float f8, float f9, float f10, float f11, float f12) {
        d dVar = new d(f7, f8, f9, f10);
        dVar.s(f11);
        dVar.t(f12);
        this.f6276g.add(dVar);
        b bVar = new b(dVar);
        float f13 = f11 + f12;
        boolean z6 = f12 < 0.0f;
        if (z6) {
            f11 = (f11 + 180.0f) % 360.0f;
        }
        c(bVar, f11, z6 ? (180.0f + f13) % 360.0f : f13);
        double d7 = f13;
        r(((f7 + f9) * 0.5f) + (((f9 - f7) / 2.0f) * ((float) Math.cos(Math.toRadians(d7)))));
        s(((f8 + f10) * 0.5f) + (((f10 - f8) / 2.0f) * ((float) Math.sin(Math.toRadians(d7)))));
    }

    public void d(Matrix matrix, Path path) {
        int size = this.f6276g.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.f6276g.get(i7).a(matrix, path);
        }
    }

    boolean e() {
        return this.f6278i;
    }

    g f(Matrix matrix) {
        b(h());
        return new a(new ArrayList(this.f6277h), new Matrix(matrix));
    }

    float i() {
        return this.f6272c;
    }

    float j() {
        return this.f6273d;
    }

    float k() {
        return this.f6270a;
    }

    float l() {
        return this.f6271b;
    }

    public void m(float f7, float f8) {
        e eVar = new e();
        eVar.f6293b = f7;
        eVar.f6294c = f8;
        this.f6276g.add(eVar);
        c cVar = new c(eVar, i(), j());
        c(cVar, cVar.c() + 270.0f, cVar.c() + 270.0f);
        r(f7);
        s(f8);
    }

    public void n(float f7, float f8) {
        o(f7, f8, 270.0f, 0.0f);
    }

    public void o(float f7, float f8, float f9, float f10) {
        t(f7);
        u(f8);
        r(f7);
        s(f8);
        p(f9);
        q((f9 + f10) % 360.0f);
        this.f6276g.clear();
        this.f6277h.clear();
        this.f6278i = false;
    }
}
