package org.chromium.media;

import android.content.Context;
import android.os.Build;
import org.chromium.base.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media")
class VideoCaptureFactory {
    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isLReleaseOrLater() {
        return true;
    }

    VideoCaptureFactory() {
    }

    static class ChromiumCameraInfo {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final String TAG = "cr.media";
        private static final String[][] SPECIAL_DEVICE_LIST = {new String[]{"Peanut", "peanut"}};
        private static int sNumberOfSystemCameras = -1;

        ChromiumCameraInfo() {
        }

        private static boolean isSpecialDevice() {
            for (String[] strArr : SPECIAL_DEVICE_LIST) {
                if (strArr[0].contentEquals(Build.MODEL) && strArr[1].contentEquals(Build.DEVICE)) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isSpecialCamera(int i) {
            return i >= sNumberOfSystemCameras;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int toSpecialCameraId(int i) {
            return i - sNumberOfSystemCameras;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int getNumberOfCameras(Context context) {
            if (sNumberOfSystemCameras == -1) {
                if (VideoCaptureFactory.isLReleaseOrLater()) {
                    sNumberOfSystemCameras = VideoCaptureCamera2.getNumberOfCameras(context);
                } else {
                    sNumberOfSystemCameras = VideoCaptureAndroid.getNumberOfCameras();
                    if (isSpecialDevice()) {
                        Log.d(TAG, "Special device: %s", Build.MODEL);
                        sNumberOfSystemCameras += VideoCaptureTango.numberOfCameras();
                    }
                }
            }
            return sNumberOfSystemCameras;
        }
    }

    static VideoCapture createVideoCapture(Context context, int i, long j) {
        if (!isLReleaseOrLater() || VideoCaptureCamera2.isLegacyDevice(context, i)) {
            if (!ChromiumCameraInfo.isSpecialCamera(i)) {
                return new VideoCaptureAndroid(context, i, j);
            }
            return new VideoCaptureTango(context, ChromiumCameraInfo.toSpecialCameraId(i), j);
        }
        return new VideoCaptureCamera2(context, i, j);
    }

    static int getNumberOfCameras(Context context) {
        return ChromiumCameraInfo.getNumberOfCameras(context);
    }

    static int getCaptureApiType(int i, Context context) {
        if (!isLReleaseOrLater()) {
            if (ChromiumCameraInfo.isSpecialCamera(i)) {
                return VideoCaptureTango.getCaptureApiType(ChromiumCameraInfo.toSpecialCameraId(i));
            }
            return VideoCaptureAndroid.getCaptureApiType(i);
        }
        return VideoCaptureCamera2.getCaptureApiType(i, context);
    }

    static String getDeviceName(int i, Context context) {
        if (!isLReleaseOrLater() || VideoCaptureCamera2.isLegacyDevice(context, i)) {
            return ChromiumCameraInfo.isSpecialCamera(i) ? VideoCaptureTango.getName(ChromiumCameraInfo.toSpecialCameraId(i)) : VideoCaptureAndroid.getName(i);
        }
        return VideoCaptureCamera2.getName(i, context);
    }

    static VideoCaptureFormat[] getDeviceSupportedFormats(Context context, int i) {
        if (!isLReleaseOrLater() || VideoCaptureCamera2.isLegacyDevice(context, i)) {
            return ChromiumCameraInfo.isSpecialCamera(i) ? VideoCaptureTango.getDeviceSupportedFormats(ChromiumCameraInfo.toSpecialCameraId(i)) : VideoCaptureAndroid.getDeviceSupportedFormats(i);
        }
        return VideoCaptureCamera2.getDeviceSupportedFormats(context, i);
    }

    static int getCaptureFormatWidth(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.getWidth();
    }

    static int getCaptureFormatHeight(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.getHeight();
    }

    static int getCaptureFormatFramerate(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.getFramerate();
    }

    static int getCaptureFormatPixelFormat(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.getPixelFormat();
    }
}
