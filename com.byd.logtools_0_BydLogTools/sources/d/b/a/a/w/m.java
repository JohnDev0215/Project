package d.b.a.a.w;

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
    public static final d.b.a.a.w.c f4853a = new k(0.5f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    d f4854b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    d f4855c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    d f4856d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    d f4857e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    d.b.a.a.w.c f4858f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    d.b.a.a.w.c f4859g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    d.b.a.a.w.c f4860h;
    d.b.a.a.w.c i;
    f j;
    f k;
    f l;
    f m;

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public interface c {
        d.b.a.a.w.c a(d.b.a.a.w.c cVar);
    }

    public static b a() {
        return new b();
    }

    public static b b(Context context, int i, int i2) {
        return c(context, i, i2, 0);
    }

    private static b c(Context context, int i, int i2, int i3) {
        return d(context, i, i2, new d.b.a.a.w.a(i3));
    }

    private static b d(Context context, int i, int i2, d.b.a.a.w.c cVar) {
        if (i2 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
            i = i2;
            context = contextThemeWrapper;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, d.b.a.a.l.R4);
        try {
            int i3 = typedArrayObtainStyledAttributes.getInt(d.b.a.a.l.S4, 0);
            int i4 = typedArrayObtainStyledAttributes.getInt(d.b.a.a.l.V4, i3);
            int i5 = typedArrayObtainStyledAttributes.getInt(d.b.a.a.l.W4, i3);
            int i6 = typedArrayObtainStyledAttributes.getInt(d.b.a.a.l.U4, i3);
            int i7 = typedArrayObtainStyledAttributes.getInt(d.b.a.a.l.T4, i3);
            d.b.a.a.w.c cVarM = m(typedArrayObtainStyledAttributes, d.b.a.a.l.X4, cVar);
            d.b.a.a.w.c cVarM2 = m(typedArrayObtainStyledAttributes, d.b.a.a.l.a5, cVarM);
            d.b.a.a.w.c cVarM3 = m(typedArrayObtainStyledAttributes, d.b.a.a.l.b5, cVarM);
            d.b.a.a.w.c cVarM4 = m(typedArrayObtainStyledAttributes, d.b.a.a.l.Z4, cVarM);
            return new b().B(i4, cVarM2).F(i5, cVarM3).w(i6, cVarM4).s(i7, m(typedArrayObtainStyledAttributes, d.b.a.a.l.Y4, cVarM));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static b e(Context context, AttributeSet attributeSet, int i, int i2) {
        return f(context, attributeSet, i, i2, 0);
    }

    public static b f(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        return g(context, attributeSet, i, i2, new d.b.a.a.w.a(i3));
    }

    public static b g(Context context, AttributeSet attributeSet, int i, int i2, d.b.a.a.w.c cVar) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.b.a.a.l.Y3, i, i2);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.Z3, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.a4, 0);
        typedArrayObtainStyledAttributes.recycle();
        return d(context, resourceId, resourceId2, cVar);
    }

    private static d.b.a.a.w.c m(TypedArray typedArray, int i, d.b.a.a.w.c cVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i);
        if (typedValuePeekValue == null) {
            return cVar;
        }
        int i2 = typedValuePeekValue.type;
        return i2 == 5 ? new d.b.a.a.w.a(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i2 == 6 ? new k(typedValuePeekValue.getFraction(1.0f, 1.0f)) : cVar;
    }

    public f h() {
        return this.l;
    }

    public d i() {
        return this.f4857e;
    }

    public d.b.a.a.w.c j() {
        return this.i;
    }

    public d k() {
        return this.f4856d;
    }

    public d.b.a.a.w.c l() {
        return this.f4860h;
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
        return this.f4854b;
    }

    public d.b.a.a.w.c r() {
        return this.f4858f;
    }

    public d s() {
        return this.f4855c;
    }

    public d.b.a.a.w.c t() {
        return this.f4859g;
    }

    public boolean u(RectF rectF) {
        boolean z = this.m.getClass().equals(f.class) && this.k.getClass().equals(f.class) && this.j.getClass().equals(f.class) && this.l.getClass().equals(f.class);
        float fA = this.f4858f.a(rectF);
        return z && ((this.f4859g.a(rectF) > fA ? 1 : (this.f4859g.a(rectF) == fA ? 0 : -1)) == 0 && (this.i.a(rectF) > fA ? 1 : (this.i.a(rectF) == fA ? 0 : -1)) == 0 && (this.f4860h.a(rectF) > fA ? 1 : (this.f4860h.a(rectF) == fA ? 0 : -1)) == 0) && ((this.f4855c instanceof l) && (this.f4854b instanceof l) && (this.f4856d instanceof l) && (this.f4857e instanceof l));
    }

    public b v() {
        return new b(this);
    }

    public m w(float f2) {
        return v().o(f2).m();
    }

    public m x(c cVar) {
        return v().E(cVar.a(r())).I(cVar.a(t())).v(cVar.a(j())).z(cVar.a(l())).m();
    }

    private m(b bVar) {
        this.f4854b = bVar.f4861a;
        this.f4855c = bVar.f4862b;
        this.f4856d = bVar.f4863c;
        this.f4857e = bVar.f4864d;
        this.f4858f = bVar.f4865e;
        this.f4859g = bVar.f4866f;
        this.f4860h = bVar.f4867g;
        this.i = bVar.f4868h;
        this.j = bVar.i;
        this.k = bVar.j;
        this.l = bVar.k;
        this.m = bVar.l;
    }

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private d f4861a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private d f4862b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private d f4863c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private d f4864d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private d.b.a.a.w.c f4865e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private d.b.a.a.w.c f4866f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private d.b.a.a.w.c f4867g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private d.b.a.a.w.c f4868h;
        private f i;
        private f j;
        private f k;
        private f l;

        public b() {
            this.f4861a = i.b();
            this.f4862b = i.b();
            this.f4863c = i.b();
            this.f4864d = i.b();
            this.f4865e = new d.b.a.a.w.a(0.0f);
            this.f4866f = new d.b.a.a.w.a(0.0f);
            this.f4867g = new d.b.a.a.w.a(0.0f);
            this.f4868h = new d.b.a.a.w.a(0.0f);
            this.i = i.c();
            this.j = i.c();
            this.k = i.c();
            this.l = i.c();
        }

        private static float n(d dVar) {
            if (dVar instanceof l) {
                return ((l) dVar).f4852a;
            }
            if (dVar instanceof e) {
                return ((e) dVar).f4829a;
            }
            return -1.0f;
        }

        public b A(f fVar) {
            this.i = fVar;
            return this;
        }

        public b B(int i, d.b.a.a.w.c cVar) {
            return C(i.a(i)).E(cVar);
        }

        public b C(d dVar) {
            this.f4861a = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                D(fN);
            }
            return this;
        }

        public b D(float f2) {
            this.f4865e = new d.b.a.a.w.a(f2);
            return this;
        }

        public b E(d.b.a.a.w.c cVar) {
            this.f4865e = cVar;
            return this;
        }

        public b F(int i, d.b.a.a.w.c cVar) {
            return G(i.a(i)).I(cVar);
        }

        public b G(d dVar) {
            this.f4862b = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                H(fN);
            }
            return this;
        }

        public b H(float f2) {
            this.f4866f = new d.b.a.a.w.a(f2);
            return this;
        }

        public b I(d.b.a.a.w.c cVar) {
            this.f4866f = cVar;
            return this;
        }

        public m m() {
            return new m(this);
        }

        public b o(float f2) {
            return D(f2).H(f2).y(f2).u(f2);
        }

        public b p(int i, float f2) {
            return q(i.a(i)).o(f2);
        }

        public b q(d dVar) {
            return C(dVar).G(dVar).x(dVar).t(dVar);
        }

        public b r(f fVar) {
            this.k = fVar;
            return this;
        }

        public b s(int i, d.b.a.a.w.c cVar) {
            return t(i.a(i)).v(cVar);
        }

        public b t(d dVar) {
            this.f4864d = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                u(fN);
            }
            return this;
        }

        public b u(float f2) {
            this.f4868h = new d.b.a.a.w.a(f2);
            return this;
        }

        public b v(d.b.a.a.w.c cVar) {
            this.f4868h = cVar;
            return this;
        }

        public b w(int i, d.b.a.a.w.c cVar) {
            return x(i.a(i)).z(cVar);
        }

        public b x(d dVar) {
            this.f4863c = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                y(fN);
            }
            return this;
        }

        public b y(float f2) {
            this.f4867g = new d.b.a.a.w.a(f2);
            return this;
        }

        public b z(d.b.a.a.w.c cVar) {
            this.f4867g = cVar;
            return this;
        }

        public b(m mVar) {
            this.f4861a = i.b();
            this.f4862b = i.b();
            this.f4863c = i.b();
            this.f4864d = i.b();
            this.f4865e = new d.b.a.a.w.a(0.0f);
            this.f4866f = new d.b.a.a.w.a(0.0f);
            this.f4867g = new d.b.a.a.w.a(0.0f);
            this.f4868h = new d.b.a.a.w.a(0.0f);
            this.i = i.c();
            this.j = i.c();
            this.k = i.c();
            this.l = i.c();
            this.f4861a = mVar.f4854b;
            this.f4862b = mVar.f4855c;
            this.f4863c = mVar.f4856d;
            this.f4864d = mVar.f4857e;
            this.f4865e = mVar.f4858f;
            this.f4866f = mVar.f4859g;
            this.f4867g = mVar.f4860h;
            this.f4868h = mVar.i;
            this.i = mVar.j;
            this.j = mVar.k;
            this.k = mVar.l;
            this.l = mVar.m;
        }
    }

    public m() {
        this.f4854b = i.b();
        this.f4855c = i.b();
        this.f4856d = i.b();
        this.f4857e = i.b();
        this.f4858f = new d.b.a.a.w.a(0.0f);
        this.f4859g = new d.b.a.a.w.a(0.0f);
        this.f4860h = new d.b.a.a.w.a(0.0f);
        this.i = new d.b.a.a.w.a(0.0f);
        this.j = i.c();
        this.k = i.c();
        this.l = i.c();
        this.m = i.c();
    }
}
