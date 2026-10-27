package com.visioncritical.touchpointkit.commons;

import android.util.Base64;
import com.amazon.music.destination.parser.ParserUtil;
import com.visioncritical.touchpointkit.utils.TouchPointActivity;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: TouchPointCommon.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u001a\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u000b\u001a\u00020\fJ\u0010\u0010\r\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004J\u0016\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/visioncritical/touchpointkit/commons/TouchPointCommon;", "", "()V", ParserUtil.TAG_QUERY_PARAM_NAME, "", "getActivityCollapsedScript", "urlString", "getHMAC", "message", "getParameterisedActivityUrlString", "activityUrl", "alwaysShow", "", "getVisitorTrackerScript", "toJSONString", "key", "value", "touchpointkit_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TouchPointCommon {
    private final String tag = "TouchPointCommon";

    public final String getHMAC(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        try {
            String apiSecret = TouchPointActivity.INSTANCE.getShared().getApiSecret();
            Mac mac = Mac.getInstance("HmacSHA256");
            Intrinsics.checkNotNullExpressionValue(mac, "getInstance(\"HmacSHA256\")");
            Charset charset = Charsets.UTF_8;
            byte[] bytes = apiSecret.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            mac.init(new SecretKeySpec(bytes, "HmacSHA256"));
            byte[] bytes2 = message.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
            String strEncodeToString = Base64.encodeToString(mac.doFinal(bytes2), 8);
            Intrinsics.checkNotNullExpressionValue(strEncodeToString, "encodeToString(\n        …64.URL_SAFE\n            )");
            String string = StringsKt__StringsKt.trim((CharSequence) strEncodeToString).toString();
            TouchPointLogger.INSTANCE.debugLog(this.tag, Intrinsics.stringPlus("getHMAC: ", string));
            return string;
        } catch (Exception e) {
            TouchPointLogger.INSTANCE.errorLog(this.tag, "getHMAC: " + e + ".localizedMessage");
            return "";
        }
    }

    public final String getParameterisedActivityUrlString(String activityUrl, boolean alwaysShow) {
        if (activityUrl == null) {
            return "";
        }
        TouchPointActivity.Companion companion = TouchPointActivity.INSTANCE;
        String visitorId = companion.getShared().getVisitorId();
        String uuid = companion.getShared().getUUID();
        if (alwaysShow) {
            return ((Object) activityUrl) + "?source=mobile_sdk&external_id=" + visitorId + "&sdk_local_storage_id=" + uuid + "&c=" + String.valueOf(System.currentTimeMillis() / ((long) 1000));
        }
        return ((Object) activityUrl) + "?source=mobile_sdk&external_id=" + visitorId + "&sdk_local_storage_id=" + uuid;
    }

    public final String getVisitorTrackerScript(String urlString) {
        if (urlString == null) {
            urlString = "";
        }
        HashMap<String, Object> visitor = TouchPointActivity.INSTANCE.getShared().getVisitor();
        ArrayList arrayList = new ArrayList(visitor.size());
        for (Map.Entry<String, Object> entry : visitor.entrySet()) {
            arrayList.add(toJSONString(entry.getKey(), entry.getValue()));
        }
        String str = "window.postMessage({type: 'SET_VISITOR',visitor: {" + CollectionsKt___CollectionsKt.joinToString$default(arrayList, ",", null, null, 0, null, null, 62, null) + "},},'" + urlString + "');";
        TouchPointLogger.INSTANCE.debugLog(this.tag, Intrinsics.stringPlus("getVisitorTrackerScript: ", str));
        return str;
    }

    public final String toJSONString(String key, Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        String strValueOf = String.valueOf(key);
        HashMap<String, String> userAttributes = TouchPointActivity.INSTANCE.getShared().getUserAttributes();
        if (Intrinsics.areEqual(userAttributes.get(key), "string") || Intrinsics.areEqual(userAttributes.get(key), "date") || Intrinsics.areEqual(key, "id")) {
            return strValueOf + ":'" + value + '\'';
        }
        return strValueOf + ':' + value;
    }

    public final String getActivityCollapsedScript(String urlString) {
        TouchPointLogger.INSTANCE.debugLog(this.tag, Intrinsics.stringPlus("getActivityCollapsedScript: ", "window.postMessage({type: 'ACTIVITY_COLLAPSED',},'*');"));
        return "window.postMessage({type: 'ACTIVITY_COLLAPSED',},'*');";
    }
}
