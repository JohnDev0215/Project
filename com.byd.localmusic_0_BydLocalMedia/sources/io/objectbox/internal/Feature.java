package io.objectbox.internal;

/* JADX INFO: loaded from: classes5.dex */
public enum Feature {
    RESULT_ARRAY(1),
    TIME_SERIES(2),
    SYNC(3),
    DEBUG_LOG(4),
    ADMIN(5),
    TREES(6),
    SYNC_SERVER(7);

    public final int id;

    Feature(int i) {
        this.id = i;
    }
}
