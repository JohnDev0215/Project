package com.amazon.podcast.media.playback;

import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastMediaItem extends MediaItem {
    protected PodcastMediaItem(final String audioUrl, final String name, final MediaAccessInfo mediaAccessInfo, final long duration, final MediaCollectionInfo collectionInfo) {
        super(new MediaItemId(MediaItemId.Type.URL, audioUrl), name, duration, MediaItem.Type.PODCAST_CONTENT, mediaAccessInfo, null, com.amazon.music.media.playback.Playback.getInstance().getPlaybackConfig().getMediaLinkProvider(), com.amazon.music.media.playback.Playback.getInstance().getPlaybackConfig().getMediaItemImageUriProvider(), PlaybackPageType.UNKNOWN, false, collectionInfo, new MediaCollectionInfo[0]);
    }
}
