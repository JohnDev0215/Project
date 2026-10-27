package com.android.inputmethod.dictionarypack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.makedict.DictionaryHeader;
import com.android.inputmethod.latin.utils.DebugLogUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;
import javax.annotation.Nullable;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes2.dex */
public class MetadataDbHelper extends SQLiteOpenHelper {
    private static final String CLIENT_LAST_UPDATE_DATE_COLUMN = "lastupdate";
    private static final String CLIENT_METADATA_ADDITIONAL_ID_COLUMN = "additionalid";
    private static final String CLIENT_PENDINGID_COLUMN = "pendingid";
    static final String CLIENT_TABLE_NAME = "clients";
    public static final int COLUMN_COUNT = 15;
    private static final int CURRENT_METADATA_DATABASE_VERSION = 16;
    public static final String DATE_COLUMN = "date";
    public static final String DESCRIPTION_COLUMN = "description";
    public static final String DICTIONARIES_ASSETS_PATH = "dictionaries";
    public static final int DICTIONARY_RETRY_THRESHOLD = 2;
    public static final String LOCALE_COLUMN = "locale";
    private static final String METADATA_CREATE_CLIENT_TABLE = "CREATE TABLE IF NOT EXISTS clients (clientid TEXT, uri TEXT, additionalid TEXT, lastupdate INTEGER NOT NULL DEFAULT 0, pendingid INTEGER, flags INTEGER, PRIMARY KEY (clientid));";
    private static final int METADATA_DATABASE_INITIAL_VERSION = 3;
    public static final String METADATA_DATABASE_NAME_STEM = "pendingUpdates";
    private static final int METADATA_DATABASE_VERSION_WITH_CLIENTID = 6;
    private static final String METADATA_TABLE_CREATE = "CREATE TABLE pendingUpdates (pendingid INTEGER, type INTEGER, status INTEGER, id TEXT, locale TEXT, description TEXT, filename TEXT, url TEXT, date INTEGER, checksum TEXT, filesize INTEGER, version INTEGER,formatversion INTEGER, flags INTEGER, rawChecksum TEXT,remainingRetries INTEGER, PRIMARY KEY (id,version));";
    public static final String METADATA_TABLE_NAME = "pendingUpdates";
    public static final String METADATA_UPDATE_DESCRIPTION = "metadata";
    private static final long NOT_A_DOWNLOAD_ID = -1;
    public static final String PENDINGID_COLUMN = "pendingid";
    public static final int STATUS_AVAILABLE = 1;
    public static final String STATUS_COLUMN = "status";
    public static final int STATUS_DELETING = 5;
    public static final int STATUS_DISABLED = 4;
    public static final int STATUS_DOWNLOADING = 2;
    public static final int STATUS_INSTALLED = 3;
    public static final int STATUS_RETRYING = 6;
    public static final int STATUS_UNKNOWN = 0;
    private static final String TAG = "MetadataDbHelper";
    public static final int TYPE_BULK = 2;
    public static final int TYPE_METADATA = 1;
    public static final int TYPE_UPDATE = 3;
    public static final String VERSION_COLUMN = "version";
    private final String mClientId;
    private final Context mContext;
    public static final String TYPE_COLUMN = "type";
    public static final String WORDLISTID_COLUMN = "id";
    public static final String LOCAL_FILENAME_COLUMN = "filename";
    public static final String REMOTE_FILENAME_COLUMN = "url";
    public static final String CHECKSUM_COLUMN = "checksum";
    public static final String FILESIZE_COLUMN = "filesize";
    public static final String FORMATVERSION_COLUMN = "formatversion";
    public static final String FLAGS_COLUMN = "flags";
    public static final String RAW_CHECKSUM_COLUMN = "rawChecksum";
    public static final String RETRY_COUNT_COLUMN = "remainingRetries";
    static final String[] METADATA_TABLE_COLUMNS = {"pendingid", TYPE_COLUMN, "status", WORDLISTID_COLUMN, "locale", "description", LOCAL_FILENAME_COLUMN, REMOTE_FILENAME_COLUMN, "date", CHECKSUM_COLUMN, FILESIZE_COLUMN, "version", FORMATVERSION_COLUMN, FLAGS_COLUMN, RAW_CHECKSUM_COLUMN, RETRY_COUNT_COLUMN};
    private static final String CLIENT_CLIENT_ID_COLUMN = "clientid";
    private static final String CLIENT_METADATA_URI_COLUMN = "uri";
    static final String[] CLIENT_TABLE_COLUMNS = {CLIENT_CLIENT_ID_COLUMN, CLIENT_METADATA_URI_COLUMN, "pendingid", FLAGS_COLUMN};
    static final String[] DICTIONARIES_LIST_PUBLIC_COLUMNS = {"status", WORDLISTID_COLUMN, "locale", "description", "date", FILESIZE_COLUMN, "version"};
    private static TreeMap<String, MetadataDbHelper> sInstanceMap = null;
    private static final MetadataUriGetter sMetadataUriGetter = new MetadataUriGetter();

    public static synchronized MetadataDbHelper getInstance(final Context context, final String clientIdOrNull) {
        MetadataDbHelper metadataDbHelper;
        if (clientIdOrNull == null) {
            clientIdOrNull = "";
        }
        if (sInstanceMap == null) {
            sInstanceMap = new TreeMap<>();
        }
        metadataDbHelper = sInstanceMap.get(clientIdOrNull);
        if (metadataDbHelper == null) {
            metadataDbHelper = new MetadataDbHelper(context, clientIdOrNull);
            sInstanceMap.put(clientIdOrNull, metadataDbHelper);
        }
        return metadataDbHelper;
    }

    private MetadataDbHelper(final Context context, final String clientId) {
        super(context, "pendingUpdates" + (TextUtils.isEmpty(clientId) ? "" : "." + clientId), (SQLiteDatabase.CursorFactory) null, 16);
        this.mContext = context;
        this.mClientId = clientId;
    }

    public static SQLiteDatabase getDb(final Context context, final String clientId) {
        return getInstance(context, clientId).getWritableDatabase();
    }

    private void createClientTable(final SQLiteDatabase db) {
        if (TextUtils.isEmpty(this.mClientId)) {
            db.execSQL(METADATA_CREATE_CLIENT_TABLE);
            String string = this.mContext.getString(R.string.default_metadata_uri);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put(CLIENT_CLIENT_ID_COLUMN, "");
            contentValues.put(CLIENT_METADATA_URI_COLUMN, string);
            contentValues.put("pendingid", (Integer) (-1));
            db.insert(CLIENT_TABLE_NAME, null, contentValues);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL(METADATA_TABLE_CREATE);
        createClientTable(db);
    }

    private static void addRawChecksumColumnUnlessPresent(final SQLiteDatabase db) {
        try {
            db.execSQL("SELECT rawChecksum FROM pendingUpdates LIMIT 0;");
        } catch (SQLiteException unused) {
            Log.i(TAG, "No rawChecksum column : creating it");
            db.execSQL("ALTER TABLE pendingUpdates ADD COLUMN rawChecksum TEXT;");
        }
    }

    private static void addRetryCountColumnUnlessPresent(final SQLiteDatabase db) {
        try {
            db.execSQL("SELECT remainingRetries FROM pendingUpdates LIMIT 0;");
        } catch (SQLiteException unused) {
            Log.i(TAG, "No remainingRetries column : creating it");
            db.execSQL("ALTER TABLE pendingUpdates ADD COLUMN remainingRetries INTEGER DEFAULT 2;");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        if (3 != oldVersion || 6 > newVersion || 16 < newVersion) {
            if (6 < newVersion && 16 >= newVersion) {
                db.execSQL("DROP TABLE IF EXISTS clients");
                if (TextUtils.isEmpty(this.mClientId)) {
                    createClientTable(db);
                }
            } else {
                db.execSQL("DROP TABLE IF EXISTS pendingUpdates");
                db.execSQL("DROP TABLE IF EXISTS clients");
                onCreate(db);
            }
        } else if (TextUtils.isEmpty(this.mClientId)) {
            createClientTable(db);
        }
        addRawChecksumColumnUnlessPresent(db);
        addRetryCountColumnUnlessPresent(db);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        if (oldVersion <= newVersion) {
            Log.e(TAG, "onDowngrade database but new version is higher? " + oldVersion + " <= " + newVersion);
        }
        db.execSQL("DROP TABLE IF EXISTS pendingUpdates");
        db.execSQL("DROP TABLE IF EXISTS clients");
        onCreate(db);
    }

    public static boolean isClientKnown(final Context context, final String clientId) {
        return getMetadataUriAsString(context, clientId) != null;
    }

    public static String getMetadataUriAsString(final Context context, final String clientId) {
        Cursor cursorQuery = getDb(context, null).query(CLIENT_TABLE_NAME, new String[]{CLIENT_METADATA_URI_COLUMN}, "clientid = ?", new String[]{clientId}, null, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                return MetadataUriGetter.getUri(context, cursorQuery.getString(0));
            }
            return null;
        } finally {
            cursorQuery.close();
        }
    }

    public static void saveLastUpdateTimeOfUri(final Context context, final String uri) {
        PrivateLog.log("Save last update time of URI : " + uri + " " + System.currentTimeMillis());
        ContentValues contentValues = new ContentValues();
        contentValues.put(CLIENT_LAST_UPDATE_DATE_COLUMN, Long.valueOf(System.currentTimeMillis()));
        SQLiteDatabase db = getDb(context, null);
        Cursor cursorQueryClientIds = queryClientIds(context);
        if (cursorQueryClientIds == null) {
            return;
        }
        try {
            if (cursorQueryClientIds.moveToFirst()) {
                do {
                    String string = cursorQueryClientIds.getString(0);
                    if (getMetadataUriAsString(context, string).equals(uri)) {
                        db.update(CLIENT_TABLE_NAME, contentValues, "clientid = ?", new String[]{string});
                    }
                } while (cursorQueryClientIds.moveToNext());
            }
        } finally {
            cursorQueryClientIds.close();
        }
    }

    public static long getLastUpdateDateForClient(final Context context, final String clientId) {
        SQLiteDatabase db = getDb(context, null);
        String[] strArr = {CLIENT_LAST_UPDATE_DATE_COLUMN};
        String[] strArr2 = new String[1];
        if (clientId == null) {
            clientId = "";
        }
        strArr2[0] = clientId;
        Cursor cursorQuery = db.query(CLIENT_TABLE_NAME, strArr, "clientid = ?", strArr2, null, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                return cursorQuery.getLong(0);
            }
            return 0L;
        } finally {
            cursorQuery.close();
        }
    }

    public static DownloadIdAndStartDate getMetadataDownloadIdAndStartDateForURI(final Context context, final String uri) {
        Cursor cursorQuery = getDb(context, null).query(CLIENT_TABLE_NAME, new String[]{"pendingid", CLIENT_LAST_UPDATE_DATE_COLUMN}, "uri = ?", new String[]{uri}, null, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                return new DownloadIdAndStartDate(cursorQuery.getInt(0), cursorQuery.getLong(1));
            }
            return null;
        } finally {
            cursorQuery.close();
        }
    }

    public static long getOldestUpdateTime(final Context context) {
        Cursor cursorQuery = getDb(context, null).query(CLIENT_TABLE_NAME, new String[]{CLIENT_LAST_UPDATE_DATE_COLUMN}, null, null, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                long jMin = LongCompanionObject.MAX_VALUE;
                do {
                    jMin = Math.min(cursorQuery.getLong(0), jMin);
                } while (cursorQuery.moveToNext());
                return jMin;
            }
            return 0L;
        } finally {
            cursorQuery.close();
        }
    }

    public static ContentValues makeContentValues(final int pendingId, final int type, final int status, final String wordlistId, final String locale, final String description, final String filename, final String url, final long date, final String rawChecksum, final String checksum, final int retryCount, final long filesize, final int version, final int formatVersion) {
        ContentValues contentValues = new ContentValues(15);
        contentValues.put("pendingid", Integer.valueOf(pendingId));
        contentValues.put(TYPE_COLUMN, Integer.valueOf(type));
        contentValues.put(WORDLISTID_COLUMN, wordlistId);
        contentValues.put("status", Integer.valueOf(status));
        contentValues.put("locale", locale);
        contentValues.put("description", description);
        contentValues.put(LOCAL_FILENAME_COLUMN, filename);
        contentValues.put(REMOTE_FILENAME_COLUMN, url);
        contentValues.put("date", Long.valueOf(date));
        contentValues.put(RAW_CHECKSUM_COLUMN, rawChecksum);
        contentValues.put(RETRY_COUNT_COLUMN, Integer.valueOf(retryCount));
        contentValues.put(CHECKSUM_COLUMN, checksum);
        contentValues.put(FILESIZE_COLUMN, Long.valueOf(filesize));
        contentValues.put("version", Integer.valueOf(version));
        contentValues.put(FORMATVERSION_COLUMN, Integer.valueOf(formatVersion));
        contentValues.put(FLAGS_COLUMN, (Integer) 0);
        return contentValues;
    }

    public static ContentValues completeWithDefaultValues(final ContentValues result) throws BadFormatException {
        if (result.get(WORDLISTID_COLUMN) == null || result.get("locale") == null) {
            throw new BadFormatException();
        }
        if (result.get("pendingid") == null) {
            result.put("pendingid", (Integer) 0);
        }
        if (result.get(TYPE_COLUMN) == null) {
            result.put(TYPE_COLUMN, (Integer) 2);
        }
        if (result.get("status") == null) {
            result.put("status", (Integer) 3);
        }
        if (result.get("description") == null) {
            result.put("description", "");
        }
        if (result.get(LOCAL_FILENAME_COLUMN) == null) {
            result.put(LOCAL_FILENAME_COLUMN, "_");
        }
        if (result.get(REMOTE_FILENAME_COLUMN) == null) {
            result.put(REMOTE_FILENAME_COLUMN, "");
        }
        if (result.get("date") == null) {
            result.put("date", (Integer) 0);
        }
        if (result.get(RAW_CHECKSUM_COLUMN) == null) {
            result.put(RAW_CHECKSUM_COLUMN, "");
        }
        if (result.get(RETRY_COUNT_COLUMN) == null) {
            result.put(RETRY_COUNT_COLUMN, (Integer) 2);
        }
        if (result.get(CHECKSUM_COLUMN) == null) {
            result.put(CHECKSUM_COLUMN, "");
        }
        if (result.get(FILESIZE_COLUMN) == null) {
            result.put(FILESIZE_COLUMN, (Integer) 0);
        }
        if (result.get("version") == null) {
            result.put("version", (Integer) 1);
        }
        if (result.get(FORMATVERSION_COLUMN) == null) {
            result.put(FORMATVERSION_COLUMN, (Integer) 86736212);
        }
        if (result.get(FLAGS_COLUMN) == null) {
            result.put(FLAGS_COLUMN, (Integer) 0);
        }
        return result;
    }

    private static void putStringResult(ContentValues result, Cursor cursor, String columnId) {
        result.put(columnId, cursor.getString(cursor.getColumnIndex(columnId)));
    }

    private static void putIntResult(ContentValues result, Cursor cursor, String columnId) {
        result.put(columnId, Integer.valueOf(cursor.getInt(cursor.getColumnIndex(columnId))));
    }

    private static ContentValues getFirstLineAsContentValues(final Cursor cursor) {
        if (!cursor.moveToFirst()) {
            return null;
        }
        ContentValues contentValues = new ContentValues(15);
        putIntResult(contentValues, cursor, "pendingid");
        putIntResult(contentValues, cursor, TYPE_COLUMN);
        putIntResult(contentValues, cursor, "status");
        putStringResult(contentValues, cursor, WORDLISTID_COLUMN);
        putStringResult(contentValues, cursor, "locale");
        putStringResult(contentValues, cursor, "description");
        putStringResult(contentValues, cursor, LOCAL_FILENAME_COLUMN);
        putStringResult(contentValues, cursor, REMOTE_FILENAME_COLUMN);
        putIntResult(contentValues, cursor, "date");
        putStringResult(contentValues, cursor, RAW_CHECKSUM_COLUMN);
        putStringResult(contentValues, cursor, CHECKSUM_COLUMN);
        putIntResult(contentValues, cursor, RETRY_COUNT_COLUMN);
        putIntResult(contentValues, cursor, FILESIZE_COLUMN);
        putIntResult(contentValues, cursor, "version");
        putIntResult(contentValues, cursor, FORMATVERSION_COLUMN);
        putIntResult(contentValues, cursor, FLAGS_COLUMN);
        if (!cursor.moveToNext()) {
            return contentValues;
        }
        Log.e(TAG, "Several SQL results when we expected only one!");
        return contentValues;
    }

    public static ContentValues getContentValuesByPendingId(final SQLiteDatabase db, final long id) {
        Cursor cursorQuery = db.query("pendingUpdates", METADATA_TABLE_COLUMNS, "pendingid= ?", new String[]{Long.toString(id)}, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        try {
            return getFirstLineAsContentValues(cursorQuery);
        } finally {
            cursorQuery.close();
        }
    }

    public static ContentValues getInstalledOrDeletingWordListContentValuesByWordListId(final SQLiteDatabase db, final String id) {
        Cursor cursorQuery = db.query("pendingUpdates", METADATA_TABLE_COLUMNS, "id=? AND (status=? OR status=?)", new String[]{id, Integer.toString(3), Integer.toString(5)}, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        try {
            return getFirstLineAsContentValues(cursorQuery);
        } finally {
            cursorQuery.close();
        }
    }

    public static ArrayList<DownloadRecord> getDownloadRecordsForDownloadId(final Context context, final long downloadId) {
        SQLiteDatabase db = getDb(context, "");
        ArrayList<DownloadRecord> arrayList = new ArrayList<>();
        Cursor cursorQuery = db.query(CLIENT_TABLE_NAME, CLIENT_TABLE_COLUMNS, null, null, null, null, null);
        try {
            if (!cursorQuery.moveToFirst()) {
                return arrayList;
            }
            int columnIndex = cursorQuery.getColumnIndex(CLIENT_CLIENT_ID_COLUMN);
            int columnIndex2 = cursorQuery.getColumnIndex("pendingid");
            do {
                long j = cursorQuery.getInt(columnIndex2);
                String string = cursorQuery.getString(columnIndex);
                if (j == downloadId) {
                    arrayList.add(new DownloadRecord(string, null));
                }
                ContentValues contentValuesByPendingId = getContentValuesByPendingId(getDb(context, string), downloadId);
                if (contentValuesByPendingId != null) {
                    arrayList.add(new DownloadRecord(string, contentValuesByPendingId));
                }
            } while (cursorQuery.moveToNext());
            return arrayList;
        } finally {
            cursorQuery.close();
        }
    }

    @Nullable
    public static ContentValues getContentValuesByWordListId(final SQLiteDatabase db, final String id, final int version) {
        Cursor cursorQuery = db.query("pendingUpdates", METADATA_TABLE_COLUMNS, "id= ? AND version= ? AND formatversion<= ?", new String[]{id, Integer.toString(version), Integer.toString(86736212)}, null, null, "formatversion DESC");
        if (cursorQuery == null) {
            return null;
        }
        try {
            return getFirstLineAsContentValues(cursorQuery);
        } finally {
            cursorQuery.close();
        }
    }

    public static ContentValues getContentValuesOfLatestAvailableWordlistById(final SQLiteDatabase db, final String id) {
        Cursor cursorQuery = db.query("pendingUpdates", METADATA_TABLE_COLUMNS, "id= ?", new String[]{id}, null, null, "version DESC", DictionaryHeader.ATTRIBUTE_VALUE_TRUE);
        if (cursorQuery == null) {
            return null;
        }
        try {
            return getFirstLineAsContentValues(cursorQuery);
        } finally {
            cursorQuery.close();
        }
    }

    public static Cursor queryInstalledOrDeletingOrAvailableDictionaryMetadata(final Context context, final String clientId) {
        return getDb(context, clientId).query("pendingUpdates", METADATA_TABLE_COLUMNS, "status = ? OR status = ? OR status = ?", new String[]{Integer.toString(3), Integer.toString(5), Integer.toString(1)}, null, null, "locale");
    }

    public static Cursor queryCurrentMetadata(final Context context, final String clientId) {
        return getDb(context, clientId).query("pendingUpdates", METADATA_TABLE_COLUMNS, null, null, null, null, "locale");
    }

    public static Cursor queryDictionaries(final Context context, final String clientId) {
        return getDb(context, clientId).query("pendingUpdates", DICTIONARIES_LIST_PUBLIC_COLUMNS, "locale != ?", new String[]{""}, null, null, "locale");
    }

    public static boolean deleteClient(final Context context, final String clientId) {
        SQLiteDatabase db = getDb(context, clientId);
        db.execSQL("DROP TABLE IF EXISTS pendingUpdates");
        db.execSQL(METADATA_TABLE_CREATE);
        return getDb(context, "").delete(CLIENT_TABLE_NAME, "clientid = ?", new String[]{clientId}) != 0;
    }

    public static void updateClientInfo(final Context context, final String clientId, final ContentValues values) {
        String asString = values.getAsString(CLIENT_CLIENT_ID_COLUMN);
        String asString2 = values.getAsString(CLIENT_METADATA_URI_COLUMN);
        String asString3 = values.getAsString(CLIENT_METADATA_ADDITIONAL_ID_COLUMN);
        if (TextUtils.isEmpty(asString) || asString2 == null || asString3 == null) {
            DebugLogUtils.l("Missing parameter for updateClientInfo");
            return;
        }
        if (!clientId.equals(asString)) {
            DebugLogUtils.l("Received an updateClientInfo request for ", clientId, " but the values contain a different ID : ", asString);
            return;
        }
        values.put("pendingid", (Integer) (-1));
        SQLiteDatabase db = getDb(context, "");
        if (-1 == db.insert(CLIENT_TABLE_NAME, null, values)) {
            db.update(CLIENT_TABLE_NAME, values, "clientid = ?", new String[]{clientId});
        }
    }

    public static Cursor queryClientIds(final Context context) {
        return getDb(context, null).query(CLIENT_TABLE_NAME, new String[]{CLIENT_CLIENT_ID_COLUMN}, null, null, null, null, null);
    }

    public static void registerMetadataDownloadId(final Context context, final String uri, final long downloadId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("pendingid", Long.valueOf(downloadId));
        contentValues.put(CLIENT_LAST_UPDATE_DATE_COLUMN, Long.valueOf(System.currentTimeMillis()));
        SQLiteDatabase db = getDb(context, "");
        Cursor cursorQueryClientIds = queryClientIds(context);
        if (cursorQueryClientIds == null) {
            return;
        }
        try {
            if (cursorQueryClientIds.moveToFirst()) {
                do {
                    String string = cursorQueryClientIds.getString(0);
                    if (getMetadataUriAsString(context, string).equals(uri)) {
                        db.update(CLIENT_TABLE_NAME, contentValues, "clientid = ?", new String[]{string});
                    }
                } while (cursorQueryClientIds.moveToNext());
            }
        } finally {
            cursorQueryClientIds.close();
        }
    }

    public static void markEntryAsFinishedDownloadingAndInstalled(final SQLiteDatabase db, final ContentValues r) {
        if (r.getAsInteger(TYPE_COLUMN).intValue() != 2) {
            return;
        }
        DebugLogUtils.l("Ended processing a wordlist");
        LinkedList linkedList = new LinkedList();
        Cursor cursorQuery = db.query("pendingUpdates", new String[]{LOCAL_FILENAME_COLUMN}, "locale = ? AND id = ? AND status = ?", new String[]{r.getAsString("locale"), r.getAsString(WORDLISTID_COLUMN), Integer.toString(3)}, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                int columnIndex = cursorQuery.getColumnIndex(LOCAL_FILENAME_COLUMN);
                do {
                    DebugLogUtils.l("Setting for removal", cursorQuery.getString(columnIndex));
                    linkedList.add(cursorQuery.getString(columnIndex));
                } while (cursorQuery.moveToNext());
            }
            cursorQuery.close();
            r.put("status", (Integer) 3);
            db.beginTransactionNonExclusive();
            db.delete("pendingUpdates", "id = ?", new String[]{r.getAsString(WORDLISTID_COLUMN)});
            db.insert("pendingUpdates", null, r);
            db.setTransactionSuccessful();
            db.endTransaction();
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                try {
                    new File((String) it.next()).delete();
                } catch (SecurityException unused) {
                }
            }
        } catch (Throwable th) {
            cursorQuery.close();
            throw th;
        }
    }

    public static void deleteDownloadingEntry(final SQLiteDatabase db, final long id) {
        db.delete("pendingUpdates", "pendingid = ? AND status = ?", new String[]{Long.toString(id), Integer.toString(2)});
    }

    public static void deleteEntry(final SQLiteDatabase db, final String id, final int version) {
        db.delete("pendingUpdates", "id = ? AND version = ?", new String[]{id, Integer.toString(version)});
    }

    private static void markEntryAs(final SQLiteDatabase db, final String id, final int version, final int status, final long downloadId) {
        ContentValues contentValuesByWordListId = getContentValuesByWordListId(db, id, version);
        contentValuesByWordListId.put("status", Integer.valueOf(status));
        if (-1 != downloadId) {
            contentValuesByWordListId.put("pendingid", Long.valueOf(downloadId));
        }
        db.update("pendingUpdates", contentValuesByWordListId, "id = ? AND version = ?", new String[]{id, Integer.toString(version)});
    }

    public static void markEntryAsEnabled(final SQLiteDatabase db, final String id, final int version) {
        markEntryAs(db, id, version, 3, -1L);
    }

    public static void markEntryAsDisabled(final SQLiteDatabase db, final String id, final int version) {
        markEntryAs(db, id, version, 4, -1L);
    }

    public static void markEntryAsAvailable(final SQLiteDatabase db, final String id, final int version) {
        markEntryAs(db, id, version, 1, -1L);
    }

    public static void markEntryAsDownloading(final SQLiteDatabase db, final String id, final int version, final long downloadId) {
        markEntryAs(db, id, version, 2, downloadId);
    }

    public static void markEntryAsDeleting(final SQLiteDatabase db, final String id, final int version) {
        markEntryAs(db, id, version, 5, -1L);
    }

    public static boolean maybeMarkEntryAsRetrying(final SQLiteDatabase db, final String id, final int version) {
        ContentValues contentValuesByWordListId = getContentValuesByWordListId(db, id, version);
        int iIntValue = contentValuesByWordListId.getAsInteger(RETRY_COUNT_COLUMN).intValue();
        if (iIntValue <= 1) {
            return false;
        }
        contentValuesByWordListId.put("status", (Integer) 6);
        contentValuesByWordListId.put(RETRY_COUNT_COLUMN, Integer.valueOf(iIntValue - 1));
        db.update("pendingUpdates", contentValuesByWordListId, "id = ? AND version = ?", new String[]{id, Integer.toString(version)});
        return true;
    }
}
