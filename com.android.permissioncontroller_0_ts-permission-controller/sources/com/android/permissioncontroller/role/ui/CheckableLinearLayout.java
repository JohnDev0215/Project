package com.android.permissioncontroller.role.ui;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes8.dex */
public class CheckableLinearLayout extends LinearLayout implements Checkable {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private boolean mChecked;

    public CheckableLinearLayout(Context context) {
        super(context);
        this.mChecked = false;
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mChecked = false;
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mChecked = false;
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mChecked = false;
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.mChecked;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean checked) {
        if (this.mChecked == checked) {
            return;
        }
        this.mChecked = checked;
        refreshDrawableState();
        updateChildrenChecked();
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.mChecked);
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int extraSpace) {
        int[] state = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(state, CHECKED_STATE_SET);
        }
        return state;
    }

    private void updateChildrenChecked() {
        updateChildrenChecked(this, this.mChecked);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void updateChildrenChecked(ViewGroup viewGroup, boolean checked) {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt.isDuplicateParentStateEnabled()) {
                if (childAt instanceof Checkable) {
                    ((Checkable) childAt).setChecked(checked);
                } else if (childAt instanceof ViewGroup) {
                    updateChildrenChecked((ViewGroup) childAt, checked);
                }
            }
        }
    }
}
