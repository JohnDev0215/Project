package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetFragranceLevelResult extends BaseResult {
    private String fragranceLevel;

    public void setFragranceLevel(String str) {
        this.fragranceLevel = str;
    }

    public String getFragranceLevel() {
        return this.fragranceLevel;
    }

    public static GetFragranceLevelResult fromJson(String str) throws JSONException {
        GetFragranceLevelResult getFragranceLevelResult = new GetFragranceLevelResult();
        getFragranceLevelResult.fragranceLevel = new JSONObject(str).optString("fragrance_level");
        return getFragranceLevelResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("fragrance_level", this.fragranceLevel);
        return jSONObject.toString();
    }
}
