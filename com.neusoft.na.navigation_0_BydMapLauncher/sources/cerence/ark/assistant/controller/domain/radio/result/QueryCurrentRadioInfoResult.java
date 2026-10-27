package cerence.ark.assistant.controller.domain.radio.result;

import cerence.ark.assistant.api.BaseResult;
import com.neusoft.na.common.ServiceBase;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryCurrentRadioInfoResult extends BaseResult {
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

    public static QueryCurrentRadioInfoResult fromJson(String str) throws JSONException {
        QueryCurrentRadioInfoResult queryCurrentRadioInfoResult = new QueryCurrentRadioInfoResult();
        JSONObject jSONObject = new JSONObject(str);
        queryCurrentRadioInfoResult.mode = jSONObject.optString(ServiceBase.PAYLOAD_MODE);
        queryCurrentRadioInfoResult.name = jSONObject.optString("name");
        queryCurrentRadioInfoResult.freq = jSONObject.optDouble("freq");
        return queryCurrentRadioInfoResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ServiceBase.PAYLOAD_MODE, this.mode);
        jSONObject.put("name", this.name);
        jSONObject.put("freq", this.freq);
        return jSONObject.toString();
    }
}
