package cerence.ark.assistant.controller.domain.media.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.MusicInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SearchMusicResult extends BaseResult {
    private String mediaProvider;
    private List<MusicInfo> musicList;
    private String musicType;
    private boolean needDisambig;

    public void setNeedDisambig(boolean z) {
        this.needDisambig = z;
    }

    public boolean getNeedDisambig() {
        return this.needDisambig;
    }

    public void setMusicList(List<MusicInfo> list) {
        this.musicList = list;
    }

    public List<MusicInfo> getMusicList() {
        return this.musicList;
    }

    public void setMusicType(String str) {
        this.musicType = str;
    }

    public String getMusicType() {
        return this.musicType;
    }

    public void setMediaProvider(String str) {
        this.mediaProvider = str;
    }

    public String getMediaProvider() {
        return this.mediaProvider;
    }

    public static SearchMusicResult fromJson(String str) throws JSONException {
        SearchMusicResult searchMusicResult = new SearchMusicResult();
        JSONObject jSONObject = new JSONObject(str);
        searchMusicResult.needDisambig = jSONObject.optBoolean("need_disambig");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("music_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(MusicInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        searchMusicResult.musicList = arrayList;
        searchMusicResult.musicType = jSONObject.optString("music_type");
        searchMusicResult.mediaProvider = jSONObject.optString("media_provider");
        return searchMusicResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("need_disambig", this.needDisambig);
        JSONArray jSONArray = new JSONArray();
        if (this.musicList != null) {
            for (int i = 0; i < this.musicList.size(); i++) {
                jSONArray.put(new JSONObject(this.musicList.get(i).toJsonString()));
            }
        }
        jSONObject.put("music_list", jSONArray);
        jSONObject.put("music_type", this.musicType);
        jSONObject.put("media_provider", this.mediaProvider);
        return jSONObject.toString();
    }
}
