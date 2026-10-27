package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.amazon.identity.kcpsdk.auth.ParseErrorException;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import com.google.firebase.perf.FirebasePerformance;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ib {
    public static final String f = "com.amazon.identity.auth.device.ib";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public yb f385a;
    public ld b;
    public tc c;
    public final Context d;
    public final y5 e;

    public ib(Context context, ld ldVar, yb ybVar, tc tcVar, y5 y5Var) {
        this.b = ldVar;
        this.c = tcVar;
        this.f385a = ybVar;
        this.d = context;
        this.e = y5Var;
    }

    public static <T extends URLConnection> T a(T t) {
        String string = UUID.randomUUID().toString();
        t.setRequestProperty("X-Amzn-RequestId", string);
        Log.i(ga.a(f), "X-Amzn-RequestId:" + string);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long j = (long) 30;
        TimeUnit timeUnit2 = TimeUnit.SECONDS;
        t.setConnectTimeout((int) timeUnit.convert(j, timeUnit2));
        t.setReadTimeout((int) timeUnit.convert(j, timeUnit2));
        return t;
    }

    public void a() throws Throwable {
        if (!a(this.b, this.c)) {
            Log.i(ga.a(f), "Failed to sign request, aborting call to " + this.b.b());
            this.e.b("FailureToSignRequest");
            this.f385a.b();
            return;
        }
        Log.i(ga.a(f), "Starting web request");
        String.format("URL: %s", this.b.b());
        try {
            a(this.d, this.b, new hb(this), this.e);
        } catch (ParseErrorException unused) {
        } catch (IOException unused2) {
            this.f385a.onNetworkError();
        } catch (UnsupportedOperationException unused3) {
            this.f385a.onNetworkError();
        }
    }

    public static md a(ld ldVar, HttpURLConnection httpURLConnection) throws IOException {
        String headerField;
        HttpVerb httpVerb = ldVar.h;
        int i = 1;
        if (httpVerb == HttpVerb.HttpVerbGet) {
            httpURLConnection.setRequestMethod(FirebasePerformance.HttpMethod.GET);
        } else if (httpVerb == HttpVerb.HttpVerbPost) {
            httpURLConnection.setRequestMethod(FirebasePerformance.HttpMethod.POST);
            httpURLConnection.setDoOutput(true);
        } else if (httpVerb == HttpVerb.HttpVerbPut) {
            httpURLConnection.setRequestMethod(FirebasePerformance.HttpMethod.PUT);
            httpURLConnection.setDoOutput(true);
        } else {
            throw new UnsupportedOperationException("unrecognized HttpVerb: " + httpVerb);
        }
        for (int i2 = 0; i2 < ldVar.b.size(); i2++) {
            String strA = ldVar.a(i2);
            String strA2 = ldVar.a(i2);
            httpURLConnection.setRequestProperty(strA, strA2 == null ? null : ldVar.f423a.get(strA2.toLowerCase(Locale.US)));
        }
        if (httpVerb == HttpVerb.HttpVerbPost || httpVerb == HttpVerb.HttpVerbPut) {
            byte[] bArr = ldVar.i;
            if (bArr != null && bArr.length != 0) {
                httpURLConnection.setFixedLengthStreamingMode(bArr.length);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                try {
                    outputStream.write(bArr);
                    try {
                        outputStream.flush();
                    } catch (IOException e) {
                        Log.e(ga.a(f), "Couldn't flush write body stream", e);
                    }
                    try {
                        outputStream.close();
                    } catch (IOException e2) {
                        Log.e(ga.a(f), "Couldn't close write body stream", e2);
                    }
                } finally {
                }
            }
            Log.i(ga.a(f), "Finished write body.");
        }
        ga.a(f);
        for (Map.Entry<String, List<String>> entry : httpURLConnection.getRequestProperties().entrySet()) {
            for (String str : entry.getValue()) {
            }
            String str2 = f;
            entry.getKey();
            ga.a(str2);
        }
        String str3 = f;
        Log.i(ga.a(str3), "Starting get response code");
        int iB = RetryLogic.b(httpURLConnection);
        Log.i(ga.a(str3), "Received response: " + iB);
        if (iB != -1) {
            md mdVar = new md();
            mdVar.c = iB;
            do {
                String headerFieldKey = httpURLConnection.getHeaderFieldKey(i);
                headerField = httpURLConnection.getHeaderField(i);
                if (headerField != null) {
                    mdVar.a(headerFieldKey, headerField);
                    i++;
                }
            } while (headerField != null);
            return mdVar;
        }
        throw new IOException("Invalid response code");
    }

    public static boolean a(ld ldVar, tc tcVar) {
        if (!ldVar.j) {
            return true;
        }
        if (tcVar != null) {
            return tcVar.a(ldVar);
        }
        Log.e(ga.a(f), "The request requires authentication, but no authentication credentials were supplied.");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x018a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0155 A[Catch: all -> 0x0185, TryCatch #2 {all -> 0x0185, blocks: (B:48:0x00cf, B:51:0x00e4, B:52:0x00fd, B:55:0x0100, B:58:0x0115, B:60:0x012f, B:62:0x0133, B:74:0x0155, B:77:0x0178, B:78:0x017d, B:79:0x0184, B:64:0x013b, B:66:0x0141, B:69:0x0147, B:71:0x014b), top: B:99:0x001d, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01b6  */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object a(android.content.Context r9, com.amazon.identity.auth.device.ld r10, com.amazon.identity.auth.device.jb r11, com.amazon.identity.auth.device.y5 r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 445
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.ib.a(android.content.Context, com.amazon.identity.auth.device.ld, com.amazon.identity.auth.device.jb, com.amazon.identity.auth.device.y5):java.lang.Object");
    }
}
