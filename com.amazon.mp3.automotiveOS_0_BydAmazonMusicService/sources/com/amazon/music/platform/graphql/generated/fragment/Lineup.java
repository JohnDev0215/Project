package com.amazon.music.platform.graphql.generated.fragment;

import androidx.core.app.NotificationCompat;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Lineup.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001:\u0003\"#$Bg\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\u0010\u0010\u0019\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u00010\u0017\u0012\u0010\u0010\u001e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001d\u0018\u00010\u0017¢\u0006\u0004\b \u0010!J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0014\u0010\fR\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\fR!\u0010\u0019\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u00010\u00178\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR!\u0010\u001e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001d\u0018\u00010\u00178\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001a\u001a\u0004\b\u001f\u0010\u001c¨\u0006%"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Lineup;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "scheduledStartDate", "getScheduledStartDate", "scheduledEndDate", "getScheduledEndDate", "actualStartDate", "getActualStartDate", "actualEndDate", "getActualEndDate", NotificationCompat.CATEGORY_STATUS, "getStatus", "", "Lcom/amazon/music/platform/graphql/generated/fragment/Lineup$Endpoint;", "endpoints", "Ljava/util/List;", "getEndpoints", "()Ljava/util/List;", "Lcom/amazon/music/platform/graphql/generated/fragment/Lineup$Artist;", "artists", "getArtists", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "Artist", "Artist1", "Endpoint", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class Lineup {
    private final String actualEndDate;
    private final String actualStartDate;
    private final List<Artist> artists;
    private final List<Endpoint> endpoints;
    private final String id;
    private final String scheduledEndDate;
    private final String scheduledStartDate;
    private final String status;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Lineup)) {
            return false;
        }
        Lineup lineup = (Lineup) other;
        return Intrinsics.areEqual(this.id, lineup.id) && Intrinsics.areEqual(this.scheduledStartDate, lineup.scheduledStartDate) && Intrinsics.areEqual(this.scheduledEndDate, lineup.scheduledEndDate) && Intrinsics.areEqual(this.actualStartDate, lineup.actualStartDate) && Intrinsics.areEqual(this.actualEndDate, lineup.actualEndDate) && Intrinsics.areEqual(this.status, lineup.status) && Intrinsics.areEqual(this.endpoints, lineup.endpoints) && Intrinsics.areEqual(this.artists, lineup.artists);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.scheduledStartDate;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.scheduledEndDate;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.actualStartDate;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.actualEndDate;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.status;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        List<Endpoint> list = this.endpoints;
        int iHashCode7 = (iHashCode6 + (list == null ? 0 : list.hashCode())) * 31;
        List<Artist> list2 = this.artists;
        return iHashCode7 + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "Lineup(id=" + this.id + ", scheduledStartDate=" + this.scheduledStartDate + ", scheduledEndDate=" + this.scheduledEndDate + ", actualStartDate=" + this.actualStartDate + ", actualEndDate=" + this.actualEndDate + ", status=" + this.status + ", endpoints=" + this.endpoints + ", artists=" + this.artists + ')';
    }

    public Lineup(String str, String str2, String str3, String str4, String str5, String str6, List<Endpoint> list, List<Artist> list2) {
        this.id = str;
        this.scheduledStartDate = str2;
        this.scheduledEndDate = str3;
        this.actualStartDate = str4;
        this.actualEndDate = str5;
        this.status = str6;
        this.endpoints = list;
        this.artists = list2;
    }

    public final String getId() {
        return this.id;
    }

    public final String getScheduledStartDate() {
        return this.scheduledStartDate;
    }

    public final String getScheduledEndDate() {
        return this.scheduledEndDate;
    }

    public final String getActualStartDate() {
        return this.actualStartDate;
    }

    public final String getActualEndDate() {
        return this.actualEndDate;
    }

    public final String getStatus() {
        return this.status;
    }

    public final List<Endpoint> getEndpoints() {
        return this.endpoints;
    }

    public final List<Artist> getArtists() {
        return this.artists;
    }

    /* JADX INFO: compiled from: Lineup.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Lineup$Endpoint;", "", "", "toString", "", "hashCode", "other", "", "equals", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ljava/lang/String;", "getType", "()Ljava/lang/String;", ImagesContract.URL, "getUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Endpoint {
        private final String type;
        private final String url;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Endpoint)) {
                return false;
            }
            Endpoint endpoint = (Endpoint) other;
            return Intrinsics.areEqual(this.type, endpoint.type) && Intrinsics.areEqual(this.url, endpoint.url);
        }

        public int hashCode() {
            String str = this.type;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.url;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Endpoint(type=" + this.type + ", url=" + this.url + ')';
        }

        public Endpoint(String str, String str2) {
            this.type = str;
            this.url = str2;
        }

        public final String getType() {
            return this.type;
        }

        public final String getUrl() {
            return this.url;
        }
    }

    /* JADX INFO: compiled from: Lineup.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Lineup$Artist;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/fragment/Lineup$Artist1;", "artist", "Lcom/amazon/music/platform/graphql/generated/fragment/Lineup$Artist1;", "getArtist", "()Lcom/amazon/music/platform/graphql/generated/fragment/Lineup$Artist1;", "startDate", "Ljava/lang/String;", "getStartDate", "()Ljava/lang/String;", "endDate", "getEndDate", "<init>", "(Lcom/amazon/music/platform/graphql/generated/fragment/Lineup$Artist1;Ljava/lang/String;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Artist {
        private final Artist1 artist;
        private final String endDate;
        private final String startDate;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Artist)) {
                return false;
            }
            Artist artist = (Artist) other;
            return Intrinsics.areEqual(this.artist, artist.artist) && Intrinsics.areEqual(this.startDate, artist.startDate) && Intrinsics.areEqual(this.endDate, artist.endDate);
        }

        public int hashCode() {
            Artist1 artist1 = this.artist;
            int iHashCode = (artist1 == null ? 0 : artist1.hashCode()) * 31;
            String str = this.startDate;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.endDate;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Artist(artist=" + this.artist + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ')';
        }

        public Artist(Artist1 artist1, String str, String str2) {
            this.artist = artist1;
            this.startDate = str;
            this.endDate = str2;
        }

        public final Artist1 getArtist() {
            return this.artist;
        }

        public final String getStartDate() {
            return this.startDate;
        }

        public final String getEndDate() {
            return this.endDate;
        }
    }

    /* JADX INFO: compiled from: Lineup.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/Lineup$Artist1;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "artistEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "getArtistEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/ArtistEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Artist1 {
        private final String __typename;
        private final ArtistEmbedded artistEmbedded;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Artist1)) {
                return false;
            }
            Artist1 artist1 = (Artist1) other;
            return Intrinsics.areEqual(this.__typename, artist1.__typename) && Intrinsics.areEqual(this.artistEmbedded, artist1.artistEmbedded);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.artistEmbedded.hashCode();
        }

        public String toString() {
            return "Artist1(__typename=" + this.__typename + ", artistEmbedded=" + this.artistEmbedded + ')';
        }

        public Artist1(String __typename, ArtistEmbedded artistEmbedded) {
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
