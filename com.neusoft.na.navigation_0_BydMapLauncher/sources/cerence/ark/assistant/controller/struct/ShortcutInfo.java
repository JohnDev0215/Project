package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import cerence.ark.assistant.library.JsonKey;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ShortcutInfo implements JsonCompliant {
    private String phrase;
    private int tagThreshold;
    private int wordThreshold;

    public void setPhrase(String str) {
        this.phrase = str;
    }

    public String getPhrase() {
        return this.phrase;
    }

    public void setTagThreshold(int i) {
        this.tagThreshold = i;
    }

    public int getTagThreshold() {
        return this.tagThreshold;
    }

    public void setWordThreshold(int i) {
        this.wordThreshold = i;
    }

    public int getWordThreshold() {
        return this.wordThreshold;
    }

    public static ShortcutInfo fromJson(String str) throws JSONException {
        ShortcutInfo shortcutInfo = new ShortcutInfo();
        JSONObject jSONObject = new JSONObject(str);
        shortcutInfo.phrase = jSONObject.optString("phrase");
        shortcutInfo.tagThreshold = jSONObject.optInt(JsonKey.WUW_TAG_THRESHOLD);
        shortcutInfo.wordThreshold = jSONObject.optInt(JsonKey.WUW_WORD_THRESHOLD);
        return shortcutInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("phrase", this.phrase);
        jSONObject.put(JsonKey.WUW_TAG_THRESHOLD, this.tagThreshold);
        jSONObject.put(JsonKey.WUW_WORD_THRESHOLD, this.wordThreshold);
        return jSONObject.toString();
    }
}
