package com.amazon.music.platform.data.mediagateway;

import com.amazon.music.platform.graphql.mediagateway.generated.type.MediaIdentifierType;
import com.amazon.music.platform.graphql.mediagateway.generated.type.MediaType;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: renamed from: com.amazon.music.platform.data.mediagateway.RecommendForAssistant$MediaItem, reason: from toString */
/* JADX INFO: compiled from: RecommendForAssistant.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0016\u001a\u00020\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u001b\u0010\r¨\u0006\u001e"}, d2 = {"com/amazon/music/platform/data/mediagateway/RecommendForAssistant$MediaItem", "Lcom/amazon/music/platform/data/mediagateway/RecommendForAssistant$MediaContent;", "", "toString", "", "hashCode", "", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "identifier", "getIdentifier", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "mediaIdentifierType", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "getMediaIdentifierType", "()Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", "mediaType", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", "getMediaType", "()Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", TetheredMessageKey.requestId, "getRequestId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class MediaItem implements RecommendForAssistant$MediaContent {
    private final String id;
    private final String identifier;
    private final MediaIdentifierType mediaIdentifierType;
    private final MediaType mediaType;
    private final String requestId;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) other;
        return Intrinsics.areEqual(this.id, mediaItem.id) && Intrinsics.areEqual(this.identifier, mediaItem.identifier) && this.mediaIdentifierType == mediaItem.mediaIdentifierType && this.mediaType == mediaItem.mediaType && Intrinsics.areEqual(this.requestId, mediaItem.requestId);
    }

    public int hashCode() {
        int iHashCode = ((((((this.id.hashCode() * 31) + this.identifier.hashCode()) * 31) + this.mediaIdentifierType.hashCode()) * 31) + this.mediaType.hashCode()) * 31;
        String str = this.requestId;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "MediaItem(id=" + this.id + ", identifier=" + this.identifier + ", mediaIdentifierType=" + this.mediaIdentifierType + ", mediaType=" + this.mediaType + ", requestId=" + this.requestId + ')';
    }

    public MediaItem(String id, String identifier, MediaIdentifierType mediaIdentifierType, MediaType mediaType, String str) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Intrinsics.checkNotNullParameter(mediaIdentifierType, "mediaIdentifierType");
        Intrinsics.checkNotNullParameter(mediaType, "mediaType");
        this.id = id;
        this.identifier = identifier;
        this.mediaIdentifierType = mediaIdentifierType;
        this.mediaType = mediaType;
        this.requestId = str;
    }

    public final String getIdentifier() {
        return this.identifier;
    }

    public final MediaType getMediaType() {
        return this.mediaType;
    }
}
