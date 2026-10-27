package com.amazon.music.uiinteraction.subscribers;

import com.amazon.music.events.ContentViewedAppEvent;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final class ContentViewDedupeCache {
    private final PriorityQueue<ContentViewedAppEvent> cache = new PriorityQueue<>(11, BY_OLDEST_EVENT_TIME);
    private static final long DEDUPE_WINDOW_MS = TimeUnit.SECONDS.toMillis(1);
    private static final Comparator<ContentViewedAppEvent> BY_OLDEST_EVENT_TIME = new Comparator<ContentViewedAppEvent>() { // from class: com.amazon.music.uiinteraction.subscribers.ContentViewDedupeCache.1
        @Override // java.util.Comparator
        public int compare(ContentViewedAppEvent contentViewedAppEvent, ContentViewedAppEvent contentViewedAppEvent2) {
            return Long.compare(contentViewedAppEvent.getEventTime(), contentViewedAppEvent2.getEventTime());
        }
    };

    private void prune() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        while (this.cache.peek() != null && jCurrentTimeMillis - this.cache.peek().getEventTime() > DEDUPE_WINDOW_MS) {
            this.cache.remove();
        }
    }

    public boolean add(ContentViewedAppEvent contentViewedAppEvent) {
        prune();
        if (contentViewedAppEvent != null) {
            boolean z = false;
            for (ContentViewedAppEvent contentViewedAppEvent2 : this.cache) {
                if (ContentViewedAppEvent.isDuplicate(contentViewedAppEvent2, contentViewedAppEvent)) {
                    if (contentViewedAppEvent.getEventTime() > contentViewedAppEvent2.getEventTime()) {
                        this.cache.remove(contentViewedAppEvent2);
                        this.cache.add(contentViewedAppEvent);
                    }
                    z = true;
                }
            }
            if (!z) {
                this.cache.add(contentViewedAppEvent);
                return true;
            }
        }
        return false;
    }
}
