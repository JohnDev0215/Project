package com.byd.dilink.hardware.camera;

import android.graphics.SurfaceTexture;
import android.media.MediaCodec;
import android.view.Surface;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkNormalCamera {
    boolean addPreviewSurface(@NonNull Surface surface);

    boolean addTexture(@NonNull SurfaceTexture surfaceTexture);

    void close();

    boolean disablePreviewCallback();

    boolean enablePreviewCallback();

    int getCameraId();

    int getLogLevel();

    int getPreviewHeight();

    int getPreviewWidth();

    boolean isOpen();

    boolean isPreview();

    boolean rmPreviewSurface(@NonNull Surface surface);

    boolean rmTexture(@NonNull SurfaceTexture surfaceTexture);

    boolean setAlgMode(int i);

    boolean setCameraFps(int i);

    boolean setDisplayOrientation(@NonNull Surface surface, int i);

    void setEventCallback(@NonNull IDiLinkNormalEventCallBack iDiLinkNormalEventCallBack);

    void setLogLevel(int i);

    boolean setMediaCodec(@NonNull MediaCodec mediaCodec);

    boolean setMediaCodecFps(@NonNull MediaCodec mediaCodec, int i);

    void setPreviewCallback(@NonNull IDiLinkNormalPreviewCallback iDiLinkNormalPreviewCallback);

    boolean setPreviewSize(int i, int i2);

    boolean setPreviewSurface(@NonNull Surface surface);

    boolean setTexture(@NonNull SurfaceTexture surfaceTexture);

    boolean startPreview();

    boolean stopPreview();
}
