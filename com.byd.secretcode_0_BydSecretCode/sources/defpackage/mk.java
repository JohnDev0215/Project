package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.google.android.material.R$styleable;

/* JADX INFO: compiled from: ShapeAppearanceModel.java */
/* JADX INFO: loaded from: classes.dex */
public class mk {
    public static final ck a = new kk(0.5f);
    public dk b;
    public dk c;
    public dk d;
    public dk e;
    public ck f;
    public ck g;
    public ck h;
    public ck i;
    public fk j;
    public fk k;
    public fk l;
    public fk m;

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public interface c {
        ck a(ck ckVar);
    }

    public static b a() {
        return new b();
    }

    public static b b(Context context, int i, int i2) {
        return c(context, i, i2, 0);
    }

    public static b c(Context context, int i, int i2, int i3) {
        return d(context, i, i2, new ak(i3));
    }

    public static b d(Context context, int i, int i2, ck ckVar) {
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
            ck ckVarM = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSize, ckVar);
            ck ckVarM2 = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopLeft, ckVarM);
            ck ckVarM3 = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopRight, ckVarM);
            ck ckVarM4 = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomRight, ckVarM);
            return new b().B(i4, ckVarM2).F(i5, ckVarM3).w(i6, ckVarM4).s(i7, m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomLeft, ckVarM));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static b e(Context context, AttributeSet attributeSet, int i, int i2) {
        return f(context, attributeSet, i, i2, 0);
    }

    public static b f(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        return g(context, attributeSet, i, i2, new ak(i3));
    }

    public static b g(Context context, AttributeSet attributeSet, int i, int i2, ck ckVar) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialShape, i, i2);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearance, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearanceOverlay, 0);
        typedArrayObtainStyledAttributes.recycle();
        return d(context, resourceId, resourceId2, ckVar);
    }

    public static ck m(TypedArray typedArray, int i, ck ckVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i);
        if (typedValuePeekValue == null) {
            return ckVar;
        }
        int i2 = typedValuePeekValue.type;
        return i2 == 5 ? new ak(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i2 == 6 ? new kk(typedValuePeekValue.getFraction(1.0f, 1.0f)) : ckVar;
    }

    public fk h() {
        return this.l;
    }

    public dk i() {
        return this.e;
    }

    public ck j() {
        return this.i;
    }

    public dk k() {
        return this.d;
    }

    public ck l() {
        return this.h;
    }

    public fk n() {
        return this.m;
    }

    public fk o() {
        return this.k;
    }

    public fk p() {
        return this.j;
    }

    public dk q() {
        return this.b;
    }

    public ck r() {
        return this.f;
    }

    public dk s() {
        return this.c;
    }

    public ck t() {
        return this.g;
    }

    public boolean u(RectF rectF) {
        boolean z = this.m.getClass().equals(fk.class) && this.k.getClass().equals(fk.class) && this.j.getClass().equals(fk.class) && this.l.getClass().equals(fk.class);
        float fA = this.f.a(rectF);
        return z && ((this.g.a(rectF) > fA ? 1 : (this.g.a(rectF) == fA ? 0 : -1)) == 0 && (this.i.a(rectF) > fA ? 1 : (this.i.a(rectF) == fA ? 0 : -1)) == 0 && (this.h.a(rectF) > fA ? 1 : (this.h.a(rectF) == fA ? 0 : -1)) == 0) && ((this.c instanceof lk) && (this.b instanceof lk) && (this.d instanceof lk) && (this.e instanceof lk));
    }

    public b v() {
        return new b(this);
    }

    public mk w(float f) {
        return v().o(f).m();
    }

    public mk x(c cVar) {
        return v().E(cVar.a(r())).I(cVar.a(t())).v(cVar.a(j())).z(cVar.a(l())).m();
    }

    public mk(b bVar) {
        this.b = bVar.a;
        this.c = bVar.b;
        this.d = bVar.c;
        this.e = bVar.d;
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
        public dk a;
        public dk b;
        public dk c;
        public dk d;
        public ck e;
        public ck f;
        public ck g;
        public ck h;
        public fk i;
        public fk j;
        public fk k;
        public fk l;

        public b() {
            this.a = ik.b();
            this.b = ik.b();
            this.c = ik.b();
            this.d = ik.b();
            this.e = new ak(0.0f);
            this.f = new ak(0.0f);
            this.g = new ak(0.0f);
            this.h = new ak(0.0f);
            this.i = ik.c();
            this.j = ik.c();
            this.k = ik.c();
            this.l = ik.c();
        }

        public static float n(dk dkVar) {
            if (dkVar instanceof lk) {
                return ((lk) dkVar).a;
            }
            if (dkVar instanceof ek) {
                return ((ek) dkVar).a;
            }
            return -1.0f;
        }

        public b A(fk fkVar) {
            this.i = fkVar;
            return this;
        }

        public b B(int i, ck ckVar) {
            return C(ik.a(i)).E(ckVar);
        }

        public b C(dk dkVar) {
            this.a = dkVar;
            float fN = n(dkVar);
            if (fN != -1.0f) {
                D(fN);
            }
            return this;
        }

        public b D(float f) {
            this.e = new ak(f);
            return this;
        }

        public b E(ck ckVar) {
            this.e = ckVar;
            return this;
        }

        public b F(int i, ck ckVar) {
            return G(ik.a(i)).I(ckVar);
        }

        public b G(dk dkVar) {
            this.b = dkVar;
            float fN = n(dkVar);
            if (fN != -1.0f) {
                H(fN);
            }
            return this;
        }

        public b H(float f) {
            this.f = new ak(f);
            return this;
        }

        public b I(ck ckVar) {
            this.f = ckVar;
            return this;
        }

        public mk m() {
            return new mk(this);
        }

        public b o(float f) {
            return D(f).H(f).y(f).u(f);
        }

        public b p(int i, float f) {
            return q(ik.a(i)).o(f);
        }

        public b q(dk dkVar) {
            return C(dkVar).G(dkVar).x(dkVar).t(dkVar);
        }

        public b r(fk fkVar) {
            this.k = fkVar;
            return this;
        }

        public b s(int i, ck ckVar) {
            return t(ik.a(i)).v(ckVar);
        }

        public b t(dk dkVar) {
            this.d = dkVar;
            float fN = n(dkVar);
            if (fN != -1.0f) {
                u(fN);
            }
            return this;
        }

        public b u(float f) {
            this.h = new ak(f);
            return this;
        }

        public b v(ck ckVar) {
            this.h = ckVar;
            return this;
        }

        public b w(int i, ck ckVar) {
            return x(ik.a(i)).z(ckVar);
        }

        public b x(dk dkVar) {
            this.c = dkVar;
            float fN = n(dkVar);
            if (fN != -1.0f) {
                y(fN);
            }
            return this;
        }

        public b y(float f) {
            this.g = new ak(f);
            return this;
        }

        public b z(ck ckVar) {
            this.g = ckVar;
            return this;
        }

        public b(mk mkVar) {
            this.a = ik.b();
            this.b = ik.b();
            this.c = ik.b();
            this.d = ik.b();
            this.e = new ak(0.0f);
            this.f = new ak(0.0f);
            this.g = new ak(0.0f);
            this.h = new ak(0.0f);
            this.i = ik.c();
            this.j = ik.c();
            this.k = ik.c();
            this.l = ik.c();
            this.a = mkVar.b;
            this.b = mkVar.c;
            this.c = mkVar.d;
            this.d = mkVar.e;
            this.e = mkVar.f;
            this.f = mkVar.g;
            this.g = mkVar.h;
            this.h = mkVar.i;
            this.i = mkVar.j;
            this.j = mkVar.k;
            this.k = mkVar.l;
            this.l = mkVar.m;
        }
    }

    public mk() {
        this.b = ik.b();
        this.c = ik.b();
        this.d = ik.b();
        this.e = ik.b();
        this.f = new ak(0.0f);
        this.g = new ak(0.0f);
        this.h = new ak(0.0f);
        this.i = new ak(0.0f);
        this.j = ik.c();
        this.k = ik.c();
        this.l = ik.c();
        this.m = ik.c();
    }
}
