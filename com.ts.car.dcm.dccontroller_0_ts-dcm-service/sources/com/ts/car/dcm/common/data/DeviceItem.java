package com.ts.car.dcm.common.data;

/* JADX INFO: loaded from: classes.dex */
public class DeviceItem {
    public String address;
    public int deviceClass;
    public int enabledFeatures;
    public int features;
    public String name;

    public int getType() {
        int i = this.deviceClass;
        if ((i & 7936) == 512) {
            return 0;
        }
        if ((i & 8188) == 1028) {
            return 1;
        }
        return 999;
    }

    public boolean isProfileSupported(int profile) {
        return profile == 0 ? (this.features & 1) != 0 : profile == 1 ? (2 & this.features) != 0 : profile == 2 ? (this.features & 4) != 0 : profile == 3 ? (this.features & 8) != 0 : profile == 4 ? (this.features & 16) != 0 : profile == 5 && (this.features & 32) != 0;
    }

    public String toString() {
        return "DeviceItem{address='" + LogEncrypt.MacAddressEncrypt(this.address) + "', name='" + this.name + "', deviceClass=" + this.deviceClass + ", features=" + this.features + ", enabledFeatures=" + this.enabledFeatures + '}';
    }
}
