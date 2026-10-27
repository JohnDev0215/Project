package com.amazon.digitalmusicxp.models;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.types.GenericQueueEntity;
import com.amazon.digitalmusicxp.types.QueueCapability;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayQueue.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000e\u0012\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001f\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001f\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0011\u001a\u0004\b\u0016\u0010\u0013R\u0017\u0010\u0018\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/amazon/digitalmusicxp/models/PlayQueue;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/models/Queue;", "queueMetaData", "Lcom/amazon/digitalmusicxp/models/Queue;", "getQueueMetaData", "()Lcom/amazon/digitalmusicxp/models/Queue;", "", "Lcom/amazon/digitalmusicxp/types/GenericQueueEntity;", "playableEntities", "Ljava/util/List;", "getPlayableEntities", "()Ljava/util/List;", "Lcom/amazon/digitalmusicxp/models/QueueSequenceSlice;", "queueSlices", "getQueueSlices", "Lcom/amazon/digitalmusicxp/types/QueueCapability;", "queueCapability", "Lcom/amazon/digitalmusicxp/types/QueueCapability;", "getQueueCapability", "()Lcom/amazon/digitalmusicxp/types/QueueCapability;", "<init>", "(Lcom/amazon/digitalmusicxp/models/Queue;Ljava/util/List;Ljava/util/List;Lcom/amazon/digitalmusicxp/types/QueueCapability;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class PlayQueue {
    private final List<GenericQueueEntity> playableEntities;
    private final QueueCapability queueCapability;
    private final Queue queueMetaData;
    private final List<QueueSequenceSlice> queueSlices;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayQueue)) {
            return false;
        }
        PlayQueue playQueue = (PlayQueue) other;
        return Intrinsics.areEqual(this.queueMetaData, playQueue.queueMetaData) && Intrinsics.areEqual(this.playableEntities, playQueue.playableEntities) && Intrinsics.areEqual(this.queueSlices, playQueue.queueSlices) && Intrinsics.areEqual(this.queueCapability, playQueue.queueCapability);
    }

    public int hashCode() {
        int iHashCode = this.queueMetaData.hashCode() * 31;
        List<GenericQueueEntity> list = this.playableEntities;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        List<QueueSequenceSlice> list2 = this.queueSlices;
        return ((iHashCode2 + (list2 != null ? list2.hashCode() : 0)) * 31) + this.queueCapability.hashCode();
    }

    public String toString() {
        return "PlayQueue(queueMetaData=" + this.queueMetaData + ", playableEntities=" + this.playableEntities + ", queueSlices=" + this.queueSlices + ", queueCapability=" + this.queueCapability + ')';
    }

    public PlayQueue(Queue queueMetaData, List<GenericQueueEntity> list, List<QueueSequenceSlice> list2, QueueCapability queueCapability) {
        Intrinsics.checkNotNullParameter(queueMetaData, "queueMetaData");
        Intrinsics.checkNotNullParameter(queueCapability, "queueCapability");
        this.queueMetaData = queueMetaData;
        this.playableEntities = list;
        this.queueSlices = list2;
        this.queueCapability = queueCapability;
        ExpectedFunctionsKt.freeze(this);
    }

    public final Queue getQueueMetaData() {
        return this.queueMetaData;
    }

    public final List<GenericQueueEntity> getPlayableEntities() {
        return this.playableEntities;
    }

    public final List<QueueSequenceSlice> getQueueSlices() {
        return this.queueSlices;
    }

    public final QueueCapability getQueueCapability() {
        return this.queueCapability;
    }
}
