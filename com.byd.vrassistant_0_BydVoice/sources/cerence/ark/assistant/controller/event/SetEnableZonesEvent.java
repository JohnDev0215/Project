package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetEnableZonesEvent extends AppEvent {
    private SetEnableZonesEvent(Builder builder) {
        super("SET_ENABLE_ZONES");
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            if (builder.enabledZones != null) {
                for (int i = 0; i < builder.enabledZones.size(); i++) {
                    jSONArray.put(builder.enabledZones.get(i));
                }
            }
            jSONObject.put("enabled_zones", jSONArray);
            setJsonData(jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private List<String> enabledZones;

        public Builder setEnabledZones(List<String> list) {
            this.enabledZones = list;
            return this;
        }

        public SetEnableZonesEvent build() {
            return new SetEnableZonesEvent(this);
        }
    }
}
