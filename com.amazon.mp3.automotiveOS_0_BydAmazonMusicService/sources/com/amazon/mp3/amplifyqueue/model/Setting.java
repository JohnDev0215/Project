package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;

/* JADX INFO: loaded from: classes3.dex */
@Index(fields = {"id"}, name = SQLiteCommandFactory.UNDEFINED)
@ModelConfig(pluralName = "Settings")
public final class Setting implements Model {

    @ModelField(isRequired = true, targetType = "DefaultQueueCapabilityIds")
    private final DefaultQueueCapabilityIds defaultQueueCapabilityIds;

    @ModelField(isRequired = true, targetType = "String")
    private final String defaultQueueEntityCapabilitiesId;

    @ModelField(isRequired = true, targetType = "ID")
    private final String id;

    @ModelField(isRequired = true, targetType = "Int")
    private final Integer maxEntitiesPerQueueSequenceSlice;

    @ModelField(isRequired = true, targetType = "Int")
    private final Integer maxQueueSize;

    @ModelField(isRequired = true, targetType = "Int")
    private final Integer queueTtlInSeconds;
    public static final QueryField ID = QueryField.field("Setting", "id");
    public static final QueryField MAX_ENTITIES_PER_QUEUE_SEQUENCE_SLICE = QueryField.field("Setting", "maxEntitiesPerQueueSequenceSlice");
    public static final QueryField MAX_QUEUE_SIZE = QueryField.field("Setting", "maxQueueSize");
    public static final QueryField QUEUE_TTL_IN_SECONDS = QueryField.field("Setting", "queueTtlInSeconds");
    public static final QueryField DEFAULT_QUEUE_CAPABILITY_IDS = QueryField.field("Setting", "defaultQueueCapabilityIds");
    public static final QueryField DEFAULT_QUEUE_ENTITY_CAPABILITIES_ID = QueryField.field("Setting", "defaultQueueEntityCapabilitiesId");

    public interface BuildStep {
    }

    public static class Builder implements MaxEntitiesPerQueueSequenceSliceStep, MaxQueueSizeStep, QueueTtlInSecondsStep, DefaultQueueCapabilityIdsStep, DefaultQueueEntityCapabilitiesIdStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface DefaultQueueCapabilityIdsStep {
    }

    public interface DefaultQueueEntityCapabilitiesIdStep {
    }

    public interface MaxEntitiesPerQueueSequenceSliceStep {
    }

    public interface MaxQueueSizeStep {
    }

    public interface QueueTtlInSecondsStep {
    }

    public String getId() {
        return this.id;
    }

    public Integer getMaxEntitiesPerQueueSequenceSlice() {
        return this.maxEntitiesPerQueueSequenceSlice;
    }

    public Integer getMaxQueueSize() {
        return this.maxQueueSize;
    }

    public Integer getQueueTtlInSeconds() {
        return this.queueTtlInSeconds;
    }

    public DefaultQueueCapabilityIds getDefaultQueueCapabilityIds() {
        return this.defaultQueueCapabilityIds;
    }

    public String getDefaultQueueEntityCapabilitiesId() {
        return this.defaultQueueEntityCapabilitiesId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Setting.class != obj.getClass()) {
            return false;
        }
        Setting setting = (Setting) obj;
        return ObjectsCompat.equals(getId(), setting.getId()) && ObjectsCompat.equals(getMaxEntitiesPerQueueSequenceSlice(), setting.getMaxEntitiesPerQueueSequenceSlice()) && ObjectsCompat.equals(getMaxQueueSize(), setting.getMaxQueueSize()) && ObjectsCompat.equals(getQueueTtlInSeconds(), setting.getQueueTtlInSeconds()) && ObjectsCompat.equals(getDefaultQueueCapabilityIds(), setting.getDefaultQueueCapabilityIds()) && ObjectsCompat.equals(getDefaultQueueEntityCapabilitiesId(), setting.getDefaultQueueEntityCapabilitiesId());
    }

    public int hashCode() {
        return (getId() + getMaxEntitiesPerQueueSequenceSlice() + getMaxQueueSize() + getQueueTtlInSeconds() + getDefaultQueueCapabilityIds() + getDefaultQueueEntityCapabilitiesId()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Setting {");
        sb.append("id=" + String.valueOf(getId()) + ", ");
        sb.append("maxEntitiesPerQueueSequenceSlice=" + String.valueOf(getMaxEntitiesPerQueueSequenceSlice()) + ", ");
        sb.append("maxQueueSize=" + String.valueOf(getMaxQueueSize()) + ", ");
        sb.append("queueTtlInSeconds=" + String.valueOf(getQueueTtlInSeconds()) + ", ");
        sb.append("defaultQueueCapabilityIds=" + String.valueOf(getDefaultQueueCapabilityIds()) + ", ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("defaultQueueEntityCapabilitiesId=");
        sb2.append(String.valueOf(getDefaultQueueEntityCapabilitiesId()));
        sb.append(sb2.toString());
        sb.append("}");
        return sb.toString();
    }
}
