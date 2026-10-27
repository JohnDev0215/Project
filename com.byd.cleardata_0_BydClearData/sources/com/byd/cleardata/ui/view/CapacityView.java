package com.byd.cleardata.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.byd.cleardata.R;
import com.byd.cleardata.c;
import com.byd.cleardata.d.g;

/* JADX INFO: loaded from: classes.dex */
public class CapacityView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2913b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2914c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Paint f2915d;
    private final Paint e;
    private String f;
    private float g;
    private int h;
    private int i;
    private int j;
    private float k;
    private Shader l;
    private int m;
    private float n;
    private Path o;
    private Path p;
    private int q;

    public CapacityView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CapacityView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = "";
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.U0);
        this.f = typedArrayObtainStyledAttributes.getString(2);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, context.getResources().getDimensionPixelSize(R.dimen.sp_18));
        this.h = typedArrayObtainStyledAttributes.getColor(3, context.getResources().getColor(R.color.text_main));
        this.i = typedArrayObtainStyledAttributes.getColor(1, context.getResources().getColor(R.color.color_highlight));
        this.j = typedArrayObtainStyledAttributes.getColor(0, context.getResources().getColor(R.color.solid_sizeView));
        TextPaint textPaint = new TextPaint(1);
        this.f2915d = textPaint;
        textPaint.setTextSize(this.g);
        textPaint.setColor(this.h);
        Paint paint = new Paint();
        this.e = paint;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.dp_3);
        this.m = dimensionPixelSize;
        paint.setStrokeWidth(dimensionPixelSize);
        this.n = context.getResources().getDimensionPixelSize(R.dimen.dp_12);
        this.q = context.getResources().getDimensionPixelSize(R.dimen.dp_8);
        typedArrayObtainStyledAttributes.recycle();
        this.p = new Path();
    }

    private int a(int i) {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.dp_48);
        int mode = View.MeasureSpec.getMode(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? dimensionPixelSize : View.MeasureSpec.getSize(i) : Math.max(dimensionPixelSize, (int) this.f2915d.measureText(this.f));
    }

    private int b(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int iMax = Math.max(getContext().getResources().getDimensionPixelSize(R.dimen.dp_126), getTextWidth());
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? iMax : size : Math.min(iMax, size);
    }

    private int getTextWidth() {
        if (TextUtils.isEmpty(this.f)) {
            return 0;
        }
        return (int) this.f2915d.measureText(this.f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.l = new LinearGradient(0.0f, this.f2914c, r0 >> 1, 0.0f, getContext().getResources().getColor(R.color.gradient_start_sizeView), getContext().getResources().getColor(R.color.gradient_end_sizeView), Shader.TileMode.CLAMP);
        this.o = new Path();
        int i = this.f2914c - this.m;
        int i2 = this.q;
        RectF rectF = new RectF(0.0f, i - i2, this.f2913b, r1 - i2);
        Path path = this.o;
        float f = this.n;
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
        this.e.setColor(this.j);
        this.e.setShader(null);
        canvas.drawPath(this.o, this.e);
        int i3 = this.f2914c - this.m;
        int i4 = this.q;
        RectF rectF2 = new RectF(0.0f, i3 - i4, this.f2913b * this.k, r1 - i4);
        this.p.reset();
        Path path2 = this.p;
        float f2 = this.n;
        path2.addRoundRect(rectF2, f2, f2, Path.Direction.CW);
        this.e.setColor(this.i);
        this.e.setShader(this.l);
        canvas.drawPath(this.p, this.e);
        canvas.drawText(this.f, 0.0f, (int) ((this.f2914c / 2) - ((this.f2915d.descent() + this.f2915d.ascent()) / 2.0f)), this.f2915d);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f2913b = b(i);
        int iA = a(i2);
        this.f2914c = iA;
        setMeasuredDimension(this.f2913b, iA);
    }

    public void setScale(g gVar) {
        this.k = gVar.a();
        this.f = gVar.b();
        invalidate();
        if (getTextWidth() != this.f2913b) {
            requestLayout();
        }
    }
}
