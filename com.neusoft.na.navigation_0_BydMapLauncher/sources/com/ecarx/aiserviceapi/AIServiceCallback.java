package com.ecarx.aiserviceapi;

/* JADX INFO: loaded from: classes2.dex */
public interface AIServiceCallback {
    void onAIStateChangeObserver(int state);

    void onTTSStateChangeByTypeObserver(int instanceType, int state);

    void onTTSStateChangeObserver(int state);

    int readMessage(int type, String contactName, String phoneNumber);

    int sendMessage(int type, String contactName, String phoneNumber, String messageContent);

    int updateMessageContent(String content, boolean isFinal);
}
