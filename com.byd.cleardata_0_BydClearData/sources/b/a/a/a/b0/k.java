package b.a.a.a.b0;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b.a.a.a.b0.c f1662a = new i(0.5f);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    d f1663b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    d f1664c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    d f1665d;
    d e;
    b.a.a.a.b0.c f;
    b.a.a.a.b0.c g;
    b.a.a.a.b0.c h;
    b.a.a.a.b0.c i;
    f j;
    f k;
    f l;
    f m;

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private d f1666a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private d f1667b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private d f1668c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private d f1669d;
        private b.a.a.a.b0.c e;
        private b.a.a.a.b0.c f;
        private b.a.a.a.b0.c g;
        private b.a.a.a.b0.c h;
        private f i;
        private f j;
        private f k;
        private f l;

        public b() {
            this.f1666a = h.b();
            this.f1667b = h.b();
            this.f1668c = h.b();
            this.f1669d = h.b();
            this.e = new b.a.a.a.b0.a(0.0f);
            this.f = new b.a.a.a.b0.a(0.0f);
            this.g = new b.a.a.a.b0.a(0.0f);
            this.h = new b.a.a.a.b0.a(0.0f);
            this.i = h.c();
            this.j = h.c();
            this.k = h.c();
            this.l = h.c();
        }

        public b(k kVar) {
            this.f1666a = h.b();
            this.f1667b = h.b();
            this.f1668c = h.b();
            this.f1669d = h.b();
            this.e = new b.a.a.a.b0.a(0.0f);
            this.f = new b.a.a.a.b0.a(0.0f);
            this.g = new b.a.a.a.b0.a(0.0f);
            this.h = new b.a.a.a.b0.a(0.0f);
            this.i = h.c();
            this.j = h.c();
            this.k = h.c();
            this.l = h.c();
            this.f1666a = kVar.f1663b;
            this.f1667b = kVar.f1664c;
            this.f1668c = kVar.f1665d;
            this.f1669d = kVar.e;
            this.e = kVar.f;
            this.f = kVar.g;
            this.g = kVar.h;
            this.h = kVar.i;
            this.i = kVar.j;
            this.j = kVar.k;
            this.k = kVar.l;
            this.l = kVar.m;
        }

        private static float n(d dVar) {
            if (dVar instanceof j) {
                return ((j) dVar).f1661a;
            }
            if (dVar instanceof e) {
                return ((e) dVar).f1649a;
            }
            return -1.0f;
        }

        public b A(b.a.a.a.b0.c cVar) {
            this.e = cVar;
            return this;
        }

        public b B(int i, b.a.a.a.b0.c cVar) {
            return C(h.a(i)).E(cVar);
        }

        public b C(d dVar) {
            this.f1667b = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                D(fN);
            }
            return this;
        }

        public b D(float f) {
            this.f = new b.a.a.a.b0.a(f);
            return this;
        }

        public b E(b.a.a.a.b0.c cVar) {
            this.f = cVar;
            return this;
        }

        public k m() {
            return new k(this);
        }

        public b o(float f) {
            return z(f).D(f).v(f).r(f);
        }

        public b p(int i, b.a.a.a.b0.c cVar) {
            return q(h.a(i)).s(cVar);
        }

        public b q(d dVar) {
            this.f1669d = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                r(fN);
            }
            return this;
        }

        public b r(float f) {
            this.h = new b.a.a.a.b0.a(f);
            return this;
        }

        public b s(b.a.a.a.b0.c cVar) {
            this.h = cVar;
            return this;
        }

        public b t(int i, b.a.a.a.b0.c cVar) {
            return u(h.a(i)).w(cVar);
        }

        public b u(d dVar) {
            this.f1668c = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                v(fN);
            }
            return this;
        }

        public b v(float f) {
            this.g = new b.a.a.a.b0.a(f);
            return this;
        }

        public b w(b.a.a.a.b0.c cVar) {
            this.g = cVar;
            return this;
        }

        public b x(int i, b.a.a.a.b0.c cVar) {
            return y(h.a(i)).A(cVar);
        }

        public b y(d dVar) {
            this.f1666a = dVar;
            float fN = n(dVar);
            if (fN != -1.0f) {
                z(fN);
            }
            return this;
        }

        public b z(float f) {
            this.e = new b.a.a.a.b0.a(f);
            return this;
        }
    }

    public interface c {
        b.a.a.a.b0.c a(b.a.a.a.b0.c cVar);
    }

    public k() {
        this.f1663b = h.b();
        this.f1664c = h.b();
        this.f1665d = h.b();
        this.e = h.b();
        this.f = new b.a.a.a.b0.a(0.0f);
        this.g = new b.a.a.a.b0.a(0.0f);
        this.h = new b.a.a.a.b0.a(0.0f);
        this.i = new b.a.a.a.b0.a(0.0f);
        this.j = h.c();
        this.k = h.c();
        this.l = h.c();
        this.m = h.c();
    }

    private k(b bVar) {
        this.f1663b = bVar.f1666a;
        this.f1664c = bVar.f1667b;
        this.f1665d = bVar.f1668c;
        this.e = bVar.f1669d;
        this.f = bVar.e;
        this.g = bVar.f;
        this.h = bVar.g;
        this.i = bVar.h;
        this.j = bVar.i;
        this.k = bVar.j;
        this.l = bVar.k;
        this.m = bVar.l;
    }

    public static b a() {
        return new b();
    }

    public static b b(Context context, int i, int i2) {
        return c(context, i, i2, 0);
    }

    private static b c(Context context, int i, int i2, int i3) {
        return d(context, i, i2, new b.a.a.a.b0.a(i3));
    }

    private static b d(Context context, int i, int i2, b.a.a.a.b0.c cVar) {
        if (i2 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
            i = i2;
            context = contextThemeWrapper;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, b.a.a.a.l.Q2);
        try {
            int i3 = typedArrayObtainStyledAttributes.getInt(b.a.a.a.l.R2, 0);
            int i4 = typedArrayObtainStyledAttributes.getInt(b.a.a.a.l.U2, i3);
            int i5 = typedArrayObtainStyledAttributes.getInt(b.a.a.a.l.V2, i3);
            int i6 = typedArrayObtainStyledAttributes.getInt(b.a.a.a.l.T2, i3);
            int i7 = typedArrayObtainStyledAttributes.getInt(b.a.a.a.l.S2, i3);
            b.a.a.a.b0.c cVarM = m(typedArrayObtainStyledAttributes, b.a.a.a.l.W2, cVar);
            b.a.a.a.b0.c cVarM2 = m(typedArrayObtainStyledAttributes, b.a.a.a.l.Z2, cVarM);
            b.a.a.a.b0.c cVarM3 = m(typedArrayObtainStyledAttributes, b.a.a.a.l.a3, cVarM);
            b.a.a.a.b0.c cVarM4 = m(typedArrayObtainStyledAttributes, b.a.a.a.l.Y2, cVarM);
            return new b().x(i4, cVarM2).B(i5, cVarM3).t(i6, cVarM4).p(i7, m(typedArrayObtainStyledAttributes, b.a.a.a.l.X2, cVarM));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static b e(Context context, AttributeSet attributeSet, int i, int i2) {
        return f(context, attributeSet, i, i2, 0);
    }

    public static b f(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        return g(context, attributeSet, i, i2, new b.a.a.a.b0.a(i3));
    }

    public static b g(Context context, AttributeSet attributeSet, int i, int i2, b.a.a.a.b0.c cVar) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.a.a.a.l.t2, i, i2);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(b.a.a.a.l.u2, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(b.a.a.a.l.v2, 0);
        typedArrayObtainStyledAttributes.recycle();
        return d(context, resourceId, resourceId2, cVar);
    }

    private static b.a.a.a.b0.c m(TypedArray typedArray, int i, b.a.a.a.b0.c cVar) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i);
        if (typedValuePeekValue == null) {
            return cVar;
        }
        int i2 = typedValuePeekValue.type;
        return i2 == 5 ? new b.a.a.a.b0.a(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i2 == 6 ? new i(typedValuePeekValue.getFraction(1.0f, 1.0f)) : cVar;
    }

    public f h() {
        return this.l;
    }

    public d i() {
        return this.e;
    }

    public b.a.a.a.b0.c j() {
        return this.i;
    }

    public d k() {
        return this.f1665d;
    }

    public b.a.a.a.b0.c l() {
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
        return this.f1663b;
    }

    public b.a.a.a.b0.c r() {
        return this.f;
    }

    public d s() {
        return this.f1664c;
    }

    public b.a.a.a.b0.c t() {
        return this.g;
    }

    public boolean u(RectF rectF) {
        boolean z = this.m.getClass().equals(f.class) && this.k.getClass().equals(f.class) && this.j.getClass().equals(f.class) && this.l.getClass().equals(f.class);
        float fA = this.f.a(rectF);
        return z && ((this.g.a(rectF) > fA ? 1 : (this.g.a(rectF) == fA ? 0 : -1)) == 0 && (this.i.a(rectF) > fA ? 1 : (this.i.a(rectF) == fA ? 0 : -1)) == 0 && (this.h.a(rectF) > fA ? 1 : (this.h.a(rectF) == fA ? 0 : -1)) == 0) && ((this.f1664c instanceof j) && (this.f1663b instanceof j) && (this.f1665d instanceof j) && (this.e instanceof j));
    }

    public b v() {
        return new b(this);
    }

    public k w(float f) {
        return v().o(f).m();
    }

    public k x(c cVar) {
        return v().A(cVar.a(r())).E(cVar.a(t())).s(cVar.a(j())).w(cVar.a(l())).m();
    }
}
