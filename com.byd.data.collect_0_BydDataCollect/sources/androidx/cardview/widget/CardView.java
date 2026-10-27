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

/* JADX INFO: loaded from: classes.dex */
public class CardView extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f755b = {R.attr.colorBackground};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final e f756c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f757d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f758e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f759f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f760g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final Rect f761h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final Rect f762i;
    private final d j;

    class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Drawable f763a;

        a() {
        }

        @Override // androidx.cardview.widget.d
        public void a(int i2, int i3, int i4, int i5) {
            CardView.this.f762i.set(i2, i3, i4, i5);
            CardView cardView = CardView.this;
            Rect rect = cardView.f761h;
            CardView.super.setPadding(i2 + rect.left, i3 + rect.top, i4 + rect.right, i5 + rect.bottom);
        }

        @Override // androidx.cardview.widget.d
        public View b() {
            return CardView.this;
        }

        @Override // androidx.cardview.widget.d
        public boolean c() {
            return CardView.this.getUseCompatPadding();
        }

        @Override // androidx.cardview.widget.d
        public Drawable d() {
            return this.f763a;
        }

        @Override // androidx.cardview.widget.d
        public void e(int i2, int i3) {
            CardView cardView = CardView.this;
            if (i2 > cardView.f759f) {
                CardView.super.setMinimumWidth(i2);
            }
            CardView cardView2 = CardView.this;
            if (i3 > cardView2.f760g) {
                CardView.super.setMinimumHeight(i3);
            }
        }

        @Override // androidx.cardview.widget.d
        public void f(Drawable drawable) {
            this.f763a = drawable;
            CardView.this.setBackgroundDrawable(drawable);
        }

        @Override // androidx.cardview.widget.d
        public boolean g() {
            return CardView.this.getPreventCornerOverlap();
        }
    }

    static {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 21) {
            f756c = new b();
        } else if (i2 >= 17) {
            f756c = new androidx.cardview.widget.a();
        } else {
            f756c = new c();
        }
        f756c.o();
    }

    public CardView(Context context) {
        this(context, null);
    }

    public ColorStateList getCardBackgroundColor() {
        return f756c.f(this.j);
    }

    public float getCardElevation() {
        return f756c.l(this.j);
    }

    public int getContentPaddingBottom() {
        return this.f761h.bottom;
    }

    public int getContentPaddingLeft() {
        return this.f761h.left;
    }

    public int getContentPaddingRight() {
        return this.f761h.right;
    }

    public int getContentPaddingTop() {
        return this.f761h.top;
    }

    public float getMaxCardElevation() {
        return f756c.a(this.j);
    }

    public boolean getPreventCornerOverlap() {
        return this.f758e;
    }

    public float getRadius() {
        return f756c.b(this.j);
    }

    public boolean getUseCompatPadding() {
        return this.f757d;
    }

    public void h(int i2, int i3, int i4, int i5) {
        this.f761h.set(i2, i3, i4, i5);
        f756c.n(this.j);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        if (f756c instanceof b) {
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
        f756c.k(this.j, ColorStateList.valueOf(i2));
    }

    public void setCardElevation(float f2) {
        f756c.g(this.j, f2);
    }

    public void setMaxCardElevation(float f2) {
        f756c.m(this.j, f2);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i2) {
        this.f760g = i2;
        super.setMinimumHeight(i2);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i2) {
        this.f759f = i2;
        super.setMinimumWidth(i2);
    }

    @Override // android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i2, int i3, int i4, int i5) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.f758e) {
            this.f758e = z;
            f756c.j(this.j);
        }
    }

    public void setRadius(float f2) {
        f756c.i(this.j, f2);
    }

    public void setUseCompatPadding(boolean z) {
        if (this.f757d != z) {
            this.f757d = z;
            f756c.e(this.j);
        }
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.c.a.cardViewStyle);
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        f756c.k(this.j, colorStateList);
    }

    public CardView(Context context, AttributeSet attributeSet, int i2) {
        int color;
        ColorStateList colorStateListValueOf;
        super(context, attributeSet, i2);
        Rect rect = new Rect();
        this.f761h = rect;
        this.f762i = new Rect();
        a aVar = new a();
        this.j = aVar;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.c.e.CardView, i2, b.c.d.CardView);
        int i3 = b.c.e.CardView_cardBackgroundColor;
        if (typedArrayObtainStyledAttributes.hasValue(i3)) {
            colorStateListValueOf = typedArrayObtainStyledAttributes.getColorStateList(i3);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(f755b);
            int color2 = typedArrayObtainStyledAttributes2.getColor(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color2, fArr);
            if (fArr[2] > 0.5f) {
                color = getResources().getColor(b.c.b.cardview_light_background);
            } else {
                color = getResources().getColor(b.c.b.cardview_dark_background);
            }
            colorStateListValueOf = ColorStateList.valueOf(color);
        }
        ColorStateList colorStateList = colorStateListValueOf;
        float dimension = typedArrayObtainStyledAttributes.getDimension(b.c.e.CardView_cardCornerRadius, 0.0f);
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(b.c.e.CardView_cardElevation, 0.0f);
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(b.c.e.CardView_cardMaxElevation, 0.0f);
        this.f757d = typedArrayObtainStyledAttributes.getBoolean(b.c.e.CardView_cardUseCompatPadding, false);
        this.f758e = typedArrayObtainStyledAttributes.getBoolean(b.c.e.CardView_cardPreventCornerOverlap, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.CardView_contentPadding, 0);
        rect.left = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.CardView_contentPaddingLeft, dimensionPixelSize);
        rect.top = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.CardView_contentPaddingTop, dimensionPixelSize);
        rect.right = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.CardView_contentPaddingRight, dimensionPixelSize);
        rect.bottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.CardView_contentPaddingBottom, dimensionPixelSize);
        float f2 = dimension2 > dimension3 ? dimension2 : dimension3;
        this.f759f = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.CardView_android_minWidth, 0);
        this.f760g = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.CardView_android_minHeight, 0);
        typedArrayObtainStyledAttributes.recycle();
        f756c.h(aVar, context, colorStateList, dimension, dimension2, f2);
    }
}
