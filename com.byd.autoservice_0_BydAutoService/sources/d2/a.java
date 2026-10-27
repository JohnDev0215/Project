package d2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.widget.TextView;
import com.google.android.material.R$styleable;
import l0.t;

/* JADX INFO: compiled from: CalendarItemStyle.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Rect f2702a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f2703b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ColorStateList f2704c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ColorStateList f2705d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2706e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final n2.m f2707f;

    public a(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i3, n2.m mVar, Rect rect) {
        k0.h.b(rect.left);
        k0.h.b(rect.top);
        k0.h.b(rect.right);
        k0.h.b(rect.bottom);
        this.f2702a = rect;
        this.f2703b = colorStateList2;
        this.f2704c = colorStateList;
        this.f2705d = colorStateList3;
        this.f2706e = i3;
        this.f2707f = mVar;
    }

    public static a a(Context context, int i3) {
        k0.h.a(i3 != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i3, R$styleable.MaterialCalendarItem);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetLeft, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetTop, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetRight, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList colorStateListA = k2.c.a(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemFillColor);
        ColorStateList colorStateListA2 = k2.c.a(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemTextColor);
        ColorStateList colorStateListA3 = k2.c.a(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemStrokeColor);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MaterialCalendarItem_itemStrokeWidth, 0);
        n2.m mVarM = n2.m.b(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendarItem_itemShapeAppearance, 0), typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).m();
        typedArrayObtainStyledAttributes.recycle();
        return new a(colorStateListA, colorStateListA2, colorStateListA3, dimensionPixelSize, mVarM, rect);
    }

    public int b() {
        return this.f2702a.bottom;
    }

    public int c() {
        return this.f2702a.top;
    }

    public void d(TextView textView) {
        n2.h hVar = new n2.h();
        n2.h hVar2 = new n2.h();
        hVar.setShapeAppearanceModel(this.f2707f);
        hVar2.setShapeAppearanceModel(this.f2707f);
        hVar.X(this.f2704c);
        hVar.h0(this.f2706e, this.f2705d);
        textView.setTextColor(this.f2703b);
        Drawable rippleDrawable = Build.VERSION.SDK_INT >= 21 ? new RippleDrawable(this.f2703b.withAlpha(30), hVar, hVar2) : hVar;
        Rect rect = this.f2702a;
        t.m0(textView, new InsetDrawable(rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
