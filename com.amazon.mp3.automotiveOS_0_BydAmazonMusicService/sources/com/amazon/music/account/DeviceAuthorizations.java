package com.amazon.music.account;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceAuthorizations {
    private final int remainingCatalogDeviceAuthorizations;
    private final int remainingLibraryDeviceAuthorizations;

    public DeviceAuthorizations(int i, int i2) {
        this.remainingLibraryDeviceAuthorizations = i;
        this.remainingCatalogDeviceAuthorizations = i2;
    }

    public int getRemainingLibraryDeviceAuthorizations() {
        return this.remainingLibraryDeviceAuthorizations;
    }

    public int getRemainingCatalogDeviceAuthorizations() {
        return this.remainingCatalogDeviceAuthorizations;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DeviceAuthorizations deviceAuthorizations = (DeviceAuthorizations) obj;
        return equals(Integer.valueOf(this.remainingLibraryDeviceAuthorizations), Integer.valueOf(deviceAuthorizations.remainingLibraryDeviceAuthorizations)) && equals(Integer.valueOf(this.remainingCatalogDeviceAuthorizations), Integer.valueOf(deviceAuthorizations.remainingCatalogDeviceAuthorizations));
    }

    public int hashCode() {
        return hash(Integer.valueOf(this.remainingLibraryDeviceAuthorizations), Integer.valueOf(this.remainingCatalogDeviceAuthorizations));
    }

    private int hash(Object... objArr) {
        int length = objArr.length;
        int iHashCode = 1;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            iHashCode = (iHashCode * 31) + (obj == null ? 0 : obj.hashCode());
        }
        return iHashCode;
    }

    private boolean equals(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }
}
