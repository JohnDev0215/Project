package com.amazon.music.media.playback;

import com.amazon.music.media.playback.PlaybackException;

/* JADX INFO: loaded from: classes4.dex */
public class TooMuchSilenceException extends PlaybackException {
    public TooMuchSilenceException() {
        super(new PlaybackException.Config(PlaybackException.Recoverability.MEDIA_ITEM).autoRetry(true));
    }
}
