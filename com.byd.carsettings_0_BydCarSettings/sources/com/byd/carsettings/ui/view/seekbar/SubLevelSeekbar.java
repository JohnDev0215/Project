package com.byd.carsettings.ui.view.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.byd.carsettings.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.text.DecimalFormat;

/* JADX INFO: loaded from: classes2.dex */
public class SubLevelSeekbar extends View {
    public LinearGradient A;
    public Paint B;
    public Paint D;
    public Paint E;
    public DecimalFormat F;
    public a G;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3336a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3337b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3338c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3339d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3340e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3341f;
    public float g;
    public float h;
    public int i;
    public int j;
    public int k;
    public int l;
    public Rect m;
    public int n;
    public int o;
    public float p;
    public float q;
    public float r;
    public float s;
    public boolean t;
    public float u;
    public float v;
    public Paint w;
    public LinearGradient x;
    public LinearGradient y;
    public LinearGradient z;

    public interface a {
        void a(float f2);

        void b(float f2);
    }

    public SubLevelSeekbar(Context context) {
        super(context);
        this.m = new Rect();
        this.t = false;
        this.u = 0.0f;
        this.v = 8.0f;
        a();
    }

    public final void a() {
        this.F = new DecimalFormat("#.#");
        Paint paint = new Paint();
        this.w = paint;
        paint.setAntiAlias(true);
        this.w.setStyle(Paint.Style.FILL);
        TextPaint textPaint = new TextPaint();
        this.B = textPaint;
        textPaint.setTextAlign(Paint.Align.CENTER);
        this.B.setAntiAlias(true);
        this.B.setTextSize(getResources().getDimension(R.dimen.sp_20));
        this.B.setColor(ContextCompat.getColor(getContext(), R.color.sublevelseekbar_text));
        Paint paint2 = new Paint();
        this.D = paint2;
        paint2.setAntiAlias(true);
        this.D.setStyle(Paint.Style.FILL);
        this.D.setColor(ContextCompat.getColor(getContext(), R.color.sublevelseekbar_circle));
        Paint paint3 = new Paint();
        this.E = paint3;
        paint3.setStrokeWidth(this.f3340e);
        this.E.setAntiAlias(true);
        this.E.setStyle(Paint.Style.STROKE);
        this.E.setColor(ContextCompat.getColor(getContext(), R.color.sublevelseekbar_line));
        this.x = new LinearGradient(0.0f, this.k, 0.0f, this.f3338c, new int[]{ContextCompat.getColor(getContext(), R.color.sublevelseekbar_start_one), ContextCompat.getColor(getContext(), R.color.sublevelseekbar_end_one)}, (float[]) null, Shader.TileMode.CLAMP);
        this.y = new LinearGradient(0.0f, this.k, 0.0f, this.f3338c, new int[]{ContextCompat.getColor(getContext(), R.color.sublevelseekbar_start_two), ContextCompat.getColor(getContext(), R.color.sublevelseekbar_end_two)}, (float[]) null, Shader.TileMode.CLAMP);
        this.A = new LinearGradient(0.0f, this.k, 0.0f, this.f3338c, new int[]{ContextCompat.getColor(getContext(), R.color.sublevelseekbar_start_four), ContextCompat.getColor(getContext(), R.color.sublevelseekbar_end_four)}, (float[]) null, Shader.TileMode.CLAMP);
        this.z = new LinearGradient(0.0f, this.k, 0.0f, this.f3338c, new int[]{ContextCompat.getColor(getContext(), R.color.sublevelseekbar_start_three), ContextCompat.getColor(getContext(), R.color.sublevelseekbar_end_three)}, (float[]) null, Shader.TileMode.CLAMP);
        this.i = getResources().getDimensionPixelSize(R.dimen.dp_53);
        this.j = getResources().getDimensionPixelSize(R.dimen.dp_63);
        this.k = getResources().getDimensionPixelSize(R.dimen.dp_42);
        this.l = getResources().getDimensionPixelSize(R.dimen.dp_14);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_12);
        this.f3338c = dimensionPixelSize;
        this.g = dimensionPixelSize / 2.0f;
        this.h = getResources().getDimensionPixelSize(R.dimen.dp_10);
        this.n = getResources().getDimensionPixelSize(R.dimen.dp_20);
        this.o = getResources().getDimensionPixelSize(R.dimen.dp_17);
        this.f3341f = getResources().getDimensionPixelSize(R.dimen.dp_6);
        this.f3340e = getResources().getDimensionPixelSize(R.dimen.dp_2);
    }

    public final void b(MotionEvent motionEvent) {
        try {
            float x = motionEvent.getX();
            float f2 = this.q;
            float f3 = this.r;
            float f4 = f2 + f3;
            int i = this.j;
            float f5 = this.p;
            int i2 = this.f3339d;
            float f6 = 1.0f;
            if (x < i + (i2 * f5)) {
                f6 = 0.0f;
            } else if (x <= i + ((f2 + f5 + f3) * i2)) {
                f6 = (((x - i) - (f5 * i2)) * 1.0f) / (i2 * f4);
            }
            float fRound = Math.round((f6 * f4) * 100.0f) / 100.0f;
            this.q = fRound;
            float f7 = f4 - fRound;
            this.r = f7;
            setProgress(this.p, fRound, f7, this.s);
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b.b.m.c.a.c("----", "[进度条]----onDraw----");
        try {
            this.w.setShader(this.p == 0.0f ? this.y : this.x);
            int i = this.j;
            float f2 = this.g;
            canvas.drawRoundRect(i - f2, this.k, i + (this.p * this.f3339d) + f2, r1 + this.f3338c, f2, f2, this.w);
            this.w.setShader(this.y);
            int i2 = this.j;
            float f3 = this.p;
            int i3 = this.f3339d;
            canvas.drawRect(i2 + (i3 * f3), this.k, i2 + ((f3 + this.q) * i3), r1 + this.f3338c, this.w);
            this.w.setShader(this.s == 0.0f ? this.z : this.A);
            int i4 = this.j;
            float f4 = this.p + this.q + this.r;
            int i5 = this.f3339d;
            float f5 = this.g;
            canvas.drawRoundRect((i4 + (f4 * i5)) - f5, this.k, i4 + i5 + f5, r1 + this.f3338c, f5, f5, this.w);
            this.w.setShader(this.z);
            int i6 = this.j;
            float f6 = this.p;
            float f7 = this.q;
            int i7 = this.f3339d;
            canvas.drawRect(i6 + ((f6 + f7) * i7), this.k, i6 + ((f6 + f7 + this.r) * i7), r1 + this.f3338c, this.w);
            String str = this.F.format((this.p + this.q) * 100.0f) + "%";
            this.B.getTextBounds(str, 0, str.length(), this.m);
            canvas.drawText(str, this.j + (this.f3339d * (this.p + this.q)), this.m.height(), this.B);
            canvas.drawCircle(this.j + (this.f3339d * (this.p + this.q)), this.f3337b - this.n, this.h, this.D);
            for (int i8 = 0; i8 < 11; i8++) {
                float f8 = this.j + (i8 * 0.1f * this.f3339d);
                int i9 = this.f3340e;
                int i10 = this.f3337b;
                int i11 = this.o;
                canvas.drawRect(f8 - (i9 / 2.0f), (i10 - i11) - this.f3341f, f8 + (i9 / 2.0f), i10 - i11, this.D);
            }
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f3336a = View.MeasureSpec.getSize(i);
        this.f3337b = View.MeasureSpec.getSize(i2);
        this.f3339d = (this.f3336a - this.i) - this.j;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.t = false;
            this.u = motionEvent.getX();
            a aVar = this.G;
            if (aVar != null) {
                aVar.b(this.p + this.q);
            }
        } else if (action == 1) {
            if (this.t) {
                b(motionEvent);
                setPressed(false);
            } else {
                b(motionEvent);
            }
            a aVar2 = this.G;
            if (aVar2 != null) {
                aVar2.a(this.p + this.q);
            }
            invalidate();
        } else if (action != 2) {
            if (action == 3) {
                if (this.t) {
                    setPressed(false);
                }
                invalidate();
            }
        } else if (this.t) {
            b(motionEvent);
            invalidate();
        } else if (Math.abs(motionEvent.getX() - this.u) > this.v) {
            this.t = true;
        }
        return true;
    }

    public void setOnSeekBarChangeListener(a aVar) {
        this.G = aVar;
    }

    public void setProgress(float f2, float f3, float f4, float f5) {
        if (f2 + f3 + f4 + f5 != 1.0f) {
            return;
        }
        this.p = f2;
        this.q = f3;
        this.r = f4;
        this.s = f5;
        postInvalidate();
    }

    public SubLevelSeekbar(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = new Rect();
        this.t = false;
        this.u = 0.0f;
        this.v = 8.0f;
        a();
    }

    public SubLevelSeekbar(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.m = new Rect();
        this.t = false;
        this.u = 0.0f;
        this.v = 8.0f;
        a();
    }
}
