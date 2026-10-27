package com.amplifyframework.datastore;

import androidx.annotation.NonNull;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.ObserveQueryOptions;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import java.io.Serializable;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public interface DataStoreCategoryBehavior {
    void clear(@NonNull Action action, @NonNull Consumer<DataStoreException> consumer);

    <T extends Model> void delete(@NonNull T t, @NonNull Consumer<DataStoreItemChange<T>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    <T extends Model> void delete(@NonNull T t, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<DataStoreItemChange<T>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    <T extends Model> void delete(@NonNull Class<T> cls, @NonNull QueryPredicate queryPredicate, @NonNull Action action, @NonNull Consumer<DataStoreException> consumer);

    void observe(@NonNull Consumer<Cancelable> consumer, @NonNull Consumer<DataStoreItemChange<? extends Model>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action);

    <T extends Model> void observe(@NonNull Class<T> cls, @NonNull Consumer<Cancelable> consumer, @NonNull Consumer<DataStoreItemChange<T>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action);

    <T extends Model> void observe(@NonNull Class<T> cls, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<Cancelable> consumer, @NonNull Consumer<DataStoreItemChange<T>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action);

    <T extends Model> void observe(@NonNull Class<T> cls, @NonNull Serializable serializable, @NonNull Consumer<Cancelable> consumer, @NonNull Consumer<DataStoreItemChange<T>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action);

    <T extends Model> void observeQuery(@NonNull Class<T> cls, @NonNull ObserveQueryOptions observeQueryOptions, @NonNull Consumer<Cancelable> consumer, @NonNull Consumer<DataStoreQuerySnapshot<T>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action);

    <T extends Model> void query(@NonNull Class<T> cls, @NonNull Consumer<Iterator<T>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    <T extends Model> void query(@NonNull Class<T> cls, @NonNull QueryOptions queryOptions, @NonNull Consumer<Iterator<T>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    <T extends Model> void query(@NonNull Class<T> cls, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<Iterator<T>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    <T extends Model> void save(@NonNull T t, @NonNull Consumer<DataStoreItemChange<T>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    <T extends Model> void save(@NonNull T t, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<DataStoreItemChange<T>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    void start(@NonNull Action action, @NonNull Consumer<DataStoreException> consumer);

    void stop(@NonNull Action action, @NonNull Consumer<DataStoreException> consumer);
}
