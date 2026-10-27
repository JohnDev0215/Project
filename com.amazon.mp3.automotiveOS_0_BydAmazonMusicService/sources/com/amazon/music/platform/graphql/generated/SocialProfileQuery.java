package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.SocialProfileQuery;
import com.amazon.music.platform.graphql.generated.adapter.SocialProfileQuery_VariablesAdapter;
import com.amazon.music.platform.graphql.generated.fragment.SocialProfilesUserEmbedded;
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
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SocialProfileQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\b\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0005%&'()B)\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001e¢\u0006\u0004\b#\u0010$J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u001a\u001a\u00020\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001f\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"¨\u0006*"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery;", "Lcom/apollographql/apollo3/api/Query;", "Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "", "limit", "D", "getLimit", "()D", "Lcom/apollographql/apollo3/api/Optional;", "cursor", "Lcom/apollographql/apollo3/api/Optional;", "getCursor", "()Lcom/apollographql/apollo3/api/Optional;", "<init>", "(Ljava/lang/String;DLcom/apollographql/apollo3/api/Optional;)V", "Companion", "Data", "FollowedUsers", "Me", "Other", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class SocialProfileQuery implements Query<Data> {
    private final Optional<String> cursor;
    private final String id;
    private final double limit;

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "query SocialProfile($id: String!, $limit: Float!, $cursor: String) { me: user { id followedUsers(userIds: [$id]) { edgeCount } } other: user(id: $id) { __typename ...socialProfilesUserEmbedded } }  fragment image on Image { width height aspectRatio url externalUrl imageType description }  fragment eligibility on Eligibility { isDownloadable isPreviewable isPrimeEligible isPurchaseable isPreorderable playbackSubscriptionTiers playback { shuffleType canPlayOnDemand } }  fragment socialProfilesPlaylistEmbedded on Playlist { id title url trackCount eligibility { __typename ...eligibility } images { __typename ...image } followerCount visibility }  fragment socialProfilesUserEmbedded on User { id url name handle images { __typename ...image } visibility playbackVisibility playlists(limit: $limit, cursor: $cursor) { edges { cursor node { __typename ...socialProfilesPlaylistEmbedded } } totalCount edgeCount pageInfo { hasNextPage token } } followers { totalCount } followedUsers { totalCount } followedArtists { totalCount } }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SocialProfileQuery)) {
            return false;
        }
        SocialProfileQuery socialProfileQuery = (SocialProfileQuery) other;
        return Intrinsics.areEqual(this.id, socialProfileQuery.id) && Intrinsics.areEqual((Object) Double.valueOf(this.limit), (Object) Double.valueOf(socialProfileQuery.limit)) && Intrinsics.areEqual(this.cursor, socialProfileQuery.cursor);
    }

    public int hashCode() {
        return (((this.id.hashCode() * 31) + Double.hashCode(this.limit)) * 31) + this.cursor.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "e5d9d9dce29c9a5fd07c28606bc0147a820a919c137d5c109a24aac94d370c21";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "SocialProfile";
    }

    public String toString() {
        return "SocialProfileQuery(id=" + this.id + ", limit=" + this.limit + ", cursor=" + this.cursor + ')';
    }

    public SocialProfileQuery(String id, double d, Optional<String> cursor) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        this.id = id;
        this.limit = d;
        this.cursor = cursor;
    }

    public final String getId() {
        return this.id;
    }

    public final double getLimit() {
        return this.limit;
    }

    public final Optional<String> getCursor() {
        return this.cursor;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        SocialProfileQuery_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.SocialProfileQuery_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"me", "other"});

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public SocialProfileQuery.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                SocialProfileQuery.Me me = null;
                SocialProfileQuery.Other other = null;
                while (true) {
                    int iSelectName = reader.selectName(RESPONSE_NAMES);
                    if (iSelectName == 0) {
                        me = (SocialProfileQuery.Me) Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfileQuery_ResponseAdapter$Me.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
                    } else if (iSelectName == 1) {
                        other = (SocialProfileQuery.Other) Adapters.m2609nullable(Adapters.m2610obj(SocialProfileQuery_ResponseAdapter$Other.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                    } else {
                        return new SocialProfileQuery.Data(me, other);
                    }
                }
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, SocialProfileQuery.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("me");
                Adapters.m2609nullable(Adapters.m2611obj$default(SocialProfileQuery_ResponseAdapter$Me.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getMe());
                writer.name("other");
                Adapters.m2609nullable(Adapters.m2610obj(SocialProfileQuery_ResponseAdapter$Other.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getOther());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: SocialProfileQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Me;", "me", "Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Me;", "getMe", "()Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Me;", "Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Other;", "Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Other;", "getOther", "()Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Other;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Me;Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Other;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final Me me;
        private final Other other;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Data)) {
                return false;
            }
            Data data = (Data) other;
            return Intrinsics.areEqual(this.me, data.me) && Intrinsics.areEqual(this.other, data.other);
        }

        public int hashCode() {
            Me me = this.me;
            int iHashCode = (me == null ? 0 : me.hashCode()) * 31;
            Other other = this.other;
            return iHashCode + (other != null ? other.hashCode() : 0);
        }

        public String toString() {
            return "Data(me=" + this.me + ", other=" + this.other + ')';
        }

        public Data(Me me, Other other) {
            this.me = me;
            this.other = other;
        }

        public final Me getMe() {
            return this.me;
        }

        public final Other getOther() {
            return this.other;
        }
    }

    /* JADX INFO: compiled from: SocialProfileQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Me;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$FollowedUsers;", "followedUsers", "Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$FollowedUsers;", "getFollowedUsers", "()Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$FollowedUsers;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$FollowedUsers;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Me {
        private final FollowedUsers followedUsers;
        private final String id;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Me)) {
                return false;
            }
            Me me = (Me) other;
            return Intrinsics.areEqual(this.id, me.id) && Intrinsics.areEqual(this.followedUsers, me.followedUsers);
        }

        public int hashCode() {
            String str = this.id;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            FollowedUsers followedUsers = this.followedUsers;
            return iHashCode + (followedUsers != null ? followedUsers.hashCode() : 0);
        }

        public String toString() {
            return "Me(id=" + this.id + ", followedUsers=" + this.followedUsers + ')';
        }

        public Me(String str, FollowedUsers followedUsers) {
            this.id = str;
            this.followedUsers = followedUsers;
        }

        public final String getId() {
            return this.id;
        }

        public final FollowedUsers getFollowedUsers() {
            return this.followedUsers;
        }
    }

    /* JADX INFO: compiled from: SocialProfileQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$FollowedUsers;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "edgeCount", "Ljava/lang/Double;", "getEdgeCount", "()Ljava/lang/Double;", "<init>", "(Ljava/lang/Double;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class FollowedUsers {
        private final Double edgeCount;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FollowedUsers) && Intrinsics.areEqual((Object) this.edgeCount, (Object) ((FollowedUsers) other).edgeCount);
        }

        public int hashCode() {
            Double d = this.edgeCount;
            if (d == null) {
                return 0;
            }
            return d.hashCode();
        }

        public String toString() {
            return "FollowedUsers(edgeCount=" + this.edgeCount + ')';
        }

        public FollowedUsers(Double d) {
            this.edgeCount = d;
        }

        public final Double getEdgeCount() {
            return this.edgeCount;
        }
    }

    /* JADX INFO: compiled from: SocialProfileQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/SocialProfileQuery$Other;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded;", "socialProfilesUserEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded;", "getSocialProfilesUserEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Other {
        private final String __typename;
        private final SocialProfilesUserEmbedded socialProfilesUserEmbedded;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Other)) {
                return false;
            }
            Other other2 = (Other) other;
            return Intrinsics.areEqual(this.__typename, other2.__typename) && Intrinsics.areEqual(this.socialProfilesUserEmbedded, other2.socialProfilesUserEmbedded);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.socialProfilesUserEmbedded.hashCode();
        }

        public String toString() {
            return "Other(__typename=" + this.__typename + ", socialProfilesUserEmbedded=" + this.socialProfilesUserEmbedded + ')';
        }

        public Other(String __typename, SocialProfilesUserEmbedded socialProfilesUserEmbedded) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(socialProfilesUserEmbedded, "socialProfilesUserEmbedded");
            this.__typename = __typename;
            this.socialProfilesUserEmbedded = socialProfilesUserEmbedded;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final SocialProfilesUserEmbedded getSocialProfilesUserEmbedded() {
            return this.socialProfilesUserEmbedded;
        }
    }
}
