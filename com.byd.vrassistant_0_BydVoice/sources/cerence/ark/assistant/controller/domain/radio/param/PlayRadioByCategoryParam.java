package cerence.ark.assistant.controller.domain.radio.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PlayRadioByCategoryParam implements JsonCompliant {
    private String channelType;

    public void setChannelType(String str) {
        this.channelType = str;
    }

    public String getChannelType() {
        return this.channelType;
    }

    public static PlayRadioByCategoryParam fromJson(String str) throws JSONException {
        PlayRadioByCategoryParam playRadioByCategoryParam = new PlayRadioByCategoryParam();
        playRadioByCategoryParam.channelType = new JSONObject(str).optString("channel_type");
        return playRadioByCategoryParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("channel_type", this.channelType);
        return jSONObject.toString();
    }
}
