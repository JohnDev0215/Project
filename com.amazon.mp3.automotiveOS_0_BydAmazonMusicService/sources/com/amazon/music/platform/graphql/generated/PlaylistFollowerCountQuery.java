package com.amazon.music.platform.graphql.generated;

import com.amazon.mp3.environment.Environment;
import com.amazon.music.platform.graphql.generated.PlaylistFollowerCountQuery;
import com.amazon.music.platform.graphql.generated.adapter.PlaylistFollowerCountQuery_VariablesAdapter;
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

/* JADX INFO: compiled from: PlaylistFollowerCountQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001c\u001d\u001eB\u0019\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u001f\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00168\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/PlaylistFollowerCountQuery;", "Lcom/apollographql/apollo3/api/Query;", "Lcom/amazon/music/platform/graphql/generated/PlaylistFollowerCountQuery$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/apollographql/apollo3/api/Optional;", "Lcom/apollographql/apollo3/api/Optional;", "getId", "()Lcom/apollographql/apollo3/api/Optional;", "<init>", "(Lcom/apollographql/apollo3/api/Optional;)V", "Companion", "Data", "Playlist", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class PlaylistFollowerCountQuery implements Query<Data> {
    private final Optional<String> id;

    public PlaylistFollowerCountQuery() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "query PlaylistFollowerCount($id: String) { playlist(id: $id) { id followerCount } }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PlaylistFollowerCountQuery) && Intrinsics.areEqual(this.id, ((PlaylistFollowerCountQuery) other).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "5f15a3b86fd8dad91429dd4f291b7b3f3f26186f0423d52e7ad4d8bfc870e0b7";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "PlaylistFollowerCount";
    }

    public String toString() {
        return "PlaylistFollowerCountQuery(id=" + this.id + ')';
    }

    public PlaylistFollowerCountQuery(Optional<String> id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
    }

    public /* synthetic */ PlaylistFollowerCountQuery(Optional optional, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Optional.Absent.INSTANCE : optional);
    }

    public final Optional<String> getId() {
        return this.id;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        PlaylistFollowerCountQuery_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.PlaylistFollowerCountQuery_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf(Environment.PLAYLIST_PATH);

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public PlaylistFollowerCountQuery.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                PlaylistFollowerCountQuery.Playlist playlist = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    playlist = (PlaylistFollowerCountQuery.Playlist) Adapters.m2609nullable(Adapters.m2611obj$default(PlaylistFollowerCountQuery_ResponseAdapter$Playlist.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
                }
                return new PlaylistFollowerCountQuery.Data(playlist);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, PlaylistFollowerCountQuery.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name(Environment.PLAYLIST_PATH);
                Adapters.m2609nullable(Adapters.m2611obj$default(PlaylistFollowerCountQuery_ResponseAdapter$Playlist.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getPlaylist());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: PlaylistFollowerCountQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/PlaylistFollowerCountQuery$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/PlaylistFollowerCountQuery$Playlist;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/platform/graphql/generated/PlaylistFollowerCountQuery$Playlist;", "getPlaylist", "()Lcom/amazon/music/platform/graphql/generated/PlaylistFollowerCountQuery$Playlist;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/PlaylistFollowerCountQuery$Playlist;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final Playlist playlist;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.playlist, ((Data) other).playlist);
        }

        public int hashCode() {
            Playlist playlist = this.playlist;
            if (playlist == null) {
                return 0;
            }
            return playlist.hashCode();
        }

        public String toString() {
            return "Data(playlist=" + this.playlist + ')';
        }

        public Data(Playlist playlist) {
            this.playlist = playlist;
        }

        public final Playlist getPlaylist() {
            return this.playlist;
        }
    }

    /* JADX INFO: compiled from: PlaylistFollowerCountQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/PlaylistFollowerCountQuery$Playlist;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "", "followerCount", "Ljava/lang/Double;", "getFollowerCount", "()Ljava/lang/Double;", "<init>", "(Ljava/lang/String;Ljava/lang/Double;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Playlist {
        private final Double followerCount;
        private final String id;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Playlist)) {
                return false;
            }
            Playlist playlist = (Playlist) other;
            return Intrinsics.areEqual(this.id, playlist.id) && Intrinsics.areEqual((Object) this.followerCount, (Object) playlist.followerCount);
        }

        public int hashCode() {
            String str = this.id;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Double d = this.followerCount;
            return iHashCode + (d != null ? d.hashCode() : 0);
        }

        public String toString() {
            return "Playlist(id=" + this.id + ", followerCount=" + this.followerCount + ')';
        }

        public Playlist(String str, Double d) {
            this.id = str;
            this.followerCount = d;
        }

        public final String getId() {
            return this.id;
        }

        public final Double getFollowerCount() {
            return this.followerCount;
        }
    }
}
