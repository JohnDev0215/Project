package defpackage;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.util.Size;
import java.util.ArrayList;
import org.chromium.base.TraceEvent;

/* JADX INFO: renamed from: aI, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0486aI implements Runnable {
    public final long s;
    public final /* synthetic */ C0542bI t;

    public RunnableC0486aI(C0542bI c0542bI, long j) {
        this.t = c0542bI;
        this.s = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        TraceEvent.I("VideoCaptureCamera2.java", "TakePhotoTask.run");
        C0542bI c0542bI = this.t;
        if (c0542bI.g == null || c0542bI.o != 2) {
            Ts.a("VideoCapture", "TakePhoto failed because mCameraDevice == null || mCameraState != CameraState.STARTED", new Object[0]);
            this.t.d(this.s);
            return;
        }
        Size[] outputSizes = ((StreamConfigurationMap) C0542bI.i(c0542bI.d).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(256);
        C0542bI c0542bI2 = this.t;
        Size sizeH = C0542bI.h(outputSizes, c0542bI2.r, c0542bI2.s);
        C0542bI c0542bI3 = this.t;
        int i = c0542bI3.r;
        int i2 = c0542bI3.s;
        if (sizeH != null) {
            sizeH.getWidth();
            sizeH.getHeight();
        }
        TraceEvent.I("VideoCaptureCamera2.java", "TakePhotoTask.run creating ImageReader");
        ImageReader imageReaderNewInstance = ImageReader.newInstance(sizeH != null ? sizeH.getWidth() : this.t.c.a, sizeH != null ? sizeH.getHeight() : this.t.c.b, 256, 1);
        imageReaderNewInstance.setOnImageAvailableListener(new QH(this.t, this.s), this.t.l);
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(imageReaderNewInstance.getSurface());
        try {
            CaptureRequest.Builder builderCreateCaptureRequest = this.t.g.createCaptureRequest(2);
            if (builderCreateCaptureRequest == null) {
                Ts.a("VideoCapture", "photoRequestBuilder error", new Object[0]);
                this.t.d(this.s);
                return;
            }
            builderCreateCaptureRequest.addTarget(imageReaderNewInstance.getSurface());
            builderCreateCaptureRequest.set(CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(this.t.a()));
            TraceEvent.I("VideoCaptureCamera2.java", "TakePhotoTask.run calling configureCommonCaptureSettings");
            this.t.g(builderCreateCaptureRequest);
            TraceEvent.I("VideoCaptureCamera2.java", "TakePhotoTask.run calling photoRequestBuilder.build()");
            RH rh = new RH(this.t, imageReaderNewInstance, builderCreateCaptureRequest.build(), this.s);
            try {
                TraceEvent.I("VideoCaptureCamera2.java", "TakePhotoTask.run calling mCameraDevice.createCaptureSession()");
                this.t.g.createCaptureSession(arrayList, rh, this.t.l);
            } catch (CameraAccessException | IllegalArgumentException | SecurityException e) {
                Ts.a("VideoCapture", "createCaptureSession: " + e, new Object[0]);
                this.t.d(this.s);
            }
        } catch (CameraAccessException e2) {
            Ts.a("VideoCapture", "createCaptureRequest() error ", e2);
            this.t.d(this.s);
        }
    }
}
