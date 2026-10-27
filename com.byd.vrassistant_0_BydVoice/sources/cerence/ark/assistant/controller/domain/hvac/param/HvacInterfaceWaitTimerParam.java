package cerence.ark.assistant.controller.domain.hvac.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class HvacInterfaceWaitTimerParam implements JsonCompliant {
    private int time;

    public void setTime(int i) {
        this.time = i;
    }

    public int getTime() {
        return this.time;
    }

    public static HvacInterfaceWaitTimerParam fromJson(String str) throws JSONException {
        HvacInterfaceWaitTimerParam hvacInterfaceWaitTimerParam = new HvacInterfaceWaitTimerParam();
        hvacInterfaceWaitTimerParam.time = new JSONObject(str).optInt("time");
        return hvacInterfaceWaitTimerParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("time", this.time);
        return jSONObject.toString();
    }
}
