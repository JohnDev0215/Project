package n2;

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
    public static final n2.c f4152a = new k(0.5f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f4153b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f4154c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f4155d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d f4156e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public n2.c f4157f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public n2.c f4158g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public n2.c f4159h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public n2.c f4160i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public f f4161j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public f f4162k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public f f4163l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public f f4164m;

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public interface c {
        n2.c a(n2.c cVar);
    }

    public static b a() {
        return new b();
    }

    public static b b(Context context, int i3, int i4) {
        return c(context, i3, i4, 0);
    }

    public static b c(Context context, int i3, int i4, int i5) {
        return d(context, i3, i4, new n2.a(i5));
    }

    public static b d(Context context, int i3, int i4, n2.c cVar) {
        if (i4 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i3);
            i3 = i4;
            context = contextThemeWrapper;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i3, R$styleable.ShapeAppearance);
        try {
            int i5 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamily, 0);
            int i6 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyTopLeft, i5);
            int i7 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyTopRight, i5);
            int i8 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyBottomRight, i5);
            int i9 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyBottomLeft, i5);
            n2.c cVarM = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSize, cVar);
            n2.c cVarM2 = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopLeft, cVarM);
            n2.c cVarM3 = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopRight, cVarM);
            n2.c cVarM4 = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomRight, cVarM);
            return new b().B(i6, cVarM2).F(i7, cVarM3).w(i8, cVarM4).s(i9, m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomLeft, cVarM));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static b e(Context context, AttributeSet attributeSet, int i3, int i4) {
        return f(context, attributeSet, i3, i4, 0);
    }

    public static b f(Context context, AttributeSet attributeSet, int i3, int i4, int i5) {
        return g(context, attributeSet, i3, i4, new n2.a(i5));
    }

    public static b g(Context context, AttributeSet attributeSet, int i3, int i4, n2.c cVar) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialShape, i3, i4);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearance, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearanceOverlay, 0);
        typedArrayObtainStyledAttributes.recycle();
        return d(context, resourceId, resourceId2, cVar);
    }

    public static n2.c m(TypedArray typedArray, int i3, n2.c cVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i3);
        if (typedValuePeekValue == null) {
            return cVar;
        }
        int i4 = typedValuePeekValue.type;
        return i4 == 5 ? new n2.a(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i4 == 6 ? new k(typedValuePeekValue.getFraction(1.0f, 1.0f)) : cVar;
    }

    public f h() {
        return this.f4163l;
    }

    public d i() {
        return this.f4156e;
    }

    public n2.c j() {
        return this.f4160i;
    }

    public d k() {
        return this.f4155d;
    }

    public n2.c l() {
        return this.f4159h;
    }

    public f n() {
        return this.f4164m;
    }

    public f o() {
        return this.f4162k;
    }

    public f p() {
        return this.f4161j;
    }

    public d q() {
        return this.f4153b;
    }

    public n2.c r() {
        return this.f4157f;
    }

    public d s() {
        return this.f4154c;
    }

    public n2.c t() {
        return this.f4158g;
    }

    public boolean u(RectF rectF) {
        boolean z3 = this.f4164m.getClass().equals(f.class) && this.f4162k.getClass().equals(f.class) && this.f4161j.getClass().equals(f.class) && this.f4163l.getClass().equals(f.class);
        float fA = this.f4157f.a(rectF);
        return z3 && ((this.f4158g.a(rectF) > fA ? 1 : (this.f4158g.a(rectF) == fA ? 0 : -1)) == 0 && (this.f4160i.a(rectF) > fA ? 1 : (this.f4160i.a(rectF) == fA ? 0 : -1)) == 0 && (this.f4159h.a(rectF) > fA ? 1 : (this.f4159h.a(rectF) == fA ? 0 : -1)) == 0) && ((this.f4154c instanceof l) && (this.f4153b instanceof l) && (this.f4155d instanceof l) && (this.f4156e instanceof l));
    }

    public b v() {
        return new b(this);
    }

    public m w(float f3) {
        return v().o(f3).m();
    }

    public m x(c cVar) {
        return v().E(cVar.a(r())).I(cVar.a(t())).v(cVar.a(j())).z(cVar.a(l())).m();
    }

    public m(b bVar) {
        this.f4153b = bVar.f4165a;
        this.f4154c = bVar.f4166b;
        this.f4155d = bVar.f4167c;
        this.f4156e = bVar.f4168d;
        this.f4157f = bVar.f4169e;
        this.f4158g = bVar.f4170f;
        this.f4159h = bVar.f4171g;
        this.f4160i = bVar.f4172h;
        this.f4161j = bVar.f4173i;
        this.f4162k = bVar.f4174j;
        this.f4163l = bVar.f4175k;
        this.f4164m = bVar.f4176l;
    }

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f4165a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public d f4166b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public d f4167c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public d f4168d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public n2.c f4169e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public n2.c f4170f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public n2.c f4171g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public n2.c f4172h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public f f4173i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public f f4174j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public f f4175k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public f f4176l;

        public b() {
            this.f4165a = i.b();
            this.f4166b = i.b();
            this.f4167c = i.b();
            this.f4168d = i.b();
            this.f4169e = new n2.a(0.0f);
            this.f4170f = new n2.a(0.0f);
            this.f4171g = new n2.a(0.0f);
            this.f4172h = new n2.a(0.0f);
            this.f4173i = i.c();
            this.f4174j = i.c();
            this.f4175k = i.c();
            this.f4176l = i.c();
        }

        public static float n(d dVar) {
            if (dVar instanceof l) {
                return ((l) dVar).f4151a;
            }
            if (dVar instanceof e) {
                return ((e) dVar).f4097a;
            }
            return -1.0f;
        }

        public b A(f fVar) {
            this.f4173i = fVar;
            return this;
        }

        public b B(int i3, n2.c cVar) {
            return C(i.a(i3)).E(cVar);
        }

        public b C(d dVar) {
            this.f4165a = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                D(fN);
            }
            return this;
        }

        public b D(float f3) {
            this.f4169e = new n2.a(f3);
            return this;
        }

        public b E(n2.c cVar) {
            this.f4169e = cVar;
            return this;
        }

        public b F(int i3, n2.c cVar) {
            return G(i.a(i3)).I(cVar);
        }

        public b G(d dVar) {
            this.f4166b = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                H(fN);
            }
            return this;
        }

        public b H(float f3) {
            this.f4170f = new n2.a(f3);
            return this;
        }

        public b I(n2.c cVar) {
            this.f4170f = cVar;
            return this;
        }

        public m m() {
            return new m(this);
        }

        public b o(float f3) {
            return D(f3).H(f3).y(f3).u(f3);
        }

        public b p(int i3, float f3) {
            return q(i.a(i3)).o(f3);
        }

        public b q(d dVar) {
            return C(dVar).G(dVar).x(dVar).t(dVar);
        }

        public b r(f fVar) {
            this.f4175k = fVar;
            return this;
        }

        public b s(int i3, n2.c cVar) {
            return t(i.a(i3)).v(cVar);
        }

        public b t(d dVar) {
            this.f4168d = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                u(fN);
            }
            return this;
        }

        public b u(float f3) {
            this.f4172h = new n2.a(f3);
            return this;
        }

        public b v(n2.c cVar) {
            this.f4172h = cVar;
            return this;
        }

        public b w(int i3, n2.c cVar) {
            return x(i.a(i3)).z(cVar);
        }

        public b x(d dVar) {
            this.f4167c = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                y(fN);
            }
            return this;
        }

        public b y(float f3) {
            this.f4171g = new n2.a(f3);
            return this;
        }

        public b z(n2.c cVar) {
            this.f4171g = cVar;
            return this;
        }

        public b(m mVar) {
            this.f4165a = i.b();
            this.f4166b = i.b();
            this.f4167c = i.b();
            this.f4168d = i.b();
            this.f4169e = new n2.a(0.0f);
            this.f4170f = new n2.a(0.0f);
            this.f4171g = new n2.a(0.0f);
            this.f4172h = new n2.a(0.0f);
            this.f4173i = i.c();
            this.f4174j = i.c();
            this.f4175k = i.c();
            this.f4176l = i.c();
            this.f4165a = mVar.f4153b;
            this.f4166b = mVar.f4154c;
            this.f4167c = mVar.f4155d;
            this.f4168d = mVar.f4156e;
            this.f4169e = mVar.f4157f;
            this.f4170f = mVar.f4158g;
            this.f4171g = mVar.f4159h;
            this.f4172h = mVar.f4160i;
            this.f4173i = mVar.f4161j;
            this.f4174j = mVar.f4162k;
            this.f4175k = mVar.f4163l;
            this.f4176l = mVar.f4164m;
        }
    }

    public m() {
        this.f4153b = i.b();
        this.f4154c = i.b();
        this.f4155d = i.b();
        this.f4156e = i.b();
        this.f4157f = new n2.a(0.0f);
        this.f4158g = new n2.a(0.0f);
        this.f4159h = new n2.a(0.0f);
        this.f4160i = new n2.a(0.0f);
        this.f4161j = i.c();
        this.f4162k = i.c();
        this.f4163l = i.c();
        this.f4164m = i.c();
    }
}
