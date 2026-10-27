package com.android.providers.telephony;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class SmsChangesProvider extends ContentProvider {
    private static final UriMatcher sURLMatcher;
    private SQLiteOpenHelper mCeOpenHelper;

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        setAppOps(14, 15);
        this.mCeOpenHelper = MmsSmsDatabaseHelper.getInstanceForCe(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (!isAutoFeatureSet() || !isUrlSupported(uri)) {
            return null;
        }
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("sms_changes");
        return sQLiteQueryBuilder.query(this.mCeOpenHelper.getReadableDatabase(), strArr, str, strArr2, null, null, null);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        if (isAutoFeatureSet() && isUrlSupported(uri)) {
            return this.mCeOpenHelper.getWritableDatabase().delete("sms_changes", null, null);
        }
        return 0;
    }

    private boolean isUrlSupported(Uri uri) {
        if (sURLMatcher.match(uri) == 0) {
            return true;
        }
        Log.e("SmsChangesProvider", "Invalid or Unsupported request: " + uri);
        return false;
    }

    private boolean isAutoFeatureSet() {
        return getContext().getPackageManager().hasSystemFeature("android.hardware.type.automotive");
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        sURLMatcher = uriMatcher;
        uriMatcher.addURI("sms-changes", null, 0);
    }
}
