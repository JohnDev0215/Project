package com.amazon.music.platform.graphql.generated.fragment;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Album.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001:\u0006ABCDEFBÇ\u0001\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u0012\u0010\u0010\u001a\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0018\u00010\u0018\u0012\u0010\u0010\u001f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0018\u00010\u0018\u0012\b\u0010$\u001a\u0004\u0018\u00010#\u0012\b\u0010)\u001a\u0004\u0018\u00010(\u0012\b\u0010-\u001a\u0004\u0018\u00010\u0002\u0012\b\u00100\u001a\u0004\u0018\u00010/\u0012\u0010\u00105\u001a\f\u0012\u0006\u0012\u0004\u0018\u000104\u0018\u00010\u0018\u0012\b\u00107\u001a\u0004\u0018\u00010(\u0012\b\u00109\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010;\u001a\u0004\u0018\u00010\u0002\u0012\u0010\u0010=\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0018¢\u0006\u0004\b?\u0010@J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R!\u0010\u001a\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR*\u0010\u001f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0018\u00010\u00188\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u001f\u0010\u001b\u0012\u0004\b!\u0010\"\u001a\u0004\b \u0010\u001dR\u0019\u0010$\u001a\u0004\u0018\u00010#8\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u0019\u0010)\u001a\u0004\u0018\u00010(8\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u0019\u0010-\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b-\u0010\n\u001a\u0004\b.\u0010\fR\u0019\u00100\u001a\u0004\u0018\u00010/8\u0006¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R!\u00105\u001a\f\u0012\u0006\u0012\u0004\u0018\u000104\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b5\u0010\u001b\u001a\u0004\b6\u0010\u001dR\u0019\u00107\u001a\u0004\u0018\u00010(8\u0006¢\u0006\f\n\u0004\b7\u0010*\u001a\u0004\b8\u0010,R\u0019\u00109\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b9\u0010\n\u001a\u0004\b:\u0010\fR\u0019\u0010;\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b;\u0010\n\u001a\u0004\b<\u0010\fR!\u0010=\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b=\u0010\u001b\u001a\u0004\b>\u0010\u001d¨\u0006G"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Album;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "title", "getTitle", ImagesContract.URL, "getUrl", "releaseDate", "getReleaseDate", "Lcom/amazon/music/platform/graphql/generated/fragment/Album$Eligibility;", "eligibility", "Lcom/amazon/music/platform/graphql/generated/fragment/Album$Eligibility;", "getEligibility", "()Lcom/amazon/music/platform/graphql/generated/fragment/Album$Eligibility;", "", "Lcom/amazon/music/platform/graphql/generated/fragment/Album$Image;", "images", "Ljava/util/List;", "getImages", "()Ljava/util/List;", "Lcom/amazon/music/platform/graphql/generated/fragment/Album$Artist;", "artists", "getArtists", "getArtists$annotations", "()V", "Lcom/amazon/music/platform/graphql/generated/fragment/Album$ContributingArtists;", "contributingArtists", "Lcom/amazon/music/platform/graphql/generated/fragment/Album$ContributingArtists;", "getContributingArtists", "()Lcom/amazon/music/platform/graphql/generated/fragment/Album$ContributingArtists;", "", "duration", "Ljava/lang/Double;", "getDuration", "()Ljava/lang/Double;", ContextMappingConstants.LABEL, "getLabel", "Lcom/amazon/music/platform/graphql/generated/fragment/Album$ParentalSettings;", "parentalSettings", "Lcom/amazon/music/platform/graphql/generated/fragment/Album$ParentalSettings;", "getParentalSettings", "()Lcom/amazon/music/platform/graphql/generated/fragment/Album$ParentalSettings;", "Lcom/amazon/music/platform/graphql/generated/fragment/Album$AlbumTrack;", "albumTracks", "getAlbumTracks", "trackCount", "getTrackCount", "copyright", "getCopyright", "languageOfPerformance", "getLanguageOfPerformance", "audioQualities", "getAudioQualities", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Album$Eligibility;Ljava/util/List;Ljava/util/List;Lcom/amazon/music/platform/graphql/generated/fragment/Album$ContributingArtists;Ljava/lang/Double;Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Album$ParentalSettings;Ljava/util/List;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "AlbumTrack", "Artist", "ContributingArtists", "Eligibility", "Image", "ParentalSettings", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Album {
    private final List<AlbumTrack> albumTracks;
    private final List<Artist> artists;
    private final List<String> audioQualities;
    private final ContributingArtists contributingArtists;
    private final String copyright;
    private final Double duration;
    private final Eligibility eligibility;
    private final String id;
    private final List<Image> images;
    private final String label;
    private final String languageOfPerformance;
    private final ParentalSettings parentalSettings;
    private final String releaseDate;
    private final String title;
    private final Double trackCount;
    private final String url;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Album)) {
            return false;
        }
        Album album = (Album) other;
        return Intrinsics.areEqual(this.id, album.id) && Intrinsics.areEqual(this.title, album.title) && Intrinsics.areEqual(this.url, album.url) && Intrinsics.areEqual(this.releaseDate, album.releaseDate) && Intrinsics.areEqual(this.eligibility, album.eligibility) && Intrinsics.areEqual(this.images, album.images) && Intrinsics.areEqual(this.artists, album.artists) && Intrinsics.areEqual(this.contributingArtists, album.contributingArtists) && Intrinsics.areEqual((Object) this.duration, (Object) album.duration) && Intrinsics.areEqual(this.label, album.label) && Intrinsics.areEqual(this.parentalSettings, album.parentalSettings) && Intrinsics.areEqual(this.albumTracks, album.albumTracks) && Intrinsics.areEqual((Object) this.trackCount, (Object) album.trackCount) && Intrinsics.areEqual(this.copyright, album.copyright) && Intrinsics.areEqual(this.languageOfPerformance, album.languageOfPerformance) && Intrinsics.areEqual(this.audioQualities, album.audioQualities);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.title;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.url;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.releaseDate;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Eligibility eligibility = this.eligibility;
        int iHashCode5 = (iHashCode4 + (eligibility == null ? 0 : eligibility.hashCode())) * 31;
        List<Image> list = this.images;
        int iHashCode6 = (iHashCode5 + (list == null ? 0 : list.hashCode())) * 31;
        List<Artist> list2 = this.artists;
        int iHashCode7 = (iHashCode6 + (list2 == null ? 0 : list2.hashCode())) * 31;
        ContributingArtists contributingArtists = this.contributingArtists;
        int iHashCode8 = (iHashCode7 + (contributingArtists == null ? 0 : contributingArtists.hashCode())) * 31;
        Double d = this.duration;
        int iHashCode9 = (iHashCode8 + (d == null ? 0 : d.hashCode())) * 31;
        String str5 = this.label;
        int iHashCode10 = (iHashCode9 + (str5 == null ? 0 : str5.hashCode())) * 31;
        ParentalSettings parentalSettings = this.parentalSettings;
        int iHashCode11 = (iHashCode10 + (parentalSettings == null ? 0 : parentalSettings.hashCode())) * 31;
        List<AlbumTrack> list3 = this.albumTracks;
        int iHashCode12 = (iHashCode11 + (list3 == null ? 0 : list3.hashCode())) * 31;
        Double d2 = this.trackCount;
        int iHashCode13 = (iHashCode12 + (d2 == null ? 0 : d2.hashCode())) * 31;
        String str6 = this.copyright;
        int iHashCode14 = (iHashCode13 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.languageOfPerformance;
        int iHashCode15 = (iHashCode14 + (str7 == null ? 0 : str7.hashCode())) * 31;
        List<String> list4 = this.audioQualities;
        return iHashCode15 + (list4 != null ? list4.hashCode() : 0);
    }

    public String toString() {
        return "Album(id=" + this.id + ", title=" + this.title + ", url=" + this.url + ", releaseDate=" + this.releaseDate + ", eligibility=" + this.eligibility + ", images=" + this.images + ", artists=" + this.artists + ", contributingArtists=" + this.contributingArtists + ", duration=" + this.duration + ", label=" + this.label + ", parentalSettings=" + this.parentalSettings + ", albumTracks=" + this.albumTracks + ", trackCount=" + this.trackCount + ", copyright=" + this.copyright + ", languageOfPerformance=" + this.languageOfPerformance + ", audioQualities=" + this.audioQualities + ')';
    }

    public Album(String str, String str2, String str3, String str4, Eligibility eligibility, List<Image> list, List<Artist> list2, ContributingArtists contributingArtists, Double d, String str5, ParentalSettings parentalSettings, List<AlbumTrack> list3, Double d2, String str6, String str7, List<String> list4) {
        this.id = str;
        this.title = str2;
        this.url = str3;
        this.releaseDate = str4;
        this.eligibility = eligibility;
        this.images = list;
        this.artists = list2;
        this.contributingArtists = contributingArtists;
        this.duration = d;
        this.label = str5;
        this.parentalSettings = parentalSettings;
        this.albumTracks = list3;
        this.trackCount = d2;
        this.copyright = str6;
        this.languageOfPerformance = str7;
        this.audioQualities = list4;
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

    public final String getReleaseDate() {
        return this.releaseDate;
    }

    public final Eligibility getEligibility() {
        return this.eligibility;
    }

    public final List<Image> getImages() {
        return this.images;
    }

    public final List<Artist> getArtists() {
        return this.artists;
    }

    public final ContributingArtists getContributingArtists() {
        return this.contributingArtists;
    }

    public final Double getDuration() {
        return this.duration;
    }

    public final String getLabel() {
        return this.label;
    }

    public final ParentalSettings getParentalSettings() {
        return this.parentalSettings;
    }

    public final List<AlbumTrack> getAlbumTracks() {
        return this.albumTracks;
    }

    public final Double getTrackCount() {
        return this.trackCount;
    }

    public final String getCopyright() {
        return this.copyright;
    }

    public final String getLanguageOfPerformance() {
        return this.languageOfPerformance;
    }

    public final List<String> getAudioQualities() {
        return this.audioQualities;
    }

    /* JADX INFO: compiled from: Album.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Album$Eligibility;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "eligibility", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "getEligibility", "()Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: Album.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Album$Image;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", ContextMappingConstants.IMAGE, "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "getImage", "()Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Image;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: Album.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Album$Artist;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "artistEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "getArtistEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Artist {
        private final String __typename;
        private final ArtistEmbedded artistEmbedded;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Artist)) {
                return false;
            }
            Artist artist = (Artist) other;
            return Intrinsics.areEqual(this.__typename, artist.__typename) && Intrinsics.areEqual(this.artistEmbedded, artist.artistEmbedded);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.artistEmbedded.hashCode();
        }

        public String toString() {
            return "Artist(__typename=" + this.__typename + ", artistEmbedded=" + this.artistEmbedded + ')';
        }

        public Artist(String __typename, ArtistEmbedded artistEmbedded) {
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

    /* JADX INFO: compiled from: Album.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Album$ContributingArtists;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection;", "artistConnection", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection;", "getArtistConnection", "()Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class ContributingArtists {
        private final String __typename;
        private final ArtistConnection artistConnection;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ContributingArtists)) {
                return false;
            }
            ContributingArtists contributingArtists = (ContributingArtists) other;
            return Intrinsics.areEqual(this.__typename, contributingArtists.__typename) && Intrinsics.areEqual(this.artistConnection, contributingArtists.artistConnection);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.artistConnection.hashCode();
        }

        public String toString() {
            return "ContributingArtists(__typename=" + this.__typename + ", artistConnection=" + this.artistConnection + ')';
        }

        public ContributingArtists(String __typename, ArtistConnection artistConnection) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(artistConnection, "artistConnection");
            this.__typename = __typename;
            this.artistConnection = artistConnection;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final ArtistConnection getArtistConnection() {
            return this.artistConnection;
        }
    }

    /* JADX INFO: compiled from: Album.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Album$ParentalSettings;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/ParentalSettings;", "parentalSettings", "Lcom/amazon/music/platform/graphql/generated/fragment/ParentalSettings;", "getParentalSettings", "()Lcom/amazon/music/platform/graphql/generated/fragment/ParentalSettings;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/ParentalSettings;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class ParentalSettings {
        private final String __typename;
        private final com.amazon.music.platform.graphql.generated.fragment.ParentalSettings parentalSettings;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ParentalSettings)) {
                return false;
            }
            ParentalSettings parentalSettings = (ParentalSettings) other;
            return Intrinsics.areEqual(this.__typename, parentalSettings.__typename) && Intrinsics.areEqual(this.parentalSettings, parentalSettings.parentalSettings);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.parentalSettings.hashCode();
        }

        public String toString() {
            return "ParentalSettings(__typename=" + this.__typename + ", parentalSettings=" + this.parentalSettings + ')';
        }

        public ParentalSettings(String __typename, com.amazon.music.platform.graphql.generated.fragment.ParentalSettings parentalSettings) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(parentalSettings, "parentalSettings");
            this.__typename = __typename;
            this.parentalSettings = parentalSettings;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final com.amazon.music.platform.graphql.generated.fragment.ParentalSettings getParentalSettings() {
            return this.parentalSettings;
        }
    }

    /* JADX INFO: compiled from: Album.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Album$AlbumTrack;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded;", "trackEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded;", "getTrackEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class AlbumTrack {
        private final String __typename;
        private final TrackEmbedded trackEmbedded;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AlbumTrack)) {
                return false;
            }
            AlbumTrack albumTrack = (AlbumTrack) other;
            return Intrinsics.areEqual(this.__typename, albumTrack.__typename) && Intrinsics.areEqual(this.trackEmbedded, albumTrack.trackEmbedded);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.trackEmbedded.hashCode();
        }

        public String toString() {
            return "AlbumTrack(__typename=" + this.__typename + ", trackEmbedded=" + this.trackEmbedded + ')';
        }

        public AlbumTrack(String __typename, TrackEmbedded trackEmbedded) {
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
