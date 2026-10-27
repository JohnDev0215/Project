package com.byd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import com.byd.auto_photo.widget.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class RoundCornerContainer extends FrameLayout {
    private int radius;
    private RectF rectF;

    public RoundCornerContainer(Context context) {
        this(context, null);
    }

    public RoundCornerContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundCornerContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.rectF = new RectF();
        setLayerType(1, null);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RoundCornerContainer);
        this.radius = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.RoundCornerContainer_clip_corner_radius, 0);
        typedArrayObtainStyledAttributes.recycle();
        setOutlineProvider(new ViewOutlineProvider() { // from class: com.byd.view.RoundCornerContainer.1
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect((int) RoundCornerContainer.this.rectF.left, (int) RoundCornerContainer.this.rectF.top, (int) RoundCornerContainer.this.rectF.right, (int) RoundCornerContainer.this.rectF.bottom, RoundCornerContainer.this.radius);
            }
        });
        setClipToOutline(true);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.rectF.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
    }

    public void setRadius(int i) {
        this.radius = i;
    }
}
