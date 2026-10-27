package defpackage;

import android.graphics.Rect;
import android.hardware.camera2.CameraDevice;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ZH implements Runnable {
    public final /* synthetic */ C0542bI s;

    public ZH(C0542bI c0542bI, PH ph) {
        this.s = c0542bI;
    }

    @Override // java.lang.Runnable
    public void run() {
        CameraDevice cameraDevice = this.s.g;
        if (cameraDevice == null) {
            return;
        }
        cameraDevice.close();
        this.s.f(3);
        this.s.q = new Rect();
    }
}
