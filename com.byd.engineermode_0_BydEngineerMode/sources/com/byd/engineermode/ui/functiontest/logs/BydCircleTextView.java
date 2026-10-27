package com.byd.engineermode.ui.functiontest.logs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import butterknife.R;

/* JADX INFO: loaded from: classes.dex */
public class BydCircleTextView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2467b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Paint f2468c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Path f2469d;
    private String e;
    private float f;
    private int g;

    public BydCircleTextView(Context context) {
        super(context);
        this.f = 180.0f;
        this.e = context.getResources().getString(R.string.str_save_log_running);
    }

    private void a(Canvas canvas, float f, float f2, float f3, float f4, float f5) {
        this.f2469d.moveTo(f, f2);
        this.f2469d.addArc(new RectF(f - f3, f2 - f3, f + f3, f2 + f3), f4 % 360.0f, f5);
        canvas.drawTextOnPath(this.e, this.f2469d, -10.0f, -10.0f, this.f2468c);
    }

    public void b(String str, int i) {
        this.e = str;
        this.f2468c.setColor(i);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f2469d.reset();
        a(canvas, this.g / 2, this.f2467b / 2, 150.0f, this.f, 120.0f);
        this.f += 1.0f;
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.g = View.getDefaultSize(getSuggestedMinimumWidth(), i);
        int defaultSize = View.getDefaultSize(getSuggestedMinimumHeight(), i2);
        this.f2467b = defaultSize;
        setMeasuredDimension(this.g, defaultSize);
    }

    public BydCircleTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = 180.0f;
        this.e = context.getResources().getString(R.string.str_save_log_running);
        this.f2469d = new Path();
        this.f2468c = new Paint();
        this.f2468c.setTypeface(Typeface.create("familyName", 0));
        this.f2468c.setAntiAlias(true);
        this.f2468c.setTextSize(30.0f);
        this.f2468c.setStrokeWidth(3.0f);
        this.f2468c.setStyle(Paint.Style.STROKE);
    }
}
