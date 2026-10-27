package com.amazon.video.sdk.content;

import com.amazon.avod.media.TimeSpan;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LocalContentManagerConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB#\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001b"}, d2 = {"Lcom/amazon/video/sdk/content/LocalContentManagerConfigData;", "Lcom/amazon/video/sdk/content/LocalContentManagerConfig;", "", "toString", "", "hashCode", "", "other", "", "equals", "", "allocationBytes", "J", "getAllocationBytes", "()J", "storagePath", "Ljava/lang/String;", "getStoragePath", "()Ljava/lang/String;", "Lcom/amazon/avod/media/TimeSpan;", "cacheDuration", "Lcom/amazon/avod/media/TimeSpan;", "getCacheDuration", "()Lcom/amazon/avod/media/TimeSpan;", "<init>", "(JLjava/lang/String;Lcom/amazon/avod/media/TimeSpan;)V", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class LocalContentManagerConfigData implements LocalContentManagerConfig {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final LocalContentManagerConfigData defaultConfig = new LocalContentManagerConfigData(0, null, null);
    private final long allocationBytes;
    private final TimeSpan cacheDuration;
    private final String storagePath;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocalContentManagerConfigData)) {
            return false;
        }
        LocalContentManagerConfigData localContentManagerConfigData = (LocalContentManagerConfigData) other;
        return getAllocationBytes() == localContentManagerConfigData.getAllocationBytes() && Intrinsics.areEqual(getStoragePath(), localContentManagerConfigData.getStoragePath()) && Intrinsics.areEqual(getCacheDuration(), localContentManagerConfigData.getCacheDuration());
    }

    public int hashCode() {
        return (((Long.hashCode(getAllocationBytes()) * 31) + (getStoragePath() == null ? 0 : getStoragePath().hashCode())) * 31) + (getCacheDuration() != null ? getCacheDuration().hashCode() : 0);
    }

    public String toString() {
        return "LocalContentManagerConfigData(allocationBytes=" + getAllocationBytes() + ", storagePath=" + ((Object) getStoragePath()) + ", cacheDuration=" + getCacheDuration() + ')';
    }

    public LocalContentManagerConfigData(long j, String str, TimeSpan timeSpan) {
        this.allocationBytes = j;
        this.storagePath = str;
        this.cacheDuration = timeSpan;
    }

    @Override // com.amazon.video.sdk.content.LocalContentManagerConfig
    public long getAllocationBytes() {
        return this.allocationBytes;
    }

    public String getStoragePath() {
        return this.storagePath;
    }

    @Override // com.amazon.video.sdk.content.LocalContentManagerConfig
    public TimeSpan getCacheDuration() {
        return this.cacheDuration;
    }

    /* JADX INFO: compiled from: LocalContentManagerConfig.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/video/sdk/content/LocalContentManagerConfigData$Companion;", "", "()V", "defaultConfig", "Lcom/amazon/video/sdk/content/LocalContentManagerConfigData;", "getDefaultConfig", "()Lcom/amazon/video/sdk/content/LocalContentManagerConfigData;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LocalContentManagerConfigData getDefaultConfig() {
            return LocalContentManagerConfigData.defaultConfig;
        }
    }
}
