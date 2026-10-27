package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.UpdateSocialProfileMutation;
import com.amazon.music.platform.graphql.generated.adapter.UpdateSocialProfileMutation_VariablesAdapter;
import com.amazon.music.platform.graphql.generated.fragment.UserEmbedded;
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

/* JADX INFO: compiled from: UpdateSocialProfileMutation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\b\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003$%&Ba\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016\u0012\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016\u0012\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016\u0012\u0010\b\u0002\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016\u0012\u0010\b\u0002\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016¢\u0006\u0004\b\"\u0010#J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u001f\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00168\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001f\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00168\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u0017\u001a\u0004\b\u001b\u0010\u0019R\u001f\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00168\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0017\u001a\u0004\b\u001d\u0010\u0019R\u001f\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00168\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u0017\u001a\u0004\b\u001f\u0010\u0019R\u001f\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00168\u0006¢\u0006\f\n\u0004\b \u0010\u0017\u001a\u0004\b!\u0010\u0019¨\u0006'"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/UpdateSocialProfileMutation;", "Lcom/apollographql/apollo3/api/Mutation;", "Lcom/amazon/music/platform/graphql/generated/UpdateSocialProfileMutation$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/apollographql/apollo3/api/Optional;", "Lcom/apollographql/apollo3/api/Optional;", "getName", "()Lcom/apollographql/apollo3/api/Optional;", "visibility", "getVisibility", "playbackVisibility", "getPlaybackVisibility", "imageUrl", "getImageUrl", "handle", "getHandle", "<init>", "(Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;Lcom/apollographql/apollo3/api/Optional;)V", "Companion", "Data", "UpdateUser", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class UpdateSocialProfileMutation implements Mutation<Data> {
    private final Optional<String> handle;
    private final Optional<String> imageUrl;
    private final Optional<String> name;
    private final Optional<String> playbackVisibility;
    private final Optional<String> visibility;

    public UpdateSocialProfileMutation() {
        this(null, null, null, null, null, 31, null);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "mutation UpdateSocialProfile($name: String, $visibility: String, $playbackVisibility: String, $imageUrl: String, $handle: String) { updateUser(name: $name, visibility: $visibility, playbackVisibility: $playbackVisibility, imageUrl: $imageUrl, handle: $handle) { __typename ...userEmbedded } }  fragment userEmbedded on User { id name }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UpdateSocialProfileMutation)) {
            return false;
        }
        UpdateSocialProfileMutation updateSocialProfileMutation = (UpdateSocialProfileMutation) other;
        return Intrinsics.areEqual(this.name, updateSocialProfileMutation.name) && Intrinsics.areEqual(this.visibility, updateSocialProfileMutation.visibility) && Intrinsics.areEqual(this.playbackVisibility, updateSocialProfileMutation.playbackVisibility) && Intrinsics.areEqual(this.imageUrl, updateSocialProfileMutation.imageUrl) && Intrinsics.areEqual(this.handle, updateSocialProfileMutation.handle);
    }

    public int hashCode() {
        return (((((((this.name.hashCode() * 31) + this.visibility.hashCode()) * 31) + this.playbackVisibility.hashCode()) * 31) + this.imageUrl.hashCode()) * 31) + this.handle.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "f0c5c722e957013f0090f582a83bc847a9c807184026b72752e6d1c24c62beff";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "UpdateSocialProfile";
    }

    public String toString() {
        return "UpdateSocialProfileMutation(name=" + this.name + ", visibility=" + this.visibility + ", playbackVisibility=" + this.playbackVisibility + ", imageUrl=" + this.imageUrl + ", handle=" + this.handle + ')';
    }

    public UpdateSocialProfileMutation(Optional<String> name, Optional<String> visibility, Optional<String> playbackVisibility, Optional<String> imageUrl, Optional<String> handle) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(visibility, "visibility");
        Intrinsics.checkNotNullParameter(playbackVisibility, "playbackVisibility");
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        Intrinsics.checkNotNullParameter(handle, "handle");
        this.name = name;
        this.visibility = visibility;
        this.playbackVisibility = playbackVisibility;
        this.imageUrl = imageUrl;
        this.handle = handle;
    }

    public /* synthetic */ UpdateSocialProfileMutation(Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Optional.Absent.INSTANCE : optional, (i & 2) != 0 ? Optional.Absent.INSTANCE : optional2, (i & 4) != 0 ? Optional.Absent.INSTANCE : optional3, (i & 8) != 0 ? Optional.Absent.INSTANCE : optional4, (i & 16) != 0 ? Optional.Absent.INSTANCE : optional5);
    }

    public final Optional<String> getName() {
        return this.name;
    }

    public final Optional<String> getVisibility() {
        return this.visibility;
    }

    public final Optional<String> getPlaybackVisibility() {
        return this.playbackVisibility;
    }

    public final Optional<String> getImageUrl() {
        return this.imageUrl;
    }

    public final Optional<String> getHandle() {
        return this.handle;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        UpdateSocialProfileMutation_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.UpdateSocialProfileMutation_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("updateUser");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public UpdateSocialProfileMutation.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                UpdateSocialProfileMutation.UpdateUser updateUser = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    updateUser = (UpdateSocialProfileMutation.UpdateUser) Adapters.m2609nullable(Adapters.m2610obj(UpdateSocialProfileMutation_ResponseAdapter$UpdateUser.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                }
                return new UpdateSocialProfileMutation.Data(updateUser);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, UpdateSocialProfileMutation.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("updateUser");
                Adapters.m2609nullable(Adapters.m2610obj(UpdateSocialProfileMutation_ResponseAdapter$UpdateUser.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getUpdateUser());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: UpdateSocialProfileMutation.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/UpdateSocialProfileMutation$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/UpdateSocialProfileMutation$UpdateUser;", "updateUser", "Lcom/amazon/music/platform/graphql/generated/UpdateSocialProfileMutation$UpdateUser;", "getUpdateUser", "()Lcom/amazon/music/platform/graphql/generated/UpdateSocialProfileMutation$UpdateUser;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/UpdateSocialProfileMutation$UpdateUser;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final UpdateUser updateUser;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.updateUser, ((Data) other).updateUser);
        }

        public int hashCode() {
            UpdateUser updateUser = this.updateUser;
            if (updateUser == null) {
                return 0;
            }
            return updateUser.hashCode();
        }

        public String toString() {
            return "Data(updateUser=" + this.updateUser + ')';
        }

        public Data(UpdateUser updateUser) {
            this.updateUser = updateUser;
        }

        public final UpdateUser getUpdateUser() {
            return this.updateUser;
        }
    }

    /* JADX INFO: compiled from: UpdateSocialProfileMutation.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/UpdateSocialProfileMutation$UpdateUser;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/UserEmbedded;", "userEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/UserEmbedded;", "getUserEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/UserEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/UserEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class UpdateUser {
        private final String __typename;
        private final UserEmbedded userEmbedded;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UpdateUser)) {
                return false;
            }
            UpdateUser updateUser = (UpdateUser) other;
            return Intrinsics.areEqual(this.__typename, updateUser.__typename) && Intrinsics.areEqual(this.userEmbedded, updateUser.userEmbedded);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.userEmbedded.hashCode();
        }

        public String toString() {
            return "UpdateUser(__typename=" + this.__typename + ", userEmbedded=" + this.userEmbedded + ')';
        }

        public UpdateUser(String __typename, UserEmbedded userEmbedded) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(userEmbedded, "userEmbedded");
            this.__typename = __typename;
            this.userEmbedded = userEmbedded;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final UserEmbedded getUserEmbedded() {
            return this.userEmbedded;
        }
    }
}
