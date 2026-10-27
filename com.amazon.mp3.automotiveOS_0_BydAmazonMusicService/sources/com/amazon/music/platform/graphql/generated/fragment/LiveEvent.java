package com.amazon.music.platform.graphql.generated.fragment;

import androidx.core.app.NotificationCompat;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amplifyframework.datastore.appsync.ModelWithMetadataAdapter;
import com.google.android.gms.cast.MediaTrack;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveEvent.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001:\u0006345678BÅ\u0001\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\u0010\u0010\u001f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0018\u00010\u001d\u0012\b\u0010#\u001a\u0004\u0018\u00010\u0002\u0012\u0010\u0010&\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010%\u0018\u00010\u001d\u0012\u0010\u0010)\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010(\u0018\u00010\u001d\u0012\u0010\u0010,\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010+\u0018\u00010\u001d\u0012\u0010\u0010/\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010.\u0018\u00010\u001d¢\u0006\u0004\b1\u00102J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\fR\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0013\u0010\n\u001a\u0004\b\u0014\u0010\fR\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\n\u001a\u0004\b\u0016\u0010\fR\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\n\u001a\u0004\b\u0018\u0010\fR\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\n\u001a\u0004\b\u001a\u0010\fR\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\n\u001a\u0004\b\u001c\u0010\fR!\u0010\u001f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0019\u0010#\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b#\u0010\n\u001a\u0004\b$\u0010\fR!\u0010&\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010%\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\b&\u0010 \u001a\u0004\b'\u0010\"R!\u0010)\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010(\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\b)\u0010 \u001a\u0004\b*\u0010\"R!\u0010,\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010+\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\b,\u0010 \u001a\u0004\b-\u0010\"R!\u0010/\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010.\u0018\u00010\u001d8\u0006¢\u0006\f\n\u0004\b/\u0010 \u001a\u0004\b0\u0010\"¨\u00069"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "name", "getName", "location", "getLocation", "venue", "getVenue", "presentedBy", "getPresentedBy", MediaTrack.ROLE_DESCRIPTION, "getDescription", "startDate", "getStartDate", "endDate", "getEndDate", NotificationCompat.CATEGORY_STATUS, "getStatus", "", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$LineupGroup;", "lineupGroups", "Ljava/util/List;", "getLineupGroups", "()Ljava/util/List;", "lineupGroupNameType", "getLineupGroupNameType", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$Image;", "images", "getImages", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$Album;", "albums", "getAlbums", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$Merch;", "merch", "getMerch", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$Playlist;", "playlists", "getPlaylists", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "Album", "Image", "Lineup", "LineupGroup", "Merch", "Playlist", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class LiveEvent {
    private final List<Album> albums;
    private final String description;
    private final String endDate;
    private final String id;
    private final List<Image> images;
    private final String lineupGroupNameType;
    private final List<LineupGroup> lineupGroups;
    private final String location;
    private final List<Merch> merch;
    private final String name;
    private final List<Playlist> playlists;
    private final String presentedBy;
    private final String startDate;
    private final String status;
    private final String venue;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LiveEvent)) {
            return false;
        }
        LiveEvent liveEvent = (LiveEvent) other;
        return Intrinsics.areEqual(this.id, liveEvent.id) && Intrinsics.areEqual(this.name, liveEvent.name) && Intrinsics.areEqual(this.location, liveEvent.location) && Intrinsics.areEqual(this.venue, liveEvent.venue) && Intrinsics.areEqual(this.presentedBy, liveEvent.presentedBy) && Intrinsics.areEqual(this.description, liveEvent.description) && Intrinsics.areEqual(this.startDate, liveEvent.startDate) && Intrinsics.areEqual(this.endDate, liveEvent.endDate) && Intrinsics.areEqual(this.status, liveEvent.status) && Intrinsics.areEqual(this.lineupGroups, liveEvent.lineupGroups) && Intrinsics.areEqual(this.lineupGroupNameType, liveEvent.lineupGroupNameType) && Intrinsics.areEqual(this.images, liveEvent.images) && Intrinsics.areEqual(this.albums, liveEvent.albums) && Intrinsics.areEqual(this.merch, liveEvent.merch) && Intrinsics.areEqual(this.playlists, liveEvent.playlists);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.location;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.venue;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.presentedBy;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.description;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.startDate;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.endDate;
        int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.status;
        int iHashCode9 = (iHashCode8 + (str9 == null ? 0 : str9.hashCode())) * 31;
        List<LineupGroup> list = this.lineupGroups;
        int iHashCode10 = (iHashCode9 + (list == null ? 0 : list.hashCode())) * 31;
        String str10 = this.lineupGroupNameType;
        int iHashCode11 = (iHashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31;
        List<Image> list2 = this.images;
        int iHashCode12 = (iHashCode11 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<Album> list3 = this.albums;
        int iHashCode13 = (iHashCode12 + (list3 == null ? 0 : list3.hashCode())) * 31;
        List<Merch> list4 = this.merch;
        int iHashCode14 = (iHashCode13 + (list4 == null ? 0 : list4.hashCode())) * 31;
        List<Playlist> list5 = this.playlists;
        return iHashCode14 + (list5 != null ? list5.hashCode() : 0);
    }

    public String toString() {
        return "LiveEvent(id=" + this.id + ", name=" + this.name + ", location=" + this.location + ", venue=" + this.venue + ", presentedBy=" + this.presentedBy + ", description=" + this.description + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", status=" + this.status + ", lineupGroups=" + this.lineupGroups + ", lineupGroupNameType=" + this.lineupGroupNameType + ", images=" + this.images + ", albums=" + this.albums + ", merch=" + this.merch + ", playlists=" + this.playlists + ')';
    }

    public LiveEvent(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, List<LineupGroup> list, String str10, List<Image> list2, List<Album> list3, List<Merch> list4, List<Playlist> list5) {
        this.id = str;
        this.name = str2;
        this.location = str3;
        this.venue = str4;
        this.presentedBy = str5;
        this.description = str6;
        this.startDate = str7;
        this.endDate = str8;
        this.status = str9;
        this.lineupGroups = list;
        this.lineupGroupNameType = str10;
        this.images = list2;
        this.albums = list3;
        this.merch = list4;
        this.playlists = list5;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getLocation() {
        return this.location;
    }

    public final String getVenue() {
        return this.venue;
    }

    public final String getPresentedBy() {
        return this.presentedBy;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getStartDate() {
        return this.startDate;
    }

    public final String getEndDate() {
        return this.endDate;
    }

    public final String getStatus() {
        return this.status;
    }

    public final List<LineupGroup> getLineupGroups() {
        return this.lineupGroups;
    }

    public final String getLineupGroupNameType() {
        return this.lineupGroupNameType;
    }

    public final List<Image> getImages() {
        return this.images;
    }

    public final List<Album> getAlbums() {
        return this.albums;
    }

    public final List<Merch> getMerch() {
        return this.merch;
    }

    public final List<Playlist> getPlaylists() {
        return this.playlists;
    }

    /* JADX INFO: compiled from: LiveEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u0012\u0010\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\r¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR!\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$LineupGroup;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$Lineup;", "lineups", "Ljava/util/List;", "getLineups", "()Ljava/util/List;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class LineupGroup {
        private final String id;
        private final List<Lineup> lineups;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LineupGroup)) {
                return false;
            }
            LineupGroup lineupGroup = (LineupGroup) other;
            return Intrinsics.areEqual(this.id, lineupGroup.id) && Intrinsics.areEqual(this.lineups, lineupGroup.lineups);
        }

        public int hashCode() {
            String str = this.id;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            List<Lineup> list = this.lineups;
            return iHashCode + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "LineupGroup(id=" + this.id + ", lineups=" + this.lineups + ')';
        }

        public LineupGroup(String str, List<Lineup> list) {
            this.id = str;
            this.lineups = list;
        }

        public final String getId() {
            return this.id;
        }

        public final List<Lineup> getLineups() {
            return this.lineups;
        }
    }

    /* JADX INFO: compiled from: LiveEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$Image;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", ContextMappingConstants.IMAGE, "Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "getImage", "()Lcom/amazon/music/platform/graphql/generated/fragment/Image;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Image;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: LiveEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$Album;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/AlbumEmbedded;", "albumEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/AlbumEmbedded;", "getAlbumEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/AlbumEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/AlbumEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
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

    /* JADX INFO: compiled from: LiveEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$Merch;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Merch;", "merch", "Lcom/amazon/music/platform/graphql/generated/fragment/Merch;", "getMerch", "()Lcom/amazon/music/platform/graphql/generated/fragment/Merch;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Merch;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Merch {
        private final String __typename;
        private final com.amazon.music.platform.graphql.generated.fragment.Merch merch;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Merch)) {
                return false;
            }
            Merch merch = (Merch) other;
            return Intrinsics.areEqual(this.__typename, merch.__typename) && Intrinsics.areEqual(this.merch, merch.merch);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.merch.hashCode();
        }

        public String toString() {
            return "Merch(__typename=" + this.__typename + ", merch=" + this.merch + ')';
        }

        public Merch(String __typename, com.amazon.music.platform.graphql.generated.fragment.Merch merch) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(merch, "merch");
            this.__typename = __typename;
            this.merch = merch;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final com.amazon.music.platform.graphql.generated.fragment.Merch getMerch() {
            return this.merch;
        }
    }

    /* JADX INFO: compiled from: LiveEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$Playlist;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/PlaylistEmbedded;", "playlistEmbedded", "Lcom/amazon/music/platform/graphql/generated/fragment/PlaylistEmbedded;", "getPlaylistEmbedded", "()Lcom/amazon/music/platform/graphql/generated/fragment/PlaylistEmbedded;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/PlaylistEmbedded;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Playlist {
        private final String __typename;
        private final PlaylistEmbedded playlistEmbedded;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Playlist)) {
                return false;
            }
            Playlist playlist = (Playlist) other;
            return Intrinsics.areEqual(this.__typename, playlist.__typename) && Intrinsics.areEqual(this.playlistEmbedded, playlist.playlistEmbedded);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.playlistEmbedded.hashCode();
        }

        public String toString() {
            return "Playlist(__typename=" + this.__typename + ", playlistEmbedded=" + this.playlistEmbedded + ')';
        }

        public Playlist(String __typename, PlaylistEmbedded playlistEmbedded) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(playlistEmbedded, "playlistEmbedded");
            this.__typename = __typename;
            this.playlistEmbedded = playlistEmbedded;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final PlaylistEmbedded getPlaylistEmbedded() {
            return this.playlistEmbedded;
        }
    }

    /* JADX INFO: compiled from: LiveEvent.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$Lineup;", "", "", "toString", "", "hashCode", "other", "", "equals", ModelWithMetadataAdapter.TYPE_NAME, "Ljava/lang/String;", "get__typename", "()Ljava/lang/String;", "Lcom/amazon/music/platform/graphql/generated/fragment/Lineup;", "lineup", "Lcom/amazon/music/platform/graphql/generated/fragment/Lineup;", "getLineup", "()Lcom/amazon/music/platform/graphql/generated/fragment/Lineup;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/graphql/generated/fragment/Lineup;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Lineup {
        private final String __typename;
        private final com.amazon.music.platform.graphql.generated.fragment.Lineup lineup;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Lineup)) {
                return false;
            }
            Lineup lineup = (Lineup) other;
            return Intrinsics.areEqual(this.__typename, lineup.__typename) && Intrinsics.areEqual(this.lineup, lineup.lineup);
        }

        public int hashCode() {
            return (this.__typename.hashCode() * 31) + this.lineup.hashCode();
        }

        public String toString() {
            return "Lineup(__typename=" + this.__typename + ", lineup=" + this.lineup + ')';
        }

        public Lineup(String __typename, com.amazon.music.platform.graphql.generated.fragment.Lineup lineup) {
            Intrinsics.checkNotNullParameter(__typename, "__typename");
            Intrinsics.checkNotNullParameter(lineup, "lineup");
            this.__typename = __typename;
            this.lineup = lineup;
        }

        public final String get__typename() {
            return this.__typename;
        }

        public final com.amazon.music.platform.graphql.generated.fragment.Lineup getLineup() {
            return this.lineup;
        }
    }
}
