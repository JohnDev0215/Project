package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.QueueEntityIdTypeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityReference.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0018¢\u0006\u0004\b\u001d\u0010\u001eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0015\u0010\fR\u0017\u0010\u0016\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010\n\u001a\u0004\b\u0017\u0010\fR\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, d2 = {"Lcom/amazon/digitalmusicxp/types/EntityReference;", "", "", "toString", "", "hashCode", "other", "", "equals", "entityReferenceId", "Ljava/lang/String;", "getEntityReferenceId", "()Ljava/lang/String;", "identifier", "getIdentifier", "Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", "identifierType", "Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", "getIdentifierType", "()Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;", "capabilitySetId", "getCapabilitySetId", "metricsContext", "getMetricsContext", "Lcom/amazon/digitalmusicxp/types/ClientMetricsInfo;", "clientMetricsInfo", "Lcom/amazon/digitalmusicxp/types/ClientMetricsInfo;", "getClientMetricsInfo", "()Lcom/amazon/digitalmusicxp/types/ClientMetricsInfo;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/enums/QueueEntityIdTypeEnum;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/digitalmusicxp/types/ClientMetricsInfo;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class EntityReference {
    private final String capabilitySetId;
    private final ClientMetricsInfo clientMetricsInfo;
    private final String entityReferenceId;
    private final String identifier;
    private final QueueEntityIdTypeEnum identifierType;
    private final String metricsContext;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EntityReference)) {
            return false;
        }
        EntityReference entityReference = (EntityReference) other;
        return Intrinsics.areEqual(this.entityReferenceId, entityReference.entityReferenceId) && Intrinsics.areEqual(this.identifier, entityReference.identifier) && this.identifierType == entityReference.identifierType && Intrinsics.areEqual(this.capabilitySetId, entityReference.capabilitySetId) && Intrinsics.areEqual(this.metricsContext, entityReference.metricsContext) && Intrinsics.areEqual(this.clientMetricsInfo, entityReference.clientMetricsInfo);
    }

    public int hashCode() {
        int iHashCode = ((((((((this.entityReferenceId.hashCode() * 31) + this.identifier.hashCode()) * 31) + this.identifierType.hashCode()) * 31) + this.capabilitySetId.hashCode()) * 31) + this.metricsContext.hashCode()) * 31;
        ClientMetricsInfo clientMetricsInfo = this.clientMetricsInfo;
        return iHashCode + (clientMetricsInfo == null ? 0 : clientMetricsInfo.hashCode());
    }

    public String toString() {
        return "EntityReference(entityReferenceId=" + this.entityReferenceId + ", identifier=" + this.identifier + ", identifierType=" + this.identifierType + ", capabilitySetId=" + this.capabilitySetId + ", metricsContext=" + this.metricsContext + ", clientMetricsInfo=" + this.clientMetricsInfo + ')';
    }

    public EntityReference(String entityReferenceId, String identifier, QueueEntityIdTypeEnum identifierType, String capabilitySetId, String metricsContext, ClientMetricsInfo clientMetricsInfo) {
        Intrinsics.checkNotNullParameter(entityReferenceId, "entityReferenceId");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(identifierType, "identifierType");
        Intrinsics.checkNotNullParameter(capabilitySetId, "capabilitySetId");
        Intrinsics.checkNotNullParameter(metricsContext, "metricsContext");
        this.entityReferenceId = entityReferenceId;
        this.identifier = identifier;
        this.identifierType = identifierType;
        this.capabilitySetId = capabilitySetId;
        this.metricsContext = metricsContext;
        this.clientMetricsInfo = clientMetricsInfo;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ EntityReference(String str, String str2, QueueEntityIdTypeEnum queueEntityIdTypeEnum, String str3, String str4, ClientMetricsInfo clientMetricsInfo, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, queueEntityIdTypeEnum, str3, str4, (i & 32) != 0 ? null : clientMetricsInfo);
    }

    public final String getEntityReferenceId() {
        return this.entityReferenceId;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final QueueEntityIdTypeEnum getIdentifierType() {
        return this.identifierType;
    }

    public final String getCapabilitySetId() {
        return this.capabilitySetId;
    }

    public final String getMetricsContext() {
        return this.metricsContext;
    }

    public final ClientMetricsInfo getClientMetricsInfo() {
        return this.clientMetricsInfo;
    }
}
