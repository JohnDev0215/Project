package com.amazon.identity.auth.device;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Base64;
import android.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class e8 {
    public static final String g = "com.amazon.identity.auth.device.e8";
    public static e8 h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f297a;
    public final SQLiteOpenHelper b;
    public Map<String, Map<String, String>> c;
    public Map<String, n7> d;
    public final f8 e;
    public c4 f;

    /* JADX INFO: compiled from: DCP */
    public static class a extends SQLiteOpenHelper {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final f8 f298a;

        public a(Context context, String str, int i, f8 f8Var) {
            super(context, str + ".db", (SQLiteDatabase.CursorFactory) null, i);
            ga.c(e8.g, "Constructing LocalDataStorageDBHelper");
            File databasePath = context.getDatabasePath(str + ".db");
            ga.c(e8.g, "Database " + str + ".db exists: " + databasePath.exists());
            this.f298a = f8Var;
        }

        public static n5 a() {
            Log.i(ga.a(e8.g), "Creating EncryptionSecret table in LocalDataStorageV2 database");
            n5 n5VarA = new n5("encryption_data").a("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").a("encryption_data_key", "TEXT NOT NULL").a("encryption_data_value", "TEXT NOT NULL");
            n5VarA.c.add(String.format("UNIQUE(%s)", "encryption_data_key"));
            return n5VarA;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            String str = e8.g;
            Log.i(ga.a(str), "Creating LocalDataStorageV2 Database");
            Log.i(ga.a(str), "Creating Accounts table in LocalDataStorageV2 database");
            sQLiteDatabase.execSQL(new n5("accounts").a("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").a("directed_id", "TEXT UNIQUE NOT NULL").a("display_name", "TEXT NOT NULL").toString());
            Log.i(ga.a(str), "Creating AccountData table in LocalDataStorageV2 database");
            n5 n5VarA = new n5("account_data").a("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").a("account_data_directed_id", "TEXT NOT NULL").a("account_data_key", "TEXT NOT NULL").a("account_data_value", "BLOB");
            n5VarA.c.add(String.format("UNIQUE(%s,%s)", "account_data_directed_id", "account_data_key"));
            sQLiteDatabase.execSQL(n5VarA.toString());
            Log.i(ga.a(str), "Creating DeviceData table in LocalDataStorageV2 database");
            n5 n5VarA2 = new n5("device_data").a("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").a("device_data_namespace", "TEXT NOT NULL").a("device_data_key", "TEXT NOT NULL").a("device_data_value", "BLOB");
            n5VarA2.c.add(String.format("UNIQUE(%s,%s)", "device_data_namespace", "device_data_key"));
            sQLiteDatabase.execSQL(n5VarA2.toString());
            sQLiteDatabase.execSQL(a().toString());
            String strA = c4.a();
            e8.d(sQLiteDatabase, strA);
            this.f298a.a("com.amazon.identity.auth.device.storage.LocalOnlySQLDB.encrypt.key", strA);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            super.onOpen(sQLiteDatabase);
            if (!sQLiteDatabase.isReadOnly()) {
                sQLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
            }
            Log.i(ga.a(e8.g), "MAP database version: " + sQLiteDatabase.getVersion());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00de  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0100  */
        @Override // android.database.sqlite.SQLiteOpenHelper
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onUpgrade(android.database.sqlite.SQLiteDatabase r12, int r13, int r14) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 269
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.e8.a.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
        }
    }

    public e8(Context context, String str, int i) {
        ga.c(g, "Constructing LocalDataStorageV2");
        this.f297a = context;
        f8 f8Var = new f8(context, "com.amazon.identity.auth.device.storage.LocalOnlySQLDB.encryption.namespace");
        this.e = f8Var;
        this.b = new a(context, str, i, f8Var);
        this.f = null;
    }

    public synchronized boolean b(String str) {
        boolean zB;
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            sQLiteDatabaseA = d8.a(this.b);
            sQLiteDatabaseA.beginTransaction();
            zB = b(sQLiteDatabaseA, str);
            if (zB) {
                sQLiteDatabaseA.setTransactionSuccessful();
            }
        } finally {
            a(sQLiteDatabaseA);
        }
        return zB;
    }

    public final Map<String, n7> c() {
        if (this.d == null) {
            try {
                Map<String, n7> mapB = b(this.b.getReadableDatabase());
                this.b.close();
                this.d = mapB;
            } catch (Throwable th) {
                this.b.close();
                throw th;
            }
        }
        return this.d;
    }

    public final Map<String, Map<String, String>> d() {
        if (this.c == null) {
            HashMap map = new HashMap();
            Cursor cursorQuery = null;
            try {
                cursorQuery = this.b.getReadableDatabase().query("device_data", new String[]{"device_data_namespace", "device_data_key", "device_data_value"}, null, null, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("device_data_namespace");
                    int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("device_data_key");
                    int columnIndexOrThrow3 = cursorQuery.getColumnIndexOrThrow("device_data_value");
                    do {
                        String string = cursorQuery.getString(columnIndexOrThrow);
                        Map map2 = (Map) map.get(string);
                        if (map2 == null) {
                            map2 = new HashMap();
                            map.put(string, map2);
                        }
                        String string2 = cursorQuery.getString(columnIndexOrThrow2);
                        if (string2 != null) {
                            map2.put(string2, a(cursorQuery.getBlob(columnIndexOrThrow3)));
                        }
                    } while (cursorQuery.moveToNext());
                }
                x9.a(cursorQuery);
                this.b.close();
                this.c = map;
            } catch (Throwable th) {
                x9.a(cursorQuery);
                this.b.close();
                throw th;
            }
        }
        return this.c;
    }

    public synchronized boolean a(String str, String str2, Map<String, String> map) {
        boolean zA;
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = d8.a(this.b);
                sQLiteDatabaseA.beginTransaction();
                zA = a(sQLiteDatabaseA, str, str2, map);
                if (zA) {
                    sQLiteDatabaseA.setTransactionSuccessful();
                }
            } catch (SQLiteConstraintException e) {
                Log.e(ga.a(g), "Cannot add account due to ", e);
                return false;
            }
        } finally {
            a((SQLiteDatabase) null);
        }
        return zA;
    }

    public final void c(String str, String str2, String str3) {
        Map<String, Map<String, String>> map = this.c;
        if (map == null || str2 == null || str3 == null) {
            return;
        }
        Map<String, String> map2 = map.get(str);
        if (map2 == null) {
            map2 = new HashMap<>();
            this.c.put(str, map2);
        }
        map2.put(str2, str3);
    }

    public synchronized boolean b(String str, String str2, String str3) {
        boolean z;
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            sQLiteDatabaseA = d8.a(this.b);
            sQLiteDatabaseA.beginTransaction();
            if (str == null || str2 == null || !c(sQLiteDatabaseA, str, str2, str3)) {
                z = false;
            } else {
                c(str, str2, str3);
                z = true;
            }
            if (z) {
                sQLiteDatabaseA.setTransactionSuccessful();
            }
        } catch (SQLiteConstraintException unused) {
            Log.e(ga.a(g), "Cannot set device data since it violated a uniqueness constraint");
            return false;
        } finally {
            a(sQLiteDatabaseA);
        }
        return z;
    }

    public final boolean c(SQLiteDatabase sQLiteDatabase, String str) {
        boolean z = sQLiteDatabase.delete("accounts", String.format("%s = ?", "directed_id"), new String[]{str}) > 0;
        sQLiteDatabase.delete("account_data", String.format("%s = ?", "account_data_directed_id"), new String[]{str});
        return z;
    }

    public synchronized String a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        n7 n7Var = c().get(str);
        if (n7Var == null) {
            return null;
        }
        return n7Var.b.get(str2);
    }

    public final boolean c(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("device_data_namespace", str);
        contentValues.put("device_data_key", str2);
        contentValues.put("device_data_value", a(str3));
        return x9.a(sQLiteDatabase, "device_data", contentValues, String.format("%s = ? and %s = ?", "device_data_namespace", "device_data_key"), new String[]{str, str2});
    }

    public synchronized boolean a(String str, String str2, String str3) {
        boolean zA;
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = d8.a(this.b);
                sQLiteDatabaseA.beginTransaction();
                zA = a(sQLiteDatabaseA, str, str2, str3);
                if (zA) {
                    sQLiteDatabaseA.setTransactionSuccessful();
                }
            } catch (SQLiteConstraintException e) {
                Log.e(ga.a(g), "Cannot set data due to: ", e);
                return false;
            }
        } finally {
            a((SQLiteDatabase) null);
        }
        return zA;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized boolean b(String str, String str2) {
        boolean z;
        SQLiteDatabase sQLiteDatabase = null;
        n7 n7Var = null;
        try {
            SQLiteDatabase sQLiteDatabaseA = d8.a(this.b);
            try {
                sQLiteDatabaseA.beginTransaction();
                z = true;
                if (str == null || str2 == null || !a(sQLiteDatabaseA, str)) {
                    z = false;
                } else {
                    if ((sQLiteDatabaseA.delete("account_data", String.format("%s = ? and %s = ?", "account_data_directed_id", "account_data_key"), new String[]{str, str2}) > 0) == true) {
                        Map<String, n7> map = this.d;
                        if (map != null) {
                            n7Var = map.get(str);
                        }
                        if (n7Var != null) {
                            n7Var.b.remove(str2);
                        }
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    sQLiteDatabaseA.setTransactionSuccessful();
                }
                a(sQLiteDatabaseA);
            } catch (Throwable th) {
                th = th;
                sQLiteDatabase = sQLiteDatabaseA;
                a(sQLiteDatabase);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        return z;
    }

    public synchronized boolean a(String str, Map<String, String> map) {
        boolean z;
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = d8.a(this.b);
                sQLiteDatabaseA.beginTransaction();
                z = true;
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry<String, String> next = it.next();
                    if (!a(sQLiteDatabaseA, str, next.getKey(), next.getValue())) {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    sQLiteDatabaseA.setTransactionSuccessful();
                }
            } finally {
                a(sQLiteDatabaseA);
            }
        } catch (SQLiteConstraintException e) {
            Log.e(ga.a(g), "Cannot set data due to: ", e);
            return false;
        }
        return z;
    }

    public static void d(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("encryption_data_key", "key_encryption_secret");
            contentValues.put("encryption_data_value", str);
            if (x9.a(sQLiteDatabase, "encryption_data", contentValues, String.format("%s = ?", "encryption_data_key"), new String[]{"key_encryption_secret"})) {
                Log.i(ga.a(g), "Encryption key prepared.");
            } else {
                Log.e(ga.a(g), "Failed to set encryption key. This should never happen!");
            }
        } catch (Exception e) {
            yd.b("MAPFailedSetEncryptionKeyToDB", new String[0]);
            Log.e(ga.a(g), "Failed to set encryption key in db", e);
        }
    }

    public final boolean b(SQLiteDatabase sQLiteDatabase, String str) {
        if (str == null || !c(sQLiteDatabase, str)) {
            return false;
        }
        Map<String, n7> map = this.d;
        if (map == null) {
            return true;
        }
        map.remove(str);
        return true;
    }

    public final boolean b(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("account_data_directed_id", str);
        contentValues.put("account_data_key", str2);
        contentValues.put("account_data_value", a(str3));
        return x9.a(sQLiteDatabase, "account_data", contentValues, String.format("%s = ? and %s = ?", "account_data_directed_id", "account_data_key"), new String[]{str, str2});
    }

    public synchronized boolean a(String str, String str2, Map<String, String> map, List<String> list) {
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            sQLiteDatabaseA = d8.a(this.b);
            sQLiteDatabaseA.beginTransaction();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                if (!b(sQLiteDatabaseA, it.next())) {
                    return false;
                }
            }
            boolean zA = a(sQLiteDatabaseA, str, str2, map);
            if (zA) {
                sQLiteDatabaseA.setTransactionSuccessful();
            }
            return zA;
        } catch (SQLiteConstraintException e) {
            Log.e(ga.a(g), "Cannot replace accounts due to ", e);
            return false;
        } finally {
            a(sQLiteDatabaseA);
        }
    }

    public final byte[] b(byte[] bArr) {
        c4 c4VarA = a(true);
        byte[] bArrA = null;
        if (c4VarA == null) {
            return null;
        }
        try {
            bArrA = c4VarA.a(bArr);
        } catch (BadPaddingException unused) {
            Log.e(ga.a(g), "The decrypt throw BadPaddingException. This should not happen in LocalDataStorageV2!");
        }
        if (bArrA != null) {
            return bArrA;
        }
        Log.e(ga.a(g), "The decrypt result is null. This should not happen!");
        yd.a("DecryptionFailure", "decryptCBCModeReturnNull");
        a(this.f297a);
        throw new IllegalStateException("decryptCBCMode returns null. Something wrong with the decryption");
    }

    public final Map<String, n7> b(SQLiteDatabase sQLiteDatabase) {
        HashMap map = new HashMap();
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery(b(), null);
            if (cursorRawQuery != null && cursorRawQuery.moveToFirst()) {
                int columnIndexOrThrow = cursorRawQuery.getColumnIndexOrThrow("directed_id");
                int columnIndexOrThrow2 = cursorRawQuery.getColumnIndexOrThrow("display_name");
                int columnIndexOrThrow3 = cursorRawQuery.getColumnIndexOrThrow("account_data_key");
                int columnIndexOrThrow4 = cursorRawQuery.getColumnIndexOrThrow("account_data_value");
                do {
                    String string = cursorRawQuery.getString(columnIndexOrThrow);
                    n7 n7Var = (n7) map.get(string);
                    if (n7Var == null) {
                        n7 n7Var2 = new n7(string, cursorRawQuery.getString(columnIndexOrThrow2), new HashMap());
                        map.put(string, n7Var2);
                        n7Var = n7Var2;
                    }
                    String string2 = cursorRawQuery.getString(columnIndexOrThrow3);
                    if (string2 != null) {
                        n7Var.b.put(string2, a(cursorRawQuery.getBlob(columnIndexOrThrow4)));
                    }
                } while (cursorRawQuery.moveToNext());
                return map;
            }
            return map;
        } finally {
            x9.a(cursorRawQuery);
        }
    }

    public final boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        if (this.d == null) {
            this.d = b(sQLiteDatabase);
        }
        return this.d.get(str) != null;
    }

    public final boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        if (str == null || str2 == null || !a(sQLiteDatabase, str) || !b(sQLiteDatabase, str, str2, str3)) {
            return false;
        }
        Map<String, n7> map = this.d;
        n7 n7Var = map == null ? null : map.get(str);
        if (n7Var == null) {
            return true;
        }
        n7Var.b.put(str2, str3);
        return true;
    }

    public final String b() {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("accounts LEFT OUTER JOIN account_data ON (" + x9.a("accounts", "directed_id") + " = " + x9.a("account_data", "account_data_directed_id") + ")");
        HashMap map = new HashMap();
        map.put("_id", x9.a("accounts", "_id", "_id"));
        map.put("directed_id", x9.a("accounts", "directed_id", "directed_id"));
        map.put("display_name", x9.a("accounts", "display_name", "display_name"));
        map.put("account_data_key", x9.a("account_data", "account_data_key", "account_data_key"));
        map.put("account_data_value", x9.a("account_data", "account_data_value", "account_data_value"));
        sQLiteQueryBuilder.setProjectionMap(map);
        return sQLiteQueryBuilder.buildQuery((String[]) map.keySet().toArray(new String[0]), null, null, null, null, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00ef A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(android.database.sqlite.SQLiteDatabase r20, java.lang.String r21, java.lang.String r22, java.util.Map<java.lang.String, java.lang.String> r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.e8.a(android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String, java.util.Map):boolean");
    }

    public final byte[] a(String str) {
        byte[] bArrB = null;
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(ParserUtil.UTF_8);
            c4 c4VarA = a(false);
            if (c4VarA != null && bytes != null && (bArrB = c4VarA.b(bytes)) == null) {
                Log.e(ga.a(g), "The encrypt result is null. This should not happen!");
                yd.a("EncryptionFailure", "encryptCBCModeReturnNull");
            }
            return bArrB;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public final String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(b(bArr), ParserUtil.UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public final synchronized c4 a(boolean z) {
        if (this.f == null) {
            String string = this.e.f313a.getString("com.amazon.identity.auth.device.storage.LocalOnlySQLDB.encrypt.key", null);
            if (string == null) {
                if (!z) {
                    String str = g;
                    Log.e(ga.a(str), "Cannot get the encryption key from SharedPreferences before encrypt. Gonna create a new key. This should not happen!");
                    yd.a("EncryptionFailure", "EncryptionKeyNotFound");
                    if (this.e.a("com.amazon.identity.auth.device.storage.LocalOnlySQLDB.encrypt.key", c4.a())) {
                        string = this.e.f313a.getString("com.amazon.identity.auth.device.storage.LocalOnlySQLDB.encrypt.key", null);
                    } else {
                        Log.e(ga.a(str), "Try to re-generate the encryption key and save it into shared preferences failed!");
                        yd.a("EncryptionFailure", "TryToRegenerateEncryptionKeyFailure");
                        return null;
                    }
                } else {
                    Log.e(ga.a(g), "Cannot get the encryption key from SharedPreferences before decrypt. This should not happen!");
                    yd.a("DecryptionFailure", "EncryptionKeyNotFound");
                    a(this.f297a);
                    throw new IllegalStateException("The encryption key is null!");
                }
            }
            this.f = new c4(Base64.decode(string, 0));
        }
        return this.f;
    }

    public final void a(SQLiteDatabase sQLiteDatabase) {
        try {
            if (sQLiteDatabase != null) {
                if (sQLiteDatabase.inTransaction()) {
                    sQLiteDatabase.endTransaction();
                }
            }
        } catch (SQLiteException e) {
            Log.e(ga.a(g), "Database exception, it shouldn't happen, might be a bug in OS", e);
        } finally {
            this.b.close();
        }
    }

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("map_data_storage_v2");
        sb.append(".db");
        String str = context.deleteDatabase(sb.toString()) ? "successful" : "failed";
        Log.i(ga.a(g), "cleanDb " + str);
    }
}
