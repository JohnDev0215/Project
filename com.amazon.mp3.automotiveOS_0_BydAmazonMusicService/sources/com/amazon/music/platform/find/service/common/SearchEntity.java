package com.amazon.music.platform.find.service.common;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.find.common.EntityIdType;
import com.amazon.music.platform.find.common.EntityType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchEntity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b&\u0010'J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0018\u0010\fR\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u001a\u0010\fR\u0019\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR$\u0010 \u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006("}, d2 = {"Lcom/amazon/music/platform/find/service/common/SearchEntity;", "", "", "toString", "", "hashCode", "other", "", "equals", ContextMappingConstants.ENTITY_ID, "Ljava/lang/String;", "getEntityId", "()Ljava/lang/String;", "Lcom/amazon/music/platform/find/common/EntityIdType;", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/platform/find/common/EntityIdType;", "getEntityIdType", "()Lcom/amazon/music/platform/find/common/EntityIdType;", "Lcom/amazon/music/platform/find/common/EntityType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", "getEntityType", "()Lcom/amazon/music/platform/find/common/EntityType;", "imageUrl", "getImageUrl", "contentUrl", "getContentUrl", "Lcom/amazon/music/platform/find/service/common/EntityProperties;", "entityProperties", "Lcom/amazon/music/platform/find/service/common/EntityProperties;", "getEntityProperties", "()Lcom/amazon/music/platform/find/service/common/EntityProperties;", "didNavigateFromSearch", "Ljava/lang/Boolean;", "getDidNavigateFromSearch", "()Ljava/lang/Boolean;", "setDidNavigateFromSearch", "(Ljava/lang/Boolean;)V", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/find/common/EntityIdType;Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/find/service/common/EntityProperties;Ljava/lang/Boolean;)V", "MusicPlatformFind_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class SearchEntity {
    private final String contentUrl;
    private Boolean didNavigateFromSearch;
    private final String entityId;
    private final EntityIdType entityIdType;
    private final EntityProperties entityProperties;
    private final EntityType entityType;
    private final String imageUrl;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchEntity)) {
            return false;
        }
        SearchEntity searchEntity = (SearchEntity) other;
        return Intrinsics.areEqual(this.entityId, searchEntity.entityId) && this.entityIdType == searchEntity.entityIdType && this.entityType == searchEntity.entityType && Intrinsics.areEqual(this.imageUrl, searchEntity.imageUrl) && Intrinsics.areEqual(this.contentUrl, searchEntity.contentUrl) && Intrinsics.areEqual(this.entityProperties, searchEntity.entityProperties) && Intrinsics.areEqual(this.didNavigateFromSearch, searchEntity.didNavigateFromSearch);
    }

    public int hashCode() {
        int iHashCode = ((((((this.entityId.hashCode() * 31) + this.entityIdType.hashCode()) * 31) + this.entityType.hashCode()) * 31) + this.imageUrl.hashCode()) * 31;
        String str = this.contentUrl;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        EntityProperties entityProperties = this.entityProperties;
        int iHashCode3 = (iHashCode2 + (entityProperties == null ? 0 : entityProperties.hashCode())) * 31;
        Boolean bool = this.didNavigateFromSearch;
        return iHashCode3 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "SearchEntity(entityId=" + this.entityId + ", entityIdType=" + this.entityIdType + ", entityType=" + this.entityType + ", imageUrl=" + this.imageUrl + ", contentUrl=" + this.contentUrl + ", entityProperties=" + this.entityProperties + ", didNavigateFromSearch=" + this.didNavigateFromSearch + ')';
    }

    public SearchEntity(String entityId, EntityIdType entityIdType, EntityType entityType, String imageUrl, String str, EntityProperties entityProperties, Boolean bool) {
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(entityIdType, "entityIdType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        this.entityId = entityId;
        this.entityIdType = entityIdType;
        this.entityType = entityType;
        this.imageUrl = imageUrl;
        this.contentUrl = str;
        this.entityProperties = entityProperties;
        this.didNavigateFromSearch = bool;
    }

    public /* synthetic */ SearchEntity(String str, EntityIdType entityIdType, EntityType entityType, String str2, String str3, EntityProperties entityProperties, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, entityIdType, entityType, str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : entityProperties, (i & 64) != 0 ? null : bool);
    }

    public final String getEntityId() {
        return this.entityId;
    }

    public final EntityIdType getEntityIdType() {
        return this.entityIdType;
    }

    public final EntityType getEntityType() {
        return this.entityType;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final String getContentUrl() {
        return this.contentUrl;
    }

    public final EntityProperties getEntityProperties() {
        return this.entityProperties;
    }
}
