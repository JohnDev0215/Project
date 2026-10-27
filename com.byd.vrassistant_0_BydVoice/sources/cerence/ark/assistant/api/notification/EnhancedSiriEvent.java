package cerence.ark.assistant.api.notification;

import android.text.TextUtils;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.JsonKey;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class EnhancedSiriEvent extends AbsNotification {
    private static final String KEY_SAMPLE_INDEX = "sample_index";
    private static final String KEY_TIMESTAMP = "timestamp";
    private Event mEvent;
    private long mSampleIndex;
    private long mTimestamp;

    public EnhancedSiriEvent(String str, String str2) {
        super(str, str2);
    }

    @Override // cerence.ark.assistant.api.notification.AbsNotification
    protected void parseParam(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("event");
            if (!TextUtils.isEmpty(strOptString)) {
                this.mEvent = Event.fromString(strOptString);
            } else {
                Logger.error(TAG, "event is empty");
            }
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(JsonKey.NOTIFICATION_EXTRA);
            if (jSONObjectOptJSONObject != null) {
                this.mTimestamp = jSONObjectOptJSONObject.optLong(KEY_TIMESTAMP);
                this.mSampleIndex = jSONObjectOptJSONObject.optLong(KEY_SAMPLE_INDEX);
            }
        } catch (JSONException e) {
            Logger.error(TAG, "EnhancedSiriEvent: parse json exception", e);
        }
    }

    public Event getEvent() {
        return this.mEvent;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public long getSampleIndex() {
        return this.mSampleIndex;
    }

    public enum Event {
        HEY_SIRI_DETECTED,
        SPEECH_DETECTED;

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
