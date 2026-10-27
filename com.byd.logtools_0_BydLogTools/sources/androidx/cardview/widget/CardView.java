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
    private static final int[] f721b = {R.attr.colorBackground};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final e f722c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f723d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f724e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f725f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f726g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final Rect f727h;
    final Rect i;
    private final d j;

    class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Drawable f728a;

        a() {
        }

        @Override // androidx.cardview.widget.d
        public void a(int i, int i2, int i3, int i4) {
            CardView.this.i.set(i, i2, i3, i4);
            CardView cardView = CardView.this;
            Rect rect = cardView.f727h;
            CardView.super.setPadding(i + rect.left, i2 + rect.top, i3 + rect.right, i4 + rect.bottom);
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
            return this.f728a;
        }

        @Override // androidx.cardview.widget.d
        public void e(int i, int i2) {
            CardView cardView = CardView.this;
            if (i > cardView.f725f) {
                CardView.super.setMinimumWidth(i);
            }
            CardView cardView2 = CardView.this;
            if (i2 > cardView2.f726g) {
                CardView.super.setMinimumHeight(i2);
            }
        }

        @Override // androidx.cardview.widget.d
        public void f(Drawable drawable) {
            this.f728a = drawable;
            CardView.this.setBackgroundDrawable(drawable);
        }

        @Override // androidx.cardview.widget.d
        public boolean g() {
            return CardView.this.getPreventCornerOverlap();
        }
    }

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            f722c = new b();
        } else if (i >= 17) {
            f722c = new androidx.cardview.widget.a();
        } else {
            f722c = new c();
        }
        f722c.o();
    }

    public CardView(Context context) {
        this(context, null);
    }

    public ColorStateList getCardBackgroundColor() {
        return f722c.f(this.j);
    }

    public float getCardElevation() {
        return f722c.l(this.j);
    }

    public int getContentPaddingBottom() {
        return this.f727h.bottom;
    }

    public int getContentPaddingLeft() {
        return this.f727h.left;
    }

    public int getContentPaddingRight() {
        return this.f727h.right;
    }

    public int getContentPaddingTop() {
        return this.f727h.top;
    }

    public float getMaxCardElevation() {
        return f722c.a(this.j);
    }

    public boolean getPreventCornerOverlap() {
        return this.f724e;
    }

    public float getRadius() {
        return f722c.b(this.j);
    }

    public boolean getUseCompatPadding() {
        return this.f723d;
    }

    public void h(int i, int i2, int i3, int i4) {
        this.f727h.set(i, i2, i3, i4);
        f722c.n(this.j);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (f722c instanceof b) {
            super.onMeasure(i, i2);
            return;
        }
        int mode = View.MeasureSpec.getMode(i);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            i = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.d(this.j)), View.MeasureSpec.getSize(i)), mode);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
            i2 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.c(this.j)), View.MeasureSpec.getSize(i2)), mode2);
        }
        super.onMeasure(i, i2);
    }

    public void setCardBackgroundColor(int i) {
        f722c.k(this.j, ColorStateList.valueOf(i));
    }

    public void setCardElevation(float f2) {
        f722c.g(this.j, f2);
    }

    public void setMaxCardElevation(float f2) {
        f722c.m(this.j, f2);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i) {
        this.f726g = i;
        super.setMinimumHeight(i);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i) {
        this.f725f = i;
        super.setMinimumWidth(i);
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.f724e) {
            this.f724e = z;
            f722c.j(this.j);
        }
    }

    public void setRadius(float f2) {
        f722c.i(this.j, f2);
    }

    public void setUseCompatPadding(boolean z) {
        if (this.f723d != z) {
            this.f723d = z;
            f722c.e(this.j);
        }
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.c.a.f2238a);
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        f722c.k(this.j, colorStateList);
    }

    public CardView(Context context, AttributeSet attributeSet, int i) {
        int color;
        ColorStateList colorStateListValueOf;
        super(context, attributeSet, i);
        Rect rect = new Rect();
        this.f727h = rect;
        this.i = new Rect();
        a aVar = new a();
        this.j = aVar;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.c.e.f2245a, i, b.c.d.f2244a);
        int i2 = b.c.e.f2248d;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            colorStateListValueOf = typedArrayObtainStyledAttributes.getColorStateList(i2);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(f721b);
            int color2 = typedArrayObtainStyledAttributes2.getColor(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color2, fArr);
            if (fArr[2] > 0.5f) {
                color = getResources().getColor(b.c.b.f2240b);
            } else {
                color = getResources().getColor(b.c.b.f2239a);
            }
            colorStateListValueOf = ColorStateList.valueOf(color);
        }
        ColorStateList colorStateList = colorStateListValueOf;
        float dimension = typedArrayObtainStyledAttributes.getDimension(b.c.e.f2249e, 0.0f);
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(b.c.e.f2250f, 0.0f);
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(b.c.e.f2251g, 0.0f);
        this.f723d = typedArrayObtainStyledAttributes.getBoolean(b.c.e.i, false);
        this.f724e = typedArrayObtainStyledAttributes.getBoolean(b.c.e.f2252h, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.j, 0);
        rect.left = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.l, dimensionPixelSize);
        rect.top = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.n, dimensionPixelSize);
        rect.right = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.m, dimensionPixelSize);
        rect.bottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.k, dimensionPixelSize);
        float f2 = dimension2 > dimension3 ? dimension2 : dimension3;
        this.f725f = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.f2246b, 0);
        this.f726g = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.f2247c, 0);
        typedArrayObtainStyledAttributes.recycle();
        f722c.h(aVar, context, colorStateList, dimension, dimension2, f2);
    }
}
