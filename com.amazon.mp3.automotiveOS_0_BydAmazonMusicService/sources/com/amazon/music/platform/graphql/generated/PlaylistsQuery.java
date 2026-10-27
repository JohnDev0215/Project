package com.amazon.music.platform.graphql.generated;

import com.amazon.mp3.environment.Environment;
import com.amazon.music.platform.graphql.generated.PlaylistsQuery;
import com.amazon.music.platform.graphql.generated.adapter.PlaylistsQuery_VariablesAdapter;
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

/* JADX INFO: compiled from: PlaylistsQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\n\b\u0086\b\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001e\u001f B!\u0012\u0018\b\u0002\u0010\u0018\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00170\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R'\u0010\u0018\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00170\u00168\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006!"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/PlaylistsQuery;", "Lcom/apollographql/apollo3/api/Query;", "Lcom/amazon/music/platform/graphql/generated/PlaylistsQuery$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/apollographql/apollo3/api/Optional;", "", "ids", "Lcom/apollographql/apollo3/api/Optional;", "getIds", "()Lcom/apollographql/apollo3/api/Optional;", "<init>", "(Lcom/apollographql/apollo3/api/Optional;)V", "Companion", "Data", "Playlist", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class PlaylistsQuery implements Query<Data> {
    private final Optional<List<String>> ids;

    public PlaylistsQuery() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "query Playlists($ids: [String]) { playlists(ids: $ids) { __typename ...playlist } }  fragment eligibility on Eligibility { isDownloadable isPreviewable isPrimeEligible isPurchaseable isPreorderable playbackSubscriptionTiers playback { shuffleType canPlayOnDemand } }  fragment image on Image { width height aspectRatio url externalUrl imageType description }  fragment parentalSettings on ParentalSettings { hasExplicitLanguage isKidFriendly }  fragment artistEmbedded on Artist { id name images { __typename ...image } }  fragment artistConnection on ArtistConnection { edges { node { __typename ...artistEmbedded } role } }  fragment trackEmbedded on Track { id title duration mediaType trackNumber languageOfPerformance eligibility { __typename ...eligibility } parentalSettings { __typename ...parentalSettings } contributingArtists { __typename ...artistConnection } images { __typename ...image } audioQualities }  fragment playlist on Playlist { id title url trackCount eligibility { __typename ...eligibility } images { __typename ...image } tracks { edges { node { __typename ...trackEmbedded } } } curator { name } duration }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PlaylistsQuery) && Intrinsics.areEqual(this.ids, ((PlaylistsQuery) other).ids);
    }

    public int hashCode() {
        return this.ids.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "3008e632ab35cd356c73d072185a3c1622283f4a2b2c8570b019a3e8beca2160";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "Playlists";
    }

    public String toString() {
        return "PlaylistsQuery(ids=" + this.ids + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PlaylistsQuery(Optional<? extends List<String>> ids) {
        Intrinsics.checkNotNullParameter(ids, "ids");
        this.ids = ids;
    }

    public /* synthetic */ PlaylistsQuery(Optional optional, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Optional.Absent.INSTANCE : optional);
    }

    public final Optional<List<String>> getIds() {
        return this.ids;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        PlaylistsQuery_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.PlaylistsQuery_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("playlists");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public PlaylistsQuery.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                List list = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    list = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(PlaylistsQuery_ResponseAdapter$Playlist.INSTANCE, true)))).fromJson(reader, customScalarAdapters);
                }
                return new PlaylistsQuery.Data(list);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, PlaylistsQuery.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("playlists");
                Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2610obj(PlaylistsQuery_ResponseAdapter$Playlist.INSTANCE, true)))).toJson(writer, customScalarAdapters, value.getPlaylists());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: PlaylistsQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R!\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/PlaylistsQuery$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/music/platform/graphql/generated/PlaylistsQuery$Playlist;", "playlists", "Ljava/util/List;", "getPlaylists", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final List<Playlist> playlists;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.playlists, ((Data) other).playlists);
        }

        public int hashCode() {
            List<Playlist> list = this.playlists;
            if (list == null) {
                return 0;
            }
            return list.hashCode();
        }

        public String toString() {
            return "Data(playlists=" + this.playlists + ')';
        }

        public Data(List<Playlist> list) {
            this.playlists = list;
        }

        public final List<Playlist> getPlaylists() {
            return this.playlists;
        }
    }

    /* JADX INFO: compiled from: PlaylistsQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/PlaylistsQuery$Playlist;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Playlist;", Environment.PLAYLIST_PATH, "Lcom/amazon/music/platform/graphql/generated/fragment/Playlist;", "getPlaylist", "()Lcom/amazon/music/platform/graphql/generated/fragment/Playlist;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Playlist;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Playlist {
        private final String __typename;
        private final com.amazon.music.platform.graphql.generated.fragment.Playlist playlist;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Playlist)) {
                return false;
            }
            Playlist playlist = (Playlist) other;
            return Intrinsics.areEqual(this.__typename, playlist.__typename) && Intrinsics.areEqual(this.playlist, playlist.playlist);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.playlist.hashCode();
        }

        public String toString() {
            return "Playlist(__typename=" + this.__typename + ", playlist=" + this.playlist + ')';
        }

        public Playlist(String __typename, com.amazon.music.platform.graphql.generated.fragment.Playlist playlist) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(playlist, "playlist");
            this.__typename = __typename;
            this.playlist = playlist;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final com.amazon.music.platform.graphql.generated.fragment.Playlist getPlaylist() {
            return this.playlist;
        }
    }
}
