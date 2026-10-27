package com.amplifyframework.datastore.events;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.hub.HubEvent;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelSyncedEvent implements HubEvent.Data<ModelSyncedEvent> {
    private final int added;
    private final int deleted;
    private final boolean isDeltaSync;
    private final boolean isFullSync;
    private final String model;
    private final int updated;

    public ModelSyncedEvent(String str, boolean z, int i, int i2, int i3) {
        this.added = i;
        this.updated = i2;
        this.deleted = i3;
        this.model = str;
        this.isFullSync = z;
        this.isDeltaSync = !z;
    }

    public String getModel() {
        return this.model;
    }

    public int getAdded() {
        return this.added;
    }

    public int getUpdated() {
        return this.updated;
    }

    public int getDeleted() {
        return this.deleted;
    }

    public boolean isFullSync() {
        return this.isFullSync;
    }

    public boolean isDeltaSync() {
        return this.isDeltaSync;
    }

    @Override // com.amplifyframework.hub.HubEvent.Data
    public HubEvent<ModelSyncedEvent> toHubEvent() {
        return HubEvent.create(DataStoreChannelEventName.MODEL_SYNCED, this);
    }

    @NonNull
    public String toString() {
        return "ModelSyncedEvent{model=" + this.model + ", isFullSync=" + this.isFullSync + ", isDeltaSync=" + this.isDeltaSync + ", added=" + this.added + ", updated=" + this.updated + ", deleted=" + this.deleted + '}';
    }

    public int hashCode() {
        String str = this.model;
        return ((((((((((str != null ? str.hashCode() : 0) * 31) + Boolean.valueOf(this.isFullSync).hashCode()) * 31) + Boolean.valueOf(this.isDeltaSync).hashCode()) * 31) + Integer.valueOf(this.added).intValue()) * 31) + Integer.valueOf(this.updated).intValue()) * 31) + Integer.valueOf(this.deleted).intValue();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ModelSyncedEvent.class != obj.getClass()) {
            return false;
        }
        ModelSyncedEvent modelSyncedEvent = (ModelSyncedEvent) obj;
        if (ObjectsCompat.equals(this.model, modelSyncedEvent.model) && ObjectsCompat.equals(Boolean.valueOf(this.isFullSync), Boolean.valueOf(modelSyncedEvent.isFullSync)) && ObjectsCompat.equals(Boolean.valueOf(this.isDeltaSync), Boolean.valueOf(modelSyncedEvent.isDeltaSync)) && ObjectsCompat.equals(Integer.valueOf(this.added), Integer.valueOf(modelSyncedEvent.added)) && ObjectsCompat.equals(Integer.valueOf(this.updated), Integer.valueOf(modelSyncedEvent.updated))) {
            return ObjectsCompat.equals(Integer.valueOf(this.deleted), Integer.valueOf(modelSyncedEvent.deleted));
        }
        return false;
    }
}
