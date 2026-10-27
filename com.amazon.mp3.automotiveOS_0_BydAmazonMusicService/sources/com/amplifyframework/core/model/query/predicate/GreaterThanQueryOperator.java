package com.amplifyframework.core.model.query.predicate;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.query.predicate.QueryOperator;
import java.lang.Comparable;

/* JADX INFO: loaded from: classes5.dex */
public final class GreaterThanQueryOperator<T extends Comparable<T>> extends QueryOperator<T> {
    private final T value;

    GreaterThanQueryOperator(T t) {
        super(QueryOperator.Type.GREATER_THAN);
        this.value = t;
    }

    public T value() {
        return this.value;
    }

    @Override // com.amplifyframework.core.model.query.predicate.Evaluable
    public boolean evaluate(T t) {
        return t.compareTo(this.value) > 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GreaterThanQueryOperator.class != obj.getClass()) {
            return false;
        }
        GreaterThanQueryOperator greaterThanQueryOperator = (GreaterThanQueryOperator) obj;
        return ObjectsCompat.equals(type(), greaterThanQueryOperator.type()) && ObjectsCompat.equals(value(), greaterThanQueryOperator.value());
    }

    public int hashCode() {
        return ObjectsCompat.hash(type(), value());
    }

    public String toString() {
        return "GreaterThanQueryOperator { type: " + type() + ", value: " + value() + " }";
    }
}
