package d.b.a.a.x;

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
    public float f4808a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Deprecated
    public float f4809b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Deprecated
    public float f4810c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Deprecated
    public float f4811d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Deprecated
    public float f4812e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Deprecated
    public float f4813f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final List<f> f4814g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final List<g> f4815h = new ArrayList();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f4816i;

    /* JADX INFO: compiled from: ShapePath.java */
    class a extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ List f4817b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Matrix f4818c;

        a(List list, Matrix matrix) {
            this.f4817b = list;
            this.f4818c = matrix;
        }

        @Override // d.b.a.a.x.o.g
        public void a(Matrix matrix, d.b.a.a.w.a aVar, int i2, Canvas canvas) {
            Iterator it = this.f4817b.iterator();
            while (it.hasNext()) {
                ((g) it.next()).a(this.f4818c, aVar, i2, canvas);
            }
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    static class b extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final d f4820b;

        public b(d dVar) {
            this.f4820b = dVar;
        }

        @Override // d.b.a.a.x.o.g
        public void a(Matrix matrix, d.b.a.a.w.a aVar, int i2, Canvas canvas) {
            aVar.a(canvas, matrix, new RectF(this.f4820b.k(), this.f4820b.o(), this.f4820b.l(), this.f4820b.j()), i2, this.f4820b.m(), this.f4820b.n());
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    static class c extends g {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f4821b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final float f4822c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final float f4823d;

        public c(e eVar, float f2, float f3) {
            this.f4821b = eVar;
            this.f4822c = f2;
            this.f4823d = f3;
        }

        @Override // d.b.a.a.x.o.g
        public void a(Matrix matrix, d.b.a.a.w.a aVar, int i2, Canvas canvas) {
            RectF rectF = new RectF(0.0f, 0.0f, (float) Math.hypot(this.f4821b.f4832c - this.f4823d, this.f4821b.f4831b - this.f4822c), 0.0f);
            Matrix matrix2 = new Matrix(matrix);
            matrix2.preTranslate(this.f4822c, this.f4823d);
            matrix2.preRotate(c());
            aVar.b(canvas, matrix2, rectF, i2);
        }

        float c() {
            return (float) Math.toDegrees(Math.atan((this.f4821b.f4832c - this.f4823d) / (this.f4821b.f4831b - this.f4822c)));
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class d extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final RectF f4824b = new RectF();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Deprecated
        public float f4825c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Deprecated
        public float f4826d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        @Deprecated
        public float f4827e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @Deprecated
        public float f4828f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        @Deprecated
        public float f4829g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        @Deprecated
        public float f4830h;

        public d(float f2, float f3, float f4, float f5) {
            q(f2);
            u(f3);
            r(f4);
            p(f5);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float j() {
            return this.f4828f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float k() {
            return this.f4825c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float l() {
            return this.f4827e;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float m() {
            return this.f4829g;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float n() {
            return this.f4830h;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public float o() {
            return this.f4826d;
        }

        private void p(float f2) {
            this.f4828f = f2;
        }

        private void q(float f2) {
            this.f4825c = f2;
        }

        private void r(float f2) {
            this.f4827e = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void s(float f2) {
            this.f4829g = f2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void t(float f2) {
            this.f4830h = f2;
        }

        private void u(float f2) {
            this.f4826d = f2;
        }

        @Override // d.b.a.a.x.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f4833a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            RectF rectF = f4824b;
            rectF.set(k(), o(), l(), j());
            path.arcTo(rectF, m(), n(), false);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static class e extends f {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private float f4831b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f4832c;

        @Override // d.b.a.a.x.o.f
        public void a(Matrix matrix, Path path) {
            Matrix matrix2 = this.f4833a;
            matrix.invert(matrix2);
            path.transform(matrix2);
            path.lineTo(this.f4831b, this.f4832c);
            path.transform(matrix);
        }
    }

    /* JADX INFO: compiled from: ShapePath.java */
    public static abstract class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected final Matrix f4833a = new Matrix();

        public abstract void a(Matrix matrix, Path path);
    }

    /* JADX INFO: compiled from: ShapePath.java */
    static abstract class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final Matrix f4834a = new Matrix();

        g() {
        }

        public abstract void a(Matrix matrix, d.b.a.a.w.a aVar, int i2, Canvas canvas);

        public final void b(d.b.a.a.w.a aVar, int i2, Canvas canvas) {
            a(f4834a, aVar, i2, canvas);
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
        this.f4815h.add(new b(dVar));
        p(f2);
    }

    private void c(g gVar, float f2, float f3) {
        b(f2);
        this.f4815h.add(gVar);
        p(f3);
    }

    private float g() {
        return this.f4812e;
    }

    private float h() {
        return this.f4813f;
    }

    private void p(float f2) {
        this.f4812e = f2;
    }

    private void q(float f2) {
        this.f4813f = f2;
    }

    private void r(float f2) {
        this.f4810c = f2;
    }

    private void s(float f2) {
        this.f4811d = f2;
    }

    private void t(float f2) {
        this.f4808a = f2;
    }

    private void u(float f2) {
        this.f4809b = f2;
    }

    public void a(float f2, float f3, float f4, float f5, float f6, float f7) {
        d dVar = new d(f2, f3, f4, f5);
        dVar.s(f6);
        dVar.t(f7);
        this.f4814g.add(dVar);
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
        int size = this.f4814g.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f4814g.get(i2).a(matrix, path);
        }
    }

    boolean e() {
        return this.f4816i;
    }

    g f(Matrix matrix) {
        b(h());
        return new a(new ArrayList(this.f4815h), matrix);
    }

    float i() {
        return this.f4810c;
    }

    float j() {
        return this.f4811d;
    }

    float k() {
        return this.f4808a;
    }

    float l() {
        return this.f4809b;
    }

    public void m(float f2, float f3) {
        e eVar = new e();
        eVar.f4831b = f2;
        eVar.f4832c = f3;
        this.f4814g.add(eVar);
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
        this.f4814g.clear();
        this.f4815h.clear();
        this.f4816i = false;
    }
}
