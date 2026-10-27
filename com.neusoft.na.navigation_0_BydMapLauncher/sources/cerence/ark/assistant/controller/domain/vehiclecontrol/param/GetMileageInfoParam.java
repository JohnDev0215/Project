package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetMileageInfoParam implements JsonCompliant {
    private String period;

    public void setPeriod(String str) {
        this.period = str;
    }

    public String getPeriod() {
        return this.period;
    }

    public static GetMileageInfoParam fromJson(String str) throws JSONException {
        GetMileageInfoParam getMileageInfoParam = new GetMileageInfoParam();
        getMileageInfoParam.period = new JSONObject(str).optString("period");
        return getMileageInfoParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("period", this.period);
        return jSONObject.toString();
    }
}
