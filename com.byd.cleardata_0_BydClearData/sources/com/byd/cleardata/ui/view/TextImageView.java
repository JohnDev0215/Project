package com.byd.cleardata.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.byd.cleardata.R;
import com.byd.cleardata.c;

/* JADX INFO: loaded from: classes.dex */
public class TextImageView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2920b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f2921c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f2922d;
    private float e;
    private float f;
    private int g;
    private int h;
    private Paint i;
    private int j;
    private int k;
    private Paint l;
    private Bitmap m;
    private Rect n;
    private Rect o;
    private boolean p;
    private ColorFilter q;
    private ColorFilter r;

    public TextImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.n1);
        this.m = BitmapFactory.decodeResource(getResources(), typedArrayObtainStyledAttributes.getResourceId(5, R.mipmap.ic_launcher));
        this.f2920b = typedArrayObtainStyledAttributes.getString(6);
        this.f2921c = typedArrayObtainStyledAttributes.getDimension(1, 0.0f);
        this.f2922d = typedArrayObtainStyledAttributes.getDimension(0, 0.0f);
        this.e = typedArrayObtainStyledAttributes.getDimension(3, 0.0f);
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, 0);
        this.g = typedArrayObtainStyledAttributes.getColor(7, -16777216);
        this.h = typedArrayObtainStyledAttributes.getColor(4, getResources().getColor(R.color.color_highlight));
        TextPaint textPaint = new TextPaint(1);
        this.i = textPaint;
        textPaint.setColor(this.g);
        this.i.setTextSize(this.f);
        this.l = new TextPaint(1);
        this.n = new Rect(0, 0, this.m.getWidth(), this.m.getHeight());
        this.q = new PorterDuffColorFilter(this.h, PorterDuff.Mode.SRC_IN);
        this.r = new PorterDuffColorFilter(getResources().getColor(R.color.disable), PorterDuff.Mode.SRC_IN);
    }

    private int a(int i) {
        int iMax = Math.max((int) this.f2922d, getTextHeight());
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? iMax : size : Math.min(iMax, size);
    }

    private int b(int i) {
        int textWidth = (int) (getTextWidth() + this.f2921c + this.e);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? textWidth : size : Math.min(textWidth, size);
    }

    private int getTextHeight() {
        if (this.f2920b == null) {
            return 0;
        }
        Rect rect = new Rect();
        Paint paint = new Paint();
        String str = this.f2920b;
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }

    private int getTextWidth() {
        String str = this.f2920b;
        if (str != null) {
            return (int) this.i.measureText(str);
        }
        return 0;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap bitmap = this.m;
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.o == null) {
            this.o = new Rect(0, 0, (int) this.f2921c, this.k);
        }
        if (isEnabled()) {
            this.l.setColorFilter(this.p ? this.q : null);
            this.i.setColor(this.p ? this.h : this.g);
        } else {
            this.i.setColor(getResources().getColor(R.color.text_tip));
            this.l.setColorFilter(this.r);
        }
        canvas.drawBitmap(this.m, this.n, this.o, this.l);
        canvas.drawText(this.f2920b, (int) (this.f2921c + this.e), (int) ((this.k / 2) - ((this.i.descent() + this.i.ascent()) / 2.0f)), this.i);
    }

    @Override // android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        this.p = z;
        postInvalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.j = b(i);
        int iA = a(i2);
        this.k = iA;
        setMeasuredDimension(this.j, iA);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        com.byd.cleardata.i.c.b("onTouchEvent");
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1 || action == 3 || action == 4) {
                this.p = false;
            }
            return super.onTouchEvent(motionEvent);
        }
        this.p = true;
        postInvalidate();
        return super.onTouchEvent(motionEvent);
    }
}
