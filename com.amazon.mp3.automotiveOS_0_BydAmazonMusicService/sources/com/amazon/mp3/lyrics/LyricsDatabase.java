package com.amazon.mp3.lyrics;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.lyrics.item.LyricsTrackInfo;
import com.amazon.mp3.util.DbUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class LyricsDatabase implements LyricsDB {
    private Context mContext;
    private String mMarketplaceId;

    public LyricsDatabase(Context applicationContext, String marketplaceId) {
        this.mContext = applicationContext;
        this.mMarketplaceId = marketplaceId;
    }

    @Override // com.amazon.mp3.lyrics.LyricsDB
    public void updateLyricsStatusForAsins(LyricsStatus status, List<String> asinList) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(this.mContext);
        try {
            DbUtil.beginTransaction(writableDatabase);
            ContentValues contentValues = new ContentValues();
            contentValues.put("lyrics_state", status.toString());
            ArrayList arrayList = new ArrayList(50);
            int i = 0;
            Iterator<String> it = asinList.iterator();
            while (it.hasNext()) {
                i++;
                arrayList.add(it.next());
                if (i % 50 == 0 || i == asinList.size()) {
                    DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", arrayList);
                    String clause = whereClause.getClause();
                    String str = clause + " AND " + constructMarketplaceWhereClause();
                    String[] args = whereClause.getArgs();
                    writableDatabase.update("Track", contentValues, str, args);
                    writableDatabase.update("PrimePlaylistTracks", contentValues, clause, args);
                    arrayList.clear();
                }
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @Override // com.amazon.mp3.lyrics.LyricsDB
    public List<LyricsTrackInfo> loadTrackInfoForNewTracks(int limit) {
        ArrayList arrayList = new ArrayList();
        if (limit <= 0) {
            return arrayList;
        }
        arrayList.addAll(loadLyricsTrackInfo("Track", "asin", "asin IS NOT NULL AND asin<>'' AND lyrics_state IS NULL AND " + constructMarketplaceWhereClause(), null, Integer.valueOf(limit != 1 ? limit / 2 : 1)));
        arrayList.addAll(loadLyricsTrackInfo("PrimePlaylistTracks", "asin", "asin IS NOT NULL AND asin<>'' AND lyrics_state IS NULL", null, Integer.valueOf(limit - arrayList.size())));
        return arrayList;
    }

    @Override // com.amazon.mp3.lyrics.LyricsDB
    public Set<LyricsTrackInfo> loadTrackInfoForAllTracksWithLyrics() {
        String str = "lyrics_state IS NOT NULL AND " + constructMarketplaceWhereClause();
        HashSet hashSet = new HashSet();
        hashSet.addAll(loadLyricsTrackInfo("Track", "asin", str, null, null));
        hashSet.addAll(loadLyricsTrackInfo("PrimePlaylistTracks", "asin", "lyrics_state IS NOT NULL", null, null));
        return hashSet;
    }

    @Override // com.amazon.mp3.lyrics.LyricsDB
    public List<LyricsTrackInfo> loadTrackInfoForAsins(List<String> asinList) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = asinList.iterator();
        int i = 0;
        while (it.hasNext()) {
            arrayList2.add(it.next());
            i++;
            if (i % 50 == 0 || i == asinList.size()) {
                DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", arrayList2);
                String clause = whereClause.getClause();
                String str = clause + " AND " + constructMarketplaceWhereClause();
                String[] args = whereClause.getArgs();
                arrayList.addAll(loadLyricsTrackInfo("Track", "asin", str, args, null));
                arrayList.addAll(loadLyricsTrackInfo("PrimePlaylistTracks", "asin", clause, args, null));
                arrayList2.clear();
            }
        }
        return arrayList;
    }

    @Override // com.amazon.mp3.lyrics.LyricsDB
    public LyricsStatus loadLyricsStatusForTrackInfo(LyricsTrackInfo info) {
        LyricsStatus lyricsStatusFromString;
        if (info == null || info.getAsin() == null || info.getMarketplaceId() == null) {
            return LyricsStatus.NO_LYRICS;
        }
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(this.mContext).query("Track", new String[]{"lyrics_state"}, "asin=? AND (marketplace=? OR marketplace IS NULL)", new String[]{info.getAsin(), info.getMarketplaceId()}, null, null, null);
        if (cursorQuery.moveToNext()) {
            lyricsStatusFromString = LyricsStatus.fromString(cursorQuery.getString(cursorQuery.getColumnIndex("lyrics_state")));
        } else {
            lyricsStatusFromString = LyricsStatus.UNKNOWN;
        }
        cursorQuery.close();
        return lyricsStatusFromString;
    }

    private List<LyricsTrackInfo> loadLyricsTrackInfo(String tableName, String asinColumn, String whereClause, String[] whereArgs, Integer limit) {
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(this.mContext).query(tableName, new String[]{asinColumn}, whereClause, whereArgs, null, null, null, limit == null ? null : Integer.toString(limit.intValue()));
        ArrayList arrayList = new ArrayList();
        while (cursorQuery.moveToNext()) {
            arrayList.add(new LibraryLyricsTrackInfo(cursorQuery.getString(cursorQuery.getColumnIndex(asinColumn)), this.mMarketplaceId));
        }
        cursorQuery.close();
        return arrayList;
    }

    private String constructMarketplaceWhereClause() {
        return "(marketplace='" + this.mMarketplaceId + "' OR " + IntlConfiguration.MARKETPLACE + "='' OR " + IntlConfiguration.MARKETPLACE + " IS NULL)";
    }

    private static class LibraryLyricsTrackInfo extends LyricsTrackInfo {
        private final String mAsin;
        private final String mMarketplace;

        public LibraryLyricsTrackInfo(String asin, String marketplace) {
            this.mAsin = asin;
            this.mMarketplace = marketplace;
        }

        @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
        public String getAsin() {
            return this.mAsin;
        }

        @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
        public String getMarketplaceId() {
            return this.mMarketplace;
        }
    }
}
