package com.byd.dilink.hardware.sensor;

import android.content.Context;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkSensorPrivacyManager {
    void addSensorPrivacyListener(Context context, int i, IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener);

    List<Integer> getAvailableSensorIds(Context context);

    long getSensorPrivacyDeadTime(Context context, int i);

    long getSensorStateForPeriod(Context context, int i);

    boolean isSensorPrivacyEnabled(Context context, int i);

    void removeSensorPrivacyListener(Context context, int i, IDiLinkOnSensorPrivacyListener iDiLinkOnSensorPrivacyListener);

    void setSensorPrivacy(Context context, int i, int i2, boolean z, long j);
}
