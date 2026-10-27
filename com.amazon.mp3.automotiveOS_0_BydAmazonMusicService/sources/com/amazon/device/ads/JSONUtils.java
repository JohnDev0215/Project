package com.amazon.device.ads;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class JSONUtils {
    JSONUtils() {
    }

    public static class JSONUtilities {
        public Map<String, String> createMapFromJSON(JSONObject jSONObject) {
            return JSONUtils.createMapFromJSON(jSONObject);
        }

        public JSONObject getJSONObjectFromString(String str) {
            return JSONUtils.getJSONObjectFromString(str);
        }
    }

    public static boolean getBooleanFromJSON(JSONObject jSONObject, String str, boolean z) {
        return jSONObject.isNull(str) ? z : jSONObject.optBoolean(str, z);
    }

    public static String getStringFromJSON(JSONObject jSONObject, String str, String str2) {
        return jSONObject.isNull(str) ? str2 : jSONObject.optString(str, str2);
    }

    public static int getIntegerFromJSON(JSONObject jSONObject, String str, int i) {
        return jSONObject.isNull(str) ? i : jSONObject.optInt(str, i);
    }

    public static void put(JSONObject jSONObject, String str, String str2) {
        if (str2 == null || str2.equals("")) {
            return;
        }
        try {
            jSONObject.put(str, str2);
        } catch (JSONException unused) {
        }
    }

    public static void put(JSONObject jSONObject, String str, boolean z) {
        try {
            jSONObject.put(str, z);
        } catch (JSONException unused) {
        }
    }

    public static JSONObject getJSONObjectFromString(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    public static Map<String, String> createMapFromJSON(JSONObject jSONObject) {
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            String stringFromJSON = getStringFromJSON(jSONObject, next, null);
            if (stringFromJSON != null) {
                map.put(next, stringFromJSON);
            }
        }
        return map;
    }
}
