package io.objectbox.exception;

/* JADX INFO: loaded from: classes5.dex */
public interface DbExceptionListener {
    void onDbException(Exception exc);

    static void cancelCurrentException() {
        DbExceptionListenerJni.nativeCancelCurrentException();
    }
}
