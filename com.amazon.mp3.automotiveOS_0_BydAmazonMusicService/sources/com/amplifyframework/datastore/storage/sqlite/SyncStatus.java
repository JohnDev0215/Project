package com.amplifyframework.datastore.storage.sqlite;

import androidx.annotation.NonNull;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.datastore.DataStoreConfiguration;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.syncengine.LastSyncMetadata;
import com.amplifyframework.util.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class SyncStatus {
    private final DataStoreConfiguration dataStoreConfiguration;
    private final SqlQueryProcessor sqlQueryProcessor;

    public SyncStatus(SqlQueryProcessor sqlQueryProcessor, DataStoreConfiguration dataStoreConfiguration) {
        this.sqlQueryProcessor = sqlQueryProcessor;
        this.dataStoreConfiguration = dataStoreConfiguration;
    }

    public boolean get(@NonNull String str, @NonNull Consumer<DataStoreException> consumer) {
        try {
            LastSyncMetadata lastSyncMetaData = getLastSyncMetaData(str, consumer);
            if (lastSyncMetaData.getLastSyncTime() != null) {
                return Time.now() - lastSyncMetaData.getLastSyncTime().longValue() < TimeUnit.MINUTES.toMillis(this.dataStoreConfiguration.getSyncIntervalInMinutes().longValue());
            }
            return false;
        } catch (DataStoreException e) {
            consumer.accept(e);
            return false;
        }
    }

    private LastSyncMetadata getLastSyncMetaData(@NonNull String str, @NonNull Consumer<DataStoreException> consumer) throws DataStoreException {
        return extractSingleResult(str, this.sqlQueryProcessor.queryOfflineData(LastSyncMetadata.class, Where.matches(QueryField.field("modelClassName").eq(str)), consumer));
    }

    private LastSyncMetadata extractSingleResult(String str, List<LastSyncMetadata> list) throws DataStoreException {
        if (list.size() > 1) {
            throw new DataStoreException("Wanted 1 sync time for model = " + str + ", but found " + list.size() + ".", "This is likely a bug. Please report it to AWS.");
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        return LastSyncMetadata.neverSynced(str);
    }
}
