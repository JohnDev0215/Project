package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetFragranceLevelParam implements JsonCompliant {
    private String level;

    public void setLevel(String str) {
        this.level = str;
    }

    public String getLevel() {
        return this.level;
    }

    public static SetFragranceLevelParam fromJson(String str) throws JSONException {
        SetFragranceLevelParam setFragranceLevelParam = new SetFragranceLevelParam();
        setFragranceLevelParam.level = new JSONObject(str).optString("level");
        return setFragranceLevelParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("level", this.level);
        return jSONObject.toString();
    }
}
