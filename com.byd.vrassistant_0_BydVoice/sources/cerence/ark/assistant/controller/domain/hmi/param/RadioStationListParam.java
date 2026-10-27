package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class RadioStationListParam implements JsonCompliant {
    private String band;

    public void setBand(String str) {
        this.band = str;
    }

    public String getBand() {
        return this.band;
    }

    public static RadioStationListParam fromJson(String str) throws JSONException {
        RadioStationListParam radioStationListParam = new RadioStationListParam();
        radioStationListParam.band = new JSONObject(str).optString("band");
        return radioStationListParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("band", this.band);
        return jSONObject.toString();
    }
}
