package cerence.ark.assistant.api.notification;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;

/* JADX INFO: loaded from: classes2.dex */
public class SpeechInputTimerEnd extends AbsNotification {
    @Override // cerence.ark.assistant.api.notification.AbsNotification
    protected void parseParam(String str) {
    }

    public SpeechInputTimerEnd(String str) {
        super(str);
        Logger.info(TAG, Utils.format("SpeechInputTimerEnd: %s", str));
    }
}
