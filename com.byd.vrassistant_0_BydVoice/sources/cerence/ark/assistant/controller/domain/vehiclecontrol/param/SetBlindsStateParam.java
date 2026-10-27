package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetBlindsStateParam implements JsonCompliant {
    private int openPercentage;

    public void setOpenPercentage(int i) {
        this.openPercentage = i;
    }

    public int getOpenPercentage() {
        return this.openPercentage;
    }

    public static SetBlindsStateParam fromJson(String str) throws JSONException {
        SetBlindsStateParam setBlindsStateParam = new SetBlindsStateParam();
        setBlindsStateParam.openPercentage = new JSONObject(str).optInt("open_percentage");
        return setBlindsStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("open_percentage", this.openPercentage);
        return jSONObject.toString();
    }
}
