package com.qtxa.qtxadefend;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f123a = "applist";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f124b = "packagename";
    private static String c = "app_name";
    private static String d = "app_version";
    private static String e = "app_version_name";
    private static String f = "app_exist";
    private static SQLiteDatabase g = null;
    private static String h = "/data/misc/iddfw/appinfo.db";

    class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f125a;

        a(Context context) {
            this.f125a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            h.a();
            h.e(this.f125a.getPackageManager().getInstalledApplications(0));
            w.e("MyDB", "save_all:ok");
        }
    }

    public static void a() {
        try {
            SQLiteDatabase sQLiteDatabaseB = b();
            if (sQLiteDatabaseB != null) {
                sQLiteDatabaseB.execSQL("create table if not exists " + f123a + " (_id integer primary key," + f124b + " text  unique," + d + " text not null," + e + " text not null," + f + " integer not null," + c + " text not null)");
                sQLiteDatabaseB.close();
            }
        } catch (Throwable th) {
            w.e("MyDB", "createTable:error:" + th.toString());
        }
    }

    public static SQLiteDatabase b() {
        SQLiteDatabase sQLiteDatabase = g;
        if (sQLiteDatabase == null) {
            synchronized (h.class) {
                try {
                    g = SQLiteDatabase.openOrCreateDatabase(h, (SQLiteDatabase.CursorFactory) null);
                } finally {
                }
            }
        } else {
            try {
                if (!sQLiteDatabase.isOpen()) {
                    g = SQLiteDatabase.openOrCreateDatabase(h, (SQLiteDatabase.CursorFactory) null);
                }
            } catch (Throwable th) {
                w.e("MyDB", "getInstance2:" + th.toString());
            }
        }
        return g;
    }

    public static void c(String str) {
        try {
            SQLiteDatabase sQLiteDatabaseB = b();
            String versionCode = Utils.getVersionCode(str);
            String strF = f(str, sQLiteDatabaseB);
            if (strF == null || !strF.equals(versionCode)) {
                d(str, sQLiteDatabaseB, strF);
                if (sQLiteDatabaseB == null || !sQLiteDatabaseB.isOpen()) {
                    return;
                }
                sQLiteDatabaseB.close();
            }
        } catch (Throwable th) {
            w.e("MyDB", "insert:error:" + th.toString());
        }
    }

    public static void d(String str, SQLiteDatabase sQLiteDatabase, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sysbuild-version", Build.DISPLAY);
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            String versionName = Utils.getVersionName(str);
            String appName = Utils.getAppName(str);
            String versionCode = Utils.getVersionCode(str);
            jSONObject2.put("package", str);
            jSONObject2.put("appname", appName);
            jSONObject2.put("appversion", versionCode);
            jSONObject2.put("appversion_name", versionName);
            jSONArray.put(jSONObject2);
            jSONObject.put("apks", jSONArray);
            w.i(1507, 3, "applist:", jSONObject.toString());
            ContentValues contentValues = new ContentValues();
            contentValues.put(f124b, str);
            contentValues.put(c, appName);
            contentValues.put(d, versionCode);
            contentValues.put(e, versionName);
            contentValues.put(f, (Integer) 1);
            if (str2 != null) {
                try {
                    sQLiteDatabase.update(f123a, contentValues, f124b + " = ?", new String[]{str});
                    w.e("MyDB", "update:ok");
                } catch (Throwable th) {
                    w.e("MyDB", "update:error" + th.toString());
                }
            } else {
                try {
                    sQLiteDatabase.insert(f123a, null, contentValues);
                    w.e("MyDB", "insert:ok");
                } catch (Throwable th2) {
                    w.e("MyDB", "insert:error" + th2.toString());
                }
            }
        } catch (Throwable th3) {
            th3.printStackTrace();
        }
    }

    public static void e(List<ApplicationInfo> list) {
        boolean z;
        String str;
        int i;
        JSONArray jSONArray;
        String str2 = "";
        try {
            SQLiteDatabase sQLiteDatabaseB = b();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sysbuild-version", Build.DISPLAY);
            JSONArray jSONArray2 = new JSONArray();
            int i2 = 0;
            boolean z2 = false;
            while (i2 < list.size()) {
                String str3 = list.get(i2).packageName;
                String versionCode = Utils.getVersionCode(str3);
                boolean z3 = z2;
                String strF = f(str3, sQLiteDatabaseB);
                if (strF == null || !strF.equals(versionCode)) {
                    JSONObject jSONObject2 = new JSONObject();
                    str = str2;
                    String versionName = Utils.getVersionName(str3);
                    i = i2;
                    String appName = Utils.getAppName(str3);
                    jSONObject2.put("package", str3);
                    jSONObject2.put("appname", appName);
                    jSONObject2.put("appversion", versionCode);
                    jSONObject2.put("appversion_name", versionName);
                    jSONArray2.put(jSONObject2);
                    if (jSONArray2.toString().length() > 4700) {
                        jSONObject.put("apks", jSONArray2);
                        w.i(1507, 3, "applist:", jSONObject.toString());
                        jSONArray = new JSONArray();
                        z3 = true;
                    } else {
                        jSONArray = jSONArray2;
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(f124b, str3);
                    contentValues.put(c, appName);
                    contentValues.put(d, versionCode);
                    contentValues.put(e, versionName);
                    contentValues.put(f, (Integer) 1);
                    if (strF != null) {
                        try {
                            String str4 = f123a;
                            String str5 = f124b + " = ?";
                            String[] strArr = new String[1];
                            try {
                                strArr[0] = str3;
                                sQLiteDatabaseB.update(str4, contentValues, str5, strArr);
                                w.e("MyDB", "update:ok");
                            } catch (Throwable th) {
                                th = th;
                                w.e("MyDB", "update:error" + th.toString());
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } else {
                        try {
                            sQLiteDatabaseB.insert(f123a, null, contentValues);
                            w.e("MyDB", "insert:ok");
                        } catch (Throwable th3) {
                            w.e("MyDB", "insert:error" + th3.toString());
                        }
                    }
                    jSONArray2 = jSONArray;
                    z2 = z3;
                } else {
                    str = str2;
                    i = i2;
                    z2 = z3;
                }
                i2 = i + 1;
                str2 = str;
            }
            String str6 = str2;
            boolean z4 = z2;
            if (jSONArray2.toString().length() > 2) {
                jSONObject.put("apks", jSONArray2);
                w.i(1507, 3, "applist:", jSONObject.toString());
                z = true;
            } else {
                z = z4;
            }
            String strF2 = f("Build_DISPLAY", sQLiteDatabaseB);
            String str7 = Build.DISPLAY;
            if (strF2 == null || !strF2.equals(str7)) {
                jSONObject.put("apks", jSONArray2);
                if (!z) {
                    w.i(1507, 3, "applist:", jSONObject.toString());
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put(f124b, "Build_DISPLAY");
                contentValues2.put(c, str6);
                contentValues2.put(d, str7);
                contentValues2.put(e, str6);
                contentValues2.put(f, (Integer) 1);
                if (strF2 != null) {
                    try {
                        sQLiteDatabaseB.update(f123a, contentValues2, f124b + " = ?", new String[]{"Build_DISPLAY"});
                        w.e("MyDB", "update:ok");
                    } catch (Throwable th4) {
                        w.e("MyDB", "update:error" + th4.toString());
                    }
                } else {
                    try {
                        sQLiteDatabaseB.insert(f123a, null, contentValues2);
                        w.e("MyDB", "insert:display_ok");
                    } catch (Throwable th5) {
                        w.e("MyDB", "insert:display_error:" + th5.toString());
                    }
                }
            }
            w.e("MyDB", "insert:ok");
            if (sQLiteDatabaseB == null || !sQLiteDatabaseB.isOpen()) {
                return;
            }
            sQLiteDatabaseB.close();
        } catch (Throwable th6) {
            w.e("MyDB", "insert_all:error:" + th6.toString());
        }
    }

    public static String f(String str, SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase == null) {
            return null;
        }
        try {
            if (!sQLiteDatabase.isOpen()) {
                return null;
            }
            Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select * from " + f123a + " where " + f124b + " = ?", new String[]{str});
            if (cursorRawQuery.isClosed() || !cursorRawQuery.moveToNext() || cursorRawQuery.getCount() <= 0) {
                cursorRawQuery.close();
                return null;
            }
            String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(d));
            cursorRawQuery.close();
            return string;
        } catch (Throwable th) {
            w.e("MyDB", "query_no_close:error:" + th.toString());
            return null;
        }
    }

    public static void g(Context context) {
        try {
            new Thread(new a(context)).start();
        } catch (Throwable th) {
            w.e("MyDB", "save_all:" + th.toString());
        }
    }

    public static void h(String str, boolean z) {
        try {
            SQLiteDatabase sQLiteDatabaseB = b();
            if (sQLiteDatabaseB == null || !sQLiteDatabaseB.isOpen()) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put(f124b, str);
            contentValues.put(c, Utils.getAppName(str));
            contentValues.put(f, Integer.valueOf(z ? 1 : 0));
            w.e("MyDB", "update:ok");
            sQLiteDatabaseB.update(f123a, contentValues, f124b + " = ?", new String[]{str});
            sQLiteDatabaseB.close();
        } catch (Throwable th) {
            w.e("MyDB", "update:error:" + th.toString());
        }
    }
}
