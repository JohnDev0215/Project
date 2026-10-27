package com.amazon.music.media.playback;

import com.amazon.music.media.playback.PlaybackException;

/* JADX INFO: loaded from: classes4.dex */
public class GhostListeningException extends PlaybackException {
    public GhostListeningException() {
        super(new PlaybackException.Config(PlaybackException.Recoverability.MEDIA_ITEM).description("Audio has been playing for too long without PlaybackController.onUserInteraction() being called."));
    }
}
