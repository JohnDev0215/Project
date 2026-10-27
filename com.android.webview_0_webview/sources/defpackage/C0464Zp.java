package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/* JADX INFO: renamed from: Zp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0464Zp {
    public static final String[] d = {"_id"};
    public SQLiteDatabase a;
    public boolean b;
    public final Object c = new Object();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0060  */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v4, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r15v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String[] a(java.lang.String r14, java.lang.String r15) throws java.lang.Throwable {
        /*
            r13 = this;
            r0 = 0
            if (r14 == 0) goto L64
            if (r15 == 0) goto L64
            boolean r1 = r13.e()
            if (r1 != 0) goto Lc
            goto L64
        Lc:
            java.lang.String r1 = "username"
            java.lang.String r2 = "password"
            java.lang.String[] r5 = new java.lang.String[]{r1, r2}
            android.database.sqlite.SQLiteDatabase r3 = r13.a     // Catch: java.lang.Throwable -> L4d java.lang.IllegalStateException -> L4f
            java.lang.String r4 = "httpauth"
            java.lang.String r6 = "(host == ?) AND (realm == ?)"
            r11 = 2
            java.lang.String[] r7 = new java.lang.String[r11]     // Catch: java.lang.Throwable -> L4d java.lang.IllegalStateException -> L4f
            r12 = 0
            r7[r12] = r14     // Catch: java.lang.Throwable -> L4d java.lang.IllegalStateException -> L4f
            r14 = 1
            r7[r14] = r15     // Catch: java.lang.Throwable -> L4d java.lang.IllegalStateException -> L4f
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r15 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L4d java.lang.IllegalStateException -> L4f
            boolean r3 = r15.moveToFirst()     // Catch: java.lang.IllegalStateException -> L4b java.lang.Throwable -> L5c
            if (r3 == 0) goto L47
            java.lang.String[] r3 = new java.lang.String[r11]     // Catch: java.lang.IllegalStateException -> L4b java.lang.Throwable -> L5c
            int r1 = r15.getColumnIndex(r1)     // Catch: java.lang.IllegalStateException -> L4b java.lang.Throwable -> L5c
            java.lang.String r1 = r15.getString(r1)     // Catch: java.lang.IllegalStateException -> L4b java.lang.Throwable -> L5c
            r3[r12] = r1     // Catch: java.lang.IllegalStateException -> L4b java.lang.Throwable -> L5c
            int r1 = r15.getColumnIndex(r2)     // Catch: java.lang.IllegalStateException -> L4b java.lang.Throwable -> L5c
            java.lang.String r1 = r15.getString(r1)     // Catch: java.lang.IllegalStateException -> L4b java.lang.Throwable -> L5c
            r3[r14] = r1     // Catch: java.lang.IllegalStateException -> L4b java.lang.Throwable -> L5c
            r0 = r3
        L47:
            r15.close()
            goto L5b
        L4b:
            r14 = move-exception
            goto L51
        L4d:
            r14 = move-exception
            goto L5e
        L4f:
            r14 = move-exception
            r15 = r0
        L51:
            java.lang.String r1 = "HttpAuthDatabase"
            java.lang.String r2 = "getHttpAuthUsernamePassword"
            android.util.Log.e(r1, r2, r14)     // Catch: java.lang.Throwable -> L5c
            if (r15 == 0) goto L5b
            goto L47
        L5b:
            return r0
        L5c:
            r14 = move-exception
            r0 = r15
        L5e:
            if (r0 == 0) goto L63
            r0.close()
        L63:
            throw r14
        L64:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0464Zp.a(java.lang.String, java.lang.String):java.lang.String[]");
    }

    public boolean b() {
        boolean zMoveToFirst = false;
        if (!e()) {
            return false;
        }
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.a.query("httpauth", d, null, null, null, null, null);
                zMoveToFirst = cursorQuery.moveToFirst();
            } catch (IllegalStateException e) {
                Log.e("HttpAuthDatabase", "hasEntries", e);
                if (cursorQuery != null) {
                }
                return zMoveToFirst;
            }
            cursorQuery.close();
            return zMoveToFirst;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public final void c(Context context, String str) {
        try {
            this.a = context.openOrCreateDatabase(str, 0, null);
        } catch (SQLiteException unused) {
            if (context.deleteDatabase(str)) {
                this.a = context.openOrCreateDatabase(str, 0, null);
            }
        }
        SQLiteDatabase sQLiteDatabase = this.a;
        if (sQLiteDatabase == null) {
            Log.e("HttpAuthDatabase", "Unable to open or create " + str);
            return;
        }
        if (sQLiteDatabase.getVersion() != 1) {
            this.a.beginTransactionNonExclusive();
            try {
                this.a.execSQL("CREATE TABLE httpauth (_id INTEGER PRIMARY KEY, host TEXT, realm TEXT, username TEXT, password TEXT, UNIQUE (host, realm) ON CONFLICT REPLACE);");
                this.a.setVersion(1);
                this.a.setTransactionSuccessful();
            } finally {
                this.a.endTransaction();
            }
        }
    }

    public void d(String str, String str2, String str3, String str4) {
        if (str == null || str2 == null || !e()) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("host", str);
        contentValues.put("realm", str2);
        contentValues.put("username", str3);
        contentValues.put("password", str4);
        this.a.insert("httpauth", "host", contentValues);
    }

    public final boolean e() {
        synchronized (this.c) {
            while (!this.b) {
                try {
                    this.c.wait();
                } catch (InterruptedException e) {
                    Log.e("HttpAuthDatabase", "Caught exception while checking initialization", e);
                }
            }
        }
        return this.a != null;
    }
}
