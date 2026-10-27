package com.amazon.music.metrics.mts;

import com.amazon.music.metrics.event.Event;

/* JADX INFO: loaded from: classes4.dex */
public class AnonymousRealTimeMTSEvent extends Event<AnonymousRealTimeMTSEvent> {
    private final MTSEvent mMtsEvent;

    public AnonymousRealTimeMTSEvent(MTSEvent mTSEvent) {
        super(AnonymousRealTimeMTSEvent.class);
        if (mTSEvent == null) {
            throw new IllegalArgumentException("Cannot create AnonymousMTSEvent with null MTSEvent");
        }
        this.mMtsEvent = mTSEvent;
    }

    public MTSEvent getMtsEvent() {
        return this.mMtsEvent;
    }
}
