package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.attributes.CORPFMResponse;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class p1 {
    public static final String g = "com.amazon.identity.auth.device.p1";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f486a;
    public final AuthenticationMethodFactory b;
    public final p4 c;
    public final o5 d;
    public final w5 e = new w5();
    public final y5 f;

    public p1(Context context, String str, y5 y5Var) {
        this.f486a = context;
        this.b = new AuthenticationMethodFactory(context, str);
        this.c = (p4) context.getSystemService("dcp_authenticated_url_connection_factory");
        this.d = (o5) context.getSystemService("dcp_device_info");
        this.f = y5Var;
    }

    /* JADX WARN: Not initialized variable reg: 14, insn: 0x01e3: MOVE (r3 I:??[OBJECT, ARRAY]) = (r14 I:??[OBJECT, ARRAY]), block:B:71:0x01e3 */
    /* JADX WARN: Removed duplicated region for block: B:53:0x017a A[Catch: all -> 0x01e2, TryCatch #0 {all -> 0x01e2, blocks: (B:18:0x00a5, B:25:0x00d3, B:27:0x00da, B:35:0x0113, B:32:0x0109, B:33:0x010b, B:34:0x010d, B:24:0x00d0, B:51:0x014a, B:53:0x017a, B:54:0x0183, B:56:0x01ad, B:58:0x01b1, B:65:0x01c6, B:63:0x01be, B:64:0x01c1), top: B:79:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01ad A[Catch: all -> 0x01e2, TryCatch #0 {all -> 0x01e2, blocks: (B:18:0x00a5, B:25:0x00d3, B:27:0x00da, B:35:0x0113, B:32:0x0109, B:33:0x010b, B:34:0x010d, B:24:0x00d0, B:51:0x014a, B:53:0x017a, B:54:0x0183, B:56:0x01ad, B:58:0x01b1, B:65:0x01c6, B:63:0x01be, B:64:0x01c1), top: B:79:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.identity.auth.attributes.CORPFMResponse a() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 495
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.p1.a():com.amazon.identity.auth.attributes.CORPFMResponse");
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x00d9: MOVE (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:25:0x00d9 */
    public CORPFMResponse a(HttpURLConnection httpURLConnection) throws Throwable {
        Closeable closeable;
        InputStream inputStream;
        Closeable closeable2 = null;
        try {
        } catch (Throwable th) {
            th = th;
            closeable2 = closeable;
        }
        try {
            try {
                inputStream = httpURLConnection.getInputStream();
                try {
                    String str = new String(sa.a(inputStream));
                    String str2 = g;
                    ga.a(str2);
                    JSONObject jSONObject = new JSONObject(str);
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("customerAttribute");
                    if (jSONObjectOptJSONObject == null) {
                        Log.w(ga.a(str2), "The server returned an error with message: " + da.a(jSONObject, "Message", "Internal Error."));
                        sa.a((Closeable) inputStream);
                        return null;
                    }
                    if (!jSONObjectOptJSONObject.has("cor") && !jSONObjectOptJSONObject.has("pfm")) {
                        Log.w(ga.a(str2), "The server did not return a cor pfm for the customer. Message: " + da.a(jSONObjectOptJSONObject, "Description", "Generic Error. No COR or PFM found."));
                        sa.a((Closeable) inputStream);
                        return null;
                    }
                    String strA = da.a(jSONObjectOptJSONObject, "cor", (String) null);
                    String strA2 = da.a(jSONObjectOptJSONObject, "pfm", (String) null);
                    String strA3 = da.a(jSONObjectOptJSONObject, "sourceOfComputationCOR", (String) null);
                    String strA4 = da.a(jSONObjectOptJSONObject, "sourceOfComputationPFM", (String) null);
                    String strA5 = da.a(jSONObjectOptJSONObject, "computationConfidenceValue", (String) null);
                    Log.i(ga.a(str2), String.format("Received response with: %nCoR: %s %nPFM:%s %nSource Of Computation CoR: %s %n Source Of Computation PFM: %s %n Computation Confidence Value: %s", strA, strA2, strA3, strA4, strA5));
                    CORPFMResponse.ComputationConfidenceValue fromValue = CORPFMResponse.ComputationConfidenceValue.parseFromValue(strA5, CORPFMResponse.ComputationConfidenceValue.CUSTOMER_PROVIDED);
                    yd.a("fetchCORPFMSuccess", new String[0]);
                    this.e.getClass();
                    CORPFMResponse cORPFMResponse = new CORPFMResponse(strA, strA2, fromValue, Long.valueOf(System.currentTimeMillis()));
                    sa.a((Closeable) inputStream);
                    return cORPFMResponse;
                } catch (IOException e) {
                    e = e;
                    yd.a("fetchCORPFMFailure", "IOException");
                    k4.a(httpURLConnection, "DCA service");
                    throw e;
                } catch (JSONException e2) {
                    e = e2;
                    yd.a("fetchCORPFMFailure", "JSONException");
                    Log.e(ga.a(g), "Error parsing DCAS JSON Response: " + e.getMessage());
                    sa.a((Closeable) inputStream);
                    return null;
                }
            } catch (IOException e3) {
                e = e3;
            } catch (JSONException e4) {
                e = e4;
                inputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            sa.a(closeable2);
            throw th;
        }
    }
}
