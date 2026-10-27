package defpackage;

import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import org.chromium.media.ScreenCapture;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class IH extends MediaProjection.Callback {
    public final /* synthetic */ ScreenCapture a;

    public IH(ScreenCapture screenCapture, GH gh) {
        this.a = screenCapture;
    }

    @Override // android.media.projection.MediaProjection.Callback
    public void onStop() {
        this.a.b(4);
        ScreenCapture screenCapture = this.a;
        screenCapture.v = null;
        VirtualDisplay virtualDisplay = screenCapture.x;
        if (virtualDisplay == null) {
            return;
        }
        virtualDisplay.release();
        this.a.x = null;
    }
}
