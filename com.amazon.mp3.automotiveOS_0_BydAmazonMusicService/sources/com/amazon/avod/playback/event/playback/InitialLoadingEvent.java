package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;

/* JADX INFO: loaded from: classes2.dex */
public class InitialLoadingEvent extends PlaybackEvent {
    private final String mTitleId;
    private final String mUniqueContentId;

    public InitialLoadingEvent(TimeSpan timeSpan, String str, String str2) {
        super(timeSpan);
        this.mTitleId = str;
        this.mUniqueContentId = str2;
    }
}
