package com.android.providers.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.android.providers.contacts.ContactsDatabaseHelper;

/* JADX INFO: loaded from: classes.dex */
public class ProfileDatabaseHelper extends ContactsDatabaseHelper {
    private static ProfileDatabaseHelper sSingleton;

    @Override // com.android.providers.contacts.ContactsDatabaseHelper
    protected int dbForProfile() {
        return 1;
    }

    @Override // com.android.providers.contacts.ContactsDatabaseHelper
    protected void loadDatabaseCreationTime(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // com.android.providers.contacts.ContactsDatabaseHelper
    protected void postOnCreate() {
    }

    @Override // com.android.providers.contacts.ContactsDatabaseHelper
    protected void setDatabaseCreationTime(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // com.android.providers.contacts.ContactsDatabaseHelper
    protected void startListeningToDeviceConfigUpdates() {
    }

    @Override // com.android.providers.contacts.ContactsDatabaseHelper
    protected void updateUseStrictPhoneNumberComparison() {
    }

    public static ProfileDatabaseHelper getNewInstanceForTest(Context context, String str) {
        return new ProfileDatabaseHelper(context, str, false, true);
    }

    private ProfileDatabaseHelper(Context context, String str, boolean z, boolean z2) {
        super(context, str, z, z2);
    }

    public static synchronized ProfileDatabaseHelper getInstance(Context context) {
        if (sSingleton == null) {
            sSingleton = new ProfileDatabaseHelper(context, "profile.db", true, false);
        }
        return sSingleton;
    }

    @Override // com.android.providers.contacts.ContactsDatabaseHelper
    protected void initializeAutoIncrementSequences(SQLiteDatabase sQLiteDatabase) {
        for (String str : ContactsDatabaseHelper.Tables.SEQUENCE_TABLES) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", str);
            contentValues.put("seq", (Long) 9223372034707292160L);
            sQLiteDatabase.insert("sqlite_sequence", null, contentValues);
        }
    }
}
