package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.UnfollowArtistMutation;
import com.amazon.music.platform.graphql.generated.adapter.UnfollowArtistMutation_VariablesAdapter;
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

/* JADX INFO: compiled from: UnfollowArtistMutation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001c\u001d\u001eB\u0019\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u001f\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00168\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/UnfollowArtistMutation;", "Lcom/apollographql/apollo3/api/Mutation;", "Lcom/amazon/music/platform/graphql/generated/UnfollowArtistMutation$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/apollographql/apollo3/api/Optional;", "Lcom/apollographql/apollo3/api/Optional;", "getId", "()Lcom/apollographql/apollo3/api/Optional;", "<init>", "(Lcom/apollographql/apollo3/api/Optional;)V", "Companion", "Data", "UnfollowArtist", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class UnfollowArtistMutation implements Mutation<Data> {
    private final Optional<String> id;

    public UnfollowArtistMutation() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "mutation UnfollowArtist($id: String) { unfollowArtist(id: $id) { followState } }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof UnfollowArtistMutation) && Intrinsics.areEqual(this.id, ((UnfollowArtistMutation) other).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "48f929f816994ae005484c2ef1e6c21de78fdf20dc19d19415ac0642aab05317";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "UnfollowArtist";
    }

    public String toString() {
        return "UnfollowArtistMutation(id=" + this.id + ')';
    }

    public UnfollowArtistMutation(Optional<String> id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
    }

    public /* synthetic */ UnfollowArtistMutation(Optional optional, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Optional.Absent.INSTANCE : optional);
    }

    public final Optional<String> getId() {
        return this.id;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        UnfollowArtistMutation_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.UnfollowArtistMutation_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("unfollowArtist");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public UnfollowArtistMutation.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                UnfollowArtistMutation.UnfollowArtist unfollowArtist = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    unfollowArtist = (UnfollowArtistMutation.UnfollowArtist) Adapters.m2609nullable(Adapters.m2611obj$default(UnfollowArtistMutation_ResponseAdapter$UnfollowArtist.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
                }
                return new UnfollowArtistMutation.Data(unfollowArtist);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, UnfollowArtistMutation.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("unfollowArtist");
                Adapters.m2609nullable(Adapters.m2611obj$default(UnfollowArtistMutation_ResponseAdapter$UnfollowArtist.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getUnfollowArtist());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: UnfollowArtistMutation.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/UnfollowArtistMutation$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/UnfollowArtistMutation$UnfollowArtist;", "unfollowArtist", "Lcom/amazon/music/platform/graphql/generated/UnfollowArtistMutation$UnfollowArtist;", "getUnfollowArtist", "()Lcom/amazon/music/platform/graphql/generated/UnfollowArtistMutation$UnfollowArtist;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/UnfollowArtistMutation$UnfollowArtist;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final UnfollowArtist unfollowArtist;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.unfollowArtist, ((Data) other).unfollowArtist);
        }

        public int hashCode() {
            UnfollowArtist unfollowArtist = this.unfollowArtist;
            if (unfollowArtist == null) {
                return 0;
            }
            return unfollowArtist.hashCode();
        }

        public String toString() {
            return "Data(unfollowArtist=" + this.unfollowArtist + ')';
        }

        public Data(UnfollowArtist unfollowArtist) {
            this.unfollowArtist = unfollowArtist;
        }

        public final UnfollowArtist getUnfollowArtist() {
            return this.unfollowArtist;
        }
    }

    /* JADX INFO: compiled from: UnfollowArtistMutation.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/UnfollowArtistMutation$UnfollowArtist;", "", "", "toString", "", "hashCode", "other", "", "equals", "followState", "Ljava/lang/String;", "getFollowState", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class UnfollowArtist {
        private final String followState;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UnfollowArtist) && Intrinsics.areEqual(this.followState, ((UnfollowArtist) other).followState);
        }

        public int hashCode() {
            String str = this.followState;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "UnfollowArtist(followState=" + this.followState + ')';
        }

        public UnfollowArtist(String str) {
            this.followState = str;
        }

        public final String getFollowState() {
            return this.followState;
        }
    }
}
