package com.amplifyframework.datastore.model;

import androidx.annotation.NonNull;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.datastore.appsync.ModelMetadata;
import com.amplifyframework.datastore.storage.sqlite.PersistentModelVersion;
import com.amplifyframework.datastore.syncengine.LastSyncMetadata;
import com.amplifyframework.datastore.syncengine.PendingMutation;

/* JADX INFO: loaded from: classes5.dex */
public final class SystemModelsProviderFactory {
    private static final String SYSTEM_MODELS_VERSION = "2f378292-cb5f-480e-b71d-cf441fd2e8be";

    private SystemModelsProviderFactory() {
    }

    @NonNull
    public static ModelProvider create() {
        return SimpleModelProvider.instance(SYSTEM_MODELS_VERSION, (Class<? extends Model>[]) new Class[]{PendingMutation.PersistentRecord.class, LastSyncMetadata.class, PersistentModelVersion.class, ModelMetadata.class});
    }
}
