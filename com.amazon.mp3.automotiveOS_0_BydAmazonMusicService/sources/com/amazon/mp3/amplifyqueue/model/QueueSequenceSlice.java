package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.storage.sqlite.SQLiteCommandFactory;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
@Index(fields = {"customerId", "queueId", "sequenceName", "sequenceVersion", "sliceOrdinal"}, name = SQLiteCommandFactory.UNDEFINED)
@ModelConfig(pluralName = "QueueSequenceSlices")
public final class QueueSequenceSlice implements Model {

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime createdAt;

    @ModelField(isRequired = true, targetType = "ID")
    private final String customerId;

    @ModelField(isRequired = true, targetType = "EntityReference")
    private final List<EntityReference> entityReferences;

    @ModelField(isRequired = true, targetType = "ID")
    private final String id;

    @ModelField(isRequired = true, targetType = "Boolean")
    private final Boolean isLastSlice;

    @ModelField(isRequired = true, targetType = "ID")
    private final String queueId;

    @ModelField(isRequired = true, targetType = "String")
    private final String sequenceName;

    @ModelField(isRequired = true, targetType = "Int")
    private final Integer sequenceVersion;

    @ModelField(isRequired = true, targetType = "Int")
    private final Integer sliceOrdinal;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime updatedAt;
    public static final QueryField ID = QueryField.field("QueueSequenceSlice", "id");
    public static final QueryField QUEUE_ID = QueryField.field("QueueSequenceSlice", "queueId");
    public static final QueryField CUSTOMER_ID = QueryField.field("QueueSequenceSlice", "customerId");
    public static final QueryField SEQUENCE_NAME = QueryField.field("QueueSequenceSlice", "sequenceName");
    public static final QueryField SEQUENCE_VERSION = QueryField.field("QueueSequenceSlice", "sequenceVersion");
    public static final QueryField SLICE_ORDINAL = QueryField.field("QueueSequenceSlice", "sliceOrdinal");
    public static final QueryField IS_LAST_SLICE = QueryField.field("QueueSequenceSlice", "isLastSlice");
    public static final QueryField ENTITY_REFERENCES = QueryField.field("QueueSequenceSlice", "entityReferences");
    public static final QueryField CREATED_AT = QueryField.field("QueueSequenceSlice", "createdAt");
    public static final QueryField UPDATED_AT = QueryField.field("QueueSequenceSlice", "updatedAt");

    public interface BuildStep {
        QueueSequenceSlice build();

        BuildStep id(String str);
    }

    public interface CreatedAtStep {
        UpdatedAtStep createdAt(Temporal.DateTime dateTime);
    }

    public interface CustomerIdStep {
        SequenceNameStep customerId(String str);
    }

    public interface EntityReferencesStep {
        CreatedAtStep entityReferences(List<EntityReference> list);
    }

    public interface IsLastSliceStep {
        EntityReferencesStep isLastSlice(Boolean bool);
    }

    public interface QueueIdStep {
        CustomerIdStep queueId(String str);
    }

    public interface SequenceNameStep {
        SequenceVersionStep sequenceName(String str);
    }

    public interface SequenceVersionStep {
        SliceOrdinalStep sequenceVersion(Integer num);
    }

    public interface SliceOrdinalStep {
        IsLastSliceStep sliceOrdinal(Integer num);
    }

    public interface UpdatedAtStep {
        BuildStep updatedAt(Temporal.DateTime dateTime);
    }

    public String getId() {
        return this.id;
    }

    public String getQueueId() {
        return this.queueId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getSequenceName() {
        return this.sequenceName;
    }

    public Integer getSequenceVersion() {
        return this.sequenceVersion;
    }

    public Integer getSliceOrdinal() {
        return this.sliceOrdinal;
    }

    public Boolean getIsLastSlice() {
        return this.isLastSlice;
    }

    public List<EntityReference> getEntityReferences() {
        return this.entityReferences;
    }

    public Temporal.DateTime getCreatedAt() {
        return this.createdAt;
    }

    public Temporal.DateTime getUpdatedAt() {
        return this.updatedAt;
    }

    private QueueSequenceSlice(String str, String str2, String str3, String str4, Integer num, Integer num2, Boolean bool, List<EntityReference> list, Temporal.DateTime dateTime, Temporal.DateTime dateTime2) {
        this.id = str;
        this.queueId = str2;
        this.customerId = str3;
        this.sequenceName = str4;
        this.sequenceVersion = num;
        this.sliceOrdinal = num2;
        this.isLastSlice = bool;
        this.entityReferences = list;
        this.createdAt = dateTime;
        this.updatedAt = dateTime2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueSequenceSlice.class != obj.getClass()) {
            return false;
        }
        QueueSequenceSlice queueSequenceSlice = (QueueSequenceSlice) obj;
        return ObjectsCompat.equals(getId(), queueSequenceSlice.getId()) && ObjectsCompat.equals(getQueueId(), queueSequenceSlice.getQueueId()) && ObjectsCompat.equals(getCustomerId(), queueSequenceSlice.getCustomerId()) && ObjectsCompat.equals(getSequenceName(), queueSequenceSlice.getSequenceName()) && ObjectsCompat.equals(getSequenceVersion(), queueSequenceSlice.getSequenceVersion()) && ObjectsCompat.equals(getSliceOrdinal(), queueSequenceSlice.getSliceOrdinal()) && ObjectsCompat.equals(getIsLastSlice(), queueSequenceSlice.getIsLastSlice()) && ObjectsCompat.equals(getEntityReferences(), queueSequenceSlice.getEntityReferences()) && ObjectsCompat.equals(getCreatedAt(), queueSequenceSlice.getCreatedAt()) && ObjectsCompat.equals(getUpdatedAt(), queueSequenceSlice.getUpdatedAt());
    }

    public int hashCode() {
        return (getId() + getQueueId() + getCustomerId() + getSequenceName() + getSequenceVersion() + getSliceOrdinal() + getIsLastSlice() + getEntityReferences() + getCreatedAt() + getUpdatedAt()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QueueSequenceSlice {");
        sb.append("id=" + String.valueOf(getId()) + ", ");
        sb.append("queueId=" + String.valueOf(getQueueId()) + ", ");
        sb.append("customerId=" + String.valueOf(getCustomerId()) + ", ");
        sb.append("sequenceName=" + String.valueOf(getSequenceName()) + ", ");
        sb.append("sequenceVersion=" + String.valueOf(getSequenceVersion()) + ", ");
        sb.append("sliceOrdinal=" + String.valueOf(getSliceOrdinal()) + ", ");
        sb.append("isLastSlice=" + String.valueOf(getIsLastSlice()) + ", ");
        sb.append("entityReferences=" + String.valueOf(getEntityReferences()) + ", ");
        sb.append("createdAt=" + String.valueOf(getCreatedAt()) + ", ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("updatedAt=");
        sb2.append(String.valueOf(getUpdatedAt()));
        sb.append(sb2.toString());
        sb.append("}");
        return sb.toString();
    }

    public static QueueIdStep builder() {
        return new Builder();
    }

    public CopyOfBuilder copyOfBuilder() {
        return new CopyOfBuilder(this.id, this.queueId, this.customerId, this.sequenceName, this.sequenceVersion, this.sliceOrdinal, this.isLastSlice, this.entityReferences, this.createdAt, this.updatedAt);
    }

    public static class Builder implements QueueIdStep, CustomerIdStep, SequenceNameStep, SequenceVersionStep, SliceOrdinalStep, IsLastSliceStep, EntityReferencesStep, CreatedAtStep, UpdatedAtStep, BuildStep {
        private Temporal.DateTime createdAt;
        private String customerId;
        private List<EntityReference> entityReferences;
        private String id;
        private Boolean isLastSlice;
        private String queueId;
        private String sequenceName;
        private Integer sequenceVersion;
        private Integer sliceOrdinal;
        private Temporal.DateTime updatedAt;

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.BuildStep
        public QueueSequenceSlice build() {
            String string = this.id;
            if (string == null) {
                string = UUID.randomUUID().toString();
            }
            return new QueueSequenceSlice(string, this.queueId, this.customerId, this.sequenceName, this.sequenceVersion, this.sliceOrdinal, this.isLastSlice, this.entityReferences, this.createdAt, this.updatedAt);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.QueueIdStep
        public CustomerIdStep queueId(String str) {
            Objects.requireNonNull(str);
            this.queueId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.CustomerIdStep
        public SequenceNameStep customerId(String str) {
            Objects.requireNonNull(str);
            this.customerId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.SequenceNameStep
        public SequenceVersionStep sequenceName(String str) {
            Objects.requireNonNull(str);
            this.sequenceName = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.SequenceVersionStep
        public SliceOrdinalStep sequenceVersion(Integer num) {
            Objects.requireNonNull(num);
            this.sequenceVersion = num;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.SliceOrdinalStep
        public IsLastSliceStep sliceOrdinal(Integer num) {
            Objects.requireNonNull(num);
            this.sliceOrdinal = num;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.IsLastSliceStep
        public EntityReferencesStep isLastSlice(Boolean bool) {
            Objects.requireNonNull(bool);
            this.isLastSlice = bool;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.EntityReferencesStep
        public CreatedAtStep entityReferences(List<EntityReference> list) {
            Objects.requireNonNull(list);
            this.entityReferences = list;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.CreatedAtStep
        public UpdatedAtStep createdAt(Temporal.DateTime dateTime) {
            Objects.requireNonNull(dateTime);
            this.createdAt = dateTime;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.UpdatedAtStep
        public BuildStep updatedAt(Temporal.DateTime dateTime) {
            Objects.requireNonNull(dateTime);
            this.updatedAt = dateTime;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.BuildStep
        public BuildStep id(String str) {
            this.id = str;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.Builder, com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.EntityReferencesStep
        public /* bridge */ /* synthetic */ CreatedAtStep entityReferences(List list) {
            return entityReferences((List<EntityReference>) list);
        }

        private CopyOfBuilder(String str, String str2, String str3, String str4, Integer num, Integer num2, Boolean bool, List<EntityReference> list, Temporal.DateTime dateTime, Temporal.DateTime dateTime2) {
            super.id(str);
            super.queueId(str2).customerId(str3).sequenceName(str4).sequenceVersion(num).sliceOrdinal(num2).isLastSlice(bool).entityReferences(list).createdAt(dateTime).updatedAt(dateTime2);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.Builder, com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.QueueIdStep
        public CopyOfBuilder queueId(String str) {
            return (CopyOfBuilder) super.queueId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.Builder, com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.CustomerIdStep
        public CopyOfBuilder customerId(String str) {
            return (CopyOfBuilder) super.customerId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.Builder, com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.SequenceNameStep
        public CopyOfBuilder sequenceName(String str) {
            return (CopyOfBuilder) super.sequenceName(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.Builder, com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.SequenceVersionStep
        public CopyOfBuilder sequenceVersion(Integer num) {
            return (CopyOfBuilder) super.sequenceVersion(num);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.Builder, com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.SliceOrdinalStep
        public CopyOfBuilder sliceOrdinal(Integer num) {
            return (CopyOfBuilder) super.sliceOrdinal(num);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.Builder, com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.IsLastSliceStep
        public CopyOfBuilder isLastSlice(Boolean bool) {
            return (CopyOfBuilder) super.isLastSlice(bool);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.Builder, com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.EntityReferencesStep
        public CopyOfBuilder entityReferences(List<EntityReference> list) {
            return (CopyOfBuilder) super.entityReferences(list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.Builder, com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.CreatedAtStep
        public CopyOfBuilder createdAt(Temporal.DateTime dateTime) {
            return (CopyOfBuilder) super.createdAt(dateTime);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.Builder, com.amazon.mp3.amplifyqueue.model.QueueSequenceSlice.UpdatedAtStep
        public CopyOfBuilder updatedAt(Temporal.DateTime dateTime) {
            return (CopyOfBuilder) super.updatedAt(dateTime);
        }
    }
}
