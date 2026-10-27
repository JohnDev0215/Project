package com.amazon.mp3.audioquality.nerdstats.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStats, reason: from toString */
/* JADX INFO: compiled from: NerdStatsDataModels.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u0015\u001a\u00020\u0007\u0012\u0006\u0010\u0019\u001a\u00020\u0007\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\u0015\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u0019\u0010\u0018R\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"com/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStats", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSummary;", "summary", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSummary;", "getSummary", "()Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSummary;", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSignalFlowStats;", "spatialStats", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSignalFlowStats;", "getSpatialStats", "()Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSignalFlowStats;", "stereoStats", "getStereoStats", "enableSpatialToggle", "Z", "getEnableSpatialToggle", "()Z", "isSpatialToggleComplete", "mediaItemAsin", "Ljava/lang/String;", "getMediaItemAsin", "()Ljava/lang/String;", "<init>", "(Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSummary;Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSignalFlowStats;Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSignalFlowStats;ZZLjava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class NerdStats {
    private final boolean enableSpatialToggle;
    private final boolean isSpatialToggleComplete;
    private final String mediaItemAsin;
    private final NerdStatsSignalFlowStats spatialStats;
    private final NerdStatsSignalFlowStats stereoStats;
    private final NerdStatsSummary summary;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NerdStats)) {
            return false;
        }
        NerdStats nerdStats = (NerdStats) other;
        return Intrinsics.areEqual(this.summary, nerdStats.summary) && Intrinsics.areEqual(this.spatialStats, nerdStats.spatialStats) && Intrinsics.areEqual(this.stereoStats, nerdStats.stereoStats) && this.enableSpatialToggle == nerdStats.enableSpatialToggle && this.isSpatialToggleComplete == nerdStats.isSpatialToggleComplete && Intrinsics.areEqual(this.mediaItemAsin, nerdStats.mediaItemAsin);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v7, types: [int] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    public int hashCode() {
        int iHashCode = this.summary.hashCode() * 31;
        NerdStatsSignalFlowStats nerdStatsSignalFlowStats = this.spatialStats;
        int iHashCode2 = (iHashCode + (nerdStatsSignalFlowStats == null ? 0 : nerdStatsSignalFlowStats.hashCode())) * 31;
        NerdStatsSignalFlowStats nerdStatsSignalFlowStats2 = this.stereoStats;
        int iHashCode3 = (iHashCode2 + (nerdStatsSignalFlowStats2 == null ? 0 : nerdStatsSignalFlowStats2.hashCode())) * 31;
        boolean z = this.enableSpatialToggle;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode3 + r1) * 31;
        boolean z2 = this.isSpatialToggleComplete;
        int i2 = (i + (z2 ? 1 : z2)) * 31;
        String str = this.mediaItemAsin;
        return i2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "NerdStats(summary=" + this.summary + ", spatialStats=" + this.spatialStats + ", stereoStats=" + this.stereoStats + ", enableSpatialToggle=" + this.enableSpatialToggle + ", isSpatialToggleComplete=" + this.isSpatialToggleComplete + ", mediaItemAsin=" + ((Object) this.mediaItemAsin) + ')';
    }

    public NerdStats(NerdStatsSummary summary, NerdStatsSignalFlowStats nerdStatsSignalFlowStats, NerdStatsSignalFlowStats nerdStatsSignalFlowStats2, boolean z, boolean z2, String str) {
        Intrinsics.checkNotNullParameter(summary, "summary");
        this.summary = summary;
        this.spatialStats = nerdStatsSignalFlowStats;
        this.stereoStats = nerdStatsSignalFlowStats2;
        this.enableSpatialToggle = z;
        this.isSpatialToggleComplete = z2;
        this.mediaItemAsin = str;
    }

    public final NerdStatsSummary getSummary() {
        return this.summary;
    }

    public final NerdStatsSignalFlowStats getSpatialStats() {
        return this.spatialStats;
    }

    public final NerdStatsSignalFlowStats getStereoStats() {
        return this.stereoStats;
    }

    public final boolean getEnableSpatialToggle() {
        return this.enableSpatialToggle;
    }

    /* JADX INFO: renamed from: isSpatialToggleComplete, reason: from getter */
    public final boolean getIsSpatialToggleComplete() {
        return this.isSpatialToggleComplete;
    }

    public final String getMediaItemAsin() {
        return this.mediaItemAsin;
    }
}
