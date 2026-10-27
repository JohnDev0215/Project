package e3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

/* JADX INFO: compiled from: ShapeAppearanceModel.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final e3.c f6227m = new i(0.5f);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    d f6228a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    d f6229b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    d f6230c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    d f6231d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    e3.c f6232e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    e3.c f6233f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    e3.c f6234g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    e3.c f6235h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    f f6236i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    f f6237j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    f f6238k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    f f6239l;

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public interface c {
        e3.c a(e3.c cVar);
    }

    public static b a() {
        return new b();
    }

    public static b b(Context context, int i7, int i8) {
        return c(context, i7, i8, 0);
    }

    private static b c(Context context, int i7, int i8, int i9) {
        return d(context, i7, i8, new e3.a(i9));
    }

    private static b d(Context context, int i7, int i8, e3.c cVar) {
        if (i8 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i7);
            i7 = i8;
            context = contextThemeWrapper;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i7, o2.l.ShapeAppearance);
        try {
            int i9 = typedArrayObtainStyledAttributes.getInt(o2.l.ShapeAppearance_cornerFamily, 0);
            int i10 = typedArrayObtainStyledAttributes.getInt(o2.l.ShapeAppearance_cornerFamilyTopLeft, i9);
            int i11 = typedArrayObtainStyledAttributes.getInt(o2.l.ShapeAppearance_cornerFamilyTopRight, i9);
            int i12 = typedArrayObtainStyledAttributes.getInt(o2.l.ShapeAppearance_cornerFamilyBottomRight, i9);
            int i13 = typedArrayObtainStyledAttributes.getInt(o2.l.ShapeAppearance_cornerFamilyBottomLeft, i9);
            e3.c cVarM = m(typedArrayObtainStyledAttributes, o2.l.ShapeAppearance_cornerSize, cVar);
            e3.c cVarM2 = m(typedArrayObtainStyledAttributes, o2.l.ShapeAppearance_cornerSizeTopLeft, cVarM);
            e3.c cVarM3 = m(typedArrayObtainStyledAttributes, o2.l.ShapeAppearance_cornerSizeTopRight, cVarM);
            e3.c cVarM4 = m(typedArrayObtainStyledAttributes, o2.l.ShapeAppearance_cornerSizeBottomRight, cVarM);
            return new b().y(i10, cVarM2).C(i11, cVarM3).u(i12, cVarM4).q(i13, m(typedArrayObtainStyledAttributes, o2.l.ShapeAppearance_cornerSizeBottomLeft, cVarM));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static b e(Context context, AttributeSet attributeSet, int i7, int i8) {
        return f(context, attributeSet, i7, i8, 0);
    }

    public static b f(Context context, AttributeSet attributeSet, int i7, int i8, int i9) {
        return g(context, attributeSet, i7, i8, new e3.a(i9));
    }

    public static b g(Context context, AttributeSet attributeSet, int i7, int i8, e3.c cVar) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, o2.l.MaterialShape, i7, i8);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(o2.l.MaterialShape_shapeAppearance, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(o2.l.MaterialShape_shapeAppearanceOverlay, 0);
        typedArrayObtainStyledAttributes.recycle();
        return d(context, resourceId, resourceId2, cVar);
    }

    private static e3.c m(TypedArray typedArray, int i7, e3.c cVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i7);
        if (typedValuePeekValue == null) {
            return cVar;
        }
        int i8 = typedValuePeekValue.type;
        return i8 == 5 ? new e3.a(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i8 == 6 ? new i(typedValuePeekValue.getFraction(1.0f, 1.0f)) : cVar;
    }

    public f h() {
        return this.f6238k;
    }

    public d i() {
        return this.f6231d;
    }

    public e3.c j() {
        return this.f6235h;
    }

    public d k() {
        return this.f6230c;
    }

    public e3.c l() {
        return this.f6234g;
    }

    public f n() {
        return this.f6239l;
    }

    public f o() {
        return this.f6237j;
    }

    public f p() {
        return this.f6236i;
    }

    public d q() {
        return this.f6228a;
    }

    public e3.c r() {
        return this.f6232e;
    }

    public d s() {
        return this.f6229b;
    }

    public e3.c t() {
        return this.f6233f;
    }

    public boolean u(RectF rectF) {
        boolean z6 = this.f6239l.getClass().equals(f.class) && this.f6237j.getClass().equals(f.class) && this.f6236i.getClass().equals(f.class) && this.f6238k.getClass().equals(f.class);
        float fA = this.f6232e.a(rectF);
        return z6 && ((this.f6233f.a(rectF) > fA ? 1 : (this.f6233f.a(rectF) == fA ? 0 : -1)) == 0 && (this.f6235h.a(rectF) > fA ? 1 : (this.f6235h.a(rectF) == fA ? 0 : -1)) == 0 && (this.f6234g.a(rectF) > fA ? 1 : (this.f6234g.a(rectF) == fA ? 0 : -1)) == 0) && ((this.f6229b instanceof j) && (this.f6228a instanceof j) && (this.f6230c instanceof j) && (this.f6231d instanceof j));
    }

    public b v() {
        return new b(this);
    }

    public k w(float f7) {
        return v().o(f7).m();
    }

    public k x(e3.c cVar) {
        return v().p(cVar).m();
    }

    public k y(c cVar) {
        return v().B(cVar.a(r())).F(cVar.a(t())).t(cVar.a(j())).x(cVar.a(l())).m();
    }

    private k(b bVar) {
        this.f6228a = bVar.f6240a;
        this.f6229b = bVar.f6241b;
        this.f6230c = bVar.f6242c;
        this.f6231d = bVar.f6243d;
        this.f6232e = bVar.f6244e;
        this.f6233f = bVar.f6245f;
        this.f6234g = bVar.f6246g;
        this.f6235h = bVar.f6247h;
        this.f6236i = bVar.f6248i;
        this.f6237j = bVar.f6249j;
        this.f6238k = bVar.f6250k;
        this.f6239l = bVar.f6251l;
    }

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private d f6240a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private d f6241b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private d f6242c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private d f6243d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private e3.c f6244e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private e3.c f6245f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private e3.c f6246g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private e3.c f6247h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private f f6248i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private f f6249j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private f f6250k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private f f6251l;

        public b() {
            this.f6240a = h.b();
            this.f6241b = h.b();
            this.f6242c = h.b();
            this.f6243d = h.b();
            this.f6244e = new e3.a(0.0f);
            this.f6245f = new e3.a(0.0f);
            this.f6246g = new e3.a(0.0f);
            this.f6247h = new e3.a(0.0f);
            this.f6248i = h.c();
            this.f6249j = h.c();
            this.f6250k = h.c();
            this.f6251l = h.c();
        }

        private static float n(d dVar) {
            if (dVar instanceof j) {
                return ((j) dVar).f6226a;
            }
            if (dVar instanceof e) {
                return ((e) dVar).f6178a;
            }
            return -1.0f;
        }

        public b A(float f7) {
            this.f6244e = new e3.a(f7);
            return this;
        }

        public b B(e3.c cVar) {
            this.f6244e = cVar;
            return this;
        }

        public b C(int i7, e3.c cVar) {
            return D(h.a(i7)).F(cVar);
        }

        public b D(d dVar) {
            this.f6241b = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                E(fN);
            }
            return this;
        }

        public b E(float f7) {
            this.f6245f = new e3.a(f7);
            return this;
        }

        public b F(e3.c cVar) {
            this.f6245f = cVar;
            return this;
        }

        public k m() {
            return new k(this);
        }

        public b o(float f7) {
            return A(f7).E(f7).w(f7).s(f7);
        }

        public b p(e3.c cVar) {
            return B(cVar).F(cVar).x(cVar).t(cVar);
        }

        public b q(int i7, e3.c cVar) {
            return r(h.a(i7)).t(cVar);
        }

        public b r(d dVar) {
            this.f6243d = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                s(fN);
            }
            return this;
        }

        public b s(float f7) {
            this.f6247h = new e3.a(f7);
            return this;
        }

        public b t(e3.c cVar) {
            this.f6247h = cVar;
            return this;
        }

        public b u(int i7, e3.c cVar) {
            return v(h.a(i7)).x(cVar);
        }

        public b v(d dVar) {
            this.f6242c = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                w(fN);
            }
            return this;
        }

        public b w(float f7) {
            this.f6246g = new e3.a(f7);
            return this;
        }

        public b x(e3.c cVar) {
            this.f6246g = cVar;
            return this;
        }

        public b y(int i7, e3.c cVar) {
            return z(h.a(i7)).B(cVar);
        }

        public b z(d dVar) {
            this.f6240a = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                A(fN);
            }
            return this;
        }

        public b(k kVar) {
            this.f6240a = h.b();
            this.f6241b = h.b();
            this.f6242c = h.b();
            this.f6243d = h.b();
            this.f6244e = new e3.a(0.0f);
            this.f6245f = new e3.a(0.0f);
            this.f6246g = new e3.a(0.0f);
            this.f6247h = new e3.a(0.0f);
            this.f6248i = h.c();
            this.f6249j = h.c();
            this.f6250k = h.c();
            this.f6251l = h.c();
            this.f6240a = kVar.f6228a;
            this.f6241b = kVar.f6229b;
            this.f6242c = kVar.f6230c;
            this.f6243d = kVar.f6231d;
            this.f6244e = kVar.f6232e;
            this.f6245f = kVar.f6233f;
            this.f6246g = kVar.f6234g;
            this.f6247h = kVar.f6235h;
            this.f6248i = kVar.f6236i;
            this.f6249j = kVar.f6237j;
            this.f6250k = kVar.f6238k;
            this.f6251l = kVar.f6239l;
        }
    }

    public k() {
        this.f6228a = h.b();
        this.f6229b = h.b();
        this.f6230c = h.b();
        this.f6231d = h.b();
        this.f6232e = new e3.a(0.0f);
        this.f6233f = new e3.a(0.0f);
        this.f6234g = new e3.a(0.0f);
        this.f6235h = new e3.a(0.0f);
        this.f6236i = h.c();
        this.f6237j = h.c();
        this.f6238k = h.c();
        this.f6239l = h.c();
    }
}
