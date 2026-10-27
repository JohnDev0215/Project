package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.appsync.ModelMetadata;
import com.amplifyframework.datastore.appsync.ModelWithMetadata;
import com.amplifyframework.hub.HubEvent;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class OutboxMutationEvent<M extends Model> implements HubEvent.Data<OutboxMutationEvent<M>> {
    private final OutboxMutationEventElement<M> element;
    private final String modelName;

    private OutboxMutationEvent(String str, OutboxMutationEventElement<M> outboxMutationEventElement) {
        this.modelName = str;
        this.element = outboxMutationEventElement;
    }

    @NonNull
    public static <M extends Model> OutboxMutationEvent<M> fromPendingMutation(@NonNull PendingMutation<M> pendingMutation) {
        Objects.requireNonNull(pendingMutation);
        return new OutboxMutationEvent<>(pendingMutation.getModelSchema().getName(), new OutboxMutationEventElement(pendingMutation.getMutatedItem(), null, null, null));
    }

    @NonNull
    public static <M extends Model> OutboxMutationEvent<M> create(@NonNull String str, @NonNull ModelWithMetadata<M> modelWithMetadata) {
        Objects.requireNonNull(str);
        Objects.requireNonNull(modelWithMetadata);
        Model model = modelWithMetadata.getModel();
        ModelMetadata syncMetadata = modelWithMetadata.getSyncMetadata();
        return new OutboxMutationEvent<>(str, new OutboxMutationEventElement(model, syncMetadata.getVersion(), syncMetadata.getLastChangedAt() != null ? Long.valueOf(syncMetadata.getLastChangedAt().getSecondsSinceEpoch()) : null, syncMetadata.isDeleted()));
    }

    @NonNull
    public String getModelName() {
        return this.modelName;
    }

    @NonNull
    public OutboxMutationEventElement<M> getElement() {
        return this.element;
    }

    public int hashCode() {
        return (this.modelName.hashCode() * 31) + this.element.hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || OutboxMutationEvent.class != obj.getClass()) {
            return false;
        }
        OutboxMutationEvent outboxMutationEvent = (OutboxMutationEvent) obj;
        return ObjectsCompat.equals(this.modelName, outboxMutationEvent.modelName) && ObjectsCompat.equals(this.element, outboxMutationEvent.element);
    }

    @NonNull
    public String toString() {
        return "OutboxMutationEvent{modelName='" + this.modelName + "', element='" + this.element + "'}";
    }

    @Override // com.amplifyframework.hub.HubEvent.Data
    public HubEvent<OutboxMutationEvent<M>> toHubEvent() {
        if (getElement().getVersion() == null) {
            return HubEvent.create(DataStoreChannelEventName.OUTBOX_MUTATION_ENQUEUED, this);
        }
        return HubEvent.create(DataStoreChannelEventName.OUTBOX_MUTATION_PROCESSED, this);
    }

    public static final class OutboxMutationEventElement<M extends Model> {
        private final Boolean deleted;
        private final Long lastChangedAt;
        private final M model;
        private final Integer version;

        private OutboxMutationEventElement(M m, Integer num, Long l, Boolean bool) {
            this.model = m;
            this.version = num;
            this.lastChangedAt = l;
            this.deleted = bool;
        }

        @Nullable
        public Boolean isDeleted() {
            return this.deleted;
        }

        @Nullable
        public Long getLastChangedAt() {
            return this.lastChangedAt;
        }

        @Nullable
        public Integer getVersion() {
            return this.version;
        }

        @NonNull
        public M getModel() {
            return this.model;
        }

        @NonNull
        public String toString() {
            return "OutboxMutationEventElement{model=" + this.model + ", version=" + this.version + ", lastChangedAt=" + this.lastChangedAt + ", deleted=" + this.deleted + '}';
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || OutboxMutationEventElement.class != obj.getClass()) {
                return false;
            }
            OutboxMutationEventElement outboxMutationEventElement = (OutboxMutationEventElement) obj;
            return getModel().equals(outboxMutationEventElement.getModel()) && ObjectsCompat.equals(getVersion(), outboxMutationEventElement.getVersion()) && ObjectsCompat.equals(getLastChangedAt(), outboxMutationEventElement.getLastChangedAt()) && ObjectsCompat.equals(isDeleted(), outboxMutationEventElement.isDeleted());
        }

        public int hashCode() {
            return ObjectsCompat.hash(getModel(), getVersion(), getLastChangedAt(), isDeleted());
        }
    }
}
