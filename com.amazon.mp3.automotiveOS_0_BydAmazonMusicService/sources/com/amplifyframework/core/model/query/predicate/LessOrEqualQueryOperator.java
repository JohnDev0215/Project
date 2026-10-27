package com.amplifyframework.core.model.query.predicate;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.query.predicate.QueryOperator;
import java.lang.Comparable;

/* JADX INFO: loaded from: classes5.dex */
public final class LessOrEqualQueryOperator<T extends Comparable<T>> extends QueryOperator<T> {
    private final T value;

    LessOrEqualQueryOperator(T t) {
        super(QueryOperator.Type.LESS_OR_EQUAL);
        this.value = t;
    }

    public T value() {
        return this.value;
    }

    @Override // com.amplifyframework.core.model.query.predicate.Evaluable
    public boolean evaluate(T t) {
        return t.compareTo(this.value) <= 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LessOrEqualQueryOperator.class != obj.getClass()) {
            return false;
        }
        LessOrEqualQueryOperator lessOrEqualQueryOperator = (LessOrEqualQueryOperator) obj;
        return ObjectsCompat.equals(type(), lessOrEqualQueryOperator.type()) && ObjectsCompat.equals(value(), lessOrEqualQueryOperator.value());
    }

    public int hashCode() {
        return ObjectsCompat.hash(type(), value());
    }

    public String toString() {
        return "LessOrEqualQueryOperator { type: " + type() + ", value: " + value() + " }";
    }
}
