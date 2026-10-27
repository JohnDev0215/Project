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
import r.b;
import r.c;
import r.d;
import r.e;

/* JADX INFO: loaded from: classes.dex */
public class CardView extends FrameLayout {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f703b = {R.attr.colorBackground};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e f704c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f705d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f706e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f707f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f708g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Rect f709h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Rect f710i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final d f711j;

    public class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Drawable f712a;

        public a() {
        }

        @Override // r.d
        public void a(int i3, int i4, int i5, int i6) {
            CardView.this.f710i.set(i3, i4, i5, i6);
            CardView cardView = CardView.this;
            Rect rect = cardView.f709h;
            CardView.super.setPadding(i3 + rect.left, i4 + rect.top, i5 + rect.right, i6 + rect.bottom);
        }

        @Override // r.d
        public View b() {
            return CardView.this;
        }

        @Override // r.d
        public boolean c() {
            return CardView.this.getUseCompatPadding();
        }

        @Override // r.d
        public Drawable d() {
            return this.f712a;
        }

        @Override // r.d
        public void e(int i3, int i4) {
            CardView cardView = CardView.this;
            if (i3 > cardView.f707f) {
                CardView.super.setMinimumWidth(i3);
            }
            CardView cardView2 = CardView.this;
            if (i4 > cardView2.f708g) {
                CardView.super.setMinimumHeight(i4);
            }
        }

        @Override // r.d
        public void f(Drawable drawable) {
            this.f712a = drawable;
            CardView.this.setBackgroundDrawable(drawable);
        }

        @Override // r.d
        public boolean g() {
            return CardView.this.getPreventCornerOverlap();
        }
    }

    static {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 21) {
            f704c = new b();
        } else if (i3 >= 17) {
            f704c = new r.a();
        } else {
            f704c = new c();
        }
        f704c.o();
    }

    public CardView(Context context) {
        this(context, null);
    }

    public ColorStateList getCardBackgroundColor() {
        return f704c.f(this.f711j);
    }

    public float getCardElevation() {
        return f704c.l(this.f711j);
    }

    public int getContentPaddingBottom() {
        return this.f709h.bottom;
    }

    public int getContentPaddingLeft() {
        return this.f709h.left;
    }

    public int getContentPaddingRight() {
        return this.f709h.right;
    }

    public int getContentPaddingTop() {
        return this.f709h.top;
    }

    public float getMaxCardElevation() {
        return f704c.a(this.f711j);
    }

    public boolean getPreventCornerOverlap() {
        return this.f706e;
    }

    public float getRadius() {
        return f704c.b(this.f711j);
    }

    public boolean getUseCompatPadding() {
        return this.f705d;
    }

    public void h(int i3, int i4, int i5, int i6) {
        this.f709h.set(i3, i4, i5, i6);
        f704c.n(this.f711j);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i3, int i4) {
        if (f704c instanceof b) {
            super.onMeasure(i3, i4);
            return;
        }
        int mode = View.MeasureSpec.getMode(i3);
        if (mode == Integer.MIN_VALUE || mode == 1073741824) {
            i3 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.d(this.f711j)), View.MeasureSpec.getSize(i3)), mode);
        }
        int mode2 = View.MeasureSpec.getMode(i4);
        if (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) {
            i4 = View.MeasureSpec.makeMeasureSpec(Math.max((int) Math.ceil(r0.c(this.f711j)), View.MeasureSpec.getSize(i4)), mode2);
        }
        super.onMeasure(i3, i4);
    }

    public void setCardBackgroundColor(int i3) {
        f704c.k(this.f711j, ColorStateList.valueOf(i3));
    }

    public void setCardElevation(float f3) {
        f704c.g(this.f711j, f3);
    }

    public void setMaxCardElevation(float f3) {
        f704c.m(this.f711j, f3);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i3) {
        this.f708g = i3;
        super.setMinimumHeight(i3);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i3) {
        this.f707f = i3;
        super.setMinimumWidth(i3);
    }

    @Override // android.view.View
    public void setPadding(int i3, int i4, int i5, int i6) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i3, int i4, int i5, int i6) {
    }

    public void setPreventCornerOverlap(boolean z3) {
        if (z3 != this.f706e) {
            this.f706e = z3;
            f704c.j(this.f711j);
        }
    }

    public void setRadius(float f3) {
        f704c.i(this.f711j, f3);
    }

    public void setUseCompatPadding(boolean z3) {
        if (this.f705d != z3) {
            this.f705d = z3;
            f704c.e(this.f711j);
        }
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.cardViewStyle);
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        f704c.k(this.f711j, colorStateList);
    }

    public CardView(Context context, AttributeSet attributeSet, int i3) {
        int color;
        ColorStateList colorStateListValueOf;
        super(context, attributeSet, i3);
        Rect rect = new Rect();
        this.f709h = rect;
        this.f710i = new Rect();
        a aVar = new a();
        this.f711j = aVar;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CardView, i3, R$style.CardView);
        int i4 = R$styleable.CardView_cardBackgroundColor;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            colorStateListValueOf = typedArrayObtainStyledAttributes.getColorStateList(i4);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(f703b);
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
        this.f705d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CardView_cardUseCompatPadding, false);
        this.f706e = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CardView_cardPreventCornerOverlap, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPadding, 0);
        rect.left = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingLeft, dimensionPixelSize);
        rect.top = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingTop, dimensionPixelSize);
        rect.right = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingRight, dimensionPixelSize);
        rect.bottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_contentPaddingBottom, dimensionPixelSize);
        float f3 = dimension2 > dimension3 ? dimension2 : dimension3;
        this.f707f = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_android_minWidth, 0);
        this.f708g = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.CardView_android_minHeight, 0);
        typedArrayObtainStyledAttributes.recycle();
        f704c.h(aVar, context, colorStateList, dimension, dimension2, f3);
    }
}
