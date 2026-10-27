package com.amazon.music.platform.data.mediagateway;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.graphql.mediagateway.generated.type.MediaIdentifierType;
import com.amazon.music.platform.graphql.mediagateway.generated.type.MediaType;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: com.amazon.music.platform.data.mediagateway.RecommendForAssistant$MediaContainer, reason: from toString */
/* JADX INFO: compiled from: RecommendForAssistant.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\b\u0012\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b%\u0010&J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0016\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001a\u001a\u0004\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001a\u0010\u001cR\u001f\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0019\u0010#\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\r¨\u0006'"}, d2 = {"com/amazon/music/platform/data/mediagateway/RecommendForAssistant$MediaContainer", "Lcom/amazon/music/platform/data/mediagateway/RecommendForAssistant$MediaContent;", "", "toString", "", "hashCode", "", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "identifier", "getIdentifier", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "mediaIdentifierType", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "getMediaIdentifierType", "()Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", "mediaType", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", "getMediaType", "()Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", "isOwned", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "", "Lcom/amazon/music/platform/data/mediagateway/RecommendForAssistant$Item;", ContextMappingConstants.ITEMS, "Ljava/util/List;", "getItems", "()Ljava/util/List;", TetheredMessageKey.requestId, "getRequestId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class MediaContainer implements RecommendForAssistant$MediaContent {
    private final String id;
    private final String identifier;
    private final Boolean isOwned;
    private final List<Item> items;
    private final MediaIdentifierType mediaIdentifierType;
    private final MediaType mediaType;
    private final String requestId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MediaContainer)) {
            return false;
        }
        MediaContainer mediaContainer = (MediaContainer) other;
        return Intrinsics.areEqual(this.id, mediaContainer.id) && Intrinsics.areEqual(this.identifier, mediaContainer.identifier) && this.mediaIdentifierType == mediaContainer.mediaIdentifierType && this.mediaType == mediaContainer.mediaType && Intrinsics.areEqual(this.isOwned, mediaContainer.isOwned) && Intrinsics.areEqual(this.items, mediaContainer.items) && Intrinsics.areEqual(this.requestId, mediaContainer.requestId);
    }

    public int hashCode() {
        int iHashCode = ((((((this.id.hashCode() * 31) + this.identifier.hashCode()) * 31) + this.mediaIdentifierType.hashCode()) * 31) + this.mediaType.hashCode()) * 31;
        Boolean bool = this.isOwned;
        int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
        List<Item> list = this.items;
        int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        String str = this.requestId;
        return iHashCode3 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "MediaContainer(id=" + this.id + ", identifier=" + this.identifier + ", mediaIdentifierType=" + this.mediaIdentifierType + ", mediaType=" + this.mediaType + ", isOwned=" + this.isOwned + ", items=" + this.items + ", requestId=" + this.requestId + ')';
    }

    public MediaContainer(String id, String identifier, MediaIdentifierType mediaIdentifierType, MediaType mediaType, Boolean bool, List<Item> list, String str) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(mediaIdentifierType, "mediaIdentifierType");
        Intrinsics.checkNotNullParameter(mediaType, "mediaType");
        this.id = id;
        this.identifier = identifier;
        this.mediaIdentifierType = mediaIdentifierType;
        this.mediaType = mediaType;
        this.isOwned = bool;
        this.items = list;
        this.requestId = str;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final MediaIdentifierType getMediaIdentifierType() {
        return this.mediaIdentifierType;
    }

    public final MediaType getMediaType() {
        return this.mediaType;
    }

    /* JADX INFO: renamed from: isOwned, reason: from getter */
    public final Boolean getIsOwned() {
        return this.isOwned;
    }

    public final List<Item> getItems() {
        return this.items;
    }

    public final String getRequestId() {
        return this.requestId;
    }
}
