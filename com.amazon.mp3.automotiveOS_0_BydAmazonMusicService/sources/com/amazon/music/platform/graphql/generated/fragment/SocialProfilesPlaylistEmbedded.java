package com.amazon.music.platform.graphql.generated.fragment;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SocialProfilesPlaylistEmbedded.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001:\u0002'(B_\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\u0010\u0010\u001d\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010\u001b\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b%\u0010&J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR!\u0010\u001d\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010\u001b8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0019\u0010!\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b!\u0010\u0013\u001a\u0004\b\"\u0010\u0015R\u0019\u0010#\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b#\u0010\n\u001a\u0004\b$\u0010\f¨\u0006)"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesPlaylistEmbedded;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "title", "getTitle", ImagesContract.URL, "getUrl", "", "trackCount", "Ljava/lang/Double;", "getTrackCount", "()Ljava/lang/Double;", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesPlaylistEmbedded$Eligibility;", "eligibility", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesPlaylistEmbedded$Eligibility;", "getEligibility", "()Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesPlaylistEmbedded$Eligibility;", "", "Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesPlaylistEmbedded$Image;", "images", "Ljava/util/List;", "getImages", "()Ljava/util/List;", "followerCount", "getFollowerCount", "visibility", "getVisibility", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesPlaylistEmbedded$Eligibility;Ljava/util/List;Ljava/lang/Double;Ljava/lang/String;)V", "Eligibility", "Image", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class SocialProfilesPlaylistEmbedded {
    private final Eligibility eligibility;
    private final Double followerCount;
    private final String id;
    private final List<Image> images;
    private final String title;
    private final Double trackCount;
    private final String url;
    private final String visibility;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SocialProfilesPlaylistEmbedded)) {
            return false;
        }
        SocialProfilesPlaylistEmbedded socialProfilesPlaylistEmbedded = (SocialProfilesPlaylistEmbedded) other;
        return Intrinsics.areEqual(this.id, socialProfilesPlaylistEmbedded.id) && Intrinsics.areEqual(this.title, socialProfilesPlaylistEmbedded.title) && Intrinsics.areEqual(this.url, socialProfilesPlaylistEmbedded.url) && Intrinsics.areEqual((Object) this.trackCount, (Object) socialProfilesPlaylistEmbedded.trackCount) && Intrinsics.areEqual(this.eligibility, socialProfilesPlaylistEmbedded.eligibility) && Intrinsics.areEqual(this.images, socialProfilesPlaylistEmbedded.images) && Intrinsics.areEqual((Object) this.followerCount, (Object) socialProfilesPlaylistEmbedded.followerCount) && Intrinsics.areEqual(this.visibility, socialProfilesPlaylistEmbedded.visibility);
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
        Double d2 = this.followerCount;
        int iHashCode7 = (iHashCode6 + (d2 == null ? 0 : d2.hashCode())) * 31;
        String str4 = this.visibility;
        return iHashCode7 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "SocialProfilesPlaylistEmbedded(id=" + this.id + ", title=" + this.title + ", url=" + this.url + ", trackCount=" + this.trackCount + ", eligibility=" + this.eligibility + ", images=" + this.images + ", followerCount=" + this.followerCount + ", visibility=" + this.visibility + ')';
    }

    public SocialProfilesPlaylistEmbedded(String str, String str2, String str3, Double d, Eligibility eligibility, List<Image> list, Double d2, String str4) {
        this.id = str;
        this.title = str2;
        this.url = str3;
        this.trackCount = d;
        this.eligibility = eligibility;
        this.images = list;
        this.followerCount = d2;
        this.visibility = str4;
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

    public final Double getFollowerCount() {
        return this.followerCount;
    }

    public final String getVisibility() {
        return this.visibility;
    }

    /* JADX INFO: compiled from: SocialProfilesPlaylistEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesPlaylistEmbedded$Eligibility;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "eligibility", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "getEligibility", "()Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: SocialProfilesPlaylistEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/SocialProfilesPlaylistEmbedded$Image;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", ContextMappingConstants.IMAGE, "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "getImage", "()Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Image;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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
