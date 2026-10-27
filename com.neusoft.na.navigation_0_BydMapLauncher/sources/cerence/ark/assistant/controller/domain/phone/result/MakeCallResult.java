package cerence.ark.assistant.controller.domain.phone.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class MakeCallResult extends BaseResult {
    private boolean needUserConfirmation;

    public void setNeedUserConfirmation(boolean z) {
        this.needUserConfirmation = z;
    }

    public boolean getNeedUserConfirmation() {
        return this.needUserConfirmation;
    }

    public static MakeCallResult fromJson(String str) throws JSONException {
        MakeCallResult makeCallResult = new MakeCallResult();
        makeCallResult.needUserConfirmation = new JSONObject(str).optBoolean("need_user_confirmation");
        return makeCallResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("need_user_confirmation", this.needUserConfirmation);
        return jSONObject.toString();
    }
}
