package com.amazon.mp3.store.html5.map;

import android.content.Context;
import android.os.Build;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.config.ApplicationAttributes;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: ApplicationContextCookie.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\u0006\u0010\u0006\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/store/html5/map/ApplicationContextCookie;", "", "()V", "cookieString", "", "appCtxtValue", "getCookieString", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ApplicationContextCookie {
    private String cookieString = Intrinsics.stringPlus("amzn-app-ctxt=", appCtxtValue());

    public final String getCookieString() {
        return this.cookieString;
    }

    private final String appCtxtValue() throws UnsupportedEncodingException {
        Context context = AmazonApplication.getContext();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pr", Build.PRODUCT);
        jSONObject.put("md", Build.MODEL);
        jSONObject.put("v", Build.VERSION.RELEASE);
        jSONObject.put("mf", Build.MANUFACTURER);
        JSONObject jSONObject2 = new JSONObject();
        int i = context.getResources().getDisplayMetrics().widthPixels;
        int i2 = context.getResources().getDisplayMetrics().heightPixels;
        int i3 = context.getResources().getDisplayMetrics().densityDpi;
        jSONObject2.put("w", i);
        jSONObject2.put("h", i2);
        jSONObject2.put("ld", i3);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("an", "DigitalMusicAndroid3P");
        jSONObject3.put("av", ApplicationAttributes.getInstance(context).getVersion());
        jSONObject3.put("xv", "1.0");
        jSONObject3.put("os", "Android");
        jSONObject3.put("ov", String.valueOf(Build.VERSION.SDK_INT));
        jSONObject3.put("cp", 0);
        jSONObject3.put("di", jSONObject);
        jSONObject3.put("dm", jSONObject2);
        String string = jSONObject3.toString();
        Intrinsics.checkNotNullExpressionValue(string, "cookieJSONObject.toString()");
        String strEncode = URLEncoder.encode(Intrinsics.stringPlus("1.0 ", string), "utf-8");
        Intrinsics.checkNotNullExpressionValue(strEncode, "encode(decodedCookieValue,\"utf-8\")");
        return strEncode;
    }
}
