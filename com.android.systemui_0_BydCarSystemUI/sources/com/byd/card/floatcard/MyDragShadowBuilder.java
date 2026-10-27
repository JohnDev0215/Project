package com.byd.card.floatcard;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class MyDragShadowBuilder extends View.DragShadowBuilder {
    Point touchPoint;

    public MyDragShadowBuilder(View view, Point touchPoint) {
        super(view);
        this.touchPoint = touchPoint;
        touchPoint.set(touchPoint.x, touchPoint.y);
    }

    @Override // android.view.View.DragShadowBuilder
    public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
        View view = getView();
        if (view != null) {
            shadowSize.set(view.getWidth(), view.getHeight());
            shadowTouchPoint.set(this.touchPoint.x, this.touchPoint.y);
        }
    }

    @Override // android.view.View.DragShadowBuilder
    public void onDrawShadow(Canvas canvas) {
        View view = getView();
        if (view != null) {
            view.draw(canvas);
        }
    }
}
