package defpackage;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: Nq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0249Nq {
    public EnumC0231Mq a;
    public String b;
    public String d;
    public List e;
    public String f;
    public long c = -1;
    public long g = -1;

    public C0249Nq(String str) {
        this.b = str;
    }

    public static C0249Nq a(String str) throws InvalidObjectException {
        JSONArray jSONArray;
        JSONObject jSONObject = new JSONObject(str);
        if (!jSONObject.has("crash-local-id")) {
            throw new InvalidObjectException("JSON Object doesn't have the field crash-local-id");
        }
        C0249Nq c0249Nq = new C0249Nq(jSONObject.getString("crash-local-id"));
        if (jSONObject.has("crash-capture-time")) {
            c0249Nq.c = jSONObject.getLong("crash-capture-time");
        }
        if (jSONObject.has("app-package-name")) {
            c0249Nq.d = jSONObject.getString("app-package-name");
        }
        if (jSONObject.has("variations") && (jSONArray = jSONObject.getJSONArray("variations")) != null) {
            c0249Nq.e = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                c0249Nq.e.add(jSONArray.getString(i));
            }
        }
        if (jSONObject.has("crash-upload-id")) {
            c0249Nq.f = jSONObject.getString("crash-upload-id");
        }
        if (jSONObject.has("crash-upload-time")) {
            c0249Nq.g = jSONObject.getLong("crash-upload-time");
        }
        return c0249Nq;
    }
}
