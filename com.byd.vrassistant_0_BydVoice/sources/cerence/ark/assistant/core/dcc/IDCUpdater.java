package cerence.ark.assistant.core.dcc;

/* JADX INFO: loaded from: classes.dex */
public interface IDCUpdater<T> {
    void destroy();

    void getContent(String str, IDCCallback<T> iDCCallback);
}
