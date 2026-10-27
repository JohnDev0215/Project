package com.byd.event_upload;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.LocationManager;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.byd.common.R$integer;
import com.byd.dilink.DiLinkServiceManager;
import com.byd.dilink.app.IDiLinkUiStyleConfig;
import com.byd.dilink.provider.CarStatusSettingConstants;
import com.byd.util.e;
import com.byd.util.w;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f2808a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f2809b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2810c;
    private String d;
    private Context e;
    private SQLiteDatabase f;
    private int g;
    private BroadcastReceiver h;
    private LocationManager i;
    private String j;
    private Gson k;
    private ExecutorService l;
    private boolean m;
    private String n;
    private String o;
    private String p;
    private int q;

    private class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                if ("android.intent.action.DATE_CHANGED".equals(action)) {
                    a.this.v();
                }
            } else {
                int iS = e.s(context);
                if (iS != a.this.g) {
                    a.this.g = iS;
                    a.this.x();
                }
            }
        }
    }

    private class c extends SQLiteOpenHelper {
        public c(Context context) {
            super(context, "event_info", (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("create table tb_event (id integer primary key autoincrement,name text,time text,value text)");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    private class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private EventInfo f2813a;

        private d(EventInfo eventInfo) {
            this.f2813a = eventInfo;
        }

        private void a() {
            w.c("event upload fail");
            EventInfo eventInfo = this.f2813a;
            if (eventInfo != null) {
                a.this.u(eventInfo);
            }
        }

        private void b(ArrayList<EventInfo> arrayList) {
            w.c("event upload success");
            if (this.f2813a == null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    a.this.A(arrayList.get(i).getId());
                }
                a.this.w();
            }
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            ArrayList<EventInfo> arrayListB;
            if (this.f2813a != null) {
                arrayListB = new ArrayList<>(1);
                arrayListB.add(this.f2813a);
            } else {
                arrayListB = a.this.B();
            }
            if (arrayListB != null) {
                EventPackage eventPackage = new EventPackage(a.this.f2810c, a.this.f2809b, a.this.E(), String.valueOf(System.currentTimeMillis()), a.this.o, a.this.p, a.this.j, a.this.n, a.this.q);
                eventPackage.setTriggerEventList(arrayListB);
                a aVar = a.this;
                byte[] bArrC = aVar.C(aVar.k.toJson(eventPackage));
                a aVar2 = a.this;
                String strF = aVar2.F(aVar2.d, a.this.n, a.this.f2810c, bArrC);
                if (strF != null) {
                    try {
                        if (new JSONObject(strF).getInt("code") == 200) {
                            b(arrayListB);
                        } else {
                            a();
                        }
                        return;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                a();
            }
        }
    }

    private a(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.e = applicationContext;
        this.i = (LocationManager) applicationContext.getSystemService("location");
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.intent.action.DATE_CHANGED");
        b bVar = new b();
        this.h = bVar;
        this.e.registerReceiver(bVar, intentFilter);
        this.k = new Gson();
        this.l = Executors.newCachedThreadPool();
        this.j = e.g(this.e);
        z(this.e);
        this.m = this.e.getResources().getInteger(R$integer.support_event_upload) == 1 && !e.G(this.e);
        if (e.E()) {
            this.m = false;
        }
        if (this.m) {
            this.n = e.i(this.e);
            this.q = e.h(this.e);
            this.o = e.o();
            this.p = e.w();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A(int i) {
        SQLiteDatabase sQLiteDatabase = this.f;
        if (sQLiteDatabase == null) {
            return;
        }
        w.c("delete event record : " + sQLiteDatabase.delete("tb_event", "id=" + i, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<EventInfo> B() {
        SQLiteDatabase sQLiteDatabase = this.f;
        ArrayList<EventInfo> arrayList = null;
        if (sQLiteDatabase == null) {
            return null;
        }
        Cursor cursorQuery = sQLiteDatabase.query("tb_event", new String[]{CarStatusSettingConstants.DiCareRecord.DICARE_ID, "name", "time", "value"}, null, null, null, null, null);
        if (cursorQuery != null) {
            if (cursorQuery.getCount() > 0) {
                ArrayList<EventInfo> arrayList2 = new ArrayList<>(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    arrayList2.add(new EventInfo(cursorQuery.getString(1), Long.parseLong(cursorQuery.getString(2)), cursorQuery.getString(3), cursorQuery.getInt(0)));
                }
                arrayList = arrayList2;
            }
            cursorQuery.close();
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v6, types: [byte[]] */
    public byte[] C(String str) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStreamIsEmpty = TextUtils.isEmpty(str);
        GZIPOutputStream gZIPOutputStream = 0;
        byte[] byteArray = null;
        gZIPOutputStream = null;
        GZIPOutputStream gZIPOutputStream2 = null;
        gZIPOutputStream = 0;
        try {
            if (byteArrayOutputStreamIsEmpty != 0) {
                return null;
            }
            try {
                byteArrayOutputStreamIsEmpty = new ByteArrayOutputStream();
                try {
                    GZIPOutputStream gZIPOutputStream3 = new GZIPOutputStream(byteArrayOutputStreamIsEmpty);
                    try {
                        gZIPOutputStream3.write(str.getBytes());
                        gZIPOutputStream3.close();
                        byteArray = byteArrayOutputStreamIsEmpty.toByteArray();
                        byteArrayOutputStreamIsEmpty.flush();
                        try {
                            gZIPOutputStream3.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            byteArrayOutputStreamIsEmpty.close();
                            byteArrayOutputStreamIsEmpty = byteArrayOutputStreamIsEmpty;
                            gZIPOutputStream = byteArray;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            byteArrayOutputStreamIsEmpty = byteArrayOutputStreamIsEmpty;
                            gZIPOutputStream = byteArray;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        bArr = byteArray;
                        gZIPOutputStream2 = gZIPOutputStream3;
                        byteArrayOutputStream = byteArrayOutputStreamIsEmpty;
                        e.printStackTrace();
                        if (gZIPOutputStream2 != null) {
                            try {
                                gZIPOutputStream2.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        gZIPOutputStream = bArr;
                        byteArrayOutputStreamIsEmpty = byteArrayOutputStream;
                    } catch (Throwable th) {
                        th = th;
                        gZIPOutputStream = gZIPOutputStream3;
                    }
                } catch (IOException e6) {
                    e = e6;
                    bArr = null;
                    byteArrayOutputStream = byteArrayOutputStreamIsEmpty;
                }
            } catch (IOException e7) {
                e = e7;
                byteArrayOutputStream = null;
                bArr = null;
            } catch (Throwable th2) {
                th = th2;
                byteArrayOutputStreamIsEmpty = 0;
            }
            return gZIPOutputStream;
        } catch (Throwable th3) {
            th = th3;
        }
        if (gZIPOutputStream != 0) {
            try {
                gZIPOutputStream.close();
            } catch (IOException e8) {
                e8.printStackTrace();
            }
        }
        if (byteArrayOutputStreamIsEmpty == 0) {
            throw th;
        }
        try {
            byteArrayOutputStreamIsEmpty.close();
            throw th;
        } catch (IOException e9) {
            e9.printStackTrace();
            throw th;
        }
    }

    public static a D(Context context) {
        if (f2808a == null) {
            synchronized (a.class) {
                if (f2808a == null) {
                    f2808a = new a(context);
                }
            }
        }
        return f2808a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String E() {
        int i = this.g;
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 6 ? "NETWORK_UNKNOWN" : "NETWORK_5G" : "NETWORK_2G" : "NETWORK_4G" : "NETWORK_3G" : "NETWORK_WIFI";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String F(String str, String str2, String str3, byte[] bArr) throws Throwable {
        String str4;
        HttpURLConnection httpURLConnection = null;
        String string = null;
        httpURLConnection = null;
        try {
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection2.setRequestMethod("POST");
                    httpURLConnection2.setDoOutput(true);
                    httpURLConnection2.setDoInput(true);
                    httpURLConnection2.setUseCaches(false);
                    httpURLConnection2.setRequestProperty("Content-Type", "binary/octet-stream");
                    httpURLConnection2.setRequestProperty("vin", str2);
                    httpURLConnection2.setRequestProperty("appId", str3);
                    OutputStream outputStream = httpURLConnection2.getOutputStream();
                    outputStream.write(bArr);
                    outputStream.flush();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection2.getInputStream();
                    byte[] bArr2 = new byte[4096];
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        int i = inputStream.read(bArr2);
                        if (i == -1) {
                            string = stringBuffer.toString();
                            inputStream.close();
                            httpURLConnection2.disconnect();
                            return string;
                        }
                        stringBuffer.append(new String(bArr2, 0, i));
                    }
                } catch (Exception e) {
                    e = e;
                    String str5 = string;
                    httpURLConnection = httpURLConnection2;
                    str4 = str5;
                    e.printStackTrace();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return str4;
                } catch (Throwable th) {
                    th = th;
                    httpURLConnection = httpURLConnection2;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
            str4 = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(EventInfo eventInfo) {
        if (this.f == null) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", eventInfo.getEventId());
        contentValues.put("time", String.valueOf(eventInfo.getEventTime()));
        contentValues.put("value", eventInfo.getEventValue());
        this.f.insert("tb_event", null, contentValues);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        if (this.f == null) {
            return;
        }
        w.c("del out validity event : " + this.f.delete("tb_event", "time<?", new String[]{String.valueOf(System.currentTimeMillis() - 172800000)}));
        w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        SQLiteDatabase sQLiteDatabase = this.f;
        if (sQLiteDatabase == null) {
            return;
        }
        Cursor cursorQuery = sQLiteDatabase.query("tb_event", null, null, null, null, null, null);
        int count = 0;
        if (cursorQuery != null) {
            count = cursorQuery.getCount();
            cursorQuery.close();
        }
        if (count == 0) {
            this.f.execSQL("update sqlite_sequence set seq=0 where name='tb_event'");
        }
    }

    private void y(int i) {
        this.d = i != 1 ? i != 2 ? i != 3 ? "http://exhibitedcaryun.byd.com:8866/carToMq/upload/appBuryPoint" : "https://exhibitedcaryun-cn.yangwangcloud.com:8866/carToMq/upload/appBuryPoint" : "https://exhibitedcaryun-cn.fangchengbaocloud.com:8866/carToMq/upload/appBuryPoint" : "http://exhibitedcaryun-cn.denzacloud.com:8866/carToMq/upload/appBuryPoint";
    }

    private void z(Context context) {
        Class<?> cls;
        int iIntValue;
        if (e.B()) {
            y(3);
            return;
        }
        try {
            cls = Class.forName("a.a.a");
            iIntValue = ((Integer) cls.getMethod("getUiStyle", new Class[0]).invoke(cls.getMethod("getInstance", Context.class).invoke(cls, context), new Object[0])).intValue();
        } catch (Exception e) {
            w.c("EventUploadManager getUiStyle exception : " + e.getMessage());
            try {
                IDiLinkUiStyleConfig iDiLinkUiStyleConfig = (IDiLinkUiStyleConfig) DiLinkServiceManager.with().getService(IDiLinkUiStyleConfig.class);
                if (iDiLinkUiStyleConfig.isDenza()) {
                    y(1);
                    return;
                } else if (iDiLinkUiStyleConfig.isF()) {
                    y(2);
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (iIntValue == e.j(cls, "STYLE_DENZA")) {
            y(1);
            return;
        }
        if (iIntValue == e.j(cls, "STYLE_F")) {
            y(2);
            return;
        }
        y(0);
    }

    public void G(String str, String str2) {
        this.f2809b = str;
        this.f2810c = str2;
    }

    public void H() {
        try {
            this.f = new c(this.e).getWritableDatabase();
            v();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.g = e.s(this.e);
        StringBuilder sb = new StringBuilder();
        sb.append("internet permission : ");
        sb.append(ContextCompat.checkSelfPermission(this.e, "android.permission.INTERNET") == 0);
        w.c(sb.toString());
        x();
    }

    public void I() {
        BroadcastReceiver broadcastReceiver = this.h;
        if (broadcastReceiver != null) {
            this.e.unregisterReceiver(broadcastReceiver);
            this.h = null;
        }
        SQLiteDatabase sQLiteDatabase = this.f;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
            this.f = null;
        }
        this.l.shutdown();
        this.e = null;
        f2808a = null;
    }

    public void t(String str, String str2) {
        if (this.m) {
            EventInfo eventInfo = new EventInfo(str, System.currentTimeMillis(), str2);
            if (this.g != 0) {
                this.l.submit(new d(eventInfo));
            } else {
                u(eventInfo);
            }
            w.c("addEvent:" + eventInfo);
        }
    }

    public void x() {
        if (this.g == 0) {
            return;
        }
        this.l.submit(new d(null));
    }
}
