package cerence.ark.assistant.controller.domain.audiobook.param;

import cerence.ark.assistant.controller.struct.AudiobookInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SearchAudiobookParam implements JsonCompliant {
    private AudiobookInfo searchCriteria;

    public void setSearchCriteria(AudiobookInfo audiobookInfo) {
        this.searchCriteria = audiobookInfo;
    }

    public AudiobookInfo getSearchCriteria() {
        return this.searchCriteria;
    }

    public static SearchAudiobookParam fromJson(String str) throws JSONException {
        SearchAudiobookParam searchAudiobookParam = new SearchAudiobookParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("search_criteria");
        if (jSONObjectOptJSONObject != null) {
            searchAudiobookParam.searchCriteria = AudiobookInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return searchAudiobookParam;
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
