package org.chromium.chrome.browser.ntp.search;

import android.content.Context;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class SearchBoxContainerView extends LinearLayout {
    public SearchBoxContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0 && (getBackground() instanceof RippleDrawable)) {
            ((RippleDrawable) getBackground()).setHotspot(motionEvent.getX(), motionEvent.getY());
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
