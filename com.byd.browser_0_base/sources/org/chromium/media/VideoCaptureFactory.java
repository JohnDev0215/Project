package org.chromium.media;

import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.Log;
import android.util.SparseArray;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class VideoCaptureFactory {

    public static class a {
        public static int a = -1;
    }

    @CalledByNative
    public static VideoCapture createVideoCapture(int i, long j) {
        return isLegacyOrDeprecatedDevice(i) ? new n(i, j) : new o(i, j);
    }

    @CalledByNative
    public static int getCaptureApiType(int i) {
        boolean z;
        if (isLegacyOrDeprecatedDevice(i)) {
            return n.e(i) == null ? 0 : 7;
        }
        CameraCharacteristics cameraCharacteristicsI = o.i(o.k(i));
        if (cameraCharacteristicsI == null) {
            return 0;
        }
        int iIntValue = ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue();
        if (iIntValue != 2) {
            int[] iArr = (int[]) cameraCharacteristicsI.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
            int length = iArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z = false;
                    break;
                }
                if (iArr[i2] == 0) {
                    z = true;
                    break;
                }
                i2++;
            }
            if (!z) {
                return 0;
            }
            if (iIntValue == 0) {
                return 10;
            }
            if (iIntValue == 1) {
                return 9;
            }
        }
        return 8;
    }

    @CalledByNative
    public static int getCaptureFormatFramerate(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.c;
    }

    @CalledByNative
    public static int getCaptureFormatHeight(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.b;
    }

    @CalledByNative
    public static int getCaptureFormatPixelFormat(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.d;
    }

    @CalledByNative
    public static int getCaptureFormatWidth(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.a;
    }

    @CalledByNative
    public static String getDeviceId(int i) {
        if (!isLegacyOrDeprecatedDevice(i)) {
            return o.j(i);
        }
        SparseArray<String> sparseArray = n.r;
        return Integer.toString(i);
    }

    @CalledByNative
    public static String getDeviceName(int i) {
        if (isLegacyOrDeprecatedDevice(i)) {
            Camera.CameraInfo cameraInfoE = n.e(i);
            if (cameraInfoE == null) {
                return null;
            }
            return "camera " + i + ", facing " + (cameraInfoE.facing != 1 ? "back" : "front");
        }
        CameraCharacteristics cameraCharacteristicsI = o.i(o.k(i));
        if (cameraCharacteristicsI == null) {
            return null;
        }
        int iIntValue = ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.LENS_FACING)).intValue();
        Integer num = (Integer) cameraCharacteristicsI.get(CameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT);
        boolean z = num != null && num.equals(6);
        return "camera2 " + i + ", facing " + (iIntValue != 0 ? "back" : "front") + (z ? " infrared" : "");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x008f  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.media.VideoCaptureFormat[] getDeviceSupportedFormats(int r18) {
        /*
            Method dump skipped, instruction units count: 390
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.VideoCaptureFactory.getDeviceSupportedFormats(int):org.chromium.media.VideoCaptureFormat[]");
    }

    @CalledByNative
    public static int getFacingMode(int i) {
        if (isLegacyOrDeprecatedDevice(i)) {
            Camera.CameraInfo cameraInfoE = n.e(i);
            if (cameraInfoE != null) {
                int i2 = cameraInfoE.facing;
                if (i2 == 0) {
                    return 2;
                }
                if (i2 == 1) {
                    return 1;
                }
            }
            return 0;
        }
        CameraCharacteristics cameraCharacteristicsI = o.i(o.k(i));
        if (cameraCharacteristicsI != null) {
            int iIntValue = ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.LENS_FACING)).intValue();
            if (iIntValue == 0) {
                return 1;
            }
            if (iIntValue == 1) {
                return 2;
            }
        }
        return 0;
    }

    @CalledByNative
    public static int getNumberOfCameras() {
        int length;
        CameraManager cameraManager;
        if (a.a == -1) {
            String[] strArr = o.G;
            try {
                cameraManager = (CameraManager) xz.a.getSystemService("camera");
            } catch (IllegalArgumentException e) {
                Log.e("cr_VideoCapture", "getSystemService(Context.CAMERA_SERVICE): ", e);
            }
            if (cameraManager == null) {
                length = 0;
                a.a = length;
            } else {
                try {
                    length = cameraManager.getCameraIdList().length;
                } catch (CameraAccessException | AssertionError | SecurityException e2) {
                    Log.e("cr_VideoCapture", "getNumberOfCameras: getCameraIdList(): ", e2);
                    length = 0;
                }
                a.a = length;
            }
        }
        return a.a;
    }

    @CalledByNative
    public static boolean isLegacyOrDeprecatedDevice(int i) {
        CameraCharacteristics cameraCharacteristicsI = o.i(i);
        return cameraCharacteristicsI != null && ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2;
    }

    @CalledByNative
    public static boolean isZoomSupported(int i) {
        if (!isLegacyOrDeprecatedDevice(i)) {
            CameraCharacteristics cameraCharacteristicsI = o.i(o.k(i));
            return cameraCharacteristicsI != null && ((Float) cameraCharacteristicsI.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM)).floatValue() > 1.0f;
        }
        SparseArray<String> sparseArray = n.r;
        try {
            Camera cameraOpen = Camera.open(i);
            Camera.Parameters parametersF = n.f(cameraOpen);
            if (parametersF == null) {
                return false;
            }
            boolean zIsZoomSupported = parametersF.isZoomSupported();
            cameraOpen.release();
            return zIsZoomSupported;
        } catch (RuntimeException e) {
            Log.e("cr_VideoCapture", "Camera.open: ", e);
            return false;
        }
    }
}
