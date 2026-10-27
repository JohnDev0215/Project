package com.amazon.mp3.auto.carmode;

import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.external.car.PodcastMediaMetadata;
import com.amazon.podcast.media.playback.Media;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayingMediaContentProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\b\u0010\b\u001a\u0004\u0018\u00010\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/auto/carmode/PlayingMediaContentProvider;", "", "playbackController", "Lcom/amazon/music/media/playback/PlaybackController;", "(Lcom/amazon/music/media/playback/PlaybackController;)V", "getPlayingContentSourceInfo", "", "getPlayingContentSubtitle", "getPlayingContentTitle", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayingMediaContentProvider {
    private final PlaybackController playbackController;

    public PlayingMediaContentProvider(PlaybackController playbackController) {
        Intrinsics.checkNotNullParameter(playbackController, "playbackController");
        this.playbackController = playbackController;
    }

    public final String getPlayingContentSourceInfo() {
        MediaCollectionInfo sourceMediaCollectionInfo;
        Media media;
        PodcastMediaMetadata podcastMediaMetadata;
        String mediaCollectionId = (!Podcast.getPlayback().isPodcastLastPlayed() || (media = Podcast.getPlayback().getMedia()) == null || (podcastMediaMetadata = media.getPodcastMediaMetadata()) == null) ? null : podcastMediaMetadata.getMediaCollectionId();
        if (mediaCollectionId != null) {
            return mediaCollectionId;
        }
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null || (sourceMediaCollectionInfo = currentMediaItem.getSourceMediaCollectionInfo()) == null) {
            return null;
        }
        return sourceMediaCollectionInfo.getName();
    }

    public final String getPlayingContentTitle() {
        Media media;
        PodcastMediaMetadata podcastMediaMetadata;
        String title = (!Podcast.getPlayback().isPodcastLastPlayed() || (media = Podcast.getPlayback().getMedia()) == null || (podcastMediaMetadata = media.getPodcastMediaMetadata()) == null) ? null : podcastMediaMetadata.getTitle();
        if (title != null) {
            return title;
        }
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null) {
            return null;
        }
        return currentMediaItem.getName();
    }

    public final String getPlayingContentSubtitle() {
        Media media;
        PodcastMediaMetadata podcastMediaMetadata;
        String subtitle = (!Podcast.getPlayback().isPodcastLastPlayed() || (media = Podcast.getPlayback().getMedia()) == null || (podcastMediaMetadata = media.getPodcastMediaMetadata()) == null) ? null : podcastMediaMetadata.getSubtitle();
        if (subtitle != null) {
            return subtitle;
        }
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null) {
            return null;
        }
        return currentMediaItem.getArtistName();
    }
}
