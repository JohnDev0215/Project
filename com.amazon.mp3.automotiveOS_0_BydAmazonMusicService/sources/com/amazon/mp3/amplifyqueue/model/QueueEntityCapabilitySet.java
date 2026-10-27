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
@ModelConfig(pluralName = "QueueEntityCapabilitySets")
public final class QueueEntityCapabilitySet implements Model {

    @ModelField(isRequired = true, targetType = "QueueEntityCapability")
    private final QueueEntityCapability capabilities;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime createdAt;

    @ModelField(isRequired = true, targetType = "ID")
    private final String id;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime updatedAt;
    public static final QueryField ID = QueryField.field("QueueEntityCapabilitySet", "id");
    public static final QueryField CAPABILITIES = QueryField.field("QueueEntityCapabilitySet", "capabilities");
    public static final QueryField CREATED_AT = QueryField.field("QueueEntityCapabilitySet", "createdAt");
    public static final QueryField UPDATED_AT = QueryField.field("QueueEntityCapabilitySet", "updatedAt");

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

    public QueueEntityCapability getCapabilities() {
        return this.capabilities;
    }

    public Temporal.DateTime getCreatedAt() {
        return this.createdAt;
    }

    public Temporal.DateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueEntityCapabilitySet.class != obj.getClass()) {
            return false;
        }
        QueueEntityCapabilitySet queueEntityCapabilitySet = (QueueEntityCapabilitySet) obj;
        return ObjectsCompat.equals(getId(), queueEntityCapabilitySet.getId()) && ObjectsCompat.equals(getCapabilities(), queueEntityCapabilitySet.getCapabilities()) && ObjectsCompat.equals(getCreatedAt(), queueEntityCapabilitySet.getCreatedAt()) && ObjectsCompat.equals(getUpdatedAt(), queueEntityCapabilitySet.getUpdatedAt());
    }

    public int hashCode() {
        return (getId() + getCapabilities() + getCreatedAt() + getUpdatedAt()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QueueEntityCapabilitySet {");
        sb.append("id=" + String.valueOf(getId()) + ", ");
        sb.append("capabilities=" + String.valueOf(getCapabilities()) + ", ");
        sb.append("createdAt=" + String.valueOf(getCreatedAt()) + ", ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("updatedAt=");
        sb2.append(String.valueOf(getUpdatedAt()));
        sb.append(sb2.toString());
        sb.append("}");
        return sb.toString();
    }
}
