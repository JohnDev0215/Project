package cerence.ark.assistant.controller.domain.media.param;

import cerence.ark.assistant.controller.struct.MusicInfo;
import cerence.ark.assistant.controller.struct.MusicSearchCriteriaInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PlayMusicParam implements JsonCompliant {
    private List<MusicInfo> musicList;
    private MusicSearchCriteriaInfo searchCriteria;

    public void setSearchCriteria(MusicSearchCriteriaInfo musicSearchCriteriaInfo) {
        this.searchCriteria = musicSearchCriteriaInfo;
    }

    public MusicSearchCriteriaInfo getSearchCriteria() {
        return this.searchCriteria;
    }

    public void setMusicList(List<MusicInfo> list) {
        this.musicList = list;
    }

    public List<MusicInfo> getMusicList() {
        return this.musicList;
    }

    public static PlayMusicParam fromJson(String str) throws JSONException {
        PlayMusicParam playMusicParam = new PlayMusicParam();
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("search_criteria");
        if (jSONObjectOptJSONObject != null) {
            playMusicParam.searchCriteria = MusicSearchCriteriaInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("music_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(MusicInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        playMusicParam.musicList = arrayList;
        return playMusicParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.searchCriteria != null) {
            jSONObject.put("search_criteria", new JSONObject(this.searchCriteria.toJsonString()));
        }
        JSONArray jSONArray = new JSONArray();
        if (this.musicList != null) {
            for (int i = 0; i < this.musicList.size(); i++) {
                jSONArray.put(new JSONObject(this.musicList.get(i).toJsonString()));
            }
        }
        jSONObject.put("music_list", jSONArray);
        return jSONObject.toString();
    }
}
