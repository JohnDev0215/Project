package c.b.a.a.v;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.google.android.material.R$styleable;

/* JADX INFO: compiled from: ShapeAppearanceModel.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c.b.a.a.v.c f1865a = new k(0.5f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f1866b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f1867c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f1868d;
    public d e;
    public c.b.a.a.v.c f;
    public c.b.a.a.v.c g;
    public c.b.a.a.v.c h;
    public c.b.a.a.v.c i;
    public f j;
    public f k;
    public f l;
    public f m;

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public interface c {
        c.b.a.a.v.c a(c.b.a.a.v.c cVar);
    }

    public static b a() {
        return new b();
    }

    public static b b(Context context, int i, int i2) {
        return c(context, i, i2, 0);
    }

    public static b c(Context context, int i, int i2, int i3) {
        return d(context, i, i2, new c.b.a.a.v.a(i3));
    }

    public static b d(Context context, int i, int i2, c.b.a.a.v.c cVar) {
        if (i2 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
            i = i2;
            context = contextThemeWrapper;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.ShapeAppearance);
        try {
            int i3 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamily, 0);
            int i4 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyTopLeft, i3);
            int i5 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyTopRight, i3);
            int i6 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyBottomRight, i3);
            int i7 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyBottomLeft, i3);
            c.b.a.a.v.c cVarM = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSize, cVar);
            c.b.a.a.v.c cVarM2 = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopLeft, cVarM);
            c.b.a.a.v.c cVarM3 = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopRight, cVarM);
            c.b.a.a.v.c cVarM4 = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomRight, cVarM);
            return new b().B(i4, cVarM2).F(i5, cVarM3).w(i6, cVarM4).s(i7, m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomLeft, cVarM));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static b e(Context context, AttributeSet attributeSet, int i, int i2) {
        return f(context, attributeSet, i, i2, 0);
    }

    public static b f(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        return g(context, attributeSet, i, i2, new c.b.a.a.v.a(i3));
    }

    public static b g(Context context, AttributeSet attributeSet, int i, int i2, c.b.a.a.v.c cVar) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialShape, i, i2);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearance, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearanceOverlay, 0);
        typedArrayObtainStyledAttributes.recycle();
        return d(context, resourceId, resourceId2, cVar);
    }

    public static c.b.a.a.v.c m(TypedArray typedArray, int i, c.b.a.a.v.c cVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i);
        if (typedValuePeekValue == null) {
            return cVar;
        }
        int i2 = typedValuePeekValue.type;
        return i2 == 5 ? new c.b.a.a.v.a(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i2 == 6 ? new k(typedValuePeekValue.getFraction(1.0f, 1.0f)) : cVar;
    }

    public f h() {
        return this.l;
    }

    public d i() {
        return this.e;
    }

    public c.b.a.a.v.c j() {
        return this.i;
    }

    public d k() {
        return this.f1868d;
    }

    public c.b.a.a.v.c l() {
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
        return this.f1866b;
    }

    public c.b.a.a.v.c r() {
        return this.f;
    }

    public d s() {
        return this.f1867c;
    }

    public c.b.a.a.v.c t() {
        return this.g;
    }

    public boolean u(RectF rectF) {
        boolean z = this.m.getClass().equals(f.class) && this.k.getClass().equals(f.class) && this.j.getClass().equals(f.class) && this.l.getClass().equals(f.class);
        float fA = this.f.a(rectF);
        return z && ((this.g.a(rectF) > fA ? 1 : (this.g.a(rectF) == fA ? 0 : -1)) == 0 && (this.i.a(rectF) > fA ? 1 : (this.i.a(rectF) == fA ? 0 : -1)) == 0 && (this.h.a(rectF) > fA ? 1 : (this.h.a(rectF) == fA ? 0 : -1)) == 0) && ((this.f1867c instanceof l) && (this.f1866b instanceof l) && (this.f1868d instanceof l) && (this.e instanceof l));
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

    public m(b bVar) {
        this.f1866b = bVar.f1869a;
        this.f1867c = bVar.f1870b;
        this.f1868d = bVar.f1871c;
        this.e = bVar.f1872d;
        this.f = bVar.e;
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
        public d f1869a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public d f1870b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public d f1871c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public d f1872d;
        public c.b.a.a.v.c e;
        public c.b.a.a.v.c f;
        public c.b.a.a.v.c g;
        public c.b.a.a.v.c h;
        public f i;
        public f j;
        public f k;
        public f l;

        public b() {
            this.f1869a = i.b();
            this.f1870b = i.b();
            this.f1871c = i.b();
            this.f1872d = i.b();
            this.e = new c.b.a.a.v.a(0.0f);
            this.f = new c.b.a.a.v.a(0.0f);
            this.g = new c.b.a.a.v.a(0.0f);
            this.h = new c.b.a.a.v.a(0.0f);
            this.i = i.c();
            this.j = i.c();
            this.k = i.c();
            this.l = i.c();
        }

        public static float n(d dVar) {
            if (dVar instanceof l) {
                return ((l) dVar).f1864a;
            }
            if (dVar instanceof e) {
                return ((e) dVar).f1849a;
            }
            return -1.0f;
        }

        public b A(f fVar) {
            this.i = fVar;
            return this;
        }

        public b B(int i, c.b.a.a.v.c cVar) {
            return C(i.a(i)).E(cVar);
        }

        public b C(d dVar) {
            this.f1869a = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                D(fN);
            }
            return this;
        }

        public b D(float f) {
            this.e = new c.b.a.a.v.a(f);
            return this;
        }

        public b E(c.b.a.a.v.c cVar) {
            this.e = cVar;
            return this;
        }

        public b F(int i, c.b.a.a.v.c cVar) {
            return G(i.a(i)).I(cVar);
        }

        public b G(d dVar) {
            this.f1870b = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                H(fN);
            }
            return this;
        }

        public b H(float f) {
            this.f = new c.b.a.a.v.a(f);
            return this;
        }

        public b I(c.b.a.a.v.c cVar) {
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

        public b s(int i, c.b.a.a.v.c cVar) {
            return t(i.a(i)).v(cVar);
        }

        public b t(d dVar) {
            this.f1872d = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                u(fN);
            }
            return this;
        }

        public b u(float f) {
            this.h = new c.b.a.a.v.a(f);
            return this;
        }

        public b v(c.b.a.a.v.c cVar) {
            this.h = cVar;
            return this;
        }

        public b w(int i, c.b.a.a.v.c cVar) {
            return x(i.a(i)).z(cVar);
        }

        public b x(d dVar) {
            this.f1871c = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                y(fN);
            }
            return this;
        }

        public b y(float f) {
            this.g = new c.b.a.a.v.a(f);
            return this;
        }

        public b z(c.b.a.a.v.c cVar) {
            this.g = cVar;
            return this;
        }

        public b(m mVar) {
            this.f1869a = i.b();
            this.f1870b = i.b();
            this.f1871c = i.b();
            this.f1872d = i.b();
            this.e = new c.b.a.a.v.a(0.0f);
            this.f = new c.b.a.a.v.a(0.0f);
            this.g = new c.b.a.a.v.a(0.0f);
            this.h = new c.b.a.a.v.a(0.0f);
            this.i = i.c();
            this.j = i.c();
            this.k = i.c();
            this.l = i.c();
            this.f1869a = mVar.f1866b;
            this.f1870b = mVar.f1867c;
            this.f1871c = mVar.f1868d;
            this.f1872d = mVar.e;
            this.e = mVar.f;
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
        this.f1866b = i.b();
        this.f1867c = i.b();
        this.f1868d = i.b();
        this.e = i.b();
        this.f = new c.b.a.a.v.a(0.0f);
        this.g = new c.b.a.a.v.a(0.0f);
        this.h = new c.b.a.a.v.a(0.0f);
        this.i = new c.b.a.a.v.a(0.0f);
        this.j = i.c();
        this.k = i.c();
        this.l = i.c();
        this.m = i.c();
    }
}
