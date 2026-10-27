package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AudiobookInfo implements JsonCompliant {
    private String artist;
    private String category;
    private String programName;

    public void setProgramName(String str) {
        this.programName = str;
    }

    public String getProgramName() {
        return this.programName;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public String getCategory() {
        return this.category;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public String getArtist() {
        return this.artist;
    }

    public static AudiobookInfo fromJson(String str) throws JSONException {
        AudiobookInfo audiobookInfo = new AudiobookInfo();
        JSONObject jSONObject = new JSONObject(str);
        audiobookInfo.programName = jSONObject.optString("programName");
        audiobookInfo.category = jSONObject.optString("category");
        audiobookInfo.artist = jSONObject.optString("artist");
        return audiobookInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("programName", this.programName);
        jSONObject.put("category", this.category);
        jSONObject.put("artist", this.artist);
        return jSONObject.toString();
    }
}
