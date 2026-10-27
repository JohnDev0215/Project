package com.loostone.libserver.version2.net;

import com.android.volley.AuthFailureError;
import com.android.volley.BuildConfig;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class HeaderJsonObjectRequest extends JsonObjectRequest {
    public static final OooO00o Companion = new OooO00o(null);
    private static String mHeader = BuildConfig.FLAVOR;

    public static final class OooO00o {
        public OooO00o() {
        }

        public /* synthetic */ OooO00o(f fVar) {
            this();
        }

        public final void OooO00o(String str) {
            h.d(str, "header");
            HeaderJsonObjectRequest.mHeader = str;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeaderJsonObjectRequest(int i, String str, JSONObject jSONObject, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(i, str, jSONObject, listener, errorListener);
        h.d(listener, "listener");
        h.d(errorListener, "errorListener");
    }

    @Override // com.android.volley.Request
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (h.a(mHeader, BuildConfig.FLAVOR)) {
            Map<String, String> headers = super.getHeaders();
            h.c(headers, "super.getHeaders()");
            return headers;
        }
        HashMap map = new HashMap();
        map.put("accessToken", mHeader);
        return map;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeaderJsonObjectRequest(String str, Response.Listener<JSONObject> listener, JSONObject jSONObject, Response.ErrorListener errorListener) {
        super(str, jSONObject, listener, errorListener);
        h.d(listener, "listener");
        h.d(jSONObject, "jsonObject");
        h.d(errorListener, "errorListener");
    }
}
