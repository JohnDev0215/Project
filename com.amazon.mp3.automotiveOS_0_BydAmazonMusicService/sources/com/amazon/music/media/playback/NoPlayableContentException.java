package com.amazon.music.media.playback;

import com.amazon.music.media.playback.PlaybackException;

/* JADX INFO: loaded from: classes4.dex */
public class NoPlayableContentException extends PlaybackException {
    public NoPlayableContentException(MediaItem mediaItem) {
        super(new PlaybackException.Config(PlaybackException.Recoverability.MEDIA_ITEM).mediaItem(mediaItem).description("No MediaItems are playable with the current MediaAccessPolicy."));
    }
}
