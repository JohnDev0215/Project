package com.amplifyframework.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class AuthDevice {
    private final String deviceId;
    private final String deviceName;

    private AuthDevice(String str, String str2) {
        this.deviceId = str;
        this.deviceName = str2;
    }

    public static AuthDevice fromId(@NonNull String str) {
        return fromId(str, null);
    }

    public static AuthDevice fromId(@NonNull String str, @Nullable String str2) {
        Objects.requireNonNull(str);
        return new AuthDevice(str, str2);
    }

    @NonNull
    public String getDeviceId() {
        return this.deviceId;
    }

    @Nullable
    public String getDeviceName() {
        return this.deviceName;
    }

    public int hashCode() {
        return ObjectsCompat.hash(getDeviceId(), getDeviceName());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AuthDevice.class != obj.getClass()) {
            return false;
        }
        AuthDevice authDevice = (AuthDevice) obj;
        return ObjectsCompat.equals(getDeviceId(), authDevice.getDeviceId()) && ObjectsCompat.equals(getDeviceName(), authDevice.getDeviceName());
    }

    public String toString() {
        return "AuthDevice{deviceId='" + this.deviceId + "', deviceName='" + this.deviceName + "'}";
    }
}
