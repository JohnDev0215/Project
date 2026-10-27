package com.amazon.avod.media.framework.error;

import com.amazon.avod.playback.PlaybackException;

/* JADX INFO: loaded from: classes2.dex */
public class InvalidRendererTimestampException extends PlaybackException {
    private static final long serialVersionUID = 1;

    public InvalidRendererTimestampException(String str) {
        super(PlaybackException.PlaybackError.NATIVE_PLAYBACK_ERROR, str);
    }
}
