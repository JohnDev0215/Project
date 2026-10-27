package cerence.ark.assistant.api.notification;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.JsonKey;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SpeechLevelUpdated extends AbsNotification {
    private int mSpeechLevel;

    public SpeechLevelUpdated(String str, String str2) {
        super(str, str2);
    }

    @Override // cerence.ark.assistant.api.notification.AbsNotification
    protected void parseParam(String str) {
        try {
            this.mSpeechLevel = new JSONObject(str).optInt(JsonKey.NOTIFICATION_SPEECH_LEVEL);
        } catch (JSONException e) {
            Logger.error(TAG, "createSpeechLevelUpdated: parse json exception", e);
        }
    }

    public int getSpeechLevel() {
        return this.mSpeechLevel;
    }
}
