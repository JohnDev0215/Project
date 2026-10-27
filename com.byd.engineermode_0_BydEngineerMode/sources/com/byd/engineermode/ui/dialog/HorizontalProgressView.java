package com.byd.engineermode.ui.dialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import butterknife.R;

/* JADX INFO: loaded from: classes.dex */
public class HorizontalProgressView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Paint f2365b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Paint f2366c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Paint f2367d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private long j;
    private int k;
    private float l;
    private int m;

    public HorizontalProgressView(Context context) {
        super(context);
        this.g = 3;
        this.h = 5;
        this.i = 15;
        this.j = 3500L;
        this.m = -1;
        c();
    }

    private int a() {
        return (int) ((getContext().getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
    }

    private void b(Canvas canvas) {
        float f = this.l;
        if (f <= 10.0f && f >= 1.0f) {
            int i = this.g;
            int i2 = this.h;
            RectF rectF = new RectF(i + i2, ((((r7 - i) - i2) * (10.0f - f)) / 20.0f) + (((i + i2) * f) / 10.0f), ((this.e - i) - i2) * (f / 100.0f), ((this.f * (10.0f - f)) / 20.0f) + ((((r7 - i) - i2) * f) / 10.0f));
            int i3 = this.k;
            canvas.drawRoundRect(rectF, i3, i3, this.f2365b);
            return;
        }
        if (f != 0.0f) {
            int i4 = this.g;
            int i5 = this.h;
            RectF rectF2 = new RectF(i4 + i5, i4 + i5, ((this.e - i4) - i5) * (f / 100.0f), (this.f - i4) - i5);
            int i6 = this.k;
            canvas.drawRoundRect(rectF2, i6, i6, this.f2365b);
        }
    }

    private void c() {
        Paint paint = new Paint();
        this.f2366c = paint;
        paint.setColor(getResources().getColor(R.color.back));
        this.f2366c.setAntiAlias(true);
        this.f2366c.setStyle(Paint.Style.STROKE);
        this.f2366c.setStrokeWidth(this.h);
        Paint paint2 = new Paint();
        this.f2365b = paint2;
        paint2.setColor(getResources().getColor(R.color.inner));
        this.f2365b.setStyle(Paint.Style.FILL_AND_STROKE);
        this.f2365b.setAntiAlias(true);
        this.f2365b.setStrokeWidth(this.h);
        Paint paint3 = new Paint();
        this.f2367d = paint3;
        paint3.setAntiAlias(true);
        this.f2367d.setStyle(Paint.Style.FILL);
        this.f2367d.setColor(getResources().getColor(R.color.back));
        this.f2367d.setTextSize(d(this.i));
    }

    private int d(int i) {
        return (int) TypedValue.applyDimension(2, i, getResources().getDisplayMetrics());
    }

    private void drawBackground(Canvas canvas) {
        int i = this.g;
        RectF rectF = new RectF(i, i, this.e - i, this.f - i);
        int i2 = this.k;
        canvas.drawRoundRect(rectF, i2, i2, this.f2366c);
    }

    private void e(Canvas canvas) {
        if (this.m == -1) {
            this.m = R.string.copy_completed;
        }
        String string = getResources().getString(this.m);
        getResources().getString(R.string.defaultText);
        int i = (int) this.l;
        String str = i + "%";
        Paint.FontMetrics fontMetrics = this.f2367d.getFontMetrics();
        int iMeasureText = (int) this.f2367d.measureText(string, 0, string.length());
        int iMeasureText2 = (int) this.f2367d.measureText(str, 0, str.length());
        int iCeil = (int) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
        int width = (getWidth() / 2) - (iMeasureText / 2);
        int width2 = (getWidth() / 2) - (iMeasureText2 / 2);
        int height = (getHeight() / 2) + (iCeil / 4);
        if (i < 100) {
            canvas.drawText(str, width2, height, this.f2367d);
        } else {
            canvas.drawText(string, width, height, this.f2367d);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        b(canvas);
        e(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824 || mode == Integer.MIN_VALUE) {
            this.e = size;
        } else {
            this.e = 0;
        }
        if (mode2 == Integer.MIN_VALUE || mode2 == 0) {
            this.f = a();
        } else {
            this.f = size2;
        }
        int i3 = this.f;
        this.k = i3 / 2;
        setMeasuredDimension(this.e, i3);
    }

    public void setDuration(long j) {
        this.j = j;
    }

    public void setFinishedTextId(int i) {
        com.byd.engineermode.utils.a.a(" setFinishedTextId finishedTextId " + i);
        this.m = i;
    }

    public void setPadding(int i) {
        this.g = i;
    }

    public void setProgress(int i) {
        com.byd.engineermode.utils.a.a(" SET_PROGRESS progress = " + i);
        if (i == 0) {
            i = 1;
        }
        this.l = i;
        invalidate();
    }

    public void setStrokeWidth(int i) {
        this.h = i;
    }

    public void setTextSize(int i) {
        this.i = i;
    }

    public HorizontalProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = 3;
        this.h = 5;
        this.i = 15;
        this.j = 3500L;
        this.m = -1;
        c();
    }

    public HorizontalProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = 3;
        this.h = 5;
        this.i = 15;
        this.j = 3500L;
        this.m = -1;
        c();
    }
}
