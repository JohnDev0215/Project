package com.ts.lib.car.hardware.ambient;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorAmbientManager {
    public static final int ID_FREQUENCY_POINT = 557945856;
    public static final int ID_KTV_RESOURCE_S = 557880321;
    public static final int ID_MEDIA_WORKING_MODE_S = 557880322;
    public static final int ID_MEDIA_WORKING_STATUS_S = 557880323;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.ambient";
    public static final String TAG = "CAR.VENDOR.AMBIENT";

    public interface OnCarAmbientEventListener {
        void onAmbientErrorEvent(int i, int i2);

        void onAmbientEventChange(AmbientEventChangeValue ambientEventChangeValue);
    }

    void addCarAmbientEventListener(OnCarAmbientEventListener onCarAmbientEventListener) throws CarNotConnectedException;

    void removeCarAmbientEventListener(OnCarAmbientEventListener onCarAmbientEventListener) throws CarNotConnectedException;

    void setFrequencyPoint(int[] iArr) throws CarNotConnectedException;

    void setKtvResourceS(int i) throws CarNotConnectedException;

    void setMediaWorkingModeS(int i) throws CarNotConnectedException;

    void setMediaWorkingStatusS(int i) throws CarNotConnectedException;
}
