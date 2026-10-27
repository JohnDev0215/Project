package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class da {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f281a = "com.amazon.identity.auth.device.da";

    public static String a(JSONObject jSONObject, String str, String str2) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str) && !jSONObject.isNull(str)) {
                    return jSONObject.getString(str);
                }
            } catch (JSONException e) {
                Log.i(ga.a(f281a), String.format("Problem parsing JSON for key %s. Error: %s", str, e.getMessage()));
            }
        }
        return str2;
    }

    public static boolean a(JSONObject jSONObject, String str, boolean z) {
        try {
            if (jSONObject.has(str) && !jSONObject.isNull(str)) {
                return jSONObject.getBoolean(str);
            }
        } catch (JSONException e) {
            Log.i(ga.a(f281a), String.format("Problem parsing JSON for key %s. Error: %s", str, e.getMessage()));
        }
        return z;
    }

    public static JSONObject a(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return null;
        }
        try {
            if (!jSONObject.has(str) || jSONObject.isNull(str)) {
                return null;
            }
            return jSONObject.getJSONObject(str);
        } catch (JSONException e) {
            Log.i(ga.a(f281a), String.format("Problem parsing JSON for key %s. Error: %s", str, e.getMessage()));
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0096 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.json.JSONObject a(java.net.HttpURLConnection r12) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "IOException thrown closing input stream"
            r1 = 1
            r2 = 0
            r3 = 0
            java.io.InputStream r4 = r12.getInputStream()     // Catch: java.lang.Throwable -> Lb java.io.IOException -> Le
            r5 = r2
            goto L13
        Lb:
            r12 = move-exception
            goto L94
        Le:
            java.io.InputStream r4 = r12.getErrorStream()     // Catch: java.lang.Throwable -> Lb
            r5 = r1
        L13:
            if (r4 != 0) goto L25
            if (r4 == 0) goto L24
            r4.close()     // Catch: java.io.IOException -> L1b
            goto L24
        L1b:
            java.lang.String r12 = com.amazon.identity.auth.device.da.f281a
            java.lang.String r12 = com.amazon.identity.auth.device.ga.a(r12)
            android.util.Log.e(r12, r0)
        L24:
            return r3
        L25:
            byte[] r6 = com.amazon.identity.auth.device.sa.a(r4)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            java.lang.String r7 = new java.lang.String     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            java.lang.String r6 = com.amazon.identity.auth.device.da.f281a     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            java.lang.String r8 = "Request to %s received response %s"
            r9 = 2
            java.lang.Object[] r10 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            java.net.URL r11 = r12.getURL()     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            java.lang.String r11 = r11.toString()     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            r10[r2] = r11     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            r10[r1] = r7     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            com.amazon.identity.auth.device.ga.a(r6, r8, r10)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            if (r5 == 0) goto L64
            java.lang.String r5 = "Request to %s received error code %s and response %s"
            r8 = 3
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            java.net.URL r10 = r12.getURL()     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            r8[r2] = r10     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            int r12 = r12.getResponseCode()     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            r8[r1] = r12     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            r8[r9] = r7     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            com.amazon.identity.auth.device.ga.a(r6, r5, r8)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
        L64:
            org.json.JSONObject r12 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            r12.<init>(r7)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7a
            r4.close()     // Catch: java.io.IOException -> L6d
            goto L76
        L6d:
            java.lang.String r1 = com.amazon.identity.auth.device.da.f281a
            java.lang.String r1 = com.amazon.identity.auth.device.ga.a(r1)
            android.util.Log.e(r1, r0)
        L76:
            return r12
        L77:
            r12 = move-exception
            r3 = r4
            goto L94
        L7a:
            r12 = move-exception
            java.lang.String r1 = com.amazon.identity.auth.device.da.f281a     // Catch: java.lang.Throwable -> L77
            java.lang.String r2 = "Could not parse response because of network issue"
            java.lang.String r1 = com.amazon.identity.auth.device.ga.a(r1)     // Catch: java.lang.Throwable -> L77
            android.util.Log.e(r1, r2, r12)     // Catch: java.lang.Throwable -> L77
            r4.close()     // Catch: java.io.IOException -> L8a
            goto L93
        L8a:
            java.lang.String r12 = com.amazon.identity.auth.device.da.f281a
            java.lang.String r12 = com.amazon.identity.auth.device.ga.a(r12)
            android.util.Log.e(r12, r0)
        L93:
            return r3
        L94:
            if (r3 == 0) goto La3
            r3.close()     // Catch: java.io.IOException -> L9a
            goto La3
        L9a:
            java.lang.String r1 = com.amazon.identity.auth.device.da.f281a
            java.lang.String r1 = com.amazon.identity.auth.device.ga.a(r1)
            android.util.Log.e(r1, r0)
        La3:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.da.a(java.net.HttpURLConnection):org.json.JSONObject");
    }

    public static String a(Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(String.format("\"%s\":\"%s\"", entry.getKey(), entry.getValue()));
        }
        return String.format("{%s}", TextUtils.join(", ", arrayList));
    }
}
