package d.b.a.a.x;

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
    public static final d.b.a.a.x.c f4776a = new k(0.5f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    d f4777b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    d f4778c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    d f4779d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    d f4780e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    d.b.a.a.x.c f4781f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    d.b.a.a.x.c f4782g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    d.b.a.a.x.c f4783h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    d.b.a.a.x.c f4784i;
    f j;
    f k;
    f l;
    f m;

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public interface c {
        d.b.a.a.x.c a(d.b.a.a.x.c cVar);
    }

    public static b a() {
        return new b();
    }

    public static b b(Context context, int i2, int i3) {
        return c(context, i2, i3, 0);
    }

    private static b c(Context context, int i2, int i3, int i4) {
        return d(context, i2, i3, new d.b.a.a.x.a(i4));
    }

    private static b d(Context context, int i2, int i3, d.b.a.a.x.c cVar) {
        if (i3 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i2);
            i2 = i3;
            context = contextThemeWrapper;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i2, d.b.a.a.l.ShapeAppearance);
        try {
            int i4 = typedArrayObtainStyledAttributes.getInt(d.b.a.a.l.ShapeAppearance_cornerFamily, 0);
            int i5 = typedArrayObtainStyledAttributes.getInt(d.b.a.a.l.ShapeAppearance_cornerFamilyTopLeft, i4);
            int i6 = typedArrayObtainStyledAttributes.getInt(d.b.a.a.l.ShapeAppearance_cornerFamilyTopRight, i4);
            int i7 = typedArrayObtainStyledAttributes.getInt(d.b.a.a.l.ShapeAppearance_cornerFamilyBottomRight, i4);
            int i8 = typedArrayObtainStyledAttributes.getInt(d.b.a.a.l.ShapeAppearance_cornerFamilyBottomLeft, i4);
            d.b.a.a.x.c cVarM = m(typedArrayObtainStyledAttributes, d.b.a.a.l.ShapeAppearance_cornerSize, cVar);
            d.b.a.a.x.c cVarM2 = m(typedArrayObtainStyledAttributes, d.b.a.a.l.ShapeAppearance_cornerSizeTopLeft, cVarM);
            d.b.a.a.x.c cVarM3 = m(typedArrayObtainStyledAttributes, d.b.a.a.l.ShapeAppearance_cornerSizeTopRight, cVarM);
            d.b.a.a.x.c cVarM4 = m(typedArrayObtainStyledAttributes, d.b.a.a.l.ShapeAppearance_cornerSizeBottomRight, cVarM);
            return new b().B(i5, cVarM2).F(i6, cVarM3).w(i7, cVarM4).s(i8, m(typedArrayObtainStyledAttributes, d.b.a.a.l.ShapeAppearance_cornerSizeBottomLeft, cVarM));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static b e(Context context, AttributeSet attributeSet, int i2, int i3) {
        return f(context, attributeSet, i2, i3, 0);
    }

    public static b f(Context context, AttributeSet attributeSet, int i2, int i3, int i4) {
        return g(context, attributeSet, i2, i3, new d.b.a.a.x.a(i4));
    }

    public static b g(Context context, AttributeSet attributeSet, int i2, int i3, d.b.a.a.x.c cVar) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d.b.a.a.l.MaterialShape, i2, i3);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.MaterialShape_shapeAppearance, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(d.b.a.a.l.MaterialShape_shapeAppearanceOverlay, 0);
        typedArrayObtainStyledAttributes.recycle();
        return d(context, resourceId, resourceId2, cVar);
    }

    private static d.b.a.a.x.c m(TypedArray typedArray, int i2, d.b.a.a.x.c cVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i2);
        if (typedValuePeekValue == null) {
            return cVar;
        }
        int i3 = typedValuePeekValue.type;
        return i3 == 5 ? new d.b.a.a.x.a(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i3 == 6 ? new k(typedValuePeekValue.getFraction(1.0f, 1.0f)) : cVar;
    }

    public f h() {
        return this.l;
    }

    public d i() {
        return this.f4780e;
    }

    public d.b.a.a.x.c j() {
        return this.f4784i;
    }

    public d k() {
        return this.f4779d;
    }

    public d.b.a.a.x.c l() {
        return this.f4783h;
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
        return this.f4777b;
    }

    public d.b.a.a.x.c r() {
        return this.f4781f;
    }

    public d s() {
        return this.f4778c;
    }

    public d.b.a.a.x.c t() {
        return this.f4782g;
    }

    public boolean u(RectF rectF) {
        boolean z = this.m.getClass().equals(f.class) && this.k.getClass().equals(f.class) && this.j.getClass().equals(f.class) && this.l.getClass().equals(f.class);
        float fA = this.f4781f.a(rectF);
        return z && ((this.f4782g.a(rectF) > fA ? 1 : (this.f4782g.a(rectF) == fA ? 0 : -1)) == 0 && (this.f4784i.a(rectF) > fA ? 1 : (this.f4784i.a(rectF) == fA ? 0 : -1)) == 0 && (this.f4783h.a(rectF) > fA ? 1 : (this.f4783h.a(rectF) == fA ? 0 : -1)) == 0) && ((this.f4778c instanceof l) && (this.f4777b instanceof l) && (this.f4779d instanceof l) && (this.f4780e instanceof l));
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
        this.f4777b = bVar.f4785a;
        this.f4778c = bVar.f4786b;
        this.f4779d = bVar.f4787c;
        this.f4780e = bVar.f4788d;
        this.f4781f = bVar.f4789e;
        this.f4782g = bVar.f4790f;
        this.f4783h = bVar.f4791g;
        this.f4784i = bVar.f4792h;
        this.j = bVar.f4793i;
        this.k = bVar.j;
        this.l = bVar.k;
        this.m = bVar.l;
    }

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private d f4785a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private d f4786b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private d f4787c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private d f4788d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private d.b.a.a.x.c f4789e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private d.b.a.a.x.c f4790f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private d.b.a.a.x.c f4791g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private d.b.a.a.x.c f4792h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private f f4793i;
        private f j;
        private f k;
        private f l;

        public b() {
            this.f4785a = i.b();
            this.f4786b = i.b();
            this.f4787c = i.b();
            this.f4788d = i.b();
            this.f4789e = new d.b.a.a.x.a(0.0f);
            this.f4790f = new d.b.a.a.x.a(0.0f);
            this.f4791g = new d.b.a.a.x.a(0.0f);
            this.f4792h = new d.b.a.a.x.a(0.0f);
            this.f4793i = i.c();
            this.j = i.c();
            this.k = i.c();
            this.l = i.c();
        }

        private static float n(d dVar) {
            if (dVar instanceof l) {
                return ((l) dVar).f4775a;
            }
            if (dVar instanceof e) {
                return ((e) dVar).f4750a;
            }
            return -1.0f;
        }

        public b A(f fVar) {
            this.f4793i = fVar;
            return this;
        }

        public b B(int i2, d.b.a.a.x.c cVar) {
            return C(i.a(i2)).E(cVar);
        }

        public b C(d dVar) {
            this.f4785a = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                D(fN);
            }
            return this;
        }

        public b D(float f2) {
            this.f4789e = new d.b.a.a.x.a(f2);
            return this;
        }

        public b E(d.b.a.a.x.c cVar) {
            this.f4789e = cVar;
            return this;
        }

        public b F(int i2, d.b.a.a.x.c cVar) {
            return G(i.a(i2)).I(cVar);
        }

        public b G(d dVar) {
            this.f4786b = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                H(fN);
            }
            return this;
        }

        public b H(float f2) {
            this.f4790f = new d.b.a.a.x.a(f2);
            return this;
        }

        public b I(d.b.a.a.x.c cVar) {
            this.f4790f = cVar;
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

        public b s(int i2, d.b.a.a.x.c cVar) {
            return t(i.a(i2)).v(cVar);
        }

        public b t(d dVar) {
            this.f4788d = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                u(fN);
            }
            return this;
        }

        public b u(float f2) {
            this.f4792h = new d.b.a.a.x.a(f2);
            return this;
        }

        public b v(d.b.a.a.x.c cVar) {
            this.f4792h = cVar;
            return this;
        }

        public b w(int i2, d.b.a.a.x.c cVar) {
            return x(i.a(i2)).z(cVar);
        }

        public b x(d dVar) {
            this.f4787c = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                y(fN);
            }
            return this;
        }

        public b y(float f2) {
            this.f4791g = new d.b.a.a.x.a(f2);
            return this;
        }

        public b z(d.b.a.a.x.c cVar) {
            this.f4791g = cVar;
            return this;
        }

        public b(m mVar) {
            this.f4785a = i.b();
            this.f4786b = i.b();
            this.f4787c = i.b();
            this.f4788d = i.b();
            this.f4789e = new d.b.a.a.x.a(0.0f);
            this.f4790f = new d.b.a.a.x.a(0.0f);
            this.f4791g = new d.b.a.a.x.a(0.0f);
            this.f4792h = new d.b.a.a.x.a(0.0f);
            this.f4793i = i.c();
            this.j = i.c();
            this.k = i.c();
            this.l = i.c();
            this.f4785a = mVar.f4777b;
            this.f4786b = mVar.f4778c;
            this.f4787c = mVar.f4779d;
            this.f4788d = mVar.f4780e;
            this.f4789e = mVar.f4781f;
            this.f4790f = mVar.f4782g;
            this.f4791g = mVar.f4783h;
            this.f4792h = mVar.f4784i;
            this.f4793i = mVar.j;
            this.j = mVar.k;
            this.k = mVar.l;
            this.l = mVar.m;
        }
    }

    public m() {
        this.f4777b = i.b();
        this.f4778c = i.b();
        this.f4779d = i.b();
        this.f4780e = i.b();
        this.f4781f = new d.b.a.a.x.a(0.0f);
        this.f4782g = new d.b.a.a.x.a(0.0f);
        this.f4783h = new d.b.a.a.x.a(0.0f);
        this.f4784i = new d.b.a.a.x.a(0.0f);
        this.j = i.c();
        this.k = i.c();
        this.l = i.c();
        this.m = i.c();
    }
}
