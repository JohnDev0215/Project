package com.android.car.ui.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes7.dex */
public class CarUiRecyclerViewContainer extends FrameLayout {
    public CarUiRecyclerViewContainer(Context context) {
        super(context);
    }

    public CarUiRecyclerViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarUiRecyclerViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CarUiRecyclerViewContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    void addRecyclerView(View view, FrameLayout.LayoutParams layoutParams) {
        addViewInLayout(view, 0, layoutParams);
    }
}
