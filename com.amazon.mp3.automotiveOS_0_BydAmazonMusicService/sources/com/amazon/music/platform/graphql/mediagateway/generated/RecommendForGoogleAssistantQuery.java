package com.amazon.music.platform.graphql.mediagateway.generated;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.graphql.mediagateway.generated.RecommendForGoogleAssistantQuery;
import com.amazon.music.platform.graphql.mediagateway.generated.adapter.RecommendForGoogleAssistantQuery_VariablesAdapter;
import com.amazon.music.platform.graphql.mediagateway.generated.type.GoogleAssistantInput;
import com.amazon.music.platform.graphql.mediagateway.generated.type.MediaIdentifierType;
import com.amazon.music.platform.graphql.mediagateway.generated.type.MediaType;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecommendForGoogleAssistantQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\b\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0007\u001d\u001e\u001f !\"#B\u000f\u0012\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006$"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery;", "Lcom/apollographql/apollo3/api/Query;", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/GoogleAssistantInput;", "googleAssistantInput", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/GoogleAssistantInput;", "getGoogleAssistantInput", "()Lcom/amazon/music/platform/graphql/mediagateway/generated/type/GoogleAssistantInput;", "<init>", "(Lcom/amazon/music/platform/graphql/mediagateway/generated/type/GoogleAssistantInput;)V", "Companion", "Data", "Item", "MediaContent", "OnMediaContainer", "OnMediaItem", "RecommendForGoogleAssistant", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class RecommendForGoogleAssistantQuery implements Query<Data> {
    private final GoogleAssistantInput googleAssistantInput;

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "query recommendForGoogleAssistant($googleAssistantInput: GoogleAssistantInput!) { recommendForGoogleAssistant(input: $googleAssistantInput) { requestId mediaContent { __typename ... on MediaItem { id identifier mediaIdentifierType mediaType } ... on MediaContainer { id identifier mediaIdentifierType mediaType isOwned items { id } } } } }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof RecommendForGoogleAssistantQuery) && Intrinsics.areEqual(this.googleAssistantInput, ((RecommendForGoogleAssistantQuery) other).googleAssistantInput);
    }

    public int hashCode() {
        return this.googleAssistantInput.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "719573cf71349d650c4d6edef1f016f18bb864a6136117488c5e564c0ab77fb5";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "recommendForGoogleAssistant";
    }

    public String toString() {
        return "RecommendForGoogleAssistantQuery(googleAssistantInput=" + this.googleAssistantInput + ')';
    }

    public RecommendForGoogleAssistantQuery(GoogleAssistantInput googleAssistantInput) {
        Intrinsics.checkNotNullParameter(googleAssistantInput, "googleAssistantInput");
        this.googleAssistantInput = googleAssistantInput;
    }

    public final GoogleAssistantInput getGoogleAssistantInput() {
        return this.googleAssistantInput;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        RecommendForGoogleAssistantQuery_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.mediagateway.generated.adapter.RecommendForGoogleAssistantQuery_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("recommendForGoogleAssistant");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public RecommendForGoogleAssistantQuery.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                List listFromJson = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    listFromJson = Adapters.m2608list(Adapters.m2611obj$default(RecommendForGoogleAssistantQuery_ResponseAdapter$RecommendForGoogleAssistant.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
                }
                Intrinsics.checkNotNull(listFromJson);
                return new RecommendForGoogleAssistantQuery.Data(listFromJson);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, RecommendForGoogleAssistantQuery.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("recommendForGoogleAssistant");
                Adapters.m2608list(Adapters.m2611obj$default(RecommendForGoogleAssistantQuery_ResponseAdapter$RecommendForGoogleAssistant.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, (List) value.getRecommendForGoogleAssistant());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: RecommendForGoogleAssistantQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$RecommendForGoogleAssistant;", "recommendForGoogleAssistant", "Ljava/util/List;", "getRecommendForGoogleAssistant", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final List<RecommendForGoogleAssistant> recommendForGoogleAssistant;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.recommendForGoogleAssistant, ((Data) other).recommendForGoogleAssistant);
        }

        public int hashCode() {
            return this.recommendForGoogleAssistant.hashCode();
        }

        public String toString() {
            return "Data(recommendForGoogleAssistant=" + this.recommendForGoogleAssistant + ')';
        }

        public Data(List<RecommendForGoogleAssistant> recommendForGoogleAssistant) {
            Intrinsics.checkNotNullParameter(recommendForGoogleAssistant, "recommendForGoogleAssistant");
            this.recommendForGoogleAssistant = recommendForGoogleAssistant;
        }

        public final List<RecommendForGoogleAssistant> getRecommendForGoogleAssistant() {
            return this.recommendForGoogleAssistant;
        }
    }

    /* JADX INFO: compiled from: RecommendForGoogleAssistantQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$RecommendForGoogleAssistant;", "", "", "toString", "", "hashCode", "other", "", "equals", TetheredMessageKey.requestId, "Ljava/lang/String;", "getRequestId", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$MediaContent;", "mediaContent", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$MediaContent;", "getMediaContent", "()Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$MediaContent;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$MediaContent;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class RecommendForGoogleAssistant {
        private final MediaContent mediaContent;
        private final String requestId;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RecommendForGoogleAssistant)) {
                return false;
            }
            RecommendForGoogleAssistant recommendForGoogleAssistant = (RecommendForGoogleAssistant) other;
            return Intrinsics.areEqual(this.requestId, recommendForGoogleAssistant.requestId) && Intrinsics.areEqual(this.mediaContent, recommendForGoogleAssistant.mediaContent);
        }

        public int hashCode() {
            int iHashCode = this.requestId.hashCode() * 31;
            MediaContent mediaContent = this.mediaContent;
            return iHashCode + (mediaContent == null ? 0 : mediaContent.hashCode());
        }

        public String toString() {
            return "RecommendForGoogleAssistant(requestId=" + this.requestId + ", mediaContent=" + this.mediaContent + ')';
        }

        public RecommendForGoogleAssistant(String requestId, MediaContent mediaContent) {
            Intrinsics.checkNotNullParameter(requestId, "requestId");
            this.requestId = requestId;
            this.mediaContent = mediaContent;
        }

        public final String getRequestId() {
            return this.requestId;
        }

        public final MediaContent getMediaContent() {
            return this.mediaContent;
        }
    }

    /* JADX INFO: compiled from: RecommendForGoogleAssistantQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$MediaContent;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$OnMediaItem;", "onMediaItem", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$OnMediaItem;", "getOnMediaItem", "()Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$OnMediaItem;", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$OnMediaContainer;", "onMediaContainer", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$OnMediaContainer;", "getOnMediaContainer", "()Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$OnMediaContainer;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$OnMediaItem;Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$OnMediaContainer;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class MediaContent {
        private final String __typename;
        private final OnMediaContainer onMediaContainer;
        private final OnMediaItem onMediaItem;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MediaContent)) {
                return false;
            }
            MediaContent mediaContent = (MediaContent) other;
            return Intrinsics.areEqual(this.__typename, mediaContent.__typename) && Intrinsics.areEqual(this.onMediaItem, mediaContent.onMediaItem) && Intrinsics.areEqual(this.onMediaContainer, mediaContent.onMediaContainer);
        }

        public int hashCode() {
            int iHashCode = this.__typename.hashCode() * 31;
            OnMediaItem onMediaItem = this.onMediaItem;
            int iHashCode2 = (iHashCode + (onMediaItem == null ? 0 : onMediaItem.hashCode())) * 31;
            OnMediaContainer onMediaContainer = this.onMediaContainer;
            return iHashCode2 + (onMediaContainer != null ? onMediaContainer.hashCode() : 0);
        }

        public String toString() {
            return "MediaContent(__typename=" + this.__typename + ", onMediaItem=" + this.onMediaItem + ", onMediaContainer=" + this.onMediaContainer + ')';
        }

        public MediaContent(String __typename, OnMediaItem onMediaItem, OnMediaContainer onMediaContainer) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            this.__typename = __typename;
            this.onMediaItem = onMediaItem;
            this.onMediaContainer = onMediaContainer;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final OnMediaItem getOnMediaItem() {
            return this.onMediaItem;
        }

        public final OnMediaContainer getOnMediaContainer() {
            return this.onMediaContainer;
        }
    }

    /* JADX INFO: compiled from: RecommendForGoogleAssistantQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$OnMediaItem;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "identifier", "getIdentifier", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "mediaIdentifierType", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "getMediaIdentifierType", "()Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", "mediaType", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", "getMediaType", "()Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class OnMediaItem {
        private final String id;
        private final String identifier;
        private final MediaIdentifierType mediaIdentifierType;
        private final MediaType mediaType;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OnMediaItem)) {
                return false;
            }
            OnMediaItem onMediaItem = (OnMediaItem) other;
            return Intrinsics.areEqual(this.id, onMediaItem.id) && Intrinsics.areEqual(this.identifier, onMediaItem.identifier) && this.mediaIdentifierType == onMediaItem.mediaIdentifierType && this.mediaType == onMediaItem.mediaType;
        }

        public int hashCode() {
            return (((((this.id.hashCode() * 31) + this.identifier.hashCode()) * 31) + this.mediaIdentifierType.hashCode()) * 31) + this.mediaType.hashCode();
        }

        public String toString() {
            return "OnMediaItem(id=" + this.id + ", identifier=" + this.identifier + ", mediaIdentifierType=" + this.mediaIdentifierType + ", mediaType=" + this.mediaType + ')';
        }

        public OnMediaItem(String id, String identifier, MediaIdentifierType mediaIdentifierType, MediaType mediaType) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            Intrinsics.checkNotNullParameter(mediaIdentifierType, "mediaIdentifierType");
            Intrinsics.checkNotNullParameter(mediaType, "mediaType");
            this.id = id;
            this.identifier = identifier;
            this.mediaIdentifierType = mediaIdentifierType;
            this.mediaType = mediaType;
        }

        public final String getId() {
            return this.id;
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
    }

    /* JADX INFO: compiled from: RecommendForGoogleAssistantQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c¢\u0006\u0004\b\"\u0010#J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0019\u0010\u001bR\u001f\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006$"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$OnMediaContainer;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "identifier", "getIdentifier", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "mediaIdentifierType", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "getMediaIdentifierType", "()Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", "mediaType", "Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", "getMediaType", "()Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;", "isOwned", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "", "Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$Item;", ContextMappingConstants.ITEMS, "Ljava/util/List;", "getItems", "()Ljava/util/List;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaIdentifierType;Lcom/amazon/music/platform/graphql/mediagateway/generated/type/MediaType;Ljava/lang/Boolean;Ljava/util/List;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class OnMediaContainer {
        private final String id;
        private final String identifier;
        private final Boolean isOwned;
        private final List<Item> items;
        private final MediaIdentifierType mediaIdentifierType;
        private final MediaType mediaType;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OnMediaContainer)) {
                return false;
            }
            OnMediaContainer onMediaContainer = (OnMediaContainer) other;
            return Intrinsics.areEqual(this.id, onMediaContainer.id) && Intrinsics.areEqual(this.identifier, onMediaContainer.identifier) && this.mediaIdentifierType == onMediaContainer.mediaIdentifierType && this.mediaType == onMediaContainer.mediaType && Intrinsics.areEqual(this.isOwned, onMediaContainer.isOwned) && Intrinsics.areEqual(this.items, onMediaContainer.items);
        }

        public int hashCode() {
            int iHashCode = ((((((this.id.hashCode() * 31) + this.identifier.hashCode()) * 31) + this.mediaIdentifierType.hashCode()) * 31) + this.mediaType.hashCode()) * 31;
            Boolean bool = this.isOwned;
            int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
            List<Item> list = this.items;
            return iHashCode2 + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "OnMediaContainer(id=" + this.id + ", identifier=" + this.identifier + ", mediaIdentifierType=" + this.mediaIdentifierType + ", mediaType=" + this.mediaType + ", isOwned=" + this.isOwned + ", items=" + this.items + ')';
        }

        public OnMediaContainer(String id, String identifier, MediaIdentifierType mediaIdentifierType, MediaType mediaType, Boolean bool, List<Item> list) {
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
        }

        public final String getId() {
            return this.id;
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
    }

    /* JADX INFO: compiled from: RecommendForGoogleAssistantQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/graphql/mediagateway/generated/RecommendForGoogleAssistantQuery$Item;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Item {
        private final String id;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Item) && Intrinsics.areEqual(this.id, ((Item) other).id);
        }

        public int hashCode() {
            return this.id.hashCode();
        }

        public String toString() {
            return "Item(id=" + this.id + ')';
        }

        public Item(String id) {
            Intrinsics.checkNotNullParameter(id, "id");
            this.id = id;
        }

        public final String getId() {
            return this.id;
        }
    }
}
