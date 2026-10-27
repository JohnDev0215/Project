package com.amazon.music.platform.graphql.generated.fragment;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Track.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001:\u0006ABCDEFBµ\u0001\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\u0010\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u0011\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\b\u0010$\u001a\u0004\u0018\u00010#\u0012\b\u0010)\u001a\u0004\u0018\u00010(\u0012\b\u0010.\u001a\u0004\u0018\u00010-\u0012\b\u00102\u001a\u0004\u0018\u00010\u0002\u0012\u0010\u00105\u001a\f\u0012\u0006\u0012\u0004\u0018\u000104\u0018\u00010\u0011\u0012\b\u00107\u001a\u0004\u0018\u00010\u0002\u0012\b\u00109\u001a\u0004\u0018\u00010#\u0012\b\u0010;\u001a\u0004\u0018\u00010\u0002\u0012\u0010\u0010=\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0011¢\u0006\u0004\b?\u0010@J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR*\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u0012\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0006¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0019\u0010$\u001a\u0004\u0018\u00010#8\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R\u0019\u0010)\u001a\u0004\u0018\u00010(8\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u0019\u0010.\u001a\u0004\u0018\u00010-8\u0006¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101R\u0019\u00102\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b2\u0010\n\u001a\u0004\b3\u0010\fR!\u00105\u001a\f\u0012\u0006\u0012\u0004\u0018\u000104\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b5\u0010\u0014\u001a\u0004\b6\u0010\u0016R\u0019\u00107\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b7\u0010\n\u001a\u0004\b8\u0010\fR\u0019\u00109\u001a\u0004\u0018\u00010#8\u0006¢\u0006\f\n\u0004\b9\u0010%\u001a\u0004\b:\u0010'R\u0019\u0010;\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b;\u0010\n\u001a\u0004\b<\u0010\fR!\u0010=\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b=\u0010\u0014\u001a\u0004\b>\u0010\u0016¨\u0006G"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Track;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "title", "getTitle", ImagesContract.URL, "getUrl", "", "Lcom/amazon/music/platform/graphql/generated/fragment/Track$Artist;", "artists", "Ljava/util/List;", "getArtists", "()Ljava/util/List;", "getArtists$annotations", "()V", "Lcom/amazon/music/platform/graphql/generated/fragment/Track$ContributingArtists;", "contributingArtists", "Lcom/amazon/music/platform/graphql/generated/fragment/Track$ContributingArtists;", "getContributingArtists", "()Lcom/amazon/music/platform/graphql/generated/fragment/Track$ContributingArtists;", "Lcom/amazon/music/platform/graphql/generated/fragment/Track$Album;", "album", "Lcom/amazon/music/platform/graphql/generated/fragment/Track$Album;", "getAlbum", "()Lcom/amazon/music/platform/graphql/generated/fragment/Track$Album;", "", "duration", "Ljava/lang/Double;", "getDuration", "()Ljava/lang/Double;", "Lcom/amazon/music/platform/graphql/generated/fragment/Track$Eligibility;", "eligibility", "Lcom/amazon/music/platform/graphql/generated/fragment/Track$Eligibility;", "getEligibility", "()Lcom/amazon/music/platform/graphql/generated/fragment/Track$Eligibility;", "Lcom/amazon/music/platform/graphql/generated/fragment/Track$ParentalSettings;", "parentalSettings", "Lcom/amazon/music/platform/graphql/generated/fragment/Track$ParentalSettings;", "getParentalSettings", "()Lcom/amazon/music/platform/graphql/generated/fragment/Track$ParentalSettings;", "releaseDate", "getReleaseDate", "Lcom/amazon/music/platform/graphql/generated/fragment/Track$Image;", "images", "getImages", "mediaType", "getMediaType", "trackNumber", "getTrackNumber", "languageOfPerformance", "getLanguageOfPerformance", "audioQualities", "getAudioQualities", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/amazon/music/platform/graphql/generated/fragment/Track$ContributingArtists;Lcom/amazon/music/platform/graphql/generated/fragment/Track$Album;Ljava/lang/Double;Lcom/amazon/music/platform/graphql/generated/fragment/Track$Eligibility;Lcom/amazon/music/platform/graphql/generated/fragment/Track$ParentalSettings;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/util/List;)V", "Album", "Artist", "ContributingArtists", "Eligibility", "Image", "ParentalSettings", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Track {
    private final Album album;
    private final List<Artist> artists;
    private final List<String> audioQualities;
    private final ContributingArtists contributingArtists;
    private final Double duration;
    private final Eligibility eligibility;
    private final String id;
    private final List<Image> images;
    private final String languageOfPerformance;
    private final String mediaType;
    private final ParentalSettings parentalSettings;
    private final String releaseDate;
    private final String title;
    private final Double trackNumber;
    private final String url;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Track)) {
            return false;
        }
        Track track = (Track) other;
        return Intrinsics.areEqual(this.id, track.id) && Intrinsics.areEqual(this.title, track.title) && Intrinsics.areEqual(this.url, track.url) && Intrinsics.areEqual(this.artists, track.artists) && Intrinsics.areEqual(this.contributingArtists, track.contributingArtists) && Intrinsics.areEqual(this.album, track.album) && Intrinsics.areEqual((Object) this.duration, (Object) track.duration) && Intrinsics.areEqual(this.eligibility, track.eligibility) && Intrinsics.areEqual(this.parentalSettings, track.parentalSettings) && Intrinsics.areEqual(this.releaseDate, track.releaseDate) && Intrinsics.areEqual(this.images, track.images) && Intrinsics.areEqual(this.mediaType, track.mediaType) && Intrinsics.areEqual((Object) this.trackNumber, (Object) track.trackNumber) && Intrinsics.areEqual(this.languageOfPerformance, track.languageOfPerformance) && Intrinsics.areEqual(this.audioQualities, track.audioQualities);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.title;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.url;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        List<Artist> list = this.artists;
        int iHashCode4 = (iHashCode3 + (list == null ? 0 : list.hashCode())) * 31;
        ContributingArtists contributingArtists = this.contributingArtists;
        int iHashCode5 = (iHashCode4 + (contributingArtists == null ? 0 : contributingArtists.hashCode())) * 31;
        Album album = this.album;
        int iHashCode6 = (iHashCode5 + (album == null ? 0 : album.hashCode())) * 31;
        Double d = this.duration;
        int iHashCode7 = (iHashCode6 + (d == null ? 0 : d.hashCode())) * 31;
        Eligibility eligibility = this.eligibility;
        int iHashCode8 = (iHashCode7 + (eligibility == null ? 0 : eligibility.hashCode())) * 31;
        ParentalSettings parentalSettings = this.parentalSettings;
        int iHashCode9 = (iHashCode8 + (parentalSettings == null ? 0 : parentalSettings.hashCode())) * 31;
        String str4 = this.releaseDate;
        int iHashCode10 = (iHashCode9 + (str4 == null ? 0 : str4.hashCode())) * 31;
        List<Image> list2 = this.images;
        int iHashCode11 = (iHashCode10 + (list2 == null ? 0 : list2.hashCode())) * 31;
        String str5 = this.mediaType;
        int iHashCode12 = (iHashCode11 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Double d2 = this.trackNumber;
        int iHashCode13 = (iHashCode12 + (d2 == null ? 0 : d2.hashCode())) * 31;
        String str6 = this.languageOfPerformance;
        int iHashCode14 = (iHashCode13 + (str6 == null ? 0 : str6.hashCode())) * 31;
        List<String> list3 = this.audioQualities;
        return iHashCode14 + (list3 != null ? list3.hashCode() : 0);
    }

    public String toString() {
        return "Track(id=" + this.id + ", title=" + this.title + ", url=" + this.url + ", artists=" + this.artists + ", contributingArtists=" + this.contributingArtists + ", album=" + this.album + ", duration=" + this.duration + ", eligibility=" + this.eligibility + ", parentalSettings=" + this.parentalSettings + ", releaseDate=" + this.releaseDate + ", images=" + this.images + ", mediaType=" + this.mediaType + ", trackNumber=" + this.trackNumber + ", languageOfPerformance=" + this.languageOfPerformance + ", audioQualities=" + this.audioQualities + ')';
    }

    public Track(String str, String str2, String str3, List<Artist> list, ContributingArtists contributingArtists, Album album, Double d, Eligibility eligibility, ParentalSettings parentalSettings, String str4, List<Image> list2, String str5, Double d2, String str6, List<String> list3) {
        this.id = str;
        this.title = str2;
        this.url = str3;
        this.artists = list;
        this.contributingArtists = contributingArtists;
        this.album = album;
        this.duration = d;
        this.eligibility = eligibility;
        this.parentalSettings = parentalSettings;
        this.releaseDate = str4;
        this.images = list2;
        this.mediaType = str5;
        this.trackNumber = d2;
        this.languageOfPerformance = str6;
        this.audioQualities = list3;
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

    public final List<Artist> getArtists() {
        return this.artists;
    }

    public final ContributingArtists getContributingArtists() {
        return this.contributingArtists;
    }

    public final Album getAlbum() {
        return this.album;
    }

    public final Double getDuration() {
        return this.duration;
    }

    public final Eligibility getEligibility() {
        return this.eligibility;
    }

    public final ParentalSettings getParentalSettings() {
        return this.parentalSettings;
    }

    public final String getReleaseDate() {
        return this.releaseDate;
    }

    public final List<Image> getImages() {
        return this.images;
    }

    public final String getMediaType() {
        return this.mediaType;
    }

    public final Double getTrackNumber() {
        return this.trackNumber;
    }

    public final String getLanguageOfPerformance() {
        return this.languageOfPerformance;
    }

    public final List<String> getAudioQualities() {
        return this.audioQualities;
    }

    /* JADX INFO: compiled from: Track.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Track$Artist;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "artistEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "getArtistEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: Track.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Track$ContributingArtists;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection;", "artistConnection", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection;", "getArtistConnection", "()Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: Track.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Track$Album;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/AlbumEmbedded;", "albumEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/AlbumEmbedded;", "getAlbumEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/AlbumEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/AlbumEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Album {
        private final String __typename;
        private final AlbumEmbedded albumEmbedded;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Album)) {
                return false;
            }
            Album album = (Album) other;
            return Intrinsics.areEqual(this.__typename, album.__typename) && Intrinsics.areEqual(this.albumEmbedded, album.albumEmbedded);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.albumEmbedded.hashCode();
        }

        public String toString() {
            return "Album(__typename=" + this.__typename + ", albumEmbedded=" + this.albumEmbedded + ')';
        }

        public Album(String __typename, AlbumEmbedded albumEmbedded) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(albumEmbedded, "albumEmbedded");
            this.__typename = __typename;
            this.albumEmbedded = albumEmbedded;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final AlbumEmbedded getAlbumEmbedded() {
            return this.albumEmbedded;
        }
    }

    /* JADX INFO: compiled from: Track.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Track$Eligibility;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "eligibility", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "getEligibility", "()Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: Track.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Track$ParentalSettings;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/ParentalSettings;", "parentalSettings", "Lcom/amazon/music/platform/graphql/generated/fragment/ParentalSettings;", "getParentalSettings", "()Lcom/amazon/music/platform/graphql/generated/fragment/ParentalSettings;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/ParentalSettings;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: Track.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Track$Image;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", ContextMappingConstants.IMAGE, "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "getImage", "()Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Image;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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
