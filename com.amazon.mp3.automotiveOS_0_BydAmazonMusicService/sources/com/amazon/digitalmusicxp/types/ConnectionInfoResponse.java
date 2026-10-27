package com.amazon.digitalmusicxp.types;

import kotlin.Metadata;

/* JADX INFO: compiled from: ConnectionInfoResponse.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\f\u0010\rR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\u0004\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/amazon/digitalmusicxp/types/ConnectionInfoResponse;", "", "", "appSyncEndpoint", "Ljava/lang/String;", "getAppSyncEndpoint", "()Ljava/lang/String;", "setAppSyncEndpoint", "(Ljava/lang/String;)V", "region", "getRegion", "setRegion", "<init>", "()V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final class ConnectionInfoResponse {
    private String appSyncEndpoint;
    private String region;

    public final String getAppSyncEndpoint() {
        return this.appSyncEndpoint;
    }

    public final String getRegion() {
        return this.region;
    }
}
