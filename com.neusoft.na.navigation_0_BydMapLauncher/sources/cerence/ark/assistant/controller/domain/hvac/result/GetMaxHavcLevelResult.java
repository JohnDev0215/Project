package cerence.ark.assistant.controller.domain.hvac.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetMaxHavcLevelResult extends BaseResult {
    private int maxLevel;

    public void setMaxLevel(int i) {
        this.maxLevel = i;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public static GetMaxHavcLevelResult fromJson(String str) throws JSONException {
        GetMaxHavcLevelResult getMaxHavcLevelResult = new GetMaxHavcLevelResult();
        getMaxHavcLevelResult.maxLevel = new JSONObject(str).optInt("max_level");
        return getMaxHavcLevelResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("max_level", this.maxLevel);
        return jSONObject.toString();
    }
}
