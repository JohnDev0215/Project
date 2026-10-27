package kotlinx.coroutines.flow;

/* JADX INFO: compiled from: StateFlow.kt */
/* JADX INFO: loaded from: classes.dex */
public interface MutableStateFlow<T> extends StateFlow<T>, MutableSharedFlow<T> {
    boolean compareAndSet(T t6, T t7);

    @Override // kotlinx.coroutines.flow.StateFlow
    T getValue();

    void setValue(T t6);
}
