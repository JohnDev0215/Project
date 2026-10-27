package b.b.m.f.e;

import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Message.java */
/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2146a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f2147b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f2148c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f2149d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2150e;

    public static List<f> k(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                f fVar = new f();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                fVar.h(jSONObject.has("handlerName") ? jSONObject.getString("handlerName") : null);
                fVar.f(jSONObject.has("callbackId") ? jSONObject.getString("callbackId") : null);
                fVar.i(jSONObject.has("responseData") ? jSONObject.getString("responseData") : null);
                fVar.j(jSONObject.has("responseId") ? jSONObject.getString("responseId") : null);
                fVar.g(jSONObject.has("data") ? jSONObject.getString("data") : null);
                arrayList.add(fVar);
            }
        } catch (JSONException e2) {
            BydLogHelper.logE(e2.getMessage());
        }
        return arrayList;
    }

    public String a() {
        return this.f2146a;
    }

    public String b() {
        return this.f2149d;
    }

    public String c() {
        return this.f2150e;
    }

    public String d() {
        return this.f2148c;
    }

    public String e() {
        return this.f2147b;
    }

    public void f(String str) {
        this.f2146a = str;
    }

    public void g(String str) {
        this.f2149d = str;
    }

    public void h(String str) {
        this.f2150e = str;
    }

    public void i(String str) {
        this.f2148c = str;
    }

    public void j(String str) {
        this.f2147b = str;
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
        } catch (JSONException e2) {
            BydLogHelper.logE(e2.getMessage());
            return null;
        }
    }
}
