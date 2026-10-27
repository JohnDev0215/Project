package com.android.providers.contacts;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.VoicemailContract;
import android.util.ArraySet;
import android.util.Log;
import com.android.common.content.ProjectionMap;
import com.android.providers.contacts.VoicemailContentProvider;
import com.android.providers.contacts.util.CloseUtils;
import com.android.providers.contacts.util.DbQueryUtils;
import com.google.common.collect.ImmutableSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class VoicemailContentTable implements VoicemailTable$Delegate {
    public static final ImmutableSet<String> ALLOWED_COLUMNS;
    private static final String[] FILENAME_ONLY_PROJECTION = {"_data"};
    private final CallLogInsertionHelper mCallLogInsertionHelper;
    private final Context mContext;
    private final CallLogDatabaseHelper mDbHelper;
    private final VoicemailTable$DelegateHelper mDelegateHelper;
    private final String mTableName;
    private final ProjectionMap mVoicemailProjectionMap;

    static {
        ImmutableSet.Builder builder = new ImmutableSet.Builder();
        builder.add("_id");
        builder.add("number");
        builder.add("date");
        builder.add("duration");
        builder.add("new");
        builder.add("is_read");
        builder.add("transcription");
        builder.add("transcription_state");
        builder.add("state");
        builder.add("source_data");
        builder.add("source_package");
        builder.add("has_content");
        builder.add("subscription_component_name");
        builder.add("subscription_id");
        builder.add("mime_type");
        builder.add("dirty");
        builder.add("deleted");
        builder.add("last_modified");
        builder.add("backed_up");
        builder.add("restored");
        builder.add("archived");
        builder.add("is_omtp_voicemail");
        builder.add("_display_name");
        builder.add("_size");
        ALLOWED_COLUMNS = builder.build();
    }

    public VoicemailContentTable(String str, Context context, CallLogDatabaseHelper callLogDatabaseHelper, VoicemailTable$DelegateHelper voicemailTable$DelegateHelper, CallLogInsertionHelper callLogInsertionHelper) {
        this.mTableName = str;
        this.mContext = context;
        this.mDbHelper = callLogDatabaseHelper;
        this.mDelegateHelper = voicemailTable$DelegateHelper;
        ProjectionMap.Builder builder = new ProjectionMap.Builder();
        builder.add("_id");
        builder.add("number");
        builder.add("date");
        builder.add("duration");
        builder.add("new");
        builder.add("is_read");
        builder.add("transcription");
        builder.add("transcription_state");
        builder.add("state");
        builder.add("source_data");
        builder.add("source_package");
        builder.add("has_content");
        builder.add("mime_type");
        builder.add("_data");
        builder.add("subscription_component_name");
        builder.add("subscription_id");
        builder.add("dirty");
        builder.add("deleted");
        builder.add("last_modified");
        builder.add("backed_up");
        builder.add("restored");
        builder.add("archived");
        builder.add("is_omtp_voicemail");
        builder.add("_display_name", createDisplayName(context));
        builder.add("_size", "NULL");
        this.mVoicemailProjectionMap = builder.build();
        this.mCallLogInsertionHelper = callLogInsertionHelper;
    }

    private static String createDisplayName(Context context) {
        return DatabaseUtils.sqlEscapeString(context.getString(R.string.voicemail_from_column)) + " || number";
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public Uri insert(VoicemailContentProvider.UriData uriData, ContentValues contentValues) {
        return insertRow(createDatabaseModifier(this.mDbHelper.getWritableDatabase()), uriData, contentValues);
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public int bulkInsert(VoicemailContentProvider.UriData uriData, ContentValues[] contentValuesArr) {
        DatabaseModifier databaseModifierCreateDatabaseModifier = createDatabaseModifier(this.mDbHelper.getWritableDatabase());
        databaseModifierCreateDatabaseModifier.startBulkOperation();
        int i = 0;
        for (ContentValues contentValues : contentValuesArr) {
            if (insertRow(databaseModifierCreateDatabaseModifier, uriData, contentValues) != null) {
                i++;
            }
            if (i % 50 == 0) {
                databaseModifierCreateDatabaseModifier.yieldBulkOperation();
            }
        }
        databaseModifierCreateDatabaseModifier.finishBulkOperation();
        return i;
    }

    private Uri insertRow(DatabaseModifier databaseModifier, VoicemailContentProvider.UriData uriData, ContentValues contentValues) {
        DbQueryUtils.checkForSupportedColumns(this.mVoicemailProjectionMap, contentValues);
        ContentValues contentValues2 = new ContentValues(contentValues);
        checkInsertSupported(uriData);
        this.mDelegateHelper.checkAndAddSourcePackageIntoValues(uriData, contentValues2);
        this.mCallLogInsertionHelper.addComputedValues(contentValues2);
        contentValues2.put("_data", generateDataFile());
        contentValues2.put("type", (Integer) 4);
        boolean zBooleanValue = contentValues.containsKey("is_read") ? contentValues.getAsBoolean("is_read").booleanValue() : false;
        if (!contentValues.containsKey("new")) {
            contentValues2.put("new", Boolean.valueOf(!zBooleanValue));
        }
        SQLiteDatabase writableDatabase = this.mDbHelper.getWritableDatabase();
        long jInsert = databaseModifier.insert(this.mTableName, null, contentValues2);
        if (jInsert <= 0) {
            return null;
        }
        Uri uriWithAppendedId = ContentUris.withAppendedId(uriData.getUri(), jInsert);
        updateVoicemailUri(writableDatabase, uriWithAppendedId);
        return uriWithAppendedId;
    }

    private void checkInsertSupported(VoicemailContentProvider.UriData uriData) {
        if (uriData.hasId()) {
            throw new UnsupportedOperationException(String.format("Cannot insert URI: %s. Inserted URIs should not contain an id.", uriData.getUri()));
        }
    }

    private String generateDataFile() {
        try {
            return File.createTempFile("voicemail", "", this.mContext.getDir("voicemail-data", 0)).getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException("unable to create temp file", e);
        }
    }

    private void updateVoicemailUri(SQLiteDatabase sQLiteDatabase, Uri uri) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("voicemail_uri", uri.toString());
        sQLiteDatabase.update(this.mTableName, contentValues, VoicemailContentProvider.UriData.createUriData(uri).getWhereClause(), null);
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public int delete(VoicemailContentProvider.UriData uriData, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mDbHelper.getWritableDatabase();
        String strConcatenateClauses = DbQueryUtils.concatenateClauses(str, uriData.getWhereClause(), getCallTypeClause());
        Cursor cursorQuery = null;
        try {
            cursorQuery = query(uriData, FILENAME_ONLY_PROJECTION, str, strArr, null);
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.getString(0);
                if (string == null) {
                    Log.w("VmContentProvider", "No filename for uri " + uriData.getUri() + ", cannot delete file");
                } else {
                    File file = new File(string);
                    if (file.exists() && !file.delete()) {
                        Log.e("VmContentProvider", "Failed to delete file: " + file.getAbsolutePath());
                    }
                }
            }
            CloseUtils.closeQuietly(cursorQuery);
            return createDatabaseModifier(writableDatabase).delete(this.mTableName, strConcatenateClauses, strArr);
        } catch (Throwable th) {
            CloseUtils.closeQuietly(cursorQuery);
            throw th;
        }
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public Cursor query(VoicemailContentProvider.UriData uriData, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(this.mTableName);
        sQLiteQueryBuilder.setProjectionMap(this.mVoicemailProjectionMap);
        sQLiteQueryBuilder.setStrict(true);
        Cursor cursorQuery = sQLiteQueryBuilder.query(this.mDbHelper.getReadableDatabase(), strArr, DbQueryUtils.concatenateClauses(str, uriData.getWhereClause(), getCallTypeClause()), strArr2, null, null, str2);
        if (cursorQuery != null) {
            cursorQuery.setNotificationUri(this.mContext.getContentResolver(), VoicemailContract.Voicemails.CONTENT_URI);
        }
        return cursorQuery;
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public int update(VoicemailContentProvider.UriData uriData, ContentValues contentValues, String str, String[] strArr) {
        DbQueryUtils.checkForSupportedColumns(ALLOWED_COLUMNS, contentValues, "Updates are not allowed.");
        checkUpdateSupported(uriData);
        return createDatabaseModifier(this.mDbHelper.getWritableDatabase()).update(uriData.getUri(), this.mTableName, contentValues, DbQueryUtils.concatenateClauses(str, uriData.getWhereClause(), getCallTypeClause()), strArr);
    }

    private void checkUpdateSupported(VoicemailContentProvider.UriData uriData) {
        if (!uriData.hasId()) {
            throw new UnsupportedOperationException(String.format("Cannot update URI: %s.  Bulk update not supported", uriData.getUri()));
        }
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public String getType(VoicemailContentProvider.UriData uriData) {
        return uriData.hasId() ? "vnd.android.cursor.item/voicemail" : "vnd.android.cursor.dir/voicemails";
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public ParcelFileDescriptor openFile(VoicemailContentProvider.UriData uriData, String str) throws FileNotFoundException {
        return this.mDelegateHelper.openDataFile(uriData, str);
    }

    @Override // com.android.providers.contacts.VoicemailTable$Delegate
    public ArraySet<String> getSourcePackages() {
        return this.mDbHelper.selectDistinctColumn(this.mTableName, "source_package");
    }

    private String getCallTypeClause() {
        return DbQueryUtils.getEqualityClause("type", 4L);
    }

    private DatabaseModifier createDatabaseModifier(SQLiteDatabase sQLiteDatabase) {
        return new DbModifierWithNotification(this.mTableName, sQLiteDatabase, this.mContext);
    }
}
