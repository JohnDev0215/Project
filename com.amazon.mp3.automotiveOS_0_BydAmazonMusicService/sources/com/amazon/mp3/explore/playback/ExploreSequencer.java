package com.amazon.mp3.explore.playback;

import android.media.MediaMetadataRetriever;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.sequencer.IndexedSequencer;
import com.amazon.music.media.playback.sequencer.IndexedSequencerBase;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class ExploreSequencer extends IndexedSequencer {
    private String artworkUrl;
    private long durationMillis;
    private String primarySubtitle;
    private String secondarySubtitle;
    private String title;

    public ExploreSequencer() {
        super(false);
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        MediaCollectionInfo mediaCollectionInfo = new MediaCollectionInfo(null, null, null, null, null, new MediaCollectionId[0]);
        mediaCollectionInfo.addId(getPlaybackSessionId());
        sequencerInfoCopySequencerInfo.setMediaCollectionInfo(mediaCollectionInfo);
        setSequencerInfo(sequencerInfoCopySequencerInfo);
    }

    public void setNowPlayingScreenMetadata(final String title, final String primarySubtitle, final String secondarySubtitle, final String artworkUrl) {
        this.title = title;
        this.primarySubtitle = primarySubtitle;
        this.secondarySubtitle = secondarySubtitle;
        this.artworkUrl = artworkUrl;
    }

    public void loadHttpsMediaItem(final String url) {
        MediaAccessInfo mediaAccessInfoBuild = new MediaAccessInfo.Builder().withAllowedOnline(true).withAvailableOffline(false).withExplicit(true).withCastable(false).build();
        HttpsMediaItem httpsMediaItem = new HttpsMediaItem(url, getDurationMillis(url), this.title, this.artworkUrl, new MediaCollectionInfo(null, null, null, MediaCollectionType.HTTPS, mediaAccessInfoBuild, new MediaCollectionId[0]), PlaybackPageType.UNKNOWN);
        String str = this.primarySubtitle;
        if (str != null) {
            httpsMediaItem.addMediaCollectionInfo(new MediaCollectionInfo(str, null, null, MediaCollectionType.ARTIST, mediaAccessInfoBuild, new MediaCollectionId[0]));
        }
        String str2 = this.secondarySubtitle;
        if (str2 != null) {
            httpsMediaItem.addMediaCollectionInfo(new MediaCollectionInfo(str2, null, null, MediaCollectionType.ALBUM, mediaAccessInfoBuild, new MediaCollectionId[0]));
        }
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        sequencerInfoCopySequencerInfo.getQueue().clear();
        sequencerInfoCopySequencerInfo.getQueue().add(httpsMediaItem);
        setSequencerInfo(sequencerInfoCopySequencerInfo);
    }

    public long getDurationMillis() {
        return this.durationMillis;
    }

    private long getDurationMillis(final String url) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(url, new HashMap());
        long j = Long.parseLong(mediaMetadataRetriever.extractMetadata(9));
        this.durationMillis = j;
        return j;
    }
}
