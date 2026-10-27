package org.chromium.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import org.chromium.base.b;

/* JADX INFO: loaded from: classes3.dex */
public class AsyncViewStub extends View {
    public static AsyncLayoutInflater e;

    public AsyncViewStub(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        new b();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AsyncViewStub);
        typedArrayObtainStyledAttributes.getResourceId(R$styleable.AsyncViewStub_layout, 0);
        typedArrayObtainStyledAttributes.recycle();
        setVisibility(8);
        setWillNotDraw(true);
        if (e == null) {
            e = new AsyncLayoutInflater(getContext());
        }
    }

    public View getInflatedView() {
        return null;
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.View
    public final void dispatchDraw(Canvas canvas) {
    }

    @Override // android.view.View
    @SuppressLint({"MissingSuperCall"})
    public final void draw(Canvas canvas) {
    }

    public void setLayoutResource(int i) {
    }

    public void setShouldInflateOnBackgroundThread(boolean z) {
    }
}
