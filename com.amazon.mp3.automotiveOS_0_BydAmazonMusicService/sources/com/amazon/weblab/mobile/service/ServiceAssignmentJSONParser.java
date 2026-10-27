package com.amazon.weblab.mobile.service;

import android.util.Log;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
class ServiceAssignmentJSONParser {
    ServiceAssignmentJSONParser() {
    }

    private static ServiceAssignment unwrapAssignment(String str, JSONObject jSONObject) throws JSONException {
        String string = null;
        String string2 = (!jSONObject.has("AllocationVersion") || jSONObject.isNull("AllocationVersion")) ? null : jSONObject.get("AllocationVersion").toString();
        if (jSONObject.has("Treatment") && !jSONObject.isNull("Treatment")) {
            string = jSONObject.get("Treatment").toString();
        }
        return new ServiceAssignment(str, string, string2, jSONObject.getBoolean("MayTrigger"));
    }

    static Map<String, ServiceAssignment> parse(String str, String str2) throws IOException {
        if (str == null) {
            return Collections.emptyMap();
        }
        try {
            HashMap map = new HashMap();
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject("InvalidWeblabs");
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                String string = jSONObject2.getString(next);
                map.put(next, new ServiceAssignment(next, null, null, false));
                Log.e("MWAC", "[ERROR] Invalid weblab detected: " + next + " - " + string);
            }
            JSONObject jSONObject3 = jSONObject.getJSONObject("Assignments");
            Iterator<String> itKeys2 = jSONObject3.keys();
            while (itKeys2.hasNext()) {
                String next2 = itKeys2.next();
                map.put(next2, unwrapAssignment(next2, jSONObject3.getJSONObject(next2)));
            }
            return map;
        } catch (JSONException e) {
            throw new IOException("Error parsing JSON.", e);
        }
    }
}
