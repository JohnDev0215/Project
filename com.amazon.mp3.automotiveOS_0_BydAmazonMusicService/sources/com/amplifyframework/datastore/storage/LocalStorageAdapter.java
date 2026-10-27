package com.amplifyframework.datastore.storage;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.query.ObserveQueryOptions;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.datastore.DataStoreConfiguration;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.DataStoreQuerySnapshot;
import com.amplifyframework.datastore.storage.StorageItemChange;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public interface LocalStorageAdapter {
    void clear(@NonNull Action action, @NonNull Consumer<DataStoreException> consumer);

    <T extends Model> void delete(@NonNull T t, @NonNull StorageItemChange.Initiator initiator, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<StorageItemChange<T>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    <T extends Model> void delete(@NonNull Class<T> cls, @NonNull StorageItemChange.Initiator initiator, @NonNull QueryPredicate queryPredicate, @NonNull Action action, @NonNull Consumer<DataStoreException> consumer);

    void initialize(@NonNull Context context, @NonNull Consumer<List<ModelSchema>> consumer, @NonNull Consumer<DataStoreException> consumer2, @NonNull DataStoreConfiguration dataStoreConfiguration);

    @NonNull
    Cancelable observe(@NonNull Consumer<StorageItemChange<? extends Model>> consumer, @NonNull Consumer<DataStoreException> consumer2, @NonNull Action action);

    <T extends Model> void observeQuery(@NonNull Class<T> cls, @NonNull ObserveQueryOptions observeQueryOptions, @NonNull Consumer<Cancelable> consumer, @NonNull Consumer<DataStoreQuerySnapshot<T>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action);

    <T extends Model> void query(@NonNull Class<T> cls, @NonNull QueryOptions queryOptions, @NonNull Consumer<Iterator<T>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    void query(@NonNull String str, @NonNull QueryOptions queryOptions, @NonNull Consumer<Iterator<? extends Model>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    <T extends Model> void save(@NonNull T t, @NonNull StorageItemChange.Initiator initiator, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<StorageItemChange<T>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    void terminate() throws DataStoreException;
}
