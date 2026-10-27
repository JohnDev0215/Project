package com.amplifyframework.datastore;

import androidx.annotation.NonNull;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.category.Category;
import com.amplifyframework.core.category.CategoryType;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.ObserveQueryOptions;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import java.io.Serializable;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class DataStoreCategory extends Category<DataStorePlugin<?>> implements DataStoreCategoryBehavior {
    @Override // com.amplifyframework.core.category.CategoryTypeable
    @NonNull
    public CategoryType getCategoryType() {
        return CategoryType.DATASTORE;
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void save(@NonNull T t, @NonNull Consumer<DataStoreItemChange<T>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        getSelectedPlugin().save(t, consumer, consumer2);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void save(@NonNull T t, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<DataStoreItemChange<T>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        getSelectedPlugin().save(t, queryPredicate, consumer, consumer2);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void delete(@NonNull T t, @NonNull Consumer<DataStoreItemChange<T>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        getSelectedPlugin().delete(t, consumer, consumer2);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void delete(@NonNull T t, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<DataStoreItemChange<T>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        getSelectedPlugin().delete(t, queryPredicate, consumer, consumer2);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void delete(@NonNull Class<T> cls, @NonNull QueryPredicate queryPredicate, @NonNull Action action, @NonNull Consumer<DataStoreException> consumer) {
        getSelectedPlugin().delete(cls, queryPredicate, action, consumer);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void query(@NonNull Class<T> cls, @NonNull Consumer<Iterator<T>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        getSelectedPlugin().query(cls, consumer, consumer2);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void query(@NonNull Class<T> cls, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<Iterator<T>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        getSelectedPlugin().query(cls, queryPredicate, consumer, consumer2);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void query(@NonNull Class<T> cls, @NonNull QueryOptions queryOptions, @NonNull Consumer<Iterator<T>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        getSelectedPlugin().query(cls, queryOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public void observe(@NonNull Consumer<Cancelable> consumer, @NonNull Consumer<DataStoreItemChange<? extends Model>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action) {
        getSelectedPlugin().observe(consumer, consumer2, consumer3, action);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void observeQuery(@NonNull Class<T> cls, @NonNull ObserveQueryOptions observeQueryOptions, @NonNull Consumer<Cancelable> consumer, @NonNull Consumer<DataStoreQuerySnapshot<T>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action) {
        getSelectedPlugin().observeQuery(cls, observeQueryOptions, consumer, consumer2, consumer3, action);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void observe(@NonNull Class<T> cls, @NonNull Consumer<Cancelable> consumer, @NonNull Consumer<DataStoreItemChange<T>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action) {
        getSelectedPlugin().observe(cls, consumer, consumer2, consumer3, action);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void observe(@NonNull Class<T> cls, @NonNull Serializable serializable, @NonNull Consumer<Cancelable> consumer, @NonNull Consumer<DataStoreItemChange<T>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action) {
        getSelectedPlugin().observe(cls, serializable, consumer, consumer2, consumer3, action);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void observe(@NonNull Class<T> cls, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<Cancelable> consumer, @NonNull Consumer<DataStoreItemChange<T>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action) {
        getSelectedPlugin().observe(cls, queryPredicate, consumer, consumer2, consumer3, action);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public void start(@NonNull Action action, @NonNull Consumer<DataStoreException> consumer) {
        getSelectedPlugin().start(action, consumer);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public void stop(@NonNull Action action, @NonNull Consumer<DataStoreException> consumer) {
        getSelectedPlugin().stop(action, consumer);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public void clear(@NonNull Action action, @NonNull Consumer<DataStoreException> consumer) {
        getSelectedPlugin().clear(action, consumer);
    }
}
