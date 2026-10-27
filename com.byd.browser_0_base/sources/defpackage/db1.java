package defpackage;

import java.util.List;

/* JADX INFO: compiled from: MainDispatcherFactory.kt */
/* JADX INFO: loaded from: classes.dex */
public interface db1 {
    cb1 createDispatcher(List<? extends db1> list);

    int getLoadPriority();

    String hintOnError();
}
