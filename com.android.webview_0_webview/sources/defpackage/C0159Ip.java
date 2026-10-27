package defpackage;

import android.widget.ZoomButtonsController;
import org.chromium.android_webview.AwContents;

/* JADX INFO: renamed from: Ip, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0159Ip implements ZoomButtonsController.OnZoomListener {
    public final /* synthetic */ C0177Jp a;

    public C0159Ip(C0177Jp c0177Jp, AbstractC0141Hp abstractC0141Hp) {
        this.a = c0177Jp;
    }

    @Override // android.widget.ZoomButtonsController.OnZoomListener
    public void onVisibilityChanged(boolean z) {
        if (z) {
            this.a.b.getZoomControls().setVisibility(0);
            this.a.c();
        }
    }

    @Override // android.widget.ZoomButtonsController.OnZoomListener
    public void onZoom(boolean z) {
        if (z) {
            AwContents awContents = this.a.a;
            if (awContents.f()) {
                awContents.c0(1.25f);
                return;
            }
            return;
        }
        AwContents awContents2 = this.a.a;
        if (awContents2.g()) {
            awContents2.c0(0.8f);
        }
    }
}
