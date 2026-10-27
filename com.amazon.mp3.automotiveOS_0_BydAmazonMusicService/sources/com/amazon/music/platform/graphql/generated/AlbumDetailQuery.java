package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.AlbumDetailQuery;
import com.amazon.music.platform.graphql.generated.adapter.AlbumDetailQuery_VariablesAdapter;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlbumDetailQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\b\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004\u001c\u001b\u001d\u001eB\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/AlbumDetailQuery;", "Lcom/apollographql/apollo3/api/Query;", "Lcom/amazon/music/platform/graphql/generated/AlbumDetailQuery$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "Companion", "Album", "Data", "Track", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class AlbumDetailQuery implements Query<Data> {
    private final String id;

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "query AlbumDetail($id: String!) { album(id: $id) { __typename ...album tracks { __typename ...track } } }  fragment eligibility on Eligibility { isDownloadable isPreviewable isPrimeEligible isPurchaseable isPreorderable playbackSubscriptionTiers playback { shuffleType canPlayOnDemand } }  fragment image on Image { width height aspectRatio url externalUrl imageType description }  fragment artistEmbedded on Artist { id name images { __typename ...image } }  fragment artistConnection on ArtistConnection { edges { node { __typename ...artistEmbedded } role } }  fragment parentalSettings on ParentalSettings { hasExplicitLanguage isKidFriendly }  fragment trackEmbedded on Track { id title duration mediaType trackNumber languageOfPerformance eligibility { __typename ...eligibility } parentalSettings { __typename ...parentalSettings } contributingArtists { __typename ...artistConnection } images { __typename ...image } audioQualities }  fragment album on Album { id title url releaseDate eligibility { __typename ...eligibility } images { __typename ...image } artists { __typename ...artistEmbedded } contributingArtists { __typename ...artistConnection } duration label parentalSettings { __typename ...parentalSettings } albumTracks: tracks { __typename ...trackEmbedded } trackCount copyright languageOfPerformance audioQualities }  fragment albumEmbedded on Album { id title releaseDate images { __typename ...image } }  fragment track on Track { id title url artists { __typename ...artistEmbedded } contributingArtists { __typename ...artistConnection } album { __typename ...albumEmbedded } duration eligibility { __typename ...eligibility } parentalSettings { __typename ...parentalSettings } releaseDate images { __typename ...image } mediaType trackNumber languageOfPerformance audioQualities }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AlbumDetailQuery) && Intrinsics.areEqual(this.id, ((AlbumDetailQuery) other).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "a03baca25265699c5e375e068638894968ba5ce86d6d1ffac3c1d240b51618b9";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "AlbumDetail";
    }

    public String toString() {
        return "AlbumDetailQuery(id=" + this.id + ')';
    }

    public AlbumDetailQuery(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
    }

    public final String getId() {
        return this.id;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        AlbumDetailQuery_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.AlbumDetailQuery_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("album");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public AlbumDetailQuery.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                AlbumDetailQuery.Album album = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    album = (AlbumDetailQuery.Album) Adapters.m2609nullable(Adapters.m2610obj(AlbumDetailQuery_ResponseAdapter$Album.INSTANCE, true)).fromJson(reader, customScalarAdapters);
                }
                return new AlbumDetailQuery.Data(album);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, AlbumDetailQuery.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("album");
                Adapters.m2609nullable(Adapters.m2610obj(AlbumDetailQuery_ResponseAdapter$Album.INSTANCE, true)).toJson(writer, customScalarAdapters, value.getAlbum());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: AlbumDetailQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/AlbumDetailQuery$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/AlbumDetailQuery$Album;", "album", "Lcom/amazon/music/platform/graphql/generated/AlbumDetailQuery$Album;", "getAlbum", "()Lcom/amazon/music/platform/graphql/generated/AlbumDetailQuery$Album;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/AlbumDetailQuery$Album;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final Album album;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.album, ((Data) other).album);
        }

        public int hashCode() {
            Album album = this.album;
            if (album == null) {
                return 0;
            }
            return album.hashCode();
        }

        public String toString() {
            return "Data(album=" + this.album + ')';
        }

        public Data(Album album) {
            this.album = album;
        }

        public final Album getAlbum() {
            return this.album;
        }
    }

    /* JADX INFO: compiled from: AlbumDetailQuery.kt */
    @Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0010\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\r\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR!\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/AlbumDetailQuery$Album;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "", "Lcom/amazon/music/platform/graphql/generated/AlbumDetailQuery$Track;", "tracks", "Ljava/util/List;", "getTracks", "()Ljava/util/List;", "Lcom/amazon/music/platform/graphql/generated/fragment/Album;", "album", "Lcom/amazon/music/platform/graphql/generated/fragment/Album;", "getAlbum", "()Lcom/amazon/music/platform/graphql/generated/fragment/Album;", "<init>", "(Ljava/lang/String;Ljava/util/List;Lcom/amazon/music/platform/graphql/generated/fragment/Album;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Album {
        private final String __typename;
        private final com.amazon.music.platform.graphql.generated.fragment.Album album;
        private final List<Track> tracks;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Album)) {
                return false;
            }
            Album album = (Album) other;
            return Intrinsics.areEqual(this.__typename, album.__typename) && Intrinsics.areEqual(this.tracks, album.tracks) && Intrinsics.areEqual(this.album, album.album);
        }

        public int hashCode() {
            int iHashCode = this.__typename.hashCode() * 31;
            List<Track> list = this.tracks;
            return ((iHashCode + (list == null ? 0 : list.hashCode())) * 31) + this.album.hashCode();
        }

        public String toString() {
            return "Album(__typename=" + this.__typename + ", tracks=" + this.tracks + ", album=" + this.album + ')';
        }

        public Album(String __typename, List<Track> list, com.amazon.music.platform.graphql.generated.fragment.Album album) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(album, "album");
            this.__typename = __typename;
            this.tracks = list;
            this.album = album;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final List<Track> getTracks() {
            return this.tracks;
        }

        public final com.amazon.music.platform.graphql.generated.fragment.Album getAlbum() {
            return this.album;
        }
    }

    /* JADX INFO: compiled from: AlbumDetailQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/AlbumDetailQuery$Track;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Track;", "track", "Lcom/amazon/music/platform/graphql/generated/fragment/Track;", "getTrack", "()Lcom/amazon/music/platform/graphql/generated/fragment/Track;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Track;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Track {
        private final String __typename;
        private final com.amazon.music.platform.graphql.generated.fragment.Track track;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Track)) {
                return false;
            }
            Track track = (Track) other;
            return Intrinsics.areEqual(this.__typename, track.__typename) && Intrinsics.areEqual(this.track, track.track);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.track.hashCode();
        }

        public String toString() {
            return "Track(__typename=" + this.__typename + ", track=" + this.track + ')';
        }

        public Track(String __typename, com.amazon.music.platform.graphql.generated.fragment.Track track) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(track, "track");
            this.__typename = __typename;
            this.track = track;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final com.amazon.music.platform.graphql.generated.fragment.Track getTrack() {
            return this.track;
        }
    }
}
