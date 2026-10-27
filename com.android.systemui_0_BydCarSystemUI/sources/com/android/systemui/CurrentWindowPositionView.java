package com.android.systemui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class CurrentWindowPositionView extends View {
    private Paint mPaint;

    public CurrentWindowPositionView(Context context) {
        this(context, null);
    }

    public CurrentWindowPositionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(Color.parseColor("#00FF99"));
        this.mPaint.setStrokeWidth(20.0f);
    }
}
