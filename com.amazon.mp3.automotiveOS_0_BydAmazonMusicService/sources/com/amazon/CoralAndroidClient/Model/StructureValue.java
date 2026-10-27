package com.amazon.CoralAndroidClient.Model;

import com.amazon.CoralAndroidClient.Exception.NativeException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class StructureValue implements Value {
    private Map<String, Value> mMap = new HashMap();

    public void put(String str, Value value) {
        this.mMap.put(str, value);
    }

    public JSONObject toJsonObject() throws NativeException {
        try {
            return (JSONObject) toJsonInternal();
        } catch (JSONException e) {
            throw new NativeException("invalid JSON object", e);
        }
    }

    @Override // com.amazon.CoralAndroidClient.Model.Value
    public Object toJsonInternal() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, Value> entry : this.mMap.entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue().toJsonInternal());
        }
        return jSONObject;
    }
}
