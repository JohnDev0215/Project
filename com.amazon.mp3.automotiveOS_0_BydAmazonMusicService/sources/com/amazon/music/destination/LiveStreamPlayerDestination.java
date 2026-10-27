package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public class LiveStreamPlayerDestination extends Destination {
    private final String liveStreamId;

    public LiveStreamPlayerDestination(String str, String str2, String str3) {
        super(str2, str3);
        this.liveStreamId = str;
    }

    public String getLiveStreamId() {
        return this.liveStreamId;
    }
}
