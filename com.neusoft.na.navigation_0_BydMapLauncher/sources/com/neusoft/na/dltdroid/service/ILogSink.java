package com.neusoft.na.dltdroid.service;

import androidx.core.app.NotificationCompat;
import java.nio.channels.Selector;
import kotlin.Metadata;

/* JADX INFO: compiled from: ILogSink.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\nH&¨\u0006\f"}, d2 = {"Lcom/neusoft/na/dltdroid/service/ILogSink;", "", "logMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Lcom/neusoft/na/dltdroid/service/DltMessage;", "register", "selector", "Ljava/nio/channels/Selector;", "start", "", "stop", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface ILogSink {
    void logMessage(DltMessage msg);

    void register(Selector selector);

    boolean start();

    boolean stop();
}
