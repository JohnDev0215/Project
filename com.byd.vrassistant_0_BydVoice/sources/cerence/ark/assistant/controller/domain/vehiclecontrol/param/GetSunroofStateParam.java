package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetSunroofStateParam implements JsonCompliant {
    private String command;

    public void setCommand(String str) {
        this.command = str;
    }

    public String getCommand() {
        return this.command;
    }

    public static GetSunroofStateParam fromJson(String str) throws JSONException {
        GetSunroofStateParam getSunroofStateParam = new GetSunroofStateParam();
        getSunroofStateParam.command = new JSONObject(str).optString("command");
        return getSunroofStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("command", this.command);
        return jSONObject.toString();
    }
}
