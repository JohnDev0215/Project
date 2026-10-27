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
import android.util.AttributeSet;
import android.view.View;
import com.byd.filemanager.R;
import com.byd.filemanager.g;
import com.byd.filemanager.k.b;
import com.byd.filemanager.k.c;

/* JADX INFO: loaded from: classes.dex */
public class ScaleView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3881b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3882c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3883d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3884e;
    private String f;
    private int g;
    private int h;
    private final Paint i;
    private final Paint j;
    private final Paint k;
    private Path l;
    private Shader m;
    private float n;
    private float o;

    public ScaleView(Context context) {
        this(context, null);
    }

    private int a(int i) {
        int iB = (int) c.b(40.0f);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? iB : size : Math.min(iB, size);
    }

    private int b(int i) {
        int iB = (int) c.b(100.0f);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? iB : size : Math.min(iB, size);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.n * this.f3881b;
        if (this.m == null) {
            this.m = new LinearGradient(0.0f, this.f3882c, r2 >> 1, 0.0f, getContext().getResources().getColor(R.color.gradient_start_sizeView), getContext().getResources().getColor(R.color.gradient_end_sizeView), Shader.TileMode.CLAMP);
        }
        this.j.setShader(this.m);
        if (this.l == null) {
            this.l = new Path();
            RectF rectF = new RectF(0.0f, 0.0f, this.f3881b, this.f3882c);
            Path path = this.l;
            float f2 = this.o;
            path.addRoundRect(rectF, f2, f2, Path.Direction.CW);
        }
        canvas.save();
        canvas.clipRect(f, 0.0f, this.f3881b, this.f3882c);
        this.k.setColor(this.g);
        this.k.setStyle(Paint.Style.FILL);
        canvas.restore();
        canvas.clipPath(this.l);
        canvas.drawRect(0.0f, 0.0f, f, this.f3882c, this.j);
        this.k.setColor(this.h);
        this.k.setStyle(Paint.Style.FILL);
        canvas.drawRect(f, 0.0f, this.f3881b, this.f3882c, this.k);
        canvas.drawText(this.f, (int) ((this.f3881b / 2) - (this.i.measureText(this.f) / 2.0f)), (int) ((this.f3882c / 2) - ((this.i.descent() + this.i.ascent()) / 2.0f)), this.i);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f3881b = b(i);
        int iA = a(i2);
        this.f3882c = iA;
        setMeasuredDimension(this.f3881b, iA);
    }

    public void setScale(float f) {
        this.n = f;
        b.a(Float.valueOf(f));
        invalidate();
    }

    public ScaleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScaleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.U0);
        this.f = typedArrayObtainStyledAttributes.getString(4);
        this.f3883d = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, context.getResources().getDimensionPixelSize(R.dimen.sp_16));
        this.f3884e = typedArrayObtainStyledAttributes.getColor(5, -16777216);
        this.g = typedArrayObtainStyledAttributes.getColor(3, context.getResources().getColor(R.color.color_highlight));
        this.h = typedArrayObtainStyledAttributes.getColor(0, context.getResources().getColor(R.color.solid_sizeView));
        TextPaint textPaint = new TextPaint(1);
        this.i = textPaint;
        textPaint.setColor(this.f3884e);
        textPaint.setTextSize(this.f3883d);
        Paint paint = new Paint();
        this.j = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        Paint paint2 = new Paint();
        this.k = paint2;
        paint.setAntiAlias(true);
        paint2.setColor(this.h);
        this.o = context.getResources().getDimensionPixelSize(R.dimen.dp_12);
        this.m = new LinearGradient(0.0f, this.f3882c, r10 >> 1, 0.0f, getContext().getResources().getColor(R.color.gradient_start_sizeView), getContext().getResources().getColor(R.color.gradient_end_sizeView), Shader.TileMode.CLAMP);
    }

    public void setScale(com.byd.filemanager.bean.c cVar) {
        setScale(cVar.b());
        this.f = cVar.c();
    }
}
