package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.controller.struct.PlaceInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PlaceDetailParam implements JsonCompliant {
    private PlaceInfo placeInfo;

    public void setPlaceInfo(PlaceInfo placeInfo) {
        this.placeInfo = placeInfo;
    }

    public PlaceInfo getPlaceInfo() {
        return this.placeInfo;
    }

    public static PlaceDetailParam fromJson(String str) throws JSONException {
        PlaceDetailParam placeDetailParam = new PlaceDetailParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("place_info");
        if (jSONObjectOptJSONObject != null) {
            placeDetailParam.placeInfo = PlaceInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return placeDetailParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.placeInfo != null) {
            jSONObject.put("place_info", new JSONObject(this.placeInfo.toJsonString()));
        }
        return jSONObject.toString();
    }
}
