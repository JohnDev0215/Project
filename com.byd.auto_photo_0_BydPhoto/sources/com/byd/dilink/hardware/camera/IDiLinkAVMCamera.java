package com.byd.dilink.hardware.camera;

import android.graphics.SurfaceTexture;
import android.media.MediaCodec;
import android.view.Surface;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkAVMCamera {
    boolean addPreviewSurface(@NonNull Surface surface, int i);

    boolean addTexture(@NonNull SurfaceTexture surfaceTexture, int i);

    void close();

    boolean disablePreviewCallback(int i);

    boolean disablePreviewCallback(int i, int i2, int i3, int i4, int i5);

    boolean enablePreviewCallback(int i);

    boolean enablePreviewCallback(int i, int i2, int i3, int i4, int i5);

    int getCameraId();

    int getLogLevel();

    int getPreviewHeight();

    int getPreviewWidth();

    boolean isOpen();

    boolean isPreview();

    boolean rmPreviewSurface(@NonNull Surface surface, int i);

    boolean rmTexture(@NonNull SurfaceTexture surfaceTexture, int i);

    boolean setAlgMode(int i);

    boolean setCameraFps(int i);

    boolean setDisplayOrientation(Surface surface, int i);

    void setEventCallback(@NonNull IDiLinkAVMEventCallBack iDiLinkAVMEventCallBack);

    void setLogLevel(int i);

    boolean setMediaCodec(@NonNull MediaCodec mediaCodec, int i);

    boolean setMediaCodec(@NonNull MediaCodec mediaCodec, int i, int i2, int i3, int i4, int i5);

    boolean setMediaCodecFps(@NonNull MediaCodec mediaCodec, int i);

    void setPreviewCallback(@NonNull IDiLinkAVMPreviewCallback iDiLinkAVMPreviewCallback);

    boolean setPreviewSize(int i, int i2);

    boolean setPreviewSurface(@NonNull Surface surface, int i);

    boolean setPreviewSurface(@NonNull Surface surface, int i, int i2, int i3, int i4, int i5);

    boolean setTexture(@NonNull SurfaceTexture surfaceTexture, int i);

    boolean setTexture(@NonNull SurfaceTexture surfaceTexture, int i, int i2, int i3, int i4, int i5);

    boolean startPreview();

    boolean stopPreview();
}
