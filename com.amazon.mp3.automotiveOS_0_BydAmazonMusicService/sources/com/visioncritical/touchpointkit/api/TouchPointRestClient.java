package com.visioncritical.touchpointkit.api;

import android.content.Context;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazonaws.http.HttpHeader;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.visioncritical.touchpointkit.commons.TouchPointCommon;
import com.visioncritical.touchpointkit.commons.TouchPointConfig;
import com.visioncritical.touchpointkit.commons.TouchPointLogger;
import com.visioncritical.touchpointkit.models.TouchPointScreenComponent;
import com.visioncritical.touchpointkit.models.TouchPointTrigger;
import com.visioncritical.touchpointkit.utils.TouchPointActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: TouchPointRestClient.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J*\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u001a\u0010\n\u001a\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u0012\u0004\u0012\u00020\u00070\u000bJ(\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u0012JF\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0018\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/visioncritical/touchpointkit/api/TouchPointRestClient;", "", "()V", ParserUtil.TAG_QUERY_PARAM_NAME, "", "getBaseURL", "getTriggers", "", "context", "Landroid/content/Context;", "callback", "Lkotlin/Function1;", "", "Lcom/visioncritical/touchpointkit/models/TouchPointTrigger;", "trackSentryProxy", "level", "exception", "apiError", "", "trackTriggerEvent", "screenName", "componentName", "triggerID", "distributionID", "eventType", "touchpointkit_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TouchPointRestClient {
    private final String tag = "TouchPointRestClient";

    public final void getTriggers(final Context context, final Function1<? super List<TouchPointTrigger>, Unit> callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        String baseURL = getBaseURL();
        if (baseURL == null) {
            TouchPointLogger.INSTANCE.errorLog(this.tag, "Triggers URL is nil");
            callback.invoke(null);
            return;
        }
        final String strStringPlus = Intrinsics.stringPlus(baseURL, "/distributor/distributor.MobileAPI/GetTriggersAndConfigure");
        final JSONObject jSONObject = new JSONObject();
        TouchPointActivity.Companion companion = TouchPointActivity.INSTANCE;
        jSONObject.put("api_key", companion.getShared().getApiKey());
        if (!companion.getShared().getDisableApiFilter()) {
            HashMap<String, Object> visitor = companion.getShared().getVisitor();
            String visitorId = companion.getShared().getVisitorId();
            HashMap<String, String> userAttributes = companion.getShared().getUserAttributes();
            List<TouchPointScreenComponent> screenComponents = companion.getShared().getScreenComponents();
            String uuid = companion.getShared().getUUID();
            if (visitorId.length() > 0) {
                jSONObject.put("visitor_id", visitorId);
            }
            if (!visitor.isEmpty()) {
                JSONObject jSONObject2 = new JSONObject();
                for (String str : visitor.keySet()) {
                    jSONObject2.put(str, String.valueOf(visitor.get(str)));
                }
                jSONObject.put("visitor", jSONObject2);
            }
            if (!userAttributes.isEmpty()) {
                jSONObject.put("user_attributes", new JSONObject((Map<?, ?>) userAttributes));
            }
            if (!screenComponents.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (TouchPointScreenComponent touchPointScreenComponent : screenComponents) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("screen_name", touchPointScreenComponent.getScreenName());
                    if (touchPointScreenComponent.getComponentName() != null) {
                        jSONObject3.put("component_name", touchPointScreenComponent.getComponentName());
                    }
                    jSONArray.put(jSONObject3);
                }
                jSONObject.put("screen_components", jSONArray);
            }
            if (uuid.length() > 0) {
                jSONObject.put("local_storage_id", uuid);
            }
        }
        final Response.Listener listener = new Response.Listener() { // from class: com.visioncritical.touchpointkit.api.TouchPointRestClient$$ExternalSyntheticLambda2
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                TouchPointRestClient.m2785getTriggers$lambda0(callback, this, context, (JSONObject) obj);
            }
        };
        final Response.ErrorListener errorListener = new Response.ErrorListener() { // from class: com.visioncritical.touchpointkit.api.TouchPointRestClient$$ExternalSyntheticLambda3
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                TouchPointRestClient.m2786getTriggers$lambda1(this.f$0, context, callback, volleyError);
            }
        };
        TouchPointVolleySingleton.INSTANCE.getInstance(context).addToRequestQueue(new JsonObjectRequest(strStringPlus, jSONObject, listener, errorListener) { // from class: com.visioncritical.touchpointkit.api.TouchPointRestClient$getTriggers$jsonObjectRequest$1
            final /* synthetic */ String $apiUrl;
            final /* synthetic */ JSONObject $jsonObject;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1, strStringPlus, jSONObject, listener, errorListener);
                this.$apiUrl = strStringPlus;
                this.$jsonObject = jSONObject;
            }

            @Override // com.android.volley.Request
            public Map<String, String> getHeaders() {
                String hmac = new TouchPointCommon().getHMAC("GetTriggersAndConfigure");
                HashMap map = new HashMap();
                map.put(HttpHeader.AUTHORIZATION, Intrinsics.stringPlus("hmac ", hmac));
                return map;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getTriggers$lambda-0, reason: not valid java name */
    public static final void m2785getTriggers$lambda0(Function1 function1, TouchPointRestClient this$0, Context context, JSONObject jSONObject) {
        String str;
        String str2;
        TouchPointScreenComponent[] touchPointScreenComponentArr;
        Function1 callback = function1;
        String str3 = AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE;
        String str4 = "screen_components";
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("triggers");
            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
                ArrayList arrayList = new ArrayList();
                int length = jSONArrayOptJSONArray.length();
                int i = 0;
                while (i < length) {
                    int i2 = i + 1;
                    try {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                        String id = jSONObject2.optString("id");
                        String captionText = jSONObject2.optString("caption_text");
                        String distributionUrl = jSONObject2.optString("distribution_url");
                        JSONArray jSONArray = jSONArrayOptJSONArray;
                        String distributionID = jSONObject2.optString("dist_id");
                        JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray(str4);
                        int i3 = length;
                        boolean zOptBoolean = jSONObject2.optBoolean("use_banner_styling");
                        boolean zOptBoolean2 = jSONObject2.optBoolean("always_show");
                        long jOptLong = jSONObject2.optLong("delay_in_ms");
                        String strOptString = jSONObject2.optString(str3);
                        boolean zOptBoolean3 = jSONObject2.optBoolean("show_on_all_screens");
                        Object obj = jSONObject2.get("style");
                        if (obj == null) {
                            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                        }
                        JSONObject jSONObject3 = (JSONObject) obj;
                        String backgroundColor = jSONObject3.optString("backgroundColor");
                        String textColor = jSONObject3.optString("textColor");
                        ArrayList arrayList2 = arrayList;
                        int i4 = 0;
                        TouchPointScreenComponent[] touchPointScreenComponentArr2 = new TouchPointScreenComponent[0];
                        if (jSONObject2.has(str4)) {
                            int length2 = jSONArrayOptJSONArray2.length();
                            TouchPointScreenComponent[] touchPointScreenComponentArr3 = new TouchPointScreenComponent[length2];
                            while (i4 < length2) {
                                String str5 = str3;
                                String string = jSONArrayOptJSONArray2.optJSONObject(i4).getString("screen_name");
                                Intrinsics.checkNotNullExpressionValue(string, "screenComponents.optJSON….getString(\"screen_name\")");
                                touchPointScreenComponentArr3[i4] = new TouchPointScreenComponent(string, jSONArrayOptJSONArray2.optJSONObject(i4).optString("component_name"));
                                i4++;
                                length2 = length2;
                                jSONArrayOptJSONArray2 = jSONArrayOptJSONArray2;
                                str4 = str4;
                                str3 = str5;
                            }
                            str = str3;
                            str2 = str4;
                            touchPointScreenComponentArr = touchPointScreenComponentArr3;
                        } else {
                            str = str3;
                            str2 = str4;
                            touchPointScreenComponentArr = touchPointScreenComponentArr2;
                        }
                        Intrinsics.checkNotNullExpressionValue(id, "id");
                        Intrinsics.checkNotNullExpressionValue(captionText, "captionText");
                        Intrinsics.checkNotNullExpressionValue(distributionUrl, "distributionUrl");
                        Intrinsics.checkNotNullExpressionValue(distributionID, "distributionID");
                        Intrinsics.checkNotNullExpressionValue(backgroundColor, "backgroundColor");
                        Intrinsics.checkNotNullExpressionValue(textColor, "textColor");
                        str3 = str;
                        Intrinsics.checkNotNullExpressionValue(strOptString, str3);
                        arrayList2.add(new TouchPointTrigger(id, captionText, distributionUrl, distributionID, touchPointScreenComponentArr, backgroundColor, textColor, zOptBoolean, zOptBoolean2, jOptLong, strOptString, zOptBoolean3));
                        callback = function1;
                        arrayList = arrayList2;
                        jSONArrayOptJSONArray = jSONArray;
                        length = i3;
                        i = i2;
                        str4 = str2;
                    } catch (Exception e) {
                        e = e;
                        callback = function1;
                        TouchPointLogger.INSTANCE.errorLog(this$0.tag, Intrinsics.stringPlus("getTriggers: error: ", e));
                        trackSentryProxy$default(this$0, context, "error", e.toString(), false, 8, null);
                        callback.invoke(null);
                        return;
                    }
                }
                callback.invoke(arrayList);
            } else {
                callback.invoke(null);
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getTriggers$lambda-1, reason: not valid java name */
    public static final void m2786getTriggers$lambda1(TouchPointRestClient this$0, Context context, Function1 callback, VolleyError volleyError) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        TouchPointLogger.INSTANCE.errorLog(this$0.tag, Intrinsics.stringPlus("getTriggers: error: ", volleyError));
        trackSentryProxy$default(this$0, context, "error", volleyError.toString(), false, 8, null);
        callback.invoke(null);
    }

    public final void trackTriggerEvent(final Context context, String screenName, String componentName, String triggerID, String distributionID, String eventType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        String baseURL = getBaseURL();
        if (baseURL == null) {
            return;
        }
        String strStringPlus = Intrinsics.stringPlus(baseURL, "/tracker/tracker.Tracker/TriggerEvent");
        JSONObject jSONObject = new JSONObject();
        TouchPointActivity.Companion companion = TouchPointActivity.INSTANCE;
        jSONObject.put("api_key", companion.getShared().getApiKey());
        jSONObject.put(MAPWebViewEventHelper.KEY_EVENT_TYPE, eventType);
        String visitorId = companion.getShared().getVisitorId();
        String uuid = companion.getShared().getUUID();
        if (visitorId.length() > 0) {
            jSONObject.put("visitor_id", visitorId);
        }
        if (uuid.length() > 0) {
            jSONObject.put("local_storage_id", uuid);
        }
        if (screenName != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("screen_name", screenName);
            if (componentName != null) {
                jSONObject2.put("component_name", componentName);
            }
            jSONObject.put("screen_component", jSONObject2);
        }
        if (triggerID != null) {
            jSONObject.put("trigger_id", triggerID);
        }
        if (distributionID != null) {
            jSONObject.put("dist_id", distributionID);
        }
        TouchPointVolleySingleton.INSTANCE.getInstance(context).addToRequestQueue(new JsonObjectRequest(1, strStringPlus, jSONObject, new Response.Listener() { // from class: com.visioncritical.touchpointkit.api.TouchPointRestClient$$ExternalSyntheticLambda0
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                TouchPointRestClient.m2789trackTriggerEvent$lambda2(this.f$0, (JSONObject) obj);
            }
        }, new Response.ErrorListener() { // from class: com.visioncritical.touchpointkit.api.TouchPointRestClient$$ExternalSyntheticLambda1
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                TouchPointRestClient.m2790trackTriggerEvent$lambda3(this.f$0, context, volleyError);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: trackTriggerEvent$lambda-2, reason: not valid java name */
    public static final void m2789trackTriggerEvent$lambda2(TouchPointRestClient this$0, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchPointLogger.INSTANCE.debugLog(this$0.tag, "trackTriggerEvent: event sent successfully");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: trackTriggerEvent$lambda-3, reason: not valid java name */
    public static final void m2790trackTriggerEvent$lambda3(TouchPointRestClient this$0, Context context, VolleyError volleyError) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        TouchPointLogger.INSTANCE.errorLog(this$0.tag, Intrinsics.stringPlus("trackTriggerEvent: error: ", volleyError));
        trackSentryProxy$default(this$0, context, "error", volleyError.toString(), false, 8, null);
    }

    public static /* synthetic */ void trackSentryProxy$default(TouchPointRestClient touchPointRestClient, Context context, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        touchPointRestClient.trackSentryProxy(context, str, str2, z);
    }

    public final void trackSentryProxy(Context context, String level, String exception, boolean apiError) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(level, "level");
        Intrinsics.checkNotNullParameter(exception, "exception");
        String baseURL = getBaseURL();
        if (baseURL == null) {
            return;
        }
        String strStringPlus = Intrinsics.stringPlus(baseURL, "/tracker/tracker.Tracker/SentryProxy");
        JSONObject jSONObject = new JSONObject();
        TouchPointActivity.Companion companion = TouchPointActivity.INSTANCE;
        jSONObject.put("app_id", companion.getShared().getApiKey());
        jSONObject.put("api_error", apiError);
        jSONObject.put("app_name", "APP_MOBILE_SDK");
        jSONObject.put("version", "0.1");
        jSONObject.put("level", level);
        jSONObject.put("pod_name", companion.getShared().getPodName());
        jSONObject.put("captured_exception", exception);
        TouchPointVolleySingleton.INSTANCE.getInstance(context).addToRequestQueue(new JsonObjectRequest(1, strStringPlus, jSONObject, new Response.Listener() { // from class: com.visioncritical.touchpointkit.api.TouchPointRestClient$$ExternalSyntheticLambda4
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                TouchPointRestClient.m2787trackSentryProxy$lambda4(this.f$0, (JSONObject) obj);
            }
        }, new Response.ErrorListener() { // from class: com.visioncritical.touchpointkit.api.TouchPointRestClient$$ExternalSyntheticLambda5
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                TouchPointRestClient.m2788trackSentryProxy$lambda5(this.f$0, volleyError);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: trackSentryProxy$lambda-4, reason: not valid java name */
    public static final void m2787trackSentryProxy$lambda4(TouchPointRestClient this$0, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchPointLogger.INSTANCE.debugLog(this$0.tag, "trackSentryProxy: event sent successfully");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: trackSentryProxy$lambda-5, reason: not valid java name */
    public static final void m2788trackSentryProxy$lambda5(TouchPointRestClient this$0, VolleyError volleyError) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TouchPointLogger.INSTANCE.errorLog(this$0.tag, Intrinsics.stringPlus("trackSentryProxy: error: ", volleyError));
    }

    private final String getBaseURL() {
        String podName = TouchPointActivity.INSTANCE.getShared().getPodName();
        if (podName.length() == 0) {
            return null;
        }
        String lowerCase = StringsKt__StringsKt.trim((CharSequence) podName).toString().toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        return TouchPointConfig.INSTANCE.getApiMap().get(lowerCase);
    }
}
