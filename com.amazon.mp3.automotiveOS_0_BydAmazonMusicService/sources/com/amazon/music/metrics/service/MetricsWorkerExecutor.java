package com.amazon.music.metrics.service;

import com.amazon.music.metrics.service.worker.EventWorker;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes4.dex */
public class MetricsWorkerExecutor implements MetricsWorkerConductor {
    private ExecutorService mExecutor;

    @Override // com.amazon.music.metrics.service.MetricsWorkerConductor
    public void execute(EventWorker eventWorker) {
        getExecutor().execute(eventWorker);
    }

    @Override // com.amazon.music.metrics.service.MetricsWorkerConductor
    public synchronized void onAllWorkersComplete() {
        ExecutorService executorService = this.mExecutor;
        if (executorService != null) {
            executorService.shutdown();
            this.mExecutor = null;
        }
    }

    private synchronized ExecutorService getExecutor() {
        if (this.mExecutor == null) {
            this.mExecutor = Executors.newCachedThreadPool();
        }
        return this.mExecutor;
    }
}
