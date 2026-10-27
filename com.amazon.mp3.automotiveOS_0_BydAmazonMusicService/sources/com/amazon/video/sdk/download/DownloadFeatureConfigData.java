package com.amazon.video.sdk.download;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadFeatureConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\b\b\u0086\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadFeatureConfigData;", "Lcom/amazon/video/sdk/download/DownloadFeatureConfig;", "", "toString", "", "hashCode", "", "other", "", "equals", "", "minimumDownloadProgressForPlayback", "D", "getMinimumDownloadProgressForPlayback", "()D", "<init>", "(D)V", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class DownloadFeatureConfigData implements DownloadFeatureConfig {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DownloadFeatureConfigData defaultConfig = new DownloadFeatureConfigData(0.0d);
    private final double minimumDownloadProgressForPlayback;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DownloadFeatureConfigData) && Intrinsics.areEqual((Object) Double.valueOf(getMinimumDownloadProgressForPlayback()), (Object) Double.valueOf(((DownloadFeatureConfigData) other).getMinimumDownloadProgressForPlayback()));
    }

    public int hashCode() {
        return Double.hashCode(getMinimumDownloadProgressForPlayback());
    }

    public String toString() {
        return "DownloadFeatureConfigData(minimumDownloadProgressForPlayback=" + getMinimumDownloadProgressForPlayback() + ')';
    }

    /* JADX INFO: compiled from: DownloadFeatureConfig.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/download/DownloadFeatureConfigData$Companion;", "", "()V", "defaultConfig", "Lcom/amazon/video/sdk/download/DownloadFeatureConfigData;", "getDefaultConfig", "()Lcom/amazon/video/sdk/download/DownloadFeatureConfigData;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DownloadFeatureConfigData getDefaultConfig() {
            return DownloadFeatureConfigData.defaultConfig;
        }
    }

    public DownloadFeatureConfigData(double d) {
        this.minimumDownloadProgressForPlayback = d;
    }

    @Override // com.amazon.video.sdk.download.DownloadFeatureConfig
    public double getMinimumDownloadProgressForPlayback() {
        return this.minimumDownloadProgressForPlayback;
    }
}
