package com.amazon.digitalmusicplayback;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class OutputDeviceAttributes {
    final OutputDevice currentDevice;
    final ArrayList<OutputDevice> devices;
    final boolean exclusiveMode;

    public OutputDeviceAttributes(ArrayList<OutputDevice> arrayList, OutputDevice outputDevice, boolean z) {
        this.devices = arrayList;
        this.currentDevice = outputDevice;
        this.exclusiveMode = z;
    }

    public ArrayList<OutputDevice> getDevices() {
        return this.devices;
    }

    public OutputDevice getCurrentDevice() {
        return this.currentDevice;
    }

    public boolean getExclusiveMode() {
        return this.exclusiveMode;
    }

    public String toString() {
        return "OutputDeviceAttributes{devices=" + this.devices + ",currentDevice=" + this.currentDevice + ",exclusiveMode=" + this.exclusiveMode + "}";
    }
}
