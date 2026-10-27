package com.amazon.music.find.data.database;

import android.database.sqlite.SQLiteDatabase;
import androidx.core.app.NotificationCompat;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.model.search.SearchContentCatalogStatus;
import com.amazon.music.find.model.search.SearchContentOwnershipStatus;
import com.amazon.music.find.model.search.SearchDownloadedState;
import com.amazon.music.find.model.search.SearchLibrarySource;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: CirrusDatabaseProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\fH&J\u001a\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u000eH&J\b\u0010\u0013\u001a\u00020\u0003H&J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0018H&J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u001aH&J\u0010\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u001cH&J\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u001eH&J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020 H&J\b\u0010!\u001a\u00020\"H&J\u0010\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%H&J\u0010\u0010&\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020'H&J\b\u0010(\u001a\u00020\u0003H&J\u0010\u0010)\u001a\u00020*2\u0006\u0010\u0004\u001a\u00020\u0003H&¨\u0006+"}, d2 = {"Lcom/amazon/music/find/data/database/CirrusDatabaseProvider;", "", "convertContentCatalogStatusToInt", "", NotificationCompat.CATEGORY_STATUS, "Lcom/amazon/music/find/model/search/SearchContentCatalogStatus;", "convertContentOwnershipStatusToInt", "Lcom/amazon/music/find/model/search/SearchContentOwnershipStatus;", "convertDownloadedStateToInt", PageUriUtils.SOURCE_QUERY_PARAM, "Lcom/amazon/music/find/model/search/SearchDownloadedState;", "convertLibrarySourceToInt", "Lcom/amazon/music/find/model/search/SearchLibrarySource;", "convertLibrarySourceToMediaSource", "", "convertStringToContentEncoding", "", "Lcom/amazon/music/contentEncoding/ContentEncoding;", ContextMappingConstants.CONTENT_ENCODING, "getContentCatalogStatusPrevCatalogOrNotBoundary", "getLocalTrackUrisColumnName", "column", "Lcom/amazon/music/find/data/database/CirrusLocalTrackUrisColumns;", "getPlaylistTracksColumnName", "Lcom/amazon/music/find/data/database/CirrusPlaylistTracksColumns;", "getPlaylistsColumnName", "Lcom/amazon/music/find/data/database/CirrusPlaylistsColumns;", "getPrimePlaylistToTrackColumnName", "Lcom/amazon/music/find/data/database/CirrusPrimePlaylistToTrackColumns;", "getPrimePlaylistTracksColumnName", "Lcom/amazon/music/find/data/database/CirrusPrimePlaylistTracksColumns;", "getPrimePlaylistsColumnName", "Lcom/amazon/music/find/data/database/CirrusPrimePlaylistsColumns;", "getReadOnlyDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "getTableName", "table", "Lcom/amazon/music/find/data/database/CirrusTable;", "getTracksColumnName", "Lcom/amazon/music/find/data/database/CirrusTracksColumns;", "getTrueValue", "isOwned", "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface CirrusDatabaseProvider {
    int convertContentCatalogStatusToInt(SearchContentCatalogStatus status);

    int convertContentOwnershipStatusToInt(SearchContentOwnershipStatus status);

    int convertDownloadedStateToInt(SearchDownloadedState source);

    int convertLibrarySourceToInt(SearchLibrarySource source);

    String convertLibrarySourceToMediaSource(SearchLibrarySource source);

    List<ContentEncoding> convertStringToContentEncoding(String contentEncoding);

    int getContentCatalogStatusPrevCatalogOrNotBoundary();

    String getLocalTrackUrisColumnName(CirrusLocalTrackUrisColumns column);

    String getPlaylistTracksColumnName(CirrusPlaylistTracksColumns column);

    String getPlaylistsColumnName(CirrusPlaylistsColumns column);

    String getPrimePlaylistToTrackColumnName(CirrusPrimePlaylistToTrackColumns column);

    String getPrimePlaylistTracksColumnName(CirrusPrimePlaylistTracksColumns column);

    String getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns column);

    SQLiteDatabase getReadOnlyDatabase();

    String getTableName(CirrusTable table);

    String getTracksColumnName(CirrusTracksColumns column);

    int getTrueValue();

    boolean isOwned(int status);
}
