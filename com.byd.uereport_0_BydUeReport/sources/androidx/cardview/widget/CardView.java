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
import androidx.cardview.R$attr;
import androidx.cardview.R$color;
import androidx.cardview.R$style;
import androidx.cardview.R$styleable;
import u.b;
import u.c;
import u.d;
import u.e;

/* JADX INFO: loaded from: classes.dex */
public class CardView extends FrameLayout {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f798d = {R.attr.colorBackground};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e f799e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f800f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f801g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f802h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f803i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Rect f804j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Rect f805k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d f806l;

    public class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Drawable f807a;

        public a() {
        }

        @Override // u.d
        public void a(int i8, int i9, int i10, int i11) {
            CardView.this.f805k.set(i8, i9, i10, i11);
            CardView cardView = CardView.this;
            Rect rect = cardView.f804j;
            CardView.super.setPadding(i8 + rect.left, i9 + rect.top, i10 + rect.right, i11 + rect.bottom);
        }

        @Override // u.d
        public View b() {
            return CardView.this;
        }

        @Override // u.d
        public void c(int i8, int i9) {
            CardView cardView = CardView.this;
            if (i8 > cardView.f802h) {
                CardView.super.setMinimumWidth(i8);
            }
            CardView cardView2 = CardView.this;
            if (i9 > cardView2.f803i) {
                CardView.super.setMinimumHeight(i9);
            }
        }

        @Override // u.d
        public void d(Drawable drawable) {
            this.f807a = drawable;
            CardView.this.setBackgroundDrawable(drawable);
        }

        @Override // u.d
        public boolean e() {
            return CardView.this.getPreventCornerOverlap();
        }

        @Override // u.d
        public boolean f() {
            return CardView.this.getUseCompatPadding();
        }

        @Override // u.d
        public Drawable g() {
            return this.f807a;
        }
    }

    static {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 21) {
            f799e = new b();
        } else if (i8 >= 17) {
            f799e = new u.a();
        } else {
            f799e = new c();
        }
        f799e.g();
    }

    public CardView(Context context) {
        this(context, null);
    }

    public ColorStateList getCardBackgroundColor() {
        return f799e.b(this.f806l);
    }

    public float getCardElevation() {
        return f799e.e(this.f806l);
    }

    public int getContentPaddingBottom() {
        return this.f804j.bottom;
    }

    public int getContentPaddingLeft() {
        return this.f804j.left;
    }

    public int getContentPaddingRight() {
        return this.f804j.right;
    }

    public int getContentPaddingTop() {
        return this.f804j.top;
    }

    public float getMaxCardElevation() {
        return f799e.a(this.f806l);
    }

    public boolean getPreventCornerOverlap() {
        return this.f801g;
    }

    public float getRadius() {
        return f799e.h(this.f806l);
    }

    public boolean getUseCompatPadding() {
        return this.f800f;
    }

    public void h(int i8, int i9, int i10, int i11) {
        this.f804j.set(i8, i9, i10, i11);
        f799e.f(this.f806l);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i8, int i9) {
        if (f799e instanceof b) {
            super.onMeasure(i8, i9);
            return;
        }
        int mode = View.MeasureSpec.getMode(i8);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            i8 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.j(this.f806l)), View.MeasureSpec.getSize(i8)), mode);
        }
        int mode2 = View.MeasureSpec.getMode(i9);
        if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
            i9 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.i(this.f806l)), View.MeasureSpec.getSize(i9)), mode2);
        }
        super.onMeasure(i8, i9);
    }

    public void setCardBackgroundColor(int i8) {
        f799e.n(this.f806l, ColorStateList.valueOf(i8));
    }

    public void setCardElevation(float f8) {
        f799e.l(this.f806l, f8);
    }

    public void setMaxCardElevation(float f8) {
        f799e.o(this.f806l, f8);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i8) {
        this.f803i = i8;
        super.setMinimumHeight(i8);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i8) {
        this.f802h = i8;
        super.setMinimumWidth(i8);
    }

    @Override // android.view.View
    public void setPadding(int i8, int i9, int i10, int i11) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i8, int i9, int i10, int i11) {
    }

    public void setPreventCornerOverlap(boolean z7) {
        if (z7 != this.f801g) {
            this.f801g = z7;
            f799e.m(this.f806l);
        }
    }

    public void setRadius(float f8) {
        f799e.d(this.f806l, f8);
    }

    public void setUseCompatPadding(boolean z7) {
        if (this.f800f != z7) {
            this.f800f = z7;
            f799e.k(this.f806l);
        }
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.cardViewStyle);
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        f799e.n(this.f806l, colorStateList);
    }

    public CardView(Context context, AttributeSet attributeSet, int i8) {
        int color;
        ColorStateList colorStateListValueOf;
        super(context, attributeSet, i8);
        Rect rect = new Rect();
        this.f804j = rect;
        this.f805k = new Rect();
        a aVar = new a();
        this.f806l = aVar;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CardView, i8, R$style.CardView);
        int i9 = R$styleable.CardView_cardBackgroundColor;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            colorStateListValueOf = typedArrayObtainStyledAttributes.getColorStateList(i9);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(f798d);
            int color2 = typedArrayObtainStyledAttributes2.getColor(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color2, fArr);
            if (fArr[2] > 0.5f) {
                color = getResources().getColor(R$color.cardview_light_background);
            } else {
                color = getResources().getColor(R$color.cardview_dark_background);
            }
            colorStateListValueOf = ColorStateList.valueOf(color);
        }
        ColorStateList colorStateList = colorStateListValueOf;
        float dimension = typedArrayObtainStyledAttributes.getDimension(R$styleable.CardView_cardCornerRadius, 0.0f);
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(R$styleable.CardView_cardElevation, 0.0f);
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(R$styleable.CardView_cardMaxElevation, 0.0f);
        this.f800f = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CardView_cardUseCompatPadding, false);
        this.f801g = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CardView_cardPreventCornerOverlap, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPadding, 0);
        rect.left = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingLeft, dimensionPixelSize);
        rect.top = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingTop, dimensionPixelSize);
        rect.right = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingRight, dimensionPixelSize);
        rect.bottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingBottom, dimensionPixelSize);
        float f8 = dimension2 > dimension3 ? dimension2 : dimension3;
        this.f802h = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_android_minWidth, 0);
        this.f803i = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_android_minHeight, 0);
        typedArrayObtainStyledAttributes.recycle();
        f799e.c(aVar, context, colorStateList, dimension, dimension2, f8);
    }
}
