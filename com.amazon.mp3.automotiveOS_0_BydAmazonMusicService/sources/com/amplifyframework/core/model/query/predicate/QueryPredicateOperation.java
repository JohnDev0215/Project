package com.amplifyframework.core.model.query.predicate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.query.predicate.QueryPredicateGroup;
import com.amplifyframework.util.FieldFinder;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class QueryPredicateOperation<T> implements QueryPredicate {
    private final String field;
    private final String modelName;
    private final QueryOperator<T> operator;

    QueryPredicateOperation(@NonNull String str, @NonNull QueryOperator<T> queryOperator) {
        this(null, str, queryOperator);
    }

    QueryPredicateOperation(@Nullable String str, @NonNull String str2, @NonNull QueryOperator<T> queryOperator) {
        this.modelName = str;
        Objects.requireNonNull(str2);
        this.field = str2;
        Objects.requireNonNull(queryOperator);
        this.operator = queryOperator;
    }

    @Nullable
    public String modelName() {
        return this.modelName;
    }

    @NonNull
    public String field() {
        return this.field;
    }

    @NonNull
    public QueryOperator<T> operator() {
        return this.operator;
    }

    @Override // com.amplifyframework.core.model.query.predicate.QueryPredicate
    @NonNull
    public QueryPredicateGroup and(QueryPredicate queryPredicate) {
        return new QueryPredicateGroup(QueryPredicateGroup.Type.AND, Arrays.asList(this, queryPredicate));
    }

    @Override // com.amplifyframework.core.model.query.predicate.QueryPredicate
    @NonNull
    public QueryPredicateGroup or(QueryPredicate queryPredicate) {
        return new QueryPredicateGroup(QueryPredicateGroup.Type.OR, Arrays.asList(this, queryPredicate));
    }

    @NonNull
    public static QueryPredicateGroup not(QueryPredicateOperation<?> queryPredicateOperation) {
        return new QueryPredicateGroup(QueryPredicateGroup.Type.NOT, Collections.singletonList(queryPredicateOperation));
    }

    @Override // com.amplifyframework.core.model.query.predicate.Evaluable
    public boolean evaluate(@NonNull Object obj) throws IllegalArgumentException {
        try {
            return this.operator.evaluate((T) FieldFinder.extractFieldValue(obj, this.field));
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(this.field + " field inside provided object cannot be evaluated by the operator type: " + this.operator.type().name(), e);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueryPredicateOperation.class != obj.getClass()) {
            return false;
        }
        QueryPredicateOperation queryPredicateOperation = (QueryPredicateOperation) obj;
        return ObjectsCompat.equals(modelName(), queryPredicateOperation.modelName()) && ObjectsCompat.equals(field(), queryPredicateOperation.field()) && ObjectsCompat.equals(operator(), queryPredicateOperation.operator());
    }

    public int hashCode() {
        return ObjectsCompat.hash(modelName(), field(), operator());
    }

    public String toString() {
        return "QueryPredicateOperation { model: " + modelName() + ", field: " + field() + ", operator: " + operator() + " }";
    }
}
