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
@Index(fields = {"customerId", "id"}, name = SQLiteCommandFactory.UNDEFINED)
@ModelConfig(pluralName = "Queues")
public final class Queue implements Model {

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime createdAt;

    @ModelField(isRequired = true, targetType = "ID")
    private final String customerId;

    @ModelField(isRequired = true, targetType = "ID")
    private final String id;

    @ModelField(targetType = "ImageSizeUrlTuple")
    private final List<ImageSizeUrlTuple> images;

    @ModelField(isRequired = true, targetType = "Int")
    private final Integer maxEntitiesPerQueueSequenceSlice;

    @ModelField(targetType = "OnlinePopulationStatusEnum")
    private final OnlinePopulationStatusEnum onlinePopulationStatus;

    @ModelField(isRequired = true, targetType = "ID")
    private final String queueCapabilitySetId;

    @ModelField(isRequired = true, targetType = "QueueSeed")
    private final List<QueueSeed> queueSeeds;

    @ModelField(isRequired = true, targetType = "Int")
    private final Integer queueTtlInSeconds;

    @ModelField(isRequired = true, targetType = "ServiceTierEnum")
    private final ServiceTierEnum serviceTier;

    @ModelField(targetType = "String")
    private final String serviceTierEnumValue;

    @ModelField(targetType = "String")
    private final String thingShadowName;

    @ModelField(targetType = "String")
    private final String title;

    @ModelField(isRequired = true, targetType = "AWSDateTime")
    private final Temporal.DateTime updatedAt;
    public static final QueryField ID = QueryField.field("Queue", "id");
    public static final QueryField CUSTOMER_ID = QueryField.field("Queue", "customerId");
    public static final QueryField SERVICE_TIER = QueryField.field("Queue", "serviceTier");
    public static final QueryField SERVICE_TIER_ENUM_VALUE = QueryField.field("Queue", "serviceTierEnumValue");
    public static final QueryField TITLE = QueryField.field("Queue", "title");
    public static final QueryField IMAGES = QueryField.field("Queue", "images");
    public static final QueryField QUEUE_TTL_IN_SECONDS = QueryField.field("Queue", "queueTtlInSeconds");
    public static final QueryField QUEUE_SEEDS = QueryField.field("Queue", "queueSeeds");
    public static final QueryField QUEUE_CAPABILITY_SET_ID = QueryField.field("Queue", "queueCapabilitySetId");
    public static final QueryField THING_SHADOW_NAME = QueryField.field("Queue", "thingShadowName");
    public static final QueryField ONLINE_POPULATION_STATUS = QueryField.field("Queue", "onlinePopulationStatus");
    public static final QueryField MAX_ENTITIES_PER_QUEUE_SEQUENCE_SLICE = QueryField.field("Queue", "maxEntitiesPerQueueSequenceSlice");
    public static final QueryField CREATED_AT = QueryField.field("Queue", "createdAt");
    public static final QueryField UPDATED_AT = QueryField.field("Queue", "updatedAt");

    public interface BuildStep {
        Queue build();

        BuildStep id(String str);

        BuildStep images(List<ImageSizeUrlTuple> list);

        BuildStep onlinePopulationStatus(OnlinePopulationStatusEnum onlinePopulationStatusEnum);

        BuildStep serviceTierEnumValue(String str);

        BuildStep thingShadowName(String str);

        BuildStep title(String str);
    }

    public interface CreatedAtStep {
        UpdatedAtStep createdAt(Temporal.DateTime dateTime);
    }

    public interface CustomerIdStep {
        ServiceTierStep customerId(String str);
    }

    public interface MaxEntitiesPerQueueSequenceSliceStep {
        CreatedAtStep maxEntitiesPerQueueSequenceSlice(Integer num);
    }

    public interface QueueCapabilitySetIdStep {
        MaxEntitiesPerQueueSequenceSliceStep queueCapabilitySetId(String str);
    }

    public interface QueueSeedsStep {
        QueueCapabilitySetIdStep queueSeeds(List<QueueSeed> list);
    }

    public interface QueueTtlInSecondsStep {
        QueueSeedsStep queueTtlInSeconds(Integer num);
    }

    public interface ServiceTierStep {
        QueueTtlInSecondsStep serviceTier(ServiceTierEnum serviceTierEnum);
    }

    public interface UpdatedAtStep {
        BuildStep updatedAt(Temporal.DateTime dateTime);
    }

    public String getId() {
        return this.id;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public ServiceTierEnum getServiceTier() {
        return this.serviceTier;
    }

    public String getServiceTierEnumValue() {
        return this.serviceTierEnumValue;
    }

    public String getTitle() {
        return this.title;
    }

    public List<ImageSizeUrlTuple> getImages() {
        return this.images;
    }

    public Integer getQueueTtlInSeconds() {
        return this.queueTtlInSeconds;
    }

    public List<QueueSeed> getQueueSeeds() {
        return this.queueSeeds;
    }

    public String getQueueCapabilitySetId() {
        return this.queueCapabilitySetId;
    }

    public String getThingShadowName() {
        return this.thingShadowName;
    }

    public OnlinePopulationStatusEnum getOnlinePopulationStatus() {
        return this.onlinePopulationStatus;
    }

    public Integer getMaxEntitiesPerQueueSequenceSlice() {
        return this.maxEntitiesPerQueueSequenceSlice;
    }

    public Temporal.DateTime getCreatedAt() {
        return this.createdAt;
    }

    public Temporal.DateTime getUpdatedAt() {
        return this.updatedAt;
    }

    private Queue(String str, String str2, ServiceTierEnum serviceTierEnum, String str3, String str4, List<ImageSizeUrlTuple> list, Integer num, List<QueueSeed> list2, String str5, String str6, OnlinePopulationStatusEnum onlinePopulationStatusEnum, Integer num2, Temporal.DateTime dateTime, Temporal.DateTime dateTime2) {
        this.id = str;
        this.customerId = str2;
        this.serviceTier = serviceTierEnum;
        this.serviceTierEnumValue = str3;
        this.title = str4;
        this.images = list;
        this.queueTtlInSeconds = num;
        this.queueSeeds = list2;
        this.queueCapabilitySetId = str5;
        this.thingShadowName = str6;
        this.onlinePopulationStatus = onlinePopulationStatusEnum;
        this.maxEntitiesPerQueueSequenceSlice = num2;
        this.createdAt = dateTime;
        this.updatedAt = dateTime2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Queue.class != obj.getClass()) {
            return false;
        }
        Queue queue = (Queue) obj;
        return ObjectsCompat.equals(getId(), queue.getId()) && ObjectsCompat.equals(getCustomerId(), queue.getCustomerId()) && ObjectsCompat.equals(getServiceTier(), queue.getServiceTier()) && ObjectsCompat.equals(getServiceTierEnumValue(), queue.getServiceTierEnumValue()) && ObjectsCompat.equals(getTitle(), queue.getTitle()) && ObjectsCompat.equals(getImages(), queue.getImages()) && ObjectsCompat.equals(getQueueTtlInSeconds(), queue.getQueueTtlInSeconds()) && ObjectsCompat.equals(getQueueSeeds(), queue.getQueueSeeds()) && ObjectsCompat.equals(getQueueCapabilitySetId(), queue.getQueueCapabilitySetId()) && ObjectsCompat.equals(getThingShadowName(), queue.getThingShadowName()) && ObjectsCompat.equals(getOnlinePopulationStatus(), queue.getOnlinePopulationStatus()) && ObjectsCompat.equals(getMaxEntitiesPerQueueSequenceSlice(), queue.getMaxEntitiesPerQueueSequenceSlice()) && ObjectsCompat.equals(getCreatedAt(), queue.getCreatedAt()) && ObjectsCompat.equals(getUpdatedAt(), queue.getUpdatedAt());
    }

    public int hashCode() {
        return (getId() + getCustomerId() + getServiceTier() + getServiceTierEnumValue() + getTitle() + getImages() + getQueueTtlInSeconds() + getQueueSeeds() + getQueueCapabilitySetId() + getThingShadowName() + getOnlinePopulationStatus() + getMaxEntitiesPerQueueSequenceSlice() + getCreatedAt() + getUpdatedAt()).hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue {");
        sb.append("id=" + String.valueOf(getId()) + ", ");
        sb.append("customerId=" + String.valueOf(getCustomerId()) + ", ");
        sb.append("serviceTier=" + String.valueOf(getServiceTier()) + ", ");
        sb.append("serviceTierEnumValue=" + String.valueOf(getServiceTierEnumValue()) + ", ");
        sb.append("title=" + String.valueOf(getTitle()) + ", ");
        sb.append("images=" + String.valueOf(getImages()) + ", ");
        sb.append("queueTtlInSeconds=" + String.valueOf(getQueueTtlInSeconds()) + ", ");
        sb.append("queueSeeds=" + String.valueOf(getQueueSeeds()) + ", ");
        sb.append("queueCapabilitySetId=" + String.valueOf(getQueueCapabilitySetId()) + ", ");
        sb.append("thingShadowName=" + String.valueOf(getThingShadowName()) + ", ");
        sb.append("onlinePopulationStatus=" + String.valueOf(getOnlinePopulationStatus()) + ", ");
        sb.append("maxEntitiesPerQueueSequenceSlice=" + String.valueOf(getMaxEntitiesPerQueueSequenceSlice()) + ", ");
        sb.append("createdAt=" + String.valueOf(getCreatedAt()) + ", ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("updatedAt=");
        sb2.append(String.valueOf(getUpdatedAt()));
        sb.append(sb2.toString());
        sb.append("}");
        return sb.toString();
    }

    public static CustomerIdStep builder() {
        return new Builder();
    }

    public static class Builder implements CustomerIdStep, ServiceTierStep, QueueTtlInSecondsStep, QueueSeedsStep, QueueCapabilitySetIdStep, MaxEntitiesPerQueueSequenceSliceStep, CreatedAtStep, UpdatedAtStep, BuildStep {
        private Temporal.DateTime createdAt;
        private String customerId;
        private String id;
        private List<ImageSizeUrlTuple> images;
        private Integer maxEntitiesPerQueueSequenceSlice;
        private OnlinePopulationStatusEnum onlinePopulationStatus;
        private String queueCapabilitySetId;
        private List<QueueSeed> queueSeeds;
        private Integer queueTtlInSeconds;
        private ServiceTierEnum serviceTier;
        private String serviceTierEnumValue;
        private String thingShadowName;
        private String title;
        private Temporal.DateTime updatedAt;

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.BuildStep
        public Queue build() {
            String string = this.id;
            if (string == null) {
                string = UUID.randomUUID().toString();
            }
            return new Queue(string, this.customerId, this.serviceTier, this.serviceTierEnumValue, this.title, this.images, this.queueTtlInSeconds, this.queueSeeds, this.queueCapabilitySetId, this.thingShadowName, this.onlinePopulationStatus, this.maxEntitiesPerQueueSequenceSlice, this.createdAt, this.updatedAt);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.CustomerIdStep
        public ServiceTierStep customerId(String str) {
            Objects.requireNonNull(str);
            this.customerId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.ServiceTierStep
        public QueueTtlInSecondsStep serviceTier(ServiceTierEnum serviceTierEnum) {
            Objects.requireNonNull(serviceTierEnum);
            this.serviceTier = serviceTierEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.QueueTtlInSecondsStep
        public QueueSeedsStep queueTtlInSeconds(Integer num) {
            Objects.requireNonNull(num);
            this.queueTtlInSeconds = num;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.QueueSeedsStep
        public QueueCapabilitySetIdStep queueSeeds(List<QueueSeed> list) {
            Objects.requireNonNull(list);
            this.queueSeeds = list;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.QueueCapabilitySetIdStep
        public MaxEntitiesPerQueueSequenceSliceStep queueCapabilitySetId(String str) {
            Objects.requireNonNull(str);
            this.queueCapabilitySetId = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.MaxEntitiesPerQueueSequenceSliceStep
        public CreatedAtStep maxEntitiesPerQueueSequenceSlice(Integer num) {
            Objects.requireNonNull(num);
            this.maxEntitiesPerQueueSequenceSlice = num;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.CreatedAtStep
        public UpdatedAtStep createdAt(Temporal.DateTime dateTime) {
            Objects.requireNonNull(dateTime);
            this.createdAt = dateTime;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.UpdatedAtStep
        public BuildStep updatedAt(Temporal.DateTime dateTime) {
            Objects.requireNonNull(dateTime);
            this.updatedAt = dateTime;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.BuildStep
        public BuildStep serviceTierEnumValue(String str) {
            this.serviceTierEnumValue = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.BuildStep
        public BuildStep title(String str) {
            this.title = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.BuildStep
        public BuildStep images(List<ImageSizeUrlTuple> list) {
            this.images = list;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.BuildStep
        public BuildStep thingShadowName(String str) {
            this.thingShadowName = str;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.BuildStep
        public BuildStep onlinePopulationStatus(OnlinePopulationStatusEnum onlinePopulationStatusEnum) {
            this.onlinePopulationStatus = onlinePopulationStatusEnum;
            return this;
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.BuildStep
        public BuildStep id(String str) {
            this.id = str;
            return this;
        }
    }

    public final class CopyOfBuilder extends Builder {
        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.BuildStep
        public /* bridge */ /* synthetic */ BuildStep images(List list) {
            return images((List<ImageSizeUrlTuple>) list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.QueueSeedsStep
        public /* bridge */ /* synthetic */ QueueCapabilitySetIdStep queueSeeds(List list) {
            return queueSeeds((List<QueueSeed>) list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.CustomerIdStep
        public CopyOfBuilder customerId(String str) {
            return (CopyOfBuilder) super.customerId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.ServiceTierStep
        public CopyOfBuilder serviceTier(ServiceTierEnum serviceTierEnum) {
            return (CopyOfBuilder) super.serviceTier(serviceTierEnum);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.QueueTtlInSecondsStep
        public CopyOfBuilder queueTtlInSeconds(Integer num) {
            return (CopyOfBuilder) super.queueTtlInSeconds(num);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.QueueSeedsStep
        public CopyOfBuilder queueSeeds(List<QueueSeed> list) {
            return (CopyOfBuilder) super.queueSeeds(list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.QueueCapabilitySetIdStep
        public CopyOfBuilder queueCapabilitySetId(String str) {
            return (CopyOfBuilder) super.queueCapabilitySetId(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.MaxEntitiesPerQueueSequenceSliceStep
        public CopyOfBuilder maxEntitiesPerQueueSequenceSlice(Integer num) {
            return (CopyOfBuilder) super.maxEntitiesPerQueueSequenceSlice(num);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.CreatedAtStep
        public CopyOfBuilder createdAt(Temporal.DateTime dateTime) {
            return (CopyOfBuilder) super.createdAt(dateTime);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.UpdatedAtStep
        public CopyOfBuilder updatedAt(Temporal.DateTime dateTime) {
            return (CopyOfBuilder) super.updatedAt(dateTime);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.BuildStep
        public CopyOfBuilder serviceTierEnumValue(String str) {
            return (CopyOfBuilder) super.serviceTierEnumValue(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.BuildStep
        public CopyOfBuilder title(String str) {
            return (CopyOfBuilder) super.title(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.BuildStep
        public CopyOfBuilder images(List<ImageSizeUrlTuple> list) {
            return (CopyOfBuilder) super.images(list);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.BuildStep
        public CopyOfBuilder thingShadowName(String str) {
            return (CopyOfBuilder) super.thingShadowName(str);
        }

        @Override // com.amazon.mp3.amplifyqueue.model.Queue.Builder, com.amazon.mp3.amplifyqueue.model.Queue.BuildStep
        public CopyOfBuilder onlinePopulationStatus(OnlinePopulationStatusEnum onlinePopulationStatusEnum) {
            return (CopyOfBuilder) super.onlinePopulationStatus(onlinePopulationStatusEnum);
        }
    }
}
