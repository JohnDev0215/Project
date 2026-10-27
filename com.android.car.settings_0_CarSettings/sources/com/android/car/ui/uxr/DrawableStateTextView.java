package com.android.car.ui.uxr;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import java.util.function.Function;

/* JADX INFO: loaded from: classes6.dex */
public class DrawableStateTextView extends TextView implements DrawableStateView {
    private DrawableStateUtil mUtil;

    public DrawableStateTextView(Context context) {
        super(context);
    }

    public DrawableStateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableStateTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // com.android.car.ui.uxr.DrawableStateView
    public void setExtraDrawableState(int[] stateToAdd, int[] stateToRemove) {
        if (this.mUtil == null) {
            this.mUtil = new DrawableStateUtil(this);
        }
        this.mUtil.setExtraDrawableState(stateToAdd, stateToRemove);
    }

    @Override // android.widget.TextView, android.view.View
    public int[] onCreateDrawableState(int extraSpace) {
        if (this.mUtil == null) {
            this.mUtil = new DrawableStateUtil(this);
        }
        return this.mUtil.onCreateDrawableState(extraSpace, new Function() { // from class: com.android.car.ui.uxr.-$$Lambda$DrawableStateTextView$PLJr76PFF8nb26knJ39EEup9_xQ
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onCreateDrawableState$0$DrawableStateTextView((Integer) obj);
            }
        });
    }

    public /* synthetic */ int[] lambda$onCreateDrawableState$0$DrawableStateTextView(Integer space) {
        return super.onCreateDrawableState(space.intValue());
    }
}
