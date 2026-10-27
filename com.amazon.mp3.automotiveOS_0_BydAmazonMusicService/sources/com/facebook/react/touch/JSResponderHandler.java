package com.facebook.react.touch;

import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
public class JSResponderHandler implements OnInterceptTouchEventListener {
    private volatile int mCurrentJSResponder = -1;

    @Nullable
    private ViewParent mViewParentBlockingNativeResponder;

    public void setJSResponder(int i, @Nullable ViewParent viewParent) {
        this.mCurrentJSResponder = i;
        maybeUnblockNativeResponder();
        if (viewParent != null) {
            viewParent.requestDisallowInterceptTouchEvent(true);
            this.mViewParentBlockingNativeResponder = viewParent;
        }
    }

    public void clearJSResponder() {
        this.mCurrentJSResponder = -1;
        maybeUnblockNativeResponder();
    }

    private void maybeUnblockNativeResponder() {
        ViewParent viewParent = this.mViewParentBlockingNativeResponder;
        if (viewParent != null) {
            viewParent.requestDisallowInterceptTouchEvent(false);
            this.mViewParentBlockingNativeResponder = null;
        }
    }

    @Override // com.facebook.react.touch.OnInterceptTouchEventListener
    public boolean onInterceptTouchEvent(ViewGroup viewGroup, MotionEvent motionEvent) {
        int i = this.mCurrentJSResponder;
        return (i == -1 || motionEvent.getAction() == 1 || viewGroup.getId() != i) ? false : true;
    }
}
