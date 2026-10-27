package com.amazon.mp3.catalog.fragment.datasource;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.DefaultStringType;
import com.amazon.mp3.util.EligibilityConverterUtil;
import com.amazon.music.find.data.database.SqliteLibrarySearchDao;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DBQueryUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b5\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\\\u0010]JY\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\r\u0010\u000eJ]\u0010\u000f\u001a\u0004\u0018\u00010\f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u000f\u0010\u000eJ\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\fJ \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014J\"\u0010\u0018\u001a\u0004\u0018\u00010\f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0016JQ\u0010\u0018\u001a\u0004\u0018\u00010\f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0005\u001a\u00020\u0004J]\u0010\u001c\u001a\u0004\u0018\u00010\f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u001c\u0010\u000eJ\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\fJ]\u0010\u001e\u001a\u0004\u0018\u00010\f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u001e\u0010\u000eJ\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\fJ\u0012\u0010!\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0010\u001a\u0004\u0018\u00010\fJ1\u0010%\u001a\b\u0012\u0004\u0012\u00020$0\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00162\u0006\u0010#\u001a\u00020\u0007¢\u0006\u0004\b%\u0010&J\u0010\u0010(\u001a\u0004\u0018\u00010\u00072\u0006\u0010'\u001a\u00020\u0014R#\u0010/\u001a\n **\u0004\u0018\u00010)0)8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R!\u00103\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b0\u0010,\u001a\u0004\b1\u00102R!\u00106\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b4\u0010,\u001a\u0004\b5\u00102R!\u00109\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b7\u0010,\u001a\u0004\b8\u00102R!\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b:\u0010,\u001a\u0004\b;\u00102R!\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b=\u0010,\u001a\u0004\b>\u00102R\u001b\u0010C\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b@\u0010,\u001a\u0004\bA\u0010BR\u001b\u0010F\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bD\u0010,\u001a\u0004\bE\u0010BR\u001b\u0010I\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bG\u0010,\u001a\u0004\bH\u0010BR!\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010,\u001a\u0004\bK\u00102R!\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bM\u0010,\u001a\u0004\bN\u00102R!\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bP\u0010,\u001a\u0004\bQ\u00102R\u001b\u0010U\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bS\u0010,\u001a\u0004\bT\u0010BR\u001b\u0010X\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bV\u0010,\u001a\u0004\bW\u0010BR\u001b\u0010[\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bY\u0010,\u001a\u0004\bZ\u0010B¨\u0006^"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/DBQueryUtil;", "", "Landroid/content/ContentResolver;", "contentResolver", "Landroid/net/Uri;", "contentUri", "", "", "projection", "selection", "selectionArgs", "sortOrder", "Landroid/database/Cursor;", "runQuery", "(Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "runTrackQuery", "cursor", "", "Lcom/amazon/mp3/library/item/MusicTrack;", "convertTracksCursorToTracks", "Lcom/amazon/mp3/library/item/AbstractItem;", "collectionItem", "", "withAudioBadging", "runAlbumDetailQuery", "(Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;", "Lcom/amazon/mp3/library/item/Album;", "convertAlbumDetailCursorToAlbum", "runAlbumListQuery", "convertCursorToAlbums", "runArtistListQuery", "Lcom/amazon/mp3/library/item/Artist;", "convertCursorToArtists", "convertCursorToArtist", "excludeSmartPlaylists", PageUriUtils.SOURCE_QUERY_PARAM, "Lcom/amazon/mp3/library/item/Playlist;", "convertCursorToPlaylists", "(Landroid/database/Cursor;Ljava/lang/Boolean;Ljava/lang/String;)Ljava/util/List;", "item", "getSharedId", "Lcom/amazon/mp3/library/item/factory/LibraryItemFactory;", "kotlin.jvm.PlatformType", "libraryItemFactory$delegate", "Lkotlin/Lazy;", "getLibraryItemFactory", "()Lcom/amazon/mp3/library/item/factory/LibraryItemFactory;", "libraryItemFactory", "trackProjection$delegate", "getTrackProjection", "()[Ljava/lang/String;", "trackProjection", "albumListProjection$delegate", "getAlbumListProjection", "albumListProjection", "albumDetailProjection$delegate", "getAlbumDetailProjection", "albumDetailProjection", "artistListProjection$delegate", "getArtistListProjection", "artistListProjection", "playlistsListProjection$delegate", "getPlaylistsListProjection", "playlistsListProjection", "trackSelection$delegate", "getTrackSelection", "()Ljava/lang/String;", "trackSelection", "libraryListSelection$delegate", "getLibraryListSelection", "libraryListSelection", "albumDetailSelection$delegate", "getAlbumDetailSelection", "albumDetailSelection", "trackSelectionArgs$delegate", "getTrackSelectionArgs", "trackSelectionArgs", "libraryListSelectionArgs$delegate", "getLibraryListSelectionArgs", "libraryListSelectionArgs", "albumDetailSelectionArgs$delegate", "getAlbumDetailSelectionArgs", "albumDetailSelectionArgs", "trackSortOrder$delegate", "getTrackSortOrder", "trackSortOrder", "albumListSortOrder$delegate", "getAlbumListSortOrder", "albumListSortOrder", "artistListSortOrder$delegate", "getArtistListSortOrder", "artistListSortOrder", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DBQueryUtil {
    public static final DBQueryUtil INSTANCE = new DBQueryUtil();

    /* JADX INFO: renamed from: libraryItemFactory$delegate, reason: from kotlin metadata */
    private static final Lazy libraryItemFactory = LazyKt__LazyJVMKt.lazy(new Function0<LibraryItemFactory>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$libraryItemFactory$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LibraryItemFactory invoke() {
            return AmazonApplication.getLibraryItemFactory();
        }
    });

    /* JADX INFO: renamed from: trackProjection$delegate, reason: from kotlin metadata */
    private static final Lazy trackProjection = LazyKt__LazyJVMKt.lazy(new Function0<String[]>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$trackProjection$2
        @Override // kotlin.jvm.functions.Function0
        public final String[] invoke() {
            return DBQueryUtil.INSTANCE.getLibraryItemFactory().getTrackProjection();
        }
    });

    /* JADX INFO: renamed from: albumListProjection$delegate, reason: from kotlin metadata */
    private static final Lazy albumListProjection = LazyKt__LazyJVMKt.lazy(new Function0<String[]>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$albumListProjection$2
        @Override // kotlin.jvm.functions.Function0
        public final String[] invoke() {
            return DBQueryUtil.INSTANCE.getLibraryItemFactory().getAlbumProjection();
        }
    });

    /* JADX INFO: renamed from: albumDetailProjection$delegate, reason: from kotlin metadata */
    private static final Lazy albumDetailProjection = LazyKt__LazyJVMKt.lazy(new Function0<String[]>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$albumDetailProjection$2
        @Override // kotlin.jvm.functions.Function0
        public final String[] invoke() {
            return new String[]{"title", "artist", "album_asin", "artist_id", "artist_asin", "album_art_id", SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN, "content_catalog_status_max_non_previous", SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX, SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX, "track_count", "content_encoding", "_id", "album_art_id", "playmode_eligibility"};
        }
    });

    /* JADX INFO: renamed from: artistListProjection$delegate, reason: from kotlin metadata */
    private static final Lazy artistListProjection = LazyKt__LazyJVMKt.lazy(new Function0<String[]>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$artistListProjection$2
        @Override // kotlin.jvm.functions.Function0
        public final String[] invoke() {
            return new String[]{"_id", "name", "follow_status", "sort_name", "album_count", "track_count", "download_state", "download_status"};
        }
    });

    /* JADX INFO: renamed from: playlistsListProjection$delegate, reason: from kotlin metadata */
    private static final Lazy playlistsListProjection = LazyKt__LazyJVMKt.lazy(new Function0<String[]>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$playlistsListProjection$2
        @Override // kotlin.jvm.functions.Function0
        public final String[] invoke() {
            return new String[]{"_id", "name", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "download_state", "track_count", "visibility", "is_sonic_rush", "is_sonic_rush_on_demand", "is_sonic_rush_golden", "dateLastTouched", "dateLastModified", "dateCreated", "playmode_eligibility"};
        }
    });

    /* JADX INFO: renamed from: trackSelection$delegate, reason: from kotlin metadata */
    private static final Lazy trackSelection = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$trackSelection$2
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return "ownership_status< ? ";
        }
    });

    /* JADX INFO: renamed from: libraryListSelection$delegate, reason: from kotlin metadata */
    private static final Lazy libraryListSelection = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$libraryListSelection$2
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return DbUtil.applyBinaryOperator(DBQueryUtil.INSTANCE.getTrackSelection(), "AND", "playlist_track_status = ?");
        }
    });

    /* JADX INFO: renamed from: albumDetailSelection$delegate, reason: from kotlin metadata */
    private static final Lazy albumDetailSelection = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$albumDetailSelection$2
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return DeluxeContentUtil.getNonDeluxeSelection();
        }
    });

    /* JADX INFO: renamed from: trackSelectionArgs$delegate, reason: from kotlin metadata */
    private static final Lazy trackSelectionArgs = LazyKt__LazyJVMKt.lazy(new Function0<String[]>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$trackSelectionArgs$2
        @Override // kotlin.jvm.functions.Function0
        public final String[] invoke() {
            return new String[]{"300"};
        }
    });

    /* JADX INFO: renamed from: libraryListSelectionArgs$delegate, reason: from kotlin metadata */
    private static final Lazy libraryListSelectionArgs = LazyKt__LazyJVMKt.lazy(new Function0<String[]>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$libraryListSelectionArgs$2
        @Override // kotlin.jvm.functions.Function0
        public final String[] invoke() {
            return new String[]{"300", String.valueOf(MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue())};
        }
    });

    /* JADX INFO: renamed from: albumDetailSelectionArgs$delegate, reason: from kotlin metadata */
    private static final Lazy albumDetailSelectionArgs = LazyKt__LazyJVMKt.lazy(new Function0<String[]>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$albumDetailSelectionArgs$2
        @Override // kotlin.jvm.functions.Function0
        public final String[] invoke() {
            return DeluxeContentUtil.getNonDeluxeSelectionArgs();
        }
    });

    /* JADX INFO: renamed from: trackSortOrder$delegate, reason: from kotlin metadata */
    private static final Lazy trackSortOrder = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$trackSortOrder$2
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return CirrusMediaSource.getTrackSortOrder();
        }
    });

    /* JADX INFO: renamed from: albumListSortOrder$delegate, reason: from kotlin metadata */
    private static final Lazy albumListSortOrder = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$albumListSortOrder$2
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return CirrusMediaSource.getAlbumSortOrder();
        }
    });

    /* JADX INFO: renamed from: artistListSortOrder$delegate, reason: from kotlin metadata */
    private static final Lazy artistListSortOrder = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil$artistListSortOrder$2
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return CirrusMediaSource.getArtistSortOrder();
        }
    });

    private DBQueryUtil() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LibraryItemFactory getLibraryItemFactory() {
        return (LibraryItemFactory) libraryItemFactory.getValue();
    }

    public final String[] getTrackProjection() {
        Object value = trackProjection.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-trackProjection>(...)");
        return (String[]) value;
    }

    public final String[] getAlbumListProjection() {
        Object value = albumListProjection.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-albumListProjection>(...)");
        return (String[]) value;
    }

    public final String[] getAlbumDetailProjection() {
        return (String[]) albumDetailProjection.getValue();
    }

    public final String[] getArtistListProjection() {
        return (String[]) artistListProjection.getValue();
    }

    public final String[] getPlaylistsListProjection() {
        return (String[]) playlistsListProjection.getValue();
    }

    public final String getTrackSelection() {
        return (String) trackSelection.getValue();
    }

    public final String getLibraryListSelection() {
        Object value = libraryListSelection.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-libraryListSelection>(...)");
        return (String) value;
    }

    public final String getAlbumDetailSelection() {
        Object value = albumDetailSelection.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-albumDetailSelection>(...)");
        return (String) value;
    }

    public final String[] getTrackSelectionArgs() {
        return (String[]) trackSelectionArgs.getValue();
    }

    public final String[] getLibraryListSelectionArgs() {
        return (String[]) libraryListSelectionArgs.getValue();
    }

    public final String[] getAlbumDetailSelectionArgs() {
        Object value = albumDetailSelectionArgs.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-albumDetailSelectionArgs>(...)");
        return (String[]) value;
    }

    public final String getTrackSortOrder() {
        Object value = trackSortOrder.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-trackSortOrder>(...)");
        return (String) value;
    }

    public final String getAlbumListSortOrder() {
        Object value = albumListSortOrder.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-albumListSortOrder>(...)");
        return (String) value;
    }

    public final String getArtistListSortOrder() {
        Object value = artistListSortOrder.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-artistListSortOrder>(...)");
        return (String) value;
    }

    public final Cursor runQuery(ContentResolver contentResolver, Uri contentUri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(projection, "projection");
        if (contentResolver == null) {
            return null;
        }
        return contentResolver.query(contentUri, projection, selection, selectionArgs, sortOrder);
    }

    public static /* synthetic */ Cursor runTrackQuery$default(DBQueryUtil dBQueryUtil, ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            strArr = dBQueryUtil.getTrackProjection();
        }
        String[] strArr3 = strArr;
        if ((i & 8) != 0) {
            str = dBQueryUtil.getTrackSelection();
        }
        String str3 = str;
        if ((i & 16) != 0) {
            strArr2 = dBQueryUtil.getTrackSelectionArgs();
        }
        String[] strArr4 = strArr2;
        if ((i & 32) != 0) {
            str2 = dBQueryUtil.getTrackSortOrder();
        }
        return dBQueryUtil.runTrackQuery(contentResolver, uri, strArr3, str3, strArr4, str2);
    }

    public final Cursor runTrackQuery(ContentResolver contentResolver, Uri contentUri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNull(projection);
        return runQuery(contentResolver, contentUri, projection, selection, selectionArgs, sortOrder);
    }

    public final List<MusicTrack> convertTracksCursorToTracks(Cursor cursor) {
        return convertTracksCursorToTracks(cursor, null);
    }

    public final List<MusicTrack> convertTracksCursorToTracks(Cursor cursor, AbstractItem collectionItem) {
        if (cursor == null) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList<MusicTrack> arrayList = new ArrayList(Math.max(cursor.getCount(), 0));
        while (cursor.moveToNext()) {
            try {
                MusicTrack track = getLibraryItemFactory().getTrack(cursor);
                Intrinsics.checkNotNullExpressionValue(track, "libraryItemFactory.getTrack(cursor)");
                arrayList.add(track);
            } finally {
                DbUtil.closeCursor(cursor);
            }
        }
        if (collectionItem != null) {
            for (MusicTrack musicTrack : arrayList) {
                musicTrack.setParentEligibilities(collectionItem);
                musicTrack.setCollectionUri(collectionItem.getContentUri());
            }
        }
        return arrayList;
    }

    public final Cursor runAlbumDetailQuery(ContentResolver contentResolver, Uri contentUri, boolean withAudioBadging) {
        String albumDetailSelection2;
        String[] albumDetailSelectionArgs2;
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        if (withAudioBadging) {
            albumDetailSelection2 = DbUtil.applyBinaryOperator(getTrackSelection(), "AND", getAlbumDetailSelection());
        } else {
            albumDetailSelection2 = getAlbumDetailSelection();
        }
        String str = albumDetailSelection2;
        if (withAudioBadging) {
            albumDetailSelectionArgs2 = DbUtil.mergeColumnArrays(getTrackSelectionArgs(), getAlbumDetailSelectionArgs());
        } else {
            albumDetailSelectionArgs2 = getAlbumDetailSelectionArgs();
        }
        return runAlbumDetailQuery$default(this, contentResolver, contentUri, null, str, albumDetailSelectionArgs2, 4, null);
    }

    public static /* synthetic */ Cursor runAlbumDetailQuery$default(DBQueryUtil dBQueryUtil, ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, int i, Object obj) {
        if ((i & 4) != 0) {
            strArr = dBQueryUtil.getAlbumDetailProjection();
        }
        String[] strArr3 = strArr;
        if ((i & 8) != 0) {
            str = dBQueryUtil.getAlbumDetailSelection();
        }
        String str2 = str;
        if ((i & 16) != 0) {
            strArr2 = dBQueryUtil.getAlbumDetailSelectionArgs();
        }
        return dBQueryUtil.runAlbumDetailQuery(contentResolver, uri, strArr3, str2, strArr2);
    }

    public final Cursor runAlbumDetailQuery(ContentResolver contentResolver, Uri contentUri, String[] projection, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNull(projection);
        return runQuery(contentResolver, contentUri, projection, selection, selectionArgs, null);
    }

    public final Album convertAlbumDetailCursorToAlbum(Cursor cursor, Uri contentUri) {
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Album album = null;
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                album = new Album(getLibraryItemFactory(), contentUri);
                album.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
                DefaultStringType defaultStringType = DefaultStringType.ALBUM;
                if (defaultStringType.isPlatformOrCirrusDefault(album.getTitle())) {
                    album.setTitle(defaultStringType.getDefault());
                }
                album.setArtistName(cursor.getString(cursor.getColumnIndexOrThrow("artist")));
                DefaultStringType defaultStringType2 = DefaultStringType.ARTIST;
                if (defaultStringType2.isPlatformOrCirrusDefault(album.getArtistName())) {
                    album.setArtistName(defaultStringType2.getDefault());
                }
                album.setAsin(cursor.getString(cursor.getColumnIndexOrThrow("album_asin")));
                album.setArtistAsin(cursor.getString(cursor.getColumnIndexOrThrow("artist_asin")));
                album.setTrackCount(cursor.getInt(cursor.getColumnIndexOrThrow("track_count")));
                album.setMaxContentCatalogStatusValue(cursor.getInt(cursor.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX)));
                album.setMinContentCatalogStatusValue(cursor.getInt(cursor.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MIN)));
                album.setMaxNonPreviousContentCatalogStatusValue(cursor.getInt(cursor.getColumnIndexOrThrow("content_catalog_status_max_non_previous")));
                album.setMaxContentOwnershipStatusValue(cursor.getInt(cursor.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX)));
                album.setMinContentOwnershipStatusValue(cursor.getInt(cursor.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MIN)));
                album.setId(cursor.getString(cursor.getColumnIndex("_id")));
                album.setAlbumArtId(cursor.getLong(cursor.getColumnIndexOrThrow("album_art_id")));
                album.setContentEncodings(ContentEncodingDecoder.getAggregatedContentEncoding(cursor.getString(cursor.getColumnIndex("content_encoding"))));
                album.setPlaymodeEligibility(EligibilityConverterUtil.getPlaymodeEligibilityFromJson(cursor.getString(cursor.getColumnIndexOrThrow("playmode_eligibility"))));
                CatalogStatusTiers tiers = album.getCatalogStatusTiers();
                Intrinsics.checkNotNullExpressionValue(tiers, "tiers");
                album.setCatalogStatusTiers(CatalogStatusTiers.copy$default(tiers, false, false, false, false, false, false, false, 3, null));
            }
            return album;
        } finally {
            DbUtil.closeCursor(cursor);
        }
    }

    public static /* synthetic */ Cursor runAlbumListQuery$default(DBQueryUtil dBQueryUtil, ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            strArr = dBQueryUtil.getAlbumListProjection();
        }
        String[] strArr3 = strArr;
        if ((i & 8) != 0) {
            str = dBQueryUtil.getLibraryListSelection();
        }
        String str3 = str;
        if ((i & 16) != 0) {
            strArr2 = dBQueryUtil.getLibraryListSelectionArgs();
        }
        String[] strArr4 = strArr2;
        if ((i & 32) != 0) {
            str2 = dBQueryUtil.getAlbumListSortOrder();
        }
        return dBQueryUtil.runAlbumListQuery(contentResolver, uri, strArr3, str3, strArr4, str2);
    }

    public final Cursor runAlbumListQuery(ContentResolver contentResolver, Uri contentUri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNull(projection);
        return runQuery(contentResolver, contentUri, projection, selection, selectionArgs, sortOrder);
    }

    public final List<Album> convertCursorToAlbums(Cursor cursor) {
        if (cursor == null) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                Album album = getLibraryItemFactory().getAlbum(cursor);
                album.setContentEncodings(ContentEncodingDecoder.getAggregatedContentEncoding(cursor.getString(cursor.getColumnIndex("content_encoding"))));
                Intrinsics.checkNotNullExpressionValue(album, "album");
                arrayList.add(album);
            } finally {
                DbUtil.closeCursor(cursor);
            }
        }
        return arrayList;
    }

    public static /* synthetic */ Cursor runArtistListQuery$default(DBQueryUtil dBQueryUtil, ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            strArr = dBQueryUtil.getArtistListProjection();
        }
        String[] strArr3 = strArr;
        if ((i & 8) != 0) {
            str = dBQueryUtil.getLibraryListSelection();
        }
        String str3 = str;
        if ((i & 16) != 0) {
            strArr2 = dBQueryUtil.getLibraryListSelectionArgs();
        }
        String[] strArr4 = strArr2;
        if ((i & 32) != 0) {
            str2 = dBQueryUtil.getArtistListSortOrder();
        }
        return dBQueryUtil.runArtistListQuery(contentResolver, uri, strArr3, str3, strArr4, str2);
    }

    public final Cursor runArtistListQuery(ContentResolver contentResolver, Uri contentUri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNull(projection);
        return runQuery(contentResolver, contentUri, projection, selection, selectionArgs, sortOrder);
    }

    public final List<Artist> convertCursorToArtists(Cursor cursor) {
        if (cursor == null) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                Artist artist = getLibraryItemFactory().getArtist(cursor);
                Intrinsics.checkNotNullExpressionValue(artist, "artist");
                arrayList.add(artist);
            } finally {
                DbUtil.closeCursor(cursor);
            }
        }
        return arrayList;
    }

    public final Artist convertCursorToArtist(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToNext()) {
                return getLibraryItemFactory().getArtist(cursor);
            }
            return null;
        } finally {
            DbUtil.closeCursor(cursor);
        }
    }

    public final List<Playlist> convertCursorToPlaylists(Cursor cursor, Boolean excludeSmartPlaylists, String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (cursor == null) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            try {
                Playlist playlist = getLibraryItemFactory().getPlaylist(cursor, source);
                if (!Intrinsics.areEqual(excludeSmartPlaylists, Boolean.TRUE) || !Intrinsics.areEqual(playlist.getType(), "vnd.android.cursor.item/vnd.amazonmp3.smartplaylist")) {
                    Intrinsics.checkNotNullExpressionValue(playlist, "playlist");
                    arrayList.add(playlist);
                }
            } finally {
                DbUtil.closeCursor(cursor);
            }
        }
        return arrayList;
    }

    public final String getSharedId(AbstractItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item instanceof Playlist) {
            return ((Playlist) item).getSharedId();
        }
        if (!(item instanceof CatalogPlaylist)) {
            return null;
        }
        CatalogPlaylist catalogPlaylist = (CatalogPlaylist) item;
        if (catalogPlaylist.isSharedUserPlaylist()) {
            return catalogPlaylist.getAsin();
        }
        return null;
    }
}
