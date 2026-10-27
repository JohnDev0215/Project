package cerence.ark.assistant.library.ipctransaction;

/* JADX INFO: loaded from: classes2.dex */
public interface IAssemblerListener<T> {
    default void onError() {
    }

    void onFinal(T t);

    default void onPartial(T t) {
    }
}
