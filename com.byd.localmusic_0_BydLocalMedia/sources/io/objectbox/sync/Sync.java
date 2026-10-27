package io.objectbox.sync;

import io.objectbox.BoxStore;
import io.objectbox.sync.server.SyncServerBuilder;

/* JADX INFO: loaded from: classes5.dex */
public final class Sync {
    public static boolean isAvailable() {
        return BoxStore.isSyncAvailable();
    }

    public static boolean isServerAvailable() {
        return BoxStore.isSyncServerAvailable();
    }

    public static SyncBuilder client(BoxStore boxStore, String str, SyncCredentials syncCredentials) {
        return new SyncBuilder(boxStore, str, syncCredentials);
    }

    public static SyncServerBuilder server(BoxStore boxStore, String str, SyncCredentials syncCredentials) {
        return new SyncServerBuilder(boxStore, str, syncCredentials);
    }

    private Sync() {
    }
}
