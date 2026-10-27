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
    public int A;
    public boolean B;
    public Drawable C;
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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Paint f548b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Paint f549c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f550d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int[] j;
    public int k;
    public Drawable l;
    public int m;
    public int n;
    public int o;
    public CharSequence[] p;
    public boolean q;
    public int r;
    public float s;
    public int t;
    public Drawable u;
    public int v;
    public int w;
    public boolean x;
    public int y;
    public int z;

    public class a implements SeekBar.OnSeekBarChangeListener {
        public a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            BydSeekBar.this.s = i / r0.getMax();
            if (BydSeekBar.this.W != null) {
                if (BydSeekBar.this.q) {
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

    /* JADX WARN: Removed duplicated region for block: B:56:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x019d A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(android.graphics.Canvas r9) {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.base.view.BydSeekBar.e(android.graphics.Canvas):void");
    }

    public final void f(Canvas canvas) {
        RectF rectF = new RectF();
        if (this.x) {
            this.f549c.setShader(new LinearGradient(0.0f, 0.0f, rectF.right, 0.0f, new int[]{this.y, this.z}, (float[]) null, Shader.TileMode.CLAMP));
        } else {
            this.f549c.setColor(this.A);
        }
        if (this.B) {
            if (this.l != null) {
                rectF.left = this.f;
                rectF.right = r1.getBounds().right + this.o;
                rectF.top = this.f550d;
                rectF.bottom = getMeasuredHeight() - this.e;
            }
        } else {
            rectF.left = this.f;
            int measuredWidth = getMeasuredWidth() - this.g;
            int i = this.f;
            float f = this.s;
            rectF.right = ((measuredWidth - i) * f) + i;
            if (f == 0.0f && this.U) {
                rectF.right = i + this.T;
            }
            rectF.top = this.f550d;
            rectF.bottom = getMeasuredHeight() - this.e;
            Path path = new Path();
            path.moveTo(this.f, this.f550d);
            float f2 = this.s;
            int measuredWidth2 = getMeasuredWidth() - this.g;
            float f3 = f2 * (measuredWidth2 - r4);
            path.lineTo((this.f + f3) - this.v, this.f550d);
            int i2 = this.v;
            if (i2 > 0) {
                int i3 = this.f;
                int i4 = this.f550d;
                path.cubicTo((i3 + f3) - i2, i4, i3 + f3, i4, i3 + f3, i4 + i2);
                path.lineTo(this.f + f3, (getMeasuredHeight() - this.v) - this.e);
                path.cubicTo(this.f + f3, (getMeasuredHeight() - this.v) - this.e, this.f + f3, getMeasuredHeight() - this.e, (this.f + f3) - this.v, getMeasuredHeight() - this.e);
            }
            path.lineTo((this.f + f3) - this.v, getMeasuredHeight() - this.e);
            path.lineTo(this.f, getMeasuredHeight() - this.e);
            path.lineTo(this.f, this.f550d);
        }
        int i5 = this.h;
        canvas.drawRoundRect(rectF, i5, i5, this.f549c);
    }

    public final void g(CharSequence[] charSequenceArr) {
        if (charSequenceArr == null) {
            this.j = b.a.b.e.a.i;
            return;
        }
        this.j = new int[charSequenceArr.length];
        for (int i = 0; i < charSequenceArr.length; i++) {
            this.j[i] = Color.parseColor(charSequenceArr[i].toString());
        }
    }

    public int getBackgroundColor() {
        return this.q ? this.j[getProgress() - 1] : this.r;
    }

    public int getPotionUnDrag() {
        if (this.K) {
            return 0;
        }
        return this.L;
    }

    public int getProgressNow() {
        return !this.K ? (int) (this.L * (getMax() / this.t)) : getProgress();
    }

    public final void h() {
        this.f548b.setAntiAlias(true);
        this.f549c.setAntiAlias(true);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        if ((getMeasuredHeight() - this.f550d) - this.e > 0 && (getMeasuredWidth() - this.f) - this.g > 0) {
            RectF rectF = new RectF();
            rectF.left = this.f;
            rectF.right = getMeasuredWidth() - this.g;
            rectF.top = this.f550d;
            float measuredHeight = getMeasuredHeight() - this.e;
            rectF.bottom = measuredHeight;
            if (this.q) {
                int i = this.i;
                float f = i == 0 ? rectF.right : 0.0f;
                if (i == 0) {
                    measuredHeight = 0.0f;
                }
                this.f548b.setShader(new LinearGradient(0.0f, 0.0f, f, measuredHeight, this.j, (float[]) null, Shader.TileMode.REPEAT));
            } else {
                this.f548b.setColor(this.r);
            }
            int i2 = this.h;
            canvas.drawRoundRect(rectF, i2, i2, this.f548b);
        }
        Drawable thumb = getThumb();
        this.l = thumb;
        if (thumb != null) {
            this.m = thumb.getBounds().height();
            this.n = this.l.getBounds().width();
            int i3 = this.l.getBounds().left;
            int i4 = this.f;
            int i5 = this.o;
            if (i3 <= i4 + i5) {
                this.l.setBounds(i4 + i5, (getMeasuredHeight() - this.m) / 2, this.n + this.f + this.o, (getMeasuredHeight() + this.m) / 2);
            } else if (this.l.getBounds().right >= (getMeasuredWidth() - this.g) - this.o) {
                this.l.setBounds(((getMeasuredWidth() - this.g) - this.n) - this.o, (getMeasuredHeight() - this.m) / 2, (getMeasuredWidth() - this.g) - this.o, (getMeasuredHeight() + this.m) / 2);
            }
        }
        if (this.u != null && this.t > 0 && !this.B) {
            e(canvas);
        }
        if (!this.q) {
            f(canvas);
        }
        if (this.u != null && this.t > 0) {
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
        } else if (mode == Integer.MIN_VALUE && (size = View.MeasureSpec.getSize(i)) >= (i3 = this.k)) {
            size = i3;
        }
        if (mode2 == 1073741824) {
            size2 = View.MeasureSpec.getSize(i2);
        } else if (mode2 == Integer.MIN_VALUE && (size2 = View.MeasureSpec.getSize(i2)) >= this.k) {
            if (getThumb() != null) {
                size2 = this.k > getThumb().getIntrinsicWidth() ? this.k : getThumb().getIntrinsicWidth();
            } else {
                size2 = this.k;
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
        int x = (int) (motionEvent.getX() - this.f);
        int measuredWidth = getMeasuredWidth();
        int i = this.f;
        int i2 = ((measuredWidth - i) - this.g) / this.t;
        int i3 = x / i2;
        int i4 = x % i2;
        if (i4 < i2 * 0.25f) {
            this.L = i3;
        } else if (i4 > (i2 * 3) / 4) {
            this.L = i3 + 1;
        }
        Drawable drawable = this.l;
        if (drawable == null) {
            return false;
        }
        drawable.setBounds((i + ((int) ((this.L * ((getMeasuredWidth() - this.f) - this.g)) / this.t))) - (this.l.getIntrinsicWidth() / 2), (getMeasuredHeight() - this.l.getIntrinsicHeight()) / 2, this.f + ((this.L * ((getMeasuredWidth() - this.f) - this.g)) / this.t) + (this.l.getIntrinsicWidth() / 2), getMeasuredHeight() - ((getMeasuredHeight() - this.l.getIntrinsicHeight()) / 2));
        setProgress((((getMax() - getMin()) / this.t) * this.L) + getMin());
        invalidate();
        return false;
    }

    public void setCorlors(int i) {
        if (i < b.a.b.e.a.e || i > b.a.b.e.a.h) {
            return;
        }
        int i2 = b.a.b.e.a.f182a;
        int[] iArr = b.a.b.e.a.i;
        if (i == b.a.b.e.a.f) {
            i2 = b.a.b.e.a.f183b;
            iArr = b.a.b.e.a.k;
        }
        if (i == b.a.b.e.a.g) {
            i2 = b.a.b.e.a.f184c;
            iArr = b.a.b.e.a.j;
        }
        if (i == b.a.b.e.a.h) {
            i2 = b.a.b.e.a.f185d;
            iArr = b.a.b.e.a.l;
        }
        setMax(i2);
        this.j = iArr;
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
        if (this.K || i < 0 || i > this.t) {
            return;
        }
        this.L = i;
    }

    public void setProgressGradientColor(int i, int i2) {
        if (this.x) {
            this.z = i2;
            this.y = i;
            invalidate();
        }
    }

    public void setProgressNow(int i) {
        if (i < 0 || i > getMax()) {
            return;
        }
        if (!this.K) {
            int iRound = Math.round(getMax() / this.t);
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
        this.f548b = new Paint();
        this.f549c = new Paint();
        this.s = 0.0f;
        this.L = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BydSeekBar);
        this.f550d = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_backgroundTop, 0.0f);
        this.e = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_backgroundBottom, 0.0f);
        this.f = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_backgroundLeft, 0.0f);
        this.g = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_backgroundRight, 0.0f);
        this.h = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_bacgroudRadius, 0.0f);
        this.i = typedArrayObtainStyledAttributes.getInt(R$styleable.BydSeekBar_linearOrientaion, 0);
        this.p = typedArrayObtainStyledAttributes.getTextArray(R$styleable.BydSeekBar_linearColors);
        this.o = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_thumbMargin, 0.0f);
        this.l = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydSeekBar_android_thumb);
        this.w = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_android_thumbOffset, 0.0f);
        this.r = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_innerBackgroundcolor, -7829368);
        this.q = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BydSeekBar_linearColorsToggle, true);
        this.t = typedArrayObtainStyledAttributes.getInt(R$styleable.BydSeekBar_areaCount, 1);
        this.u = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydSeekBar_areaBackground);
        this.C = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydSeekBar_areaBackgroundFirst);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, context.getResources().getDisplayMetrics());
        this.k = iApplyDimension;
        this.D = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_areaBackgroundFirstWidth, iApplyDimension);
        this.E = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_areaBackgroundFirstHeight, this.k);
        this.I = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_areaBackgroundFirstMaginH, 0.0f);
        this.J = typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_areaBackgroundEndMaginH, 0.0f);
        this.F = typedArrayObtainStyledAttributes.getDrawable(R$styleable.BydSeekBar_areaBackgroundEnd);
        this.G = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_areaBackgroundEndWidth, this.k);
        this.H = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_areaBackgroundEndHeight, this.k);
        this.v = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.BydSeekBar_clipRadius, 0.0f);
        this.x = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BydSeekBar_progressGradient, false);
        this.B = typedArrayObtainStyledAttributes.getBoolean(R$styleable.BydSeekBar_isThumbVisiable, false);
        this.y = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_progressStartColor, 0);
        this.z = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_progressEndColor, 0);
        this.A = typedArrayObtainStyledAttributes.getColor(R$styleable.BydSeekBar_progressColor, -16776961);
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
        if (this.l == null && this.B) {
            Drawable drawable = context.getDrawable(R$drawable.ambient_lighting_seekbar_color_thumb);
            this.l = drawable;
            setThumb(drawable);
            setThumbOffset(this.w);
        }
        if (this.q) {
            g(this.p);
        }
        typedArrayObtainStyledAttributes.recycle();
        h();
        super.setOnSeekBarChangeListener(new a());
    }

    public void setUSelectedColor(String str) {
        setUSelectedColor(Color.parseColor(str));
    }
}
