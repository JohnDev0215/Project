package cerence.ark.assistant.controller.domain.system.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckPermissionParam implements JsonCompliant {
    private String permission;

    public void setPermission(String str) {
        this.permission = str;
    }

    public String getPermission() {
        return this.permission;
    }

    public static CheckPermissionParam fromJson(String str) throws JSONException {
        CheckPermissionParam checkPermissionParam = new CheckPermissionParam();
        checkPermissionParam.permission = new JSONObject(str).optString("permission");
        return checkPermissionParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("permission", this.permission);
        return jSONObject.toString();
    }
}
