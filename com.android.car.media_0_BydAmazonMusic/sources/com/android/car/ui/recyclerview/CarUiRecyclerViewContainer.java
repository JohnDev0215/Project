package com.android.car.ui.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.android.car.ui.R$layout;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class CarUiRecyclerViewContainer extends FrameLayout {
    public CarUiRecyclerViewContainer(Context context) {
        this(context, null);
    }

    public CarUiRecyclerViewContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarUiRecyclerViewContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View.inflate(context, R$layout.car_ui_recycler_view_only, this);
    }
}
