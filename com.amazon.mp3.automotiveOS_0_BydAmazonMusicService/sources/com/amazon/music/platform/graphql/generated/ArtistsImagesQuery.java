package com.amazon.music.platform.graphql.generated;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.platform.graphql.generated.ArtistsImagesQuery;
import com.amazon.music.platform.graphql.generated.adapter.ArtistsImagesQuery_VariablesAdapter;
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

/* JADX INFO: compiled from: ArtistsImagesQuery.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u000b\b\u0086\b\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0004\u001f\u001e !B!\u0012\u0018\b\u0002\u0010\u0018\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00170\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R'\u0010\u0018\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00170\u00168\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\""}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ArtistsImagesQuery;", "Lcom/apollographql/apollo3/api/Query;", "Lcom/amazon/music/platform/graphql/generated/ArtistsImagesQuery$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/apollographql/apollo3/api/Optional;", "", "ids", "Lcom/apollographql/apollo3/api/Optional;", "getIds", "()Lcom/apollographql/apollo3/api/Optional;", "<init>", "(Lcom/apollographql/apollo3/api/Optional;)V", "Companion", "Artist", "Data", "Image", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ArtistsImagesQuery implements Query<Data> {
    private final Optional<List<String>> ids;

    public ArtistsImagesQuery() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "query ArtistsImages($ids: [String]) { artists(ids: $ids) { id images { __typename ...image } } }  fragment image on Image { width height aspectRatio url externalUrl imageType description }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ArtistsImagesQuery) && Intrinsics.areEqual(this.ids, ((ArtistsImagesQuery) other).ids);
    }

    public int hashCode() {
        return this.ids.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "e5784d79aa922aee494462b2bd494c5fe7303117f275d2e2ffe3deeffe53a638";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "ArtistsImages";
    }

    public String toString() {
        return "ArtistsImagesQuery(ids=" + this.ids + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArtistsImagesQuery(Optional<? extends List<String>> ids) {
        Intrinsics.checkNotNullParameter(ids, "ids");
        this.ids = ids;
    }

    public /* synthetic */ ArtistsImagesQuery(Optional optional, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Optional.Absent.INSTANCE : optional);
    }

    public final Optional<List<String>> getIds() {
        return this.ids;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        ArtistsImagesQuery_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.ArtistsImagesQuery_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("artists");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public ArtistsImagesQuery.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                List list = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    list = (List) Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2611obj$default(ArtistsImagesQuery_ResponseAdapter$Artist.INSTANCE, false, 1, null)))).fromJson(reader, customScalarAdapters);
                }
                return new ArtistsImagesQuery.Data(list);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, ArtistsImagesQuery.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("artists");
                Adapters.m2609nullable(Adapters.m2608list(Adapters.m2609nullable(Adapters.m2611obj$default(ArtistsImagesQuery_ResponseAdapter$Artist.INSTANCE, false, 1, null)))).toJson(writer, customScalarAdapters, value.getArtists());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: ArtistsImagesQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R!\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ArtistsImagesQuery$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/music/platform/graphql/generated/ArtistsImagesQuery$Artist;", "artists", "Ljava/util/List;", "getArtists", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final List<Artist> artists;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.artists, ((Data) other).artists);
        }

        public int hashCode() {
            List<Artist> list = this.artists;
            if (list == null) {
                return 0;
            }
            return list.hashCode();
        }

        public String toString() {
            return "Data(artists=" + this.artists + ')';
        }

        public Data(List<Artist> list) {
            this.artists = list;
        }

        public final List<Artist> getArtists() {
            return this.artists;
        }
    }

    /* JADX INFO: compiled from: ArtistsImagesQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\u0010\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\r¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR!\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ArtistsImagesQuery$Artist;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "", "Lcom/amazon/music/platform/graphql/generated/ArtistsImagesQuery$Image;", "images", "Ljava/util/List;", "getImages", "()Ljava/util/List;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Artist {
        private final String id;
        private final List<Image> images;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Artist)) {
                return false;
            }
            Artist artist = (Artist) other;
            return Intrinsics.areEqual(this.id, artist.id) && Intrinsics.areEqual(this.images, artist.images);
        }

        public int hashCode() {
            String str = this.id;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            List<Image> list = this.images;
            return iHashCode + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "Artist(id=" + this.id + ", images=" + this.images + ')';
        }

        public Artist(String str, List<Image> list) {
            this.id = str;
            this.images = list;
        }

        public final String getId() {
            return this.id;
        }

        public final List<Image> getImages() {
            return this.images;
        }
    }

    /* JADX INFO: compiled from: ArtistsImagesQuery.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ArtistsImagesQuery$Image;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", ContextMappingConstants.IMAGE, "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "getImage", "()Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Image;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Image {
        private final String __typename;
        private final com.amazon.music.platform.graphql.generated.fragment.Image image;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Image)) {
                return false;
            }
            Image image = (Image) other;
            return Intrinsics.areEqual(this.__typename, image.__typename) && Intrinsics.areEqual(this.image, image.image);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.image.hashCode();
        }

        public String toString() {
            return "Image(__typename=" + this.__typename + ", image=" + this.image + ')';
        }

        public Image(String __typename, com.amazon.music.platform.graphql.generated.fragment.Image image) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(image, "image");
            this.__typename = __typename;
            this.image = image;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final com.amazon.music.platform.graphql.generated.fragment.Image getImage() {
            return this.image;
        }
    }
}
