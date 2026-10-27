package com.amazon.mp3.library.provider.source.cirrus;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Profiler;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class CirrusDatabaseUtil {
    private static final Set<String> DATE_COLUMNS;
    private static HashMap<String, Integer> sCachedTrackCounts;
    private static HashMap<Integer, AccessTimeInfo> sAccessTimesCache = new HashMap<>();
    private static int sTotalAccessItems = -1;

    static {
        HashMap<String, Integer> map = new HashMap<>();
        sCachedTrackCounts = map;
        map.put("cirrus", -1);
        sCachedTrackCounts.put("cirrus-local", -1);
        HashSet hashSet = new HashSet();
        hashSet.add("date_purchased");
        hashSet.add("date_updated");
        hashSet.add("date_created");
        DATE_COLUMNS = Collections.unmodifiableSet(hashSet);
    }

    private CirrusDatabaseUtil() {
    }

    public static int getCachedTrackCount(Context context, String source, boolean forceUpdateIfUnknown) {
        Integer numValueOf = sCachedTrackCounts.get(source);
        if (numValueOf.intValue() == -1 && forceUpdateIfUnknown) {
            numValueOf = Integer.valueOf(getPersistedTrackCount(context, source));
        }
        return numValueOf.intValue();
    }

    public static void updateAllCachedTrackCounts(Context context) {
        Iterator it = new HashSet(sCachedTrackCounts.keySet()).iterator();
        while (it.hasNext()) {
            getPersistedTrackCount(context, (String) it.next());
        }
    }

    private static boolean hasTracks(String source) {
        Integer num = sCachedTrackCounts.get(source);
        return num != null && num.intValue() > 0;
    }

    public static int getPersistedTrackCount(Context context, String source) {
        int iIntFromCursor = DbUtil.intFromCursor(CirrusDatabase.getReadOnlyDatabase(context).query("Track", new String[]{"COUNT(*)"}, "source=?", getSelectionArgForSource(source), null, null, null));
        updateCachedTrackCount(context, source, iIntFromCursor);
        return iIntFromCursor;
    }

    private static void updateCachedTrackCount(Context context, String source, int count) {
        sCachedTrackCounts.put(source, Integer.valueOf(count));
        if ("cirrus".equals(source)) {
            AccountDetailStorage.get(context).setHasTracksCirrus(hasTracks(source));
        }
        if ("cirrus-local".equals(source)) {
            SettingsUtil.setHasTracksDevice(context, hasTracks(source));
        }
    }

    public static long getMostRecentDate(Context context, String columnName, String source) {
        Profiler.begin("Getting the most recent date out of cirrus");
        if (!DATE_COLUMNS.contains(columnName)) {
            throw new IllegalArgumentException("Specified column name is not a date");
        }
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("Track");
        String[] selectionArgForSource = getSelectionArgForSource(source);
        Cursor cursorQuery = sQLiteQueryBuilder.query(CirrusDatabase.getReadOnlyDatabase(context), new String[]{"max(" + columnName + ")"}, "source=?", selectionArgForSource, null, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() == 1) {
                    return DbUtil.longFromCursor(cursorQuery);
                }
            } finally {
                Profiler.end();
                DbUtil.closeCursor(cursorQuery);
            }
        }
        Profiler.end();
        DbUtil.closeCursor(cursorQuery);
        return -1L;
    }

    public static String[] getSelectionArgForSource(String source) {
        String[] strArr = new String[1];
        if ("cirrus".equals(source)) {
            strArr[0] = Integer.toString(0);
        } else if ("cirrus-local".equals(source)) {
            strArr[0] = Integer.toString(1);
        } else {
            throw new IllegalArgumentException("Unknown source passed in to getSelectionArgForSource");
        }
        return strArr;
    }

    public static int getLastSource(Uri contentUri) {
        Profiler.begin("Get last source");
        updateAccessTimeCache();
        int i = 1;
        if (sAccessTimesCache.containsKey(Integer.valueOf(contentUri.toString().hashCode()))) {
            Profiler.begin("get last accessed time - cached");
            i = sAccessTimesCache.get(Integer.valueOf(contentUri.toString().hashCode())).mSource;
            Profiler.end();
        } else if (sAccessTimesCache.size() < sTotalAccessItems) {
            String[] strArr = {PageUriUtils.SOURCE_QUERY_PARAM};
            String[] strArr2 = {contentUri.toString()};
            Cursor cursorQuery = null;
            try {
                cursorQuery = CirrusDatabase.getReadOnlyDatabase(AmazonApplication.getContext()).query("AccessTime", strArr, "content_uri=?", strArr2, null, null, null);
                if (cursorQuery != null && cursorQuery.getCount() == 1 && cursorQuery.moveToFirst()) {
                    i = cursorQuery.getInt(cursorQuery.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM));
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
        Profiler.end();
        return i;
    }

    private static void updateAccessTimeCache() {
        if (sTotalAccessItems == -1) {
            Profiler.begin("updating the access time cache");
            Cursor cursorQuery = null;
            try {
                cursorQuery = CirrusDatabase.getWritableDatabase(AmazonApplication.getContext()).query("AccessTime", new String[]{"content_uri_hash", "date_played", "date_downloaded", "date_purchased", PageUriUtils.SOURCE_QUERY_PARAM}, null, null, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    sTotalAccessItems = cursorQuery.getCount();
                    int columnIndex = cursorQuery.getColumnIndex("content_uri_hash");
                    int columnIndex2 = cursorQuery.getColumnIndex("date_played");
                    int columnIndex3 = cursorQuery.getColumnIndex("date_downloaded");
                    int columnIndex4 = cursorQuery.getColumnIndex("date_purchased");
                    int columnIndex5 = cursorQuery.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM);
                    int i = 0;
                    do {
                        long j = 0;
                        long j2 = cursorQuery.isNull(columnIndex2) ? 0L : cursorQuery.getLong(columnIndex2);
                        long j3 = cursorQuery.isNull(columnIndex3) ? 0L : cursorQuery.getLong(columnIndex3);
                        if (!cursorQuery.isNull(columnIndex4)) {
                            j = cursorQuery.getLong(columnIndex4);
                        }
                        sAccessTimesCache.put(Integer.valueOf(cursorQuery.getInt(columnIndex)), new AccessTimeInfo(j2, j3, j, (byte) (cursorQuery.isNull(columnIndex5) ? 1 : cursorQuery.getInt(columnIndex5))));
                        i++;
                        if (i >= 50000) {
                            break;
                        }
                    } while (cursorQuery.moveToNext());
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
                Profiler.end();
            }
        }
    }

    private static class AccessTimeInfo {
        public long mDownloaded;
        public long mPlayed;
        public long mPurchased;
        public byte mSource;

        AccessTimeInfo(long played, long downloaded, long purchased, byte source) {
            this.mPlayed = played;
            this.mDownloaded = downloaded;
            this.mPurchased = purchased;
            this.mSource = source;
        }
    }
}
