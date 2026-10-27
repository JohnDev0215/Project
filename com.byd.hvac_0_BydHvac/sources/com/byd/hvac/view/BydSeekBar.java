package com.byd.hvac.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import androidx.core.internal.view.SupportMenu;
import com.byd.hvac.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: caradapter-bug */
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
    public b Q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Paint f991a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Paint f992b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f993c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int[] i;
    public int j;
    public int[] k;
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

    /* JADX INFO: compiled from: caradapter-bug */
    public class a implements SeekBar.OnSeekBarChangeListener {
        public a() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            BydSeekBar.this.s = i / r0.getMax();
            BydSeekBar.this.P = i;
            if (BydSeekBar.this.Q != null) {
                if (BydSeekBar.this.q) {
                    BydSeekBar.this.Q.p(BydSeekBar.this.getBackgroundColor());
                }
                BydSeekBar.this.Q.onProgressChanged(seekBar, i, z);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            if (BydSeekBar.this.Q != null) {
                BydSeekBar.this.Q.onStartTrackingTouch(seekBar);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (BydSeekBar.this.Q != null) {
                BydSeekBar.this.Q.f0(seekBar, BydSeekBar.this.P);
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface b {
        void f0(SeekBar seekBar, int i);

        void onProgressChanged(SeekBar seekBar, int i, boolean z);

        void onStartTrackingTouch(SeekBar seekBar);

        void p(int i);
    }

    public BydSeekBar(Context context) {
        this(context, null);
    }

    public final void f(Canvas canvas) {
        Drawable drawable;
        Drawable drawable2;
        int i = 0;
        while (true) {
            int i2 = this.t;
            if (i > i2) {
                return;
            }
            Drawable drawable3 = this.u;
            if (drawable3 != null && i != 0 && i != i2) {
                drawable3.setBounds((this.e + ((int) ((((getMeasuredWidth() - this.e) - this.f) * i) / this.t))) - (this.u.getIntrinsicWidth() / 2), (getMeasuredHeight() - this.u.getIntrinsicHeight()) / 2, this.e + ((((getMeasuredWidth() - this.e) - this.f) * i) / this.t) + (this.u.getIntrinsicWidth() / 2), getMeasuredHeight() - ((getMeasuredHeight() - this.u.getIntrinsicHeight()) / 2));
                this.u.setColorFilter((!this.O || ((float) i) / ((float) this.t) > ((float) getProgress()) / ((float) getMax())) ? this.N : this.M, PorterDuff.Mode.SRC_IN);
                if (!this.K) {
                    this.u.setColorFilter((!this.O || i > this.L) ? this.N : this.M, PorterDuff.Mode.SRC_IN);
                }
                this.u.draw(canvas);
            }
            if (i == 0 && (drawable2 = this.C) != null) {
                drawable2.setBounds((int) this.I, (getMeasuredHeight() - this.E) / 2, (int) (this.I + this.D), (getMeasuredHeight() + this.E) / 2);
                this.C.setColorFilter((!this.O || getProgress() <= 0) ? this.N : this.M, PorterDuff.Mode.SRC_IN);
                if (!this.K) {
                    this.C.setColorFilter((!this.O || this.L <= 0) ? this.N : this.M, PorterDuff.Mode.SRC_IN);
                }
                this.C.draw(canvas);
            }
            if (i == this.t && (drawable = this.F) != null) {
                drawable.setBounds((int) ((getMeasuredWidth() - this.J) - this.G), (getMeasuredHeight() - this.H) / 2, (int) (getMeasuredWidth() - this.J), (getMeasuredHeight() + this.H) / 2);
                this.F.setColorFilter((this.O && getMax() == getProgress()) ? this.M : this.N, PorterDuff.Mode.SRC_IN);
                if (!this.K) {
                    this.F.setColorFilter((this.O && this.L == this.t) ? this.M : this.N, PorterDuff.Mode.SRC_IN);
                }
                this.F.draw(canvas);
            }
            i++;
        }
    }

    public final void g(Canvas canvas) {
        RectF rectF = new RectF();
        if (this.B) {
            if (this.l != null) {
                rectF.left = this.e;
                rectF.right = r1.getBounds().right + this.o;
                rectF.top = this.f993c;
                rectF.bottom = getMeasuredHeight() - this.d;
            }
        } else {
            rectF.left = this.e;
            rectF.right = getMeasuredWidth() - this.f;
            rectF.top = this.f993c;
            rectF.bottom = getMeasuredHeight() - this.d;
            Path path = new Path();
            path.moveTo(this.e, this.f993c);
            float f = this.s;
            int measuredWidth = getMeasuredWidth() - this.f;
            float f2 = f * (measuredWidth - r4);
            path.lineTo((this.e + f2) - this.v, this.f993c);
            int i = this.v;
            if (i > 0) {
                int i2 = this.e;
                int i3 = this.f993c;
                path.cubicTo((i2 + f2) - i, i3, i2 + f2, i3, i2 + f2, i3 + i);
                path.lineTo(this.e + f2, (getMeasuredHeight() - this.v) - this.d);
                path.cubicTo(this.e + f2, (getMeasuredHeight() - this.v) - this.d, this.e + f2, getMeasuredHeight() - this.d, (this.e + f2) - this.v, getMeasuredHeight() - this.d);
            }
            path.lineTo((this.e + f2) - this.v, getMeasuredHeight() - this.d);
            path.lineTo(this.e, getMeasuredHeight() - this.d);
            path.lineTo(this.e, this.f993c);
            canvas.clipPath(path);
        }
        if (this.x) {
            this.f992b.setShader(new LinearGradient(0.0f, 0.0f, rectF.right, rectF.bottom, new int[]{this.y, this.z}, (float[]) null, Shader.TileMode.CLAMP));
        } else {
            this.f992b.setColor(this.A);
        }
        int i4 = this.g;
        canvas.drawRoundRect(rectF, i4, i4, this.f992b);
    }

    public int getAreaCount() {
        return this.t;
    }

    public int getBackgroundColor() {
        if (this.q) {
            float length = 1.0f / (r1.length - 1);
            for (int length2 = this.i.length - 1; length2 >= 0; length2--) {
                float f = length2 * length;
                if (f <= getProgress() / getMax()) {
                    int[] iArr = this.i;
                    if (length2 == iArr.length - 1) {
                        return iArr[iArr.length - 1];
                    }
                    if (length2 == 0) {
                        return iArr[0];
                    }
                    float progress = ((getProgress() / getMax()) - f) / length;
                    int[] iArr2 = this.i;
                    return h(progress, iArr2[length2], iArr2[length2 + 1]);
                }
            }
        }
        return this.r;
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

    public final int h(float f, int i, int i2) {
        int iRed = Color.red(i);
        int iBlue = Color.blue(i);
        return Color.argb(255, (int) (((double) iRed) + ((double) ((Color.red(i2) - iRed) * f)) + 0.5d), (int) (((double) Color.green(i)) + ((double) ((Color.green(i2) - r12) * f)) + 0.5d), (int) (((double) iBlue) + ((double) ((Color.blue(i2) - iBlue) * f)) + 0.5d));
    }

    public final void i(CharSequence[] charSequenceArr) {
        if (charSequenceArr == null) {
            this.i = this.k;
            return;
        }
        this.i = new int[charSequenceArr.length];
        for (int i = 0; i < charSequenceArr.length; i++) {
            if (charSequenceArr[i] != null) {
                this.i[i] = Color.parseColor(charSequenceArr[i].toString());
            }
        }
    }

    public final void j() {
        this.f991a.setAntiAlias(true);
        this.f992b.setAntiAlias(true);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onDraw(Canvas canvas) {
        if ((getMeasuredHeight() - this.f993c) - this.d > 0 && (getMeasuredWidth() - this.e) - this.f > 0) {
            RectF rectF = new RectF();
            rectF.left = this.e;
            rectF.right = getMeasuredWidth() - this.f;
            rectF.top = this.f993c;
            rectF.bottom = getMeasuredHeight() - this.d;
            if (this.q) {
                int i = this.h;
                this.f991a.setShader(new LinearGradient(0.0f, 0.0f, i == 0 ? rectF.right : 0.0f, i == 0 ? 0.0f : rectF.bottom, this.i, (float[]) null, Shader.TileMode.REPEAT));
            } else {
                this.f991a.setColor(this.r);
            }
            int i2 = this.g;
            canvas.drawRoundRect(rectF, i2, i2, this.f991a);
        }
        Drawable thumb = getThumb();
        this.l = thumb;
        if (thumb != null) {
            this.m = thumb.getBounds().height();
            this.n = this.l.getBounds().width();
            int i3 = this.l.getBounds().left;
            int i4 = this.e;
            int i5 = this.o;
            if (i3 <= i4 + i5) {
                this.l.setBounds(i4 + i5, (getMeasuredHeight() - this.m) / 2, this.n + this.e + this.o, (getMeasuredHeight() + this.m) / 2);
            } else if (this.l.getBounds().right >= (getMeasuredWidth() - this.f) - this.o) {
                this.l.setBounds(((getMeasuredWidth() - this.f) - this.n) - this.o, (getMeasuredHeight() - this.m) / 2, (getMeasuredWidth() - this.f) - this.o, (getMeasuredHeight() + this.m) / 2);
            }
        }
        if (this.u != null && this.t > 0 && !this.B) {
            f(canvas);
        }
        if (!this.q) {
            g(canvas);
        }
        if (this.u != null && this.t > 0) {
            f(canvas);
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            if (mode == Integer.MIN_VALUE) {
                i = View.MeasureSpec.getSize(i);
                int i3 = this.j;
                if (i >= i3) {
                    i = i3;
                }
            } else {
                i = size;
            }
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
        setMeasuredDimension(i, size2);
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int x = (int) (motionEvent.getX() - this.e);
        int measuredWidth = ((getMeasuredWidth() - this.e) - this.f) / this.t;
        int i = x / measuredWidth;
        int i2 = x % measuredWidth;
        if (x <= measuredWidth / 2) {
            b bVar = this.Q;
            if (bVar != null) {
                bVar.f0(this, this.P);
            }
            return false;
        }
        if (this.K || !isEnabled()) {
            return super.onTouchEvent(motionEvent);
        }
        if (i2 < measuredWidth / 4) {
            this.L = i;
        } else if (i2 > (measuredWidth * 3) / 4) {
            this.L = i + 1;
        }
        Drawable drawable = this.l;
        if (drawable != null) {
            drawable.setBounds((this.e + ((int) ((this.L * ((getMeasuredWidth() - this.e) - this.f)) / this.t))) - (this.l.getIntrinsicWidth() / 2), (getMeasuredHeight() - this.l.getIntrinsicHeight()) / 2, this.e + ((this.L * ((getMeasuredWidth() - this.e) - this.f)) / this.t) + (this.l.getIntrinsicWidth() / 2), getMeasuredHeight() - ((getMeasuredHeight() - this.l.getIntrinsicHeight()) / 2));
            invalidate();
        }
        return false;
    }

    public void setAreaCount(int i) {
        this.t = i;
    }

    public void setGradientColorListener(b bVar) {
        this.Q = bVar;
    }

    public void setPositionUnDrag(int i) {
        if (this.K || i < 0 || i > this.t) {
            return;
        }
        this.L = i;
        invalidate();
    }

    public void setProgressGradientColor(int i, int i2) {
        BydLogHelper.logD("setProgressGradientColor startColor:" + i + " -- endColor:" + i2);
        StringBuilder sb = new StringBuilder();
        sb.append("setProgressGradientColor progressGradient:");
        sb.append(this.x);
        BydLogHelper.logD(sb.toString());
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
        if (this.K) {
            setProgress(i);
            return;
        }
        int iRound = Math.round(getMax() / this.t);
        int i2 = i / iRound;
        if (i % iRound >= (iRound * 3) / 4) {
            setPositionUnDrag(i2 + 1);
        } else {
            setPositionUnDrag(i2);
        }
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
        this.f991a = new Paint();
        this.f992b = new Paint();
        this.k = new int[]{Color.parseColor("#FFB620E0"), Color.parseColor("#FF6236FF"), Color.parseColor("#FF0091FF"), Color.parseColor("#FF6DD400"), Color.parseColor("#FFF7B500"), Color.parseColor("#FFFA6400"), Color.parseColor("#FFE02020")};
        this.s = 0.0f;
        this.L = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BydSeekBar);
        this.f993c = (int) typedArrayObtainStyledAttributes.getDimension(18, 0.0f);
        this.d = (int) typedArrayObtainStyledAttributes.getDimension(15, 0.0f);
        this.e = (int) typedArrayObtainStyledAttributes.getDimension(16, 0.0f);
        this.f = (int) typedArrayObtainStyledAttributes.getDimension(17, 0.0f);
        this.g = (int) typedArrayObtainStyledAttributes.getDimension(14, 0.0f);
        this.h = typedArrayObtainStyledAttributes.getInt(26, 0);
        this.p = typedArrayObtainStyledAttributes.getTextArray(24);
        this.o = (int) typedArrayObtainStyledAttributes.getDimension(31, 0.0f);
        this.l = typedArrayObtainStyledAttributes.getDrawable(0);
        this.w = (int) typedArrayObtainStyledAttributes.getDimension(1, 0.0f);
        this.r = typedArrayObtainStyledAttributes.getColor(20, -7829368);
        this.q = typedArrayObtainStyledAttributes.getBoolean(25, true);
        this.t = typedArrayObtainStyledAttributes.getInt(13, 0);
        this.u = typedArrayObtainStyledAttributes.getDrawable(2);
        this.C = typedArrayObtainStyledAttributes.getDrawable(7);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, context.getResources().getDisplayMetrics());
        this.j = iApplyDimension;
        this.D = (int) typedArrayObtainStyledAttributes.getDimension(10, iApplyDimension);
        this.E = (int) typedArrayObtainStyledAttributes.getDimension(8, this.j);
        this.I = typedArrayObtainStyledAttributes.getDimension(9, 0.0f);
        this.J = typedArrayObtainStyledAttributes.getDimension(5, 0.0f);
        this.F = typedArrayObtainStyledAttributes.getDrawable(3);
        this.G = (int) typedArrayObtainStyledAttributes.getDimension(6, this.j);
        this.H = (int) typedArrayObtainStyledAttributes.getDimension(4, this.j);
        this.v = (int) typedArrayObtainStyledAttributes.getDimension(19, 0.0f);
        this.x = typedArrayObtainStyledAttributes.getBoolean(29, false);
        this.B = typedArrayObtainStyledAttributes.getBoolean(22, false);
        this.y = typedArrayObtainStyledAttributes.getColor(30, 0);
        this.z = typedArrayObtainStyledAttributes.getColor(28, 0);
        this.A = typedArrayObtainStyledAttributes.getColor(27, SupportMenu.CATEGORY_MASK);
        this.K = typedArrayObtainStyledAttributes.getBoolean(21, true);
        this.M = typedArrayObtainStyledAttributes.getColor(11, -16776961);
        this.N = typedArrayObtainStyledAttributes.getColor(12, -16711936);
        this.O = typedArrayObtainStyledAttributes.getBoolean(23, false);
        if (this.l == null && this.B) {
            Drawable drawable = context.getDrawable(R.drawable.icon_light_sun_big_48);
            this.l = drawable;
            setThumb(drawable);
            setThumbOffset(this.w);
        }
        if (this.q) {
            i(this.p);
        }
        typedArrayObtainStyledAttributes.recycle();
        j();
        setOnSeekBarChangeListener(new a());
    }

    public void setUSelectedColor(String str) {
        setUSelectedColor(Color.parseColor(str));
    }
}
