package androidx.leanback.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class NonOverlappingLinearLayout extends LinearLayout {
    boolean mDeferFocusableViewAvailableInLayout;
    boolean mFocusableViewAvailableFixEnabled;
    final ArrayList<ArrayList<View>> mSortedAvailableViews;

    public NonOverlappingLinearLayout(Context context) {
        this(context, null);
    }

    public NonOverlappingLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NonOverlappingLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mFocusableViewAvailableFixEnabled = false;
        this.mSortedAvailableViews = new ArrayList<>();
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public void setFocusableViewAvailableFixEnabled(boolean enabled) {
        this.mFocusableViewAvailableFixEnabled = enabled;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int i;
        boolean z;
        int size;
        try {
            boolean z2 = this.mFocusableViewAvailableFixEnabled && getOrientation() == 0 && getLayoutDirection() == 1;
            this.mDeferFocusableViewAvailableInLayout = z2;
            if (z2) {
                while (this.mSortedAvailableViews.size() > getChildCount()) {
                    this.mSortedAvailableViews.remove(this.mSortedAvailableViews.size() - 1);
                }
                while (this.mSortedAvailableViews.size() < getChildCount()) {
                    this.mSortedAvailableViews.add(new ArrayList<>());
                }
            }
            super.onLayout(changed, l, t, r, b);
            if (this.mDeferFocusableViewAvailableInLayout) {
                for (int i2 = 0; i2 < this.mSortedAvailableViews.size(); i2++) {
                    for (int j = 0; j < this.mSortedAvailableViews.get(i2).size(); j++) {
                        super.focusableViewAvailable(this.mSortedAvailableViews.get(i2).get(j));
                    }
                }
            }
            if (z) {
                while (true) {
                    if (i >= size) {
                        return;
                    }
                }
            }
        } finally {
            if (this.mDeferFocusableViewAvailableInLayout) {
                this.mDeferFocusableViewAvailableInLayout = false;
                for (i = 0; i < this.mSortedAvailableViews.size(); i++) {
                    this.mSortedAvailableViews.get(i).clear();
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void focusableViewAvailable(View v) {
        if (this.mDeferFocusableViewAvailableInLayout) {
            View i = v;
            int index = -1;
            while (true) {
                if (i == this || i == null) {
                    break;
                }
                if (i.getParent() == this) {
                    index = indexOfChild(i);
                    break;
                }
                i = (View) i.getParent();
            }
            if (index != -1) {
                this.mSortedAvailableViews.get(index).add(v);
                return;
            }
            return;
        }
        super.focusableViewAvailable(v);
    }
}
