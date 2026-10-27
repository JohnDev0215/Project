package l2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.SparseArray;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import j2.AbstractC1539b;
import j2.C1538a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class l implements m {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String[] f18487e = {"id", "key", "metadata"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1538a f18488a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SparseArray f18489b = new SparseArray();
    public String c;
    public String d;

    public l(C1538a c1538a) {
        this.f18488a = c1538a;
    }

    @Override // l2.m
    public final boolean a() throws E5.s {
        try {
            SQLiteDatabase readableDatabase = this.f18488a.getReadableDatabase();
            String str = this.c;
            str.getClass();
            return AbstractC1539b.a(readableDatabase, 1, str) != -1;
        } catch (SQLException e10) {
            throw new E5.s(e10);
        }
    }

    @Override // l2.m
    public final void b(HashMap map) throws E5.s {
        SparseArray sparseArray = this.f18489b;
        if (sparseArray.size() == 0) {
            return;
        }
        try {
            SQLiteDatabase writableDatabase = this.f18488a.getWritableDatabase();
            writableDatabase.beginTransactionNonExclusive();
            for (int i7 = 0; i7 < sparseArray.size(); i7++) {
                try {
                    k kVar = (k) sparseArray.valueAt(i7);
                    if (kVar == null) {
                        int iKeyAt = sparseArray.keyAt(i7);
                        String str = this.d;
                        str.getClass();
                        writableDatabase.delete(str, "id = ?", new String[]{Integer.toString(iKeyAt)});
                    } else {
                        i(writableDatabase, kVar);
                    }
                } catch (Throwable th) {
                    writableDatabase.endTransaction();
                    throw th;
                }
            }
            writableDatabase.setTransactionSuccessful();
            sparseArray.clear();
            writableDatabase.endTransaction();
        } catch (SQLException e10) {
            throw new E5.s(e10);
        }
    }

    @Override // l2.m
    public final void c(HashMap map) throws E5.s {
        try {
            SQLiteDatabase writableDatabase = this.f18488a.getWritableDatabase();
            writableDatabase.beginTransactionNonExclusive();
            try {
                j(writableDatabase);
                Iterator it = map.values().iterator();
                while (it.hasNext()) {
                    i(writableDatabase, (k) it.next());
                }
                writableDatabase.setTransactionSuccessful();
                this.f18489b.clear();
                writableDatabase.endTransaction();
            } catch (Throwable th) {
                writableDatabase.endTransaction();
                throw th;
            }
        } catch (SQLException e10) {
            throw new E5.s(e10);
        }
    }

    @Override // l2.m
    public final void d(long j3) {
        String hexString = Long.toHexString(j3);
        this.c = hexString;
        this.d = M5.b.g("ExoPlayerCacheIndex", hexString);
    }

    @Override // l2.m
    public final void e(k kVar) {
        this.f18489b.put(kVar.f18484a, kVar);
    }

    @Override // l2.m
    public final void f(HashMap map, SparseArray sparseArray) throws E5.s {
        C1538a c1538a = this.f18488a;
        AbstractC1346c.j(this.f18489b.size() == 0);
        try {
            SQLiteDatabase readableDatabase = c1538a.getReadableDatabase();
            String str = this.c;
            str.getClass();
            if (AbstractC1539b.a(readableDatabase, 1, str) != 1) {
                SQLiteDatabase writableDatabase = c1538a.getWritableDatabase();
                writableDatabase.beginTransactionNonExclusive();
                try {
                    j(writableDatabase);
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                } catch (Throwable th) {
                    writableDatabase.endTransaction();
                    throw th;
                }
            }
            SQLiteDatabase readableDatabase2 = c1538a.getReadableDatabase();
            String str2 = this.d;
            str2.getClass();
            Cursor cursorQuery = readableDatabase2.query(str2, f18487e, null, null, null, null, null);
            while (cursorQuery.moveToNext()) {
                try {
                    int i7 = cursorQuery.getInt(0);
                    String string = cursorQuery.getString(1);
                    string.getClass();
                    map.put(string, new k(i7, string, H0.b.a(new DataInputStream(new ByteArrayInputStream(cursorQuery.getBlob(2))))));
                    sparseArray.put(i7, string);
                } finally {
                }
            }
            cursorQuery.close();
        } catch (SQLiteException e10) {
            map.clear();
            sparseArray.clear();
            throw new E5.s(e10);
        }
    }

    @Override // l2.m
    public final void g(k kVar, boolean z6) {
        SparseArray sparseArray = this.f18489b;
        int i7 = kVar.f18484a;
        if (z6) {
            sparseArray.delete(i7);
        } else {
            sparseArray.put(i7, null);
        }
    }

    @Override // l2.m
    public final void h() throws E5.s {
        C1538a c1538a = this.f18488a;
        String str = this.c;
        str.getClass();
        try {
            String strConcat = "ExoPlayerCacheIndex".concat(str);
            SQLiteDatabase writableDatabase = c1538a.getWritableDatabase();
            writableDatabase.beginTransactionNonExclusive();
            try {
                int i7 = AbstractC1539b.f17007a;
                try {
                    int i10 = AbstractC1343A.f15195a;
                    if (DatabaseUtils.queryNumEntries(writableDatabase, "sqlite_master", "tbl_name = ?", new String[]{"ExoPlayerVersions"}) > 0) {
                        writableDatabase.delete("ExoPlayerVersions", "feature = ? AND instance_uid = ?", new String[]{Integer.toString(1), str});
                    }
                    writableDatabase.execSQL("DROP TABLE IF EXISTS " + strConcat);
                    writableDatabase.setTransactionSuccessful();
                } catch (SQLException e10) {
                    throw new E5.s(e10);
                }
            } finally {
                writableDatabase.endTransaction();
            }
        } catch (SQLException e11) {
            throw new E5.s(e11);
        }
    }

    public final void i(SQLiteDatabase sQLiteDatabase, k kVar) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        H0.b.c(kVar.f18486e, new DataOutputStream(byteArrayOutputStream));
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(kVar.f18484a));
        contentValues.put("key", kVar.f18485b);
        contentValues.put("metadata", byteArray);
        String str = this.d;
        str.getClass();
        sQLiteDatabase.replaceOrThrow(str, null, contentValues);
    }

    public final void j(SQLiteDatabase sQLiteDatabase) throws E5.s {
        String str = this.c;
        str.getClass();
        AbstractC1539b.b(sQLiteDatabase, 1, str);
        String str2 = this.d;
        str2.getClass();
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ".concat(str2));
        sQLiteDatabase.execSQL("CREATE TABLE " + this.d + " (id INTEGER PRIMARY KEY NOT NULL,key TEXT NOT NULL,metadata BLOB NOT NULL)");
    }
}
