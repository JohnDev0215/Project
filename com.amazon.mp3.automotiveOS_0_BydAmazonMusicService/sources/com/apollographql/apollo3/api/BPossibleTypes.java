package com.apollographql.apollo3.api;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BooleanExpression.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/apollographql/apollo3/api/BPossibleTypes;", "Lcom/apollographql/apollo3/api/BTerm;", "", "toString", "", "hashCode", "", "other", "", "equals", "", "possibleTypes", "Ljava/util/Set;", "getPossibleTypes", "()Ljava/util/Set;", "<init>", "(Ljava/util/Set;)V", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final /* data */ class BPossibleTypes extends BTerm {
    private final Set<String> possibleTypes;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof BPossibleTypes) && Intrinsics.areEqual(this.possibleTypes, ((BPossibleTypes) other).possibleTypes);
    }

    public int hashCode() {
        return this.possibleTypes.hashCode();
    }

    public String toString() {
        return "BPossibleTypes(possibleTypes=" + this.possibleTypes + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BPossibleTypes(Set<String> possibleTypes) {
        super(null);
        Intrinsics.checkNotNullParameter(possibleTypes, "possibleTypes");
        this.possibleTypes = possibleTypes;
    }

    public final Set<String> getPossibleTypes() {
        return this.possibleTypes;
    }
}
