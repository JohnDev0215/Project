package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;
import cerence.ark.assistant.controller.struct.PageInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ScrollChangedEvent extends AppEvent {
    private ScrollChangedEvent(Builder builder) {
        super("SCROLL_CHANGED");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("page_info", new JSONObject(builder.pageInfo.toJsonString()));
            setJsonData(jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private PageInfo pageInfo;

        public Builder setPageInfo(PageInfo pageInfo) {
            this.pageInfo = pageInfo;
            return this;
        }

        public ScrollChangedEvent build() {
            return new ScrollChangedEvent(this);
        }
    }
}
