package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import cerence.ark.assistant.library.JsonKey;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class HmiFontColorParam implements JsonCompliant {
    private String domain;
    private String fontColor;
    private String intent;
    private String type;

    public void setFontColor(String str) {
        this.fontColor = str;
    }

    public String getFontColor() {
        return this.fontColor;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getType() {
        return this.type;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setIntent(String str) {
        this.intent = str;
    }

    public String getIntent() {
        return this.intent;
    }

    public static HmiFontColorParam fromJson(String str) throws JSONException {
        HmiFontColorParam hmiFontColorParam = new HmiFontColorParam();
        JSONObject jSONObject = new JSONObject(str);
        hmiFontColorParam.fontColor = jSONObject.optString("font_color");
        hmiFontColorParam.type = jSONObject.optString("type");
        hmiFontColorParam.domain = jSONObject.optString(JsonKey.DOMAIN_NAME);
        hmiFontColorParam.intent = jSONObject.optString("intent");
        return hmiFontColorParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("font_color", this.fontColor);
        jSONObject.put("type", this.type);
        jSONObject.put(JsonKey.DOMAIN_NAME, this.domain);
        jSONObject.put("intent", this.intent);
        return jSONObject.toString();
    }
}
