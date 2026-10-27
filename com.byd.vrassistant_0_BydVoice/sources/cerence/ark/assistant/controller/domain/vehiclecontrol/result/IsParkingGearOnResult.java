package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class IsParkingGearOnResult extends BaseResult {
    private String isParkinggearOn;

    public void setIsParkinggearOn(String str) {
        this.isParkinggearOn = str;
    }

    public String getIsParkinggearOn() {
        return this.isParkinggearOn;
    }

    public static IsParkingGearOnResult fromJson(String str) throws JSONException {
        IsParkingGearOnResult isParkingGearOnResult = new IsParkingGearOnResult();
        isParkingGearOnResult.isParkinggearOn = new JSONObject(str).optString("is_parkinggear_on");
        return isParkingGearOnResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_parkinggear_on", this.isParkinggearOn);
        return jSONObject.toString();
    }
}
