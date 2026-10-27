package com.amplifyframework.datastore.appsync;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.Model;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelWithMetadata<M extends Model> {
    private final M model;
    private final ModelMetadata syncMetadata;

    public ModelWithMetadata(@NonNull M m, @NonNull ModelMetadata modelMetadata) {
        Objects.requireNonNull(m);
        this.model = m;
        Objects.requireNonNull(modelMetadata);
        this.syncMetadata = new ModelMetadata(m.getModelName() + "|" + m.getPrimaryKeyString(), modelMetadata.isDeleted(), modelMetadata.getVersion(), modelMetadata.getLastChangedAt());
    }

    @NonNull
    public M getModel() {
        return this.model;
    }

    @NonNull
    public ModelMetadata getSyncMetadata() {
        return this.syncMetadata;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ModelWithMetadata.class != obj.getClass()) {
            return false;
        }
        ModelWithMetadata modelWithMetadata = (ModelWithMetadata) obj;
        if (ObjectsCompat.equals(this.model, modelWithMetadata.model)) {
            return ObjectsCompat.equals(this.syncMetadata, modelWithMetadata.syncMetadata);
        }
        return false;
    }

    public int hashCode() {
        M m = this.model;
        int iHashCode = (m != null ? m.hashCode() : 0) * 31;
        ModelMetadata modelMetadata = this.syncMetadata;
        return iHashCode + (modelMetadata != null ? modelMetadata.hashCode() : 0);
    }

    public String toString() {
        return "ModelWithMetadata{model=" + this.model + ", syncMetadata=" + this.syncMetadata + '}';
    }
}
