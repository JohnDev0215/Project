package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.bouncycastle.i18n.TextBundle;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UvInfo implements JsonCompliant {
    private int index;
    private String text;

    public void setIndex(int i) {
        this.index = i;
    }

    public int getIndex() {
        return this.index;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }

    public static UvInfo fromJson(String str) throws JSONException {
        UvInfo uvInfo = new UvInfo();
        JSONObject jSONObject = new JSONObject(str);
        uvInfo.index = jSONObject.optInt("index");
        uvInfo.text = jSONObject.optString(TextBundle.TEXT_ENTRY);
        return uvInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("index", this.index);
        jSONObject.put(TextBundle.TEXT_ENTRY, this.text);
        return jSONObject.toString();
    }
}
