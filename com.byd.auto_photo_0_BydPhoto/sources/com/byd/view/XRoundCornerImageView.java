package com.byd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.byd.auto_photo.R;

/* JADX INFO: loaded from: classes2.dex */
public class XRoundCornerImageView extends ImageView {
    private int borderColor;
    private int borderWidth;
    private int height;
    private Paint paint;
    private Path path;
    private int radius;
    private int roundBgColor;
    private RectF roundBound;
    private int width;
    private PorterDuffXfermode xfermode;

    public XRoundCornerImageView(Context context) {
        this(context, null);
    }

    public XRoundCornerImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XRoundCornerImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XRoundCornerImageView);
        this.radius = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, 12);
        this.borderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.borderColor = typedArrayObtainStyledAttributes.getColor(0, -10066330);
        this.roundBgColor = typedArrayObtainStyledAttributes.getColor(3, 0);
        typedArrayObtainStyledAttributes.recycle();
        init(context);
    }

    private void init(Context context) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
        this.path = new Path();
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.roundBound = new RectF();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        int i2;
        int i3 = this.radius;
        if (i3 <= 0 || (i = this.width) < i3 || (i2 = this.height) < i3) {
            super.onDraw(canvas);
            return;
        }
        this.roundBound.set(0.0f, 0.0f, i, i2);
        if (this.borderWidth > 0) {
            this.paint.setColor(this.borderColor);
            RectF rectF = this.roundBound;
            int i4 = this.radius;
            canvas.drawRoundRect(rectF, i4, i4, this.paint);
            RectF rectF2 = this.roundBound;
            int i5 = this.borderWidth;
            rectF2.set(i5, i5, this.width - i5, this.height - i5);
            this.paint.setXfermode(this.xfermode);
            RectF rectF3 = this.roundBound;
            int i6 = this.radius;
            canvas.drawRoundRect(rectF3, i6, i6, this.paint);
            this.paint.setXfermode(null);
        }
        this.path.reset();
        Path path = this.path;
        RectF rectF4 = this.roundBound;
        int i7 = this.radius;
        path.addRoundRect(rectF4, i7, i7, Path.Direction.CW);
        canvas.save();
        canvas.clipPath(this.path);
        int i8 = this.roundBgColor;
        if (i8 != 0) {
            this.paint.setColor(i8);
            RectF rectF5 = this.roundBound;
            int i9 = this.borderWidth;
            rectF5.set(i9, i9, this.width - i9, this.height - i9);
            RectF rectF6 = this.roundBound;
            int i10 = this.radius;
            canvas.drawRoundRect(rectF6, i10, i10, this.paint);
        }
        if ((getDrawable() instanceof BitmapDrawable) && (getDrawable() == null || ((BitmapDrawable) getDrawable()).getBitmap() == null || ((BitmapDrawable) getDrawable()).getBitmap().isRecycled())) {
            canvas.restore();
        } else {
            super.onDraw(canvas);
            canvas.restore();
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.width = getWidth();
        this.height = getHeight();
    }

    public XRoundCornerImageView setBorderColor(int i) {
        this.borderColor = i;
        return this;
    }

    public XRoundCornerImageView setBorderWidth(int i) {
        this.borderWidth = i;
        return this;
    }

    public XRoundCornerImageView setRadius(int i) {
        this.radius = i;
        return this;
    }

    public XRoundCornerImageView setRoundBgColor(int i) {
        this.roundBgColor = i;
        return this;
    }
}
