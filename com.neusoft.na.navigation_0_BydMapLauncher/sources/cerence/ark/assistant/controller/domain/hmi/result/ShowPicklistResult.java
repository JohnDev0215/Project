package cerence.ark.assistant.controller.domain.hmi.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.PageInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ShowPicklistResult extends BaseResult {
    private PageInfo pageInfo;

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public PageInfo getPageInfo() {
        return this.pageInfo;
    }

    public static ShowPicklistResult fromJson(String str) throws JSONException {
        ShowPicklistResult showPicklistResult = new ShowPicklistResult();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("page_info");
        if (jSONObjectOptJSONObject != null) {
            showPicklistResult.pageInfo = PageInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return showPicklistResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.pageInfo != null) {
            jSONObject.put("page_info", new JSONObject(this.pageInfo.toJsonString()));
        }
        return jSONObject.toString();
    }
}
