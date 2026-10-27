package com.ecarx.aiserviceapi.sdk;

import android.os.RemoteException;
import com.ecarx.aiserviceapi.AIServiceCallback;
import com.ecarx.aiserviceapi.IAIServiceObserver;

/* JADX INFO: loaded from: classes2.dex */
public class AIServiceObserver extends IAIServiceObserver.Stub {
    private final AIServiceCallback mCallback;

    public AIServiceObserver(AIServiceCallback callback) {
        this.mCallback = callback;
    }

    @Override // com.ecarx.aiserviceapi.IAIServiceObserver
    public void onAIStateChangeObserver(int state) throws RemoteException {
        this.mCallback.onAIStateChangeObserver(state);
    }

    @Override // com.ecarx.aiserviceapi.IAIServiceObserver
    public void onTTSStateChangeObserver(int state) throws RemoteException {
        this.mCallback.onTTSStateChangeObserver(state);
    }

    @Override // com.ecarx.aiserviceapi.IAIServiceObserver
    public void onTTSStateChangeByTypeObserver(int instanceType, int state) throws RemoteException {
        this.mCallback.onTTSStateChangeByTypeObserver(instanceType, state);
    }

    @Override // com.ecarx.aiserviceapi.IAIServiceObserver
    public int sendMessage(int type, String contactName, String phoneNumber, String messageContent) throws RemoteException {
        return this.mCallback.sendMessage(type, contactName, phoneNumber, messageContent);
    }

    @Override // com.ecarx.aiserviceapi.IAIServiceObserver
    public int readMessage(int type, String contactName, String phoneNumber) throws RemoteException {
        return this.mCallback.readMessage(type, contactName, phoneNumber);
    }

    @Override // com.ecarx.aiserviceapi.IAIServiceObserver
    public int updateMessageContent(String content, boolean isFinal) throws RemoteException {
        return this.mCallback.updateMessageContent(content, isFinal);
    }
}
