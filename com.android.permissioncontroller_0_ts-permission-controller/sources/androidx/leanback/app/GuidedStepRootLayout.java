package androidx.leanback.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.leanback.widget.Util;

/* JADX INFO: loaded from: classes3.dex */
class GuidedStepRootLayout extends LinearLayout {
    private boolean mFocusOutEnd;
    private boolean mFocusOutStart;

    public GuidedStepRootLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mFocusOutStart = false;
        this.mFocusOutEnd = false;
    }

    public GuidedStepRootLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mFocusOutStart = false;
        this.mFocusOutEnd = false;
    }

    public void setFocusOutStart(boolean focusOutStart) {
        this.mFocusOutStart = focusOutStart;
    }

    public void setFocusOutEnd(boolean focusOutEnd) {
        this.mFocusOutEnd = focusOutEnd;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View focused, int direction) {
        View newFocus = super.focusSearch(focused, direction);
        if ((direction != 17 && direction != 66) || Util.isDescendant(this, newFocus)) {
            return newFocus;
        }
        if (getLayoutDirection() != 0 ? direction == 66 : direction == 17) {
            if (!this.mFocusOutStart) {
                return focused;
            }
        } else if (!this.mFocusOutEnd) {
            return focused;
        }
        return newFocus;
    }
}
