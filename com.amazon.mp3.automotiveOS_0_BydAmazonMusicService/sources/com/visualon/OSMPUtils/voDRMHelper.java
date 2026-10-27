package com.visualon.OSMPUtils;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
public class voDRMHelper {
    private static final String TAG = "@@@voDRMHelper";

    public static JSONObject getDRMData(String str, String str2, LinkedList<HashMap> linkedList) {
        JSONArray jSONArray = null;
        if (TextUtils.isEmpty(str) || !str.equals("VO_PARTNER_INSIDESECURE")) {
            return null;
        }
        for (HashMap map : linkedList) {
            if (jSONArray == null) {
                jSONArray = new JSONArray();
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(str2, (String) map.get(str2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (!entry.getKey().equals(str2)) {
                    try {
                        jSONObject.put((String) entry.getKey(), (String) entry.getValue());
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                it.remove();
            }
            jSONArray.put(jSONObject);
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("Preference", jSONArray);
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "[" + str + "][JSON]" + jSONObject2.toString(), new Object[0]);
        }
        return jSONObject2;
    }
}
