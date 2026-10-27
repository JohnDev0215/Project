package defpackage;

import J.N;
import android.hardware.camera2.CameraDevice;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VH extends CameraDevice.StateCallback {
    public final /* synthetic */ C0542bI a;

    public VH(C0542bI c0542bI, PH ph) {
        this.a = c0542bI;
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public void onClosed(CameraDevice cameraDevice) {
        C0542bI c0542bI = this.a;
        if (c0542bI.h != null) {
            c0542bI.h = null;
        }
        this.a.m.open();
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public void onDisconnected(CameraDevice cameraDevice) {
        Ts.a("VideoCapture", "cameraDevice was closed unexpectedly", new Object[0]);
        cameraDevice.close();
        C0542bI c0542bI = this.a;
        c0542bI.g = null;
        c0542bI.f(3);
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public void onError(CameraDevice cameraDevice, int i) {
        Ts.a("VideoCapture", "cameraDevice encountered an error", new Object[0]);
        cameraDevice.close();
        C0542bI c0542bI = this.a;
        c0542bI.g = null;
        c0542bI.f(3);
        C0542bI c0542bI2 = this.a;
        long j = c0542bI2.e;
        StringBuilder sbF = Z7.f("Camera device error ");
        sbF.append(Integer.toString(i));
        N.MhmwjISE(j, c0542bI2, 69, sbF.toString());
    }

    @Override // android.hardware.camera2.CameraDevice.StateCallback
    public void onOpened(CameraDevice cameraDevice) {
        Ts.a("VideoCapture", "CameraDevice.StateCallback onOpened", new Object[0]);
        C0542bI c0542bI = this.a;
        c0542bI.g = cameraDevice;
        c0542bI.m.close();
        this.a.f(1);
        C0542bI.e(this.a, 114);
    }
}
