package com.amplifyframework.core.model.query;

import androidx.annotation.NonNull;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelIdentifier;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.PrimaryKey;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class Where {
    private Where() {
    }

    public static QueryOptions matchesAll() {
        return new QueryOptions();
    }

    public static QueryOptions matches(@NonNull QueryPredicate queryPredicate) {
        Objects.requireNonNull(queryPredicate);
        return new QueryOptions(queryPredicate, null, null);
    }

    @Deprecated
    public static QueryOptions id(@NonNull String str) {
        QueryField queryFieldField = QueryField.field(PrimaryKey.fieldName());
        Objects.requireNonNull(str);
        return matches(queryFieldField.eq(str)).paginated(Page.firstResult());
    }

    public static <T extends Model> QueryOptions identifier(@NonNull Class<T> cls, @NonNull Serializable serializable) throws AmplifyException {
        QueryOptions queryOptionsMatches;
        List<String> primaryIndexFields = ModelSchema.fromModelClass(cls).getPrimaryIndexFields();
        ListIterator<String> listIterator = primaryIndexFields.listIterator();
        QueryField queryFieldField = QueryField.field(cls.getSimpleName(), listIterator.next());
        if (primaryIndexFields.size() == 1 && !(serializable instanceof ModelIdentifier)) {
            String string = serializable.toString();
            Objects.requireNonNull(string);
            queryOptionsMatches = matches(queryFieldField.eq(string));
        } else {
            ModelIdentifier modelIdentifier = (ModelIdentifier) serializable;
            ListIterator<? extends Serializable> listIterator2 = modelIdentifier.sortedKeys().listIterator();
            Serializable serializableKey = modelIdentifier.key();
            Objects.requireNonNull(serializableKey);
            QueryOptions queryOptionsMatches2 = matches(queryFieldField.eq(serializableKey));
            while (listIterator2.hasNext()) {
                QueryField queryFieldField2 = QueryField.field(cls.getSimpleName(), listIterator.next());
                Serializable next = listIterator2.next();
                Objects.requireNonNull(next);
                queryOptionsMatches2.matches(queryFieldField2.eq(next));
            }
            queryOptionsMatches = queryOptionsMatches2;
        }
        return queryOptionsMatches.paginated(Page.firstResult());
    }

    public static QueryOptions paginated(@NonNull QueryPaginationInput queryPaginationInput) {
        Objects.requireNonNull(queryPaginationInput);
        return new QueryOptions(null, queryPaginationInput, null);
    }

    public static QueryOptions sorted(@NonNull QuerySortBy... querySortByArr) {
        Objects.requireNonNull(querySortByArr);
        return new QueryOptions(null, null, Arrays.asList(querySortByArr));
    }

    public static QueryOptions matchesAndSorts(QueryPredicate queryPredicate, List<QuerySortBy> list) {
        return new QueryOptions(queryPredicate, null, list);
    }
}
