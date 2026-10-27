package defpackage;

import org.chromium.content.browser.androidoverlay.AndroidOverlayProviderImpl;

/* JADX INFO: renamed from: vA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1642vA implements Runnable {
    public final /* synthetic */ AndroidOverlayProviderImpl s;

    public RunnableC1642vA(AndroidOverlayProviderImpl androidOverlayProviderImpl) {
        this.s = androidOverlayProviderImpl;
    }

    @Override // java.lang.Runnable
    public void run() {
        AndroidOverlayProviderImpl.x(this.s);
    }
}
