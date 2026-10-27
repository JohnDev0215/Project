package kotlinx.coroutines.internal;

import java.util.List;
import kotlinx.coroutines.v1;

/* JADX INFO: compiled from: MainDispatcherFactory.kt */
/* JADX INFO: loaded from: classes.dex */
public interface MainDispatcherFactory {
    v1 createDispatcher(List<? extends MainDispatcherFactory> list);

    int getLoadPriority();

    String hintOnError();
}
