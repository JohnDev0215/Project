package com.ts.lib.car.hardware.cloudmessage;

import com.ts.lib.car.CarNotConnectedException;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorCloudMessageManager {
    public static final int ID_BUS_CLOUD_MESSAGE = 561049648;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.assist";
    public static final String TAG = "CAR.VENDOR.CLOUD";

    public interface OnCarCloudMessageEventListener {
        void onCloudMessageErrorEvent(int i, int i2);

        void onCloudMessageEventChange(CloudMessageEventChangeValue cloudMessageEventChangeValue);
    }

    void addCarCloudMessageEventListener(OnCarCloudMessageEventListener onCarCloudMessageEventListener) throws CarNotConnectedException;

    byte[] getCloudMessage() throws CarNotConnectedException;

    void removeCarCloudMessageEventListener(OnCarCloudMessageEventListener onCarCloudMessageEventListener) throws CarNotConnectedException;

    void setCanId(Set<String> set);

    void setCloudMessage(byte[] bArr) throws CarNotConnectedException;
}
