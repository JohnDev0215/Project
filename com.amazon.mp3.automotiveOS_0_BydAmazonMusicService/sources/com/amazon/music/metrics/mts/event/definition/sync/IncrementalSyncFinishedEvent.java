package com.amazon.music.metrics.mts.event.definition.sync;

import com.amazon.music.metrics.mts.event.exception.CirrusInvalidDataException;

/* JADX INFO: loaded from: classes4.dex */
public class IncrementalSyncFinishedEvent extends SyncEvent {
    public IncrementalSyncFinishedEvent(long j) throws CirrusInvalidDataException {
        super("incrementalSyncCompleted", j);
    }
}
