package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetSocStateResult extends BaseResult {
    private int currentSoc;
    private int maxSoc;
    private int minSoc;

    public void setCurrentSoc(int i) {
        this.currentSoc = i;
    }

    public int getCurrentSoc() {
        return this.currentSoc;
    }

    public void setMaxSoc(int i) {
        this.maxSoc = i;
    }

    public int getMaxSoc() {
        return this.maxSoc;
    }

    public void setMinSoc(int i) {
        this.minSoc = i;
    }

    public int getMinSoc() {
        return this.minSoc;
    }

    public static GetSocStateResult fromJson(String str) throws JSONException {
        GetSocStateResult getSocStateResult = new GetSocStateResult();
        JSONObject jSONObject = new JSONObject(str);
        getSocStateResult.currentSoc = jSONObject.optInt("current_soc");
        getSocStateResult.maxSoc = jSONObject.optInt("max_soc");
        getSocStateResult.minSoc = jSONObject.optInt("min_soc");
        return getSocStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("current_soc", this.currentSoc);
        jSONObject.put("max_soc", this.maxSoc);
        jSONObject.put("min_soc", this.minSoc);
        return jSONObject.toString();
    }
}
