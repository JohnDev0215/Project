package com.byd.dilink_DiR.hardware.camera;

import android.graphics.SurfaceTexture;
import android.hardware.NormalCamera;
import android.media.MediaCodec;
import android.view.Surface;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.hardware.camera.IDiLinkNormalCamera;
import com.byd.dilink.hardware.camera.IDiLinkNormalEventCallBack;
import com.byd.dilink.hardware.camera.IDiLinkNormalPreviewCallback;

/* JADX INFO: loaded from: classes.dex */
public class DiLinkNormalCamera implements IDiLinkNormalCamera {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private NormalCamera f2180a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLinkDiR_NormalEventCallbacks f2181b = new DiLinkDiR_NormalEventCallbacks();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLinkDiR_NormalPreviewCallback f2182c = new DiLinkDiR_NormalPreviewCallback();

    public static class DiLinkDiR_NormalEventCallbacks extends DiLinkCallbackHolders<IDiLinkNormalEventCallBack, NormalCamera.IEventCallback> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public NormalCamera.IEventCallback createInnerCallback(final IDiLinkNormalEventCallBack iDiLinkNormalEventCallBack) {
            return new NormalCamera.IEventCallback() { // from class: com.byd.dilink_DiR.hardware.camera.DiLinkNormalCamera.DiLinkDiR_NormalEventCallbacks.1
                @Keep
                public void onEvent(NormalCamera normalCamera, int i, int i2, int i3) {
                    IDiLinkNormalEventCallBack iDiLinkNormalEventCallBack2;
                    DiLinkNormalCamera diLinkNormalCamera;
                    if (normalCamera != null) {
                        diLinkNormalCamera = new DiLinkNormalCamera(normalCamera);
                        iDiLinkNormalEventCallBack2 = iDiLinkNormalEventCallBack;
                    } else {
                        iDiLinkNormalEventCallBack2 = iDiLinkNormalEventCallBack;
                        diLinkNormalCamera = null;
                    }
                    iDiLinkNormalEventCallBack2.onEvent(diLinkNormalCamera, i, i2, i3);
                }
            };
        }
    }

    public static class DiLinkDiR_NormalPreviewCallback extends DiLinkCallbackHolders<IDiLinkNormalPreviewCallback, NormalCamera.IPreviewCallback> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public NormalCamera.IPreviewCallback createInnerCallback(final IDiLinkNormalPreviewCallback iDiLinkNormalPreviewCallback) {
            return new NormalCamera.IPreviewCallback() { // from class: com.byd.dilink_DiR.hardware.camera.DiLinkNormalCamera.DiLinkDiR_NormalPreviewCallback.1
                @Keep
                public void onPreview(NormalCamera normalCamera, byte[] bArr, int i, int i2, int i3, int i4, long j) {
                    if (normalCamera == null) {
                        iDiLinkNormalPreviewCallback.onPreview(null, bArr, i, i2, i3, i4, j);
                    } else {
                        iDiLinkNormalPreviewCallback.onPreview(new DiLinkNormalCamera(normalCamera), bArr, i, i2, i3, i4, j);
                    }
                }
            };
        }
    }

    public DiLinkNormalCamera(NormalCamera normalCamera) {
        if (normalCamera == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("normalCamera is null");
        }
        this.f2180a = normalCamera;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(NormalCamera.IEventCallback iEventCallback) {
        this.f2180a.setEventCallback(iEventCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(NormalCamera.IPreviewCallback iPreviewCallback) {
        this.f2180a.setPreviewCallback(iPreviewCallback);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean addPreviewSurface(@NonNull Surface surface) {
        return this.f2180a.addPreviewSurface(surface);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean addTexture(@NonNull SurfaceTexture surfaceTexture) {
        return this.f2180a.addTexture(surfaceTexture);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public void close() {
        this.f2180a.close();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean disablePreviewCallback() {
        return this.f2180a.disablePreviewCallback();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean enablePreviewCallback() {
        return this.f2180a.enablePreviewCallback();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public int getCameraId() {
        return this.f2180a.getCameraId();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public int getLogLevel() {
        return this.f2180a.getLogLevel();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public int getPreviewHeight() {
        return this.f2180a.getPreviewHeight();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public int getPreviewWidth() {
        return this.f2180a.getPreviewWidth();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean isOpen() {
        return this.f2180a.isOpen();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean isPreview() {
        return this.f2180a.isPreview();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean rmPreviewSurface(@NonNull Surface surface) {
        return this.f2180a.rmPreviewSurface(surface);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean rmTexture(@NonNull SurfaceTexture surfaceTexture) {
        return this.f2180a.rmTexture(surfaceTexture);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean setAlgMode(int i) {
        return this.f2180a.setAlgMode(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean setCameraFps(int i) {
        return this.f2180a.setCameraFps(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean setDisplayOrientation(@NonNull Surface surface, int i) {
        return this.f2180a.setDisplayOrientation(surface, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public void setEventCallback(@NonNull IDiLinkNormalEventCallBack iDiLinkNormalEventCallBack) {
        this.f2181b.addCallbackIgnoreRemoteException(iDiLinkNormalEventCallBack, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.hardware.camera.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2189a.c((NormalCamera.IEventCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public void setLogLevel(int i) {
        this.f2180a.setLogLevel(i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean setMediaCodec(@NonNull MediaCodec mediaCodec) {
        return this.f2180a.setMediaCodec(mediaCodec);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean setMediaCodecFps(@NonNull MediaCodec mediaCodec, int i) {
        return this.f2180a.setMediaCodecFps(mediaCodec, i);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public void setPreviewCallback(@NonNull IDiLinkNormalPreviewCallback iDiLinkNormalPreviewCallback) {
        this.f2182c.addCallbackIgnoreRemoteException(iDiLinkNormalPreviewCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_DiR.hardware.camera.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2190a.d((NormalCamera.IPreviewCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean setPreviewSize(int i, int i2) {
        return this.f2180a.setPreviewSize(i, i2);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean setPreviewSurface(@NonNull Surface surface) {
        return this.f2180a.setPreviewSurface(surface);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean setTexture(@NonNull SurfaceTexture surfaceTexture) {
        return this.f2180a.setTexture(surfaceTexture);
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean startPreview() {
        return this.f2180a.startPreview();
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCamera
    public boolean stopPreview() {
        return this.f2180a.stopPreview();
    }
}
