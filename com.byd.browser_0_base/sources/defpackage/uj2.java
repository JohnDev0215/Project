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
public final class uj2 {
    public static final ma2 m = new ma2(0.5f);
    public final d70 a;
    public final d70 b;
    public final d70 c;
    public final d70 d;
    public final l00 e;
    public final l00 f;
    public final l00 g;
    public final l00 h;
    public final if0 i;
    public final if0 j;
    public final if0 k;
    public final if0 l;

    public uj2(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
    }

    public static a a(Context context, int i, int i2, l00 l00Var) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
        if (i2 != 0) {
            contextThemeWrapper = new ContextThemeWrapper(contextThemeWrapper, i2);
        }
        TypedArray typedArrayObtainStyledAttributes = contextThemeWrapper.obtainStyledAttributes(R$styleable.ShapeAppearance);
        try {
            int i3 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamily, 0);
            int i4 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyTopLeft, i3);
            int i5 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyTopRight, i3);
            int i6 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyBottomRight, i3);
            int i7 = typedArrayObtainStyledAttributes.getInt(R$styleable.ShapeAppearance_cornerFamilyBottomLeft, i3);
            l00 l00VarD = d(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSize, l00Var);
            l00 l00VarD2 = d(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopLeft, l00VarD);
            l00 l00VarD3 = d(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeTopRight, l00VarD);
            l00 l00VarD4 = d(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomRight, l00VarD);
            l00 l00VarD5 = d(typedArrayObtainStyledAttributes, R$styleable.ShapeAppearance_cornerSizeBottomLeft, l00VarD);
            a aVar = new a();
            d70 d70VarF = oj2.f(i4);
            aVar.a = d70VarF;
            float fB = a.b(d70VarF);
            if (fB != -1.0f) {
                aVar.f(fB);
            }
            aVar.e = l00VarD2;
            d70 d70VarF2 = oj2.f(i5);
            aVar.b = d70VarF2;
            float fB2 = a.b(d70VarF2);
            if (fB2 != -1.0f) {
                aVar.g(fB2);
            }
            aVar.f = l00VarD3;
            d70 d70VarF3 = oj2.f(i6);
            aVar.c = d70VarF3;
            float fB3 = a.b(d70VarF3);
            if (fB3 != -1.0f) {
                aVar.e(fB3);
            }
            aVar.g = l00VarD4;
            d70 d70VarF4 = oj2.f(i7);
            aVar.d = d70VarF4;
            float fB4 = a.b(d70VarF4);
            if (fB4 != -1.0f) {
                aVar.d(fB4);
            }
            aVar.h = l00VarD5;
            return aVar;
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static a b(Context context, AttributeSet attributeSet, int i, int i2) {
        return c(context, attributeSet, i, i2, new C0195l(0));
    }

    public static a c(Context context, AttributeSet attributeSet, int i, int i2, l00 l00Var) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialShape, i, i2);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearance, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialShape_shapeAppearanceOverlay, 0);
        typedArrayObtainStyledAttributes.recycle();
        return a(context, resourceId, resourceId2, l00Var);
    }

    public static l00 d(TypedArray typedArray, int i, l00 l00Var) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i);
        if (typedValuePeekValue == null) {
            return l00Var;
        }
        int i2 = typedValuePeekValue.type;
        return i2 == 5 ? new C0195l(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i2 == 6 ? new ma2(typedValuePeekValue.getFraction(1.0f, 1.0f)) : l00Var;
    }

    public final boolean e(RectF rectF) {
        boolean z = this.l.getClass().equals(if0.class) && this.j.getClass().equals(if0.class) && this.i.getClass().equals(if0.class) && this.k.getClass().equals(if0.class);
        float fA = this.e.a(rectF);
        return z && ((this.f.a(rectF) > fA ? 1 : (this.f.a(rectF) == fA ? 0 : -1)) == 0 && (this.h.a(rectF) > fA ? 1 : (this.h.a(rectF) == fA ? 0 : -1)) == 0 && (this.g.a(rectF) > fA ? 1 : (this.g.a(rectF) == fA ? 0 : -1)) == 0) && ((this.b instanceof tc2) && (this.a instanceof tc2) && (this.c instanceof tc2) && (this.d instanceof tc2));
    }

    public final uj2 f(float f) {
        a aVar = new a(this);
        aVar.c(f);
        return new uj2(aVar);
    }

    /* JADX INFO: compiled from: ShapeAppearanceModel.java */
    public static final class a {
        public d70 a;
        public d70 b;
        public d70 c;
        public d70 d;
        public l00 e;
        public l00 f;
        public l00 g;
        public l00 h;
        public if0 i;
        public final if0 j;
        public if0 k;
        public final if0 l;

        public a() {
            this.a = new tc2();
            this.b = new tc2();
            this.c = new tc2();
            this.d = new tc2();
            this.e = new C0195l(0.0f);
            this.f = new C0195l(0.0f);
            this.g = new C0195l(0.0f);
            this.h = new C0195l(0.0f);
            this.i = new if0();
            this.j = new if0();
            this.k = new if0();
            this.l = new if0();
        }

        public static float b(d70 d70Var) {
            if (d70Var instanceof tc2) {
                return ((tc2) d70Var).c;
            }
            if (d70Var instanceof q50) {
                return ((q50) d70Var).c;
            }
            return -1.0f;
        }

        public final uj2 a() {
            return new uj2(this);
        }

        public final void c(float f) {
            f(f);
            g(f);
            e(f);
            d(f);
        }

        public final void d(float f) {
            this.h = new C0195l(f);
        }

        public final void e(float f) {
            this.g = new C0195l(f);
        }

        public final void f(float f) {
            this.e = new C0195l(f);
        }

        public final void g(float f) {
            this.f = new C0195l(f);
        }

        public a(uj2 uj2Var) {
            this.a = new tc2();
            this.b = new tc2();
            this.c = new tc2();
            this.d = new tc2();
            this.e = new C0195l(0.0f);
            this.f = new C0195l(0.0f);
            this.g = new C0195l(0.0f);
            this.h = new C0195l(0.0f);
            this.i = new if0();
            this.j = new if0();
            this.k = new if0();
            this.l = new if0();
            this.a = uj2Var.a;
            this.b = uj2Var.b;
            this.c = uj2Var.c;
            this.d = uj2Var.d;
            this.e = uj2Var.e;
            this.f = uj2Var.f;
            this.g = uj2Var.g;
            this.h = uj2Var.h;
            this.i = uj2Var.i;
            this.j = uj2Var.j;
            this.k = uj2Var.k;
            this.l = uj2Var.l;
        }
    }

    public uj2() {
        this.a = new tc2();
        this.b = new tc2();
        this.c = new tc2();
        this.d = new tc2();
        this.e = new C0195l(0.0f);
        this.f = new C0195l(0.0f);
        this.g = new C0195l(0.0f);
        this.h = new C0195l(0.0f);
        this.i = new if0();
        this.j = new if0();
        this.k = new if0();
        this.l = new if0();
    }
}
