package android.hardware.camera2.legacy;

import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.impl.CameraMetadataNative;
import android.hardware.camera2.legacy.ParameterUtils;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.utils.ParamsUtils;
import android.location.Location;
import android.util.Log;
import android.util.Size;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LegacyResultMapper {
    private static final boolean DEBUG = false;
    private static final String TAG = "LegacyResultMapper";
    private LegacyRequest mCachedRequest = null;
    private CameraMetadataNative mCachedResult = null;

    public CameraMetadataNative cachedConvertResultMetadata(LegacyRequest legacyRequest, long j) {
        CameraMetadataNative cameraMetadataNative;
        if (this.mCachedRequest != null && legacyRequest.parameters.same(this.mCachedRequest.parameters) && legacyRequest.captureRequest.equals((Object) this.mCachedRequest.captureRequest)) {
            cameraMetadataNative = new CameraMetadataNative(this.mCachedResult);
        } else {
            CameraMetadataNative cameraMetadataNativeConvertResultMetadata = convertResultMetadata(legacyRequest);
            this.mCachedRequest = legacyRequest;
            this.mCachedResult = new CameraMetadataNative(cameraMetadataNativeConvertResultMetadata);
            cameraMetadataNative = cameraMetadataNativeConvertResultMetadata;
        }
        cameraMetadataNative.set(CaptureResult.SENSOR_TIMESTAMP, Long.valueOf(j));
        return cameraMetadataNative;
    }

    private static CameraMetadataNative convertResultMetadata(LegacyRequest legacyRequest) {
        CameraCharacteristics cameraCharacteristics = legacyRequest.characteristics;
        CaptureRequest captureRequest = legacyRequest.captureRequest;
        Size size = legacyRequest.previewSize;
        Camera.Parameters parameters = legacyRequest.parameters;
        CameraMetadataNative cameraMetadataNative = new CameraMetadataNative();
        Rect rect = (Rect) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        ParameterUtils.ZoomData zoomDataConvertToLegacyZoom = ParameterUtils.convertToLegacyZoom(rect, (Rect) captureRequest.get(CaptureRequest.SCALER_CROP_REGION), (Float) captureRequest.get(CaptureRequest.CONTROL_ZOOM_RATIO), size, parameters);
        cameraMetadataNative.set(CaptureResult.COLOR_CORRECTION_ABERRATION_MODE, (Integer) captureRequest.get(CaptureRequest.COLOR_CORRECTION_ABERRATION_MODE));
        mapAe(cameraMetadataNative, cameraCharacteristics, captureRequest, rect, zoomDataConvertToLegacyZoom, parameters);
        mapAf(cameraMetadataNative, rect, zoomDataConvertToLegacyZoom, parameters);
        mapAwb(cameraMetadataNative, parameters);
        cameraMetadataNative.set(CaptureResult.CONTROL_CAPTURE_INTENT, Integer.valueOf(LegacyRequestMapper.filterSupportedCaptureIntent(((Integer) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_CAPTURE_INTENT, 1)).intValue())));
        if (((Integer) ParamsUtils.getOrDefault(captureRequest, CaptureRequest.CONTROL_MODE, 1)).intValue() != 2) {
            cameraMetadataNative.set((CaptureResult.Key<int>) CaptureResult.CONTROL_MODE, 1);
        } else {
            cameraMetadataNative.set((CaptureResult.Key<int>) CaptureResult.CONTROL_MODE, 2);
        }
        String sceneMode = parameters.getSceneMode();
        int iConvertSceneModeFromLegacy = LegacyMetadataMapper.convertSceneModeFromLegacy(sceneMode);
        if (iConvertSceneModeFromLegacy != -1) {
            cameraMetadataNative.set(CaptureResult.CONTROL_SCENE_MODE, Integer.valueOf(iConvertSceneModeFromLegacy));
        } else {
            Log.w(TAG, "Unknown scene mode " + sceneMode + " returned by camera HAL, setting to disabled.");
            cameraMetadataNative.set((CaptureResult.Key<int>) CaptureResult.CONTROL_SCENE_MODE, 0);
        }
        String colorEffect = parameters.getColorEffect();
        int iConvertEffectModeFromLegacy = LegacyMetadataMapper.convertEffectModeFromLegacy(colorEffect);
        if (iConvertEffectModeFromLegacy != -1) {
            cameraMetadataNative.set(CaptureResult.CONTROL_EFFECT_MODE, Integer.valueOf(iConvertEffectModeFromLegacy));
        } else {
            Log.w(TAG, "Unknown effect mode " + colorEffect + " returned by camera HAL, setting to off.");
            cameraMetadataNative.set((CaptureResult.Key<int>) CaptureResult.CONTROL_EFFECT_MODE, 0);
        }
        cameraMetadataNative.set(CaptureResult.CONTROL_VIDEO_STABILIZATION_MODE, Integer.valueOf((parameters.isVideoStabilizationSupported() && parameters.getVideoStabilization()) ? 1 : 0));
        if (Camera.Parameters.FOCUS_MODE_INFINITY.equals(parameters.getFocusMode())) {
            cameraMetadataNative.set(CaptureResult.LENS_FOCUS_DISTANCE, Float.valueOf(0.0f));
        }
        cameraMetadataNative.set(CaptureResult.LENS_FOCAL_LENGTH, Float.valueOf(parameters.getFocalLength()));
        cameraMetadataNative.set(CaptureResult.REQUEST_PIPELINE_DEPTH, (Byte) cameraCharacteristics.get(CameraCharacteristics.REQUEST_PIPELINE_MAX_DEPTH));
        mapScaler(cameraMetadataNative, zoomDataConvertToLegacyZoom, parameters);
        cameraMetadataNative.set((CaptureResult.Key<int>) CaptureResult.SENSOR_TEST_PATTERN_MODE, 0);
        cameraMetadataNative.set(CaptureResult.JPEG_GPS_LOCATION, (Location) captureRequest.get(CaptureRequest.JPEG_GPS_LOCATION));
        cameraMetadataNative.set(CaptureResult.JPEG_ORIENTATION, (Integer) captureRequest.get(CaptureRequest.JPEG_ORIENTATION));
        cameraMetadataNative.set(CaptureResult.JPEG_QUALITY, Byte.valueOf((byte) parameters.getJpegQuality()));
        cameraMetadataNative.set(CaptureResult.JPEG_THUMBNAIL_QUALITY, Byte.valueOf((byte) parameters.getJpegThumbnailQuality()));
        Camera.Size jpegThumbnailSize = parameters.getJpegThumbnailSize();
        if (jpegThumbnailSize != null) {
            cameraMetadataNative.set(CaptureResult.JPEG_THUMBNAIL_SIZE, ParameterUtils.convertSize(jpegThumbnailSize));
        } else {
            Log.w(TAG, "Null thumbnail size received from parameters.");
        }
        cameraMetadataNative.set(CaptureResult.NOISE_REDUCTION_MODE, (Integer) captureRequest.get(CaptureRequest.NOISE_REDUCTION_MODE));
        return cameraMetadataNative;
    }

    private static void mapAe(CameraMetadataNative cameraMetadataNative, CameraCharacteristics cameraCharacteristics, CaptureRequest captureRequest, Rect rect, ParameterUtils.ZoomData zoomData, Camera.Parameters parameters) {
        cameraMetadataNative.set(CaptureResult.CONTROL_AE_ANTIBANDING_MODE, Integer.valueOf(LegacyMetadataMapper.convertAntiBandingModeOrDefault(parameters.getAntibanding())));
        cameraMetadataNative.set(CaptureResult.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(parameters.getExposureCompensation()));
        boolean autoExposureLock = parameters.isAutoExposureLockSupported() ? parameters.getAutoExposureLock() : false;
        cameraMetadataNative.set(CaptureResult.CONTROL_AE_LOCK, Boolean.valueOf(autoExposureLock));
        Boolean bool = (Boolean) captureRequest.get(CaptureRequest.CONTROL_AE_LOCK);
        if (bool != null && bool.booleanValue() != autoExposureLock) {
            Log.w(TAG, "mapAe - android.control.aeLock was requested to " + bool + " but resulted in " + autoExposureLock);
        }
        mapAeAndFlashMode(cameraMetadataNative, cameraCharacteristics, parameters);
        if (parameters.getMaxNumMeteringAreas() > 0) {
            cameraMetadataNative.set(CaptureResult.CONTROL_AE_REGIONS, getMeteringRectangles(rect, zoomData, parameters.getMeteringAreas(), "AE"));
        }
    }

    private static void mapAf(CameraMetadataNative cameraMetadataNative, Rect rect, ParameterUtils.ZoomData zoomData, Camera.Parameters parameters) {
        cameraMetadataNative.set(CaptureResult.CONTROL_AF_MODE, Integer.valueOf(convertLegacyAfMode(parameters.getFocusMode())));
        if (parameters.getMaxNumFocusAreas() > 0) {
            cameraMetadataNative.set(CaptureResult.CONTROL_AF_REGIONS, getMeteringRectangles(rect, zoomData, parameters.getFocusAreas(), "AF"));
        }
    }

    private static void mapAwb(CameraMetadataNative cameraMetadataNative, Camera.Parameters parameters) {
        cameraMetadataNative.set(CaptureResult.CONTROL_AWB_LOCK, Boolean.valueOf(parameters.isAutoWhiteBalanceLockSupported() ? parameters.getAutoWhiteBalanceLock() : false));
        cameraMetadataNative.set(CaptureResult.CONTROL_AWB_MODE, Integer.valueOf(convertLegacyAwbMode(parameters.getWhiteBalance())));
    }

    private static MeteringRectangle[] getMeteringRectangles(Rect rect, ParameterUtils.ZoomData zoomData, List<Camera.Area> list, String str) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator<Camera.Area> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(ParameterUtils.convertCameraAreaToActiveArrayRectangle(rect, zoomData, it.next()).toMetering());
            }
        }
        return (MeteringRectangle[]) arrayList.toArray(new MeteringRectangle[0]);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008d A[PHI: r0 r9
  0x008d: PHI (r0v2 int) = (r0v1 int), (r0v1 int), (r0v5 int), (r0v1 int) binds: [B:7:0x001d, B:26:0x0059, B:32:0x007e, B:31:0x0063] A[DONT_GENERATE, DONT_INLINE]
  0x008d: PHI (r9v6 java.lang.Integer) = (r9v5 java.lang.Integer), (r9v5 java.lang.Integer), (r9v11 java.lang.Integer), (r9v5 java.lang.Integer) binds: [B:7:0x001d, B:26:0x0059, B:32:0x007e, B:31:0x0063] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void mapAeAndFlashMode(android.hardware.camera2.impl.CameraMetadataNative r8, android.hardware.camera2.CameraCharacteristics r9, android.hardware.Camera.Parameters r10) {
        /*
            android.hardware.camera2.CameraCharacteristics$Key<java.lang.Boolean> r0 = android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE
            java.lang.Object r9 = r9.get(r0)
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            r0 = 0
            if (r9 == 0) goto L11
            r9 = 0
            goto L15
        L11:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r0)
        L15:
            java.lang.String r1 = r10.getFlashMode()
            r2 = 4
            r3 = 2
            r4 = 1
            r5 = 3
            if (r1 == 0) goto L8d
            r6 = -1
            int r7 = r1.hashCode()
            switch(r7) {
                case 3551: goto L50;
                case 109935: goto L46;
                case 3005871: goto L3c;
                case 110547964: goto L32;
                case 1081542389: goto L28;
                default: goto L27;
            }
        L27:
            goto L59
        L28:
            java.lang.String r7 = "red-eye"
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L59
            r6 = r5
            goto L59
        L32:
            java.lang.String r7 = "torch"
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L59
            r6 = r2
            goto L59
        L3c:
            java.lang.String r7 = "auto"
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L59
            r6 = r4
            goto L59
        L46:
            java.lang.String r7 = "off"
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L59
            r6 = r0
            goto L59
        L50:
            java.lang.String r7 = "on"
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L59
            r6 = r3
        L59:
            if (r6 == 0) goto L8d
            if (r6 == r4) goto L8b
            if (r6 == r3) goto L84
            if (r6 == r5) goto L8e
            if (r6 == r2) goto L7e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "mapAeAndFlashMode - Ignoring unknown flash mode "
            r1.append(r2)
            java.lang.String r10 = r10.getFlashMode()
            r1.append(r10)
            java.lang.String r10 = r1.toString()
            java.lang.String r1 = "LegacyResultMapper"
            android.util.Log.w(r1, r10)
            goto L8d
        L7e:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r5)
            r0 = r3
            goto L8d
        L84:
            java.lang.Integer r9 = java.lang.Integer.valueOf(r5)
            r0 = r4
            r2 = r5
            goto L8e
        L8b:
            r2 = r3
            goto L8e
        L8d:
            r2 = r4
        L8e:
            android.hardware.camera2.CaptureResult$Key<java.lang.Integer> r10 = android.hardware.camera2.CaptureResult.FLASH_STATE
            r8.set(r10, r9)
            android.hardware.camera2.CaptureResult$Key<java.lang.Integer> r9 = android.hardware.camera2.CaptureResult.FLASH_MODE
            java.lang.Integer r10 = java.lang.Integer.valueOf(r0)
            r8.set(r9, r10)
            android.hardware.camera2.CaptureResult$Key<java.lang.Integer> r9 = android.hardware.camera2.CaptureResult.CONTROL_AE_MODE
            java.lang.Integer r10 = java.lang.Integer.valueOf(r2)
            r8.set(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.camera2.legacy.LegacyResultMapper.mapAeAndFlashMode(android.hardware.camera2.impl.CameraMetadataNative, android.hardware.camera2.CameraCharacteristics, android.hardware.Camera$Parameters):void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static int convertLegacyAfMode(String str) {
        if (str == null) {
            Log.w(TAG, "convertLegacyAfMode - no AF mode, default to OFF");
            return 0;
        }
        byte b = -1;
        switch (str.hashCode()) {
            case -194628547:
                if (str.equals(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                    b = 2;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    b = 0;
                }
                break;
            case 3108534:
                if (str.equals(Camera.Parameters.FOCUS_MODE_EDOF)) {
                    b = 3;
                }
                break;
            case 97445748:
                if (str.equals(Camera.Parameters.FOCUS_MODE_FIXED)) {
                    b = 5;
                }
                break;
            case 103652300:
                if (str.equals(Camera.Parameters.FOCUS_MODE_MACRO)) {
                    b = 4;
                }
                break;
            case 173173288:
                if (str.equals(Camera.Parameters.FOCUS_MODE_INFINITY)) {
                    b = 6;
                }
                break;
            case 910005312:
                if (str.equals(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                    b = 1;
                }
                break;
        }
        switch (b) {
            case 0:
                return 1;
            case 1:
                return 4;
            case 2:
                return 3;
            case 3:
                return 5;
            case 4:
                return 2;
            default:
                Log.w(TAG, "convertLegacyAfMode - unknown mode " + str + " , ignoring");
            case 5:
            case 6:
                return 0;
        }
    }

    private static int convertLegacyAwbMode(String str) {
        if (str == null) {
            return 1;
        }
        switch (str) {
            case "auto":
                break;
            case "incandescent":
                break;
            case "fluorescent":
                break;
            case "warm-fluorescent":
                break;
            case "daylight":
                break;
            case "cloudy-daylight":
                break;
            case "twilight":
                break;
            case "shade":
                break;
            default:
                Log.w(TAG, "convertAwbMode - unrecognized WB mode " + str);
                break;
        }
        return 1;
    }

    private static void mapScaler(CameraMetadataNative cameraMetadataNative, ParameterUtils.ZoomData zoomData, Camera.Parameters parameters) {
        cameraMetadataNative.set(CaptureResult.SCALER_CROP_REGION, zoomData.reportedCrop);
        cameraMetadataNative.set(CaptureResult.CONTROL_ZOOM_RATIO, Float.valueOf(zoomData.reportedZoomRatio));
    }
}
