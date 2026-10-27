package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueMetadata.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0010\b\u0002\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b\u0012\u0010\b\u0002\u0010\"\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001b\u0012\u0006\u0010%\u001a\u00020$\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010+¢\u0006\u0004\b0\u00101J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u001a\u0010\fR\u001f\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001f\u0010\"\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u001b8\u0006¢\u0006\f\n\u0004\b\"\u0010\u001e\u001a\u0004\b#\u0010 R\u0017\u0010%\u001a\u00020$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0019\u0010)\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b)\u0010\n\u001a\u0004\b*\u0010\fR\u0019\u0010,\u001a\u0004\u0018\u00010+8\u0006¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/¨\u00062"}, d2 = {"Lcom/amazon/digitalmusicxp/types/QueueMetadata;", "", "", "toString", "", "hashCode", "other", "", "equals", "customerId", "Ljava/lang/String;", "getCustomerId", "()Ljava/lang/String;", "queueId", "getQueueId", "", "createdAt", "J", "getCreatedAt", "()J", "Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "serviceTier", "Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "getServiceTier", "()Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "title", "getTitle", "", "Lcom/amazon/digitalmusicxp/types/ImageSizeUrlTuple;", "images", "Ljava/util/List;", "getImages", "()Ljava/util/List;", "Lcom/amazon/digitalmusicxp/types/QueueSeed;", "queueSeeds", "getQueueSeeds", "Lcom/amazon/digitalmusicxp/types/QueueCapability;", "capabilities", "Lcom/amazon/digitalmusicxp/types/QueueCapability;", "getCapabilities", "()Lcom/amazon/digitalmusicxp/types/QueueCapability;", "thingShadowName", "getThingShadowName", "Lcom/amazon/digitalmusicxp/types/IdentityContext;", "identityContext", "Lcom/amazon/digitalmusicxp/types/IdentityContext;", "getIdentityContext", "()Lcom/amazon/digitalmusicxp/types/IdentityContext;", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLcom/amazon/digitalmusicxp/enums/ServiceTierEnum;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/amazon/digitalmusicxp/types/QueueCapability;Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/IdentityContext;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class QueueMetadata {
    private final QueueCapability capabilities;
    private final long createdAt;
    private final String customerId;
    private final IdentityContext identityContext;
    private final List<ImageSizeUrlTuple> images;
    private final String queueId;
    private final List<QueueSeed> queueSeeds;
    private final ServiceTierEnum serviceTier;
    private final String thingShadowName;
    private final String title;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueMetadata)) {
            return false;
        }
        QueueMetadata queueMetadata = (QueueMetadata) other;
        return Intrinsics.areEqual(this.customerId, queueMetadata.customerId) && Intrinsics.areEqual(this.queueId, queueMetadata.queueId) && this.createdAt == queueMetadata.createdAt && this.serviceTier == queueMetadata.serviceTier && Intrinsics.areEqual(this.title, queueMetadata.title) && Intrinsics.areEqual(this.images, queueMetadata.images) && Intrinsics.areEqual(this.queueSeeds, queueMetadata.queueSeeds) && Intrinsics.areEqual(this.capabilities, queueMetadata.capabilities) && Intrinsics.areEqual(this.thingShadowName, queueMetadata.thingShadowName) && Intrinsics.areEqual(this.identityContext, queueMetadata.identityContext);
    }

    public int hashCode() {
        int iHashCode = ((((((((this.customerId.hashCode() * 31) + this.queueId.hashCode()) * 31) + Long.hashCode(this.createdAt)) * 31) + this.serviceTier.hashCode()) * 31) + this.title.hashCode()) * 31;
        List<ImageSizeUrlTuple> list = this.images;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        List<QueueSeed> list2 = this.queueSeeds;
        int iHashCode3 = (((iHashCode2 + (list2 == null ? 0 : list2.hashCode())) * 31) + this.capabilities.hashCode()) * 31;
        String str = this.thingShadowName;
        int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        IdentityContext identityContext = this.identityContext;
        return iHashCode4 + (identityContext != null ? identityContext.hashCode() : 0);
    }

    public String toString() {
        return "QueueMetadata(customerId=" + this.customerId + ", queueId=" + this.queueId + ", createdAt=" + this.createdAt + ", serviceTier=" + this.serviceTier + ", title=" + this.title + ", images=" + this.images + ", queueSeeds=" + this.queueSeeds + ", capabilities=" + this.capabilities + ", thingShadowName=" + this.thingShadowName + ", identityContext=" + this.identityContext + ')';
    }

    public QueueMetadata(String customerId, String queueId, long j, ServiceTierEnum serviceTier, String title, List<ImageSizeUrlTuple> list, List<QueueSeed> list2, QueueCapability capabilities, String str, IdentityContext identityContext) {
        Intrinsics.checkNotNullParameter(customerId, "customerId");
        Intrinsics.checkNotNullParameter(queueId, "queueId");
        Intrinsics.checkNotNullParameter(serviceTier, "serviceTier");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(capabilities, "capabilities");
        this.customerId = customerId;
        this.queueId = queueId;
        this.createdAt = j;
        this.serviceTier = serviceTier;
        this.title = title;
        this.images = list;
        this.queueSeeds = list2;
        this.capabilities = capabilities;
        this.thingShadowName = str;
        this.identityContext = identityContext;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getCustomerId() {
        return this.customerId;
    }

    public final String getQueueId() {
        return this.queueId;
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

    public final List<QueueSeed> getQueueSeeds() {
        return this.queueSeeds;
    }

    public final QueueCapability getCapabilities() {
        return this.capabilities;
    }

    public final String getThingShadowName() {
        return this.thingShadowName;
    }
}
