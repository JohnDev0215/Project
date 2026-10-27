package cerence.ark.assistant.controller.domain.radio.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetDabSupplyVersionResult extends BaseResult {
    private String dabSupplyVersion;

    public void setDabSupplyVersion(String str) {
        this.dabSupplyVersion = str;
    }

    public String getDabSupplyVersion() {
        return this.dabSupplyVersion;
    }

    public static GetDabSupplyVersionResult fromJson(String str) throws JSONException {
        GetDabSupplyVersionResult getDabSupplyVersionResult = new GetDabSupplyVersionResult();
        getDabSupplyVersionResult.dabSupplyVersion = new JSONObject(str).optString("dab_supply_version");
        return getDabSupplyVersionResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("dab_supply_version", this.dabSupplyVersion);
        return jSONObject.toString();
    }
}
