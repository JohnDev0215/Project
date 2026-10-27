package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.ArtistMerchQuery;
import com.amazon.music.platform.graphql.generated.adapter.ArtistMerchQuery_VariablesAdapter;
import com.amazon.music.platform.graphql.generated.fragment.ArtistEmbedded;
import com.amazon.music.platform.graphql.generated.fragment.Merch;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Optional;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtistMerchQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0010\b\u0086\b\u0018\u0000 #2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0007$#%&'()B3\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0019\u0012\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0019¢\u0006\u0004\b!\u0010\"J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001f\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00198\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u001f\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00198\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010\u001e¨\u0006*"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery;", "Lcom/apollographql/apollo3/api/Query;", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Lcom/apollographql/apollo3/api/Optional;", "", "limit", "Lcom/apollographql/apollo3/api/Optional;", "getLimit", "()Lcom/apollographql/apollo3/api/Optional;", "cursor", "getCursor", "<init>", "(Ljava/lang/String;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;)V", "Companion", "Artist", "Data", "Edge", "Node", "PageInfo", "RelatedMerch", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ArtistMerchQuery implements Query<Data> {
    private final Optional<String> cursor;
    private final String id;
    private final Optional<Double> limit;

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "query ArtistMerch($id: String!, $limit: Float, $cursor: String) { artist(id: $id) { __typename ...artistEmbedded relatedMerch(limit: $limit, cursor: $cursor) { edges { node { __typename ...merch } } edgeCount pageInfo { hasNextPage token } } } }  fragment image on Image { width height aspectRatio url externalUrl imageType description }  fragment artistEmbedded on Artist { id name images { __typename ...image } }  fragment eligibility on Eligibility { isDownloadable isPreviewable isPrimeEligible isPurchaseable isPreorderable playbackSubscriptionTiers playback { shuffleType canPlayOnDemand } }  fragment merch on Merch { id name images { __typename ...image } price format assetType relatedAssetTypes eligibility { __typename ...eligibility } }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArtistMerchQuery)) {
            return false;
        }
        ArtistMerchQuery artistMerchQuery = (ArtistMerchQuery) other;
        return Intrinsics.areEqual(this.id, artistMerchQuery.id) && Intrinsics.areEqual(this.limit, artistMerchQuery.limit) && Intrinsics.areEqual(this.cursor, artistMerchQuery.cursor);
    }

    public int hashCode() {
        return (((this.id.hashCode() * 31) + this.limit.hashCode()) * 31) + this.cursor.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "80eb7beed74a989fce616ca330ef60bcb3c5d9cec51122722da4ecd6d256056f";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "ArtistMerch";
    }

    public String toString() {
        return "ArtistMerchQuery(id=" + this.id + ", limit=" + this.limit + ", cursor=" + this.cursor + ')';
    }

    public ArtistMerchQuery(String id, Optional<Double> limit, Optional<String> cursor) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(limit, "limit");
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        this.id = id;
        this.limit = limit;
        this.cursor = cursor;
    }

    public final String getId() {
        return this.id;
    }

    public /* synthetic */ ArtistMerchQuery(String str, Optional optional, Optional optional2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? Optional.Absent.INSTANCE : optional, (i & 4) != 0 ? Optional.Absent.INSTANCE : optional2);
    }

    public final Optional<Double> getLimit() {
        return this.limit;
    }

    public final Optional<String> getCursor() {
        return this.cursor;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        ArtistMerchQuery_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.ArtistMerchQuery_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("artist");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public ArtistMerchQuery.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                ArtistMerchQuery.Artist artist = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    artist = (ArtistMerchQuery.Artist) Adapters.m2609nullable(Adapters.m2610obj(ArtistMerchQuery_ResponseAdapter$Artist.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                }
                return new ArtistMerchQuery.Data(artist);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, ArtistMerchQuery.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("artist");
                Adapters.m2609nullable(Adapters.m2610obj(ArtistMerchQuery_ResponseAdapter$Artist.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getArtist());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: ArtistMerchQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;", "artist", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;", "getArtist", "()Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final Artist artist;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.artist, ((Data) other).artist);
        }

        public int hashCode() {
            Artist artist = this.artist;
            if (artist == null) {
                return 0;
            }
            return artist.hashCode();
        }

        public String toString() {
            return "Data(artist=" + this.artist + ')';
        }

        public Data(Artist artist) {
            this.artist = artist;
        }

        public final Artist getArtist() {
            return this.artist;
        }
    }

    /* JADX INFO: compiled from: ArtistMerchQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$RelatedMerch;", "relatedMerch", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$RelatedMerch;", "getRelatedMerch", "()Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$RelatedMerch;", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "artistEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "getArtistEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$RelatedMerch;Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Artist {
        private final String __typename;
        private final ArtistEmbedded artistEmbedded;
        private final RelatedMerch relatedMerch;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Artist)) {
                return false;
            }
            Artist artist = (Artist) other;
            return Intrinsics.areEqual(this.__typename, artist.__typename) && Intrinsics.areEqual(this.relatedMerch, artist.relatedMerch) && Intrinsics.areEqual(this.artistEmbedded, artist.artistEmbedded);
        }

        public int hashCode() {
            int iHashCode = this.__typename.hashCode() * 31;
            RelatedMerch relatedMerch = this.relatedMerch;
            return ((iHashCode + (relatedMerch == null ? 0 : relatedMerch.hashCode())) * 31) + this.artistEmbedded.hashCode();
        }

        public String toString() {
            return "Artist(__typename=" + this.__typename + ", relatedMerch=" + this.relatedMerch + ", artistEmbedded=" + this.artistEmbedded + ')';
        }

        public Artist(String __typename, RelatedMerch relatedMerch, ArtistEmbedded artistEmbedded) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(artistEmbedded, "artistEmbedded");
            this.__typename = __typename;
            this.relatedMerch = relatedMerch;
            this.artistEmbedded = artistEmbedded;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final RelatedMerch getRelatedMerch() {
            return this.relatedMerch;
        }

        public final ArtistEmbedded getArtistEmbedded() {
            return this.artistEmbedded;
        }
    }

    /* JADX INFO: compiled from: ArtistMerchQuery.kt */
    @Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R!\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$RelatedMerch;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Edge;", "edges", "Ljava/util/List;", "getEdges", "()Ljava/util/List;", "", "edgeCount", "Ljava/lang/Double;", "getEdgeCount", "()Ljava/lang/Double;", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$PageInfo;", "pageInfo", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$PageInfo;", "getPageInfo", "()Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$PageInfo;", "<init>", "(Ljava/util/List;Ljava/lang/Double;Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$PageInfo;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class RelatedMerch {
        private final Double edgeCount;
        private final List<Edge> edges;
        private final PageInfo pageInfo;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RelatedMerch)) {
                return false;
            }
            RelatedMerch relatedMerch = (RelatedMerch) other;
            return Intrinsics.areEqual(this.edges, relatedMerch.edges) && Intrinsics.areEqual((Object) this.edgeCount, (Object) relatedMerch.edgeCount) && Intrinsics.areEqual(this.pageInfo, relatedMerch.pageInfo);
        }

        public int hashCode() {
            List<Edge> list = this.edges;
            int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
            Double d = this.edgeCount;
            int iHashCode2 = (iHashCode + (d == null ? 0 : d.hashCode())) * 31;
            PageInfo pageInfo = this.pageInfo;
            return iHashCode2 + (pageInfo != null ? pageInfo.hashCode() : 0);
        }

        public String toString() {
            return "RelatedMerch(edges=" + this.edges + ", edgeCount=" + this.edgeCount + ", pageInfo=" + this.pageInfo + ')';
        }

        public RelatedMerch(List<Edge> list, Double d, PageInfo pageInfo) {
            this.edges = list;
            this.edgeCount = d;
            this.pageInfo = pageInfo;
        }

        public final List<Edge> getEdges() {
            return this.edges;
        }

        public final Double getEdgeCount() {
            return this.edgeCount;
        }

        public final PageInfo getPageInfo() {
            return this.pageInfo;
        }
    }

    /* JADX INFO: compiled from: ArtistMerchQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Edge;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Node;", "node", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Node;", "getNode", "()Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Node;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Node;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Edge {
        private final Node node;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Edge) && Intrinsics.areEqual(this.node, ((Edge) other).node);
        }

        public int hashCode() {
            Node node = this.node;
            if (node == null) {
                return 0;
            }
            return node.hashCode();
        }

        public String toString() {
            return "Edge(node=" + this.node + ')';
        }

        public Edge(Node node) {
            this.node = node;
        }

        public final Node getNode() {
            return this.node;
        }
    }

    /* JADX INFO: compiled from: ArtistMerchQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Node;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Merch;", "merch", "Lcom/amazon/music/platform/graphql/generated/fragment/Merch;", "getMerch", "()Lcom/amazon/music/platform/graphql/generated/fragment/Merch;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Merch;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Node {
        private final String __typename;
        private final Merch merch;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Node)) {
                return false;
            }
            Node node = (Node) other;
            return Intrinsics.areEqual(this.__typename, node.__typename) && Intrinsics.areEqual(this.merch, node.merch);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.merch.hashCode();
        }

        public String toString() {
            return "Node(__typename=" + this.__typename + ", merch=" + this.merch + ')';
        }

        public Node(String __typename, Merch merch) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(merch, "merch");
            this.__typename = __typename;
            this.merch = merch;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final Merch getMerch() {
            return this.merch;
        }
    }

    /* JADX INFO: compiled from: ArtistMerchQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$PageInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "hasNextPage", "Z", "getHasNextPage", "()Z", "token", "Ljava/lang/String;", "getToken", "()Ljava/lang/String;", "<init>", "(ZLjava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class PageInfo {
        private final boolean hasNextPage;
        private final String token;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PageInfo)) {
                return false;
            }
            PageInfo pageInfo = (PageInfo) other;
            return this.hasNextPage == pageInfo.hasNextPage && Intrinsics.areEqual(this.token, pageInfo.token);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.hasNextPage;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.token;
            return i + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "PageInfo(hasNextPage=" + this.hasNextPage + ", token=" + this.token + ')';
        }

        public PageInfo(boolean z, String str) {
            this.hasNextPage = z;
            this.token = str;
        }

        public final boolean getHasNextPage() {
            return this.hasNextPage;
        }

        public final String getToken() {
            return this.token;
        }
    }
}
