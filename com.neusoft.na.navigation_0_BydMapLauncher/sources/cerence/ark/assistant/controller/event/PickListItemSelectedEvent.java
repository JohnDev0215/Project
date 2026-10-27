package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PickListItemSelectedEvent extends AppEvent {
    private PickListItemSelectedEvent(Builder builder) {
        super("PICK_LIST_ITEM_SELECTED");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("item_ordinal", builder.itemOrdinal);
            setJsonData(jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private int itemOrdinal;

        public Builder setItemOrdinal(int i) {
            this.itemOrdinal = i;
            return this;
        }

        public PickListItemSelectedEvent build() {
            return new PickListItemSelectedEvent(this);
        }
    }
}
