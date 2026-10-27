package f4;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Message.java */
/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4961a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f4962b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f4963c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f4964d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4965e;

    public static List<f> k(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i8 = 0; i8 < jSONArray.length(); i8++) {
                f fVar = new f();
                JSONObject jSONObject = jSONArray.getJSONObject(i8);
                fVar.h(jSONObject.has("handlerName") ? jSONObject.getString("handlerName") : null);
                fVar.f(jSONObject.has("callbackId") ? jSONObject.getString("callbackId") : null);
                fVar.i(jSONObject.has("responseData") ? jSONObject.getString("responseData") : null);
                fVar.j(jSONObject.has("responseId") ? jSONObject.getString("responseId") : null);
                fVar.g(jSONObject.has("data") ? jSONObject.getString("data") : null);
                arrayList.add(fVar);
            }
        } catch (JSONException e8) {
            e8.printStackTrace();
        }
        return arrayList;
    }

    public String a() {
        return this.f4961a;
    }

    public String b() {
        return this.f4964d;
    }

    public String c() {
        return this.f4965e;
    }

    public String d() {
        return this.f4963c;
    }

    public String e() {
        return this.f4962b;
    }

    public void f(String str) {
        this.f4961a = str;
    }

    public void g(String str) {
        this.f4964d = str;
    }

    public void h(String str) {
        this.f4965e = str;
    }

    public void i(String str) {
        this.f4963c = str;
    }

    public void j(String str) {
        this.f4962b = str;
    }

    public String l() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("callbackId", a());
            jSONObject.put("data", b());
            jSONObject.put("handlerName", c());
            jSONObject.put("responseData", d());
            jSONObject.put("responseId", e());
            return jSONObject.toString();
        } catch (JSONException e8) {
            e8.printStackTrace();
            return null;
        }
    }
}
