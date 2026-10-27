package b.d.a.a.v;

import a.b.k.l;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Rect f2071a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f2072b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ColorStateList f2073c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ColorStateList f2074d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2075e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b.d.a.a.e0.j f2076f;

    public b(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, b.d.a.a.e0.j jVar, Rect rect) {
        l.i.g(rect.left);
        l.i.g(rect.top);
        l.i.g(rect.right);
        l.i.g(rect.bottom);
        this.f2071a = rect;
        this.f2072b = colorStateList2;
        this.f2073c = colorStateList;
        this.f2074d = colorStateList3;
        this.f2075e = i;
        this.f2076f = jVar;
    }

    public static b a(Context context, int i) {
        if (!(i != 0)) {
            throw new IllegalArgumentException("Cannot create a CalendarItemStyle with a styleResId of 0");
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, b.d.a.a.k.MaterialCalendarItem);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(b.d.a.a.k.MaterialCalendarItem_android_insetLeft, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(b.d.a.a.k.MaterialCalendarItem_android_insetTop, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(b.d.a.a.k.MaterialCalendarItem_android_insetRight, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(b.d.a.a.k.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList colorStateListY = l.i.y(context, typedArrayObtainStyledAttributes, b.d.a.a.k.MaterialCalendarItem_itemFillColor);
        ColorStateList colorStateListY2 = l.i.y(context, typedArrayObtainStyledAttributes, b.d.a.a.k.MaterialCalendarItem_itemTextColor);
        ColorStateList colorStateListY3 = l.i.y(context, typedArrayObtainStyledAttributes, b.d.a.a.k.MaterialCalendarItem_itemStrokeColor);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.a.a.k.MaterialCalendarItem_itemStrokeWidth, 0);
        b.d.a.a.e0.j jVarA = b.d.a.a.e0.j.a(context, typedArrayObtainStyledAttributes.getResourceId(b.d.a.a.k.MaterialCalendarItem_itemShapeAppearance, 0), typedArrayObtainStyledAttributes.getResourceId(b.d.a.a.k.MaterialCalendarItem_itemShapeAppearanceOverlay, 0), new b.d.a.a.e0.a(0)).a();
        typedArrayObtainStyledAttributes.recycle();
        return new b(colorStateListY, colorStateListY2, colorStateListY3, dimensionPixelSize, jVarA, rect);
    }

    public void b(TextView textView) {
        b.d.a.a.e0.g gVar = new b.d.a.a.e0.g();
        b.d.a.a.e0.g gVar2 = new b.d.a.a.e0.g();
        gVar.setShapeAppearanceModel(this.f2076f);
        gVar2.setShapeAppearanceModel(this.f2076f);
        gVar.q(this.f2073c);
        gVar.t(this.f2075e, this.f2074d);
        textView.setTextColor(this.f2072b);
        RippleDrawable rippleDrawable = new RippleDrawable(this.f2072b.withAlpha(30), gVar, gVar2);
        Rect rect = this.f2071a;
        a.h.k.q.X(textView, new InsetDrawable((Drawable) rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
