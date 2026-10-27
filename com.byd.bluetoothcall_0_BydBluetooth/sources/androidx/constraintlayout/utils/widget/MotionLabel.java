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
import android.os.Build;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.constraintlayout.motion.widget.d;
import androidx.constraintlayout.widget.e;

/* JADX INFO: loaded from: classes.dex */
public class MotionLabel extends View implements d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static String f1015b = "MotionLabel";
    private int A;
    private boolean B;
    private float C;
    private float D;
    private float E;
    private Drawable F;
    Matrix G;
    private Bitmap H;
    private BitmapShader I;
    private Matrix J;
    private float K;
    private float L;
    private float M;
    private float N;
    Paint O;
    private int P;
    Rect Q;
    Paint R;
    float S;
    float T;
    float U;
    float V;
    float W;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    TextPaint f1016c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Path f1017d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1018e;
    private int f;
    private boolean g;
    private float h;
    private float i;
    ViewOutlineProvider j;
    RectF k;
    private float l;
    private float m;
    private int n;
    private int o;
    private float p;
    private String q;
    boolean r;
    private Rect s;
    private int t;
    private int u;
    private int v;
    private int w;
    private String x;
    private Layout y;
    private int z;

    class a extends ViewOutlineProvider {
        a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, MotionLabel.this.getWidth(), MotionLabel.this.getHeight(), (Math.min(r3, r4) * MotionLabel.this.h) / 2.0f);
        }
    }

    class b extends ViewOutlineProvider {
        b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, MotionLabel.this.getWidth(), MotionLabel.this.getHeight(), MotionLabel.this.i);
        }
    }

    public MotionLabel(Context context) {
        super(context);
        this.f1016c = new TextPaint();
        this.f1017d = new Path();
        this.f1018e = 65535;
        this.f = 65535;
        this.g = false;
        this.h = 0.0f;
        this.i = Float.NaN;
        this.l = 48.0f;
        this.m = Float.NaN;
        this.p = 0.0f;
        this.q = "Hello World";
        this.r = true;
        this.s = new Rect();
        this.t = 1;
        this.u = 1;
        this.v = 1;
        this.w = 1;
        this.z = 8388659;
        this.A = 0;
        this.B = false;
        this.K = Float.NaN;
        this.L = Float.NaN;
        this.M = 0.0f;
        this.N = 0.0f;
        this.O = new Paint();
        this.P = 0;
        this.T = Float.NaN;
        this.U = Float.NaN;
        this.V = Float.NaN;
        this.W = Float.NaN;
        g(context, null);
    }

    private void d(float f, float f2, float f3, float f4) {
        if (this.J == null) {
            return;
        }
        this.D = f3 - f;
        this.E = f4 - f2;
        l();
    }

    private void g(Context context, AttributeSet attributeSet) {
        i(context, attributeSet);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.E9);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == e.K9) {
                    setText(typedArrayObtainStyledAttributes.getText(index));
                } else if (index == e.L9) {
                    this.x = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == e.P9) {
                    this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, (int) this.m);
                } else if (index == e.F9) {
                    this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, (int) this.l);
                } else if (index == e.H9) {
                    this.n = typedArrayObtainStyledAttributes.getInt(index, this.n);
                } else if (index == e.G9) {
                    this.o = typedArrayObtainStyledAttributes.getInt(index, this.o);
                } else if (index == e.I9) {
                    this.f1018e = typedArrayObtainStyledAttributes.getColor(index, this.f1018e);
                } else if (index == e.N9) {
                    float dimension = typedArrayObtainStyledAttributes.getDimension(index, this.i);
                    this.i = dimension;
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRound(dimension);
                    }
                } else if (index == e.O9) {
                    float f = typedArrayObtainStyledAttributes.getFloat(index, this.h);
                    this.h = f;
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRoundPercent(f);
                    }
                } else if (index == e.J9) {
                    setGravity(typedArrayObtainStyledAttributes.getInt(index, -1));
                } else if (index == e.M9) {
                    this.A = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == e.V9) {
                    this.f = typedArrayObtainStyledAttributes.getInt(index, this.f);
                    this.g = true;
                } else if (index == e.W9) {
                    this.p = typedArrayObtainStyledAttributes.getDimension(index, this.p);
                    this.g = true;
                } else if (index == e.Q9) {
                    this.F = typedArrayObtainStyledAttributes.getDrawable(index);
                    this.g = true;
                } else if (index == e.R9) {
                    this.T = typedArrayObtainStyledAttributes.getFloat(index, this.T);
                } else if (index == e.S9) {
                    this.U = typedArrayObtainStyledAttributes.getFloat(index, this.U);
                } else if (index == e.X9) {
                    this.M = typedArrayObtainStyledAttributes.getFloat(index, this.M);
                } else if (index == e.Y9) {
                    this.N = typedArrayObtainStyledAttributes.getFloat(index, this.N);
                } else if (index == e.T9) {
                    this.W = typedArrayObtainStyledAttributes.getFloat(index, this.W);
                } else if (index == e.U9) {
                    this.V = typedArrayObtainStyledAttributes.getFloat(index, this.V);
                } else if (index == e.aa) {
                    this.K = typedArrayObtainStyledAttributes.getDimension(index, this.K);
                } else if (index == e.ba) {
                    this.L = typedArrayObtainStyledAttributes.getDimension(index, this.L);
                } else if (index == e.Z9) {
                    this.P = typedArrayObtainStyledAttributes.getInt(index, this.P);
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        k();
        j();
    }

    private float getHorizontalOffset() {
        float f = Float.isNaN(this.m) ? 1.0f : this.l / this.m;
        TextPaint textPaint = this.f1016c;
        String str = this.q;
        return (((((Float.isNaN(this.D) ? getMeasuredWidth() : this.D) - getPaddingLeft()) - getPaddingRight()) - (f * textPaint.measureText(str, 0, str.length()))) * (this.M + 1.0f)) / 2.0f;
    }

    private float getVerticalOffset() {
        float f = Float.isNaN(this.m) ? 1.0f : this.l / this.m;
        Paint.FontMetrics fontMetrics = this.f1016c.getFontMetrics();
        float measuredHeight = ((Float.isNaN(this.E) ? getMeasuredHeight() : this.E) - getPaddingTop()) - getPaddingBottom();
        float f2 = fontMetrics.descent;
        float f3 = fontMetrics.ascent;
        return (((measuredHeight - ((f2 - f3) * f)) * (1.0f - this.N)) / 2.0f) - (f * f3);
    }

    private void h(String str, int i, int i2) {
        Typeface typefaceCreate;
        if (str != null) {
            typefaceCreate = Typeface.create(str, i2);
            if (typefaceCreate != null) {
                setTypeface(typefaceCreate);
                return;
            }
        } else {
            typefaceCreate = null;
        }
        if (i == 1) {
            typefaceCreate = Typeface.SANS_SERIF;
        } else if (i == 2) {
            typefaceCreate = Typeface.SERIF;
        } else if (i == 3) {
            typefaceCreate = Typeface.MONOSPACE;
        }
        if (i2 <= 0) {
            this.f1016c.setFakeBoldText(false);
            this.f1016c.setTextSkewX(0.0f);
            setTypeface(typefaceCreate);
        } else {
            Typeface typefaceDefaultFromStyle = typefaceCreate == null ? Typeface.defaultFromStyle(i2) : Typeface.create(typefaceCreate, i2);
            setTypeface(typefaceDefaultFromStyle);
            int i3 = (~(typefaceDefaultFromStyle != null ? typefaceDefaultFromStyle.getStyle() : 0)) & i2;
            this.f1016c.setFakeBoldText((i3 & 1) != 0);
            this.f1016c.setTextSkewX((i3 & 2) != 0 ? -0.25f : 0.0f);
        }
    }

    private void i(Context context, AttributeSet attributeSet) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(b.a.a.y, typedValue, true);
        TextPaint textPaint = this.f1016c;
        int i = typedValue.data;
        this.f1018e = i;
        textPaint.setColor(i);
    }

    private void k() {
        if (this.F != null) {
            this.J = new Matrix();
            int intrinsicWidth = this.F.getIntrinsicWidth();
            int intrinsicHeight = this.F.getIntrinsicHeight();
            if (intrinsicWidth <= 0 && (intrinsicWidth = getWidth()) == 0) {
                intrinsicWidth = Float.isNaN(this.L) ? 128 : (int) this.L;
            }
            if (intrinsicHeight <= 0 && (intrinsicHeight = getHeight()) == 0) {
                intrinsicHeight = Float.isNaN(this.K) ? 128 : (int) this.K;
            }
            if (this.P != 0) {
                intrinsicWidth /= 2;
                intrinsicHeight /= 2;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
            this.H = bitmapCreateBitmap;
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            this.F.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            this.F.setFilterBitmap(true);
            this.F.draw(canvas);
            if (this.P != 0) {
                this.H = e(this.H, 4);
            }
            Bitmap bitmap = this.H;
            Shader.TileMode tileMode = Shader.TileMode.REPEAT;
            this.I = new BitmapShader(bitmap, tileMode, tileMode);
        }
    }

    private void l() {
        float f = Float.isNaN(this.T) ? 0.0f : this.T;
        float f2 = Float.isNaN(this.U) ? 0.0f : this.U;
        float f3 = Float.isNaN(this.V) ? 1.0f : this.V;
        float f4 = Float.isNaN(this.W) ? 0.0f : this.W;
        this.J.reset();
        float width = this.H.getWidth();
        float height = this.H.getHeight();
        float f5 = Float.isNaN(this.L) ? this.D : this.L;
        float f6 = Float.isNaN(this.K) ? this.E : this.K;
        float f7 = f3 * (width * f6 < height * f5 ? f5 / width : f6 / height);
        this.J.postScale(f7, f7);
        float f8 = width * f7;
        float f9 = f5 - f8;
        float f10 = f7 * height;
        float f11 = f6 - f10;
        if (!Float.isNaN(this.K)) {
            f11 = this.K / 2.0f;
        }
        if (!Float.isNaN(this.L)) {
            f9 = this.L / 2.0f;
        }
        this.J.postTranslate((((f * f9) + f5) - f8) * 0.5f, (((f2 * f11) + f6) - f10) * 0.5f);
        this.J.postRotate(f4, f5 / 2.0f, f6 / 2.0f);
        this.I.setLocalMatrix(this.J);
    }

    @Override // androidx.constraintlayout.motion.widget.d
    public void a(float f, float f2, float f3, float f4) {
        int i = (int) (f + 0.5f);
        this.C = f - i;
        int i2 = (int) (f3 + 0.5f);
        int i3 = i2 - i;
        int i4 = (int) (f4 + 0.5f);
        int i5 = (int) (0.5f + f2);
        int i6 = i4 - i5;
        float f5 = f3 - f;
        this.D = f5;
        float f6 = f4 - f2;
        this.E = f6;
        d(f, f2, f3, f4);
        if (getMeasuredHeight() == i6 && getMeasuredWidth() == i3) {
            super.layout(i, i5, i2, i4);
        } else {
            measure(View.MeasureSpec.makeMeasureSpec(i3, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i6, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            super.layout(i, i5, i2, i4);
        }
        if (this.B) {
            if (this.Q == null) {
                this.R = new Paint();
                this.Q = new Rect();
                this.R.set(this.f1016c);
                this.S = this.R.getTextSize();
            }
            this.D = f5;
            this.E = f6;
            Paint paint = this.R;
            String str = this.q;
            paint.getTextBounds(str, 0, str.length(), this.Q);
            float fHeight = this.Q.height() * 1.3f;
            float f7 = (f5 - this.u) - this.t;
            float f8 = (f6 - this.w) - this.v;
            float fWidth = this.Q.width();
            if (fWidth * f8 > fHeight * f7) {
                this.f1016c.setTextSize((this.S * f7) / fWidth);
            } else {
                this.f1016c.setTextSize((this.S * f8) / fHeight);
            }
            if (this.g || !Float.isNaN(this.m)) {
                f(Float.isNaN(this.m) ? 1.0f : this.l / this.m);
            }
        }
    }

    Bitmap e(Bitmap bitmap, int i) {
        System.nanoTime();
        int width = bitmap.getWidth() / 2;
        int height = bitmap.getHeight() / 2;
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        for (int i2 = 0; i2 < i && width >= 32 && height >= 32; i2++) {
            width /= 2;
            height /= 2;
            bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCreateScaledBitmap, width, height, true);
        }
        return bitmapCreateScaledBitmap;
    }

    void f(float f) {
        if (this.g || f != 1.0f) {
            this.f1017d.reset();
            String str = this.q;
            int length = str.length();
            this.f1016c.getTextBounds(str, 0, length, this.s);
            this.f1016c.getTextPath(str, 0, length, 0.0f, 0.0f, this.f1017d);
            if (f != 1.0f) {
                Log.v(f1015b, androidx.constraintlayout.motion.widget.b.a() + " scale " + f);
                Matrix matrix = new Matrix();
                matrix.postScale(f, f);
                this.f1017d.transform(matrix);
            }
            Rect rect = this.s;
            rect.right--;
            rect.left++;
            rect.bottom++;
            rect.top--;
            RectF rectF = new RectF();
            rectF.bottom = getHeight();
            rectF.right = getWidth();
            this.r = false;
        }
    }

    public float getRound() {
        return this.i;
    }

    public float getRoundPercent() {
        return this.h;
    }

    public float getScaleFromTextSize() {
        return this.m;
    }

    public float getTextBackgroundPanX() {
        return this.T;
    }

    public float getTextBackgroundPanY() {
        return this.U;
    }

    public float getTextBackgroundRotate() {
        return this.W;
    }

    public float getTextBackgroundZoom() {
        return this.V;
    }

    public int getTextOutlineColor() {
        return this.f;
    }

    public float getTextPanX() {
        return this.M;
    }

    public float getTextPanY() {
        return this.N;
    }

    public float getTextureHeight() {
        return this.K;
    }

    public float getTextureWidth() {
        return this.L;
    }

    public Typeface getTypeface() {
        return this.f1016c.getTypeface();
    }

    void j() {
        this.t = getPaddingLeft();
        this.u = getPaddingRight();
        this.v = getPaddingTop();
        this.w = getPaddingBottom();
        h(this.x, this.o, this.n);
        this.f1016c.setColor(this.f1018e);
        this.f1016c.setStrokeWidth(this.p);
        this.f1016c.setStyle(Paint.Style.FILL_AND_STROKE);
        this.f1016c.setFlags(128);
        setTextSize(this.l);
        this.f1016c.setAntiAlias(true);
    }

    @Override // android.view.View
    public void layout(int i, int i2, int i3, int i4) {
        super.layout(i, i2, i3, i4);
        boolean zIsNaN = Float.isNaN(this.m);
        float f = zIsNaN ? 1.0f : this.l / this.m;
        this.D = i3 - i;
        this.E = i4 - i2;
        if (this.B) {
            if (this.Q == null) {
                this.R = new Paint();
                this.Q = new Rect();
                this.R.set(this.f1016c);
                this.S = this.R.getTextSize();
            }
            Paint paint = this.R;
            String str = this.q;
            paint.getTextBounds(str, 0, str.length(), this.Q);
            int iWidth = this.Q.width();
            int iHeight = (int) (this.Q.height() * 1.3f);
            float f2 = (this.D - this.u) - this.t;
            float f3 = (this.E - this.w) - this.v;
            if (zIsNaN) {
                float f4 = iWidth;
                float f5 = iHeight;
                if (f4 * f3 > f5 * f2) {
                    this.f1016c.setTextSize((this.S * f2) / f4);
                } else {
                    this.f1016c.setTextSize((this.S * f3) / f5);
                }
            } else {
                float f6 = iWidth;
                float f7 = iHeight;
                f = f6 * f3 > f7 * f2 ? f2 / f6 : f3 / f7;
            }
        }
        if (this.g || !zIsNaN) {
            d(i, i2, i3, i4);
            f(f);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f = Float.isNaN(this.m) ? 1.0f : this.l / this.m;
        super.onDraw(canvas);
        if (!this.g && f == 1.0f) {
            canvas.drawText(this.q, this.C + this.t + getHorizontalOffset(), this.v + getVerticalOffset(), this.f1016c);
            return;
        }
        if (this.r) {
            f(f);
        }
        if (this.G == null) {
            this.G = new Matrix();
        }
        if (!this.g) {
            float horizontalOffset = this.t + getHorizontalOffset();
            float verticalOffset = this.v + getVerticalOffset();
            this.G.reset();
            this.G.preTranslate(horizontalOffset, verticalOffset);
            this.f1017d.transform(this.G);
            this.f1016c.setColor(this.f1018e);
            this.f1016c.setStyle(Paint.Style.FILL_AND_STROKE);
            this.f1016c.setStrokeWidth(this.p);
            canvas.drawPath(this.f1017d, this.f1016c);
            this.G.reset();
            this.G.preTranslate(-horizontalOffset, -verticalOffset);
            this.f1017d.transform(this.G);
            return;
        }
        this.O.set(this.f1016c);
        this.G.reset();
        float horizontalOffset2 = this.t + getHorizontalOffset();
        float verticalOffset2 = this.v + getVerticalOffset();
        this.G.postTranslate(horizontalOffset2, verticalOffset2);
        this.G.preScale(f, f);
        this.f1017d.transform(this.G);
        if (this.I != null) {
            this.f1016c.setFilterBitmap(true);
            this.f1016c.setShader(this.I);
        } else {
            this.f1016c.setColor(this.f1018e);
        }
        this.f1016c.setStyle(Paint.Style.FILL);
        this.f1016c.setStrokeWidth(this.p);
        canvas.drawPath(this.f1017d, this.f1016c);
        if (this.I != null) {
            this.f1016c.setShader(null);
        }
        this.f1016c.setColor(this.f);
        this.f1016c.setStyle(Paint.Style.STROKE);
        this.f1016c.setStrokeWidth(this.p);
        canvas.drawPath(this.f1017d, this.f1016c);
        this.G.reset();
        this.G.postTranslate(-horizontalOffset2, -verticalOffset2);
        this.f1017d.transform(this.G);
        this.f1016c.set(this.O);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        this.B = false;
        this.t = getPaddingLeft();
        this.u = getPaddingRight();
        this.v = getPaddingTop();
        this.w = getPaddingBottom();
        if (mode != 1073741824 || mode2 != 1073741824) {
            TextPaint textPaint = this.f1016c;
            String str = this.q;
            textPaint.getTextBounds(str, 0, str.length(), this.s);
            if (mode != 1073741824) {
                size = (int) (this.s.width() + 0.99999f);
            }
            size += this.t + this.u;
            if (mode2 != 1073741824) {
                int fontMetricsInt = (int) (this.f1016c.getFontMetricsInt(null) + 0.99999f);
                if (mode2 == Integer.MIN_VALUE) {
                    fontMetricsInt = Math.min(size2, fontMetricsInt);
                }
                size2 = this.v + this.w + fontMetricsInt;
            }
        } else if (this.A != 0) {
            this.B = true;
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
        int i2 = i & 8388615;
        int i3 = this.z;
        int i4 = i3 & 8388615;
        if (i != i3) {
            invalidate();
        }
        this.z = i;
        int i5 = i & 112;
        if (i5 == 48) {
            this.N = -1.0f;
        } else if (i5 != 80) {
            this.N = 0.0f;
        } else {
            this.N = 1.0f;
        }
        int i6 = i & 8388615;
        if (i6 != 3) {
            if (i6 != 5) {
                if (i6 != 8388611) {
                    if (i6 != 8388613) {
                        this.M = 0.0f;
                        return;
                    }
                }
            }
            this.M = 1.0f;
            return;
        }
        this.M = -1.0f;
    }

    public void setRound(float f) {
        if (Float.isNaN(f)) {
            this.i = f;
            float f2 = this.h;
            this.h = -1.0f;
            setRoundPercent(f2);
            return;
        }
        boolean z = this.i != f;
        this.i = f;
        if (f != 0.0f) {
            if (this.f1017d == null) {
                this.f1017d = new Path();
            }
            if (this.k == null) {
                this.k = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.j == null) {
                    b bVar = new b();
                    this.j = bVar;
                    setOutlineProvider(bVar);
                }
                setClipToOutline(true);
            }
            this.k.set(0.0f, 0.0f, getWidth(), getHeight());
            this.f1017d.reset();
            Path path = this.f1017d;
            RectF rectF = this.k;
            float f3 = this.i;
            path.addRoundRect(rectF, f3, f3, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setRoundPercent(float f) {
        boolean z = this.h != f;
        this.h = f;
        if (f != 0.0f) {
            if (this.f1017d == null) {
                this.f1017d = new Path();
            }
            if (this.k == null) {
                this.k = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.j == null) {
                    a aVar = new a();
                    this.j = aVar;
                    setOutlineProvider(aVar);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float fMin = (Math.min(width, height) * this.h) / 2.0f;
            this.k.set(0.0f, 0.0f, width, height);
            this.f1017d.reset();
            this.f1017d.addRoundRect(this.k, fMin, fMin, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setScaleFromTextSize(float f) {
        this.m = f;
    }

    public void setText(CharSequence charSequence) {
        this.q = charSequence.toString();
        invalidate();
    }

    public void setTextBackgroundPanX(float f) {
        this.T = f;
        l();
        invalidate();
    }

    public void setTextBackgroundPanY(float f) {
        this.U = f;
        l();
        invalidate();
    }

    public void setTextBackgroundRotate(float f) {
        this.W = f;
        l();
        invalidate();
    }

    public void setTextBackgroundZoom(float f) {
        this.V = f;
        l();
        invalidate();
    }

    public void setTextFillColor(int i) {
        this.f1018e = i;
        invalidate();
    }

    public void setTextOutlineColor(int i) {
        this.f = i;
        this.g = true;
        invalidate();
    }

    public void setTextOutlineThickness(float f) {
        this.p = f;
        this.g = true;
        if (Float.isNaN(f)) {
            this.p = 1.0f;
            this.g = false;
        }
        invalidate();
    }

    public void setTextPanX(float f) {
        this.M = f;
        invalidate();
    }

    public void setTextPanY(float f) {
        this.N = f;
        invalidate();
    }

    public void setTextSize(float f) {
        this.l = f;
        Log.v(f1015b, androidx.constraintlayout.motion.widget.b.a() + "  " + f + " / " + this.m);
        TextPaint textPaint = this.f1016c;
        if (!Float.isNaN(this.m)) {
            f = this.m;
        }
        textPaint.setTextSize(f);
        f(Float.isNaN(this.m) ? 1.0f : this.l / this.m);
        requestLayout();
        invalidate();
    }

    public void setTextureHeight(float f) {
        this.K = f;
        l();
        invalidate();
    }

    public void setTextureWidth(float f) {
        this.L = f;
        l();
        invalidate();
    }

    public void setTypeface(Typeface typeface) {
        if (this.f1016c.getTypeface() != typeface) {
            this.f1016c.setTypeface(typeface);
            if (this.y != null) {
                this.y = null;
                requestLayout();
                invalidate();
            }
        }
    }

    public MotionLabel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1016c = new TextPaint();
        this.f1017d = new Path();
        this.f1018e = 65535;
        this.f = 65535;
        this.g = false;
        this.h = 0.0f;
        this.i = Float.NaN;
        this.l = 48.0f;
        this.m = Float.NaN;
        this.p = 0.0f;
        this.q = "Hello World";
        this.r = true;
        this.s = new Rect();
        this.t = 1;
        this.u = 1;
        this.v = 1;
        this.w = 1;
        this.z = 8388659;
        this.A = 0;
        this.B = false;
        this.K = Float.NaN;
        this.L = Float.NaN;
        this.M = 0.0f;
        this.N = 0.0f;
        this.O = new Paint();
        this.P = 0;
        this.T = Float.NaN;
        this.U = Float.NaN;
        this.V = Float.NaN;
        this.W = Float.NaN;
        g(context, attributeSet);
    }

    public MotionLabel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1016c = new TextPaint();
        this.f1017d = new Path();
        this.f1018e = 65535;
        this.f = 65535;
        this.g = false;
        this.h = 0.0f;
        this.i = Float.NaN;
        this.l = 48.0f;
        this.m = Float.NaN;
        this.p = 0.0f;
        this.q = "Hello World";
        this.r = true;
        this.s = new Rect();
        this.t = 1;
        this.u = 1;
        this.v = 1;
        this.w = 1;
        this.z = 8388659;
        this.A = 0;
        this.B = false;
        this.K = Float.NaN;
        this.L = Float.NaN;
        this.M = 0.0f;
        this.N = 0.0f;
        this.O = new Paint();
        this.P = 0;
        this.T = Float.NaN;
        this.U = Float.NaN;
        this.V = Float.NaN;
        this.W = Float.NaN;
        g(context, attributeSet);
    }
}
