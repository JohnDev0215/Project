package com.amazon.music.playback.subscriber;

import android.net.Uri;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.playback.event.CollectionLoadEndEvent;
import com.amazon.music.playback.event.CollectionLoadStartEvent;
import com.amazon.music.playback.event.LocalPlaybackEndEvent;
import com.amazon.music.playback.event.StreamingEndEvent;
import com.amazon.music.playback.event.StreamingStartEvent;
import com.amazon.music.playback.exception.CollectionLoadException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.greenrobot.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class StreamingTerminatedEventSubscriber extends EventSubscriber {
    private static String TAG = "StreamingTerminatedEventSubscriber";
    private int CACHED_COLLECTIONS_THRESHOLD;
    LinkedHashMap<Uri, Long> collectionDelay;
    private long collectionLoadEndTimeMS;
    private Uri collectionLoadEndUri;
    private long collectionLoadStartTimeMS;
    private Uri collectionLoadStartUri;
    private PostDelayedEventHandler postDelayedEventHandler;
    private final Logger slf4jLogger;

    public StreamingTerminatedEventSubscriber(MetricsManager metricsManager) {
        super(metricsManager);
        this.slf4jLogger = LoggerFactory.getLogger(TAG);
        this.CACHED_COLLECTIONS_THRESHOLD = 2;
        this.collectionDelay = new LinkedHashMap<Uri, Long>() { // from class: com.amazon.music.playback.subscriber.StreamingTerminatedEventSubscriber.1
            @Override // java.util.LinkedHashMap
            protected boolean removeEldestEntry(Map.Entry<Uri, Long> entry) {
                return StreamingTerminatedEventSubscriber.this.collectionDelay.size() > StreamingTerminatedEventSubscriber.this.CACHED_COLLECTIONS_THRESHOLD;
            }
        };
        this.postDelayedEventHandler = new PostDelayedEventHandler(metricsManager);
    }

    @Subscribe
    public void onEvent(CollectionLoadStartEvent collectionLoadStartEvent) {
        long j = collectionLoadStartEvent.startTimeMS;
        this.collectionLoadStartTimeMS = j;
        this.collectionLoadStartUri = collectionLoadStartEvent.collectionUri;
        this.slf4jLogger.debug("collectionLoadStartTimeMS = {}, collectionLoadStartUri = {}", Long.valueOf(j), this.collectionLoadStartUri.toString());
    }

    @Subscribe
    public void onEvent(CollectionLoadEndEvent collectionLoadEndEvent) throws CollectionLoadException {
        this.collectionLoadEndTimeMS = collectionLoadEndEvent.endTimeMS;
        Uri uri = collectionLoadEndEvent.collectionUri;
        this.collectionLoadEndUri = uri;
        if (uri != null && uri.equals(this.collectionLoadStartUri)) {
            long j = this.collectionLoadEndTimeMS - this.collectionLoadStartTimeMS;
            this.slf4jLogger.debug("delay = {}, collectionLoadStartTimeMS = {}, collectionLoadEndTimeMS = {}, collectionLoadStartUri = {}, collectionLoadEndUri = {}", Long.valueOf(j), Long.valueOf(this.collectionLoadStartTimeMS), Long.valueOf(this.collectionLoadEndTimeMS), this.collectionLoadStartUri.toString(), this.collectionLoadEndUri.toString());
            if (this.collectionLoadEndTimeMS > 0 && j > 0) {
                this.collectionDelay.put(this.collectionLoadEndUri, Long.valueOf(j));
                this.slf4jLogger.debug("cached a new collection, uri = {}, delay = {}", this.collectionLoadEndUri.toString(), Long.valueOf(j));
                return;
            } else {
                resetCollectionData();
                throw new CollectionLoadException("Incorrect collection loading time");
            }
        }
        Logger logger = this.slf4jLogger;
        Uri uri2 = this.collectionLoadStartUri;
        String string = uri2 == null ? null : uri2.toString();
        Uri uri3 = this.collectionLoadEndUri;
        logger.debug("collectionLoadStartUri = {}, collectionLoadEndUri = {}", string, uri3 != null ? uri3.toString() : null);
        resetCollectionData();
        throw new CollectionLoadException("collection doesn't match or events not coming in order");
    }

    @Subscribe
    public void onEvent(StreamingEndEvent streamingEndEvent) throws Exception {
        throw null;
    }

    @Subscribe
    public void onEvent(LocalPlaybackEndEvent localPlaybackEndEvent) throws Exception {
        this.slf4jLogger.debug("LocalPlaybackEndEvent: " + localPlaybackEndEvent);
        throw null;
    }

    @Subscribe
    public void onEvent(StreamingStartEvent streamingStartEvent) throws Exception {
        this.slf4jLogger.debug("StreamingStartEvent: " + streamingStartEvent);
        this.postDelayedEventHandler.updateTerminationReason(streamingStartEvent);
    }

    private void resetCollectionData() {
        this.slf4jLogger.debug("reset collection data");
        this.collectionLoadStartUri = null;
        this.collectionLoadEndUri = null;
        this.collectionLoadEndTimeMS = 0L;
        this.collectionLoadStartTimeMS = 0L;
    }
}
