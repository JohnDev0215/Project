package com.android.providers.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.providers.contacts.util.PropertyUtils;

/* JADX INFO: loaded from: classes.dex */
public class CallLogDatabaseHelper {
    private static CallLogDatabaseHelper sInstance;
    private static CallLogDatabaseHelper sInstanceForShadow;
    private final Context mContext;
    private final OpenHelper mOpenHelper;

    private final class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
            super(context, str, cursorFactory, i);
            setIdleConnectionTimeout(30000L);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            PropertyUtils.createPropertiesTable(sQLiteDatabase);
            sQLiteDatabase.execSQL("CREATE TABLE calls (_id INTEGER PRIMARY KEY AUTOINCREMENT,number TEXT,presentation INTEGER NOT NULL DEFAULT 1,post_dial_digits TEXT NOT NULL DEFAULT '',via_number TEXT NOT NULL DEFAULT '',date INTEGER,duration INTEGER,data_usage INTEGER,type INTEGER,features INTEGER NOT NULL DEFAULT 0,subscription_component_name TEXT,subscription_id TEXT,phone_account_address TEXT,phone_account_hidden INTEGER NOT NULL DEFAULT 0,sub_id INTEGER DEFAULT -1,new INTEGER,name TEXT,numbertype INTEGER,numberlabel TEXT,countryiso TEXT,voicemail_uri TEXT,is_read INTEGER,geocoded_location TEXT,lookup_uri TEXT,matched_number TEXT,normalized_number TEXT,photo_id INTEGER NOT NULL DEFAULT 0,photo_uri TEXT,formatted_number TEXT,add_for_all_users INTEGER NOT NULL DEFAULT 1,last_modified INTEGER DEFAULT 0,call_screening_component_name TEXT,call_screening_app_name TEXT,block_reason INTEGER NOT NULL DEFAULT 0,_data TEXT,has_content INTEGER,mime_type TEXT,source_data TEXT,source_package TEXT,transcription TEXT,transcription_state INTEGER NOT NULL DEFAULT 0,state INTEGER,dirty INTEGER NOT NULL DEFAULT 0,deleted INTEGER NOT NULL DEFAULT 0,backed_up INTEGER NOT NULL DEFAULT 0,restored INTEGER NOT NULL DEFAULT 0,archived INTEGER NOT NULL DEFAULT 0,is_omtp_voicemail INTEGER NOT NULL DEFAULT 0);");
            sQLiteDatabase.execSQL("CREATE TABLE voicemail_status (_id INTEGER PRIMARY KEY AUTOINCREMENT,source_package TEXT NOT NULL,phone_account_component_name TEXT,phone_account_id TEXT,settings_uri TEXT,voicemail_access_uri TEXT,configuration_state INTEGER,data_channel_state INTEGER,notification_channel_state INTEGER,quota_occupied INTEGER DEFAULT -1,quota_total INTEGER DEFAULT -1,source_type TEXT);");
            CallLogDatabaseHelper.this.migrateFromLegacyTables(sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i < 2) {
                CallLogDatabaseHelper.this.upgradeToVersion2(sQLiteDatabase);
            }
            if (i < 3) {
                CallLogDatabaseHelper.this.upgradeToVersion3(sQLiteDatabase);
            }
            if (i < 4) {
                CallLogDatabaseHelper.this.upgradeToVersion4(sQLiteDatabase);
            }
            if (i < 5) {
                CallLogDatabaseHelper.this.upgradeToVersion5(sQLiteDatabase);
            }
            if (i < 6) {
                CallLogDatabaseHelper.this.upgradeToVersion6(sQLiteDatabase);
            }
            if (i < 7) {
                CallLogDatabaseHelper.this.upgradeToVersion7(sQLiteDatabase);
            }
            if (i < 8) {
                CallLogDatabaseHelper.this.upgradetoVersion8(sQLiteDatabase);
            }
        }
    }

    @VisibleForTesting
    CallLogDatabaseHelper(Context context, String str) {
        this.mContext = context;
        this.mOpenHelper = new OpenHelper(this.mContext, str, null, 8);
    }

    public static synchronized CallLogDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CallLogDatabaseHelper(context, "calllog.db");
        }
        return sInstance;
    }

    public static synchronized CallLogDatabaseHelper getInstanceForShadow(Context context) {
        if (sInstanceForShadow == null) {
            sInstanceForShadow = new CallLogDatabaseHelper(context.createDeviceProtectedStorageContext(), "calllog_shadow.db");
        }
        return sInstanceForShadow;
    }

    public SQLiteDatabase getReadableDatabase() {
        return this.mOpenHelper.getReadableDatabase();
    }

    public SQLiteDatabase getWritableDatabase() {
        return this.mOpenHelper.getWritableDatabase();
    }

    public String getProperty(String str, String str2) {
        return PropertyUtils.getProperty(getReadableDatabase(), str, str2);
    }

    public void setProperty(String str, String str2) {
        PropertyUtils.setProperty(getWritableDatabase(), str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeToVersion2(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD via_number TEXT NOT NULL DEFAULT ''");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeToVersion3(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE voicemail_status ADD source_type TEXT");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeToVersion4(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD backed_up INTEGER NOT NULL DEFAULT 0");
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD restored INTEGER NOT NULL DEFAULT 0");
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD archived INTEGER NOT NULL DEFAULT 0");
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD is_omtp_voicemail INTEGER NOT NULL DEFAULT 0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeToVersion5(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD transcription_state INTEGER NOT NULL DEFAULT 0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeToVersion6(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD call_screening_component_name TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD call_screening_app_name TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD block_reason INTEGER NOT NULL DEFAULT 0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeToVersion7(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD call_id_package_name TEXT NULL");
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD call_id_app_name TEXT NULL");
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD call_id_name TEXT NULL");
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD call_id_description TEXT NULL");
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD call_id_details TEXT NULL");
        sQLiteDatabase.execSQL("ALTER TABLE calls ADD call_id_nuisance_confidence INTEGER NULL");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradetoVersion8(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            sQLiteDatabase.execSQL("ALTER TABLE calls RENAME TO calls_old");
            sQLiteDatabase.execSQL("CREATE TABLE calls (_id INTEGER PRIMARY KEY AUTOINCREMENT,number TEXT,presentation INTEGER NOT NULL DEFAULT 1,post_dial_digits TEXT NOT NULL DEFAULT '',via_number TEXT NOT NULL DEFAULT '',date INTEGER,duration INTEGER,data_usage INTEGER,type INTEGER,features INTEGER NOT NULL DEFAULT 0,subscription_component_name TEXT,subscription_id TEXT,phone_account_address TEXT,phone_account_hidden INTEGER NOT NULL DEFAULT 0,sub_id INTEGER DEFAULT -1,new INTEGER,name TEXT,numbertype INTEGER,numberlabel TEXT,countryiso TEXT,voicemail_uri TEXT,is_read INTEGER,geocoded_location TEXT,lookup_uri TEXT,matched_number TEXT,normalized_number TEXT,photo_id INTEGER NOT NULL DEFAULT 0,photo_uri TEXT,formatted_number TEXT,add_for_all_users INTEGER NOT NULL DEFAULT 1,last_modified INTEGER DEFAULT 0,call_screening_component_name TEXT,call_screening_app_name TEXT,block_reason INTEGER NOT NULL DEFAULT 0,_data TEXT,has_content INTEGER,mime_type TEXT,source_data TEXT,source_package TEXT,transcription TEXT,transcription_state INTEGER NOT NULL DEFAULT 0,state INTEGER,dirty INTEGER NOT NULL DEFAULT 0,deleted INTEGER NOT NULL DEFAULT 0,backed_up INTEGER NOT NULL DEFAULT 0,restored INTEGER NOT NULL DEFAULT 0,archived INTEGER NOT NULL DEFAULT 0,is_omtp_voicemail INTEGER NOT NULL DEFAULT 0);");
            sQLiteDatabase.execSQL("INSERT INTO calls (_id, number, presentation, post_dial_digits, via_number, date, duration, data_usage, type, features, subscription_component_name, subscription_id, phone_account_address, phone_account_hidden, sub_id, new, name, numbertype, numberlabel, countryiso, voicemail_uri, is_read, geocoded_location, lookup_uri, matched_number, normalized_number, photo_id, photo_uri, formatted_number, add_for_all_users, last_modified, call_screening_component_name, call_screening_app_name, block_reason, _data, has_content, mime_type, source_data, source_package, transcription, transcription_state, state, dirty, deleted, backed_up, restored, archived, is_omtp_voicemail) SELECT _id, number, presentation, post_dial_digits, via_number, date, duration, data_usage, type, features, subscription_component_name, subscription_id, phone_account_address, phone_account_hidden, sub_id, new, name, numbertype, numberlabel, countryiso, voicemail_uri, is_read, geocoded_location, lookup_uri, matched_number, normalized_number, photo_id, photo_uri, formatted_number, add_for_all_users, last_modified, call_screening_component_name, call_screening_app_name, block_reason, _data, has_content, mime_type, source_data, source_package, transcription, transcription_state, state, dirty, deleted, backed_up, restored, archived, is_omtp_voicemail FROM calls_old");
            StringBuilder sb = new StringBuilder();
            sb.append("DROP TABLE ");
            sb.append("calls_old");
            sQLiteDatabase.execSQL(sb.toString());
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void migrateFromLegacyTables(SQLiteDatabase sQLiteDatabase) {
        SQLiteDatabase contactsWritableDatabaseForMigration = getContactsWritableDatabaseForMigration();
        if (contactsWritableDatabaseForMigration == null) {
            Log.w("CallLogDatabaseHelper", "Contacts DB == null, skipping migration. (running tests?)");
            return;
        }
        if ("1".equals(PropertyUtils.getProperty(sQLiteDatabase, "migrated", ""))) {
            return;
        }
        Log.i("CallLogDatabaseHelper", "Migrating from old tables...");
        contactsWritableDatabaseForMigration.beginTransaction();
        try {
            try {
            } catch (RuntimeException e) {
                Log.w("CallLogDatabaseHelper", "Exception caught during migration", e);
            }
            if (tableExists(contactsWritableDatabaseForMigration, "calls") && tableExists(contactsWritableDatabaseForMigration, "voicemail_status")) {
                sQLiteDatabase.beginTransaction();
                try {
                    ContentValues contentValues = new ContentValues();
                    Cursor cursorRawQuery = contactsWritableDatabaseForMigration.rawQuery("SELECT * FROM calls", null);
                    while (cursorRawQuery.moveToNext()) {
                        try {
                            contentValues.clear();
                            DatabaseUtils.cursorRowToContentValues(cursorRawQuery, contentValues);
                            sQLiteDatabase.insertOrThrow("calls", null, contentValues);
                        } finally {
                        }
                    }
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    cursorRawQuery = contactsWritableDatabaseForMigration.rawQuery("SELECT * FROM voicemail_status", null);
                    while (cursorRawQuery.moveToNext()) {
                        try {
                            contentValues.clear();
                            DatabaseUtils.cursorRowToContentValues(cursorRawQuery, contentValues);
                            sQLiteDatabase.insertOrThrow("voicemail_status", null, contentValues);
                        } finally {
                        }
                    }
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    contactsWritableDatabaseForMigration.execSQL("DROP TABLE calls;");
                    contactsWritableDatabaseForMigration.execSQL("DROP TABLE voicemail_status;");
                    PropertyUtils.setProperty(sQLiteDatabase, "call_log_last_synced", PropertyUtils.getProperty(contactsWritableDatabaseForMigration, "call_log_last_synced", null));
                    Log.i("CallLogDatabaseHelper", "Migration completed.");
                    sQLiteDatabase.setTransactionSuccessful();
                    sQLiteDatabase.endTransaction();
                    contactsWritableDatabaseForMigration.setTransactionSuccessful();
                    contactsWritableDatabaseForMigration.endTransaction();
                    PropertyUtils.setProperty(sQLiteDatabase, "migrated", "1");
                    return;
                } catch (Throwable th) {
                    sQLiteDatabase.endTransaction();
                    throw th;
                }
            }
            Log.i("CallLogDatabaseHelper", "Source tables don't exist.");
        } finally {
            contactsWritableDatabaseForMigration.endTransaction();
        }
    }

    @VisibleForTesting
    static boolean tableExists(SQLiteDatabase sQLiteDatabase, String str) {
        return DatabaseUtils.longForQuery(sQLiteDatabase, "select count(*) from sqlite_master where type='table' and name=?", new String[]{str}) > 0;
    }

    @VisibleForTesting
    SQLiteDatabase getContactsWritableDatabaseForMigration() {
        return ContactsDatabaseHelper.getInstance(this.mContext).getWritableDatabase();
    }

    public ArraySet<String> selectDistinctColumn(String str, String str2) {
        ArraySet<String> arraySet = new ArraySet<>();
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT DISTINCT " + str2 + " FROM " + str, null);
        try {
            cursorRawQuery.moveToPosition(-1);
            while (cursorRawQuery.moveToNext()) {
                if (!cursorRawQuery.isNull(0)) {
                    String string = cursorRawQuery.getString(0);
                    if (!TextUtils.isEmpty(string)) {
                        arraySet.add(string);
                    }
                }
            }
            return arraySet;
        } finally {
            cursorRawQuery.close();
        }
    }

    @VisibleForTesting
    void closeForTest() {
        this.mOpenHelper.close();
    }
}
