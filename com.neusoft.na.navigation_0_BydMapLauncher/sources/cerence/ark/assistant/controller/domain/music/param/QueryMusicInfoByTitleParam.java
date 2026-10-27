package cerence.ark.assistant.controller.domain.music.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.bouncycastle.i18n.MessageBundle;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryMusicInfoByTitleParam implements JsonCompliant {
    private String title;

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }

    public static QueryMusicInfoByTitleParam fromJson(String str) throws JSONException {
        QueryMusicInfoByTitleParam queryMusicInfoByTitleParam = new QueryMusicInfoByTitleParam();
        queryMusicInfoByTitleParam.title = new JSONObject(str).optString(MessageBundle.TITLE_ENTRY);
        return queryMusicInfoByTitleParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(MessageBundle.TITLE_ENTRY, this.title);
        return jSONObject.toString();
    }
}
