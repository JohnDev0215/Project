package com.amazon.music.platform.graphql.generated.fragment;

import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtistConnection.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001:\u0002\u0011\u0012B\u0019\u0012\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R!\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection$Edge;", "edges", "Ljava/util/List;", "getEdges", "()Ljava/util/List;", "<init>", "(Ljava/util/List;)V", "Edge", "Node", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ArtistConnection {
    private final List<Edge> edges;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ArtistConnection) && Intrinsics.areEqual(this.edges, ((ArtistConnection) other).edges);
    }

    public int hashCode() {
        List<Edge> list = this.edges;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public String toString() {
        return "ArtistConnection(edges=" + this.edges + ')';
    }

    public ArtistConnection(List<Edge> list) {
        this.edges = list;
    }

    public final List<Edge> getEdges() {
        return this.edges;
    }

    /* JADX INFO: compiled from: ArtistConnection.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection$Edge;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection$Node;", "node", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection$Node;", "getNode", "()Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection$Node;", "role", "Ljava/lang/String;", "getRole", "()Ljava/lang/String;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection$Node;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Edge {
        private final Node node;
        private final String role;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Edge)) {
                return false;
            }
            Edge edge = (Edge) other;
            return Intrinsics.areEqual(this.node, edge.node) && Intrinsics.areEqual(this.role, edge.role);
        }

        public int hashCode() {
            Node node = this.node;
            int iHashCode = (node == null ? 0 : node.hashCode()) * 31;
            String str = this.role;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Edge(node=" + this.node + ", role=" + this.role + ')';
        }

        public Edge(Node node, String str) {
            this.node = node;
            this.role = str;
        }

        public final Node getNode() {
            return this.node;
        }

        public final String getRole() {
            return this.role;
        }
    }

    /* JADX INFO: compiled from: ArtistConnection.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection$Node;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "artistEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "getArtistEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Node {
        private final String __typename;
        private final ArtistEmbedded artistEmbedded;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Node)) {
                return false;
            }
            Node node = (Node) other;
            return Intrinsics.areEqual(this.__typename, node.__typename) && Intrinsics.areEqual(this.artistEmbedded, node.artistEmbedded);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.artistEmbedded.hashCode();
        }

        public String toString() {
            return "Node(__typename=" + this.__typename + ", artistEmbedded=" + this.artistEmbedded + ')';
        }

        public Node(String __typename, ArtistEmbedded artistEmbedded) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(artistEmbedded, "artistEmbedded");
            this.__typename = __typename;
            this.artistEmbedded = artistEmbedded;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final ArtistEmbedded getArtistEmbedded() {
            return this.artistEmbedded;
        }
    }
}
