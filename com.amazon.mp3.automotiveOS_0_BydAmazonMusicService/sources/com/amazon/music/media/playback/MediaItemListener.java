package com.amazon.music.media.playback;

/* JADX INFO: loaded from: classes4.dex */
public interface MediaItemListener {
    void onErrorFetchingMediaLinks(MediaItem mediaItem, PlaybackException playbackException);

    void onMediaLinksChanged(MediaItem mediaItem);
}
