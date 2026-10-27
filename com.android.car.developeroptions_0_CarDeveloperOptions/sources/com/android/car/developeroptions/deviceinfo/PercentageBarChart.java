package com.android.car.developeroptions.deviceinfo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.android.car.developeroptions.R;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class PercentageBarChart extends View {
    private final Paint mEmptyPaint;
    private Collection<Entry> mEntries;
    private int mMinTickWidth;

    public static class Entry implements Comparable<Entry> {
        public final int order;
        public final Paint paint;
        public final float percentage;

        @Override // java.lang.Comparable
        public int compareTo(Entry entry) {
            return this.order - entry.order;
        }
    }

    public PercentageBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mEmptyPaint = new Paint();
        this.mMinTickWidth = 1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PercentageBarChart);
        this.mMinTickWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 1);
        int color = typedArrayObtainStyledAttributes.getColor(0, -16777216);
        typedArrayObtainStyledAttributes.recycle();
        this.mEmptyPaint.setColor(color);
        this.mEmptyPaint.setStyle(Paint.Style.FILL);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f;
        float f2;
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int width = getWidth() - getPaddingRight();
        int paddingTop = getPaddingTop();
        int height = getHeight() - getPaddingBottom();
        int i = width - paddingLeft;
        if (isLayoutRtl()) {
            float f3 = width;
            Collection<Entry> collection = this.mEntries;
            if (collection != null) {
                float f4 = f3;
                for (Entry entry : collection) {
                    float f5 = entry.percentage;
                    float fMax = f4 - (f5 == 0.0f ? 0.0f : Math.max(this.mMinTickWidth, i * f5));
                    float f6 = paddingLeft;
                    if (fMax < f6) {
                        canvas.drawRect(f6, paddingTop, f4, height, entry.paint);
                        return;
                    } else {
                        canvas.drawRect(fMax, paddingTop, f4, height, entry.paint);
                        f4 = fMax;
                    }
                }
                f2 = f4;
            } else {
                f2 = f3;
            }
            canvas.drawRect(paddingLeft, paddingTop, f2, height, this.mEmptyPaint);
            return;
        }
        float f7 = paddingLeft;
        Collection<Entry> collection2 = this.mEntries;
        if (collection2 != null) {
            float f8 = f7;
            for (Entry entry2 : collection2) {
                float f9 = entry2.percentage;
                float fMax2 = f8 + (f9 == 0.0f ? 0.0f : Math.max(this.mMinTickWidth, i * f9));
                float f10 = width;
                if (fMax2 > f10) {
                    canvas.drawRect(f8, paddingTop, f10, height, entry2.paint);
                    return;
                } else {
                    canvas.drawRect(f8, paddingTop, fMax2, height, entry2.paint);
                    f8 = fMax2;
                }
            }
            f = f8;
        } else {
            f = f7;
        }
        canvas.drawRect(f, paddingTop, width, height, this.mEmptyPaint);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mEmptyPaint.setColor(i);
    }

    public void setEntries(Collection<Entry> collection) {
        this.mEntries = collection;
    }
}
