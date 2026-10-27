package com.amazon.digitalmusicxp.models;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.types.EntityReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueSequenceSlice.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u001d\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u000f¢\u0006\u0004\b*\u0010+Ju\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000fHÆ\u0001J\t\u0010\u0013\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u001a\u0010\u0019R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0017\u001a\u0004\b\u001b\u0010\u0019R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0017\u001a\u0004\b\u001c\u0010\u0019R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\u001d\u001a\u0004\b \u0010\u001fR\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010!\u001a\u0004\b\u000b\u0010\"R\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0006¢\u0006\f\n\u0004\b\u000e\u0010#\u001a\u0004\b$\u0010%R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010\u0011\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0011\u0010&\u001a\u0004\b)\u0010(¨\u0006,"}, d2 = {"Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;", "", "", "queueId", "customerId", "id", "sequenceName", "", "sequenceVersion", "sliceOrdinal", "", "isLastSlice", "", "Lcom/amazon/digitalmusicxp/types/EntityReference;", "entityReferences", "", "createdAt", "updatedAt", "copy", "toString", "hashCode", "other", "equals", "Ljava/lang/String;", "getQueueId", "()Ljava/lang/String;", "getCustomerId", "getId", "getSequenceName", "I", "getSequenceVersion", "()I", "getSliceOrdinal", "Z", "()Z", "Ljava/util/List;", "getEntityReferences", "()Ljava/util/List;", "J", "getCreatedAt", "()J", "getUpdatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIZLjava/util/List;JJ)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class QueueSequenceSlice {
    private final long createdAt;
    private final String customerId;
    private final List<EntityReference> entityReferences;
    private final String id;
    private final boolean isLastSlice;
    private final String queueId;
    private final String sequenceName;
    private final int sequenceVersion;
    private final int sliceOrdinal;
    private final long updatedAt;

    public final QueueSequenceSlice copy(String queueId, String customerId, String id, String sequenceName, int sequenceVersion, int sliceOrdinal, boolean isLastSlice, List<EntityReference> entityReferences, long createdAt, long updatedAt) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(sequenceName, "sequenceName");
        Intrinsics.checkNotNullParameter(entityReferences, "entityReferences");
        return new QueueSequenceSlice(queueId, customerId, id, sequenceName, sequenceVersion, sliceOrdinal, isLastSlice, entityReferences, createdAt, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueSequenceSlice)) {
            return false;
        }
        QueueSequenceSlice queueSequenceSlice = (QueueSequenceSlice) other;
        return Intrinsics.areEqual(this.queueId, queueSequenceSlice.queueId) && Intrinsics.areEqual(this.customerId, queueSequenceSlice.customerId) && Intrinsics.areEqual(this.id, queueSequenceSlice.id) && Intrinsics.areEqual(this.sequenceName, queueSequenceSlice.sequenceName) && this.sequenceVersion == queueSequenceSlice.sequenceVersion && this.sliceOrdinal == queueSequenceSlice.sliceOrdinal && this.isLastSlice == queueSequenceSlice.isLastSlice && Intrinsics.areEqual(this.entityReferences, queueSequenceSlice.entityReferences) && this.createdAt == queueSequenceSlice.createdAt && this.updatedAt == queueSequenceSlice.updatedAt;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v12, types: [int] */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v21 */
    public int hashCode() {
        int iHashCode = ((this.queueId.hashCode() * 31) + this.customerId.hashCode()) * 31;
        String str = this.id;
        int iHashCode2 = (((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.sequenceName.hashCode()) * 31) + Integer.hashCode(this.sequenceVersion)) * 31) + Integer.hashCode(this.sliceOrdinal)) * 31;
        boolean z = this.isLastSlice;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return ((((((iHashCode2 + r1) * 31) + this.entityReferences.hashCode()) * 31) + Long.hashCode(this.createdAt)) * 31) + Long.hashCode(this.updatedAt);
    }

    public String toString() {
        return "QueueSequenceSlice(queueId=" + this.queueId + ", customerId=" + this.customerId + ", id=" + this.id + ", sequenceName=" + this.sequenceName + ", sequenceVersion=" + this.sequenceVersion + ", sliceOrdinal=" + this.sliceOrdinal + ", isLastSlice=" + this.isLastSlice + ", entityReferences=" + this.entityReferences + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ')';
    }

    public QueueSequenceSlice(String queueId, String customerId, String str, String sequenceName, int i, int i2, boolean z, List<EntityReference> entityReferences, long j, long j2) {
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(sequenceName, "sequenceName");
        Intrinsics.checkNotNullParameter(entityReferences, "entityReferences");
        this.queueId = queueId;
        this.customerId = customerId;
        this.id = str;
        this.sequenceName = sequenceName;
        this.sequenceVersion = i;
        this.sliceOrdinal = i2;
        this.isLastSlice = z;
        this.entityReferences = entityReferences;
        this.createdAt = j;
        this.updatedAt = j2;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getQueueId() {
        return this.queueId;
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final String getId() {
        return this.id;
    }

    public final String getSequenceName() {
        return this.sequenceName;
    }

    public final int getSequenceVersion() {
        return this.sequenceVersion;
    }

    public final int getSliceOrdinal() {
        return this.sliceOrdinal;
    }

    /* JADX INFO: renamed from: isLastSlice, reason: from getter */
    public final boolean getIsLastSlice() {
        return this.isLastSlice;
    }

    public final List<EntityReference> getEntityReferences() {
        return this.entityReferences;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final long getUpdatedAt() {
        return this.updatedAt;
    }
}
