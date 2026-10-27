package defpackage;

import java.nio.charset.Charset;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: GCMMessage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wr0 {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final byte[] e;
    public final String[] f;

    /* JADX INFO: compiled from: GCMMessage.java */
    public static class a {
        public final boolean a(Object obj, String str) {
            return ((JSONObject) obj).has(str);
        }

        public final String b(Object obj, String str) {
            JSONObject jSONObject = (JSONObject) obj;
            if (JSONObject.NULL.equals(jSONObject.opt(str))) {
                return null;
            }
            return jSONObject.optString(str, null);
        }
    }

    public wr0(Object obj, a aVar) {
        this.a = aVar.b(obj, "senderId");
        this.b = aVar.b(obj, "appId");
        this.d = aVar.b(obj, "collapseKey");
        aVar.b(obj, "originalPriority");
        this.c = aVar.b(obj, "messageId");
        String strB = aVar.b(obj, "rawData");
        String[] strArr = null;
        if (strB == null) {
            this.e = null;
        } else if (strB.length() > 0) {
            this.e = strB.getBytes(Charset.forName("ISO-8859-1"));
        } else {
            this.e = new byte[0];
        }
        JSONArray jSONArrayOptJSONArray = ((JSONObject) obj).optJSONArray("data");
        if (jSONArrayOptJSONArray != null) {
            ArrayList arrayList = new ArrayList(jSONArrayOptJSONArray.length());
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.optString(i));
            }
            strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
        this.f = strArr;
    }

    public static wr0 a(JSONObject jSONObject) {
        a aVar = new a();
        if (!(aVar.a(jSONObject, "appId") && aVar.a(jSONObject, "collapseKey") && aVar.a(jSONObject, "data") && aVar.a(jSONObject, "rawData") && aVar.a(jSONObject, "senderId") && aVar.a(jSONObject, "originalPriority") && aVar.a(jSONObject, "messageId")) || aVar.b(jSONObject, "appId") == null || aVar.b(jSONObject, "senderId") == null) {
            return null;
        }
        return new wr0(jSONObject, aVar);
    }
}
