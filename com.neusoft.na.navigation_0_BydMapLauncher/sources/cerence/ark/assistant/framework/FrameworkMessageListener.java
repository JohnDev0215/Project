package cerence.ark.assistant.framework;

import cerence.ark.assistant.dm.data.RecognitionErrorCode;

/* JADX INFO: loaded from: classes2.dex */
public interface FrameworkMessageListener {
    boolean onAbortRequest(String str);

    void onBeginningOfSpeech();

    void onEndOfSpeech();

    void onError(RecognitionErrorCode recognitionErrorCode);

    void onNotificationReceived(String str);

    void onPromptAudio(String str, byte[] bArr, int i);

    void onPromptError(String str, String str2, int i);

    void onPromptEvent(String str, String str2, int i);

    void onReadyForSpeech();

    void onRecognizedSpeech(String str, boolean z);

    void onRequestReceived(String str, String str2);

    void onRmsChanged(int i);
}
