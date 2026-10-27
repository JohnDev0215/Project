package com.byd.automultipletheme.common.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.byd.automultipletheme.common.LogUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OpenContentProviders.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J,\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\bH\u0016J1\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0010\u0010\u0013\u001a\f\u0012\u0006\b\u0001\u0012\u00020\n\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001a\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016JO\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0010\u001a\u00020\u00112\u0010\u0010\u001e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\n\u0018\u00010\u00142\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0010\u0010\u0013\u001a\f\u0012\u0006\b\u0001\u0012\u00020\n\u0018\u00010\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0002\u0010 J;\u0010!\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0010\u0010\u0013\u001a\f\u0012\u0006\b\u0001\u0012\u00020\n\u0018\u00010\u0014H\u0016¢\u0006\u0002\u0010\"R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/byd/automultipletheme/common/db/OpenContentProviders;", "Landroid/content/ContentProvider;", "()V", "databaseOpenHelper", "Lcom/byd/automultipletheme/common/db/DatabaseOpenHelper;", "uriMatcher", "Landroid/content/UriMatcher;", NotificationCompat.CATEGORY_CALL, "Landroid/os/Bundle;", "authority", "", "method", "arg", "extras", "delete", "", "uri", "Landroid/net/Uri;", "selection", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "values", "Landroid/content/ContentValues;", "onCreate", "", "query", "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class OpenContentProviders extends ContentProvider {
    private DatabaseOpenHelper databaseOpenHelper;
    private final UriMatcher uriMatcher;

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    public OpenContentProviders() {
        UriMatcher uriMatcher = new UriMatcher(-1);
        this.uriMatcher = uriMatcher;
        uriMatcher.addURI("com.byd.automultipletheme.ContentProvider", "external", 0);
        uriMatcher.addURI("com.byd.automultipletheme.ContentProvider", "carousel_time", 1);
        uriMatcher.addURI("com.byd.automultipletheme.ContentProvider", "wallpaper_result", 4);
        uriMatcher.addURI("com.byd.automultipletheme.ContentProvider", "carousel_res", 2);
        uriMatcher.addURI("com.byd.automultipletheme.ContentProvider", "carousel_save_path", 3);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.databaseOpenHelper = new DatabaseOpenHelper(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        DatabaseOpenHelper databaseOpenHelper = this.databaseOpenHelper;
        if (databaseOpenHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseOpenHelper");
            databaseOpenHelper = null;
        }
        SQLiteDatabase writableDatabase = databaseOpenHelper.getWritableDatabase();
        int iMatch = this.uriMatcher.match(uri);
        if (iMatch == 0) {
            return writableDatabase.query("external", projection, selection, selectionArgs, null, null, sortOrder);
        }
        if (iMatch == 1) {
            return writableDatabase.query("carousel_time", projection, selection, selectionArgs, null, null, sortOrder);
        }
        if (iMatch == 2) {
            return writableDatabase.query("carousel_res", projection, selection, selectionArgs, null, null, sortOrder);
        }
        if (iMatch == 3) {
            return writableDatabase.query("carousel_save_path", projection, selection, selectionArgs, null, null, sortOrder);
        }
        if (iMatch == 4) {
            return writableDatabase.query("wallpaper_result", projection, selection, selectionArgs, null, null, sortOrder);
        }
        throw new IllegalArgumentException("UnKnow URI" + uri);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        long jReplace;
        Intrinsics.checkNotNullParameter(uri, "uri");
        DatabaseOpenHelper databaseOpenHelper = this.databaseOpenHelper;
        if (databaseOpenHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseOpenHelper");
            databaseOpenHelper = null;
        }
        SQLiteDatabase writableDatabase = databaseOpenHelper.getWritableDatabase();
        int iMatch = this.uriMatcher.match(uri);
        LogUtils.INSTANCE.i("The type of database table: ", Integer.valueOf(iMatch));
        if (iMatch == 0) {
            jReplace = writableDatabase.replace("external", "_id", values);
        } else if (iMatch == 1) {
            jReplace = writableDatabase.replace("carousel_time", "_id", values);
        } else if (iMatch == 2) {
            jReplace = writableDatabase.replace("carousel_res", "_id", values);
        } else if (iMatch == 3) {
            jReplace = writableDatabase.replace("carousel_save_path", "_id", values);
        } else if (iMatch == 4) {
            jReplace = writableDatabase.replace("wallpaper_result", "_id", values);
        } else {
            throw new IllegalArgumentException("UnKnow URI" + uri);
        }
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        context.getContentResolver().notifyChange(uri, null);
        Uri uriWithAppendedId = ContentUris.withAppendedId(uri, jReplace);
        Intrinsics.checkNotNullExpressionValue(uriWithAppendedId, "withAppendedId(uri, rowId)");
        return uriWithAppendedId;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        DatabaseOpenHelper databaseOpenHelper = this.databaseOpenHelper;
        if (databaseOpenHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseOpenHelper");
            databaseOpenHelper = null;
        }
        SQLiteDatabase writableDatabase = databaseOpenHelper.getWritableDatabase();
        if (this.uriMatcher.match(uri) == 2) {
            int iDelete = writableDatabase.delete("carousel_res", selection, selectionArgs);
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            context.getContentResolver().notifyChange(uri, null);
            return iDelete;
        }
        throw new IllegalArgumentException("UnKnow URI" + uri);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int iUpdate;
        Intrinsics.checkNotNullParameter(uri, "uri");
        DatabaseOpenHelper databaseOpenHelper = this.databaseOpenHelper;
        if (databaseOpenHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseOpenHelper");
            databaseOpenHelper = null;
        }
        SQLiteDatabase writableDatabase = databaseOpenHelper.getWritableDatabase();
        int iMatch = this.uriMatcher.match(uri);
        LogUtils.INSTANCE.i(Integer.valueOf(iMatch));
        if (iMatch == 0) {
            iUpdate = writableDatabase.update("external", values, selection, selectionArgs);
        } else if (iMatch == 1) {
            iUpdate = writableDatabase.update("carousel_time", values, selection, selectionArgs);
        } else if (iMatch == 2) {
            iUpdate = writableDatabase.update("carousel_res", values, selection, selectionArgs);
        } else if (iMatch == 3) {
            iUpdate = writableDatabase.update("carousel_save_path", values, selection, selectionArgs);
        } else if (iMatch == 4) {
            iUpdate = writableDatabase.update("wallpaper_result", values, selection, selectionArgs);
        } else {
            throw new IllegalArgumentException("UnKnow URI" + uri);
        }
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        context.getContentResolver().notifyChange(uri, null);
        return iUpdate;
    }

    @Override // android.content.ContentProvider
    public Bundle call(String authority, String method, String arg, Bundle extras) {
        Intrinsics.checkNotNullParameter(authority, "authority");
        Intrinsics.checkNotNullParameter(method, "method");
        Bundle bundle = new Bundle();
        LogUtils.INSTANCE.i("provider call: " + method);
        if (Intrinsics.areEqual(method, DB.CAROUSEL_SAVE_METHOD)) {
            DatabaseOpenHelper databaseOpenHelper = this.databaseOpenHelper;
            if (databaseOpenHelper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("databaseOpenHelper");
                databaseOpenHelper = null;
            }
            SQLiteDatabase writableDatabase = databaseOpenHelper.getWritableDatabase();
            Intrinsics.checkNotNullExpressionValue(writableDatabase, "databaseOpenHelper.writableDatabase");
            Cursor cursorQuery = writableDatabase.query("carousel_save_path", null, null, null, null, null, null);
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    int i = cursorQuery.getInt(cursorQuery.getColumnIndex("res_id"));
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex(DB.CAROUSEL_SAVE_RES_PATH));
                    bundle.putInt("carousel_save_res_id", i);
                    bundle.putString("carousel_save_res_path", string);
                }
                cursorQuery.close();
            }
        }
        return bundle;
    }
}
