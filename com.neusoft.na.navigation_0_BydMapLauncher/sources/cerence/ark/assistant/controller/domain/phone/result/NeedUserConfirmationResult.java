package cerence.ark.assistant.controller.domain.phone.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class NeedUserConfirmationResult extends BaseResult {
    private int callingDelayTime;
    private boolean needConfirmation;

    public void setNeedConfirmation(boolean z) {
        this.needConfirmation = z;
    }

    public boolean getNeedConfirmation() {
        return this.needConfirmation;
    }

    public void setCallingDelayTime(int i) {
        this.callingDelayTime = i;
    }

    public int getCallingDelayTime() {
        return this.callingDelayTime;
    }

    public static NeedUserConfirmationResult fromJson(String str) throws JSONException {
        NeedUserConfirmationResult needUserConfirmationResult = new NeedUserConfirmationResult();
        JSONObject jSONObject = new JSONObject(str);
        needUserConfirmationResult.needConfirmation = jSONObject.optBoolean("need_confirmation");
        needUserConfirmationResult.callingDelayTime = jSONObject.optInt("calling_delay_time");
        return needUserConfirmationResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("need_confirmation", this.needConfirmation);
        jSONObject.put("calling_delay_time", this.callingDelayTime);
        return jSONObject.toString();
    }
}
