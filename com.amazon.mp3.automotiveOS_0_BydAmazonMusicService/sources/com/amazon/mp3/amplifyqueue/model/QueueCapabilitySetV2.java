package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;

/* JADX INFO: loaded from: classes3.dex */
@Index(fields = {"id"}, name = SQLiteCommandFactory.UNDEFINED)
@ModelConfig(pluralName = "QueueCapabilitySetV2s")
public final class QueueCapabilitySetV2 implements Model {

    @ModelField(isRequired = true, targetType = "QueueCapability")
    private final QueueCapability capabilities;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime createdAt;

    @ModelField(isRequired = true, targetType = "ID")
    private final String id;

    @ModelField(targetType = "String")
    private final String queueInteractionPattern;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime updatedAt;
    public static final QueryField ID = QueryField.field("QueueCapabilitySetV2", "id");
    public static final QueryField CAPABILITIES = QueryField.field("QueueCapabilitySetV2", "capabilities");
    public static final QueryField CREATED_AT = QueryField.field("QueueCapabilitySetV2", "createdAt");
    public static final QueryField UPDATED_AT = QueryField.field("QueueCapabilitySetV2", "updatedAt");
    public static final QueryField QUEUE_INTERACTION_PATTERN = QueryField.field("QueueCapabilitySetV2", "queueInteractionPattern");

    public interface BuildStep {
    }

    public static class Builder implements CapabilitiesStep, CreatedAtStep, UpdatedAtStep, BuildStep {
    }

    public interface CapabilitiesStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface CreatedAtStep {
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

    public String getQueueInteractionPattern() {
        return this.queueInteractionPattern;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueCapabilitySetV2.class != obj.getClass()) {
            return false;
        }
        QueueCapabilitySetV2 queueCapabilitySetV2 = (QueueCapabilitySetV2) obj;
        return ObjectsCompat.equals(getId(), queueCapabilitySetV2.getId()) && ObjectsCompat.equals(getCapabilities(), queueCapabilitySetV2.getCapabilities()) && ObjectsCompat.equals(getCreatedAt(), queueCapabilitySetV2.getCreatedAt()) && ObjectsCompat.equals(getUpdatedAt(), queueCapabilitySetV2.getUpdatedAt()) && ObjectsCompat.equals(getQueueInteractionPattern(), queueCapabilitySetV2.getQueueInteractionPattern());
    }

    public int hashCode() {
        return (getId() + getCapabilities() + getCreatedAt() + getUpdatedAt() + getQueueInteractionPattern()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QueueCapabilitySetV2 {");
        sb.append("id=" + String.valueOf(getId()) + ", ");
        sb.append("capabilities=" + String.valueOf(getCapabilities()) + ", ");
        sb.append("createdAt=" + String.valueOf(getCreatedAt()) + ", ");
        sb.append("updatedAt=" + String.valueOf(getUpdatedAt()) + ", ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("queueInteractionPattern=");
        sb2.append(String.valueOf(getQueueInteractionPattern()));
        sb.append(sb2.toString());
        sb.append("}");
        return sb.toString();
    }
}
