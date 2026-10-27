package com.android.car.developeroptions.homepage.contextualcards;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import com.android.settingslib.utils.ThreadUtils;

/* JADX INFO: loaded from: classes.dex */
public class CardContentProvider extends ContentProvider {
    private static final UriMatcher URI_MATCHER;
    private CardDatabaseHelper mDBHelper;
    public static final Uri REFRESH_CARD_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.homepage.CardContentProvider").appendPath("cards").build();
    public static final Uri DELETE_CARD_URI = new Uri.Builder().scheme("content").authority("com.android.car.developeroptions.homepage.CardContentProvider").appendPath("card_dismissed").build();

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        URI_MATCHER = uriMatcher;
        uriMatcher.addURI("com.android.car.developeroptions.homepage.CardContentProvider", "cards", 100);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mDBHelper = CardDatabaseHelper.getInstance(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        bulkInsert(uri, new ContentValues[]{contentValues});
        return uri;
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        SQLiteDatabase writableDatabase = this.mDBHelper.getWritableDatabase();
        try {
            maybeEnableStrictMode();
            String tableFromMatch = getTableFromMatch(uri);
            writableDatabase.beginTransaction();
            writableDatabase.delete(tableFromMatch, null, null);
            int i = 0;
            for (ContentValues contentValues : contentValuesArr) {
                if (writableDatabase.insert(tableFromMatch, null, contentValues) != -1) {
                    i++;
                } else {
                    Log.e("CardContentProvider", "The row " + contentValues.getAsString("name") + " insertion failed! Please check your data.");
                }
            }
            writableDatabase.setTransactionSuccessful();
            getContext().getContentResolver().notifyChange(uri, null);
            return i;
        } finally {
            writableDatabase.endTransaction();
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("delete operation not supported currently.");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("getType operation not supported currently.");
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        try {
            maybeEnableStrictMode();
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables(getTableFromMatch(uri));
            Cursor cursorQuery = sQLiteQueryBuilder.query(this.mDBHelper.getReadableDatabase(), strArr, str, strArr2, null, null, str2);
            cursorQuery.setNotificationUri(getContext().getContentResolver(), uri);
            return cursorQuery;
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("update operation not supported currently.");
    }

    void maybeEnableStrictMode() {
        if (Build.IS_DEBUGGABLE && ThreadUtils.isMainThread()) {
            enableStrictMode();
        }
    }

    void enableStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().build());
    }

    String getTableFromMatch(Uri uri) {
        if (URI_MATCHER.match(uri) == 100) {
            return "cards";
        }
        throw new IllegalArgumentException("Unknown Uri format: " + uri);
    }
}
