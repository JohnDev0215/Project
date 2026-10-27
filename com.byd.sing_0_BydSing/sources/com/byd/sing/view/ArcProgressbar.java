package com.byd.sing.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.byd.sing.c;
import com.byd.sing.h;

/* JADX INFO: loaded from: classes.dex */
public class ArcProgressbar extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f3284b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f3285c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Paint f3286d;
    private final float e;
    private int f;
    private int g;
    private RectF h;
    private final int i;
    private final int j;
    private final int k;
    private int l;

    public ArcProgressbar(Context context) {
        this(context, null);
    }

    private int a(int i, int i2) {
        int iB = (int) c.b(i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? iB : size : Math.min(iB, size);
    }

    public int getProgress() {
        return this.l;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.h == null) {
            float f = this.e;
            int i = this.f;
            this.h = new RectF(f / 2.0f, f / 2.0f, i - (f / 2.0f), i - (f / 2.0f));
        }
        this.f3286d.setColor(this.i);
        if (this.f3284b == 0.0f) {
            int i2 = this.f;
            float degrees = ((float) Math.toDegrees(Math.acos((this.g - (i2 / 2.0f)) / (i2 / 2.0f)))) + 90.0f + 2.0f;
            this.f3284b = degrees;
            this.f3285c = 360.0f - ((degrees - 90.0f) * 2.0f);
        }
        canvas.drawArc(this.h, this.f3284b, this.f3285c, false, this.f3286d);
        this.f3286d.setColor(this.j);
        canvas.drawArc(this.h, this.f3284b, ((this.l * 1.0f) / this.k) * this.f3285c, false, this.f3286d);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f = a(i, 233);
        int iA = a(i2, 178);
        this.g = iA;
        setMeasuredDimension(this.f, iA);
    }

    public void setProgress(int i) {
        this.l = Math.min(i, this.k);
        invalidate();
    }

    public ArcProgressbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ArcProgressbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3284b = 0.0f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, h.s);
        float dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, (int) c.b(2.0f));
        this.e = dimensionPixelSize;
        this.i = typedArrayObtainStyledAttributes.getColor(0, -16777216);
        this.j = typedArrayObtainStyledAttributes.getColor(1, -1);
        this.l = typedArrayObtainStyledAttributes.getInt(3, 0);
        this.k = typedArrayObtainStyledAttributes.getInt(2, 100);
        typedArrayObtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.f3286d = paint;
        paint.setStrokeWidth(dimensionPixelSize);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }
}
