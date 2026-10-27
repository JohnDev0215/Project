package org.chromium.media;

import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.os.Build;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Log;

/* JADX INFO: loaded from: classes3.dex */
public class VideoCaptureAndroid extends VideoCaptureCamera {
    private static final int NUM_CAPTURE_BUFFERS = 3;
    private static final String TAG = "cr.media";
    private int mExpectedFrameSize;

    private static class BuggyDeviceHack {
        private static final String[] COLORSPACE_BUGGY_DEVICE_LIST = {"SAMSUNG-SGH-I747", "ODROID-U2", "XT1092", "XT1095", "XT1096", "XT1097"};

        private BuggyDeviceHack() {
        }

        static int getImageFormat() {
            for (String str : COLORSPACE_BUGGY_DEVICE_LIST) {
                if (str.contentEquals(Build.MODEL)) {
                    return 17;
                }
            }
            return AndroidImageFormat.YV12;
        }
    }

    static int getNumberOfCameras() {
        return Camera.getNumberOfCameras();
    }

    static int getCaptureApiType(int i) {
        return VideoCaptureCamera.getCameraInfo(i) == null ? 5 : 0;
    }

    static String getName(int i) {
        Camera.CameraInfo cameraInfo = VideoCaptureCamera.getCameraInfo(i);
        if (cameraInfo == null) {
            return null;
        }
        return "camera " + i + ", facing " + (cameraInfo.facing == 1 ? "front" : "back");
    }

    static VideoCaptureFormat[] getDeviceSupportedFormats(int i) {
        try {
            Camera cameraOpen = Camera.open(i);
            Camera.Parameters cameraParameters = getCameraParameters(cameraOpen);
            if (cameraParameters == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            List<Integer> supportedPreviewFormats = cameraParameters.getSupportedPreviewFormats();
            if (supportedPreviewFormats == null) {
                supportedPreviewFormats = new ArrayList<>();
            }
            if (supportedPreviewFormats.size() == 0) {
                supportedPreviewFormats.add(0);
            }
            for (Integer num : supportedPreviewFormats) {
                int iIntValue = num.intValue();
                int i2 = AndroidImageFormat.YV12;
                if (iIntValue != 842094169) {
                    if (num.intValue() != 17) {
                        i2 = 0;
                    }
                }
                List<int[]> supportedPreviewFpsRange = cameraParameters.getSupportedPreviewFpsRange();
                if (supportedPreviewFpsRange == null) {
                    supportedPreviewFpsRange = new ArrayList<>();
                }
                if (supportedPreviewFpsRange.size() == 0) {
                    supportedPreviewFpsRange.add(new int[]{0, 0});
                }
                for (int[] iArr : supportedPreviewFpsRange) {
                    List<Camera.Size> supportedPreviewSizes = cameraParameters.getSupportedPreviewSizes();
                    if (supportedPreviewSizes == null) {
                        supportedPreviewSizes = new ArrayList<>();
                    }
                    if (supportedPreviewSizes.size() == 0) {
                        cameraOpen.getClass();
                        supportedPreviewSizes.add(new Camera.Size(cameraOpen, 0, 0));
                    }
                    for (Camera.Size size : supportedPreviewSizes) {
                        arrayList.add(new VideoCaptureFormat(size.width, size.height, (iArr[1] + 999) / 1000, i2));
                    }
                }
            }
            cameraOpen.release();
            return (VideoCaptureFormat[]) arrayList.toArray(new VideoCaptureFormat[arrayList.size()]);
        } catch (RuntimeException e) {
            Log.e(TAG, "Camera.open: ", e);
            return null;
        }
    }

    VideoCaptureAndroid(Context context, int i, long j) {
        super(context, i, j);
    }

    @Override // org.chromium.media.VideoCaptureCamera
    protected void setCaptureParameters(int i, int i2, int i3, Camera.Parameters parameters) {
        this.mCaptureFormat = new VideoCaptureFormat(i, i2, i3, BuggyDeviceHack.getImageFormat());
    }

    @Override // org.chromium.media.VideoCaptureCamera
    protected void allocateBuffers() {
        this.mExpectedFrameSize = ((this.mCaptureFormat.mWidth * this.mCaptureFormat.mHeight) * ImageFormat.getBitsPerPixel(this.mCaptureFormat.mPixelFormat)) / 8;
        for (int i = 0; i < 3; i++) {
            this.mCamera.addCallbackBuffer(new byte[this.mExpectedFrameSize]);
        }
    }

    @Override // org.chromium.media.VideoCaptureCamera
    protected void setPreviewCallback(Camera.PreviewCallback previewCallback) {
        this.mCamera.setPreviewCallbackWithBuffer(previewCallback);
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        this.mPreviewBufferLock.lock();
        try {
            if (!this.mIsRunning) {
                if (camera != null) {
                    return;
                } else {
                    return;
                }
            }
            if (bArr.length == this.mExpectedFrameSize) {
                nativeOnFrameAvailable(this.mNativeVideoCaptureDeviceAndroid, bArr, this.mExpectedFrameSize, getCameraRotation());
            }
            this.mPreviewBufferLock.unlock();
            if (camera != null) {
                camera.addCallbackBuffer(bArr);
            }
        } finally {
            this.mPreviewBufferLock.unlock();
            if (camera != null) {
                camera.addCallbackBuffer(bArr);
            }
        }
    }
}
