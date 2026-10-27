package e6;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.google.android.material.R$styleable;

/* JADX INFO: compiled from: ShapeAppearanceModel.java */
/* JADX INFO: loaded from: classes2.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final e6.c f4705a = new k(0.5f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public d f4706b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d f4707c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f4708d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d f4709e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public e6.c f4710f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public e6.c f4711g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public e6.c f4712h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public e6.c f4713i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public f f4714j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public f f4715k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public f f4716l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public f f4717m;

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public interface c {
        e6.c a(e6.c cVar);
    }

    public static b a() {
        return new b();
    }

    public static b b(Context context, int i8, int i9) {
        return c(context, i8, i9, 0);
    }

    public static b c(Context context, int i8, int i9, int i10) {
        return d(context, i8, i9, new e6.a(i10));
    }

    public static b d(Context context, int i8, int i9, e6.c cVar) {
        if (i9 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i8);
            i8 = i9;
            context = contextThemeWrapper;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i8, R$styleable.ShapeAppearance);
        try {
            int i10 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamily, 0);
            int i11 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyTopLeft, i10);
            int i12 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyTopRight, i10);
            int i13 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyBottomRight, i10);
            int i14 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyBottomLeft, i10);
            e6.c cVarM = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSize, cVar);
            e6.c cVarM2 = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopLeft, cVarM);
            e6.c cVarM3 = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopRight, cVarM);
            e6.c cVarM4 = m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomRight, cVarM);
            return new b().B(i11, cVarM2).F(i12, cVarM3).w(i13, cVarM4).s(i14, m(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomLeft, cVarM));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static b e(Context context, AttributeSet attributeSet, int i8, int i9) {
        return f(context, attributeSet, i8, i9, 0);
    }

    public static b f(Context context, AttributeSet attributeSet, int i8, int i9, int i10) {
        return g(context, attributeSet, i8, i9, new e6.a(i10));
    }

    public static b g(Context context, AttributeSet attributeSet, int i8, int i9, e6.c cVar) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialShape, i8, i9);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearance, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearanceOverlay, 0);
        typedArrayObtainStyledAttributes.recycle();
        return d(context, resourceId, resourceId2, cVar);
    }

    public static e6.c m(TypedArray typedArray, int i8, e6.c cVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i8);
        if (typedValuePeekValue == null) {
            return cVar;
        }
        int i9 = typedValuePeekValue.type;
        return i9 == 5 ? new e6.a(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i9 == 6 ? new k(typedValuePeekValue.getFraction(1.0f, 1.0f)) : cVar;
    }

    public f h() {
        return this.f4716l;
    }

    public d i() {
        return this.f4709e;
    }

    public e6.c j() {
        return this.f4713i;
    }

    public d k() {
        return this.f4708d;
    }

    public e6.c l() {
        return this.f4712h;
    }

    public f n() {
        return this.f4717m;
    }

    public f o() {
        return this.f4715k;
    }

    public f p() {
        return this.f4714j;
    }

    public d q() {
        return this.f4706b;
    }

    public e6.c r() {
        return this.f4710f;
    }

    public d s() {
        return this.f4707c;
    }

    public e6.c t() {
        return this.f4711g;
    }

    public boolean u(RectF rectF) {
        boolean z7 = this.f4717m.getClass().equals(f.class) && this.f4715k.getClass().equals(f.class) && this.f4714j.getClass().equals(f.class) && this.f4716l.getClass().equals(f.class);
        float fA = this.f4710f.a(rectF);
        return z7 && ((this.f4711g.a(rectF) > fA ? 1 : (this.f4711g.a(rectF) == fA ? 0 : -1)) == 0 && (this.f4713i.a(rectF) > fA ? 1 : (this.f4713i.a(rectF) == fA ? 0 : -1)) == 0 && (this.f4712h.a(rectF) > fA ? 1 : (this.f4712h.a(rectF) == fA ? 0 : -1)) == 0) && ((this.f4707c instanceof l) && (this.f4706b instanceof l) && (this.f4708d instanceof l) && (this.f4709e instanceof l));
    }

    public b v() {
        return new b(this);
    }

    public m w(float f8) {
        return v().o(f8).m();
    }

    public m x(c cVar) {
        return v().E(cVar.a(r())).I(cVar.a(t())).v(cVar.a(j())).z(cVar.a(l())).m();
    }

    public m(b bVar) {
        this.f4706b = bVar.f4718a;
        this.f4707c = bVar.f4719b;
        this.f4708d = bVar.f4720c;
        this.f4709e = bVar.f4721d;
        this.f4710f = bVar.f4722e;
        this.f4711g = bVar.f4723f;
        this.f4712h = bVar.f4724g;
        this.f4713i = bVar.f4725h;
        this.f4714j = bVar.f4726i;
        this.f4715k = bVar.f4727j;
        this.f4716l = bVar.f4728k;
        this.f4717m = bVar.f4729l;
    }

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public d f4718a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public d f4719b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public d f4720c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public d f4721d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public e6.c f4722e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public e6.c f4723f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public e6.c f4724g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public e6.c f4725h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public f f4726i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public f f4727j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public f f4728k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public f f4729l;

        public b() {
            this.f4718a = i.b();
            this.f4719b = i.b();
            this.f4720c = i.b();
            this.f4721d = i.b();
            this.f4722e = new e6.a(0.0f);
            this.f4723f = new e6.a(0.0f);
            this.f4724g = new e6.a(0.0f);
            this.f4725h = new e6.a(0.0f);
            this.f4726i = i.c();
            this.f4727j = i.c();
            this.f4728k = i.c();
            this.f4729l = i.c();
        }

        public static float n(d dVar) {
            if (dVar instanceof l) {
                return ((l) dVar).f4704a;
            }
            if (dVar instanceof e) {
                return ((e) dVar).f4651a;
            }
            return -1.0f;
        }

        public b A(f fVar) {
            this.f4726i = fVar;
            return this;
        }

        public b B(int i8, e6.c cVar) {
            return C(i.a(i8)).E(cVar);
        }

        public b C(d dVar) {
            this.f4718a = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                D(fN);
            }
            return this;
        }

        public b D(float f8) {
            this.f4722e = new e6.a(f8);
            return this;
        }

        public b E(e6.c cVar) {
            this.f4722e = cVar;
            return this;
        }

        public b F(int i8, e6.c cVar) {
            return G(i.a(i8)).I(cVar);
        }

        public b G(d dVar) {
            this.f4719b = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                H(fN);
            }
            return this;
        }

        public b H(float f8) {
            this.f4723f = new e6.a(f8);
            return this;
        }

        public b I(e6.c cVar) {
            this.f4723f = cVar;
            return this;
        }

        public m m() {
            return new m(this);
        }

        public b o(float f8) {
            return D(f8).H(f8).y(f8).u(f8);
        }

        public b p(int i8, float f8) {
            return q(i.a(i8)).o(f8);
        }

        public b q(d dVar) {
            return C(dVar).G(dVar).x(dVar).t(dVar);
        }

        public b r(f fVar) {
            this.f4728k = fVar;
            return this;
        }

        public b s(int i8, e6.c cVar) {
            return t(i.a(i8)).v(cVar);
        }

        public b t(d dVar) {
            this.f4721d = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                u(fN);
            }
            return this;
        }

        public b u(float f8) {
            this.f4725h = new e6.a(f8);
            return this;
        }

        public b v(e6.c cVar) {
            this.f4725h = cVar;
            return this;
        }

        public b w(int i8, e6.c cVar) {
            return x(i.a(i8)).z(cVar);
        }

        public b x(d dVar) {
            this.f4720c = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                y(fN);
            }
            return this;
        }

        public b y(float f8) {
            this.f4724g = new e6.a(f8);
            return this;
        }

        public b z(e6.c cVar) {
            this.f4724g = cVar;
            return this;
        }

        public b(m mVar) {
            this.f4718a = i.b();
            this.f4719b = i.b();
            this.f4720c = i.b();
            this.f4721d = i.b();
            this.f4722e = new e6.a(0.0f);
            this.f4723f = new e6.a(0.0f);
            this.f4724g = new e6.a(0.0f);
            this.f4725h = new e6.a(0.0f);
            this.f4726i = i.c();
            this.f4727j = i.c();
            this.f4728k = i.c();
            this.f4729l = i.c();
            this.f4718a = mVar.f4706b;
            this.f4719b = mVar.f4707c;
            this.f4720c = mVar.f4708d;
            this.f4721d = mVar.f4709e;
            this.f4722e = mVar.f4710f;
            this.f4723f = mVar.f4711g;
            this.f4724g = mVar.f4712h;
            this.f4725h = mVar.f4713i;
            this.f4726i = mVar.f4714j;
            this.f4727j = mVar.f4715k;
            this.f4728k = mVar.f4716l;
            this.f4729l = mVar.f4717m;
        }
    }

    public m() {
        this.f4706b = i.b();
        this.f4707c = i.b();
        this.f4708d = i.b();
        this.f4709e = i.b();
        this.f4710f = new e6.a(0.0f);
        this.f4711g = new e6.a(0.0f);
        this.f4712h = new e6.a(0.0f);
        this.f4713i = new e6.a(0.0f);
        this.f4714j = i.c();
        this.f4715k = i.c();
        this.f4716l = i.c();
        this.f4717m = i.c();
    }
}
