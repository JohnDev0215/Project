package com.amazon.music.find.data.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.find.api.util.SearchFilterUtil;
import com.amazon.music.find.api.util.SpellCorrectionsUtil;
import com.amazon.music.find.featuregating.FindFeatureGating;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.model.search.AlbumSearchItem;
import com.amazon.music.find.model.search.ArtistSearchItem;
import com.amazon.music.find.model.search.ContentOwnershipItem;
import com.amazon.music.find.model.search.GenericSearchResponse;
import com.amazon.music.find.model.search.PlaylistSearchItem;
import com.amazon.music.find.model.search.PlaylistType;
import com.amazon.music.find.model.search.SearchContentCatalogStatus;
import com.amazon.music.find.model.search.SearchContentOwnershipStatus;
import com.amazon.music.find.model.search.SearchDownloadedState;
import com.amazon.music.find.model.search.SearchFollowSource;
import com.amazon.music.find.model.search.SearchLibrarySource;
import com.amazon.music.find.model.search.SearchPlaylistTrackStatus;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.model.search.TrackSearchItem;
import com.amazon.music.find.providers.SearchEntityFactory;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.search.SectionType;
import com.amazon.music.search.SpellCorrections;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SqliteLibrarySearchDao.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u0085\u00012\u00020\u0001:\u0002\u0085\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020 0\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J,\u0010&\u001a\u00020'2\n\u0010(\u001a\u00060)j\u0002`*2\u0006\u0010+\u001a\u00020\u000b2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\nH\u0002J$\u0010.\u001a\u00020'2\n\u0010(\u001a\u00060)j\u0002`*2\u0006\u0010+\u001a\u00020\u000b2\u0006\u0010/\u001a\u000200H\u0002J\u0018\u00101\u001a\u0002022\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u001e\u00103\u001a\b\u0012\u0004\u0012\u0002020\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002JH\u00104\u001a\u0002052\u0006\u0010!\u001a\u00020\"2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\n2\u0006\u00106\u001a\u0002072\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u00109\u001a\u0004\u0018\u00010\u000b2\u0006\u0010:\u001a\u00020\u000bH\u0002J\u0010\u0010;\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\u000bH\u0002J2\u0010<\u001a\b\u0012\u0004\u0012\u00020 0\n2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020 0\n2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\n2\u0006\u0010@\u001a\u00020AH\u0002J$\u0010B\u001a\b\u0012\u0004\u0012\u0002020\n2\f\u0010C\u001a\b\u0012\u0004\u0012\u0002020\n2\u0006\u0010@\u001a\u00020AH\u0002J$\u0010D\u001a\b\u0012\u0004\u0012\u00020E0\n2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020E0\n2\u0006\u0010@\u001a\u00020AH\u0002J$\u0010G\u001a\b\u0012\u0004\u0012\u00020?0\n2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\n2\u0006\u0010@\u001a\u00020AH\u0002J\u001e\u0010H\u001a\u0004\u0018\u00010\u000b2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010:\u001a\u00020\u000bJ>\u0010J\u001a\b\u0012\u0004\u0012\u00020 0\n2\u0006\u0010!\u001a\u00020\"2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\n2\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010K\u001a\u000205H\u0002J>\u0010L\u001a\b\u0012\u0004\u0012\u0002020\n2\u0006\u0010!\u001a\u00020\"2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\n2\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010M\u001a\u000205H\u0002J'\u0010N\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010I\u001a\n\u0012\u0006\b\u0001\u0012\u00020P0O\"\u00020PH\u0002¢\u0006\u0002\u0010QJ^\u0010R\u001a\b\u0012\u0004\u0012\u00020E0\n2\u0006\u0010S\u001a\u00020\"2\u000e\u0010T\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\n2\u0006\u0010U\u001a\u00020\"2\u000e\u0010V\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\n2\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010W\u001a\u0002052\u0006\u0010/\u001a\u000200H\u0002J\u0010\u0010X\u001a\u00020\"2\u0006\u0010Y\u001a\u00020ZH\u0002J>\u0010[\u001a\b\u0012\u0004\u0012\u00020?0\n2\u0006\u0010!\u001a\u00020\"2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\n2\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010\\\u001a\u000205H\u0002J'\u0010]\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0012\u0010I\u001a\n\u0012\u0006\b\u0001\u0012\u00020^0O\"\u00020^H\u0002¢\u0006\u0002\u0010_JF\u0010`\u001a\b\u0012\u0004\u0012\u00020a0\n2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020c0\n2\f\u0010d\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020a0\n2\f\u0010f\u001a\b\u0012\u0004\u0012\u00020a0\nH\u0007J\u0010\u0010g\u001a\u00020a2\u0006\u0010#\u001a\u00020$H\u0002J\u0016\u0010h\u001a\b\u0012\u0004\u0012\u00020a0\n2\u0006\u0010#\u001a\u00020$H\u0002J\u0018\u0010i\u001a\u00020E2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u001e\u0010j\u001a\b\u0012\u0004\u0012\u00020E0\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u0018\u0010k\u001a\u00020E2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u001e\u0010l\u001a\b\u0012\u0004\u0012\u00020E0\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002JA\u0010:\u001a\u00020m2\u0006\u0010n\u001a\u00020\u000b2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010@\u001a\u00020A2\b\u0010o\u001a\u0004\u0018\u00010p2\b\u0010q\u001a\u0004\u0018\u0001052\u0006\u0010/\u001a\u000200H\u0016¢\u0006\u0002\u0010rJH\u0010s\u001a\u00020$2\u0006\u0010!\u001a\u00020\"2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\n2\u0006\u00106\u001a\u0002072\u0006\u0010t\u001a\u00020\u000b2\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010u\u001a\u000205H\u0002J8\u0010v\u001a\u00020$2\u0006\u0010!\u001a\u00020\"2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\n2\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010u\u001a\u000205H\u0002J4\u0010w\u001a\b\u0012\u0004\u0012\u00020a0\n2\u0006\u0010!\u001a\u00020\"2\u0006\u00106\u001a\u0002072\f\u0010d\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010u\u001a\u000205H\u0002J4\u0010x\u001a\b\u0012\u0004\u0012\u00020a0\n2\u0006\u0010!\u001a\u00020\"2\u0006\u00106\u001a\u0002072\f\u0010b\u001a\b\u0012\u0004\u0012\u00020c0\n2\u0006\u0010u\u001a\u000205H\u0002J8\u0010y\u001a\u00020$2\u0006\u0010!\u001a\u00020\"2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\n2\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010u\u001a\u000205H\u0002J@\u0010z\u001a\u00020$2\u0006\u0010!\u001a\u00020\"2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\n2\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010u\u001a\u0002052\u0006\u0010/\u001a\u000200H\u0002JX\u0010{\u001a\u00020$2\u0006\u0010!\u001a\u00020\"2\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\n2\u0006\u00106\u001a\u0002072\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010t\u001a\u00020\u000b2\b\u00109\u001a\u0004\u0018\u00010\u000b2\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010u\u001a\u000205H\u0002JA\u0010|\u001a\b\u0012\u0004\u0012\u00020a0\n2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020c0\n2\f\u0010d\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010Y\u001a\u00020Z2\b\u0010q\u001a\u0004\u0018\u000105H\u0016¢\u0006\u0002\u0010}JB\u0010~\u001a\u00020\u007f2\u0006\u0010n\u001a\u00020\u000b2\u0006\u0010Y\u001a\u00020Z2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0006\u0010@\u001a\u00020A2\b\u0010q\u001a\u0004\u0018\u0001052\u0006\u0010/\u001a\u000200H\u0016¢\u0006\u0003\u0010\u0082\u0001J\u0019\u0010\u0083\u0001\u001a\u00020?2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u001f\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020?0\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\u0014\u0010\u0012\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\rR\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\rR\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\rR\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\rR\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0086\u0001"}, d2 = {"Lcom/amazon/music/find/data/database/SqliteLibrarySearchDao;", "Lcom/amazon/music/find/data/database/LibrarySearchDao;", "cirrusDatabaseProvider", "Lcom/amazon/music/find/data/database/CirrusDatabaseProvider;", "entityFactory", "Lcom/amazon/music/find/providers/SearchEntityFactory;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Lcom/amazon/music/find/data/database/CirrusDatabaseProvider;Lcom/amazon/music/find/providers/SearchEntityFactory;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "ALBUM_SEARCH_COLUMNS", "", "", "getALBUM_SEARCH_COLUMNS", "()Ljava/util/List;", "ALBUM_TABLE_COL", "getALBUM_TABLE_COL", "ARTIST_SEARCH_COLUMNS", "getARTIST_SEARCH_COLUMNS", "MAX_NON_PREVIOUS_QUERY", "getMAX_NON_PREVIOUS_QUERY", "()Ljava/lang/String;", "PRIME_PLAYLIST_SEARCH_COLUMNS", "getPRIME_PLAYLIST_SEARCH_COLUMNS", "TRACKS_LOCALURI_TABLE_COL", "getTRACKS_LOCALURI_TABLE_COL", "TRACK_OWNERSHIP_COLUMNS", "getTRACK_OWNERSHIP_COLUMNS", "TRACK_SEARCH_COLUMNS", "getTRACK_SEARCH_COLUMNS", "USER_PLAYLIST_SEARCH_COLUMNS", "getUSER_PLAYLIST_SEARCH_COLUMNS", "albumFromCursor", "Lcom/amazon/music/find/model/search/AlbumSearchItem;", "librarySource", "Lcom/amazon/music/find/model/search/SearchLibrarySource;", "c", "Landroid/database/Cursor;", "albumsFromCursor", "appendDownloadedState", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "columnName", "downloadedState", "Lcom/amazon/music/find/model/search/SearchDownloadedState;", "appendFollowSource", "searchFollowSource", "Lcom/amazon/music/find/model/search/SearchFollowSource;", "artistFromCursor", "Lcom/amazon/music/find/model/search/ArtistSearchItem;", "artistsFromCursor", "countOf", "", "database", "Landroid/database/sqlite/SQLiteDatabase;", "searchColumns", "groupByColumn", SearchIntents.EXTRA_QUERY, "escapeLikeChars", "filterAlbums", "albums", "tracks", "Lcom/amazon/music/find/model/search/TrackSearchItem;", "searchFilterSelections", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "filterArtists", "artists", "filterPlaylists", "Lcom/amazon/music/find/model/search/PlaylistSearchItem;", "playlists", "filterTracks", "generateLikeExpression", "columns", "getAlbums", "maxNumOfAlbums", "getArtists", "maxNumOfArtists", "getLocalTrackUrisColumnNames", "", "Lcom/amazon/music/find/data/database/CirrusLocalTrackUrisColumns;", "([Lcom/amazon/music/find/data/database/CirrusLocalTrackUrisColumns;)Ljava/util/List;", "getPlaylists", "userPlaylistLibrarySource", "userPlaylistDownloadedState", "primePlaylistLibrarySource", "primePlaylistDownloadedState", "maxNumOfPlaylists", "getSearchLibrarySource", "isOffline", "", "getTracks", "maxNumOfTracks", "getTracksColumnNames", "Lcom/amazon/music/find/data/database/CirrusTracksColumns;", "([Lcom/amazon/music/find/data/database/CirrusTracksColumns;)Ljava/util/List;", "mergeOwnershipResults", "Lcom/amazon/music/find/model/search/ContentOwnershipItem;", "trackIds", "", "trackAsins", "itemsByTrackIds", "itemsByTrackAsins", "ownershipItemFromCursor", "ownershipItemsFromCursor", "playlistFromCursor", "playlistsFromCursor", "primePlaylistFromCursor", "primePlaylistsFromCursor", "Lcom/amazon/music/find/model/search/GenericSearchResponse;", "searchTerm", "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "pageSize", "(Ljava/lang/String;ZLcom/amazon/music/find/model/filter/SearchFilterSelections;Lcom/amazon/music/search/SpellCorrections;Ljava/lang/Integer;Lcom/amazon/music/find/model/search/SearchFollowSource;)Lcom/amazon/music/find/model/search/GenericSearchResponse;", "queryAlbums", "orderByColumn", "limit", "queryArtists", "queryContentOwnershipItemsByAsins", "queryContentOwnershipItemsByIds", "queryPlaylists", "queryPrimePlaylists", "queryTracks", "requestState", "(Ljava/util/List;Ljava/util/List;ZLjava/lang/Integer;)Ljava/util/List;", PageUriUtils.DO_SEE_MORE_VALUE, "Lcom/amazon/music/find/model/search/SeeMoreResponse;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/find/common/EntityType;", "(Ljava/lang/String;ZLcom/amazon/music/platform/find/common/EntityType;Lcom/amazon/music/find/model/filter/SearchFilterSelections;Ljava/lang/Integer;Lcom/amazon/music/find/model/search/SearchFollowSource;)Lcom/amazon/music/find/model/search/SeeMoreResponse;", "trackFromCursor", "tracksFromCursor", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SqliteLibrarySearchDao implements LibrarySearchDao {
    public static final String ARTIST_ALBUM_COUNT = "artist_album_count";
    public static final String ARTIST_TRACK_COUNT = "artist_track_count";
    public static final String CONTENT_CATALOG_STATUS_MAX = "content_catalog_status_max";
    public static final String CONTENT_CATALOG_STATUS_MAX_NON_PREVIOUS = "content_catalog_status_max_np";
    public static final String CONTENT_CATALOG_STATUS_MIN = "content_catalog_status_min";
    public static final String CONTENT_OWNERSHIP_STATUS_MAX = "content_ownership_status_max";
    public static final String CONTENT_OWNERSHIP_STATUS_MIN = "content_ownership_status_min";
    public static final int DEFAULT_QUERY_RESULT_LIMIT = 200;
    public static final String PLAYLIST_TRACK_COUNT = "playlist_track_count";
    private static final String TAG = "SPL";
    private final CirrusDatabaseProvider cirrusDatabaseProvider;
    private final SearchEntityFactory entityFactory;
    private final SearchFeaturesProvider searchFeaturesProvider;
    private static final Logger logger = LoggerFactory.getLogger(SqliteLibrarySearchDao.class.getName());
    private static final Regex asinRegex = new Regex("[a-zA-Z0-9_-]+");

    /* JADX INFO: compiled from: SqliteLibrarySearchDao.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EntityType.values().length];
            iArr[EntityType.TRACK.ordinal()] = 1;
            iArr[EntityType.ALBUM.ordinal()] = 2;
            iArr[EntityType.ARTIST.ordinal()] = 3;
            iArr[EntityType.PLAYLIST.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SqliteLibrarySearchDao(CirrusDatabaseProvider cirrusDatabaseProvider, SearchEntityFactory entityFactory, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(cirrusDatabaseProvider, "cirrusDatabaseProvider");
        Intrinsics.checkNotNullParameter(entityFactory, "entityFactory");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.cirrusDatabaseProvider = cirrusDatabaseProvider;
        this.entityFactory = entityFactory;
        this.searchFeaturesProvider = searchFeaturesProvider;
    }

    private final List<String> getTRACKS_LOCALURI_TABLE_COL() {
        return CollectionsKt___CollectionsKt.plus((Collection) getTracksColumnNames(CirrusTracksColumns._ID, CirrusTracksColumns.LUID, CirrusTracksColumns.TITLE, CirrusTracksColumns.ALBUM, CirrusTracksColumns.ARTIST, CirrusTracksColumns.ALBUM_ASIN, CirrusTracksColumns.ARTIST_ASIN, CirrusTracksColumns.ASIN, CirrusTracksColumns.ALBUM_ID, CirrusTracksColumns.ARTIST_ID, CirrusTracksColumns.CONTENT_CATALOG_STATUS, CirrusTracksColumns.LYRICS_STATE, CirrusTracksColumns.DOWNLOAD_STATE, CirrusTracksColumns.CONTENT_OWNERSHIP_STATUS, CirrusTracksColumns.DURATION, CirrusTracksColumns.TRACK_NUM, CirrusTracksColumns.DISC_NUM, CirrusTracksColumns.SIZE, CirrusTracksColumns.IS_EXPLICIT, CirrusTracksColumns.CONTENT_ENCODING), (Iterable) getLocalTrackUrisColumnNames(CirrusLocalTrackUrisColumns.LOCAL_URI));
    }

    private final String getMAX_NON_PREVIOUS_QUERY() {
        StringBuilder sb = new StringBuilder();
        sb.append("MAX(");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("CASE WHEN (");
        CirrusDatabaseProvider cirrusDatabaseProvider = this.cirrusDatabaseProvider;
        CirrusTracksColumns cirrusTracksColumns = CirrusTracksColumns.CONTENT_CATALOG_STATUS;
        sb2.append(cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns));
        sb2.append(" < ");
        sb2.append(this.cirrusDatabaseProvider.getContentCatalogStatusPrevCatalogOrNotBoundary());
        sb2.append(") ");
        sb.append(sb2.toString());
        sb.append(Intrinsics.stringPlus("THEN ", this.cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns)));
        sb.append(Intrinsics.stringPlus(" ELSE ", Integer.valueOf(this.cirrusDatabaseProvider.convertContentCatalogStatusToInt(SearchContentCatalogStatus.UNKNOWN))));
        sb.append(" END)");
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "sb.toString()");
        return string;
    }

    private final List<String> getALBUM_TABLE_COL() {
        List<String> tracksColumnNames = getTracksColumnNames(CirrusTracksColumns.ALBUM, CirrusTracksColumns.ARTIST, CirrusTracksColumns.ALBUM_ASIN, CirrusTracksColumns.ARTIST_ASIN, CirrusTracksColumns.ALBUM_ID);
        StringBuilder sb = new StringBuilder();
        sb.append("min(");
        CirrusDatabaseProvider cirrusDatabaseProvider = this.cirrusDatabaseProvider;
        CirrusTracksColumns cirrusTracksColumns = CirrusTracksColumns.DOWNLOAD_STATE;
        sb.append(cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns));
        sb.append(") as ");
        sb.append(this.cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns));
        StringBuilder sb2 = new StringBuilder();
        sb2.append("min(");
        CirrusDatabaseProvider cirrusDatabaseProvider2 = this.cirrusDatabaseProvider;
        CirrusTracksColumns cirrusTracksColumns2 = CirrusTracksColumns.CONTENT_CATALOG_STATUS;
        sb2.append(cirrusDatabaseProvider2.getTracksColumnName(cirrusTracksColumns2));
        sb2.append(") as content_catalog_status_min");
        StringBuilder sb3 = new StringBuilder();
        sb3.append("min(");
        CirrusDatabaseProvider cirrusDatabaseProvider3 = this.cirrusDatabaseProvider;
        CirrusTracksColumns cirrusTracksColumns3 = CirrusTracksColumns.CONTENT_OWNERSHIP_STATUS;
        sb3.append(cirrusDatabaseProvider3.getTracksColumnName(cirrusTracksColumns3));
        sb3.append(") as content_ownership_status_min");
        return CollectionsKt___CollectionsKt.plus((Collection) tracksColumnNames, (Iterable) CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{sb.toString(), sb2.toString(), sb3.toString(), Intrinsics.stringPlus(getMAX_NON_PREVIOUS_QUERY(), " as content_catalog_status_max_np"), "max(" + this.cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns2) + ") as content_catalog_status_max", "max(" + this.cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns3) + ") as content_ownership_status_max"}));
    }

    private final List<String> getTRACK_SEARCH_COLUMNS() {
        return getTracksColumnNames(CirrusTracksColumns.TITLE, CirrusTracksColumns.ALBUM, CirrusTracksColumns.ARTIST, CirrusTracksColumns.SORT_TITLE, CirrusTracksColumns.SORT_ALBUM, CirrusTracksColumns.SORT_ARTIST, CirrusTracksColumns.GENRE);
    }

    private final List<String> getTRACK_OWNERSHIP_COLUMNS() {
        return getTracksColumnNames(CirrusTracksColumns._ID, CirrusTracksColumns.ASIN, CirrusTracksColumns.CONTENT_OWNERSHIP_STATUS);
    }

    private final List<String> getARTIST_SEARCH_COLUMNS() {
        return getTracksColumnNames(CirrusTracksColumns.ARTIST, CirrusTracksColumns.SORT_ARTIST, CirrusTracksColumns.GENRE);
    }

    private final List<String> getPRIME_PLAYLIST_SEARCH_COLUMNS() {
        return CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{Intrinsics.stringPlus("tracks.", this.cirrusDatabaseProvider.getPrimePlaylistTracksColumnName(CirrusPrimePlaylistTracksColumns.TITLE)), Intrinsics.stringPlus("tracks.", this.cirrusDatabaseProvider.getPrimePlaylistTracksColumnName(CirrusPrimePlaylistTracksColumns.ARTIST)), Intrinsics.stringPlus("tracks.", this.cirrusDatabaseProvider.getPrimePlaylistTracksColumnName(CirrusPrimePlaylistTracksColumns.ALBUM)), Intrinsics.stringPlus("tracks.", this.cirrusDatabaseProvider.getPrimePlaylistTracksColumnName(CirrusPrimePlaylistTracksColumns.SORT_TITLE))});
    }

    private final List<String> getALBUM_SEARCH_COLUMNS() {
        return getTracksColumnNames(CirrusTracksColumns.ALBUM, CirrusTracksColumns.ARTIST, CirrusTracksColumns.SORT_ALBUM, CirrusTracksColumns.SORT_ARTIST, CirrusTracksColumns.GENRE);
    }

    private final List<String> getUSER_PLAYLIST_SEARCH_COLUMNS() {
        return CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{Intrinsics.stringPlus("tracks.", this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.TITLE)), Intrinsics.stringPlus("tracks.", this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ARTIST)), Intrinsics.stringPlus("tracks.", this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ALBUM)), Intrinsics.stringPlus("tracks.", this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.SORT_TITLE)), Intrinsics.stringPlus("tracks.", this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.SORT_ARTIST)), Intrinsics.stringPlus("tracks.", this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.SORT_ALBUM))});
    }

    @Override // com.amazon.music.find.data.database.LibrarySearchDao
    public GenericSearchResponse query(String searchTerm, boolean isOffline, SearchFilterSelections searchFilterSelections, SpellCorrections spellCorrections, Integer pageSize, SearchFollowSource searchFollowSource) {
        Intrinsics.checkNotNullParameter(searchTerm, "searchTerm");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        Intrinsics.checkNotNullParameter(searchFollowSource, "searchFollowSource");
        GenericSearchResponse genericSearchResponse = new GenericSearchResponse();
        String executedTerm = SpellCorrectionsUtil.INSTANCE.getExecutedTerm(searchTerm, spellCorrections);
        genericSearchResponse.setOriginalQuery(searchTerm);
        genericSearchResponse.setSpellCorrections(spellCorrections);
        genericSearchResponse.setExecutedTerm(executedTerm);
        String strEscapeLikeChars = escapeLikeChars(executedTerm);
        SQLiteDatabase readOnlyDatabase = this.cirrusDatabaseProvider.getReadOnlyDatabase();
        SearchLibrarySource searchLibrarySource = getSearchLibrarySource(isOffline);
        SearchLibrarySource searchLibrarySource2 = SearchLibrarySource.CLOUD;
        List<? extends SearchDownloadedState> listListOf = isOffline ? CollectionsKt__CollectionsJVMKt.listOf(SearchDownloadedState.DOWNLOADED) : null;
        List<? extends SearchDownloadedState> listListOf2 = isOffline ? CollectionsKt__CollectionsKt.listOf((Object[]) new SearchDownloadedState[]{SearchDownloadedState.DOWNLOADED, SearchDownloadedState.DOWNLOADING}) : null;
        int iIntValue = pageSize == null ? 200 : pageSize.intValue();
        List<? extends SearchDownloadedState> list = listListOf;
        List<TrackSearchItem> tracks = getTracks(searchLibrarySource, list, readOnlyDatabase, strEscapeLikeChars, iIntValue);
        genericSearchResponse.setTracks(filterTracks(tracks, searchFilterSelections));
        genericSearchResponse.setAlbums(filterAlbums(getAlbums(searchLibrarySource, list, readOnlyDatabase, strEscapeLikeChars, iIntValue), tracks, searchFilterSelections));
        genericSearchResponse.setArtists(filterArtists(getArtists(searchLibrarySource, list, readOnlyDatabase, strEscapeLikeChars, iIntValue), searchFilterSelections));
        genericSearchResponse.setPlaylists(filterPlaylists(getPlaylists(searchLibrarySource, list, searchLibrarySource2, listListOf2, readOnlyDatabase, strEscapeLikeChars, iIntValue, searchFollowSource), searchFilterSelections));
        genericSearchResponse.setSectionOrdering(CollectionsKt__CollectionsKt.listOf((Object[]) new SectionType[]{SectionType.SPELL_CORRECTION_SECTION, SectionType.ARTISTS_SECTION, SectionType.ALBUMS_SECTION, SectionType.TRACKS_SECTION, SectionType.PLAYLISTS_SECTION}));
        return genericSearchResponse;
    }

    @Override // com.amazon.music.find.data.database.LibrarySearchDao
    public SeeMoreResponse seeMore(String searchTerm, boolean isOffline, EntityType entityType, SearchFilterSelections searchFilterSelections, Integer pageSize, SearchFollowSource searchFollowSource) {
        SeeMoreResponse seeMoreResponse;
        Intrinsics.checkNotNullParameter(searchTerm, "searchTerm");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        Intrinsics.checkNotNullParameter(searchFilterSelections, "searchFilterSelections");
        Intrinsics.checkNotNullParameter(searchFollowSource, "searchFollowSource");
        SearchLibrarySource searchLibrarySource = getSearchLibrarySource(isOffline);
        SearchLibrarySource searchLibrarySource2 = SearchLibrarySource.CLOUD;
        List<? extends SearchDownloadedState> listListOf = isOffline ? CollectionsKt__CollectionsJVMKt.listOf(SearchDownloadedState.DOWNLOADED) : null;
        List<? extends SearchDownloadedState> listListOf2 = isOffline ? CollectionsKt__CollectionsKt.listOf((Object[]) new SearchDownloadedState[]{SearchDownloadedState.DOWNLOADED, SearchDownloadedState.DOWNLOADING}) : null;
        String strEscapeLikeChars = escapeLikeChars(searchTerm);
        SQLiteDatabase readOnlyDatabase = this.cirrusDatabaseProvider.getReadOnlyDatabase();
        int iIntValue = pageSize == null ? 200 : pageSize.intValue();
        List<TrackSearchItem> tracks = getTracks(searchLibrarySource, listListOf, readOnlyDatabase, strEscapeLikeChars, iIntValue);
        List<TrackSearchItem> listFilterTracks = filterTracks(tracks, searchFilterSelections);
        int i = WhenMappings.$EnumSwitchMapping$0[entityType.ordinal()];
        if (i == 1) {
            seeMoreResponse = new SeeMoreResponse(SectionType.TRACKS_SECTION, null, listFilterTracks);
        } else if (i == 2) {
            seeMoreResponse = new SeeMoreResponse(SectionType.ALBUMS_SECTION, null, filterAlbums(getAlbums(searchLibrarySource, listListOf, readOnlyDatabase, strEscapeLikeChars, iIntValue), tracks, searchFilterSelections));
        } else if (i == 3) {
            seeMoreResponse = new SeeMoreResponse(SectionType.ARTISTS_SECTION, null, filterArtists(getArtists(searchLibrarySource, listListOf, readOnlyDatabase, strEscapeLikeChars, iIntValue), searchFilterSelections));
        } else if (i == 4) {
            seeMoreResponse = new SeeMoreResponse(SectionType.PLAYLISTS_SECTION, null, filterPlaylists(getPlaylists(searchLibrarySource, listListOf, searchLibrarySource2, listListOf2, readOnlyDatabase, strEscapeLikeChars, iIntValue, searchFollowSource), searchFilterSelections));
        } else {
            return new SeeMoreResponse(null, null, null, 7, null);
        }
        return seeMoreResponse;
    }

    @Override // com.amazon.music.find.data.database.LibrarySearchDao
    public List<ContentOwnershipItem> requestState(List<Long> trackIds, List<String> trackAsins, boolean isOffline, Integer pageSize) {
        Intrinsics.checkNotNullParameter(trackIds, "trackIds");
        Intrinsics.checkNotNullParameter(trackAsins, "trackAsins");
        SQLiteDatabase readOnlyDatabase = this.cirrusDatabaseProvider.getReadOnlyDatabase();
        SearchLibrarySource searchLibrarySource = getSearchLibrarySource(isOffline);
        int iIntValue = pageSize == null ? 200 : pageSize.intValue();
        return mergeOwnershipResults(trackIds, trackAsins, queryContentOwnershipItemsByIds(searchLibrarySource, readOnlyDatabase, trackIds, iIntValue), queryContentOwnershipItemsByAsins(searchLibrarySource, readOnlyDatabase, trackAsins, iIntValue));
    }

    @VisibleForTesting(otherwise = 2)
    public final List<ContentOwnershipItem> mergeOwnershipResults(List<Long> trackIds, List<String> trackAsins, List<ContentOwnershipItem> itemsByTrackIds, List<ContentOwnershipItem> itemsByTrackAsins) {
        Intrinsics.checkNotNullParameter(trackIds, "trackIds");
        Intrinsics.checkNotNullParameter(trackAsins, "trackAsins");
        Intrinsics.checkNotNullParameter(itemsByTrackIds, "itemsByTrackIds");
        Intrinsics.checkNotNullParameter(itemsByTrackAsins, "itemsByTrackAsins");
        int iConvertContentOwnershipStatusToInt = this.cirrusDatabaseProvider.convertContentOwnershipStatusToInt(SearchContentOwnershipStatus.NOT_IN_LIBRARY);
        ArrayList arrayList = new ArrayList();
        if (itemsByTrackIds.size() < trackIds.size()) {
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it = itemsByTrackIds.iterator();
            while (it.hasNext()) {
                Long id = ((ContentOwnershipItem) it.next()).getId();
                if (id != null) {
                    arrayList2.add(id);
                }
            }
            Set set = CollectionsKt___CollectionsKt.toSet(arrayList2);
            ArrayList arrayList3 = new ArrayList();
            for (Object obj : trackIds) {
                if (!set.contains(Long.valueOf(((Number) obj).longValue()))) {
                    arrayList3.add(obj);
                }
            }
            ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList3, 10));
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                arrayList4.add(new ContentOwnershipItem(null, Long.valueOf(((Number) it2.next()).longValue()), Integer.valueOf(iConvertContentOwnershipStatusToInt)));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, arrayList4);
        }
        if (itemsByTrackAsins.size() < trackAsins.size()) {
            ArrayList arrayList5 = new ArrayList();
            Iterator<T> it3 = itemsByTrackAsins.iterator();
            while (it3.hasNext()) {
                String asin = ((ContentOwnershipItem) it3.next()).getAsin();
                if (asin != null) {
                    arrayList5.add(asin);
                }
            }
            Set set2 = CollectionsKt___CollectionsKt.toSet(arrayList5);
            ArrayList arrayList6 = new ArrayList();
            for (Object obj2 : trackAsins) {
                if (!set2.contains((String) obj2)) {
                    arrayList6.add(obj2);
                }
            }
            ArrayList arrayList7 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList6, 10));
            Iterator it4 = arrayList6.iterator();
            while (it4.hasNext()) {
                arrayList7.add(new ContentOwnershipItem((String) it4.next(), null, Integer.valueOf(iConvertContentOwnershipStatusToInt)));
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, arrayList7);
        }
        List listPlus = CollectionsKt___CollectionsKt.plus((Collection) itemsByTrackIds, (Iterable) itemsByTrackAsins);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(listPlus, 10)), 16));
        for (Object obj3 : listPlus) {
            linkedHashMap.put(((ContentOwnershipItem) obj3).getId(), obj3);
        }
        return CollectionsKt___CollectionsKt.plus((Collection) CollectionsKt___CollectionsKt.toList(linkedHashMap.values()), (Iterable) arrayList);
    }

    private final SearchLibrarySource getSearchLibrarySource(boolean isOffline) {
        return isOffline ? SearchLibrarySource.DEVICE : SearchLibrarySource.CLOUD;
    }

    private final List<TrackSearchItem> getTracks(SearchLibrarySource librarySource, List<? extends SearchDownloadedState> downloadedState, SQLiteDatabase database, String query, int maxNumOfTracks) {
        if (countOf(librarySource, downloadedState, database, getTRACK_SEARCH_COLUMNS(), null, query) > 0) {
            return tracksFromCursor(librarySource, queryTracks(librarySource, downloadedState, database, getTRACK_SEARCH_COLUMNS(), this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.TITLE), null, query, maxNumOfTracks));
        }
        return CollectionsKt__CollectionsKt.emptyList();
    }

    private final List<ArtistSearchItem> getArtists(SearchLibrarySource librarySource, List<? extends SearchDownloadedState> downloadedState, SQLiteDatabase database, String query, int maxNumOfArtists) {
        if (countOf(librarySource, downloadedState, database, getARTIST_SEARCH_COLUMNS(), this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ARTIST_ID), query) > 0) {
            return artistsFromCursor(librarySource, queryArtists(librarySource, downloadedState, database, query, maxNumOfArtists));
        }
        return CollectionsKt__CollectionsKt.emptyList();
    }

    private final List<AlbumSearchItem> getAlbums(SearchLibrarySource librarySource, List<? extends SearchDownloadedState> downloadedState, SQLiteDatabase database, String query, int maxNumOfAlbums) {
        List<String> album_search_columns = getALBUM_SEARCH_COLUMNS();
        CirrusDatabaseProvider cirrusDatabaseProvider = this.cirrusDatabaseProvider;
        CirrusTracksColumns cirrusTracksColumns = CirrusTracksColumns.ALBUM;
        if (countOf(librarySource, downloadedState, database, album_search_columns, cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns), query) > 0) {
            return albumsFromCursor(librarySource, queryAlbums(librarySource, downloadedState, database, this.cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns), this.cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns), query, maxNumOfAlbums));
        }
        return CollectionsKt__CollectionsKt.emptyList();
    }

    private final List<PlaylistSearchItem> getPlaylists(SearchLibrarySource userPlaylistLibrarySource, List<? extends SearchDownloadedState> userPlaylistDownloadedState, SearchLibrarySource primePlaylistLibrarySource, List<? extends SearchDownloadedState> primePlaylistDownloadedState, SQLiteDatabase database, String query, int maxNumOfPlaylists, SearchFollowSource searchFollowSource) {
        List<PlaylistSearchItem> listPlaylistsFromCursor = playlistsFromCursor(userPlaylistLibrarySource, queryPlaylists(userPlaylistLibrarySource, userPlaylistDownloadedState, database, query, maxNumOfPlaylists));
        List<PlaylistSearchItem> listPrimePlaylistsFromCursor = primePlaylistsFromCursor(primePlaylistLibrarySource, queryPrimePlaylists(primePlaylistLibrarySource, primePlaylistDownloadedState, database, query, maxNumOfPlaylists, searchFollowSource));
        if (listPlaylistsFromCursor.size() + listPrimePlaylistsFromCursor.size() > 0) {
            List listPlus = CollectionsKt___CollectionsKt.plus((Collection) listPlaylistsFromCursor, (Iterable) listPrimePlaylistsFromCursor);
            ArrayList arrayList = new ArrayList();
            Iterator it = listPlus.iterator();
            while (true) {
                if (it.hasNext()) {
                    Object next = it.next();
                    if (((PlaylistSearchItem) next).getTitle() != null) {
                        arrayList.add(next);
                    }
                } else {
                    final Comparator case_insensitive_order = StringsKt__StringsJVMKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE);
                    List listSortedWith = CollectionsKt___CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.amazon.music.find.data.database.SqliteLibrarySearchDao$getPlaylists$$inlined$compareBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            Comparator comparator = case_insensitive_order;
                            String title = ((PlaylistSearchItem) t).getTitle();
                            Intrinsics.checkNotNull(title);
                            String title2 = ((PlaylistSearchItem) t2).getTitle();
                            Intrinsics.checkNotNull(title2);
                            return comparator.compare(title, title2);
                        }
                    });
                    return listSortedWith.subList(0, Math.min(listSortedWith.size(), maxNumOfPlaylists));
                }
            }
        } else {
            return CollectionsKt__CollectionsKt.emptyList();
        }
    }

    private final String escapeLikeChars(String query) {
        return StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(StringsKt__StringsJVMKt.replace$default(query, "'", "''", false, 4, (Object) null), "%", "[%]", false, 4, (Object) null), Attributes.PREDEFINED_ATTRIBUTE_PREFIX, "[_]", false, 4, (Object) null);
    }

    private final int countOf(SearchLibrarySource librarySource, List<? extends SearchDownloadedState> downloadedState, SQLiteDatabase database, List<String> searchColumns, String groupByColumn, String query) {
        String strStringPlus = groupByColumn != null ? Intrinsics.stringPlus("DISTINCT ", groupByColumn) : "*";
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(" + strStringPlus + ") FROM ");
        sb.append(this.cirrusDatabaseProvider.getTableName(CirrusTable.TRACKS));
        sb.append(" WHERE ");
        sb.append(generateLikeExpression(searchColumns, query));
        sb.append(" AND ");
        sb.append(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.SOURCE) + '=' + this.cirrusDatabaseProvider.convertLibrarySourceToInt(librarySource));
        appendDownloadedState(sb, this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.DOWNLOAD_STATE), downloadedState);
        sb.append(" AND ");
        sb.append(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.CONTENT_OWNERSHIP_STATUS) + "!=" + this.cirrusDatabaseProvider.convertContentOwnershipStatusToInt(SearchContentOwnershipStatus.NOT_IN_LIBRARY));
        Cursor cursorRawQuery = database.rawQuery(sb.toString(), null);
        try {
            cursorRawQuery.moveToFirst();
            int i = cursorRawQuery.getInt(0);
            CloseableKt.closeFinally(cursorRawQuery, null);
            return i;
        } finally {
        }
    }

    private final Cursor queryTracks(SearchLibrarySource librarySource, List<? extends SearchDownloadedState> downloadedState, SQLiteDatabase database, List<String> searchColumns, String orderByColumn, String groupByColumn, String query, int limit) {
        String strStringPlus = Intrinsics.stringPlus(orderByColumn, " ASC");
        String str = this.cirrusDatabaseProvider.getTableName(CirrusTable.TRACKS) + " LEFT JOIN " + this.cirrusDatabaseProvider.getTableName(CirrusTable.LOCAL_TRACK_URIS) + " ON " + this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.LUID) + " = " + this.cirrusDatabaseProvider.getLocalTrackUrisColumnName(CirrusLocalTrackUrisColumns.TRACK_LUID);
        StringBuilder sb = new StringBuilder();
        sb.append(generateLikeExpression(searchColumns, query));
        sb.append(" AND ");
        sb.append(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.SOURCE) + '=' + this.cirrusDatabaseProvider.convertLibrarySourceToInt(librarySource));
        appendDownloadedState(sb, this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.DOWNLOAD_STATE), downloadedState);
        sb.append(" AND ");
        sb.append(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.CONTENT_OWNERSHIP_STATUS) + "!=" + this.cirrusDatabaseProvider.convertContentOwnershipStatusToInt(SearchContentOwnershipStatus.NOT_IN_LIBRARY));
        Object[] array = getTRACKS_LOCALURI_TABLE_COL().toArray(new String[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        Cursor cursorQuery = database.query(true, str, (String[]) array, sb.toString(), null, groupByColumn, null, strStringPlus, limit > 0 ? String.valueOf(limit) : null);
        Intrinsics.checkNotNullExpressionValue(cursorQuery, "database.query(\n        …se null\n                )");
        return cursorQuery;
    }

    private final Cursor queryArtists(SearchLibrarySource librarySource, List<? extends SearchDownloadedState> downloadedState, SQLiteDatabase database, String query, int limit) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT *, ");
        sb.append("COUNT(DISTINCT " + this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns._ID) + ") AS artist_track_count");
        sb.append(", COUNT(DISTINCT " + this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ALBUM_ID) + ") AS artist_album_count");
        sb.append(Intrinsics.stringPlus(" FROM ", this.cirrusDatabaseProvider.getTableName(CirrusTable.TRACKS)));
        sb.append(Intrinsics.stringPlus(" WHERE ", generateLikeExpression(getARTIST_SEARCH_COLUMNS(), query)));
        sb.append(" AND ");
        sb.append(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.SOURCE) + '=' + this.cirrusDatabaseProvider.convertLibrarySourceToInt(librarySource));
        appendDownloadedState(sb, this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.DOWNLOAD_STATE), downloadedState);
        sb.append(" AND ");
        sb.append(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.CONTENT_OWNERSHIP_STATUS) + "!=" + this.cirrusDatabaseProvider.convertContentOwnershipStatusToInt(SearchContentOwnershipStatus.NOT_IN_LIBRARY));
        sb.append(Intrinsics.stringPlus(" GROUP BY ", getTracksColumnNames(CirrusTracksColumns.ARTIST_ID)));
        sb.append(" ORDER BY " + getTracksColumnNames(CirrusTracksColumns.ARTIST) + " ASC");
        if (limit > 0) {
            sb.append(Intrinsics.stringPlus("  LIMIT ", Integer.valueOf(limit)));
        }
        Cursor cursorRawQuery = database.rawQuery(sb.toString(), null);
        Intrinsics.checkNotNullExpressionValue(cursorRawQuery, "database.rawQuery(sb.toString(), null)");
        return cursorRawQuery;
    }

    private final Cursor queryAlbums(SearchLibrarySource librarySource, List<? extends SearchDownloadedState> downloadedState, SQLiteDatabase database, String orderByColumn, String groupByColumn, String query, int limit) {
        String strStringPlus = Intrinsics.stringPlus(orderByColumn, " ASC");
        String tableName = this.cirrusDatabaseProvider.getTableName(CirrusTable.TRACKS);
        StringBuilder sb = new StringBuilder();
        sb.append(generateLikeExpression(getALBUM_SEARCH_COLUMNS(), query));
        sb.append(" AND ");
        sb.append(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.SOURCE) + '=' + this.cirrusDatabaseProvider.convertLibrarySourceToInt(librarySource));
        appendDownloadedState(sb, this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.DOWNLOAD_STATE), downloadedState);
        sb.append(" AND ");
        sb.append(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.CONTENT_OWNERSHIP_STATUS) + "!=" + this.cirrusDatabaseProvider.convertContentOwnershipStatusToInt(SearchContentOwnershipStatus.NOT_IN_LIBRARY));
        Object[] array = getALBUM_TABLE_COL().toArray(new String[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        Cursor cursorQuery = database.query(true, tableName, (String[]) array, sb.toString(), null, groupByColumn, null, strStringPlus, limit > 0 ? String.valueOf(limit) : null);
        Intrinsics.checkNotNullExpressionValue(cursorQuery, "database.query(\n        …ing() else null\n        )");
        return cursorQuery;
    }

    private final Cursor queryPlaylists(SearchLibrarySource librarySource, List<? extends SearchDownloadedState> downloadedState, SQLiteDatabase database, String query, int limit) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT pl.*, count(*) AS playlist_track_count");
        sb.append(Intrinsics.stringPlus(" FROM ", this.cirrusDatabaseProvider.getTableName(CirrusTable.PLAYLISTS)));
        sb.append(Intrinsics.stringPlus(" pl INNER JOIN ", this.cirrusDatabaseProvider.getTableName(CirrusTable.PLAYLISTS_TRACKS)));
        CirrusDatabaseProvider cirrusDatabaseProvider = this.cirrusDatabaseProvider;
        CirrusPlaylistsColumns cirrusPlaylistsColumns = CirrusPlaylistsColumns._ID;
        sb.append(Intrinsics.stringPlus(" plt ON pl.", cirrusDatabaseProvider.getPlaylistsColumnName(cirrusPlaylistsColumns)));
        sb.append(Intrinsics.stringPlus(" == plt.", this.cirrusDatabaseProvider.getPlaylistTracksColumnName(CirrusPlaylistTracksColumns.PLAYLIST_ID)));
        sb.append(Intrinsics.stringPlus(" LEFT JOIN ", this.cirrusDatabaseProvider.getTableName(CirrusTable.TRACKS)));
        sb.append(Intrinsics.stringPlus(" tracks ON tracks.", this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.LUID)));
        sb.append(Intrinsics.stringPlus(" == plt.", this.cirrusDatabaseProvider.getPlaylistTracksColumnName(CirrusPlaylistTracksColumns.TRACK_LUID)));
        CirrusDatabaseProvider cirrusDatabaseProvider2 = this.cirrusDatabaseProvider;
        CirrusPlaylistsColumns cirrusPlaylistsColumns2 = CirrusPlaylistsColumns.NAME;
        sb.append(Intrinsics.stringPlus(" WHERE (pl.", cirrusDatabaseProvider2.getPlaylistsColumnName(cirrusPlaylistsColumns2)));
        sb.append(" LIKE '%" + query + "%'");
        sb.append(" OR " + ((Object) generateLikeExpression(getUSER_PLAYLIST_SEARCH_COLUMNS(), query)) + ')');
        if (!FindFeatureGating.USER_PLAYLIST_FOR_OFFLINE_SEARCH_ENABLED.isEnabled()) {
            sb.append(Intrinsics.stringPlus(" AND pl.", this.cirrusDatabaseProvider.getPlaylistsColumnName(CirrusPlaylistsColumns.SOURCE)));
            sb.append(Intrinsics.stringPlus(" = ", Integer.valueOf(this.cirrusDatabaseProvider.convertLibrarySourceToInt(librarySource))));
        }
        appendDownloadedState(sb, Intrinsics.stringPlus("pl.", this.cirrusDatabaseProvider.getPlaylistsColumnName(CirrusPlaylistsColumns.DOWNLOAD_STATE)), downloadedState);
        sb.append(Intrinsics.stringPlus(" GROUP BY pl.", this.cirrusDatabaseProvider.getPlaylistsColumnName(cirrusPlaylistsColumns)));
        sb.append(" ORDER BY pl." + this.cirrusDatabaseProvider.getPlaylistsColumnName(cirrusPlaylistsColumns2) + " ASC");
        if (limit > 0) {
            sb.append(Intrinsics.stringPlus("  LIMIT ", Integer.valueOf(limit)));
        }
        Cursor cursorRawQuery = database.rawQuery(sb.toString(), null);
        Intrinsics.checkNotNullExpressionValue(cursorRawQuery, "database.rawQuery(sb.toString(), null)");
        return cursorRawQuery;
    }

    private final Cursor queryPrimePlaylists(SearchLibrarySource librarySource, List<? extends SearchDownloadedState> downloadedState, SQLiteDatabase database, String query, int limit, SearchFollowSource searchFollowSource) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT pl.*, count(*) AS playlist_track_count");
        sb.append(Intrinsics.stringPlus(" FROM ", this.cirrusDatabaseProvider.getTableName(CirrusTable.PRIME_PLAYLISTS)));
        sb.append(Intrinsics.stringPlus(" pl INNER JOIN ", this.cirrusDatabaseProvider.getTableName(CirrusTable.PRIME_PLAYLIST_TO_TRACK)));
        CirrusDatabaseProvider cirrusDatabaseProvider = this.cirrusDatabaseProvider;
        CirrusPrimePlaylistsColumns cirrusPrimePlaylistsColumns = CirrusPrimePlaylistsColumns._ID;
        sb.append(Intrinsics.stringPlus(" plt ON pl.", cirrusDatabaseProvider.getPrimePlaylistsColumnName(cirrusPrimePlaylistsColumns)));
        sb.append(Intrinsics.stringPlus(" == plt.", this.cirrusDatabaseProvider.getPrimePlaylistToTrackColumnName(CirrusPrimePlaylistToTrackColumns.PLAYLIST_ID)));
        sb.append(Intrinsics.stringPlus(" LEFT JOIN ", this.cirrusDatabaseProvider.getTableName(CirrusTable.PRIME_PLAYLISTS_TRACKS)));
        sb.append(Intrinsics.stringPlus(" tracks ON tracks.", this.cirrusDatabaseProvider.getPrimePlaylistTracksColumnName(CirrusPrimePlaylistTracksColumns.ASIN)));
        sb.append(Intrinsics.stringPlus(" == plt.", this.cirrusDatabaseProvider.getPrimePlaylistToTrackColumnName(CirrusPrimePlaylistToTrackColumns.TRACK_ASIN)));
        CirrusDatabaseProvider cirrusDatabaseProvider2 = this.cirrusDatabaseProvider;
        CirrusPrimePlaylistsColumns cirrusPrimePlaylistsColumns2 = CirrusPrimePlaylistsColumns.TITLE;
        sb.append(Intrinsics.stringPlus(" WHERE (pl.", cirrusDatabaseProvider2.getPrimePlaylistsColumnName(cirrusPrimePlaylistsColumns2)));
        sb.append(" LIKE '%" + query + "%'");
        sb.append(" OR " + ((Object) generateLikeExpression(getPRIME_PLAYLIST_SEARCH_COLUMNS(), query)) + ')');
        sb.append(Intrinsics.stringPlus(" AND pl.", this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns.SOURCE)));
        sb.append(Intrinsics.stringPlus(" = ", Integer.valueOf(this.cirrusDatabaseProvider.convertLibrarySourceToInt(librarySource))));
        appendFollowSource(sb, this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns.IS_FOLLOWING), searchFollowSource);
        appendDownloadedState(sb, Intrinsics.stringPlus("pl.", this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns.DOWNLOAD_STATE)), downloadedState);
        sb.append(Intrinsics.stringPlus(" GROUP BY pl.", this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(cirrusPrimePlaylistsColumns)));
        sb.append(" ORDER BY pl." + this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(cirrusPrimePlaylistsColumns2) + " ASC");
        if (limit > 0) {
            sb.append(Intrinsics.stringPlus("  LIMIT ", Integer.valueOf(limit)));
        }
        Cursor cursorRawQuery = database.rawQuery(sb.toString(), null);
        Intrinsics.checkNotNullExpressionValue(cursorRawQuery, "database.rawQuery(sb.toString(), null)");
        return cursorRawQuery;
    }

    public final String generateLikeExpression(List<String> columns, String query) {
        Intrinsics.checkNotNullParameter(columns, "columns");
        Intrinsics.checkNotNullParameter(query, "query");
        List<String> listSplit$default = StringsKt__StringsKt.split$default((CharSequence) query, new String[]{" "}, false, 0, 6, (Object) null);
        StringBuilder sb = new StringBuilder("(");
        int i = 0;
        for (String str : listSplit$default) {
            int i2 = i + 1;
            if (i > 0) {
                sb.append(")");
                sb.append(" AND ");
            }
            if (listSplit$default.size() > 1) {
                sb.append("(");
            }
            int i3 = 0;
            for (String str2 : columns) {
                int i4 = i3 + 1;
                if (i3 > 0) {
                    sb.append(" OR ");
                }
                sb.append(str2);
                sb.append(" LIKE '");
                sb.append(str);
                sb.append("%'");
                sb.append(" OR ");
                sb.append(str2);
                sb.append(" LIKE '% ");
                sb.append(str);
                sb.append("%'");
                i3 = i4;
            }
            i = i2;
        }
        sb.append(")");
        if (listSplit$default.size() > 1) {
            sb.append(")");
        }
        return sb.toString();
    }

    private final void appendFollowSource(StringBuilder sb, String columnName, SearchFollowSource searchFollowSource) {
        if ((searchFollowSource != SearchFollowSource.ALL ? searchFollowSource : null) == null) {
            return;
        }
        sb.append(" AND pl." + columnName + " = " + searchFollowSource.getValue());
    }

    private final void appendDownloadedState(StringBuilder sb, String columnName, List<? extends SearchDownloadedState> downloadedState) {
        if (downloadedState == null) {
            return;
        }
        sb.append(" AND ");
        sb.append(columnName + " IN (" + CollectionsKt___CollectionsKt.joinToString$default(downloadedState, null, null, null, 0, null, new Function1<SearchDownloadedState, CharSequence>() { // from class: com.amazon.music.find.data.database.SqliteLibrarySearchDao$appendDownloadedState$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(SearchDownloadedState it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return String.valueOf(this.this$0.cirrusDatabaseProvider.convertDownloadedStateToInt(it));
            }
        }, 31, null) + ')');
    }

    private final List<TrackSearchItem> tracksFromCursor(SearchLibrarySource librarySource, Cursor c) {
        ArrayList arrayList = new ArrayList();
        while (c.moveToNext()) {
            try {
                arrayList.add(trackFromCursor(librarySource, c));
            } finally {
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(c, null);
        return arrayList;
    }

    private final TrackSearchItem trackFromCursor(SearchLibrarySource librarySource, Cursor c) {
        int i;
        TrackSearchItem trackSearchItemCreateTrackSearchItem = this.entityFactory.createTrackSearchItem();
        String string = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ASIN)));
        String string2 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.LUID)));
        String string3 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ARTIST)));
        String string4 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ARTIST_ASIN)));
        String string5 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.TITLE)));
        String string6 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ALBUM)));
        String string7 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ALBUM_ASIN)));
        long j = c.getLong(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ALBUM_ID)));
        long j2 = c.getLong(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns._ID)));
        int i2 = c.getInt(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.CONTENT_CATALOG_STATUS)));
        int i3 = c.getInt(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.DURATION)));
        int i4 = c.getInt(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.TRACK_NUM)));
        int i5 = c.getInt(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.DISC_NUM)));
        int i6 = c.getInt(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.SIZE)));
        String string8 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.LYRICS_STATE)));
        String string9 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getLocalTrackUrisColumnName(CirrusLocalTrackUrisColumns.LOCAL_URI)));
        int i7 = c.getInt(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.CONTENT_OWNERSHIP_STATUS)));
        int i8 = c.getInt(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.DOWNLOAD_STATE)));
        trackSearchItemCreateTrackSearchItem.setTitle(string5);
        trackSearchItemCreateTrackSearchItem.setAsin(string);
        PlaymodeEligibility playmodeEligibility = null;
        trackSearchItemCreateTrackSearchItem.setArtworkUri(null);
        trackSearchItemCreateTrackSearchItem.setArtist(string3);
        trackSearchItemCreateTrackSearchItem.setArtistAsin(string4);
        trackSearchItemCreateTrackSearchItem.setAlbum(string6);
        trackSearchItemCreateTrackSearchItem.setAlbumAsin(string7);
        trackSearchItemCreateTrackSearchItem.setArtist(string3);
        trackSearchItemCreateTrackSearchItem.setArtworkId(Long.valueOf(j));
        trackSearchItemCreateTrackSearchItem.setId(Long.valueOf(j2));
        trackSearchItemCreateTrackSearchItem.setLuid(string2);
        trackSearchItemCreateTrackSearchItem.setDuration(Integer.valueOf(i3));
        trackSearchItemCreateTrackSearchItem.setTrackNum(i4);
        trackSearchItemCreateTrackSearchItem.setDiscNum(i5);
        trackSearchItemCreateTrackSearchItem.setSize(i6);
        trackSearchItemCreateTrackSearchItem.setLocalUri(string9);
        if (this.searchFeaturesProvider.shouldRequestPlaymodeEligibility(EntityType.TRACK)) {
            i = i7;
            playmodeEligibility = new PlaymodeEligibility(Boolean.valueOf(this.cirrusDatabaseProvider.isOwned(i)), Boolean.TRUE, null);
        } else {
            i = i7;
        }
        trackSearchItemCreateTrackSearchItem.setPlaymodeEligibility(playmodeEligibility);
        trackSearchItemCreateTrackSearchItem.setCatalogStatus(i2);
        trackSearchItemCreateTrackSearchItem.setContentAccessSet(i2);
        trackSearchItemCreateTrackSearchItem.setOwnershipStatus(i);
        trackSearchItemCreateTrackSearchItem.setLyricsState(string8);
        trackSearchItemCreateTrackSearchItem.setSourceAndDownloadState(this.cirrusDatabaseProvider.convertLibrarySourceToMediaSource(librarySource), string9, i8);
        int columnIndex = c.getColumnIndex(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.IS_EXPLICIT));
        if (columnIndex != -1) {
            trackSearchItemCreateTrackSearchItem.setExplicit(1 == c.getInt(columnIndex));
        }
        int columnIndex2 = c.getColumnIndex(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.CONTENT_ENCODING));
        if (columnIndex2 != -1) {
            trackSearchItemCreateTrackSearchItem.setContentEncoding(this.cirrusDatabaseProvider.convertStringToContentEncoding(c.getString(columnIndex2)));
        }
        return trackSearchItemCreateTrackSearchItem;
    }

    private final List<ArtistSearchItem> artistsFromCursor(SearchLibrarySource librarySource, Cursor c) {
        ArrayList arrayList = new ArrayList();
        while (c.moveToNext()) {
            try {
                arrayList.add(artistFromCursor(librarySource, c));
            } finally {
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(c, null);
        return arrayList;
    }

    private final ArtistSearchItem artistFromCursor(SearchLibrarySource librarySource, Cursor c) {
        ArtistSearchItem artistSearchItemCreateArtistSearchItem = this.entityFactory.createArtistSearchItem();
        String string = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ARTIST)));
        String string2 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ARTIST_ASIN)));
        long j = c.getLong(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ARTIST_ID)));
        int i = c.getInt(c.getColumnIndexOrThrow(ARTIST_TRACK_COUNT));
        int i2 = c.getInt(c.getColumnIndexOrThrow(ARTIST_ALBUM_COUNT));
        int iConvertContentOwnershipStatusToInt = this.cirrusDatabaseProvider.convertContentOwnershipStatusToInt(SearchContentOwnershipStatus.OWNED);
        artistSearchItemCreateArtistSearchItem.setTitle(string);
        artistSearchItemCreateArtistSearchItem.setAsin(string2);
        artistSearchItemCreateArtistSearchItem.setContributorAsin(string2);
        artistSearchItemCreateArtistSearchItem.setTrackCount(i);
        artistSearchItemCreateArtistSearchItem.setAlbumCount(i2);
        artistSearchItemCreateArtistSearchItem.setArtworkUri(null);
        artistSearchItemCreateArtistSearchItem.setArtworkId(Long.valueOf(j));
        artistSearchItemCreateArtistSearchItem.setId(Long.valueOf(j));
        artistSearchItemCreateArtistSearchItem.setSource(this.cirrusDatabaseProvider.convertLibrarySourceToMediaSource(librarySource));
        artistSearchItemCreateArtistSearchItem.setCatalogStatus(true);
        artistSearchItemCreateArtistSearchItem.setOwnershipStatus(iConvertContentOwnershipStatusToInt);
        return artistSearchItemCreateArtistSearchItem;
    }

    private final List<AlbumSearchItem> albumsFromCursor(SearchLibrarySource librarySource, Cursor c) {
        ArrayList arrayList = new ArrayList();
        while (c.moveToNext()) {
            try {
                arrayList.add(albumFromCursor(librarySource, c));
            } finally {
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(c, null);
        return arrayList;
    }

    private final AlbumSearchItem albumFromCursor(SearchLibrarySource librarySource, Cursor c) {
        AlbumSearchItem albumSearchItemCreateAlbumSearchItem = this.entityFactory.createAlbumSearchItem();
        String string = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ALBUM)));
        String string2 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ALBUM_ASIN)));
        String string3 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ARTIST)));
        String string4 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ARTIST_ASIN)));
        long j = c.getLong(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ALBUM_ID)));
        int i = c.getInt(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.DOWNLOAD_STATE)));
        int i2 = c.getInt(c.getColumnIndexOrThrow(CONTENT_CATALOG_STATUS_MIN));
        int i3 = c.getInt(c.getColumnIndexOrThrow(CONTENT_CATALOG_STATUS_MAX));
        int i4 = c.getInt(c.getColumnIndexOrThrow(CONTENT_OWNERSHIP_STATUS_MIN));
        int i5 = c.getInt(c.getColumnIndexOrThrow(CONTENT_OWNERSHIP_STATUS_MAX));
        int i6 = c.getInt(c.getColumnIndexOrThrow(CONTENT_CATALOG_STATUS_MAX_NON_PREVIOUS));
        albumSearchItemCreateAlbumSearchItem.setTitle(string);
        albumSearchItemCreateAlbumSearchItem.setAsin(string2);
        albumSearchItemCreateAlbumSearchItem.setArtworkUri(null);
        albumSearchItemCreateAlbumSearchItem.setArtist(string3);
        albumSearchItemCreateAlbumSearchItem.setArtistAsin(string4);
        albumSearchItemCreateAlbumSearchItem.setArtworkId(Long.valueOf(j));
        albumSearchItemCreateAlbumSearchItem.setId(Long.valueOf(j));
        albumSearchItemCreateAlbumSearchItem.setSource(this.cirrusDatabaseProvider.convertLibrarySourceToMediaSource(librarySource));
        albumSearchItemCreateAlbumSearchItem.setDownloadState(i);
        albumSearchItemCreateAlbumSearchItem.setCatalogStatuses(i2, i3, i6);
        albumSearchItemCreateAlbumSearchItem.setOwnershipStatuses(i4, i5);
        albumSearchItemCreateAlbumSearchItem.setContentAccessSet(i2);
        return albumSearchItemCreateAlbumSearchItem;
    }

    private final List<PlaylistSearchItem> playlistsFromCursor(SearchLibrarySource librarySource, Cursor c) {
        ArrayList arrayList = new ArrayList();
        while (c.moveToNext()) {
            try {
                arrayList.add(playlistFromCursor(librarySource, c));
            } finally {
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(c, null);
        return arrayList;
    }

    private final PlaylistSearchItem playlistFromCursor(SearchLibrarySource librarySource, Cursor c) {
        PlaylistSearchItem playlistSearchItemCreatePlaylistSearchItem = this.entityFactory.createPlaylistSearchItem();
        String string = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPlaylistsColumnName(CirrusPlaylistsColumns.NAME)));
        String string2 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPlaylistsColumnName(CirrusPlaylistsColumns.DESCRIPTION)));
        long j = c.getLong(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPlaylistsColumnName(CirrusPlaylistsColumns._ID)));
        int i = c.getInt(c.getColumnIndexOrThrow(PLAYLIST_TRACK_COUNT));
        String string3 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPlaylistsColumnName(CirrusPlaylistsColumns.THUMBNAIL_ART_URL)));
        String string4 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPlaylistsColumnName(CirrusPlaylistsColumns.LUID)));
        logger.debug("SPL::playlistFromCursor. title=" + ((Object) string) + ", id=" + j + ", luid=" + ((Object) string4));
        int columnIndex = c.getColumnIndex(this.cirrusDatabaseProvider.getPlaylistsColumnName(CirrusPlaylistsColumns.IS_NIGHTWING));
        int columnIndex2 = c.getColumnIndex(this.cirrusDatabaseProvider.getPlaylistsColumnName(CirrusPlaylistsColumns.IS_NIGHTWING_ON_DEMAND));
        int columnIndex3 = c.getColumnIndex(this.cirrusDatabaseProvider.getPlaylistsColumnName(CirrusPlaylistsColumns.IS_PRIME));
        int columnIndex4 = c.getColumnIndex(this.cirrusDatabaseProvider.getPlaylistsColumnName(CirrusPlaylistsColumns.IS_SONIC_RUSH));
        playlistSearchItemCreatePlaylistSearchItem.setTitle(string);
        playlistSearchItemCreatePlaylistSearchItem.setAsin(null);
        playlistSearchItemCreatePlaylistSearchItem.setArtworkUri(string3);
        playlistSearchItemCreatePlaylistSearchItem.setDescription(string2);
        playlistSearchItemCreatePlaylistSearchItem.setRating(0.0f);
        playlistSearchItemCreatePlaylistSearchItem.setNumReviews(0);
        playlistSearchItemCreatePlaylistSearchItem.setTrackCount(i);
        playlistSearchItemCreatePlaylistSearchItem.setArtworkId(Long.valueOf(j));
        playlistSearchItemCreatePlaylistSearchItem.setId(Long.valueOf(j));
        playlistSearchItemCreatePlaylistSearchItem.setSource(this.cirrusDatabaseProvider.convertLibrarySourceToMediaSource(librarySource));
        playlistSearchItemCreatePlaylistSearchItem.setOwnership(true);
        playlistSearchItemCreatePlaylistSearchItem.setPlaylistId(string4);
        playlistSearchItemCreatePlaylistSearchItem.setPlaylistType(PlaylistType.USER_PLAYLIST);
        if (columnIndex3 < 0) {
            playlistSearchItemCreatePlaylistSearchItem.setCatalogStatus(this.cirrusDatabaseProvider.convertContentCatalogStatusToInt(SearchContentCatalogStatus.NON_CATALOG));
        } else {
            boolean z = c.getInt(columnIndex) == this.cirrusDatabaseProvider.getTrueValue();
            boolean z2 = c.getInt(columnIndex2) == this.cirrusDatabaseProvider.getTrueValue();
            boolean z3 = c.getInt(columnIndex3) == this.cirrusDatabaseProvider.getTrueValue();
            boolean z4 = c.getInt(columnIndex4) == this.cirrusDatabaseProvider.getTrueValue();
            playlistSearchItemCreatePlaylistSearchItem.setCatalogStatus(z3, z4, (z3 || z4 || !this.searchFeaturesProvider.isUserInHawkfireMarketplace()) ? false : true, z, z2);
        }
        return playlistSearchItemCreatePlaylistSearchItem;
    }

    private final List<PlaylistSearchItem> primePlaylistsFromCursor(SearchLibrarySource librarySource, Cursor c) {
        ArrayList arrayList = new ArrayList();
        while (c.moveToNext()) {
            try {
                arrayList.add(primePlaylistFromCursor(librarySource, c));
            } finally {
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(c, null);
        return arrayList;
    }

    private final PlaylistSearchItem primePlaylistFromCursor(SearchLibrarySource librarySource, Cursor c) {
        PlaylistSearchItem playlistSearchItemCreatePlaylistSearchItem = this.entityFactory.createPlaylistSearchItem();
        String string = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns.TITLE)));
        String string2 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns.ASIN)));
        String string3 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns.THUMBNAIL_ART_URL)));
        String string4 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns.DESCRIPTION)));
        int i = c.getInt(c.getColumnIndexOrThrow(PLAYLIST_TRACK_COUNT));
        CirrusDatabaseProvider cirrusDatabaseProvider = this.cirrusDatabaseProvider;
        CirrusPrimePlaylistsColumns cirrusPrimePlaylistsColumns = CirrusPrimePlaylistsColumns.IS_NIGHTWING;
        String nightwingString = c.getString(c.getColumnIndexOrThrow(cirrusDatabaseProvider.getPrimePlaylistsColumnName(cirrusPrimePlaylistsColumns)));
        Intrinsics.checkNotNullExpressionValue(nightwingString, "nightwingString");
        boolean z = Integer.parseInt(nightwingString) == this.cirrusDatabaseProvider.getTrueValue();
        String nightwingOnDemandString = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(cirrusPrimePlaylistsColumns)));
        Intrinsics.checkNotNullExpressionValue(nightwingOnDemandString, "nightwingOnDemandString");
        boolean z2 = Integer.parseInt(nightwingOnDemandString) == this.cirrusDatabaseProvider.getTrueValue();
        String primeString = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns.IS_PRIME)));
        Intrinsics.checkNotNullExpressionValue(primeString, "primeString");
        boolean z3 = Integer.parseInt(primeString) == this.cirrusDatabaseProvider.getTrueValue();
        String sonicRushString = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns.IS_SONIC_RUSH)));
        Intrinsics.checkNotNullExpressionValue(sonicRushString, "sonicRushString");
        boolean z4 = Integer.parseInt(sonicRushString) == this.cirrusDatabaseProvider.getTrueValue();
        String isSharedUserPlaylistString = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns.IS_SHARED_USER_PLAYLIST)));
        Intrinsics.checkNotNullExpressionValue(isSharedUserPlaylistString, "isSharedUserPlaylistString");
        boolean z5 = Integer.parseInt(isSharedUserPlaylistString) == this.cirrusDatabaseProvider.getTrueValue();
        String string5 = c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns.LUID)));
        boolean z6 = z2;
        boolean z7 = z;
        long j = c.getLong(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns._ID)));
        logger.debug("SPL::primePlaylistFromCursor. title=" + ((Object) string) + ", id=" + j + ", asin=" + ((Object) string2) + ", isSharedUserPlaylist=" + z5 + ", luid=" + ((Object) string5));
        boolean z8 = (z3 || z4 || !this.searchFeaturesProvider.isUserInHawkfireMarketplace()) ? false : true;
        playlistSearchItemCreatePlaylistSearchItem.setTitle(string);
        playlistSearchItemCreatePlaylistSearchItem.setAsin(!z5 ? string2 : null);
        playlistSearchItemCreatePlaylistSearchItem.setArtworkUri(string3);
        playlistSearchItemCreatePlaylistSearchItem.setDescription(string4);
        playlistSearchItemCreatePlaylistSearchItem.setRating(0.0f);
        playlistSearchItemCreatePlaylistSearchItem.setNumReviews(0);
        playlistSearchItemCreatePlaylistSearchItem.setTrackCount(i);
        playlistSearchItemCreatePlaylistSearchItem.setSource(this.cirrusDatabaseProvider.convertLibrarySourceToMediaSource(librarySource));
        playlistSearchItemCreatePlaylistSearchItem.setSharedUserPlaylist(z5);
        playlistSearchItemCreatePlaylistSearchItem.setOwnership(!z5);
        if (librarySource != SearchLibrarySource.CLOUD) {
            string2 = string5;
        }
        playlistSearchItemCreatePlaylistSearchItem.setPlaylistId(string2);
        playlistSearchItemCreatePlaylistSearchItem.setPlaylistType(z5 ? PlaylistType.SHARED_USER_PLAYLIST : PlaylistType.PRIME_PLAYLIST);
        playlistSearchItemCreatePlaylistSearchItem.setId(Long.valueOf(j));
        playlistSearchItemCreatePlaylistSearchItem.setCatalogStatus(z3, z4, z8, z7, z6);
        return playlistSearchItemCreatePlaylistSearchItem;
    }

    private final List<ContentOwnershipItem> queryContentOwnershipItemsByIds(SearchLibrarySource librarySource, SQLiteDatabase database, List<Long> trackIds, int limit) {
        if (trackIds.isEmpty()) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Intrinsics.stringPlus("SELECT ", CollectionsKt___CollectionsKt.joinToString$default(getTRACK_OWNERSHIP_COLUMNS(), ", ", null, null, 0, null, null, 62, null)));
        sb.append(Intrinsics.stringPlus(" FROM ", this.cirrusDatabaseProvider.getTableName(CirrusTable.TRACKS)));
        sb.append(" WHERE ");
        String strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt___CollectionsKt.sorted(trackIds), ", ", "(", ")", 0, null, null, 56, null);
        StringBuilder sb2 = new StringBuilder();
        CirrusDatabaseProvider cirrusDatabaseProvider = this.cirrusDatabaseProvider;
        CirrusTracksColumns cirrusTracksColumns = CirrusTracksColumns._ID;
        sb2.append(cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns));
        sb2.append(" IN ");
        sb2.append(strJoinToString$default);
        sb.append(sb2.toString());
        sb.append(Intrinsics.stringPlus(" GROUP BY ", this.cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns)));
        sb.append(" ORDER BY " + this.cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns) + " ASC");
        sb.append(Intrinsics.stringPlus(" LIMIT ", Integer.valueOf(limit)));
        Cursor c = database.rawQuery(sb.toString(), null);
        Intrinsics.checkNotNullExpressionValue(c, "c");
        return ownershipItemsFromCursor(c);
    }

    private final List<ContentOwnershipItem> queryContentOwnershipItemsByAsins(SearchLibrarySource librarySource, SQLiteDatabase database, List<String> trackAsins, int limit) {
        if (trackAsins.isEmpty()) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Intrinsics.stringPlus("SELECT ", CollectionsKt___CollectionsKt.joinToString$default(getTRACK_OWNERSHIP_COLUMNS(), ", ", null, null, 0, null, null, 62, null)));
        sb.append(Intrinsics.stringPlus(" FROM ", this.cirrusDatabaseProvider.getTableName(CirrusTable.TRACKS)));
        sb.append(" WHERE ");
        ArrayList arrayList = new ArrayList();
        for (Object obj : trackAsins) {
            if (asinRegex.matches((String) obj)) {
                arrayList.add(obj);
            }
        }
        sb.append(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ASIN) + " IN " + CollectionsKt___CollectionsKt.joinToString$default(CollectionsKt___CollectionsKt.sorted(arrayList), ", ", "(", ")", 0, null, new Function1<String, CharSequence>() { // from class: com.amazon.music.find.data.database.SqliteLibrarySearchDao$queryContentOwnershipItemsByAsins$asinList$2
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(String it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return '\'' + it + '\'';
            }
        }, 24, null));
        sb.append(" AND ");
        sb.append(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.PLAYLIST_TRACK_STATUS) + '=' + SearchPlaylistTrackStatus.NOT_CATALOG_TRACK.getValue());
        CirrusDatabaseProvider cirrusDatabaseProvider = this.cirrusDatabaseProvider;
        CirrusTracksColumns cirrusTracksColumns = CirrusTracksColumns._ID;
        sb.append(Intrinsics.stringPlus(" GROUP BY ", cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns)));
        sb.append(" ORDER BY " + this.cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns) + " ASC");
        sb.append(Intrinsics.stringPlus(" LIMIT ", Integer.valueOf(limit)));
        Cursor c = database.rawQuery(sb.toString(), null);
        Intrinsics.checkNotNullExpressionValue(c, "c");
        return ownershipItemsFromCursor(c);
    }

    private final List<ContentOwnershipItem> ownershipItemsFromCursor(Cursor c) {
        ArrayList arrayList = new ArrayList();
        while (c.moveToNext()) {
            try {
                arrayList.add(ownershipItemFromCursor(c));
            } finally {
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(c, null);
        return CollectionsKt___CollectionsKt.toList(arrayList);
    }

    private final ContentOwnershipItem ownershipItemFromCursor(Cursor c) {
        long j = c.getLong(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns._ID)));
        return new ContentOwnershipItem(c.getString(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.ASIN))), Long.valueOf(j), Integer.valueOf(c.getInt(c.getColumnIndexOrThrow(this.cirrusDatabaseProvider.getTracksColumnName(CirrusTracksColumns.CONTENT_OWNERSHIP_STATUS)))));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<TrackSearchItem> filterTracks(List<? extends TrackSearchItem> tracks, SearchFilterSelections searchFilterSelections) {
        if (!this.searchFeaturesProvider.getAreQualityFiltersEnabled()) {
            return tracks;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : tracks) {
            if (SearchFilterUtil.INSTANCE.trackMatchesAudioQualities((TrackSearchItem) obj, searchFilterSelections)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<AlbumSearchItem> filterAlbums(List<? extends AlbumSearchItem> albums, List<? extends TrackSearchItem> tracks, SearchFilterSelections searchFilterSelections) {
        if (!this.searchFeaturesProvider.getAreQualityFiltersEnabled()) {
            return albums;
        }
        for (AlbumSearchItem albumSearchItem : albums) {
            SearchFilterUtil searchFilterUtil = SearchFilterUtil.INSTANCE;
            ArrayList arrayList = new ArrayList();
            for (Object obj : tracks) {
                if (StringsKt__StringsJVMKt.equals$default(((TrackSearchItem) obj).getAlbum(), albumSearchItem.getTitle(), false, 2, null)) {
                    arrayList.add(obj);
                }
            }
            albumSearchItem.setContentEncoding(searchFilterUtil.getCollectionContentEncodings(arrayList));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : albums) {
            if (SearchFilterUtil.INSTANCE.albumMatchesAudioQualities((AlbumSearchItem) obj2, searchFilterSelections)) {
                arrayList2.add(obj2);
            }
        }
        return arrayList2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<ArtistSearchItem> filterArtists(List<? extends ArtistSearchItem> artists, SearchFilterSelections searchFilterSelections) {
        if (!this.searchFeaturesProvider.getAreQualityFiltersEnabled()) {
            return artists;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : artists) {
            if (SearchFilterUtil.INSTANCE.artistMatchesAudioQualities((ArtistSearchItem) obj, searchFilterSelections)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<PlaylistSearchItem> filterPlaylists(List<? extends PlaylistSearchItem> playlists, SearchFilterSelections searchFilterSelections) {
        if (!this.searchFeaturesProvider.getAreQualityFiltersEnabled()) {
            return playlists;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : playlists) {
            if (SearchFilterUtil.INSTANCE.playlistMatchesAudioQualities((PlaylistSearchItem) obj, searchFilterSelections)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private final List<String> getLocalTrackUrisColumnNames(CirrusLocalTrackUrisColumns... columns) {
        ArrayList arrayList = new ArrayList(columns.length);
        int length = columns.length;
        int i = 0;
        while (i < length) {
            CirrusLocalTrackUrisColumns cirrusLocalTrackUrisColumns = columns[i];
            i++;
            arrayList.add(this.cirrusDatabaseProvider.getLocalTrackUrisColumnName(cirrusLocalTrackUrisColumns));
        }
        return arrayList;
    }

    private final List<String> getTracksColumnNames(CirrusTracksColumns... columns) {
        ArrayList arrayList = new ArrayList(columns.length);
        int length = columns.length;
        int i = 0;
        while (i < length) {
            CirrusTracksColumns cirrusTracksColumns = columns[i];
            i++;
            arrayList.add(this.cirrusDatabaseProvider.getTracksColumnName(cirrusTracksColumns));
        }
        return arrayList;
    }
}
