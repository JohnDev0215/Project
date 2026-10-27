package cerence.ark.assistant.framework;

/* JADX INFO: loaded from: classes.dex */
public interface FrameworkMessageListener {
    boolean onAbortRequest(String str);

    void onNotificationReceived(String str);

    void onRequestReceived(String str);
}
