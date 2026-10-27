package org.chromium.chrome.browser.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import defpackage.yp;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class ShapeTextView extends AppCompatTextView {
    public int k;
    public float l;
    public Paint m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;

    public ShapeTextView(Context context) {
        super(context);
        q();
    }

    @Override // android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.n) {
            canvas.drawLine(0.0f, 0.0f, 0.0f, getHeight(), this.m);
        }
        if (this.o) {
            canvas.drawLine(getWidth(), 0.0f, getWidth(), getHeight(), this.m);
        }
        if (this.p) {
            canvas.drawLine(0.0f, 0.0f, getWidth(), 0.0f, this.m);
        }
        if (this.q) {
            canvas.drawLine(0.0f, getHeight(), getWidth(), getHeight(), this.m);
        }
    }

    public final void q() {
        if (this.l <= 0.0f) {
            this.l = (1.0f * getResources().getDisplayMetrics().density) + 0.5f;
        }
        if (this.k == -1) {
            this.k = yp.c(getContext(), R$dimen.default_elevation_0);
        }
        Paint paint = new Paint();
        this.m = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.m.setStrokeWidth(this.l);
        this.m.setColor(this.k);
    }

    public void setShowBottomBorder(boolean z) {
        this.q = z;
    }

    public void setShowLeftBorder(boolean z) {
        this.n = z;
    }

    public void setShowRightBorder(boolean z) {
        this.o = z;
    }

    public void setShowTopBorder(boolean z) {
        this.p = z;
    }

    public ShapeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ShapeTextView);
        this.k = typedArrayObtainStyledAttributes.getColor(R$styleable.ShapeTextView_shapeStrokeDefaultColor, -1);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeTextView_shapeStrokeWidth, -1);
        this.p = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ShapeTextView_showTopBorder, true);
        this.n = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ShapeTextView_showLeftBorder, true);
        this.q = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ShapeTextView_showBottomBorder, false);
        this.o = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ShapeTextView_showRightBorder, false);
        typedArrayObtainStyledAttributes.recycle();
        q();
    }

    public ShapeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        q();
    }
}
