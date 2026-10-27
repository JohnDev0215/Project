package com.android.car.settings.common.rotary;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import com.android.car.ui.utils.DirectManipulationHelper;

/* JADX INFO: loaded from: classes5.dex */
public class DirectManipulationState {
    static final int UNKNOWN_DESCENDANT_FOCUSABILITY = -1;
    private Drawable mOriginalBackground;
    private int mOriginalDescendantFocusability = -1;
    private View mViewInDirectManipulationMode;

    public boolean isActive() {
        return this.mViewInDirectManipulationMode != null;
    }

    public void enable(View view) {
        this.mViewInDirectManipulationMode = view;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            this.mOriginalDescendantFocusability = viewGroup.getDescendantFocusability();
            viewGroup.setDescendantFocusability(262144);
        }
        DirectManipulationHelper.enableDirectManipulationMode(view, true);
    }

    public void disable() {
        int i;
        DirectManipulationHelper.enableDirectManipulationMode(this.mViewInDirectManipulationMode, false);
        View view = this.mViewInDirectManipulationMode;
        if ((view instanceof ViewGroup) && (i = this.mOriginalDescendantFocusability) != -1) {
            ViewGroup viewGroup = (ViewGroup) view;
            viewGroup.setDescendantFocusability(i);
        }
        this.mViewInDirectManipulationMode = null;
        this.mOriginalDescendantFocusability = -1;
    }

    View getViewInDirectManipulationMode() {
        return this.mViewInDirectManipulationMode;
    }

    int getOriginalDescendantFocusability() {
        return this.mOriginalDescendantFocusability;
    }
}
