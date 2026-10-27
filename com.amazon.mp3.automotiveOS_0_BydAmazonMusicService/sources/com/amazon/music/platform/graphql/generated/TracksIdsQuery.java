package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.TracksIdsQuery;
import com.amazon.music.platform.graphql.generated.adapter.TracksIdsQuery_VariablesAdapter;
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

/* JADX INFO: compiled from: TracksIdsQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u000b\b\u0086\b\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004\u001f\u001e !B!\u0012\u0018\b\u0002\u0010\u0018\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00170\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R'\u0010\u0018\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00170\u00168\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\""}, d2 = {"Lcom/amazon/music/platform/graphql/generated/TracksIdsQuery;", "Lcom/apollographql/apollo3/api/Query;", "Lcom/amazon/music/platform/graphql/generated/TracksIdsQuery$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/apollographql/apollo3/api/Optional;", "", "ids", "Lcom/apollographql/apollo3/api/Optional;", "getIds", "()Lcom/apollographql/apollo3/api/Optional;", "<init>", "(Lcom/apollographql/apollo3/api/Optional;)V", "Companion", "Album", "Data", "Track", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class TracksIdsQuery implements Query<Data> {
    private final Optional<List<String>> ids;

    public TracksIdsQuery() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "query TracksIds($ids: [String]) { tracks(ids: $ids) { id album { id } } }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TracksIdsQuery) && Intrinsics.areEqual(this.ids, ((TracksIdsQuery) other).ids);
    }

    public int hashCode() {
        return this.ids.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "7f9a8c666cef6dc28fcdc264c53307340ed2fddde52ed89a262894eb9898e9b4";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "TracksIds";
    }

    public String toString() {
        return "TracksIdsQuery(ids=" + this.ids + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TracksIdsQuery(Optional<? extends List<String>> ids) {
        Intrinsics.checkNotNullParameter(ids, "ids");
        this.ids = ids;
    }

    public /* synthetic */ TracksIdsQuery(Optional optional, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Optional.Absent.INSTANCE : optional);
    }

    public final Optional<List<String>> getIds() {
        return this.ids;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        TracksIdsQuery_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.TracksIdsQuery_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("tracks");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public TracksIdsQuery.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                List list = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    list = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2611obj$default(TracksIdsQuery_ResponseAdapter$Track.INSTANCE, false, 1, null)))).fromJson(reader, customScalarAdapters);
                }
                return new TracksIdsQuery.Data(list);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, TracksIdsQuery.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("tracks");
                Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2611obj$default(TracksIdsQuery_ResponseAdapter$Track.INSTANCE, false, 1, null)))).toJson(writer, customScalarAdapters, value.getTracks());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: TracksIdsQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R!\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/TracksIdsQuery$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/music/platform/graphql/generated/TracksIdsQuery$Track;", "tracks", "Ljava/util/List;", "getTracks", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final List<Track> tracks;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.tracks, ((Data) other).tracks);
        }

        public int hashCode() {
            List<Track> list = this.tracks;
            if (list == null) {
                return 0;
            }
            return list.hashCode();
        }

        public String toString() {
            return "Data(tracks=" + this.tracks + ')';
        }

        public Data(List<Track> list) {
            this.tracks = list;
        }

        public final List<Track> getTracks() {
            return this.tracks;
        }
    }

    /* JADX INFO: compiled from: TracksIdsQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/TracksIdsQuery$Track;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/TracksIdsQuery$Album;", "album", "Lcom/amazon/music/platform/graphql/generated/TracksIdsQuery$Album;", "getAlbum", "()Lcom/amazon/music/platform/graphql/generated/TracksIdsQuery$Album;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/TracksIdsQuery$Album;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Track {
        private final Album album;
        private final String id;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Track)) {
                return false;
            }
            Track track = (Track) other;
            return Intrinsics.areEqual(this.id, track.id) && Intrinsics.areEqual(this.album, track.album);
        }

        public int hashCode() {
            String str = this.id;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Album album = this.album;
            return iHashCode + (album != null ? album.hashCode() : 0);
        }

        public String toString() {
            return "Track(id=" + this.id + ", album=" + this.album + ')';
        }

        public Track(String str, Album album) {
            this.id = str;
            this.album = album;
        }

        public final String getId() {
            return this.id;
        }

        public final Album getAlbum() {
            return this.album;
        }
    }

    /* JADX INFO: compiled from: TracksIdsQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/TracksIdsQuery$Album;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Album {
        private final String id;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Album) && Intrinsics.areEqual(this.id, ((Album) other).id);
        }

        public int hashCode() {
            String str = this.id;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "Album(id=" + this.id + ')';
        }

        public Album(String str) {
            this.id = str;
        }

        public final String getId() {
            return this.id;
        }
    }
}
