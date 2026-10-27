package com.amazon.music.metrics.mts.event.definition.sync;

import com.amazon.music.metrics.mts.event.exception.CirrusInvalidDataException;

/* JADX INFO: loaded from: classes4.dex */
public class InitialSyncFinishedEvent extends SyncEvent {
    public InitialSyncFinishedEvent(long j) throws CirrusInvalidDataException {
        super("initialSyncCompleted", j);
    }
}
