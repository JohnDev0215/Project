package com.byd.dilink51_main.hardware.camera;

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
    private AVMCamera f1746a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink5_1_EventCallbacks f1747b = new DiLink5_1_EventCallbacks();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLink5_1_PreviewCallback f1748c = new DiLink5_1_PreviewCallback();

    public static class DiLink5_1_EventCallbacks extends DiLinkCallbackHolders<IDiLinkAVMEventCallBack, AVMCamera.IEventCallback> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AVMCamera.IEventCallback createInnerCallback(final IDiLinkAVMEventCallBack iDiLinkAVMEventCallBack) {
            return new AVMCamera.IEventCallback() { // from class: com.byd.dilink51_main.hardware.camera.DiLinkAVMCamera.DiLink5_1_EventCallbacks.1
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

    public static class DiLink5_1_PreviewCallback extends DiLinkCallbackHolders<IDiLinkAVMPreviewCallback, AVMCamera.IPreviewCallback> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AVMCamera.IPreviewCallback createInnerCallback(final IDiLinkAVMPreviewCallback iDiLinkAVMPreviewCallback) {
            return new AVMCamera.IPreviewCallback() { // from class: com.byd.dilink51_main.hardware.camera.DiLinkAVMCamera.DiLink5_1_PreviewCallback.1
                @Keep
                public void onPreview(AVMCamera aVMCamera, byte[] bArr, int i, int i2, int i3, int i4, int i5, long j) {
                    if (aVMCamera == null) {
                        iDiLinkAVMPreviewCallback.onPreview(null, bArr, i, i2, i3, i4, i5, j);
                    } else {
                        iDiLinkAVMPreviewCallback.onPreview(new DiLinkAVMCamera(aVMCamera), bArr, i, i2, i3, i4, i5, j);
                    }
                }
            };
        }
    }

    public DiLinkAVMCamera(AVMCamera aVMCamera) {
        if (aVMCamera == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("AVMCamera is null");
        }
        this.f1746a = aVMCamera;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(AVMCamera.IEventCallback iEventCallback) {
        this.f1746a.setEventCallback(iEventCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(AVMCamera.IPreviewCallback iPreviewCallback) {
        this.f1746a.setPreviewCallback(iPreviewCallback);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean addPreviewSurface(@NonNull Surface surface, int i) {
        return this.f1746a.addPreviewSurface(surface, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean addTexture(@NonNull SurfaceTexture surfaceTexture, int i) {
        return this.f1746a.addTexture(surfaceTexture, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public void close() {
        this.f1746a.close();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean disablePreviewCallback(int i) {
        return this.f1746a.disablePreviewCallback(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean disablePreviewCallback(int i, int i2, int i3, int i4, int i5) {
        return this.f1746a.disablePreviewCallback(i, i2, i3, i4, i5);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean enablePreviewCallback(int i) {
        return this.f1746a.enablePreviewCallback(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean enablePreviewCallback(int i, int i2, int i3, int i4, int i5) {
        return this.f1746a.enablePreviewCallback(i, i2, i3, i4, i5);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public int getCameraId() {
        return this.f1746a.getCameraId();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public int getLogLevel() {
        return this.f1746a.getLogLevel();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public int getPreviewHeight() {
        return this.f1746a.getPreviewHeight();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public int getPreviewWidth() {
        return this.f1746a.getPreviewWidth();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean isOpen() {
        return this.f1746a.isOpen();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean isPreview() {
        return this.f1746a.isPreview();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean rmPreviewSurface(@NonNull Surface surface, int i) {
        return this.f1746a.rmPreviewSurface(surface, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean rmTexture(@NonNull SurfaceTexture surfaceTexture, int i) {
        return this.f1746a.rmTexture(surfaceTexture, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setAlgMode(int i) {
        return this.f1746a.setAlgMode(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setCameraFps(int i) {
        return this.f1746a.setCameraFps(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setDisplayOrientation(Surface surface, int i) {
        return this.f1746a.setDisplayOrientation(surface, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public void setEventCallback(@NonNull IDiLinkAVMEventCallBack iDiLinkAVMEventCallBack) {
        this.f1747b.addCallbackIgnoreRemoteException(iDiLinkAVMEventCallBack, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.hardware.camera.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1760a.a((AVMCamera.IEventCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public void setLogLevel(int i) {
        this.f1746a.setLogLevel(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setMediaCodec(@NonNull MediaCodec mediaCodec, int i) {
        return this.f1746a.setMediaCodec(mediaCodec, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setMediaCodec(@NonNull MediaCodec mediaCodec, int i, int i2, int i3, int i4, int i5) {
        return this.f1746a.setMediaCodec(mediaCodec, i, i2, i3, i4, i5);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setMediaCodecFps(@NonNull MediaCodec mediaCodec, int i) {
        return this.f1746a.setMediaCodecFps(mediaCodec, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public void setPreviewCallback(@NonNull IDiLinkAVMPreviewCallback iDiLinkAVMPreviewCallback) {
        this.f1748c.addCallbackIgnoreRemoteException(iDiLinkAVMPreviewCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.hardware.camera.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1761a.b((AVMCamera.IPreviewCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setPreviewSize(int i, int i2) {
        return this.f1746a.setPreviewSize(i, i2);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setPreviewSurface(@NonNull Surface surface, int i) {
        return this.f1746a.setPreviewSurface(surface, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setPreviewSurface(@NonNull Surface surface, int i, int i2, int i3, int i4, int i5) {
        return this.f1746a.setPreviewSurface(surface, i, i2, i3, i4, i5);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setTexture(@NonNull SurfaceTexture surfaceTexture, int i) {
        return this.f1746a.setTexture(surfaceTexture, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean setTexture(@NonNull SurfaceTexture surfaceTexture, int i, int i2, int i3, int i4, int i5) {
        return this.f1746a.setTexture(surfaceTexture, i, i2, i3, i4, i5);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean startPreview() {
        return this.f1746a.startPreview();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCamera
    public boolean stopPreview() {
        return this.f1746a.stopPreview();
    }
}
