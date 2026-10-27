package org.chromium.media;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES20;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media")
public abstract class VideoCaptureCamera extends VideoCapture implements Camera.PreviewCallback {
    protected static final int GL_TEXTURE_EXTERNAL_OES = 36197;
    private static final String TAG = "VideoCapture";
    protected Camera mCamera;
    protected int[] mGlTextures;
    protected boolean mIsRunning;
    private long mPhotoTakenCallbackId;
    private final Object mPhotoTakenCallbackLock;
    protected ReentrantLock mPreviewBufferLock;
    protected SurfaceTexture mSurfaceTexture;

    abstract void allocateBuffers();

    abstract void setCaptureParameters(int i, int i2, int i3, Camera.Parameters parameters);

    abstract void setPreviewCallback(Camera.PreviewCallback previewCallback);

    protected static Camera.CameraInfo getCameraInfo(int i) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        try {
            Camera.getCameraInfo(i, cameraInfo);
            return cameraInfo;
        } catch (RuntimeException e) {
            Log.e(TAG, "getCameraInfo: Camera.getCameraInfo: " + e, new Object[0]);
            return null;
        }
    }

    protected static Camera.Parameters getCameraParameters(Camera camera) {
        try {
            return camera.getParameters();
        } catch (RuntimeException e) {
            Log.e(TAG, "getCameraParameters: android.hardware.Camera.getParameters: " + e, new Object[0]);
            if (camera == null) {
                return null;
            }
            camera.release();
            return null;
        }
    }

    private class CrErrorCallback implements Camera.ErrorCallback {
        private CrErrorCallback() {
        }

        @Override // android.hardware.Camera.ErrorCallback
        public void onError(int i, Camera camera) {
            VideoCaptureCamera videoCaptureCamera = VideoCaptureCamera.this;
            videoCaptureCamera.nativeOnError(videoCaptureCamera.mNativeVideoCaptureDeviceAndroid, "Error id: " + i);
            synchronized (VideoCaptureCamera.this.mPhotoTakenCallbackLock) {
                if (VideoCaptureCamera.this.mPhotoTakenCallbackId == 0) {
                    return;
                }
                VideoCaptureCamera videoCaptureCamera2 = VideoCaptureCamera.this;
                videoCaptureCamera2.nativeOnPhotoTaken(videoCaptureCamera2.mNativeVideoCaptureDeviceAndroid, VideoCaptureCamera.this.mPhotoTakenCallbackId, new byte[0]);
                VideoCaptureCamera.this.mPhotoTakenCallbackId = 0L;
            }
        }
    }

    private class CrPictureCallback implements Camera.PictureCallback {
        private CrPictureCallback() {
        }

        @Override // android.hardware.Camera.PictureCallback
        public void onPictureTaken(byte[] bArr, Camera camera) {
            synchronized (VideoCaptureCamera.this.mPhotoTakenCallbackLock) {
                if (VideoCaptureCamera.this.mPhotoTakenCallbackId != 0) {
                    VideoCaptureCamera videoCaptureCamera = VideoCaptureCamera.this;
                    videoCaptureCamera.nativeOnPhotoTaken(videoCaptureCamera.mNativeVideoCaptureDeviceAndroid, VideoCaptureCamera.this.mPhotoTakenCallbackId, bArr);
                }
                VideoCaptureCamera.this.mPhotoTakenCallbackId = 0L;
            }
            Camera.Parameters cameraParameters = VideoCaptureCamera.getCameraParameters(VideoCaptureCamera.this.mCamera);
            cameraParameters.setRotation(0);
            VideoCaptureCamera.this.mCamera.setParameters(cameraParameters);
            camera.startPreview();
        }
    }

    VideoCaptureCamera(Context context, int i, long j) {
        super(context, i, j);
        this.mPhotoTakenCallbackLock = new Object();
        this.mPhotoTakenCallbackId = 0L;
        this.mPreviewBufferLock = new ReentrantLock();
        this.mIsRunning = false;
        this.mGlTextures = null;
        this.mSurfaceTexture = null;
    }

    @Override // org.chromium.media.VideoCapture
    public boolean allocate(int i, int i2, int i3) {
        int i4;
        Log.d(TAG, "allocate: requested (%d x %d) @%dfps", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
        try {
            this.mCamera = Camera.open(this.mId);
            Camera.CameraInfo cameraInfo = getCameraInfo(this.mId);
            if (cameraInfo == null) {
                this.mCamera.release();
                this.mCamera = null;
                return false;
            }
            this.mCameraNativeOrientation = cameraInfo.orientation;
            this.mInvertDeviceOrientationReadings = cameraInfo.facing == 0;
            Log.d(TAG, "allocate: Rotation dev=%d, cam=%d, facing back? %s", Integer.valueOf(getDeviceRotation()), Integer.valueOf(this.mCameraNativeOrientation), Boolean.valueOf(this.mInvertDeviceOrientationReadings));
            Camera.Parameters cameraParameters = getCameraParameters(this.mCamera);
            if (cameraParameters == null) {
                this.mCamera = null;
                return false;
            }
            List<int[]> supportedPreviewFpsRange = cameraParameters.getSupportedPreviewFpsRange();
            if (supportedPreviewFpsRange == null || supportedPreviewFpsRange.size() == 0) {
                Log.e(TAG, "allocate: no fps range found", new Object[0]);
                return false;
            }
            int i5 = i3 * 1000;
            int[] iArr = supportedPreviewFpsRange.get(0);
            int i6 = ((Math.abs(i5 - iArr[0]) < Math.abs(i5 - iArr[1]) ? iArr[0] : iArr[1]) + 999) / 1000;
            int i7 = Integer.MAX_VALUE;
            for (int[] iArr2 : supportedPreviewFpsRange) {
                int i8 = iArr2[0];
                if (i8 <= i5 && i5 <= (i4 = iArr2[1]) && i4 - i8 <= i7) {
                    i7 = i4 - i8;
                    i6 = i3;
                    iArr = iArr2;
                }
            }
            Log.d(TAG, "allocate: fps set to %d, [%d-%d]", Integer.valueOf(i6), Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
            int i9 = i;
            int i10 = i2;
            int i11 = Integer.MAX_VALUE;
            for (Camera.Size size : cameraParameters.getSupportedPreviewSizes()) {
                int iAbs = Math.abs(size.width - i) + Math.abs(size.height - i2);
                Log.d(TAG, "allocate: supported (%d, %d), diff=%d", Integer.valueOf(size.width), Integer.valueOf(size.height), Integer.valueOf(iAbs));
                if (iAbs < i11 && size.width % 32 == 0) {
                    i9 = size.width;
                    i10 = size.height;
                    i11 = iAbs;
                }
            }
            if (i11 == Integer.MAX_VALUE) {
                Log.e(TAG, "allocate: can not find a multiple-of-32 resolution", new Object[0]);
                return false;
            }
            Log.d(TAG, "allocate: matched (%d x %d)", Integer.valueOf(i9), Integer.valueOf(i10));
            if (cameraParameters.isVideoStabilizationSupported()) {
                Log.d(TAG, "Image stabilization supported, currently: " + cameraParameters.getVideoStabilization() + ", setting it.");
                cameraParameters.setVideoStabilization(true);
            } else {
                Log.d(TAG, "Image stabilization not supported.");
            }
            if (cameraParameters.getSupportedFocusModes().contains("continuous-video")) {
                cameraParameters.setFocusMode("continuous-video");
            } else {
                Log.d(TAG, "Continuous focus mode not supported.");
            }
            setCaptureParameters(i9, i10, i6, cameraParameters);
            cameraParameters.setPictureSize(i9, i10);
            cameraParameters.setPreviewSize(i9, i10);
            cameraParameters.setPreviewFpsRange(iArr[0], iArr[1]);
            cameraParameters.setPreviewFormat(this.mCaptureFormat.mPixelFormat);
            try {
                this.mCamera.setParameters(cameraParameters);
                int[] iArr3 = new int[1];
                this.mGlTextures = iArr3;
                GLES20.glGenTextures(1, iArr3, 0);
                GLES20.glBindTexture(GL_TEXTURE_EXTERNAL_OES, this.mGlTextures[0]);
                GLES20.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, 10241, 9729.0f);
                GLES20.glTexParameterf(GL_TEXTURE_EXTERNAL_OES, 10240, 9729.0f);
                GLES20.glTexParameteri(GL_TEXTURE_EXTERNAL_OES, 10242, 33071);
                GLES20.glTexParameteri(GL_TEXTURE_EXTERNAL_OES, 10243, 33071);
                SurfaceTexture surfaceTexture = new SurfaceTexture(this.mGlTextures[0]);
                this.mSurfaceTexture = surfaceTexture;
                surfaceTexture.setOnFrameAvailableListener(null);
                try {
                    this.mCamera.setPreviewTexture(this.mSurfaceTexture);
                    this.mCamera.setErrorCallback(new CrErrorCallback());
                    allocateBuffers();
                    return true;
                } catch (IOException e) {
                    Log.e(TAG, "allocate: " + e, new Object[0]);
                    return false;
                }
            } catch (RuntimeException e2) {
                Log.e(TAG, "setParameters: " + e2, new Object[0]);
                return false;
            }
        } catch (RuntimeException e3) {
            Log.e(TAG, "allocate: Camera.open: " + e3, new Object[0]);
            return false;
        }
    }

    @Override // org.chromium.media.VideoCapture
    public boolean startCapture() {
        if (this.mCamera == null) {
            Log.e(TAG, "startCapture: mCamera is null", new Object[0]);
            return false;
        }
        this.mPreviewBufferLock.lock();
        try {
            if (this.mIsRunning) {
                return true;
            }
            this.mIsRunning = true;
            this.mPreviewBufferLock.unlock();
            setPreviewCallback(this);
            try {
                this.mCamera.startPreview();
                return true;
            } catch (RuntimeException e) {
                Log.e(TAG, "startCapture: Camera.startPreview: " + e, new Object[0]);
                return false;
            }
        } finally {
            this.mPreviewBufferLock.unlock();
        }
    }

    @Override // org.chromium.media.VideoCapture
    public boolean stopCapture() {
        if (this.mCamera == null) {
            Log.e(TAG, "stopCapture: mCamera is null", new Object[0]);
            return true;
        }
        this.mPreviewBufferLock.lock();
        try {
            if (!this.mIsRunning) {
                return true;
            }
            this.mIsRunning = false;
            this.mPreviewBufferLock.unlock();
            this.mCamera.stopPreview();
            setPreviewCallback(null);
            return true;
        } finally {
            this.mPreviewBufferLock.unlock();
        }
    }

    @Override // org.chromium.media.VideoCapture
    public PhotoCapabilities getPhotoCapabilities() {
        Camera.Parameters cameraParameters = getCameraParameters(this.mCamera);
        if (!cameraParameters.isZoomSupported()) {
            return new PhotoCapabilities(0, 0, 0);
        }
        Log.d(TAG, "parameters.getZoomRatios(): " + cameraParameters.getZoomRatios().toString());
        return new PhotoCapabilities(cameraParameters.getZoomRatios().get(cameraParameters.getMaxZoom()).intValue(), 100, (cameraParameters.getZoom() * 100) + 100);
    }

    @Override // org.chromium.media.VideoCapture
    public boolean takePhoto(long j) {
        if (this.mCamera == null || !this.mIsRunning) {
            Log.e(TAG, "takePhoto: mCamera is null or is not running", new Object[0]);
            return false;
        }
        synchronized (this.mPhotoTakenCallbackLock) {
            if (this.mPhotoTakenCallbackId != 0) {
                return false;
            }
            this.mPhotoTakenCallbackId = j;
            Camera.Parameters cameraParameters = getCameraParameters(this.mCamera);
            cameraParameters.setRotation(getCameraRotation());
            this.mCamera.setParameters(cameraParameters);
            try {
                this.mCamera.takePicture(null, null, null, new CrPictureCallback());
                return true;
            } catch (RuntimeException e) {
                Log.e(TAG, "takePicture ", e);
                return false;
            }
        }
    }

    @Override // org.chromium.media.VideoCapture
    public void deallocate() {
        if (this.mCamera == null) {
            return;
        }
        stopCapture();
        try {
            this.mCamera.setPreviewTexture(null);
            int[] iArr = this.mGlTextures;
            if (iArr != null) {
                GLES20.glDeleteTextures(1, iArr, 0);
            }
            this.mCaptureFormat = null;
            this.mCamera.release();
            this.mCamera = null;
        } catch (IOException e) {
            Log.e(TAG, "deallocate: failed to deallocate camera, " + e, new Object[0]);
        }
    }
}
