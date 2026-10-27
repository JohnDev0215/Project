package m3;

import android.view.MotionEvent;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes.dex */
public final class L extends WebView {
    @Override // android.webkit.WebView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return false;
    }

    @Override // android.view.View
    public final boolean performClick() {
        super.performClick();
        return false;
    }
}
