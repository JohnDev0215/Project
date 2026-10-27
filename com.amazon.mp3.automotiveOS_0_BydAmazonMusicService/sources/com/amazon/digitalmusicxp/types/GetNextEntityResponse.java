package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GetNextEntityResponse.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/amazon/digitalmusicxp/types/GetNextEntityResponse;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/types/EntityResponseTuple;", "naturalNext", "Lcom/amazon/digitalmusicxp/types/EntityResponseTuple;", "getNaturalNext", "()Lcom/amazon/digitalmusicxp/types/EntityResponseTuple;", "userNext", "getUserNext", "<init>", "(Lcom/amazon/digitalmusicxp/types/EntityResponseTuple;Lcom/amazon/digitalmusicxp/types/EntityResponseTuple;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class GetNextEntityResponse {
    private final EntityResponseTuple naturalNext;
    private final EntityResponseTuple userNext;

    public GetNextEntityResponse() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GetNextEntityResponse)) {
            return false;
        }
        GetNextEntityResponse getNextEntityResponse = (GetNextEntityResponse) other;
        return Intrinsics.areEqual(this.naturalNext, getNextEntityResponse.naturalNext) && Intrinsics.areEqual(this.userNext, getNextEntityResponse.userNext);
    }

    public int hashCode() {
        EntityResponseTuple entityResponseTuple = this.naturalNext;
        int iHashCode = (entityResponseTuple == null ? 0 : entityResponseTuple.hashCode()) * 31;
        EntityResponseTuple entityResponseTuple2 = this.userNext;
        return iHashCode + (entityResponseTuple2 != null ? entityResponseTuple2.hashCode() : 0);
    }

    public String toString() {
        return "GetNextEntityResponse(naturalNext=" + this.naturalNext + ", userNext=" + this.userNext + ')';
    }

    public GetNextEntityResponse(EntityResponseTuple entityResponseTuple, EntityResponseTuple entityResponseTuple2) {
        this.naturalNext = entityResponseTuple;
        this.userNext = entityResponseTuple2;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ GetNextEntityResponse(EntityResponseTuple entityResponseTuple, EntityResponseTuple entityResponseTuple2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : entityResponseTuple, (i & 2) != 0 ? null : entityResponseTuple2);
    }

    public final EntityResponseTuple getNaturalNext() {
        return this.naturalNext;
    }

    public final EntityResponseTuple getUserNext() {
        return this.userNext;
    }
}
