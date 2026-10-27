package com.byd.database.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public class MyContentProvider extends ContentProvider {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static UriMatcher f3536b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f3537c;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        f3536b = uriMatcher;
        uriMatcher.addURI("com.android.bluetooth", "contactprovider", 0);
        f3536b.addURI("com.android.bluetooth", "contactprovider/#", 1);
        f3536b.addURI("com.android.bluetooth", "callrecordsprovider", 2);
        f3536b.addURI("com.android.bluetooth", "callrecordsprovider/#", 3);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.f3537c.getWritableDatabase();
        int iMatch = f3536b.match(uri);
        if (iMatch == 0) {
            return writableDatabase.delete("Book", str, strArr);
        }
        if (iMatch == 1) {
            return writableDatabase.delete("Book", "id = ?", new String[]{uri.getPathSegments().get(1)});
        }
        if (iMatch == 2) {
            return writableDatabase.delete("Category", str, strArr);
        }
        if (iMatch != 3) {
            return 0;
        }
        return writableDatabase.delete("Category", "id = ?", new String[]{uri.getPathSegments().get(1)});
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int iMatch = f3536b.match(uri);
        if (iMatch == 0) {
            return "vnd.android.cursor.dir/vnd.com.example.administrator.exp7.provider.book";
        }
        if (iMatch == 1) {
            return "vnd.android.cursor.item/vnd.com.example.administrator.exp7.provider.book";
        }
        if (iMatch == 2) {
            return "vnd.android.cursor.dir/vnd.com.example.administrator.exp7.provider.category";
        }
        if (iMatch != 3) {
            return null;
        }
        return "vnd.android.cursor.item/vnd.com.example.administrator.exp7.provider.category";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase writableDatabase = this.f3537c.getWritableDatabase();
        int iMatch = f3536b.match(uri);
        if (iMatch == 0 || iMatch == 1) {
            return Uri.parse("content://com.android.bluetooth/book/" + writableDatabase.insert("Book", null, contentValues));
        }
        if (iMatch != 2 && iMatch != 3) {
            return null;
        }
        return Uri.parse("content://com.android.bluetooth/category/" + writableDatabase.insert("Category", null, contentValues));
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.f3537c = new a(getContext());
        b.d(getContext()).f();
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase readableDatabase = this.f3537c.getReadableDatabase();
        int iMatch = f3536b.match(uri);
        if (iMatch == 0) {
            return readableDatabase.query("phone_book", strArr, str, strArr2, null, null, str2);
        }
        if (iMatch == 1) {
            return readableDatabase.query("phone_book", strArr, "id = ?", new String[]{uri.getPathSegments().get(1)}, null, null, str2);
        }
        if (iMatch == 2) {
            return readableDatabase.query("call_log_book", strArr, str, strArr2, null, null, str2);
        }
        if (iMatch != 3) {
            return null;
        }
        return readableDatabase.query("call_log_book", strArr, "id = ?", new String[]{uri.getPathSegments().get(1)}, null, null, str2);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.f3537c.getWritableDatabase();
        int iMatch = f3536b.match(uri);
        if (iMatch == 0) {
            return writableDatabase.update("phone_book", contentValues, str, strArr);
        }
        if (iMatch == 1) {
            return writableDatabase.update("phone_book", contentValues, "id = ?", new String[]{uri.getPathSegments().get(1)});
        }
        if (iMatch == 2) {
            return writableDatabase.update("call_log_book", contentValues, str, strArr);
        }
        if (iMatch != 3) {
            return 0;
        }
        return writableDatabase.update("call_log_book", contentValues, "id = ?", new String[]{uri.getPathSegments().get(1)});
    }
}
