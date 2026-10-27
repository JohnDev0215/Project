package com.amplifyframework.rx;

import androidx.annotation.NonNull;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.ObserveQueryOptions;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.datastore.DataStoreItemChange;
import com.amplifyframework.datastore.DataStoreQuerySnapshot;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes5.dex */
public interface RxDataStoreCategoryBehavior {
    Completable clear();

    @NonNull
    <T extends Model> Completable delete(@NonNull T t);

    @NonNull
    <T extends Model> Completable delete(@NonNull T t, @NonNull QueryPredicate queryPredicate);

    @NonNull
    <T extends Model> Completable delete(@NonNull Class<T> cls, @NonNull QueryPredicate queryPredicate);

    @NonNull
    Observable<DataStoreItemChange<? extends Model>> observe();

    @NonNull
    <T extends Model> Observable<DataStoreItemChange<T>> observe(@NonNull Class<T> cls);

    @NonNull
    <T extends Model> Observable<DataStoreItemChange<T>> observe(@NonNull Class<T> cls, @NonNull QueryPredicate queryPredicate);

    @NonNull
    <T extends Model> Observable<DataStoreItemChange<T>> observe(@NonNull Class<T> cls, @NonNull String str);

    @NonNull
    <T extends Model> Observable<DataStoreQuerySnapshot<T>> observeQuery(@NonNull Class<T> cls, ObserveQueryOptions observeQueryOptions);

    @NonNull
    <T extends Model> Observable<T> query(@NonNull Class<T> cls);

    @NonNull
    <T extends Model> Observable<T> query(@NonNull Class<T> cls, @NonNull QueryOptions queryOptions);

    @NonNull
    <T extends Model> Observable<T> query(@NonNull Class<T> cls, @NonNull QueryPredicate queryPredicate);

    @NonNull
    <T extends Model> Completable save(@NonNull T t);

    @NonNull
    <T extends Model> Completable save(@NonNull T t, @NonNull QueryPredicate queryPredicate);

    Completable start();

    Completable stop();
}
