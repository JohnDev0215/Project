package com.amazon.mp3.library.provider.source.cirrus.dbutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.service.sync.TrackSyncUtil;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes3.dex */
public final class LocalTrackUtil {
    private static final String TAG = "LocalTrackUtil";
    private final SQLiteDatabase mDb;

    public LocalTrackUtil(final SQLiteDatabase mDb) {
        this.mDb = mDb;
    }

    public long insertTrackLocalUri(final Context context, final String luid, final String asin, final String destinationUri) {
        if (luid == null) {
            Log.error(TAG, "The LUID for a track should not be null: " + asin + ", " + destinationUri);
        }
        Validate.notNull(destinationUri, "The destinationUri should not be null", new Object[0]);
        if (asin != null && TextUtils.getTrimmedLength(asin) > 0) {
            deleteTrackLocalUri(asin);
        }
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("track_asin", asin);
        contentValues.put("local_uri", destinationUri);
        if (StringUtils.isBlank(luid) || IdGenerator.isPrimeLuid(luid)) {
            MusicTrack trackForAsin = new CirrusSourceLibraryItemFactory(context).getTrackForAsin(asin);
            if (trackForAsin != null) {
                Log.debug(TAG, "The track being downloaded is in library: asin = %s, luid = %s, localUri = %s", asin, trackForAsin.getLuid(), destinationUri);
                ContentValues contentValues2 = new ContentValues(3);
                contentValues2.put("track_asin", asin);
                contentValues2.put("track_luid", trackForAsin.getLuid());
                contentValues2.put("local_uri", destinationUri);
                this.mDb.insertWithOnConflict("LocalTrackUri", null, contentValues2, 5);
            }
            contentValues.put("track_luid", getCorrespondingLuidFromTrack(luid, asin));
            return this.mDb.insertWithOnConflict("LocalTrackUri", null, contentValues, 5);
        }
        contentValues.put("track_luid", luid);
        return this.mDb.insertWithOnConflict("LocalTrackUri", null, contentValues, 5);
    }

    public long insertPrimePlaylistTrackLocalUri(final String asin, final String destinationUri) {
        Validate.notNull(asin, "The asin should not be null", new Object[0]);
        Validate.notNull(destinationUri, "The destinationUri should not be null", new Object[0]);
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("track_asin", asin);
        contentValues.put("local_uri", destinationUri);
        deletePrimePlaylistTrackLocalUri(asin);
        deleteHarleyPrimePlaylistTrackLocalUri(asin);
        return this.mDb.insert("LocalTrackUri", null, contentValues);
    }

    @Deprecated
    private void deletePrimePlaylistTrackLocalUri(final String asin) {
        Validate.notNull(asin, "The asin should not be null", new Object[0]);
        this.mDb.delete("LocalTrackUri", "track_luid IS NULL AND track_asin=? AND local_uri LIKE ?", new String[]{asin, "%PrimePlaylist%"});
    }

    private void deleteHarleyPrimePlaylistTrackLocalUri(final String asin) {
        Validate.notNull(asin, "The asin should not be null", new Object[0]);
        this.mDb.delete("LocalTrackUri", "track_luid IS NULL AND track_asin=? AND local_uri LIKE ?", new String[]{asin, "%asin://%"});
    }

    private int deleteTrackLocalUri(final String asin) {
        Validate.notNull(asin, "The asin should not be null", new Object[0]);
        return this.mDb.delete("LocalTrackUri", "track_asin=? AND local_uri NOT LIKE ?", new String[]{asin, "%PrimePlaylist%"});
    }

    public int deleteTrackLocalUrisByLuids(final String[] luidArray) {
        Validate.notEmpty(luidArray, "The luidArray should not be empty", new Object[0]);
        Validate.noNullElements(luidArray, "The luidArray should not contain null elements", new Object[0]);
        return this.mDb.delete("LocalTrackUri", "track_luid=?", luidArray);
    }

    public int deleteTracksLocalUrisByUris(final String[] uriArray) {
        Validate.notEmpty(uriArray, "The uriArray should not be empty", new Object[0]);
        Validate.noNullElements(uriArray, "The uriArray should not contain null elements", new Object[0]);
        return this.mDb.delete("LocalTrackUri", "local_uri=?", uriArray);
    }

    public int updateTempPrimeLuid(final String asin, final String luid) {
        Validate.notNull(asin, "The asin should not be null", new Object[0]);
        Validate.notNull(luid, "The luid should not be null", new Object[0]);
        return new LocalTrackUriForLibraryTracks(this.mDb).updateTempPrimeLuid(asin, luid);
    }

    protected String getCorrespondingLuidFromTrack(final String luid, final String asin) {
        String str;
        String[] strArr;
        if (luid == null) {
            strArr = new String[]{String.valueOf(0), asin};
            str = "source=? AND luid is not NULL AND asin=?";
        } else {
            String[] strArr2 = {String.valueOf(0), luid, asin};
            str = "source=? AND luid is not NULL AND luid!=? AND asin=?";
            strArr = strArr2;
        }
        String existingLocalTrackLuidForAsin = TrackSyncUtil.getExistingLocalTrackLuidForAsin(this.mDb, str, strArr);
        if (existingLocalTrackLuidForAsin == null) {
            return luid;
        }
        Log.debug(TAG, "found different luid in tracks table. oldLuid:" + luid + " and new luid: " + existingLocalTrackLuidForAsin);
        return existingLocalTrackLuidForAsin;
    }

    public boolean isTrackAlsoDownloadedInPrimePlaylist(@NonNull final String asin) {
        Validate.notNull(asin, "The asin should not be null", new Object[0]);
        Cursor cursorQuery = this.mDb.query("LocalTrackUri", new String[]{"track_asin", "track_luid"}, "track_asin=? AND track_luid IS NULL", new String[]{asin}, null, null, null);
        if (cursorQuery != null) {
            if (cursorQuery.moveToFirst()) {
                cursorQuery.close();
                return true;
            }
            cursorQuery.close();
        }
        return false;
    }

    public int replaceAsinByLuid(@NonNull String newAsin, @NonNull String luid, @Nullable MusicTrack oldTrack) {
        Validate.notNull(newAsin);
        Validate.notNull(luid);
        if (oldTrack == null || oldTrack.getOwnershipStatus() == ContentOwnershipStatus.OWNED) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("track_asin", newAsin);
        return this.mDb.update("LocalTrackUri", contentValues, "track_luid=?", new String[]{luid});
    }

    public Cursor queryForAllImportedTracks() {
        return this.mDb.query("LocalTrackUri", new String[]{"track_asin", "track_luid", "local_uri", "license_expiration"}, null, null, "local_uri", "COUNT(*)=1", null);
    }
}
