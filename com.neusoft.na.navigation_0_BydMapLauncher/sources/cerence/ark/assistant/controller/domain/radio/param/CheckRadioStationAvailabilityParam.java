package cerence.ark.assistant.controller.domain.radio.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckRadioStationAvailabilityParam implements JsonCompliant {
    private String name;

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public static CheckRadioStationAvailabilityParam fromJson(String str) throws JSONException {
        CheckRadioStationAvailabilityParam checkRadioStationAvailabilityParam = new CheckRadioStationAvailabilityParam();
        checkRadioStationAvailabilityParam.name = new JSONObject(str).optString("name");
        return checkRadioStationAvailabilityParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", this.name);
        return jSONObject.toString();
    }
}
