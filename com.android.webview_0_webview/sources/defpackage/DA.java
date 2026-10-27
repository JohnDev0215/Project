package defpackage;

import org.chromium.content.browser.androidoverlay.DialogOverlayImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DA implements Runnable {
    public final /* synthetic */ AA s;

    public DA(DialogOverlayImpl dialogOverlayImpl, AA aa) {
        this.s = aa;
    }

    @Override // java.lang.Runnable
    public void run() {
        AA aa = this.s;
        aa.a();
        aa.c();
        aa.d.token = null;
        aa.a = null;
    }
}
