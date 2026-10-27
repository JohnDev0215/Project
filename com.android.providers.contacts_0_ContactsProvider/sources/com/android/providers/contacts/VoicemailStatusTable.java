package com.android.providers.contacts;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.VoicemailContract;
import android.util.ArraySet;
import com.android.common.content.ProjectionMap;
import com.android.providers.contacts.VoicemailContentProvider;
import com.android.providers.contacts.util.DbQueryUtils;

/* JADX INFO: loaded from: classes.dex */
public class VoicemailStatusTable implements VoicemailTable$Delegate {
    private static final Object DATABASE_LOCK;
    private static final ProjectionMap sStatusProjectionMap;
    private final Context mContext;
    private final CallLogDatabaseHelper mDbHelper;
    private final VoicemailTable$DelegateHelper mDelegateHelper;
    private final String mTableName;

    static {
        ProjectionMap.Builder builder = new ProjectionMap.Builder();
        builder.add("_id");
        builder.add("phone_account_component_name");
        builder.add("phone_account_id");
        builder.add("configuration_state");
        builder.add("data_channel_state");
        builder.add("notification_channel_state");
        builder.add("settings_uri");
        builder.add("source_package");
        builder.add("voicemail_access_uri");
        builder.add("quota_occupied");
        builder.add("quota_total");
        builder.add("source_type");
        sStatusProjectionMap = builder.build();
        DATABASE_LOCK = new Object();
    }

    public VoicemailStatusTable(String str, Context context, CallLogDatabaseHelper callLogDatabaseHelper, VoicemailTable$DelegateHelper voicemailTable$DelegateHelper) {
        this.mTableName = str;
        this.mContext = context;
        this.mDbHelper = callLogDatabaseHelper;
        this.mDelegateHelper = voicemailTable$DelegateHelper;
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public Uri insert(VoicemailContentProvider.UriData uriData, ContentValues contentValues) {
        synchronized (DATABASE_LOCK) {
            SQLiteDatabase writableDatabase = this.mDbHelper.getWritableDatabase();
            String whereClause = uriData.getWhereClause();
            if (createDatabaseModifier(writableDatabase).update(uriData.getUri(), this.mTableName, contentValues, whereClause, null) != 0) {
                Cursor cursorQuery = writableDatabase.query(this.mTableName, new String[]{"_id"}, whereClause, null, null, null, null);
                cursorQuery.moveToFirst();
                int i = cursorQuery.getInt(0);
                cursorQuery.close();
                return ContentUris.withAppendedId(uriData.getUri(), i);
            }
            ContentValues contentValues2 = new ContentValues(contentValues);
            this.mDelegateHelper.checkAndAddSourcePackageIntoValues(uriData, contentValues2);
            long jInsert = createDatabaseModifier(writableDatabase).insert(this.mTableName, null, contentValues2);
            if (jInsert <= 0) {
                return null;
            }
            return ContentUris.withAppendedId(uriData.getUri(), jInsert);
        }
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public int bulkInsert(VoicemailContentProvider.UriData uriData, ContentValues[] contentValuesArr) {
        int i = 0;
        for (ContentValues contentValues : contentValuesArr) {
            if (insert(uriData, contentValues) != null) {
                i++;
            }
        }
        return i;
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public int delete(VoicemailContentProvider.UriData uriData, String str, String[] strArr) {
        int iDelete;
        synchronized (DATABASE_LOCK) {
            iDelete = createDatabaseModifier(this.mDbHelper.getWritableDatabase()).delete(this.mTableName, DbQueryUtils.concatenateClauses(str, uriData.getWhereClause()), strArr);
        }
        return iDelete;
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public Cursor query(VoicemailContentProvider.UriData uriData, String[] strArr, String str, String[] strArr2, String str2) {
        Cursor cursorQuery;
        synchronized (DATABASE_LOCK) {
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables(this.mTableName);
            sQLiteQueryBuilder.setProjectionMap(sStatusProjectionMap);
            sQLiteQueryBuilder.setStrict(true);
            cursorQuery = sQLiteQueryBuilder.query(this.mDbHelper.getReadableDatabase(), strArr, DbQueryUtils.concatenateClauses(str, uriData.getWhereClause()), strArr2, null, null, str2);
            if (cursorQuery != null) {
                cursorQuery.setNotificationUri(this.mContext.getContentResolver(), VoicemailContract.Status.CONTENT_URI);
            }
        }
        return cursorQuery;
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public int update(VoicemailContentProvider.UriData uriData, ContentValues contentValues, String str, String[] strArr) {
        int iUpdate;
        synchronized (DATABASE_LOCK) {
            iUpdate = createDatabaseModifier(this.mDbHelper.getWritableDatabase()).update(uriData.getUri(), this.mTableName, contentValues, DbQueryUtils.concatenateClauses(str, uriData.getWhereClause()), strArr);
        }
        return iUpdate;
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public String getType(VoicemailContentProvider.UriData uriData) {
        return uriData.hasId() ? "vnd.android.cursor.item/voicemail.source.status" : "vnd.android.cursor.dir/voicemail.source.status";
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public ParcelFileDescriptor openFile(VoicemailContentProvider.UriData uriData, String str) {
        throw new UnsupportedOperationException("File operation is not supported for status table");
    }

    private DatabaseModifier createDatabaseModifier(SQLiteDatabase sQLiteDatabase) {
        return new DbModifierWithNotification(this.mTableName, sQLiteDatabase, this.mContext);
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public ArraySet<String> getSourcePackages() {
        return this.mDbHelper.selectDistinctColumn(this.mTableName, "source_package");
    }
}
