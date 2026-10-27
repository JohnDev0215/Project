package defpackage;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ZoomButtonsController;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwSettings;

/* JADX INFO: renamed from: Jp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0177Jp {
    public AwContents a;
    public ZoomButtonsController b;

    public C0177Jp(AwContents awContents) {
        this.a = awContents;
    }

    public final ZoomButtonsController a() {
        boolean z;
        if (this.b == null) {
            AwSettings awSettings = this.a.S;
            synchronized (awSettings.f) {
                z = awSettings.R() && awSettings.k0;
            }
            if (z) {
                ZoomButtonsController zoomButtonsController = new ZoomButtonsController(this.a.u);
                this.b = zoomButtonsController;
                zoomButtonsController.setOnZoomListener(new C0159Ip(this, null));
                ViewGroup.LayoutParams layoutParams = this.b.getZoomControls().getLayoutParams();
                if (layoutParams instanceof FrameLayout.LayoutParams) {
                    ((FrameLayout.LayoutParams) layoutParams).gravity = 5;
                }
            }
        }
        return this.b;
    }

    public void b() {
        ZoomButtonsController zoomButtonsControllerA = a();
        if (zoomButtonsControllerA != null) {
            zoomButtonsControllerA.setVisible(true);
        }
    }

    public void c() {
        ZoomButtonsController zoomButtonsControllerA = a();
        if (zoomButtonsControllerA == null) {
            return;
        }
        boolean zF = this.a.f();
        boolean zG = this.a.g();
        if (!zF && !zG) {
            zoomButtonsControllerA.getZoomControls().setVisibility(8);
        } else {
            zoomButtonsControllerA.setZoomInEnabled(zF);
            zoomButtonsControllerA.setZoomOutEnabled(zG);
        }
    }
}
