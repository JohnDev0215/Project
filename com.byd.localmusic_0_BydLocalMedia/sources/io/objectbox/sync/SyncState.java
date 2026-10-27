package io.objectbox.sync;

/* JADX INFO: loaded from: classes5.dex */
public enum SyncState {
    UNKNOWN(0),
    CREATED(1),
    STARTED(2),
    CONNECTED(3),
    LOGGED_IN(4),
    DISCONNECTED(5),
    STOPPED(6),
    DEAD(7);

    public final int id;

    SyncState(int i) {
        this.id = i;
    }

    public static SyncState fromId(int i) {
        for (SyncState syncState : values()) {
            if (syncState.id == i) {
                return syncState;
            }
        }
        return UNKNOWN;
    }
}
