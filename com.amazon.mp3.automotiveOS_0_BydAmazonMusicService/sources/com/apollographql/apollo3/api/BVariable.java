package com.apollographql.apollo3.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BooleanExpression.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo3/api/BVariable;", "Lcom/apollographql/apollo3/api/BTerm;", "", "toString", "", "hashCode", "", "other", "", "equals", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final /* data */ class BVariable extends BTerm {
    private final String name;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof BVariable) && Intrinsics.areEqual(this.name, ((BVariable) other).name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return "BVariable(name=" + this.name + ')';
    }

    public final String getName() {
        return this.name;
    }
}
