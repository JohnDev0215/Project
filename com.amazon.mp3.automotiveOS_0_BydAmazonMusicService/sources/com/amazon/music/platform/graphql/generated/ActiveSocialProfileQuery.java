package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.ActiveSocialProfileQuery;
import com.amazon.music.platform.graphql.generated.adapter.ActiveSocialProfileQuery_VariablesAdapter;
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
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActiveSocialProfileQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\b\u0018\u0000 \"2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\"#$B!\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001b¢\u0006\u0004\b \u0010!J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u001f\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006%"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ActiveSocialProfileQuery;", "Lcom/apollographql/apollo3/api/Query;", "Lcom/amazon/music/platform/graphql/generated/ActiveSocialProfileQuery$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "", "limit", "D", "getLimit", "()D", "Lcom/apollographql/apollo3/api/Optional;", "cursor", "Lcom/apollographql/apollo3/api/Optional;", "getCursor", "()Lcom/apollographql/apollo3/api/Optional;", "<init>", "(DLcom/apollographql/apollo3/api/Optional;)V", "Companion", "Data", "User", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ActiveSocialProfileQuery implements Query<Data> {
    private final Optional<String> cursor;
    private final double limit;

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "query ActiveSocialProfile($limit: Float!, $cursor: String) { user { __typename ...socialProfilesUserEmbedded } }  fragment image on Image { width height aspectRatio url externalUrl imageType description }  fragment eligibility on Eligibility { isDownloadable isPreviewable isPrimeEligible isPurchaseable isPreorderable playbackSubscriptionTiers playback { shuffleType canPlayOnDemand } }  fragment socialProfilesPlaylistEmbedded on Playlist { id title url trackCount eligibility { __typename ...eligibility } images { __typename ...image } followerCount visibility }  fragment socialProfilesUserEmbedded on User { id url name handle images { __typename ...image } visibility playbackVisibility playlists(limit: $limit, cursor: $cursor) { edges { cursor node { __typename ...socialProfilesPlaylistEmbedded } } totalCount edgeCount pageInfo { hasNextPage token } } followers { totalCount } followedUsers { totalCount } followedArtists { totalCount } }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActiveSocialProfileQuery)) {
            return false;
        }
        ActiveSocialProfileQuery activeSocialProfileQuery = (ActiveSocialProfileQuery) other;
        return Intrinsics.areEqual((Object) Double.valueOf(this.limit), (Object) Double.valueOf(activeSocialProfileQuery.limit)) && Intrinsics.areEqual(this.cursor, activeSocialProfileQuery.cursor);
    }

    public int hashCode() {
        return (Double.hashCode(this.limit) * 31) + this.cursor.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "6fddd5e8e5ac86b115cd15d2c05019112986eb6a8dc7488f2d96217abf54dc42";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "ActiveSocialProfile";
    }

    public String toString() {
        return "ActiveSocialProfileQuery(limit=" + this.limit + ", cursor=" + this.cursor + ')';
    }

    public ActiveSocialProfileQuery(double d, Optional<String> cursor) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        this.limit = d;
        this.cursor = cursor;
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
        ActiveSocialProfileQuery_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.ActiveSocialProfileQuery_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("user");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public ActiveSocialProfileQuery.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                ActiveSocialProfileQuery.User user = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    user = (ActiveSocialProfileQuery.User) Adapters.m2609nullable(Adapters.m2610obj(ActiveSocialProfileQuery_ResponseAdapter$User.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                }
                return new ActiveSocialProfileQuery.Data(user);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, ActiveSocialProfileQuery.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("user");
                Adapters.m2609nullable(Adapters.m2610obj(ActiveSocialProfileQuery_ResponseAdapter$User.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getUser());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: ActiveSocialProfileQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ActiveSocialProfileQuery$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/ActiveSocialProfileQuery$User;", "user", "Lcom/amazon/music/platform/graphql/generated/ActiveSocialProfileQuery$User;", "getUser", "()Lcom/amazon/music/platform/graphql/generated/ActiveSocialProfileQuery$User;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/ActiveSocialProfileQuery$User;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: ActiveSocialProfileQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ActiveSocialProfileQuery$User;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded;", "socialProfilesUserEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded;", "getSocialProfilesUserEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class User {
        private final String __typename;
        private final SocialProfilesUserEmbedded socialProfilesUserEmbedded;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof User)) {
                return false;
            }
            User user = (User) other;
            return Intrinsics.areEqual(this.__typename, user.__typename) && Intrinsics.areEqual(this.socialProfilesUserEmbedded, user.socialProfilesUserEmbedded);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.socialProfilesUserEmbedded.hashCode();
        }

        public String toString() {
            return "User(__typename=" + this.__typename + ", socialProfilesUserEmbedded=" + this.socialProfilesUserEmbedded + ')';
        }

        public User(String __typename, SocialProfilesUserEmbedded socialProfilesUserEmbedded) {
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
