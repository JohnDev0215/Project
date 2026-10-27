package com.android.launcher3;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class InsettableFrameLayout extends FrameLayout implements Insettable {

    @ViewDebug.ExportedProperty(category = "launcher")
    protected Rect mInsets;

    public Rect getInsets() {
        return this.mInsets;
    }

    public InsettableFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mInsets = new Rect();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setFrameLayoutChildInsets(View child, Rect newInsets, Rect oldInsets) {
        LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
        if (child instanceof Insettable) {
            ((Insettable) child).setInsets(newInsets);
        } else if (!layoutParams.ignoreInsets) {
            layoutParams.topMargin += newInsets.top - oldInsets.top;
            layoutParams.leftMargin += newInsets.left - oldInsets.left;
            layoutParams.rightMargin += newInsets.right - oldInsets.right;
            layoutParams.bottomMargin += newInsets.bottom - oldInsets.bottom;
        }
        child.setLayoutParams(layoutParams);
    }

    @Override // com.android.launcher3.Insettable
    public void setInsets(Rect insets) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            setFrameLayoutChildInsets(getChildAt(i), insets, this.mInsets);
        }
        this.mInsets.set(insets);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    public static class LayoutParams extends FrameLayout.LayoutParams {
        public boolean ignoreInsets;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.ignoreInsets = false;
            this.ignoreInsets = false;
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.ignoreInsets = false;
        }

        public LayoutParams(ViewGroup.LayoutParams lp) {
            super(lp);
            this.ignoreInsets = false;
        }
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        if (isAttachedToWindow()) {
            setFrameLayoutChildInsets(child, this.mInsets, new Rect());
        }
    }

    public static void dispatchInsets(ViewGroup parent, Rect insets) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            KeyEvent.Callback childAt = parent.getChildAt(i);
            if (childAt instanceof Insettable) {
                ((Insettable) childAt).setInsets(insets);
            }
        }
    }
}
