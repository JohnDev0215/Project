package defpackage;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.media.ImageReader;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RH extends CameraCaptureSession.StateCallback {
    public final ImageReader a;
    public final CaptureRequest b;
    public final long c;
    public final /* synthetic */ C0542bI d;

    public RH(C0542bI c0542bI, ImageReader imageReader, CaptureRequest captureRequest, long j) {
        this.d = c0542bI;
        this.a = imageReader;
        this.b = captureRequest;
        this.c = j;
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public void onClosed(CameraCaptureSession cameraCaptureSession) {
        this.a.close();
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
        Ts.a("VideoCapture", "failed configuring capture session", new Object[0]);
        this.d.d(this.c);
    }

    @Override // android.hardware.camera2.CameraCaptureSession.StateCallback
    public void onConfigured(CameraCaptureSession cameraCaptureSession) {
        TraceEvent.I("VideoCaptureCamera2.java", "CrPhotoSessionListener.onConfigured");
        try {
            TraceEvent.I("VideoCaptureCamera2.java", "Calling CameraCaptureSession.capture()");
            cameraCaptureSession.capture(this.b, null, null);
        } catch (CameraAccessException e) {
            Ts.a("VideoCapture", "capture() CameraAccessException", e);
            this.d.d(this.c);
        } catch (IllegalStateException e2) {
            Ts.a("VideoCapture", "capture() IllegalStateException", e2);
            this.d.d(this.c);
        }
    }
}
