package com.android.systemui.stackdivider;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class DividerHandleView extends View {
    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
    }

    public DividerHandleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
