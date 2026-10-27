package com.amazon.music.find.model;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityMetaData.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\tHÆ\u0003J5\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0007HÖ\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/find/model/EntityMetaData;", "", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID, "", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;)V", "getActionType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "getEntityId", "()Ljava/lang/String;", "getEntityIdType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", "getEntityType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class EntityMetaData {
    private final ActionType actionType;
    private final String entityId;
    private final EntityIdType entityIdType;
    private final EntityType entityType;

    public static /* synthetic */ EntityMetaData copy$default(EntityMetaData entityMetaData, EntityType entityType, EntityIdType entityIdType, String str, ActionType actionType, int i, Object obj) {
        if ((i & 1) != 0) {
            entityType = entityMetaData.entityType;
        }
        if ((i & 2) != 0) {
            entityIdType = entityMetaData.entityIdType;
        }
        if ((i & 4) != 0) {
            str = entityMetaData.entityId;
        }
        if ((i & 8) != 0) {
            actionType = entityMetaData.actionType;
        }
        return entityMetaData.copy(entityType, entityIdType, str, actionType);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final EntityType getEntityType() {
        return this.entityType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final EntityIdType getEntityIdType() {
        return this.entityIdType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getEntityId() {
        return this.entityId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ActionType getActionType() {
        return this.actionType;
    }

    public final EntityMetaData copy(EntityType entityType, EntityIdType entityIdType, String entityId, ActionType actionType) {
        Intrinsics.checkNotNullParameter(entityIdType, "entityIdType");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        return new EntityMetaData(entityType, entityIdType, entityId, actionType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EntityMetaData)) {
            return false;
        }
        EntityMetaData entityMetaData = (EntityMetaData) other;
        return this.entityType == entityMetaData.entityType && this.entityIdType == entityMetaData.entityIdType && Intrinsics.areEqual(this.entityId, entityMetaData.entityId) && this.actionType == entityMetaData.actionType;
    }

    public int hashCode() {
        EntityType entityType = this.entityType;
        int iHashCode = (((((entityType == null ? 0 : entityType.hashCode()) * 31) + this.entityIdType.hashCode()) * 31) + this.entityId.hashCode()) * 31;
        ActionType actionType = this.actionType;
        return iHashCode + (actionType != null ? actionType.hashCode() : 0);
    }

    public String toString() {
        return "EntityMetaData(entityType=" + this.entityType + ", entityIdType=" + this.entityIdType + ", entityId=" + this.entityId + ", actionType=" + this.actionType + ')';
    }

    public EntityMetaData(EntityType entityType, EntityIdType entityIdType, String entityId, ActionType actionType) {
        Intrinsics.checkNotNullParameter(entityIdType, "entityIdType");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        this.entityType = entityType;
        this.entityIdType = entityIdType;
        this.entityId = entityId;
        this.actionType = actionType;
    }

    public /* synthetic */ EntityMetaData(EntityType entityType, EntityIdType entityIdType, String str, ActionType actionType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(entityType, entityIdType, str, (i & 8) != 0 ? null : actionType);
    }

    public final EntityType getEntityType() {
        return this.entityType;
    }

    public final EntityIdType getEntityIdType() {
        return this.entityIdType;
    }

    public final String getEntityId() {
        return this.entityId;
    }

    public final ActionType getActionType() {
        return this.actionType;
    }
}
