package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.TrackReferenceIdTypeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackReferenceInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/TrackReferenceInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Lcom/amazon/digitalmusicxp/enums/TrackReferenceIdTypeEnum;", "idType", "Lcom/amazon/digitalmusicxp/enums/TrackReferenceIdTypeEnum;", "getIdType", "()Lcom/amazon/digitalmusicxp/enums/TrackReferenceIdTypeEnum;", "<init>", "(Ljava/lang/String;Lcom/amazon/digitalmusicxp/enums/TrackReferenceIdTypeEnum;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class TrackReferenceInput {
    private final String id;
    private final TrackReferenceIdTypeEnum idType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrackReferenceInput)) {
            return false;
        }
        TrackReferenceInput trackReferenceInput = (TrackReferenceInput) other;
        return Intrinsics.areEqual(this.id, trackReferenceInput.id) && this.idType == trackReferenceInput.idType;
    }

    public int hashCode() {
        return (this.id.hashCode() * 31) + this.idType.hashCode();
    }

    public String toString() {
        return "TrackReferenceInput(id=" + this.id + ", idType=" + this.idType + ')';
    }

    public TrackReferenceInput(String id, TrackReferenceIdTypeEnum idType) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(idType, "idType");
        this.id = id;
        this.idType = idType;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getId() {
        return this.id;
    }

    public final TrackReferenceIdTypeEnum getIdType() {
        return this.idType;
    }
}
