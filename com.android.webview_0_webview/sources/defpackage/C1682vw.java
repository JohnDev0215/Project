package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import com.android.webview.R;

/* JADX INFO: renamed from: vw, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1682vw extends View.AccessibilityDelegate {
    public final /* synthetic */ C1737ww a;

    public C1682vw(C1737ww c1737ww) {
        this.a = c1737ww;
    }

    @Override // android.view.View.AccessibilityDelegate
    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        this.a.a().removeCallbacks(this.a.w);
        if (accessibilityEvent.getEventType() == R.anim.abc_fade_in) {
            this.a.a().postDelayed(this.a.w, 100L);
        }
        return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }
}
