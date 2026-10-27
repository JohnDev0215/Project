package c.b.a.a.w;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

/* JADX INFO: compiled from: ShapeAppearanceModel.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c.b.a.a.w.c f2769a = new k(0.5f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    d f2770b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    d f2771c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    d f2772d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    d f2773e;
    c.b.a.a.w.c f;
    c.b.a.a.w.c g;
    c.b.a.a.w.c h;
    c.b.a.a.w.c i;
    f j;
    f k;
    f l;
    f m;

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public interface c {
        c.b.a.a.w.c a(c.b.a.a.w.c cVar);
    }

    public static b a() {
        return new b();
    }

    public static b b(Context context, int i, int i2) {
        return c(context, i, i2, 0);
    }

    private static b c(Context context, int i, int i2, int i3) {
        return d(context, i, i2, new c.b.a.a.w.a(i3));
    }

    private static b d(Context context, int i, int i2, c.b.a.a.w.c cVar) {
        if (i2 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
            i = i2;
            context = contextThemeWrapper;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, c.b.a.a.l.R4);
        try {
            int i3 = typedArrayObtainStyledAttributes.getInt(c.b.a.a.l.S4, 0);
            int i4 = typedArrayObtainStyledAttributes.getInt(c.b.a.a.l.V4, i3);
            int i5 = typedArrayObtainStyledAttributes.getInt(c.b.a.a.l.W4, i3);
            int i6 = typedArrayObtainStyledAttributes.getInt(c.b.a.a.l.U4, i3);
            int i7 = typedArrayObtainStyledAttributes.getInt(c.b.a.a.l.T4, i3);
            c.b.a.a.w.c cVarM = m(typedArrayObtainStyledAttributes, c.b.a.a.l.X4, cVar);
            c.b.a.a.w.c cVarM2 = m(typedArrayObtainStyledAttributes, c.b.a.a.l.a5, cVarM);
            c.b.a.a.w.c cVarM3 = m(typedArrayObtainStyledAttributes, c.b.a.a.l.b5, cVarM);
            c.b.a.a.w.c cVarM4 = m(typedArrayObtainStyledAttributes, c.b.a.a.l.Z4, cVarM);
            return new b().B(i4, cVarM2).F(i5, cVarM3).w(i6, cVarM4).s(i7, m(typedArrayObtainStyledAttributes, c.b.a.a.l.Y4, cVarM));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static b e(Context context, AttributeSet attributeSet, int i, int i2) {
        return f(context, attributeSet, i, i2, 0);
    }

    public static b f(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        return g(context, attributeSet, i, i2, new c.b.a.a.w.a(i3));
    }

    public static b g(Context context, AttributeSet attributeSet, int i, int i2, c.b.a.a.w.c cVar) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.b.a.a.l.Y3, i, i2);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.Z3, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.a4, 0);
        typedArrayObtainStyledAttributes.recycle();
        return d(context, resourceId, resourceId2, cVar);
    }

    private static c.b.a.a.w.c m(TypedArray typedArray, int i, c.b.a.a.w.c cVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i);
        if (typedValuePeekValue == null) {
            return cVar;
        }
        int i2 = typedValuePeekValue.type;
        return i2 == 5 ? new c.b.a.a.w.a(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i2 == 6 ? new k(typedValuePeekValue.getFraction(1.0f, 1.0f)) : cVar;
    }

    public f h() {
        return this.l;
    }

    public d i() {
        return this.f2773e;
    }

    public c.b.a.a.w.c j() {
        return this.i;
    }

    public d k() {
        return this.f2772d;
    }

    public c.b.a.a.w.c l() {
        return this.h;
    }

    public f n() {
        return this.m;
    }

    public f o() {
        return this.k;
    }

    public f p() {
        return this.j;
    }

    public d q() {
        return this.f2770b;
    }

    public c.b.a.a.w.c r() {
        return this.f;
    }

    public d s() {
        return this.f2771c;
    }

    public c.b.a.a.w.c t() {
        return this.g;
    }

    public boolean u(RectF rectF) {
        boolean z = this.m.getClass().equals(f.class) && this.k.getClass().equals(f.class) && this.j.getClass().equals(f.class) && this.l.getClass().equals(f.class);
        float fA = this.f.a(rectF);
        return z && ((this.g.a(rectF) > fA ? 1 : (this.g.a(rectF) == fA ? 0 : -1)) == 0 && (this.i.a(rectF) > fA ? 1 : (this.i.a(rectF) == fA ? 0 : -1)) == 0 && (this.h.a(rectF) > fA ? 1 : (this.h.a(rectF) == fA ? 0 : -1)) == 0) && ((this.f2771c instanceof l) && (this.f2770b instanceof l) && (this.f2772d instanceof l) && (this.f2773e instanceof l));
    }

    public b v() {
        return new b(this);
    }

    public m w(float f) {
        return v().o(f).m();
    }

    public m x(c cVar) {
        return v().E(cVar.a(r())).I(cVar.a(t())).v(cVar.a(j())).z(cVar.a(l())).m();
    }

    private m(b bVar) {
        this.f2770b = bVar.f2774a;
        this.f2771c = bVar.f2775b;
        this.f2772d = bVar.f2776c;
        this.f2773e = bVar.f2777d;
        this.f = bVar.f2778e;
        this.g = bVar.f;
        this.h = bVar.g;
        this.i = bVar.h;
        this.j = bVar.i;
        this.k = bVar.j;
        this.l = bVar.k;
        this.m = bVar.l;
    }

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private d f2774a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private d f2775b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private d f2776c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private d f2777d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private c.b.a.a.w.c f2778e;
        private c.b.a.a.w.c f;
        private c.b.a.a.w.c g;
        private c.b.a.a.w.c h;
        private f i;
        private f j;
        private f k;
        private f l;

        public b() {
            this.f2774a = i.b();
            this.f2775b = i.b();
            this.f2776c = i.b();
            this.f2777d = i.b();
            this.f2778e = new c.b.a.a.w.a(0.0f);
            this.f = new c.b.a.a.w.a(0.0f);
            this.g = new c.b.a.a.w.a(0.0f);
            this.h = new c.b.a.a.w.a(0.0f);
            this.i = i.c();
            this.j = i.c();
            this.k = i.c();
            this.l = i.c();
        }

        private static float n(d dVar) {
            if (dVar instanceof l) {
                return ((l) dVar).f2768a;
            }
            if (dVar instanceof e) {
                return ((e) dVar).f2751a;
            }
            return -1.0f;
        }

        public b A(f fVar) {
            this.i = fVar;
            return this;
        }

        public b B(int i, c.b.a.a.w.c cVar) {
            return C(i.a(i)).E(cVar);
        }

        public b C(d dVar) {
            this.f2774a = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                D(fN);
            }
            return this;
        }

        public b D(float f) {
            this.f2778e = new c.b.a.a.w.a(f);
            return this;
        }

        public b E(c.b.a.a.w.c cVar) {
            this.f2778e = cVar;
            return this;
        }

        public b F(int i, c.b.a.a.w.c cVar) {
            return G(i.a(i)).I(cVar);
        }

        public b G(d dVar) {
            this.f2775b = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                H(fN);
            }
            return this;
        }

        public b H(float f) {
            this.f = new c.b.a.a.w.a(f);
            return this;
        }

        public b I(c.b.a.a.w.c cVar) {
            this.f = cVar;
            return this;
        }

        public m m() {
            return new m(this);
        }

        public b o(float f) {
            return D(f).H(f).y(f).u(f);
        }

        public b p(int i, float f) {
            return q(i.a(i)).o(f);
        }

        public b q(d dVar) {
            return C(dVar).G(dVar).x(dVar).t(dVar);
        }

        public b r(f fVar) {
            this.k = fVar;
            return this;
        }

        public b s(int i, c.b.a.a.w.c cVar) {
            return t(i.a(i)).v(cVar);
        }

        public b t(d dVar) {
            this.f2777d = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                u(fN);
            }
            return this;
        }

        public b u(float f) {
            this.h = new c.b.a.a.w.a(f);
            return this;
        }

        public b v(c.b.a.a.w.c cVar) {
            this.h = cVar;
            return this;
        }

        public b w(int i, c.b.a.a.w.c cVar) {
            return x(i.a(i)).z(cVar);
        }

        public b x(d dVar) {
            this.f2776c = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                y(fN);
            }
            return this;
        }

        public b y(float f) {
            this.g = new c.b.a.a.w.a(f);
            return this;
        }

        public b z(c.b.a.a.w.c cVar) {
            this.g = cVar;
            return this;
        }

        public b(m mVar) {
            this.f2774a = i.b();
            this.f2775b = i.b();
            this.f2776c = i.b();
            this.f2777d = i.b();
            this.f2778e = new c.b.a.a.w.a(0.0f);
            this.f = new c.b.a.a.w.a(0.0f);
            this.g = new c.b.a.a.w.a(0.0f);
            this.h = new c.b.a.a.w.a(0.0f);
            this.i = i.c();
            this.j = i.c();
            this.k = i.c();
            this.l = i.c();
            this.f2774a = mVar.f2770b;
            this.f2775b = mVar.f2771c;
            this.f2776c = mVar.f2772d;
            this.f2777d = mVar.f2773e;
            this.f2778e = mVar.f;
            this.f = mVar.g;
            this.g = mVar.h;
            this.h = mVar.i;
            this.i = mVar.j;
            this.j = mVar.k;
            this.k = mVar.l;
            this.l = mVar.m;
        }
    }

    public m() {
        this.f2770b = i.b();
        this.f2771c = i.b();
        this.f2772d = i.b();
        this.f2773e = i.b();
        this.f = new c.b.a.a.w.a(0.0f);
        this.g = new c.b.a.a.w.a(0.0f);
        this.h = new c.b.a.a.w.a(0.0f);
        this.i = new c.b.a.a.w.a(0.0f);
        this.j = i.c();
        this.k = i.c();
        this.l = i.c();
        this.m = i.c();
    }
}
