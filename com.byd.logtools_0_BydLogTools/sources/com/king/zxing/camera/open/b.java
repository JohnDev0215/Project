package com.king.zxing.camera.open;

import android.hardware.Camera;
import android.util.Log;

/* JADX INFO: compiled from: OpenCameraInterface.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f4589a = "com.king.zxing.camera.open.b";

    public static a a(int i) {
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras == 0) {
            Log.w(f4589a, "No cameras!");
            return null;
        }
        if (i >= numberOfCameras) {
            Log.w(f4589a, "Requested camera does not exist: " + i);
            return null;
        }
        if (i <= -1) {
            i = 0;
            while (i < numberOfCameras) {
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i, cameraInfo);
                if (CameraFacing.values()[cameraInfo.facing] == CameraFacing.BACK) {
                    break;
                }
                i++;
            }
            if (i == numberOfCameras) {
                Log.i(f4589a, "No camera facing " + CameraFacing.BACK + "; returning camera #0");
                i = 0;
            }
        }
        Log.i(f4589a, "Opening camera #" + i);
        Camera.CameraInfo cameraInfo2 = new Camera.CameraInfo();
        Camera.getCameraInfo(i, cameraInfo2);
        Camera cameraOpen = Camera.open(i);
        if (cameraOpen == null) {
            return null;
        }
        return new a(i, cameraOpen, CameraFacing.values()[cameraInfo2.facing], cameraInfo2.orientation);
    }
}
