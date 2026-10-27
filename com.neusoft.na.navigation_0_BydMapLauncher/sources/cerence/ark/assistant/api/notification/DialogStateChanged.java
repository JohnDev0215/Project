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
public class DialogStateChanged extends AbsNotification {
    private String mExtra;
    private State mState;

    public DialogStateChanged(String str) {
        super(str);
        Logger.info(TAG, Utils.format("DialogStateChanged: %s", str));
    }

    @Override // cerence.ark.assistant.api.notification.AbsNotification
    protected void parseParam(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        String strOptString = jSONObject.optString(JsonKey.NOTIFICATION_CURRENT_STATE);
        if (!TextUtils.isEmpty(strOptString)) {
            this.mState = State.fromString(strOptString);
        } else {
            Logger.error(TAG, "current state is empty");
        }
        this.mExtra = jSONObject.optString(JsonKey.NOTIFICATION_EXTRA);
    }

    public State getState() {
        return this.mState;
    }

    public String getExtra() {
        return this.mExtra;
    }

    public enum State {
        IDLE,
        LISTENING_WUW,
        LISTENING_CMD,
        CAPTURING,
        PROCESSING,
        RECORDING;

        private static final Map<String, State> STATE_MAP = new HashMap(16);

        static {
            for (State state : values()) {
                STATE_MAP.put(state.toString(), state);
            }
        }

        public static State fromString(String str) {
            return STATE_MAP.get(str.toUpperCase(Locale.US));
        }
    }
}
