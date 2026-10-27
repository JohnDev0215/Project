package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.UnfollowSocialProfileMutation;
import com.amazon.music.platform.graphql.generated.adapter.UnfollowSocialProfileMutation_VariablesAdapter;
import com.amazon.music.platform.graphql.generated.fragment.FollowUserResponse;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Mutation;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UnfollowSocialProfileMutation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001b\u001c\u001dB\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/UnfollowSocialProfileMutation;", "Lcom/apollographql/apollo3/api/Mutation;", "Lcom/amazon/music/platform/graphql/generated/UnfollowSocialProfileMutation$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "Companion", "Data", "UnfollowUser", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class UnfollowSocialProfileMutation implements Mutation<Data> {
    private final String id;

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "mutation UnfollowSocialProfile($id: String!) { unfollowUser(id: $id) { __typename ...followUserResponse } }  fragment followUserResponse on FollowUserResponse { user { id } followState }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof UnfollowSocialProfileMutation) && Intrinsics.areEqual(this.id, ((UnfollowSocialProfileMutation) other).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "9702f19b6f8348c087364aaefc4febd7c12f096e832950208b69a4b01aa6fdc7";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "UnfollowSocialProfile";
    }

    public String toString() {
        return "UnfollowSocialProfileMutation(id=" + this.id + ')';
    }

    public UnfollowSocialProfileMutation(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
    }

    public final String getId() {
        return this.id;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        UnfollowSocialProfileMutation_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.UnfollowSocialProfileMutation_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("unfollowUser");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public UnfollowSocialProfileMutation.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                UnfollowSocialProfileMutation.UnfollowUser unfollowUser = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    unfollowUser = (UnfollowSocialProfileMutation.UnfollowUser) Adapters.m2609nullable(Adapters.m2610obj(UnfollowSocialProfileMutation_ResponseAdapter$UnfollowUser.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                }
                return new UnfollowSocialProfileMutation.Data(unfollowUser);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, UnfollowSocialProfileMutation.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("unfollowUser");
                Adapters.m2609nullable(Adapters.m2610obj(UnfollowSocialProfileMutation_ResponseAdapter$UnfollowUser.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getUnfollowUser());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: UnfollowSocialProfileMutation.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/UnfollowSocialProfileMutation$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/UnfollowSocialProfileMutation$UnfollowUser;", "unfollowUser", "Lcom/amazon/music/platform/graphql/generated/UnfollowSocialProfileMutation$UnfollowUser;", "getUnfollowUser", "()Lcom/amazon/music/platform/graphql/generated/UnfollowSocialProfileMutation$UnfollowUser;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/UnfollowSocialProfileMutation$UnfollowUser;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final UnfollowUser unfollowUser;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.unfollowUser, ((Data) other).unfollowUser);
        }

        public int hashCode() {
            UnfollowUser unfollowUser = this.unfollowUser;
            if (unfollowUser == null) {
                return 0;
            }
            return unfollowUser.hashCode();
        }

        public String toString() {
            return "Data(unfollowUser=" + this.unfollowUser + ')';
        }

        public Data(UnfollowUser unfollowUser) {
            this.unfollowUser = unfollowUser;
        }

        public final UnfollowUser getUnfollowUser() {
            return this.unfollowUser;
        }
    }

    /* JADX INFO: compiled from: UnfollowSocialProfileMutation.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/UnfollowSocialProfileMutation$UnfollowUser;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse;", "followUserResponse", "Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse;", "getFollowUserResponse", "()Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/FollowUserResponse;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class UnfollowUser {
        private final String __typename;
        private final FollowUserResponse followUserResponse;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UnfollowUser)) {
                return false;
            }
            UnfollowUser unfollowUser = (UnfollowUser) other;
            return Intrinsics.areEqual(this.__typename, unfollowUser.__typename) && Intrinsics.areEqual(this.followUserResponse, unfollowUser.followUserResponse);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.followUserResponse.hashCode();
        }

        public String toString() {
            return "UnfollowUser(__typename=" + this.__typename + ", followUserResponse=" + this.followUserResponse + ')';
        }

        public UnfollowUser(String __typename, FollowUserResponse followUserResponse) {
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
