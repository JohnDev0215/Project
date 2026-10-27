package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.controller.struct.PlaceInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.i18n.MessageBundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class NaviPoiListParam implements JsonCompliant {
    private List<PlaceInfo> poiList;
    private String title;

    public void setPoiList(List<PlaceInfo> list) {
        this.poiList = list;
    }

    public List<PlaceInfo> getPoiList() {
        return this.poiList;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }

    public static NaviPoiListParam fromJson(String str) throws JSONException {
        NaviPoiListParam naviPoiListParam = new NaviPoiListParam();
        JSONObject jSONObject = new JSONObject(str);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("poi_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PlaceInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        naviPoiListParam.poiList = arrayList;
        naviPoiListParam.title = jSONObject.optString(MessageBundle.TITLE_ENTRY);
        return naviPoiListParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.poiList != null) {
            for (int i = 0; i < this.poiList.size(); i++) {
                jSONArray.put(new JSONObject(this.poiList.get(i).toJsonString()));
            }
        }
        jSONObject.put("poi_list", jSONArray);
        jSONObject.put(MessageBundle.TITLE_ENTRY, this.title);
        return jSONObject.toString();
    }
}
