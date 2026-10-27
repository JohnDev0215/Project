package com.byd.logtools.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import com.byd.logtools.R;

/* JADX INFO: loaded from: classes.dex */
public class BydCircleTextView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3210b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Paint f3211c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Path f3212d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3213e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f3214f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f3215g;

    public BydCircleTextView(Context context) {
        super(context);
        this.f3214f = 180.0f;
        this.f3213e = context.getResources().getString(R.string.str_save_log_running);
    }

    private void a(Canvas canvas, float f2, float f3, float f4, float f5, float f6) {
        this.f3212d.moveTo(f2, f3);
        this.f3212d.addArc(new RectF(f2 - f4, f3 - f4, f2 + f4, f3 + f4), f5 % 360.0f, f6);
        canvas.drawTextOnPath(this.f3213e, this.f3212d, -10.0f, -10.0f, this.f3211c);
    }

    public void b(String str, int i) {
        this.f3213e = str;
        this.f3211c.setColor(i);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f3212d.reset();
        a(canvas, this.f3215g / 2, this.f3210b / 2, 150.0f, this.f3214f, 120.0f);
        this.f3214f += 1.0f;
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f3215g = View.getDefaultSize(getSuggestedMinimumWidth(), i);
        int defaultSize = View.getDefaultSize(getSuggestedMinimumHeight(), i2);
        this.f3210b = defaultSize;
        setMeasuredDimension(this.f3215g, defaultSize);
    }

    public BydCircleTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3214f = 180.0f;
        this.f3213e = context.getResources().getString(R.string.str_save_log_running);
        this.f3212d = new Path();
        this.f3211c = new Paint();
        this.f3211c.setTypeface(Typeface.create("familyName", 0));
        this.f3211c.setAntiAlias(true);
        this.f3211c.setTextSize(30.0f);
        this.f3211c.setStrokeWidth(3.0f);
        this.f3211c.setStyle(Paint.Style.STROKE);
    }
}
