package com.android.launcher3.keyboard;

import android.graphics.Canvas;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.keyboard.FocusIndicatorHelper;

/* JADX INFO: loaded from: classes4.dex */
public class FocusedItemDecorator extends RecyclerView.ItemDecoration {
    private FocusIndicatorHelper mHelper;

    public FocusedItemDecorator(View container) {
        this.mHelper = new FocusIndicatorHelper.SimpleFocusIndicatorHelper(container);
    }

    public View.OnFocusChangeListener getFocusListener() {
        return this.mHelper;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        this.mHelper.draw(c);
    }
}
