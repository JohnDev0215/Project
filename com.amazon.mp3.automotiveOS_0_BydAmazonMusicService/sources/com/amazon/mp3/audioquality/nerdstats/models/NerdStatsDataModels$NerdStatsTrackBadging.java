package com.amazon.mp3.audioquality.nerdstats.models;

import com.amazon.mp3.playback.KatanaNPSBadgeProvider;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStatsTrackBadging, reason: from toString */
/* JADX INFO: compiled from: NerdStatsDataModels.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0004¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"com/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsTrackBadging", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/mp3/playback/KatanaNPSBadgeProvider$AudioQualityBadge;", "audioQualityBadge", "Lcom/amazon/mp3/playback/KatanaNPSBadgeProvider$AudioQualityBadge;", "getAudioQualityBadge", "()Lcom/amazon/mp3/playback/KatanaNPSBadgeProvider$AudioQualityBadge;", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", MediaTrack.ROLE_DESCRIPTION, "getDescription", "imageId", "I", "getImageId", "()I", "<init>", "(Lcom/amazon/mp3/playback/KatanaNPSBadgeProvider$AudioQualityBadge;Ljava/lang/String;Ljava/lang/String;I)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class NerdStatsTrackBadging {
    private final KatanaNPSBadgeProvider.AudioQualityBadge audioQualityBadge;
    private final String description;
    private final int imageId;
    private final String title;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NerdStatsTrackBadging)) {
            return false;
        }
        NerdStatsTrackBadging nerdStatsTrackBadging = (NerdStatsTrackBadging) other;
        return this.audioQualityBadge == nerdStatsTrackBadging.audioQualityBadge && Intrinsics.areEqual(this.title, nerdStatsTrackBadging.title) && Intrinsics.areEqual(this.description, nerdStatsTrackBadging.description) && this.imageId == nerdStatsTrackBadging.imageId;
    }

    public int hashCode() {
        return (((((this.audioQualityBadge.hashCode() * 31) + this.title.hashCode()) * 31) + this.description.hashCode()) * 31) + Integer.hashCode(this.imageId);
    }

    public String toString() {
        return "NerdStatsTrackBadging(audioQualityBadge=" + this.audioQualityBadge + ", title=" + this.title + ", description=" + this.description + ", imageId=" + this.imageId + ')';
    }

    public NerdStatsTrackBadging(KatanaNPSBadgeProvider.AudioQualityBadge audioQualityBadge, String title, String description, int i) {
        Intrinsics.checkNotNullParameter(audioQualityBadge, "audioQualityBadge");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(description, "description");
        this.audioQualityBadge = audioQualityBadge;
        this.title = title;
        this.description = description;
        this.imageId = i;
    }

    public final KatanaNPSBadgeProvider.AudioQualityBadge getAudioQualityBadge() {
        return this.audioQualityBadge;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getDescription() {
        return this.description;
    }

    public final int getImageId() {
        return this.imageId;
    }
}
