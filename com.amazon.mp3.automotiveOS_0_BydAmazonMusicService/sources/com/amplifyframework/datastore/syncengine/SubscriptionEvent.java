package com.amplifyframework.datastore.syncengine;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.datastore.appsync.ModelWithMetadata;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
final class SubscriptionEvent<T extends Model> {
    private final ModelSchema modelSchema;
    private final ModelWithMetadata<T> modelWithMetadata;
    private final Type type;

    public enum Type {
        CREATE,
        UPDATE,
        DELETE
    }

    private SubscriptionEvent(ModelWithMetadata<T> modelWithMetadata, ModelSchema modelSchema, Type type) {
        this.modelWithMetadata = modelWithMetadata;
        this.modelSchema = modelSchema;
        this.type = type;
    }

    @NonNull
    ModelWithMetadata<T> modelWithMetadata() {
        return this.modelWithMetadata;
    }

    @NonNull
    ModelSchema modelSchema() {
        return this.modelSchema;
    }

    @NonNull
    Type type() {
        return this.type;
    }

    @NonNull
    static <T extends Model> Builder<T> builder() {
        return new Builder<>();
    }

    static final class Builder<T extends Model> {
        private ModelSchema modelSchema;
        private ModelWithMetadata<T> modelWithMetadata;
        private Type type;

        Builder() {
        }

        @NonNull
        Builder<T> modelWithMetadata(@NonNull ModelWithMetadata<T> modelWithMetadata) {
            Objects.requireNonNull(modelWithMetadata);
            this.modelWithMetadata = modelWithMetadata;
            return this;
        }

        @NonNull
        Builder<T> modelSchema(@NonNull ModelSchema modelSchema) {
            Objects.requireNonNull(modelSchema);
            this.modelSchema = modelSchema;
            return this;
        }

        @NonNull
        Builder<T> type(@NonNull Type type) {
            Objects.requireNonNull(type);
            this.type = type;
            return this;
        }

        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        SubscriptionEvent<T> build() {
            return new SubscriptionEvent<>(this.modelWithMetadata, this.modelSchema, this.type);
        }
    }
}
