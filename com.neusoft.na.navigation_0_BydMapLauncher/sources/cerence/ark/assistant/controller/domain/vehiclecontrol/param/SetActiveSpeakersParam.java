package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetActiveSpeakersParam implements JsonCompliant {
    private String speaker;

    public void setSpeaker(String str) {
        this.speaker = str;
    }

    public String getSpeaker() {
        return this.speaker;
    }

    public static SetActiveSpeakersParam fromJson(String str) throws JSONException {
        SetActiveSpeakersParam setActiveSpeakersParam = new SetActiveSpeakersParam();
        setActiveSpeakersParam.speaker = new JSONObject(str).optString("speaker");
        return setActiveSpeakersParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("speaker", this.speaker);
        return jSONObject.toString();
    }
}
