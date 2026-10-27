package cerence.ark.assistant.controller.domain.weather.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryCurrentAirQualityResult extends BaseResult {
    private int index;
    private int pm10Index;
    private int pm25Index;

    public void setIndex(int i) {
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }

    public void setPm25Index(int i) {
        this.pm25Index = i;
    }

    public int getPm25Index() {
        return this.pm25Index;
    }

    public void setPm10Index(int i) {
        this.pm10Index = i;
    }

    public int getPm10Index() {
        return this.pm10Index;
    }

    public static QueryCurrentAirQualityResult fromJson(String str) throws JSONException {
        QueryCurrentAirQualityResult queryCurrentAirQualityResult = new QueryCurrentAirQualityResult();
        JSONObject jSONObject = new JSONObject(str);
        queryCurrentAirQualityResult.index = jSONObject.optInt("index");
        queryCurrentAirQualityResult.pm25Index = jSONObject.optInt("pm25_index");
        queryCurrentAirQualityResult.pm10Index = jSONObject.optInt("pm10_index");
        return queryCurrentAirQualityResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("index", this.index);
        jSONObject.put("pm25_index", this.pm25Index);
        jSONObject.put("pm10_index", this.pm10Index);
        return jSONObject.toString();
    }
}
