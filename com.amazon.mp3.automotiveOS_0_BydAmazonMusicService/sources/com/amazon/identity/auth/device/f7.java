package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazonaws.http.HttpHeader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class f7 {
    public static final String c = "com.amazon.identity.auth.device.f7";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f311a;
    public de b;

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public JSONObject f312a;
        public Integer b;
        public Exception c;
        public Integer d;

        public a(Integer num, JSONObject jSONObject, Exception exc, Integer num2) {
            this.b = num;
            this.f312a = jSONObject;
            this.c = exc;
            this.d = num2;
        }

        public void a() throws JSONException, IOException {
            Exception exc = this.c;
            if (exc != null) {
                if (exc instanceof IOException) {
                    throw ((IOException) exc);
                }
                if (exc instanceof JSONException) {
                    throw ((JSONException) exc);
                }
            }
        }
    }

    public f7(t5 t5Var) {
        this.f311a = t5Var;
    }

    public abstract String a(JSONObject jSONObject);

    public Map<String, String> a() {
        HashMap map = new HashMap();
        int i = new kb(this.f311a, null).c;
        if (i > 0) {
            map.put("x-amzn-identity-retry-attempt", String.valueOf(i));
        }
        return map;
    }

    public abstract AuthenticationMethod b();

    public abstract JSONObject b(y5 y5Var) throws JSONException;

    public final void b(HttpsURLConnection httpsURLConnection) throws IOException {
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setRequestProperty(HttpHeader.ACCEPT, "application/json");
        httpsURLConnection.setRequestProperty(HttpHeader.CONTENT_TYPE, "application/json");
        httpsURLConnection.setRequestProperty("x-amzn-identity-auth-domain", f());
    }

    /* JADX WARN: Not initialized variable reg: 13, insn: 0x02c9: MOVE (r12 I:??[OBJECT, ARRAY]) = (r13 I:??[OBJECT, ARRAY]), block:B:85:0x02c9 */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.identity.auth.device.f7.a c(com.amazon.identity.auth.device.y5 r17) {
        /*
            Method dump skipped, instruction units count: 720
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.f7.c(com.amazon.identity.auth.device.y5):com.amazon.identity.auth.device.f7$a");
    }

    public List<MAPCookie> c() {
        return null;
    }

    public abstract String d();

    public abstract String e();

    public abstract String f();

    public URL g() {
        String strH = h();
        if (TextUtils.isEmpty(strH)) {
            Log.e(ga.a(c), "No path specified for service call");
            throw new IllegalArgumentException("No path specified for service call");
        }
        try {
            return EnvironmentUtils.c.a(d(), strH);
        } catch (MalformedURLException e) {
            ga.b(c, "Domain or path for service call invalid", d(), strH, e.getMessage());
            throw new IllegalArgumentException("Domain or path for service call invalid", e);
        }
    }

    public abstract String h();

    public void a(HttpsURLConnection httpsURLConnection, List<MAPCookie> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (MAPCookie mAPCookie : list) {
            httpsURLConnection.addRequestProperty("Cookie", String.format("%s=%s", mAPCookie.c(), mAPCookie.e()));
        }
    }

    public void b(JSONObject jSONObject) {
        String strA = a(jSONObject);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        String str = c;
        Log.e(ga.a(str), "Error index is found in error response: " + strA);
        ga.a(str);
    }

    public final String a(String str) {
        if (!str.contains("auth/tiv/push_notification?token=")) {
            return str;
        }
        return new StringBuilder(str).replace(str.indexOf("token=") + 6 + 5 + 2, str.length() - 2, "****").toString();
    }

    public void a(Integer num, String str) {
        de deVar = this.b;
        if (deVar != null) {
            deVar.a(e.a(g(), num == null ? 0 : num.intValue(), str));
            this.b.c();
        }
    }

    public HttpsURLConnection a(y5 y5Var) throws IOException {
        URL urlG = g();
        t5 t5Var = this.f311a;
        return (HttpsURLConnection) ((HttpURLConnection) ib.a(e.a(urlG, new kb(t5Var, null), y5Var, t5Var)));
    }

    public final void a(HttpsURLConnection httpsURLConnection) throws IOException {
        httpsURLConnection.setRequestMethod(e());
        Map<String, String> mapA = a();
        if (mapA == null || mapA.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : mapA.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String str = c;
            String.format("Setting panda api %s connection properties:%s to %s", h(), key, value);
            ga.a(str);
            httpsURLConnection.setRequestProperty(key, value);
        }
    }
}
