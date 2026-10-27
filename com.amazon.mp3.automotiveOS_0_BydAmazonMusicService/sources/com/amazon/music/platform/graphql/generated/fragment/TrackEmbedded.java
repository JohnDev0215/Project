package com.amazon.music.platform.graphql.generated.fragment;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackEmbedded.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001:\u00043456B\u0085\u0001\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u000f\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a\u0012\b\u0010 \u001a\u0004\u0018\u00010\u001f\u0012\b\u0010%\u001a\u0004\u0018\u00010$\u0012\u0010\u0010+\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010*\u0018\u00010)\u0012\u0010\u0010/\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010)¢\u0006\u0004\b1\u00102J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0015\u0010\fR\u0019\u0010\u0016\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\n\u001a\u0004\b\u0019\u0010\fR\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010 \u001a\u0004\u0018\u00010\u001f8\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010%\u001a\u0004\u0018\u00010$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R!\u0010+\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010*\u0018\u00010)8\u0006¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R!\u0010/\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010)8\u0006¢\u0006\f\n\u0004\b/\u0010,\u001a\u0004\b0\u0010.¨\u00067"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "title", "getTitle", "", "duration", "Ljava/lang/Double;", "getDuration", "()Ljava/lang/Double;", "mediaType", "getMediaType", "trackNumber", "getTrackNumber", "languageOfPerformance", "getLanguageOfPerformance", "Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$Eligibility;", "eligibility", "Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$Eligibility;", "getEligibility", "()Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$Eligibility;", "Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$ParentalSettings;", "parentalSettings", "Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$ParentalSettings;", "getParentalSettings", "()Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$ParentalSettings;", "Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$ContributingArtists;", "contributingArtists", "Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$ContributingArtists;", "getContributingArtists", "()Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$ContributingArtists;", "", "Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$Image;", "images", "Ljava/util/List;", "getImages", "()Ljava/util/List;", "audioQualities", "getAudioQualities", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$Eligibility;Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$ParentalSettings;Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$ContributingArtists;Ljava/util/List;Ljava/util/List;)V", "ContributingArtists", "Eligibility", "Image", "ParentalSettings", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class TrackEmbedded {
    private final List<String> audioQualities;
    private final ContributingArtists contributingArtists;
    private final Double duration;
    private final Eligibility eligibility;
    private final String id;
    private final List<Image> images;
    private final String languageOfPerformance;
    private final String mediaType;
    private final ParentalSettings parentalSettings;
    private final String title;
    private final Double trackNumber;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrackEmbedded)) {
            return false;
        }
        TrackEmbedded trackEmbedded = (TrackEmbedded) other;
        return Intrinsics.areEqual(this.id, trackEmbedded.id) && Intrinsics.areEqual(this.title, trackEmbedded.title) && Intrinsics.areEqual((Object) this.duration, (Object) trackEmbedded.duration) && Intrinsics.areEqual(this.mediaType, trackEmbedded.mediaType) && Intrinsics.areEqual((Object) this.trackNumber, (Object) trackEmbedded.trackNumber) && Intrinsics.areEqual(this.languageOfPerformance, trackEmbedded.languageOfPerformance) && Intrinsics.areEqual(this.eligibility, trackEmbedded.eligibility) && Intrinsics.areEqual(this.parentalSettings, trackEmbedded.parentalSettings) && Intrinsics.areEqual(this.contributingArtists, trackEmbedded.contributingArtists) && Intrinsics.areEqual(this.images, trackEmbedded.images) && Intrinsics.areEqual(this.audioQualities, trackEmbedded.audioQualities);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.title;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Double d = this.duration;
        int iHashCode3 = (iHashCode2 + (d == null ? 0 : d.hashCode())) * 31;
        String str3 = this.mediaType;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Double d2 = this.trackNumber;
        int iHashCode5 = (iHashCode4 + (d2 == null ? 0 : d2.hashCode())) * 31;
        String str4 = this.languageOfPerformance;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Eligibility eligibility = this.eligibility;
        int iHashCode7 = (iHashCode6 + (eligibility == null ? 0 : eligibility.hashCode())) * 31;
        ParentalSettings parentalSettings = this.parentalSettings;
        int iHashCode8 = (iHashCode7 + (parentalSettings == null ? 0 : parentalSettings.hashCode())) * 31;
        ContributingArtists contributingArtists = this.contributingArtists;
        int iHashCode9 = (iHashCode8 + (contributingArtists == null ? 0 : contributingArtists.hashCode())) * 31;
        List<Image> list = this.images;
        int iHashCode10 = (iHashCode9 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.audioQualities;
        return iHashCode10 + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "TrackEmbedded(id=" + this.id + ", title=" + this.title + ", duration=" + this.duration + ", mediaType=" + this.mediaType + ", trackNumber=" + this.trackNumber + ", languageOfPerformance=" + this.languageOfPerformance + ", eligibility=" + this.eligibility + ", parentalSettings=" + this.parentalSettings + ", contributingArtists=" + this.contributingArtists + ", images=" + this.images + ", audioQualities=" + this.audioQualities + ')';
    }

    public TrackEmbedded(String str, String str2, Double d, String str3, Double d2, String str4, Eligibility eligibility, ParentalSettings parentalSettings, ContributingArtists contributingArtists, List<Image> list, List<String> list2) {
        this.id = str;
        this.title = str2;
        this.duration = d;
        this.mediaType = str3;
        this.trackNumber = d2;
        this.languageOfPerformance = str4;
        this.eligibility = eligibility;
        this.parentalSettings = parentalSettings;
        this.contributingArtists = contributingArtists;
        this.images = list;
        this.audioQualities = list2;
    }

    public final String getId() {
        return this.id;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Double getDuration() {
        return this.duration;
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

    public final Eligibility getEligibility() {
        return this.eligibility;
    }

    public final ParentalSettings getParentalSettings() {
        return this.parentalSettings;
    }

    public final ContributingArtists getContributingArtists() {
        return this.contributingArtists;
    }

    public final List<Image> getImages() {
        return this.images;
    }

    public final List<String> getAudioQualities() {
        return this.audioQualities;
    }

    /* JADX INFO: compiled from: TrackEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$Eligibility;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "eligibility", "Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "getEligibility", "()Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Eligibility;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: TrackEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$ParentalSettings;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/ParentalSettings;", "parentalSettings", "Lcom/amazon/music/platform/graphql/generated/fragment/ParentalSettings;", "getParentalSettings", "()Lcom/amazon/music/platform/graphql/generated/fragment/ParentalSettings;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/ParentalSettings;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: TrackEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$ContributingArtists;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection;", "artistConnection", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection;", "getArtistConnection", "()Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/ArtistConnection;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: TrackEmbedded.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/TrackEmbedded$Image;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", ContextMappingConstants.IMAGE, "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "getImage", "()Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Image;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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
