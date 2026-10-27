package cerence.ark.assistant.controller.domain.news.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class NewsPlayControlParam implements JsonCompliant {
    private String controlType;

    public void setControlType(String str) {
        this.controlType = str;
    }

    public String getControlType() {
        return this.controlType;
    }

    public static NewsPlayControlParam fromJson(String str) throws JSONException {
        NewsPlayControlParam newsPlayControlParam = new NewsPlayControlParam();
        newsPlayControlParam.controlType = new JSONObject(str).optString("control_type");
        return newsPlayControlParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("control_type", this.controlType);
        return jSONObject.toString();
    }
}
