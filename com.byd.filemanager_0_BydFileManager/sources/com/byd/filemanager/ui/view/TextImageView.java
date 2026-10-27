package com.byd.filemanager.ui.view;

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
import com.byd.filemanager.R;
import com.byd.filemanager.g;

/* JADX INFO: loaded from: classes.dex */
public class TextImageView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3889b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f3890c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f3891d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f3892e;
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
    private boolean s;

    public TextImageView(Context context) {
        this(context, null);
    }

    private int a(int i) {
        int iMax = Math.max((int) this.f3891d, getTextHeight());
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? iMax : size : Math.min(iMax, size);
    }

    private int b(int i) {
        int textWidth = (int) (getTextWidth() + this.f3890c + this.f3892e);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? textWidth : size : Math.min(textWidth, size);
    }

    private int getTextHeight() {
        if (this.f3889b == null) {
            return 0;
        }
        Rect rect = new Rect();
        Paint paint = new Paint();
        String str = this.f3889b;
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }

    private int getTextWidth() {
        String str = this.f3889b;
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
            this.o = new Rect(0, 0, (int) this.f3890c, this.k);
        }
        if (isEnabled()) {
            this.l.setColorFilter(this.p ? this.q : null);
            this.i.setColor(this.p ? this.h : this.g);
        } else {
            this.i.setColor(getResources().getColor(R.color.text_tip));
            this.l.setColorFilter(this.r);
        }
        if (this.s) {
            canvas.drawBitmap(this.m, getTextWidth() + this.f3892e, 0.0f, this.l);
            canvas.drawText(this.f3889b, 0, (int) ((this.k / 2) - ((this.i.descent() + this.i.ascent()) / 2.0f)), this.i);
        } else {
            canvas.drawBitmap(this.m, this.n, this.o, this.l);
            canvas.drawText(this.f3889b, (int) (this.f3890c + this.f3892e), (int) ((this.k / 2) - ((this.i.descent() + this.i.ascent()) / 2.0f)), this.i);
        }
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
        int action = motionEvent.getAction();
        if (action == 0) {
            this.p = true;
            postInvalidate();
        } else if (action == 1 || action == 3 || action == 4) {
            this.p = false;
            postInvalidate();
        }
        return super.onTouchEvent(motionEvent);
    }

    public TextImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.n1);
        this.m = BitmapFactory.decodeResource(getResources(), typedArrayObtainStyledAttributes.getResourceId(6, R.mipmap.ic_launcher));
        this.f3889b = typedArrayObtainStyledAttributes.getString(7);
        this.f3890c = typedArrayObtainStyledAttributes.getDimension(1, 0.0f);
        this.f3891d = typedArrayObtainStyledAttributes.getDimension(0, 0.0f);
        this.f3892e = typedArrayObtainStyledAttributes.getDimension(4, 0.0f);
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, 0);
        this.g = typedArrayObtainStyledAttributes.getColor(8, -16777216);
        this.h = typedArrayObtainStyledAttributes.getColor(5, getResources().getColor(R.color.color_highlight));
        this.s = typedArrayObtainStyledAttributes.getBoolean(3, false);
        TextPaint textPaint = new TextPaint(1);
        this.i = textPaint;
        textPaint.setColor(this.g);
        this.i.setTextSize(this.f);
        this.l = new TextPaint(1);
        this.n = new Rect(0, 0, this.m.getWidth(), this.m.getHeight());
        this.q = new PorterDuffColorFilter(this.h, PorterDuff.Mode.SRC_IN);
        this.r = new PorterDuffColorFilter(getResources().getColor(R.color.disable), PorterDuff.Mode.SRC_IN);
    }
}
