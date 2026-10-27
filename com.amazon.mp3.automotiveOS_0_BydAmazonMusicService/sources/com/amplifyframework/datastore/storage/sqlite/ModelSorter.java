package com.amplifyframework.datastore.storage.sqlite;

import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.ObserveQueryOptions;
import com.amplifyframework.core.model.query.QuerySortBy;
import com.amplifyframework.core.model.query.QuerySortOrder;
import com.amplifyframework.datastore.DataStoreException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ModelSorter<T extends Model> {
    public void sort(ObserveQueryOptions observeQueryOptions, List<T> list, Class<T> cls, Consumer<DataStoreException> consumer) {
        if (observeQueryOptions == null || observeQueryOptions.getSortBy() == null || observeQueryOptions.getSortBy().size() <= 0) {
            return;
        }
        Collections.sort(list, getComparator(observeQueryOptions.getSortBy(), cls, consumer));
    }

    private Comparator<T> getComparator(List<QuerySortBy> list, Class<T> cls, Consumer<DataStoreException> consumer) {
        QuerySortBy querySortBy = list.get(0);
        Comparator<T> modelComparator = new ModelComparator<>(querySortBy, cls, consumer);
        if (querySortBy.getSortOrder() == QuerySortOrder.DESCENDING) {
            modelComparator = modelComparator.reversed();
        }
        for (int i = 1; i < list.size(); i++) {
            QuerySortBy querySortBy2 = list.get(i);
            Comparator<T> comparatorThenComparing = modelComparator.thenComparing(new ModelComparator(querySortBy2, cls, consumer));
            if (querySortBy2.getSortOrder() == QuerySortOrder.DESCENDING) {
                comparatorThenComparing.reversed();
            }
        }
        return modelComparator;
    }
}
