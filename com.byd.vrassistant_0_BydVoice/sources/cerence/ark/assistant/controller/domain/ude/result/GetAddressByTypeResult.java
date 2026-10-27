package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetAddressByTypeResult extends BaseResult {
    private PlaceInfo placeInfo;

    public void setPlaceInfo(PlaceInfo placeInfo) {
        this.placeInfo = placeInfo;
    }

    public PlaceInfo getPlaceInfo() {
        return this.placeInfo;
    }

    public static GetAddressByTypeResult fromJson(String str) throws JSONException {
        GetAddressByTypeResult getAddressByTypeResult = new GetAddressByTypeResult();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("place_info");
        if (jSONObjectOptJSONObject != null) {
            getAddressByTypeResult.placeInfo = PlaceInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return getAddressByTypeResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.placeInfo != null) {
            jSONObject.put("place_info", new JSONObject(this.placeInfo.toJsonString()));
        }
        return jSONObject.toString();
    }
}
