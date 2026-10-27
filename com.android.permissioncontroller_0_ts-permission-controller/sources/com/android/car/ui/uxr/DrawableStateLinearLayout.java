package com.android.car.ui.uxr;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import java.util.function.Function;

/* JADX INFO: loaded from: classes7.dex */
public class DrawableStateLinearLayout extends LinearLayout implements DrawableStateView {
    private DrawableStateUtil mUtil;

    public DrawableStateLinearLayout(Context context) {
        super(context);
    }

    public DrawableStateLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableStateLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // com.android.car.ui.uxr.DrawableStateView
    public void setExtraDrawableState(int[] stateToAdd, int[] stateToRemove) {
        if (this.mUtil == null) {
            this.mUtil = new DrawableStateUtil(this);
        }
        this.mUtil.setExtraDrawableState(stateToAdd, stateToRemove);
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int extraSpace) {
        if (this.mUtil == null) {
            this.mUtil = new DrawableStateUtil(this);
        }
        return this.mUtil.onCreateDrawableState(extraSpace, new Function() { // from class: com.android.car.ui.uxr.-$$Lambda$DrawableStateLinearLayout$1bT6rbzb2WmzodeUm2hyC_gTf8I
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onCreateDrawableState$0$DrawableStateLinearLayout((Integer) obj);
            }
        });
    }

    public /* synthetic */ int[] lambda$onCreateDrawableState$0$DrawableStateLinearLayout(Integer space) {
        return super.onCreateDrawableState(space.intValue());
    }
}
