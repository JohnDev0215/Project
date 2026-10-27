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
    private static final int[] f505b = {R.attr.colorBackground};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final e f506c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f507d;
    private boolean e;
    int f;
    int g;
    final Rect h;
    final Rect i;
    private final d j;

    class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Drawable f508a;

        a() {
        }

        @Override // androidx.cardview.widget.d
        public void a(int i, int i2, int i3, int i4) {
            CardView.this.i.set(i, i2, i3, i4);
            CardView cardView = CardView.this;
            Rect rect = cardView.h;
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
            return this.f508a;
        }

        @Override // androidx.cardview.widget.d
        public void e(int i, int i2) {
            CardView cardView = CardView.this;
            if (i > cardView.f) {
                CardView.super.setMinimumWidth(i);
            }
            CardView cardView2 = CardView.this;
            if (i2 > cardView2.g) {
                CardView.super.setMinimumHeight(i2);
            }
        }

        @Override // androidx.cardview.widget.d
        public void f(Drawable drawable) {
            this.f508a = drawable;
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
            f506c = new b();
        } else if (i >= 17) {
            f506c = new androidx.cardview.widget.a();
        } else {
            f506c = new c();
        }
        f506c.o();
    }

    public CardView(Context context) {
        this(context, null);
    }

    public ColorStateList getCardBackgroundColor() {
        return f506c.f(this.j);
    }

    public float getCardElevation() {
        return f506c.l(this.j);
    }

    public int getContentPaddingBottom() {
        return this.h.bottom;
    }

    public int getContentPaddingLeft() {
        return this.h.left;
    }

    public int getContentPaddingRight() {
        return this.h.right;
    }

    public int getContentPaddingTop() {
        return this.h.top;
    }

    public float getMaxCardElevation() {
        return f506c.a(this.j);
    }

    public boolean getPreventCornerOverlap() {
        return this.e;
    }

    public float getRadius() {
        return f506c.b(this.j);
    }

    public boolean getUseCompatPadding() {
        return this.f507d;
    }

    public void h(int i, int i2, int i3, int i4) {
        this.h.set(i, i2, i3, i4);
        f506c.n(this.j);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (f506c instanceof b) {
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
        f506c.k(this.j, ColorStateList.valueOf(i));
    }

    public void setCardElevation(float f) {
        f506c.g(this.j, f);
    }

    public void setMaxCardElevation(float f) {
        f506c.m(this.j, f);
    }

    @Override // android.view.View
    public void setMinimumHeight(int i) {
        this.g = i;
        super.setMinimumHeight(i);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i) {
        this.f = i;
        super.setMinimumWidth(i);
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
    }

    public void setPreventCornerOverlap(boolean z) {
        if (z != this.e) {
            this.e = z;
            f506c.j(this.j);
        }
    }

    public void setRadius(float f) {
        f506c.i(this.j, f);
    }

    public void setUseCompatPadding(boolean z) {
        if (this.f507d != z) {
            this.f507d = z;
            f506c.e(this.j);
        }
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.c.a.f1596a);
    }

    public void setCardBackgroundColor(ColorStateList colorStateList) {
        f506c.k(this.j, colorStateList);
    }

    public CardView(Context context, AttributeSet attributeSet, int i) {
        int color;
        ColorStateList colorStateListValueOf;
        super(context, attributeSet, i);
        Rect rect = new Rect();
        this.h = rect;
        this.i = new Rect();
        a aVar = new a();
        this.j = aVar;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b.c.e.f1603a, i, b.c.d.f1602a);
        int i2 = b.c.e.f1606d;
        if (typedArrayObtainStyledAttributes.hasValue(i2)) {
            colorStateListValueOf = typedArrayObtainStyledAttributes.getColorStateList(i2);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(f505b);
            int color2 = typedArrayObtainStyledAttributes2.getColor(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color2, fArr);
            if (fArr[2] > 0.5f) {
                color = getResources().getColor(b.c.b.f1598b);
            } else {
                color = getResources().getColor(b.c.b.f1597a);
            }
            colorStateListValueOf = ColorStateList.valueOf(color);
        }
        ColorStateList colorStateList = colorStateListValueOf;
        float dimension = typedArrayObtainStyledAttributes.getDimension(b.c.e.e, 0.0f);
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(b.c.e.f, 0.0f);
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(b.c.e.g, 0.0f);
        this.f507d = typedArrayObtainStyledAttributes.getBoolean(b.c.e.i, false);
        this.e = typedArrayObtainStyledAttributes.getBoolean(b.c.e.h, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.j, 0);
        rect.left = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.l, dimensionPixelSize);
        rect.top = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.n, dimensionPixelSize);
        rect.right = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.m, dimensionPixelSize);
        rect.bottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.k, dimensionPixelSize);
        float f = dimension2 > dimension3 ? dimension2 : dimension3;
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.f1604b, 0);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.c.e.f1605c, 0);
        typedArrayObtainStyledAttributes.recycle();
        f506c.h(aVar, context, colorStateList, dimension, dimension2, f);
    }
}
