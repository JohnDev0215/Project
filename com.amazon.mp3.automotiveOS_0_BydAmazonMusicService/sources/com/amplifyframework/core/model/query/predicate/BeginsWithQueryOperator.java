package com.amplifyframework.core.model.query.predicate;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.query.predicate.QueryOperator;

/* JADX INFO: loaded from: classes5.dex */
public final class BeginsWithQueryOperator extends QueryOperator<String> {
    private final String value;

    BeginsWithQueryOperator(String str) {
        super(QueryOperator.Type.BEGINS_WITH);
        this.value = str;
    }

    public Object value() {
        return this.value;
    }

    @Override // com.amplifyframework.core.model.query.predicate.Evaluable
    public boolean evaluate(String str) {
        return str.indexOf(this.value) == 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BeginsWithQueryOperator.class != obj.getClass()) {
            return false;
        }
        BeginsWithQueryOperator beginsWithQueryOperator = (BeginsWithQueryOperator) obj;
        return ObjectsCompat.equals(type(), beginsWithQueryOperator.type()) && ObjectsCompat.equals(value(), beginsWithQueryOperator.value());
    }

    public int hashCode() {
        return ObjectsCompat.hash(type(), value());
    }

    public String toString() {
        return "BeginsWithQueryOperator { type: " + type() + ", value: " + value() + " }";
    }
}
