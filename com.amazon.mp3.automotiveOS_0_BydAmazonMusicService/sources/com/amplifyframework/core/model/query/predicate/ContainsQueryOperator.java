package com.amplifyframework.core.model.query.predicate;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.query.predicate.QueryOperator;

/* JADX INFO: loaded from: classes5.dex */
public final class ContainsQueryOperator extends QueryOperator<String> {
    private final String value;

    ContainsQueryOperator(String str) {
        super(QueryOperator.Type.CONTAINS);
        this.value = str;
    }

    public Object value() {
        return this.value;
    }

    @Override // com.amplifyframework.core.model.query.predicate.Evaluable
    public boolean evaluate(String str) {
        return str.contains(this.value);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ContainsQueryOperator.class != obj.getClass()) {
            return false;
        }
        ContainsQueryOperator containsQueryOperator = (ContainsQueryOperator) obj;
        return ObjectsCompat.equals(type(), containsQueryOperator.type()) && ObjectsCompat.equals(value(), containsQueryOperator.value());
    }

    public int hashCode() {
        return ObjectsCompat.hash(type(), value());
    }

    public String toString() {
        return "ContainsQueryOperator { type: " + type() + ", value: " + value() + " }";
    }
}
