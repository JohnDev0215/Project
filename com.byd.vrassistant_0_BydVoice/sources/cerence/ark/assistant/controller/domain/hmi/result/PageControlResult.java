package cerence.ark.assistant.controller.domain.hmi.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PageControlResult extends BaseResult {
    private int numOfItems;
    private int startItemIndex;
    private int startItemOrdinal;

    public void setStartItemIndex(int i) {
        this.startItemIndex = i;
    }

    public int getStartItemIndex() {
        return this.startItemIndex;
    }

    public void setNumOfItems(int i) {
        this.numOfItems = i;
    }

    public int getNumOfItems() {
        return this.numOfItems;
    }

    public void setStartItemOrdinal(int i) {
        this.startItemOrdinal = i;
    }

    public int getStartItemOrdinal() {
        return this.startItemOrdinal;
    }

    public static PageControlResult fromJson(String str) throws JSONException {
        PageControlResult pageControlResult = new PageControlResult();
        JSONObject jSONObject = new JSONObject(str);
        pageControlResult.startItemIndex = jSONObject.optInt("start_item_index");
        pageControlResult.numOfItems = jSONObject.optInt("num_of_items");
        pageControlResult.startItemOrdinal = jSONObject.optInt("start_item_ordinal");
        return pageControlResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("start_item_index", this.startItemIndex);
        jSONObject.put("num_of_items", this.numOfItems);
        jSONObject.put("start_item_ordinal", this.startItemOrdinal);
        return jSONObject.toString();
    }
}
