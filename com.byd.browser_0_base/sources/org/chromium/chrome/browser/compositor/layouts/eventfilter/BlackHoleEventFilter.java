package org.chromium.chrome.browser.compositor.layouts.eventfilter;

import android.content.Context;
import android.view.MotionEvent;
import org.chromium.chrome.browser.layouts.EventFilter;

/* JADX INFO: loaded from: classes.dex */
public class BlackHoleEventFilter extends EventFilter {
    public BlackHoleEventFilter(Context context) {
        super(context);
    }

    @Override // org.chromium.chrome.browser.layouts.EventFilter
    public final boolean b(MotionEvent motionEvent) {
        return true;
    }

    @Override // org.chromium.chrome.browser.layouts.EventFilter
    public final void c(MotionEvent motionEvent) {
    }
}
