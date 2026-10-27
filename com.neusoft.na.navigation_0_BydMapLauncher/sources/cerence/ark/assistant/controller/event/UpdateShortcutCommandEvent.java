package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;
import cerence.ark.assistant.controller.struct.ShortcutInfo;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateShortcutCommandEvent extends AppEvent {
    private UpdateShortcutCommandEvent(Builder builder) {
        super("UPDATE_SHORTCUT_COMMAND");
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            if (builder.shortcutCommands != null) {
                for (int i = 0; i < builder.shortcutCommands.size(); i++) {
                    jSONArray.put(new JSONObject(((ShortcutInfo) builder.shortcutCommands.get(i)).toJsonString()));
                }
            }
            jSONObject.put("shortcut_commands", jSONArray);
            setJsonData(jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private List<ShortcutInfo> shortcutCommands;

        public Builder setShortcutCommands(List<ShortcutInfo> list) {
            this.shortcutCommands = list;
            return this;
        }

        public UpdateShortcutCommandEvent build() {
            return new UpdateShortcutCommandEvent(this);
        }
    }
}
