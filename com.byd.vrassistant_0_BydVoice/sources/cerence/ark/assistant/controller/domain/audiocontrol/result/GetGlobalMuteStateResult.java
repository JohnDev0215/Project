package cerence.ark.assistant.controller.domain.audiocontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetGlobalMuteStateResult extends BaseResult {
    private String globalMuteState;

    public void setGlobalMuteState(String str) {
        this.globalMuteState = str;
    }

    public String getGlobalMuteState() {
        return this.globalMuteState;
    }

    public static GetGlobalMuteStateResult fromJson(String str) throws JSONException {
        GetGlobalMuteStateResult getGlobalMuteStateResult = new GetGlobalMuteStateResult();
        getGlobalMuteStateResult.globalMuteState = new JSONObject(str).optString("global_mute_state");
        return getGlobalMuteStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("global_mute_state", this.globalMuteState);
        return jSONObject.toString();
    }
}
