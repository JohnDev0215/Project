package cerence.ark.assistant.library.ipctransaction;

/* JADX INFO: loaded from: classes2.dex */
public interface ISplitter<T> {

    public interface SplitterListener<K> {
        void onSplit(K k, String str, int i, int i2);
    }

    void split(T t, SplitterListener<T> splitterListener);
}
