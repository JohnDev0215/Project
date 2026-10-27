package com.amazon.mp3.cloudqueue.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConnectionInfoMetadata.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/cloudqueue/model/ConnectionInfoMetadata;", "", "", "toString", "", "hashCode", "other", "", "equals", "iotEndPoint", "Ljava/lang/String;", "getIotEndPoint", "()Ljava/lang/String;", "identityPoolId", "getIdentityPoolId", "clientId", "getClientId", "region", "getRegion", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ConnectionInfoMetadata {
    private final String clientId;
    private final String identityPoolId;
    private final String iotEndPoint;
    private final String region;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConnectionInfoMetadata)) {
            return false;
        }
        ConnectionInfoMetadata connectionInfoMetadata = (ConnectionInfoMetadata) other;
        return Intrinsics.areEqual(this.iotEndPoint, connectionInfoMetadata.iotEndPoint) && Intrinsics.areEqual(this.identityPoolId, connectionInfoMetadata.identityPoolId) && Intrinsics.areEqual(this.clientId, connectionInfoMetadata.clientId) && Intrinsics.areEqual(this.region, connectionInfoMetadata.region);
    }

    public int hashCode() {
        String str = this.iotEndPoint;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.identityPoolId;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.clientId;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.region;
        return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "ConnectionInfoMetadata(iotEndPoint=" + ((Object) this.iotEndPoint) + ", identityPoolId=" + ((Object) this.identityPoolId) + ", clientId=" + ((Object) this.clientId) + ", region=" + ((Object) this.region) + ')';
    }

    public ConnectionInfoMetadata(String str, String str2, String str3, String str4) {
        this.iotEndPoint = str;
        this.identityPoolId = str2;
        this.clientId = str3;
        this.region = str4;
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getIdentityPoolId() {
        return this.identityPoolId;
    }

    public final String getIotEndPoint() {
        return this.iotEndPoint;
    }

    public final String getRegion() {
        return this.region;
    }
}
