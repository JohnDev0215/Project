package com.android.bluetooth.hfp;

/* JADX INFO: loaded from: classes.dex */
class HeadsetDeviceState extends HeadsetMessageObject {
    int mBatteryCharge;
    int mRoam;
    int mService;
    int mSignal;

    HeadsetDeviceState(int service, int roam, int signal, int batteryCharge) {
        this.mService = service;
        this.mRoam = roam;
        this.mSignal = signal;
        this.mBatteryCharge = batteryCharge;
    }

    @Override // com.android.bluetooth.hfp.HeadsetMessageObject
    public void buildString(StringBuilder builder) {
        if (builder == null) {
            return;
        }
        builder.append(getClass().getSimpleName());
        builder.append("[hasCellularService=");
        builder.append(this.mService);
        builder.append(", isRoaming=");
        builder.append(this.mRoam);
        builder.append(", signalStrength");
        builder.append(this.mSignal);
        builder.append(", batteryCharge=");
        builder.append(this.mBatteryCharge);
        builder.append("]");
    }
}
