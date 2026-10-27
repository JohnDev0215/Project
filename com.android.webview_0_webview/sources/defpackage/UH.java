package defpackage;

import J.N;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class UH extends CameraCaptureSession.StateCallback {
    public final CaptureRequest a;
    public final /* synthetic */ C0542bI b;

    public UH(C0542bI c0542bI, CaptureRequest captureRequest) {
        this.b = c0542bI;
        this.a = captureRequest;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public void onClosed(CameraCaptureSession cameraCaptureSession) {
        this.b.h = null;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        this.b.f(3);
        this.b.h = null;
        C0542bI c0542bI = this.b;
        N.MhmwjISE(c0542bI.e, c0542bI, 70, "Camera session configuration error");
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public void onConfigured(CameraCaptureSession cameraCaptureSession) {
        this.b.h = cameraCaptureSession;
        try {
            cameraCaptureSession.setRepeatingRequest(this.a, new TH(this), null);
            this.b.f(2);
            C0542bI c0542bI = this.b;
            N.MPaf3s5k(c0542bI.e, c0542bI);
        } catch (CameraAccessException | IllegalArgumentException | IllegalStateException | SecurityException e) {
            Ts.a("VideoCapture", "setRepeatingRequest: ", e);
        }
    }
}
