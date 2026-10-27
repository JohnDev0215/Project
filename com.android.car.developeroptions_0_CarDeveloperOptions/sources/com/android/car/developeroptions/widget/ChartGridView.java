package com.android.car.developeroptions.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class ChartGridView extends View {
    private Drawable mBorder;
    private ChartAxis mHoriz;
    private Layout mLabelEnd;
    private Layout mLabelMid;
    private Layout mLabelStart;
    private Drawable mPrimary;
    private Drawable mSecondary;
    private ChartAxis mVert;

    public ChartGridView(Context context) {
        this(context, null, 0);
    }

    public ChartGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChartGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setWillNotDraw(false);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ChartGridView, i, 0);
        this.mPrimary = typedArrayObtainStyledAttributes.getDrawable(3);
        this.mSecondary = typedArrayObtainStyledAttributes.getDrawable(4);
        this.mBorder = typedArrayObtainStyledAttributes.getDrawable(2);
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(typedArrayObtainStyledAttributes.getResourceId(0, -1), com.android.internal.R.styleable.TextAppearance);
        typedArrayObtainStyledAttributes2.getDimensionPixelSize(0, 0);
        typedArrayObtainStyledAttributes2.recycle();
        typedArrayObtainStyledAttributes.getColorStateList(1).getDefaultColor();
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight() - getPaddingBottom();
        Drawable drawable = this.mSecondary;
        if (drawable != null) {
            int intrinsicHeight = drawable.getIntrinsicHeight();
            for (float f : this.mVert.getTickPoints()) {
                drawable.setBounds(0, (int) f, width, (int) Math.min(intrinsicHeight + f, height));
                drawable.draw(canvas);
            }
        }
        Drawable drawable2 = this.mPrimary;
        if (drawable2 != null) {
            int intrinsicWidth = drawable2.getIntrinsicWidth();
            drawable2.getIntrinsicHeight();
            for (float f2 : this.mHoriz.getTickPoints()) {
                drawable2.setBounds((int) f2, 0, (int) Math.min(intrinsicWidth + f2, width), height);
                drawable2.draw(canvas);
            }
        }
        this.mBorder.setBounds(0, 0, width, height);
        this.mBorder.draw(canvas);
        Layout layout = this.mLabelStart;
        int height2 = layout != null ? layout.getHeight() / 8 : 0;
        Layout layout2 = this.mLabelStart;
        if (layout2 != null) {
            int iSave = canvas.save();
            canvas.translate(0.0f, height + height2);
            layout2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        Layout layout3 = this.mLabelMid;
        if (layout3 != null) {
            int iSave2 = canvas.save();
            canvas.translate((width - layout3.getWidth()) / 2, height + height2);
            layout3.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        Layout layout4 = this.mLabelEnd;
        if (layout4 != null) {
            int iSave3 = canvas.save();
            canvas.translate(width - layout4.getWidth(), height + height2);
            layout4.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
    }
}
