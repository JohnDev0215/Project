package com.android.car.ui.baselayout;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ClickableViewAccessibility"})
@TargetApi(28)
public class ClickBlockingView extends View {
    public boolean a;
    public boolean b;

    public ClickBlockingView(Context context) {
        super(context);
        this.a = false;
        this.b = false;
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.b = false;
        }
        if (!this.b) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.b = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.b = false;
        }
        return true;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.a = false;
        }
        if (!this.a) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.a = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.a = false;
        }
        return true;
    }

    public ClickBlockingView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = false;
        this.b = false;
    }

    public ClickBlockingView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = false;
        this.b = false;
    }

    public ClickBlockingView(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = false;
        this.b = false;
    }
}
