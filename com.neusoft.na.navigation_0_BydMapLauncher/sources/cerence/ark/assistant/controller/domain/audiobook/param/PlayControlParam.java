package cerence.ark.assistant.controller.domain.audiobook.param;

import cerence.ark.assistant.core.JsonCompliant;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PlayControlParam implements JsonCompliant {
    private String controlType;
    private int value;

    public void setControlType(String str) {
        this.controlType = str;
    }

    public String getControlType() {
        return this.controlType;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static PlayControlParam fromJson(String str) throws JSONException {
        PlayControlParam playControlParam = new PlayControlParam();
        JSONObject jSONObject = new JSONObject(str);
        playControlParam.controlType = jSONObject.optString("control_type");
        playControlParam.value = jSONObject.optInt(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE);
        return playControlParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("control_type", this.controlType);
        jSONObject.put(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, this.value);
        return jSONObject.toString();
    }
}
