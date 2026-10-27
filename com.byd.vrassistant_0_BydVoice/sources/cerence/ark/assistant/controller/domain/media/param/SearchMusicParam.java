package cerence.ark.assistant.controller.domain.media.param;

import cerence.ark.assistant.controller.struct.MusicSearchCriteriaInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SearchMusicParam implements JsonCompliant {
    private MusicSearchCriteriaInfo searchCriteria;

    public void setSearchCriteria(MusicSearchCriteriaInfo musicSearchCriteriaInfo) {
        this.searchCriteria = musicSearchCriteriaInfo;
    }

    public MusicSearchCriteriaInfo getSearchCriteria() {
        return this.searchCriteria;
    }

    public static SearchMusicParam fromJson(String str) throws JSONException {
        SearchMusicParam searchMusicParam = new SearchMusicParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("search_criteria");
        if (jSONObjectOptJSONObject != null) {
            searchMusicParam.searchCriteria = MusicSearchCriteriaInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return searchMusicParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.searchCriteria != null) {
            jSONObject.put("search_criteria", new JSONObject(this.searchCriteria.toJsonString()));
        }
        return jSONObject.toString();
    }
}
