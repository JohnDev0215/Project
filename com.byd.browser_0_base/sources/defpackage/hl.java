package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.R$styleable;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: CalendarItemStyle.java */
/* JADX INFO: loaded from: classes.dex */
public final class hl {
    public final Rect a;
    public final ColorStateList b;
    public final ColorStateList c;
    public final ColorStateList d;
    public final int e;
    public final uj2 f;

    public hl(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, uj2 uj2Var, Rect rect) {
        n3.t(rect.left);
        n3.t(rect.top);
        n3.t(rect.right);
        n3.t(rect.bottom);
        this.a = rect;
        this.b = colorStateList2;
        this.c = colorStateList;
        this.d = colorStateList3;
        this.e = i;
        this.f = uj2Var;
    }

    public static hl a(Context context, int i) {
        n3.s("Cannot create a CalendarItemStyle with a styleResId of 0", i != 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.MaterialCalendarItem);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetLeft, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetTop, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetRight, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList colorStateListA = rc1.a(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemFillColor);
        ColorStateList colorStateListA2 = rc1.a(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemTextColor);
        ColorStateList colorStateListA3 = rc1.a(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemStrokeColor);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MaterialCalendarItem_itemStrokeWidth, 0);
        uj2 uj2Var = new uj2(uj2.a(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendarItem_itemShapeAppearance, 0), typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendarItem_itemShapeAppearanceOverlay, 0), new C0195l(0)));
        typedArrayObtainStyledAttributes.recycle();
        return new hl(colorStateListA, colorStateListA2, colorStateListA3, dimensionPixelSize, uj2Var, rect);
    }

    public final void b(TextView textView) {
        sc1 sc1Var = new sc1();
        sc1 sc1Var2 = new sc1();
        uj2 uj2Var = this.f;
        sc1Var.setShapeAppearanceModel(uj2Var);
        sc1Var2.setShapeAppearanceModel(uj2Var);
        sc1Var.n(this.c);
        sc1Var.e.k = this.e;
        sc1Var.invalidateSelf();
        sc1Var.s(this.d);
        ColorStateList colorStateList = this.b;
        textView.setTextColor(colorStateList);
        RippleDrawable rippleDrawable = new RippleDrawable(colorStateList.withAlpha(30), sc1Var, sc1Var2);
        Rect rect = this.a;
        InsetDrawable insetDrawable = new InsetDrawable((Drawable) rippleDrawable, rect.left, rect.top, rect.right, rect.bottom);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.n(textView, insetDrawable);
    }
}
