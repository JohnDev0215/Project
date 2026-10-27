package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.LightBrightnessInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetInteriorLightBrightnessResult extends BaseResult {
    private List<LightBrightnessInfo> brightnessList;
    private int maxBrightness;
    private int minBrightness;

    public void setBrightnessList(List<LightBrightnessInfo> list) {
        this.brightnessList = list;
    }

    public List<LightBrightnessInfo> getBrightnessList() {
        return this.brightnessList;
    }

    public void setMaxBrightness(int i) {
        this.maxBrightness = i;
    }

    public int getMaxBrightness() {
        return this.maxBrightness;
    }

    public void setMinBrightness(int i) {
        this.minBrightness = i;
    }

    public int getMinBrightness() {
        return this.minBrightness;
    }

    public static GetInteriorLightBrightnessResult fromJson(String str) throws JSONException {
        GetInteriorLightBrightnessResult getInteriorLightBrightnessResult = new GetInteriorLightBrightnessResult();
        JSONObject jSONObject = new JSONObject(str);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("brightness_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(LightBrightnessInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        getInteriorLightBrightnessResult.brightnessList = arrayList;
        getInteriorLightBrightnessResult.maxBrightness = jSONObject.optInt("max_brightness");
        getInteriorLightBrightnessResult.minBrightness = jSONObject.optInt("min_brightness");
        return getInteriorLightBrightnessResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.brightnessList != null) {
            for (int i = 0; i < this.brightnessList.size(); i++) {
                jSONArray.put(new JSONObject(this.brightnessList.get(i).toJsonString()));
            }
        }
        jSONObject.put("brightness_list", jSONArray);
        jSONObject.put("max_brightness", this.maxBrightness);
        jSONObject.put("min_brightness", this.minBrightness);
        return jSONObject.toString();
    }
}
