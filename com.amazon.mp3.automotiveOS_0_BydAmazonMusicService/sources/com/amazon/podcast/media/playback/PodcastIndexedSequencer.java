package com.amazon.podcast.media.playback;

import com.amazon.music.ContentAccessType;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.sequencer.IndexedSequencer;
import com.amazon.music.media.playback.sequencer.IndexedSequencerBase;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.cast.Casting;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastIndexedSequencer extends IndexedSequencer {
    private MediaAccessInfo mediaAccessInfo;
    private MediaCollectionInfo mediaCollectionInfo;
    private PodcastMediaItem podcastMediaItem;

    public PodcastIndexedSequencer() {
        super(false);
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        MediaCollectionInfo mediaCollectionInfo = new MediaCollectionInfo(null, null, null, null, null, new MediaCollectionId[0]);
        mediaCollectionInfo.addId(getPlaybackSessionId());
        sequencerInfoCopySequencerInfo.setMediaCollectionInfo(mediaCollectionInfo);
        setSequencerInfo(sequencerInfoCopySequencerInfo);
    }

    public void loadPodcastMediaItem(final String audioUrl) {
        clearPodcastMedia();
        this.mediaAccessInfo = new MediaAccessInfo.Builder().withAllowedOnline(true).withAvailableOffline(true).withCastable(true).withExplicit(false).withContentAccessType(ContentAccessType.NIGHTWING).build();
        this.mediaCollectionInfo = new MediaCollectionInfo(null, null, null, MediaCollectionType.getCollectionType("HTTPS"), this.mediaAccessInfo, new MediaCollectionId[0]);
        this.podcastMediaItem = new PodcastMediaItem(audioUrl, getTitle(), this.mediaAccessInfo, getDurationMillis(), this.mediaCollectionInfo);
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        sequencerInfoCopySequencerInfo.getQueue().clear();
        sequencerInfoCopySequencerInfo.getQueue().add(this.podcastMediaItem);
        setSequencerInfo(sequencerInfoCopySequencerInfo);
    }

    private void clearPodcastMedia() {
        this.mediaAccessInfo = null;
        this.mediaCollectionInfo = null;
        this.podcastMediaItem = null;
    }

    private long getDurationMillis() {
        Playback playback = Podcast.getPlayback();
        if (playback == null || playback.getMedia() == null || playback.getMedia().getMediaMetadataElement() == null) {
            return 0L;
        }
        long jLongValue = playback.getMedia().getMediaMetadataElement().getDurationSeconds().longValue();
        if (Casting.isCasting()) {
            return jLongValue;
        }
        return -1L;
    }

    private String getTitle() {
        Playback playback = Podcast.getPlayback();
        return (playback == null || playback.getMedia() == null || playback.getMedia().getMediaMetadataElement() == null) ? "" : playback.getMedia().getMediaMetadataElement().getTitle();
    }
}
