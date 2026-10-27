package com.amazon.identity.auth.device.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.d8;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.m7;
import com.amazon.identity.auth.device.me;
import com.amazon.identity.auth.device.n5;
import com.amazon.identity.auth.device.o7;
import com.amazon.identity.auth.device.token.CentralTokenManagementCommunication;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.wa;
import com.amazon.identity.auth.device.x7;
import com.amazon.identity.auth.device.x9;
import com.amazon.identity.auth.device.yd;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class LocalDataStorage {
    public static final String[] f = {AccountConstants.KEY_ACCOUNT_STATUS, "has.notified.server.of.deregister"};
    public static LocalDataStorage g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f556a;
    public final a b;
    public final LambortishClock c;
    public Map<String, Map<String, x7<String>>> d = null;
    public Map<String, x7<m7>> e;

    /* JADX INFO: compiled from: DCP */
    public enum GetDataOptions {
        DirtyOnly,
        NotDirtyOnly,
        Deleted,
        NotDeleted
    }

    /* JADX INFO: compiled from: DCP */
    public static class a extends SQLiteOpenHelper {
        public a(Context context, String str, int i) {
            super(context, str + ".db", (SQLiteDatabase.CursorFactory) null, i);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Log.i(ga.a("LocalDataStorage"), "Creating Local DataStore");
            sQLiteDatabase.execSQL(new n5("accounts").a("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").a("directed_id", "TEXT UNIQUE NOT NULL").a("display_name", "TEXT UNIQUE").a("account_timestamp", "INTEGER NOT NULL").a("account_deleted", "INTEGER NOT NULL").a("account_dirty", "INTEGER NOT NULL").toString());
            n5 n5VarA = new n5("userdata").a("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").a("userdata_account_id", "TEXT NOT NULL").a("userdata_key", "TEXT NOT NULL").a("userdata_value", "TEXT").a("userdata_timestamp", "INTEGER NOT NULL").a("userdata_deleted", "INTEGER NOT NULL").a("userdata_dirty", "INTEGER NOT NULL");
            n5VarA.c.add(String.format("UNIQUE(%s,%s)", "userdata_account_id", "userdata_key"));
            sQLiteDatabase.execSQL(n5VarA.toString());
            n5 n5VarA2 = new n5("tokens").a("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").a("token_account_id", "TEXT NOT NULL").a(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "TEXT NOT NULL").a("token_value", "TEXT").a("token_timestamp", "INTEGER NOT NULL").a("token_deleted", "INTEGER NOT NULL").a("token_dirty", "INTEGER NOT NULL");
            n5VarA2.c.add(String.format("UNIQUE(%s,%s)", "token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN));
            sQLiteDatabase.execSQL(n5VarA2.toString());
            n5 n5VarA3 = new n5("device_data").a("_id", "INTEGER PRIMARY KEY AUTOINCREMENT").a("device_data_namespace", "TEXT NOT NULL").a("device_data_key", "TEXT NOT NULL").a("device_data_value", "TEXT").a("device_data_timestamp", "INTEGER NOT NULL").a("device_data_deleted", "INTEGER NOT NULL").a("device_data_dirty", "INTEGER NOT NULL");
            n5VarA3.c.add(String.format("UNIQUE(%s,%s)", "device_data_namespace", "device_data_key"));
            sQLiteDatabase.execSQL(n5VarA3.toString());
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            super.onOpen(sQLiteDatabase);
            if (sQLiteDatabase.isReadOnly()) {
                return;
            }
            sQLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            throw new IllegalStateException(String.format("Cannot upgrade from version %d to %d", Integer.valueOf(i), Integer.valueOf(i2)));
        }
    }

    public LocalDataStorage(Context context, a aVar, LambortishClock lambortishClock, Map<String, Map<String, x7<String>>> map) {
        this.f556a = context;
        this.b = aVar;
        this.c = lambortishClock;
    }

    public final boolean a(boolean z) {
        return !z;
    }

    public final boolean b(boolean z) {
        return !z;
    }

    public final boolean c(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, boolean z, Date date, boolean z2) {
        if (str == null || str2 == null || date == null) {
            return false;
        }
        if (b(z2) && !a(sQLiteDatabase, str)) {
            return false;
        }
        x7<String> x7VarA = a(sQLiteDatabase, str, str2, str3, z || a(sQLiteDatabase, str, date), date, z2);
        if (x7VarA == null) {
            return z2;
        }
        Map<String, x7<m7>> map = this.e;
        x7<m7> x7Var = map == null ? null : map.get(str);
        if (x7Var != null) {
            x7Var.f659a.c.put(str2, x7VarA);
        }
        return true;
    }

    public synchronized Collection<Map<String, String>> d() {
        return a((Date) null, EnumSet.of(GetDataOptions.NotDirtyOnly, GetDataOptions.Deleted));
    }

    public final Map<String, Map<String, x7<String>>> e() {
        if (this.d == null) {
            HashMap map = new HashMap();
            Cursor cursorQuery = null;
            try {
                cursorQuery = this.b.getReadableDatabase().query("device_data", new String[]{"device_data_namespace", "device_data_key", "device_data_value", "device_data_timestamp", "device_data_dirty", "device_data_deleted"}, null, null, null, null, null);
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    do {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("device_data_namespace"));
                        Map map2 = (Map) map.get(string);
                        if (map2 == null) {
                            map2 = new HashMap();
                            map.put(string, map2);
                        }
                        map2.put(cursorQuery.getString(cursorQuery.getColumnIndex("device_data_key")), new x7(cursorQuery.getString(cursorQuery.getColumnIndex("device_data_value")), x9.b(cursorQuery, "device_data_timestamp"), x9.a(cursorQuery, "device_data_dirty"), x9.a(cursorQuery, "device_data_deleted")));
                    } while (cursorQuery.moveToNext());
                }
                x9.a(cursorQuery);
                this.b.close();
                this.d = map;
            } catch (Throwable th) {
                x9.a(cursorQuery);
                this.b.close();
                throw th;
            }
        }
        return this.d;
    }

    public final boolean d(SQLiteDatabase sQLiteDatabase, Date date, Map map) {
        String str = (String) map.get("userdata_account");
        if (str == null) {
            return false;
        }
        String str2 = (String) map.get("userdata_key");
        sQLiteDatabase.delete("userdata", String.format("%s = ? and %s = ? and %s = ? and %s = 1", "userdata_account_id", "userdata_key", "userdata_timestamp", "userdata_deleted"), new String[]{str, str2, Long.toString(date.getTime())});
        Map<String, x7<m7>> map2 = this.e;
        x7<m7> x7Var = map2 == null ? null : map2.get(str);
        if (x7Var != null) {
            x7Var.f659a.c.remove(str2);
        }
        return true;
    }

    public final boolean c(SQLiteDatabase sQLiteDatabase, Date date, Map map) {
        String str = (String) map.get("token_account");
        if (str == null) {
            return false;
        }
        String str2 = (String) map.get(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN);
        sQLiteDatabase.delete("tokens", String.format("%s = ? and %s = ? and %s = ? and %s = 1", "token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_timestamp", "token_deleted"), new String[]{str, str2, Long.toString(date.getTime())});
        Map<String, x7<m7>> map2 = this.e;
        x7<m7> x7Var = map2 == null ? null : map2.get(str);
        if (x7Var != null) {
            x7Var.f659a.d.remove(str2);
        }
        return true;
    }

    public synchronized String c(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        x7<m7> x7Var = a().get(str);
        if (x7Var != null && !x7Var.d) {
            x7<String> x7Var2 = x7Var.f659a.c.get(str2);
            if (x7Var2 != null && !x7Var2.d) {
                return x7Var2.f659a;
            }
            return null;
        }
        return null;
    }

    public final String c() {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("accounts LEFT OUTER JOIN userdata ON (" + x9.a("accounts", "directed_id") + " = " + x9.a("userdata", "userdata_account_id") + ") LEFT OUTER JOIN tokens ON (" + x9.a("accounts", "directed_id") + " = " + x9.a("tokens", "token_account_id") + ")");
        HashMap map = new HashMap();
        map.put("_id", x9.a("accounts", "_id", "_id"));
        map.put("directed_id", x9.a("accounts", "directed_id", "directed_id"));
        map.put("display_name", x9.a("accounts", "display_name", "display_name"));
        map.put("account_timestamp", x9.a("accounts", "account_timestamp", "account_timestamp"));
        map.put("account_dirty", x9.a("accounts", "account_dirty", "account_dirty"));
        map.put("account_deleted", x9.a("accounts", "account_deleted", "account_deleted"));
        map.put("userdata_key", x9.a("userdata", "userdata_key", "userdata_key"));
        map.put("userdata_value", x9.a("userdata", "userdata_value", "userdata_value"));
        map.put("userdata_timestamp", x9.a("userdata", "userdata_timestamp", "userdata_timestamp"));
        map.put("userdata_dirty", x9.a("userdata", "userdata_dirty", "userdata_dirty"));
        map.put("userdata_deleted", x9.a("userdata", "userdata_deleted", "userdata_deleted"));
        map.put(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, x9.a("tokens", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN));
        map.put("token_value", x9.a("tokens", "token_value", "token_value"));
        map.put("token_timestamp", x9.a("tokens", "token_timestamp", "token_timestamp"));
        map.put("token_dirty", x9.a("tokens", "token_dirty", "token_dirty"));
        map.put("token_deleted", x9.a("tokens", "token_deleted", "token_deleted"));
        sQLiteQueryBuilder.setProjectionMap(map);
        return sQLiteQueryBuilder.buildQuery((String[]) map.keySet().toArray(new String[0]), null, null, null, null, null, null);
    }

    public final void a(Cursor cursor, Map<String, x7<String>> map) {
        String string = cursor.getString(cursor.getColumnIndex(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN));
        if (string == null) {
            return;
        }
        map.put(string, new x7<>(cursor.getString(cursor.getColumnIndex("token_value")), x9.b(cursor, "token_timestamp"), x9.a(cursor, "token_dirty"), x9.a(cursor, "token_deleted")));
    }

    public final void b(Cursor cursor, Map<String, x7<String>> map) {
        String string = cursor.getString(cursor.getColumnIndex("userdata_key"));
        if (string == null) {
            return;
        }
        map.put(string, new x7<>(cursor.getString(cursor.getColumnIndex("userdata_value")), x9.b(cursor, "userdata_timestamp"), x9.a(cursor, "userdata_dirty"), x9.a(cursor, "userdata_deleted")));
    }

    public synchronized boolean a(String str, o7 o7Var, Date date, boolean z) {
        boolean zA;
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = d8.a(this.b);
                sQLiteDatabaseA.beginTransaction();
                zA = a(sQLiteDatabaseA, str, o7Var, date, z);
                if (zA) {
                    sQLiteDatabaseA.setTransactionSuccessful();
                }
                b(sQLiteDatabaseA);
                this.b.close();
            } catch (SQLiteConstraintException unused) {
                Log.e(ga.a("LocalDataStorage"), "Cannot add account since it violated a uniqueness constraint");
                if (sQLiteDatabaseA == null) {
                    return false;
                }
                b(sQLiteDatabaseA);
                this.b.close();
                return false;
            }
        } catch (Throwable th) {
            if (sQLiteDatabaseA != null) {
                b(sQLiteDatabaseA);
                this.b.close();
            }
            throw th;
        }
        return zA;
    }

    public synchronized Set<String> b() {
        HashSet hashSet;
        Map<String, x7<m7>> mapA = a();
        hashSet = new HashSet();
        for (Map.Entry<String, x7<m7>> entry : mapA.entrySet()) {
            if (!entry.getValue().d) {
                hashSet.add(entry.getKey());
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public synchronized void b(String str, String str2, Date date) {
        SQLiteDatabase sQLiteDatabaseA;
        x7<m7> x7Var;
        e.a((Object) str, "directedId");
        e.a((Object) str2, "key");
        e.a(date, "dateTime");
        synchronized (this) {
            SQLiteDatabase sQLiteDatabase = null;
            x7Var = null;
            try {
                sQLiteDatabaseA = d8.a(this.b);
            } catch (Throwable th) {
                th = th;
            }
            try {
                sQLiteDatabaseA.beginTransaction();
                b(sQLiteDatabaseA, str, str2, date);
                sQLiteDatabaseA.setTransactionSuccessful();
                b(sQLiteDatabaseA);
                this.b.close();
            } catch (Throwable th2) {
                th = th2;
                sQLiteDatabase = sQLiteDatabaseA;
                if (sQLiteDatabase != null) {
                    b(sQLiteDatabase);
                    this.b.close();
                }
                throw th;
            }
        }
        Map<String, x7<m7>> map = this.e;
        if (map != null) {
            x7Var = map.get(str);
        }
        if (x7Var == null) {
            return;
        }
        x7<String> x7Var2 = x7Var.f659a.c.get(str2);
        if (x7Var2 == null) {
            return;
        }
        if (x7Var2.b.equals(date)) {
            x7Var2.c = false;
        }
    }

    public final boolean a(SQLiteDatabase sQLiteDatabase, String str, o7 o7Var, Date date, boolean z) throws Throwable {
        x7<m7> x7Var;
        if (TextUtils.isEmpty(str) || date == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("directed_id", o7Var.f476a);
        contentValues.put("display_name", str);
        contentValues.put("account_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("account_dirty", Integer.valueOf(a(z) ? 1 : 0));
        contentValues.put("account_deleted", (Integer) 0);
        Cursor cursor = null;
        if (!x9.a(sQLiteDatabase, "accounts", contentValues, String.format("%s = ? and %s < ?", "directed_id", "account_timestamp"), new String[]{o7Var.f476a, Long.toString(date.getTime())})) {
            Log.e(ga.a("LocalDataStorage"), "Failed to add account");
        } else {
            String str2 = o7Var.f476a;
            HashMap map = new HashMap();
            try {
                int i = 3;
                Cursor cursorQuery = sQLiteDatabase.query("userdata", new String[]{"userdata_account_id", "userdata_key", "userdata_value", "userdata_timestamp", "userdata_deleted", "userdata_dirty"}, String.format("%s = ? and %s >= ? and %s = 0", "userdata_account_id", "userdata_timestamp", "userdata_deleted"), new String[]{str2, Long.toString(date.getTime())}, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            do {
                                b(cursorQuery, map);
                            } while (cursorQuery.moveToNext());
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        x9.a(cursor);
                        throw th;
                    }
                }
                x9.a(cursorQuery);
                Iterator<Map.Entry<String, String>> it = o7Var.b.entrySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        Map.Entry<String, String> next = it.next();
                        int i2 = i;
                        HashMap map2 = map;
                        x7<String> x7VarA = a(sQLiteDatabase, o7Var.f476a, next.getKey(), next.getValue(), false, date, z);
                        if (x7VarA == null) {
                            Log.e(ga.a("LocalDataStorage"), "Failed to save account because saving userdata was unsuccessful");
                            break;
                        }
                        map2.put(next.getKey(), x7VarA);
                        map = map2;
                        i = i2;
                    } else {
                        int i3 = i;
                        HashMap map3 = map;
                        String str3 = o7Var.f476a;
                        HashMap map4 = new HashMap();
                        try {
                            String[] strArr = {"token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_value", "token_timestamp", "token_deleted", "token_dirty"};
                            Object[] objArr = new Object[i3];
                            objArr[0] = "token_account_id";
                            objArr[1] = "token_timestamp";
                            objArr[2] = "token_deleted";
                            Cursor cursorQuery2 = sQLiteDatabase.query("tokens", strArr, String.format("%s = ? and %s >= ? and %s = 0", objArr), new String[]{str3, Long.toString(date.getTime())}, null, null, null);
                            if (cursorQuery2 != null) {
                                try {
                                    if (cursorQuery2.moveToFirst()) {
                                        do {
                                            a(cursorQuery2, map4);
                                        } while (cursorQuery2.moveToNext());
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    cursor = cursorQuery2;
                                    x9.a(cursor);
                                    throw th;
                                }
                            }
                            x9.a(cursorQuery2);
                            for (Map.Entry<String, String> entry : o7Var.c.entrySet()) {
                                x7<String> x7VarB = b(sQLiteDatabase, o7Var.f476a, entry.getKey(), entry.getValue(), date, z);
                                if (x7VarB == null) {
                                    Log.e(ga.a("LocalDataStorage"), "Failed to save account because saving token was unsuccessful");
                                } else {
                                    map4.put(entry.getKey(), x7VarB);
                                }
                            }
                            x7Var = new x7<>(new m7(o7Var.f476a, str, map3, map4), date, a(z), false);
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
        x7Var = null;
        if (x7Var == null) {
            return z;
        }
        Map<String, x7<m7>> map5 = this.e;
        if (map5 != null) {
            map5.put(o7Var.f476a, x7Var);
        }
        return true;
    }

    public synchronized boolean b(Collection<Map<String, String>> collection) {
        SQLiteDatabase sQLiteDatabaseA;
        boolean zA;
        if (collection == null) {
            return true;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabaseA = d8.a(this.b);
        } catch (Throwable th) {
            th = th;
        }
        try {
            sQLiteDatabaseA.beginTransaction();
            boolean z = true;
            for (Map<String, String> map : collection) {
                Date dateA = wa.a(map.get("timestamp_key"));
                boolean z2 = Boolean.parseBoolean(map.get("deleted_key"));
                boolean z3 = false;
                if (map.get("directedId") != null) {
                    String str = map.get("directedId");
                    if (str != null) {
                        if (!z2) {
                            zA = a(sQLiteDatabaseA, map.get("display_name"), new o7(str, null, null, null), dateA, true);
                        } else {
                            zA = a(sQLiteDatabaseA, str, dateA, true);
                        }
                        z3 = zA;
                        z &= z3;
                    } else {
                        z &= z3;
                    }
                } else if (map.get("userdata_account") != null) {
                    String str2 = map.get("userdata_account");
                    if (str2 == null) {
                        z &= z3;
                    } else {
                        zA = c(sQLiteDatabaseA, str2, map.get("userdata_key"), map.get("userdata_value"), z2, dateA, true);
                        z3 = zA;
                        z &= z3;
                    }
                } else if (map.get("token_account") != null) {
                    String str3 = map.get("token_account");
                    if (str3 == null) {
                        z &= z3;
                    } else {
                        String str4 = map.get(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN);
                        if (!z2) {
                            zA = a(sQLiteDatabaseA, str3, str4, map.get("token_value"), dateA, true);
                        } else {
                            zA = a(sQLiteDatabaseA, str3, str4, dateA, true);
                        }
                        z3 = zA;
                        z &= z3;
                    }
                } else if (map.get("namespace") != null) {
                    String str5 = map.get("namespace");
                    if (str5 == null) {
                        z &= z3;
                    } else {
                        zA = b(sQLiteDatabaseA, str5, map.get("device_data_key"), map.get("device_data_value"), z2, dateA, true);
                        z3 = zA;
                        z &= z3;
                    }
                }
            }
            sQLiteDatabaseA.setTransactionSuccessful();
            b(sQLiteDatabaseA);
            this.b.close();
            return z;
        } catch (Throwable th2) {
            th = th2;
            sQLiteDatabase = sQLiteDatabaseA;
            if (sQLiteDatabase != null) {
                b(sQLiteDatabase);
                this.b.close();
            }
            throw th;
        }
    }

    public final boolean b(SQLiteDatabase sQLiteDatabase, Date date, Map map) {
        Map<String, x7<String>> map2;
        String str = (String) map.get("namespace");
        if (str == null) {
            return false;
        }
        sQLiteDatabase.delete("device_data", String.format("%s = ? and %s = ? and %s = ?  and %s = 1", "device_data_namespace", "device_data_key", "device_data_timestamp", "device_data_deleted"), new String[]{str, (String) map.get("device_data_key"), Long.toString(date.getTime())});
        Map<String, Map<String, x7<String>>> map3 = this.d;
        if (map3 != null && (map2 = map3.get(str)) != null) {
            map2.remove(str);
        }
        return true;
    }

    public synchronized String b(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        x7<m7> x7Var = a().get(str);
        if (x7Var != null && !x7Var.d) {
            x7<String> x7Var2 = x7Var.f659a.d.get(str2);
            if (x7Var2 != null && !x7Var2.d) {
                return x7Var2.f659a;
            }
            return null;
        }
        return null;
    }

    public final boolean b(SQLiteDatabase sQLiteDatabase, String str, Date date, boolean z) {
        boolean zA;
        ContentValues contentValues = new ContentValues();
        contentValues.put("directed_id", str);
        contentValues.putNull("display_name");
        contentValues.put("account_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("account_dirty", Integer.valueOf(a(z) ? 1 : 0));
        contentValues.put("account_deleted", (Integer) 1);
        String str2 = String.format("%s = ? and %s < ? and %s = 0", "directed_id", "account_timestamp", "account_deleted");
        String[] strArr = {str, Long.toString(date.getTime())};
        if (z) {
            zA = x9.a(sQLiteDatabase, "accounts", contentValues, str2, strArr);
        } else {
            zA = sQLiteDatabase.update("accounts", contentValues, str2, strArr) > 0;
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.putNull("userdata_value");
        contentValues2.put("userdata_timestamp", Long.valueOf(date.getTime()));
        contentValues2.put("userdata_dirty", Integer.valueOf(a(z) ? 1 : 0));
        contentValues2.put("userdata_deleted", (Integer) 1);
        sQLiteDatabase.update("userdata", contentValues2, String.format("%s = ? and %s < ? and %s = 0", "userdata_account_id", "userdata_timestamp", "userdata_deleted"), new String[]{str, Long.toString(date.getTime())});
        ContentValues contentValues3 = new ContentValues();
        contentValues3.putNull("token_value");
        contentValues3.put("token_timestamp", Long.valueOf(date.getTime()));
        contentValues3.put("token_dirty", Integer.valueOf(a(z) ? 1 : 0));
        contentValues3.put("token_deleted", (Integer) 1);
        sQLiteDatabase.update("tokens", contentValues3, String.format("%s = ? and %s < ? and %s = 0", "token_account_id", "token_timestamp", "token_deleted"), new String[]{str, Long.toString(date.getTime())});
        return zA;
    }

    public synchronized boolean a(String str, Date date, boolean z) {
        boolean zA;
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            sQLiteDatabaseA = d8.a(this.b);
            sQLiteDatabaseA.beginTransaction();
            zA = a(sQLiteDatabaseA, str, date, z);
            if (zA) {
                sQLiteDatabaseA.setTransactionSuccessful();
            }
            b(sQLiteDatabaseA);
            this.b.close();
        } catch (Throwable th) {
            if (sQLiteDatabaseA != null) {
                b(sQLiteDatabaseA);
                this.b.close();
            }
            throw th;
        }
        return zA;
    }

    public final boolean a(SQLiteDatabase sQLiteDatabase, String str, Date date, boolean z) {
        if (str == null || date == null) {
            return false;
        }
        if (!b(sQLiteDatabase, str, date, z)) {
            return z;
        }
        boolean zA = a(z);
        Map<String, x7<m7>> map = this.e;
        x7<m7> x7Var = map == null ? null : map.get(str);
        if (x7Var != null) {
            m7 m7Var = new m7(x7Var.f659a.f431a, null, new HashMap());
            x7<m7> x7Var2 = new x7<>(m7Var, date, zA, true);
            Iterator<Map.Entry<String, x7<String>>> it = x7Var.f659a.c.entrySet().iterator();
            while (it.hasNext()) {
                m7Var.c.put(it.next().getKey(), new x7<>(null, date, zA, true));
            }
            Iterator<Map.Entry<String, x7<String>>> it2 = x7Var.f659a.d.entrySet().iterator();
            while (it2.hasNext()) {
                m7Var.d.put(it2.next().getKey(), new x7<>(null, date, zA, true));
            }
            a().put(str, x7Var2);
        }
        return true;
    }

    public final void b(SQLiteDatabase sQLiteDatabase, String str, String str2, Date date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("userdata_dirty", (Integer) 0);
        sQLiteDatabase.update("userdata", contentValues, String.format("%s = ? and %s = ? and %s = ? and %s = 1", "userdata_account_id", "userdata_key", "userdata_timestamp", "userdata_dirty"), new String[]{str, str2, Long.toString(date.getTime())});
    }

    public synchronized boolean a(o7 o7Var, Date date, boolean z) {
        boolean z2;
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = d8.a(this.b);
                sQLiteDatabaseA.beginTransaction();
                z2 = true;
                Iterator<Map.Entry<String, String>> it = o7Var.b.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry<String, String> next = it.next();
                    if (!c(sQLiteDatabaseA, o7Var.f476a, next.getKey(), next.getValue(), false, date, z)) {
                        z2 = false;
                        break;
                    }
                }
                Iterator<Map.Entry<String, String>> it2 = o7Var.c.entrySet().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Map.Entry<String, String> next2 = it2.next();
                    if (!a(sQLiteDatabaseA, o7Var.f476a, next2.getKey(), next2.getValue(), date, z)) {
                        z2 = false;
                        break;
                    }
                }
                if (z2) {
                    sQLiteDatabaseA.setTransactionSuccessful();
                }
                b(sQLiteDatabaseA);
                this.b.close();
            } finally {
                if (sQLiteDatabaseA != null) {
                    b(sQLiteDatabaseA);
                    this.b.close();
                }
            }
        } catch (SQLiteConstraintException unused) {
            Log.e(ga.a("LocalDataStorage"), "Cannot set token since it violated a uniqueness constraint");
            return false;
        }
        return z2;
    }

    public final x7<String> b(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, Date date, boolean z) {
        boolean zA = a(sQLiteDatabase, str, date);
        ContentValues contentValues = new ContentValues();
        contentValues.put("token_account_id", str);
        contentValues.put(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, str2);
        contentValues.put("token_value", str3);
        contentValues.put("token_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("token_dirty", Integer.valueOf(a(z) ? 1 : 0));
        contentValues.put("token_deleted", Integer.valueOf(zA ? 1 : 0));
        if (x9.a(sQLiteDatabase, "tokens", contentValues, String.format("%s = ? and %s < ? and %s = ?", "token_account_id", "token_timestamp", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN), new String[]{str, Long.toString(date.getTime()), str2})) {
            return new x7<>(str3, date, a(z), zA);
        }
        return null;
    }

    public final boolean b(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, boolean z, Date date, boolean z2) {
        if (str == null || str2 == null || date == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("device_data_namespace", str);
        contentValues.put("device_data_key", str2);
        contentValues.put("device_data_value", str3);
        contentValues.put("device_data_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("device_data_deleted", Integer.valueOf(z ? 1 : 0));
        contentValues.put("device_data_dirty", Integer.valueOf(a(z2) ? 1 : 0));
        x7<String> x7Var = !x9.a(sQLiteDatabase, "device_data", contentValues, String.format("%s = ? and %s = ? and %s < ?", "device_data_namespace", "device_data_key", "device_data_timestamp"), new String[]{str, str2, Long.toString(date.getTime())}) ? null : new x7<>(str3, date, a(z2), z);
        if (x7Var == null) {
            return z2;
        }
        Map<String, Map<String, x7<String>>> map = this.d;
        if (map != null) {
            Map<String, x7<String>> map2 = map.get(str);
            if (map2 == null) {
                map2 = new HashMap<>();
                this.d.put(str, map2);
            }
            map2.put(str2, x7Var);
        }
        return true;
    }

    public final boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, Date date, boolean z) {
        if (str == null || str2 == null || date == null) {
            return false;
        }
        if (b(z) && !a(sQLiteDatabase, str)) {
            return false;
        }
        x7<String> x7VarB = b(sQLiteDatabase, str, str2, str3, date, z);
        if (x7VarB == null) {
            return z;
        }
        Map<String, x7<m7>> map = this.e;
        x7<m7> x7Var = map == null ? null : map.get(str);
        if (x7Var == null) {
            return true;
        }
        x7Var.f659a.d.put(str2, x7VarB);
        return true;
    }

    public synchronized void a(String str, String str2, Date date) {
        e.a((Object) str, "directedId");
        e.a((Object) str2, "key");
        e.a(date, "dateTime");
        synchronized (this) {
            try {
                a(d8.a(this.b), str, str2, date);
            } finally {
                this.b.close();
            }
        }
        Map<String, x7<m7>> map = this.e;
        x7<m7> x7Var = map == null ? null : map.get(str);
        if (x7Var == null) {
            return;
        }
        x7<String> x7Var2 = x7Var.f659a.d.get(str2);
        if (x7Var2 == null) {
            return;
        }
        if (x7Var2.b.equals(date)) {
            x7Var2.c = false;
        }
    }

    public synchronized boolean a(String str, String str2, Date date, boolean z) {
        boolean zA;
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            sQLiteDatabaseA = d8.a(this.b);
            sQLiteDatabaseA.beginTransaction();
            zA = a(sQLiteDatabaseA, str, str2, date, z);
            if (zA) {
                sQLiteDatabaseA.setTransactionSuccessful();
            }
            b(sQLiteDatabaseA);
            this.b.close();
        } catch (Throwable th) {
            if (sQLiteDatabaseA != null) {
                b(sQLiteDatabaseA);
                this.b.close();
            }
            throw th;
        }
        return zA;
    }

    public void b(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase.inTransaction()) {
            sQLiteDatabase.endTransaction();
        }
    }

    public synchronized String a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        ga.a("LocalDataStorage");
        Map<String, x7<String>> map = e().get(str);
        if (map == null) {
            return null;
        }
        x7<String> x7Var = map.get(str2);
        if (x7Var != null && !x7Var.d) {
            for (Map.Entry<String, x7<String>> entry : map.entrySet()) {
                entry.getKey();
                entry.getValue().toString();
                ga.a("LocalDataStorage");
            }
            return x7Var.f659a;
        }
        return null;
    }

    public synchronized boolean a(String str, String str2, String str3, Date date, boolean z) {
        boolean zB;
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            try {
                sQLiteDatabaseA = d8.a(this.b);
                sQLiteDatabaseA.beginTransaction();
                zB = b(sQLiteDatabaseA, str, str2, str3, false, date, z);
                if (zB) {
                    sQLiteDatabaseA.setTransactionSuccessful();
                }
                b(sQLiteDatabaseA);
                this.b.close();
            } catch (SQLiteConstraintException unused) {
                Log.e(ga.a("LocalDataStorage"), "Cannot set device data since it violated a uniqueness constraint");
                if (sQLiteDatabaseA == null) {
                    return false;
                }
                b(sQLiteDatabaseA);
                this.b.close();
                return false;
            }
        } catch (Throwable th) {
            if (sQLiteDatabaseA != null) {
                b(sQLiteDatabaseA);
                this.b.close();
            }
            throw th;
        }
        return zB;
    }

    public final Collection<Map<String, String>> a(Date date, EnumSet<GetDataOptions> enumSet) {
        LinkedList linkedList = new LinkedList();
        Iterator<Map.Entry<String, x7<m7>>> it = a().entrySet().iterator();
        while (it.hasNext()) {
            x7<m7> value = it.next().getValue();
            m7 m7Var = value.f659a;
            if (a(enumSet, date, value)) {
                HashMap map = new HashMap();
                map.put("directedId", value.f659a.f431a);
                map.put("display_name", value.f659a.b);
                a(map, value);
                linkedList.add(map);
            }
            for (Map.Entry<String, x7<String>> entry : m7Var.c.entrySet()) {
                if (a(enumSet, date, entry.getValue())) {
                    String str = m7Var.f431a;
                    String key = entry.getKey();
                    x7<?> value2 = entry.getValue();
                    HashMap map2 = new HashMap();
                    map2.put("userdata_account", str);
                    map2.put("userdata_key", key);
                    map2.put("userdata_value", value2.f659a);
                    a(map2, value2);
                    linkedList.add(map2);
                }
            }
            for (Map.Entry<String, x7<String>> entry2 : m7Var.d.entrySet()) {
                if (a(enumSet, date, entry2.getValue())) {
                    String str2 = m7Var.f431a;
                    String key2 = entry2.getKey();
                    x7<?> value3 = entry2.getValue();
                    HashMap map3 = new HashMap();
                    map3.put("token_account", str2);
                    map3.put(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, key2);
                    map3.put("token_value", value3.f659a);
                    a(map3, value3);
                    linkedList.add(map3);
                }
            }
        }
        for (Map.Entry<String, Map<String, x7<String>>> entry3 : e().entrySet()) {
            for (Map.Entry<String, x7<String>> entry4 : entry3.getValue().entrySet()) {
                if (a(enumSet, date, entry4.getValue())) {
                    String key3 = entry3.getKey();
                    String key4 = entry4.getKey();
                    x7<String> value4 = entry4.getValue();
                    HashMap map4 = new HashMap();
                    map4.put("namespace", key3);
                    map4.put("device_data_key", key4);
                    map4.put("device_data_value", value4.f659a);
                    a(map4, value4);
                    linkedList.add(map4);
                }
            }
        }
        return Collections.unmodifiableCollection(linkedList);
    }

    public final boolean a(EnumSet<GetDataOptions> enumSet, Date date, x7<?> x7Var) {
        boolean z;
        if (!((!enumSet.contains(GetDataOptions.DirtyOnly) || x7Var.c) && !(enumSet.contains(GetDataOptions.NotDirtyOnly) && x7Var.c) && ((!enumSet.contains(GetDataOptions.Deleted) || x7Var.d) && !(enumSet.contains(GetDataOptions.NotDeleted) && x7Var.d)))) {
            return false;
        }
        if (date == null) {
            z = true;
        } else {
            x7Var.getClass();
            z = !x7Var.b.after(date);
        }
        return z;
    }

    public final void a(Map<String, String> map, x7<?> x7Var) {
        map.put("timestamp_key", wa.a(x7Var.b()));
        map.put("dirty_key", Boolean.toString(x7Var.c));
        map.put("deleted_key", Boolean.toString(x7Var.d));
    }

    public synchronized boolean a(Collection<Map<String, String>> collection) {
        boolean zA;
        if (collection == null) {
            return true;
        }
        SQLiteDatabase sQLiteDatabaseA = null;
        try {
            sQLiteDatabaseA = d8.a(this.b);
            sQLiteDatabaseA.beginTransaction();
            boolean z = true;
            for (Map<String, String> map : collection) {
                Date dateA = wa.a(map.get("timestamp_key"));
                if (!Boolean.parseBoolean(map.get("deleted_key"))) {
                    Log.e(ga.a("LocalDataStorage"), "Given a row that is not marked deleted. Cannot remove from the database!");
                } else {
                    if (map.get("directedId") != null) {
                        zA = a(sQLiteDatabaseA, dateA, map);
                    } else if (map.get("userdata_account") != null) {
                        zA = d(sQLiteDatabaseA, dateA, map);
                    } else if (map.get("token_account") != null) {
                        zA = c(sQLiteDatabaseA, dateA, map);
                    } else if (map.get("namespace") != null) {
                        zA = b(sQLiteDatabaseA, dateA, map);
                    }
                    z &= zA;
                }
            }
            sQLiteDatabaseA.setTransactionSuccessful();
            b(sQLiteDatabaseA);
            this.b.close();
            return z;
        } catch (Throwable th) {
            if (sQLiteDatabaseA != null) {
                b(sQLiteDatabaseA);
                this.b.close();
            }
            throw th;
        }
    }

    public final boolean a(SQLiteDatabase sQLiteDatabase, Date date, Map map) {
        String str = (String) map.get("directedId");
        if (str == null) {
            return false;
        }
        sQLiteDatabase.delete("accounts", String.format("%s = ? and %s = ? and %s = 1", "directed_id", "account_timestamp", "account_deleted"), new String[]{str, Long.toString(date.getTime())});
        sQLiteDatabase.delete("userdata", String.format("%s = ? and %s = ? and %s = 1", "userdata_account_id", "userdata_timestamp", "userdata_deleted"), new String[]{str, Long.toString(date.getTime())});
        sQLiteDatabase.delete("tokens", String.format("%s = ? and %s = ? and %s = 1", "token_account_id", "token_timestamp", "token_deleted"), new String[]{str, Long.toString(date.getTime())});
        Map<String, x7<m7>> map2 = this.e;
        if (map2 == null) {
            return true;
        }
        map2.remove(str);
        return true;
    }

    public final x7<m7> a(String str) {
        Map<String, x7<m7>> map = this.e;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public final Map<String, x7<m7>> a() {
        if (this.e == null) {
            try {
                Map<String, x7<m7>> mapA = a(this.b.getReadableDatabase());
                this.b.close();
                this.e = mapA;
            } catch (Throwable th) {
                this.b.close();
                throw th;
            }
        }
        return this.e;
    }

    public final boolean a(SQLiteDatabase sQLiteDatabase, String str) {
        if (this.e == null) {
            this.e = a(sQLiteDatabase);
        }
        x7<m7> x7Var = this.e.get(str);
        return (x7Var == null || x7Var.d) ? false : true;
    }

    public final x7<String> a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, boolean z, Date date, boolean z2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("userdata_account_id", str);
        contentValues.put("userdata_key", str2);
        contentValues.put("userdata_value", str3);
        contentValues.put("userdata_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("userdata_dirty", Integer.valueOf(a(z2) ? 1 : 0));
        contentValues.put("userdata_deleted", Integer.valueOf(z ? 1 : 0));
        if (x9.a(sQLiteDatabase, "userdata", contentValues, String.format("%s = ? and %s = ? and %s < ?", "userdata_account_id", "userdata_key", "userdata_timestamp"), new String[]{str, str2, Long.toString(date.getTime())})) {
            return new x7<>(str3, date, a(z2), z);
        }
        return null;
    }

    public final void a(SQLiteDatabase sQLiteDatabase, String str, String str2, Date date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("token_dirty", (Integer) 0);
        sQLiteDatabase.update("tokens", contentValues, String.format("%s = ? and %s = ? and %s = ? and %s = 0 and %s = 1", "token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_timestamp", "token_deleted", "token_dirty"), new String[]{str, str2, Long.toString(date.getTime())});
    }

    public final boolean a(SQLiteDatabase sQLiteDatabase, String str, String str2, Date date, boolean z) {
        boolean zA = false;
        if (str == null || str2 == null || date == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("token_account_id", str);
        contentValues.put(CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, str2);
        contentValues.putNull("token_value");
        contentValues.put("token_timestamp", Long.valueOf(date.getTime()));
        contentValues.put("token_deleted", (Integer) 1);
        contentValues.put("token_dirty", Integer.valueOf(a(z) ? 1 : 0));
        String str3 = String.format("%s = ? and %s = ? and %s < ? and %s = 0", "token_account_id", CentralTokenManagementCommunication.GetTokenCommand.KEY_TOKEN, "token_timestamp", "token_deleted");
        String[] strArr = {str, str2, Long.toString(date.getTime())};
        if (z) {
            zA = x9.a(sQLiteDatabase, "tokens", contentValues, str3, strArr);
        } else if (sQLiteDatabase.update("tokens", contentValues, str3, strArr) > 0) {
            zA = true;
        }
        x7<String> x7Var = !zA ? null : new x7<>(null, date, a(z), true);
        if (x7Var == null) {
            return z;
        }
        Map<String, x7<m7>> map = this.e;
        x7<m7> x7Var2 = map != null ? map.get(str) : null;
        if (x7Var2 != null) {
            x7Var2.f659a.d.put(str2, x7Var);
        }
        return true;
    }

    public final void a(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, Date date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str3, (Integer) 0);
        String.format("Update %d items not dirty in table %s.", Integer.valueOf(sQLiteDatabase.update(str, contentValues, String.format("%s <= ?", str2), new String[]{Long.toString(date.getTime())})), str);
        ga.a("LocalDataStorage");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Map<String, x7<m7>> a(SQLiteDatabase sQLiteDatabase) {
        HashMap map = new HashMap();
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery(c(), null);
            if (cursorRawQuery == null || !cursorRawQuery.moveToFirst()) {
                return map;
            }
            do {
                String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("directed_id"));
                x7 x7Var = (x7) map.get(string);
                if (x7Var == null) {
                    String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("display_name"));
                    x7Var = new x7(new m7(string, string2, new HashMap()), x9.b(cursorRawQuery, "account_timestamp"), x9.a(cursorRawQuery, "account_dirty"), x9.a(cursorRawQuery, "account_deleted"));
                    map.put(string, x7Var);
                }
                b(cursorRawQuery, ((m7) x7Var.f659a).c);
                a(cursorRawQuery, ((m7) x7Var.f659a).d);
            } while (cursorRawQuery.moveToNext());
            if (this.c.a(a(map))) {
                me.a aVar = (me.a) yd.a();
                aVar.f438a = "LamportTimestampUpdatedBasedOnDBSnapshot";
                aVar.a().a();
                yd.a("LamportTimestampUpdatedBasedOnDBSnapshot", new String[0]);
            }
            return map;
        } finally {
            x9.a(cursorRawQuery);
        }
    }

    public Date a(Map<String, x7<m7>> map) {
        Date dateA = null;
        for (x7<m7> x7Var : map.values()) {
            Iterator<x7<String>> it = x7Var.f659a.c.values().iterator();
            while (it.hasNext()) {
                dateA = a(dateA, it.next().b());
            }
            Iterator<x7<String>> it2 = x7Var.f659a.d.values().iterator();
            while (it2.hasNext()) {
                dateA = a(dateA, it2.next().b());
            }
            dateA = a(dateA, x7Var.b());
        }
        return dateA;
    }

    public Date a(Date date, Date date2) {
        return (date == null || date2.after(date)) ? date2 : date;
    }

    public final boolean a(SQLiteDatabase sQLiteDatabase, String str, Date date) {
        Cursor cursorQuery = sQLiteDatabase.query("accounts", new String[]{"directed_id"}, String.format("%s = ? and %s > ?", "directed_id", "account_timestamp"), new String[]{str, Long.toString(date.getTime())}, null, null, null);
        String string = null;
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    string = cursorQuery.getString(cursorQuery.getColumnIndex("directed_id"));
                }
            } finally {
                cursorQuery.close();
            }
        }
        return string != null;
    }

    public final void a(StringBuilder sb, x7<?> x7Var) {
        a(sb, wa.a(x7Var.b()));
        a(sb, String.valueOf(x7Var.d));
        a(sb, String.valueOf(x7Var.c));
        sb.append("\n");
    }

    public final void a(StringBuilder sb, Object obj) {
        sb.append(obj);
        sb.append(",");
    }

    public final void a(StringBuilder sb, String str, String str2, x7<String> x7Var) {
        sb.append((Object) str);
        sb.append(",");
        sb.append((Object) str2);
        sb.append(",");
        String[] strArr = f;
        int length = strArr.length;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (str2.endsWith(strArr[i])) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            a(sb, x7Var.f659a);
        } else {
            sb.append("");
            sb.append(",");
        }
        a(sb, (x7<?>) x7Var);
    }
}
