package com.amazon.digitalmusicxp.inputs;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StartAtInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/StartAtInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "queueEntityIdType", "Ljava/lang/String;", "getQueueEntityIdType", "()Ljava/lang/String;", "queueEntityId", "getQueueEntityId", "offsetInMs", "I", "getOffsetInMs", "()I", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class StartAtInput {
    private final int offsetInMs;
    private final String queueEntityId;
    private final String queueEntityIdType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StartAtInput)) {
            return false;
        }
        StartAtInput startAtInput = (StartAtInput) other;
        return Intrinsics.areEqual(this.queueEntityIdType, startAtInput.queueEntityIdType) && Intrinsics.areEqual(this.queueEntityId, startAtInput.queueEntityId) && this.offsetInMs == startAtInput.offsetInMs;
    }

    public int hashCode() {
        return (((this.queueEntityIdType.hashCode() * 31) + this.queueEntityId.hashCode()) * 31) + Integer.hashCode(this.offsetInMs);
    }

    public String toString() {
        return "StartAtInput(queueEntityIdType=" + this.queueEntityIdType + ", queueEntityId=" + this.queueEntityId + ", offsetInMs=" + this.offsetInMs + ')';
    }

    public final int getOffsetInMs() {
        return this.offsetInMs;
    }
}
