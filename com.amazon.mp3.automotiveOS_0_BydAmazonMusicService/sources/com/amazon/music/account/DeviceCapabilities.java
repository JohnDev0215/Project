package com.amazon.music.account;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public class DeviceCapabilities {
    private final String deviceLosingCatalogDeviceAuthorizationNextId;
    private final String deviceLosingCatalogDeviceAuthorizationNextName;
    private final int maxCatalogDeviceAuthorizations;

    public DeviceCapabilities(int i, @Nullable String str, @Nullable String str2) {
        this.maxCatalogDeviceAuthorizations = i;
        this.deviceLosingCatalogDeviceAuthorizationNextName = str;
        this.deviceLosingCatalogDeviceAuthorizationNextId = str2;
    }

    public int getMaxCatalogDeviceAuthorizations() {
        return this.maxCatalogDeviceAuthorizations;
    }

    @Nullable
    public String getDeviceLosingCatalogDeviceAuthorizationNextName() {
        return this.deviceLosingCatalogDeviceAuthorizationNextName;
    }

    @Nullable
    public String getDeviceLosingCatalogDeviceAuthorizationNextId() {
        return this.deviceLosingCatalogDeviceAuthorizationNextId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DeviceCapabilities deviceCapabilities = (DeviceCapabilities) obj;
        return equals(Integer.valueOf(this.maxCatalogDeviceAuthorizations), Integer.valueOf(deviceCapabilities.maxCatalogDeviceAuthorizations)) && equals(this.deviceLosingCatalogDeviceAuthorizationNextName, deviceCapabilities.deviceLosingCatalogDeviceAuthorizationNextName) && equals(this.deviceLosingCatalogDeviceAuthorizationNextId, deviceCapabilities.deviceLosingCatalogDeviceAuthorizationNextId);
    }

    public int hashCode() {
        return hash(Integer.valueOf(this.maxCatalogDeviceAuthorizations), this.deviceLosingCatalogDeviceAuthorizationNextName, this.deviceLosingCatalogDeviceAuthorizationNextId);
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
