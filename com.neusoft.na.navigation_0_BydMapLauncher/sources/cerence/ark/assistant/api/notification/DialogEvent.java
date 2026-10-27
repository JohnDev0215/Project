package cerence.ark.assistant.api.notification;

import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DialogEvent extends AbsNotification {
    private Event mEvent;
    private String mExtra;

    public DialogEvent(String str) {
        super(str);
    }

    @Override // cerence.ark.assistant.api.notification.AbsNotification
    protected void parseParam(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        String strOptString = jSONObject.optString("event");
        if (!TextUtils.isEmpty(strOptString)) {
            this.mEvent = Event.fromString(strOptString);
        } else {
            Logger.error(TAG, "event is empty");
        }
        this.mExtra = jSONObject.optString(JsonKey.NOTIFICATION_EXTRA);
        if (this.mEvent != Event.PROMPT_LIPS_INFO) {
            Logger.info(TAG, Utils.format("DialogEvent: %s", str));
        }
    }

    public Event getEvent() {
        return this.mEvent;
    }

    public String getExtra() {
        return this.mExtra;
    }

    public enum Event {
        CONVERSATION_BEGIN,
        CONVERSATION_END,
        SESSION_BEGIN,
        SESSION_END,
        PROMPT_BEGIN,
        PROMPT_LIPS_INFO,
        PROMPT_END,
        SNOOZING_BEGIN,
        SNOOZING_END,
        RECOGNITION_RESTARTED,
        ACTIVE_ZONE_CHANGED;

        private static final Map<String, Event> EVENT_MAP = new HashMap(16);

        static {
            for (Event event : values()) {
                EVENT_MAP.put(event.toString(), event);
            }
        }

        public static Event fromString(String str) {
            return EVENT_MAP.get(str.toUpperCase(Locale.US));
        }
    }
}
