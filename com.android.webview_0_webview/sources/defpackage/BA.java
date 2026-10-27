package defpackage;

import J.N;
import org.chromium.content.browser.androidoverlay.DialogOverlayImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BA implements Runnable {
    public final /* synthetic */ CA s;

    public BA(CA ca) {
        this.s = ca;
    }

    @Override // java.lang.Runnable
    public void run() {
        DialogOverlayImpl dialogOverlayImpl = this.s.w;
        long j = dialogOverlayImpl.y;
        if (j != 0) {
            N.MQAm7B7f(j, dialogOverlayImpl);
        }
    }
}
