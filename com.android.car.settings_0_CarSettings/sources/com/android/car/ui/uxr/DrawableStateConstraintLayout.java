package com.android.car.ui.uxr;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.function.Function;

/* JADX INFO: loaded from: classes6.dex */
public class DrawableStateConstraintLayout extends ConstraintLayout implements DrawableStateView {
    private DrawableStateUtil mUtil;

    public DrawableStateConstraintLayout(Context context) {
        super(context);
    }

    public DrawableStateConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableStateConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
        return this.mUtil.onCreateDrawableState(extraSpace, new Function() { // from class: com.android.car.ui.uxr.-$$Lambda$DrawableStateConstraintLayout$bLH5f2aBGw97AlLTe_6MqifwU9s
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onCreateDrawableState$0$DrawableStateConstraintLayout((Integer) obj);
            }
        });
    }

    public /* synthetic */ int[] lambda$onCreateDrawableState$0$DrawableStateConstraintLayout(Integer space) {
        return super.onCreateDrawableState(space.intValue());
    }
}
