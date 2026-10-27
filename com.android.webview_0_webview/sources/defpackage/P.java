package defpackage;

import androidx.appcompat.widget.ActionBarOverlayLayout;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class P implements Runnable {
    public final /* synthetic */ ActionBarOverlayLayout s;

    public P(ActionBarOverlayLayout actionBarOverlayLayout) {
        this.s = actionBarOverlayLayout;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.b();
        ActionBarOverlayLayout actionBarOverlayLayout = this.s;
        actionBarOverlayLayout.I = actionBarOverlayLayout.u.animate().translationY(0.0f).setListener(this.s.f13J);
    }
}
