package com.amazon.identity.auth.device;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class v1 {
    public static final String d = "com.amazon.identity.auth.device.v1";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f623a;
    public final rb b;
    public final BackwardsCompatiableDataStorage c;

    public v1(Context context) {
        t5 t5VarA = t5.a(context);
        this.f623a = t5VarA;
        this.c = new BackwardsCompatiableDataStorage(t5VarA);
        this.b = new rb();
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0074: MOVE (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:27:0x0074 */
    public final t7 a(HttpURLConnection httpURLConnection) throws IOException {
        IOException e;
        Closeable closeable;
        InputStream inputStream;
        Closeable closeable2 = null;
        try {
            try {
                inputStream = httpURLConnection.getInputStream();
            } catch (IOException e2) {
                e = e2;
            } catch (Throwable th) {
                th = th;
                sa.a(closeable2);
                throw th;
            }
            try {
                byte[] bArrA = sa.a(inputStream);
                sd sdVar = new sd();
                sdVar.f547a.write(bArrA, 0, bArrA.length);
                Document documentA = sdVar.a();
                if (documentA == null) {
                    Log.e(ga.a(d), "Could not parse get Store credentials response XML");
                    sa.a((Closeable) inputStream);
                    return null;
                }
                Element documentElement = documentA.getDocumentElement();
                if (documentElement != null && documentElement.getTagName().equals("response")) {
                    t7 t7Var = new t7(this.b.a(e.a(documentElement, "cookies")));
                    sa.a((Closeable) inputStream);
                    return t7Var;
                }
                String str = d;
                Log.e(ga.a(str), "Get Store Credentials request form was invalid. Could not get cookies");
                ga.a(str, "Store Credentials response: %s", new String(bArrA));
                sa.a((Closeable) inputStream);
                return null;
            } catch (IOException e3) {
                e = e3;
                k4.a(httpURLConnection, "Get Kindle Store Credentials Service");
                throw e;
            }
        } catch (Throwable th2) {
            th = th2;
            closeable2 = closeable;
            sa.a(closeable2);
            throw th;
        }
    }

    public final URL a() {
        try {
            return new URL(new Uri.Builder().scheme("https").authority(EnvironmentUtils.c.g()).appendPath("FirsProxy").appendPath("getStoreCredentials").build().toString());
        } catch (MalformedURLException unused) {
            Log.e(ga.a(d), "Cannot construct store credentials request");
            return null;
        }
    }
}
