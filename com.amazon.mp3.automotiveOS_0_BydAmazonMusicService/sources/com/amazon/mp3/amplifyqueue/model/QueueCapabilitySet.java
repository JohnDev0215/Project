package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.Indexes;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;

/* JADX INFO: loaded from: classes3.dex */
@Indexes({@Index(fields = {"serviceTier", "queueSeedType"}, name = SQLiteCommandFactory.UNDEFINED), @Index(fields = {"id"}, name = "queueCapabilitySetById")})
@ModelConfig(pluralName = "QueueCapabilitySets")
public final class QueueCapabilitySet implements Model {

    @ModelField(isRequired = true, targetType = "QueueCapability")
    private final QueueCapability capabilities;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime createdAt;

    @ModelField(isRequired = true, targetType = "ID")
    private final String id;

    @ModelField(isRequired = true, targetType = "QueueInteractionPatternEnum")
    private final QueueInteractionPatternEnum queueInteractionPattern;

    @ModelField(isRequired = true, targetType = "QueueSeedTypeEnum")
    private final QueueSeedTypeEnum queueSeedType;

    @ModelField(isRequired = true, targetType = "ServiceTierEnum")
    private final ServiceTierEnum serviceTier;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime updatedAt;
    public static final QueryField ID = QueryField.field("QueueCapabilitySet", "id");
    public static final QueryField CAPABILITIES = QueryField.field("QueueCapabilitySet", "capabilities");
    public static final QueryField CREATED_AT = QueryField.field("QueueCapabilitySet", "createdAt");
    public static final QueryField UPDATED_AT = QueryField.field("QueueCapabilitySet", "updatedAt");
    public static final QueryField SERVICE_TIER = QueryField.field("QueueCapabilitySet", "serviceTier");
    public static final QueryField QUEUE_SEED_TYPE = QueryField.field("QueueCapabilitySet", "queueSeedType");
    public static final QueryField QUEUE_INTERACTION_PATTERN = QueryField.field("QueueCapabilitySet", "queueInteractionPattern");

    public interface BuildStep {
    }

    public static class Builder implements CapabilitiesStep, CreatedAtStep, UpdatedAtStep, ServiceTierStep, QueueSeedTypeStep, QueueInteractionPatternStep, BuildStep {
    }

    public interface CapabilitiesStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CreatedAtStep {
    }

    public interface QueueInteractionPatternStep {
    }

    public interface QueueSeedTypeStep {
    }

    public interface ServiceTierStep {
    }

    public interface UpdatedAtStep {
    }

    public String getId() {
        return this.id;
    }

    public QueueCapability getCapabilities() {
        return this.capabilities;
    }

    public Temporal.DateTime getCreatedAt() {
        return this.createdAt;
    }

    public Temporal.DateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public ServiceTierEnum getServiceTier() {
        return this.serviceTier;
    }

    public QueueSeedTypeEnum getQueueSeedType() {
        return this.queueSeedType;
    }

    public QueueInteractionPatternEnum getQueueInteractionPattern() {
        return this.queueInteractionPattern;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueCapabilitySet.class != obj.getClass()) {
            return false;
        }
        QueueCapabilitySet queueCapabilitySet = (QueueCapabilitySet) obj;
        return ObjectsCompat.equals(getId(), queueCapabilitySet.getId()) && ObjectsCompat.equals(getCapabilities(), queueCapabilitySet.getCapabilities()) && ObjectsCompat.equals(getCreatedAt(), queueCapabilitySet.getCreatedAt()) && ObjectsCompat.equals(getUpdatedAt(), queueCapabilitySet.getUpdatedAt()) && ObjectsCompat.equals(getServiceTier(), queueCapabilitySet.getServiceTier()) && ObjectsCompat.equals(getQueueSeedType(), queueCapabilitySet.getQueueSeedType()) && ObjectsCompat.equals(getQueueInteractionPattern(), queueCapabilitySet.getQueueInteractionPattern());
    }

    public int hashCode() {
        return (getId() + getCapabilities() + getCreatedAt() + getUpdatedAt() + getServiceTier() + getQueueSeedType() + getQueueInteractionPattern()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QueueCapabilitySet {");
        sb.append("id=" + String.valueOf(getId()) + ", ");
        sb.append("capabilities=" + String.valueOf(getCapabilities()) + ", ");
        sb.append("createdAt=" + String.valueOf(getCreatedAt()) + ", ");
        sb.append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ");
        sb.append("serviceTier=" + String.valueOf(getServiceTier()) + ", ");
        sb.append("queueSeedType=" + String.valueOf(getQueueSeedType()) + ", ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("queueInteractionPattern=");
        sb2.append(String.valueOf(getQueueInteractionPattern()));
        sb.append(sb2.toString());
        sb.append("}");
        return sb.toString();
    }
}
