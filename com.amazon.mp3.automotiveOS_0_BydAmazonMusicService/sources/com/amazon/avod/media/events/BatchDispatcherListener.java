package com.amazon.avod.media.events;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class BatchDispatcherListener implements BatchDispatcher {
    private final Set<MediaEventProducer> mMediaEventProducers;

    public BatchDispatcherListener(@Nonnull Set<MediaEventProducer> set) {
        this.mMediaEventProducers = (Set) Preconditions.checkNotNull(set, "mediaEventProducers");
    }

    @Override // com.amazon.avod.media.events.BatchDispatcher
    public void onNewBatchCreated() {
        Iterator<MediaEventProducer> it = this.mMediaEventProducers.iterator();
        while (it.hasNext()) {
            it.next().produce();
        }
    }

    @Override // com.amazon.avod.media.events.BatchDispatcher
    public void initialize() {
        Iterator<MediaEventProducer> it = this.mMediaEventProducers.iterator();
        while (it.hasNext()) {
            it.next().initialize();
        }
    }
}
