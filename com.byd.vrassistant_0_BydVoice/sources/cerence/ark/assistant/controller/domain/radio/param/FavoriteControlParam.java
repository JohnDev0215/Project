package cerence.ark.assistant.controller.domain.radio.param;

import cerence.ark.assistant.controller.struct.RadioInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class FavoriteControlParam implements JsonCompliant {
    private String controlType;
    private RadioInfo radioInfo;

    public void setControlType(String str) {
        this.controlType = str;
    }

    public String getControlType() {
        return this.controlType;
    }

    public void setRadioInfo(RadioInfo radioInfo) {
        this.radioInfo = radioInfo;
    }

    public RadioInfo getRadioInfo() {
        return this.radioInfo;
    }

    public static FavoriteControlParam fromJson(String str) throws JSONException {
        FavoriteControlParam favoriteControlParam = new FavoriteControlParam();
        JSONObject jSONObject = new JSONObject(str);
        favoriteControlParam.controlType = jSONObject.optString("control_type");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("radio_info");
        if (jSONObjectOptJSONObject != null) {
            favoriteControlParam.radioInfo = RadioInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return favoriteControlParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("control_type", this.controlType);
        if (this.radioInfo != null) {
            jSONObject.put("radio_info", new JSONObject(this.radioInfo.toJsonString()));
        }
        return jSONObject.toString();
    }
}
