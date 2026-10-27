package u5;

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
import o0.t;

/* JADX INFO: compiled from: CalendarItemStyle.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Rect f8244a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f8245b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ColorStateList f8246c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ColorStateList f8247d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8248e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final e6.m f8249f;

    public a(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i8, e6.m mVar, Rect rect) {
        n0.h.c(rect.left);
        n0.h.c(rect.top);
        n0.h.c(rect.right);
        n0.h.c(rect.bottom);
        this.f8244a = rect;
        this.f8245b = colorStateList2;
        this.f8246c = colorStateList;
        this.f8247d = colorStateList3;
        this.f8248e = i8;
        this.f8249f = mVar;
    }

    public static a a(Context context, int i8) {
        n0.h.b(i8 != 0, "Cannot create a CalendarItemStyle with a styleResId of 0");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i8, R$styleable.MaterialCalendarItem);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetLeft, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetTop, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetRight, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList colorStateListA = b6.c.a(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemFillColor);
        ColorStateList colorStateListA2 = b6.c.a(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemTextColor);
        ColorStateList colorStateListA3 = b6.c.a(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemStrokeColor);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MaterialCalendarItem_itemStrokeWidth, 0);
        e6.m mVarM = e6.m.b(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendarItem_itemShapeAppearance, 0), typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).m();
        typedArrayObtainStyledAttributes.recycle();
        return new a(colorStateListA, colorStateListA2, colorStateListA3, dimensionPixelSize, mVarM, rect);
    }

    public int b() {
        return this.f8244a.bottom;
    }

    public int c() {
        return this.f8244a.top;
    }

    public void d(TextView textView) {
        e6.h hVar = new e6.h();
        e6.h hVar2 = new e6.h();
        hVar.setShapeAppearanceModel(this.f8249f);
        hVar2.setShapeAppearanceModel(this.f8249f);
        hVar.X(this.f8246c);
        hVar.h0(this.f8248e, this.f8247d);
        textView.setTextColor(this.f8245b);
        Drawable rippleDrawable = Build.VERSION.SDK_INT >= 21 ? new RippleDrawable(this.f8245b.withAlpha(30), hVar, hVar2) : hVar;
        Rect rect = this.f8244a;
        t.o0(textView, new InsetDrawable(rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
