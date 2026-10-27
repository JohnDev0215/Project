package com.neusoft.na.dltdroid.service;

import java.nio.channels.Selector;
import kotlin.Metadata;
import rx.Observable;

/* JADX INFO: compiled from: ILogSource.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0005H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\rH&¨\u0006\u000f"}, d2 = {"Lcom/neusoft/na/dltdroid/service/ILogSource;", "", "getClientCount", "", "getClientCountObservable", "Lrx/Observable;", "getMessageObservable", "Lcom/neusoft/na/dltdroid/service/DltMessage;", "register", "", "selector", "Ljava/nio/channels/Selector;", "start", "", "stop", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface ILogSource {
    int getClientCount();

    Observable<Integer> getClientCountObservable();

    Observable<DltMessage> getMessageObservable();

    void register(Selector selector);

    boolean start();

    boolean stop();
}
