package com.byd.filemanager.ui.view;

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
import com.byd.filemanager.R;
import com.byd.filemanager.bean.c;
import com.byd.filemanager.g;

/* JADX INFO: loaded from: classes.dex */
public class CapacityView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3864b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3865c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Paint f3866d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Paint f3867e;
    private String f;
    private float g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private float m;
    private Shader n;
    private int o;
    private float p;
    private Path q;
    private Path r;
    private int s;

    public CapacityView(Context context) {
        this(context, null);
    }

    private int a(int i) {
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.dp_48);
        int mode = View.MeasureSpec.getMode(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? dimensionPixelSize : View.MeasureSpec.getSize(i) : Math.max(dimensionPixelSize, (int) this.f3866d.measureText(this.f));
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
        return (int) this.f3866d.measureText(this.f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.n = new LinearGradient(0.0f, this.f3865c, r0 >> 1, 0.0f, this.j, this.k, Shader.TileMode.CLAMP);
        this.q = new Path();
        int i = this.f3865c - this.o;
        int i2 = this.s;
        RectF rectF = new RectF(0.0f, i - i2, this.f3864b, r1 - i2);
        Path path = this.q;
        float f = this.p;
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
        this.f3867e.setColor(this.l);
        this.f3867e.setShader(null);
        canvas.drawPath(this.q, this.f3867e);
        int i3 = this.f3865c - this.o;
        int i4 = this.s;
        RectF rectF2 = new RectF(0.0f, i3 - i4, this.f3864b * this.m, r1 - i4);
        this.r.reset();
        Path path2 = this.r;
        float f2 = this.p;
        path2.addRoundRect(rectF2, f2, f2, Path.Direction.CW);
        this.f3867e.setColor(this.i);
        this.f3867e.setShader(this.n);
        canvas.drawPath(this.r, this.f3867e);
        canvas.drawText(this.f, 0.0f, (int) ((this.f3865c / 2) - ((this.f3866d.descent() + this.f3866d.ascent()) / 2.0f)), this.f3866d);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f3864b = b(i);
        int iA = a(i2);
        this.f3865c = iA;
        setMeasuredDimension(this.f3864b, iA);
    }

    public void setScale(c cVar) {
        this.m = cVar.b();
        this.f = cVar.c();
        invalidate();
        if (getTextWidth() != this.f3864b) {
            requestLayout();
        }
    }

    public CapacityView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CapacityView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = "";
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.U0);
        this.f = typedArrayObtainStyledAttributes.getString(4);
        this.g = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, context.getResources().getDimensionPixelSize(R.dimen.sp_18));
        this.h = typedArrayObtainStyledAttributes.getColor(5, context.getResources().getColor(R.color.text_main));
        this.i = typedArrayObtainStyledAttributes.getColor(3, context.getResources().getColor(R.color.color_highlight));
        this.l = typedArrayObtainStyledAttributes.getColor(0, context.getResources().getColor(R.color.solid_sizeView));
        this.j = typedArrayObtainStyledAttributes.getColor(2, context.getResources().getColor(R.color.gradient_start_sizeView));
        this.k = typedArrayObtainStyledAttributes.getColor(1, context.getResources().getColor(R.color.gradient_end_sizeView));
        TextPaint textPaint = new TextPaint(1);
        this.f3866d = textPaint;
        textPaint.setTextSize(this.g);
        textPaint.setColor(this.h);
        Paint paint = new Paint();
        this.f3867e = paint;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.dp_3);
        this.o = dimensionPixelSize;
        paint.setStrokeWidth(dimensionPixelSize);
        this.p = context.getResources().getDimensionPixelSize(R.dimen.dp_12);
        this.s = context.getResources().getDimensionPixelSize(R.dimen.dp_8);
        typedArrayObtainStyledAttributes.recycle();
        this.r = new Path();
    }
}
