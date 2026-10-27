package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ZoomMapParam implements JsonCompliant {
    private String zoomOption;

    public void setZoomOption(String str) {
        this.zoomOption = str;
    }

    public String getZoomOption() {
        return this.zoomOption;
    }

    public static ZoomMapParam fromJson(String str) throws JSONException {
        ZoomMapParam zoomMapParam = new ZoomMapParam();
        zoomMapParam.zoomOption = new JSONObject(str).optString("zoom_option");
        return zoomMapParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("zoom_option", this.zoomOption);
        return jSONObject.toString();
    }
}
