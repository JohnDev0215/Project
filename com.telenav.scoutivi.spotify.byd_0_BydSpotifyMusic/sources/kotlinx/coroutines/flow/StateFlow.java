package kotlinx.coroutines.flow;

/* JADX INFO: compiled from: StateFlow.kt */
/* JADX INFO: loaded from: classes.dex */
public interface StateFlow<T> extends SharedFlow<T> {
    T getValue();
}
