package cerence.ark.assistant.api.notification;

import cerence.ark.assistant.library.JsonKey;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SpeechLevelUpdated extends AbsNotification {
    private int mSpeechLevel;

    public SpeechLevelUpdated(String str) {
        super(str);
    }

    @Override // cerence.ark.assistant.api.notification.AbsNotification
    protected void parseParam(String str) throws JSONException {
        this.mSpeechLevel = new JSONObject(str).optInt(JsonKey.NOTIFICATION_SPEECH_LEVEL);
    }

    public int getSpeechLevel() {
        return this.mSpeechLevel;
    }
}
