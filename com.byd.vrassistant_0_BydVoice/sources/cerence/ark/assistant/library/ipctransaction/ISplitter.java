package cerence.ark.assistant.library.ipctransaction;

/* JADX INFO: loaded from: classes.dex */
public interface ISplitter<T> {

    public interface SplitterListener<K> {
        void onSplit(K k, boolean z, boolean z2);
    }

    void split(T t, SplitterListener<T> splitterListener);
}
