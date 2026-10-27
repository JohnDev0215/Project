package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.FollowedArtistsQuery;
import com.amazon.music.platform.graphql.generated.adapter.FollowedArtistsQuery_VariablesAdapter;
import com.amazon.music.platform.graphql.generated.fragment.ArtistEmbedded;
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
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FollowedArtistsQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\f\b\u0086\b\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0007%&'()*+B9\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001b\u0012\u0016\b\u0002\u0010!\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010 0\u001b¢\u0006\u0004\b#\u0010$J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u001f\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR%\u0010!\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010 0\u001b8\u0006¢\u0006\f\n\u0004\b!\u0010\u001d\u001a\u0004\b\"\u0010\u001f¨\u0006,"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery;", "Lcom/apollographql/apollo3/api/Query;", "Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "", "limit", "D", "getLimit", "()D", "Lcom/apollographql/apollo3/api/Optional;", "cursor", "Lcom/apollographql/apollo3/api/Optional;", "getCursor", "()Lcom/apollographql/apollo3/api/Optional;", "", "artistIds", "getArtistIds", "<init>", "(DLcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;)V", "Companion", "Data", "Edge", "FollowedArtists", "Node", "PageInfo", "User", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class FollowedArtistsQuery implements Query<Data> {
    private final Optional<List<String>> artistIds;
    private final Optional<String> cursor;
    private final double limit;

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "query FollowedArtists($limit: Float!, $cursor: String, $artistIds: [String!]) { user { followedArtists(limit: $limit, cursor: $cursor, artistIds: $artistIds) { totalCount pageInfo { hasNextPage token } edgeCount edges { node { __typename ...artistEmbedded } followState } } } }  fragment image on Image { width height aspectRatio url externalUrl imageType description }  fragment artistEmbedded on Artist { id name images { __typename ...image } }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FollowedArtistsQuery)) {
            return false;
        }
        FollowedArtistsQuery followedArtistsQuery = (FollowedArtistsQuery) other;
        return Intrinsics.areEqual((Object) Double.valueOf(this.limit), (Object) Double.valueOf(followedArtistsQuery.limit)) && Intrinsics.areEqual(this.cursor, followedArtistsQuery.cursor) && Intrinsics.areEqual(this.artistIds, followedArtistsQuery.artistIds);
    }

    public int hashCode() {
        return (((Double.hashCode(this.limit) * 31) + this.cursor.hashCode()) * 31) + this.artistIds.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "0ff0e9341ff4869bce16ece5235fc11c6ae11b0b96119b7566cf072012dd46bc";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "FollowedArtists";
    }

    public String toString() {
        return "FollowedArtistsQuery(limit=" + this.limit + ", cursor=" + this.cursor + ", artistIds=" + this.artistIds + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FollowedArtistsQuery(double d, Optional<String> cursor, Optional<? extends List<String>> artistIds) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Intrinsics.checkNotNullParameter(artistIds, "artistIds");
        this.limit = d;
        this.cursor = cursor;
        this.artistIds = artistIds;
    }

    public final double getLimit() {
        return this.limit;
    }

    public /* synthetic */ FollowedArtistsQuery(double d, Optional optional, Optional optional2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(d, (i & 2) != 0 ? Optional.Absent.INSTANCE : optional, (i & 4) != 0 ? Optional.Absent.INSTANCE : optional2);
    }

    public final Optional<String> getCursor() {
        return this.cursor;
    }

    public final Optional<List<String>> getArtistIds() {
        return this.artistIds;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        FollowedArtistsQuery_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.FollowedArtistsQuery_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("user");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public FollowedArtistsQuery.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                FollowedArtistsQuery.User user = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    user = (FollowedArtistsQuery.User) Adapters.m2609nullable(Adapters.m2611obj$default(FollowedArtistsQuery_ResponseAdapter$User.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
                }
                return new FollowedArtistsQuery.Data(user);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, FollowedArtistsQuery.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("user");
                Adapters.m2609nullable(Adapters.m2611obj$default(FollowedArtistsQuery_ResponseAdapter$User.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getUser());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: FollowedArtistsQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$User;", "user", "Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$User;", "getUser", "()Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$User;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$User;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final User user;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.user, ((Data) other).user);
        }

        public int hashCode() {
            User user = this.user;
            if (user == null) {
                return 0;
            }
            return user.hashCode();
        }

        public String toString() {
            return "Data(user=" + this.user + ')';
        }

        public Data(User user) {
            this.user = user;
        }

        public final User getUser() {
            return this.user;
        }
    }

    /* JADX INFO: compiled from: FollowedArtistsQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$User;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$FollowedArtists;", "followedArtists", "Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$FollowedArtists;", "getFollowedArtists", "()Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$FollowedArtists;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$FollowedArtists;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class User {
        private final FollowedArtists followedArtists;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof User) && Intrinsics.areEqual(this.followedArtists, ((User) other).followedArtists);
        }

        public int hashCode() {
            FollowedArtists followedArtists = this.followedArtists;
            if (followedArtists == null) {
                return 0;
            }
            return followedArtists.hashCode();
        }

        public String toString() {
            return "User(followedArtists=" + this.followedArtists + ')';
        }

        public User(FollowedArtists followedArtists) {
            this.followedArtists = followedArtists;
        }

        public final FollowedArtists getFollowedArtists() {
            return this.followedArtists;
        }
    }

    /* JADX INFO: compiled from: FollowedArtistsQuery.kt */
    @Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\f\u0012\u0010\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\t\u0010\b\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\r\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0016\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0017\u0010\u0010R!\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$FollowedArtists;", "", "", "Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$Edge;", "edgesFilterNotNull", "", "toString", "", "hashCode", "other", "", "equals", "", "totalCount", "Ljava/lang/Double;", "getTotalCount", "()Ljava/lang/Double;", "Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$PageInfo;", "pageInfo", "Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$PageInfo;", "getPageInfo", "()Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$PageInfo;", "edgeCount", "getEdgeCount", "edges", "Ljava/util/List;", "getEdges", "()Ljava/util/List;", "<init>", "(Ljava/lang/Double;Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$PageInfo;Ljava/lang/Double;Ljava/util/List;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class FollowedArtists {
        private final Double edgeCount;
        private final List<Edge> edges;
        private final PageInfo pageInfo;
        private final Double totalCount;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FollowedArtists)) {
                return false;
            }
            FollowedArtists followedArtists = (FollowedArtists) other;
            return Intrinsics.areEqual((Object) this.totalCount, (Object) followedArtists.totalCount) && Intrinsics.areEqual(this.pageInfo, followedArtists.pageInfo) && Intrinsics.areEqual((Object) this.edgeCount, (Object) followedArtists.edgeCount) && Intrinsics.areEqual(this.edges, followedArtists.edges);
        }

        public int hashCode() {
            Double d = this.totalCount;
            int iHashCode = (d == null ? 0 : d.hashCode()) * 31;
            PageInfo pageInfo = this.pageInfo;
            int iHashCode2 = (iHashCode + (pageInfo == null ? 0 : pageInfo.hashCode())) * 31;
            Double d2 = this.edgeCount;
            int iHashCode3 = (iHashCode2 + (d2 == null ? 0 : d2.hashCode())) * 31;
            List<Edge> list = this.edges;
            return iHashCode3 + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "FollowedArtists(totalCount=" + this.totalCount + ", pageInfo=" + this.pageInfo + ", edgeCount=" + this.edgeCount + ", edges=" + this.edges + ')';
        }

        public FollowedArtists(Double d, PageInfo pageInfo, Double d2, List<Edge> list) {
            this.totalCount = d;
            this.pageInfo = pageInfo;
            this.edgeCount = d2;
            this.edges = list;
        }

        public final Double getTotalCount() {
            return this.totalCount;
        }

        public final PageInfo getPageInfo() {
            return this.pageInfo;
        }

        public final Double getEdgeCount() {
            return this.edgeCount;
        }

        public final List<Edge> getEdges() {
            return this.edges;
        }

        public final List<Edge> edgesFilterNotNull() {
            List<Edge> list = this.edges;
            if (list != null) {
                return CollectionsKt___CollectionsKt.filterNotNull(list);
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: FollowedArtistsQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$PageInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "hasNextPage", "Z", "getHasNextPage", "()Z", "token", "Ljava/lang/String;", "getToken", "()Ljava/lang/String;", "<init>", "(ZLjava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: FollowedArtistsQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$Edge;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$Node;", "node", "Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$Node;", "getNode", "()Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$Node;", "followState", "Ljava/lang/String;", "getFollowState", "()Ljava/lang/String;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$Node;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Edge {
        private final String followState;
        private final Node node;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Edge)) {
                return false;
            }
            Edge edge = (Edge) other;
            return Intrinsics.areEqual(this.node, edge.node) && Intrinsics.areEqual(this.followState, edge.followState);
        }

        public int hashCode() {
            Node node = this.node;
            int iHashCode = (node == null ? 0 : node.hashCode()) * 31;
            String str = this.followState;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Edge(node=" + this.node + ", followState=" + this.followState + ')';
        }

        public Edge(Node node, String str) {
            this.node = node;
            this.followState = str;
        }

        public final Node getNode() {
            return this.node;
        }

        public final String getFollowState() {
            return this.followState;
        }
    }

    /* JADX INFO: compiled from: FollowedArtistsQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/FollowedArtistsQuery$Node;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "artistEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "getArtistEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Node {
        private final String __typename;
        private final ArtistEmbedded artistEmbedded;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Node)) {
                return false;
            }
            Node node = (Node) other;
            return Intrinsics.areEqual(this.__typename, node.__typename) && Intrinsics.areEqual(this.artistEmbedded, node.artistEmbedded);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.artistEmbedded.hashCode();
        }

        public String toString() {
            return "Node(__typename=" + this.__typename + ", artistEmbedded=" + this.artistEmbedded + ')';
        }

        public Node(String __typename, ArtistEmbedded artistEmbedded) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(artistEmbedded, "artistEmbedded");
            this.__typename = __typename;
            this.artistEmbedded = artistEmbedded;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final ArtistEmbedded getArtistEmbedded() {
            return this.artistEmbedded;
        }
    }
}
