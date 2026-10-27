package com.amazon.music.playback.subscriber;

import com.amazon.music.playback.event.HLSTrackSegmentCachedEvent;
import org.greenrobot.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class HLSTrackSegmentCachedEventSubscriber {
    private static String TAG = "HLSTrackSegmentCachedEventSubscriber";
    private final Logger slf4jLogger = LoggerFactory.getLogger(TAG);

    @Subscribe
    public void onEvent(HLSTrackSegmentCachedEvent hLSTrackSegmentCachedEvent) {
        throw null;
    }
}
