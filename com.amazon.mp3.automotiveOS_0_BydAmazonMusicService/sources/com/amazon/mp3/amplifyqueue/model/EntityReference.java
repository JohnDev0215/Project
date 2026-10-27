package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class EntityReference {
    private final String capabilitySetId;
    private final ClientMetricsInfo clientMetricsInfo;
    private final String entityReferenceId;
    private final String identifier;
    private final QueueEntityIdTypeEnum identifierType;
    private final String identifierTypeEnumValue;
    private final String metricsContext;

    public interface BuildStep {
        EntityReference build();

        BuildStep clientMetricsInfo(ClientMetricsInfo clientMetricsInfo);

        BuildStep identifierTypeEnumValue(String str);
    }

    public interface CapabilitySetIdStep {
        MetricsContextStep capabilitySetId(String str);
    }

    public interface EntityReferenceIdStep {
        IdentifierStep entityReferenceId(String str);
    }

    public interface IdentifierStep {
        IdentifierTypeStep identifier(String str);
    }

    public interface IdentifierTypeStep {
        CapabilitySetIdStep identifierType(QueueEntityIdTypeEnum queueEntityIdTypeEnum);
    }

    public interface MetricsContextStep {
        BuildStep metricsContext(String str);
    }

    public String getEntityReferenceId() {
        return this.entityReferenceId;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public QueueEntityIdTypeEnum getIdentifierType() {
        return this.identifierType;
    }

    public String getIdentifierTypeEnumValue() {
        return this.identifierTypeEnumValue;
    }

    public String getCapabilitySetId() {
        return this.capabilitySetId;
    }

    public String getMetricsContext() {
        return this.metricsContext;
    }

    public ClientMetricsInfo getClientMetricsInfo() {
        return this.clientMetricsInfo;
    }

    private EntityReference(String str, String str2, QueueEntityIdTypeEnum queueEntityIdTypeEnum, String str3, String str4, String str5, ClientMetricsInfo clientMetricsInfo) {
        this.entityReferenceId = str;
        this.identifier = str2;
        this.identifierType = queueEntityIdTypeEnum;
        this.identifierTypeEnumValue = str3;
        this.capabilitySetId = str4;
        this.metricsContext = str5;
        this.clientMetricsInfo = clientMetricsInfo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || EntityReference.class != obj.getClass()) {
            return false;
        }
        EntityReference entityReference = (EntityReference) obj;
        return ObjectsCompat.equals(getEntityReferenceId(), entityReference.getEntityReferenceId()) && ObjectsCompat.equals(getIdentifier(), entityReference.getIdentifier()) && ObjectsCompat.equals(getIdentifierType(), entityReference.getIdentifierType()) && ObjectsCompat.equals(getIdentifierTypeEnumValue(), entityReference.getIdentifierTypeEnumValue()) && ObjectsCompat.equals(getCapabilitySetId(), entityReference.getCapabilitySetId()) && ObjectsCompat.equals(getMetricsContext(), entityReference.getMetricsContext()) && ObjectsCompat.equals(getClientMetricsInfo(), entityReference.getClientMetricsInfo());
    }

    public int hashCode() {
        return (getEntityReferenceId() + getIdentifier() + getIdentifierType() + getIdentifierTypeEnumValue() + getCapabilitySetId() + getMetricsContext() + getClientMetricsInfo()).hashCode();
    }

    public static EntityReferenceIdStep builder() {
        return new Builder();
    }

    public static class Builder implements EntityReferenceIdStep, IdentifierStep, IdentifierTypeStep, CapabilitySetIdStep, MetricsContextStep, BuildStep {
        private String capabilitySetId;
        private ClientMetricsInfo clientMetricsInfo;
        private String entityReferenceId;
        private String identifier;
        private QueueEntityIdTypeEnum identifierType;
        private String identifierTypeEnumValue;
        private String metricsContext;

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.BuildStep
        public EntityReference build() {
            return new EntityReference(this.entityReferenceId, this.identifier, this.identifierType, this.identifierTypeEnumValue, this.capabilitySetId, this.metricsContext, this.clientMetricsInfo);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.EntityReferenceIdStep
        public IdentifierStep entityReferenceId(String str) {
            Objects.requireNonNull(str);
            this.entityReferenceId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.IdentifierStep
        public IdentifierTypeStep identifier(String str) {
            Objects.requireNonNull(str);
            this.identifier = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.IdentifierTypeStep
        public CapabilitySetIdStep identifierType(QueueEntityIdTypeEnum queueEntityIdTypeEnum) {
            Objects.requireNonNull(queueEntityIdTypeEnum);
            this.identifierType = queueEntityIdTypeEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.CapabilitySetIdStep
        public MetricsContextStep capabilitySetId(String str) {
            Objects.requireNonNull(str);
            this.capabilitySetId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.MetricsContextStep
        public BuildStep metricsContext(String str) {
            Objects.requireNonNull(str);
            this.metricsContext = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.BuildStep
        public BuildStep identifierTypeEnumValue(String str) {
            this.identifierTypeEnumValue = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.BuildStep
        public BuildStep clientMetricsInfo(ClientMetricsInfo clientMetricsInfo) {
            this.clientMetricsInfo = clientMetricsInfo;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.Builder, com.amazon.mp3.amplifyqueue.model.EntityReference.EntityReferenceIdStep
        public CopyOfBuilder entityReferenceId(String str) {
            return (CopyOfBuilder) super.entityReferenceId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.Builder, com.amazon.mp3.amplifyqueue.model.EntityReference.IdentifierStep
        public CopyOfBuilder identifier(String str) {
            return (CopyOfBuilder) super.identifier(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.Builder, com.amazon.mp3.amplifyqueue.model.EntityReference.IdentifierTypeStep
        public CopyOfBuilder identifierType(QueueEntityIdTypeEnum queueEntityIdTypeEnum) {
            return (CopyOfBuilder) super.identifierType(queueEntityIdTypeEnum);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.Builder, com.amazon.mp3.amplifyqueue.model.EntityReference.CapabilitySetIdStep
        public CopyOfBuilder capabilitySetId(String str) {
            return (CopyOfBuilder) super.capabilitySetId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.Builder, com.amazon.mp3.amplifyqueue.model.EntityReference.MetricsContextStep
        public CopyOfBuilder metricsContext(String str) {
            return (CopyOfBuilder) super.metricsContext(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.Builder, com.amazon.mp3.amplifyqueue.model.EntityReference.BuildStep
        public CopyOfBuilder identifierTypeEnumValue(String str) {
            return (CopyOfBuilder) super.identifierTypeEnumValue(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.EntityReference.Builder, com.amazon.mp3.amplifyqueue.model.EntityReference.BuildStep
        public CopyOfBuilder clientMetricsInfo(ClientMetricsInfo clientMetricsInfo) {
            return (CopyOfBuilder) super.clientMetricsInfo(clientMetricsInfo);
        }
    }
}
