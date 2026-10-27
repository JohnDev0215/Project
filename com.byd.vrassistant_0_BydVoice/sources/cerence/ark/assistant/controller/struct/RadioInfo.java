package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class RadioInfo implements JsonCompliant {
    private String band;
    private double freq;
    private String name;

    public void setBand(String str) {
        this.band = str;
    }

    public String getBand() {
        return this.band;
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

    public static RadioInfo fromJson(String str) throws JSONException {
        RadioInfo radioInfo = new RadioInfo();
        JSONObject jSONObject = new JSONObject(str);
        radioInfo.band = jSONObject.optString("band");
        radioInfo.name = jSONObject.optString("name");
        radioInfo.freq = jSONObject.optDouble("freq");
        return radioInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("band", this.band);
        jSONObject.put("name", this.name);
        jSONObject.put("freq", this.freq);
        return jSONObject.toString();
    }
}
