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
public class ThirdPartyVpaEvent extends AbsNotification {
    private static final String KEY_PHRASE = "phrase";
    private static final String KEY_SAMPLE_INDEX = "sample_index";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_WUW_BEGIN_SAMPLE_INDEX = "vpa_wuw_begin_sample_index";
    private static final String KEY_WUW_BEGIN_TIME = "vpa_wuw_begin_time";
    private static final String KEY_WUW_END_SAMPLE_INDEX = "vpa_wuw_end_sample_index";
    private static final String KEY_WUW_END_TIME = "vpa_wuw_end_time";
    private static final String KEY_WUW_TAG_BEGIN_SAMPLE_INDEX = "vpa_tag_begin_sample_index";
    private static final String KEY_WUW_TAG_BEGIN_TIME = "vpa_tag_begin_time";
    private static final String KEY_ZONE = "zone";
    private Event mEvent;
    protected String mPhrase;
    private long mSpeechSampleIndex;
    private long mSpeechTimestamp;
    private long mWuwBeginSampleIndex;
    private long mWuwBeginTimestamp;
    private long mWuwEndSampleIndex;
    private long mWuwEndTimestamp;
    private long mWuwTagBeginSampleIndex;
    private long mWuwTagBeginTimestamp;
    protected String mZone;

    public ThirdPartyVpaEvent(String str) {
        super(str);
        Logger.info(TAG, Utils.format("ThirdPartyVpaEvent: %s", str));
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
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(JsonKey.NOTIFICATION_EXTRA);
        if (jSONObjectOptJSONObject != null) {
            this.mPhrase = jSONObjectOptJSONObject.optString(KEY_PHRASE);
            this.mZone = jSONObjectOptJSONObject.optString("zone");
            this.mSpeechTimestamp = jSONObjectOptJSONObject.optLong(KEY_TIMESTAMP);
            this.mSpeechSampleIndex = jSONObjectOptJSONObject.optLong(KEY_SAMPLE_INDEX);
            this.mWuwTagBeginSampleIndex = jSONObjectOptJSONObject.optLong(KEY_WUW_TAG_BEGIN_SAMPLE_INDEX);
            this.mWuwTagBeginTimestamp = jSONObjectOptJSONObject.optLong(KEY_WUW_TAG_BEGIN_TIME);
            this.mWuwBeginSampleIndex = jSONObjectOptJSONObject.optLong(KEY_WUW_BEGIN_SAMPLE_INDEX);
            this.mWuwBeginTimestamp = jSONObjectOptJSONObject.optLong(KEY_WUW_BEGIN_TIME);
            this.mWuwEndSampleIndex = jSONObjectOptJSONObject.optLong(KEY_WUW_END_SAMPLE_INDEX);
            this.mWuwEndTimestamp = jSONObjectOptJSONObject.optLong(KEY_WUW_END_TIME);
        }
    }

    public Event getEvent() {
        return this.mEvent;
    }

    public String getPhrase() {
        return this.mPhrase;
    }

    public String getZone() {
        String str = this.mZone;
        return str == null ? "" : str;
    }

    public long getSpeechSampleIndex() {
        return this.mSpeechSampleIndex;
    }

    public long getSpeechTimestamp() {
        return this.mSpeechTimestamp;
    }

    public long getWuwTagBeginSampleIndex() {
        return this.mWuwTagBeginSampleIndex;
    }

    public long getWuwTagBeginTimestamp() {
        return this.mWuwTagBeginTimestamp;
    }

    public long getWuwBeginSampleIndex() {
        return this.mWuwBeginSampleIndex;
    }

    public long getWuwBeginTimestamp() {
        return this.mWuwBeginTimestamp;
    }

    public long getWuwEndSampleIndex() {
        return this.mWuwEndSampleIndex;
    }

    public long getWuwEndTimestamp() {
        return this.mWuwEndTimestamp;
    }

    public String toString() {
        return "ThirdPartyVpaEvent{mEvent=" + this.mEvent + ", mPhrase='" + this.mPhrase + "', mZone='" + this.mZone + "', mSpeechTimestamp=" + this.mSpeechTimestamp + ", mSpeechSampleIndex=" + this.mSpeechSampleIndex + ", mWuwTagBeginSampleIndex=" + this.mWuwTagBeginSampleIndex + ", mWuwTagBeginTimestamp=" + this.mWuwTagBeginTimestamp + ", mWuwBeginSampleIndex=" + this.mWuwBeginSampleIndex + ", mWuwBeginTimestamp=" + this.mWuwBeginTimestamp + ", mWuwEndSampleIndex=" + this.mWuwEndSampleIndex + ", mWuwEndTimestamp=" + this.mWuwEndTimestamp + '}';
    }

    public enum Event {
        WUW_DETECTED,
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
