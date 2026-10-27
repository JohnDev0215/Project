package com.byd.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import androidx.annotation.RequiresApi;
import com.byd.base.R$drawable;
import com.byd.base.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class BydSeekBar extends SeekBar {
    public boolean A;
    public Drawable B;
    public int D;
    public int E;
    public Drawable F;
    public int G;
    public int H;
    public float I;
    public float J;
    public boolean K;
    public int L;
    public int M;
    public int N;
    public boolean O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public boolean U;
    public SeekBar.OnSeekBarChangeListener V;
    public b W;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Paint f2458a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Paint f2459b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2460c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2461d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2462e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2463f;
    public int g;
    public int h;
    public int[] i;
    public int j;
    public Drawable k;
    public int l;
    public int m;
    public int n;
    public CharSequence[] o;
    public boolean p;
    public int q;
    public float r;
    public int s;
    public Drawable t;
    public int u;
    public int v;
    public boolean w;
    public int x;
    public int y;
    public int z;

    public class a implements SeekBar.OnSeekBarChangeListener {
        public a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            BydSeekBar.this.r = i / r0.getMax();
            if (BydSeekBar.this.W != null) {
                if (BydSeekBar.this.p) {
                    BydSeekBar.this.W.a(BydSeekBar.this.getBackgroundColor());
                }
                BydSeekBar.this.W.onProgressChanged(seekBar, i, z);
            }
            if (BydSeekBar.this.V != null) {
                BydSeekBar.this.V.onProgressChanged(seekBar, i, z);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (BydSeekBar.this.W != null) {
                BydSeekBar.this.W.onStartTrackingTouch(seekBar);
            }
            if (BydSeekBar.this.V != null) {
                BydSeekBar.this.V.onStartTrackingTouch(seekBar);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (BydSeekBar.this.W != null) {
                BydSeekBar.this.W.onStopTrackingTouch(seekBar);
            }
            if (BydSeekBar.this.V != null) {
                BydSeekBar.this.V.onStopTrackingTouch(seekBar);
            }
        }
    }

    public interface b {
        void a(int i);

        void onProgressChanged(SeekBar seekBar, int i, boolean z);

        void onStartTrackingTouch(SeekBar seekBar);

        void onStopTrackingTouch(SeekBar seekBar);
    }

    public BydSeekBar(Context context) {
        this(context, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0190 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(android.graphics.Canvas r9) {
        /*
            Method dump skipped, instruction units count: 405
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.base.view.BydSeekBar.e(android.graphics.Canvas):void");
    }

    public final void f(Canvas canvas) {
        RectF rectF = new RectF();
        if (this.w) {
            this.f2459b.setShader(new LinearGradient(0.0f, 0.0f, rectF.right, 0.0f, new int[]{this.x, this.y}, (float[]) null, Shader.TileMode.CLAMP));
        } else {
            this.f2459b.setColor(this.z);
        }
        if (this.A) {
            if (this.k != null) {
                rectF.left = this.f2462e;
                rectF.right = r1.getBounds().right + this.n;
                rectF.top = this.f2460c;
                rectF.bottom = getMeasuredHeight() - this.f2461d;
            }
        } else {
            rectF.left = this.f2462e;
            int measuredWidth = getMeasuredWidth() - this.f2463f;
            int i = this.f2462e;
            float f2 = this.r;
            rectF.right = ((measuredWidth - i) * f2) + i;
            if (f2 == 0.0f && this.U) {
                rectF.right = i + this.T;
            }
            rectF.top = this.f2460c;
            rectF.bottom = getMeasuredHeight() - this.f2461d;
            Path path = new Path();
            path.moveTo(this.f2462e, this.f2460c);
            float f3 = this.r;
            int measuredWidth2 = getMeasuredWidth() - this.f2463f;
            float f4 = f3 * (measuredWidth2 - r4);
            path.lineTo((this.f2462e + f4) - this.u, this.f2460c);
            int i2 = this.u;
            if (i2 > 0) {
                int i3 = this.f2462e;
                int i4 = this.f2460c;
                path.cubicTo((i3 + f4) - i2, i4, i3 + f4, i4, i3 + f4, i4 + i2);
                path.lineTo(this.f2462e + f4, (getMeasuredHeight() - this.u) - this.f2461d);
                path.cubicTo(this.f2462e + f4, (getMeasuredHeight() - this.u) - this.f2461d, this.f2462e + f4, getMeasuredHeight() - this.f2461d, (this.f2462e + f4) - this.u, getMeasuredHeight() - this.f2461d);
            }
            path.lineTo((this.f2462e + f4) - this.u, getMeasuredHeight() - this.f2461d);
            path.lineTo(this.f2462e, getMeasuredHeight() - this.f2461d);
            path.lineTo(this.f2462e, this.f2460c);
        }
        int i5 = this.g;
        canvas.drawRoundRect(rectF, i5, i5, this.f2459b);
    }

    public final void g(CharSequence[] charSequenceArr) {
        if (charSequenceArr == null) {
            this.i = b.b.a.j.a.i;
            return;
        }
        this.i = new int[charSequenceArr.length];
        for (int i = 0; i < charSequenceArr.length; i++) {
            this.i[i] = Color.parseColor(charSequenceArr[i].toString());
        }
    }

    public int getBackgroundColor() {
        return this.p ? this.i[getProgress() - 1] : this.q;
    }

    public int getPotionUnDrag() {
        if (this.K) {
            return 0;
        }
        return this.L;
    }

    public int getProgressNow() {
        return !this.K ? (int) (this.L * (getMax() / this.s)) : getProgress();
    }

    public final void h() {
        this.f2458a.setAntiAlias(true);
        this.f2459b.setAntiAlias(true);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        if ((getMeasuredHeight() - this.f2460c) - this.f2461d > 0 && (getMeasuredWidth() - this.f2462e) - this.f2463f > 0) {
            RectF rectF = new RectF();
            rectF.left = this.f2462e;
            rectF.right = getMeasuredWidth() - this.f2463f;
            rectF.top = this.f2460c;
            float measuredHeight = getMeasuredHeight() - this.f2461d;
            rectF.bottom = measuredHeight;
            if (this.p) {
                int i = this.h;
                float f2 = i == 0 ? rectF.right : 0.0f;
                if (i == 0) {
                    measuredHeight = 0.0f;
                }
                this.f2458a.setShader(new LinearGradient(0.0f, 0.0f, f2, measuredHeight, this.i, (float[]) null, Shader.TileMode.REPEAT));
            } else {
                this.f2458a.setColor(this.q);
            }
            int i2 = this.g;
            canvas.drawRoundRect(rectF, i2, i2, this.f2458a);
        }
        Drawable thumb = getThumb();
        this.k = thumb;
        if (thumb != null) {
            this.l = thumb.getBounds().height();
            this.m = this.k.getBounds().width();
            int i3 = this.k.getBounds().left;
            int i4 = this.f2462e;
            int i5 = this.n;
            if (i3 <= i4 + i5) {
                this.k.setBounds(i4 + i5, (getMeasuredHeight() - this.l) / 2, this.m + this.f2462e + this.n, (getMeasuredHeight() + this.l) / 2);
            } else if (this.k.getBounds().right >= (getMeasuredWidth() - this.f2463f) - this.n) {
                this.k.setBounds(((getMeasuredWidth() - this.f2463f) - this.m) - this.n, (getMeasuredHeight() - this.l) / 2, (getMeasuredWidth() - this.f2463f) - this.n, (getMeasuredHeight() + this.l) / 2);
            }
        }
        if (this.t != null && this.s > 0 && !this.A) {
            e(canvas);
        }
        if (!this.p) {
            f(canvas);
        }
        if (this.t != null && this.s > 0) {
            e(canvas);
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            size = View.MeasureSpec.getSize(i);
        } else if (mode == Integer.MIN_VALUE && (size = View.MeasureSpec.getSize(i)) >= (i3 = this.j)) {
            size = i3;
        }
        if (mode2 == 1073741824) {
            size2 = View.MeasureSpec.getSize(i2);
        } else if (mode2 == Integer.MIN_VALUE && (size2 = View.MeasureSpec.getSize(i2)) >= this.j) {
            if (getThumb() != null) {
                size2 = this.j > getThumb().getIntrinsicWidth() ? this.j : getThumb().getIntrinsicWidth();
            } else {
                size2 = this.j;
            }
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    @RequiresApi(api = 26)
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.K) {
            return super.onTouchEvent(motionEvent);
        }
        int x = (int) (motionEvent.getX() - this.f2462e);
        int measuredWidth = getMeasuredWidth();
        int i = this.f2462e;
        int i2 = ((measuredWidth - i) - this.f2463f) / this.s;
        int i3 = x / i2;
        int i4 = x % i2;
        if (i4 < i2 * 0.25f) {
            this.L = i3;
        } else if (i4 > (i2 * 3) / 4) {
            this.L = i3 + 1;
        }
        Drawable drawable = this.k;
        if (drawable == null) {
            return false;
        }
        drawable.setBounds((i + ((int) ((this.L * ((getMeasuredWidth() - this.f2462e) - this.f2463f)) / this.s))) - (this.k.getIntrinsicWidth() / 2), (getMeasuredHeight() - this.k.getIntrinsicHeight()) / 2, this.f2462e + ((this.L * ((getMeasuredWidth() - this.f2462e) - this.f2463f)) / this.s) + (this.k.getIntrinsicWidth() / 2), getMeasuredHeight() - ((getMeasuredHeight() - this.k.getIntrinsicHeight()) / 2));
        setProgress((((getMax() - getMin()) / this.s) * this.L) + getMin());
        invalidate();
        return false;
    }

    public void setCorlors(int i) {
        if (i < b.b.a.j.a.f538e || i > b.b.a.j.a.h) {
            return;
        }
        int i2 = b.b.a.j.a.f534a;
        int[] iArr = b.b.a.j.a.i;
        if (i == b.b.a.j.a.f539f) {
            i2 = b.b.a.j.a.f535b;
            iArr = b.b.a.j.a.k;
        }
        if (i == b.b.a.j.a.g) {
            i2 = b.b.a.j.a.f536c;
            iArr = b.b.a.j.a.j;
        }
        if (i == b.b.a.j.a.h) {
            i2 = b.b.a.j.a.f537d;
            iArr = b.b.a.j.a.l;
        }
        setMax(i2);
        this.i = iArr;
        invalidate();
    }

    public void setGradientColorListener(b bVar) {
        this.W = bVar;
    }

    @Override // android.widget.SeekBar
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.V = onSeekBarChangeListener;
    }

    public void setPositionUnDrag(int i) {
        if (this.K || i < 0 || i > this.s) {
            return;
        }
        this.L = i;
    }

    public void setProgressGradientColor(int i, int i2) {
        if (this.w) {
            this.y = i2;
            this.x = i;
            invalidate();
        }
    }

    public void setProgressNow(int i) {
        if (i < 0 || i > getMax()) {
            return;
        }
        if (!this.K) {
            int iRound = Math.round(getMax() / this.s);
            int i2 = i / iRound;
            if (i % iRound >= (iRound * 3.0f) / 4.0f) {
                setPositionUnDrag(i2 + 1);
            } else {
                setPositionUnDrag(i2);
            }
        }
        setProgress(i);
    }

    public void setUSelectedColor(int i) {
        this.N = i;
        invalidate();
    }

    public BydSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BydSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f2458a = new Paint();
        this.f2459b = new Paint();
        this.r = 0.0f;
        this.L = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BydSeekBar);
        this.f2460c = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_backgroundTop, 0.0f);
        this.f2461d = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_backgroundBottom, 0.0f);
        this.f2462e = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_backgroundLeft, 0.0f);
        this.f2463f = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_backgroundRight, 0.0f);
        this.g = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_bacgroudRadius, 0.0f);
        this.h = typedArrayObtainStyledAttributes.getInt(R$styleable.BydSeekBar_linearOrientaion, 0);
        this.o = typedArrayObtainStyledAttributes.getTextArray(R$styleable.BydSeekBar_linearColors);
        this.n = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_thumbMargin, 0.0f);
        this.k = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydSeekBar_android_thumb);
        this.v = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_android_thumbOffset, 0.0f);
        this.q = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_innerBackgroundcolor, -7829368);
        this.p = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BydSeekBar_linearColorsToggle, true);
        this.s = typedArrayObtainStyledAttributes.getInt(R$styleable.BydSeekBar_areaCount, 1);
        this.t = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydSeekBar_areaBackground);
        this.B = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydSeekBar_areaBackgroundFirst);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, context.getResources().getDisplayMetrics());
        this.j = iApplyDimension;
        this.D = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_areaBackgroundFirstWidth, iApplyDimension);
        this.E = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_areaBackgroundFirstHeight, this.j);
        this.I = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_areaBackgroundFirstMaginH, 0.0f);
        this.J = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_areaBackgroundEndMaginH, 0.0f);
        this.F = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydSeekBar_areaBackgroundEnd);
        this.G = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_areaBackgroundEndWidth, this.j);
        this.H = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_areaBackgroundEndHeight, this.j);
        this.u = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_clipRadius, 0.0f);
        this.w = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BydSeekBar_progressGradient, false);
        this.A = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BydSeekBar_isThumbVisiable, false);
        this.x = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_progressStartColor, 0);
        this.y = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_progressEndColor, 0);
        this.z = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_progressColor, -16776961);
        this.K = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BydSeekBar_isCanDrag, true);
        this.M = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_areaBackgroundSelctedColor, -16776961);
        this.N = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_areaBackgroundUnSelctedColor, -16711936);
        this.P = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_firstAreaBgSelectedColor, -1);
        this.Q = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_firstAreaBgUnSelectedColor, -1);
        this.R = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_endAreaBgSelectedColor, -1);
        this.S = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_endAreaBgUnSelectedColor, -1);
        this.T = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_defaultDistance, 0.0f);
        this.U = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BydSeekBar_isDrawDefaultDistance, false);
        this.O = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BydSeekBar_isneedColorFilter, false);
        if (this.k == null && this.A) {
            Drawable drawable = context.getDrawable(R$drawable.ambient_lighting_seekbar_color_thumb);
            this.k = drawable;
            setThumb(drawable);
            setThumbOffset(this.v);
        }
        if (this.p) {
            g(this.o);
        }
        typedArrayObtainStyledAttributes.recycle();
        h();
        super.setOnSeekBarChangeListener(new a());
    }

    public void setUSelectedColor(String str) {
        setUSelectedColor(Color.parseColor(str));
    }
}
