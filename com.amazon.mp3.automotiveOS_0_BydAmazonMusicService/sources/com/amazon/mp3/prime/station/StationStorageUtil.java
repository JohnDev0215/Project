package com.amazon.mp3.prime.station;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.SQLiteOpenHelper;
import com.amazon.music.metrics.util.StringUtil;
import com.amazon.music.station.StationException;
import com.amazon.music.station.StationItem;
import com.amazon.music.station.StationManager;
import com.amazon.music.station.StationNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class StationStorageUtil {
    private static boolean intToBoolean(int value) {
        return value != 0;
    }

    @Nullable
    public static StationItem queryFromContentUri(Context context, Uri contentUri) {
        ContentResolver contentResolver = context.getContentResolver();
        if (MediaProvider.Station.isStation(contentUri)) {
            String key = MediaProvider.Station.getKey(contentUri);
            Cursor cursorQuery = contentResolver.query(contentUri, null, "key=?", new String[]{key}, null);
            if (cursorQuery != null) {
                try {
                    stationItem = cursorQuery.moveToFirst() ? new StationItem(intToBoolean(cursorQuery.getInt(cursorQuery.getColumnIndex("is_music_subscription"))), intToBoolean(cursorQuery.getInt(cursorQuery.getColumnIndex("is_prime"))), intToBoolean(cursorQuery.getInt(cursorQuery.getColumnIndex("prime_explicit_status"))), intToBoolean(cursorQuery.getInt(cursorQuery.getColumnIndex("unlimited_explicit_status"))), cursorQuery.getString(cursorQuery.getColumnIndex("title")), cursorQuery.getString(cursorQuery.getColumnIndex("image_url")), null, null, key) : null;
                } finally {
                    cursorQuery.close();
                }
            }
        }
        return stationItem;
    }

    public static void insert(Context context, ContentValues[] contentValuesArray) {
        SQLiteDatabase writableDatabase = OpenHelper.getInstance(context).getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            for (ContentValues contentValues : contentValuesArray) {
                writableDatabase.insert("stations", null, contentValues);
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public static void insert(Context context, ContentValues contentValues) {
        OpenHelper openHelper = OpenHelper.getInstance(context);
        if (openHelper.getWritableDatabase().insertWithOnConflict("stations", null, contentValues, 5) < 0) {
            openHelper.getWritableDatabase().update("stations", contentValues, "key=?", new String[]{contentValues.getAsString("key")});
        }
    }

    public static void insert(Context context, StationItem stationItem) {
        context.getContentResolver().insert(MediaProvider.Station.getContentUri(stationItem.getKey()), stationItemToContentValues(stationItem));
    }

    public static void insertStationWithDataChecks(final Context context, final StationItem stationItem) {
        Uri contentUri = MediaProvider.Station.getContentUri(stationItem.getKey());
        StationItem stationItemQueryFromContentUri = queryFromContentUri(context, contentUri);
        if (stationItemQueryFromContentUri == null) {
            insert(context, stationItem);
            return;
        }
        ContentValues contentValues = new ContentValues();
        if (StringUtil.isNullOrEmpty(stationItem.getTitle())) {
            contentValues.put("title", stationItemQueryFromContentUri.getTitle());
        } else {
            contentValues.put("title", stationItem.getTitle());
        }
        if (StringUtil.isNullOrEmpty(stationItem.getImageUrl())) {
            contentValues.put("image_url", stationItemQueryFromContentUri.getImageUrl());
        } else {
            contentValues.put("image_url", stationItem.getImageUrl());
        }
        if (StringUtil.isNullOrEmpty(stationItem.getKey())) {
            contentValues.put("key", stationItemQueryFromContentUri.getKey());
        } else {
            contentValues.put("key", stationItem.getKey());
        }
        contentValues.put("is_prime", stationItem.isPrime());
        contentValues.put("is_music_subscription", stationItem.isMusicSubscription());
        contentValues.put("prime_explicit_status", Boolean.valueOf(stationItem.hasPrimeExplicitContent()));
        contentValues.put("unlimited_explicit_status", Boolean.valueOf(stationItem.hasUnlimitedExplicitContent()));
        context.getContentResolver().insert(contentUri, contentValues);
    }

    private static ContentValues stationItemToContentValues(final StationItem stationItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", stationItem.getTitle());
        contentValues.put("image_url", stationItem.getImageUrl());
        contentValues.put("key", stationItem.getKey());
        contentValues.put("is_prime", stationItem.isPrime());
        contentValues.put("is_music_subscription", stationItem.isMusicSubscription());
        contentValues.put("prime_explicit_status", Boolean.valueOf(stationItem.hasPrimeExplicitContent()));
        contentValues.put("unlimited_explicit_status", Boolean.valueOf(stationItem.hasUnlimitedExplicitContent()));
        return contentValues;
    }

    public static Cursor query(Context context, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return OpenHelper.getInstance(context).getReadOnlyDatabase().query("stations", projection, selection, selectionArgs, null, null, sortOrder);
    }

    public static int update(Context context, ContentValues values, String selection, String[] selectionArgs) {
        return OpenHelper.getInstance(context).getWritableDatabase().update("stations", values, selection, selectionArgs);
    }

    public static int delete(Context context, String selection, String[] selectionArgs) {
        return OpenHelper.getInstance(context).getWritableDatabase().delete("stations", selection, selectionArgs);
    }

    public static StationItem fromJson(JSONObject jsonObject) throws JSONException {
        return new StationItem(jsonObject.getBoolean("is_music_subscription"), jsonObject.getBoolean("is_prime"), jsonObject.getBoolean("prime_explicit_status"), jsonObject.getBoolean("unlimited_explicit_status"), jsonObject.getString("title"), jsonObject.getString("image_url"), null, null, jsonObject.getString("key"));
    }

    public static StationItem fromRemoteRecent(final RecentlyPlayedItem recentlyPlayedItem) {
        return new StationItem(recentlyPlayedItem.getCatalogStatusTiers().toSet(), StationItem.buildExplicitMap(recentlyPlayedItem.isExplicit()), recentlyPlayedItem.getTitle(), recentlyPlayedItem.getArtworkImageUrl(), null, null, recentlyPlayedItem.getCollectionAsin(), MediaProvider.Station.getStationFromAnythingSeedId(recentlyPlayedItem.getContentUri()));
    }

    public static void toJson(StationItem stationItem, JSONObject jsonObject) throws JSONException {
        jsonObject.put("is_music_subscription", stationItem.isMusicSubscription());
        jsonObject.put("is_prime", stationItem.isPrime());
        jsonObject.put("title", stationItem.getTitle());
        jsonObject.put("image_url", stationItem.getImageUrl());
        jsonObject.put("prime_explicit_status", stationItem.hasPrimeExplicitContent());
        jsonObject.put("unlimited_explicit_status", stationItem.hasUnlimitedExplicitContent());
        jsonObject.put("key", stationItem.getKey());
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        private static OpenHelper sInstance;

        /* JADX INFO: Access modifiers changed from: private */
        public static synchronized OpenHelper getInstance(Context context) {
            if (sInstance == null) {
                sInstance = new OpenHelper(context);
            }
            return sInstance;
        }

        private OpenHelper(Context context) {
            super(context.getApplicationContext(), "stations", null, 3);
        }

        @Override // com.amazon.mp3.util.SQLiteOpenHelper
        public void onCreate(final SQLiteDatabase db) {
            db.beginTransaction();
            try {
                db.execSQL("CREATE TABLE IF NOT EXISTS stations ( _id INTEGER PRIMARY KEY AUTOINCREMENT, seed_id TEXT UNIQUE, seed_type TEST, key TEXT UNIQUE, is_prime INTEGER, is_music_subscription INTEGER, prime_explicit_status INTEGER, unlimited_explicit_status INTEGER, title TEXT, subtitle TEXT, image_url TEXT );");
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        }

        @Override // com.amazon.mp3.util.SQLiteOpenHelper
        public int onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
            if (oldVersion < 3) {
                try {
                    db.beginTransaction();
                    try {
                        db.execSQL("DROP TABLE stations");
                        db.execSQL("CREATE TABLE IF NOT EXISTS stations ( _id INTEGER PRIMARY KEY AUTOINCREMENT, seed_id TEXT UNIQUE, seed_type TEST, key TEXT UNIQUE, is_prime INTEGER, is_music_subscription INTEGER, prime_explicit_status INTEGER, unlimited_explicit_status INTEGER, title TEXT, subtitle TEXT, image_url TEXT );");
                        db.setTransactionSuccessful();
                        db.endTransaction();
                    } catch (Throwable th) {
                        db.endTransaction();
                        throw th;
                    }
                } catch (SQLiteException e) {
                    DbUtil.reportDbUpgradeException(oldVersion, newVersion, StationStorageUtil.class.getSimpleName(), e);
                }
            }
            return 3;
        }
    }

    public static void doUpgrade(Context context) {
        Cursor cursorQuery = query(context, null, null, null, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                if (cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("key")) == null) {
                    arrayList.add(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("seed_id")));
                }
            }
            updateSeedIdsToKeys(context, arrayList);
        } finally {
            cursorQuery.close();
        }
    }

    private static void updateSeedIdsToKeys(Context context, List<String> seedIdsToUpdate) {
        if (seedIdsToUpdate.isEmpty()) {
            return;
        }
        StationManager stationManager = StationsFactory.getInstance(context).getStationManager();
        SQLiteDatabase writableDatabase = OpenHelper.getInstance(context).getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            for (String str : seedIdsToUpdate) {
                try {
                    try {
                        StationItem stationItemBySeedId = stationManager.getStationItemBySeedId(str);
                        ContentValues contentValues = new ContentValues(1);
                        contentValues.put("key", stationItemBySeedId.getKey());
                        update(context, contentValues, "seed_id=?", new String[]{str});
                    } catch (StationNotFoundException unused) {
                        removeEntry(context, str);
                    }
                } catch (StationException e) {
                    if (e.getReason() != StationException.Reason.NETWORK_ERROR) {
                        removeEntry(context, str);
                    } else {
                        writableDatabase.setTransactionSuccessful();
                    }
                }
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    private static void removeEntry(Context context, String seedId) {
        delete(context, "seed_id=?", new String[]{seedId});
    }
}
