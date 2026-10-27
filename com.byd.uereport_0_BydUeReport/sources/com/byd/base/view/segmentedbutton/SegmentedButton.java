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
import com.byd.base.R$dimen;
import com.byd.base.R$styleable;
import s2.a;
import t2.b;

/* JADX INFO: loaded from: classes.dex */
public class SegmentedButton extends View implements a {
    public Paint A;
    public LinearGradient B;
    public RectF C;
    public Paint D;
    public int E;
    public float F;
    public float G;
    public float H;
    public float I;
    public PorterDuffColorFilter J;
    public PorterDuffColorFilter K;
    public Drawable L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public boolean W;
    public boolean X;
    public boolean Y;
    public boolean Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public boolean f2610a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public boolean f2611b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public boolean f2612c0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2613d;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public boolean f2614d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2615e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public boolean f2616e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2617f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public float f2618f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2619g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public float f2620g0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2621h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public String f2622h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2623i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public int f2624i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2625j;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public String f2626j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2627k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public Typeface f2628k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f2629l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public DrawableGravity f2630l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2631m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public boolean f2632m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2633n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public boolean f2634n0;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f2635o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public boolean f2636o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f2637p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f2638q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f2639r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public TextPaint f2640s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public StaticLayout f2641t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public StaticLayout f2642u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Rect f2643v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f2644w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f2645x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f2646y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f2647z;

    public enum DrawableGravity {
        LEFT(0),
        TOP(1),
        RIGHT(2),
        BOTTOM(3);

        private int intValue;

        DrawableGravity(int i8) {
            this.intValue = i8;
        }

        public static DrawableGravity getById(int i8) {
            for (DrawableGravity drawableGravity : values()) {
                if (drawableGravity.intValue == i8) {
                    return drawableGravity;
                }
            }
            return null;
        }

        private int getIntValue() {
            return this.intValue;
        }

        public boolean isHorizontal() {
            int i8 = this.intValue;
            return i8 == 0 || i8 == 2;
        }
    }

    public SegmentedButton(Context context) {
        super(context);
        this.f2613d = 0;
        this.f2615e = 0;
        this.f2635o = true;
        this.f2643v = new Rect();
        this.F = 0.0f;
        this.G = 0.0f;
        this.H = 0.0f;
        this.I = 0.0f;
        this.f2636o0 = false;
        i(context, null);
    }

    private void setTypeface(Typeface typeface) {
        this.f2640s.setTypeface(typeface);
    }

    public final void a(int i8, int i9) {
        float height;
        float width;
        float fWidth;
        float intrinsicHeight;
        float intrinsicWidth;
        if (this.f2634n0) {
            height = this.f2641t.getHeight();
            width = this.f2641t.getWidth();
            fWidth = this.f2643v.width();
        } else {
            height = 0.0f;
            width = 0.0f;
            fWidth = 0.0f;
        }
        if (this.f2632m0) {
            intrinsicHeight = this.L.getIntrinsicHeight();
            intrinsicWidth = this.L.getIntrinsicWidth();
        } else {
            intrinsicHeight = 0.0f;
            intrinsicWidth = 0.0f;
        }
        if (this.f2630l0.isHorizontal()) {
            float f8 = i9;
            if (f8 > Math.max(height, intrinsicHeight)) {
                float f9 = f8 / 2.0f;
                this.G = ((f9 - (height / 2.0f)) + getPaddingTop()) - getPaddingBottom();
                this.I = ((f9 - (intrinsicHeight / 2.0f)) + getPaddingTop()) - getPaddingBottom();
            } else if (height > intrinsicHeight) {
                float paddingTop = getPaddingTop();
                this.G = paddingTop;
                this.I = (paddingTop + (height / 2.0f)) - (intrinsicHeight / 2.0f);
            } else {
                float paddingTop2 = getPaddingTop();
                this.I = paddingTop2;
                this.G = (paddingTop2 + (intrinsicHeight / 2.0f)) - (height / 2.0f);
            }
            this.F = getPaddingLeft();
            this.H = width;
            float f10 = i8 - (fWidth + intrinsicWidth);
            if (f10 > 0.0f) {
                f10 /= 2.0f;
            }
            DrawableGravity drawableGravity = this.f2630l0;
            if (drawableGravity == DrawableGravity.RIGHT) {
                int i10 = this.V;
                float paddingLeft = ((f10 + getPaddingLeft()) - getPaddingRight()) - (i10 / 2.0f);
                this.F = paddingLeft;
                this.H = paddingLeft + fWidth + i10;
                return;
            }
            if (drawableGravity == DrawableGravity.LEFT) {
                int i11 = this.V;
                float paddingLeft2 = ((f10 + getPaddingLeft()) - getPaddingRight()) - (i11 / 2.0f);
                this.H = paddingLeft2;
                this.F = paddingLeft2 + intrinsicWidth + i11;
                return;
            }
            return;
        }
        DrawableGravity drawableGravity2 = this.f2630l0;
        if (drawableGravity2 == DrawableGravity.TOP) {
            float paddingTop3 = getPaddingTop() - getPaddingBottom();
            int i12 = this.V;
            float f11 = paddingTop3 - (i12 / 2.0f);
            this.I = f11;
            float f12 = (i9 - (height + intrinsicHeight)) / 2.0f;
            if (f12 > 0.0f) {
                this.I = f11 + f12;
            }
            this.G = this.I + intrinsicHeight + i12;
        } else if (drawableGravity2 == DrawableGravity.BOTTOM) {
            float paddingTop4 = getPaddingTop() - getPaddingBottom();
            int i13 = this.V;
            float f13 = paddingTop4 - (i13 / 2.0f);
            this.G = f13;
            float f14 = i9 - (intrinsicHeight + height);
            if (f14 > 0.0f) {
                this.G = f13 + (f14 / 2.0f);
            }
            this.I = this.G + height + i13;
        }
        float f15 = i8;
        if (f15 > Math.max(fWidth, intrinsicWidth)) {
            float f16 = f15 / 2.0f;
            this.F = ((f16 - (fWidth / 2.0f)) + getPaddingLeft()) - getPaddingRight();
            this.H = ((f16 - (intrinsicWidth / 2.0f)) + getPaddingLeft()) - getPaddingRight();
        } else if (fWidth > intrinsicWidth) {
            float paddingLeft3 = getPaddingLeft();
            this.F = paddingLeft3;
            this.H = (paddingLeft3 + (fWidth / 2.0f)) - (intrinsicWidth / 2.0f);
        } else {
            float paddingLeft4 = getPaddingLeft();
            this.H = paddingLeft4;
            this.F = (paddingLeft4 + (intrinsicWidth / 2.0f)) - (fWidth / 2.0f);
        }
    }

    public void b(float f8) {
        this.f2639r = false;
        this.f2638q = 1.0f - f8;
        invalidate();
    }

    public void c(float f8) {
        this.f2639r = true;
        this.f2638q = f8;
        invalidate();
    }

    public final void d(Canvas canvas, ColorFilter colorFilter) {
        int intrinsicWidth = this.L.getIntrinsicWidth();
        if (this.f2611b0) {
            intrinsicWidth = this.T;
        }
        int intrinsicHeight = this.L.getIntrinsicHeight();
        if (this.f2612c0) {
            intrinsicHeight = this.U;
        }
        this.L.setColorFilter(colorFilter);
        int i8 = (int) this.H;
        int i9 = (int) this.I;
        this.L.setBounds(i8, i9, intrinsicWidth + i8, intrinsicHeight + i9);
        this.L.draw(canvas);
    }

    public final void e(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.SegmentedButton);
        this.f2624i0 = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButton_android_minHeight, getContext().getResources().getDimension(R$dimen.dp_40));
        int i8 = R$styleable.SegmentedButton_sb_drawableTint_onSelection;
        this.M = typedArrayObtainStyledAttributes.getColor(i8, -1);
        this.f2610a0 = typedArrayObtainStyledAttributes.hasValue(i8);
        int i9 = R$styleable.SegmentedButton_sb_textColor_onSelection;
        this.N = typedArrayObtainStyledAttributes.getColor(i9, -1);
        this.W = typedArrayObtainStyledAttributes.hasValue(i9);
        int i10 = R$styleable.SegmentedButton_sb_rippleColor;
        this.P = typedArrayObtainStyledAttributes.getColor(i10, 0);
        this.X = typedArrayObtainStyledAttributes.hasValue(i10);
        int i11 = R$styleable.SegmentedButton_sb_text;
        this.f2626j0 = typedArrayObtainStyledAttributes.getString(i11);
        this.f2634n0 = typedArrayObtainStyledAttributes.hasValue(i11);
        this.f2620g0 = typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButton_sb_textSize, b.a(getContext(), 14));
        this.O = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButton_sb_textColor, -7829368);
        int i12 = R$styleable.SegmentedButton_sb_textTypefacePath;
        this.f2622h0 = typedArrayObtainStyledAttributes.getString(i12);
        this.f2616e0 = typedArrayObtainStyledAttributes.hasValue(i12);
        boolean z7 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.SegmentedButton_sb_isfirst, false);
        this.f2629l = z7;
        if (z7) {
            this.f2625j = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButton_sb_isfirstSelectorColor, 0);
            this.f2631m = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButton_sb_stroke_width, 0.0f);
            this.f2633n = typedArrayObtainStyledAttributes.getColor(R$styleable.SegmentedButton_sb_stroke_color, 0);
        }
        int i13 = typedArrayObtainStyledAttributes.getInt(R$styleable.SegmentedButton_sb_textTypeface, 1);
        if (i13 == 0) {
            this.f2628k0 = Typeface.MONOSPACE;
        } else if (i13 == 1) {
            this.f2628k0 = Typeface.DEFAULT;
        } else if (i13 == 2) {
            this.f2628k0 = Typeface.SANS_SERIF;
        } else if (i13 == 3) {
            this.f2628k0 = Typeface.SERIF;
        }
        try {
            int i14 = R$styleable.SegmentedButton_android_layout_weight;
            this.Z = typedArrayObtainStyledAttributes.hasValue(i14);
            this.f2618f0 = typedArrayObtainStyledAttributes.getFloat(i14, 0.0f);
            this.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButton_android_layout_width, 0);
        } catch (Exception unused) {
            this.Z = true;
            this.f2618f0 = 1.0f;
        }
        this.Y = !this.Z && this.Q > 0;
        int i15 = R$styleable.SegmentedButton_sb_drawable;
        this.R = typedArrayObtainStyledAttributes.getResourceId(i15, 0);
        int i16 = R$styleable.SegmentedButton_sb_drawableTint;
        this.S = typedArrayObtainStyledAttributes.getColor(i16, -1);
        int i17 = R$styleable.SegmentedButton_sb_drawableWidth;
        this.T = typedArrayObtainStyledAttributes.getDimensionPixelSize(i17, -1);
        int i18 = R$styleable.SegmentedButton_sb_drawableHeight;
        this.U = typedArrayObtainStyledAttributes.getDimensionPixelSize(i18, -1);
        this.V = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SegmentedButton_sb_drawablePadding, 0);
        this.f2632m0 = typedArrayObtainStyledAttributes.hasValue(i15);
        this.f2614d0 = typedArrayObtainStyledAttributes.hasValue(i16);
        this.f2611b0 = typedArrayObtainStyledAttributes.hasValue(i17);
        this.f2612c0 = typedArrayObtainStyledAttributes.hasValue(i18);
        this.f2630l0 = DrawableGravity.getById(typedArrayObtainStyledAttributes.getInteger(R$styleable.SegmentedButton_sb_drawableGravity, 0));
        this.f2637p = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.SegmentedButton_sb_text_gap, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void f(boolean z7) {
        this.f2646y = z7;
    }

    public void g(boolean z7) {
        this.f2647z = z7;
    }

    public int getButtonWidth() {
        return this.Q;
    }

    public int getDrawableTint() {
        return this.S;
    }

    public int getDrawableTintOnSelection() {
        return this.M;
    }

    public int getRippleColor() {
        return this.P;
    }

    public int getTextColorOnSelection() {
        return this.N;
    }

    public float getWeight() {
        return this.f2618f0;
    }

    public boolean h() {
        return this.X;
    }

    public final void i(Context context, AttributeSet attributeSet) {
        e(attributeSet);
        this.f2643v.left = getPaddingStart();
        this.f2643v.top = getPaddingTop();
        this.f2643v.right = getPaddingEnd();
        this.f2643v.bottom = getPaddingBottom();
        x3.a.a("SegmentedButton", "----init----mTextBounds: " + this.f2643v);
        k();
        j();
        this.C = new RectF();
        Paint paint = new Paint();
        this.D = paint;
        paint.setColor(-16777216);
        this.D.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.A = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.A.setStrokeWidth(this.f2631m);
        this.A.setColor(this.f2633n);
    }

    public final void j() {
        if (this.f2632m0) {
            this.L = e0.a.d(getContext(), this.R);
        }
        if (this.f2614d0) {
            this.J = new PorterDuffColorFilter(this.S, PorterDuff.Mode.SRC_IN);
        }
        if (this.f2610a0) {
            this.K = new PorterDuffColorFilter(this.M, PorterDuff.Mode.SRC_IN);
        }
    }

    public final void k() {
        if (this.f2634n0) {
            TextPaint textPaint = new TextPaint();
            this.f2640s = textPaint;
            textPaint.setAntiAlias(true);
            this.f2640s.setTextSize(this.f2620g0);
            this.f2640s.setColor(this.O);
            if (this.f2616e0) {
                setTypeface(this.f2622h0);
            } else {
                Typeface typeface = this.f2628k0;
                if (typeface != null) {
                    setTypeface(typeface);
                }
            }
            int iMeasureText = (int) this.f2640s.measureText(this.f2626j0);
            int minimumWidth = getMinimumWidth();
            if (iMeasureText < minimumWidth) {
                iMeasureText = minimumWidth;
            }
            Rect rect = this.f2643v;
            int i8 = rect.right + rect.left + iMeasureText;
            x3.a.a("SegmentedButton", "----initText----textW: " + iMeasureText + ", minW: " + minimumWidth + ", width: " + i8);
            this.f2641t = new StaticLayout(this.f2626j0, this.f2640s, i8, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            this.f2642u = new StaticLayout(this.f2626j0, this.f2640s, i8, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        }
    }

    public final void l(int i8) {
        if (this.f2634n0) {
            if (this.f2632m0 && this.f2630l0.isHorizontal()) {
                this.L.getIntrinsicWidth();
            }
            int minimumWidth = getMinimumWidth();
            if (i8 < minimumWidth) {
                i8 = minimumWidth;
            }
            if (i8 < 0) {
                return;
            }
            this.f2640s.setTextSize(this.f2620g0);
            int i9 = i8;
            this.f2641t = new StaticLayout(this.f2626j0, this.f2640s, i9, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            this.f2642u = new StaticLayout(this.f2626j0, this.f2640s, i9, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        }
    }

    public void m(int i8, int i9) {
        this.f2613d = i8;
        this.f2615e = i9;
    }

    public void n(int i8, int i9, int i10, int i11) {
        this.f2623i = i8;
        this.f2617f = i9;
        this.f2621h = i10;
        this.f2619g = i11;
        x3.a.a("SegmentedButton", "----setRecfPadding----left: " + i8 + ", top: " + i9 + ", right: " + i10 + ", bottom: " + i11);
        Rect rect = this.f2643v;
        rect.left = i8;
        rect.top = i9;
        rect.right = i10;
        rect.bottom = i11;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.save();
        if (this.f2639r) {
            canvas.translate((-width) * (this.f2638q - 1.0f), 0.0f);
        } else {
            canvas.translate(width * (this.f2638q - 1.0f), 0.0f);
        }
        RectF rectF = this.C;
        float f8 = (this.f2646y ? this.f2645x : 0) + this.f2623i;
        int i8 = this.f2645x;
        rectF.set(f8, this.f2617f + i8, (this.f2647z ? width - i8 : width) - this.f2621h, (this.E - i8) - this.f2619g);
        int i9 = this.f2613d;
        int i10 = this.f2615e;
        if (!this.f2635o) {
            i9 = 0;
            i10 = 0;
        }
        if (this.f2639r && this.f2638q == 1.0f && this.f2629l) {
            i9 = this.f2625j;
            if (this.f2631m > 0) {
                RectF rectF2 = this.C;
                int i11 = this.f2644w;
                canvas.drawRoundRect(rectF2, i11, i11, this.A);
                RectF rectF3 = this.C;
                float f9 = (this.f2646y ? this.f2645x : 0) + this.f2623i + this.f2631m;
                int i12 = this.f2645x;
                rectF3.set(f9, this.f2617f + i12 + r10, ((this.f2647z ? width - i12 : width) - this.f2621h) - r10, ((this.E - i12) - this.f2619g) - r10);
            }
            i10 = i9;
        }
        RectF rectF4 = this.C;
        float f10 = rectF4.left;
        float f11 = rectF4.top;
        LinearGradient linearGradient = new LinearGradient(f10, f11, rectF4.right, f11, new int[]{i9, i10}, (float[]) null, Shader.TileMode.MIRROR);
        this.B = linearGradient;
        this.D.setShader(linearGradient);
        if (!this.f2636o0) {
            RectF rectF5 = this.C;
            int i13 = this.f2644w;
            canvas.drawRoundRect(rectF5, i13, i13, this.D);
        }
        this.f2636o0 = false;
        canvas.restore();
        canvas.save();
        if (this.f2634n0) {
            canvas.translate(0.0f, this.G);
            this.f2640s.setColor(this.O);
            this.f2641t.draw(canvas);
            canvas.restore();
        }
        canvas.save();
        if (this.f2632m0) {
            d(canvas, this.J);
        }
        if (this.f2639r) {
            float f12 = width;
            canvas.clipRect((1.0f - this.f2638q) * f12, 0.0f, f12, height);
        } else {
            canvas.clipRect(0.0f, 0.0f, width * this.f2638q, height);
        }
        canvas.save();
        if (this.f2634n0 && this.W) {
            canvas.translate(0.0f, this.G);
            this.f2640s.setColor(this.N);
            this.f2642u.draw(canvas);
            canvas.restore();
        }
        if (this.f2632m0) {
            d(canvas, this.K);
        }
        canvas.restore();
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        int iMax;
        int paddingBottom;
        int paddingTop;
        int mode = View.MeasureSpec.getMode(i8);
        int size = View.MeasureSpec.getSize(i8);
        int mode2 = View.MeasureSpec.getMode(i9);
        this.E = View.MeasureSpec.getSize(i9);
        int intrinsicWidth = this.f2632m0 ? this.L.getIntrinsicWidth() : 0;
        int width = this.f2634n0 ? this.f2641t.getWidth() : 0;
        int paddingTop2 = getPaddingTop() + getPaddingBottom();
        int intrinsicHeight = this.f2632m0 ? this.L.getIntrinsicHeight() : 0;
        int height = this.f2634n0 ? this.f2641t.getHeight() : 0;
        if (mode == Integer.MIN_VALUE) {
            size = (getPaddingLeft() * 2) + (getPaddingRight() * 2) + (this.f2630l0.isHorizontal() ? width + intrinsicWidth + this.V : Math.max(intrinsicWidth, width));
        } else if (mode == 0) {
            size = width + intrinsicWidth;
        } else if (mode == 1073741824 && size > 0) {
            l(size);
        } else {
            size = 0;
        }
        if (this.f2634n0) {
            TextPaint textPaint = this.f2640s;
            String str = this.f2626j0;
            textPaint.getTextBounds(str, 0, str.length(), this.f2643v);
        }
        if (mode2 == Integer.MIN_VALUE) {
            iMax = (this.f2630l0.isHorizontal() ? Math.max(height, intrinsicHeight) : this.V + height + intrinsicHeight) + (getPaddingTop() * 2);
            paddingBottom = getPaddingBottom();
        } else {
            if (mode2 != 0) {
                if (mode2 == 1073741824) {
                    if (this.f2630l0.isHorizontal()) {
                        paddingTop = this.E;
                        int iMax2 = Math.max(height, intrinsicHeight) + getPaddingTop() + getPaddingBottom();
                        paddingTop2 = this.E < iMax2 ? iMax2 : paddingTop;
                    } else {
                        int paddingTop3 = height + intrinsicHeight + getPaddingTop() + getPaddingBottom();
                        int i10 = this.E;
                        if (i10 < paddingTop3) {
                            paddingTop2 = paddingTop3;
                        } else {
                            paddingTop = (i10 + getPaddingTop()) - getPaddingBottom();
                        }
                    }
                }
                a(size, Math.max(paddingTop2, this.f2624i0));
                setMeasuredDimension(size, Math.max(paddingTop2, this.f2624i0));
            }
            iMax = Math.max(height, intrinsicHeight) + (getPaddingTop() * 2);
            paddingBottom = getPaddingBottom();
        }
        paddingTop2 = iMax + (paddingBottom * 2);
        a(size, Math.max(paddingTop2, this.f2624i0));
        setMeasuredDimension(size, Math.max(paddingTop2, this.f2624i0));
    }

    public void setBorderSize(int i8) {
        this.f2645x = i8;
    }

    public void setCheckEnable(boolean z7) {
        this.f2635o = z7;
        invalidate();
    }

    public void setDrawable(int i8) {
        setDrawable(e0.a.d(getContext(), i8));
    }

    public void setDrawableTint(int i8) {
        this.S = i8;
    }

    public void setFirstSelectorColor(int i8) {
        this.f2625j = i8;
        Log.i("SegmentedButton", "setFirstSelectorColor: mIndex=" + this.f2627k);
        this.D.setColor(i8);
        invalidate();
    }

    public void setGravity(DrawableGravity drawableGravity) {
        this.f2630l0 = drawableGravity;
    }

    public void setIndex(int i8) {
        Log.i("SegmentedButton", "setIndex: index=" + i8);
        this.f2627k = i8;
    }

    @Override // s2.a
    public /* bridge */ /* synthetic */ void setInvalid(boolean z7) {
        super.setInvalid(z7);
    }

    public void setSelectorColor(int i8) {
        this.D.setColor(i8);
    }

    public void setSelectorRadius(int i8) {
        this.f2644w = i8;
    }

    public void setText(String str) {
        boolean z7 = !TextUtils.equals(this.f2626j0, str);
        this.f2626j0 = str;
        this.f2634n0 = !TextUtils.isEmpty(str);
        k();
        if (this.f2634n0 && z7) {
            getParent().requestLayout();
        }
        invalidate();
    }

    public void setTextColorOnSelection(int i8) {
        this.N = i8;
        invalidate();
    }

    public void setTextOnSelection(boolean z7) {
        this.W = z7;
    }

    public void setTextSize(int i8) {
        this.f2620g0 = i8;
        invalidate();
    }

    public void setUnselectedTextColor(int i8) {
        this.O = i8;
        invalidate();
    }

    public void setViewGone(int i8) {
        if (i8 <= -1) {
            return;
        }
        super.setVisibility(8);
        ViewParent parent = getParent().getParent().getParent();
        if (parent instanceof SegmentedButtonGroup) {
            ((SegmentedButtonGroup) parent).o(i8);
        }
    }

    private void setTypeface(String str) {
        if (str == null || str.equals("")) {
            return;
        }
        this.f2640s.setTypeface(Typeface.createFromAsset(getContext().getAssets(), str));
    }

    public void setDrawable(Drawable drawable) {
        this.L = drawable;
        this.f2632m0 = true;
        requestLayout();
    }

    public SegmentedButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2613d = 0;
        this.f2615e = 0;
        this.f2635o = true;
        this.f2643v = new Rect();
        this.F = 0.0f;
        this.G = 0.0f;
        this.H = 0.0f;
        this.I = 0.0f;
        this.f2636o0 = false;
        i(context, attributeSet);
    }

    public SegmentedButton(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2613d = 0;
        this.f2615e = 0;
        this.f2635o = true;
        this.f2643v = new Rect();
        this.F = 0.0f;
        this.G = 0.0f;
        this.H = 0.0f;
        this.I = 0.0f;
        this.f2636o0 = false;
        i(context, attributeSet);
    }
}
