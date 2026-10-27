package com.byd.widget.segmentedbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import b.g.b.a;
import c.a.e.c.b;
import com.byd.bydbase.R$dimen;
import com.byd.bydbase.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class SegmentedButton extends View {
    public transient Paint A;
    public transient int B;
    public transient float C;
    public transient float D;
    public transient float E;
    public transient float F;
    public transient PorterDuffColorFilter G;
    public transient PorterDuffColorFilter H;
    public transient Drawable I;
    public transient int J;
    public transient int K;
    public transient int L;
    public transient int M;
    public transient int N;
    public transient int O;
    public int P;
    public transient int Q;
    public transient int R;
    public transient int S;
    public transient boolean T;
    public transient boolean U;
    public transient boolean V;
    public transient boolean W;
    public transient boolean a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient int f2007b;
    public transient boolean b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient int f2008c;
    public transient boolean c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient int f2009d;
    public transient boolean d0;
    public transient int e;
    public transient boolean e0;
    public transient int f;
    public transient float f0;
    public transient int g;
    public transient float g0;
    public transient int h;
    public transient String h0;
    public transient int i;
    public transient int i0;
    public transient boolean j;
    public transient String j0;
    public transient int k;
    public transient Typeface k0;
    public transient int l;
    public transient DrawableGravity l0;
    public transient boolean m;
    public transient boolean m0;
    public transient float n;
    public transient boolean n0;
    public transient boolean o;
    public transient boolean o0;
    public transient TextPaint p;
    public transient StaticLayout q;
    public transient StaticLayout r;
    public transient Rect s;
    public transient int t;
    public transient int u;
    public transient boolean v;
    public transient boolean w;
    public transient Paint x;
    public transient LinearGradient y;
    public transient RectF z;

    public enum DrawableGravity {
        LEFT(0),
        TOP(1),
        RIGHT(2),
        BOTTOM(3);

        private int intValue;

        DrawableGravity(int i) {
            this.intValue = i;
        }

        public static DrawableGravity getById(int i) {
            for (DrawableGravity drawableGravity : values()) {
                if (drawableGravity.intValue == i) {
                    return drawableGravity;
                }
            }
            return null;
        }

        public boolean isHorizontal() {
            int i = this.intValue;
            return i == 0 || i == 2;
        }
    }

    public SegmentedButton(Context context) {
        super(context);
        this.f2007b = 0;
        this.f2008c = 0;
        this.m = true;
        this.s = new Rect();
        this.C = 0.0f;
        this.D = 0.0f;
        this.E = 0.0f;
        this.F = 0.0f;
        this.o0 = false;
        i(context, null);
    }

    private void setTypeface(Typeface typeface) {
        this.p.setTypeface(typeface);
    }

    public final void a(int i, int i2) {
        float height;
        float width;
        float fWidth;
        float intrinsicHeight;
        float intrinsicWidth;
        if (this.n0) {
            height = this.q.getHeight();
            width = this.q.getWidth();
            fWidth = this.s.width();
        } else {
            height = 0.0f;
            width = 0.0f;
            fWidth = 0.0f;
        }
        if (this.m0) {
            intrinsicHeight = this.I.getIntrinsicHeight();
            intrinsicWidth = this.I.getIntrinsicWidth();
        } else {
            intrinsicHeight = 0.0f;
            intrinsicWidth = 0.0f;
        }
        if (this.l0.isHorizontal()) {
            float f = i2;
            if (f > Math.max(height, intrinsicHeight)) {
                float f2 = f / 2.0f;
                this.D = ((f2 - (height / 2.0f)) + getPaddingTop()) - getPaddingBottom();
                this.F = ((f2 - (intrinsicHeight / 2.0f)) + getPaddingTop()) - getPaddingBottom();
            } else if (height > intrinsicHeight) {
                float paddingTop = getPaddingTop();
                this.D = paddingTop;
                this.F = (paddingTop + (height / 2.0f)) - (intrinsicHeight / 2.0f);
            } else {
                float paddingTop2 = getPaddingTop();
                this.F = paddingTop2;
                this.D = (paddingTop2 + (intrinsicHeight / 2.0f)) - (height / 2.0f);
            }
            this.C = getPaddingLeft();
            this.E = width;
            float f3 = i - (fWidth + intrinsicWidth);
            if (f3 > 0.0f) {
                f3 /= 2.0f;
            }
            DrawableGravity drawableGravity = this.l0;
            if (drawableGravity == DrawableGravity.RIGHT) {
                int i3 = this.S;
                float paddingLeft = ((f3 + getPaddingLeft()) - getPaddingRight()) - (i3 / 2.0f);
                this.C = paddingLeft;
                this.E = paddingLeft + fWidth + i3;
                return;
            }
            if (drawableGravity == DrawableGravity.LEFT) {
                int i4 = this.S;
                float paddingLeft2 = ((f3 + getPaddingLeft()) - getPaddingRight()) - (i4 / 2.0f);
                this.E = paddingLeft2;
                this.C = paddingLeft2 + intrinsicWidth + i4;
                return;
            }
            return;
        }
        DrawableGravity drawableGravity2 = this.l0;
        if (drawableGravity2 == DrawableGravity.TOP) {
            float paddingTop3 = getPaddingTop() - getPaddingBottom();
            int i5 = this.S;
            float f4 = paddingTop3 - (i5 / 2.0f);
            this.F = f4;
            float f5 = (i2 - (height + intrinsicHeight)) / 2.0f;
            if (f5 > 0.0f) {
                this.F = f4 + f5;
            }
            this.D = this.F + intrinsicHeight + i5;
        } else if (drawableGravity2 == DrawableGravity.BOTTOM) {
            float paddingTop4 = getPaddingTop() - getPaddingBottom();
            int i6 = this.S;
            float f6 = paddingTop4 - (i6 / 2.0f);
            this.D = f6;
            float f7 = i2 - (intrinsicHeight + height);
            if (f7 > 0.0f) {
                this.D = f6 + (f7 / 2.0f);
            }
            this.F = this.D + height + i6;
        }
        float f8 = i;
        if (f8 > Math.max(fWidth, intrinsicWidth)) {
            float f9 = f8 / 2.0f;
            this.C = ((f9 - (fWidth / 2.0f)) + getPaddingLeft()) - getPaddingRight();
            this.E = ((f9 - (intrinsicWidth / 2.0f)) + getPaddingLeft()) - getPaddingRight();
        } else if (fWidth > intrinsicWidth) {
            float paddingLeft3 = getPaddingLeft();
            this.C = paddingLeft3;
            this.E = (paddingLeft3 + (fWidth / 2.0f)) - (intrinsicWidth / 2.0f);
        } else {
            float paddingLeft4 = getPaddingLeft();
            this.E = paddingLeft4;
            this.C = (paddingLeft4 + (intrinsicWidth / 2.0f)) - (fWidth / 2.0f);
        }
    }

    public void b(float f) {
        this.o = false;
        this.n = 1.0f - f;
        invalidate();
    }

    public void c(float f) {
        this.o = true;
        this.n = f;
        invalidate();
    }

    public final void d(Canvas canvas, ColorFilter colorFilter) {
        int intrinsicWidth = this.I.getIntrinsicWidth();
        if (this.b0) {
            intrinsicWidth = this.Q;
        }
        int intrinsicHeight = this.I.getIntrinsicHeight();
        if (this.c0) {
            intrinsicHeight = this.R;
        }
        this.I.setColorFilter(colorFilter);
        int i = (int) this.E;
        int i2 = (int) this.F;
        this.I.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
        this.I.draw(canvas);
    }

    public final void e(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SegmentedButton);
        this.i0 = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButton_android_minHeight, getContext().getResources().getDimension(R$dimen.dp_40));
        int i = R$styleable.SegmentedButton_sb_drawableTint_onSelection;
        this.J = typedArrayObtainStyledAttributes.getColor(i, -1);
        this.a0 = typedArrayObtainStyledAttributes.hasValue(i);
        int i2 = R$styleable.SegmentedButton_sb_textColor_onSelection;
        this.K = typedArrayObtainStyledAttributes.getColor(i2, -1);
        this.T = typedArrayObtainStyledAttributes.hasValue(i2);
        int i3 = R$styleable.SegmentedButton_sb_rippleColor;
        this.M = typedArrayObtainStyledAttributes.getColor(i3, 0);
        this.U = typedArrayObtainStyledAttributes.hasValue(i3);
        int i4 = R$styleable.SegmentedButton_sb_text;
        this.j0 = typedArrayObtainStyledAttributes.getString(i4);
        this.n0 = typedArrayObtainStyledAttributes.hasValue(i4);
        this.g0 = typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButton_sb_textSize, b.a(getContext(), 14));
        this.L = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButton_sb_textColor, -7829368);
        int i5 = R$styleable.SegmentedButton_sb_textTypefacePath;
        this.h0 = typedArrayObtainStyledAttributes.getString(i5);
        this.e0 = typedArrayObtainStyledAttributes.hasValue(i5);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButton_sb_isfirst, false);
        this.j = z;
        if (z) {
            this.h = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButton_sb_isfirstSelectorColor, 0);
            this.k = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButton_sb_stroke_width, 0.0f);
            this.l = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButton_sb_stroke_color, 0);
        }
        int i6 = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButton_sb_textTypeface, 1);
        if (i6 == 0) {
            this.k0 = Typeface.MONOSPACE;
        } else if (i6 == 1) {
            this.k0 = Typeface.DEFAULT;
        } else if (i6 == 2) {
            this.k0 = Typeface.SANS_SERIF;
        } else if (i6 == 3) {
            this.k0 = Typeface.SERIF;
        }
        try {
            int i7 = R$styleable.SegmentedButton_android_layout_weight;
            this.W = typedArrayObtainStyledAttributes.hasValue(i7);
            this.f0 = typedArrayObtainStyledAttributes.getFloat(i7, 0.0f);
            this.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButton_android_layout_width, 0);
        } catch (Exception unused) {
            this.W = true;
            this.f0 = 1.0f;
        }
        this.V = !this.W && this.N > 0;
        int i8 = R$styleable.SegmentedButton_sb_drawable;
        this.O = typedArrayObtainStyledAttributes.getResourceId(i8, 0);
        int i9 = R$styleable.SegmentedButton_sb_drawableTint;
        this.P = typedArrayObtainStyledAttributes.getColor(i9, -1);
        int i10 = R$styleable.SegmentedButton_sb_drawableWidth;
        this.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(i10, -1);
        int i11 = R$styleable.SegmentedButton_sb_drawableHeight;
        this.R = typedArrayObtainStyledAttributes.getDimensionPixelSize(i11, -1);
        this.S = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButton_sb_drawablePadding, 0);
        this.m0 = typedArrayObtainStyledAttributes.hasValue(i8);
        this.d0 = typedArrayObtainStyledAttributes.hasValue(i9);
        this.b0 = typedArrayObtainStyledAttributes.hasValue(i10);
        this.c0 = typedArrayObtainStyledAttributes.hasValue(i11);
        this.l0 = DrawableGravity.getById(typedArrayObtainStyledAttributes.getInteger(R$styleable.SegmentedButton_sb_drawableGravity, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    public void f(boolean z) {
        this.v = z;
    }

    public void g(boolean z) {
        this.w = z;
    }

    public int getButtonWidth() {
        return this.N;
    }

    public int getDrawableTint() {
        return this.P;
    }

    public int getDrawableTintOnSelection() {
        return this.J;
    }

    public int getRippleColor() {
        return this.M;
    }

    public int getTextColorOnSelection() {
        return this.K;
    }

    public float getWeight() {
        return this.f0;
    }

    public boolean h() {
        return this.U;
    }

    public final void i(Context context, AttributeSet attributeSet) {
        e(attributeSet);
        k();
        j();
        this.z = new RectF();
        Paint paint = new Paint();
        this.A = paint;
        paint.setColor(-16777216);
        this.A.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.x = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.x.setStrokeWidth(this.k);
        this.x.setColor(this.l);
    }

    public final void j() {
        if (this.m0) {
            this.I = a.d(getContext(), this.O);
        }
        if (this.d0) {
            this.G = new PorterDuffColorFilter(this.P, PorterDuff.Mode.SRC_IN);
        }
        if (this.a0) {
            this.H = new PorterDuffColorFilter(this.J, PorterDuff.Mode.SRC_IN);
        }
    }

    public final void k() {
        if (this.n0) {
            TextPaint textPaint = new TextPaint();
            this.p = textPaint;
            textPaint.setAntiAlias(true);
            this.p.setTextSize(this.g0);
            this.p.setColor(this.L);
            if (this.e0) {
                setTypeface(this.h0);
            } else {
                Typeface typeface = this.k0;
                if (typeface != null) {
                    setTypeface(typeface);
                }
            }
            int iMeasureText = (int) this.p.measureText(this.j0);
            this.q = new StaticLayout(this.j0, this.p, iMeasureText, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            this.r = new StaticLayout(this.j0, this.p, iMeasureText, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        }
    }

    public final void l(int i) {
        if (this.n0) {
            int intrinsicWidth = i - ((((this.m0 && this.l0.isHorizontal()) ? this.I.getIntrinsicWidth() : 0) + getPaddingLeft()) + getPaddingRight());
            if (intrinsicWidth < 0) {
                return;
            }
            this.p.setTextSize(this.g0);
            this.q = new StaticLayout(this.j0, this.p, intrinsicWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            this.r = new StaticLayout(this.j0, this.p, intrinsicWidth, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        }
    }

    public void m(int i, int i2) {
        this.f2007b = i;
        this.f2008c = i2;
    }

    public void n(int i, int i2, int i3, int i4) {
        this.g = i;
        this.f2009d = i2;
        this.f = i3;
        this.e = i4;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.save();
        if (this.o) {
            canvas.translate((-width) * (this.n - 1.0f), 0.0f);
        } else {
            canvas.translate(width * (this.n - 1.0f), 0.0f);
        }
        RectF rectF = this.z;
        float f = (this.v ? this.u : 0) + this.g;
        int i = this.u;
        rectF.set(f, this.f2009d + i, (this.w ? width - i : width) - this.f, (this.B - i) - this.e);
        int i2 = this.f2007b;
        int i3 = this.f2008c;
        if (!this.m) {
            i2 = 0;
            i3 = 0;
        }
        if (this.o && this.n == 1.0f && this.j) {
            i2 = this.h;
            if (this.k > 0) {
                RectF rectF2 = this.z;
                int i4 = this.t;
                canvas.drawRoundRect(rectF2, i4, i4, this.x);
                RectF rectF3 = this.z;
                float f2 = (this.v ? this.u : 0) + this.g + this.k;
                int i5 = this.u;
                rectF3.set(f2, this.f2009d + i5 + r10, ((this.w ? width - i5 : width) - this.f) - r10, ((this.B - i5) - this.e) - r10);
            }
            i3 = i2;
        }
        RectF rectF4 = this.z;
        float f3 = rectF4.left;
        float f4 = rectF4.top;
        LinearGradient linearGradient = new LinearGradient(f3, f4, rectF4.right, f4, new int[]{i2, i3}, (float[]) null, Shader.TileMode.MIRROR);
        this.y = linearGradient;
        this.A.setShader(linearGradient);
        if (!this.o0) {
            RectF rectF5 = this.z;
            int i6 = this.t;
            canvas.drawRoundRect(rectF5, i6, i6, this.A);
        }
        this.o0 = false;
        canvas.restore();
        canvas.save();
        if (this.n0) {
            canvas.translate(0.0f, this.D);
            this.p.setColor(this.L);
            this.q.draw(canvas);
            canvas.restore();
        }
        canvas.save();
        if (this.m0) {
            d(canvas, this.G);
        }
        if (this.o) {
            float f5 = width;
            canvas.clipRect((1.0f - this.n) * f5, 0.0f, f5, height);
        } else {
            canvas.clipRect(0.0f, 0.0f, width * this.n, height);
        }
        canvas.save();
        if (this.n0 && this.T) {
            canvas.translate(0.0f, this.D);
            this.p.setColor(this.K);
            this.r.draw(canvas);
            canvas.restore();
        }
        if (this.m0) {
            d(canvas, this.H);
        }
        canvas.restore();
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        int iMax;
        int paddingBottom;
        int paddingTop;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        this.B = View.MeasureSpec.getSize(i2);
        int intrinsicWidth = this.m0 ? this.I.getIntrinsicWidth() : 0;
        int width = this.n0 ? this.q.getWidth() : 0;
        int paddingTop2 = getPaddingTop() + getPaddingBottom();
        int intrinsicHeight = this.m0 ? this.I.getIntrinsicHeight() : 0;
        int height = this.n0 ? this.q.getHeight() : 0;
        if (mode == Integer.MIN_VALUE) {
            size = (getPaddingLeft() * 2) + (getPaddingRight() * 2) + (this.l0.isHorizontal() ? width + intrinsicWidth + this.S : Math.max(intrinsicWidth, width));
        } else if (mode == 0) {
            size = width + intrinsicWidth;
        } else if (mode == 1073741824 && size > 0) {
            l(size);
        } else {
            size = 0;
        }
        if (this.n0) {
            TextPaint textPaint = this.p;
            String str = this.j0;
            textPaint.getTextBounds(str, 0, str.length(), this.s);
        }
        if (mode2 == Integer.MIN_VALUE) {
            iMax = (this.l0.isHorizontal() ? Math.max(height, intrinsicHeight) : this.S + height + intrinsicHeight) + (getPaddingTop() * 2);
            paddingBottom = getPaddingBottom();
        } else {
            if (mode2 != 0) {
                if (mode2 == 1073741824) {
                    if (this.l0.isHorizontal()) {
                        paddingTop = this.B;
                        int iMax2 = Math.max(height, intrinsicHeight) + getPaddingTop() + getPaddingBottom();
                        paddingTop2 = this.B < iMax2 ? iMax2 : paddingTop;
                    } else {
                        int paddingTop3 = height + intrinsicHeight + getPaddingTop() + getPaddingBottom();
                        int i3 = this.B;
                        if (i3 < paddingTop3) {
                            paddingTop2 = paddingTop3;
                        } else {
                            paddingTop = (i3 + getPaddingTop()) - getPaddingBottom();
                        }
                    }
                }
                a(size, Math.max(paddingTop2, this.i0));
                setMeasuredDimension(size, Math.max(paddingTop2, this.i0));
            }
            iMax = Math.max(height, intrinsicHeight) + (getPaddingTop() * 2);
            paddingBottom = getPaddingBottom();
        }
        paddingTop2 = iMax + (paddingBottom * 2);
        a(size, Math.max(paddingTop2, this.i0));
        setMeasuredDimension(size, Math.max(paddingTop2, this.i0));
    }

    public void setBorderSize(int i) {
        this.u = i;
    }

    public void setCheckEnable(boolean z) {
        this.m = z;
        invalidate();
    }

    public void setDrawable(int i) {
        setDrawable(a.d(getContext(), i));
    }

    public void setDrawableTint(int i) {
        this.P = i;
    }

    public void setFirstSelectorColor(int i) {
        this.h = i;
        Log.i("SegmentedButton", "setFirstSelectorColor: mIndex=" + this.i);
        this.A.setColor(i);
        invalidate();
    }

    public void setGravity(DrawableGravity drawableGravity) {
        this.l0 = drawableGravity;
    }

    public void setIndex(int i) {
        Log.i("SegmentedButton", "setIndex: index=" + i);
        this.i = i;
    }

    public void setSelectorColor(int i) {
        this.A.setColor(i);
    }

    public void setSelectorRadius(int i) {
        this.t = i;
    }

    public void setText(String str) {
        boolean z = !TextUtils.equals(this.j0, str);
        this.j0 = str;
        this.n0 = !TextUtils.isEmpty(str);
        k();
        if (this.n0 && z) {
            getParent().requestLayout();
        }
        invalidate();
    }

    public void setTextColorOnSelection(int i) {
        this.K = i;
        invalidate();
    }

    public void setTextOnSelection(boolean z) {
        this.T = z;
    }

    public void setTextSize(int i) {
        this.g0 = i;
        invalidate();
    }

    public void setUnselectedTextColor(int i) {
        this.L = i;
        invalidate();
    }

    private void setTypeface(String str) {
        if (str == null || str.equals("")) {
            return;
        }
        this.p.setTypeface(Typeface.createFromAsset(getContext().getAssets(), str));
    }

    public void setDrawable(Drawable drawable) {
        this.I = drawable;
        this.m0 = true;
        requestLayout();
    }

    public SegmentedButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2007b = 0;
        this.f2008c = 0;
        this.m = true;
        this.s = new Rect();
        this.C = 0.0f;
        this.D = 0.0f;
        this.E = 0.0f;
        this.F = 0.0f;
        this.o0 = false;
        i(context, attributeSet);
    }

    public SegmentedButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2007b = 0;
        this.f2008c = 0;
        this.m = true;
        this.s = new Rect();
        this.C = 0.0f;
        this.D = 0.0f;
        this.E = 0.0f;
        this.F = 0.0f;
        this.o0 = false;
        i(context, attributeSet);
    }
}
