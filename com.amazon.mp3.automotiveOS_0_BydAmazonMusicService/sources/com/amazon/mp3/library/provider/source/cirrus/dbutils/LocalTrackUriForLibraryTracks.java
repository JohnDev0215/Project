package com.amazon.mp3.library.provider.source.cirrus.dbutils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import java.util.List;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public final class LocalTrackUriForLibraryTracks {
    private static final String TAG = "LocalTrackUriForLibraryTracks";
    private final SQLiteDatabase mDb;

    public LocalTrackUriForLibraryTracks(final SQLiteDatabase mDb) {
        this.mDb = mDb;
    }

    public boolean containsNullLuids() {
        boolean z = false;
        Cursor cursorQuery = this.mDb.query("LocalTrackUri", new String[]{"track_asin", "local_uri"}, "track_luid IS NULL AND local_uri NOT LIKE '%/PrimePlaylist/%'", new String[0], null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                int columnIndex = cursorQuery.getColumnIndex("track_asin");
                int columnIndex2 = cursorQuery.getColumnIndex("local_uri");
                while (!cursorQuery.isAfterLast()) {
                    String string = cursorQuery.getString(columnIndex);
                    String string2 = cursorQuery.getString(columnIndex2);
                    Log.debug(TAG, "Found a Null luid record for asin: " + string + ", localUri: " + string2);
                    z = true;
                    cursorQuery.moveToNext();
                }
                cursorQuery.close();
                return z;
            }
            cursorQuery.close();
            return false;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public boolean containsUnmatchedTempPrimeLuids() {
        Cursor unmatchedTempPrimeLuids = getUnmatchedTempPrimeLuids();
        if (unmatchedTempPrimeLuids != null) {
            try {
                if (unmatchedTempPrimeLuids.moveToNext()) {
                    unmatchedTempPrimeLuids.close();
                    return true;
                }
            } catch (Throwable th) {
                try {
                    unmatchedTempPrimeLuids.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (unmatchedTempPrimeLuids == null) {
            return false;
        }
        unmatchedTempPrimeLuids.close();
        return false;
    }

    protected Cursor getUnmatchedTempPrimeLuids() {
        return this.mDb.query("LocalTrackUri LEFT JOIN Track ON track_luid = luid", new String[]{"track_luid", "track_asin"}, "luid is NULL AND track_luid LIKE 'prime-%' AND track_asin IS NOT NULL AND local_uri NOT LIKE '%/PrimePlaylist/%'", new String[0], null, null, null);
    }

    @VisibleForTesting
    boolean containsTempLuid(final String asin) {
        Validate.notBlank(asin, "The asin should not be null.", new Object[0]);
        Cursor cursorRawQuery = this.mDb.rawQuery("SELECT COUNT(*) FROM LocalTrackUri WHERE track_asin =? AND track_luid LIKE 'prime-%'AND + local_uri NOT LIKE '%/PrimePlaylist/%'", new String[]{asin});
        try {
            cursorRawQuery.moveToFirst();
            boolean z = cursorRawQuery.getInt(0) > 0;
            cursorRawQuery.close();
            return z;
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                try {
                    cursorRawQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @VisibleForTesting
    boolean containsNullLuid(final String asin) {
        return countNullLuid(asin) > 0;
    }

    private int countNullLuid(final String asin) {
        Validate.notBlank(asin, "The asin should not be null.", new Object[0]);
        Cursor cursorRawQuery = this.mDb.rawQuery("SELECT COUNT(*) FROM LocalTrackUri WHERE track_asin =? AND track_luid IS NULL AND + local_uri NOT LIKE '%/PrimePlaylist/%'", new String[]{asin});
        try {
            cursorRawQuery.moveToFirst();
            int i = cursorRawQuery.getInt(0);
            cursorRawQuery.close();
            return i;
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                try {
                    cursorRawQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @VisibleForTesting
    boolean contains(final String asin, final String luid) {
        Validate.notBlank(asin, "The asin should not be null.", new Object[0]);
        if (luid == null) {
            return containsNullLuid(asin);
        }
        Cursor cursorRawQuery = this.mDb.rawQuery("SELECT COUNT(*) FROM LocalTrackUri WHERE track_asin =? AND track_luid =? AND local_uri NOT LIKE '%/PrimePlaylist/%'", new String[]{asin, luid});
        try {
            cursorRawQuery.moveToFirst();
            boolean z = cursorRawQuery.getInt(0) > 0;
            cursorRawQuery.close();
            return z;
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                try {
                    cursorRawQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void removeNullLuidRecords(final String asin) {
        Validate.notBlank(asin, "The asin should not be null.", new Object[0]);
        this.mDb.delete("LocalTrackUri", "track_asin =?  AND track_luid IS NULL  AND local_uri NOT LIKE '%/PrimePlaylist/%'", new String[]{asin});
    }

    private void removeDuplicateNullRecords(final String asin) {
        Validate.notBlank(asin, "The asin should not be null.", new Object[0]);
        this.mDb.execSQL("DELETE FROM LocalTrackUri WHERE track_luid IS NULL  AND track_asin =?  AND rowid NOT IN ( SELECT min(rowid) FROM LocalTrackUri WHERE track_luid IS NULL  AND track_asin =?  )", new String[]{asin, asin});
    }

    private void removeTempLuidRecords(final String asin) {
        this.mDb.delete("LocalTrackUri", "track_asin =?  AND track_luid LIKE 'prime-%' AND local_uri NOT LIKE '%/PrimePlaylist/%'", new String[]{asin});
    }

    public int updateTempPrimeLuid(final String asin, final String luid) {
        Validate.notNull(asin, "The asin should not be null", new Object[0]);
        Validate.notNull(luid, "The luid should not be null", new Object[0]);
        String strGeneratePrimeLuid = IdGenerator.generatePrimeLuid(asin);
        ContentValues contentValues = new ContentValues();
        contentValues.put("track_luid", luid);
        return this.mDb.update("LocalTrackUri", contentValues, "track_asin=? AND track_luid=? AND local_uri NOT LIKE '%PrimePlaylist%'", new String[]{asin, strGeneratePrimeLuid});
    }

    @VisibleForTesting
    void updateNullLuid(final String asin, final String luid) {
        Validate.notNull(asin, "The asin should not be null", new Object[0]);
        Validate.notNull(luid, "The luid should not be null", new Object[0]);
        ContentValues contentValues = new ContentValues();
        contentValues.put("track_luid", luid);
        this.mDb.update("LocalTrackUri", contentValues, "track_asin=? AND track_luid IS NULL  AND local_uri NOT LIKE '%PrimePlaylist%'", new String[]{asin});
    }

    public void updateNullOrTempPrimeLuids(final List<ContentValues> contentValuesList) {
        for (ContentValues contentValues : contentValuesList) {
            updateNullOrTempPrimeLuid(contentValues.getAsString("asin"), contentValues.getAsString("luid"));
        }
    }

    @VisibleForTesting
    void updateNullOrTempPrimeLuid(final String asin, final String luid) {
        Validate.notBlank(asin, "The asin should be provided.", new Object[0]);
        Validate.notBlank(luid, "The luid should be provided.", new Object[0]);
        if (contains(asin, luid)) {
            removeNullLuidRecords(asin);
            removeTempLuidRecords(asin);
        } else if (containsTempLuid(asin)) {
            updateTempPrimeLuid(asin, luid);
            removeNullLuidRecords(asin);
        } else {
            if (countNullLuid(asin) > 1) {
                removeDuplicateNullRecords(asin);
            }
            updateNullLuid(asin, luid);
        }
    }
}
