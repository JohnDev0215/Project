package com.byd.logtools.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: BydCrashInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f3222a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3223b = "crash_info";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.byd.logtools.d.d f3224c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BYDAutoBodyworkDevice f3225d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private BYDAutoVersionDevice f3226e;

    public c(Context context) {
        this.f3222a = context;
        this.f3224c = new com.byd.logtools.d.d(context);
    }

    public String a(JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("iccid", j());
            jSONObject.put("VIN", l());
            jSONObject.put("SwVersion", h());
            jSONObject.put("SI", HttpUrl.FRAGMENT_ENCODE_SET);
            jSONObject2.put("iccid", d(j()));
            jSONObject2.put("VIN", d(l()));
            jSONObject2.put("SwVersion", h());
            jSONObject2.put("SI", HttpUrl.FRAGMENT_ENCODE_SET);
            if (jSONArray != null && jSONArray.length() > 0) {
                jSONObject.put("accStatusList", jSONArray);
                jSONObject2.put("accStatusList", jSONArray);
            }
            jSONObject.put("crashUnit", HttpUrl.FRAGMENT_ENCODE_SET);
            jSONObject2.put("crashUnit", HttpUrl.FRAGMENT_ENCODE_SET);
        } catch (JSONException unused) {
            Log.d("BydCrashInfo", "combVINICCIDSWVersion error");
        }
        Log.d("BydCrashInfo", "combVINICCIDSWVersion STR = " + jSONObject2.toString());
        return jSONObject.toString();
    }

    public <K, V> String b(HashMap<K, V> map) {
        if (map.isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                for (Map.Entry<K, V> entry2 : ((HashMap) entry.getValue()).entrySet()) {
                    JSONObject jSONObject3 = new JSONObject();
                    for (Map.Entry<K, V> entry3 : ((HashMap) entry2.getValue()).entrySet()) {
                        jSONObject3.put(entry3.getKey().toString(), entry3.getValue());
                    }
                    jSONObject2.put(entry2.getKey().toString(), jSONObject3);
                }
                jSONObject.put(entry.getKey().toString(), jSONObject2);
            } catch (Exception unused) {
            }
        }
        return jSONObject.toString();
    }

    public JSONObject c() {
        return new JSONObject();
    }

    public String d(String str) {
        if (HttpUrl.FRAGMENT_ENCODE_SET.equals(str) || str == null || str.length() < 10) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        for (int i = 3; i < str.length() - 6; i++) {
            sb.setCharAt(i, '*');
        }
        return sb.toString();
    }

    public boolean e(String str) {
        File file = new File("/data/data/com.byd.bydlogtool/" + str);
        if (file.exists()) {
            file.delete();
            return true;
        }
        Log.d("BydCrashInfo", str + " is not exist");
        return false;
    }

    public void f() {
        SQLiteDatabase readableDatabase;
        try {
            readableDatabase = this.f3224c.getReadableDatabase();
        } catch (SQLiteCantOpenDatabaseException unused) {
            Log.d("BydCrashInfo", "compressInfoToFile SQLiteCantOpenDatabaseException");
            readableDatabase = null;
        }
        if (readableDatabase != null) {
            readableDatabase.delete(this.f3223b, null, null);
        }
    }

    public String g() {
        String str = SystemProperties.get("ro.build.type", "eng");
        String upperCase = ("userdebug".equals(str) ? "eng" : str).toUpperCase();
        Log.d("BydCrashInfo", "getBuildType : " + upperCase);
        return upperCase;
    }

    public String h() {
        BYDAutoVersionDevice bYDAutoVersionDevice = BYDAutoVersionDevice.getInstance(this.f3222a.getApplicationContext());
        this.f3226e = bYDAutoVersionDevice;
        String str = "51.1.1.2301010.1";
        if (bYDAutoVersionDevice != null) {
            String bydSystemVersion = bYDAutoVersionDevice.getBydSystemVersion();
            if (!TextUtils.isEmpty(bydSystemVersion)) {
                str = bydSystemVersion;
            }
        }
        String str2 = "8155f_" + g() + "_SIGN_SQ000_" + m(str) + "_Q" + str;
        Log.d("BydCrashInfo", "getBydSystemVersion() versionTag = " + str2);
        return str2;
    }

    public String i() {
        StringBuilder sb = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        int i4 = calendar.get(11);
        int i5 = calendar.get(12);
        int i6 = calendar.get(13);
        sb.append(i);
        sb.append("-");
        if (i2 < 10) {
            sb.append("0");
        }
        sb.append(i2);
        sb.append("-");
        if (i3 < 10) {
            sb.append("0");
        }
        sb.append(i3);
        sb.append(" ");
        if (i4 < 10) {
            sb.append("0");
        }
        sb.append(i4);
        sb.append(":");
        if (i5 < 10) {
            sb.append("0");
        }
        sb.append(":");
        if (i6 < 10) {
            sb.append("0");
        }
        sb.append(i6);
        return sb.toString();
    }

    public String j() {
        try {
            String strV = h.a.b.a.c.d().v();
            Log.d("BydCrashInfo", "crashInfo iccid = " + d(strV));
            return strV;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
    }

    public String k() {
        return SystemProperties.get("ro.product.version.soc.name", "null");
    }

    public String l() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this.f3222a);
        this.f3225d = bYDAutoBodyworkDevice;
        if (bYDAutoBodyworkDevice == null) {
            return "LGXCF4CD8R2051543";
        }
        String realAutoVIN = bYDAutoBodyworkDevice.getRealAutoVIN();
        return TextUtils.isEmpty(realAutoVIN) ? "LGXCF4CD8R2051543" : realAutoVIN;
    }

    public String m(String str) {
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(str)) {
            return "202401010101";
        }
        String[] strArrSplit = str.split("\\.");
        Log.d("BydCrashInfo", "getBydSystemVersion() fruits = " + strArrSplit + ", fruits.length = " + strArrSplit.length);
        for (int i = 0; i < strArrSplit.length; i++) {
            Log.d("BydCrashInfo", "getBydSystemVersion() fruit = " + strArrSplit[i]);
            if (i == 3) {
                sb.append("20");
                sb.append(strArrSplit[i]);
                sb.append("101");
                return sb.toString();
            }
        }
        return "202401010101";
    }

    public String n(String str) {
        String strSubstring = null;
        if (str == null) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            Log.d("BydCrashInfo", str + " not exist");
            return null;
        }
        if (str.endsWith(".gz")) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(file))));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        if (line.startsWith("Process")) {
                            strSubstring = line.substring(9);
                            break;
                        }
                        if (line.contains(">>>")) {
                            strSubstring = line.substring(line.indexOf(">>>") + 4, line.indexOf("<<<") - 1);
                            break;
                        }
                    } else {
                        break;
                    }
                }
                bufferedReader.close();
            } catch (Exception unused) {
                Log.d("BydCrashInfo", "parseCrashProcessName Exception1");
            }
            Log.d("BydCrashInfo", "parseCrashProcessName appName = " + strSubstring);
            return strSubstring;
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader2 = new BufferedReader(fileReader);
            while (true) {
                String line2 = bufferedReader2.readLine();
                if (line2 != null) {
                    if (line2.startsWith("Process")) {
                        strSubstring = line2.substring(9);
                        break;
                    }
                    if (line2.contains(">>>")) {
                        strSubstring = line2.substring(line2.indexOf(">>>") + 4, line2.indexOf("<<<") - 1);
                        break;
                    }
                }
            }
            try {
                bufferedReader2.close();
            } catch (IOException unused2) {
            }
            fileReader.close();
        } catch (Exception unused3) {
            Log.d("BydCrashInfo", "parseCrashProcessName Exception2");
        }
        Log.d("BydCrashInfo", "parseCrashProcessName appName = " + strSubstring);
        return strSubstring;
    }

    public void o(String str, String str2) throws Throwable {
        SQLiteDatabase writableDatabase;
        Cursor cursor = null;
        try {
            writableDatabase = this.f3224c.getWritableDatabase();
        } catch (Exception e2) {
            e2.printStackTrace();
            writableDatabase = null;
        }
        if (writableDatabase != null) {
            try {
                try {
                    Cursor cursorQuery = writableDatabase.query(this.f3223b, new String[]{"id", "count"}, "crash_type= '" + str + "' AND crash_module='" + str2 + "'", null, null, null, null);
                    try {
                        if (cursorQuery.getColumnCount() == 0 || !cursorQuery.moveToFirst()) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("crash_type", str);
                            contentValues.put("crash_module", str2);
                            contentValues.put("count", (Integer) 1);
                            Log.d("BydCrashInfo", "crashType = " + str + " crashmodule = " + str2);
                            writableDatabase.insert(this.f3223b, null, contentValues);
                        } else {
                            int i = cursorQuery.getInt(cursorQuery.getColumnIndex("id"));
                            int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("count")) + 1;
                            ContentValues contentValues2 = new ContentValues();
                            contentValues2.put("count", Integer.valueOf(i2));
                            writableDatabase.update(this.f3223b, contentValues2, "id=?", new String[]{String.valueOf(i)});
                        }
                        cursor = cursorQuery;
                    } catch (Exception e3) {
                        e = e3;
                        cursor = cursorQuery;
                        e.printStackTrace();
                        if (cursor == null) {
                            return;
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e4) {
                e = e4;
            }
            cursor.close();
        }
    }

    public boolean p(File file, String str) throws Throwable {
        BufferedWriter bufferedWriter = null;
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
                try {
                    bufferedWriter2.write(str);
                    bufferedWriter = bufferedWriter2;
                } catch (Exception unused) {
                    bufferedWriter = bufferedWriter2;
                    Log.d("BydCrashInfo", "writeFileContent is not ok");
                    if (bufferedWriter == null) {
                        return true;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused2) {
        }
        bufferedWriter.close();
        return true;
    }

    public boolean q(String str, String str2, String str3) {
        File file = new File(str + str2);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            p(file, str3);
            return false;
        } catch (Exception unused) {
            Log.d("BydCrashInfo", "writeJsonToFile failed");
            return false;
        }
    }
}
