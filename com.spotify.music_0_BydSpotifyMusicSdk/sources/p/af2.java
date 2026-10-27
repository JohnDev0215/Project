package p;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.google.firebase.encoders.EncodingException;
import com.spotify.cosmos.cosmos.Request;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class af2 implements bq, hi0, vh2, h62, i80, d00, io.reactivex.rxjava3.functions.i, EventListener.Factory {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ af2(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // okhttp3.EventListener.Factory
    public final EventListener a(Call call) {
        EventListener eventListener = (EventListener) this.e;
        byte[] bArr = Util.a;
        v03.j(eventListener, "$this_asFactory");
        v03.j(call, "it");
        return eventListener;
    }

    @Override // p.h62
    public final Object apply(Object obj) {
        URL url;
        nq nqVar;
        int i = this.c;
        Object obj2 = this.e;
        switch (i) {
            case 2:
                oq oqVar = (oq) obj2;
                mq mqVar = (mq) obj;
                oqVar.getClass();
                URL url2 = mqVar.a;
                if (Log.isLoggable("TRuntime.".concat("CctTransportBackend"), 4)) {
                    String.format("Making request to: %s", url2);
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) mqVar.a.openConnection();
                httpURLConnection.setConnectTimeout(30000);
                httpURLConnection.setReadTimeout(oqVar.g);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.setRequestMethod(Request.POST);
                httpURLConnection.setRequestProperty("User-Agent", String.format("datatransport/%s android/", "3.1.9"));
                httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
                String str = mqVar.c;
                if (str != null) {
                    httpURLConnection.setRequestProperty("X-Goog-Api-Key", str);
                }
                try {
                    try {
                        try {
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            try {
                                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                                try {
                                    oqVar.a.u(mqVar.b, new BufferedWriter(new OutputStreamWriter(gZIPOutputStream)));
                                    gZIPOutputStream.close();
                                    if (outputStream != null) {
                                        outputStream.close();
                                    }
                                    int responseCode = httpURLConnection.getResponseCode();
                                    Integer numValueOf = Integer.valueOf(responseCode);
                                    if (Log.isLoggable("TRuntime.".concat("CctTransportBackend"), 4)) {
                                        String.format("Status Code: %d", numValueOf);
                                    }
                                    ln1.h("CctTransportBackend", "Content-Type: %s", httpURLConnection.getHeaderField("Content-Type"));
                                    ln1.h("CctTransportBackend", "Content-Encoding: %s", httpURLConnection.getHeaderField("Content-Encoding"));
                                    if (responseCode == 302 || responseCode == 301 || responseCode == 307) {
                                        nqVar = new nq(responseCode, new URL(httpURLConnection.getHeaderField("Location")), 0L);
                                    } else {
                                        if (responseCode != 200) {
                                            return new nq(responseCode, null, 0L);
                                        }
                                        InputStream inputStream = httpURLConnection.getInputStream();
                                        try {
                                            InputStream gZIPInputStream = "gzip".equals(httpURLConnection.getHeaderField("Content-Encoding")) ? new GZIPInputStream(inputStream) : inputStream;
                                            try {
                                                nqVar = new nq(responseCode, null, hi.a(new BufferedReader(new InputStreamReader(gZIPInputStream))).a);
                                                if (gZIPInputStream != null) {
                                                    gZIPInputStream.close();
                                                }
                                                if (inputStream != null) {
                                                    inputStream.close();
                                                }
                                            } finally {
                                            }
                                        } finally {
                                        }
                                    }
                                    return nqVar;
                                } finally {
                                }
                            } finally {
                            }
                        } catch (EncodingException | IOException e) {
                            ln1.j("CctTransportBackend", "Couldn't encode request, returning with 400", e);
                            return new nq(400, null, 0L);
                        }
                    } catch (ConnectException | UnknownHostException e2) {
                        e = e2;
                        url = null;
                        ln1.j("CctTransportBackend", "Couldn't open connection, returning with 500", e);
                        return new nq(500, url, 0L);
                    }
                } catch (ConnectException | UnknownHostException e3) {
                    e = e3;
                    url = null;
                    ln1.j("CctTransportBackend", "Couldn't open connection, returning with 500", e);
                    return new nq(500, url, 0L);
                }
            default:
                Map map = (Map) obj2;
                Cursor cursor = (Cursor) obj;
                ae0 ae0Var = j62.n;
                while (cursor.moveToNext()) {
                    long j = cursor.getLong(0);
                    Set hashSet = (Set) map.get(Long.valueOf(j));
                    if (hashSet == null) {
                        hashSet = new HashSet();
                        map.put(Long.valueOf(j), hashSet);
                    }
                    hashSet.add(new i62(cursor.getString(1), cursor.getString(2)));
                }
                return null;
        }
    }

    @Override // p.vh2
    public final Object b() {
        int i = this.c;
        int i2 = 3;
        int i3 = 0;
        Object obj = this.e;
        switch (i) {
            case 3:
                j62 j62Var = (j62) ((jk0) obj);
                return Integer.valueOf(((Integer) j62Var.i(new e62(j62Var, ((lr2) j62Var.e).a() - j62Var.l.d))).intValue());
            case 4:
                j62 j62Var2 = (j62) ((jr2) obj).i;
                j62Var2.getClass();
                j62Var2.i(new g62(j62Var2, i3));
                return null;
            case 5:
                j62 j62Var3 = (j62) ((rt) obj);
                j62Var3.getClass();
                int i4 = wt.e;
                gh2 gh2Var = new gh2(7);
                HashMap map = new HashMap();
                SQLiteDatabase sQLiteDatabaseB = j62Var3.b();
                sQLiteDatabaseB.beginTransaction();
                try {
                    wt wtVar = (wt) j62.o0(sQLiteDatabaseB.rawQuery("SELECT log_source, reason, events_dropped_count FROM log_event_dropped", new String[0]), new r70(j62Var3, map, gh2Var, i2));
                    sQLiteDatabaseB.setTransactionSuccessful();
                    return wtVar;
                } finally {
                    sQLiteDatabaseB.endTransaction();
                }
            default:
                sx2 sx2Var = (sx2) obj;
                j62 j62Var4 = (j62) sx2Var.b;
                j62Var4.getClass();
                Iterator it = ((Iterable) j62Var4.i(new iy2(3))).iterator();
                while (it.hasNext()) {
                    ((s61) sx2Var.c).a((wi) it.next(), 1, false);
                }
                return null;
        }
    }

    @Override // p.bq
    public final void c() {
        bf2 bf2Var = (bf2) this.e;
        v03.j(bf2Var, "this$0");
        bf2Var.a();
    }

    @Override // p.hi0
    public final ii0 d() {
        return (ii0) this.e;
    }

    @Override // p.i80
    public final void e(yy1 yy1Var) {
        k30 k30Var = (k30) this.e;
        k30Var.getClass();
        Log.isLoggable("FirebaseCrashlytics", 3);
        k30Var.b.set((j30) yy1Var.get());
    }

    @Override // p.d00
    public final Object j(aj2 aj2Var) {
        boolean z;
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 11:
                ((xb2) obj).getClass();
                if (aj2Var.d()) {
                    ch chVar = (ch) aj2Var.c();
                    String str = chVar.b;
                    Log.isLoggable("FirebaseCrashlytics", 3);
                    File file = chVar.c;
                    if (file.delete()) {
                        file.getPath();
                        Log.isLoggable("FirebaseCrashlytics", 3);
                    } else {
                        file.getPath();
                    }
                    z = true;
                } else {
                    aj2Var.b();
                    z = false;
                }
                return Boolean.valueOf(z);
            default:
                ExecutorService executorService = ks2.a;
                ((CountDownLatch) obj).countDown();
                return null;
        }
    }
}
