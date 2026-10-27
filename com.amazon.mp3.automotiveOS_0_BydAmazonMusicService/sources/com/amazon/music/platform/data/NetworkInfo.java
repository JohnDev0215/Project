package com.amazon.music.platform.data;

import com.amazon.music.platform.PlatformProviderUtilKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NetworkInfo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u0011\u001a\u00020\u0007\u0012\u0006\u0010\u0012\u001a\u00020\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\t\u0010\u000bR\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0011\u0010\u000bR\u0017\u0010\u0012\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0012\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/platform/data/NetworkInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "isConnected", "Z", "()Z", "Lcom/amazon/music/platform/data/ConnectionType;", "connectionType", "Lcom/amazon/music/platform/data/ConnectionType;", "getConnectionType", "()Lcom/amazon/music/platform/data/ConnectionType;", "isWiFiOnlyDevice", "isRoaming", "<init>", "(ZLcom/amazon/music/platform/data/ConnectionType;ZZ)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class NetworkInfo {
    private final ConnectionType connectionType;
    private final boolean isConnected;
    private final boolean isRoaming;
    private final boolean isWiFiOnlyDevice;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NetworkInfo)) {
            return false;
        }
        NetworkInfo networkInfo = (NetworkInfo) other;
        return this.isConnected == networkInfo.isConnected && Intrinsics.areEqual(this.connectionType, networkInfo.connectionType) && this.isWiFiOnlyDevice == networkInfo.isWiFiOnlyDevice && this.isRoaming == networkInfo.isRoaming;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    public int hashCode() {
        boolean z = this.isConnected;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int iHashCode = ((r0 * 31) + this.connectionType.hashCode()) * 31;
        boolean z2 = this.isWiFiOnlyDevice;
        ?? r2 = z2;
        if (z2) {
            r2 = 1;
        }
        int i = (iHashCode + r2) * 31;
        boolean z3 = this.isRoaming;
        return i + (z3 ? 1 : z3);
    }

    public String toString() {
        return "NetworkInfo(isConnected=" + this.isConnected + ", connectionType=" + this.connectionType + ", isWiFiOnlyDevice=" + this.isWiFiOnlyDevice + ", isRoaming=" + this.isRoaming + ')';
    }

    public NetworkInfo(boolean z, ConnectionType connectionType, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(connectionType, "connectionType");
        this.isConnected = z;
        this.connectionType = connectionType;
        this.isWiFiOnlyDevice = z2;
        this.isRoaming = z3;
        PlatformProviderUtilKt.freeze(this);
    }

    /* JADX INFO: renamed from: isConnected, reason: from getter */
    public final boolean getIsConnected() {
        return this.isConnected;
    }

    public final ConnectionType getConnectionType() {
        return this.connectionType;
    }

    /* JADX INFO: renamed from: isWiFiOnlyDevice, reason: from getter */
    public final boolean getIsWiFiOnlyDevice() {
        return this.isWiFiOnlyDevice;
    }

    /* JADX INFO: renamed from: isRoaming, reason: from getter */
    public final boolean getIsRoaming() {
        return this.isRoaming;
    }
}
