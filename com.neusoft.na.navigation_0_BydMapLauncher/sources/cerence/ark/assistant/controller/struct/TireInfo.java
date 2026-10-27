package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class TireInfo implements JsonCompliant {
    private String position;
    private double tirePressure;

    public void setPosition(String str) {
        this.position = str;
    }

    public String getPosition() {
        return this.position;
    }

    public void setTirePressure(double d) {
        this.tirePressure = d;
    }

    public double getTirePressure() {
        return this.tirePressure;
    }

    public static TireInfo fromJson(String str) throws JSONException {
        TireInfo tireInfo = new TireInfo();
        JSONObject jSONObject = new JSONObject(str);
        tireInfo.position = jSONObject.optString("position");
        tireInfo.tirePressure = jSONObject.optDouble("tire_pressure");
        return tireInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("position", this.position);
        jSONObject.put("tire_pressure", this.tirePressure);
        return jSONObject.toString();
    }
}
