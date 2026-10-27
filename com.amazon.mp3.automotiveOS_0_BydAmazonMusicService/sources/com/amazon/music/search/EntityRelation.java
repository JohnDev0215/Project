package com.amazon.music.search;

import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class EntityRelation {
    private final com.amazon.music.platform.model.EntityRelation entityRelation;

    EntityRelation(com.amazon.music.platform.model.EntityRelation entityRelation) {
        Objects.requireNonNull(entityRelation, "entityRelation cannot be null");
        this.entityRelation = entityRelation;
    }

    public EntityRelationRole getRole() {
        String role = this.entityRelation.getRole();
        role.hashCode();
        if (role.equals("FEATURED_PLAYLIST")) {
            return EntityRelationRole.FEATURED_PLAYLIST;
        }
        if (role.equals("RELATED_PLAYLIST")) {
            return EntityRelationRole.RELATED_PLAYLIST;
        }
        return null;
    }

    public Long getSequenceNumber() {
        return this.entityRelation.getSequenceNumber();
    }
}
