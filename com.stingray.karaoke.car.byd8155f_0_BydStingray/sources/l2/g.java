package l2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import j2.AbstractC1539b;
import j2.C1538a;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static final String[] c = {"name", "length", "last_touch_timestamp"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f18475a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Serializable f18476b;

    public g() {
        this.f18475a = new HashMap();
        this.f18476b = new ArrayList();
    }

    public void a(Object obj, String str) {
        HashMap map = (HashMap) this.f18475a;
        obj.getClass();
        map.put(str, obj);
        ((ArrayList) this.f18476b).remove(str);
    }

    public HashMap b() throws E5.s {
        try {
            ((String) this.f18476b).getClass();
            Cursor cursorQuery = ((C1538a) this.f18475a).getReadableDatabase().query((String) this.f18476b, c, null, null, null, null, null);
            try {
                HashMap map = new HashMap(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(0);
                    string.getClass();
                    map.put(string, new f(cursorQuery.getLong(1), cursorQuery.getLong(2)));
                }
                cursorQuery.close();
                return map;
            } finally {
            }
        } catch (SQLException e10) {
            throw new E5.s(e10);
        }
    }

    public void c(long j3) throws E5.s {
        C1538a c1538a = (C1538a) this.f18475a;
        try {
            String hexString = Long.toHexString(j3);
            this.f18476b = "ExoPlayerCacheFileMetadata" + hexString;
            if (AbstractC1539b.a(c1538a.getReadableDatabase(), 2, hexString) != 1) {
                SQLiteDatabase writableDatabase = c1538a.getWritableDatabase();
                writableDatabase.beginTransactionNonExclusive();
                try {
                    AbstractC1539b.b(writableDatabase, 2, hexString);
                    writableDatabase.execSQL("DROP TABLE IF EXISTS " + ((String) this.f18476b));
                    writableDatabase.execSQL("CREATE TABLE " + ((String) this.f18476b) + " (name TEXT PRIMARY KEY NOT NULL,length INTEGER NOT NULL,last_touch_timestamp INTEGER NOT NULL)");
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                } catch (Throwable th) {
                    writableDatabase.endTransaction();
                    throw th;
                }
            }
        } catch (SQLException e10) {
            throw new E5.s(e10);
        }
    }

    public void d(Set set) throws E5.s {
        ((String) this.f18476b).getClass();
        try {
            SQLiteDatabase writableDatabase = ((C1538a) this.f18475a).getWritableDatabase();
            writableDatabase.beginTransactionNonExclusive();
            try {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    writableDatabase.delete((String) this.f18476b, "name = ?", new String[]{(String) it.next()});
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
            } catch (Throwable th) {
                writableDatabase.endTransaction();
                throw th;
            }
        } catch (SQLException e10) {
            throw new E5.s(e10);
        }
    }

    public void e(long j3, long j10, String str) throws E5.s {
        ((String) this.f18476b).getClass();
        try {
            SQLiteDatabase writableDatabase = ((C1538a) this.f18475a).getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", str);
            contentValues.put("length", Long.valueOf(j3));
            contentValues.put("last_touch_timestamp", Long.valueOf(j10));
            writableDatabase.replaceOrThrow((String) this.f18476b, null, contentValues);
        } catch (SQLException e10) {
            throw new E5.s(e10);
        }
    }

    public g(C1538a c1538a) {
        this.f18475a = c1538a;
    }
}
