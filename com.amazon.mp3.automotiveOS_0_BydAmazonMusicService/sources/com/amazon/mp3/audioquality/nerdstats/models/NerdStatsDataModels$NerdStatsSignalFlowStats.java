package com.amazon.mp3.audioquality.nerdstats.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsSignalFlowStats, reason: from toString */
/* JADX INFO: compiled from: NerdStatsDataModels.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001e\u001a\u00020\u001d\u0012\u0006\u0010#\u001a\u00020\"\u0012\u0006\u0010'\u001a\u00020\u0002\u0012\u0006\u0010+\u001a\u00020\u0002¢\u0006\u0004\b-\u0010.J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0019\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001e\u001a\u00020\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0017\u0010#\u001a\u00020\"8\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010'\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\u0017\u0010+\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b+\u0010(\u001a\u0004\b,\u0010*¨\u0006/"}, d2 = {"com/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSignalFlowStats", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$SignalFlowState;", "signalFlowState", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$SignalFlowState;", "getSignalFlowState", "()Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$SignalFlowState;", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsTrackBadging;", "trackBadging", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsTrackBadging;", "getTrackBadging", "()Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsTrackBadging;", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsTrackQuality;", "trackQuality", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsTrackQuality;", "getTrackQuality", "()Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsTrackQuality;", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsDeviceQuality;", "deviceQuality", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsDeviceQuality;", "getDeviceQuality", "()Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsDeviceQuality;", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsOutputQuality;", "outputQuality", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsOutputQuality;", "getOutputQuality", "()Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsOutputQuality;", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType;", "reducedQualityAlert", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType;", "getReducedQualityAlert", "()Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType;", "toggleLabel", "Ljava/lang/String;", "getToggleLabel", "()Ljava/lang/String;", "toggleAnnouncement", "getToggleAnnouncement", "<init>", "(Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$SignalFlowState;Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsTrackBadging;Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsTrackQuality;Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsDeviceQuality;Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsOutputQuality;Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsReducedQualityAlertType;Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class NerdStatsSignalFlowStats {
    private final NerdStatsDeviceQuality deviceQuality;
    private final NerdStatsOutputQuality outputQuality;
    private final NerdStatsReducedQualityAlertType reducedQualityAlert;
    private final NerdStatsDataModels$SignalFlowState signalFlowState;
    private final String toggleAnnouncement;
    private final String toggleLabel;
    private final NerdStatsTrackBadging trackBadging;
    private final NerdStatsTrackQuality trackQuality;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NerdStatsSignalFlowStats)) {
            return false;
        }
        NerdStatsSignalFlowStats nerdStatsSignalFlowStats = (NerdStatsSignalFlowStats) other;
        return this.signalFlowState == nerdStatsSignalFlowStats.signalFlowState && Intrinsics.areEqual(this.trackBadging, nerdStatsSignalFlowStats.trackBadging) && Intrinsics.areEqual(this.trackQuality, nerdStatsSignalFlowStats.trackQuality) && Intrinsics.areEqual(this.deviceQuality, nerdStatsSignalFlowStats.deviceQuality) && Intrinsics.areEqual(this.outputQuality, nerdStatsSignalFlowStats.outputQuality) && Intrinsics.areEqual(this.reducedQualityAlert, nerdStatsSignalFlowStats.reducedQualityAlert) && Intrinsics.areEqual(this.toggleLabel, nerdStatsSignalFlowStats.toggleLabel) && Intrinsics.areEqual(this.toggleAnnouncement, nerdStatsSignalFlowStats.toggleAnnouncement);
    }

    public int hashCode() {
        return (((((((((((((this.signalFlowState.hashCode() * 31) + this.trackBadging.hashCode()) * 31) + this.trackQuality.hashCode()) * 31) + this.deviceQuality.hashCode()) * 31) + this.outputQuality.hashCode()) * 31) + this.reducedQualityAlert.hashCode()) * 31) + this.toggleLabel.hashCode()) * 31) + this.toggleAnnouncement.hashCode();
    }

    public String toString() {
        return "NerdStatsSignalFlowStats(signalFlowState=" + this.signalFlowState + ", trackBadging=" + this.trackBadging + ", trackQuality=" + this.trackQuality + ", deviceQuality=" + this.deviceQuality + ", outputQuality=" + this.outputQuality + ", reducedQualityAlert=" + this.reducedQualityAlert + ", toggleLabel=" + this.toggleLabel + ", toggleAnnouncement=" + this.toggleAnnouncement + ')';
    }

    public NerdStatsSignalFlowStats(NerdStatsDataModels$SignalFlowState signalFlowState, NerdStatsTrackBadging trackBadging, NerdStatsTrackQuality trackQuality, NerdStatsDeviceQuality deviceQuality, NerdStatsOutputQuality outputQuality, NerdStatsReducedQualityAlertType reducedQualityAlert, String toggleLabel, String toggleAnnouncement) {
        Intrinsics.checkNotNullParameter(signalFlowState, "signalFlowState");
        Intrinsics.checkNotNullParameter(trackBadging, "trackBadging");
        Intrinsics.checkNotNullParameter(trackQuality, "trackQuality");
        Intrinsics.checkNotNullParameter(deviceQuality, "deviceQuality");
        Intrinsics.checkNotNullParameter(outputQuality, "outputQuality");
        Intrinsics.checkNotNullParameter(reducedQualityAlert, "reducedQualityAlert");
        Intrinsics.checkNotNullParameter(toggleLabel, "toggleLabel");
        Intrinsics.checkNotNullParameter(toggleAnnouncement, "toggleAnnouncement");
        this.signalFlowState = signalFlowState;
        this.trackBadging = trackBadging;
        this.trackQuality = trackQuality;
        this.deviceQuality = deviceQuality;
        this.outputQuality = outputQuality;
        this.reducedQualityAlert = reducedQualityAlert;
        this.toggleLabel = toggleLabel;
        this.toggleAnnouncement = toggleAnnouncement;
    }

    public final NerdStatsDataModels$SignalFlowState getSignalFlowState() {
        return this.signalFlowState;
    }

    public final NerdStatsTrackBadging getTrackBadging() {
        return this.trackBadging;
    }

    public final NerdStatsTrackQuality getTrackQuality() {
        return this.trackQuality;
    }

    public final NerdStatsDeviceQuality getDeviceQuality() {
        return this.deviceQuality;
    }

    public final NerdStatsOutputQuality getOutputQuality() {
        return this.outputQuality;
    }

    public final NerdStatsReducedQualityAlertType getReducedQualityAlert() {
        return this.reducedQualityAlert;
    }

    public final String getToggleLabel() {
        return this.toggleLabel;
    }

    public final String getToggleAnnouncement() {
        return this.toggleAnnouncement;
    }
}
