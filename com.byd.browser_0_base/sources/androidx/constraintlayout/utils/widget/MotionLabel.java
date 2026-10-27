package androidx.constraintlayout.utils.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.appcompat.R;
import androidx.constraintlayout.widget.R$styleable;
import defpackage.b70;
import defpackage.rn0;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class MotionLabel extends View implements rn0 {
    public int A;
    public int B;
    public boolean C;
    public float D;
    public float E;
    public float F;
    public Drawable G;
    public Matrix H;
    public Bitmap I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public BitmapShader f7J;
    public Matrix K;
    public float L;
    public float M;
    public float N;
    public float O;
    public final Paint P;
    public int Q;
    public Rect R;
    public Paint S;
    public float T;
    public float U;
    public float V;
    public float W;
    public float a0;
    public final TextPaint e;
    public Path f;
    public int g;
    public int h;
    public boolean i;
    public float j;
    public float k;
    public ViewOutlineProvider l;
    public RectF m;
    public float n;
    public float o;
    public int p;
    public int q;
    public float r;
    public String s;
    public boolean t;
    public final Rect u;
    public int v;
    public int w;
    public int x;
    public int y;
    public String z;

    public class a extends ViewOutlineProvider {
        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public final void getOutline(View view, Outline outline) {
            MotionLabel motionLabel = MotionLabel.this;
            outline.setRoundRect(0, 0, motionLabel.getWidth(), motionLabel.getHeight(), (Math.min(r3, r4) * motionLabel.j) / 2.0f);
        }
    }

    public class b extends ViewOutlineProvider {
        public b() {
        }

        @Override // android.view.ViewOutlineProvider
        public final void getOutline(View view, Outline outline) {
            MotionLabel motionLabel = MotionLabel.this;
            outline.setRoundRect(0, 0, motionLabel.getWidth(), motionLabel.getHeight(), motionLabel.k);
        }
    }

    public MotionLabel(Context context) {
        super(context);
        this.e = new TextPaint();
        this.f = new Path();
        this.g = 65535;
        this.h = 65535;
        this.i = false;
        this.j = 0.0f;
        this.k = Float.NaN;
        this.n = 48.0f;
        this.o = Float.NaN;
        this.r = 0.0f;
        this.s = "Hello World";
        this.t = true;
        this.u = new Rect();
        this.v = 1;
        this.w = 1;
        this.x = 1;
        this.y = 1;
        this.A = 8388659;
        this.B = 0;
        this.C = false;
        this.L = Float.NaN;
        this.M = Float.NaN;
        this.N = 0.0f;
        this.O = 0.0f;
        this.P = new Paint();
        this.Q = 0;
        this.U = Float.NaN;
        this.V = Float.NaN;
        this.W = Float.NaN;
        this.a0 = Float.NaN;
        c(context, null);
    }

    private float getHorizontalOffset() {
        float f = Float.isNaN(this.o) ? 1.0f : this.n / this.o;
        String str = this.s;
        return ((this.N + 1.0f) * ((((Float.isNaN(this.E) ? getMeasuredWidth() : this.E) - getPaddingLeft()) - getPaddingRight()) - (this.e.measureText(str, 0, str.length()) * f))) / 2.0f;
    }

    private float getVerticalOffset() {
        float f = Float.isNaN(this.o) ? 1.0f : this.n / this.o;
        Paint.FontMetrics fontMetrics = this.e.getFontMetrics();
        float measuredHeight = ((Float.isNaN(this.F) ? getMeasuredHeight() : this.F) - getPaddingTop()) - getPaddingBottom();
        float f2 = fontMetrics.descent;
        float f3 = fontMetrics.ascent;
        return (((1.0f - this.O) * (measuredHeight - ((f2 - f3) * f))) / 2.0f) - (f * f3);
    }

    private void setUpTheme(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        int i = typedValue.data;
        this.g = i;
        this.e.setColor(i);
    }

    @Override // defpackage.rn0
    public final void a(float f, float f2, float f3, float f4) {
        int i = (int) (f + 0.5f);
        this.D = f - i;
        int i2 = (int) (f3 + 0.5f);
        int i3 = i2 - i;
        int i4 = (int) (f4 + 0.5f);
        int i5 = (int) (0.5f + f2);
        int i6 = i4 - i5;
        float f5 = f3 - f;
        this.E = f5;
        float f6 = f4 - f2;
        this.F = f6;
        if (this.K != null) {
            this.E = f5;
            this.F = f6;
            d();
        }
        if (getMeasuredHeight() == i6 && getMeasuredWidth() == i3) {
            super.layout(i, i5, i2, i4);
        } else {
            measure(View.MeasureSpec.makeMeasureSpec(i3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i6, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            super.layout(i, i5, i2, i4);
        }
        if (this.C) {
            Rect rect = this.R;
            TextPaint textPaint = this.e;
            if (rect == null) {
                this.S = new Paint();
                this.R = new Rect();
                this.S.set(textPaint);
                this.T = this.S.getTextSize();
            }
            this.E = f5;
            this.F = f6;
            Paint paint = this.S;
            String str = this.s;
            paint.getTextBounds(str, 0, str.length(), this.R);
            float fHeight = this.R.height() * 1.3f;
            float f7 = (f5 - this.w) - this.v;
            float f8 = (f6 - this.y) - this.x;
            float fWidth = this.R.width();
            if (fWidth * f8 > fHeight * f7) {
                textPaint.setTextSize((this.T * f7) / fWidth);
            } else {
                textPaint.setTextSize((this.T * f8) / fHeight);
            }
            if (this.i || !Float.isNaN(this.o)) {
                b(Float.isNaN(this.o) ? 1.0f : this.n / this.o);
            }
        }
    }

    public final void b(float f) {
        if (this.i || f != 1.0f) {
            this.f.reset();
            String str = this.s;
            int length = str.length();
            TextPaint textPaint = this.e;
            Rect rect = this.u;
            textPaint.getTextBounds(str, 0, length, rect);
            this.e.getTextPath(str, 0, length, 0.0f, 0.0f, this.f);
            if (f != 1.0f) {
                b70.a();
                Matrix matrix = new Matrix();
                matrix.postScale(f, f);
                this.f.transform(matrix);
            }
            rect.right--;
            rect.left++;
            rect.bottom++;
            rect.top--;
            RectF rectF = new RectF();
            rectF.bottom = getHeight();
            rectF.right = getWidth();
            this.t = false;
        }
    }

    public final void c(Context context, AttributeSet attributeSet) {
        Typeface typefaceCreate;
        setUpTheme(context);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.MotionLabel);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.MotionLabel_android_text) {
                    setText(typedArrayObtainStyledAttributes.getText(index));
                } else if (index == R$styleable.MotionLabel_android_fontFamily) {
                    this.z = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == R$styleable.MotionLabel_scaleFromTextSize) {
                    this.o = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, (int) this.o);
                } else if (index == R$styleable.MotionLabel_android_textSize) {
                    this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, (int) this.n);
                } else if (index == R$styleable.MotionLabel_android_textStyle) {
                    this.p = typedArrayObtainStyledAttributes.getInt(index, this.p);
                } else if (index == R$styleable.MotionLabel_android_typeface) {
                    this.q = typedArrayObtainStyledAttributes.getInt(index, this.q);
                } else if (index == R$styleable.MotionLabel_android_textColor) {
                    this.g = typedArrayObtainStyledAttributes.getColor(index, this.g);
                } else if (index == R$styleable.MotionLabel_borderRound) {
                    float dimension = typedArrayObtainStyledAttributes.getDimension(index, this.k);
                    this.k = dimension;
                    setRound(dimension);
                } else if (index == R$styleable.MotionLabel_borderRoundPercent) {
                    float f = typedArrayObtainStyledAttributes.getFloat(index, this.j);
                    this.j = f;
                    setRoundPercent(f);
                } else if (index == R$styleable.MotionLabel_android_gravity) {
                    setGravity(typedArrayObtainStyledAttributes.getInt(index, -1));
                } else if (index == R$styleable.MotionLabel_android_autoSizeTextType) {
                    this.B = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.MotionLabel_textOutlineColor) {
                    this.h = typedArrayObtainStyledAttributes.getInt(index, this.h);
                    this.i = true;
                } else if (index == R$styleable.MotionLabel_textOutlineThickness) {
                    this.r = typedArrayObtainStyledAttributes.getDimension(index, this.r);
                    this.i = true;
                } else if (index == R$styleable.MotionLabel_textBackground) {
                    this.G = typedArrayObtainStyledAttributes.getDrawable(index);
                    this.i = true;
                } else if (index == R$styleable.MotionLabel_textBackgroundPanX) {
                    this.U = typedArrayObtainStyledAttributes.getFloat(index, this.U);
                } else if (index == R$styleable.MotionLabel_textBackgroundPanY) {
                    this.V = typedArrayObtainStyledAttributes.getFloat(index, this.V);
                } else if (index == R$styleable.MotionLabel_textPanX) {
                    this.N = typedArrayObtainStyledAttributes.getFloat(index, this.N);
                } else if (index == R$styleable.MotionLabel_textPanY) {
                    this.O = typedArrayObtainStyledAttributes.getFloat(index, this.O);
                } else if (index == R$styleable.MotionLabel_textBackgroundRotate) {
                    this.a0 = typedArrayObtainStyledAttributes.getFloat(index, this.a0);
                } else if (index == R$styleable.MotionLabel_textBackgroundZoom) {
                    this.W = typedArrayObtainStyledAttributes.getFloat(index, this.W);
                } else if (index == R$styleable.MotionLabel_textureHeight) {
                    this.L = typedArrayObtainStyledAttributes.getDimension(index, this.L);
                } else if (index == R$styleable.MotionLabel_textureWidth) {
                    this.M = typedArrayObtainStyledAttributes.getDimension(index, this.M);
                } else if (index == R$styleable.MotionLabel_textureEffect) {
                    this.Q = typedArrayObtainStyledAttributes.getInt(index, this.Q);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        if (this.G != null) {
            this.K = new Matrix();
            int intrinsicWidth = this.G.getIntrinsicWidth();
            int intrinsicHeight = this.G.getIntrinsicHeight();
            if (intrinsicWidth <= 0 && (intrinsicWidth = getWidth()) == 0) {
                intrinsicWidth = Float.isNaN(this.M) ? 128 : (int) this.M;
            }
            if (intrinsicHeight <= 0 && (intrinsicHeight = getHeight()) == 0) {
                intrinsicHeight = Float.isNaN(this.L) ? 128 : (int) this.L;
            }
            if (this.Q != 0) {
                intrinsicWidth /= 2;
                intrinsicHeight /= 2;
            }
            this.I = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(this.I);
            this.G.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            this.G.setFilterBitmap(true);
            this.G.draw(canvas);
            if (this.Q != 0) {
                Bitmap bitmap = this.I;
                int width = bitmap.getWidth() / 2;
                int height = bitmap.getHeight() / 2;
                Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
                for (int i2 = 0; i2 < 4 && width >= 32 && height >= 32; i2++) {
                    width /= 2;
                    height /= 2;
                    bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCreateScaledBitmap, width, height, true);
                }
                this.I = bitmapCreateScaledBitmap;
            }
            Bitmap bitmap2 = this.I;
            Shader.TileMode tileMode = Shader.TileMode.REPEAT;
            this.f7J = new BitmapShader(bitmap2, tileMode, tileMode);
        }
        this.v = getPaddingLeft();
        this.w = getPaddingRight();
        this.x = getPaddingTop();
        this.y = getPaddingBottom();
        String str = this.z;
        int i3 = this.q;
        int i4 = this.p;
        TextPaint textPaint = this.e;
        if (str != null) {
            typefaceCreate = Typeface.create(str, i4);
            if (typefaceCreate != null) {
                setTypeface(typefaceCreate);
            }
            textPaint.setColor(this.g);
            textPaint.setStrokeWidth(this.r);
            textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            textPaint.setFlags(128);
            setTextSize(this.n);
            textPaint.setAntiAlias(true);
        }
        typefaceCreate = null;
        if (i3 == 1) {
            typefaceCreate = Typeface.SANS_SERIF;
        } else if (i3 == 2) {
            typefaceCreate = Typeface.SERIF;
        } else if (i3 == 3) {
            typefaceCreate = Typeface.MONOSPACE;
        }
        if (i4 > 0) {
            Typeface typefaceDefaultFromStyle = typefaceCreate == null ? Typeface.defaultFromStyle(i4) : Typeface.create(typefaceCreate, i4);
            setTypeface(typefaceDefaultFromStyle);
            int i5 = (~(typefaceDefaultFromStyle != null ? typefaceDefaultFromStyle.getStyle() : 0)) & i4;
            textPaint.setFakeBoldText((i5 & 1) != 0);
            textPaint.setTextSkewX((i5 & 2) != 0 ? -0.25f : 0.0f);
        } else {
            textPaint.setFakeBoldText(false);
            textPaint.setTextSkewX(0.0f);
            setTypeface(typefaceCreate);
        }
        textPaint.setColor(this.g);
        textPaint.setStrokeWidth(this.r);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        textPaint.setFlags(128);
        setTextSize(this.n);
        textPaint.setAntiAlias(true);
    }

    public final void d() {
        float f = Float.isNaN(this.U) ? 0.0f : this.U;
        float f2 = Float.isNaN(this.V) ? 0.0f : this.V;
        float f3 = Float.isNaN(this.W) ? 1.0f : this.W;
        float f4 = Float.isNaN(this.a0) ? 0.0f : this.a0;
        this.K.reset();
        float width = this.I.getWidth();
        float height = this.I.getHeight();
        float f5 = Float.isNaN(this.M) ? this.E : this.M;
        float f6 = Float.isNaN(this.L) ? this.F : this.L;
        float f7 = f3 * (width * f6 < height * f5 ? f5 / width : f6 / height);
        this.K.postScale(f7, f7);
        float f8 = width * f7;
        float f9 = f5 - f8;
        float f10 = f7 * height;
        float f11 = f6 - f10;
        if (!Float.isNaN(this.L)) {
            f11 = this.L / 2.0f;
        }
        if (!Float.isNaN(this.M)) {
            f9 = this.M / 2.0f;
        }
        this.K.postTranslate((((f * f9) + f5) - f8) * 0.5f, (((f2 * f11) + f6) - f10) * 0.5f);
        this.K.postRotate(f4, f5 / 2.0f, f6 / 2.0f);
        this.f7J.setLocalMatrix(this.K);
    }

    public float getRound() {
        return this.k;
    }

    public float getRoundPercent() {
        return this.j;
    }

    public float getScaleFromTextSize() {
        return this.o;
    }

    public float getTextBackgroundPanX() {
        return this.U;
    }

    public float getTextBackgroundPanY() {
        return this.V;
    }

    public float getTextBackgroundRotate() {
        return this.a0;
    }

    public float getTextBackgroundZoom() {
        return this.W;
    }

    public int getTextOutlineColor() {
        return this.h;
    }

    public float getTextPanX() {
        return this.N;
    }

    public float getTextPanY() {
        return this.O;
    }

    public float getTextureHeight() {
        return this.L;
    }

    public float getTextureWidth() {
        return this.M;
    }

    public Typeface getTypeface() {
        return this.e.getTypeface();
    }

    @Override // android.view.View
    public final void layout(int i, int i2, int i3, int i4) {
        super.layout(i, i2, i3, i4);
        boolean zIsNaN = Float.isNaN(this.o);
        float f = zIsNaN ? 1.0f : this.n / this.o;
        this.E = i3 - i;
        this.F = i4 - i2;
        if (this.C) {
            Rect rect = this.R;
            TextPaint textPaint = this.e;
            if (rect == null) {
                this.S = new Paint();
                this.R = new Rect();
                this.S.set(textPaint);
                this.T = this.S.getTextSize();
            }
            Paint paint = this.S;
            String str = this.s;
            paint.getTextBounds(str, 0, str.length(), this.R);
            int iWidth = this.R.width();
            int iHeight = (int) (this.R.height() * 1.3f);
            float f2 = (this.E - this.w) - this.v;
            float f3 = (this.F - this.y) - this.x;
            if (zIsNaN) {
                float f4 = iWidth;
                float f5 = iHeight;
                if (f4 * f3 > f5 * f2) {
                    textPaint.setTextSize((this.T * f2) / f4);
                } else {
                    textPaint.setTextSize((this.T * f3) / f5);
                }
            } else {
                float f6 = iWidth;
                float f7 = iHeight;
                f = f6 * f3 > f7 * f2 ? f2 / f6 : f3 / f7;
            }
        }
        if (this.i || !zIsNaN) {
            float f8 = i;
            float f9 = i2;
            float f10 = i3;
            float f11 = i4;
            if (this.K != null) {
                this.E = f10 - f8;
                this.F = f11 - f9;
                d();
            }
            b(f);
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        float f = Float.isNaN(this.o) ? 1.0f : this.n / this.o;
        super.onDraw(canvas);
        boolean z = this.i;
        TextPaint textPaint = this.e;
        if (!z && f == 1.0f) {
            canvas.drawText(this.s, this.D + this.v + getHorizontalOffset(), this.x + getVerticalOffset(), textPaint);
            return;
        }
        if (this.t) {
            b(f);
        }
        if (this.H == null) {
            this.H = new Matrix();
        }
        if (!this.i) {
            float horizontalOffset = this.v + getHorizontalOffset();
            float verticalOffset = this.x + getVerticalOffset();
            this.H.reset();
            this.H.preTranslate(horizontalOffset, verticalOffset);
            this.f.transform(this.H);
            textPaint.setColor(this.g);
            textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            textPaint.setStrokeWidth(this.r);
            canvas.drawPath(this.f, textPaint);
            this.H.reset();
            this.H.preTranslate(-horizontalOffset, -verticalOffset);
            this.f.transform(this.H);
            return;
        }
        Paint paint = this.P;
        paint.set(textPaint);
        this.H.reset();
        float horizontalOffset2 = this.v + getHorizontalOffset();
        float verticalOffset2 = this.x + getVerticalOffset();
        this.H.postTranslate(horizontalOffset2, verticalOffset2);
        this.H.preScale(f, f);
        this.f.transform(this.H);
        if (this.f7J != null) {
            textPaint.setFilterBitmap(true);
            textPaint.setShader(this.f7J);
        } else {
            textPaint.setColor(this.g);
        }
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(this.r);
        canvas.drawPath(this.f, textPaint);
        if (this.f7J != null) {
            textPaint.setShader(null);
        }
        textPaint.setColor(this.h);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(this.r);
        canvas.drawPath(this.f, textPaint);
        this.H.reset();
        this.H.postTranslate(-horizontalOffset2, -verticalOffset2);
        this.f.transform(this.H);
        textPaint.set(paint);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        this.C = false;
        this.v = getPaddingLeft();
        this.w = getPaddingRight();
        this.x = getPaddingTop();
        this.y = getPaddingBottom();
        if (mode != 1073741824 || mode2 != 1073741824) {
            String str = this.s;
            int length = str.length();
            this.e.getTextBounds(str, 0, length, this.u);
            if (mode != 1073741824) {
                size = (int) (r7.width() + 0.99999f);
            }
            size += this.v + this.w;
            if (mode2 != 1073741824) {
                int fontMetricsInt = (int) (r6.getFontMetricsInt(null) + 0.99999f);
                if (mode2 == Integer.MIN_VALUE) {
                    fontMetricsInt = Math.min(size2, fontMetricsInt);
                }
                size2 = this.x + this.y + fontMetricsInt;
            }
        } else if (this.B != 0) {
            this.C = true;
        }
        setMeasuredDimension(size, size2);
    }

    @SuppressLint({"RtlHardcoded"})
    public void setGravity(int i) {
        if ((i & 8388615) == 0) {
            i |= 8388611;
        }
        if ((i & 112) == 0) {
            i |= 48;
        }
        if (i != this.A) {
            invalidate();
        }
        this.A = i;
        int i2 = i & 112;
        if (i2 == 48) {
            this.O = -1.0f;
        } else if (i2 != 80) {
            this.O = 0.0f;
        } else {
            this.O = 1.0f;
        }
        int i3 = i & 8388615;
        if (i3 != 3) {
            if (i3 != 5) {
                if (i3 != 8388611) {
                    if (i3 != 8388613) {
                        this.N = 0.0f;
                        return;
                    }
                }
            }
            this.N = 1.0f;
            return;
        }
        this.N = -1.0f;
    }

    public void setRound(float f) {
        if (Float.isNaN(f)) {
            this.k = f;
            float f2 = this.j;
            this.j = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.k != f;
        this.k = f;
        if (f != 0.0f) {
            if (this.f == null) {
                this.f = new Path();
            }
            if (this.m == null) {
                this.m = new RectF();
            }
            if (this.l == null) {
                b bVar = new b();
                this.l = bVar;
                setOutlineProvider(bVar);
            }
            setClipToOutline(true);
            this.m.set(0.0f, 0.0f, getWidth(), getHeight());
            this.f.reset();
            Path path = this.f;
            RectF rectF = this.m;
            float f3 = this.k;
            path.addRoundRect(rectF, f3, f3, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z) {
            invalidateOutline();
        }
    }

    public void setRoundPercent(float f) {
        boolean z = this.j != f;
        this.j = f;
        if (f != 0.0f) {
            if (this.f == null) {
                this.f = new Path();
            }
            if (this.m == null) {
                this.m = new RectF();
            }
            if (this.l == null) {
                a aVar = new a();
                this.l = aVar;
                setOutlineProvider(aVar);
            }
            setClipToOutline(true);
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.j) / 2.0f;
            this.m.set(0.0f, 0.0f, width, height);
            this.f.reset();
            this.f.addRoundRect(this.m, fMin, fMin, Path.Direction.CW);
        } else {
            setClipToOutline(false);
        }
        if (z) {
            invalidateOutline();
        }
    }

    public void setScaleFromTextSize(float f) {
        this.o = f;
    }

    public void setText(CharSequence charSequence) {
        this.s = charSequence.toString();
        invalidate();
    }

    public void setTextBackgroundPanX(float f) {
        this.U = f;
        d();
        invalidate();
    }

    public void setTextBackgroundPanY(float f) {
        this.V = f;
        d();
        invalidate();
    }

    public void setTextBackgroundRotate(float f) {
        this.a0 = f;
        d();
        invalidate();
    }

    public void setTextBackgroundZoom(float f) {
        this.W = f;
        d();
        invalidate();
    }

    public void setTextFillColor(int i) {
        this.g = i;
        invalidate();
    }

    public void setTextOutlineColor(int i) {
        this.h = i;
        this.i = true;
        invalidate();
    }

    public void setTextOutlineThickness(float f) {
        this.r = f;
        this.i = true;
        if (Float.isNaN(f)) {
            this.r = 1.0f;
            this.i = false;
        }
        invalidate();
    }

    public void setTextPanX(float f) {
        this.N = f;
        invalidate();
    }

    public void setTextPanY(float f) {
        this.O = f;
        invalidate();
    }

    public void setTextSize(float f) {
        this.n = f;
        if (!Float.isNaN(this.o)) {
            f = this.o;
        }
        this.e.setTextSize(f);
        b(Float.isNaN(this.o) ? 1.0f : this.n / this.o);
        requestLayout();
        invalidate();
    }

    public void setTextureHeight(float f) {
        this.L = f;
        d();
        invalidate();
    }

    public void setTextureWidth(float f) {
        this.M = f;
        d();
        invalidate();
    }

    public void setTypeface(Typeface typeface) {
        TextPaint textPaint = this.e;
        if (Objects.equals(textPaint.getTypeface(), typeface)) {
            return;
        }
        textPaint.setTypeface(typeface);
    }

    public MotionLabel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new TextPaint();
        this.f = new Path();
        this.g = 65535;
        this.h = 65535;
        this.i = false;
        this.j = 0.0f;
        this.k = Float.NaN;
        this.n = 48.0f;
        this.o = Float.NaN;
        this.r = 0.0f;
        this.s = "Hello World";
        this.t = true;
        this.u = new Rect();
        this.v = 1;
        this.w = 1;
        this.x = 1;
        this.y = 1;
        this.A = 8388659;
        this.B = 0;
        this.C = false;
        this.L = Float.NaN;
        this.M = Float.NaN;
        this.N = 0.0f;
        this.O = 0.0f;
        this.P = new Paint();
        this.Q = 0;
        this.U = Float.NaN;
        this.V = Float.NaN;
        this.W = Float.NaN;
        this.a0 = Float.NaN;
        c(context, attributeSet);
    }

    public MotionLabel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new TextPaint();
        this.f = new Path();
        this.g = 65535;
        this.h = 65535;
        this.i = false;
        this.j = 0.0f;
        this.k = Float.NaN;
        this.n = 48.0f;
        this.o = Float.NaN;
        this.r = 0.0f;
        this.s = "Hello World";
        this.t = true;
        this.u = new Rect();
        this.v = 1;
        this.w = 1;
        this.x = 1;
        this.y = 1;
        this.A = 8388659;
        this.B = 0;
        this.C = false;
        this.L = Float.NaN;
        this.M = Float.NaN;
        this.N = 0.0f;
        this.O = 0.0f;
        this.P = new Paint();
        this.Q = 0;
        this.U = Float.NaN;
        this.V = Float.NaN;
        this.W = Float.NaN;
        this.a0 = Float.NaN;
        c(context, attributeSet);
    }
}
