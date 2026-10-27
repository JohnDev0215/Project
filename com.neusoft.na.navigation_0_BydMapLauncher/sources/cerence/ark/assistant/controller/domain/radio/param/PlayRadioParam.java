package cerence.ark.assistant.controller.domain.radio.param;

import cerence.ark.assistant.core.JsonCompliant;
import com.neusoft.na.common.ServiceBase;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PlayRadioParam implements JsonCompliant {
    private double freq;
    private String mode;
    private String name;

    public void setMode(String str) {
        this.mode = str;
    }

    public String getMode() {
        return this.mode;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setFreq(double d) {
        this.freq = d;
    }

    public double getFreq() {
        return this.freq;
    }

    public static PlayRadioParam fromJson(String str) throws JSONException {
        PlayRadioParam playRadioParam = new PlayRadioParam();
        JSONObject jSONObject = new JSONObject(str);
        playRadioParam.mode = jSONObject.optString(ServiceBase.PAYLOAD_MODE);
        playRadioParam.name = jSONObject.optString("name");
        playRadioParam.freq = jSONObject.optDouble("freq");
        return playRadioParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ServiceBase.PAYLOAD_MODE, this.mode);
        jSONObject.put("name", this.name);
        jSONObject.put("freq", this.freq);
        return jSONObject.toString();
    }
}
