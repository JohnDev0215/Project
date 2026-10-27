package com.king.zxing.camera;

import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: CameraConfigurationUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Pattern f4571a = Pattern.compile(";");

    private static List<Camera.Area> a(int i) {
        int i2 = -i;
        return Collections.singletonList(new Camera.Area(new Rect(i2, i2, i, i), 1));
    }

    public static Point b(Camera.Parameters parameters, Point point) {
        Iterator<Camera.Size> it;
        Camera.Size size;
        String str;
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        String str2 = "Parameters contained no preview size!";
        if (supportedPreviewSizes == null) {
            Log.w("CameraConfiguration", "Device returned no supported preview sizes; using default");
            Camera.Size previewSize = parameters.getPreviewSize();
            if (previewSize != null) {
                return new Point(previewSize.width, previewSize.height);
            }
            throw new IllegalStateException("Parameters contained no preview size!");
        }
        if (Log.isLoggable("CameraConfiguration", 4)) {
            StringBuilder sb = new StringBuilder();
            for (Camera.Size size2 : supportedPreviewSizes) {
                sb.append(size2.width);
                sb.append('x');
                sb.append(size2.height);
                sb.append(' ');
            }
            Log.i("CameraConfiguration", "Supported preview sizes: " + ((Object) sb));
        }
        int i = point.x;
        int i2 = point.y;
        double d2 = i < i2 ? ((double) i) / ((double) i2) : ((double) i2) / ((double) i);
        Log.i("CameraConfiguration", "screenAspectRatio: " + d2);
        Camera.Size size3 = null;
        Iterator<Camera.Size> it2 = supportedPreviewSizes.iterator();
        char c2 = 0;
        int i3 = 0;
        while (it2.hasNext()) {
            Camera.Size next = it2.next();
            int i4 = next.width;
            int i5 = next.height;
            int i6 = i4 * i5;
            if (i6 < 153600) {
                it = it2;
                str = str2;
                size = size3;
            } else {
                char c3 = i4 < i5 ? (char) 1 : c2;
                int i7 = c3 != 0 ? i4 : i5;
                int i8 = c3 != 0 ? i5 : i4;
                Object[] objArr = new Object[2];
                objArr[c2] = Integer.valueOf(i7);
                objArr[1] = Integer.valueOf(i8);
                Log.i("CameraConfiguration", String.format("maybeFlipped:%d * %d", objArr));
                it = it2;
                size = size3;
                str = str2;
                double d3 = ((double) i7) / ((double) i8);
                Log.i("CameraConfiguration", "aspectRatio: " + d3);
                double dAbs = Math.abs(d3 - d2);
                Log.i("CameraConfiguration", "distortion: " + dAbs);
                if (dAbs <= 0.05d) {
                    if (i7 == point.x && i8 == point.y) {
                        Point point2 = new Point(i4, i5);
                        Log.i("CameraConfiguration", "Found preview size exactly matching screen size: " + point2);
                        return point2;
                    }
                    if (i6 > i3) {
                        size3 = next;
                        i3 = i6;
                    } else {
                        size3 = size;
                    }
                    it2 = it;
                }
                str2 = str;
                c2 = 0;
            }
            it2 = it;
            size3 = size;
            str2 = str;
            c2 = 0;
        }
        String str3 = str2;
        Camera.Size size4 = size3;
        if (size4 != null) {
            Point point3 = new Point(size4.width, size4.height);
            Log.i("CameraConfiguration", "Using largest suitable preview size: " + point3);
            return point3;
        }
        Camera.Size previewSize2 = parameters.getPreviewSize();
        if (previewSize2 == null) {
            throw new IllegalStateException(str3);
        }
        Point point4 = new Point(previewSize2.width, previewSize2.height);
        Log.i("CameraConfiguration", "No suitable preview sizes, using default: " + point4);
        return point4;
    }

    private static String c(String str, Collection<String> collection, String... strArr) {
        Log.i("CameraConfiguration", "Requesting " + str + " value from among: " + Arrays.toString(strArr));
        Log.i("CameraConfiguration", "Supported " + str + " values: " + collection);
        if (collection != null) {
            for (String str2 : strArr) {
                if (collection.contains(str2)) {
                    Log.i("CameraConfiguration", "Can set " + str + " to: " + str2);
                    return str2;
                }
            }
        }
        Log.i("CameraConfiguration", "No supported values match");
        return null;
    }

    public static void d(Camera.Parameters parameters) {
        if ("barcode".equals(parameters.getSceneMode())) {
            Log.i("CameraConfiguration", "Barcode scene mode already set");
            return;
        }
        String strC = c("scene mode", parameters.getSupportedSceneModes(), "barcode");
        if (strC != null) {
            parameters.setSceneMode(strC);
        }
    }

    public static void e(Camera.Parameters parameters, boolean z) {
        int minExposureCompensation = parameters.getMinExposureCompensation();
        int maxExposureCompensation = parameters.getMaxExposureCompensation();
        float exposureCompensationStep = parameters.getExposureCompensationStep();
        if (minExposureCompensation != 0 || maxExposureCompensation != 0) {
            if (exposureCompensationStep > 0.0f) {
                int iRound = Math.round((z ? 0.0f : 1.5f) / exposureCompensationStep);
                float f2 = exposureCompensationStep * iRound;
                int iMax = Math.max(Math.min(iRound, maxExposureCompensation), minExposureCompensation);
                if (parameters.getExposureCompensation() == iMax) {
                    Log.i("CameraConfiguration", "Exposure compensation already set to " + iMax + " / " + f2);
                    return;
                }
                Log.i("CameraConfiguration", "Setting exposure compensation to " + iMax + " / " + f2);
                parameters.setExposureCompensation(iMax);
                return;
            }
        }
        Log.i("CameraConfiguration", "Camera does not support exposure compensation");
    }

    public static void f(Camera.Parameters parameters, boolean z, boolean z2, boolean z3) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        String strC = z ? (z3 || z2) ? c("focus mode", supportedFocusModes, "auto") : c("focus mode", supportedFocusModes, "continuous-picture", "continuous-video", "auto") : null;
        if (!z3 && strC == null) {
            strC = c("focus mode", supportedFocusModes, "macro", "edof");
        }
        if (strC != null) {
            if (!strC.equals(parameters.getFocusMode())) {
                parameters.setFocusMode(strC);
                return;
            }
            Log.i("CameraConfiguration", "Focus mode already set to " + strC);
        }
    }

    public static void g(Camera.Parameters parameters) {
        if (parameters.getMaxNumFocusAreas() <= 0) {
            Log.i("CameraConfiguration", "Device does not support focus areas");
            return;
        }
        Log.i("CameraConfiguration", "Old focus areas: " + l(parameters.getFocusAreas()));
        List<Camera.Area> listA = a(400);
        Log.i("CameraConfiguration", "Setting focus area to : " + l(listA));
        parameters.setFocusAreas(listA);
    }

    public static void h(Camera.Parameters parameters) {
        if ("negative".equals(parameters.getColorEffect())) {
            Log.i("CameraConfiguration", "Negative effect already set");
            return;
        }
        String strC = c("color effect", parameters.getSupportedColorEffects(), "negative");
        if (strC != null) {
            parameters.setColorEffect(strC);
        }
    }

    public static void i(Camera.Parameters parameters) {
        if (parameters.getMaxNumMeteringAreas() <= 0) {
            Log.i("CameraConfiguration", "Device does not support metering areas");
            return;
        }
        Log.i("CameraConfiguration", "Old metering areas: " + parameters.getMeteringAreas());
        List<Camera.Area> listA = a(400);
        Log.i("CameraConfiguration", "Setting metering area to : " + l(listA));
        parameters.setMeteringAreas(listA);
    }

    public static void j(Camera.Parameters parameters, boolean z) {
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        String strC = z ? c("flash mode", supportedFlashModes, "torch", "on") : c("flash mode", supportedFlashModes, "off");
        if (strC != null) {
            if (strC.equals(parameters.getFlashMode())) {
                Log.i("CameraConfiguration", "Flash mode already set to " + strC);
                return;
            }
            Log.i("CameraConfiguration", "Setting flash mode to " + strC);
            parameters.setFlashMode(strC);
        }
    }

    public static void k(Camera.Parameters parameters) {
        if (!parameters.isVideoStabilizationSupported()) {
            Log.i("CameraConfiguration", "This device does not support video stabilization");
        } else if (parameters.getVideoStabilization()) {
            Log.i("CameraConfiguration", "Video stabilization already enabled");
        } else {
            Log.i("CameraConfiguration", "Enabling video stabilization...");
            parameters.setVideoStabilization(true);
        }
    }

    private static String l(Iterable<Camera.Area> iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Camera.Area area : iterable) {
            sb.append(area.rect);
            sb.append(':');
            sb.append(area.weight);
            sb.append(' ');
        }
        return sb.toString();
    }
}
