package defpackage;

import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;

/* JADX INFO: renamed from: lA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1092lA implements Runnable {
    public final /* synthetic */ WebContentsAccessibilityImpl s;

    public RunnableC1092lA(WebContentsAccessibilityImpl webContentsAccessibilityImpl) {
        this.s = webContentsAccessibilityImpl;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.w();
    }
}
