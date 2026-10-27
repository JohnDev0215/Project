package com.byd.base.view.segmentedbutton;

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
import android.view.ViewParent;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import b.a.b.e.c.a;
import b.a.b.e.f.b;
import com.byd.base.R$dimen;
import com.byd.base.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class SegmentedButton extends View implements a {
    public RectF A;
    public Paint B;
    public int C;
    public float D;
    public float E;
    public float F;
    public float G;
    public PorterDuffColorFilter H;
    public PorterDuffColorFilter I;
    public Drawable J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public boolean U;
    public boolean V;
    public boolean W;
    public boolean X;
    public boolean Y;
    public boolean Z;
    public boolean a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f594b;
    public boolean b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f595c;
    public boolean c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f596d;
    public float d0;
    public int e;
    public float e0;
    public int f;
    public String f0;
    public int g;
    public int g0;
    public int h;
    public String h0;
    public int i;
    public Typeface i0;
    public boolean j;
    public DrawableGravity j0;
    public int k;
    public boolean k0;
    public int l;
    public boolean l0;
    public boolean m;
    public boolean m0;
    public int n;
    public float o;
    public boolean p;
    public TextPaint q;
    public StaticLayout r;
    public StaticLayout s;
    public Rect t;
    public int u;
    public int v;
    public boolean w;
    public boolean x;
    public Paint y;
    public LinearGradient z;

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
        this.f594b = 0;
        this.f595c = 0;
        this.m = true;
        this.t = new Rect();
        this.D = 0.0f;
        this.E = 0.0f;
        this.F = 0.0f;
        this.G = 0.0f;
        this.m0 = false;
        i(context, null);
    }

    private void setTypeface(Typeface typeface) {
        this.q.setTypeface(typeface);
    }

    public final void a(int i, int i2) {
        float height;
        float width;
        float fWidth;
        float intrinsicHeight;
        float intrinsicWidth;
        if (this.l0) {
            height = this.r.getHeight();
            width = this.r.getWidth();
            fWidth = this.t.width();
        } else {
            height = 0.0f;
            width = 0.0f;
            fWidth = 0.0f;
        }
        if (this.k0) {
            intrinsicHeight = this.J.getIntrinsicHeight();
            intrinsicWidth = this.J.getIntrinsicWidth();
        } else {
            intrinsicHeight = 0.0f;
            intrinsicWidth = 0.0f;
        }
        if (this.j0.isHorizontal()) {
            float f = i2;
            if (f > Math.max(height, intrinsicHeight)) {
                float f2 = f / 2.0f;
                this.E = ((f2 - (height / 2.0f)) + getPaddingTop()) - getPaddingBottom();
                this.G = ((f2 - (intrinsicHeight / 2.0f)) + getPaddingTop()) - getPaddingBottom();
            } else if (height > intrinsicHeight) {
                float paddingTop = getPaddingTop();
                this.E = paddingTop;
                this.G = (paddingTop + (height / 2.0f)) - (intrinsicHeight / 2.0f);
            } else {
                float paddingTop2 = getPaddingTop();
                this.G = paddingTop2;
                this.E = (paddingTop2 + (intrinsicHeight / 2.0f)) - (height / 2.0f);
            }
            this.D = getPaddingLeft();
            this.F = width;
            float f3 = i - (fWidth + intrinsicWidth);
            if (f3 > 0.0f) {
                f3 /= 2.0f;
            }
            DrawableGravity drawableGravity = this.j0;
            if (drawableGravity == DrawableGravity.RIGHT) {
                int i3 = this.T;
                float paddingLeft = ((f3 + getPaddingLeft()) - getPaddingRight()) - (i3 / 2.0f);
                this.D = paddingLeft;
                this.F = paddingLeft + fWidth + i3;
                return;
            }
            if (drawableGravity == DrawableGravity.LEFT) {
                int i4 = this.T;
                float paddingLeft2 = ((f3 + getPaddingLeft()) - getPaddingRight()) - (i4 / 2.0f);
                this.F = paddingLeft2;
                this.D = paddingLeft2 + intrinsicWidth + i4;
                return;
            }
            return;
        }
        DrawableGravity drawableGravity2 = this.j0;
        if (drawableGravity2 == DrawableGravity.TOP) {
            float paddingTop3 = getPaddingTop() - getPaddingBottom();
            int i5 = this.T;
            float f4 = paddingTop3 - (i5 / 2.0f);
            this.G = f4;
            float f5 = (i2 - (height + intrinsicHeight)) / 2.0f;
            if (f5 > 0.0f) {
                this.G = f4 + f5;
            }
            this.E = this.G + intrinsicHeight + i5;
        } else if (drawableGravity2 == DrawableGravity.BOTTOM) {
            float paddingTop4 = getPaddingTop() - getPaddingBottom();
            int i6 = this.T;
            float f6 = paddingTop4 - (i6 / 2.0f);
            this.E = f6;
            float f7 = i2 - (intrinsicHeight + height);
            if (f7 > 0.0f) {
                this.E = f6 + (f7 / 2.0f);
            }
            this.G = this.E + height + i6;
        }
        float f8 = i;
        if (f8 > Math.max(fWidth, intrinsicWidth)) {
            float f9 = f8 / 2.0f;
            this.D = ((f9 - (fWidth / 2.0f)) + getPaddingLeft()) - getPaddingRight();
            this.F = ((f9 - (intrinsicWidth / 2.0f)) + getPaddingLeft()) - getPaddingRight();
        } else if (fWidth > intrinsicWidth) {
            float paddingLeft3 = getPaddingLeft();
            this.D = paddingLeft3;
            this.F = (paddingLeft3 + (fWidth / 2.0f)) - (intrinsicWidth / 2.0f);
        } else {
            float paddingLeft4 = getPaddingLeft();
            this.F = paddingLeft4;
            this.D = (paddingLeft4 + (intrinsicWidth / 2.0f)) - (fWidth / 2.0f);
        }
    }

    public void b(float f) {
        this.p = false;
        this.o = 1.0f - f;
        invalidate();
    }

    public void c(float f) {
        this.p = true;
        this.o = f;
        invalidate();
    }

    public final void d(Canvas canvas, ColorFilter colorFilter) {
        int intrinsicWidth = this.J.getIntrinsicWidth();
        if (this.Z) {
            intrinsicWidth = this.R;
        }
        int intrinsicHeight = this.J.getIntrinsicHeight();
        if (this.a0) {
            intrinsicHeight = this.S;
        }
        this.J.setColorFilter(colorFilter);
        int i = (int) this.F;
        int i2 = (int) this.G;
        this.J.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
        this.J.draw(canvas);
    }

    public final void e(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SegmentedButton);
        this.g0 = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButton_android_minHeight, getContext().getResources().getDimension(R$dimen.dp_40));
        int i = R$styleable.SegmentedButton_sb_drawableTint_onSelection;
        this.K = typedArrayObtainStyledAttributes.getColor(i, -1);
        this.Y = typedArrayObtainStyledAttributes.hasValue(i);
        int i2 = R$styleable.SegmentedButton_sb_textColor_onSelection;
        this.L = typedArrayObtainStyledAttributes.getColor(i2, -1);
        this.U = typedArrayObtainStyledAttributes.hasValue(i2);
        int i3 = R$styleable.SegmentedButton_sb_rippleColor;
        this.N = typedArrayObtainStyledAttributes.getColor(i3, 0);
        this.V = typedArrayObtainStyledAttributes.hasValue(i3);
        int i4 = R$styleable.SegmentedButton_sb_text;
        this.h0 = typedArrayObtainStyledAttributes.getString(i4);
        this.l0 = typedArrayObtainStyledAttributes.hasValue(i4);
        this.e0 = typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButton_sb_textSize, b.a(getContext(), 14));
        this.M = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButton_sb_textColor, -7829368);
        int i5 = R$styleable.SegmentedButton_sb_textTypefacePath;
        this.f0 = typedArrayObtainStyledAttributes.getString(i5);
        this.c0 = typedArrayObtainStyledAttributes.hasValue(i5);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButton_sb_isfirst, false);
        this.j = z;
        if (z) {
            this.h = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButton_sb_isfirstSelectorColor, 0);
            this.k = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButton_sb_stroke_width, 0.0f);
            this.l = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButton_sb_stroke_color, 0);
        }
        int i6 = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButton_sb_textTypeface, 1);
        if (i6 == 0) {
            this.i0 = Typeface.MONOSPACE;
        } else if (i6 == 1) {
            this.i0 = Typeface.DEFAULT;
        } else if (i6 == 2) {
            this.i0 = Typeface.SANS_SERIF;
        } else if (i6 == 3) {
            this.i0 = Typeface.SERIF;
        }
        try {
            int i7 = R$styleable.SegmentedButton_android_layout_weight;
            this.X = typedArrayObtainStyledAttributes.hasValue(i7);
            this.d0 = typedArrayObtainStyledAttributes.getFloat(i7, 0.0f);
            this.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButton_android_layout_width, 0);
        } catch (Exception unused) {
            this.X = true;
            this.d0 = 1.0f;
        }
        this.W = !this.X && this.O > 0;
        int i8 = R$styleable.SegmentedButton_sb_drawable;
        this.P = typedArrayObtainStyledAttributes.getResourceId(i8, 0);
        int i9 = R$styleable.SegmentedButton_sb_drawableTint;
        this.Q = typedArrayObtainStyledAttributes.getColor(i9, -1);
        int i10 = R$styleable.SegmentedButton_sb_drawableWidth;
        this.R = typedArrayObtainStyledAttributes.getDimensionPixelSize(i10, -1);
        int i11 = R$styleable.SegmentedButton_sb_drawableHeight;
        this.S = typedArrayObtainStyledAttributes.getDimensionPixelSize(i11, -1);
        this.T = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButton_sb_drawablePadding, 0);
        this.k0 = typedArrayObtainStyledAttributes.hasValue(i8);
        this.b0 = typedArrayObtainStyledAttributes.hasValue(i9);
        this.Z = typedArrayObtainStyledAttributes.hasValue(i10);
        this.a0 = typedArrayObtainStyledAttributes.hasValue(i11);
        this.j0 = DrawableGravity.getById(typedArrayObtainStyledAttributes.getInteger(R$styleable.SegmentedButton_sb_drawableGravity, 0));
        this.n = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButton_sb_text_gap, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void f(boolean z) {
        this.w = z;
    }

    public void g(boolean z) {
        this.x = z;
    }

    public int getButtonWidth() {
        return this.O;
    }

    public int getDrawableTint() {
        return this.Q;
    }

    public int getDrawableTintOnSelection() {
        return this.K;
    }

    public int getRippleColor() {
        return this.N;
    }

    public int getTextColorOnSelection() {
        return this.L;
    }

    public float getWeight() {
        return this.d0;
    }

    public boolean h() {
        return this.V;
    }

    public final void i(Context context, AttributeSet attributeSet) {
        e(attributeSet);
        this.t.left = getPaddingStart();
        this.t.top = getPaddingTop();
        this.t.right = getPaddingEnd();
        this.t.bottom = getPaddingBottom();
        b.a.d.b.a.a("SegmentedButton", "----init----mTextBounds: " + this.t);
        k();
        j();
        this.A = new RectF();
        Paint paint = new Paint();
        this.B = paint;
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.B.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.y = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.y.setStrokeWidth(this.k);
        this.y.setColor(this.l);
    }

    public final void j() {
        if (this.k0) {
            this.J = ContextCompat.getDrawable(getContext(), this.P);
        }
        if (this.b0) {
            this.H = new PorterDuffColorFilter(this.Q, PorterDuff.Mode.SRC_IN);
        }
        if (this.Y) {
            this.I = new PorterDuffColorFilter(this.K, PorterDuff.Mode.SRC_IN);
        }
    }

    public final void k() {
        if (this.l0) {
            TextPaint textPaint = new TextPaint();
            this.q = textPaint;
            textPaint.setAntiAlias(true);
            this.q.setTextSize(this.e0);
            this.q.setColor(this.M);
            if (this.c0) {
                setTypeface(this.f0);
            } else {
                Typeface typeface = this.i0;
                if (typeface != null) {
                    setTypeface(typeface);
                }
            }
            int iMeasureText = (int) this.q.measureText(this.h0);
            int minimumWidth = getMinimumWidth();
            if (iMeasureText < minimumWidth) {
                iMeasureText = minimumWidth;
            }
            Rect rect = this.t;
            int i = rect.left + iMeasureText + rect.right;
            b.a.d.b.a.a("SegmentedButton", "----initText----textW: " + iMeasureText + ", minW: " + minimumWidth + ", width: " + i);
            this.r = new StaticLayout(this.h0, this.q, (i - getPaddingStart()) - getPaddingEnd(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            this.s = new StaticLayout(this.h0, this.q, (i - getPaddingStart()) - getPaddingEnd(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        }
    }

    public final void l(int i) {
        if (this.l0) {
            if (this.k0 && this.j0.isHorizontal()) {
                this.J.getIntrinsicWidth();
            }
            int minimumWidth = getMinimumWidth();
            if (i >= minimumWidth) {
                minimumWidth = i;
            }
            if (minimumWidth < 0) {
                return;
            }
            this.q.setTextSize(this.e0);
            this.r = new StaticLayout(this.h0, this.q, (minimumWidth - getPaddingStart()) - getPaddingEnd(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            this.s = new StaticLayout(this.h0, this.q, (minimumWidth - getPaddingStart()) - getPaddingEnd(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.save();
        if (this.p) {
            canvas.translate((-width) * (this.o - 1.0f), 0.0f);
        } else {
            canvas.translate(width * (this.o - 1.0f), 0.0f);
        }
        RectF rectF = this.A;
        float f = (this.w ? this.v : 0) + this.g;
        int i = this.v;
        rectF.set(f, this.f596d + i, (this.x ? width - i : width) - this.f, (this.C - i) - this.e);
        int i2 = this.f594b;
        int i3 = this.f595c;
        if (!this.m) {
            i2 = 0;
            i3 = 0;
        }
        if (this.p && this.o == 1.0f && this.j) {
            i2 = this.h;
            if (this.k > 0) {
                RectF rectF2 = this.A;
                int i4 = this.u;
                canvas.drawRoundRect(rectF2, i4, i4, this.y);
                RectF rectF3 = this.A;
                float f2 = (this.w ? this.v : 0) + this.g + this.k;
                int i5 = this.v;
                rectF3.set(f2, this.f596d + i5 + r10, ((this.x ? width - i5 : width) - this.f) - r10, ((this.C - i5) - this.e) - r10);
            }
            i3 = i2;
        }
        RectF rectF4 = this.A;
        float f3 = rectF4.left;
        float f4 = rectF4.top;
        LinearGradient linearGradient = new LinearGradient(f3, f4, rectF4.right, f4, new int[]{i2, i3}, (float[]) null, Shader.TileMode.MIRROR);
        this.z = linearGradient;
        this.B.setShader(linearGradient);
        if (!this.m0) {
            RectF rectF5 = this.A;
            int i6 = this.u;
            canvas.drawRoundRect(rectF5, i6, i6, this.B);
        }
        this.m0 = false;
        canvas.restore();
        canvas.save();
        if (this.l0) {
            canvas.translate(getPaddingStart(), this.E);
            this.q.setColor(this.M);
            this.r.draw(canvas);
            canvas.restore();
        }
        canvas.save();
        if (this.k0) {
            d(canvas, this.H);
        }
        if (this.p) {
            float f5 = width;
            canvas.clipRect((1.0f - this.o) * f5, 0.0f, f5, height);
        } else {
            canvas.clipRect(0.0f, 0.0f, width * this.o, height);
        }
        canvas.save();
        if (this.l0 && this.U) {
            canvas.translate(getPaddingStart(), this.E);
            this.q.setColor(this.L);
            this.s.draw(canvas);
            canvas.restore();
        }
        if (this.k0) {
            d(canvas, this.I);
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
        this.C = View.MeasureSpec.getSize(i2);
        int intrinsicWidth = this.k0 ? this.J.getIntrinsicWidth() : 0;
        int width = this.l0 ? this.r.getWidth() : 0;
        int paddingTop2 = getPaddingTop() + getPaddingBottom();
        int intrinsicHeight = this.k0 ? this.J.getIntrinsicHeight() : 0;
        int height = this.l0 ? this.r.getHeight() : 0;
        if (mode == Integer.MIN_VALUE) {
            size = (getPaddingLeft() * 2) + (getPaddingRight() * 2) + (this.j0.isHorizontal() ? width + intrinsicWidth + this.T : Math.max(intrinsicWidth, width));
        } else if (mode == 0) {
            size = width + intrinsicWidth;
        } else if (mode == 1073741824 && size > 0) {
            l(size);
        } else {
            size = 0;
        }
        if (this.l0) {
            TextPaint textPaint = this.q;
            String str = this.h0;
            textPaint.getTextBounds(str, 0, str.length(), this.t);
        }
        if (mode2 == Integer.MIN_VALUE) {
            iMax = (this.j0.isHorizontal() ? Math.max(height, intrinsicHeight) : this.T + height + intrinsicHeight) + (getPaddingTop() * 2);
            paddingBottom = getPaddingBottom();
        } else {
            if (mode2 != 0) {
                if (mode2 == 1073741824) {
                    if (this.j0.isHorizontal()) {
                        paddingTop = this.C;
                        int iMax2 = Math.max(height, intrinsicHeight) + getPaddingTop() + getPaddingBottom();
                        paddingTop2 = this.C < iMax2 ? iMax2 : paddingTop;
                    } else {
                        int paddingTop3 = height + intrinsicHeight + getPaddingTop() + getPaddingBottom();
                        int i3 = this.C;
                        if (i3 < paddingTop3) {
                            paddingTop2 = paddingTop3;
                        } else {
                            paddingTop = (i3 + getPaddingTop()) - getPaddingBottom();
                        }
                    }
                }
                a(size, Math.max(paddingTop2, this.g0));
                setMeasuredDimension(size, Math.max(paddingTop2, this.g0));
            }
            iMax = Math.max(height, intrinsicHeight) + (getPaddingTop() * 2);
            paddingBottom = getPaddingBottom();
        }
        paddingTop2 = iMax + (paddingBottom * 2);
        a(size, Math.max(paddingTop2, this.g0));
        setMeasuredDimension(size, Math.max(paddingTop2, this.g0));
    }

    public void setBorderSize(int i) {
        this.v = i;
    }

    public void setCheckEnable(boolean z) {
        this.m = z;
        invalidate();
    }

    public void setDrawable(int i) {
        setDrawable(ContextCompat.getDrawable(getContext(), i));
    }

    public void setDrawableTint(int i) {
        this.Q = i;
    }

    public void setFirstSelectorColor(int i) {
        this.h = i;
        Log.i("SegmentedButton", "setFirstSelectorColor: mIndex=" + this.i);
        this.B.setColor(i);
        invalidate();
    }

    public void setGradientColor(int i, int i2) {
        this.f594b = i;
        this.f595c = i2;
    }

    public void setGravity(DrawableGravity drawableGravity) {
        this.j0 = drawableGravity;
    }

    public void setIndex(int i) {
        Log.i("SegmentedButton", "setIndex: index=" + i);
        this.i = i;
    }

    @Override // b.a.b.e.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z) {
        super.setInvalid(z);
    }

    @Override // b.a.b.e.c.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z, boolean z2) {
        super.setInvalid(z, z2);
    }

    public void setRecfPadding(int i, int i2, int i3, int i4) {
        this.g = i;
        this.f596d = i2;
        this.f = i3;
        this.e = i4;
        b.a.d.b.a.a("SegmentedButton", "----setRecfPadding----left: " + i + ", top: " + i2 + ", right: " + i3 + ", bottom: " + i4);
        Rect rect = this.t;
        rect.left = i;
        rect.top = i2;
        rect.right = i3;
        rect.bottom = i4;
    }

    public void setSelectorColor(int i) {
        this.B.setColor(i);
    }

    public void setSelectorRadius(int i) {
        this.u = i;
    }

    public void setText(String str) {
        boolean z = !TextUtils.equals(this.h0, str);
        this.h0 = str;
        this.l0 = !TextUtils.isEmpty(str);
        k();
        if (this.l0 && z) {
            getParent().requestLayout();
        }
        invalidate();
    }

    public void setTextColorOnSelection(int i) {
        this.L = i;
        invalidate();
    }

    public void setTextOnSelection(boolean z) {
        this.U = z;
    }

    public void setTextSize(int i) {
        this.e0 = i;
        invalidate();
    }

    public void setUnselectedTextColor(int i) {
        this.M = i;
        invalidate();
    }

    public void setViewGone(int i) {
        if (i <= -1) {
            return;
        }
        super.setVisibility(8);
        ViewParent parent = getParent().getParent().getParent();
        if (parent instanceof SegmentedButtonGroup) {
            ((SegmentedButtonGroup) parent).o(i);
        }
    }

    private void setTypeface(String str) {
        if (str == null || str.equals("")) {
            return;
        }
        this.q.setTypeface(Typeface.createFromAsset(getContext().getAssets(), str));
    }

    public void setDrawable(Drawable drawable) {
        this.J = drawable;
        this.k0 = true;
        requestLayout();
    }

    public SegmentedButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f594b = 0;
        this.f595c = 0;
        this.m = true;
        this.t = new Rect();
        this.D = 0.0f;
        this.E = 0.0f;
        this.F = 0.0f;
        this.G = 0.0f;
        this.m0 = false;
        i(context, attributeSet);
    }

    public SegmentedButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f594b = 0;
        this.f595c = 0;
        this.m = true;
        this.t = new Rect();
        this.D = 0.0f;
        this.E = 0.0f;
        this.F = 0.0f;
        this.G = 0.0f;
        this.m0 = false;
        i(context, attributeSet);
    }
}
