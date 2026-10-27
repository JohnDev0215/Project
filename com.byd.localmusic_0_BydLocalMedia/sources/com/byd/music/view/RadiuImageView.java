package com.byd.music.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public class RadiuImageView extends AppCompatImageView {
    private int defaultRadius;
    private float height;
    private int leftBottomRadius;
    private int leftTopRadius;
    private int radius;
    private int rightBottomRadius;
    private int rightTopRadius;
    private float width;

    public RadiuImageView(Context context) {
        this(context, null);
        init(context, null);
    }

    public RadiuImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        init(context, attributeSet);
    }

    public RadiuImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.defaultRadius = 0;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RoundCornerImageView);
        this.radius = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.RoundCornerImageView_round_corner_radius, this.defaultRadius);
        this.leftTopRadius = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.RoundCornerImageView_left_top_radius, this.defaultRadius);
        this.rightTopRadius = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.RoundCornerImageView_right_top_radius, this.defaultRadius);
        this.rightBottomRadius = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.RoundCornerImageView_right_bottom_radius, this.defaultRadius);
        this.leftBottomRadius = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.RoundCornerImageView_left_bottom_radius, this.defaultRadius);
        if (this.defaultRadius == this.leftTopRadius) {
            this.leftTopRadius = this.radius;
        }
        if (this.defaultRadius == this.rightTopRadius) {
            this.rightTopRadius = this.radius;
        }
        if (this.defaultRadius == this.rightBottomRadius) {
            this.rightBottomRadius = this.radius;
        }
        if (this.defaultRadius == this.leftBottomRadius) {
            this.leftBottomRadius = this.radius;
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.width = getWidth();
        this.height = getHeight();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        int iMax = Math.max(this.leftTopRadius, this.leftBottomRadius) + Math.max(this.rightTopRadius, this.rightBottomRadius);
        int iMax2 = Math.max(this.leftTopRadius, this.rightTopRadius) + Math.max(this.leftBottomRadius, this.rightBottomRadius);
        if (this.width >= iMax && this.height > iMax2) {
            Path path = new Path();
            path.moveTo(this.leftTopRadius, 0.0f);
            path.lineTo(this.width - this.rightTopRadius, 0.0f);
            float f = this.width;
            path.quadTo(f, 0.0f, f, this.rightTopRadius);
            path.lineTo(this.width, this.height - this.rightBottomRadius);
            float f2 = this.width;
            float f3 = this.height;
            path.quadTo(f2, f3, f2 - this.rightBottomRadius, f3);
            path.lineTo(this.leftBottomRadius, this.height);
            float f4 = this.height;
            path.quadTo(0.0f, f4, 0.0f, f4 - this.leftBottomRadius);
            path.lineTo(0.0f, this.leftTopRadius);
            path.quadTo(0.0f, 0.0f, this.leftTopRadius, 0.0f);
            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}
