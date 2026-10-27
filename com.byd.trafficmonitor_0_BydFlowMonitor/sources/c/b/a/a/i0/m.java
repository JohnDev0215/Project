package c.b.a.a.i0;

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
    public static final c.b.a.a.i0.c f3881a = new k(0.5f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f3882b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f3883c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f3884d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d f3885e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c.b.a.a.i0.c f3886f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.b.a.a.i0.c f3887g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c.b.a.a.i0.c f3888h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c.b.a.a.i0.c f3889i;
    public f j;
    public f k;
    public f l;
    public f m;

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public interface c {
        c.b.a.a.i0.c a(c.b.a.a.i0.c cVar);
    }

    public static b a() {
        return new b();
    }

    public static b b(Context context, int i2, int i3) {
        return c(context, i2, i3, 0);
    }

    public static b c(Context context, int i2, int i3, int i4) {
        return d(context, i2, i3, new c.b.a.a.i0.a(i4));
    }

    public static b d(Context context, int i2, int i3, c.b.a.a.i0.c cVar) {
        if (i3 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i2);
            i2 = i3;
            context = contextThemeWrapper;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i2, c.b.a.a.l.ShapeAppearance);
        try {
            int i4 = typedArrayObtainStyledAttributes.getInt(c.b.a.a.l.ShapeAppearance_cornerFamily, 0);
            int i5 = typedArrayObtainStyledAttributes.getInt(c.b.a.a.l.ShapeAppearance_cornerFamilyTopLeft, i4);
            int i6 = typedArrayObtainStyledAttributes.getInt(c.b.a.a.l.ShapeAppearance_cornerFamilyTopRight, i4);
            int i7 = typedArrayObtainStyledAttributes.getInt(c.b.a.a.l.ShapeAppearance_cornerFamilyBottomRight, i4);
            int i8 = typedArrayObtainStyledAttributes.getInt(c.b.a.a.l.ShapeAppearance_cornerFamilyBottomLeft, i4);
            c.b.a.a.i0.c cVarM = m(typedArrayObtainStyledAttributes, c.b.a.a.l.ShapeAppearance_cornerSize, cVar);
            c.b.a.a.i0.c cVarM2 = m(typedArrayObtainStyledAttributes, c.b.a.a.l.ShapeAppearance_cornerSizeTopLeft, cVarM);
            c.b.a.a.i0.c cVarM3 = m(typedArrayObtainStyledAttributes, c.b.a.a.l.ShapeAppearance_cornerSizeTopRight, cVarM);
            c.b.a.a.i0.c cVarM4 = m(typedArrayObtainStyledAttributes, c.b.a.a.l.ShapeAppearance_cornerSizeBottomRight, cVarM);
            return new b().B(i5, cVarM2).F(i6, cVarM3).w(i7, cVarM4).s(i8, m(typedArrayObtainStyledAttributes, c.b.a.a.l.ShapeAppearance_cornerSizeBottomLeft, cVarM));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static b e(Context context, AttributeSet attributeSet, int i2, int i3) {
        return f(context, attributeSet, i2, i3, 0);
    }

    public static b f(Context context, AttributeSet attributeSet, int i2, int i3, int i4) {
        return g(context, attributeSet, i2, i3, new c.b.a.a.i0.a(i4));
    }

    public static b g(Context context, AttributeSet attributeSet, int i2, int i3, c.b.a.a.i0.c cVar) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.b.a.a.l.MaterialShape, i2, i3);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.MaterialShape_shapeAppearance, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.MaterialShape_shapeAppearanceOverlay, 0);
        typedArrayObtainStyledAttributes.recycle();
        return d(context, resourceId, resourceId2, cVar);
    }

    public static c.b.a.a.i0.c m(TypedArray typedArray, int i2, c.b.a.a.i0.c cVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i2);
        if (typedValuePeekValue == null) {
            return cVar;
        }
        int i3 = typedValuePeekValue.type;
        return i3 == 5 ? new c.b.a.a.i0.a(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i3 == 6 ? new k(typedValuePeekValue.getFraction(1.0f, 1.0f)) : cVar;
    }

    public f h() {
        return this.l;
    }

    public d i() {
        return this.f3885e;
    }

    public c.b.a.a.i0.c j() {
        return this.f3889i;
    }

    public d k() {
        return this.f3884d;
    }

    public c.b.a.a.i0.c l() {
        return this.f3888h;
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
        return this.f3882b;
    }

    public c.b.a.a.i0.c r() {
        return this.f3886f;
    }

    public d s() {
        return this.f3883c;
    }

    public c.b.a.a.i0.c t() {
        return this.f3887g;
    }

    public boolean u(RectF rectF) {
        boolean z = this.m.getClass().equals(f.class) && this.k.getClass().equals(f.class) && this.j.getClass().equals(f.class) && this.l.getClass().equals(f.class);
        float fA = this.f3886f.a(rectF);
        return z && ((this.f3887g.a(rectF) > fA ? 1 : (this.f3887g.a(rectF) == fA ? 0 : -1)) == 0 && (this.f3889i.a(rectF) > fA ? 1 : (this.f3889i.a(rectF) == fA ? 0 : -1)) == 0 && (this.f3888h.a(rectF) > fA ? 1 : (this.f3888h.a(rectF) == fA ? 0 : -1)) == 0) && ((this.f3883c instanceof l) && (this.f3882b instanceof l) && (this.f3884d instanceof l) && (this.f3885e instanceof l));
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

    public m(b bVar) {
        this.f3882b = bVar.f3890a;
        this.f3883c = bVar.f3891b;
        this.f3884d = bVar.f3892c;
        this.f3885e = bVar.f3893d;
        this.f3886f = bVar.f3894e;
        this.f3887g = bVar.f3895f;
        this.f3888h = bVar.f3896g;
        this.f3889i = bVar.f3897h;
        this.j = bVar.f3898i;
        this.k = bVar.j;
        this.l = bVar.k;
        this.m = bVar.l;
    }

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f3890a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public d f3891b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public d f3892c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public d f3893d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public c.b.a.a.i0.c f3894e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public c.b.a.a.i0.c f3895f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public c.b.a.a.i0.c f3896g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public c.b.a.a.i0.c f3897h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public f f3898i;
        public f j;
        public f k;
        public f l;

        public b() {
            this.f3890a = i.b();
            this.f3891b = i.b();
            this.f3892c = i.b();
            this.f3893d = i.b();
            this.f3894e = new c.b.a.a.i0.a(0.0f);
            this.f3895f = new c.b.a.a.i0.a(0.0f);
            this.f3896g = new c.b.a.a.i0.a(0.0f);
            this.f3897h = new c.b.a.a.i0.a(0.0f);
            this.f3898i = i.c();
            this.j = i.c();
            this.k = i.c();
            this.l = i.c();
        }

        public static float n(d dVar) {
            if (dVar instanceof l) {
                return ((l) dVar).f3880a;
            }
            if (dVar instanceof e) {
                return ((e) dVar).f3855a;
            }
            return -1.0f;
        }

        public b A(f fVar) {
            this.f3898i = fVar;
            return this;
        }

        public b B(int i2, c.b.a.a.i0.c cVar) {
            return C(i.a(i2)).E(cVar);
        }

        public b C(d dVar) {
            this.f3890a = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                D(fN);
            }
            return this;
        }

        public b D(float f2) {
            this.f3894e = new c.b.a.a.i0.a(f2);
            return this;
        }

        public b E(c.b.a.a.i0.c cVar) {
            this.f3894e = cVar;
            return this;
        }

        public b F(int i2, c.b.a.a.i0.c cVar) {
            return G(i.a(i2)).I(cVar);
        }

        public b G(d dVar) {
            this.f3891b = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                H(fN);
            }
            return this;
        }

        public b H(float f2) {
            this.f3895f = new c.b.a.a.i0.a(f2);
            return this;
        }

        public b I(c.b.a.a.i0.c cVar) {
            this.f3895f = cVar;
            return this;
        }

        public m m() {
            return new m(this);
        }

        public b o(float f2) {
            return D(f2).H(f2).y(f2).u(f2);
        }

        public b p(int i2, float f2) {
            return q(i.a(i2)).o(f2);
        }

        public b q(d dVar) {
            return C(dVar).G(dVar).x(dVar).t(dVar);
        }

        public b r(f fVar) {
            this.k = fVar;
            return this;
        }

        public b s(int i2, c.b.a.a.i0.c cVar) {
            return t(i.a(i2)).v(cVar);
        }

        public b t(d dVar) {
            this.f3893d = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                u(fN);
            }
            return this;
        }

        public b u(float f2) {
            this.f3897h = new c.b.a.a.i0.a(f2);
            return this;
        }

        public b v(c.b.a.a.i0.c cVar) {
            this.f3897h = cVar;
            return this;
        }

        public b w(int i2, c.b.a.a.i0.c cVar) {
            return x(i.a(i2)).z(cVar);
        }

        public b x(d dVar) {
            this.f3892c = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                y(fN);
            }
            return this;
        }

        public b y(float f2) {
            this.f3896g = new c.b.a.a.i0.a(f2);
            return this;
        }

        public b z(c.b.a.a.i0.c cVar) {
            this.f3896g = cVar;
            return this;
        }

        public b(m mVar) {
            this.f3890a = i.b();
            this.f3891b = i.b();
            this.f3892c = i.b();
            this.f3893d = i.b();
            this.f3894e = new c.b.a.a.i0.a(0.0f);
            this.f3895f = new c.b.a.a.i0.a(0.0f);
            this.f3896g = new c.b.a.a.i0.a(0.0f);
            this.f3897h = new c.b.a.a.i0.a(0.0f);
            this.f3898i = i.c();
            this.j = i.c();
            this.k = i.c();
            this.l = i.c();
            this.f3890a = mVar.f3882b;
            this.f3891b = mVar.f3883c;
            this.f3892c = mVar.f3884d;
            this.f3893d = mVar.f3885e;
            this.f3894e = mVar.f3886f;
            this.f3895f = mVar.f3887g;
            this.f3896g = mVar.f3888h;
            this.f3897h = mVar.f3889i;
            this.f3898i = mVar.j;
            this.j = mVar.k;
            this.k = mVar.l;
            this.l = mVar.m;
        }
    }

    public m() {
        this.f3882b = i.b();
        this.f3883c = i.b();
        this.f3884d = i.b();
        this.f3885e = i.b();
        this.f3886f = new c.b.a.a.i0.a(0.0f);
        this.f3887g = new c.b.a.a.i0.a(0.0f);
        this.f3888h = new c.b.a.a.i0.a(0.0f);
        this.f3889i = new c.b.a.a.i0.a(0.0f);
        this.j = i.c();
        this.k = i.c();
        this.l = i.c();
        this.m = i.c();
    }
}
