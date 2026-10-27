package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.controller.struct.PlaceInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ReplaceDestinationParam implements JsonCompliant {
    private PlaceInfo newDestination;

    public void setNewDestination(PlaceInfo placeInfo) {
        this.newDestination = placeInfo;
    }

    public PlaceInfo getNewDestination() {
        return this.newDestination;
    }

    public static ReplaceDestinationParam fromJson(String str) throws JSONException {
        ReplaceDestinationParam replaceDestinationParam = new ReplaceDestinationParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("new_destination");
        if (jSONObjectOptJSONObject != null) {
            replaceDestinationParam.newDestination = PlaceInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return replaceDestinationParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.newDestination != null) {
            jSONObject.put("new_destination", new JSONObject(this.newDestination.toJsonString()));
        }
        return jSONObject.toString();
    }
}
