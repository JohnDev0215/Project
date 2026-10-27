package com.byd.dilink_DiR.hardware.camera;

import android.graphics.SurfaceTexture;
import android.hardware.AVMCamera;
import android.media.MediaCodec;
import android.view.Surface;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.hardware.camera.IDiLinkAVMCamera;
import com.byd.dilink.hardware.camera.IDiLinkAVMEventCallBack;
import com.byd.dilink.hardware.camera.IDiLinkAVMPreviewCallback;

/* JADX INFO: loaded from: classes.dex */
public class DiLinkAVMCamera implements IDiLinkAVMCamera {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private AVMCamera f2173a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLinkDiR_IEventCallbackListeners f2174b = new DiLinkDiR_IEventCallbackListeners();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f2175c = new a();

    public static class DiLinkDiR_IEventCallbackListeners extends DiLinkCallbackHolders<IDiLinkAVMEventCallBack, AVMCamera.IEventCallback> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AVMCamera.IEventCallback createInnerCallback(final IDiLinkAVMEventCallBack iDiLinkAVMEventCallBack) {
            return new AVMCamera.IEventCallback() { // from class: com.byd.dilink_DiR.hardware.camera.DiLinkAVMCamera.DiLinkDiR_IEventCallbackListeners.1
                @Keep
                public void onEvent(AVMCamera aVMCamera, int i, int i2, int i3) {
                    IDiLinkAVMEventCallBack iDiLinkAVMEventCallBack2;
                    DiLinkAVMCamera diLinkAVMCamera;
                    if (aVMCamera != null) {
                        diLinkAVMCamera = new DiLinkAVMCamera(aVMCamera);
                        iDiLinkAVMEventCallBack2 = iDiLinkAVMEventCallBack;
                    } else {
                        iDiLinkAVMEventCallBack2 = iDiLinkAVMEventCallBack;
                        diLinkAVMCamera = null;
                    }
                    iDiLinkAVMEventCallBack2.onEvent(diLinkAVMCamera, i, i2, i3);
                }
            };
        }
    }

    public static class a extends DiLinkCallbackHolders<IDiLinkAVMPreviewCallback, AVMCamera.IPreviewCallback> {

        /* JADX INFO: renamed from: com.byd.dilink_DiR.hardware.camera.DiLinkAVMCamera$a$a, reason: collision with other inner class name */
        public class C0054a implements AVMCamera.IPreviewCallback {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IDiLinkAVMPreviewCallback f2178a;

            public C0054a(IDiLinkAVMPreviewCallback iDiLinkAVMPreviewCallback) {
                this.f2178a = iDiLinkAVMPreviewCallback;
            }

            public void onPreview(AVMCamera aVMCamera, byte[] bArr, int i, int i2, int i3, int i4, int i5, long j) {
                if (aVMCamera == null) {
                    this.f2178a.onPreview(null, bArr, i, i2, i3, i4, i5, j);
                } else {
                    this.f2178a.onPreview(new DiLinkAVMCamera(aVMCamera), bArr, i, i2, i3, i4, i5, j);
                }
            }
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AVMCamera.IPreviewCallback createInnerCallback(IDiLinkAVMPreviewCallback iDiLinkAVMPreviewCallback) {
            return new C0054a(iDiLinkAVMPreviewCallback);
        }
    }

    public DiLinkAVMCamera(AVMCamera aVMCamera) {
        if (aVMCamera == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("AVMCamera is null");
        }
        this.f2173a = aVMCamera;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(AVMCamera.IEventCallback iEventCallback) {
        this.f2173a.setEventCallback(iEventCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(AVMCamera.IPreviewCallback iPreviewCallback) {
        this.f2173a.setPreviewCallback(iPreviewCallback);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean addPreviewSurface(@NonNull Surface surface, int i) {
        return this.f2173a.addPreviewSurface(surface, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean addTexture(@NonNull SurfaceTexture surfaceTexture, int i) {
        return this.f2173a.addTexture(surfaceTexture, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public void close() {
        this.f2173a.close();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean disablePreviewCallback(int i) {
        return this.f2173a.disablePreviewCallback(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean disablePreviewCallback(int i, int i2, int i3, int i4, int i5) {
        return this.f2173a.disablePreviewCallback(i, i2, i3, i4, i5);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean enablePreviewCallback(int i) {
        return this.f2173a.enablePreviewCallback(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean enablePreviewCallback(int i, int i2, int i3, int i4, int i5) {
        return this.f2173a.enablePreviewCallback(i, i2, i3, i4, i5);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public int getCameraId() {
        return this.f2173a.getCameraId();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public int getLogLevel() {
        return this.f2173a.getLogLevel();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public int getPreviewHeight() {
        return this.f2173a.getPreviewHeight();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public int getPreviewWidth() {
        return this.f2173a.getPreviewWidth();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean isOpen() {
        return this.f2173a.isOpen();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean isPreview() {
        return this.f2173a.isPreview();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean rmPreviewSurface(@NonNull Surface surface, int i) {
        return this.f2173a.rmPreviewSurface(surface, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean rmTexture(@NonNull SurfaceTexture surfaceTexture, int i) {
        return this.f2173a.rmTexture(surfaceTexture, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setAlgMode(int i) {
        return this.f2173a.setAlgMode(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setCameraFps(int i) {
        return this.f2173a.setCameraFps(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setDisplayOrientation(Surface surface, int i) {
        return this.f2173a.setDisplayOrientation(surface, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public void setEventCallback(@NonNull IDiLinkAVMEventCallBack iDiLinkAVMEventCallBack) {
        this.f2174b.addCallbackIgnoreRemoteException(iDiLinkAVMEventCallBack, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.hardware.camera.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2187a.c((AVMCamera.IEventCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public void setLogLevel(int i) {
        this.f2173a.setLogLevel(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setMediaCodec(@NonNull MediaCodec mediaCodec, int i) {
        return this.f2173a.setMediaCodec(mediaCodec, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setMediaCodec(@NonNull MediaCodec mediaCodec, int i, int i2, int i3, int i4, int i5) {
        return this.f2173a.setMediaCodec(mediaCodec, i, i2, i3, i4, i5);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setMediaCodecFps(@NonNull MediaCodec mediaCodec, int i) {
        return this.f2173a.setMediaCodecFps(mediaCodec, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public void setPreviewCallback(@NonNull IDiLinkAVMPreviewCallback iDiLinkAVMPreviewCallback) {
        this.f2175c.addCallbackIgnoreRemoteException(iDiLinkAVMPreviewCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.hardware.camera.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2188a.d((AVMCamera.IPreviewCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setPreviewSize(int i, int i2) {
        return this.f2173a.setPreviewSize(i, i2);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setPreviewSurface(@NonNull Surface surface, int i) {
        return this.f2173a.setPreviewSurface(surface, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setPreviewSurface(@NonNull Surface surface, int i, int i2, int i3, int i4, int i5) {
        return this.f2173a.setPreviewSurface(surface, i, i2, i3, i4, i5);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setTexture(@NonNull SurfaceTexture surfaceTexture, int i) {
        return this.f2173a.setTexture(surfaceTexture, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setTexture(@NonNull SurfaceTexture surfaceTexture, int i, int i2, int i3, int i4, int i5) {
        return this.f2173a.setTexture(surfaceTexture, i, i2, i3, i4, i5);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean startPreview() {
        return this.f2173a.startPreview();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean stopPreview() {
        return this.f2173a.stopPreview();
    }
}
