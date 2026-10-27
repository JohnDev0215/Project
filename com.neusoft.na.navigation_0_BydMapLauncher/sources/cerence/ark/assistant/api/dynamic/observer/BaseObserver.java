package cerence.ark.assistant.api.dynamic.observer;

import cerence.ark.assistant.api.ArkAssistant;
import cerence.ark.assistant.api.IObserver;
import cerence.ark.assistant.common.Logger;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseObserver implements IObserver {
    private static final String TAG = "BaseObserver";

    @Override // cerence.ark.assistant.api.IObserver
    public void onContentChanged(String str) {
        Logger.info(TAG, str + " has been changed.");
        ArkAssistant.get().notifyContentChanged(str);
    }
}
