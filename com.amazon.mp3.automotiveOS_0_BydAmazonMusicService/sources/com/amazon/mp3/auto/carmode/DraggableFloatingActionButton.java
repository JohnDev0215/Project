package com.amazon.mp3.auto.carmode;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DraggableFloatingActionButton.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\b\u0016\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0016R\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082D¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0016\u0010\u000f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\rR\u0016\u0010\u0010\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\rR\u0016\u0010\u0011\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/auto/carmode/DraggableFloatingActionButton;", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "Landroid/view/View$OnTouchListener;", "", "init", "Landroid/view/View;", "view", "Landroid/view/MotionEvent;", "motionEvent", "", "onTouch", "", "CLICK_DRAG_TOLERANCE", "F", "downRawX", "downRawY", "dX", "dY", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DraggableFloatingActionButton extends FloatingActionButton implements View.OnTouchListener {
    private final float CLICK_DRAG_TOLERANCE;
    private float dX;
    private float dY;
    private float downRawX;
    private float downRawY;

    public DraggableFloatingActionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.CLICK_DRAG_TOLERANCE = 10.0f;
        init();
    }

    private final void init() {
        setOnTouchListener(this);
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.downRawX = motionEvent.getRawX();
            this.downRawY = motionEvent.getRawY();
            this.dX = view.getX() - motionEvent.getRawX();
            this.dY = view.getY() - motionEvent.getRawY();
            return true;
        }
        if (action == 1) {
            float rawX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            float f = rawX - this.downRawX;
            float f2 = rawY - this.downRawY;
            if (Math.abs(f) >= this.CLICK_DRAG_TOLERANCE || Math.abs(f2) >= this.CLICK_DRAG_TOLERANCE) {
                return true;
            }
            return performClick();
        }
        if (action == 2) {
            int width = view.getWidth();
            int height = view.getHeight();
            Object parent = view.getParent();
            if (parent == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.View");
            }
            View view2 = (View) parent;
            int width2 = view2.getWidth();
            int height2 = view2.getHeight();
            view.animate().x(Math.min((width2 - width) - marginLayoutParams.rightMargin, Math.max(marginLayoutParams.leftMargin, motionEvent.getRawX() + this.dX))).y(Math.min((height2 - height) - marginLayoutParams.bottomMargin, Math.max(marginLayoutParams.topMargin, motionEvent.getRawY() + this.dY))).setDuration(0L).start();
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }
}
