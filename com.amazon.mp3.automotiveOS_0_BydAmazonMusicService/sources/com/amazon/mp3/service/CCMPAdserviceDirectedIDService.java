package com.amazon.mp3.service;

import android.content.Context;
import com.amazon.mp3.util.Log;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: CCMPAdserviceDirectedIDService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J4\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0010\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\bR\u001c\u0010\u000e\u001a\n \r*\u0004\u0018\u00010\u00040\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/service/CCMPAdserviceDirectedIDService;", "", "Landroid/content/Context;", "context", "", "getDirectedIdEndpointURL", "customerId", "thirdPartyIdentifier", "Lcom/android/volley/Response$Listener;", "Lorg/json/JSONObject;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "getDirectedId", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CCMPAdserviceDirectedIDService {
    public static final CCMPAdserviceDirectedIDService INSTANCE = new CCMPAdserviceDirectedIDService();
    private static final String TAG = CCMPAdserviceDirectedIDService.class.getSimpleName();

    private CCMPAdserviceDirectedIDService() {
    }

    public final void getDirectedId(Context context, String customerId, String thirdPartyIdentifier, Response.Listener<JSONObject> listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        RequestQueue requestQueueNewRequestQueue = Volley.newRequestQueue(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("customerId", customerId);
            jSONObject.put("thirdParty", thirdPartyIdentifier);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, getDirectedIdEndpointURL(context), jSONObject, listener, new Response.ErrorListener() { // from class: com.amazon.mp3.service.CCMPAdserviceDirectedIDService$$ExternalSyntheticLambda0
                @Override // com.android.volley.Response.ErrorListener
                public final void onErrorResponse(VolleyError volleyError) {
                    CCMPAdserviceDirectedIDService.m1618getDirectedId$lambda0(volleyError);
                }
            });
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(15000, 1, 1.0f));
            requestQueueNewRequestQueue.add(jsonObjectRequest);
            Log.debug(TAG, "DirectedId API call sent to the queue with thirdParty %s", thirdPartyIdentifier);
        } catch (JSONException e) {
            Log.error(TAG, "JSONException received: %s", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getDirectedId$lambda-0, reason: not valid java name */
    public static final void m1618getDirectedId$lambda0(VolleyError volleyError) {
        Log.error(TAG, "Error calling the Directed ID API", volleyError);
    }

    private final String getDirectedIdEndpointURL(Context context) {
        return context.getApplicationInfo().flags != 0 ? "https://omocodci0k.execute-api.us-east-1.amazonaws.com/prod/" : "https://e0pe4mfc07.execute-api.us-east-1.amazonaws.com/prod/";
    }
}
