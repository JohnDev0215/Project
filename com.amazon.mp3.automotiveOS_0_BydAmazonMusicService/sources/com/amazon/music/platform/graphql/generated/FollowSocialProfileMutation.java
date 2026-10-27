package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.FollowSocialProfileMutation;
import com.amazon.music.platform.graphql.generated.adapter.FollowSocialProfileMutation_VariablesAdapter;
import com.amazon.music.platform.graphql.generated.fragment.FollowUserResponse;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Mutation;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Optional;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FollowSocialProfileMutation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001c\u001d\u001eB\u0019\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u001f\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00168\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/FollowSocialProfileMutation;", "Lcom/apollographql/apollo3/api/Mutation;", "Lcom/amazon/music/platform/graphql/generated/FollowSocialProfileMutation$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/apollographql/apollo3/api/Optional;", "Lcom/apollographql/apollo3/api/Optional;", "getId", "()Lcom/apollographql/apollo3/api/Optional;", "<init>", "(Lcom/apollographql/apollo3/api/Optional;)V", "Companion", "Data", "FollowUser", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class FollowSocialProfileMutation implements Mutation<Data> {
    private final Optional<String> id;

    public FollowSocialProfileMutation() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "mutation FollowSocialProfile($id: String) { followUser(id: $id) { __typename ...followUserResponse } }  fragment followUserResponse on FollowUserResponse { user { id } followState }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FollowSocialProfileMutation) && Intrinsics.areEqual(this.id, ((FollowSocialProfileMutation) other).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "3344ee581ea5df46324e8fa8c6041f7e57333eba5d94a5dd643095097f2b2561";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "FollowSocialProfile";
    }

    public String toString() {
        return "FollowSocialProfileMutation(id=" + this.id + ')';
    }

    public FollowSocialProfileMutation(Optional<String> id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
    }

    public /* synthetic */ FollowSocialProfileMutation(Optional optional, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Optional.Absent.INSTANCE : optional);
    }

    public final Optional<String> getId() {
        return this.id;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        FollowSocialProfileMutation_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.FollowSocialProfileMutation_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("followUser");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public FollowSocialProfileMutation.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                FollowSocialProfileMutation.FollowUser followUser = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    followUser = (FollowSocialProfileMutation.FollowUser) Adapters.m2609nullable(Adapters.m2610obj(FollowSocialProfileMutation_ResponseAdapter$FollowUser.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                }
                return new FollowSocialProfileMutation.Data(followUser);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, FollowSocialProfileMutation.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("followUser");
                Adapters.m2609nullable(Adapters.m2610obj(FollowSocialProfileMutation_ResponseAdapter$FollowUser.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getFollowUser());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: FollowSocialProfileMutation.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/FollowSocialProfileMutation$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/FollowSocialProfileMutation$FollowUser;", "followUser", "Lcom/amazon/music/platform/graphql/generated/FollowSocialProfileMutation$FollowUser;", "getFollowUser", "()Lcom/amazon/music/platform/graphql/generated/FollowSocialProfileMutation$FollowUser;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/FollowSocialProfileMutation$FollowUser;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final FollowUser followUser;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.followUser, ((Data) other).followUser);
        }

        public int hashCode() {
            FollowUser followUser = this.followUser;
            if (followUser == null) {
                return 0;
            }
            return followUser.hashCode();
        }

        public String toString() {
            return "Data(followUser=" + this.followUser + ')';
        }

        public Data(FollowUser followUser) {
            this.followUser = followUser;
        }

        public final FollowUser getFollowUser() {
            return this.followUser;
        }
    }

    /* JADX INFO: compiled from: FollowSocialProfileMutation.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/FollowSocialProfileMutation$FollowUser;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse;", "followUserResponse", "Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse;", "getFollowUserResponse", "()Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class FollowUser {
        private final String __typename;
        private final FollowUserResponse followUserResponse;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FollowUser)) {
                return false;
            }
            FollowUser followUser = (FollowUser) other;
            return Intrinsics.areEqual(this.__typename, followUser.__typename) && Intrinsics.areEqual(this.followUserResponse, followUser.followUserResponse);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.followUserResponse.hashCode();
        }

        public String toString() {
            return "FollowUser(__typename=" + this.__typename + ", followUserResponse=" + this.followUserResponse + ')';
        }

        public FollowUser(String __typename, FollowUserResponse followUserResponse) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(followUserResponse, "followUserResponse");
            this.__typename = __typename;
            this.followUserResponse = followUserResponse;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final FollowUserResponse getFollowUserResponse() {
            return this.followUserResponse;
        }
    }
}
