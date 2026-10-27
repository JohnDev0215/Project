package org.chromium.chrome.browser.share.qrcode.scan_tab;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import defpackage.c82;
import defpackage.ya0;
import defpackage.zx1;

/* JADX INFO: loaded from: classes2.dex */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    public final Context e;
    public final Camera.PreviewCallback f;
    public final Camera.ErrorCallback g;
    public int h;
    public Camera i;
    public HandlerThread j;

    public CameraPreview(Context context, Camera.PreviewCallback previewCallback, c82.a aVar) {
        super(context);
        this.e = context;
        this.f = previewCallback;
        this.g = aVar;
    }

    private int getCameraOrientation() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(this.h, cameraInfo);
        int displayOrientation = getDisplayOrientation();
        return cameraInfo.facing == 0 ? ((cameraInfo.orientation - displayOrientation) + 360) % 360 : (360 - ((cameraInfo.orientation + displayOrientation) % 360)) % 360;
    }

    private static int getDefaultCameraId() {
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int i = -1;
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == 0) {
                return i2;
            }
            i = i2;
        }
        return i;
    }

    private int getDisplayOrientation() {
        int i = ya0.a(this.e).i;
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 90;
        }
        if (i != 2) {
            return i != 3 ? 0 : 270;
        }
        return 180;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupCamera(Camera camera) {
        this.i = camera;
        c();
    }

    public final void b() {
        if (this.j == null) {
            HandlerThread handlerThread = new HandlerThread("CameraHandlerThread");
            this.j = handlerThread;
            handlerThread.start();
        }
        this.h = getDefaultCameraId();
        new Handler(this.j.getLooper()).post(new zx1(11, this));
    }

    public final void c() {
        Camera.ErrorCallback errorCallback = this.g;
        getHolder().addCallback(this);
        Camera camera = this.i;
        if (camera == null) {
            return;
        }
        try {
            camera.setPreviewDisplay(getHolder());
            this.i.setDisplayOrientation(getCameraOrientation());
            this.i.setOneShotPreviewCallback(this.f);
            this.i.setErrorCallback(errorCallback);
            Camera.Parameters parameters = this.i.getParameters();
            parameters.setFocusMode("continuous-picture");
            this.i.setParameters(parameters);
            this.i.startPreview();
        } catch (Exception unused) {
            errorCallback.onError(1003, this.i);
        }
    }

    public final void d() {
        if (this.i == null) {
            return;
        }
        e();
        this.i.release();
        this.i = null;
        HandlerThread handlerThread = this.j;
        if (handlerThread != null) {
            handlerThread.quit();
            this.j = null;
        }
    }

    public final void e() {
        getHolder().removeCallback(this);
        Camera camera = this.i;
        if (camera == null) {
            return;
        }
        camera.setOneShotPreviewCallback(null);
        this.i.setErrorCallback(null);
        try {
            this.i.stopPreview();
        } catch (RuntimeException unused) {
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        e();
        c();
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        c();
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        e();
    }
}
