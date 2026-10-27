package io.objectbox.sync;

/* JADX INFO: loaded from: classes5.dex */
public class SyncChange {
    final long[] changedIds;
    final int entityTypeId;
    final long[] removedIds;

    public SyncChange(int i, long[] jArr, long[] jArr2) {
        this.entityTypeId = i;
        this.changedIds = jArr;
        this.removedIds = jArr2;
    }

    @Deprecated
    public SyncChange(long j, long[] jArr, long[] jArr2) {
        this.entityTypeId = (int) j;
        this.changedIds = jArr;
        this.removedIds = jArr2;
    }

    public int getEntityTypeId() {
        return this.entityTypeId;
    }

    public long[] getChangedIds() {
        return this.changedIds;
    }

    public long[] getRemovedIds() {
        return this.removedIds;
    }
}
