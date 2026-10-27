package com.byd.vrassistant.utils;

import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import com.byd.vrassistant.BydVoiceApplication;

/* JADX INFO: loaded from: classes2.dex */
public class CameraUtils {
    public static boolean checkHasCamera() {
        try {
            CameraManager cameraManager = (CameraManager) BydVoiceApplication.getContext().getSystemService("camera");
            String[] cameraIdList = cameraManager.getCameraIdList();
            if (cameraIdList.length > 0) {
                for (String str : cameraIdList) {
                    Integer num = (Integer) cameraManager.getCameraCharacteristics(str).get(CameraCharacteristics.LENS_FACING);
                    if (num != null && num.intValue() == 0) {
                        RLog.i("NEU_CameraUtils", "Camera get true");
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
            RLog.i("NEU_CameraUtils", "Camera get error");
        }
        return false;
    }

    private static boolean checkCameraFacing(int i) {
        if (getSdkVersion() < 9) {
            return false;
        }
        int numberOfCameras = Camera.getNumberOfCameras();
        RLog.i("NEU_CameraUtils", "cameraCount: " + numberOfCameras);
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            RLog.i("NEU_CameraUtils", "facing: " + cameraInfo.facing);
            if (i == cameraInfo.facing) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasBackFacingCamera() {
        return checkCameraFacing(0);
    }

    public static boolean hasFrontFacingCamera() {
        return checkCameraFacing(1);
    }

    public static int getSdkVersion() {
        return Build.VERSION.SDK_INT;
    }
}
