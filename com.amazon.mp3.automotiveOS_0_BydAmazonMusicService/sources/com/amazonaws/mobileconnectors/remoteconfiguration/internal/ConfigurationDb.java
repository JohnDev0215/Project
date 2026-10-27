package com.amazonaws.mobileconnectors.remoteconfiguration.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
import com.amazonaws.mobileconnectors.remoteconfiguration.exceptions.ConfigurationNotFoundException;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.gear.Checks;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfigurationImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes5.dex */
public class ConfigurationDb {
    private static final int COL_ENTITY_TAG = 2;
    private static final int COL_ORIGIN = 3;
    private static final int COL_TIMESTAMP = 1;
    private static final int COL_VALUE = 0;
    private static final int DATABASE_VERSION = 2;
    private static final String DB_NAME = "ConfigurationCache.db";
    private static final String SQL_CREATE_CONFIGURATION_TABLE = "CREATE TABLE configuration (_id INTEGER PRIMARY KEY, json TEXT, origin INTEGER NOT NULL CHECK (origin IN (1,2,3)), timestamp INTEGER, entity_tag TEXT);";
    private static final String TAG = "ConfigurationDb";
    private final DBHelper dbHelper;
    private SQLiteDatabase mDatabase;
    private final AtomicInteger openCounter = new AtomicInteger(0);
    private static final String[] PROJECTION_CONFIGURATION = {"json", TetheredMessageKey.timestamp};
    private static final String[] PROJECTION_REMOTE_CONFIGURATION = {"json", TetheredMessageKey.timestamp, "entity_tag", "origin"};
    private static final Map<String, ConfigurationDb> INSTANCE_MAP = new HashMap();

    public static ConfigurationDb getOrCreateInstance(Context context, String str) {
        ConfigurationDb configurationDb;
        String strReplace = str.replace("/", "");
        Map<String, ConfigurationDb> map = INSTANCE_MAP;
        synchronized (map) {
            configurationDb = map.get(strReplace);
            if (configurationDb == null) {
                DBHelper dBHelper = new DBHelper(context, strReplace);
                try {
                    dBHelper.getWritableDatabase();
                } catch (DowngradeException unused) {
                    context.deleteDatabase(DBHelper.getDbFileNameFor(strReplace));
                    dBHelper = new DBHelper(context, strReplace);
                }
                ConfigurationDb configurationDb2 = new ConfigurationDb(dBHelper);
                INSTANCE_MAP.put(strReplace, configurationDb2);
                configurationDb = configurationDb2;
            }
        }
        return configurationDb;
    }

    ConfigurationDb(DBHelper dBHelper) {
        this.dbHelper = dBHelper;
    }

    private synchronized void closeDatabase() {
        SQLiteDatabase sQLiteDatabase;
        if (this.openCounter.decrementAndGet() == 0 && (sQLiteDatabase = this.mDatabase) != null) {
            sQLiteDatabase.close();
        }
    }

    public void saveConfiguration(RemoteConfiguration remoteConfiguration) {
        if (remoteConfiguration == null) {
            throw new NullPointerException("The RemoteConfiguration may not be null");
        }
        if (remoteConfiguration.getConfiguration() == null) {
            throw new NullPointerException("The contained Configuration may not be null");
        }
        if (remoteConfiguration.getConfiguration().getTimestamp() == null) {
            throw new NullPointerException("The Configuration's timestamp may not be null");
        }
        saveConfiguration(remoteConfiguration.getConfiguration().getAsJsonString(), remoteConfiguration.getOrigin(), Long.valueOf(remoteConfiguration.getConfiguration().getTimestamp().getTime()), remoteConfiguration.getEntityTag());
    }

    protected synchronized void saveConfiguration(String str, int i, Long l, String str2) {
        Checks.checkNotNull(str, "configuration cannot be null");
        Checks.checkNotNull(l, "timestamp cannot be null for non-default configuration");
        SQLiteDatabase sQLiteDatabaseOpenDatabase = null;
        try {
            sQLiteDatabaseOpenDatabase = openDatabase();
            sQLiteDatabaseOpenDatabase.beginTransaction();
            saveConfigurationPrivate(sQLiteDatabaseOpenDatabase, str, i, l, str2);
            sQLiteDatabaseOpenDatabase.setTransactionSuccessful();
            sQLiteDatabaseOpenDatabase.endTransaction();
            closeDatabase();
        } catch (Throwable th) {
            if (sQLiteDatabaseOpenDatabase != null) {
                sQLiteDatabaseOpenDatabase.endTransaction();
            }
            closeDatabase();
            throw th;
        }
    }

    private void saveConfigurationPrivate(SQLiteDatabase sQLiteDatabase, String str, int i, Long l, String str2) {
        ContentValues contentValues = getContentValues(str, Integer.valueOf(i), l, str2);
        if (hasConfiguration(sQLiteDatabase)) {
            int iUpdate = sQLiteDatabase.update("configuration", contentValues, null, null);
            if (iUpdate != 1) {
                throw new IllegalStateException("Updated " + iUpdate + " rows while was intending to update one and only one row in configuration");
            }
            Log.d(TAG, "Updated 1 row in configuration table,\n" + contentValues);
            return;
        }
        sQLiteDatabase.insertOrThrow("configuration", null, contentValues);
        Log.d(TAG, "Inserted 1 row into configuration table,\n" + contentValues);
    }

    private boolean hasConfiguration(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("configuration", new String[]{"_id"}, null, null, null, null, null, "1");
        if (cursorQuery == null) {
            return false;
        }
        boolean zMoveToFirst = cursorQuery.moveToFirst();
        cursorQuery.close();
        return zMoveToFirst;
    }

    public synchronized Configuration readConfiguration() throws ConfigurationNotFoundException {
        SQLiteDatabase sQLiteDatabaseOpenDatabase;
        Configuration configurationConfigurationFromCursor;
        Cursor cursor = null;
        try {
            try {
                sQLiteDatabaseOpenDatabase = openDatabase();
            } catch (Throwable th) {
                th = th;
                sQLiteDatabaseOpenDatabase = null;
            }
            try {
                sQLiteDatabaseOpenDatabase.beginTransaction();
                Cursor cursorQuery = sQLiteDatabaseOpenDatabase.query("configuration", PROJECTION_CONFIGURATION, null, null, null, null, null);
                sQLiteDatabaseOpenDatabase.setTransactionSuccessful();
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    configurationConfigurationFromCursor = configurationFromCursor(cursorQuery);
                    cursorQuery.close();
                    sQLiteDatabaseOpenDatabase.endTransaction();
                    closeDatabase();
                } else {
                    throw new ConfigurationNotFoundException("Configuration not found");
                }
            } catch (Throwable th2) {
                th = th2;
                if (0 != 0) {
                    cursor.close();
                }
                if (sQLiteDatabaseOpenDatabase != null) {
                    sQLiteDatabaseOpenDatabase.endTransaction();
                }
                closeDatabase();
                throw th;
            }
        } catch (Throwable th3) {
            throw th3;
        }
        return configurationConfigurationFromCursor;
    }

    public synchronized RemoteConfiguration readRemoteConfiguration(String str) {
        SQLiteDatabase sQLiteDatabaseOpenDatabase;
        Cursor cursor = null;
        try {
            try {
                sQLiteDatabaseOpenDatabase = openDatabase();
                try {
                    sQLiteDatabaseOpenDatabase.beginTransaction();
                    Cursor cursorQuery = sQLiteDatabaseOpenDatabase.query("configuration", PROJECTION_REMOTE_CONFIGURATION, null, null, null, null, null);
                    try {
                        sQLiteDatabaseOpenDatabase.setTransactionSuccessful();
                        if (cursorQuery != null && cursorQuery.moveToFirst()) {
                            RemoteConfiguration remoteConfigurationRemoteConfigurationFromCursor = remoteConfigurationFromCursor(cursorQuery, str);
                            cursorQuery.close();
                            sQLiteDatabaseOpenDatabase.endTransaction();
                            closeDatabase();
                            return remoteConfigurationRemoteConfigurationFromCursor;
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        sQLiteDatabaseOpenDatabase.endTransaction();
                        closeDatabase();
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabaseOpenDatabase != null) {
                            sQLiteDatabaseOpenDatabase.endTransaction();
                        }
                        closeDatabase();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                sQLiteDatabaseOpenDatabase = null;
            }
        } catch (Throwable th4) {
            throw th4;
        }
    }

    private RemoteConfiguration remoteConfigurationFromCursor(Cursor cursor, String str) {
        if (cursor == null) {
            return null;
        }
        return new RemoteConfigurationImpl(configurationFromCursor(cursor), str, cursor.getInt(3), cursor.getString(2), false);
    }

    private Configuration configurationFromCursor(Cursor cursor) {
        if (cursor == null || cursor.getString(0) == null) {
            throw new ConfigurationNotFoundException("Configuration not found");
        }
        return new ConfigurationImpl(cursor.getString(0), cursor.isNull(1) ? null : new Date(cursor.getLong(1)));
    }

    private ContentValues getContentValues(String str, Integer num, Long l, String str2) {
        ContentValues contentValues = new ContentValues();
        if (str != null) {
            contentValues.put("json", str);
        } else {
            contentValues.putNull("json");
        }
        if (num != null) {
            contentValues.put("origin", num);
        } else {
            contentValues.putNull("origin");
        }
        if (l != null) {
            contentValues.put(TetheredMessageKey.timestamp, l);
        } else {
            contentValues.putNull(TetheredMessageKey.timestamp);
        }
        if (str2 != null) {
            contentValues.put("entity_tag", str2);
        } else {
            contentValues.putNull("entity_tag");
        }
        return contentValues;
    }

    private static class ConfigurationTable implements BaseColumns {
        private static final String COLUMN_ENTITY_TAG = "entity_tag";
        private static final String COLUMN_JSON = "json";
        private static final String COLUMN_ORIGIN = "origin";
        private static final String COLUMN_TIMESTAMP = "timestamp";
        private static final String TABLE_NAME = "configuration";

        private ConfigurationTable() {
        }
    }

    static class DBHelper extends SQLiteOpenHelper {
        private final File mDbFile;

        protected DBHelper(Context context, String str) {
            super(context, getDbFileNameFor(str), (SQLiteDatabase.CursorFactory) null, 2);
            this.mDbFile = context.getDatabasePath(getDbFileNameFor(str));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String getDbFileNameFor(String str) {
            return str + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + ConfigurationDb.DB_NAME;
        }

        public File getDbFile() {
            return this.mDbFile;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Log.i(ConfigurationDb.TAG, "Creating Configuration DB version 2");
            setupDb(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i == 1 && i2 == 2) {
                Log.i(ConfigurationDb.TAG, "Upgrading database schema from " + i + " to " + i2);
                sQLiteDatabase.execSQL("DROP INDEX e_tag_idx_configuration;");
                sQLiteDatabase.execSQL("DROP INDEX configuration_type_idx_configuration;");
                sQLiteDatabase.execSQL("ALTER TABLE configuration RENAME TO configuration_old;");
                sQLiteDatabase.execSQL(ConfigurationDb.SQL_CREATE_CONFIGURATION_TABLE);
                sQLiteDatabase.execSQL("INSERT INTO configuration(_id, json, origin, entity_tag, timestamp) SELECT _id, value, CASE WHEN e_tag IS NULL THEN 1 ELSE 2 END AS origin, e_tag, last_load_time FROM configuration_old WHERE configuration_type = 1;");
                sQLiteDatabase.execSQL("DROP TABLE configuration_old;");
                Log.d(ConfigurationDb.TAG, "Database upgrade completed");
                return;
            }
            throw new IllegalArgumentException("Illegal upgrade: " + i + " to " + i2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            throw new DowngradeException();
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            super.onOpen(sQLiteDatabase);
            sQLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
        }

        private void setupDb(SQLiteDatabase sQLiteDatabase) {
            try {
                ArrayList<String> arrayList = new ArrayList();
                arrayList.add(ConfigurationDb.SQL_CREATE_CONFIGURATION_TABLE);
                sQLiteDatabase.beginTransaction();
                try {
                    for (String str : arrayList) {
                        sQLiteDatabase.execSQL(str);
                        Log.d(ConfigurationDb.TAG, "Executed sql, \n" + str);
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                    Log.i(ConfigurationDb.TAG, "Created tables for version 2");
                } catch (Throwable th) {
                    sQLiteDatabase.endTransaction();
                    throw th;
                }
            } catch (Throwable th2) {
                if (1 != 0) {
                    boolean zDelete = this.mDbFile.delete();
                    String str2 = ConfigurationDb.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed creating tables in SyncDB. Attempt to delete SyncDB ");
                    sb.append(zDelete ? "succeeded" : "failed");
                    Log.e(str2, sb.toString());
                }
                throw th2;
            }
        }
    }

    protected synchronized SQLiteDatabase openDatabase() {
        if (this.openCounter.incrementAndGet() == 1) {
            this.mDatabase = this.dbHelper.getWritableDatabase();
        }
        return this.mDatabase;
    }

    public synchronized void deleteConfiguration() {
        SQLiteDatabase sQLiteDatabaseOpenDatabase;
        Throwable th;
        try {
            sQLiteDatabaseOpenDatabase = openDatabase();
        } catch (Throwable th2) {
            sQLiteDatabaseOpenDatabase = null;
            th = th2;
        }
        try {
            sQLiteDatabaseOpenDatabase.beginTransaction();
            sQLiteDatabaseOpenDatabase.delete("configuration", null, null);
            Log.d(TAG, "Deleted rows from configuration table");
            sQLiteDatabaseOpenDatabase.setTransactionSuccessful();
            sQLiteDatabaseOpenDatabase.endTransaction();
            closeDatabase();
        } catch (Throwable th3) {
            th = th3;
            if (sQLiteDatabaseOpenDatabase != null) {
                sQLiteDatabaseOpenDatabase.endTransaction();
            }
            closeDatabase();
            throw th;
        }
    }
}
