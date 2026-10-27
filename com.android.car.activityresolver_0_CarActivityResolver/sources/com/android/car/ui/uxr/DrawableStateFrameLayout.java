package com.android.car.ui.uxr;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import java.util.function.Function;

/* JADX INFO: loaded from: classes2.dex */
public class DrawableStateFrameLayout extends FrameLayout implements DrawableStateView {
    private DrawableStateUtil mUtil;

    public DrawableStateFrameLayout(Context context) {
        super(context);
    }

    public DrawableStateFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableStateFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
        return this.mUtil.onCreateDrawableState(extraSpace, new Function() { // from class: com.android.car.ui.uxr.-$$Lambda$DrawableStateFrameLayout$vYrYPDtl8pYls0QFTkXQ07DrvZ8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onCreateDrawableState$0$DrawableStateFrameLayout((Integer) obj);
            }
        });
    }

    public /* synthetic */ int[] lambda$onCreateDrawableState$0$DrawableStateFrameLayout(Integer space) {
        return super.onCreateDrawableState(space.intValue());
    }
}
