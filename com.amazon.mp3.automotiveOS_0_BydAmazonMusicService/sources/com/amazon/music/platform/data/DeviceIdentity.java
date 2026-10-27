package com.amazon.music.platform.data;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.PlatformProviderUtilKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeviceIdentity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/platform/data/DeviceIdentity;", "", "", "toString", "", "hashCode", "other", "", "equals", Splash.PARAMS_DEVICE_ID, "Ljava/lang/String;", "getDeviceId", "()Ljava/lang/String;", "deviceTypeId", "getDeviceTypeId", "centralDeviceTypeId", "getCentralDeviceTypeId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class DeviceIdentity {
    private final String centralDeviceTypeId;
    private final String deviceId;
    private final String deviceTypeId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceIdentity)) {
            return false;
        }
        DeviceIdentity deviceIdentity = (DeviceIdentity) other;
        return Intrinsics.areEqual(this.deviceId, deviceIdentity.deviceId) && Intrinsics.areEqual(this.deviceTypeId, deviceIdentity.deviceTypeId) && Intrinsics.areEqual(this.centralDeviceTypeId, deviceIdentity.centralDeviceTypeId);
    }

    public int hashCode() {
        return (((this.deviceId.hashCode() * 31) + this.deviceTypeId.hashCode()) * 31) + this.centralDeviceTypeId.hashCode();
    }

    public String toString() {
        return "DeviceIdentity(deviceId=" + this.deviceId + ", deviceTypeId=" + this.deviceTypeId + ", centralDeviceTypeId=" + this.centralDeviceTypeId + ')';
    }

    public DeviceIdentity(String deviceId, String deviceTypeId, String centralDeviceTypeId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceTypeId, "deviceTypeId");
        Intrinsics.checkNotNullParameter(centralDeviceTypeId, "centralDeviceTypeId");
        this.deviceId = deviceId;
        this.deviceTypeId = deviceTypeId;
        this.centralDeviceTypeId = centralDeviceTypeId;
        PlatformProviderUtilKt.freeze(this);
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final String getDeviceTypeId() {
        return this.deviceTypeId;
    }
}
