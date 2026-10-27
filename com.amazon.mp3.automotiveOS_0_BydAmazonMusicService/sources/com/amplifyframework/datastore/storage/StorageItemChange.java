package com.amplifyframework.datastore.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes5.dex */
public final class StorageItemChange<T extends Model> {
    private final UUID changeId;
    private final Initiator initiator;
    private final T item;
    private final ModelSchema modelSchema;
    private final SerializedModel patchItem;
    private final QueryPredicate predicate;
    private final Type type;

    public enum Initiator {
        SYNC_ENGINE,
        DATA_STORE_API
    }

    public enum Type {
        CREATE,
        UPDATE,
        DELETE
    }

    private StorageItemChange(UUID uuid, Initiator initiator, Type type, QueryPredicate queryPredicate, T t, SerializedModel serializedModel, ModelSchema modelSchema) {
        this.changeId = uuid;
        this.initiator = initiator;
        this.type = type;
        this.predicate = queryPredicate;
        this.item = t;
        this.patchItem = serializedModel;
        this.modelSchema = modelSchema;
    }

    @NonNull
    public UUID changeId() {
        return this.changeId;
    }

    @NonNull
    public Initiator initiator() {
        return this.initiator;
    }

    @NonNull
    public Type type() {
        return this.type;
    }

    @NonNull
    public QueryPredicate predicate() {
        return this.predicate;
    }

    @NonNull
    public T item() {
        return this.item;
    }

    @NonNull
    public SerializedModel patchItem() {
        return this.patchItem;
    }

    @NonNull
    public ModelSchema modelSchema() {
        return this.modelSchema;
    }

    @NonNull
    public static <T extends Model> Builder<T> builder() {
        return new Builder<>();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || StorageItemChange.class != obj.getClass()) {
            return false;
        }
        StorageItemChange storageItemChange = (StorageItemChange) obj;
        if (this.changeId.equals(storageItemChange.changeId) && this.initiator == storageItemChange.initiator && this.type == storageItemChange.type && this.predicate.equals(storageItemChange.predicate) && this.item.equals(storageItemChange.item)) {
            return this.modelSchema.equals(storageItemChange.modelSchema);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((this.changeId.hashCode() * 31) + this.initiator.hashCode()) * 31) + this.type.hashCode()) * 31) + this.predicate.hashCode()) * 31) + this.item.hashCode()) * 31) + this.modelSchema.hashCode();
    }

    @NonNull
    public String toString() {
        return "StorageItemChange{changeId=" + this.changeId + ", initiator=" + this.initiator + ", type=" + this.type + ", predicate=" + this.predicate + ", item=" + this.item + ", modelSchema=" + this.modelSchema + '}';
    }

    public static final class Builder<T extends Model> {
        private UUID changeId;
        private Initiator initiator;
        private T item;
        private ModelSchema modelSchema;
        private SerializedModel patchItem;
        private QueryPredicate predicate;
        private Type type;

        @NonNull
        public Builder<T> changeId(@NonNull String str) {
            Objects.requireNonNull(str);
            this.changeId = UUID.fromString(str);
            return this;
        }

        @NonNull
        public Builder<T> randomChangeId() {
            this.changeId = null;
            return this;
        }

        @NonNull
        public Builder<T> initiator(@NonNull Initiator initiator) {
            Objects.requireNonNull(initiator);
            this.initiator = initiator;
            return this;
        }

        @NonNull
        public Builder<T> type(@NonNull Type type) {
            Objects.requireNonNull(type);
            this.type = type;
            return this;
        }

        @NonNull
        public Builder<T> predicate(@NonNull QueryPredicate queryPredicate) {
            Objects.requireNonNull(queryPredicate);
            this.predicate = queryPredicate;
            return this;
        }

        @NonNull
        public Builder<T> item(@NonNull T t) {
            Objects.requireNonNull(t);
            this.item = t;
            return this;
        }

        public Builder<T> patchItem(@NonNull SerializedModel serializedModel) {
            Objects.requireNonNull(serializedModel);
            this.patchItem = serializedModel;
            return this;
        }

        @NonNull
        public Builder<T> modelSchema(@NonNull ModelSchema modelSchema) {
            Objects.requireNonNull(modelSchema);
            this.modelSchema = modelSchema;
            return this;
        }

        @NonNull
        public StorageItemChange<T> build() {
            UUID uuidRandomUUID = this.changeId;
            if (uuidRandomUUID == null) {
                uuidRandomUUID = UUID.randomUUID();
            }
            UUID uuid = uuidRandomUUID;
            randomChangeId();
            Objects.requireNonNull(uuid);
            Initiator initiator = this.initiator;
            Objects.requireNonNull(initiator);
            Type type = this.type;
            Objects.requireNonNull(type);
            QueryPredicate queryPredicate = this.predicate;
            Objects.requireNonNull(queryPredicate);
            QueryPredicate queryPredicate2 = queryPredicate;
            T t = this.item;
            Objects.requireNonNull(t);
            SerializedModel serializedModel = this.patchItem;
            Objects.requireNonNull(serializedModel);
            ModelSchema modelSchema = this.modelSchema;
            Objects.requireNonNull(modelSchema);
            return new StorageItemChange<>(uuid, initiator, type, queryPredicate2, t, serializedModel, modelSchema);
        }
    }
}
