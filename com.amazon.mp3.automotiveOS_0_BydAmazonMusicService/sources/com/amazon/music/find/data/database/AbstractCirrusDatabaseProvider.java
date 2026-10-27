package com.amazon.music.find.data.database;

import com.amazon.music.find.model.search.SearchDownloadedState;
import com.amazon.music.find.utils.PageUriUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AbstractCirrusDatabaseProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0019H\u0016¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/find/data/database/AbstractCirrusDatabaseProvider;", "Lcom/amazon/music/find/data/database/CirrusDatabaseProvider;", "()V", "convertDownloadedStateToInt", "", PageUriUtils.SOURCE_QUERY_PARAM, "Lcom/amazon/music/find/model/search/SearchDownloadedState;", "getLocalTrackUrisColumnName", "", "column", "Lcom/amazon/music/find/data/database/CirrusLocalTrackUrisColumns;", "getPlaylistTracksColumnName", "Lcom/amazon/music/find/data/database/CirrusPlaylistTracksColumns;", "getPlaylistsColumnName", "Lcom/amazon/music/find/data/database/CirrusPlaylistsColumns;", "getPrimePlaylistToTrackColumnName", "Lcom/amazon/music/find/data/database/CirrusPrimePlaylistToTrackColumns;", "getPrimePlaylistTracksColumnName", "Lcom/amazon/music/find/data/database/CirrusPrimePlaylistTracksColumns;", "getPrimePlaylistsColumnName", "Lcom/amazon/music/find/data/database/CirrusPrimePlaylistsColumns;", "getTableName", "table", "Lcom/amazon/music/find/data/database/CirrusTable;", "getTracksColumnName", "Lcom/amazon/music/find/data/database/CirrusTracksColumns;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AbstractCirrusDatabaseProvider implements CirrusDatabaseProvider {
    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public String getTableName(CirrusTable table) {
        Intrinsics.checkNotNullParameter(table, "table");
        return table.getValue();
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public String getTracksColumnName(CirrusTracksColumns column) {
        Intrinsics.checkNotNullParameter(column, "column");
        return column.getValue();
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public String getLocalTrackUrisColumnName(CirrusLocalTrackUrisColumns column) {
        Intrinsics.checkNotNullParameter(column, "column");
        return column.getValue();
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public String getPlaylistsColumnName(CirrusPlaylistsColumns column) {
        Intrinsics.checkNotNullParameter(column, "column");
        return column.getValue();
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public String getPrimePlaylistsColumnName(CirrusPrimePlaylistsColumns column) {
        Intrinsics.checkNotNullParameter(column, "column");
        return column.getValue();
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public String getPlaylistTracksColumnName(CirrusPlaylistTracksColumns column) {
        Intrinsics.checkNotNullParameter(column, "column");
        return column.getValue();
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public String getPrimePlaylistTracksColumnName(CirrusPrimePlaylistTracksColumns column) {
        Intrinsics.checkNotNullParameter(column, "column");
        return column.getValue();
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public String getPrimePlaylistToTrackColumnName(CirrusPrimePlaylistToTrackColumns column) {
        Intrinsics.checkNotNullParameter(column, "column");
        return column.getValue();
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public int convertDownloadedStateToInt(SearchDownloadedState source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return source.getValue();
    }
}
