package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetDistanceParam implements JsonCompliant {
    private double latitude;
    private double longitude;

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public static GetDistanceParam fromJson(String str) throws JSONException {
        GetDistanceParam getDistanceParam = new GetDistanceParam();
        JSONObject jSONObject = new JSONObject(str);
        getDistanceParam.longitude = jSONObject.optDouble("longitude");
        getDistanceParam.latitude = jSONObject.optDouble("latitude");
        return getDistanceParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("longitude", this.longitude);
        jSONObject.put("latitude", this.latitude);
        return jSONObject.toString();
    }
}
