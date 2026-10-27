package com.byd.hvac.view.verticalseekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.byd.hvac.R;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class TemperatureVerticalSeekBar extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1093a = TemperatureVerticalSeekBar.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1094b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f1095c;
    public float d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public float j;
    public float k;
    public float l;
    public float m;
    public float n;
    public float o;
    public Paint p;
    public a q;
    public int r;
    public int s;
    public int t;
    public int u;
    public Bitmap v;
    public Bitmap w;
    public boolean x;
    public int[] y;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        void F(View view, int i);

        void M(View view, int i);

        void r0(View view, int i);
    }

    public TemperatureVerticalSeekBar(Context context) {
        this(context, null);
    }

    public static Bitmap a(Bitmap bitmap, int i) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        int i2 = 0;
        for (int i3 = 0; i3 < height; i3++) {
            for (int i4 = 0; i4 < width; i4++) {
                if (Color.alpha(bitmap.getPixel(i4, i3)) != 0) {
                    iArr[i2] = i;
                }
                i2++;
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }

    public final void b(Canvas canvas) {
        int i = (this.s - this.t) / 2;
        canvas.drawBitmap(this.v, (Rect) null, new Rect(i, 0, this.s - i, this.r), (Paint) null);
    }

    public final void c(Canvas canvas) {
        int i = this.s;
        float f = i * 0.25f;
        this.j = f;
        float f2 = i * 0.75f;
        this.l = f2;
        this.k = 0.0f;
        float f3 = this.r;
        this.m = f3;
        this.n = f2 - f;
        float f4 = f3 - 0.0f;
        this.o = f4;
        this.f1095c = i / 2.0f;
        this.d = ((r0 - this.f) * f4) / this.i;
        Paint paint = new Paint();
        float fMax = Math.max(this.d, this.e);
        this.d = fMax;
        this.d = Math.min(fMax, this.o - this.e);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.f1094b);
        float f5 = this.d;
        int i2 = this.e;
        Rect rect = new Rect(0, (int) (f5 - i2), this.s, (int) (f5 + i2));
        Bitmap bitmapA = a(this.w, getResources().getColor(this.y[this.f]));
        this.w = bitmapA;
        canvas.drawBitmap(bitmapA, (Rect) null, rect, (Paint) null);
    }

    public void d(int i, int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        int i3 = this.i;
        if (i2 > i3) {
            i2 = i3;
        }
        if (i == 0) {
            invalidate();
            return;
        }
        if (i == 1) {
            a aVar = this.q;
            if (aVar != null) {
                aVar.M(this, i2);
                return;
            }
            return;
        }
        if (i != 2) {
            return;
        }
        a aVar2 = this.q;
        if (aVar2 != null) {
            aVar2.r0(this, i2);
        }
        setProgress(i2);
        invalidate();
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        getParent().requestDisallowInterceptTouchEvent(true);
        if (!isEnabled() || this.x) {
            return super.dispatchTouchEvent(motionEvent);
        }
        a aVar = this.q;
        if (aVar == null) {
            return false;
        }
        aVar.F(this, this.f);
        return false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.r = getMeasuredHeight();
        this.s = getMeasuredWidth();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = this.s;
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), this.h, options);
        this.w = bitmapDecodeResource;
        int height = (bitmapDecodeResource.getHeight() * this.s) / this.w.getWidth();
        int i = this.u;
        if (i == 0) {
            this.e = height / 2;
        } else {
            this.e = i / 2;
        }
        this.v = BitmapFactory.decodeResource(getResources(), this.g, options);
        b(canvas);
        c(canvas);
        this.p.reset();
    }

    @Override // android.view.View
    public synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled()) {
            float y = motionEvent.getY();
            this.d = y;
            float f = this.o;
            this.f = (int) (((f - y) * this.i) / f);
            int action = motionEvent.getAction();
            if (action == 0) {
                d(0, this.f);
            } else if (action == 1) {
                d(1, this.f);
            } else if (action == 2) {
                d(2, this.f);
            }
        }
        return true;
    }

    public void setCanScroll(boolean z) {
        this.x = z;
    }

    public void setOnSlideChangeListener(a aVar) {
        this.q = aVar;
    }

    public void setProgress(int i) {
        this.f = i;
        invalidate();
    }

    public TemperatureVerticalSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TemperatureVerticalSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1094b = ViewCompat.MEASURED_STATE_MASK;
        this.i = 16;
        this.p = new Paint();
        this.x = true;
        this.y = new int[]{R.color.electric_temperature_seekbar_color0, R.color.electric_temperature_seekbar_color1, R.color.electric_temperature_seekbar_color2, R.color.electric_temperature_seekbar_color3, R.color.electric_temperature_seekbar_color4, R.color.electric_temperature_seekbar_color5, R.color.electric_temperature_seekbar_color6, R.color.electric_temperature_seekbar_color7, R.color.electric_temperature_seekbar_color8, R.color.electric_temperature_seekbar_color9, R.color.electric_temperature_seekbar_color10, R.color.electric_temperature_seekbar_color11, R.color.electric_temperature_seekbar_color12, R.color.electric_temperature_seekbar_color13, R.color.electric_temperature_seekbar_color14, R.color.electric_temperature_seekbar_color15, R.color.electric_temperature_seekbar_color16};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TemperatureVerticalSeekBar, i, 0);
        this.g = typedArrayObtainStyledAttributes.getResourceId(2, R.mipmap.img_light_air_electric_air_conditioner_bg);
        this.h = typedArrayObtainStyledAttributes.getResourceId(4, R.mipmap.img_light_air_electric_air_conditioner_bg_red);
        this.i = typedArrayObtainStyledAttributes.getInteger(0, 100);
        this.f = typedArrayObtainStyledAttributes.getInteger(1, 0);
        this.t = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, getResources().getDimensionPixelSize(R.dimen.dp_20));
        this.u = typedArrayObtainStyledAttributes.getDimensionPixelSize(5, 0);
        typedArrayObtainStyledAttributes.recycle();
    }
}
