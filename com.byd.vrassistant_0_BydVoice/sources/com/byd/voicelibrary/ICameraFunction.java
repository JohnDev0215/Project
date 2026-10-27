package com.byd.voicelibrary;

/* JADX INFO: loaded from: classes.dex */
public interface ICameraFunction {
    public static final int FACING_INSIDE = 0;
    public static final int FACING_OUTSIDE = 1;
    public static final int FUC_CAMERA = 1;

    @Deprecated
    public static final int FUC_DVR = 0;

    void closeCameraApp();

    void openCamera(int i);

    void startRecord(int i, int i2, boolean z);

    void stopRecord(boolean z);

    void switchCamera(int i);

    void takePicture(int i, int i2, int i3, boolean z);
}
