package com.amazon.digitalmusicxp.models;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.OnlinePopulationStatusEnum;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.digitalmusicxp.types.ImageSizeUrlTuple;
import com.amazon.digitalmusicxp.types.QueueSeed;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Queue.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u001d\b\u0086\b\u0018\u00002\u00020\u0001B\u0091\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\u0012\b\u0002\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\b\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u000b\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0016\u001a\u00020\u0014¢\u0006\u0004\b6\u00107J£\u0001\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\u0012\b\u0002\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\b2\b\b\u0002\u0010\u000f\u001a\u00020\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u0014HÆ\u0001J\t\u0010\u0018\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0019\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001d\u001a\u0004\b \u0010\u001fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001d\u001a\u0004\b$\u0010\u001fR!\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\n\u0010%\u001a\u0004\b&\u0010'R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010(\u001a\u0004\b)\u0010*R\u001f\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u000e\u0010%\u001a\u0004\b+\u0010'R\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u001d\u001a\u0004\b,\u0010\u001fR\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u001d\u001a\u0004\b-\u0010\u001fR\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010.\u001a\u0004\b/\u00100R\u0017\u0010\u0013\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\u0013\u0010(\u001a\u0004\b1\u0010*R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u00102\u001a\u0004\b3\u00104R\u0017\u0010\u0016\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0016\u00102\u001a\u0004\b5\u00104¨\u00068"}, d2 = {"Lcom/amazon/digitalmusicxp/models/Queue;", "", "", "customerId", "id", "Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "serviceTier", "title", "", "Lcom/amazon/digitalmusicxp/types/ImageSizeUrlTuple;", "images", "", "queueTtlInSeconds", "Lcom/amazon/digitalmusicxp/types/QueueSeed;", "queueSeeds", "queueCapabilitySetId", "thingShadowName", "Lcom/amazon/digitalmusicxp/enums/OnlinePopulationStatusEnum;", "onlinePopulationStatus", "maxEntitiesPerQueueSequenceSlice", "", "createdAt", "updatedAt", "copy", "toString", "hashCode", "other", "", "equals", "Ljava/lang/String;", "getCustomerId", "()Ljava/lang/String;", "getId", "Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "getServiceTier", "()Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "getTitle", "Ljava/util/List;", "getImages", "()Ljava/util/List;", "I", "getQueueTtlInSeconds", "()I", "getQueueSeeds", "getQueueCapabilitySetId", "getThingShadowName", "Lcom/amazon/digitalmusicxp/enums/OnlinePopulationStatusEnum;", "getOnlinePopulationStatus", "()Lcom/amazon/digitalmusicxp/enums/OnlinePopulationStatusEnum;", "getMaxEntitiesPerQueueSequenceSlice", "J", "getCreatedAt", "()J", "getUpdatedAt", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;Ljava/lang/String;Ljava/util/List;ILjava/util/List;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/enums/OnlinePopulationStatusEnum;IJJ)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Queue {
    private final long createdAt;
    private final String customerId;
    private final String id;
    private final List<ImageSizeUrlTuple> images;
    private final int maxEntitiesPerQueueSequenceSlice;
    private final OnlinePopulationStatusEnum onlinePopulationStatus;
    private final String queueCapabilitySetId;
    private final List<QueueSeed> queueSeeds;
    private final int queueTtlInSeconds;
    private final ServiceTierEnum serviceTier;
    private final String thingShadowName;
    private final String title;
    private final long updatedAt;

    public final Queue copy(String customerId, String id, ServiceTierEnum serviceTier, String title, List<ImageSizeUrlTuple> images, int queueTtlInSeconds, List<QueueSeed> queueSeeds, String queueCapabilitySetId, String thingShadowName, OnlinePopulationStatusEnum onlinePopulationStatus, int maxEntitiesPerQueueSequenceSlice, long createdAt, long updatedAt) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(serviceTier, "serviceTier");
        Intrinsics.checkNotNullParameter(queueCapabilitySetId, "queueCapabilitySetId");
        return new Queue(customerId, id, serviceTier, title, images, queueTtlInSeconds, queueSeeds, queueCapabilitySetId, thingShadowName, onlinePopulationStatus, maxEntitiesPerQueueSequenceSlice, createdAt, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Queue)) {
            return false;
        }
        Queue queue = (Queue) other;
        return Intrinsics.areEqual(this.customerId, queue.customerId) && Intrinsics.areEqual(this.id, queue.id) && this.serviceTier == queue.serviceTier && Intrinsics.areEqual(this.title, queue.title) && Intrinsics.areEqual(this.images, queue.images) && this.queueTtlInSeconds == queue.queueTtlInSeconds && Intrinsics.areEqual(this.queueSeeds, queue.queueSeeds) && Intrinsics.areEqual(this.queueCapabilitySetId, queue.queueCapabilitySetId) && Intrinsics.areEqual(this.thingShadowName, queue.thingShadowName) && this.onlinePopulationStatus == queue.onlinePopulationStatus && this.maxEntitiesPerQueueSequenceSlice == queue.maxEntitiesPerQueueSequenceSlice && this.createdAt == queue.createdAt && this.updatedAt == queue.updatedAt;
    }

    public int hashCode() {
        int iHashCode = ((((this.customerId.hashCode() * 31) + this.id.hashCode()) * 31) + this.serviceTier.hashCode()) * 31;
        String str = this.title;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        List<ImageSizeUrlTuple> list = this.images;
        int iHashCode3 = (((iHashCode2 + (list == null ? 0 : list.hashCode())) * 31) + Integer.hashCode(this.queueTtlInSeconds)) * 31;
        List<QueueSeed> list2 = this.queueSeeds;
        int iHashCode4 = (((iHashCode3 + (list2 == null ? 0 : list2.hashCode())) * 31) + this.queueCapabilitySetId.hashCode()) * 31;
        String str2 = this.thingShadowName;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        OnlinePopulationStatusEnum onlinePopulationStatusEnum = this.onlinePopulationStatus;
        return ((((((iHashCode5 + (onlinePopulationStatusEnum != null ? onlinePopulationStatusEnum.hashCode() : 0)) * 31) + Integer.hashCode(this.maxEntitiesPerQueueSequenceSlice)) * 31) + Long.hashCode(this.createdAt)) * 31) + Long.hashCode(this.updatedAt);
    }

    public String toString() {
        return "Queue(customerId=" + this.customerId + ", id=" + this.id + ", serviceTier=" + this.serviceTier + ", title=" + this.title + ", images=" + this.images + ", queueTtlInSeconds=" + this.queueTtlInSeconds + ", queueSeeds=" + this.queueSeeds + ", queueCapabilitySetId=" + this.queueCapabilitySetId + ", thingShadowName=" + this.thingShadowName + ", onlinePopulationStatus=" + this.onlinePopulationStatus + ", maxEntitiesPerQueueSequenceSlice=" + this.maxEntitiesPerQueueSequenceSlice + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ')';
    }

    public Queue(String customerId, String id, ServiceTierEnum serviceTier, String str, List<ImageSizeUrlTuple> list, int i, List<QueueSeed> list2, String queueCapabilitySetId, String str2, OnlinePopulationStatusEnum onlinePopulationStatusEnum, int i2, long j, long j2) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(serviceTier, "serviceTier");
        Intrinsics.checkNotNullParameter(queueCapabilitySetId, "queueCapabilitySetId");
        this.customerId = customerId;
        this.id = id;
        this.serviceTier = serviceTier;
        this.title = str;
        this.images = list;
        this.queueTtlInSeconds = i;
        this.queueSeeds = list2;
        this.queueCapabilitySetId = queueCapabilitySetId;
        this.thingShadowName = str2;
        this.onlinePopulationStatus = onlinePopulationStatusEnum;
        this.maxEntitiesPerQueueSequenceSlice = i2;
        this.createdAt = j;
        this.updatedAt = j2;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ Queue(String str, String str2, ServiceTierEnum serviceTierEnum, String str3, List list, int i, List list2, String str4, String str5, OnlinePopulationStatusEnum onlinePopulationStatusEnum, int i2, long j, long j2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, serviceTierEnum, (i3 & 8) != 0 ? null : str3, (i3 & 16) != 0 ? null : list, i, (i3 & 64) != 0 ? null : list2, str4, (i3 & 256) != 0 ? null : str5, (i3 & 512) != 0 ? null : onlinePopulationStatusEnum, i2, j, j2);
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final String getId() {
        return this.id;
    }

    public final ServiceTierEnum getServiceTier() {
        return this.serviceTier;
    }

    public final String getTitle() {
        return this.title;
    }

    public final List<ImageSizeUrlTuple> getImages() {
        return this.images;
    }

    public final int getQueueTtlInSeconds() {
        return this.queueTtlInSeconds;
    }

    public final List<QueueSeed> getQueueSeeds() {
        return this.queueSeeds;
    }

    public final String getQueueCapabilitySetId() {
        return this.queueCapabilitySetId;
    }

    public final String getThingShadowName() {
        return this.thingShadowName;
    }

    public final OnlinePopulationStatusEnum getOnlinePopulationStatus() {
        return this.onlinePopulationStatus;
    }

    public final int getMaxEntitiesPerQueueSequenceSlice() {
        return this.maxEntitiesPerQueueSequenceSlice;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final long getUpdatedAt() {
        return this.updatedAt;
    }
}
