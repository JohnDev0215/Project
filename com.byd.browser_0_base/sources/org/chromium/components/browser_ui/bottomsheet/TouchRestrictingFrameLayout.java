package org.chromium.components.browser_ui.bottomsheet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes2.dex */
class TouchRestrictingFrameLayout extends FrameLayout {
    public BottomSheet e;

    public TouchRestrictingFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        BottomSheet bottomSheet = this.e;
        if (bottomSheet == null || bottomSheet.q == 4) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        BottomSheet bottomSheet = this.e;
        if (bottomSheet == null || bottomSheet.q == 4) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }
}
