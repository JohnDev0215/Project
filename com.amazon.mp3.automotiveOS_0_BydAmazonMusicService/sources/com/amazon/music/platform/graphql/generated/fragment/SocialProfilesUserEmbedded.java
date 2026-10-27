package com.amazon.music.platform.graphql.generated.fragment;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SocialProfilesUserEmbedded.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\b\u0018\u00002\u00020\u0001:\b3456789:B}\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\u0010\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0018\u00010\u0013\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\b\u0010#\u001a\u0004\u0018\u00010\"\u0012\b\u0010(\u001a\u0004\u0018\u00010'\u0012\b\u0010-\u001a\u0004\u0018\u00010,¢\u0006\u0004\b1\u00102J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR!\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u001a\u0010\fR\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\n\u001a\u0004\b\u001c\u0010\fR\u0019\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0019\u0010#\u001a\u0004\u0018\u00010\"8\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0019\u0010(\u001a\u0004\u0018\u00010'8\u0006¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+R\u0019\u0010-\u001a\u0004\u0018\u00010,8\u0006¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100¨\u0006;"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", ImagesContract.URL, "getUrl", "name", "getName", "handle", "getHandle", "", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Image;", "images", "Ljava/util/List;", "getImages", "()Ljava/util/List;", "visibility", "getVisibility", "playbackVisibility", "getPlaybackVisibility", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Playlists;", "playlists", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Playlists;", "getPlaylists", "()Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Playlists;", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Followers;", "followers", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Followers;", "getFollowers", "()Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Followers;", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$FollowedUsers;", "followedUsers", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$FollowedUsers;", "getFollowedUsers", "()Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$FollowedUsers;", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$FollowedArtists;", "followedArtists", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$FollowedArtists;", "getFollowedArtists", "()Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$FollowedArtists;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Playlists;Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Followers;Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$FollowedUsers;Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$FollowedArtists;)V", "Edge", "FollowedArtists", "FollowedUsers", "Followers", "Image", "Node", "PageInfo", "Playlists", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class SocialProfilesUserEmbedded {
    private final FollowedArtists followedArtists;
    private final FollowedUsers followedUsers;
    private final Followers followers;
    private final String handle;
    private final String id;
    private final List<Image> images;
    private final String name;
    private final String playbackVisibility;
    private final Playlists playlists;
    private final String url;
    private final String visibility;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SocialProfilesUserEmbedded)) {
            return false;
        }
        SocialProfilesUserEmbedded socialProfilesUserEmbedded = (SocialProfilesUserEmbedded) other;
        return Intrinsics.areEqual(this.id, socialProfilesUserEmbedded.id) && Intrinsics.areEqual(this.url, socialProfilesUserEmbedded.url) && Intrinsics.areEqual(this.name, socialProfilesUserEmbedded.name) && Intrinsics.areEqual(this.handle, socialProfilesUserEmbedded.handle) && Intrinsics.areEqual(this.images, socialProfilesUserEmbedded.images) && Intrinsics.areEqual(this.visibility, socialProfilesUserEmbedded.visibility) && Intrinsics.areEqual(this.playbackVisibility, socialProfilesUserEmbedded.playbackVisibility) && Intrinsics.areEqual(this.playlists, socialProfilesUserEmbedded.playlists) && Intrinsics.areEqual(this.followers, socialProfilesUserEmbedded.followers) && Intrinsics.areEqual(this.followedUsers, socialProfilesUserEmbedded.followedUsers) && Intrinsics.areEqual(this.followedArtists, socialProfilesUserEmbedded.followedArtists);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.url;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.name;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.handle;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        List<Image> list = this.images;
        int iHashCode5 = (iHashCode4 + (list == null ? 0 : list.hashCode())) * 31;
        String str5 = this.visibility;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.playbackVisibility;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Playlists playlists = this.playlists;
        int iHashCode8 = (iHashCode7 + (playlists == null ? 0 : playlists.hashCode())) * 31;
        Followers followers = this.followers;
        int iHashCode9 = (iHashCode8 + (followers == null ? 0 : followers.hashCode())) * 31;
        FollowedUsers followedUsers = this.followedUsers;
        int iHashCode10 = (iHashCode9 + (followedUsers == null ? 0 : followedUsers.hashCode())) * 31;
        FollowedArtists followedArtists = this.followedArtists;
        return iHashCode10 + (followedArtists != null ? followedArtists.hashCode() : 0);
    }

    public String toString() {
        return "SocialProfilesUserEmbedded(id=" + this.id + ", url=" + this.url + ", name=" + this.name + ", handle=" + this.handle + ", images=" + this.images + ", visibility=" + this.visibility + ", playbackVisibility=" + this.playbackVisibility + ", playlists=" + this.playlists + ", followers=" + this.followers + ", followedUsers=" + this.followedUsers + ", followedArtists=" + this.followedArtists + ')';
    }

    public SocialProfilesUserEmbedded(String str, String str2, String str3, String str4, List<Image> list, String str5, String str6, Playlists playlists, Followers followers, FollowedUsers followedUsers, FollowedArtists followedArtists) {
        this.id = str;
        this.url = str2;
        this.name = str3;
        this.handle = str4;
        this.images = list;
        this.visibility = str5;
        this.playbackVisibility = str6;
        this.playlists = playlists;
        this.followers = followers;
        this.followedUsers = followedUsers;
        this.followedArtists = followedArtists;
    }

    public final String getId() {
        return this.id;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getName() {
        return this.name;
    }

    public final String getHandle() {
        return this.handle;
    }

    public final List<Image> getImages() {
        return this.images;
    }

    public final String getVisibility() {
        return this.visibility;
    }

    public final String getPlaybackVisibility() {
        return this.playbackVisibility;
    }

    public final Playlists getPlaylists() {
        return this.playlists;
    }

    public final Followers getFollowers() {
        return this.followers;
    }

    public final FollowedUsers getFollowedUsers() {
        return this.followedUsers;
    }

    public final FollowedArtists getFollowedArtists() {
        return this.followedArtists;
    }

    /* JADX INFO: compiled from: SocialProfilesUserEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Image;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", ContextMappingConstants.IMAGE, "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "getImage", "()Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Image;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: SocialProfilesUserEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0010\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R!\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Playlists;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Edge;", "edges", "Ljava/util/List;", "getEdges", "()Ljava/util/List;", "", "totalCount", "Ljava/lang/Double;", "getTotalCount", "()Ljava/lang/Double;", "edgeCount", "getEdgeCount", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$PageInfo;", "pageInfo", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$PageInfo;", "getPageInfo", "()Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$PageInfo;", "<init>", "(Ljava/util/List;Ljava/lang/Double;Ljava/lang/Double;Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$PageInfo;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Playlists {
        private final Double edgeCount;
        private final List<Edge> edges;
        private final PageInfo pageInfo;
        private final Double totalCount;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Playlists)) {
                return false;
            }
            Playlists playlists = (Playlists) other;
            return Intrinsics.areEqual(this.edges, playlists.edges) && Intrinsics.areEqual((Object) this.totalCount, (Object) playlists.totalCount) && Intrinsics.areEqual((Object) this.edgeCount, (Object) playlists.edgeCount) && Intrinsics.areEqual(this.pageInfo, playlists.pageInfo);
        }

        public int hashCode() {
            List<Edge> list = this.edges;
            int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
            Double d = this.totalCount;
            int iHashCode2 = (iHashCode + (d == null ? 0 : d.hashCode())) * 31;
            Double d2 = this.edgeCount;
            int iHashCode3 = (iHashCode2 + (d2 == null ? 0 : d2.hashCode())) * 31;
            PageInfo pageInfo = this.pageInfo;
            return iHashCode3 + (pageInfo != null ? pageInfo.hashCode() : 0);
        }

        public String toString() {
            return "Playlists(edges=" + this.edges + ", totalCount=" + this.totalCount + ", edgeCount=" + this.edgeCount + ", pageInfo=" + this.pageInfo + ')';
        }

        public Playlists(List<Edge> list, Double d, Double d2, PageInfo pageInfo) {
            this.edges = list;
            this.totalCount = d;
            this.edgeCount = d2;
            this.pageInfo = pageInfo;
        }

        public final List<Edge> getEdges() {
            return this.edges;
        }

        public final Double getTotalCount() {
            return this.totalCount;
        }

        public final Double getEdgeCount() {
            return this.edgeCount;
        }

        public final PageInfo getPageInfo() {
            return this.pageInfo;
        }
    }

    /* JADX INFO: compiled from: SocialProfilesUserEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Followers;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "totalCount", "Ljava/lang/Double;", "getTotalCount", "()Ljava/lang/Double;", "<init>", "(Ljava/lang/Double;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Followers {
        private final Double totalCount;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Followers) && Intrinsics.areEqual((Object) this.totalCount, (Object) ((Followers) other).totalCount);
        }

        public int hashCode() {
            Double d = this.totalCount;
            if (d == null) {
                return 0;
            }
            return d.hashCode();
        }

        public String toString() {
            return "Followers(totalCount=" + this.totalCount + ')';
        }

        public Followers(Double d) {
            this.totalCount = d;
        }

        public final Double getTotalCount() {
            return this.totalCount;
        }
    }

    /* JADX INFO: compiled from: SocialProfilesUserEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$FollowedUsers;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "totalCount", "Ljava/lang/Double;", "getTotalCount", "()Ljava/lang/Double;", "<init>", "(Ljava/lang/Double;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class FollowedUsers {
        private final Double totalCount;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FollowedUsers) && Intrinsics.areEqual((Object) this.totalCount, (Object) ((FollowedUsers) other).totalCount);
        }

        public int hashCode() {
            Double d = this.totalCount;
            if (d == null) {
                return 0;
            }
            return d.hashCode();
        }

        public String toString() {
            return "FollowedUsers(totalCount=" + this.totalCount + ')';
        }

        public FollowedUsers(Double d) {
            this.totalCount = d;
        }

        public final Double getTotalCount() {
            return this.totalCount;
        }
    }

    /* JADX INFO: compiled from: SocialProfilesUserEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$FollowedArtists;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "totalCount", "Ljava/lang/Double;", "getTotalCount", "()Ljava/lang/Double;", "<init>", "(Ljava/lang/Double;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class FollowedArtists {
        private final Double totalCount;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FollowedArtists) && Intrinsics.areEqual((Object) this.totalCount, (Object) ((FollowedArtists) other).totalCount);
        }

        public int hashCode() {
            Double d = this.totalCount;
            if (d == null) {
                return 0;
            }
            return d.hashCode();
        }

        public String toString() {
            return "FollowedArtists(totalCount=" + this.totalCount + ')';
        }

        public FollowedArtists(Double d) {
            this.totalCount = d;
        }

        public final Double getTotalCount() {
            return this.totalCount;
        }
    }

    /* JADX INFO: compiled from: SocialProfilesUserEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Edge;", "", "", "toString", "", "hashCode", "other", "", "equals", "cursor", "Ljava/lang/String;", "getCursor", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Node;", "node", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Node;", "getNode", "()Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Node;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Node;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Edge {
        private final String cursor;
        private final Node node;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Edge)) {
                return false;
            }
            Edge edge = (Edge) other;
            return Intrinsics.areEqual(this.cursor, edge.cursor) && Intrinsics.areEqual(this.node, edge.node);
        }

        public int hashCode() {
            String str = this.cursor;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Node node = this.node;
            return iHashCode + (node != null ? node.hashCode() : 0);
        }

        public String toString() {
            return "Edge(cursor=" + this.cursor + ", node=" + this.node + ')';
        }

        public Edge(String str, Node node) {
            this.cursor = str;
            this.node = node;
        }

        public final String getCursor() {
            return this.cursor;
        }

        public final Node getNode() {
            return this.node;
        }
    }

    /* JADX INFO: compiled from: SocialProfilesUserEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$Node;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesPlaylistEmbedded;", "socialProfilesPlaylistEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesPlaylistEmbedded;", "getSocialProfilesPlaylistEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesPlaylistEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesPlaylistEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Node {
        private final String __typename;
        private final SocialProfilesPlaylistEmbedded socialProfilesPlaylistEmbedded;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Node)) {
                return false;
            }
            Node node = (Node) other;
            return Intrinsics.areEqual(this.__typename, node.__typename) && Intrinsics.areEqual(this.socialProfilesPlaylistEmbedded, node.socialProfilesPlaylistEmbedded);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.socialProfilesPlaylistEmbedded.hashCode();
        }

        public String toString() {
            return "Node(__typename=" + this.__typename + ", socialProfilesPlaylistEmbedded=" + this.socialProfilesPlaylistEmbedded + ')';
        }

        public Node(String __typename, SocialProfilesPlaylistEmbedded socialProfilesPlaylistEmbedded) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(socialProfilesPlaylistEmbedded, "socialProfilesPlaylistEmbedded");
            this.__typename = __typename;
            this.socialProfilesPlaylistEmbedded = socialProfilesPlaylistEmbedded;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final SocialProfilesPlaylistEmbedded getSocialProfilesPlaylistEmbedded() {
            return this.socialProfilesPlaylistEmbedded;
        }
    }

    /* JADX INFO: compiled from: SocialProfilesUserEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesUserEmbedded$PageInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "hasNextPage", "Z", "getHasNextPage", "()Z", "token", "Ljava/lang/String;", "getToken", "()Ljava/lang/String;", "<init>", "(ZLjava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class PageInfo {
        private final boolean hasNextPage;
        private final String token;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PageInfo)) {
                return false;
            }
            PageInfo pageInfo = (PageInfo) other;
            return this.hasNextPage == pageInfo.hasNextPage && Intrinsics.areEqual(this.token, pageInfo.token);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.hasNextPage;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            String str = this.token;
            return i + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "PageInfo(hasNextPage=" + this.hasNextPage + ", token=" + this.token + ')';
        }

        public PageInfo(boolean z, String str) {
            this.hasNextPage = z;
            this.token = str;
        }

        public final boolean getHasNextPage() {
            return this.hasNextPage;
        }

        public final String getToken() {
            return this.token;
        }
    }
}
