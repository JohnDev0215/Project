package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class TrunkStateInfo implements JsonCompliant {
    private int openPercentage;

    public void setOpenPercentage(int i) {
        this.openPercentage = i;
    }

    public int getOpenPercentage() {
        return this.openPercentage;
    }

    public static TrunkStateInfo fromJson(String str) throws JSONException {
        TrunkStateInfo trunkStateInfo = new TrunkStateInfo();
        trunkStateInfo.openPercentage = new JSONObject(str).optInt("open_percentage");
        return trunkStateInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("open_percentage", this.openPercentage);
        return jSONObject.toString();
    }
}
