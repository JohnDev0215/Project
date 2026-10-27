package com.android.server;

import com.android.server.deviceidle.IDeviceIdleConstraint;

/* JADX INFO: loaded from: classes4.dex */
public interface DeviceIdleInternal {

    public interface StationaryListener {
        void onDeviceStationaryChanged(boolean z);
    }

    void addPowerSaveTempWhitelistApp(int i, String str, long j, int i2, boolean z, String str2);

    void addPowerSaveTempWhitelistAppDirect(int i, long j, boolean z, String str);

    void exitIdle(String str);

    long getNotificationWhitelistDuration();

    int[] getPowerSaveTempWhitelistAppIds();

    int[] getPowerSaveWhitelistUserAppIds();

    boolean isAppOnWhitelist(int i);

    void onConstraintStateChanged(IDeviceIdleConstraint iDeviceIdleConstraint, boolean z);

    void registerDeviceIdleConstraint(IDeviceIdleConstraint iDeviceIdleConstraint, String str, int i);

    void registerStationaryListener(StationaryListener stationaryListener);

    void setAlarmsActive(boolean z);

    void setJobsActive(boolean z);

    void unregisterDeviceIdleConstraint(IDeviceIdleConstraint iDeviceIdleConstraint);

    void unregisterStationaryListener(StationaryListener stationaryListener);
}
