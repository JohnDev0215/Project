package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetMapDisplayModeResult extends BaseResult {
    private List<String> modeList;

    public void setModeList(List<String> list) {
        this.modeList = list;
    }

    public List<String> getModeList() {
        return this.modeList;
    }

    public static GetMapDisplayModeResult fromJson(String str) throws JSONException {
        GetMapDisplayModeResult getMapDisplayModeResult = new GetMapDisplayModeResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("mode_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.get(i).toString());
            }
        }
        getMapDisplayModeResult.modeList = arrayList;
        return getMapDisplayModeResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.modeList != null) {
            for (int i = 0; i < this.modeList.size(); i++) {
                jSONArray.put(this.modeList.get(i));
            }
        }
        jSONObject.put("mode_list", jSONArray);
        return jSONObject.toString();
    }
}
