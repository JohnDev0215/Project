package com.amazon.music.platform.playback.data;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.PlatformProviderUtilKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackDevice.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/platform/playback/data/PlaybackDevice;", "", "", "toString", "", "hashCode", "other", "", "equals", Splash.PARAMS_DEVICE_ID, "Ljava/lang/String;", "getDeviceId", "()Ljava/lang/String;", Splash.PARAMS_DEVICE_TYPE, "getDeviceType", "deviceName", "getDeviceName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class PlaybackDevice {
    private final String deviceId;
    private final String deviceName;
    private final String deviceType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackDevice)) {
            return false;
        }
        PlaybackDevice playbackDevice = (PlaybackDevice) other;
        return Intrinsics.areEqual(this.deviceId, playbackDevice.deviceId) && Intrinsics.areEqual(this.deviceType, playbackDevice.deviceType) && Intrinsics.areEqual(this.deviceName, playbackDevice.deviceName);
    }

    public int hashCode() {
        int iHashCode = ((this.deviceId.hashCode() * 31) + this.deviceType.hashCode()) * 31;
        String str = this.deviceName;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public PlaybackDevice(String deviceId, String deviceType, String str) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        this.deviceId = deviceId;
        this.deviceType = deviceType;
        this.deviceName = str;
        PlatformProviderUtilKt.freeze(this);
    }

    public /* synthetic */ PlaybackDevice(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : str3);
    }

    public String toString() {
        return "PlaybackDevice(deviceId='" + this.deviceId + "', deviceType='" + this.deviceType + "')";
    }
}
