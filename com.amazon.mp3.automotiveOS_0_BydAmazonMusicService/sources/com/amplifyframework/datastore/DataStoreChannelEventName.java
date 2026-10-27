package com.amplifyframework.datastore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public enum DataStoreChannelEventName {
    READY("ready"),
    NETWORK_STATUS("networkStatus"),
    SUBSCRIPTIONS_ESTABLISHED("subscriptionsEstablished"),
    SUBSCRIPTION_DATA_PROCESSED("subscriptionDataProcessed"),
    OUTBOX_STATUS("outboxStatus"),
    OUTBOX_MUTATION_ENQUEUED("outboxMutationEnqueued"),
    OUTBOX_MUTATION_PROCESSED("outboxMutationProcessed"),
    OUTBOX_MUTATION_FAILED("outboxMutationFailed"),
    SYNC_QUERIES_STARTED("syncQueriesStarted"),
    SYNC_QUERIES_READY("syncQueriesReady"),
    MODEL_SYNCED("modelSynced");

    private final String hubEventName;

    DataStoreChannelEventName(@NonNull String str) {
        Objects.requireNonNull(str);
        this.hubEventName = str;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.hubEventName;
    }

    @NonNull
    public static DataStoreChannelEventName fromString(@Nullable String str) {
        for (DataStoreChannelEventName dataStoreChannelEventName : values()) {
            if (dataStoreChannelEventName.toString().equals(str)) {
                return dataStoreChannelEventName;
            }
        }
        throw new IllegalArgumentException("DataStore category does not publish any Hub event with name = " + str);
    }
}
