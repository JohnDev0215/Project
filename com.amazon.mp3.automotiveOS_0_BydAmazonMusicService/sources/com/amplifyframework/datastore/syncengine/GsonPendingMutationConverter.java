package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.syncengine.PendingMutation;
import com.amplifyframework.util.GsonFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/* JADX INFO: loaded from: classes5.dex */
final class GsonPendingMutationConverter implements PendingMutation.Converter {
    private final Gson gson = GsonFactory.instance().newBuilder().registerTypeAdapter(TimeBasedUuid.class, new TimeBasedUuidTypeAdapter()).create();

    GsonPendingMutationConverter() {
    }

    @Override // com.amplifyframework.datastore.syncengine.PendingMutation.Converter
    @NonNull
    public <T extends Model> PendingMutation.PersistentRecord toRecord(@NonNull PendingMutation<T> pendingMutation) {
        return PendingMutation.PersistentRecord.builder().containedModelId(pendingMutation.getMutatedItem().getPrimaryKeyString()).containedModelClassName(pendingMutation.getMutatedItem().getClass().getName()).serializedMutationData(this.gson.toJson(pendingMutation)).mutationId(pendingMutation.getMutationId()).build();
    }

    @Override // com.amplifyframework.datastore.syncengine.PendingMutation.Converter
    @NonNull
    public <T extends Model> PendingMutation<T> fromRecord(@NonNull PendingMutation.PersistentRecord persistentRecord) throws DataStoreException {
        try {
            return (PendingMutation) this.gson.fromJson(persistentRecord.getSerializedMutationData(), TypeToken.getParameterized(PendingMutation.class, Class.forName(persistentRecord.getContainedModelClassName())).getType());
        } catch (ClassNotFoundException e) {
            throw new DataStoreException("Could not find a class with the name " + persistentRecord.getContainedModelClassName(), e, "Verify that you have built this model into your project.");
        }
    }
}
