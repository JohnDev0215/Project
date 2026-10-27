package cerence.ark.assistant.controller.domain.system.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CheckShortcutAvailableParam implements JsonCompliant {
    private String shortcutCmd;

    public void setShortcutCmd(String str) {
        this.shortcutCmd = str;
    }

    public String getShortcutCmd() {
        return this.shortcutCmd;
    }

    public static CheckShortcutAvailableParam fromJson(String str) throws JSONException {
        CheckShortcutAvailableParam checkShortcutAvailableParam = new CheckShortcutAvailableParam();
        checkShortcutAvailableParam.shortcutCmd = new JSONObject(str).optString("shortcut_cmd");
        return checkShortcutAvailableParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("shortcut_cmd", this.shortcutCmd);
        return jSONObject.toString();
    }
}
