package cerence.ark.assistant.controller.domain.radio.param;

import cerence.ark.assistant.controller.struct.RadioInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PlayRadioParam implements JsonCompliant {
    private RadioInfo radioInfo;

    public void setRadioInfo(RadioInfo radioInfo) {
        this.radioInfo = radioInfo;
    }

    public RadioInfo getRadioInfo() {
        return this.radioInfo;
    }

    public static PlayRadioParam fromJson(String str) throws JSONException {
        PlayRadioParam playRadioParam = new PlayRadioParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("radio_info");
        if (jSONObjectOptJSONObject != null) {
            playRadioParam.radioInfo = RadioInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return playRadioParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.radioInfo != null) {
            jSONObject.put("radio_info", new JSONObject(this.radioInfo.toJsonString()));
        }
        return jSONObject.toString();
    }
}
