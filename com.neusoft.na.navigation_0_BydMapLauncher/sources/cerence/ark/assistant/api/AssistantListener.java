package cerence.ark.assistant.api;

import cerence.ark.assistant.framework.ErrorListener;

/* JADX INFO: loaded from: classes2.dex */
public interface AssistantListener extends ErrorListener {
    void onDestroyed();

    void onInitialized();

    void onStarted();

    void onStopped();
}
