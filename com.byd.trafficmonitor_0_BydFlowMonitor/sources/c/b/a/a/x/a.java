package c.b.a.a.x;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.widget.TextView;
import b.h.m.t;

/* JADX INFO: compiled from: CalendarItemStyle.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Rect f4100a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f4101b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ColorStateList f4102c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ColorStateList f4103d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4104e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final c.b.a.a.i0.m f4105f;

    public a(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i2, c.b.a.a.i0.m mVar, Rect rect) {
        b.h.l.g.b(rect.left);
        b.h.l.g.b(rect.top);
        b.h.l.g.b(rect.right);
        b.h.l.g.b(rect.bottom);
        this.f4100a = rect;
        this.f4101b = colorStateList2;
        this.f4102c = colorStateList;
        this.f4103d = colorStateList3;
        this.f4104e = i2;
        this.f4105f = mVar;
    }

    public static a a(Context context, int i2) {
        b.h.l.g.a(i2 != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i2, c.b.a.a.l.MaterialCalendarItem);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.b.a.a.l.MaterialCalendarItem_android_insetLeft, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.b.a.a.l.MaterialCalendarItem_android_insetTop, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.b.a.a.l.MaterialCalendarItem_android_insetRight, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.b.a.a.l.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList colorStateListA = c.b.a.a.f0.c.a(context, typedArrayObtainStyledAttributes, c.b.a.a.l.MaterialCalendarItem_itemFillColor);
        ColorStateList colorStateListA2 = c.b.a.a.f0.c.a(context, typedArrayObtainStyledAttributes, c.b.a.a.l.MaterialCalendarItem_itemTextColor);
        ColorStateList colorStateListA3 = c.b.a.a.f0.c.a(context, typedArrayObtainStyledAttributes, c.b.a.a.l.MaterialCalendarItem_itemStrokeColor);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(c.b.a.a.l.MaterialCalendarItem_itemStrokeWidth, 0);
        c.b.a.a.i0.m mVarM = c.b.a.a.i0.m.b(context, typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.MaterialCalendarItem_itemShapeAppearance, 0), typedArrayObtainStyledAttributes.getResourceId(c.b.a.a.l.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).m();
        typedArrayObtainStyledAttributes.recycle();
        return new a(colorStateListA, colorStateListA2, colorStateListA3, dimensionPixelSize, mVarM, rect);
    }

    public int b() {
        return this.f4100a.bottom;
    }

    public int c() {
        return this.f4100a.top;
    }

    public void d(TextView textView) {
        c.b.a.a.i0.h hVar = new c.b.a.a.i0.h();
        c.b.a.a.i0.h hVar2 = new c.b.a.a.i0.h();
        hVar.setShapeAppearanceModel(this.f4105f);
        hVar2.setShapeAppearanceModel(this.f4105f);
        hVar.X(this.f4102c);
        hVar.h0(this.f4104e, this.f4103d);
        textView.setTextColor(this.f4101b);
        Drawable rippleDrawable = Build.VERSION.SDK_INT >= 21 ? new RippleDrawable(this.f4101b.withAlpha(30), hVar, hVar2) : hVar;
        Rect rect = this.f4100a;
        t.n0(textView, new InsetDrawable(rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
