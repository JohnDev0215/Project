package com.amplifyframework.datastore;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.amplifyframework.core.model.Model;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes5.dex */
public final class DataStoreItemChange<T extends Model> {
    private final Initiator initiator;
    private final T item;
    private final Class<T> itemClass;
    private final Type type;
    private final UUID uuid;

    public enum Initiator {
        LOCAL,
        REMOTE
    }

    public enum Type {
        CREATE,
        UPDATE,
        DELETE
    }

    private DataStoreItemChange(UUID uuid, Type type, T t, Class<T> cls, Initiator initiator) {
        this.uuid = uuid;
        this.type = type;
        this.item = t;
        this.itemClass = cls;
        this.initiator = initiator;
    }

    @NonNull
    public UUID uuid() {
        return this.uuid;
    }

    @NonNull
    public Type type() {
        return this.type;
    }

    @NonNull
    public T item() {
        return this.item;
    }

    @NonNull
    public Class<T> itemClass() {
        return this.itemClass;
    }

    @NonNull
    public Initiator initiator() {
        return this.initiator;
    }

    @NonNull
    public static <T extends Model> Builder<T> builder() {
        return new Builder<>();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DataStoreItemChange.class != obj.getClass()) {
            return false;
        }
        DataStoreItemChange dataStoreItemChange = (DataStoreItemChange) obj;
        return this.uuid.equals(dataStoreItemChange.uuid) && this.type == dataStoreItemChange.type && this.item.equals(dataStoreItemChange.item) && this.itemClass.equals(dataStoreItemChange.itemClass) && this.initiator == dataStoreItemChange.initiator;
    }

    public int hashCode() {
        return (((((((this.uuid.hashCode() * 31) + this.type.hashCode()) * 31) + this.item.hashCode()) * 31) + this.itemClass.hashCode()) * 31) + this.initiator.hashCode();
    }

    public String toString() {
        return "DataStoreItemChange{uuid=" + this.uuid + ", type=" + this.type + ", item=" + this.item + ", itemClass=" + this.itemClass + ", initiator=" + this.initiator + '}';
    }

    public static final class Builder<T extends Model> {
        private Initiator initiator;
        private T item;
        private Class<T> itemClass;
        private Type type;
        private UUID uuid;

        @NonNull
        public Builder<T> uuid(@NonNull String str) {
            Objects.requireNonNull(str);
            this.uuid = UUID.fromString(str);
            return this;
        }

        @NonNull
        public Builder<T> randomUuid() {
            this.uuid = null;
            return this;
        }

        @NonNull
        public Builder<T> type(@NonNull Type type) {
            Objects.requireNonNull(type);
            this.type = type;
            return this;
        }

        @NonNull
        public Builder<T> item(@NonNull T t) {
            Objects.requireNonNull(t);
            this.item = t;
            return this;
        }

        @NonNull
        public Builder<T> itemClass(@NonNull Class<T> cls) {
            Objects.requireNonNull(cls);
            this.itemClass = cls;
            return this;
        }

        @NonNull
        public Builder<T> initiator(@NonNull Initiator initiator) {
            Objects.requireNonNull(initiator);
            this.initiator = initiator;
            return this;
        }

        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public DataStoreItemChange<T> build() {
            UUID uuidRandomUUID = this.uuid;
            if (uuidRandomUUID == null) {
                uuidRandomUUID = UUID.randomUUID();
            }
            UUID uuid = uuidRandomUUID;
            randomUuid();
            Objects.requireNonNull(uuid);
            Type type = this.type;
            Objects.requireNonNull(type);
            T t = this.item;
            Objects.requireNonNull(t);
            Class<T> cls = this.itemClass;
            Objects.requireNonNull(cls);
            Initiator initiator = this.initiator;
            Objects.requireNonNull(initiator);
            return new DataStoreItemChange<>(uuid, type, t, cls, initiator);
        }
    }
}
