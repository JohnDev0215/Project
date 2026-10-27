package com.amazon.identity.auth.device;

import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class gb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f352a = "com.amazon.identity.auth.device.gb";

    public static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2, String str) {
        try {
            JSONObject jSONObject3 = new JSONObject();
            if (TextUtils.isEmpty(str)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                jSONObject3.put("dat", simpleDateFormat.format(new Date()));
            } else {
                jSONObject3.put("dat", str);
            }
            jSONObject3.put("dev", jSONObject);
            jSONObject3.put("cust", jSONObject2);
            return jSONObject3;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0040, code lost:
    
        r6 = r8.split(":")[1].trim();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ba A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r6v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.json.JSONObject a(java.lang.String r6, java.lang.String r7, java.lang.String r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.gb.a(java.lang.String, java.lang.String, java.lang.String):org.json.JSONObject");
    }

    public static JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("typ", "v1");
        return jSONObject;
    }
}
