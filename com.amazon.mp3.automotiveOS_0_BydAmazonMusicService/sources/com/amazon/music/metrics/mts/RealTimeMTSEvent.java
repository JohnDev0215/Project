package com.amazon.music.metrics.mts;

import com.amazon.music.metrics.event.Event;

/* JADX INFO: loaded from: classes4.dex */
public class RealTimeMTSEvent extends Event<RealTimeMTSEvent> {
    private final MTSEvent mMtsEvent;

    public RealTimeMTSEvent(MTSEvent mTSEvent) {
        super(RealTimeMTSEvent.class);
        if (mTSEvent == null) {
            throw new IllegalArgumentException("Cannot create RealTimeMTSEvent with null MTSEvent");
        }
        this.mMtsEvent = mTSEvent;
    }

    public MTSEvent getMtsEvent() {
        return this.mMtsEvent;
    }
}
