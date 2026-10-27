package com.amazon.mp3.net.dmls;

import com.amazon.music.inappmessaging.internal.model.Splash;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeviceToken.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/net/dmls/DeviceToken;", "Ljava/io/Serializable;", "", "toString", "", "hashCode", "", "other", "", "equals", Splash.PARAMS_DEVICE_ID, "Ljava/lang/String;", "getDeviceId", "()Ljava/lang/String;", "clientId", "getClientId", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class DeviceToken implements Serializable {
    private final String clientId;
    private final String deviceId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceToken)) {
            return false;
        }
        DeviceToken deviceToken = (DeviceToken) other;
        return Intrinsics.areEqual(this.deviceId, deviceToken.deviceId) && Intrinsics.areEqual(this.clientId, deviceToken.clientId);
    }

    public int hashCode() {
        return (this.deviceId.hashCode() * 31) + this.clientId.hashCode();
    }

    public String toString() {
        return "DeviceToken(deviceId=" + this.deviceId + ", clientId=" + this.clientId + ')';
    }

    public DeviceToken(String deviceId, String clientId) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        this.deviceId = deviceId;
        this.clientId = clientId;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }
}
