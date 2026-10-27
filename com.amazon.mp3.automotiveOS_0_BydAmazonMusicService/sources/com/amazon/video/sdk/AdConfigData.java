package com.amazon.video.sdk;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AdConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0017\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\b¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\n\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/amazon/video/sdk/AdConfigData;", "Lcom/amazon/video/sdk/AdConfig;", "", "toString", "", "hashCode", "", "other", "", "equals", "advertisingId", "Ljava/lang/String;", "getAdvertisingId", "()Ljava/lang/String;", "optOut", "Z", "getOptOut", "()Z", "<init>", "(Ljava/lang/String;Z)V", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AdConfigData implements AdConfig {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AdConfigData defaultConfig = new AdConfigData("null", true);
    private final String advertisingId;
    private final boolean optOut;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdConfigData)) {
            return false;
        }
        AdConfigData adConfigData = (AdConfigData) other;
        return Intrinsics.areEqual(getAdvertisingId(), adConfigData.getAdvertisingId()) && getOptOut() == adConfigData.getOptOut();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    public int hashCode() {
        int iHashCode = getAdvertisingId().hashCode() * 31;
        boolean optOut = getOptOut();
        ?? r1 = optOut;
        if (optOut) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "AdConfigData(advertisingId=" + getAdvertisingId() + ", optOut=" + getOptOut() + ')';
    }

    /* JADX INFO: compiled from: AdConfig.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/AdConfigData$Companion;", "", "()V", "defaultConfig", "Lcom/amazon/video/sdk/AdConfigData;", "getDefaultConfig", "()Lcom/amazon/video/sdk/AdConfigData;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AdConfigData getDefaultConfig() {
            return AdConfigData.defaultConfig;
        }
    }

    public AdConfigData(String advertisingId, boolean z) {
        Intrinsics.checkNotNullParameter(advertisingId, "advertisingId");
        this.advertisingId = advertisingId;
        this.optOut = z;
    }

    @Override // com.amazon.video.sdk.AdConfig
    public String getAdvertisingId() {
        return this.advertisingId;
    }

    @Override // com.amazon.video.sdk.AdConfig
    public boolean getOptOut() {
        return this.optOut;
    }
}
