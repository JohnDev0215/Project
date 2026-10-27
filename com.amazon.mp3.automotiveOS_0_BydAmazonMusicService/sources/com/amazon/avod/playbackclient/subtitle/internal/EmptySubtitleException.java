package com.amazon.avod.playbackclient.subtitle.internal;

import com.amazon.avod.playbackclient.subtitle.internal.SubtitleException;

/* JADX INFO: loaded from: classes2.dex */
public class EmptySubtitleException extends SubtitleException {
    public EmptySubtitleException(SubtitleException.SubtitleErrorCode subtitleErrorCode, String str) {
        super(subtitleErrorCode, str);
    }
}
