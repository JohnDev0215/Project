package com.amazon.music.find.data.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.find.common.EntityIdType;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.platform.find.service.common.EntityProperties;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: com.amazon.music.find.data.database.entities.SearchEntityData, reason: from toString */
/* JADX INFO: compiled from: SearchEntityData.kt */
/* JADX INFO: loaded from: classes4.dex */
@Entity(primaryKeys = {"entity_id", "entity_id_type", "profile_id"}, tableName = "search_entity")
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003¢\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0006HÆ\u0003J\t\u0010'\u001a\u00020\bHÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010*\u001a\u00020\rHÆ\u0003J\t\u0010+\u001a\u00020\u000fHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jq\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u0003HÆ\u0001J\u0013\u0010.\u001a\u00020\u000f2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\b\u00102\u001a\u00020\u0003H\u0016R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0016\u0010\u0011\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014¨\u00063"}, d2 = {"Lcom/amazon/music/find/data/database/entities/SearchEntityData;", "", "title", "", ContextMappingConstants.ENTITY_ID, ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/platform/find/common/EntityIdType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", "imageUrl", "entityProperties", "Lcom/amazon/music/platform/find/service/common/EntityProperties;", TetheredMessageKey.timestamp, "", "didNavigateFromSearch", "", "contentUrl", "pid", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/find/common/EntityIdType;Lcom/amazon/music/platform/find/common/EntityType;Ljava/lang/String;Lcom/amazon/music/platform/find/service/common/EntityProperties;JZLjava/lang/String;Ljava/lang/String;)V", "getContentUrl", "()Ljava/lang/String;", "getDidNavigateFromSearch", "()Z", "getEntityId", "getEntityIdType", "()Lcom/amazon/music/platform/find/common/EntityIdType;", "getEntityProperties", "()Lcom/amazon/music/platform/find/service/common/EntityProperties;", "getEntityType", "()Lcom/amazon/music/platform/find/common/EntityType;", "getImageUrl", "getPid", "getTimestamp", "()J", "getTitle", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class SearchItem {

    @ColumnInfo(name = "content_url")
    private final String contentUrl;

    @ColumnInfo(name = "did_navigate_from_search")
    private final boolean didNavigateFromSearch;

    @ColumnInfo(name = "entity_id")
    private final String entityId;

    @ColumnInfo(name = "entity_id_type")
    private final EntityIdType entityIdType;

    /* JADX INFO: renamed from: entityProperties, reason: from kotlin metadata and from toString */
    @ColumnInfo(name = "entity_property")
    private final EntityProperties entityProperty;

    @ColumnInfo(name = "entity_type")
    private final EntityType entityType;

    @ColumnInfo(name = "image_url")
    private final String imageUrl;

    @ColumnInfo(name = "profile_id")
    private final String pid;

    @ColumnInfo(name = TetheredMessageKey.timestamp)
    private final long timestamp;

    @ColumnInfo(collate = 3)
    private final String title;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getPid() {
        return this.pid;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getEntityId() {
        return this.entityId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final EntityIdType getEntityIdType() {
        return this.entityIdType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final EntityType getEntityType() {
        return this.entityType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getImageUrl() {
        return this.imageUrl;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final EntityProperties getEntityProperty() {
        return this.entityProperty;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getDidNavigateFromSearch() {
        return this.didNavigateFromSearch;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getContentUrl() {
        return this.contentUrl;
    }

    public final SearchItem copy(String title, String entityId, EntityIdType entityIdType, EntityType entityType, String imageUrl, EntityProperties entityProperties, long timestamp, boolean didNavigateFromSearch, String contentUrl, String pid) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(entityIdType, "entityIdType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        Intrinsics.checkNotNullParameter(pid, "pid");
        return new SearchItem(title, entityId, entityIdType, entityType, imageUrl, entityProperties, timestamp, didNavigateFromSearch, contentUrl, pid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchItem)) {
            return false;
        }
        SearchItem searchItem = (SearchItem) other;
        return Intrinsics.areEqual(this.title, searchItem.title) && Intrinsics.areEqual(this.entityId, searchItem.entityId) && this.entityIdType == searchItem.entityIdType && this.entityType == searchItem.entityType && Intrinsics.areEqual(this.imageUrl, searchItem.imageUrl) && Intrinsics.areEqual(this.entityProperty, searchItem.entityProperty) && this.timestamp == searchItem.timestamp && this.didNavigateFromSearch == searchItem.didNavigateFromSearch && Intrinsics.areEqual(this.contentUrl, searchItem.contentUrl) && Intrinsics.areEqual(this.pid, searchItem.pid);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13, types: [int] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v19 */
    public int hashCode() {
        int iHashCode = ((((((((this.title.hashCode() * 31) + this.entityId.hashCode()) * 31) + this.entityIdType.hashCode()) * 31) + this.entityType.hashCode()) * 31) + this.imageUrl.hashCode()) * 31;
        EntityProperties entityProperties = this.entityProperty;
        int iHashCode2 = (((iHashCode + (entityProperties == null ? 0 : entityProperties.hashCode())) * 31) + Long.hashCode(this.timestamp)) * 31;
        boolean z = this.didNavigateFromSearch;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode2 + r1) * 31;
        String str = this.contentUrl;
        return ((i + (str != null ? str.hashCode() : 0)) * 31) + this.pid.hashCode();
    }

    public SearchItem(String title, String entityId, EntityIdType entityIdType, EntityType entityType, String imageUrl, EntityProperties entityProperties, long j, boolean z, String str, String pid) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(entityId, "entityId");
        Intrinsics.checkNotNullParameter(entityIdType, "entityIdType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        Intrinsics.checkNotNullParameter(pid, "pid");
        this.title = title;
        this.entityId = entityId;
        this.entityIdType = entityIdType;
        this.entityType = entityType;
        this.imageUrl = imageUrl;
        this.entityProperty = entityProperties;
        this.timestamp = j;
        this.didNavigateFromSearch = z;
        this.contentUrl = str;
        this.pid = pid;
    }

    public /* synthetic */ SearchItem(String str, String str2, EntityIdType entityIdType, EntityType entityType, String str3, EntityProperties entityProperties, long j, boolean z, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, entityIdType, entityType, str3, (i & 32) != 0 ? null : entityProperties, j, z, (i & 256) != 0 ? null : str4, str5);
    }

    public final String getTitle() {
        return this.title;
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

    public final EntityProperties getEntityProperties() {
        return this.entityProperty;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final boolean getDidNavigateFromSearch() {
        return this.didNavigateFromSearch;
    }

    public final String getContentUrl() {
        return this.contentUrl;
    }

    public final String getPid() {
        return this.pid;
    }

    public String toString() {
        return "SearchItem(title='" + this.title + "', entityId='" + this.entityId + "', entityIdType='" + this.entityIdType + "', entityType='" + this.entityType + "', imageUrl='" + this.imageUrl + "', timestamp='" + this.timestamp + "', entityProperty='" + this.entityProperty + "), didNavigateFromSearch = '" + this.didNavigateFromSearch + "contentUrl = '" + ((Object) this.contentUrl);
    }
}
