package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.controller.struct.PlaceInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AddToFavoritesParam implements JsonCompliant {
    private PlaceInfo place;

    public void setPlace(PlaceInfo placeInfo) {
        this.place = placeInfo;
    }

    public PlaceInfo getPlace() {
        return this.place;
    }

    public static AddToFavoritesParam fromJson(String str) throws JSONException {
        AddToFavoritesParam addToFavoritesParam = new AddToFavoritesParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("place");
        if (jSONObjectOptJSONObject != null) {
            addToFavoritesParam.place = PlaceInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return addToFavoritesParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.place != null) {
            jSONObject.put("place", new JSONObject(this.place.toJsonString()));
        }
        return jSONObject.toString();
    }
}
