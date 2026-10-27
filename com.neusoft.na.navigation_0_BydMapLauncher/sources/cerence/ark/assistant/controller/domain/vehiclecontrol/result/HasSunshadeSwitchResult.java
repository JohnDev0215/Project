package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class HasSunshadeSwitchResult extends BaseResult {
    private boolean value;

    public void setValue(boolean z) {
        this.value = z;
    }

    public boolean getValue() {
        return this.value;
    }

    public static HasSunshadeSwitchResult fromJson(String str) throws JSONException {
        HasSunshadeSwitchResult hasSunshadeSwitchResult = new HasSunshadeSwitchResult();
        hasSunshadeSwitchResult.value = new JSONObject(str).optBoolean(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE);
        return hasSunshadeSwitchResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, this.value);
        return jSONObject.toString();
    }
}
