package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import com.amplifyframework.datastore.DataStoreConfigurationProvider;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.DataStoreSyncExpression;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Function;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
final class QueryPredicateProvider {
    private final DataStoreConfigurationProvider dataStoreConfigurationProvider;
    private final Map<String, QueryPredicate> predicateMap = new HashMap();

    QueryPredicateProvider(DataStoreConfigurationProvider dataStoreConfigurationProvider) {
        this.dataStoreConfigurationProvider = dataStoreConfigurationProvider;
    }

    public void resolvePredicates() throws DataStoreException {
        Map<String, DataStoreSyncExpression> syncExpressions = this.dataStoreConfigurationProvider.getConfiguration().getSyncExpressions();
        this.predicateMap.clear();
        this.predicateMap.putAll((Map) Observable.fromIterable(syncExpressions.entrySet()).map(new Function() { // from class: com.amplifyframework.datastore.syncengine.QueryPredicateProvider$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return QueryPredicateProvider.lambda$resolvePredicates$0((Map.Entry) obj);
            }
        }).toMap(new Function() { // from class: com.amplifyframework.datastore.syncengine.QueryPredicateProvider$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return QueryPredicateProvider.lambda$resolvePredicates$1((Pair) obj);
            }
        }, new Function() { // from class: com.amplifyframework.datastore.syncengine.QueryPredicateProvider$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return QueryPredicateProvider.lambda$resolvePredicates$2((Pair) obj);
            }
        }).blockingGet());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Pair lambda$resolvePredicates$0(Map.Entry entry) throws Throwable {
        return Pair.create(entry.getKey(), ((DataStoreSyncExpression) entry.getValue()).resolvePredicate());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String lambda$resolvePredicates$1(Pair pair) throws Throwable {
        return (String) pair.first;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ QueryPredicate lambda$resolvePredicates$2(Pair pair) throws Throwable {
        return (QueryPredicate) pair.second;
    }

    @NonNull
    public QueryPredicate getPredicate(@NonNull String str) {
        Map<String, QueryPredicate> map = this.predicateMap;
        Objects.requireNonNull(str);
        QueryPredicate queryPredicate = map.get(str);
        return queryPredicate == null ? QueryPredicates.all() : queryPredicate;
    }
}
