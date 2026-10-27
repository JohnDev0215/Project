package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ControlSunroofParam implements JsonCompliant {
    private boolean isTilted;
    private int openPercentage;

    public void setOpenPercentage(int i) {
        this.openPercentage = i;
    }

    public int getOpenPercentage() {
        return this.openPercentage;
    }

    public void setIsTilted(boolean z) {
        this.isTilted = z;
    }

    public boolean getIsTilted() {
        return this.isTilted;
    }

    public static ControlSunroofParam fromJson(String str) throws JSONException {
        ControlSunroofParam controlSunroofParam = new ControlSunroofParam();
        JSONObject jSONObject = new JSONObject(str);
        controlSunroofParam.openPercentage = jSONObject.optInt("open_percentage");
        controlSunroofParam.isTilted = jSONObject.optBoolean("is_tilted");
        return controlSunroofParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("open_percentage", this.openPercentage);
        jSONObject.put("is_tilted", this.isTilted);
        return jSONObject.toString();
    }
}
