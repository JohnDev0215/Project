package com.amazon.mp3.download;

import com.amazon.digitalmusicplayback.TrackQualityEntry;
import com.amazon.mp3.playback.PlaybackAudioQualityToAudioQuality;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HarleyDownloadedItem.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u0012\u001a\u00020\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0002J\t\u0010\b\u001a\u00020\u0007HÖ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\u0013\u0010\f\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0012\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/amazon/mp3/download/HarleyDownloadedItem;", "", "", "isAvailableOffline", "Lcom/amazon/music/media/playback/PlaybackAudioQuality;", "getPlaybackAudioQuality", "isDownloadedInKatana", "", "toString", "", "hashCode", "other", "equals", "Lcom/amazon/digitalmusicplayback/TrackQualityEntry;", "trackQualityEntry", "Lcom/amazon/digitalmusicplayback/TrackQualityEntry;", "getTrackQualityEntry", "()Lcom/amazon/digitalmusicplayback/TrackQualityEntry;", "isDrmContent", "Z", "()Z", "<init>", "(Lcom/amazon/digitalmusicplayback/TrackQualityEntry;Z)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class HarleyDownloadedItem {
    private final boolean isDrmContent;
    private final TrackQualityEntry trackQualityEntry;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HarleyDownloadedItem)) {
            return false;
        }
        HarleyDownloadedItem harleyDownloadedItem = (HarleyDownloadedItem) other;
        return Intrinsics.areEqual(this.trackQualityEntry, harleyDownloadedItem.trackQualityEntry) && this.isDrmContent == harleyDownloadedItem.isDrmContent;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    public int hashCode() {
        TrackQualityEntry trackQualityEntry = this.trackQualityEntry;
        int iHashCode = (trackQualityEntry == null ? 0 : trackQualityEntry.hashCode()) * 31;
        boolean z = this.isDrmContent;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iHashCode + r1;
    }

    public String toString() {
        return "HarleyDownloadedItem(trackQualityEntry=" + this.trackQualityEntry + ", isDrmContent=" + this.isDrmContent + ')';
    }

    public HarleyDownloadedItem(TrackQualityEntry trackQualityEntry, boolean z) {
        this.trackQualityEntry = trackQualityEntry;
        this.isDrmContent = z;
    }

    public final boolean isAvailableOffline() {
        String uri;
        if (this.isDrmContent) {
            TrackQualityEntry trackQualityEntry = this.trackQualityEntry;
            String uri2 = trackQualityEntry == null ? null : trackQualityEntry.getURI();
            if (!(uri2 == null || uri2.length() == 0)) {
                return true;
            }
        } else {
            TrackQualityEntry trackQualityEntry2 = this.trackQualityEntry;
            if (trackQualityEntry2 != null && (uri = trackQualityEntry2.getURI()) != null) {
                File file = new File(uri);
                if (file.canRead() && file.exists()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final PlaybackAudioQuality getPlaybackAudioQuality() {
        TrackQualityEntry trackQualityEntry = this.trackQualityEntry;
        PlaybackAudioQuality playbackAudioQualityFromPlayerAudioQuality = PlaybackAudioQuality.fromPlayerAudioQuality(trackQualityEntry == null ? null : trackQualityEntry.getQuality());
        Intrinsics.checkNotNullExpressionValue(playbackAudioQualityFromPlayerAudioQuality, "fromPlayerAudioQuality(trackQualityEntry?.quality)");
        return playbackAudioQualityFromPlayerAudioQuality;
    }

    public final boolean isDownloadedInKatana() {
        PlaybackAudioQualityToAudioQuality playbackAudioQualityToAudioQuality = new PlaybackAudioQualityToAudioQuality();
        return playbackAudioQualityToAudioQuality.isHD(getPlaybackAudioQuality()) || playbackAudioQualityToAudioQuality.isUHD(getPlaybackAudioQuality());
    }
}
