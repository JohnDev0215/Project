package com.amazon.music.metrics.service;

import com.amazon.music.metrics.service.worker.EventWorker;

/* JADX INFO: loaded from: classes4.dex */
public interface MetricsWorkerConductor {
    void execute(EventWorker eventWorker);

    void onAllWorkersComplete();
}
