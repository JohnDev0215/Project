package com.amazon.music.playback.subscriber;

import com.amazon.music.playback.event.ProgressiveDownloadEvent;
import org.greenrobot.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ProgressiveDownloadEventSubscriber {
    private static String TAG = "ProgressiveDownloadEventSubscriber";
    private final Logger slf4jLogger = LoggerFactory.getLogger(TAG);

    @Subscribe
    public void onEvent(ProgressiveDownloadEvent progressiveDownloadEvent) {
        throw null;
    }
}
