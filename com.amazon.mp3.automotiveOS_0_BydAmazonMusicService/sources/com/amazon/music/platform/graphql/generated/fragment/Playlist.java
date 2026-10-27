package com.amazon.music.platform.graphql.generated.fragment;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Playlist.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\b\u0018\u00002\u00020\u0001:\u0006/01234Bi\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\u0010\u0010\u001d\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010\u001b\u0012\b\u0010\"\u001a\u0004\u0018\u00010!\u0012\b\u0010'\u001a\u0004\u0018\u00010&\u0012\b\u0010+\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b-\u0010.J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR!\u0010\u001d\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010\u001b8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0019\u0010\"\u001a\u0004\u0018\u00010!8\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u0019\u0010'\u001a\u0004\u0018\u00010&8\u0006¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\u0019\u0010+\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b+\u0010\u0013\u001a\u0004\b,\u0010\u0015¨\u00065"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Playlist;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "title", "getTitle", ImagesContract.URL, "getUrl", "", "trackCount", "Ljava/lang/Double;", "getTrackCount", "()Ljava/lang/Double;", "Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Eligibility;", "eligibility", "Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Eligibility;", "getEligibility", "()Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Eligibility;", "", "Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Image;", "images", "Ljava/util/List;", "getImages", "()Ljava/util/List;", "Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Tracks;", "tracks", "Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Tracks;", "getTracks", "()Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Tracks;", "Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Curator;", "curator", "Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Curator;", "getCurator", "()Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Curator;", "duration", "getDuration", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Eligibility;Ljava/util/List;Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Tracks;Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Curator;Ljava/lang/Double;)V", "Curator", "Edge", "Eligibility", "Image", "Node", "Tracks", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Playlist {
    private final Curator curator;
    private final Double duration;
    private final Eligibility eligibility;
    private final String id;
    private final List<Image> images;
    private final String title;
    private final Double trackCount;
    private final Tracks tracks;
    private final String url;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Playlist)) {
            return false;
        }
        Playlist playlist = (Playlist) other;
        return Intrinsics.areEqual(this.id, playlist.id) && Intrinsics.areEqual(this.title, playlist.title) && Intrinsics.areEqual(this.url, playlist.url) && Intrinsics.areEqual((Object) this.trackCount, (Object) playlist.trackCount) && Intrinsics.areEqual(this.eligibility, playlist.eligibility) && Intrinsics.areEqual(this.images, playlist.images) && Intrinsics.areEqual(this.tracks, playlist.tracks) && Intrinsics.areEqual(this.curator, playlist.curator) && Intrinsics.areEqual((Object) this.duration, (Object) playlist.duration);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.title;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.url;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Double d = this.trackCount;
        int iHashCode4 = (iHashCode3 + (d == null ? 0 : d.hashCode())) * 31;
        Eligibility eligibility = this.eligibility;
        int iHashCode5 = (iHashCode4 + (eligibility == null ? 0 : eligibility.hashCode())) * 31;
        List<Image> list = this.images;
        int iHashCode6 = (iHashCode5 + (list == null ? 0 : list.hashCode())) * 31;
        Tracks tracks = this.tracks;
        int iHashCode7 = (iHashCode6 + (tracks == null ? 0 : tracks.hashCode())) * 31;
        Curator curator = this.curator;
        int iHashCode8 = (iHashCode7 + (curator == null ? 0 : curator.hashCode())) * 31;
        Double d2 = this.duration;
        return iHashCode8 + (d2 != null ? d2.hashCode() : 0);
    }

    public String toString() {
        return "Playlist(id=" + this.id + ", title=" + this.title + ", url=" + this.url + ", trackCount=" + this.trackCount + ", eligibility=" + this.eligibility + ", images=" + this.images + ", tracks=" + this.tracks + ", curator=" + this.curator + ", duration=" + this.duration + ')';
    }

    public Playlist(String str, String str2, String str3, Double d, Eligibility eligibility, List<Image> list, Tracks tracks, Curator curator, Double d2) {
        this.id = str;
        this.title = str2;
        this.url = str3;
        this.trackCount = d;
        this.eligibility = eligibility;
        this.images = list;
        this.tracks = tracks;
        this.curator = curator;
        this.duration = d2;
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getUrl() {
        return this.url;
    }

    public final Double getTrackCount() {
        return this.trackCount;
    }

    public final Eligibility getEligibility() {
        return this.eligibility;
    }

    public final List<Image> getImages() {
        return this.images;
    }

    public final Tracks getTracks() {
        return this.tracks;
    }

    public final Curator getCurator() {
        return this.curator;
    }

    public final Double getDuration() {
        return this.duration;
    }

    /* JADX INFO: compiled from: Playlist.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Eligibility;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "eligibility", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "getEligibility", "()Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Eligibility {
        private final String __typename;
        private final com.amazon.music.platform.graphql.generated.fragment.Eligibility eligibility;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Eligibility)) {
                return false;
            }
            Eligibility eligibility = (Eligibility) other;
            return Intrinsics.areEqual(this.__typename, eligibility.__typename) && Intrinsics.areEqual(this.eligibility, eligibility.eligibility);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.eligibility.hashCode();
        }

        public String toString() {
            return "Eligibility(__typename=" + this.__typename + ", eligibility=" + this.eligibility + ')';
        }

        public Eligibility(String __typename, com.amazon.music.platform.graphql.generated.fragment.Eligibility eligibility) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(eligibility, "eligibility");
            this.__typename = __typename;
            this.eligibility = eligibility;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final com.amazon.music.platform.graphql.generated.fragment.Eligibility getEligibility() {
            return this.eligibility;
        }
    }

    /* JADX INFO: compiled from: Playlist.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Image;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", ContextMappingConstants.IMAGE, "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "getImage", "()Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Image;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: Playlist.kt */
    @Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R!\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Tracks;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Edge;", "edges", "Ljava/util/List;", "getEdges", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Tracks {
        private final List<Edge> edges;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Tracks) && Intrinsics.areEqual(this.edges, ((Tracks) other).edges);
        }

        public int hashCode() {
            List<Edge> list = this.edges;
            if (list == null) {
                return 0;
            }
            return list.hashCode();
        }

        public String toString() {
            return "Tracks(edges=" + this.edges + ')';
        }

        public Tracks(List<Edge> list) {
            this.edges = list;
        }

        public final List<Edge> getEdges() {
            return this.edges;
        }
    }

    /* JADX INFO: compiled from: Playlist.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Curator;", "", "", "toString", "", "hashCode", "other", "", "equals", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Curator {
        private final String name;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Curator) && Intrinsics.areEqual(this.name, ((Curator) other).name);
        }

        public int hashCode() {
            String str = this.name;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        public String toString() {
            return "Curator(name=" + this.name + ')';
        }

        public Curator(String str) {
            this.name = str;
        }

        public final String getName() {
            return this.name;
        }
    }

    /* JADX INFO: compiled from: Playlist.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Edge;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Node;", "node", "Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Node;", "getNode", "()Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Node;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Node;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Edge {
        private final Node node;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Edge) && Intrinsics.areEqual(this.node, ((Edge) other).node);
        }

        public int hashCode() {
            Node node = this.node;
            if (node == null) {
                return 0;
            }
            return node.hashCode();
        }

        public String toString() {
            return "Edge(node=" + this.node + ')';
        }

        public Edge(Node node) {
            this.node = node;
        }

        public final Node getNode() {
            return this.node;
        }
    }

    /* JADX INFO: compiled from: Playlist.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Playlist$Node;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded;", "trackEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded;", "getTrackEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Node {
        private final String __typename;
        private final TrackEmbedded trackEmbedded;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Node)) {
                return false;
            }
            Node node = (Node) other;
            return Intrinsics.areEqual(this.__typename, node.__typename) && Intrinsics.areEqual(this.trackEmbedded, node.trackEmbedded);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.trackEmbedded.hashCode();
        }

        public String toString() {
            return "Node(__typename=" + this.__typename + ", trackEmbedded=" + this.trackEmbedded + ')';
        }

        public Node(String __typename, TrackEmbedded trackEmbedded) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(trackEmbedded, "trackEmbedded");
            this.__typename = __typename;
            this.trackEmbedded = trackEmbedded;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final TrackEmbedded getTrackEmbedded() {
            return this.trackEmbedded;
        }
    }
}
