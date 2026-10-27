package defpackage;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import org.chromium.chrome.browser.omnibox.status.StatusView;

/* JADX INFO: compiled from: StatusView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bu2 extends View.AccessibilityDelegate {
    public final /* synthetic */ StatusView a;

    public bu2(StatusView statusView) {
        this.a = statusView;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        StatusView statusView = this.a;
        if (statusView.s == 0) {
            return;
        }
        accessibilityNodeInfo.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, statusView.getContext().getResources().getString(statusView.s)));
    }
}
