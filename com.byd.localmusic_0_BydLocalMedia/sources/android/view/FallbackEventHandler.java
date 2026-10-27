package android.view;

/* JADX INFO: loaded from: classes3.dex */
public interface FallbackEventHandler {
    boolean dispatchKeyEvent(KeyEvent keyEvent);

    void preDispatchKeyEvent(KeyEvent keyEvent);

    void setView(View view);
}
