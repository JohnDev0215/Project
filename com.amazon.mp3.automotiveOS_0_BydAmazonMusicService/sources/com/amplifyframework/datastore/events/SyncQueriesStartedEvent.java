package com.amplifyframework.datastore.events;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class SyncQueriesStartedEvent {
    private final String[] models;

    public SyncQueriesStartedEvent(String[] strArr) {
        this.models = (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    public String[] getModels() {
        return this.models;
    }

    @NonNull
    public String toString() {
        return "SyncQueriesStartedEvent{models=" + Arrays.toString(this.models) + '}';
    }

    public int hashCode() {
        return Arrays.hashCode(this.models);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SyncQueriesStartedEvent.class != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.models, ((SyncQueriesStartedEvent) obj).models);
    }
}
