package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PageControlParam implements JsonCompliant {
    private String pageControlType;
    private int pageOrdinal;
    private String scenario;

    public void setScenario(String str) {
        this.scenario = str;
    }

    public String getScenario() {
        return this.scenario;
    }

    public void setPageControlType(String str) {
        this.pageControlType = str;
    }

    public String getPageControlType() {
        return this.pageControlType;
    }

    public void setPageOrdinal(int i) {
        this.pageOrdinal = i;
    }

    public int getPageOrdinal() {
        return this.pageOrdinal;
    }

    public static PageControlParam fromJson(String str) throws JSONException {
        PageControlParam pageControlParam = new PageControlParam();
        JSONObject jSONObject = new JSONObject(str);
        pageControlParam.scenario = jSONObject.optString("scenario");
        pageControlParam.pageControlType = jSONObject.optString("page_control_type");
        pageControlParam.pageOrdinal = jSONObject.optInt("page_ordinal");
        return pageControlParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("scenario", this.scenario);
        jSONObject.put("page_control_type", this.pageControlType);
        jSONObject.put("page_ordinal", this.pageOrdinal);
        return jSONObject.toString();
    }
}
