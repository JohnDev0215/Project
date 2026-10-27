package p;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;

/* JADX INFO: loaded from: classes.dex */
public final class oc2 {
    public l13 a = new q52();
    public l13 b = new q52();
    public l13 c = new q52();
    public l13 d = new q52();
    public v10 e = new y0(0.0f);
    public v10 f = new y0(0.0f);
    public v10 g = new y0(0.0f);
    public v10 h = new y0(0.0f);
    public zb0 i = new zb0();
    public zb0 j = new zb0();
    public zb0 k = new zb0();
    public zb0 l = new zb0();

    public static wg a(Context context, int i, int i2, y0 y0Var) {
        if (i2 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
            i = i2;
            context = contextThemeWrapper;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, xz1.w);
        try {
            int i3 = typedArrayObtainStyledAttributes.getInt(0, 0);
            int i4 = typedArrayObtainStyledAttributes.getInt(3, i3);
            int i5 = typedArrayObtainStyledAttributes.getInt(4, i3);
            int i6 = typedArrayObtainStyledAttributes.getInt(2, i3);
            int i7 = typedArrayObtainStyledAttributes.getInt(1, i3);
            v10 v10VarC = c(typedArrayObtainStyledAttributes, 5, y0Var);
            v10 v10VarC2 = c(typedArrayObtainStyledAttributes, 8, v10VarC);
            v10 v10VarC3 = c(typedArrayObtainStyledAttributes, 9, v10VarC);
            v10 v10VarC4 = c(typedArrayObtainStyledAttributes, 7, v10VarC);
            v10 v10VarC5 = c(typedArrayObtainStyledAttributes, 6, v10VarC);
            wg wgVar = new wg(1);
            l13 l13VarM = f60.m(i4);
            wgVar.a = l13VarM;
            wg.b(l13VarM);
            wgVar.e = v10VarC2;
            l13 l13VarM2 = f60.m(i5);
            wgVar.b = l13VarM2;
            wg.b(l13VarM2);
            wgVar.f = v10VarC3;
            l13 l13VarM3 = f60.m(i6);
            wgVar.c = l13VarM3;
            wg.b(l13VarM3);
            wgVar.g = v10VarC4;
            l13 l13VarM4 = f60.m(i7);
            wgVar.d = l13VarM4;
            wg.b(l13VarM4);
            wgVar.h = v10VarC5;
            return wgVar;
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static wg b(Context context, AttributeSet attributeSet, int i, int i2) {
        y0 y0Var = new y0(0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, xz1.q, i, i2);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        return a(context, resourceId, resourceId2, y0Var);
    }

    public static v10 c(TypedArray typedArray, int i, v10 v10Var) {
        TypedValue typedValuePeekValue = typedArray.peekValue(i);
        if (typedValuePeekValue == null) {
            return v10Var;
        }
        int i2 = typedValuePeekValue.type;
        return i2 == 5 ? new y0(TypedValue.complexToDimensionPixelSize(typedValuePeekValue.data, typedArray.getResources().getDisplayMetrics())) : i2 == 6 ? new w22(typedValuePeekValue.getFraction(1.0f, 1.0f)) : v10Var;
    }

    public final boolean d(RectF rectF) {
        boolean z = this.l.getClass().equals(zb0.class) && this.j.getClass().equals(zb0.class) && this.i.getClass().equals(zb0.class) && this.k.getClass().equals(zb0.class);
        float fA = this.e.a(rectF);
        return z && ((this.f.a(rectF) > fA ? 1 : (this.f.a(rectF) == fA ? 0 : -1)) == 0 && (this.h.a(rectF) > fA ? 1 : (this.h.a(rectF) == fA ? 0 : -1)) == 0 && (this.g.a(rectF) > fA ? 1 : (this.g.a(rectF) == fA ? 0 : -1)) == 0) && ((this.b instanceof q52) && (this.a instanceof q52) && (this.c instanceof q52) && (this.d instanceof q52));
    }

    public final wg e() {
        wg wgVar = new wg();
        wgVar.a = this.a;
        wgVar.b = this.b;
        wgVar.c = this.c;
        wgVar.d = this.d;
        wgVar.e = this.e;
        wgVar.f = this.f;
        wgVar.g = this.g;
        wgVar.h = this.h;
        wgVar.i = this.i;
        wgVar.j = this.j;
        wgVar.k = this.k;
        wgVar.l = this.l;
        return wgVar;
    }
}
