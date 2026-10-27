package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes5.dex */
@ModelConfig(type = Model.Type.SYSTEM)
public final class LastSyncMetadata implements Model {

    @ModelField(isRequired = true, targetType = "ID")
    private final String id;

    @ModelField(isRequired = true, targetType = "AWSTimestamp")
    private final Long lastSyncTime;

    @ModelField(isRequired = true, targetType = "String")
    private final String lastSyncType;

    @ModelField(isRequired = true, targetType = "String")
    private final String modelClassName;

    private LastSyncMetadata(String str, String str2, Long l, SyncType syncType) {
        this.id = str;
        this.modelClassName = str2;
        this.lastSyncTime = l;
        this.lastSyncType = syncType.name();
    }

    public static <T extends Model> LastSyncMetadata baseSyncedAt(@NonNull String str, @Nullable long j) {
        Objects.requireNonNull(str);
        return create(str, Long.valueOf(j), SyncType.BASE);
    }

    static <T extends Model> LastSyncMetadata deltaSyncedAt(@NonNull String str, @Nullable long j) {
        Objects.requireNonNull(str);
        return create(str, Long.valueOf(j), SyncType.DELTA);
    }

    public static <T extends Model> LastSyncMetadata neverSynced(@NonNull String str) {
        Objects.requireNonNull(str);
        return create(str, null, SyncType.BASE);
    }

    static <T extends Model> LastSyncMetadata create(@NonNull String str, @Nullable Long l, @NonNull SyncType syncType) {
        Objects.requireNonNull(str);
        return new LastSyncMetadata(hash(str), str, l, syncType);
    }

    @Override // com.amplifyframework.core.model.Model
    @NonNull
    public String resolveIdentifier() {
        return this.id;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    @NonNull
    String getModelClassName() {
        return this.modelClassName;
    }

    public Long getLastSyncTime() {
        return this.lastSyncTime;
    }

    public String getLastSyncType() {
        return this.lastSyncType;
    }

    private static String hash(@NonNull String str) {
        return UUID.nameUUIDFromBytes(str.getBytes()).toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LastSyncMetadata.class != obj.getClass()) {
            return false;
        }
        LastSyncMetadata lastSyncMetadata = (LastSyncMetadata) obj;
        if (ObjectsCompat.equals(this.id, lastSyncMetadata.id) && ObjectsCompat.equals(this.modelClassName, lastSyncMetadata.modelClassName) && ObjectsCompat.equals(this.lastSyncType, lastSyncMetadata.lastSyncType)) {
            return ObjectsCompat.equals(this.lastSyncTime, lastSyncMetadata.lastSyncTime);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.id.hashCode() * 31) + this.modelClassName.hashCode()) * 31) + this.lastSyncTime.hashCode()) * 31) + this.lastSyncType.hashCode();
    }

    public String toString() {
        return "LastSyncMetadata{id='" + this.id + "', modelClassName='" + this.modelClassName + "', lastSyncTime=" + this.lastSyncTime + ", lastSyncType=" + this.lastSyncType + '}';
    }
}
