package com.byd.provider;

import android.app.Activity;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.auto_photo.R;
import com.byd.util.w;

/* JADX INFO: loaded from: classes2.dex */
public class PhotoProvider extends ContentProvider {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f2876a = "com.byd.auto_photo";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private UriMatcher f2877b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f2878c;
    private boolean d;

    private static class a extends SQLiteOpenHelper {
        public a(Context context) {
            super(context, "photo_opt", (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("create table tb_opt (_id integer primary key autoincrement,path text not null,opt integer not null,extras text)");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    private SQLiteDatabase a(boolean z) {
        a aVarB = b();
        if (aVarB == null) {
            return null;
        }
        if (z) {
            return aVarB.getReadableDatabase();
        }
        SQLiteDatabase writableDatabase = aVarB.getWritableDatabase();
        if (!writableDatabase.isReadOnly()) {
            return writableDatabase;
        }
        w.c("db is read only!!!");
        writableDatabase.close();
        a aVar = this.f2878c;
        if (aVar != null) {
            aVar.close();
            this.f2878c = null;
        }
        return null;
    }

    private a b() {
        if (this.f2878c == null) {
            this.f2878c = new a(getContext());
        }
        return this.f2878c;
    }

    private void c() {
        UriMatcher uriMatcher = new UriMatcher(-1);
        this.f2877b = uriMatcher;
        uriMatcher.addURI(f2876a, "opt", 0);
        this.f2877b.addURI(f2876a, "opt/#", 1);
        this.f2877b.addURI(f2876a, "share", 2);
    }

    private int e(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        StringBuilder sb = new StringBuilder("select count(1) from ");
        sb.append(str);
        if (str2 != null) {
            sb.append(" where ");
            sb.append(str2);
        }
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery(sb.toString(), strArr);
        if (cursorRawQuery != null) {
            i = cursorRawQuery.moveToFirst() ? cursorRawQuery.getInt(0) : 0;
            cursorRawQuery.close();
        }
        return i;
    }

    public static void f(Activity activity, boolean z) {
        Uri uri = Uri.parse("content://" + activity.getApplicationInfo().packageName);
        Bundle bundle = new Bundle();
        bundle.putString("activityName", activity.getClass().getName());
        bundle.putBoolean("activityStart", z);
        w.c("recordMainActivityState -> " + activity.getContentResolver().call(uri, "activityState", (String) null, bundle));
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Bundle call(@NonNull String str, @Nullable String str2, @Nullable Bundle bundle) {
        return call(f2876a, str, str2, bundle);
    }

    @Override // android.content.ContentProvider
    @Nullable
    public Bundle call(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable Bundle bundle) {
        return com.byd.provider.a.c(this).a(this, f2876a, str, str2, str3, bundle);
    }

    public boolean d() {
        return this.d;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        int iDelete;
        SQLiteDatabase sQLiteDatabaseA = a(false);
        if (sQLiteDatabaseA == null) {
            return 0;
        }
        try {
            int iMatch = this.f2877b.match(uri);
            if (iMatch == 0) {
                iDelete = sQLiteDatabaseA.delete("tb_opt", str, strArr);
            } else {
                if (iMatch != 1) {
                    w.c("Unsupported URI in delete: " + uri);
                    return 0;
                }
                iDelete = sQLiteDatabaseA.delete("tb_opt", "_id=?", new String[]{uri.getPathSegments().get(1)});
            }
            return iDelete;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int iMatch = this.f2877b.match(uri);
        if (iMatch == 0) {
            return "vnd.android.cursor.dir/vnd.com.byd.auto_photo";
        }
        if (iMatch == 1) {
            return "vnd.android.cursor.item/vnd.com.byd.auto_photo";
        }
        if (iMatch == 2) {
            if (this.d) {
                return "vnd.android.cursor.item/share";
            }
            return null;
        }
        w.c("Unsupported URI int getType: " + uri);
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        if (this.f2877b.match(uri) == 0) {
            SQLiteDatabase sQLiteDatabaseA = a(false);
            if (sQLiteDatabaseA == null) {
                return null;
            }
            long jInsert = sQLiteDatabaseA.insert("tb_opt", null, contentValues);
            if (jInsert > 0) {
                Uri uriWithAppendedId = ContentUris.withAppendedId(uri, jInsert);
                getContext().getContentResolver().notifyChange(uriWithAppendedId, null);
                return uriWithAppendedId;
            }
        } else {
            w.c("Unsupported URI int insert: " + uri);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        f2876a = getContext().getApplicationInfo().packageName;
        c();
        try {
            SQLiteDatabase sQLiteDatabaseA = a(false);
            if (sQLiteDatabaseA == null) {
                return false;
            }
            if (e(sQLiteDatabaseA, "tb_opt", null, null) == 0) {
                sQLiteDatabaseA.execSQL("update sqlite_sequence set seq=0 where name='tb_opt'");
            }
        } catch (Exception e) {
            w.c("PhotoProvider create database fail!");
            e.printStackTrace();
        }
        this.d = getContext().getResources().getInteger(R.integer.support_share_qr_code) == 1;
        w.c("PhotoProvider onCreate : " + f2876a + " | share support : " + this.d);
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteDatabase sQLiteDatabaseA = a(true);
        if (sQLiteDatabaseA == null) {
            return null;
        }
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("tb_opt");
        int iMatch = this.f2877b.match(uri);
        if (iMatch < 0) {
            w.c("Unsupported URI int query: " + uri);
            return null;
        }
        if (iMatch == 1) {
            sQLiteQueryBuilder.appendWhere("_id=" + uri.getPathSegments().get(1));
        }
        return sQLiteQueryBuilder.query(sQLiteDatabaseA, strArr, str, strArr2, null, null, str2);
    }

    @Override // android.content.ContentProvider
    public void shutdown() {
        SQLiteDatabase sQLiteDatabaseA = a(false);
        if (sQLiteDatabaseA != null) {
            if (e(sQLiteDatabaseA, "tb_opt", null, null) == 0) {
                sQLiteDatabaseA.execSQL("update sqlite_sequence set seq=0 where name='tb_opt'");
            }
            sQLiteDatabaseA.close();
        }
        super.shutdown();
        this.f2878c = null;
        com.byd.provider.a.c(this).b();
        w.c("PhotoProvider shutdown");
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        SQLiteDatabase sQLiteDatabaseA = a(false);
        if (sQLiteDatabaseA == null) {
            return 0;
        }
        int iMatch = this.f2877b.match(uri);
        if (iMatch != 0) {
            if (iMatch == 1) {
                return sQLiteDatabaseA.update("tb_opt", contentValues, "_id=?", new String[]{uri.getPathSegments().get(1)});
            }
            w.c("Unsupported URI in update: " + uri);
            return 0;
        }
        if (str != null && str.contains("?") && strArr != null) {
            return sQLiteDatabaseA.update("tb_opt", contentValues, str, strArr);
        }
        w.c("PhotoProvider can not update Uri : " + uri + " => selection : " + str);
        return 0;
    }
}
