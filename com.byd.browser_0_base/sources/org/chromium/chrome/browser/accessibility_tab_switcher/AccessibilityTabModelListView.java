package org.chromium.chrome.browser.accessibility_tab_switcher;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListAdapter;
import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityTabModelListView extends ListView {
    public final a e;
    public boolean f;

    public AccessibilityTabModelListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = true;
        this.e = new a(getContext(), this);
    }

    @Override // android.widget.ListView, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setAdapter((ListAdapter) this.e);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    public void setCanScroll(boolean z) {
        this.f = z;
    }
}
