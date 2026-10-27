package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class HighlightItemParam implements JsonCompliant {
    private int itemOrdinal;

    public void setItemOrdinal(int i) {
        this.itemOrdinal = i;
    }

    public int getItemOrdinal() {
        return this.itemOrdinal;
    }

    public static HighlightItemParam fromJson(String str) throws JSONException {
        HighlightItemParam highlightItemParam = new HighlightItemParam();
        highlightItemParam.itemOrdinal = new JSONObject(str).optInt("item_ordinal");
        return highlightItemParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("item_ordinal", this.itemOrdinal);
        return jSONObject.toString();
    }
}
