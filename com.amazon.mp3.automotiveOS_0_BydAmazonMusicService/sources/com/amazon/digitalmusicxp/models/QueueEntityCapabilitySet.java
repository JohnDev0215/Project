package com.amazon.digitalmusicxp.models;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.types.QueueEntityCapability;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueEntityCapabilitySet.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0017\u001a\u00020\u0012¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0016¨\u0006\u001b"}, d2 = {"Lcom/amazon/digitalmusicxp/models/QueueEntityCapabilitySet;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "capabilities", "Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "getCapabilities", "()Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;", "", "createdAt", "J", "getCreatedAt", "()J", "updatedAt", "getUpdatedAt", "<init>", "(Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/QueueEntityCapability;JJ)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class QueueEntityCapabilitySet {
    private final QueueEntityCapability capabilities;
    private final long createdAt;
    private final String id;
    private final long updatedAt;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueEntityCapabilitySet)) {
            return false;
        }
        QueueEntityCapabilitySet queueEntityCapabilitySet = (QueueEntityCapabilitySet) other;
        return Intrinsics.areEqual(this.id, queueEntityCapabilitySet.id) && Intrinsics.areEqual(this.capabilities, queueEntityCapabilitySet.capabilities) && this.createdAt == queueEntityCapabilitySet.createdAt && this.updatedAt == queueEntityCapabilitySet.updatedAt;
    }

    public int hashCode() {
        return (((((this.id.hashCode() * 31) + this.capabilities.hashCode()) * 31) + Long.hashCode(this.createdAt)) * 31) + Long.hashCode(this.updatedAt);
    }

    public String toString() {
        return "QueueEntityCapabilitySet(id=" + this.id + ", capabilities=" + this.capabilities + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ')';
    }

    public QueueEntityCapabilitySet(String id, QueueEntityCapability capabilities, long j, long j2) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(capabilities, "capabilities");
        this.id = id;
        this.capabilities = capabilities;
        this.createdAt = j;
        this.updatedAt = j2;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getId() {
        return this.id;
    }

    public final QueueEntityCapability getCapabilities() {
        return this.capabilities;
    }
}
