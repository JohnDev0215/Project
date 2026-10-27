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
public class DialogStateChanged extends AbsNotification {
    private State mState;

    public DialogStateChanged(String str, String str2) {
        super(str, str2);
    }

    @Override // cerence.ark.assistant.api.notification.AbsNotification
    protected void parseParam(String str) {
        try {
            String strOptString = new JSONObject(str).optString(JsonKey.NOTIFICATION_CURRENT_STATE);
            if (!TextUtils.isEmpty(strOptString)) {
                this.mState = State.fromString(strOptString);
            } else {
                Logger.error(TAG, "current state is empty");
            }
        } catch (JSONException e) {
            Logger.error(TAG, "createDialogState: parse json exception", e);
        }
    }

    public State getState() {
        return this.mState;
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
