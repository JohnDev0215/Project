package c.b.a.a.l;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.widget.TextView;
import b.g.j.t;
import com.google.android.material.R$styleable;

/* JADX INFO: compiled from: CalendarItemStyle.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Rect f1708a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f1709b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ColorStateList f1710c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ColorStateList f1711d;
    public final int e;
    public final c.b.a.a.v.m f;

    public a(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, c.b.a.a.v.m mVar, Rect rect) {
        b.g.i.h.b(rect.left);
        b.g.i.h.b(rect.top);
        b.g.i.h.b(rect.right);
        b.g.i.h.b(rect.bottom);
        this.f1708a = rect;
        this.f1709b = colorStateList2;
        this.f1710c = colorStateList;
        this.f1711d = colorStateList3;
        this.e = i;
        this.f = mVar;
    }

    public static a a(Context context, int i) {
        b.g.i.h.a(i != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.MaterialCalendarItem);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetLeft, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetTop, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetRight, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList colorStateListA = c.b.a.a.s.c.a(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemFillColor);
        ColorStateList colorStateListA2 = c.b.a.a.s.c.a(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemTextColor);
        ColorStateList colorStateListA3 = c.b.a.a.s.c.a(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemStrokeColor);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MaterialCalendarItem_itemStrokeWidth, 0);
        c.b.a.a.v.m mVarM = c.b.a.a.v.m.b(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendarItem_itemShapeAppearance, 0), typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).m();
        typedArrayObtainStyledAttributes.recycle();
        return new a(colorStateListA, colorStateListA2, colorStateListA3, dimensionPixelSize, mVarM, rect);
    }

    public int b() {
        return this.f1708a.bottom;
    }

    public int c() {
        return this.f1708a.top;
    }

    public void d(TextView textView) {
        c.b.a.a.v.h hVar = new c.b.a.a.v.h();
        c.b.a.a.v.h hVar2 = new c.b.a.a.v.h();
        hVar.setShapeAppearanceModel(this.f);
        hVar2.setShapeAppearanceModel(this.f);
        hVar.X(this.f1710c);
        hVar.h0(this.e, this.f1711d);
        textView.setTextColor(this.f1709b);
        Drawable rippleDrawable = Build.VERSION.SDK_INT >= 21 ? new RippleDrawable(this.f1709b.withAlpha(30), hVar, hVar2) : hVar;
        Rect rect = this.f1708a;
        t.m0(textView, new InsetDrawable(rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
