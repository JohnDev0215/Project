package com.android.server.telecom;

import android.os.Handler;
import android.telecom.Logging.Runnable;
import com.android.server.telecom.TelecomSystem;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class LoggedHandlerExecutor implements Executor {
    private Handler mHandler;
    private TelecomSystem.SyncRoot mLock;
    private String mSessionName;

    public LoggedHandlerExecutor(Handler handler, String str, TelecomSystem.SyncRoot syncRoot) {
        this.mHandler = handler;
        this.mSessionName = str;
        this.mLock = syncRoot;
    }

    @Override // java.util.concurrent.Executor
    public void execute(final Runnable runnable) {
        this.mHandler.post(new Runnable(this, this.mSessionName, this.mLock) { // from class: com.android.server.telecom.LoggedHandlerExecutor.1
            public void loggedRun() {
                runnable.run();
            }
        }.prepare());
    }
}
