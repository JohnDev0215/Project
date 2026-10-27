package cerence.ark.assistant.api.notification;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SpeechInputTimerBegin extends AbsNotification {
    private int mListeningTimeout;

    public SpeechInputTimerBegin(String str) {
        super(str);
        Logger.info(TAG, Utils.format("SpeechInputTimerBegin: %s", str));
    }

    @Override // cerence.ark.assistant.api.notification.AbsNotification
    protected void parseParam(String str) throws JSONException {
        this.mListeningTimeout = new JSONObject(str).optInt(JsonKey.NOTIFICATION_LS_TIMEOUT);
    }

    public int getListeningTimeout() {
        return this.mListeningTimeout;
    }
}
