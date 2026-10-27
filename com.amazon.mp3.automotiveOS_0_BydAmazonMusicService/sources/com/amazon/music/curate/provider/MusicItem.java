package com.amazon.music.curate.provider;

import ExternalActionInterface.v1_0.EntityIdType;

/* JADX INFO: loaded from: classes4.dex */
public final class MusicItem {
    private final String entityId;
    private final EntityIdType entityIdType;
    private final EntityType entityType;

    public MusicItem(final EntityType entityType, final EntityIdType entityIdType, final String entityId) {
        this.entityType = entityType;
        this.entityIdType = entityIdType;
        this.entityId = entityId;
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public EntityIdType getEntityIdType() {
        return this.entityIdType;
    }

    public String getEntityId() {
        return this.entityId;
    }
}
