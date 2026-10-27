package kotlinx.coroutines.flow;

import java.util.List;

/* JADX INFO: compiled from: SharedFlow.kt */
/* JADX INFO: loaded from: classes.dex */
public interface SharedFlow<T> extends Flow<T> {
    List<T> getReplayCache();
}
