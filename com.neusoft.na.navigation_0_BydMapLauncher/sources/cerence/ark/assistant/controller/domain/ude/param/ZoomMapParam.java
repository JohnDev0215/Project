package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ZoomMapParam implements JsonCompliant {
    private String zoomType;

    public void setZoomType(String str) {
        this.zoomType = str;
    }

    public String getZoomType() {
        return this.zoomType;
    }

    public static ZoomMapParam fromJson(String str) throws JSONException {
        ZoomMapParam zoomMapParam = new ZoomMapParam();
        zoomMapParam.zoomType = new JSONObject(str).optString("zoom_type");
        return zoomMapParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("zoom_type", this.zoomType);
        return jSONObject.toString();
    }
}
