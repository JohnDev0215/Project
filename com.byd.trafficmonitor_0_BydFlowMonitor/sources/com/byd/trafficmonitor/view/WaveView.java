package com.byd.trafficmonitor.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import c.a.d.l;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class WaveView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f4430b = Color.parseColor("#28FFFFFF");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f4431c = Color.parseColor("#3CFFFFFF");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ShapeType f4432d = ShapeType.CIRCLE;
    public int A;
    public int B;
    public ShapeType C;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4433e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f4434f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f4435g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4436h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4437i;
    public String j;
    public String k;
    public boolean l;
    public BitmapShader m;
    public Matrix n;
    public Paint o;
    public Paint p;
    public Paint q;
    public float r;
    public float s;
    public float t;
    public double u;
    public float v;
    public float w;
    public float x;
    public float y;
    public int z;

    public enum ShapeType {
        CIRCLE,
        SQUARE
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f4439a;

        static {
            int[] iArr = new int[ShapeType.values().length];
            f4439a = iArr;
            try {
                iArr[ShapeType.CIRCLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4439a[ShapeType.SQUARE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public WaveView(Context context) {
        super(context);
        this.f4433e = "";
        this.f4434f = "";
        this.v = 0.05f;
        this.w = 1.0f;
        this.x = 0.5f;
        this.y = 0.0f;
        this.z = f4430b;
        this.A = f4431c;
        this.B = 0;
        this.C = f4432d;
        d();
    }

    public final void a() {
        BydLogHelper.logD("createShader");
        this.u = 6.283185307179586d / ((double) getWidth());
        this.r = getHeight() * 0.05f;
        this.s = getHeight() * 0.5f;
        this.t = getWidth();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setStrokeWidth(2.0f);
        paint.setAntiAlias(true);
        int width = getWidth() + 1;
        int height = getHeight() + 1;
        float[] fArr = new float[width];
        paint.setColor(this.z);
        for (int i2 = 0; i2 < width; i2++) {
            float fSin = (float) (((double) this.s) + (((double) this.r) * Math.sin(((double) i2) * this.u)));
            float f2 = i2;
            canvas.drawLine(f2, fSin, f2, height, paint);
            fArr[i2] = fSin;
        }
        paint.setColor(this.A);
        int i3 = (int) (this.t / 4.0f);
        for (int i4 = 0; i4 < width; i4++) {
            float f3 = i4;
            canvas.drawLine(f3, fArr[(i4 + i3) % width], f3, height, paint);
        }
        BitmapShader bitmapShader = new BitmapShader(bitmapCreateBitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        this.m = bitmapShader;
        this.o.setShader(bitmapShader);
    }

    public final void b(Canvas canvas, String[] strArr) {
        float f2;
        float f3;
        canvas.translate(getWidth() / 2, getHeight() / 2);
        this.q.setTextSize(getResources().getDimension(R.dimen.sp_42));
        Paint.FontMetrics fontMetrics = this.q.getFontMetrics();
        float fAbs = Math.abs(fontMetrics.top);
        float fAbs2 = Math.abs(fontMetrics.ascent);
        float f4 = fontMetrics.descent;
        float f5 = fontMetrics.bottom;
        int length = strArr.length;
        float f6 = fAbs + f5;
        float f7 = length * f6;
        float f8 = (length - 1) / 2.0f;
        for (int i2 = 0; i2 < length; i2++) {
            if (!TextUtils.isEmpty(strArr[i2])) {
                Resources.Theme theme = getContext().getTheme();
                if (i2 == 0) {
                    this.q.setTextSize(getResources().getDimension(R.dimen.sp_18));
                    this.q.setColor(getResources().getColor(R.color.wave_text_1_color, theme));
                } else if (i2 == 1) {
                    this.q.setTextSize(getResources().getDimension(R.dimen.sp_42));
                    this.q.setColor(getResources().getColor(this.f4436h ? R.color.wave_text_content_warn_color : R.color.wave_text_content_color, theme));
                } else if (i2 == 2) {
                    this.q.setTextSize(getResources().getDimension(R.dimen.sp_20));
                    this.q.setColor(getResources().getColor(this.f4436h ? R.color.wave_text_content_warn_color : R.color.wave_sub_text_unit_color, theme));
                }
                float fMeasureText = this.q.measureText(strArr[i2]);
                float f9 = i2;
                if (f9 < f8) {
                    f2 = 2.0f;
                    f3 = -(((f7 / 2.0f) - (f9 * f6)) - fAbs);
                } else {
                    f2 = 2.0f;
                    f3 = f9 > f8 ? ((f7 / 2.0f) - (((length - i2) - 1) * f6)) - (3.0f * f5) : (fAbs2 - f4) / 2.0f;
                }
                canvas.drawText(strArr[i2], (-fMeasureText) / f2, f3, this.q);
            }
        }
    }

    public final void c(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        this.q.setTextSize(getResources().getDimension(R.dimen.sp_18));
        Paint.FontMetrics fontMetrics = this.q.getFontMetrics();
        float dimension = getResources().getDimension(R.dimen.dp_10);
        float fAbs = Math.abs(fontMetrics.top);
        float fAbs2 = Math.abs(fontMetrics.ascent);
        float f2 = fontMetrics.descent;
        float f3 = fontMetrics.bottom;
        float f4 = (fAbs + f3) * 3.0f;
        this.q.setTextSize(getResources().getDimension(R.dimen.sp_18));
        this.q.setColor(getResources().getColor(R.color.wave_text_1_color, getContext().getTheme()));
        if (TextUtils.isEmpty(this.j) || TextUtils.isEmpty(this.k)) {
            canvas.drawText("----", (-this.q.measureText("----")) / 2.0f, (fAbs2 - f2) / 2.0f, this.q);
            return;
        }
        float f5 = f4 / 2.0f;
        canvas.drawText(this.j, (-this.q.measureText(this.j)) / 2.0f, (-(f5 - fAbs)) - dimension, this.q);
        canvas.drawText("↓", (-this.q.measureText("↓")) / 2.0f, (fAbs2 - f2) / 2.0f, this.q);
        canvas.drawText(this.k, (-this.q.measureText(this.j)) / 2.0f, (f5 - (f3 * 3.0f)) + dimension, this.q);
    }

    public final void d() {
        this.n = new Matrix();
        Paint paint = new Paint();
        this.o = paint;
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.q = paint2;
        paint2.setAntiAlias(true);
    }

    public final void e(AttributeSet attributeSet) {
        d();
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, l.WaveView, 0, 0);
        this.v = typedArrayObtainStyledAttributes.getFloat(0, 0.05f);
        this.x = typedArrayObtainStyledAttributes.getFloat(7, 0.5f);
        this.w = typedArrayObtainStyledAttributes.getFloat(4, 1.0f);
        this.y = typedArrayObtainStyledAttributes.getFloat(5, 0.0f);
        this.A = typedArrayObtainStyledAttributes.getColor(2, f4431c);
        this.z = typedArrayObtainStyledAttributes.getColor(1, f4430b);
        if (this.B == 0) {
            Log.i("WaveView", "init unitFormat = " + this.B);
            this.B = typedArrayObtainStyledAttributes.getInt(6, 0);
        }
        this.f4437i = typedArrayObtainStyledAttributes.getBoolean(3, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void f(String str, long j, String str2) {
        this.l = false;
        this.f4433e = str;
        this.f4435g = j;
        this.f4434f = str2;
    }

    public void g(int i2, int i3) {
        this.z = i2;
        this.A = i3;
        if (getWidth() <= 0 || getHeight() <= 0) {
            return;
        }
        this.m = null;
        a();
        invalidate();
    }

    public float getAmplitudeRatio() {
        return this.v;
    }

    public float getWaterLevelRatio() {
        return this.x;
    }

    public float getWaveLengthRatio() {
        return this.w;
    }

    public float getWaveShiftRatio() {
        return this.y;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (!this.f4437i || this.m == null) {
            this.o.setShader(null);
            return;
        }
        if (this.o.getShader() == null) {
            this.o.setShader(this.m);
        }
        this.n.setScale(this.w / 1.0f, this.v / 0.05f, 0.0f, this.s);
        this.n.postTranslate(this.y * getWidth(), (0.5f - this.x) * getHeight());
        this.m.setLocalMatrix(this.n);
        Paint paint = this.p;
        float strokeWidth = paint == null ? 0.0f : paint.getStrokeWidth();
        int i2 = a.f4439a[this.C.ordinal()];
        if (i2 == 1) {
            if (strokeWidth > 0.0f) {
                canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, ((getWidth() - strokeWidth) / 2.0f) - 1.0f, this.p);
            }
            canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, (getWidth() / 2.0f) - strokeWidth, this.o);
        } else if (i2 == 2) {
            if (strokeWidth > 0.0f) {
                float f2 = strokeWidth / 2.0f;
                canvas.drawRect(f2, f2, (getWidth() - f2) - 0.5f, (getHeight() - f2) - 0.5f, this.p);
            }
            canvas.drawRect(strokeWidth, strokeWidth, getWidth() - strokeWidth, getHeight() - strokeWidth, this.o);
        }
        String strA = c.a.a.g.a.a(this.B, this.x * this.f4435g);
        if (this.l) {
            c(canvas);
        } else {
            b(canvas, new String[]{this.f4433e, strA, this.f4434f});
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        a();
    }

    public void setAmplitudeRatio(float f2) {
        if (this.v != f2) {
            this.v = f2;
            invalidate();
        }
    }

    public void setShapeType(ShapeType shapeType) {
        this.C = shapeType;
        invalidate();
    }

    public void setShowWave(boolean z) {
        this.f4437i = z;
    }

    public void setUnitFormat(int i2) {
        if (this.B != i2) {
            this.B = i2;
        }
    }

    public void setWaterLevelRatio(float f2) {
        if (this.x != f2) {
            this.x = f2;
            invalidate();
        }
    }

    public void setWaveLengthRatio(float f2) {
        this.w = f2;
    }

    public void setWaveShiftRatio(float f2) {
        if (this.y != f2) {
            this.y = f2;
            invalidate();
        }
    }

    public WaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4433e = "";
        this.f4434f = "";
        this.v = 0.05f;
        this.w = 1.0f;
        this.x = 0.5f;
        this.y = 0.0f;
        this.z = f4430b;
        this.A = f4431c;
        this.B = 0;
        this.C = f4432d;
        e(attributeSet);
    }

    public WaveView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f4433e = "";
        this.f4434f = "";
        this.v = 0.05f;
        this.w = 1.0f;
        this.x = 0.5f;
        this.y = 0.0f;
        this.z = f4430b;
        this.A = f4431c;
        this.B = 0;
        this.C = f4432d;
        e(attributeSet);
    }
}
