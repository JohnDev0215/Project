package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;

/* JADX INFO: loaded from: classes6.dex */
public abstract class EventStoreModule {
    static String dbName() {
        return "com.google.android.datatransport.events";
    }

    static EventStoreConfig storeConfig() {
        return EventStoreConfig.DEFAULT;
    }

    static int schemaVersion() {
        return SchemaManager.SCHEMA_VERSION;
    }

    static String packageName(Context context) {
        return context.getPackageName();
    }
}
