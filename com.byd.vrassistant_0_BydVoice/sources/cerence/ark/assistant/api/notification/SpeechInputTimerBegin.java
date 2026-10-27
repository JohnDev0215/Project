package cerence.ark.assistant.api.notification;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.JsonKey;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SpeechInputTimerBegin extends AbsNotification {
    private int mListeningTimeout;

    public SpeechInputTimerBegin(String str, String str2) {
        super(str, str2);
    }

    @Override // cerence.ark.assistant.api.notification.AbsNotification
    protected void parseParam(String str) {
        try {
            this.mListeningTimeout = new JSONObject(str).optInt(JsonKey.NOTIFICATION_LS_TIMEOUT);
        } catch (JSONException e) {
            Logger.error(TAG, "createSpeechInputTimerBegin: parse json exception", e);
        }
    }

    public int getListeningTimeout() {
        return this.mListeningTimeout;
    }
}
