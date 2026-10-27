package org.chromium.media;

import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.util.Size;
import defpackage.AbstractC0654dI;
import defpackage.C0542bI;
import defpackage.C0598cI;
import defpackage.Is;
import defpackage.Ts;
import java.util.ArrayList;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VideoCaptureFactory {
    public static VideoCapture createVideoCapture(int i, long j) {
        return isLegacyOrDeprecatedDevice(i) ? new C0598cI(i, j) : new C0542bI(i, j);
    }

    public static int getCaptureApiType(int i) {
        if (isLegacyOrDeprecatedDevice(i)) {
            return C0598cI.e(i) == null ? 11 : 6;
        }
        CameraCharacteristics cameraCharacteristicsI = C0542bI.i(i);
        if (cameraCharacteristicsI == null) {
            return 11;
        }
        int iIntValue = ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue();
        if (iIntValue != 0) {
            return iIntValue != 1 ? 7 : 8;
        }
        return 9;
    }

    public static int getCaptureFormatFramerate(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.c;
    }

    public static int getCaptureFormatHeight(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.b;
    }

    public static int getCaptureFormatPixelFormat(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.d;
    }

    public static int getCaptureFormatWidth(VideoCaptureFormat videoCaptureFormat) {
        return videoCaptureFormat.a;
    }

    public static String getDeviceName(int i) {
        Integer num;
        if (isLegacyOrDeprecatedDevice(i)) {
            Camera.CameraInfo cameraInfoE = C0598cI.e(i);
            if (cameraInfoE == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("camera ");
            sb.append(i);
            sb.append(", facing ");
            sb.append(cameraInfoE.facing != 1 ? "back" : "front");
            return sb.toString();
        }
        CameraCharacteristics cameraCharacteristicsI = C0542bI.i(i);
        if (cameraCharacteristicsI == null) {
            return null;
        }
        int iIntValue = ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.LENS_FACING)).intValue();
        boolean z = Build.VERSION.SDK_INT >= 29 && (num = (Integer) cameraCharacteristicsI.get(CameraCharacteristics.SENSOR_INFO_COLOR_FILTER_ARRANGEMENT)) != null && num.equals(6);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("camera2 ");
        sb2.append(i);
        sb2.append(", facing ");
        sb2.append(iIntValue != 0 ? "back" : "front");
        sb2.append(z ? " infrared" : "");
        return sb2.toString();
    }

    public static VideoCaptureFormat[] getDeviceSupportedFormats(int i) {
        boolean z;
        if (isLegacyOrDeprecatedDevice(i)) {
            return C0598cI.g(i);
        }
        CameraCharacteristics cameraCharacteristicsI = C0542bI.i(i);
        if (cameraCharacteristicsI == null) {
            return null;
        }
        int[] iArr = (int[]) cameraCharacteristicsI.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);
        int length = iArr.length;
        int i2 = 0;
        while (true) {
            z = true;
            if (i2 >= length) {
                z = false;
                break;
            }
            if (iArr[i2] == 1) {
                break;
            }
            i2++;
        }
        ArrayList arrayList = new ArrayList();
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristicsI.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        for (int i3 : streamConfigurationMap.getOutputFormats()) {
            Size[] outputSizes = streamConfigurationMap.getOutputSizes(i3);
            if (outputSizes != null) {
                for (Size size : outputSizes) {
                    double d = 0.0d;
                    if (z) {
                        long outputMinFrameDuration = streamConfigurationMap.getOutputMinFrameDuration(i3, size);
                        if (outputMinFrameDuration != 0) {
                            d = 1.0E9d / outputMinFrameDuration;
                        }
                    }
                    arrayList.add(new VideoCaptureFormat(size.getWidth(), size.getHeight(), (int) d, i3));
                }
            }
        }
        return (VideoCaptureFormat[]) arrayList.toArray(new VideoCaptureFormat[arrayList.size()]);
    }

    public static int getFacingMode(int i) {
        if (isLegacyOrDeprecatedDevice(i)) {
            Camera.CameraInfo cameraInfoE = C0598cI.e(i);
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
        CameraCharacteristics cameraCharacteristicsI = C0542bI.i(i);
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

    public static int getNumberOfCameras() {
        if (AbstractC0654dI.a == -1) {
            if (Build.VERSION.SDK_INT >= 23 || Is.a.getPackageManager().checkPermission("android.permission.CAMERA", Is.a.getPackageName()) == 0) {
                AbstractC0654dI.a = C0542bI.j();
            } else {
                AbstractC0654dI.a = 0;
                Ts.f("media", "Missing android.permission.CAMERA permission, no system camera available.", new Object[0]);
            }
        }
        return AbstractC0654dI.a;
    }

    public static boolean isLegacyOrDeprecatedDevice(int i) {
        CameraCharacteristics cameraCharacteristicsI = C0542bI.i(i);
        return cameraCharacteristicsI != null && ((Integer) cameraCharacteristicsI.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)).intValue() == 2;
    }
}
