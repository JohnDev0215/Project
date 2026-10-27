package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PageInfo implements JsonCompliant {
    private int numOfItems;
    private int startItemIndex;
    private int startItemOrdinal;
    private int totalPageNumber;

    public void setStartItemIndex(int i) {
        this.startItemIndex = i;
    }

    public int getStartItemIndex() {
        return this.startItemIndex;
    }

    public void setStartItemOrdinal(int i) {
        this.startItemOrdinal = i;
    }

    public int getStartItemOrdinal() {
        return this.startItemOrdinal;
    }

    public void setNumOfItems(int i) {
        this.numOfItems = i;
    }

    public int getNumOfItems() {
        return this.numOfItems;
    }

    public void setTotalPageNumber(int i) {
        this.totalPageNumber = i;
    }

    public int getTotalPageNumber() {
        return this.totalPageNumber;
    }

    public static PageInfo fromJson(String str) throws JSONException {
        PageInfo pageInfo = new PageInfo();
        JSONObject jSONObject = new JSONObject(str);
        pageInfo.startItemIndex = jSONObject.optInt("start_item_index");
        pageInfo.startItemOrdinal = jSONObject.optInt("start_item_ordinal");
        pageInfo.numOfItems = jSONObject.optInt("num_of_items");
        pageInfo.totalPageNumber = jSONObject.optInt("total_page_number");
        return pageInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("start_item_index", this.startItemIndex);
        jSONObject.put("start_item_ordinal", this.startItemOrdinal);
        jSONObject.put("num_of_items", this.numOfItems);
        jSONObject.put("total_page_number", this.totalPageNumber);
        return jSONObject.toString();
    }
}
