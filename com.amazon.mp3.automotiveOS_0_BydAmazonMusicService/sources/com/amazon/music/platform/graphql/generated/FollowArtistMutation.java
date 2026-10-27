package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.FollowArtistMutation;
import com.amazon.music.platform.graphql.generated.adapter.FollowArtistMutation_VariablesAdapter;
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

/* JADX INFO: compiled from: FollowArtistMutation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001c\u001d\u001eB\u0019\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u001f\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00168\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/FollowArtistMutation;", "Lcom/apollographql/apollo3/api/Mutation;", "Lcom/amazon/music/platform/graphql/generated/FollowArtistMutation$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/apollographql/apollo3/api/Optional;", "Lcom/apollographql/apollo3/api/Optional;", "getId", "()Lcom/apollographql/apollo3/api/Optional;", "<init>", "(Lcom/apollographql/apollo3/api/Optional;)V", "Companion", "Data", "FollowArtist", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class FollowArtistMutation implements Mutation<Data> {
    private final Optional<String> id;

    public FollowArtistMutation() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "mutation FollowArtist($id: String) { followArtist(id: $id) { followState } }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FollowArtistMutation) && Intrinsics.areEqual(this.id, ((FollowArtistMutation) other).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "51ee6e8df06e0671ef0e3151c7e4479e647d672ba2dd78134df3886469f6e783";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "FollowArtist";
    }

    public String toString() {
        return "FollowArtistMutation(id=" + this.id + ')';
    }

    public FollowArtistMutation(Optional<String> id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
    }

    public /* synthetic */ FollowArtistMutation(Optional optional, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Optional.Absent.INSTANCE : optional);
    }

    public final Optional<String> getId() {
        return this.id;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        FollowArtistMutation_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.FollowArtistMutation_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("followArtist");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public FollowArtistMutation.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                FollowArtistMutation.FollowArtist followArtist = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    followArtist = (FollowArtistMutation.FollowArtist) Adapters.m2609nullable(Adapters.m2611obj$default(FollowArtistMutation_ResponseAdapter$FollowArtist.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
                }
                return new FollowArtistMutation.Data(followArtist);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, FollowArtistMutation.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("followArtist");
                Adapters.m2609nullable(Adapters.m2611obj$default(FollowArtistMutation_ResponseAdapter$FollowArtist.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getFollowArtist());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: FollowArtistMutation.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/FollowArtistMutation$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/FollowArtistMutation$FollowArtist;", "followArtist", "Lcom/amazon/music/platform/graphql/generated/FollowArtistMutation$FollowArtist;", "getFollowArtist", "()Lcom/amazon/music/platform/graphql/generated/FollowArtistMutation$FollowArtist;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/FollowArtistMutation$FollowArtist;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final FollowArtist followArtist;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.followArtist, ((Data) other).followArtist);
        }

        public int hashCode() {
            FollowArtist followArtist = this.followArtist;
            if (followArtist == null) {
                return 0;
            }
            return followArtist.hashCode();
        }

        public String toString() {
            return "Data(followArtist=" + this.followArtist + ')';
        }

        public Data(FollowArtist followArtist) {
            this.followArtist = followArtist;
        }

        public final FollowArtist getFollowArtist() {
            return this.followArtist;
        }
    }

    /* JADX INFO: compiled from: FollowArtistMutation.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/FollowArtistMutation$FollowArtist;", "", "", "toString", "", "hashCode", "other", "", "equals", "followState", "Ljava/lang/String;", "getFollowState", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class FollowArtist {
        private final String followState;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FollowArtist) && Intrinsics.areEqual(this.followState, ((FollowArtist) other).followState);
        }

        public int hashCode() {
            String str = this.followState;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "FollowArtist(followState=" + this.followState + ')';
        }

        public FollowArtist(String str) {
            this.followState = str;
        }

        public final String getFollowState() {
            return this.followState;
        }
    }
}
