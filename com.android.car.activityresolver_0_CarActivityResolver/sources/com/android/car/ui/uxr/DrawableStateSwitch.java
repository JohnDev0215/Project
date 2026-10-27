package com.android.car.ui.uxr;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Switch;
import java.util.function.Function;

/* JADX INFO: loaded from: classes2.dex */
public class DrawableStateSwitch extends Switch implements DrawableStateView {
    private DrawableStateUtil mUtil;

    public DrawableStateSwitch(Context context) {
        super(context);
    }

    public DrawableStateSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableStateSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawableStateSwitch(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override // com.android.car.ui.uxr.DrawableStateView
    public void setExtraDrawableState(int[] stateToAdd, int[] stateToRemove) {
        if (this.mUtil == null) {
            this.mUtil = new DrawableStateUtil(this);
        }
        this.mUtil.setExtraDrawableState(stateToAdd, stateToRemove);
    }

    @Override // android.widget.Switch, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int extraSpace) {
        if (this.mUtil == null) {
            this.mUtil = new DrawableStateUtil(this);
        }
        return this.mUtil.onCreateDrawableState(extraSpace, new Function() { // from class: com.android.car.ui.uxr.-$$Lambda$DrawableStateSwitch$yo6qqRPmq133cjT9hp6QJFRGwO4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onCreateDrawableState$0$DrawableStateSwitch((Integer) obj);
            }
        });
    }

    public /* synthetic */ int[] lambda$onCreateDrawableState$0$DrawableStateSwitch(Integer space) {
        return super.onCreateDrawableState(space.intValue());
    }
}
