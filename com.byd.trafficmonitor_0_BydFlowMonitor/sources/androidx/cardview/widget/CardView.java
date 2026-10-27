package androidx.cardview.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import b.d.f.b;
import b.d.f.c;
import b.d.f.d;
import b.d.f.e;

/* JADX INFO: loaded from: classes.dex */
public class CardView extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f563b = {R.attr.colorBackground};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f564c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f565d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f566e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f567f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f568g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Rect f569h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Rect f570i;
    public final d j;

    public class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Drawable f571a;

        public a() {
        }

        @Override // b.d.f.d
        public void a(int i2, int i3, int i4, int i5) {
            CardView.this.f570i.set(i2, i3, i4, i5);
            CardView cardView = CardView.this;
            Rect rect = cardView.f569h;
            CardView.super.setPadding(i2 + rect.left, i3 + rect.top, i4 + rect.right, i5 + rect.bottom);
        }

        @Override // b.d.f.d
        public View b() {
            return CardView.this;
        }

        @Override // b.d.f.d
        public boolean c() {
            return CardView.this.getUseCompatPadding();
        }

        @Override // b.d.f.d
        public Drawable d() {
            return this.f571a;
        }

        @Override // b.d.f.d
        public void e(int i2, int i3) {
            CardView cardView = CardView.this;
            if (i2 > cardView.f567f) {
                CardView.super.setMinimumWidth(i2);
            }
            CardView cardView2 = CardView.this;
            if (i3 > cardView2.f568g) {
                CardView.super.setMinimumHeight(i3);
            }
        }

        @Override // b.d.f.d
        public void f(Drawable drawable) {
            this.f571a = drawable;
            CardView.this.setBackgroundDrawable(drawable);
        }

        @Override // b.d.f.d
        public boolean g() {
            return CardView.this.getPreventCornerOverlap();
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            f564c = new b();
        } else if (i2 >= 17) {
            f564c = new b.d.f.a();
        } else {
            f564c = new c();
        }
        f564c.o();
    }

    public CardView(Context context) {
        this(context, null);
    }

    public ColorStateList getCardBackgroundColor() {
        return f564c.f(this.j);
    }

    public float getCardElevation() {
        return f564c.l(this.j);
    }

    public int getContentPaddingBottom() {
        return this.f569h.bottom;
    }

    public int getContentPaddingLeft() {
        return this.f569h.left;
    }

    public int getContentPaddingRight() {
        return this.f569h.right;
    }

    public int getContentPaddingTop() {
        return this.f569h.top;
    }

    public float getMaxCardElevation() {
        return f564c.a(this.j);
    }

    public boolean getPreventCornerOverlap() {
        return this.f566e;
    }

    public float getRadius() {
        return f564c.b(this.j);
    }

    public boolean getUseCompatPadding() {
        return this.f565d;
    }

    public void h(int i2, int i3, int i4, int i5) {
        this.f569h.set(i2, i3, i4, i5);
        f564c.n(this.j);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (f564c instanceof b) {
            super.onMeasure(i2, i3);
            return;
        }
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.d(this.j)), View.MeasureSpec.getSize(i2)), mode);
        }
        int mode2 = View.MeasureSpec.getMode(i3);
        if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
            i3 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.c(this.j)), View.MeasureSpec.getSize(i3)), mode2);
        }
        super.onMeasure(i2, i3);
    }

    public void setCardBackgroundColor(int i2) {
        f564c.k(this.j, ColorStateList.valueOf(i2));
    }

    public void setCardElevation(float f2) {
        f564c.g(this.j, f2);
    }

    public void setMaxCardElevation(float f2) {
        f564c.m(this.j, f2);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i2) {
        this.f568g = i2;
        super.setMinimumHeight(i2);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i2) {
        this.f567f = i2;
        super.setMinimumWidth(i2);
    }

    @Override // android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i2, int i3, int i4, int i5) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.f566e) {
            this.f566e = z;
            f564c.j(this.j);
        }
    }

    public void setRadius(float f2) {
        f564c.i(this.j, f2);
    }

    public void setUseCompatPadding(boolean z) {
        if (this.f565d != z) {
            this.f565d = z;
            f564c.e(this.j);
        }
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.d.a.cardViewStyle);
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        f564c.k(this.j, colorStateList);
    }

    public CardView(Context context, AttributeSet attributeSet, int i2) {
        int color;
        ColorStateList colorStateListValueOf;
        super(context, attributeSet, i2);
        Rect rect = new Rect();
        this.f569h = rect;
        this.f570i = new Rect();
        a aVar = new a();
        this.j = aVar;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.d.e.CardView, i2, b.d.d.CardView);
        int i3 = b.d.e.CardView_cardBackgroundColor;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            colorStateListValueOf = typedArrayObtainStyledAttributes.getColorStateList(i3);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(f563b);
            int color2 = typedArrayObtainStyledAttributes2.getColor(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color2, fArr);
            if (fArr[2] > 0.5f) {
                color = getResources().getColor(b.d.b.cardview_light_background);
            } else {
                color = getResources().getColor(b.d.b.cardview_dark_background);
            }
            colorStateListValueOf = ColorStateList.valueOf(color);
        }
        ColorStateList colorStateList = colorStateListValueOf;
        float dimension = typedArrayObtainStyledAttributes.getDimension(b.d.e.CardView_cardCornerRadius, 0.0f);
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(b.d.e.CardView_cardElevation, 0.0f);
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(b.d.e.CardView_cardMaxElevation, 0.0f);
        this.f565d = typedArrayObtainStyledAttributes.getBoolean(b.d.e.CardView_cardUseCompatPadding, false);
        this.f566e = typedArrayObtainStyledAttributes.getBoolean(b.d.e.CardView_cardPreventCornerOverlap, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.e.CardView_contentPadding, 0);
        rect.left = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.e.CardView_contentPaddingLeft, dimensionPixelSize);
        rect.top = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.e.CardView_contentPaddingTop, dimensionPixelSize);
        rect.right = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.e.CardView_contentPaddingRight, dimensionPixelSize);
        rect.bottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.e.CardView_contentPaddingBottom, dimensionPixelSize);
        float f2 = dimension2 > dimension3 ? dimension2 : dimension3;
        this.f567f = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.e.CardView_android_minWidth, 0);
        this.f568g = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.e.CardView_android_minHeight, 0);
        typedArrayObtainStyledAttributes.recycle();
        f564c.h(aVar, context, colorStateList, dimension, dimension2, f2);
    }
}
