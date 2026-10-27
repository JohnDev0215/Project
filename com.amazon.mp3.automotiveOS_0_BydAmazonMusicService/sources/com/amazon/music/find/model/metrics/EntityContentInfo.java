package com.amazon.music.find.model.metrics;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.find.common.EntityIdType;
import com.amazon.music.platform.find.common.EntityType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityContentInfo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\tHÆ\u0003J?\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001f\u001a\u00020\tHÖ\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lcom/amazon/music/find/model/metrics/EntityContentInfo;", "", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/platform/find/common/EntityIdType;", "entityPos", "", ContextMappingConstants.ENTITY_ID, "", "entityText", "(Lcom/amazon/music/platform/find/common/EntityType;Lcom/amazon/music/platform/find/common/EntityIdType;ILjava/lang/String;Ljava/lang/String;)V", "getEntityId", "()Ljava/lang/String;", "getEntityIdType", "()Lcom/amazon/music/platform/find/common/EntityIdType;", "getEntityPos", "()I", "getEntityText", "getEntityType", "()Lcom/amazon/music/platform/find/common/EntityType;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class EntityContentInfo {
    private final String entityId;
    private final EntityIdType entityIdType;
    private final int entityPos;
    private final String entityText;
    private final EntityType entityType;

    public static /* synthetic */ EntityContentInfo copy$default(EntityContentInfo entityContentInfo, EntityType entityType, EntityIdType entityIdType, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            entityType = entityContentInfo.entityType;
        }
        if ((i2 & 2) != 0) {
            entityIdType = entityContentInfo.entityIdType;
        }
        EntityIdType entityIdType2 = entityIdType;
        if ((i2 & 4) != 0) {
            i = entityContentInfo.entityPos;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            str = entityContentInfo.entityId;
        }
        String str3 = str;
        if ((i2 & 16) != 0) {
            str2 = entityContentInfo.entityText;
        }
        return entityContentInfo.copy(entityType, entityIdType2, i3, str3, str2);
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
    public final int getEntityPos() {
        return this.entityPos;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getEntityId() {
        return this.entityId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getEntityText() {
        return this.entityText;
    }

    public final EntityContentInfo copy(EntityType entityType, EntityIdType entityIdType, int entityPos, String entityId, String entityText) {
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(entityIdType, "entityIdType");
        return new EntityContentInfo(entityType, entityIdType, entityPos, entityId, entityText);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EntityContentInfo)) {
            return false;
        }
        EntityContentInfo entityContentInfo = (EntityContentInfo) other;
        return this.entityType == entityContentInfo.entityType && this.entityIdType == entityContentInfo.entityIdType && this.entityPos == entityContentInfo.entityPos && Intrinsics.areEqual(this.entityId, entityContentInfo.entityId) && Intrinsics.areEqual(this.entityText, entityContentInfo.entityText);
    }

    public int hashCode() {
        int iHashCode = ((((this.entityType.hashCode() * 31) + this.entityIdType.hashCode()) * 31) + Integer.hashCode(this.entityPos)) * 31;
        String str = this.entityId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.entityText;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "EntityContentInfo(entityType=" + this.entityType + ", entityIdType=" + this.entityIdType + ", entityPos=" + this.entityPos + ", entityId=" + ((Object) this.entityId) + ", entityText=" + ((Object) this.entityText) + ')';
    }

    public EntityContentInfo(EntityType entityType, EntityIdType entityIdType, int i, String str, String str2) {
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(entityIdType, "entityIdType");
        this.entityType = entityType;
        this.entityIdType = entityIdType;
        this.entityPos = i;
        this.entityId = str;
        this.entityText = str2;
    }

    public /* synthetic */ EntityContentInfo(EntityType entityType, EntityIdType entityIdType, int i, String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(entityType, entityIdType, i, (i2 & 8) != 0 ? null : str, (i2 & 16) != 0 ? null : str2);
    }

    public final EntityType getEntityType() {
        return this.entityType;
    }

    public final EntityIdType getEntityIdType() {
        return this.entityIdType;
    }

    public final int getEntityPos() {
        return this.entityPos;
    }

    public final String getEntityId() {
        return this.entityId;
    }

    public final String getEntityText() {
        return this.entityText;
    }
}
