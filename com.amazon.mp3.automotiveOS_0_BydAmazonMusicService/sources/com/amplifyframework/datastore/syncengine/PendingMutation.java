package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import com.amplifyframework.datastore.DataStoreException;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class PendingMutation<T extends Model> implements Comparable<PendingMutation<? extends Model>> {
    private final ModelSchema modelSchema;
    private final T mutatedItem;
    private final TimeBasedUuid mutationId;
    private final Type mutationType;
    private final QueryPredicate predicate;

    interface Converter {
        <T extends Model> PendingMutation<T> fromRecord(PersistentRecord persistentRecord) throws DataStoreException;

        <T extends Model> PersistentRecord toRecord(PendingMutation<T> pendingMutation);
    }

    enum Type {
        CREATE,
        UPDATE,
        DELETE
    }

    private PendingMutation(TimeBasedUuid timeBasedUuid, T t, ModelSchema modelSchema, Type type, QueryPredicate queryPredicate) {
        this.mutationId = timeBasedUuid;
        this.mutatedItem = t;
        this.modelSchema = modelSchema;
        this.mutationType = type;
        this.predicate = queryPredicate;
    }

    @NonNull
    static <T extends Model> PendingMutation<T> instance(@NonNull TimeBasedUuid timeBasedUuid, @NonNull T t, @NonNull ModelSchema modelSchema, @NonNull Type type, @NonNull QueryPredicate queryPredicate) {
        Objects.requireNonNull(timeBasedUuid);
        Objects.requireNonNull(t);
        Objects.requireNonNull(modelSchema);
        Objects.requireNonNull(type);
        Objects.requireNonNull(queryPredicate);
        return new PendingMutation<>(timeBasedUuid, t, modelSchema, type, queryPredicate);
    }

    @NonNull
    static <T extends Model> PendingMutation<T> instance(@NonNull T t, @NonNull ModelSchema modelSchema, @NonNull Type type, @NonNull QueryPredicate queryPredicate) {
        return instance(TimeBasedUuid.create(), t, modelSchema, type, queryPredicate);
    }

    @NonNull
    static <T extends Model> PendingMutation<T> creation(@NonNull T t, @NonNull ModelSchema modelSchema) {
        return instance(t, modelSchema, Type.CREATE, QueryPredicates.all());
    }

    @NonNull
    static <T extends Model> PendingMutation<T> update(@NonNull T t, @NonNull ModelSchema modelSchema) {
        return instance(t, modelSchema, Type.UPDATE, QueryPredicates.all());
    }

    @NonNull
    static <T extends Model> PendingMutation<T> update(@NonNull T t, @NonNull ModelSchema modelSchema, @NonNull QueryPredicate queryPredicate) {
        return instance(t, modelSchema, Type.UPDATE, queryPredicate);
    }

    @NonNull
    static <T extends Model> PendingMutation<T> deletion(@NonNull T t, @NonNull ModelSchema modelSchema) {
        return instance(t, modelSchema, Type.DELETE, QueryPredicates.all());
    }

    @NonNull
    static <T extends Model> PendingMutation<T> deletion(@NonNull T t, @NonNull ModelSchema modelSchema, @NonNull QueryPredicate queryPredicate) {
        return instance(t, modelSchema, Type.DELETE, queryPredicate);
    }

    @NonNull
    TimeBasedUuid getMutationId() {
        return this.mutationId;
    }

    @NonNull
    public T getMutatedItem() {
        return this.mutatedItem;
    }

    @NonNull
    ModelSchema getModelSchema() {
        return this.modelSchema;
    }

    @NonNull
    Type getMutationType() {
        return this.mutationType;
    }

    @NonNull
    QueryPredicate getPredicate() {
        return this.predicate;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PendingMutation.class != obj.getClass()) {
            return false;
        }
        PendingMutation pendingMutation = (PendingMutation) obj;
        return ObjectsCompat.equals(this.mutationId, pendingMutation.mutationId) && ObjectsCompat.equals(this.mutatedItem, pendingMutation.mutatedItem) && ObjectsCompat.equals(this.modelSchema, pendingMutation.modelSchema) && ObjectsCompat.equals(this.mutationType, pendingMutation.mutationType) && ObjectsCompat.equals(this.predicate, pendingMutation.predicate);
    }

    public int hashCode() {
        return (((((((this.mutationId.hashCode() * 31) + this.mutatedItem.hashCode()) * 31) + this.modelSchema.hashCode()) * 31) + this.mutationType.hashCode()) * 31) + this.predicate.hashCode();
    }

    @NonNull
    public String toString() {
        return "PendingMutation{mutatedItem=" + this.mutatedItem + ", mutationType=" + this.mutationType + ", mutationId=" + this.mutationId + ", predicate=" + this.predicate + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull PendingMutation<? extends Model> pendingMutation) {
        Objects.requireNonNull(pendingMutation);
        return this.mutationId.compareTo(pendingMutation.getMutationId());
    }

    @Index(fields = {"containedModelClassName"}, name = "containedModelClassNameBasedIndex")
    @ModelConfig(pluralName = "PersistentRecords", type = Model.Type.SYSTEM)
    public static final class PersistentRecord implements Model, Comparable<PersistentRecord> {

        @ModelField(isRequired = true, targetType = "String")
        private final String containedModelClassName;

        @ModelField(isRequired = true, targetType = "String")
        private final String containedModelId;

        @ModelField(isRequired = true, targetType = "ID")
        private final String id;

        @ModelField(isRequired = true, targetType = "String")
        private final String serializedMutationData;

        private PersistentRecord(String str, String str2, String str3, String str4) {
            this.id = str;
            this.containedModelId = str2;
            this.serializedMutationData = str3;
            this.containedModelClassName = str4;
        }

        @Override // com.amplifyframework.core.model.Model
        @NonNull
        public String resolveIdentifier() {
            return this.id;
        }

        @NonNull
        String getContainedModelId() {
            return this.containedModelId;
        }

        @NonNull
        String getSerializedMutationData() {
            return this.serializedMutationData;
        }

        @NonNull
        String getContainedModelClassName() {
            return this.containedModelClassName;
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || PersistentRecord.class != obj.getClass()) {
                return false;
            }
            PersistentRecord persistentRecord = (PersistentRecord) obj;
            return ObjectsCompat.equals(this.id, persistentRecord.id) && ObjectsCompat.equals(this.containedModelId, persistentRecord.containedModelId) && ObjectsCompat.equals(this.serializedMutationData, persistentRecord.serializedMutationData) && ObjectsCompat.equals(this.containedModelClassName, persistentRecord.containedModelClassName);
        }

        public int hashCode() {
            return (((((this.id.hashCode() * 31) + this.containedModelId.hashCode()) * 31) + this.serializedMutationData.hashCode()) * 31) + this.containedModelClassName.hashCode();
        }

        @NonNull
        public String toString() {
            return "Record{id='" + this.id + "', containedModelId='" + this.containedModelId + "', serializedMutationData='" + this.serializedMutationData + "', containedModelClassName='" + this.containedModelClassName + "'}";
        }

        @NonNull
        public static <T extends Model> Builder<T> builder() {
            return new Builder<>();
        }

        @Override // java.lang.Comparable
        public int compareTo(@NonNull PersistentRecord persistentRecord) {
            return TimeBasedUuid.fromString(resolveIdentifier()).compareTo(TimeBasedUuid.fromString(persistentRecord.resolveIdentifier()));
        }

        static final class Builder<T extends Model> {
            private String containedModelClassName;
            private String containedModelId;
            private TimeBasedUuid mutationId;
            private String serializedMutationData;

            Builder() {
            }

            @NonNull
            Builder<T> mutationId(@NonNull TimeBasedUuid timeBasedUuid) {
                Objects.requireNonNull(timeBasedUuid);
                this.mutationId = timeBasedUuid;
                return this;
            }

            @NonNull
            Builder<T> containedModelId(@NonNull String str) {
                Objects.requireNonNull(str);
                this.containedModelId = str;
                return this;
            }

            @NonNull
            Builder<T> serializedMutationData(@NonNull String str) {
                Objects.requireNonNull(str);
                this.serializedMutationData = str;
                return this;
            }

            @NonNull
            Builder<T> containedModelClassName(@NonNull String str) {
                Objects.requireNonNull(str);
                this.containedModelClassName = str;
                return this;
            }

            @NonNull
            PersistentRecord build() {
                TimeBasedUuid timeBasedUuid = this.mutationId;
                Objects.requireNonNull(timeBasedUuid);
                String string = timeBasedUuid.toString();
                String str = this.containedModelId;
                Objects.requireNonNull(str);
                String str2 = this.serializedMutationData;
                Objects.requireNonNull(str2);
                String str3 = this.containedModelClassName;
                Objects.requireNonNull(str3);
                return new PersistentRecord(string, str, str2, str3);
            }
        }
    }
}
