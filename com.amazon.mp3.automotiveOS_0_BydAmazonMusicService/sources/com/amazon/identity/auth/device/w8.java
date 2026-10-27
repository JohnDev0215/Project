package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class w8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AuthEndpointErrorParser f645a = new AuthEndpointErrorParser();
    public ha b;
    public Context c;

    /* JADX INFO: compiled from: DCP */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f646a;
        public final int b;

        public a(String str, int i) {
            this.f646a = str;
            this.b = i;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f647a;

        public b(String str, String str2) {
            this.f647a = str;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f648a;
        public b b;
        public a c;
        public b d;
        public a e;

        public c(String str) {
            this.f648a = str;
        }
    }

    public w8(Context context) {
        this.c = context;
        this.b = new ha(context);
    }

    public List<c> a(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = jSONObject.getJSONArray("device_tokens");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            c cVar = new c(jSONObject2.getString(CentralAccountManagerCommunication.RegisterChildApplication.KEY_DEVICE_TYPE));
            JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject("actor_access_token");
            if (jSONObjectOptJSONObject != null) {
                cVar.e = new a(jSONObjectOptJSONObject.optString("token"), jSONObjectOptJSONObject.optInt("expires_in"));
            }
            JSONObject jSONObjectOptJSONObject2 = jSONObject2.optJSONObject("actor_refresh_token");
            if (jSONObjectOptJSONObject2 != null) {
                cVar.d = new b(jSONObjectOptJSONObject2.optString("token"), jSONObjectOptJSONObject2.optString("actor_id"));
            }
            JSONObject jSONObjectOptJSONObject3 = jSONObject2.optJSONObject("account_access_token");
            if (jSONObjectOptJSONObject3 != null) {
                cVar.c = new a(jSONObjectOptJSONObject3.optString("token"), jSONObjectOptJSONObject3.optInt("expires_in"));
            }
            JSONObject jSONObjectOptJSONObject4 = jSONObject2.optJSONObject("account_refresh_token");
            if (jSONObjectOptJSONObject4 != null) {
                cVar.b = new b(jSONObjectOptJSONObject4.optString("token"), jSONObjectOptJSONObject4.optString("account_id"));
            }
            arrayList.add(cVar);
        }
        return arrayList;
    }
}
