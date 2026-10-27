package com.android.bluetooth.hfp;

/* JADX INFO: loaded from: classes.dex */
public class HeadsetAgIndicatorEnableState extends HeadsetMessageObject {
    public boolean battery;
    public boolean roam;
    public boolean service;
    public boolean signal;

    HeadsetAgIndicatorEnableState(boolean newService, boolean newRoam, boolean newSignal, boolean newBattery) {
        this.service = newService;
        this.roam = newRoam;
        this.signal = newSignal;
        this.battery = newBattery;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HeadsetAgIndicatorEnableState)) {
            return false;
        }
        HeadsetAgIndicatorEnableState other = (HeadsetAgIndicatorEnableState) obj;
        return this.service == other.service && this.roam == other.roam && this.signal == other.signal && this.battery == other.battery;
    }

    public int hashCode() {
        int result = this.service ? 0 + 1 : 0;
        if (this.roam) {
            result += 2;
        }
        if (this.signal) {
            result += 4;
        }
        return this.battery ? result + 8 : result;
    }

    @Override // com.android.bluetooth.hfp.HeadsetMessageObject
    public void buildString(StringBuilder builder) {
        if (builder == null) {
            return;
        }
        builder.append(getClass().getSimpleName());
        builder.append("[service=");
        builder.append(this.service);
        builder.append(", roam=");
        builder.append(this.roam);
        builder.append(", signal=");
        builder.append(this.signal);
        builder.append(", battery=");
        builder.append(this.battery);
        builder.append("]");
    }
}
