package com.amazon.mp3.audioquality.nerdstats.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsSummary, reason: from toString */
/* JADX INFO: compiled from: NerdStatsDataModels.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, d2 = {"com/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSummary", "", "", "toString", "", "hashCode", "other", "", "equals", "playbackQuality", "Ljava/lang/String;", "getPlaybackQuality", "()Ljava/lang/String;", "playbackNote", "getPlaybackNote", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class NerdStatsSummary {
    private final String playbackNote;
    private final String playbackQuality;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NerdStatsSummary)) {
            return false;
        }
        NerdStatsSummary nerdStatsSummary = (NerdStatsSummary) other;
        return Intrinsics.areEqual(this.playbackQuality, nerdStatsSummary.playbackQuality) && Intrinsics.areEqual(this.playbackNote, nerdStatsSummary.playbackNote);
    }

    public int hashCode() {
        return (this.playbackQuality.hashCode() * 31) + this.playbackNote.hashCode();
    }

    public String toString() {
        return "NerdStatsSummary(playbackQuality=" + this.playbackQuality + ", playbackNote=" + this.playbackNote + ')';
    }

    public NerdStatsSummary(String playbackQuality, String playbackNote) {
        Intrinsics.checkNotNullParameter(playbackQuality, "playbackQuality");
        Intrinsics.checkNotNullParameter(playbackNote, "playbackNote");
        this.playbackQuality = playbackQuality;
        this.playbackNote = playbackNote;
    }

    public final String getPlaybackQuality() {
        return this.playbackQuality;
    }

    public final String getPlaybackNote() {
        return this.playbackNote;
    }
}
