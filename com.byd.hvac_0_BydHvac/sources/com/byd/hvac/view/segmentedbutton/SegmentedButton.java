package com.byd.hvac.view.segmentedbutton;

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
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import b.a.a.i.n.b;
import com.byd.hvac.R;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class SegmentedButton extends View {
    public float A;
    public float B;
    public float C;
    public float D;
    public PorterDuffColorFilter E;
    public PorterDuffColorFilter F;
    public Drawable G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public boolean R;
    public boolean S;
    public boolean T;
    public boolean U;
    public boolean V;
    public boolean W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1070a;
    public boolean a0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1071b;
    public boolean b0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1072c;
    public boolean c0;
    public int d;
    public float d0;
    public int e;
    public float e0;
    public int f;
    public String f0;
    public int g;
    public int g0;
    public int h;
    public String h0;
    public boolean i;
    public Typeface i0;
    public int j;
    public DrawableGravity j0;
    public int k;
    public boolean k0;
    public boolean l;
    public boolean l0;
    public float m;
    public boolean m0;
    public boolean n;
    public TextPaint o;
    public StaticLayout p;
    public StaticLayout q;
    public Rect r;
    public int s;
    public int t;
    public boolean u;
    public boolean v;
    public Paint w;
    public RectF x;
    public Paint y;
    public int z;

    /* JADX INFO: compiled from: caradapter-bug */
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
        this.f1070a = 0;
        this.f1071b = 0;
        this.l = true;
        this.r = new Rect();
        this.A = 0.0f;
        this.B = 0.0f;
        this.C = 0.0f;
        this.D = 0.0f;
        this.m0 = false;
        i(context, null);
    }

    public final void a(int i, int i2) {
        float height;
        float width;
        float fWidth;
        float intrinsicHeight;
        float intrinsicWidth;
        if (this.l0) {
            height = this.p.getHeight();
            width = this.p.getWidth();
            fWidth = this.r.width();
        } else {
            height = 0.0f;
            width = 0.0f;
            fWidth = 0.0f;
        }
        if (this.k0) {
            intrinsicHeight = this.G.getIntrinsicHeight();
            intrinsicWidth = this.G.getIntrinsicWidth();
        } else {
            intrinsicHeight = 0.0f;
            intrinsicWidth = 0.0f;
        }
        if (this.j0.isHorizontal()) {
            float f = i2;
            if (f > Math.max(height, intrinsicHeight)) {
                float f2 = f / 2.0f;
                this.B = ((f2 - (height / 2.0f)) + getPaddingTop()) - getPaddingBottom();
                this.D = ((f2 - (intrinsicHeight / 2.0f)) + getPaddingTop()) - getPaddingBottom();
            } else if (height > intrinsicHeight) {
                float paddingTop = getPaddingTop();
                this.B = paddingTop;
                this.D = (paddingTop + (height / 2.0f)) - (intrinsicHeight / 2.0f);
            } else {
                float paddingTop2 = getPaddingTop();
                this.D = paddingTop2;
                this.B = (paddingTop2 + (intrinsicHeight / 2.0f)) - (height / 2.0f);
            }
            this.A = getPaddingLeft();
            this.C = width;
            float f3 = i - (fWidth + intrinsicWidth);
            if (f3 > 0.0f) {
                f3 /= 2.0f;
            }
            DrawableGravity drawableGravity = this.j0;
            if (drawableGravity == DrawableGravity.RIGHT) {
                int i3 = this.Q;
                float paddingLeft = ((f3 + getPaddingLeft()) - getPaddingRight()) - (i3 / 2.0f);
                this.A = paddingLeft;
                this.C = paddingLeft + fWidth + i3;
                return;
            }
            if (drawableGravity == DrawableGravity.LEFT) {
                int i4 = this.Q;
                float paddingLeft2 = ((f3 + getPaddingLeft()) - getPaddingRight()) - (i4 / 2.0f);
                this.C = paddingLeft2;
                this.A = paddingLeft2 + intrinsicWidth + i4;
                return;
            }
            return;
        }
        DrawableGravity drawableGravity2 = this.j0;
        if (drawableGravity2 == DrawableGravity.TOP) {
            float paddingTop3 = getPaddingTop() - getPaddingBottom();
            int i5 = this.Q;
            float f4 = paddingTop3 - (i5 / 2.0f);
            this.D = f4;
            float f5 = (i2 - (height + intrinsicHeight)) / 2.0f;
            if (f5 > 0.0f) {
                this.D = f4 + f5;
            }
            this.B = this.D + intrinsicHeight + i5;
        } else if (drawableGravity2 == DrawableGravity.BOTTOM) {
            float paddingTop4 = getPaddingTop() - getPaddingBottom();
            int i6 = this.Q;
            float f6 = paddingTop4 - (i6 / 2.0f);
            this.B = f6;
            float f7 = i2 - (intrinsicHeight + height);
            if (f7 > 0.0f) {
                this.B = f6 + (f7 / 2.0f);
            }
            this.D = this.B + height + i6;
        }
        float f8 = i;
        if (f8 > Math.max(fWidth, intrinsicWidth)) {
            float f9 = f8 / 2.0f;
            this.A = ((f9 - (fWidth / 2.0f)) + getPaddingLeft()) - getPaddingRight();
            this.C = ((f9 - (intrinsicWidth / 2.0f)) + getPaddingLeft()) - getPaddingRight();
        } else if (fWidth > intrinsicWidth) {
            float paddingLeft3 = getPaddingLeft();
            this.A = paddingLeft3;
            this.C = (paddingLeft3 + (fWidth / 2.0f)) - (intrinsicWidth / 2.0f);
        } else {
            float paddingLeft4 = getPaddingLeft();
            this.C = paddingLeft4;
            this.A = (paddingLeft4 + (intrinsicWidth / 2.0f)) - (fWidth / 2.0f);
        }
    }

    public void b(float f) {
        this.n = false;
        this.m = 1.0f - f;
        invalidate();
    }

    public void c(float f) {
        this.n = true;
        this.m = f;
        invalidate();
    }

    public final void d(Canvas canvas, ColorFilter colorFilter) {
        int intrinsicWidth = this.G.getIntrinsicWidth();
        if (this.W) {
            intrinsicWidth = this.O;
        }
        int intrinsicHeight = this.G.getIntrinsicHeight();
        if (this.a0) {
            intrinsicHeight = this.P;
        }
        this.G.setColorFilter(colorFilter);
        int i = (int) this.C;
        int i2 = (int) this.D;
        this.G.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
        this.G.draw(canvas);
    }

    public final void e(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SegmentedButton);
        this.g0 = (int) typedArrayObtainStyledAttributes.getDimension(13, getContext().getResources().getDimension(R.dimen.dp_40));
        this.H = typedArrayObtainStyledAttributes.getColor(9, -1);
        this.V = typedArrayObtainStyledAttributes.hasValue(9);
        this.I = typedArrayObtainStyledAttributes.getColor(19, -1);
        this.R = typedArrayObtainStyledAttributes.hasValue(19);
        this.K = typedArrayObtainStyledAttributes.getColor(14, 0);
        this.S = typedArrayObtainStyledAttributes.hasValue(14);
        this.h0 = typedArrayObtainStyledAttributes.getString(17);
        this.l0 = typedArrayObtainStyledAttributes.hasValue(17);
        this.e0 = typedArrayObtainStyledAttributes.getDimension(22, b.a(getContext(), 14));
        this.J = typedArrayObtainStyledAttributes.getColor(18, -7829368);
        this.f0 = typedArrayObtainStyledAttributes.getString(25);
        this.c0 = typedArrayObtainStyledAttributes.hasValue(25);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(11, false);
        this.i = z;
        if (z) {
            this.g = typedArrayObtainStyledAttributes.getColor(12, 0);
            this.j = (int) typedArrayObtainStyledAttributes.getDimension(16, 0.0f);
            this.k = typedArrayObtainStyledAttributes.getColor(15, 0);
        }
        int i = typedArrayObtainStyledAttributes.getInt(24, 1);
        if (i == 0) {
            this.i0 = Typeface.MONOSPACE;
        } else if (i == 1) {
            this.i0 = Typeface.DEFAULT;
        } else if (i == 2) {
            this.i0 = Typeface.SANS_SERIF;
        } else if (i == 3) {
            this.i0 = Typeface.SERIF;
        }
        try {
            this.U = typedArrayObtainStyledAttributes.hasValue(2);
            this.d0 = typedArrayObtainStyledAttributes.getFloat(2, 0.0f);
            this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        } catch (Exception unused) {
            this.U = true;
            this.d0 = 1.0f;
        }
        this.T = !this.U && this.L > 0;
        this.M = typedArrayObtainStyledAttributes.getResourceId(3, 0);
        this.N = typedArrayObtainStyledAttributes.getColor(8, -1);
        this.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, -1);
        this.P = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, -1);
        this.Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, 0);
        this.k0 = typedArrayObtainStyledAttributes.hasValue(3);
        this.b0 = typedArrayObtainStyledAttributes.hasValue(8);
        this.W = typedArrayObtainStyledAttributes.hasValue(10);
        this.a0 = typedArrayObtainStyledAttributes.hasValue(5);
        this.j0 = DrawableGravity.getById(typedArrayObtainStyledAttributes.getInteger(4, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    public void f(boolean z) {
        this.u = z;
    }

    public void g(boolean z) {
        this.v = z;
    }

    public int getButtonWidth() {
        return this.L;
    }

    public int getDrawableTint() {
        return this.N;
    }

    public int getDrawableTintOnSelection() {
        return this.H;
    }

    public int getRippleColor() {
        return this.K;
    }

    public int getTextColorOnSelection() {
        return this.I;
    }

    public float getWeight() {
        return this.d0;
    }

    public boolean h() {
        return this.S;
    }

    public final void i(Context context, AttributeSet attributeSet) {
        e(attributeSet);
        this.r.left = getPaddingStart();
        this.r.top = getPaddingTop();
        this.r.right = getPaddingEnd();
        this.r.bottom = getPaddingBottom();
        k();
        j();
        this.x = new RectF();
        Paint paint = new Paint();
        this.y = paint;
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.y.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.w = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.w.setStrokeWidth(this.j);
        this.w.setColor(this.k);
    }

    public final void j() {
        if (this.k0) {
            this.G = ContextCompat.getDrawable(getContext(), this.M);
        }
        if (this.b0) {
            this.E = new PorterDuffColorFilter(this.N, PorterDuff.Mode.SRC_IN);
        }
        if (this.V) {
            this.F = new PorterDuffColorFilter(this.H, PorterDuff.Mode.SRC_IN);
        }
    }

    public final void k() {
        if (this.l0) {
            TextPaint textPaint = new TextPaint();
            this.o = textPaint;
            textPaint.setAntiAlias(true);
            this.o.setTextSize(this.e0);
            this.o.setColor(this.J);
            if (this.c0) {
                setTypeface(this.f0);
            } else {
                Typeface typeface = this.i0;
                if (typeface != null) {
                    setTypeface(typeface);
                }
            }
            int iMeasureText = (int) this.o.measureText(this.h0);
            int minimumWidth = getMinimumWidth();
            if (iMeasureText < minimumWidth) {
                iMeasureText = minimumWidth;
            }
            Rect rect = this.r;
            int i = iMeasureText + rect.left + rect.right;
            this.p = new StaticLayout(this.h0, this.o, i, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            this.q = new StaticLayout(this.h0, this.o, i, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        }
    }

    public final void l(int i) {
        if (this.l0) {
            if (this.k0 && this.j0.isHorizontal()) {
                this.G.getIntrinsicWidth();
            }
            int minimumWidth = getMinimumWidth();
            if (i < minimumWidth) {
                i = minimumWidth;
            }
            if (i < 0) {
                return;
            }
            this.o.setTextSize(this.e0);
            int i2 = i;
            this.p = new StaticLayout(this.h0, this.o, i2, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            this.q = new StaticLayout(this.h0, this.o, i2, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.save();
        if (this.n) {
            canvas.translate((-width) * (this.m - 1.0f), 0.0f);
        } else {
            canvas.translate(width * (this.m - 1.0f), 0.0f);
        }
        RectF rectF = this.x;
        float f = (this.u ? this.t : 0) + this.f;
        int i = this.t;
        rectF.set(f, this.f1072c + i, (this.v ? width - i : width) - this.e, (this.z - i) - this.d);
        int i2 = this.f1070a;
        int i3 = this.f1071b;
        boolean z = this.l;
        if (!z) {
            i2 = 0;
            i3 = 0;
        }
        if (this.n && this.m == 1.0f && this.i) {
            if (z) {
                i2 = this.g;
                i3 = i2;
            }
            if (this.j > 0) {
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(this.j);
                paint.setColor(this.k);
                RectF rectF2 = this.x;
                int i4 = this.s;
                canvas.drawRoundRect(rectF2, i4, i4, paint);
                RectF rectF3 = this.x;
                float f2 = (this.u ? this.t : 0) + this.f + this.j;
                int i5 = this.t;
                rectF3.set(f2, this.f1072c + i5 + r11, ((this.v ? width - i5 : width) - this.e) - r11, ((this.z - i5) - this.d) - r11);
            }
        }
        RectF rectF4 = this.x;
        float f3 = rectF4.left;
        float f4 = rectF4.top;
        this.y.setShader(new LinearGradient(f3, f4, rectF4.right, f4, new int[]{i2, i3}, (float[]) null, Shader.TileMode.MIRROR));
        if (!this.m0) {
            RectF rectF5 = this.x;
            int i6 = this.s;
            canvas.drawRoundRect(rectF5, i6, i6, this.y);
        }
        this.m0 = false;
        canvas.restore();
        canvas.save();
        if (this.l0) {
            canvas.translate(0.0f, this.B);
            this.o.setColor(this.J);
            this.p.draw(canvas);
            canvas.restore();
        }
        canvas.save();
        if (this.k0) {
            d(canvas, this.E);
        }
        if (this.n) {
            float f5 = width;
            canvas.clipRect((1.0f - this.m) * f5, 0.0f, f5, height);
        } else {
            canvas.clipRect(0.0f, 0.0f, width * this.m, height);
        }
        canvas.save();
        if (this.l0 && this.R) {
            canvas.translate(0.0f, this.B);
            this.o.setColor(this.I);
            this.q.draw(canvas);
            canvas.restore();
        }
        if (this.k0) {
            d(canvas, this.F);
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
        this.z = View.MeasureSpec.getSize(i2);
        int intrinsicWidth = this.k0 ? this.G.getIntrinsicWidth() : 0;
        int width = this.l0 ? this.p.getWidth() : 0;
        int paddingTop2 = getPaddingTop() + getPaddingBottom();
        int intrinsicHeight = this.k0 ? this.G.getIntrinsicHeight() : 0;
        int height = this.l0 ? this.p.getHeight() : 0;
        if (mode == Integer.MIN_VALUE) {
            size = (getPaddingLeft() * 2) + (getPaddingRight() * 2) + (this.j0.isHorizontal() ? width + intrinsicWidth + this.Q : Math.max(intrinsicWidth, width));
        } else if (mode == 0) {
            size = width + intrinsicWidth;
        } else if (mode == 1073741824 && size > 0) {
            l(size);
        } else {
            size = 0;
        }
        if (this.l0) {
            TextPaint textPaint = this.o;
            String str = this.h0;
            textPaint.getTextBounds(str, 0, str.length(), this.r);
        }
        if (mode2 == Integer.MIN_VALUE) {
            iMax = (this.j0.isHorizontal() ? Math.max(height, intrinsicHeight) : this.Q + height + intrinsicHeight) + (getPaddingTop() * 2);
            paddingBottom = getPaddingBottom();
        } else {
            if (mode2 != 0) {
                if (mode2 == 1073741824) {
                    if (this.j0.isHorizontal()) {
                        paddingTop = this.z;
                        int iMax2 = Math.max(height, intrinsicHeight) + getPaddingTop() + getPaddingBottom();
                        paddingTop2 = this.z < iMax2 ? iMax2 : paddingTop;
                    } else {
                        int paddingTop3 = height + intrinsicHeight + getPaddingTop() + getPaddingBottom();
                        int i3 = this.z;
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
        this.t = i;
    }

    public void setCheckEnable(boolean z) {
        Log.i("SegmentedButton", "setCheckEnable: enable=" + z);
        this.l = z;
        invalidate();
    }

    public void setDrawable(int i) {
        setDrawable(ContextCompat.getDrawable(getContext(), i));
    }

    public void setDrawableTint(int i) {
        this.N = i;
    }

    public void setFirstSelectorColor(int i) {
        this.g = i;
        Log.i("SegmentedButton", "setFirstSelectorColor: mIndex=" + this.h);
        this.y.setColor(i);
        invalidate();
    }

    public void setGradientColor(int i, int i2) {
        this.f1070a = i;
        this.f1071b = i2;
    }

    public void setGravity(DrawableGravity drawableGravity) {
        this.j0 = drawableGravity;
    }

    public void setIndex(int i) {
        Log.i("SegmentedButton", "setIndex: index=" + i);
        this.h = i;
    }

    public void setRecfPadding(int i, int i2, int i3, int i4) {
        this.f = i;
        this.f1072c = i2;
        this.e = i3;
        this.d = i4;
        Rect rect = this.r;
        rect.left = i;
        rect.top = i2;
        rect.right = i3;
        rect.bottom = i4;
    }

    public void setSelectorColor(int i) {
        this.y.setColor(i);
    }

    public void setSelectorRadius(int i) {
        this.s = i;
    }

    public void setText(String str) {
        this.h0 = str;
        this.l0 = !TextUtils.isEmpty(str);
        k();
        invalidate();
    }

    public void setTextColorOnSelection(int i) {
        this.I = i;
        invalidate();
    }

    public void setTextOnSelection(boolean z) {
        this.R = z;
    }

    public void setTypeface(Typeface typeface) {
        this.o.setTypeface(typeface);
    }

    public void setUnselectedTextColor(int i) {
        this.J = i;
        invalidate();
    }

    public void setDrawable(Drawable drawable) {
        this.G = drawable;
        this.k0 = true;
        requestLayout();
    }

    public void setTypeface(String str) {
        if (str == null || str.equals("")) {
            return;
        }
        this.o.setTypeface(Typeface.createFromAsset(getContext().getAssets(), str));
    }

    public SegmentedButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1070a = 0;
        this.f1071b = 0;
        this.l = true;
        this.r = new Rect();
        this.A = 0.0f;
        this.B = 0.0f;
        this.C = 0.0f;
        this.D = 0.0f;
        this.m0 = false;
        i(context, attributeSet);
    }

    public SegmentedButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1070a = 0;
        this.f1071b = 0;
        this.l = true;
        this.r = new Rect();
        this.A = 0.0f;
        this.B = 0.0f;
        this.C = 0.0f;
        this.D = 0.0f;
        this.m0 = false;
        i(context, attributeSet);
    }
}
