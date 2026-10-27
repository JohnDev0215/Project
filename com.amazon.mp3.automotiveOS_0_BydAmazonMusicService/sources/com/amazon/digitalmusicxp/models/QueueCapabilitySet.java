package com.amazon.digitalmusicxp.models;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.QueueSeedTypeEnum;
import com.amazon.digitalmusicxp.enums.ServiceTierEnum;
import com.amazon.digitalmusicxp.types.QueueCapability;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QueueCapabilitySet.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0017\u001a\u00020\u0012\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b%\u0010&J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0014\u001a\u0004\b\u0018\u0010\u0016R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u001a\u0010\fR\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0019\u0010!\u001a\u0004\u0018\u00010 8\u0006¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$¨\u0006'"}, d2 = {"Lcom/amazon/digitalmusicxp/models/QueueCapabilitySet;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Lcom/amazon/digitalmusicxp/types/QueueCapability;", "capabilities", "Lcom/amazon/digitalmusicxp/types/QueueCapability;", "getCapabilities", "()Lcom/amazon/digitalmusicxp/types/QueueCapability;", "", "createdAt", "J", "getCreatedAt", "()J", "updatedAt", "getUpdatedAt", "queueInteractionPattern", "getQueueInteractionPattern", "Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "serviceTier", "Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "getServiceTier", "()Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;", "Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;", "queueSeedType", "Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;", "getQueueSeedType", "()Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;", "<init>", "(Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/QueueCapability;JJLjava/lang/String;Lcom/amazon/digitalmusicxp/enums/ServiceTierEnum;Lcom/amazon/digitalmusicxp/enums/QueueSeedTypeEnum;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class QueueCapabilitySet {
    private final QueueCapability capabilities;
    private final long createdAt;
    private final String id;
    private final String queueInteractionPattern;
    private final QueueSeedTypeEnum queueSeedType;
    private final ServiceTierEnum serviceTier;
    private final long updatedAt;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof QueueCapabilitySet)) {
            return false;
        }
        QueueCapabilitySet queueCapabilitySet = (QueueCapabilitySet) other;
        return Intrinsics.areEqual(this.id, queueCapabilitySet.id) && Intrinsics.areEqual(this.capabilities, queueCapabilitySet.capabilities) && this.createdAt == queueCapabilitySet.createdAt && this.updatedAt == queueCapabilitySet.updatedAt && Intrinsics.areEqual(this.queueInteractionPattern, queueCapabilitySet.queueInteractionPattern) && this.serviceTier == queueCapabilitySet.serviceTier && this.queueSeedType == queueCapabilitySet.queueSeedType;
    }

    public int hashCode() {
        int iHashCode = ((((((this.id.hashCode() * 31) + this.capabilities.hashCode()) * 31) + Long.hashCode(this.createdAt)) * 31) + Long.hashCode(this.updatedAt)) * 31;
        String str = this.queueInteractionPattern;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        ServiceTierEnum serviceTierEnum = this.serviceTier;
        int iHashCode3 = (iHashCode2 + (serviceTierEnum == null ? 0 : serviceTierEnum.hashCode())) * 31;
        QueueSeedTypeEnum queueSeedTypeEnum = this.queueSeedType;
        return iHashCode3 + (queueSeedTypeEnum != null ? queueSeedTypeEnum.hashCode() : 0);
    }

    public String toString() {
        return "QueueCapabilitySet(id=" + this.id + ", capabilities=" + this.capabilities + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", queueInteractionPattern=" + this.queueInteractionPattern + ", serviceTier=" + this.serviceTier + ", queueSeedType=" + this.queueSeedType + ')';
    }

    public QueueCapabilitySet(String id, QueueCapability capabilities, long j, long j2, String str, ServiceTierEnum serviceTierEnum, QueueSeedTypeEnum queueSeedTypeEnum) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(capabilities, "capabilities");
        this.id = id;
        this.capabilities = capabilities;
        this.createdAt = j;
        this.updatedAt = j2;
        this.queueInteractionPattern = str;
        this.serviceTier = serviceTierEnum;
        this.queueSeedType = queueSeedTypeEnum;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ QueueCapabilitySet(String str, QueueCapability queueCapability, long j, long j2, String str2, ServiceTierEnum serviceTierEnum, QueueSeedTypeEnum queueSeedTypeEnum, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, queueCapability, j, j2, str2, (i & 32) != 0 ? null : serviceTierEnum, (i & 64) != 0 ? null : queueSeedTypeEnum);
    }

    public final String getId() {
        return this.id;
    }

    public final QueueCapability getCapabilities() {
        return this.capabilities;
    }
}
