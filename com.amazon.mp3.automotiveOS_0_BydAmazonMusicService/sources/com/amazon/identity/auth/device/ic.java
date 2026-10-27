package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.kcpsdk.auth.PandaError;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ic<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f386a = "com.amazon.identity.auth.device.ic";

    public abstract T a(ob obVar) throws JSONException;

    public abstract T a(PandaError pandaError);

    public T a(JSONObject jSONObject) {
        JSONObject jSONObject2;
        try {
            jSONObject2 = jSONObject.getJSONObject("response");
        } catch (JSONException unused) {
            Log.e(ga.a(f386a), "Panda Response is not correctly formatted.");
        }
        if (jSONObject2.has("success")) {
            return b(jSONObject2.getJSONObject("success"));
        }
        if (!jSONObject2.has("error")) {
            if (jSONObject2.has("challenge")) {
                return a(ob.a(jSONObject2.getJSONObject("challenge")));
            }
            Log.e(ga.a(f386a), "Panda Response is not correctly formatted.");
            return a(PandaError.PandaErrorUnknown);
        }
        String strA = da.a(da.a(jSONObject2, "error"), "index", (String) null);
        if (!TextUtils.isEmpty(strA)) {
            String str = f386a;
            Log.e(ga.a(str), "Received Panda error index when parsing the error response: " + strA);
            ga.a(str);
        }
        JSONObject jSONObject3 = jSONObject2.getJSONObject("error");
        String string = jSONObject.getString("request_id");
        PandaError pandaError = PandaError.getPandaError(jSONObject3.getString("code"));
        if (pandaError == null) {
            return c(jSONObject3);
        }
        Log.w(ga.a(f386a), String.format("Panda Error:  %s. Request ID: %s", jSONObject3.toString(), string));
        return a(pandaError);
    }

    public abstract T b(JSONObject jSONObject) throws JSONException;

    public abstract T c(JSONObject jSONObject) throws JSONException;
}
