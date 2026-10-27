package com.byd.dilink.hardware.camera;

import androidx.annotation.NonNull;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkBmmCameraInfo {
    ArrayList<String> getAvailableCameraTypeList();

    int getCameraId(String str);

    int getCameraNumbers();

    String getCameraTag(int i);

    float getDefaultFps(int i);

    int getDefaultPreviewHeight(int i);

    int getDefaultPreviewWidth(int i);

    String getSensorModule(int i);

    String getSupplier(int i);

    ArrayList<String> getValidCameraTag();

    boolean isValidCamera(int i);

    boolean isValidCamera(@NonNull String str);
}
