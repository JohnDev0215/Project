package android.hardware.camera2.legacy;

import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.legacy.ParameterUtils;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.utils.ListUtils;
import android.hardware.camera2.utils.ParamsUtils;
import android.location.Location;
import android.util.Log;
import android.util.Range;
import android.util.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LegacyRequestMapper {
    private static final boolean DEBUG = false;
    private static final byte DEFAULT_JPEG_QUALITY = 85;
    private static final String TAG = "LegacyRequestMapper";

    private static String convertAeAntiBandingModeToLegacy(int i) {
        if (i == 0) {
            return "off";
        }
        if (i == 1) {
            return Camera.Parameters.ANTIBANDING_50HZ;
        }
        if (i == 2) {
            return Camera.Parameters.ANTIBANDING_60HZ;
        }
        if (i != 3) {
            return null;
        }
        return "auto";
    }

    public static void convertRequestMetadata(LegacyRequest legacyRequest) {
        String strConvertAeAntiBandingModeToLegacy;
        String str;
        String str2;
        CameraCharacteristics cameraCharacteristics = legacyRequest.characteristics;
        CaptureRequest captureRequest = legacyRequest.captureRequest;
        Size size = legacyRequest.previewSize;
        Camera.Parameters parameters = legacyRequest.parameters;
        Rect rect = (Rect) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        ParameterUtils.ZoomData zoomDataConvertToLegacyZoom = ParameterUtils.convertToLegacyZoom(rect, (Rect) captureRequest.get(CaptureRequest.SCALER_CROP_REGION), (Float) captureRequest.get(CaptureRequest.CONTROL_ZOOM_RATIO), size, parameters);
        if (parameters.isZoomSupported()) {
            parameters.setZoom(zoomDataConvertToLegacyZoom.zoomIndex);
        }
        int iIntValue = ((Integer) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE, 1)).intValue();
        String str3 = TAG;
        if (iIntValue != 1 && iIntValue != 2) {
            Log.w(TAG, "convertRequestToMetadata - Ignoring unsupported colorCorrection.aberrationMode = " + iIntValue);
        }
        Integer num = (Integer) captureRequest.get(CaptureRequest.CONTROL_AE_ANTIBANDING_MODE);
        String str4 = "auto";
        if (num != null) {
            strConvertAeAntiBandingModeToLegacy = convertAeAntiBandingModeToLegacy(num.intValue());
        } else {
            strConvertAeAntiBandingModeToLegacy = (String) ListUtils.listSelectFirstFrom(parameters.getSupportedAntibanding(), new String[]{"auto", "off", Camera.Parameters.ANTIBANDING_50HZ, Camera.Parameters.ANTIBANDING_60HZ});
        }
        if (strConvertAeAntiBandingModeToLegacy != null) {
            parameters.setAntibanding(strConvertAeAntiBandingModeToLegacy);
        }
        MeteringRectangle[] meteringRectangleArr = (MeteringRectangle[]) captureRequest.get(CaptureRequest.CONTROL_AE_REGIONS);
        if (captureRequest.get(CaptureRequest.CONTROL_AWB_REGIONS) != null) {
            Log.w(TAG, "convertRequestMetadata - control.awbRegions setting is not supported, ignoring value");
        }
        int maxNumMeteringAreas = parameters.getMaxNumMeteringAreas();
        List<Camera.Area> listConvertMeteringRegionsToLegacy = convertMeteringRegionsToLegacy(rect, zoomDataConvertToLegacyZoom, meteringRectangleArr, maxNumMeteringAreas, "AE");
        if (maxNumMeteringAreas > 0) {
            parameters.setMeteringAreas(listConvertMeteringRegionsToLegacy);
        }
        MeteringRectangle[] meteringRectangleArr2 = (MeteringRectangle[]) captureRequest.get(CaptureRequest.CONTROL_AF_REGIONS);
        int maxNumFocusAreas = parameters.getMaxNumFocusAreas();
        List<Camera.Area> listConvertMeteringRegionsToLegacy2 = convertMeteringRegionsToLegacy(rect, zoomDataConvertToLegacyZoom, meteringRectangleArr2, maxNumFocusAreas, "AF");
        if (maxNumFocusAreas > 0) {
            parameters.setFocusAreas(listConvertMeteringRegionsToLegacy2);
        }
        Range range = (Range) captureRequest.get(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE);
        if (range != null) {
            int[] iArrConvertAeFpsRangeToLegacy = convertAeFpsRangeToLegacy(range);
            int[] iArr = null;
            Iterator<int[]> it = parameters.getSupportedPreviewFpsRange().iterator();
            while (true) {
                if (!it.hasNext()) {
                    str2 = str3;
                    break;
                }
                int[] next = it.next();
                int iFloor = ((int) Math.floor(((double) next[0]) / 1000.0d)) * 1000;
                str2 = str3;
                int iCeil = ((int) Math.ceil(((double) next[1]) / 1000.0d)) * 1000;
                if (iArrConvertAeFpsRangeToLegacy[0] == iFloor && iArrConvertAeFpsRangeToLegacy[1] == iCeil) {
                    iArr = next;
                    break;
                }
                str3 = str2;
            }
            if (iArr != null) {
                parameters.setPreviewFpsRange(iArr[0], iArr[1]);
                str = str2;
            } else {
                str = str2;
                Log.w(str, "Unsupported FPS range set [" + iArrConvertAeFpsRangeToLegacy[0] + "," + iArrConvertAeFpsRangeToLegacy[1] + "]");
            }
        } else {
            str = TAG;
        }
        Range range2 = (Range) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
        int iIntValue2 = ((Integer) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, 0)).intValue();
        if (!range2.contains(Integer.valueOf(iIntValue2))) {
            Log.w(str, "convertRequestMetadata - control.aeExposureCompensation is out of range, ignoring value");
            iIntValue2 = 0;
        }
        parameters.setExposureCompensation(iIntValue2);
        Boolean bool = (Boolean) getIfSupported(captureRequest, CaptureRequest.CONTROL_AE_LOCK, false, parameters.isAutoExposureLockSupported(), false);
        if (bool != null) {
            parameters.setAutoExposureLock(bool.booleanValue());
        }
        mapAeAndFlashMode(captureRequest, parameters);
        String strConvertAfModeToLegacy = LegacyMetadataMapper.convertAfModeToLegacy(((Integer) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_AF_MODE, 0)).intValue(), parameters.getSupportedFocusModes());
        if (strConvertAfModeToLegacy != null) {
            parameters.setFocusMode(strConvertAfModeToLegacy);
        }
        Integer num2 = (Integer) getIfSupported(captureRequest, CaptureRequest.CONTROL_AWB_MODE, 1, parameters.getSupportedWhiteBalance() != null, 1);
        if (num2 != null) {
            parameters.setWhiteBalance(convertAwbModeToLegacy(num2.intValue()));
        }
        Boolean bool2 = (Boolean) getIfSupported(captureRequest, CaptureRequest.CONTROL_AWB_LOCK, false, parameters.isAutoWhiteBalanceLockSupported(), false);
        if (bool2 != null) {
            parameters.setAutoWhiteBalanceLock(bool2.booleanValue());
        }
        int iFilterSupportedCaptureIntent = filterSupportedCaptureIntent(((Integer) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_CAPTURE_INTENT, 1)).intValue());
        parameters.setRecordingHint(iFilterSupportedCaptureIntent == 3 || iFilterSupportedCaptureIntent == 4);
        Integer num3 = (Integer) getIfSupported(captureRequest, CaptureRequest.CONTROL_VIDEO_STABILIZATION_MODE, 0, parameters.isVideoStabilizationSupported(), 0);
        if (num3 != null) {
            parameters.setVideoStabilization(num3.intValue() == 1);
        }
        boolean zListContains = ListUtils.listContains(parameters.getSupportedFocusModes(), Camera.Parameters.FOCUS_MODE_INFINITY);
        Float f = (Float) getIfSupported(captureRequest, CaptureRequest.LENS_FOCUS_DISTANCE, Float.valueOf(0.0f), zListContains, Float.valueOf(0.0f));
        if (f == null || f.floatValue() != 0.0f) {
            Log.w(str, "convertRequestToMetadata - Ignoring android.lens.focusDistance " + zListContains + ", only 0.0f is supported");
        }
        if (parameters.getSupportedSceneModes() != null) {
            int iIntValue3 = ((Integer) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_MODE, 1)).intValue();
            if (iIntValue3 != 1) {
                if (iIntValue3 == 2) {
                    int iIntValue4 = ((Integer) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_SCENE_MODE, 0)).intValue();
                    String strConvertSceneModeToLegacy = LegacyMetadataMapper.convertSceneModeToLegacy(iIntValue4);
                    if (strConvertSceneModeToLegacy != null) {
                        str4 = strConvertSceneModeToLegacy;
                    } else {
                        Log.w(str, "Skipping unknown requested scene mode: " + iIntValue4);
                    }
                } else {
                    Log.w(str, "Control mode " + iIntValue3 + " is unsupported, defaulting to AUTO");
                }
            }
            parameters.setSceneMode(str4);
        }
        if (parameters.getSupportedColorEffects() != null) {
            int iIntValue5 = ((Integer) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_EFFECT_MODE, 0)).intValue();
            String strConvertEffectModeToLegacy = LegacyMetadataMapper.convertEffectModeToLegacy(iIntValue5);
            if (strConvertEffectModeToLegacy != null) {
                parameters.setColorEffect(strConvertEffectModeToLegacy);
            } else {
                parameters.setColorEffect("none");
                Log.w(str, "Skipping unknown requested effect mode: " + iIntValue5);
            }
        }
        int iIntValue6 = ((Integer) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.SENSOR_TEST_PATTERN_MODE, 0)).intValue();
        if (iIntValue6 != 0) {
            Log.w(str, "convertRequestToMetadata - ignoring sensor.testPatternMode " + iIntValue6 + "; only OFF is supported");
        }
        Location location = (Location) captureRequest.get(CaptureRequest.JPEG_GPS_LOCATION);
        if (location != null) {
            if (checkForCompleteGpsData(location)) {
                parameters.setGpsAltitude(location.getAltitude());
                parameters.setGpsLatitude(location.getLatitude());
                parameters.setGpsLongitude(location.getLongitude());
                parameters.setGpsProcessingMethod(location.getProvider().toUpperCase());
                parameters.setGpsTimestamp(location.getTime());
            } else {
                Log.w(str, "Incomplete GPS parameters provided in location " + location);
            }
        } else {
            parameters.removeGpsData();
        }
        Integer num4 = (Integer) captureRequest.get(CaptureRequest.JPEG_ORIENTATION);
        parameters.setRotation(((Integer) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.JPEG_ORIENTATION, Integer.valueOf(num4 == null ? 0 : num4.intValue()))).intValue());
        parameters.setJpegQuality(((Byte) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.JPEG_QUALITY, Byte.valueOf(DEFAULT_JPEG_QUALITY))).byteValue() & 255);
        parameters.setJpegThumbnailQuality(((Byte) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.JPEG_THUMBNAIL_QUALITY, Byte.valueOf(DEFAULT_JPEG_QUALITY))).byteValue() & 255);
        List<Camera.Size> supportedJpegThumbnailSizes = parameters.getSupportedJpegThumbnailSizes();
        if (supportedJpegThumbnailSizes != null && supportedJpegThumbnailSizes.size() > 0) {
            Size size2 = (Size) captureRequest.get(CaptureRequest.JPEG_THUMBNAIL_SIZE);
            boolean z = (size2 == null || ParameterUtils.containsSize(supportedJpegThumbnailSizes, size2.getWidth(), size2.getHeight())) ? false : true;
            if (z) {
                Log.w(str, "Invalid JPEG thumbnail size set " + size2 + ", skipping thumbnail...");
            }
            if (size2 == null || z) {
                parameters.setJpegThumbnailSize(0, 0);
            } else {
                parameters.setJpegThumbnailSize(size2.getWidth(), size2.getHeight());
            }
        }
        int iIntValue7 = ((Integer) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.NOISE_REDUCTION_MODE, 1)).intValue();
        if (iIntValue7 == 1 || iIntValue7 == 2) {
            return;
        }
        Log.w(str, "convertRequestToMetadata - Ignoring unsupported noiseReduction.mode = " + iIntValue7);
    }

    private static boolean checkForCompleteGpsData(Location location) {
        return (location == null || location.getProvider() == null || location.getTime() == 0) ? false : true;
    }

    static int filterSupportedCaptureIntent(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                return i;
            case 5:
            case 6:
                Log.w(TAG, "Unsupported control.captureIntent value 1; default to PREVIEW");
                break;
        }
        Log.w(TAG, "Unknown control.captureIntent value 1; default to PREVIEW");
        return 1;
    }

    private static List<Camera.Area> convertMeteringRegionsToLegacy(Rect rect, ParameterUtils.ZoomData zoomData, MeteringRectangle[] meteringRectangleArr, int i, String str) {
        if (meteringRectangleArr == null || i <= 0) {
            if (i > 0) {
                return Arrays.asList(ParameterUtils.CAMERA_AREA_DEFAULT);
            }
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (MeteringRectangle meteringRectangle : meteringRectangleArr) {
            if (meteringRectangle.getMeteringWeight() != 0) {
                arrayList.add(meteringRectangle);
            }
        }
        if (arrayList.size() == 0) {
            Log.w(TAG, "Only received metering rectangles with weight 0.");
            return Arrays.asList(ParameterUtils.CAMERA_AREA_DEFAULT);
        }
        int iMin = Math.min(i, arrayList.size());
        ArrayList arrayList2 = new ArrayList(iMin);
        for (int i2 = 0; i2 < iMin; i2++) {
            arrayList2.add(ParameterUtils.convertMeteringRectangleToLegacy(rect, (MeteringRectangle) arrayList.get(i2), zoomData).meteringArea);
        }
        if (i < arrayList.size()) {
            Log.w(TAG, "convertMeteringRegionsToLegacy - Too many requested " + str + " regions, ignoring all beyond the first " + i);
        }
        return arrayList2;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void mapAeAndFlashMode(android.hardware.camera2.CaptureRequest r10, android.hardware.Camera.Parameters r11) {
        /*
            android.hardware.camera2.CaptureRequest$Key<java.lang.Integer> r0 = android.hardware.camera2.CaptureRequest.FLASH_MODE
            r1 = 0
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object r0 = android.hardware.camera2.utils.ParamsUtils.getOrDefault(r10, r0, r1)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            android.hardware.camera2.CaptureRequest$Key<java.lang.Integer> r1 = android.hardware.camera2.CaptureRequest.CONTROL_AE_MODE
            r2 = 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            java.lang.Object r10 = android.hardware.camera2.utils.ParamsUtils.getOrDefault(r10, r1, r3)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            java.util.List r1 = r11.getSupportedFlashModes()
            java.lang.String r3 = "off"
            boolean r4 = android.hardware.camera2.utils.ListUtils.listContains(r1, r3)
            if (r4 == 0) goto L2f
            goto L30
        L2f:
            r3 = 0
        L30:
            java.lang.String r4 = "red-eye"
            java.lang.String r5 = "auto"
            java.lang.String r6 = "torch"
            r7 = 2
            java.lang.String r8 = "on"
            java.lang.String r9 = "LegacyRequestMapper"
            if (r10 != r2) goto L5c
            if (r0 != r7) goto L4d
            boolean r10 = android.hardware.camera2.utils.ListUtils.listContains(r1, r6)
            if (r10 == 0) goto L47
            r3 = r6
            goto L8d
        L47:
            java.lang.String r10 = "mapAeAndFlashMode - Ignore flash.mode == TORCH;camera does not support it"
            android.util.Log.w(r9, r10)
            goto L8d
        L4d:
            if (r0 != r2) goto L8d
            boolean r10 = android.hardware.camera2.utils.ListUtils.listContains(r1, r8)
            if (r10 == 0) goto L56
            goto L65
        L56:
            java.lang.String r10 = "mapAeAndFlashMode - Ignore flash.mode == SINGLE;camera does not support it"
            android.util.Log.w(r9, r10)
            goto L8d
        L5c:
            r0 = 3
            if (r10 != r0) goto L6d
            boolean r10 = android.hardware.camera2.utils.ListUtils.listContains(r1, r8)
            if (r10 == 0) goto L67
        L65:
            r3 = r8
            goto L8d
        L67:
            java.lang.String r10 = "mapAeAndFlashMode - Ignore control.aeMode == ON_ALWAYS_FLASH;camera does not support it"
            android.util.Log.w(r9, r10)
            goto L8d
        L6d:
            if (r10 != r7) goto L7d
            boolean r10 = android.hardware.camera2.utils.ListUtils.listContains(r1, r5)
            if (r10 == 0) goto L77
            r3 = r5
            goto L8d
        L77:
            java.lang.String r10 = "mapAeAndFlashMode - Ignore control.aeMode == ON_AUTO_FLASH;camera does not support it"
            android.util.Log.w(r9, r10)
            goto L8d
        L7d:
            r0 = 4
            if (r10 != r0) goto L8d
            boolean r10 = android.hardware.camera2.utils.ListUtils.listContains(r1, r4)
            if (r10 == 0) goto L88
            r3 = r4
            goto L8d
        L88:
            java.lang.String r10 = "mapAeAndFlashMode - Ignore control.aeMode == ON_AUTO_FLASH_REDEYE;camera does not support it"
            android.util.Log.w(r9, r10)
        L8d:
            if (r3 == 0) goto L92
            r11.setFlashMode(r3)
        L92:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.camera2.legacy.LegacyRequestMapper.mapAeAndFlashMode(android.hardware.camera2.CaptureRequest, android.hardware.Camera$Parameters):void");
    }

    private static int[] convertAeFpsRangeToLegacy(Range<Integer> range) {
        return new int[]{((Integer) range.getLower()).intValue() * 1000, ((Integer) range.getUpper()).intValue() * 1000};
    }

    private static String convertAwbModeToLegacy(int i) {
        switch (i) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            default:
                Log.w(TAG, "convertAwbModeToLegacy - unrecognized control.awbMode" + i);
                break;
        }
        return "auto";
    }

    private static <T> T getIfSupported(CaptureRequest captureRequest, CaptureRequest.Key<T> key, T t, boolean z, T t2) {
        T t3 = (T) ParamsUtils.getOrDefault(captureRequest, key, t);
        if (z) {
            return t3;
        }
        if (!Objects.equals(t3, t2)) {
            Log.w(TAG, key.getName() + " is not supported; ignoring requested value " + t3);
        }
        return null;
    }
}
