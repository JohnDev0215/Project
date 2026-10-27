package com.amplifyframework.core.model.query.predicate;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.query.predicate.QueryOperator;
import java.lang.Comparable;

/* JADX INFO: loaded from: classes5.dex */
public final class BetweenQueryOperator<T extends Comparable<T>> extends QueryOperator<T> {
    private final T end;
    private final T start;

    BetweenQueryOperator(T t, T t2) {
        super(QueryOperator.Type.BETWEEN);
        this.start = t;
        this.end = t2;
    }

    public T start() {
        return this.start;
    }

    public T end() {
        return this.end;
    }

    @Override // com.amplifyframework.core.model.query.predicate.Evaluable
    public boolean evaluate(T t) {
        return t.compareTo(this.start) >= 0 && t.compareTo(this.end) <= 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BetweenQueryOperator.class != obj.getClass()) {
            return false;
        }
        BetweenQueryOperator betweenQueryOperator = (BetweenQueryOperator) obj;
        return ObjectsCompat.equals(type(), betweenQueryOperator.type()) && ObjectsCompat.equals(start(), betweenQueryOperator.start()) && ObjectsCompat.equals(end(), betweenQueryOperator.end());
    }

    public int hashCode() {
        return ObjectsCompat.hash(type(), start(), end());
    }

    public String toString() {
        return "BetweenQueryOperator { type: " + type() + ", start: " + this.start + ", end: " + this.end + " }";
    }
}
